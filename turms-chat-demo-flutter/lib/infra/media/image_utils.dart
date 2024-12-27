import 'dart:async';
import 'dart:typed_data';
import 'dart:ui';

import 'package:image/image.dart' as image;

import 'image_format.dart';

class ImageUtils {
  ImageUtils._();

  static Uint8List crop({
    required image.Image original,
    required Offset topLeft,
    required Offset bottomRight,
    ImageFormat outputFormat = ImageFormat.jpeg,
  }) {
    if (topLeft.dx.isNegative ||
        topLeft.dy.isNegative ||
        bottomRight.dx.isNegative ||
        bottomRight.dy.isNegative ||
        topLeft.dx.toInt() > original.width ||
        topLeft.dy.toInt() > original.height ||
        bottomRight.dx.toInt() > original.width ||
        bottomRight.dy.toInt() > original.height) {
      throw ArgumentError(
          'Invalid rect: (topLeft: $topLeft, bottomRight: $bottomRight)');
    }
    if (topLeft.dx > bottomRight.dx || topLeft.dy > bottomRight.dy) {
      throw ArgumentError(
          'Invalid rect: (topLeft: $topLeft, bottomRight: $bottomRight)');
    }
    return Uint8List.fromList(
      image.encodePng(
        image.copyCrop(
          original,
          x: topLeft.dx.toInt(),
          y: topLeft.dy.toInt(),
          width: (bottomRight.dx - topLeft.dx).toInt(),
          height: (bottomRight.dy - topLeft.dy).toInt(),
        ),
      ),
    );
  }
}
