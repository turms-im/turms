import 'dart:io';

import '../model/proto/constant/device_type.pb.dart';

DeviceType _getDeviceType() {
  //  * [kIsWeb], the equivalent constant in the `foundation` library.
  // note that turms-client-dart doesn't support browser currently.
  const isBrowser = identical(0, 0.0);
  if (isBrowser) {
    return DeviceType.BROWSER;
  }
  if (Platform.isAndroid) {
    return DeviceType.ANDROID;
  }
  if (Platform.isIOS) {
    return DeviceType.IOS;
  }
  return DeviceType.DESKTOP;
}

final DeviceType currentDeviceType = _getDeviceType();
