import Foundation
import PromiseKit
import Starscream

public class TurmsDriver {
    let stateStore: StateStore

    private let connectionService: ConnectionService
    private let heartbeatService: HeartbeatService
    private let messageService: DriverMessageService

    public init(wsUrl: String? = nil, connectTimeout: TimeInterval? = nil, requestTimeout: TimeInterval? = nil, minRequestInterval: TimeInterval? = nil, heartbeatInterval: TimeInterval? = nil) {
        stateStore = StateStore()

        connectionService = ConnectionService(stateStore: stateStore, wsUrl: wsUrl, connectTimeout: connectTimeout)
        heartbeatService = HeartbeatService(stateStore: stateStore, heartbeatInterval: heartbeatInterval)
        messageService = DriverMessageService(stateStore: stateStore, requestTimeout: requestTimeout, minRequestInterval: minRequestInterval)

        initConnectionService()
    }

    // Lifecycle Hook

    private func initConnectionService() {
        connectionService.addOnDisconnectedListener { [weak self] _ in
            self?.onConnectionDisconnected()
        }
        connectionService.addMessageListener { [weak self] in
            self?.onMessage($0)
        }
    }

    public func close() -> Promise<Void> {
        return when(resolved: connectionService.close(), heartbeatService.close(), messageService.close())
            .asVoid()
    }

    // Heartbeat Service

    public func startHeartbeat() {
        heartbeatService.start()
    }

    public func stopHeartbeat() {
        heartbeatService.stop()
    }

    public func sendHeartbeat() -> Promise<Void> {
        return heartbeatService.send()
    }

    public var isHeartbeatRunning: Bool {
        return heartbeatService.isRunning
    }

    // Connection Service

    public func connect(wsUrl: String? = nil, connectTimeout: TimeInterval? = nil) -> Promise<Void> {
        return connectionService.connect(wsUrl: wsUrl, connectTimeout: connectTimeout)
    }

    public func disconnect() -> Promise<Void> {
        return connectionService.disconnect()
    }

    public var isConnected: Bool {
        return stateStore.isConnected
    }

    // Connection Listeners

    public func addOnConnectedListener(_ listener: @escaping () -> Void) {
        connectionService.addOnConnectedListener(listener)
    }

    public func addOnDisconnectedListener(listener: @escaping (ConnectionDisconnectInfo) -> Void) {
        connectionService.addOnDisconnectedListener(listener)
    }

    // Message Service

    public func send(_ populator: (inout TurmsRequest) -> Void) -> Promise<TurmsNotification> {
        var request = TurmsRequest()
        populator(&request)
        let notification = messageService.sendRequest(&request)
        if case .createSessionRequest = request.kind {
            notification.done { _ in
                self.heartbeatService.start()
            }
        }
        return notification
    }

    public func addNotificationListener(_ listener: @escaping (TurmsNotification) -> Void) {
        messageService.addNotificationListener(listener)
    }

    // Intermediary functions as a mediator between services

    private func onConnectionDisconnected() {
        stateStore.reset()
        heartbeatService.onDisconnected()
        messageService.onDisconnected()
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
            if heartbeatService.rejectHeartbeatPromisesIfFail(notification) {
                return
            }
            if notification.hasData, case .userSession = notification.data.kind! {
                stateStore.sessionId = notification.data.userSession.sessionID
                stateStore.serverId = notification.data.userSession.serverID
            } else if notification.hasCloseStatus {
                stateStore.isSessionOpen = false
            }
            messageService.didReceiveNotification(notification)
        }
    }
}
