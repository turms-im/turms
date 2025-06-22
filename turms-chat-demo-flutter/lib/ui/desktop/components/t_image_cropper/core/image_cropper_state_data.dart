import 'dart:math';

import 'package:flutter/widgets.dart';

import '../../../../../infra/media/image_shape.dart';
import 'rect_calculator.dart';

class ImageCropperStateData {
  factory ImageCropperStateData.create(
    Size imageSize, {
    required Size containerSize,
    required ImageShape imageShape,
    required double scale,
    required double? aspectRatio,
  }) {
    final isFitVertically = imageSize.aspectRatio < containerSize.aspectRatio;
    final calculator = isFitVertically
        ? const VerticalCalculator()
        : const HorizontalCalculator();
    final imageRect = calculator.calcImageRectToFitContainer(
      containerSize,
      imageSize.aspectRatio,
    );
    return ImageCropperStateData._(
      containerSize: containerSize,
      imageSize: imageSize,
      imageRect: imageRect,
      imageShape: imageShape,
      cropRect: calculator.calcInitialCropAreaRect(
        containerSize,
        imageRect,
        imageShape == ImageShape.circle ? 1.0 : aspectRatio ?? 1.0,
        1,
      ),
      minCropAreaDimension: 100,
      scale: scale,
      aspectRatio: aspectRatio,
    );
  }

  ImageCropperStateData._({
    required this.containerSize,
    required this.imageSize,
    required this.imageRect,
    required this.imageShape,
    required this.cropRect,
    required this.minCropAreaDimension,
    required this.scale,
    this.offset = Offset.zero,
    this.aspectRatio,
  });

  final Size containerSize;
  final Size imageSize;
  final Rect imageRect;
  final ImageShape imageShape;
  final Rect cropRect;
  final double minCropAreaDimension;
  final double scale;
  final Offset offset;
  final double? aspectRatio;

  late final isFitVertically =
      imageSize.aspectRatio < containerSize.aspectRatio;

  late final calculator = isFitVertically
      ? const VerticalCalculator()
      : const HorizontalCalculator();

  late final containerSizeRatio = calculator.calcContainerSizeRatio(
    imageSize,
    containerSize,
  );

  late final imageRectToCrop = Rect.fromLTWH(
    max(0, cropRect.left - imageRect.left) * containerSizeRatio / scale,
    max(0, cropRect.top - imageRect.top) * containerSizeRatio / scale,
    cropRect.width * containerSizeRatio / scale,
    cropRect.height * containerSizeRatio / scale,
  );

  late final imageScaleToCoverContainer = calculator
      .calcImageScaleToCoverContainer(containerSize, imageRect);

  ImageCropperStateData resetCropRect() => copyWith(
    imageRect: calculator.calcImageRectToFitContainer(
      containerSize,
      imageSize.aspectRatio,
    ),
  );

  ImageCropperStateData moveRect(Offset delta) => copyWith(
    cropRect: calculator.moveRect(cropRect, delta.dx, delta.dy, imageRect),
  );

  ImageCropperStateData moveTopLeft(Offset delta) => copyWith(
    cropRect: calculator.moveTopLeft(
      cropRect,
      minCropAreaDimension,
      delta.dx,
      delta.dy,
      imageRect,
      aspectRatio,
    ),
  );

  ImageCropperStateData moveTopRight(Offset delta) => copyWith(
    cropRect: calculator.moveTopRight(
      cropRect,
      minCropAreaDimension,
      delta.dx,
      delta.dy,
      imageRect,
      aspectRatio,
    ),
  );

  ImageCropperStateData moveBottomLeft(Offset delta) => copyWith(
    cropRect: calculator.moveBottomLeft(
      cropRect,
      minCropAreaDimension,
      delta.dx,
      delta.dy,
      imageRect,
      aspectRatio,
    ),
  );

  ImageCropperStateData moveBottomRight(Offset delta) => copyWith(
    cropRect: calculator.moveBottomRight(
      cropRect,
      minCropAreaDimension,
      delta.dx,
      delta.dy,
      imageRect,
      aspectRatio,
    ),
  );

  ImageCropperStateData updateImageRect(Offset offset) {
    var newLeft = imageRect.left + offset.dx;
    if (newLeft + imageRect.width < cropRect.right) {
      newLeft = cropRect.right - imageRect.width;
    }

    var newTop = imageRect.top + offset.dy;
    if (newTop + imageRect.height < cropRect.bottom) {
      newTop = cropRect.bottom - imageRect.height;
    }

    return copyWith(
      imageRect: Rect.fromLTWH(
        min(cropRect.left, newLeft),
        min(cropRect.top, newTop),
        imageRect.width,
        imageRect.height,
      ),
    );
  }

  ImageCropperStateData updateImageRectAndScale(
    double newScale, {
    Offset? focalPoint,
  }) {
    final baseSize = isFitVertically
        ? Size(
            containerSize.height * imageSize.aspectRatio,
            containerSize.height,
          )
        : Size(
            containerSize.width,
            containerSize.width / imageSize.aspectRatio,
          );

    // Clamp the scale
    newScale = max(
      newScale,
      max(cropRect.width / baseSize.width, cropRect.height / baseSize.height),
    );

    if (scale == newScale) {
      return this;
    }

    // Get new width
    final newWidth = baseSize.width * newScale;
    final horizontalFocalPointBias = focalPoint == null
        ? 0.5
        : (focalPoint.dx - imageRect.left) / imageRect.width;
    final leftPositionDelta =
        (newWidth - imageRect.width) * horizontalFocalPointBias;

    // Get new height
    final newHeight = baseSize.height * newScale;
    final verticalFocalPointBias = focalPoint == null
        ? 0.5
        : (focalPoint.dy - imageRect.top) / imageRect.height;
    final topPositionDelta =
        (newHeight - imageRect.height) * verticalFocalPointBias;

    // Get new position
    final newLeft = max(
      min(cropRect.left, imageRect.left - leftPositionDelta),
      cropRect.right - newWidth,
    );
    final newTop = max(
      min(cropRect.top, imageRect.top - topPositionDelta),
      cropRect.bottom - newHeight,
    );

    return copyWith(
      scale: newScale,
      imageRect: Rect.fromLTWH(newLeft, newTop, newWidth, newHeight),
    );
  }

  ImageCropperStateData copyWith({
    Size? containerSize,
    Size? imageSize,
    Rect? imageRect,
    ImageShape? imageShape,
    Rect? cropRect,
    double? minCropAreaDimension,
    double? scale,
    Offset? offset,
    double? aspectRatio,
  }) => ImageCropperStateData._(
    containerSize: containerSize ?? this.containerSize,
    imageSize: imageSize ?? this.imageSize,
    imageRect: imageRect ?? this.imageRect,
    imageShape: imageShape ?? this.imageShape,
    cropRect: cropRect ?? this.cropRect,
    minCropAreaDimension: minCropAreaDimension ?? this.minCropAreaDimension,
    scale: scale ?? this.scale,
    offset: offset ?? this.offset,
    aspectRatio: aspectRatio ?? this.aspectRatio,
  );
}
