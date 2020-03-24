public struct TurmsBusinessException: Error {
    public let code: TurmsStatusCode
    public let reason: String

    init(_ code: TurmsStatusCode) {
        self.code = code
        self.reason = code.reason
    }

    init(_ code: Int32) {
        self.code = TurmsStatusCode(rawValue: Int(code))!
        self.reason = self.code.reason
    }

    init(_ code: Int32, _ reason: String) {
        self.code = TurmsStatusCode(rawValue: Int(code))!
        self.reason = reason
    }
}
