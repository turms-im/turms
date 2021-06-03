import Foundation
import PromiseKit

class HeartbeatService: BaseService {
    private static let HEARTBEAT_REQUEST = Data()

    private let createQueue = DispatchQueue(label: "im.turms.turmsclient.heartbeatservice.createqueue")

    private let heartbeatInterval: TimeInterval
    private let heartbeatTimerInterval: TimeInterval
    private var lastHeartbeatRequestDate: TimeInterval = 0
    private var heartbeatTimer: Timer?
    private var heartbeatPromises: [Resolver<()>] = []

    init(stateStore: StateStore, heartbeatInterval: TimeInterval? = nil) {
        self.heartbeatInterval = heartbeatInterval ?? 120
        self.heartbeatTimerInterval = max(1, self.heartbeatInterval / 10)
        super.init(stateStore)
    }

    var isRunning: Bool {
        get {
            return heartbeatTimer?.isValid == true
        }
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
                repeats: true)
        }
    }

    func stop() {
        heartbeatTimer?.invalidate()
    }

    func send() -> Promise<Void> {
        return Promise { seal in
            if !stateStore.isConnected || !stateStore.isSessionOpen {
                seal.reject(TurmsBusinessError(.clientSessionHasBeenClosed))
                return
            }
            stateStore.websocket?.write(data: HeartbeatService.HEARTBEAT_REQUEST) {
                self.heartbeatPromises.append(seal)
            }
        }
    }

    func fulfillHeartbeatPromises() {
        repeat {
            heartbeatPromises.popLast()?.fulfill(())
        } while !heartbeatPromises.isEmpty
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

    override func close() -> Promise<()> {
        onDisconnected()
        return Promise.value(())
    }

    override func onDisconnected() {
        stop();
        rejectHeartbeatPromises(TurmsBusinessError(.clientSessionHasBeenClosed));
    }

}
