import { im } from "../model/proto-bundle";
import DeviceType = im.turms.proto.DeviceType;
export default class SystemUtil {
    static isBrowser(): boolean;
    static getDeviceType(): DeviceType;
}
