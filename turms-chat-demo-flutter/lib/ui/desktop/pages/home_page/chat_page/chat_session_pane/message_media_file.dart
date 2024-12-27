import 'dart:typed_data';

class MessageMediaFile {
  const MessageMediaFile(
      {required this.originalMediaUrl,
      this.originalMediaPath,
      this.originalMediaBytes,
      this.thumbnailImageUrl,
      this.thumbnailPath,
      this.thumbnailBytes});

  final String originalMediaUrl;

  final String? originalMediaPath;

  final Uint8List? originalMediaBytes;

  final String? thumbnailImageUrl;

  final String? thumbnailPath;

  final Uint8List? thumbnailBytes;
}
