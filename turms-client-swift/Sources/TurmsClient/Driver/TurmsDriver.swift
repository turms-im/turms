import Foundation
import PromiseKit
import Starscream

public class TurmsDriver {
    private var heartbeatInterval: Int = 20

    private var websocket: WebSocket?
    private var heartbeatTimer: Timer?
    private var isConnected = false
    public var onMessage: ((TurmsNotification) -> Void)?
    public var onClose: ((Bool, TurmsStatusCode, Error?) -> Void)?
    private var onDisconnectResolver: Resolver<Void>?

    private var url: String
    private var connectionTimeout: Int
    private var minRequestsInterval: Int?
    private var requestsMap: [Int64: Resolver<TurmsNotification>] = [:]
    private var lastRequestDate = Date(timeIntervalSince1970: 0)
    private var isLastRequestHeartbeat = false
    private var userId: Int64?
    private var password: String?
    private var address: String?

    public init(url: String? = nil, connectionTimeout: Int? = nil, minRequestsInterval: Int? = nil) {
        self.url = url ?? "ws://localhost:9510"
        self.connectionTimeout = connectionTimeout ?? 10
        self.minRequestsInterval = minRequestsInterval
    }

    public func sendHeartbeat() -> Promise<Void> {
        return Promise { seal in
            if connected() {
                setLastRequestRecord(true, Date())
                websocket!.write(data: Data()) { seal.fulfill(()) }
            } else {
                seal.reject(TurmsBusinessError(.clientSessionAlreadyEstablished))
            }
        }
    }

    public func connected() -> Bool {
        return websocket != nil && isConnected
    }

    public func disconnect() -> Promise<Void> {
        return Promise { seal in
            if connected() {
                onDisconnectResolver = seal
                websocket?.disconnect()
            } else {
                seal.reject(TurmsBusinessError(.clientSessionAlreadyEstablished))
            }
        }
    }

    public func connect(userId: Int64, password: String, url: String? = nil, connectionTimeout: Int = 10) -> Promise<Void> {
        return Promise { seal in
            if connected() {
                seal.reject(TurmsBusinessError(.clientSessionAlreadyEstablished))
            } else {
                self.userId = userId
                self.password = password
                let request = URLRequest(
                    url: URL(string: url ?? self.url)!,
                    timeoutInterval: TimeInterval(connectionTimeout))
                websocket = WebSocket(request: request)
                websocket!.onEvent = { event in
                    switch event {
                        case .binary(let data):
                            let notification = try! TurmsNotification(serializedData: data)
                            if self.onMessage != nil {
                                self.onMessage!(notification)
                            }
                            let handler: Resolver<TurmsNotification>? = self.requestsMap[notification.requestID.value]
                            handler?.fulfill(notification)
                        case .connected:
                            self.onWebsocketOpen()
                        case .disconnected(let reason, _):
                            self.onWebsocketClose(Int(reason)!)
                            self.onDisconnectResolver?.fulfill(())
                        case .error(let error):
                            self.onWebsocketError(error)
                        default: break
                    }
                }
            }
        }
    }

    public func resetHeartBeatTimer() {
        heartbeatTimer?.invalidate()
        heartbeatTimer = Timer.scheduledTimer(
            timeInterval: TimeInterval(heartbeatInterval),
            target: self,
            selector: #selector(checkAndSendHeartbeat),
            userInfo: nil,
            repeats: true)
    }

    @objc func checkAndSendHeartbeat() {
        let difference = Date().timeIntervalSince1970 - lastRequestDate.timeIntervalSince1970
        if minRequestsInterval == nil || Int(round(difference)) > minRequestsInterval! {
            sendHeartbeat()
        }
    }

    func send(_ requestBuilder: RequestBuilder) -> Promise<TurmsNotification> {
        let request = requestBuilder
            .id(generateRandomId())
            .build()
        return send(request)
    }

    func send(_ request: TurmsRequest) -> Promise<TurmsNotification> {
        return Promise { seal in
            if connected() {
                let now = Date()
                if minRequestsInterval == nil || Int(round(now.timeIntervalSince1970 - lastRequestDate.timeIntervalSince1970)) > minRequestsInterval! {
                    setLastRequestRecord(false, now)
                    let data = try! request.jsonUTF8Data()
                    resetHeartBeatTimer()
                    websocket?.write(data: data)
                } else {
                    seal.reject(TurmsBusinessError(.clientRequestsTooFrequent))
                }
            } else {
                seal.reject(TurmsBusinessError(.clientSessionHasBeenClosed))
            }
        }
    }

    private func setLastRequestRecord(_ isLastRequestHeartbeat: Bool, _ lastRequestDate: Date) {
        self.isLastRequestHeartbeat = isLastRequestHeartbeat
        self.lastRequestDate = lastRequestDate
    }

    private func generateRandomId() -> Int64 {
        var id: Int64
        repeat {
            id = Int64.random(in: 0...Int64.max)
        } while requestsMap.keys.contains(id)
        return id
    }

    private func onWebsocketOpen() {
        resetHeartBeatTimer()
        isConnected = true
    }

    private func onWebsocketClose(_ code: Int) {
        let wasLogged = isConnected
        isConnected = false
        heartbeatTimer?.invalidate()
        onClose?(wasLogged, TurmsStatusCode(rawValue: code)!, nil)
    }

    private func onWebsocketError(_ error: Error?) {
        let wasLogged = isConnected
        isConnected = false
        heartbeatTimer?.invalidate()
        if !wasLogged, userId != nil, error != nil {
            connect(userId: userId!, password: password!, url: url)
        } else {
            onClose?(wasLogged, .failed, error)
        }
    }
}
