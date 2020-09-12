import {im} from "../model/proto-bundle";
import DeviceType = im.turms.proto.DeviceType;

export default class SystemUtil {

    static isBrowser(): boolean {
        return typeof window !== 'undefined' && typeof window.document !== 'undefined';
    }

    static getDeviceType(): DeviceType {
        return SystemUtil.isBrowser() ? DeviceType.BROWSER : DeviceType.DESKTOP;
    }

}