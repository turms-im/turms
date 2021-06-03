public extension TurmsNotification {

    func getFirstId() throws -> Int64 {
        let ids = data.ids
        if ids.values.count > 0 {
            return ids.values[0]
        } else {
            throw TurmsBusinessError(TurmsStatusCode.invalidResponse)
        }
    }

    func isSuccessful() -> Bool {
        return hasCode && TurmsStatusCode.isSuccessCode(Int(code))
    }

    func isServerError() -> Bool {
        return hasCode && TurmsStatusCode.isServerError(Int(code))
    }

}
