import Foundation
import PromiseKit

public class TurmsDriver {
    let stateStore: StateStore

    private let connectionService: ConnectionService
    private let heartbeatService: HeartbeatService
    private let messageService: DriverMessageService

    public init(host: String? = nil, port: UInt16? = nil, connectTimeout: TimeInterval? = nil, requestTimeout: TimeInterval? = nil, minRequestInterval: TimeInterval? = nil, heartbeatInterval: TimeInterval? = nil) {
        stateStore = StateStore()

        connectionService = ConnectionService(stateStore: stateStore, host: host, port: port, connectTimeout: connectTimeout)
        heartbeatService = HeartbeatService(stateStore: stateStore, heartbeatInterval: heartbeatInterval)
        messageService = DriverMessageService(stateStore: stateStore, requestTimeout: requestTimeout, minRequestInterval: minRequestInterval)

        initConnectionService()
    }

    // Lifecycle Hook

    private func initConnectionService() {
        connectionService.addOnDisconnectedListener { [weak self] in
            self?.onConnectionDisconnected($0)
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

    public func connect(host: String? = nil, port: UInt16? = nil, connectTimeout: TimeInterval? = nil, certificatePinning: CertificatePinning? = nil) -> Promise<Void> {
        return connectionService.connect(host: host, port: port, connectTimeout: connectTimeout, certificatePinning: certificatePinning)
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

    public func addOnDisconnectedListener(listener: @escaping (Error?) -> Void) {
        connectionService.addOnDisconnectedListener(listener)
    }

    // Message Service

    public func send(_ populator: (inout TurmsRequest) throws -> Void) -> Promise<TurmsNotification> {
        var request = TurmsRequest()
        do {
            try populator(&request)
        } catch {
            return Promise(error: error)
        }
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

    private func onConnectionDisconnected(_ error: Error?) {
        stateStore.reset()
        heartbeatService.onDisconnected(error)
        messageService.onDisconnected(error)
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
