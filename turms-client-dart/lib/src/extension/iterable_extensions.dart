import 'dart:core';

extension IterableExtensions<T> on Iterable<T> {
  bool get areAllNull {
    for (final item in this) {
      if (item != null) {
        return false;
      }
    }
    return true;
  }
}
