import Foundation

public struct ConnectionDisconnectInfo {
    public var wasConnected: Bool
    public var wsUrl: URL?
    public var websocketStatusCode: Int?
    public var websocketReason: String?
    public var error: Error?
}
