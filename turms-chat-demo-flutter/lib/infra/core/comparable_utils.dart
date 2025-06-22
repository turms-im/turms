import 'dart:typed_data';

import '../rust/api/icu.dart' as icu;

class ComparableUtils {
  ComparableUtils._();

  static int compare<T>(Comparable<T>? a, Comparable<T>? b) {
    if (a == null) {
      return b == null ? 0 : -1;
    }
    if (b == null) {
      return 1;
    }
    return a.compareTo(b as T);
  }

  static int compareBool(bool a, bool b) => a == b
      ? 0
      : a
      ? 1
      : -1;

  static int compareStrings(String locale, String s1, String s2) =>
      icu.compareStrings(locale: locale, s1: s1, s2: s2)!;

  static Uint16List compareStringList(String locale, List<String> strings) =>
      icu.compareStringVec(locale: locale, strings: strings)!;

  static List<T> sortByStrings<T>(
    String locale,
    List<T> items,
    String Function(T item) stringExtractor,
  ) {
    final strings = items.map(stringExtractor).toList();
    final indexes = icu.compareStringVec(locale: locale, strings: strings)!;
    return List.generate(items.length, (i) => items[indexes[i]]);
  }

  static Map<T, int> sortByStringsAsMap<T>(
    String locale,
    List<T> items,
    String Function(T item) stringExtractor,
  ) {
    final strings = items.map(stringExtractor).toList();
    final indexes = icu.compareStringVec(locale: locale, strings: strings)!;
    return Map.fromEntries(
      indexes.indexed.map((e) {
        final (i, index) = e;
        return MapEntry(items[index], i);
      }),
    );
  }
}
