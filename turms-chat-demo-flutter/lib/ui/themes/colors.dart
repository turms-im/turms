import 'dart:ui';

class AppColors {
  AppColors._();

  static const primary = Color(0xff1890ff);

  // base color palettes

  static const green6 = Color(0xFF52c41a);
  static const red5 = Color(0xFFFF4D4F);
  static const gold6 = Color(0xFFfaad14);

  static const blue5 = Color(0xFF4096FF);
  static const blue6 = Color(0xFF1677FF);

  static const gray5 = Color(0xFFD9D9D9);
  static const gray6 = Color(0xFFbfbfbf);
  static const gray7 = Color(0xFF8c8c8c);
  static const gray9 = Color(0xFF434343);

  // utils

  /// Colors.white.withValues(alpha: 0.0)
  /// Reference: https://github.com/flutter/flutter/issues/14151#issuecomment-424104489
  static const transparentWhite = Color(0x00FFFFFF);

  // color filters

  static const greyscale = ColorFilter.matrix([
    0.2126, 0.7152, 0.0722, 0, 0, //
    0.2126, 0.7152, 0.0722, 0, 0,
    0.2126, 0.7152, 0.0722, 0, 0,
    0, 0, 0, 1, 0,
  ]);
}
