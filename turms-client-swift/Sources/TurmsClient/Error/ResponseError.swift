public struct ResponseError: Error {
    public let code: Int
    public let reason: String?

    init(_ code: ResponseStatusCode, _ reason: String? = nil) {
        self.code = code.rawValue
        self.reason = reason
    }

    init(_ code: Int, _ reason: String? = nil) {
        self.code = code
        self.reason = reason
    }

    init(_ notification: TurmsNotification) {
        code = Int(notification.code)
        reason = notification.hasReason ? notification.reason : nil
    }
}
