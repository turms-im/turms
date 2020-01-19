declare enum Code {
    OK = 2000,
    NO_CONTENT = 2001,
    ALREADY_UP_TO_DATE = 2002,
    RECIPIENTS_OFFLINE = 2003,
    NOT_RESPONSIBLE = 3000,
    FAILED = 4000,
    DISABLED_FUNCTION = 4001,
    EXPIRY_DATE_BEFORE_NOW = 4002,
    EXPIRY_RESOURCE = 4003,
    ID_DUPLICATED = 4004,
    ILLEGAL_ARGUMENTS = 4005,
    ILLEGAL_DATE_FORMAT = 4006,
    OWNED_RESOURCE_LIMIT_REACHED = 4007,
    REQUESTED_RECORDS_TOO_MANY = 4008,
    RESOURCES_HAVE_BEEN_HANDLED = 4009,
    RESOURCES_HAVE_CHANGED = 4010,
    SESSION_SIMULTANEOUS_CONFLICTS_DECLINE = 4011,
    SESSION_SIMULTANEOUS_CONFLICTS_NOTIFY = 4012,
    SESSION_SIMULTANEOUS_CONFLICTS_OFFLINE = 4013,
    SUCCESSOR_NOT_GROUP_MEMBER = 4014,
    TARGET_USERS_UNAUTHORIZED = 4015,
    UNAUTHORIZED = 4016,
    SERVER_INTERNAL_ERROR = 5000,
    LOGGED_DEVICES_CANNOT_OFFLINE = 5001
}
export default class TurmsStatusCode {
    private static _code2ReasonMap;
    private code;
    private reason;
    constructor(code: number);
    static isSuccessCode(code: number | string | Code): boolean;
    static isErrorCode(code: number | string): boolean;
    static getReason(code: number): string;
}
export {};
