import { google, im } from "../model/proto-bundle";
import { ParsedModel } from "../model/parsed-model";
import TurmsNotification = im.turms.proto.TurmsNotification;
import IInt64Value = google.protobuf.IInt64Value;
export default class NotificationUtil {
    static transform(data?: object | number | string, parentKey?: string): object | number | string | undefined;
    static getFirstVal(notification: im.turms.proto.TurmsNotification, path: string, throwIfUndefined?: boolean): any;
    static getVal(notification: TurmsNotification, path: string): any;
    static getAndTransform(notification: TurmsNotification, path: string): any;
    static getArrAndTransform(notification: TurmsNotification, path: string): any;
    static get(notification: TurmsNotification, path: string): any;
    static getArr(notification: TurmsNotification, path: string): any[];
    private static _get;
    static transformDate(date?: IInt64Value): Date | undefined;
    static getIdsWithVer(n: TurmsNotification): ParsedModel.IdsWithVersion | undefined;
    static getVerDate(n: TurmsNotification, path: string): Date | undefined;
}
