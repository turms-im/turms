import Foundation

class ConnectOptions {
    var wsUrl: String?
    var connectTimeout: TimeInterval?

    var userId: Int64?
    var password: String?
    var deviceType: DeviceType?
    var userOnlineStatus: UserStatus?
    var location: Position?
}
