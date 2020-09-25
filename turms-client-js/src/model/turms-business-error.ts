import {im} from "./proto-bundle";
import TurmsStatusCode from "./turms-status-code";
import TurmsNotification = im.turms.proto.TurmsNotification;

export default class TurmsBusinessError extends Error {
    private readonly _code: number;
    private readonly _reason: string;

    constructor(code: number, reason: string) {
        super(`${code}:${reason}`);
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

    static fromNotification(notification: TurmsNotification): TurmsBusinessError {
        if (notification.reason && notification.reason.value) {
            return new TurmsBusinessError(notification.code.value, notification.reason.value);
        } else {
            return new TurmsBusinessError(notification.code.value, TurmsStatusCode.getReason(notification.code.value));
        }
    }

    static fromMessage(message?: string): TurmsBusinessError {
        if (message) {
            return new TurmsBusinessError(TurmsStatusCode.FAILED, message);
        } else {
            return TurmsBusinessError.fromCode(TurmsStatusCode.FAILED);
        }
    }

    static fromCode(code: number): TurmsBusinessError {
        return new TurmsBusinessError(code, TurmsStatusCode.getReason(code));
    }

    static illegalParam<T = never>(reason: string): Promise<T> {
        const exception = new TurmsBusinessError(TurmsStatusCode.ILLEGAL_ARGUMENTS, reason);
        return Promise.reject(exception);
    }

    // The method is used to avoid the duplicate string declaration to reduce the file size
    static notFalsy<T = never>(name: string, notEmpty = false): Promise<T> {
        const emptyPlaceholder = notEmpty ? ' or empty' : '';
        return this.illegalParam(`${name} must not be null${emptyPlaceholder} or an invalid param`);
    }
}