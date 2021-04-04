import {DeviceType} from '../model/proto/constant/device_type';

export default class SystemUtil {

    static isBrowser(): boolean {
        return typeof window !== 'undefined' && typeof window.document !== 'undefined';
    }

    static getDeviceType(): DeviceType {
        return SystemUtil.isBrowser() ? DeviceType.BROWSER : DeviceType.DESKTOP;
    }

}