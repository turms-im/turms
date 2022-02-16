import TurmsStatusCode from './turms-status-code';
import {TurmsNotification} from './proto/notification/turms_notification';

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

    override toString(): string {
        return `${this._code}:${this._reason}`;
    }

    static fromNotification(notification: TurmsNotification): TurmsBusinessError {
        return new TurmsBusinessError(notification.code, notification.reason);
    }

    static from(code: number, reason?: string): TurmsBusinessError {
        return new TurmsBusinessError(code, reason);
    }

    static fromCode(code: number): TurmsBusinessError {
        return new TurmsBusinessError(code, null);
    }

    static illegalParam(reason: string): never {
        throw new TurmsBusinessError(TurmsStatusCode.ILLEGAL_ARGUMENT, reason);
    }

    static notFalsy(name: string, notEmpty = false): never {
        const emptyPlaceholder = notEmpty ? ' or empty' : '';
        throw this.illegalParam(`${name} must not be null${emptyPlaceholder} or an invalid param`);
    }

    static illegalParamPromise<T = never>(reason: string): Promise<T> {
        const exception = new TurmsBusinessError(TurmsStatusCode.ILLEGAL_ARGUMENT, reason);
        return Promise.reject(exception);
    }

    static notFalsyPromise<T = never>(name: string, notEmpty = false): Promise<T> {
        const emptyPlaceholder = notEmpty ? ' or empty' : '';
        return this.illegalParamPromise(`${name} must not be null${emptyPlaceholder} or an invalid param`);
    }
}