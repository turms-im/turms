import Foundation

public enum SessionCloseStatus: Int {
    case illegalRequest = 100
    case heartbeatTimeout = 110
    case loginTimeout
    case switchProtocol

    case serverError = 200
    case serverClosed
    case serverUnavailable

    case connectionClosed = 300

    case unknownError = 400

    case disconnectedByClient = 500
    case disconnectedByOtherDevice

    case disconnectedByAdmin = 600

    case userIsDeletedOrInactivated = 700
    case userIsBlocked
}
