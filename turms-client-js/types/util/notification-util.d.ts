import { google, im } from "../model/proto-bundle";
import { ParsedModel } from "../model/parsed-model";
import TurmsNotification = im.turms.proto.TurmsNotification;
import IInt64Value = google.protobuf.IInt64Value;
export default class NotificationUtil {
    static transform(data?: object | number | string, parentKey?: string): object | number | string | undefined;
    static getFirstArrayAndTransform(data?: object): Array<any> | undefined;
    static transformDate(date?: IInt64Value): Date | undefined;
    static getIdsWithVersion(notification: TurmsNotification): ParsedModel.IdsWithVersion;
}
