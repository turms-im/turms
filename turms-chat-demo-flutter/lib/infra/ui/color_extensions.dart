import 'package:flutter/material.dart';

extension ColorBrightness on Color {
  Color darken([double factor = .1]) => Color.lerp(this, Colors.black, factor)!;

  Color lighten([double factor = .1]) =>
      Color.lerp(this, Colors.white, factor)!;

  bool isLight() => computeLuminance() > 0.5;

  bool isDark() => computeLuminance() <= 0.5;
}
