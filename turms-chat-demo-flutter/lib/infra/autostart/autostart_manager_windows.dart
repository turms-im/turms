import 'package:win32_registry/win32_registry.dart';

import 'autostart_manager.dart';

class AutostartManagerWindows extends AutostartManager {
  AutostartManagerWindows({
    required String appName,
    required String appPath,
    List<String> args = const [],
  }) : super(appName: appName, appPath: appPath, args: args) {
    _registryValue = args.isEmpty ? appPath : '$appPath ${args.join(' ')}';
  }

  late String _registryValue;

  RegistryKey get _regKey => Registry.openPath(
        RegistryHive.currentUser,
        path: r'Software\Microsoft\Windows\CurrentVersion\Run',
        desiredAccessRights: AccessRights.allAccess,
      );

  @override
  Future<bool> isEnabled() async {
    final value = _regKey.getValueAsString(appName);
    return value == _registryValue;
  }

  @override
  Future<void> enable() async {
    _regKey.createValue(RegistryValue(
      appName,
      RegistryValueType.string,
      _registryValue,
    ));
  }

  @override
  Future<void> disable() async {
    if (await isEnabled()) {
      _regKey.deleteValue(appName);
    }
  }
}
