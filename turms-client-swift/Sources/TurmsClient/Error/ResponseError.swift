public struct ResponseError: Error {
    public let requestId: Int64?
    public let code: Int
    public let reason: String?
    public let cause: Error?

    init(requestId: Int64? = nil, code: Int, reason: String? = nil, cause: Error? = nil) {
        self.requestId = requestId
        self.code = code
        self.reason = reason
        self.cause = cause
    }

    init(requestId: Int64? = nil, code: ResponseStatusCode, reason: String? = nil, cause: Error? = nil) {
        self.requestId = requestId
        self.code = code.rawValue
        self.reason = reason
        self.cause = cause
    }

    init(_ notification: TurmsNotification) {
        requestId = notification.hasRequestID ? notification.requestID : nil
        code = Int(notification.code)
        reason = notification.hasReason ? notification.reason : nil
        cause = nil
    }
}
