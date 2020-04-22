import { im } from "./proto-bundle";
import TurmsNotification = im.turms.proto.TurmsNotification;
export default class TurmsBusinessException {
    private readonly _code;
    private readonly _reason;
    static URL_UNINITIALIZED: string;
    static CLIENT_ALREADY_CONNECTED: string;
    constructor(code: number, reason: string);
    get code(): number;
    get reason(): string;
    toString(): string;
    static fromNotification(notification: TurmsNotification): TurmsBusinessException;
    static fromCode(code: number): TurmsBusinessException;
    static illegalParam<T = never>(reason: string): Promise<T>;
    static notFalsy<T = never>(name: string, notEmpty?: boolean): Promise<T>;
}
