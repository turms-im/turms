import Foundation
import PromiseKit

class DriverMessageService: BaseService {
    private let requestTimeout: TimeInterval
    private let minRequestInterval: TimeInterval?
    private var notificationListeners: [(TurmsNotification) -> ()] = []
    private var requestMap: [Int64: Resolver<TurmsNotification>] = [:]
    private var lastRequestDate = Date(timeIntervalSince1970: 0)

    init(stateStore: StateStore, requestTimeout: TimeInterval? = nil, minRequestInterval: TimeInterval? = nil) {
        self.requestTimeout = requestTimeout ?? 60
        self.minRequestInterval = minRequestInterval ?? 0
        super.init(stateStore)
    }

    // Listeners

    func addNotificationListener(_ listener: @escaping (TurmsNotification) -> ()) {
        notificationListeners.append(listener)
    }

    func notifyNotificationListener(_ notification: TurmsNotification) {
        notificationListeners.forEach {
            $0(notification)
        }
    }

    // Request and notification

    func sendRequest(_ populator: (inout TurmsRequest) -> ()) -> Promise<TurmsNotification> {
        var request = TurmsRequest()
        populator(&request)
        return sendRequest(&request)
    }

    func sendRequest(_ request: inout TurmsRequest) -> Promise<TurmsNotification> {
        return Promise { seal in
            if case .createSessionRequest = request.kind {
                if stateStore.isSessionOpen {
                    return seal.reject(TurmsBusinessError(.clientSessionAlreadyEstablished))
                }
            } else if !stateStore.isConnected || !stateStore.isSessionOpen {
                return seal.reject(TurmsBusinessError(.clientSessionHasBeenClosed))
            }
            let now = Date()
            let difference = now.timeIntervalSince1970 - lastRequestDate.timeIntervalSince1970
            let isFrequent = minRequestInterval != nil && minRequestInterval! > 0 && difference <= minRequestInterval!
            if isFrequent {
                return seal.reject(TurmsBusinessError(.clientRequestsTooFrequent))
            }
            request.requestID = generateRandomId()
            if requestTimeout > 0 {
                after(.seconds(Int(requestTimeout))).done {
                    seal.reject(TurmsBusinessError(TurmsStatusCode.requestTimeout))
                }
            }
            let data = try request.serializedData()
            requestMap.updateValue(seal, forKey: request.requestID)
            stateStore.lastRequestDate = now
            stateStore.websocket!.write(data: data)
        }
    }

    func didReceiveNotification(_ notification: TurmsNotification) {
        let isResponse = !notification.hasRelayedRequest && notification.hasRequestID
        if isResponse {
            let requestId = notification.requestID
            let handler = requestMap[requestId]
            if notification.hasCode {
                let code = Int(notification.code)
                if TurmsStatusCode.isSuccessCode(code) {
                    handler?.fulfill(notification)
                } else {
                    if notification.hasReason {
                        handler?.reject(TurmsBusinessError(code, notification.reason))
                    } else {
                        handler?.reject(TurmsBusinessError(code))
                    }
                }
            } else {
                handler?.reject(TurmsBusinessError(TurmsStatusCode.invalidNotification, "The code is missing"))
            }
        }
        notifyNotificationListener(notification)
    }

    private func generateRandomId() -> Int64 {
        var id: Int64
        repeat {
            id = Int64.random(in: 1...Int64.max)
        } while requestMap.keys.contains(id)
        return id
    }
}
