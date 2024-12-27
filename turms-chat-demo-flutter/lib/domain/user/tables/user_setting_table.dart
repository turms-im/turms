import 'package:drift/drift.dart';

/// The table should only be used for development purposes without the server.
/// The client should fetch user settings dynamically from the server in
/// production.
class UserSettingTable extends Table {
  late final id = integer()();

  late final value = sqliteAny()();

  late final createdDate = dateTime()();

  late final lastModifiedDate = dateTime()();

  @override
  String get tableName => 'user_setting';

  @override
  Set<Column> get primaryKey => {id};

  @override
  bool get withoutRowId => true;

  @override
  bool get isStrict => true;
}
