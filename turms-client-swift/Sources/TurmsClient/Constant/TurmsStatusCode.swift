public enum TurmsStatusCode: Int {
    // For general use
    case ok = 2000
    case notResponsible = 3000
    case failed = 4000
    case serverInternalError = 5000

    case noContent = 2001
    case alreadyUpToDate
    case recipientsOffline

    case disabledFunction = 4001
    case expiryDateBeforeNow
    case expiredResource
    case duplicateKey
    case illegalArguments
    case illegalDateFormat
    case notActive
    case ownedResourceLimitReached
    case requestedRecordsTooMany
    case resourcesHaveBeenHandled
    case resourcesHaveChanged
    case sessionSimultaneousConflictsDecline
    case sessionSimultaneousConflictsNotify
    case sessionSimultaneousConflictsOffline
    case successorNotGroupMember
    case targetUsersUnauthorized
    case targetUsersNotExist
    case typeNotExists
    case unauthorized
    case redundantRequest
    case alreadyGroupMember
    case friendRequestHasExisted
    case relationshipHasEstablished
    case userNotGroupMember
    case userHasBeenBlacklisted
    case groupHasBeenMuted
    case memberHasBeenMuted
    case guestsHaveBeenMuted
    case fileTooLarge
    case requestTooLarge

    case loggedInDevicesCannotOffline = 5001
    case notImplemented
    case unavailable

    case clientUserIdAndPasswordMustNotNull = 6000
    case clientSessionHasBeenClosed
    case clientSessionAlreadyEstablished
    case clientRequestsTooFrequent
    case missingData
}

extension TurmsStatusCode {
    public var reason: String {
        switch self {
            case .ok: return "ok"
            case .notResponsible: return "The server isn't responsible for the user"
            case .failed: return "failed"
            case .serverInternalError: return "Internal server error"
            case .noContent: return "No content"
            case .alreadyUpToDate: return "Already up-to-date"
            case .recipientsOffline: return "The recipients are offline"
            case .disabledFunction: return "The function has been disabled in servers"
            case .expiryDateBeforeNow: return "Expiration date must be greater than now"
            case .expiredResource: return "The target resource has expired"
            case .duplicateKey: return "The record being added contains a duplicate key"
            case .illegalArguments: return "Illegal arguments"
            case .illegalDateFormat: return "Illegal date format"
            case .notActive: return "Not active"
            case .ownedResourceLimitReached: return "The resource limit is reached"
            case .requestedRecordsTooMany: return "Too many records are requested"
            case .resourcesHaveBeenHandled: return "The resources have been handled"
            case .resourcesHaveChanged: return "The resources have been changed"
            case .sessionSimultaneousConflictsDecline: return "A different device has logged into your account"
            case .sessionSimultaneousConflictsNotify: return "Someone attempted to log into your account"
            case .sessionSimultaneousConflictsOffline: return "A different device has logged into your account"
            case .successorNotGroupMember: return "The successor is not a member of the group"
            case .targetUsersUnauthorized: return "The target users are unauthorized"
            case .targetUsersNotExist: return "The target users do not exist"
            case .typeNotExists: return "The resource type does not exist"
            case .unauthorized: return "Unauthorized"
            case .redundantRequest: return "The request is redundant"
            case .alreadyGroupMember: return "The user is already a member of the group"
            case .friendRequestHasExisted: return "A friend request has already existed"
            case .relationshipHasEstablished: return "The relationship has already established"
            case .loggedInDevicesCannotOffline: return "Cannot set logged in devices offline"
            case .notImplemented: return "Not Implemented"
            case .unavailable: return "The service is unavailable"
            case .clientUserIdAndPasswordMustNotNull: return "The user ID and password must not be null"
            case .clientSessionHasBeenClosed: return "The session has been closed"
            case .clientSessionAlreadyEstablished: return "The session has been established"
            case .clientRequestsTooFrequent: return "Client requests are too frequent"
            case .missingData: return "The data is missing"
            case .userNotGroupMember: return "The user is not a member of the group"
            case .userHasBeenBlacklisted: return "The user has been blacklisted"
            case .groupHasBeenMuted: return "The group has been muted"
            case .memberHasBeenMuted: return "The group member has been muted"
            case .guestsHaveBeenMuted: return "The guests of the group have been muted"
            case .fileTooLarge: return "The file is too large to upload"
            case .requestTooLarge: return "The request is too large"
        }
    }
    
    public static func isSuccess(_ code: Int32) -> Bool {
        return 2000 <= code && code < 3000
    }
}
