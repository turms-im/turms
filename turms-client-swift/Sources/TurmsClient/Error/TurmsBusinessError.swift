public struct TurmsBusinessError: Error {
    public let code: TurmsStatusCode

    init(_ code: TurmsStatusCode) {
        self.code = code
    }
}
