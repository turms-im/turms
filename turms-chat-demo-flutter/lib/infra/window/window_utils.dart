import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:window_manager/window_manager.dart';

import 'window_event_listener.dart';

class WindowUtils {
  WindowUtils._();

  static Future<void> maximize() => windowManager.maximize();

  static Future<void> unmaximize() => windowManager.unmaximize();

  static Future<bool> isMaximized() => windowManager.isMaximized();

  static Future<void> minimize() => windowManager.minimize();

  static Future<bool> isAlwaysOnTop() => windowManager.isAlwaysOnTop();

  static Future<void> setAlwaysOnTop(bool isAlwaysOnTop) =>
      windowManager.setAlwaysOnTop(isAlwaysOnTop);

  static Future<bool> isVisible() => windowManager.isVisible();

  static Future<void> show({bool focus = true}) async {
    await windowManager.show();
    if (focus) {
      await windowManager.focus();
    }
  }

  static Future<void> hide() => windowManager.hide();

  static Future<void> focus() async => windowManager.focus();

  static Future<void> ensureInitialized() => windowManager.ensureInitialized();

  static Future<void> setupWindow({
    Size? size,
    Size? minimumSize,
    bool resizable = false,
    Color? backgroundColor,
    String? title,
  }) async {
    await windowManager.waitUntilReadyToShow(
      WindowOptions(
        minimumSize: minimumSize,
        size: size,
        backgroundColor: Colors.transparent,
        center: true,
        titleBarStyle: TitleBarStyle.hidden,
        skipTaskbar: false,
        title: title,
      ),
    );
    if (backgroundColor != null) {
      await windowManager.setBackgroundColor(backgroundColor);
    }
    // Note that we should not make the window frameless, resizable will not work
    // on Windows otherwise.
    // await windowManager.setAsFrameless();
    await windowManager.setResizable(resizable);
    await windowManager.setHasShadow(true);
  }

  static Future<void> startDragging() => windowManager.startDragging();

  static bool hasListeners() => windowManager.hasListeners;

  static void addListener(WindowListener listener) {
    windowManager.addListener(listener);
  }

  static void removeListener(WindowEventListener listener) {
    windowManager.removeListener(listener);
  }

  static Future<void> setSkipTaskbar(bool skip) async =>
      windowManager.setSkipTaskbar(skip);

  static Future<void> waitUntilInvisible() async {
    while (await WindowUtils.isVisible()) {
      await Future<void>.delayed(const Duration(milliseconds: 50));
    }
  }
}

final isWindowMaximizedViewModel = StateProvider<bool>((ref) => false);
