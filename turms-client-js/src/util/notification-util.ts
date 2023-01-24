import { ParsedModel } from '../model/parsed-model';
import ResponseError from '../error/response-error';
import ResponseStatusCode from '../model/response-status-code';
import { TurmsNotification_Data } from '../model/proto/notification/turms_notification';

export default class NotificationUtil {

    static transform(data?: object | number | string, parentKey?: string): any {
        // Note that data can be 0 or ''
        if (data == null) {
            return null;
        }
        const isDateType = typeof parentKey === 'string'
            && (parentKey.endsWith('Date') || parentKey.endsWith('_date') || parentKey === 'date')
            && typeof data === 'string';
        if (isDateType) {
            return new Date(parseInt(data as string));
        }
        if (typeof data === 'object') {
            if (data instanceof Array) {
                data = data.map(item => this.transform(item));
            } else {
                const keys = Object.keys(data);
                for (const key of keys) {
                    if ((key === 'value' || key === 'values') && keys.length === 1) {
                        return data[key];
                    } else {
                        data[key] = this.transform(data[key], key);
                    }
                }
            }
        }
        return data;
    }

    static transformDate(date?: string): Date | undefined {
        return date ? new Date(parseInt(date)) : undefined;
    }

    static transformOrEmpty(data: any[]): any[] {
        return this.transform(data) || [];
    }

    static transformMapValToDate(record?: Record<string, string>): Record<string, Date> | undefined {
        if (!record) {
            return;
        }
        const newMap = {};
        for (const key in record) {
            if (Object.prototype.hasOwnProperty.call(record, key)) {
                newMap[key] = new Date(parseInt(record[key]));
            }
        }
        return newMap;
    }

    static getLongOrThrow(data?: TurmsNotification_Data): string {
        const long = data?.long;
        if (long == null) {
            throw ResponseError.from({
                code: ResponseStatusCode.INVALID_RESPONSE,
                reason: `Could not get a long value from the invalid response: ${JSON.stringify(data)}`
            });
        }
        return long;
    }

    static getLongsWithVersion(data?: TurmsNotification_Data): ParsedModel.LongsWithVersion | undefined {
        const longsWithVersion = data?.longsWithVersion;
        const longs = longsWithVersion?.longs;
        if (longs?.length) {
            return {
                longs,
                lastUpdatedDate: this.transformDate(longsWithVersion.lastUpdatedDate)
            };
        }
    }

    static toMap(array: string[]): Record<string, string> {
        const length = array.length;
        if (length % 2 != 0) {
            throw ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'The number of elements must be even'
            });
        }
        const map = {};
        for (let i = 0; i < length; i += 2) {
            map[array[i]] = array[i + 1];
        }
        return map;
    }

}
