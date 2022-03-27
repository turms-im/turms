import Foundation
import PromiseKit
import Starscream

public class ConnectionService: BaseService {
    private let initialWsUrl: String
    private let initialConnectTimeout: TimeInterval

    private var disconnectPromises: [Resolver<Void>] = []

    private var onConnectedListeners: [() -> Void] = []
    private var onDisconnectedListeners: [(ConnectionDisconnectInfo) -> Void] = []
    private var messageListeners: [(Data) -> Void] = []

    init(stateStore: StateStore, wsUrl: String? = nil, connectTimeout: TimeInterval? = nil) {
        initialWsUrl = wsUrl ?? "ws://localhost:10510"
        initialConnectTimeout = connectTimeout ?? 30
        super.init(stateStore)
    }

    private func resetStates() {
        fulfillDisconnectPromises()
    }

    // Listeners

    func addOnConnectedListener(_ listener: @escaping () -> Void) {
        onConnectedListeners.append(listener)
    }

    func addOnDisconnectedListener(_ listener: @escaping (ConnectionDisconnectInfo) -> Void) {
        onDisconnectedListeners.append(listener)
    }

    func addMessageListener(_ listener: @escaping (Data) -> Void) {
        messageListeners.append(listener)
    }

    private func notifyOnConnectedListeners() {
        onConnectedListeners.forEach {
            $0()
        }
    }

    private func notifyOnDisconnectedListeners(_ info: ConnectionDisconnectInfo) {
        onDisconnectedListeners.forEach {
            $0(info)
        }
    }

    private func notifyMessageListeners(_ message: Data) {
        messageListeners.forEach {
            $0(message)
        }
    }

    private func fulfillDisconnectPromises() {
        repeat {
            disconnectPromises.popLast()?.fulfill(())
        } while !disconnectPromises.isEmpty
    }

    // Connection

    public func connect(wsUrl: String? = nil, connectTimeout: TimeInterval? = nil) -> Promise<Void> {
        return Promise { seal in
            if stateStore.isConnected {
                seal.reject(ResponseError(.clientSessionAlreadyEstablished))
                return
            }
            resetStates()
            let request = URLRequest(
                url: URL(string: wsUrl ?? initialWsUrl)!,
                timeoutInterval: connectTimeout ?? initialConnectTimeout
            )
            let websocket = WebSocket(request: request)
            websocket.onEvent = { [weak self] event in
                switch event {
                case let .binary(data):
                    self?.notifyMessageListeners(data)
                case .connected:
                    self?.onWebSocketOpen()
                    seal.fulfill(())
                case let .disconnected(reason, websocketCode):
                    self?.onWebsocketClose(statusCode: Int(websocketCode), reason: reason)
                    seal.reject(NSError())
                case .cancelled: // disconnect by client and won't trigger the "disconnected" event
                    self?.onWebsocketClose()
                    seal.reject(NSError())
                case let .error(error):
                    self?.onWebsocketClose(error: error)
                    seal.reject(error ?? NSError())
                default: break
                }
            }
            websocket.connect()
            stateStore.websocket = websocket
        }
    }

    public func disconnect() -> Promise<Void> {
        return Promise { seal in
            if stateStore.isConnected {
                seal.fulfill(())
                return
            }
            disconnectPromises.append(seal)
            stateStore.websocket!.disconnect()
        }
    }

    // Lifecycle hooks

    private func onWebSocketOpen() {
        stateStore.isConnected = true
        notifyOnConnectedListeners()
    }

    private func onWebsocketClose(statusCode: Int? = nil, reason: String? = nil, error: Error? = nil) -> ConnectionDisconnectInfo {
        let wasConnected = stateStore.isConnected
        let url = stateStore.websocket!.request.url
        fulfillDisconnectPromises()
        let disconnectInfo = ConnectionDisconnectInfo(wasConnected: wasConnected, wsUrl: url, websocketStatusCode: statusCode, websocketReason: reason, error: error)
        notifyOnDisconnectedListeners(disconnectInfo)
        return disconnectInfo
    }

    // Base methods

    override func close() -> Promise<Void> {
        return disconnect()
    }
}
