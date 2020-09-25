import Foundation
import PromiseKit

class HeartbeatService {
    private static let DEFAULT_HEARTBEAT_INTERVAL: TimeInterval = 120
    private static let HEARTBEAT_REQUEST = Data()

    private let stateStore: StateStore
    private let createQueue = DispatchQueue(label: "im.turms.turmsclient.heartbeatservice.createqueue")

    private let heartbeatInterval: TimeInterval
    private let minRequestInterval: TimeInterval
    private var heartbeatTimer: Timer?
    private var heartbeatPromises: [Resolver<Void>] = []

    public init(stateStore: StateStore, minRequestInterval: TimeInterval?, heartbeatInterval: TimeInterval? = nil) {
        self.stateStore = stateStore
        self.minRequestInterval = minRequestInterval ?? 0
        self.heartbeatInterval = heartbeatInterval ?? HeartbeatService.DEFAULT_HEARTBEAT_INTERVAL
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
                stateStore.websocket?.write(data: HeartbeatService.HEARTBEAT_REQUEST) {
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
        if difference > minRequestInterval {
            send()
        }
    }
}
