import 'dart:async';
import 'dart:typed_data';
import 'dart:ui' as ui;

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:image/image.dart' as img;

import '../../../../infra/media/image_extensions.dart';
import '../../../../infra/media/image_shape.dart';
import '../../../../infra/media/image_utils.dart';
import '../../../../infra/ui/rect_extensions.dart';
import '../t_screenshot/t_screenshot.dart';
import '../t_transform/t_transform.dart';
import 'core/circle_crop_area_clipper.dart';
import 'core/dot_handle.dart';
import 'core/image_cropper_state_data.dart';
import 'core/rect_crop_area_clipper.dart';
import 'core/t_image_cropper_controller.dart';

typedef WillUpdateScale = bool Function(double newScale);
typedef OnCropAreaMoved = void Function(Rect containerRect, Rect imageRect);
typedef OnImageMoved = void Function(Rect imageRect);

class TImageCropper extends StatelessWidget {
  TImageCropper({
    super.key,
    required this.image,
    this.imageShape = ImageShape.rectangle,
    this.controller,
    this.flipX = false,
    this.flipY = false,
    this.rotationAngle = 0,
    this.aspectRatio,
    this.fixCropRect = false,
    this.containerBackgroundColor = Colors.white,
    this.maskColor = Colors.black26,
    this.radius = 0,
    this.clipBehavior = Clip.hardEdge,
    this.dotHandleDimension = 20,
    this.isImageInteractive = false,
    this.filterQuality = FilterQuality.medium,
    this.willUpdateScale,
    this.onCropAreaMoved,
    this.onImageMoved,
  });

  final ImageProvider image;
  final ImageShape imageShape;

  final TImageCropperController? controller;

  final bool flipX;
  final bool flipY;
  final double rotationAngle;

  final double? aspectRatio;
  final bool fixCropRect;

  final Color containerBackgroundColor;
  final Color maskColor;

  final double radius;
  final Clip clipBehavior;

  final double dotHandleDimension;

  final bool isImageInteractive;
  final FilterQuality filterQuality;

  final WillUpdateScale? willUpdateScale;
  final OnCropAreaMoved? onCropAreaMoved;
  final OnImageMoved? onImageMoved;

  @override
  Widget build(BuildContext context) => LayoutBuilder(
        builder: (c, constraints) => _ImageCropper(
          key: key,
          image: image,
          imageShape: imageShape,
          controller: controller,
          flipX: flipX,
          flipY: flipY,
          rotationAngle: rotationAngle,
          aspectRatio: aspectRatio,
          fixCropRect: fixCropRect,
          containerSize: constraints.biggest,
          containerBackgroundColor: containerBackgroundColor,
          maskColor: maskColor,
          radius: radius,
          dotHandleDimension: dotHandleDimension,
          isImageInteractive: isImageInteractive,
          filterQuality: filterQuality,
          willUpdateScale: willUpdateScale,
          onCropAreaMoved: onCropAreaMoved,
          onImageMoved: onImageMoved,
        ),
      );
}

class _ImageCropper extends StatefulWidget {
  const _ImageCropper({
    super.key,
    required this.image,
    required this.imageShape,
    required this.controller,
    required this.flipX,
    required this.flipY,
    required this.rotationAngle,
    required this.aspectRatio,
    required this.fixCropRect,
    required this.containerSize,
    required this.containerBackgroundColor,
    required this.maskColor,
    required this.radius,
    required this.dotHandleDimension,
    required this.isImageInteractive,
    required this.filterQuality,
    required this.willUpdateScale,
    required this.onCropAreaMoved,
    required this.onImageMoved,
  });

  final ImageProvider image;
  final ImageShape imageShape;

  final TImageCropperController? controller;

  final bool flipX;
  final bool flipY;
  final double rotationAngle;

  final double? aspectRatio;
  final bool fixCropRect;

  final Size containerSize;
  final Color containerBackgroundColor;
  final Color maskColor;

  final double radius;

  final double dotHandleDimension;

  final bool isImageInteractive;
  final FilterQuality filterQuality;

  final WillUpdateScale? willUpdateScale;
  final OnCropAreaMoved? onCropAreaMoved;
  final OnImageMoved? onImageMoved;

  @override
  _ImageCropperState createState() => _ImageCropperState();
}

class _ImageCropperState extends State<_ImageCropper> {
  final TScreenshotController _screenshotController = TScreenshotController();
  ui.Image? _capturedImage;

  var _loading = true;
  ImageCropperStateData? _data;

  ImageInfo? _imageInfo;

  double _scaleOnStart = 1.0;
  DateTime? _pointerSignalLastUpdated;

  @override
  void initState() {
    super.initState();
    _init();
  }

  @override
  void didUpdateWidget(covariant _ImageCropper oldWidget) {
    super.didUpdateWidget(oldWidget);
    _init(oldWidget);
  }

  @override
  Widget build(BuildContext context) => _buildView(context);

  Future<void> _init([_ImageCropper? oldWidget]) async {
    if (widget.controller != oldWidget?.controller) {
      widget.controller?.cropAsBytes = _cropAsBytes;
      widget.controller?.capture = _capture;
    }
    if (widget.flipX != oldWidget?.flipX ||
        widget.flipY != oldWidget?.flipY ||
        widget.rotationAngle != oldWidget?.rotationAngle ||
        widget.aspectRatio != oldWidget?.aspectRatio ||
        widget.containerSize != oldWidget?.containerSize) {
      _capturedImage = null;
      if (_data case final data?) {
        widget.onCropAreaMoved?.call(data.cropRect, data.imageRectToCrop);
      }
    }
    final image = widget.image;
    if (image != oldWidget?.image) {
      _capturedImage = null;
      _loading = true;
      final imageInfo = await image.resolveImageInfo();
      if (!mounted) {
        return;
      }
      _imageInfo = imageInfo;
      final parsedImage = imageInfo.image;
      final data = ImageCropperStateData.create(
        Size(parsedImage.width.toDouble(), parsedImage.height.toDouble()),
        containerSize: widget.containerSize,
        imageShape: widget.imageShape,
        aspectRatio: widget.aspectRatio,
        scale: 1.0,
      );
      var appliedScale = false;
      if (widget.isImageInteractive) {
        appliedScale = _applyScale(data.imageScaleToCoverContainer);
      }
      if (!appliedScale) {
        widget.onImageMoved?.call(data.imageRect);
      }
      widget.onCropAreaMoved?.call(data.cropRect, data.imageRectToCrop);
      _data = data;
      _loading = false;
      setState(() {});
    }
  }

  void _updateCropRect(ImageCropperStateData data) {
    _data = data;
    widget.onCropAreaMoved?.call(data.cropRect, data.imageRectToCrop);
    setState(() {});
  }

  Future<Uint8List?> _cropAsBytes() async {
    ByteBuffer? imageBytes;
    if (_imageInfo case final imageInfo?) {
      final byteData = await imageInfo.image.toByteData();
      imageBytes = byteData?.buffer;
    }
    if (imageBytes == null) {
      return null;
    }
    final image = _imageInfo!.image;
    return compute(
      _cropImage,
      [
        imageBytes,
        image.width,
        image.height,
        _data!.imageRectToCrop,
        widget.imageShape,
        widget.flipX,
        widget.flipY,
        widget.rotationAngle,
      ],
    );
  }

  Future<CaptureResult?> _capture() async {
    _capturedImage ??= await _screenshotController.capture();
    if (_capturedImage == null) {
      return null;
    }
    var cropRect = _data!.cropRect;
    final center = widget.containerSize.center(Offset.zero);
    cropRect = cropRect.rotate(center, widget.rotationAngle);
    if (widget.flipX) {
      if (widget.flipY) {
        cropRect = cropRect.flip(center);
      } else {
        cropRect = cropRect.flipX(center);
      }
    } else if (widget.flipY) {
      cropRect = cropRect.flipY(center);
    }
    return CaptureResult(_capturedImage!, cropRect);
  }

  void _onScaleStart(ScaleStartDetails detail) {
    _scaleOnStart = _data!.scale;
  }

  void _onScaleUpdate(ScaleUpdateDetails detail) {
    _data = _data!.updateImageRect(detail.focalPointDelta);
    widget.onImageMoved?.call(_data!.imageRect);

    _applyScale(
      _scaleOnStart * detail.scale,
      focalPoint: detail.localFocalPoint,
    );

    setState(() {});
  }

  void _onPointerSignal(PointerSignalEvent signal) {
    if (signal is! PointerScrollEvent) {
      return;
    }
    final now = DateTime.now();
    if (_pointerSignalLastUpdated == null ||
        now.difference(_pointerSignalLastUpdated!).inMilliseconds > 500) {
      _pointerSignalLastUpdated = now;
    }
    final dy = signal.scrollDelta.dy;
    if (dy > 0) {
      _applyScale(
        _data!.scale,
        focalPoint: signal.localPosition,
      );
    } else if (dy < 0) {
      _applyScale(
        _data!.scale,
        focalPoint: signal.localPosition,
      );
    }
  }

  bool _applyScale(
    double nextScale, {
    Offset? focalPoint,
  }) {
    final allowScale = widget.willUpdateScale?.call(nextScale) ?? true;
    if (!allowScale) {
      return false;
    }
    _data = _data!.updateImageRectAndScale(
      nextScale,
      focalPoint: focalPoint,
    );
    widget.onImageMoved?.call(_data!.imageRect);
    setState(() {});
    return true;
  }
}

extension _ImageCropperStateView on _ImageCropperState {
  Widget _buildView(BuildContext context) {
    if (_loading) {
      return const Center(child: CircularProgressIndicator());
    }
    final flipX = widget.flipX;
    final flipY = widget.flipY;
    final rotationAngle = widget.rotationAngle;

    final cropRect = _data!.cropRect;
    final imageRect = _data!.imageRect;

    final children = [
      TScreenshot(
        controller: _screenshotController,
        child: TTransform(
            flipX: flipX,
            flipY: flipY,
            rotationAngle: rotationAngle,
            child: _buildImage(context, imageRect)),
      ),
      TTransform(
          flipX: flipX,
          flipY: flipY,
          rotationAngle: rotationAngle,
          child: _buildMask(cropRect)),
    ];
    if (!widget.fixCropRect) {
      var topLeftHandlePosition = DotHandlePosition.topLeft;
      if (rotationAngle > 0) {
        topLeftHandlePosition = topLeftHandlePosition.rotate(rotationAngle);
      }
      if (flipX) {
        topLeftHandlePosition = topLeftHandlePosition.flipX();
      }
      if (flipY) {
        topLeftHandlePosition = topLeftHandlePosition.flipY();
      }
      children.add(TTransform(
          flipX: flipX,
          flipY: flipY,
          rotationAngle: rotationAngle,
          child: Stack(
            children: _buildHandles(cropRect, topLeftHandlePosition),
          )));
    }
    return Stack(
      children: children,
    );
  }

  Widget _buildImage(BuildContext context, Rect imageRect) {
    final containerSize = widget.containerSize;
    final isImageInteractive = widget.isImageInteractive;
    return Listener(
      onPointerSignal: _onPointerSignal,
      child: GestureDetector(
        onScaleStart: isImageInteractive ? _onScaleStart : null,
        onScaleUpdate: isImageInteractive ? _onScaleUpdate : null,
        child: Container(
          color: widget.containerBackgroundColor,
          width: containerSize.width,
          height: containerSize.height,
          child: Stack(
            children: [
              const SizedBox.expand(),
              Positioned(
                left: imageRect.left,
                top: imageRect.top,
                child: Image(
                  image: widget.image,
                  width: imageRect.width,
                  height: imageRect.height,
                  fit: BoxFit.contain,
                  filterQuality: widget.filterQuality,
                  // If false, the image widget will blink
                  // as the image loads,
                  // while the image is loaded from the memory or filesystem,
                  // it should be loaded very quickly.
                  // so we set it to true to avoiding blinking.
                  gaplessPlayback: true,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildMask(Rect cropRect) => IgnorePointer(
        child: ClipPath(
          clipper: switch (widget.imageShape) {
            ImageShape.circle => CircleCropAreaClipper(cropRect),
            ImageShape.rectangle => RectCropAreaClipper(cropRect, widget.radius)
          },
          child: SizedBox(
            width: double.infinity,
            height: double.infinity,
            child: ColoredBox(color: widget.maskColor),
          ),
        ),
      );

  List<Widget> _buildHandles(
      Rect cropRect, DotHandlePosition topLeftHandlePosition) {
    final dotHandleDimension = widget.dotHandleDimension;
    var handlePosition = topLeftHandlePosition;
    return [
      Positioned(
        left: cropRect.left,
        top: cropRect.top,
        child: MouseRegion(
          cursor: SystemMouseCursors.move,
          child: GestureDetector(
            onPanUpdate: (details) => _updateCropRect(
              _data!.moveRect(details.delta),
            ),
            child: Container(
              width: cropRect.width,
              height: cropRect.height,
              color: Colors.transparent,
            ),
          ),
        ),
      ),
      Positioned(
        left: cropRect.left - (dotHandleDimension / 2),
        top: cropRect.top - (dotHandleDimension / 2),
        child: GestureDetector(
          onPanUpdate: (details) => _updateCropRect(
            _data!.moveTopLeft(details.delta),
          ),
          child: DotHandle(
            position: handlePosition,
            dimension: dotHandleDimension,
          ),
        ),
      ),
      Positioned(
        left: cropRect.right - (dotHandleDimension / 2),
        top: cropRect.top - (dotHandleDimension / 2),
        child: GestureDetector(
          onPanUpdate: (details) => _updateCropRect(
            _data!.moveTopRight(details.delta),
          ),
          child: DotHandle(
            position: handlePosition = handlePosition.next(),
            dimension: dotHandleDimension,
          ),
        ),
      ),
      Positioned(
        left: cropRect.right - (dotHandleDimension / 2),
        top: cropRect.bottom - (dotHandleDimension / 2),
        child: GestureDetector(
          onPanUpdate: (details) => _updateCropRect(
            _data!.moveBottomRight(details.delta),
          ),
          child: DotHandle(
            position: handlePosition = handlePosition.next(),
            dimension: dotHandleDimension,
          ),
        ),
      ),
      Positioned(
        left: cropRect.left - (dotHandleDimension / 2),
        top: cropRect.bottom - (dotHandleDimension / 2),
        child: GestureDetector(
          onPanUpdate: (details) => _updateCropRect(
            _data!.moveBottomLeft(details.delta),
          ),
          child: DotHandle(
            position: handlePosition.next(),
            dimension: dotHandleDimension,
          ),
        ),
      ),
    ];
  }
}

FutureOr<Uint8List> _cropImage(List<dynamic> args) {
  final originalImage = args[0] as ByteBuffer;
  final originalImageWidth = args[1] as int;
  final originalImageHeight = args[2] as int;
  final rect = args[3] as Rect;
  final shape = args[4] as ImageShape;
  final flipX = args[5] as bool;
  final flipY = args[6] as bool;
  final rotationAngle = args[7] as double;
  return ImageUtils.cropAsBytes(
    image: img.Image.fromBytes(
        bytes: originalImage,
        width: originalImageWidth,
        height: originalImageHeight),
    shape: shape,
    topLeft: Offset(rect.left, rect.top),
    bottomRight: Offset(rect.right, rect.bottom),
    flipX: flipX,
    flipY: flipY,
    rotationAngle: rotationAngle,
    outputFormat: img.ImageFormat.png,
  );
}
