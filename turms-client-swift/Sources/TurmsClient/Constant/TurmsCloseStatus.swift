import Foundation

public enum TurmsCloseStatus: Int {
    case redirect = 4300
    case switch
    case illegalRequest = 4400
    case heartbeatTimeout
    case disconnectedByClient
    case serverError = 4500
    case serverClosed
    case serverUnavailable
    case loginConflict = 4600
    case disconnectedByAdmin = 4700
    case userIsDeletedOrInactivated
    case unknownError = 4900
}
