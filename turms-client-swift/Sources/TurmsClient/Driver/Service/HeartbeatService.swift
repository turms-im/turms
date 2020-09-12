import Foundation
import PromiseKit

class HeartbeatService {
    private static let HEARTBEAT_INTERVAL: TimeInterval = 120
    private static let HEARTBEAT_REQUEST = Data()

    private let stateStore: StateStore
    private let createQueue = DispatchQueue(label: "im.turms.turmsclient.heartbeatservice.createqueue")

    private let heartbeatInterval: TimeInterval
    private let minRequestsInterval: TimeInterval
    private var heartbeatTimer: Timer?
    private var heartbeatPromises: [Resolver<Void>] = []

    public init(stateStore: StateStore, minRequestsInterval: TimeInterval?, heartbeatInterval: TimeInterval? = nil) {
        self.stateStore = stateStore
        self.minRequestsInterval = minRequestsInterval ?? 0
        self.heartbeatInterval = heartbeatInterval ?? HeartbeatService.HEARTBEAT_INTERVAL
    }

    func start() {
        createQueue.sync {
            if heartbeatTimer == nil || !heartbeatTimer!.isValid {
                heartbeatTimer = Timer.scheduledTimer(
                    timeInterval: heartbeatInterval,
                    target: self,
                    selector: #selector(checkAndSendHeartbeat),
                    userInfo: nil,
                    repeats: true)
            }
        }
    }

    func stop() {
        heartbeatTimer?.invalidate()
    }

    func send() -> Promise<Void> {
        return Promise { seal in
            if stateStore.isConnected {
                stateStore.websocket!.write(data: HeartbeatService.HEARTBEAT_REQUEST) {
                    self.heartbeatPromises.append(seal)
                }
            } else {
                seal.reject(TurmsBusinessError(.clientSessionAlreadyEstablished))
            }
        }
    }

    func reset() {
        stop()
        start()
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
        let difference = Date().timeIntervalSince1970 - stateStore.lastRequestDate.timeIntervalSince1970
        if difference > minRequestsInterval {
            send()
        }
    }
}
