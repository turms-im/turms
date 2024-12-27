import 'package:flutter/widgets.dart';

class Shortcut {
  const Shortcut(this.shortcutActivator, this.initialized);

  static const unset = Shortcut(null, false);

  final ShortcutActivator? shortcutActivator;

  /// Used to distinguish between `Shortcut(null, false)` and `Shortcut(null, true)`.
  final bool initialized;
}
