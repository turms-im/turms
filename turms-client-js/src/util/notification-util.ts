import { ParsedModel } from '../model/parsed-model';
import ResponseError from '../error/response-error';
import ResponseStatusCode from '../model/response-status-code';
import { TurmsNotification } from '../model/proto/notification/turms_notification';

export default class NotificationUtil {

    static transform(data?: object | number | string, parentKey?: string): any {
        // Note that data can be 0 or ''
        if (data != null) {
            const isDateType = typeof parentKey === 'string'
                && (parentKey.endsWith('Date') || parentKey.endsWith('_date') || parentKey === 'date')
                && typeof data === 'string';
            if (isDateType) {
                return new Date(parseInt(data as string));
            } else if (typeof data === 'object') {
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

    static getFirstIdOrThrow(notification: TurmsNotification): any {
        const value = notification.data?.ids?.values?.[0];
        if (value == null) {
            const reason = `Cannot get ID from the invalid response: ${JSON.stringify(notification)}`;
            throw ResponseError.from(ResponseStatusCode.INVALID_RESPONSE, reason);
        }
        return value;
    }

    static getIdsWithVer(n: TurmsNotification): ParsedModel.IdsWithVersion | undefined {
        const idsWithVersion = n.data?.idsWithVersion;
        const arr = idsWithVersion?.values;
        if (arr?.length) {
            return {
                ids: arr,
                lastUpdatedDate: this.transformDate(idsWithVersion.lastUpdatedDate)
            };
        }
    }

}
