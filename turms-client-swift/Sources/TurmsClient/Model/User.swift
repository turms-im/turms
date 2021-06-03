import Foundation

public struct User {
    public let userId: Int64
    public var password: String? = nil
    public var deviceType: DeviceType? = nil
    public var onlineStatus: UserStatus? = nil
    public var location: Location? = nil
}
