import Foundation

private class RequestContext {
    let continuation: UnsafeContinuation<TurmsNotification, any Error>
    let timeoutTask: Task<Void, Never>?

    init(continuation: UnsafeContinuation<TurmsNotification, any Error>, timeoutTask: Task<Void, Never>?) {
        self.continuation = continuation
        self.timeoutTask = timeoutTask
    }
}

class DriverMessageService: BaseService {
    private let requestTimeout: TimeInterval
    private let minRequestInterval: TimeInterval
    private var notificationListeners: [(TurmsNotification) -> Void] = []
    private var requestIdToContext: [Int64: RequestContext] = [:]
    private var lastRequestDate = Date(timeIntervalSince1970: 0)
    private let requestLock = Lock()

    init(stateStore: StateStore, requestTimeout: TimeInterval? = nil, minRequestInterval: TimeInterval? = nil) {
        self.requestTimeout = requestTimeout ?? 60
        self.minRequestInterval = minRequestInterval ?? 0
        super.init(stateStore)
    }

    // Listeners

    func addNotificationListener(_ listener: @escaping (TurmsNotification) -> Void) {
        notificationListeners.append(listener)
    }

    func notifyNotificationListener(_ notification: TurmsNotification) {
        for notificationListener in notificationListeners {
            notificationListener(notification)
        }
    }

    // Request and notification

    func sendRequest(_ populator: (inout TurmsRequest) -> Void) async throws -> TurmsNotification {
        var request = TurmsRequest()
        populator(&request)
        return try await sendRequest(&request)
    }

    func sendRequest(_ request: inout TurmsRequest) async throws -> TurmsNotification {
        return try await withUnsafeThrowingContinuation { continuation in
            if case .createSessionRequest = request.kind {
                if stateStore.isSessionOpen {
                    return continuation.resume(throwing: ResponseError(code: .clientSessionAlreadyEstablished))
                }
            } else if !stateStore.isConnected || !stateStore.isSessionOpen {
                return continuation.resume(throwing: ResponseError(code: .clientSessionHasBeenClosed))
            }
            guard let tcp = stateStore.tcp else {
                return continuation.resume(throwing: ResponseError(code: .clientSessionHasBeenClosed))
            }
            let now = Date()
            let difference = now.timeIntervalSince1970 - lastRequestDate.timeIntervalSince1970
            let isFrequent = minRequestInterval > 0 && difference <= minRequestInterval
            if isFrequent {
                return continuation.resume(throwing: ResponseError(code: .clientRequestsTooFrequent))
            }
            requestLock.locked {
                let requestId = generateRandomId()
                request.requestID = requestId
                let data: Data
                do {
                    data = try request.serializedData()
                } catch {
                    return continuation.resume(throwing: ResponseError(code: .invalidRequest, reason: "Failed to serialize the request: \(request)", cause: error))
                }
                var timeoutTask: Task<Void, Never>?
                if requestTimeout > 0 {
                    timeoutTask = Task {
                        do {
                            try await Task.sleep(nanoseconds: UInt64(requestTimeout * 1_000_000_000))
                            requestLock.locked {
                                requestIdToContext.removeValue(forKey: requestId)?.continuation.resume(throwing: ResponseError(code: .requestTimeout))
                            }
                        } catch {}
                    }
                }
                requestIdToContext.updateValue(RequestContext(continuation: continuation, timeoutTask: timeoutTask), forKey: request.requestID)
                stateStore.lastRequestDate = now
                Task {
                    do {
                        try await tcp.writeVarIntLengthAndBytes(data)
                    } catch {
                        requestLock.locked {
                            requestIdToContext.removeValue(forKey: requestId)?.continuation.resume(throwing: ResponseError(code: .networkError, reason: "Failed to write", cause: error))
                        }
                    }
                }
            }
        }
    }

    func didReceiveNotification(_ notification: TurmsNotification) {
        let isResponse = !notification.hasRelayedRequest && notification.hasRequestID
        if isResponse {
            let requestId = notification.requestID
            requestLock.locked {
                if let context = requestIdToContext.removeValue(forKey: requestId) {
                    context.timeoutTask?.cancel()
                    let continuation = context.continuation
                    if notification.hasCode {
                        let code = Int(notification.code)
                        if ResponseStatusCode.isSuccessCode(code) {
                            continuation.resume(returning: notification)
                        } else {
                            if notification.hasReason {
                                continuation.resume(throwing: ResponseError(code: code, reason: notification.reason))
                            } else {
                                continuation.resume(throwing: ResponseError(code: code))
                            }
                        }
                    } else {
                        continuation.resume(throwing: ResponseError(code: ResponseStatusCode.invalidNotification, reason: "The code is missing"))
                    }
                }
            }
        }
        notifyNotificationListener(notification)
    }

    private func generateRandomId() -> Int64 {
        var id: Int64
        repeat {
            id = Int64.random(in: 1 ... Int64.max)
        } while requestIdToContext.keys.contains(id)
        return id
    }

    private func rejectRequests(_ e: ResponseError) {
        requestLock.locked {
            repeat {
                requestIdToContext.popFirst()?.value.continuation.resume(throwing: e)
            } while !requestIdToContext.isEmpty
        }
    }

    override func close() async {
        onDisconnected()
    }

    override func onDisconnected(_ error: Error? = nil) {
        rejectRequests(ResponseError(code: .clientSessionHasBeenClosed, cause: error))
    }
}