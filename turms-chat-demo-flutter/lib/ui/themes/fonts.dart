import 'package:flutter/foundation.dart';

class Fonts {
  Fonts._();

  static final emojiFontFamily = switch (defaultTargetPlatform) {
    TargetPlatform.iOS || TargetPlatform.macOS => 'Apple Color Emoji',
    TargetPlatform.android ||
    TargetPlatform.fuchsia ||
    TargetPlatform.linux =>
      'Noto Color Emoji',
    TargetPlatform.windows => 'Segoe UI Emoji'
  };
  static const emojiFontFamilyFallback = [
    'Apple Color Emoji',
    'Segoe UI Emoji',
    'Segoe UI Symbol',
    'Noto Color Emoji',
    'Noto Color Emoji Compat',
    'Android Emoji',
    'EmojiSymbols'
  ];
}
