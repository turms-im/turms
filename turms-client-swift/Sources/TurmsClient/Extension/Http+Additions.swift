import Foundation

public extension HTTPURLResponse {
    var isSuccessful: Bool {
        return 200 ..< 300 ~= statusCode
    }

    var isNotSuccessful: Bool {
        return !isSuccessful
    }
}
