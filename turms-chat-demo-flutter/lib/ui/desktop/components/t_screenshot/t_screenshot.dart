import 'dart:ui' as ui;

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:image/image.dart' as img;

class TScreenshot extends StatelessWidget {
  const TScreenshot({
    Key? key,
    required this.child,
    required this.controller,
  }) : super(key: key);

  final Widget child;
  final TScreenshotController controller;

  @override
  Widget build(BuildContext context) => RepaintBoundary(
        key: controller._containerKey,
        child: child,
      );
}

class TScreenshotController {
  final _containerKey = GlobalKey();

  Future<ui.Image?> capture() async {
    final boundary = _containerKey.currentContext?.findRenderObject()
        as RenderRepaintBoundary?;
    if (boundary == null) {
      return null;
    }
    return boundary.toImage();
  }

  Future<Uint8List?> captureAsBytes({int quality = 80}) async {
    final image = await capture();
    if (image == null) {
      return null;
    }
    final byteData = await image.toByteData();
    if (byteData == null) {
      return null;
    }
    final outputImage = img.Image.fromBytes(
        width: image.width, height: image.height, bytes: byteData.buffer);
    return img.encodeJpg(outputImage, quality: quality);
  }
}
