class CorruptedMediaFileException implements Exception {
  const CorruptedMediaFileException([this.message]);

  final String? message;
}
