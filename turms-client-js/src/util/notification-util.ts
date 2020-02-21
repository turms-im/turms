import {google, im} from "../model/proto-bundle";
import {ParsedModel} from "../model/parsed-model";
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

    static getFirstArrayAndTransform(data?: object): Array<any> | undefined {
        if (typeof data === 'object') {
            for (const key of Object.keys(data)) {
                let item = data[key];
                if (item instanceof Array) {
                    item = item.map(value => this.transform(value));
                    return item;
                }
            }
        }
    }

    static transformDate(date?: IInt64Value): Date | undefined {
        if (date && typeof date.value === 'string') {
            return new Date(date.value);
        }
    }

    static getIdsWithVersion(notification: TurmsNotification): ParsedModel.IdsWithVersion {
        return {
            ids: notification.data.idsWithVersion.values,
            lastUpdatedDate: notification.data.idsWithVersion.lastUpdatedDate && notification.data.idsWithVersion.lastUpdatedDate.value ?
                new Date(notification.data.idsWithVersion.lastUpdatedDate.value) : null
        };
    }
}
