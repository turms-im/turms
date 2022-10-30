import Foundation

public extension Array {
    func toMap() throws -> [String: String] {
        if count % 2 != 0 {
            throw ResponseError(
                code: .illegalArgument,
                reason: "The number of elements must be even"
            )
        }
        var dict: [String: String] = [:]
        for i in stride(from: 0, through: count, by: 2) {
            dict[String(describing: self[i])] = String(describing: self[i + 1])
        }
        return dict
    }
}
