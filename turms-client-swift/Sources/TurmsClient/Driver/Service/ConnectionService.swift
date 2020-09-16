import Foundation
import PromiseKit
import Starscream

public class ConnectionService {
    private static let HEADER_REASON = "X-API-Reason"

    private static let REQUEST_ID_FIELD = "rid"
    private static let USER_ID_FIELD = "uid"
    private static let PASSWORD_FIELD = "pwd"
    private static let DEVICE_TYPE_FIELD = "dt"
    private static let USER_ONLINE_STATUS_FIELD = "us"
    private static let USER_LOCATION_FIELD = "loc"
    private static let LOCATION_DELIMITER = ":"

    private let stateStore: StateStore

    private var isClosedByClient = false
    private var disconnectPromises: [Resolver<()>] = []
    private var connectOptions = ConnectOptions()

    private var onConnectedListeners: [() -> ()] = []
    private var onDisconnectedListeners: [(SessionDisconnectInfo) -> ()] = []
    private var onClosedListeners: [(SessionDisconnectInfo) -> ()] = []
    private var onMessageListeners: [(Data) -> ()] = []

    public init(stateStore: StateStore) {
        self.stateStore = stateStore
    }

    private func resetStates() {
        stateStore.connectionRequestId = nil
        isClosedByClient = false
        fulfillDisconnectPromises()
    }

    // Listeners

    func addOnConnectedListener(_ listener: @escaping () -> ()) {
        onConnectedListeners.append(listener)
    }

    func addOnDisconnectedListener(_ listener: @escaping (SessionDisconnectInfo) -> ()) {
        onDisconnectedListeners.append(listener)
    }

    func addOnClosedListener(_ listener: @escaping (SessionDisconnectInfo) -> ()) {
        onClosedListeners.append(listener)
    }

    func addOnMessageListener(_ listener: @escaping (Data) -> ()) {
        onMessageListeners.append(listener)
    }

    private func notifyOnConnectedListeners() {
        onConnectedListeners.forEach { $0() }
    }

    private func notifyOnDisconnectedListeners(_ info: SessionDisconnectInfo) {
        onDisconnectedListeners.forEach { $0(info) }
    }

    private func notifyOnClosedListeners(_ info: SessionDisconnectInfo) {
        onClosedListeners.forEach { $0(info) }
    }

    private func notifyOnMessageListeners(_ message: Data) {
        onMessageListeners.forEach { $0(message) }
    }

    private func fulfillDisconnectPromises() {
        repeat {
            disconnectPromises.popLast()?.fulfill(())
        } while !disconnectPromises.isEmpty
    }

    // Connection

    public func connect(wsUrl: String, connectTimeout: TimeInterval?, userId: Int64, password: String, deviceType: DeviceType? = nil, userOnlineStatus: UserStatus? = nil, location: Position? = nil) -> Promise<()> {
        return Promise { seal in
            if stateStore.isConnected {
                seal.reject(TurmsBusinessError(.clientSessionAlreadyEstablished))
            } else {
                resetStates()
                connectOptions.wsUrl = wsUrl
                connectOptions.connectTimeout = connectTimeout
                connectOptions.userId = userId
                connectOptions.password = password
                connectOptions.deviceType = deviceType
                connectOptions.userOnlineStatus = userOnlineStatus
                connectOptions.location = location
                var request = URLRequest(
                    url: URL(string: wsUrl)!,
                    timeoutInterval: connectTimeout ?? 0)
                let connectRequestId = Int64.random(in: 1..<Int64.max)
                request.addValue(String(connectRequestId), forHTTPHeaderField: ConnectionService.REQUEST_ID_FIELD)
                request.addValue(String(userId), forHTTPHeaderField: ConnectionService.USER_ID_FIELD)
                request.addValue(password, forHTTPHeaderField: ConnectionService.PASSWORD_FIELD)
                if let type = deviceType {
                    request.addValue(type.toString(), forHTTPHeaderField: ConnectionService.DEVICE_TYPE_FIELD)
                }
                if let onlineStatus = userOnlineStatus {
                    request.addValue(onlineStatus.toString(), forHTTPHeaderField: ConnectionService.USER_ONLINE_STATUS_FIELD)
                }
                if let userLocation = location {
                    request.addValue(userLocation.toString(), forHTTPHeaderField: ConnectionService.USER_LOCATION_FIELD)
                }
                let websocket = WebSocket(request: request)
                websocket.onEvent = { event in
                    switch event {
                    case .binary(let data):
                        self.notifyOnMessageListeners(data)
                    case .connected:
                        self.onWebSocketOpen()
                        seal.fulfill(())
                    case .disconnected(let reason, let websocketCode):
                        self.onWebsocketClose(false, statusCode: Int(websocketCode), reason: reason)
                            .done {
                                seal.fulfill(())
                            }.catch {
                                seal.reject($0)
                            }
                    case .cancelled: // disconnect by client and won't trigger the "disconnected" event
                        self.onWebsocketClose(true)
                    case .error(let error):
                        self.onWebsocketClose(false, error: error)
                            .done {
                                seal.fulfill(())
                            }.catch {
                                seal.reject(error ?? $0)
                            }
                    default: break
                    }
                }
                websocket.connect()
                stateStore.websocket = websocket
            }
        }
    }

    private func reconnect(_ host: String? = nil) -> Promise<()> {
        if let hostStr = host {
            let isSecure = connectOptions.wsUrl != nil && connectOptions.wsUrl!.starts(with: "wss://")
            let urlProtocol = isSecure ? "wss://" : "ws://"
            let url = "\(urlProtocol)\(hostStr)"
            connectOptions.wsUrl = url
        }
        return connect(wsUrl: connectOptions.wsUrl!, connectTimeout: connectOptions.connectTimeout, userId: connectOptions.userId!, password: connectOptions.password!, deviceType: connectOptions.deviceType, userOnlineStatus: connectOptions.userOnlineStatus, location: connectOptions.location)
    }

    public func disconnect() -> Promise<()> {
        return Promise { seal in
            if stateStore.isConnected {
                isClosedByClient = true
                stateStore.isConnected = false
                disconnectPromises.append(seal)
                stateStore.websocket!.disconnect()
            } else {
                seal.reject(TurmsBusinessError(.clientSessionAlreadyEstablished))
            }
        }
    }

    // Lifecycle hooks

    private func onWebSocketOpen() {
        stateStore.isConnected = true
        notifyOnConnectedListeners()
    }

    private func onWebsocketClose(_ isClosedByClient: Bool, statusCode: Int? = nil, reason: String? = nil, error: Error? = nil) -> Promise<()> {
        fulfillDisconnectPromises()
        let wasConnected = stateStore.isConnected
        stateStore.isConnected = false
        stateStore.websocket = nil

        var status: TurmsCloseStatus?
        if isClosedByClient {
            status = .disconnectedByClient
        } else if let code = statusCode {
            status = TurmsCloseStatus(rawValue: code)
        }

        var isRedirectSignal = false
        var redirectHost: String?
        if let closeStatus = status, closeStatus == .redirect {
            isRedirectSignal = true
        } else if case .notAnUpgrade(let code, let headers) = error as? HTTPUpgradeError {
            isRedirectSignal = code == 307
            redirectHost = headers["X-API-Reason"]
        }
        if isRedirectSignal, redirectHost == nil, let host = reason, host.count > 0 {
            redirectHost = host
        }

        let shouldReconnect = isRedirectSignal && redirectHost != nil
        let disconnectInfo = SessionDisconnectInfo(wasConnected: wasConnected, isClosedByClient: isClosedByClient, isReconnecting: shouldReconnect, closeStatus: status, webSocketStatusCode: statusCode, webSocketReason: reason, error: error)
        notifyOnDisconnectedListeners(disconnectInfo)
        if isRedirectSignal, let host = redirectHost {
            return reconnect(host)
                .recover {
                    let closeInfo = SessionDisconnectInfo(wasConnected: wasConnected, isClosedByClient: isClosedByClient, isReconnecting: false, closeStatus: status, webSocketStatusCode: statusCode, webSocketReason: reason, error: error)
                    self.notifyOnClosedListeners(closeInfo)
                    throw $0
                }
        } else {
            let closeInfo = SessionDisconnectInfo(wasConnected: wasConnected, isClosedByClient: isClosedByClient, isReconnecting: false, closeStatus: status, webSocketStatusCode: statusCode, webSocketReason: reason, error: error)
            notifyOnClosedListeners(closeInfo)
            return Promise(error: TurmsBusinessError(.failed))
        }
    }
}
