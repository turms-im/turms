import {im} from "./proto-bundle";
import TurmsStatusCode from "./turms-status-code";
import TurmsNotification = im.turms.proto.TurmsNotification;

export default class TurmsBusinessException {
    private readonly _code: number;
    private readonly _reason: string;

    static URL_UNINITIALIZED = 'The baseUrl is uninitialized.';
    static CLIENT_ALREADY_CONNECTED = 'The client has connected to the server. Please close it before logging in again.';

    constructor(code: number, reason: string) {
        this._code = code;
        this._reason = reason;
    }

    get code(): number {
        return this._code;
    }

    get reason(): string {
        return this._reason;
    }

    toString(): string {
        return `${this._code}:${this._reason}`;
    }

    static fromNotification(notification: TurmsNotification): TurmsBusinessException {
        if (notification.reason && notification.reason.value) {
            return new TurmsBusinessException(notification.code.value, notification.reason.value);
        } else {
            return new TurmsBusinessException(notification.code.value, TurmsStatusCode.getReason(notification.code.value));
        }
    }

    static fromCode(code: number): TurmsBusinessException {
        return new TurmsBusinessException(code, TurmsStatusCode.getReason(code));
    }

    static illegalParam<T = never>(reason: string): Promise<T> {
        const exception = new TurmsBusinessException(TurmsStatusCode.ILLEGAL_ARGUMENTS, reason);
        return Promise.reject(exception);
    }

    // The method is used to avoid the duplicate string declaration to reduce the file size
    static notFalsy<T = never>(name: string, notEmpty = false): Promise<T> {
        return this.illegalParam(`${name} must be not null${notEmpty ? ' or empty' : ''}`);
    }
}