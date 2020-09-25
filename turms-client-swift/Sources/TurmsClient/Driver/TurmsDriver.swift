import Foundation
import PromiseKit
import Starscream

public class TurmsDriver {
    private var _onSessionConnected: (() -> ())?
    private var _onSessionDisconnected: ((SessionDisconnectInfo) -> ())?
    private var _onSessionClosed: ((SessionDisconnectInfo) -> ())?

    public var onSessionConnected: (() -> ())? {
        get { return _onSessionConnected }
        set { _onSessionConnected = newValue }
    }

    public var onSessionDisconnected: ((SessionDisconnectInfo) -> ())? {
        get { return _onSessionDisconnected }
        set { _onSessionDisconnected = newValue }
    }

    public var onSessionClosed: ((SessionDisconnectInfo) -> ())? {
        get { return _onSessionClosed }
        set { _onSessionClosed = newValue }
    }

    private let stateStore: StateStore

    private let connectionService: ConnectionService
    private let heartbeatService: HeartbeatService
    private let messageService: DriverMessageService
    private let sessionService: SessionService

    public init(wsUrl: String? = nil, connectTimeout: TimeInterval? = nil, requestTimeout: TimeInterval? = nil, minRequestInterval: TimeInterval? = nil, heartbeatInterval: TimeInterval? = nil) {
        self.stateStore = StateStore()

        self.connectionService = ConnectionService(stateStore: stateStore, wsUrl: wsUrl, connectTimeout: connectTimeout)
        self.heartbeatService = HeartbeatService(stateStore: stateStore, minRequestInterval: minRequestInterval, heartbeatInterval: heartbeatInterval)
        self.messageService = DriverMessageService(stateStore: stateStore, requestTimeout: requestTimeout, minRequestInterval: minRequestInterval)
        self.sessionService = SessionService(stateStore: stateStore)

        initConnectionService()
        initSessionService()
    }

    // Initializers

    private func initConnectionService() {
        connectionService.addOnConnectedListener { [weak self] in
            self?.onConnectionConnected()
        }
        connectionService.addOnDisconnectedListener { [weak self] in
            self?.onConnectionDisconnected($0)
        }
        connectionService.addOnClosedListener { [weak self] in
            self?.onConnectionClosed($0)
        }
        connectionService.addOnMessageListener { [weak self] in
            self?.onMessage($0)
        }
    }

    private func initSessionService() {
        sessionService.addOnSessionConnectedListeners { [weak self] in
            self?.onSessionConnected?()
        }
        sessionService.addOnSessionDisconnectedListeners { [weak self] in
            self?.onSessionDisconnected?($0)
        }
        sessionService.addOnSessionClosedListeners { [weak self] in
            self?.onSessionClosed?($0)
        }
    }

    // Session Service

    public var currentStatus: SessionStatus {
        return sessionService.currentStatus
    }

    public var isConnected: Bool {
        return sessionService.isConnected
    }

    public var isClosed: Bool {
        return sessionService.isClosed
    }

    // Heartbeat Service

    public func startHeartbeat() {
        heartbeatService.start()
    }

    public func stopHeartbeat() {
        heartbeatService.stop()
    }

    public func sendHeartbeat() -> Promise<()> {
        return heartbeatService.send()
    }

    public func resetHeartbeat() {
        heartbeatService.reset()
    }

    // Connection Service

    public func disconnect() -> Promise<()> {
        return connectionService.disconnect()
    }

    public func connect(userId: Int64, password: String, deviceType: DeviceType? = nil, userOnlineStatus: UserStatus? = nil, location: Position? = nil) -> Promise<()> {
        return connectionService.connect(userId: userId, password: password, deviceType: deviceType, userOnlineStatus: userOnlineStatus, location: location)
    }

    // Message Service

    public func send(_ populator: (inout RequestBuilder) -> ()) -> Promise<TurmsNotification> {
        return messageService.send(populator)
    }

    public func send(_ request: TurmsRequest) -> Promise<TurmsNotification> {
        return messageService.send(request)
    }

    public func addOnNotificationListener(_ listener: @escaping (TurmsNotification) -> ()) {
        messageService.addOnNotificationListener(listener)
    }

    // Intermediary functions as a mediator between services

    private func onConnectionConnected() {
        heartbeatService.start()
        sessionService.notifyOnSessionConnectedListeners()
    }

    private func onConnectionDisconnected(_ info: SessionDisconnectInfo) {
        heartbeatService.stop()
        heartbeatService.rejectHeartbeatPromises(TurmsBusinessError(.clientSessionHasBeenClosed))
    }

    private func onConnectionClosed(_ info: SessionDisconnectInfo) {
        sessionService.notifyOnSessionClosedListeners(info)
    }

    private func onMessage(_ message: Data) {
        if message.isEmpty {
            heartbeatService.fulfillHeartbeatPromises()
        } else {
            var notification: TurmsNotification
            do {
                notification = try TurmsNotification(serializedData: message)
            } catch {
                Logger.error("Failed to parse TurmsNotification: %@", String(describing: error))
                return
            }
            if notification.hasData, case .session = notification.data.kind! {
                sessionService.sessionId = notification.data.session.sessionID
            }
            messageService.didReceiveNotification(notification)
        }
    }
}
