import Foundation
import PromiseKit
import Starscream

public class TurmsDriver {
    private static let REQUEST_ID_FIELD = "rid"
    private static let USER_ID_FIELD = "uid"
    private static let PASSWORD_FIELD = "pwd"
    private static let DEVICE_TYPE_FIELD = "dt"
    private static let USER_ONLINE_STATUS_FIELD = "us"
    private static let USER_LOCATION_FIELD = "loc"
    private static let USER_DEVICE_DETAILS = "dd"

    private var heartbeatInterval: Int = 20

    private weak var turmsClient: TurmsClient!
    private var websocket: WebSocket?
    private var heartbeatTimer: Timer?
    private var isConnected = false

    public var onNotificationListeners: [(TurmsNotification) -> Void] = []
    public var onClose: ((TurmsCloseStatus?, Int?, String?, Error?) -> Void)?
    private var onDisconnectResolver: Resolver<Void>?
    private var heartbeatCallbacks: [Resolver<Void>] = []

    private var url: String
    private var connectionTimeout: Int
    private var minRequestsInterval: Int?
    private var requestsMap: [Int64: Resolver<TurmsNotification>] = [:]
    private var lastRequestDate = Date(timeIntervalSince1970: 0)
    private var isLastRequestHeartbeat = false

    private var address: String?
    private var sessionId: String?

    public init(_ turmsClient: TurmsClient, url: String? = nil, connectionTimeout: Int? = nil, minRequestsInterval: Int? = nil) {
        self.turmsClient = turmsClient
        self.url = url ?? "ws://localhost:9510"
        self.connectionTimeout = connectionTimeout ?? 10
        self.minRequestsInterval = minRequestsInterval
    }

    public func sendHeartbeat() -> Promise<Void> {
        return Promise { seal in
            if connected() {
                setLastRequestRecord(true, Date())
                websocket!.write(data: Data()) {
                    DispatchQueue.global().sync {
                        self.heartbeatCallbacks.append(seal)
                    }
                }
            } else {
                seal.reject(TurmsBusinessException(.clientSessionAlreadyEstablished))
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
                websocket!.disconnect()
            } else {
                seal.reject(TurmsBusinessException(.clientSessionAlreadyEstablished))
            }
        }
    }

    public func connect(userId: Int64, password: String, deviceType: DeviceType? = nil, userOnlineStatus: UserStatus? = nil, location: Position? = nil) -> Promise<Void> {
        return Promise { seal in
            if connected() {
                seal.reject(TurmsBusinessException(.clientSessionAlreadyEstablished))
            } else {
                var request = URLRequest(
                    url: URL(string: url)!,
                    timeoutInterval: TimeInterval(connectionTimeout))
                request.addValue(String(userId), forHTTPHeaderField: TurmsDriver.USER_ID_FIELD)
                request.addValue(password, forHTTPHeaderField: TurmsDriver.PASSWORD_FIELD)
                request.addValue(deviceType != nil ? deviceType!.toString() : "IOS", forHTTPHeaderField: TurmsDriver.DEVICE_TYPE_FIELD)
                if let onlineStatus = userOnlineStatus {
                    request.addValue(onlineStatus.toString(), forHTTPHeaderField: TurmsDriver.USER_ONLINE_STATUS_FIELD)
                }
                if let userLocation = location {
                    request.addValue(userLocation.toString(), forHTTPHeaderField: TurmsDriver.USER_LOCATION_FIELD)
                }
                websocket = WebSocket(request: request)
                websocket!.onEvent = { event in
                    switch event {
                        case .binary(let data):
                            if data.count == 0 {
                                DispatchQueue.global().sync {
                                    for callback in self.heartbeatCallbacks {
                                        callback.fulfill(())
                                    }
                                    self.heartbeatCallbacks.removeAll()
                                }
                                return
                            }
                            let notification = try! TurmsNotification(serializedData: data)
                            if notification.hasData, case .session = notification.data.kind! {
                                self.address = notification.data.session.address
                                self.sessionId = notification.data.session.sessionID
                            } else if notification.hasRequestID {
                                let requestId = notification.requestID.value
                                let handler = self.requestsMap[requestId]
                                if notification.hasCode {
                                    let code = notification.code.value
                                    if TurmsStatusCode.isSuccess(code) {
                                        handler?.fulfill(notification)
                                    } else {
                                        if notification.hasReason {
                                            handler?.reject(TurmsBusinessException(code, notification.reason.value))
                                        } else {
                                            handler?.reject(TurmsBusinessException(code))
                                        }
                                    }
                                } else {
                                    handler?.fulfill(notification)
                                }
                            }
                            for listener in self.onNotificationListeners {
                                listener(notification)
                            }
                        case .connected:
                            self.onWebsocketOpen()
                            seal.fulfill(())
                        case .disconnected(let reason, let websocketCode):
                            self.onWebsocketClose(false, Int(websocketCode), reason)
                            self.onDisconnectResolver?.fulfill(())
                        case .cancelled: // disconnect by client and won't trigger the disconnected event
                            self.onWebsocketClose(true)
                            self.onDisconnectResolver?.fulfill(())
                        case .error(let error as HTTPUpgradeError):
                            self.onWebsocketError(error, seal)
                        default: break
                    }
                }
                websocket!.connect()
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
            try! sendHeartbeat().wait()
        }
    }

    func send(_ populator: (inout RequestBuilder) -> Void) -> Promise<TurmsNotification> {
        var builder = RequestBuilder()
        populator(&builder)
        let request = builder
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
                    let data = try! request.serializedData()
                    resetHeartBeatTimer()
                    requestsMap.updateValue(seal, forKey: request.requestID.value)
                    websocket?.write(data: data)
                } else {
                    seal.reject(TurmsBusinessException(.clientRequestsTooFrequent))
                }
            } else {
                seal.reject(TurmsBusinessException(.clientSessionHasBeenClosed))
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

    private func onWebsocketClose(_ isClosedByClient: Bool, _ statusCode: Int? = nil, _ reason: String? = nil) {
        isConnected = false
        heartbeatTimer?.invalidate()
        var status: TurmsCloseStatus?
        if isClosedByClient {
            status = .disconnectedByClient
        } else {
            status = statusCode != nil ? TurmsCloseStatus(rawValue: statusCode!) : nil
        }
        if status == .redirect, let address = reason, address.count > 0 {
            reconnect(address)
        } else {
            onClose?(status, statusCode, reason, nil)
        }
    }

    private func onWebsocketError(_ error: HTTPUpgradeError, _ seal: Resolver<Void>) {
        isConnected = false
        heartbeatTimer?.invalidate()
        if turmsClient.userService.userId != nil, turmsClient.userService.password != nil {
            switch error {
                case .notAnUpgrade(let code, let headers):
                    if code == 307, let address = headers["reason"], address.count > 0 {
                        reconnect(address)
                    }
                    fallthrough
                default:
                    seal.reject(error)
            }
        } else {
            seal.reject(error)
        }
    }

    private func reconnect(_ address: String) {
        let isSecure = url.starts(with: "wss://")
        url = "\(isSecure ? "wss://" : "ws://")\(address)"
        try! connect(userId: turmsClient.userService.userId!, password: turmsClient.userService.password!, deviceType: turmsClient.userService.deviceType, userOnlineStatus: turmsClient.userService.userOnlineStatus, location: turmsClient.userService.location).wait()
    }
}

extension DeviceType {
    public func toString() -> String {
        switch self {
            case .desktop:
                return "DESKTOP"
            case .browser:
                return "BROWSER"
            case .ios:
                return "IOS"
            case .android:
                return "ANDROID"
            case .others:
                return "OTHERS"
            case .unknown:
                return "UNKNOWN"
            case .UNRECOGNIZED:
                return ""
        }
    }
}

extension UserStatus {
    public func toString() -> String {
        switch self {
            case .available:
                return "AVAILABLE"
            case .offline:
                return "OFFLINE"
            case .invisible:
                return "INVISIBLE"
            case .busy:
                return "BUSY"
            case .doNotDisturb:
                return "DO_NOT_DISTURB"
            case .away:
                return "AWAY"
            case .beRightBack:
                return "BE_RIGHT_BACK"
            case .UNRECOGNIZED:
                return ""
        }
    }
}
