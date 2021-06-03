import Foundation

public struct Location {
    public var longitude: Float
    public var latitude: Float

    public func toString() -> String {
        return "\(longitude):\(latitude)"
    }
}
