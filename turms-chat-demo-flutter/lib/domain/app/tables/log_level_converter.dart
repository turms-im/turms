import 'package:drift/drift.dart';

import '../../../infra/logging/log_level.dart';

class LogLevelConverter extends TypeConverter<LogLevel, int> {
  @override
  LogLevel fromSql(int fromDb) => LogLevel.fromInt(fromDb)!;

  @override
  int toSql(LogLevel value) => value.value;
}
