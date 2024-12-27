import 'package:fixnum/fixnum.dart';

import 'log_appender.dart';
import 'log_entry.dart';

class LogAppenderDatabase extends LogAppender {
  LogAppenderDatabase({
    required this.userId,
  });

  final Int64 userId;

  @override
  void append(LogEntry entry) {}
}
