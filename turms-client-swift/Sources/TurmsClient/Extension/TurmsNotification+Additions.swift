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
    func getFirstId() throws -> Int64 {
        let ids = ids
        if ids.values.count > 0 {
            return ids.values[0]
        } else {
            throw ResponseError(.invalidResponse)
        }
    }
}
