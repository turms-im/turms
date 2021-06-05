public struct TurmsBusinessError: Error {

    public let code: Int
    public let reason: String?

    init(_ code: TurmsStatusCode, _ reason: String? = nil) {
        self.code = code.rawValue
        self.reason = reason
    }

    init(_ code: Int, _ reason: String? = nil) {
        self.code = code
        self.reason = reason
    }

    init(_ notification: TurmsNotification) {
        self.code = notification.code
        self.reason = notification.hasReason ? notification.reason : nil
    }

}
