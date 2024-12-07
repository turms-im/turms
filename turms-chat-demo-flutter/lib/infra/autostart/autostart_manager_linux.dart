import 'dart:io';

import 'autostart_manager.dart';

class AutostartManagerLinux extends AutostartManager {
  AutostartManagerLinux({
    required String appName,
    required String appPath,
    List<String> args = const [],
  }) : super(appName: appName, appPath: appPath, args: args);

  File get _desktopFile => File(
      '${Platform.environment['HOME']}/.config/autostart/$appName.desktop');

  @override
  Future<bool> isEnabled() async => _desktopFile.exists();

  @override
  Future<void> enable() async {
    final contents = '''
[Desktop Entry]
Type=Application
Name=$appName
Comment=$appName startup script
Exec=${args.isEmpty ? appPath : '$appPath ${args.join(' ')}'}
StartupNotify=false
Terminal=false
''';
    if (!await _desktopFile.parent.exists()) {
      await _desktopFile.parent.create(recursive: true);
    }
    await _desktopFile.writeAsString(contents);
  }

  @override
  Future<void> disable() async {
    if (await _desktopFile.exists()) {
      await _desktopFile.delete();
    }
  }
}
