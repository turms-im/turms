import 'package:flutter/services.dart';

extension LogicalKeyboardKeyExtensions on LogicalKeyboardKey {
  bool get isModifier =>
      this == LogicalKeyboardKey.control ||
      this == LogicalKeyboardKey.shift ||
      this == LogicalKeyboardKey.alt ||
      this == LogicalKeyboardKey.meta;

  LogicalKeyboardKey get normalizedKey => switch (this) {
        LogicalKeyboardKey.numpad0 => LogicalKeyboardKey.digit0,
        LogicalKeyboardKey.numpad1 => LogicalKeyboardKey.digit1,
        LogicalKeyboardKey.numpad2 => LogicalKeyboardKey.digit2,
        LogicalKeyboardKey.numpad3 => LogicalKeyboardKey.digit3,
        LogicalKeyboardKey.numpad4 => LogicalKeyboardKey.digit4,
        LogicalKeyboardKey.numpad5 => LogicalKeyboardKey.digit5,
        LogicalKeyboardKey.numpad6 => LogicalKeyboardKey.digit6,
        LogicalKeyboardKey.numpad7 => LogicalKeyboardKey.digit7,
        LogicalKeyboardKey.numpad8 => LogicalKeyboardKey.digit8,
        LogicalKeyboardKey.numpad9 => LogicalKeyboardKey.digit9,
        _ => synonyms.firstOrNull ?? this
      };
}
