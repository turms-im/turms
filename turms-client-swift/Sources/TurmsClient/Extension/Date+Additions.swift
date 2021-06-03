import Foundation

extension Date {
    public func toMillis() -> Int64 {
        return Int64(timeIntervalSince1970)
    }
}
