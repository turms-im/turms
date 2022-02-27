public extension DeviceType {
    func toString() -> String {
        switch self {
        case .desktop:
            return "DESKTOP"
        case .browser:
            return "BROWSER"
        case .ios:
            return "IOS"
        case .android:
            return "ANDROID"
        case .others:
            return "OTHERS"
        case .unknown:
            return "UNKNOWN"
        case .UNRECOGNIZED:
            return ""
        }
    }
}
