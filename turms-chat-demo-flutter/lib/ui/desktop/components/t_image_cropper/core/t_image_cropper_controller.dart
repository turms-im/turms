import 'dart:typed_data';
import 'dart:ui';

import 'package:flutter/foundation.dart';

class CaptureResult {
  const CaptureResult(this.img, this.cropRect);

  final Image img;
  final Rect cropRect;
}

class TImageCropperController {
  Future<Uint8List?> Function()? cropAsBytes;
  Future<CaptureResult?> Function()? capture;
}
