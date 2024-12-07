class StackfulException implements Exception {
  StackfulException(
      {required this.cause,
      required this.stackTrace,
      required this.suppressed});

  final Exception cause;
  final StackTrace stackTrace;
  final List<Exception> suppressed;

  @override
  String toString() =>
      'StackfulException(cause: $cause, stackTrace: $stackTrace, suppressed: $suppressed)';

  void addSuppressed(Exception e) {
    suppressed.add(e);
  }
}
