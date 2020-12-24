public struct TurmsBusinessError: Error {
    public let code: Int
    public let reason: String?

    init(_ code: TurmsStatusCode) {
        self.code = code.rawValue
        self.reason = nil
    }

    init(_ code: TurmsStatusCode, _ reason: String) {
        self.code = code.rawValue
        self.reason = reason
    }

    init(_ code: Int, _ reason: String) {
        self.code = code
        self.reason = reason
    }

    init(_ code: Int) {
        self.code = code
        self.reason = nil
    }
}
