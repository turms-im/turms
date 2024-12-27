import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../domain/user/view_models/user_settings_view_model.dart';
import '../l10n/view_models/index.dart';
import 'themes.dart';

final themeViewModel = StateProvider<ThemeData>((ref) {
  final observer = _PlatformBrightnessObserver(ref);
  final binding = WidgetsBinding.instance..addObserver(observer);
  ref.onDispose(() => binding.removeObserver(observer));

  final userSettings = ref.watch(userSettingsViewModel);
  final localeInfo = ref.watch(localeInfoViewModel);
  final fontFamily = _getFontFamily(localeInfo.locale);
  return switch (userSettings?.theme) {
    ThemeMode.light => getLightTheme(fontFamily: fontFamily),
    ThemeMode.dark => getDarkTheme(fontFamily: fontFamily),
    _ =>
      _getThemeData(binding.platformDispatcher.platformBrightness, fontFamily),
  };
});

ThemeData _getThemeData(Brightness brightness, String? fontFamily) =>
    switch (brightness) {
      Brightness.dark => getDarkTheme(fontFamily: fontFamily),
      Brightness.light => getLightTheme(fontFamily: fontFamily),
    };

String? _getFontFamily(Locale? locale) {
  if (!Platform.isWindows) {
    return null;
  }
  // FIXME: Used to fix the text rendering problem
  // mentioned in https://github.com/flutter/flutter/issues/63043.
  // Reference: https://learn.microsoft.com/en-us/windows/apps/design/globalizing/loc-international-fonts.
  return switch (locale?.languageCode) {
    'ja' => 'Yu Gothic UI',
    'ko' => 'Malgun Gothic',
    'zh' || 'zh_CN' => 'Microsoft YaHei UI',
    'zh_HK' || 'zh_TW' => 'Microsoft JhengHei UI',
    _ => null
  };
}

class _PlatformBrightnessObserver extends WidgetsBindingObserver {
  _PlatformBrightnessObserver(this.ref);

  final StateProviderRef<ThemeData> ref;

  @override
  void didChangePlatformBrightness() {
    final brightness =
        WidgetsBinding.instance.platformDispatcher.platformBrightness;
    final userSettings = ref.read(userSettingsViewModel);
    final localeInfo = ref.read(localeInfoViewModel);

    final themeMode = userSettings?.theme;
    if (themeMode == null || themeMode == ThemeMode.system) {
      ref.controller.state =
          _getThemeData(brightness, _getFontFamily(localeInfo.locale));
    }
  }
}
