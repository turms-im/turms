import 'dart:ui' as ui;

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../infra/media/image_extensions.dart';
import '../index.dart';

typedef CropRectProvider = Rect Function(ImageInfo imageInfo);

class TImage extends StatefulWidget {
  const TImage({
    super.key,
    required this.image,
    required this.cropRectProvider,
  });

  final ImageProvider image;
  final CropRectProvider cropRectProvider;

  @override
  State<TImage> createState() => _TImageState();
}

class _TImageState extends State<TImage> {
  late Future<ImageInfo> _loadImageInfoTask;

  @override
  void initState() {
    super.initState();
    _loadImageInfoTask = widget.image.resolveImageInfo();
  }

  @override
  void dispose() {
    widget.image.evict(cache: PaintingBinding.instance.imageCache);
    super.dispose();
  }

  @override
  void didUpdateWidget(covariant TImage oldWidget) {
    if (widget.image != oldWidget.image) {
      oldWidget.image.evict(cache: PaintingBinding.instance.imageCache);
      _loadImageInfoTask = widget.image.resolveImageInfo();
    }
    super.didUpdateWidget(oldWidget);
  }

  @override
  Widget build(BuildContext context) => TAsyncBuilder(
        future: _loadImageInfoTask,
        builder: (context, snapshot) => snapshot.when(
          loading: () => const CircularProgressIndicator(),
          error: (error, stackTrace) => Text('Error: $error'),
          data: (data) {
            final image = data!.image;
            return TRawImage(
                image: image, cropRect: widget.cropRectProvider(data));
          },
        ),
      );
}

class TRawImage extends StatelessWidget {
  const TRawImage({
    super.key,
    required this.image,
    required this.cropRect,
  });

  final ui.Image image;
  final Rect cropRect;

  @override
  Widget build(BuildContext context) => CustomPaint(
        painter: TImagePainter(
          image: image,
          cropRect: cropRect,
        ),
        size: Size(image.width.toDouble(), image.height.toDouble()),
      );
}

class TImagePainter extends CustomPainter {
  TImagePainter({
    required this.image,
    required this.cropRect,
  });

  final ui.Image image;
  final Rect cropRect;

  @override
  void paint(Canvas canvas, Size size) {
    final rect = Offset.zero & size;
    final imageSize = Size(image.width.toDouble(), image.height.toDouble());
    final sizes = applyBoxFit(BoxFit.fitWidth, imageSize, size);
    final outputSubRect = Alignment.center.inscribe(sizes.destination, rect);
    canvas.drawImageRect(
        image,
        cropRect,
        outputSubRect,
        Paint()
          ..isAntiAlias = true
          // Use medium instead of high to get the best image quality.
          // See [FilterQuality] for details.
          ..filterQuality = ui.FilterQuality.medium);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) => false;
}
