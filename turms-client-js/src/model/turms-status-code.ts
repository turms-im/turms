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
    ILLEGAL_DATE_FORMAT,
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

    SERVER_INTERNAL_ERROR = 5000,
    LOGGED_DEVICES_CANNOT_OFFLINE
}

export default class TurmsStatusCode {
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
        [Code.ILLEGAL_DATE_FORMAT]: "Illegal date format",
        [Code.NOT_ACTIVE]: "Not active",
        [Code.OWNED_RESOURCE_LIMIT_REACHED]: "The resource limit is reached",
        [Code.REQUESTED_RECORDS_TOO_MANY]: "Too many records are requested",
        [Code.RESOURCES_HAVE_BEEN_HANDLED]: "The resources have been handled",
        [Code.RESOURCES_HAVE_CHANGED]: "The resources have been changed",
        [Code.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE]: "A different device has logged into your account",
        [Code.SESSION_SIMULTANEOUS_CONFLICTS_NOTIFY]: "Someone attempted to log into your account",
        [Code.SESSION_SIMULTANEOUS_CONFLICTS_OFFLINE]: "A different device has logged into your account",
        [Code.SUCCESSOR_NOT_GROUP_MEMBER]: "The successor is not the group member",
        [Code.TARGET_USERS_UNAUTHORIZED]: "The target users are unauthorized",
        [Code.TARGET_USERS_NOT_EXIST]: "The target users do not exist",
        [Code.TYPE_NOT_EXISTS]: "The resource type does not exist",
        [Code.UNAUTHORIZED]: "Unauthorized",
        [Code.REDUNDANT_REQUEST]: "The request is redundant",
        [Code.ALREADY_GROUP_MEMBER]: "The user is already a member of the group",
        [Code.LOGGED_DEVICES_CANNOT_OFFLINE]: "Cannot set logged in devices offline"
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
        return code === Code.OK;
    }

    static isErrorCode(code: number | string): boolean {
        return !this.isSuccessCode(code);
    }

    static getReason(code: number): string {
        return this._code2ReasonMap[code];
    }
}