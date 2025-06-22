import 'dart:async';
import 'dart:io';
import 'dart:ui';

import 'package:flutter/foundation.dart';
import 'package:flutter/widgets.dart';
import 'package:path/path.dart';

import '../../../../../../../infra/crypto/crypto_utils.dart';
import '../../../../../../../infra/env/env_vars.dart';
import '../../../../../../../infra/http/file_too_large_exception.dart';
import '../../../../../../../infra/http/http_utils.dart';
import '../../../../../../../infra/http/resource_not_found_exception.dart';
import '../../../../../../../infra/io/path_utils.dart';
import '../../../../../../../infra/media/corrupted_media_file_exception.dart';
import '../../../../../../../infra/rust/api/image.dart';
import '../../../../../../../infra/task/task_utils.dart';
import '../../../../../../../infra/units/file_size_extensions.dart';
import '../message_media_file.dart';

class MessageImageProvider extends ImageProvider<MessageImageProvider> {
  MessageImageProvider(this.originalImageUrl, this.asThumbnail);

  final String originalImageUrl;
  final bool asThumbnail;
  final StreamController<ImageChunkEvent> chunkEvents =
      StreamController<ImageChunkEvent>();
  MessageMediaFile? mediaFile;

  @override
  Future<MessageImageProvider> obtainKey(ImageConfiguration configuration) =>
      SynchronousFuture<MessageImageProvider>(this);

  @override
  ImageStreamCompleter loadImage(
    MessageImageProvider key,
    ImageDecoderCallback decode,
  ) => MultiFrameImageStreamCompleter(
    codec: _load(decode),
    scale: 1.0,
    debugLabel: '$originalImageUrl:$asThumbnail',
    chunkEvents: chunkEvents.stream,
    informationCollector: () sync* {
      yield ErrorDescription('Cache Entry ID: $originalImageUrl:$asThumbnail');
    },
  );

  Future<Codec> _load(ImageDecoderCallback decode) async {
    final bytes = await _fetchImage();
    final buffer = await ImmutableBuffer.fromUint8List(bytes);
    return decode(buffer);
  }

  Future<Uint8List> _fetchImage() async {
    final url = originalImageUrl;
    final urlStr = url.toString();
    final ext = extension(urlStr);
    final fileBaseName = CryptoUtils.getSha256ByString(urlStr);
    final fileFullName = '$fileBaseName$ext';
    final outputOriginalImagePath = PathUtils.joinPathInUserScope([
      'files',
      fileFullName,
    ]);
    final outputThumbnailPath = PathUtils.joinPathInUserScope([
      'files',
      '$fileBaseName-thumbnail$ext',
    ]);
    if (asThumbnail) {
      final outputThumbnailFile = File(outputThumbnailPath);
      if (await outputThumbnailFile.exists()) {
        final bytes = await outputThumbnailFile.readAsBytes();
        this.mediaFile = MessageMediaFile(
          originalMediaUrl: url,
          thumbnailPath: outputThumbnailPath,
          thumbnailBytes: bytes,
        );
        return bytes;
      }
      chunkEvents.add(
        const ImageChunkEvent(
          cumulativeBytesLoaded: 0,
          expectedTotalBytes: null,
        ),
      );
      final mediaFile = await TaskUtils.cacheFutureProvider(
        id: 'download:$url',
        futureProvider: () =>
            _fetchImage0(url, outputOriginalImagePath, outputThumbnailPath),
      );
      this.mediaFile = mediaFile;
      return mediaFile.thumbnailBytes ?? mediaFile.originalMediaBytes!;
    } else {
      final outputOriginalImageFile = File(outputOriginalImagePath);
      if (await outputOriginalImageFile.exists()) {
        final bytes = await outputOriginalImageFile.readAsBytes();
        this.mediaFile = MessageMediaFile(
          originalMediaUrl: url,
          originalMediaPath: outputOriginalImagePath,
          originalMediaBytes: bytes,
        );
        return bytes;
      }
      chunkEvents.add(
        const ImageChunkEvent(
          cumulativeBytesLoaded: 0,
          expectedTotalBytes: null,
        ),
      );
      final mediaFile = await TaskUtils.cacheFutureProvider(
        id: 'download:$url',
        futureProvider: () =>
            _fetchImage0(url, outputOriginalImagePath, outputThumbnailPath),
      );
      this.mediaFile = mediaFile;
      return mediaFile.originalMediaBytes!;
    }
  }

  Future<MessageMediaFile> _fetchImage0(
    String uri,
    String outputOriginalImagePath,
    String outputThumbnailPath,
  ) async {
    final originalImageFile = await HttpUtils.downloadFile(
      uri: Uri.parse(uri),
      filePath: outputOriginalImagePath,
      maxBytes: EnvVars.messageImageMaxDownloadableSizeBytes.MB,
    );
    if (originalImageFile == null) {
      throw ResourceNotFoundException(uri);
    }
    final originalImageBytes = await originalImageFile.bytes;
    if (originalImageBytes.isEmpty) {
      throw ResourceNotFoundException(uri);
    }
    final resizeResult = await resize(
      inputPath: originalImageFile.file.path,
      outputPath: outputThumbnailPath,
      width: EnvVars.messageImageThumbnailSizeWidth.toInt(),
      height: EnvVars.messageImageThumbnailSizeHeight.toInt(),
    );
    final errorType = resizeResult.errorType;
    if (errorType == null) {
      // TODO: optimize memory usage.
      if (resizeResult.resized) {
        final thumbnailBytes = await File(outputThumbnailPath).readAsBytes();
        return MessageMediaFile(
          originalMediaUrl: uri,
          originalMediaPath: outputOriginalImagePath,
          originalMediaBytes: originalImageBytes,
          thumbnailPath: outputThumbnailPath,
          thumbnailBytes: thumbnailBytes,
        );
      } else {
        return MessageMediaFile(
          originalMediaUrl: uri,
          originalMediaPath: outputOriginalImagePath,
          originalMediaBytes: originalImageBytes,
        );
      }
    } else {
      return switch (errorType) {
        ResizeError.decoding => throw const CorruptedMediaFileException(),
        ResizeError.parameter => throw ArgumentError(),
        ResizeError.limits => throw const FileTooLargeException(),
        ResizeError.unsupported || ResizeError.ioError => throw Exception('io'),
      };
    }
  }

  void dispose() {
    PaintingBinding.instance.imageCache.evict(this, includeLive: false);
  }

  @override
  bool operator ==(Object other) {
    if (other.runtimeType != runtimeType) {
      return false;
    }
    return other is MessageImageProvider &&
        other.originalImageUrl == originalImageUrl &&
        other.asThumbnail == asThumbnail;
  }

  @override
  int get hashCode => originalImageUrl.hashCode ^ asThumbnail.hashCode;

  @override
  String toString() =>
      '${objectRuntimeType(this, 'MessageImageProvider')}("$originalImageUrl:$asThumbnail")';

  bool loaded() => mediaFile != null;
}
