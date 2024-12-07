import 'dart:io';

import 'autostart_manager.dart';

class AutostartManagerMacOS extends AutostartManager {
  AutostartManagerMacOS({
    required String appName,
    required String appPath,
    List<String> args = const [],
  }) : super(appName: appName, appPath: appPath, args: args);

  File get _plistFile => File(
      '${Platform.environment['HOME']}/Library/LaunchAgents/$appName.plist');

  @override
  Future<bool> isEnabled() async => _plistFile.exists();

  @override
  Future<void> enable() async {
    final contents = '''
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
  <dict>
    <key>Label</key>
    <string>$appName</string>
    <key>ProgramArguments</key>
    <array>
      <string>$appPath</string>
      ${args.map((e) => '<string>$e</string>').join("\n")}
    </array>
    <key>RunAtLoad</key>
    <true/>
    <key>ProcessType</key>
    <string>Interactive</string>
    <key>StandardErrorPath</key>
    <string>/dev/null</string>
    <key>StandardOutPath</key>
    <string>/dev/null</string>
  </dict>
</plist>
''';
    if (!await _plistFile.parent.exists()) {
      await _plistFile.parent.create(recursive: true);
    }
    await _plistFile.writeAsString(contents);
  }

  @override
  Future<void> disable() async {
    if (await _plistFile.exists()) {
      await _plistFile.delete();
    }
  }
}
