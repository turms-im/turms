import {google, im} from "../model/proto-bundle";
import {ParsedModel} from "../model/parsed-model";
import TurmsStatusCode from "../model/turms-status-code";
import TurmsBusinessError from "../model/turms-business-error";
import TurmsNotification = im.turms.proto.TurmsNotification;
import IInt64Value = google.protobuf.IInt64Value;

export default class NotificationUtil {
    static transform(data?: object | number | string, parentKey?: string): object | number | string | undefined {
        // Note that data can be 0 or ''
        if (typeof data !== 'undefined' && data !== null) {
            if (typeof parentKey === 'string' && (parentKey.endsWith('Date') || parentKey.endsWith('_date') || parentKey === 'date')
                && typeof data === 'string') {
                return new Date(parseInt(data));
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

    static getFirstVal(notification: im.turms.proto.TurmsNotification, path: string, throwIfUndefined = false): any {
        path += '.values.0';
        const value = this._get(notification, path, undefined);
        if (value === undefined && throwIfUndefined) {
            throw TurmsBusinessError.fromCode(TurmsStatusCode.MISSING_DATA)
        }
        return value;
    }

    static getVal(notification: TurmsNotification, path: string): any {
        path += '.value';
        return this._get(notification, path, undefined);
    }

    static getAndTransform(notification: TurmsNotification, path: string): any {
        return this.transform(this.get(notification, path));
    }

    static getArrAndTransform(notification: TurmsNotification, path: string): any {
        return this.transform(this.getArr(notification, path));
    }

    static get(notification: TurmsNotification, path: string): any {
        return this._get(notification, path, undefined);
    }

    static getArr(notification: TurmsNotification, path: string): any[] {
        return this._get(notification, path, []);
    }

    private static _get(notification: TurmsNotification, path: string, defaultValue: any): any {
        path = 'data.' + path;
        if (notification.code === TurmsStatusCode.NO_CONTENT) {
            return defaultValue;
        } else {
            const keys = path.split('.');
            let object = notification;
            for (const key of keys) {
                object = object[key];
                if (!object) {
                    return defaultValue;
                }
            }
            return object;
        }
    }

    static transformDate(date?: IInt64Value): Date | undefined {
        return date ? new Date(parseInt(date.value)) : undefined;
    }

    static getIdsWithVer(n: TurmsNotification): ParsedModel.IdsWithVersion | undefined {
        const arr = this.getArr(n, 'idsWithVersion.values');
        if (arr.length) {
            let date = this.get(n, 'idsWithVersion.lastUpdatedDate');
            date = this.transformDate(date);
            return {
                ids: arr,
                lastUpdatedDate: date
            };
        }
    }

    static getVerDate(n: TurmsNotification, path: string): Date | undefined {
        path += '.lastUpdatedDate';
        return NotificationUtil.getAndTransform(n, path);
    }
}
