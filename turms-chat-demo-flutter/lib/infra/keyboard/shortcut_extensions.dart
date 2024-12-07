import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'shortcut_utils.dart';

extension ShortcutActivatorExtensions on ShortcutActivator {
  /// Don't call "keys" to avoid confusing with [KeySet.keys].
  List<LogicalKeyboardKey> get keyList {
    switch (this) {
      case SingleActivator():
        final singleActivator = this as SingleActivator;
        if (singleActivator.control) {
          return [
            LogicalKeyboardKey.control,
            singleActivator.trigger,
          ];
        } else if (singleActivator.shift) {
          return [
            LogicalKeyboardKey.shift,
            singleActivator.trigger,
          ];
        } else if (singleActivator.alt) {
          return [
            LogicalKeyboardKey.alt,
            singleActivator.trigger,
          ];
        } else if (singleActivator.meta) {
          return [
            LogicalKeyboardKey.meta,
            singleActivator.trigger,
          ];
        } else {
          return [singleActivator.trigger];
        }
      case LogicalKeySet():
        return (this as LogicalKeySet).keys.toList()..sortKeys();
      default:
        return [];
    }
  }

  Uint8List toSqlBlob() => ShortcutUtils.toSqlBlob(this);

  String get description {
    // LogicalKeySet
    switch (this) {
      case LogicalKeySet():
        final keys = (this as LogicalKeySet).keyList;
        if (keys.isEmpty) {
          return '';
        }
        final buffer = StringBuffer();
        for (final key in keys) {
          if (buffer.isNotEmpty) {
            buffer.write(' + ');
          }
          if (key == LogicalKeyboardKey.control) {
            buffer.write('Ctrl');
          } else {
            buffer.write(key.keyLabel);
          }
        }
        return buffer.toString();
      case SingleActivator():
        final buffer = StringBuffer();
        final activator = this as SingleActivator;
        if (activator.alt) {
          buffer.write('Alt');
        }
        if (activator.control) {
          if (buffer.isNotEmpty) {
            buffer.write(' + ');
          }
          buffer.write('Ctrl');
        }
        if (activator.meta) {
          if (buffer.isNotEmpty) {
            buffer.write(' + ');
          }
          buffer.write('Meta');
        }
        if (activator.shift) {
          if (buffer.isNotEmpty) {
            buffer.write(' + ');
          }
          buffer.write('Shift');
        }
        buffer.write(' + ');
        buffer.write(activator.trigger.keyLabel);
        return buffer.toString();
      default:
        return '';
    }
  }

  bool hasSameKeys(ShortcutActivator other) =>
      listEquals(keyList, other.keyList);
}

extension SingleActivatorExtensions on SingleActivator {
  LogicalKeySet toLogicalKeySet() {
    final keys = <LogicalKeyboardKey>{};
    if (alt) {
      keys.add(LogicalKeyboardKey.alt);
    }
    if (control) {
      keys.add(LogicalKeyboardKey.control);
    }
    if (meta) {
      keys.add(LogicalKeyboardKey.meta);
    }
    if (shift) {
      keys.add(LogicalKeyboardKey.shift);
    }
    keys.add(trigger);
    return LogicalKeySet.fromSet(keys);
  }
}

extension ShortcutExtensionsIterable<T extends LogicalKeyboardKey> on List<T> {
  void sortKeys() {
    sort(_compareKey);
  }

  int _compareKey(LogicalKeyboardKey a, LogicalKeyboardKey b) {
    if (a == LogicalKeyboardKey.control) {
      return -1;
    } else if (b == LogicalKeyboardKey.control) {
      return 1;
    } else if (a == LogicalKeyboardKey.shift) {
      return -1;
    } else if (b == LogicalKeyboardKey.shift) {
      return 1;
    } else if (a == LogicalKeyboardKey.alt) {
      return -1;
    } else if (b == LogicalKeyboardKey.alt) {
      return 1;
    } else if (a == LogicalKeyboardKey.meta) {
      return -1;
    } else if (b == LogicalKeyboardKey.meta) {
      return 1;
    }
    return a.keyLabel.compareTo(b.keyLabel);
  }
}
