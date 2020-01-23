import { google, im } from "../model/proto-bundle";
import { ParsedModel } from "../model/parsed-model";
import TurmsNotification = im.turms.proto.TurmsNotification;
import IInt64Value = google.protobuf.IInt64Value;
export default class NotificationUtil {
    static transform(data?: object | number, parentKey?: string): object | number | undefined;
    static getFirstArrayAndTransform(data?: object): Array<any> | undefined;
    static transformDate(date?: IInt64Value): Date | undefined;
    static getFirstIdFromIds(notification?: TurmsNotification): number | null;
    static getIds(notification: TurmsNotification): number[];
    static getIdsWithVersion(notification: TurmsNotification): ParsedModel.IdsWithVersion;
}
