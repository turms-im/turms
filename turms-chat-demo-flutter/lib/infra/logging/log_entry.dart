import 'log_level.dart';

class LogEntry {
  LogEntry(
    this.level,
    this.message, {
    DateTime? time,
    this.error,
    this.stackTrace,
  }) : timestamp = time ?? DateTime.now();

  final LogLevel level;
  final dynamic message;
  final Object? error;
  final StackTrace? stackTrace;

  final DateTime timestamp;
}
