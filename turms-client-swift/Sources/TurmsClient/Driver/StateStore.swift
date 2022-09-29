import Foundation

public class StateStore {
    // Connection

    // Note: Do NOT set the property weak because it only be referenced here
    // and it will be deinited unexpectedly if it's weak
    var tcp: TcpClient?
    var isConnected: Bool = false

    // Session
    var isSessionOpen: Bool = false
    var sessionId: String?
    var serverId: String?

    // Request
    var lastRequestDate = Date(timeIntervalSince1970: 0)

    func reset() {
        tcp = nil
        isConnected = false
        isSessionOpen = false
        sessionId = nil
        serverId = nil
        lastRequestDate = Date(timeIntervalSince1970: 0)
    }
}
