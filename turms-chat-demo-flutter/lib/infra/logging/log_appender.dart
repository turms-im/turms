import 'log_entry.dart';

abstract class LogAppender {
  Future<void> init() async {}

  Future<void> destroy() async {}

  void append(LogEntry entry);
}
