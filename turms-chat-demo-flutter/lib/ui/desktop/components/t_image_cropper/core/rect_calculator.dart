import 'dart:math';

import 'package:flutter/widgets.dart';

sealed class RectCalculator {
  const RectCalculator();

  Rect calcImageRectToFitContainer(Size containerSize, double imageAspectRatio);

  double calcImageScaleToCoverContainer(Size containerSize, Rect imageRect);

  Rect calcInitialCropAreaRect(
      Size containerSize, Rect imageRect, double aspectRatio, double sizeRatio);

  double calcContainerSizeRatio(Size imageSize, Size containerSize);

  Rect moveRect(
    Rect cropRect,
    double deltaX,
    double deltaY,
    Rect imageRect,
  ) {
    if (cropRect.left + deltaX < imageRect.left) {
      deltaX = (cropRect.left - imageRect.left) * -1;
    }
    if (cropRect.right + deltaX > imageRect.right) {
      deltaX = imageRect.right - cropRect.right;
    }
    if (cropRect.top + deltaY < imageRect.top) {
      deltaY = (cropRect.top - imageRect.top) * -1;
    }
    if (cropRect.bottom + deltaY > imageRect.bottom) {
      deltaY = imageRect.bottom - cropRect.bottom;
    }
    return Rect.fromLTWH(
      cropRect.left + deltaX,
      cropRect.top + deltaY,
      cropRect.width,
      cropRect.height,
    );
  }

  Rect moveTopLeft(
    Rect cropRect,
    double minCropAreaDimension,
    double deltaX,
    double deltaY,
    Rect imageRect,
    double? aspectRatio,
  ) {
    final newLeft = max(imageRect.left,
        min(cropRect.left + deltaX, cropRect.right - minCropAreaDimension));
    final newTop = min(max(cropRect.top + deltaY, imageRect.top),
        cropRect.bottom - minCropAreaDimension);
    if (aspectRatio == null) {
      return Rect.fromLTRB(
        newLeft,
        newTop,
        cropRect.right,
        cropRect.bottom,
      );
    } else {
      if (deltaX.abs() > deltaY.abs()) {
        var newWidth = cropRect.right - newLeft;
        var newHeight = newWidth / aspectRatio;
        if (cropRect.bottom - newHeight < imageRect.top) {
          newHeight = cropRect.bottom - imageRect.top;
          newWidth = newHeight * aspectRatio;
        }

        return Rect.fromLTRB(
          cropRect.right - newWidth,
          cropRect.bottom - newHeight,
          cropRect.right,
          cropRect.bottom,
        );
      } else {
        var newHeight = cropRect.bottom - newTop;
        var newWidth = newHeight * aspectRatio;
        if (cropRect.right - newWidth < imageRect.left) {
          newWidth = cropRect.right - imageRect.left;
          newHeight = newWidth / aspectRatio;
        }
        return Rect.fromLTRB(
          cropRect.right - newWidth,
          cropRect.bottom - newHeight,
          cropRect.right,
          cropRect.bottom,
        );
      }
    }
  }

  Rect moveTopRight(
    Rect cropRect,
    double minCropAreaDimension,
    double deltaX,
    double deltaY,
    Rect imageRect,
    double? aspectRatio,
  ) {
    final newTop = min(max(cropRect.top + deltaY, imageRect.top),
        cropRect.bottom - minCropAreaDimension);
    final newRight = max(min(cropRect.right + deltaX, imageRect.right),
        cropRect.left + minCropAreaDimension);
    if (aspectRatio == null) {
      return Rect.fromLTRB(
        cropRect.left,
        newTop,
        newRight,
        cropRect.bottom,
      );
    } else {
      if (deltaX.abs() > deltaY.abs()) {
        var newWidth = newRight - cropRect.left;
        var newHeight = newWidth / aspectRatio;
        if (cropRect.bottom - newHeight < imageRect.top) {
          newHeight = cropRect.bottom - imageRect.top;
          newWidth = newHeight * aspectRatio;
        }

        return Rect.fromLTWH(
          cropRect.left,
          cropRect.bottom - newHeight,
          newWidth,
          newHeight,
        );
      } else {
        var newHeight = cropRect.bottom - newTop;
        var newWidth = newHeight * aspectRatio;
        if (cropRect.left + newWidth > imageRect.right) {
          newWidth = imageRect.right - cropRect.left;
          newHeight = newWidth / aspectRatio;
        }
        return Rect.fromLTRB(
          cropRect.left,
          cropRect.bottom - newHeight,
          cropRect.left + newWidth,
          cropRect.bottom,
        );
      }
    }
  }

  Rect moveBottomLeft(
    Rect cropRect,
    double minCropAreaDimension,
    double deltaX,
    double deltaY,
    Rect imageRect,
    double? aspectRatio,
  ) {
    final newLeft = max(imageRect.left,
        min(cropRect.left + deltaX, cropRect.right - minCropAreaDimension));
    final newBottom = max(min(cropRect.bottom + deltaY, imageRect.bottom),
        cropRect.top + minCropAreaDimension);

    if (aspectRatio == null) {
      return Rect.fromLTRB(
        newLeft,
        cropRect.top,
        cropRect.right,
        newBottom,
      );
    } else {
      if (deltaX.abs() > deltaY.abs()) {
        var newWidth = cropRect.right - newLeft;
        var newHeight = newWidth / aspectRatio;
        if (cropRect.top + newHeight > imageRect.bottom) {
          newHeight = imageRect.bottom - cropRect.top;
          newWidth = newHeight * aspectRatio;
        }

        return Rect.fromLTRB(
          cropRect.right - newWidth,
          cropRect.top,
          cropRect.right,
          cropRect.top + newHeight,
        );
      } else {
        var newHeight = newBottom - cropRect.top;
        var newWidth = newHeight * aspectRatio;
        if (cropRect.right - newWidth < imageRect.left) {
          newWidth = cropRect.right - imageRect.left;
          newHeight = newWidth / aspectRatio;
        }
        return Rect.fromLTRB(
          cropRect.right - newWidth,
          cropRect.top,
          cropRect.right,
          cropRect.top + newHeight,
        );
      }
    }
  }

  Rect moveBottomRight(
    Rect cropRect,
    double minCropAreaDimension,
    double deltaX,
    double deltaY,
    Rect imageRect,
    double? aspectRatio,
  ) {
    final newRight = min(imageRect.right,
        max(cropRect.right + deltaX, cropRect.left + minCropAreaDimension));
    final newBottom = max(min(cropRect.bottom + deltaY, imageRect.bottom),
        cropRect.top + minCropAreaDimension);
    if (aspectRatio == null) {
      return Rect.fromLTRB(
        cropRect.left,
        cropRect.top,
        newRight,
        newBottom,
      );
    } else {
      if (deltaX.abs() > deltaY.abs()) {
        var newWidth = newRight - cropRect.left;
        var newHeight = newWidth / aspectRatio;
        if (cropRect.top + newHeight > imageRect.bottom) {
          newHeight = imageRect.bottom - cropRect.top;
          newWidth = newHeight * aspectRatio;
        }

        return Rect.fromLTWH(
          cropRect.left,
          cropRect.top,
          newWidth,
          newHeight,
        );
      } else {
        var newHeight = newBottom - cropRect.top;
        var newWidth = newHeight * aspectRatio;
        if (cropRect.left + newWidth > imageRect.right) {
          newWidth = imageRect.right - cropRect.left;
          newHeight = newWidth / aspectRatio;
        }
        return Rect.fromLTWH(
          cropRect.left,
          cropRect.top,
          newWidth,
          newHeight,
        );
      }
    }
  }
}

class HorizontalCalculator extends RectCalculator {
  const HorizontalCalculator();

  @override
  Rect calcImageRectToFitContainer(
      Size containerSize, double imageAspectRatio) {
    final imageHeight = containerSize.width / imageAspectRatio;
    final top = (containerSize.height - imageHeight) / 2;
    final bottom = top + imageHeight;
    return Rect.fromLTWH(0, top, containerSize.width, bottom - top);
  }

  @override
  Rect calcInitialCropAreaRect(
    Size containerSize,
    Rect imageRect,
    double aspectRatio,
    double sizeRatio,
  ) {
    final imageRatio = imageRect.width / imageRect.height;

    // consider crop area will fit vertically or horizontally to image
    final initialSize = imageRatio > aspectRatio
        ? Size((imageRect.height * aspectRatio) * sizeRatio,
            imageRect.height * sizeRatio)
        : Size(containerSize.width * sizeRatio,
            (containerSize.width / aspectRatio) * sizeRatio);

    return Rect.fromLTWH(
      (containerSize.width - initialSize.width) / 2,
      (containerSize.height - initialSize.height) / 2,
      initialSize.width,
      initialSize.height,
    );
  }

  @override
  double calcImageScaleToCoverContainer(Size containerSize, Rect imageRect) =>
      containerSize.height / imageRect.height;

  @override
  double calcContainerSizeRatio(Size imageSize, Size containerSize) =>
      imageSize.width / containerSize.width;
}

class VerticalCalculator extends RectCalculator {
  const VerticalCalculator();

  @override
  Rect calcImageRectToFitContainer(
      Size containerSize, double imageAspectRatio) {
    final imageWidth = containerSize.height * imageAspectRatio;
    final left = (containerSize.width - imageWidth) / 2;
    final right = left + imageWidth;
    return Rect.fromLTWH(left, 0, right - left, containerSize.height);
  }

  @override
  Rect calcInitialCropAreaRect(
    Size containerSize,
    Rect imageRect,
    double aspectRatio,
    double sizeRatio,
  ) {
    final imageRatio = imageRect.width / imageRect.height;

    final initialSize = imageRatio < aspectRatio
        ? Size(imageRect.width * sizeRatio,
            imageRect.width / aspectRatio * sizeRatio)
        : Size((containerSize.height * aspectRatio) * sizeRatio,
            containerSize.height * sizeRatio);

    return Rect.fromLTWH(
      (containerSize.width - initialSize.width) / 2,
      (containerSize.height - initialSize.height) / 2,
      initialSize.width,
      initialSize.height,
    );
  }

  @override
  double calcImageScaleToCoverContainer(Size containerSize, Rect imageRect) =>
      containerSize.width / imageRect.width;

  @override
  double calcContainerSizeRatio(Size imageSize, Size containerSize) =>
      imageSize.height / containerSize.height;
}
