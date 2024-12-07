import 'package:drift/drift.dart';

class AppSettingTable extends Table {
  late final id = integer()();

  late final value = sqliteAny()();

  late final createdDate = dateTime()();

  late final lastModifiedDate = dateTime()();

  @override
  String get tableName => 'app_setting';

  @override
  Set<Column> get primaryKey => {id};

  @override
  bool get withoutRowId => true;

  @override
  bool get isStrict => true;
}
