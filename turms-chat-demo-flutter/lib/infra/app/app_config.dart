import 'dart:io';
import 'dart:ui';

import 'package:package_info_plus/package_info_plus.dart';
import 'package:path_provider/path_provider.dart';

import '../env/env_vars.dart';

class AppConfig {
  AppConfig._();

  static const title = EnvVars.windowTitle;

  static const defaultWindowSizeForLoginScreen = Size(480, 456);

  static const defaultWindowSizeForHomeScreen = Size(
    980,
    // Get from: 64 (tile height) * 12
    768,
  );
  static const minWindowSizeForHomeScreen = Size(700, 640);

  static late PackageInfo packageInfo;

  static late String appDir;
  static late String tempDir;

  static Future<void> load() async {
    packageInfo = await PackageInfo.fromPlatform();
    final appDocDir = await getApplicationDocumentsDirectory();
    final appDocDirPath = await appDocDir.resolveSymbolicLinks();
    appDir =
        '$appDocDirPath${Platform.pathSeparator}${AppConfig.packageInfo.packageName}';
    tempDir = await (await getTemporaryDirectory()).resolveSymbolicLinks();
  }
}
