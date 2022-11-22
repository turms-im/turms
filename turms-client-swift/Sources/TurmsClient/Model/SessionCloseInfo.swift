import Foundation

public struct SessionCloseInfo {
    public let closeStatus: Int32
    public let businessStatus: Int32?
    public let reason: String?
    public let cause: Error?
}
