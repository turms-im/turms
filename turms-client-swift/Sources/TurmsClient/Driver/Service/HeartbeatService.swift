import Foundation

class HeartbeatService: BaseService {
    private static let HEARTBEAT_FAILURE_REQUEST_ID: Int64 = -100
    private static let HEARTBEAT_REQUEST = Data([0])

    private let lock = Lock()

    private let heartbeatInterval: TimeInterval
    private let heartbeatTimerInterval: TimeInterval
    private var lastHeartbeatRequestDate: TimeInterval = 0
    private var heartbeatTimer: Timer?
    private var heartbeatContinuations: [UnsafeContinuation<Void, any Error>] = []

    init(stateStore: StateStore, heartbeatInterval: TimeInterval? = nil) {
        self.heartbeatInterval = heartbeatInterval ?? 120
        heartbeatTimerInterval = max(1, self.heartbeatInterval / 10)
        super.init(stateStore)
    }

    var isRunning: Bool {
        return heartbeatTimer?.isValid == true
    }

    func start() {
        lock.locked {
            if isRunning {
                return
            }
            heartbeatTimer = Timer.scheduledTimer(
                timeInterval: heartbeatInterval,
                target: self,
                selector: #selector(checkAndSendHeartbeat),
                userInfo: nil,
                repeats: true
            )
        }
    }

    func stop() {
        heartbeatTimer?.invalidate()
    }

    func send() async throws {
        return try await withUnsafeThrowingContinuation { continuation in
            if !stateStore.isConnected || !stateStore.isSessionOpen {
                return continuation.resume(throwing: ResponseError(code: .clientSessionHasBeenClosed))
            }
            Task {
                guard let tcp = stateStore.tcp else {
                    return continuation.resume(throwing: ResponseError(code: .clientSessionHasBeenClosed))
                }
                do {
                    try await tcp.write(HeartbeatService.HEARTBEAT_REQUEST)
                } catch {
                    return continuation.resume(throwing: error)
                }
                lock.locked {
                    self.heartbeatContinuations.append(continuation)
                }
            }
        }
    }

    func fulfillHeartbeatContinuations() {
        lock.locked {
            repeat {
                heartbeatContinuations.popLast()?.resume()
            } while !heartbeatContinuations.isEmpty
        }
    }

    func rejectHeartbeatContinuationsIfFail(_ notification: TurmsNotification) -> Bool {
        if notification.hasRequestID, notification.requestID == HeartbeatService.HEARTBEAT_FAILURE_REQUEST_ID {
            rejectHeartbeatContinuations(ResponseError(notification))
            return true
        }
        return false
    }

    func rejectHeartbeatContinuations(_ error: Error) {
        lock.locked {
            repeat {
                heartbeatContinuations.popLast()?.resume(throwing: error)
            } while !heartbeatContinuations.isEmpty
        }
    }

    @objc func checkAndSendHeartbeat() async throws {
        let now = Date().timeIntervalSince1970
        let difference = min(now - stateStore.lastRequestDate.timeIntervalSince1970,
                             now - lastHeartbeatRequestDate)
        if difference > heartbeatInterval {
            do {
                try await send()
            } catch {
                // TODO: log
                return
            }
            lastHeartbeatRequestDate = max(lastHeartbeatRequestDate, now)
        }
    }

    override func close() async {
        onDisconnected()
    }

    override func onDisconnected(_ error: Error? = nil) {
        stop()
        rejectHeartbeatContinuations(ResponseError(code: .clientSessionHasBeenClosed, cause: error))
    }
}
