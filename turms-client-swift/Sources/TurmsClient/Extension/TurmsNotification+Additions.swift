public extension TurmsNotification {
    var isSuccess: Bool {
        return hasCode && ResponseStatusCode.isSuccessCode(Int(code))
    }

    var isError: Bool {
        return hasCode && ResponseStatusCode.isErrorCode(Int(code))
    }

    func toResponse<T>(_ dataTransformer: ((TurmsNotification.DataMessage) throws -> T)? = nil) throws -> Response<T> {
        return try Response.fromNotification(self, dataTransformer)
    }
}

public extension TurmsNotification.DataMessage {
    func getLongOrThrow() throws -> Int64 {
        if case let .long(v)? = kind {
            return v
        }
        throw ResponseError(
            code: .invalidResponse,
            reason: "Could not get a long value from the invalid response: \(self)"
        )
    }
}
