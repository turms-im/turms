enum Code {
    OK = 2000,
    NO_CONTENT,
    ALREADY_UP_TO_DATE,
    RECIPIENTS_OFFLINE,

    NOT_RESPONSIBLE = 3000,

    FAILED = 4000,
    DISABLED_FUNCTION,
    EXPIRY_DATE_BEFORE_NOW,
    EXPIRY_RESOURCE,
    ID_DUPLICATED,
    ILLEGAL_ARGUMENTS,
    INVALID_DATA,
    NOT_ACTIVE,
    OWNED_RESOURCE_LIMIT_REACHED,
    REQUESTED_RECORDS_TOO_MANY,
    RESOURCES_HAVE_BEEN_HANDLED,
    RESOURCES_HAVE_CHANGED,
    SESSION_SIMULTANEOUS_CONFLICTS_DECLINE,
    SESSION_SIMULTANEOUS_CONFLICTS_NOTIFY,
    SESSION_SIMULTANEOUS_CONFLICTS_OFFLINE,
    SUCCESSOR_NOT_GROUP_MEMBER,
    TARGET_USERS_UNAUTHORIZED,
    TARGET_USERS_NOT_EXIST,
    TYPE_NOT_EXISTS,
    UNAUTHORIZED,
    REDUNDANT_REQUEST,
    ALREADY_GROUP_MEMBER,
    FRIEND_REQUEST_HAS_EXISTED,
    RELATIONSHIP_HAS_ESTABLISHED,
    USER_NOT_GROUP_MEMBER,
    USER_HAS_BEEN_BLACKLISTED,
    GROUP_HAS_BEEN_MUTED,
    MEMBER_HAS_BEEN_MUTED,
    GUESTS_HAVE_BEEN_MUTED,
    FILE_TOO_LARGE,
    REQUEST_TOO_LARGE,
    FORBIDDEN_DEVICE_TYPE,

    SERVER_INTERNAL_ERROR = 5000,
    LOGGED_DEVICES_CANNOT_OFFLINE,
    NOT_IMPLEMENTED,
    UNAVAILABLE,

    CLIENT_USER_ID_AND_PASSWORD_MUST_NOT_NULL = 6000,
    CLIENT_SESSION_HAS_BEEN_CLOSED,
    CLIENT_SESSION_ALREADY_ESTABLISHED,
    CLIENT_REQUESTS_TOO_FREQUENT,
    MISSING_DATA,
    TIMEOUT

}

class TurmsStatusCode {
    private static _code2ReasonMap = {
        [Code.OK]: "ok",
        [Code.NOT_RESPONSIBLE]: "The server isn't responsible for the user",
        [Code.FAILED]: "failed",
        [Code.SERVER_INTERNAL_ERROR]: "Internal server error",

        [Code.NO_CONTENT]: "No content",
        [Code.ALREADY_UP_TO_DATE]: "Already up-to-date",
        [Code.RECIPIENTS_OFFLINE]: "The recipients are offline",

        [Code.DISABLED_FUNCTION]: "The function has been disabled in servers",
        [Code.EXPIRY_DATE_BEFORE_NOW]: "Expiration date must be greater than now",
        [Code.EXPIRY_RESOURCE]: "The target resource has expired",
        [Code.ID_DUPLICATED]: "ID must be unique",
        [Code.ILLEGAL_ARGUMENTS]: "Illegal arguments",
        [Code.INVALID_DATA]: "Invalid data",
        [Code.NOT_ACTIVE]: "Not active",
        [Code.OWNED_RESOURCE_LIMIT_REACHED]: "The resource limit is reached",
        [Code.REQUESTED_RECORDS_TOO_MANY]: "Too many records are requested",
        [Code.RESOURCES_HAVE_BEEN_HANDLED]: "The resources have been handled",
        [Code.RESOURCES_HAVE_CHANGED]: "The resources have been changed",
        [Code.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE]: "A different device has logged into your account",
        [Code.SESSION_SIMULTANEOUS_CONFLICTS_NOTIFY]: "Someone attempted to log into your account",
        [Code.SESSION_SIMULTANEOUS_CONFLICTS_OFFLINE]: "A different device has logged into your account",
        [Code.SUCCESSOR_NOT_GROUP_MEMBER]: "The successor is not a member of the group",
        [Code.TARGET_USERS_UNAUTHORIZED]: "The target users are unauthorized",
        [Code.TARGET_USERS_NOT_EXIST]: "The target users do not exist",
        [Code.TYPE_NOT_EXISTS]: "The resource type does not exist",
        [Code.UNAUTHORIZED]: "Unauthorized",
        [Code.REDUNDANT_REQUEST]: "The request is redundant",
        [Code.ALREADY_GROUP_MEMBER]: "The user is already a member of the group",
        [Code.FRIEND_REQUEST_HAS_EXISTED]: "A friend request has already existed",
        [Code.RELATIONSHIP_HAS_ESTABLISHED]: "The relationship has already established",
        [Code.USER_NOT_GROUP_MEMBER]: "The user is not a member of the group",
        [Code.USER_HAS_BEEN_BLACKLISTED]: "The user has been blacklisted",
        [Code.GROUP_HAS_BEEN_MUTED]: "The group has been muted",
        [Code.MEMBER_HAS_BEEN_MUTED]: "The group member has been muted",
        [Code.GUESTS_HAVE_BEEN_MUTED]: "The guests of the group have been muted",
        [Code.FILE_TOO_LARGE]: "The file is too large to upload",
        [Code.REQUEST_TOO_LARGE]: "The request is too large",
        [Code.FORBIDDEN_DEVICE_TYPE]: "The device type is forbidden for the request",
        [Code.LOGGED_DEVICES_CANNOT_OFFLINE]: "Cannot set logged in devices offline",
        [Code.NOT_IMPLEMENTED]: "Not Implemented",
        [Code.UNAVAILABLE]: "The service is unavailable",
        [Code.CLIENT_USER_ID_AND_PASSWORD_MUST_NOT_NULL]: "The user ID and password must not be null",
        [Code.CLIENT_SESSION_HAS_BEEN_CLOSED]: "The session has been closed",
        [Code.CLIENT_SESSION_ALREADY_ESTABLISHED]: "The session has been established",
        [Code.CLIENT_REQUESTS_TOO_FREQUENT]: "Client requests are too frequent",
        [Code.MISSING_DATA]: "The data is missing",
        [Code.TIMEOUT]: "The request has timed out"
    };
    private code: number;
    private reason: string;

    constructor(code: number) {
        this.code = code;
        this.reason = TurmsStatusCode.getReason(code);
    }

    static isSuccessCode(code: number | string | Code): boolean {
        if (typeof code === 'string') {
            code = parseInt(code);
        }
        return 2000 <= code && code < 3000;
    }

    static isErrorCode(code: number | string): boolean {
        return !this.isSuccessCode(code);
    }

    static getReason(code: number): string {
        return this._code2ReasonMap[code];
    }
}

export default Object.assign(TurmsStatusCode, Code);