import 'package:drift/drift.dart';

import 'log_level_converter.dart';

class LogEntryTable extends Table {
  late final id = integer().autoIncrement()();

  late final level = integer().map(LogLevelConverter())();

  late final createdDate = dateTime()();

  late final message = text()();

  @override
  String get tableName => 'log_entry';

  @override
  bool get isStrict => true;
}
