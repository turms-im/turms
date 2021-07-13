import Foundation
import PromiseKit
import Starscream

public class ConnectionService: BaseService {

    private let initialWsUrl: String
    private let initialConnectTimeout: TimeInterval

    private var disconnectPromises: [Resolver<()>] = []

    private var onConnectedListeners: [() -> ()] = []
    private var onDisconnectedListeners: [(ConnectionDisconnectInfo) -> ()] = []
    private var messageListeners: [(Data) -> ()] = []

    init(stateStore: StateStore, wsUrl: String? = nil, connectTimeout: TimeInterval? = nil) {
        self.initialWsUrl = wsUrl ?? "ws://localhost:10510"
        self.initialConnectTimeout = connectTimeout ?? 30
        super.init(stateStore)
    }

    private func resetStates() {
        fulfillDisconnectPromises()
    }

    // Listeners

    func addOnConnectedListener(_ listener: @escaping () -> ()) {
        onConnectedListeners.append(listener)
    }

    func addOnDisconnectedListener(_ listener: @escaping (ConnectionDisconnectInfo) -> ()) {
        onDisconnectedListeners.append(listener)
    }

    func addMessageListener(_ listener: @escaping (Data) -> ()) {
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

    public func connect(wsUrl: String? = nil, connectTimeout: TimeInterval? = nil) -> Promise<()> {
        return Promise { seal in
            if stateStore.isConnected {
                seal.reject(TurmsBusinessError(.clientSessionAlreadyEstablished))
                return
            }
            resetStates()
            let request = URLRequest(
                url: URL(string: wsUrl ?? initialWsUrl)!,
                timeoutInterval: connectTimeout ?? initialConnectTimeout)
            let websocket = WebSocket(request: request)
            websocket.onEvent = { [weak self] event in
                switch event {
                case .binary(let data):
                    self?.notifyMessageListeners(data)
                case .connected:
                    self?.onWebSocketOpen()
                    seal.fulfill(())
                case .disconnected(let reason, let websocketCode):
                    self?.onWebsocketClose(statusCode: Int(websocketCode), reason: reason)
                    seal.reject(NSError())
                case .cancelled: // disconnect by client and won't trigger the "disconnected" event
                    self?.onWebsocketClose()
                    seal.reject(NSError())
                case .error(let error):
                    self?.onWebsocketClose(error: error)
                    seal.reject(error ?? NSError())
                default: break
                }
            }
            websocket.connect()
            stateStore.websocket = websocket
        }
    }

    public func disconnect() -> Promise<()> {
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

    override func close() -> Promise<()> {
        return disconnect()
    }

}
