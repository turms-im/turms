class FileTooLargeException implements Exception {
  const FileTooLargeException([this.allowedBytes, this.actualBytes]);

  final int? allowedBytes;
  final int? actualBytes;
}
