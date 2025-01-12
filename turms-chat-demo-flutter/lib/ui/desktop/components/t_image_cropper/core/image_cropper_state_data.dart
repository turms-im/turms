import 'dart:math';

import 'package:flutter/widgets.dart';

import '../../../../../infra/media/image_shape.dart';
import 'rect_calculator.dart';

sealed class ImageCropperStateData {
  ImageCropperStateData({
    required this.ready,
    required this.containerSize,
    this.minCropAreaDimension = 100,
    required this.imageShape,
    required this.aspectRatio,
  });

  final bool ready;
  final Size containerSize;
  final double minCropAreaDimension;
  final ImageShape imageShape;
  final double? aspectRatio;
}

class PreparingImageCropperStateData extends ImageCropperStateData {
  PreparingImageCropperStateData({
    super.ready = false,
    required super.containerSize,
    required super.imageShape,
    required super.aspectRatio,
  });

  ReadyImageCropperStateData prepared(Size imageSize) =>
      ReadyImageCropperStateData.prepared(
        imageSize,
        containerSize: containerSize,
        imageShape: imageShape,
        aspectRatio: aspectRatio,
        scale: 1.0,
      );
}

class ReadyImageCropperStateData extends ImageCropperStateData {
  factory ReadyImageCropperStateData.prepared(
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

    return ReadyImageCropperStateData(
      containerSize: containerSize,
      imageSize: imageSize,
      imageRect: calculator.calcImageRectToFitContainer(
          containerSize, imageSize.aspectRatio),
      imageShape: imageShape,
      cropRect: Rect.zero,
      scale: scale,
      aspectRatio: aspectRatio,
    );
  }

  ReadyImageCropperStateData({
    super.ready = true,
    required super.containerSize,
    required this.imageSize,
    required this.imageRect,
    required super.imageShape,
    required this.cropRect,
    required this.scale,
    this.offset = Offset.zero,
    required super.aspectRatio,
  });

  final Size imageSize;
  final Rect cropRect;
  final Rect imageRect;
  final double scale;
  final Offset offset;

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

  late final imageScaleToCoverContainer =
      calculator.calcImageScaleToCoverContainer(containerSize, imageRect);

  ReadyImageCropperStateData resetCropRect() => copyWith(
        imageRect: calculator.calcImageRectToFitContainer(
            containerSize, imageSize.aspectRatio),
      );

  ReadyImageCropperStateData updateCropAreaRect({
    double? aspectRatio,
  }) =>
      copyWith(
        cropRect: calculator.calcInitialCropAreaRect(containerSize, imageRect,
            imageShape == ImageShape.circle ? 1.0 : aspectRatio ?? 1.0, 1),
      );

  ReadyImageCropperStateData moveRect(Offset delta) => copyWith(
          cropRect: calculator.moveRect(
        cropRect,
        delta.dx,
        delta.dy,
        imageRect,
      ));

  ReadyImageCropperStateData moveTopLeft(Offset delta) => copyWith(
          cropRect: calculator.moveTopLeft(
        cropRect,
        minCropAreaDimension,
        delta.dx,
        delta.dy,
        imageRect,
        aspectRatio,
      ));

  ReadyImageCropperStateData moveTopRight(Offset delta) => copyWith(
          cropRect: calculator.moveTopRight(
        cropRect,
        minCropAreaDimension,
        delta.dx,
        delta.dy,
        imageRect,
        aspectRatio,
      ));

  ReadyImageCropperStateData moveBottomLeft(Offset delta) => copyWith(
          cropRect: calculator.moveBottomLeft(
        cropRect,
        minCropAreaDimension,
        delta.dx,
        delta.dy,
        imageRect,
        aspectRatio,
      ));

  ReadyImageCropperStateData moveBottomRight(Offset delta) => copyWith(
          cropRect: calculator.moveBottomRight(
        cropRect,
        minCropAreaDimension,
        delta.dx,
        delta.dy,
        imageRect,
        aspectRatio,
      ));

  ReadyImageCropperStateData updateImageRect(Offset offset) {
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

  ReadyImageCropperStateData updateImageRectAndScale(
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
    final newLeft = max(min(cropRect.left, imageRect.left - leftPositionDelta),
        cropRect.right - newWidth);
    final newTop = max(min(cropRect.top, imageRect.top - topPositionDelta),
        cropRect.bottom - newHeight);

    return copyWith(
      scale: newScale,
      imageRect: Rect.fromLTWH(
        newLeft,
        newTop,
        newWidth,
        newHeight,
      ),
    );
  }

  ReadyImageCropperStateData copyWith({
    Size? containerSize,
    Size? imageSize,
    Rect? imageRect,
    ImageShape? imageShape,
    Rect? cropRect,
    double? scale,
    Offset? offset,
    double? aspectRatio,
  }) =>
      ReadyImageCropperStateData(
        containerSize: containerSize ?? this.containerSize,
        imageSize: imageSize ?? this.imageSize,
        imageRect: imageRect ?? this.imageRect,
        imageShape: imageShape ?? this.imageShape,
        cropRect: cropRect ?? this.cropRect,
        scale: scale ?? this.scale,
        offset: offset ?? this.offset,
        aspectRatio: aspectRatio ?? this.aspectRatio,
      );
}
