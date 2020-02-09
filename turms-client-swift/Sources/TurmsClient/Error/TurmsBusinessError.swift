public struct TurmsBusinessError: Error {
    public let code: TurmsStatusCode
    
    init(_ code: TurmsStatusCode) {
        self.code = code
    }
    
    init(_ code: Int32) {
        self.code = TurmsStatusCode(rawValue: Int(code))!
    }
}
