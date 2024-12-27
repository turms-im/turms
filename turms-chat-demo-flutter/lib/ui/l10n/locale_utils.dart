import 'dart:ui';

class LocaleUtils {
  LocaleUtils._();

  static Locale fromLanguageTag(String languageTag) {
    final subtags = languageTag.split('-');
    return switch (subtags.length) {
      1 => Locale(subtags[0]),
      2 => Locale(subtags[0], subtags[1]),
      3 => Locale.fromSubtags(
          languageCode: subtags[0],
          scriptCode: subtags[1],
          countryCode: subtags[2]),
      _ => throw ArgumentError(),
    };
  }
}
