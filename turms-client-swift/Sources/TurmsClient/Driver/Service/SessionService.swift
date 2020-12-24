import Foundation

class SessionService {
    private let stateStore: StateStore
    private let statusChangeQueue = DispatchQueue(label: "im.turms.turmsclient.sessionservice.statuschangequeue")

    var sessionId: String? {
        get { return stateStore.sessionId }
        set { stateStore.sessionId = newValue }
    }

    // Status

    private var _currentStatus: SessionStatus = .closed

    var currentStatus: SessionStatus {
        get { return _currentStatus }
        set { _currentStatus = newValue }
    }

    var isConnected: Bool {
        return currentStatus == .connected
    }

    var isClosed: Bool {
        return currentStatus == .closed
    }

    private var onSessionConnectedListeners: [() -> ()] = []
    private var onSessionDisconnectedListeners: [(SessionDisconnectInfo) -> ()] = []
    private var onSessionClosedListeners: [(SessionDisconnectInfo) -> ()] = []

    init(stateStore: StateStore) {
        self.stateStore = stateStore
    }

    // Listeners

    func addOnSessionConnectedListener(_ listener: @escaping () -> ()) {
        onSessionConnectedListeners.append(listener)
    }

    func addOnSessionDisconnectedListener(_ listener: @escaping (SessionDisconnectInfo) -> ()) {
        onSessionDisconnectedListeners.append(listener)
    }

    func addOnSessionClosedListener(_ listener: @escaping (SessionDisconnectInfo) -> ()) {
        onSessionClosedListeners.append(listener)
    }

    func notifyOnSessionConnectedListeners() {
        statusChangeQueue.sync {
            if currentStatus == .disconnected || currentStatus == .closed {
                onSessionConnectedListeners.forEach { $0() }
                currentStatus = .connected
            }
        }
    }

    func notifyOnSessionDisconnectedListeners(_ info: SessionDisconnectInfo) {
        statusChangeQueue.sync {
            if currentStatus == .connected {
                onSessionDisconnectedListeners.forEach { $0(info) }
                currentStatus = .disconnected
            }
        }
    }

    func notifyOnSessionClosedListeners(_ info: SessionDisconnectInfo) {
        statusChangeQueue.sync {
            switch currentStatus {
                case .connected:
                    notifyOnSessionDisconnectedListeners(info)
                case .disconnected:
                    currentStatus = .disconnected
                case .closed:
                    return
            }
            onSessionClosedListeners.forEach { $0(info) }
            currentStatus = .closed
        }
    }
}
