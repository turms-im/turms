import ResponseError from '../error/response-error';
import ResponseStatusCode from './response-status-code';
import { TurmsNotification, TurmsNotification_Data } from './proto/notification/turms_notification';

export default class Response<T> {
    public readonly requestId?: string;
    public readonly code: number;
    public readonly data: T;

    constructor(requestId: string, code: number, data) {
        this.requestId = requestId;
        this.code = code;
        this.data = data;
    }

    static get isTurmsResponse(): boolean {
        return true;
    }

    static value<T>(data: T): Response<T> {
        return new Response(null, ResponseStatusCode.OK, data);
    }

    static nullValue<T>(): Response<T> {
        return new Response(null, ResponseStatusCode.OK, null);
    }

    static emptyList<T>(): Response<T[]> {
        return new Response(null, ResponseStatusCode.OK, []);
    }

    static fromNotification<T>(notification: TurmsNotification, dataTransformer?: (data: TurmsNotification_Data) => T): Response<T> {
        if (notification.code == null) {
            throw ResponseError.fromCodeAndReason(
                ResponseStatusCode.INVALID_NOTIFICATION,
                'Cannot parse a success response from a notification without code'
            );
        }
        if (ResponseStatusCode.isErrorCode(notification.code)) {
            throw ResponseError.fromCodeAndReason(
                ResponseStatusCode.INVALID_NOTIFICATION,
                'Cannot parse a success response from non-success notification');
        }
        const data = notification.data || {} as TurmsNotification_Data;
        let responseData;
        try {
            responseData = dataTransformer?.(data);
        } catch (e: any) {
            throw ResponseError.fromCodeAndReason(ResponseStatusCode.INVALID_NOTIFICATION,
                `Failed to transform notification data: ${notification.data}. Error: ${e?.message}`);
        }
        return new Response(notification.requestId == null ? null : notification.requestId,
            notification.code, responseData);
    }
}
