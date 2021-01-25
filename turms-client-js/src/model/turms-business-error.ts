import {im} from "./proto-bundle";
import TurmsStatusCode from "./turms-status-code";
import TurmsNotification = im.turms.proto.TurmsNotification;

export default class TurmsBusinessError extends Error {
    private readonly _isTurmsBusinessError = true;
    private readonly _code: number;
    private readonly _reason: string;

    private constructor(code: number, reason: string) {
        super(`${code}:${reason}`);
        this._code = code;
        this._reason = reason;
    }

    get isTurmsBusinessError(): boolean {
        return this._isTurmsBusinessError;
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
            return new TurmsBusinessError(notification.code.value, null);
        }
    }

    static from(code: number, reason?: string): TurmsBusinessError {
        return new TurmsBusinessError(code, reason);
    }

    static fromCode(code: number): TurmsBusinessError {
        return new TurmsBusinessError(code, null);
    }

    static illegalParam<T = never>(reason: string): Promise<T> {
        const exception = new TurmsBusinessError(TurmsStatusCode.ILLEGAL_ARGUMENT, reason);
        return Promise.reject(exception);
    }

    // The method is used to avoid the duplicate string declaration to reduce the file size
    static notFalsy<T = never>(name: string, notEmpty = false): Promise<T> {
        const emptyPlaceholder = notEmpty ? ' or empty' : '';
        return this.illegalParam(`${name} must not be null${emptyPlaceholder} or an invalid param`);
    }
}