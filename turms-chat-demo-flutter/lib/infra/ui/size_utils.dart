import 'dart:ui';

class SizeUtils {
  SizeUtils._();

  static Size keepAspectRatio(Size size, double maxWidth, double maxHeight) {
    final width = size.width;
    final height = size.height;
    if (width > maxWidth || height > maxHeight) {
      final ratio = width / height;
      return ratio > 1
          ? Size(maxWidth, (maxWidth / ratio).roundToDouble())
          : Size((maxHeight * ratio).roundToDouble(), maxHeight);
    }
    return size;
  }
}
