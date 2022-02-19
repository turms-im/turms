import { DeviceType } from '../model/proto/constant/device_type';

export default class SystemUtil {

    static isBrowser(): boolean {
        return typeof window === 'object' && window.document != null;
    }

    static getDeviceType(): DeviceType {
        return SystemUtil.isBrowser() ? DeviceType.BROWSER : DeviceType.DESKTOP;
    }

}