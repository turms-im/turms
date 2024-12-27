import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

class ShortcutUtils {
  ShortcutUtils._();

  static Uint8List toSqlBlob(ShortcutActivator activator) {
    final finalKeys = <int>[];
    switch (activator) {
      case LogicalKeySet():
        final keys = activator.keys;
        if (keys.isEmpty) {
          return Uint8List.fromList([]);
        }
        for (final key in keys) {
          finalKeys.add(key.keyId);
        }
        break;
      case SingleActivator():
        if (activator.alt) {
          finalKeys.add(LogicalKeyboardKey.alt.keyId);
        }
        if (activator.control) {
          finalKeys.add(LogicalKeyboardKey.control.keyId);
        }
        if (activator.meta) {
          finalKeys.add(LogicalKeyboardKey.meta.keyId);
        }
        if (activator.shift) {
          finalKeys.add(LogicalKeyboardKey.shift.keyId);
        }
        finalKeys.add(activator.trigger.keyId);
        break;
      default:
        throw UnsupportedError('Unsupported ShortcutActivator: $activator');
    }
    return Uint8List.fromList(finalKeys);
  }

  static LogicalKeySet? fromSqlBlob(Uint8List keys) {
    if (keys.isEmpty) {
      return null;
    }
    final result = <LogicalKeyboardKey>{};
    for (final key in keys) {
      result.add(LogicalKeyboardKey(key));
    }
    return LogicalKeySet.fromSet(result);
  }
}
