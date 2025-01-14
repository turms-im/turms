import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:image/image.dart' as img;

import '../../../../infra/media/image_extensions.dart';
import '../../../../infra/media/image_shape.dart';
import '../../../../infra/media/image_utils.dart';
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
          aspectRatio: aspectRatio,
          fixCropRect: fixCropRect,
          containerSize: constraints.biggest,
          containerBackgroundColor: containerBackgroundColor,
          maskColor: maskColor,
          radius: radius,
          clipBehavior: clipBehavior,
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
    required this.aspectRatio,
    required this.fixCropRect,
    required this.containerSize,
    required this.containerBackgroundColor,
    required this.maskColor,
    required this.radius,
    required this.clipBehavior,
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

  final double? aspectRatio;
  final bool fixCropRect;

  final Size containerSize;
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
  _ImageCropperState createState() => _ImageCropperState();
}

class _ImageCropperState extends State<_ImageCropper> {
  TImageCropperController? _cropController;

  var _loading = true;
  late ImageCropperStateData _data;

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

  Future<void> _init([_ImageCropper? oldWidget]) async {
    if (widget.controller != oldWidget?.controller) {
      _cropController?.crop = _crop;
    }
    final image = widget.image;
    if (image != oldWidget?.image) {
      _loading = true;
      setState(() {});
      final imageInfo = await image.resolveImageInfo();
      if (!mounted) {
        return;
      }
      _imageInfo = imageInfo;
      final parsedImage = imageInfo.image;
      _data = ImageCropperStateData.create(
        Size(parsedImage.width.toDouble(), parsedImage.height.toDouble()),
        containerSize: widget.containerSize,
        imageShape: widget.imageShape,
        aspectRatio: widget.aspectRatio,
        scale: 1.0,
      );
      var appliedScale = false;
      if (widget.isImageInteractive) {
        appliedScale = _applyScale(_data.imageScaleToCoverContainer);
      }
      if (!appliedScale) {
        widget.onImageMoved?.call(_data.imageRect);
      }
      widget.onCropAreaMoved?.call(_data.cropRect, _data.imageRectToCrop);
      _loading = false;
      setState(() {});
    }
  }

  @override
  Widget build(BuildContext context) => _buildView(context);

  void _updateCropRect(ImageCropperStateData data) {
    _data = data;
    widget.onCropAreaMoved?.call(_data.cropRect, _data.imageRectToCrop);
    setState(() {});
  }

  Future<Uint8List> _crop() async => compute(
        _cropImage,
        [
          _imageInfo!.image,
          _data.imageRectToCrop,
          widget.imageShape,
        ],
      );

  void _onScaleStart(ScaleStartDetails detail) {
    _scaleOnStart = _data.scale;
  }

  void _onScaleUpdate(ScaleUpdateDetails detail) {
    _data = _data.updateImageRect(detail.focalPointDelta);
    widget.onImageMoved?.call(_data.imageRect);

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
        _data.scale,
        focalPoint: signal.localPosition,
      );
    } else if (dy < 0) {
      _applyScale(
        _data.scale,
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
    _data = _data.updateImageRectAndScale(
      nextScale,
      focalPoint: focalPoint,
    );
    widget.onImageMoved?.call(_data.imageRect);
    setState(() {});
    return true;
  }
}

extension _ImageCropperStateView on _ImageCropperState {
  Widget _buildView(BuildContext context) {
    if (_loading) {
      return const Center(child: CircularProgressIndicator());
    }
    final dotHandleDimension = widget.dotHandleDimension;
    final cropRect = _data.cropRect;
    final imageRect = _data.imageRect;
    return Stack(
      clipBehavior: widget.clipBehavior,
      children: [
        _buildImage(context, imageRect),
        IgnorePointer(
          child: ClipPath(
            clipper: switch (widget.imageShape) {
              ImageShape.circle => CircleCropAreaClipper(cropRect),
              ImageShape.rectangle =>
                RectCropAreaClipper(cropRect, widget.radius)
            },
            child: SizedBox(
              width: double.infinity,
              height: double.infinity,
              child: ColoredBox(color: widget.maskColor),
            ),
          ),
        ),
        if (!widget.isImageInteractive && !widget.fixCropRect)
          Positioned(
            left: cropRect.left,
            top: cropRect.top,
            child: MouseRegion(
              cursor: SystemMouseCursors.move,
              child: GestureDetector(
                onPanUpdate: (details) => _updateCropRect(
                  _data.moveRect(details.delta),
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
            onPanUpdate: widget.fixCropRect
                ? null
                : (details) => _updateCropRect(
                      _data.moveTopLeft(details.delta),
                    ),
            child: DotHandle(
              position: DotHandlePosition.topLeft,
              dimension: dotHandleDimension,
            ),
          ),
        ),
        Positioned(
          left: cropRect.right - (dotHandleDimension / 2),
          top: cropRect.top - (dotHandleDimension / 2),
          child: GestureDetector(
            onPanUpdate: widget.fixCropRect
                ? null
                : (details) => _updateCropRect(
                      _data.moveTopRight(details.delta),
                    ),
            child: DotHandle(
              // todo: RESpect transform (rotate, flip)
              position: DotHandlePosition.topRight,
              dimension: dotHandleDimension,
            ),
          ),
        ),
        Positioned(
          left: cropRect.left - (dotHandleDimension / 2),
          top: cropRect.bottom - (dotHandleDimension / 2),
          child: GestureDetector(
            onPanUpdate: widget.fixCropRect
                ? null
                : (details) => _updateCropRect(
                      _data.moveBottomLeft(details.delta),
                    ),
            child: DotHandle(
              position: DotHandlePosition.bottomLeft,
              dimension: dotHandleDimension,
            ),
          ),
        ),
        Positioned(
          left: cropRect.right - (dotHandleDimension / 2),
          top: cropRect.bottom - (dotHandleDimension / 2),
          child: GestureDetector(
            onPanUpdate: widget.fixCropRect
                ? null
                : (details) => _updateCropRect(
                      _data.moveBottomRight(details.delta),
                    ),
            child: DotHandle(
              position: DotHandlePosition.bottomRight,
              dimension: dotHandleDimension,
            ),
          ),
        ),
      ],
    );
  }

  Listener _buildImage(BuildContext context, Rect imageRect) {
    final containerSize = widget.containerSize;
    return Listener(
      onPointerSignal: _onPointerSignal,
      child: GestureDetector(
        onScaleStart: widget.isImageInteractive ? _onScaleStart : null,
        onScaleUpdate: widget.isImageInteractive ? _onScaleUpdate : null,
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
}

FutureOr<Uint8List> _cropImage(List<dynamic> args) {
  final originalImage = args[0] as img.Image;
  final rect = args[1] as Rect;
  final shape = args[2] as ImageShape;
  return ImageUtils.crop(
    image: originalImage,
    shape: shape,
    topLeft: Offset(rect.left, rect.top),
    bottomRight: Offset(rect.right, rect.bottom),
    outputFormat: img.ImageFormat.png,
  );
}
