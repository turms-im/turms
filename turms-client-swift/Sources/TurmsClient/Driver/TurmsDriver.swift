import Foundation

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

    public func close() async {
        _ = await [connectionService.close(), heartbeatService.close(), messageService.close()]
    }

    // Heartbeat Service

    public func startHeartbeat() {
        heartbeatService.start()
    }

    public func stopHeartbeat() {
        heartbeatService.stop()
    }

    public func sendHeartbeat() async throws {
        try await heartbeatService.send()
    }

    public var isHeartbeatRunning: Bool {
        return heartbeatService.isRunning
    }

    // Connection Service

    public func connect(host: String? = nil, port: UInt16? = nil, connectTimeout: TimeInterval? = nil, certificatePinning: CertificatePinning? = nil) async throws {
        try await connectionService.connect(host: host, port: port, connectTimeout: connectTimeout, certificatePinning: certificatePinning)
    }

    public func disconnect() async {
        await connectionService.disconnect()
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

    public func send(_ populator: (inout TurmsRequest) throws -> Void) async throws -> TurmsNotification {
        var request = TurmsRequest()
        try populator(&request)
        let notification = try await messageService.sendRequest(&request)
        if case .createSessionRequest = request.kind {
            heartbeatService.start()
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
            heartbeatService.fulfillHeartbeatContinuations()
        } else {
            var notification: TurmsNotification
            do {
                notification = try TurmsNotification(serializedBytes: message)
            } catch {
                Logger.error("Failed to parse TurmsNotification: %@", String(describing: error))
                return
            }
            if heartbeatService.rejectHeartbeatContinuationsIfFail(notification) {
                return
            }
            if notification.hasCloseStatus {
                stateStore.isSessionOpen = false
                messageService.didReceiveNotification(notification)
                Task {
                    // We must close the connection after finishing handling the notification
                    // to ensure notification handlers will always be triggered before connection close handlers.
                    await connectionService.disconnect()
                }
                return
            }
            if notification.hasData, case .userSession = notification.data.kind! {
                stateStore.sessionId = notification.data.userSession.sessionID
                stateStore.serverId = notification.data.userSession.serverID
            }
            messageService.didReceiveNotification(notification)
        }
    }
}
