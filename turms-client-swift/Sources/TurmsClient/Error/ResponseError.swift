public struct ResponseError: Error {
    public let requestId: Int64?
    public let code: Int
    public let reason: String?

    init(_ requestId: Int64? = nil, _ code: ResponseStatusCode, _ reason: String? = nil) {
        self.requestId = requestId
        self.code = code.rawValue
        self.reason = reason
    }

    init(_ code: ResponseStatusCode, _ reason: String? = nil) {
        requestId = nil
        self.code = code.rawValue
        self.reason = reason
    }

    init(_ code: Int, _ reason: String? = nil) {
        requestId = nil
        self.code = code
        self.reason = reason
    }

    init(_ notification: TurmsNotification) {
        requestId = notification.hasRequestID ? notification.requestID : nil
        code = Int(notification.code)
        reason = notification.hasReason ? notification.reason : nil
    }
}
