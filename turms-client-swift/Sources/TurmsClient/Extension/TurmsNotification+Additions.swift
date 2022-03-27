public extension TurmsNotification {
    func getFirstId() throws -> Int64 {
        let ids = data.ids
        if ids.values.count > 0 {
            return ids.values[0]
        } else {
            throw ResponseError(ResponseStatusCode.invalidResponse)
        }
    }

    func isSuccessful() -> Bool {
        return hasCode && ResponseStatusCode.isSuccessCode(Int(code))
    }

    func isServerError() -> Bool {
        return hasCode && ResponseStatusCode.isServerError(Int(code))
    }
}
