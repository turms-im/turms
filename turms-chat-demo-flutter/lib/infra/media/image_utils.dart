import 'dart:math';
import 'dart:typed_data';
import 'dart:ui';

import 'package:image/image.dart';

import 'corrupted_media_file_exception.dart';
import 'image_shape.dart';

final class ImageUtils {
  ImageUtils._();

  static ImageFormat findFormat(Uint8List data) => findFormatForData(data);

  static Uint8List crop({
    required Image image,
    required ImageShape shape,
    required Offset topLeft,
    required Offset bottomRight,
    ImageFormat outputFormat = ImageFormat.jpg,
  }) {
    if (topLeft.dx.isNegative ||
        topLeft.dy.isNegative ||
        bottomRight.dx.isNegative ||
        bottomRight.dy.isNegative ||
        topLeft.dx.toInt() > image.width ||
        topLeft.dy.toInt() > image.height ||
        bottomRight.dx.toInt() > image.width ||
        bottomRight.dy.toInt() > image.height) {
      throw ArgumentError(
          'Invalid rect: (topLeft: $topLeft, bottomRight: $bottomRight)');
    }
    if (topLeft.dx > bottomRight.dx || topLeft.dy > bottomRight.dy) {
      throw ArgumentError(
          'Invalid rect: (topLeft: $topLeft, bottomRight: $bottomRight)');
    }
    final size = Size(
      bottomRight.dx - topLeft.dx,
      bottomRight.dy - topLeft.dy,
    );
    switch (shape) {
      case ImageShape.rectangle:
        return _findEncodeFuncForRect(outputFormat)(
          copyCrop(
            image,
            x: topLeft.dx.toInt(),
            y: topLeft.dy.toInt(),
            width: size.width.toInt(),
            height: size.height.toInt(),
          ),
        );
      case ImageShape.circle:
        final center = Offset(
          topLeft.dx + size.width / 2,
          topLeft.dy + size.height / 2,
        );
        final radius = min(size.width, size.height) / 2;
        return _findEncodeFuncForCircle(outputFormat)(
          copyCropCircle(
            image.numChannels == 4 ? image : image.convert(numChannels: 4),
            centerX: center.dx.toInt(),
            centerY: center.dy.toInt(),
            radius: radius.toInt(),
          ),
        );
    }
  }

  static Image parse(Uint8List data, ImageFormat? format) {
    final decodedImage = _decode(data, format);
    return switch (decodedImage?.exif.exifIfd.orientation ?? -1) {
      3 => copyRotate(decodedImage!, angle: 180),
      6 => copyRotate(decodedImage!, angle: 90),
      8 => copyRotate(decodedImage!, angle: -90),
      _ => decodedImage!,
    };
  }

  static Image? _decode(Uint8List data, ImageFormat? format) =>
      switch (format) {
        ImageFormat.jpg => decodeJpg(data),
        ImageFormat.png => decodePng(data),
        ImageFormat.bmp => decodeBmp(data),
        ImageFormat.ico => decodeIco(data),
        ImageFormat.webp => decodeWebP(data),
        _ => throw const CorruptedMediaFileException(),
      };

  static Uint8List Function(Image) _findEncodeFuncForRect(
          ImageFormat? outputFormat) =>
      switch (outputFormat) {
        ImageFormat.bmp => encodeBmp,
        ImageFormat.ico => encodeIco,
        ImageFormat.jpg => encodeJpg,
        ImageFormat.png => encodePng,
        _ => throw UnsupportedError('Unsupported format: $outputFormat'),
      };

  static Uint8List Function(Image) _findEncodeFuncForCircle(
          ImageFormat? outputFormat) =>
      switch (outputFormat) {
        ImageFormat.bmp => encodeBmp,
        ImageFormat.ico => encodeIco,
        ImageFormat.png => encodePng,
        _ => throw UnsupportedError('Unsupported format: $outputFormat'),
      };
}