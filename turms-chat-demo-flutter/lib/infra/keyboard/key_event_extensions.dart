import 'package:flutter/services.dart';

extension KeyEventExtensions on KeyEvent {
  bool get isControlPressed =>
      logicalKey == LogicalKeyboardKey.controlLeft ||
      logicalKey == LogicalKeyboardKey.controlRight;

  bool get isShiftPressed =>
      logicalKey == LogicalKeyboardKey.shiftLeft ||
      logicalKey == LogicalKeyboardKey.shiftRight;

  bool get isAltPressed =>
      logicalKey == LogicalKeyboardKey.altLeft ||
      logicalKey == LogicalKeyboardKey.altRight;

  bool get isMetaPressed =>
      logicalKey == LogicalKeyboardKey.metaLeft ||
      logicalKey == LogicalKeyboardKey.metaRight;
}
