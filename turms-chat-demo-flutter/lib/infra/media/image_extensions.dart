import 'dart:async';

import 'package:flutter/painting.dart';

extension ImageProviderExtensions on ImageProvider {
  Future<ImageInfo> resolveImageInfo(
      [ImageConfiguration configuration = ImageConfiguration.empty]) {
    final completer = Completer<ImageInfo>();
    resolve(configuration).addListener(ImageStreamListener(
      (imageInfo, _) async {
        completer.complete(imageInfo);
      },
      onError: completer.completeError,
    ));
    return completer.future;
  }
}
