import Foundation
import PromiseKit

class HeartbeatService: BaseService {
    private static let HEARTBEAT_FAILURE_REQUEST_ID: Int64 = -100
    private static let HEARTBEAT_REQUEST = Data([0])

    private let createQueue = DispatchQueue(label: "im.turms.turmsclient.heartbeatservice.createqueue")

    private let heartbeatInterval: TimeInterval
    private let heartbeatTimerInterval: TimeInterval
    private var lastHeartbeatRequestDate: TimeInterval = 0
    private var heartbeatTimer: Timer?
    private var heartbeatPromises: [Resolver<Void>] = []

    init(stateStore: StateStore, heartbeatInterval: TimeInterval? = nil) {
        self.heartbeatInterval = heartbeatInterval ?? 120
        heartbeatTimerInterval = max(1, self.heartbeatInterval / 10)
        super.init(stateStore)
    }

    var isRunning: Bool {
        return heartbeatTimer?.isValid == true
    }

    func start() {
        createQueue.sync {
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

    func send() -> Promise<Void> {
        return Promise { seal in
            if !stateStore.isConnected || !stateStore.isSessionOpen {
                seal.reject(ResponseError(code: .clientSessionHasBeenClosed))
                return
            }
            stateStore.tcp!.write(HeartbeatService.HEARTBEAT_REQUEST) { error in
                if let error = error {
                    seal.reject(error)
                    return
                }
                self.heartbeatPromises.append(seal)
            }
        }
    }

    func fulfillHeartbeatPromises() {
        repeat {
            heartbeatPromises.popLast()?.fulfill(())
        } while !heartbeatPromises.isEmpty
    }

    func rejectHeartbeatPromisesIfFail(_ notification: TurmsNotification) -> Bool {
        if notification.hasRequestID, notification.requestID == HeartbeatService.HEARTBEAT_FAILURE_REQUEST_ID {
            rejectHeartbeatPromises(ResponseError(notification))
            return true
        }
        return false
    }

    func rejectHeartbeatPromises(_ error: Error) {
        repeat {
            heartbeatPromises.popLast()?.reject(error)
        } while !heartbeatPromises.isEmpty
    }

    @objc func checkAndSendHeartbeat() {
        let now = Date().timeIntervalSince1970
        let difference = min(now - stateStore.lastRequestDate.timeIntervalSince1970,
                             now - lastHeartbeatRequestDate)
        if difference > heartbeatInterval {
            send()
            lastHeartbeatRequestDate = now
        }
    }

    override func close() -> Promise<Void> {
        onDisconnected()
        return Promise.value(())
    }

    override func onDisconnected(_ error: Error? = nil) {
        stop()
        rejectHeartbeatPromises(ResponseError(code: .clientSessionHasBeenClosed, cause: error))
    }
}
