import Foundation

public extension Date {
    func toMillis() -> Int64 {
        return Int64(timeIntervalSince1970)
    }
}
