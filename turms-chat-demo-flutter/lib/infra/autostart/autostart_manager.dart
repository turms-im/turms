import 'dart:io';

import 'package:flutter/foundation.dart';

import 'autostart_manager_linux.dart';
import 'autostart_manager_macos.dart';
import 'autostart_manager_unsupported.dart';
import 'autostart_manager_windows.dart';

abstract class AutostartManager {
  factory AutostartManager.create({
    required String appName,
    required String appPath,
    required List<String> args,
  }) {
    if (kIsWeb) {
      return AutostartManagerUnsupported();
    }
    if (Platform.isWindows) {
      return AutostartManagerWindows(
        appName: appName,
        appPath: appPath,
        args: args,
      );
    } else if (Platform.isMacOS) {
      return AutostartManagerMacOS(
        appName: appName,
        appPath: appPath,
        args: args,
      );
    } else if (Platform.isLinux) {
      return AutostartManagerLinux(
        appName: appName,
        appPath: appPath,
        args: args,
      );
    }
    return AutostartManagerUnsupported();
  }

  AutostartManager({
    required this.appName,
    required this.appPath,
    this.args = const [],
  });

  final String appName;
  final String appPath;
  final List<String> args;

  static bool isPlatformSupported() =>
      !kIsWeb && (Platform.isLinux || Platform.isMacOS || Platform.isWindows);

  Future<bool> isEnabled();

  Future<void> enable();

  Future<void> disable();
}

void initAutostartManager({
  required String appName,
  required String appPath,
  required List<String> args,
}) {
  autostartManager = AutostartManager.create(
    appName: appName,
    appPath: appPath,
    args: args,
  );
}

late AutostartManager autostartManager;
