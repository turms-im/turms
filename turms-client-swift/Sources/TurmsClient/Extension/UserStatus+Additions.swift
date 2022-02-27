public extension UserStatus {
    func toString() -> String {
        switch self {
        case .available:
            return "AVAILABLE"
        case .offline:
            return "OFFLINE"
        case .invisible:
            return "INVISIBLE"
        case .busy:
            return "BUSY"
        case .doNotDisturb:
            return "DO_NOT_DISTURB"
        case .away:
            return "AWAY"
        case .beRightBack:
            return "BE_RIGHT_BACK"
        case .UNRECOGNIZED:
            return ""
        }
    }
}
