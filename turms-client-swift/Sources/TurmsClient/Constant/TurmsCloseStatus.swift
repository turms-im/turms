import Foundation

public enum TurmsCloseStatus: Int {
    case switchProtocol = 300

    case illegalRequest = 400
    case heartbeatTimeout
    case disconnectedByClient
    case disconnectedByOtherDevice

    case serverError = 500
    case serverClosed
    case serverUnavailable

    case loginConflict = 600
    case loginTimeout

    case disconnectedByAdmin = 700
    case userIsDeletedOrInactivated

    case unknownError = 900
}
