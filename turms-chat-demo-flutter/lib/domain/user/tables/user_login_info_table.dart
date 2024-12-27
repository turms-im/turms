import 'package:drift/drift.dart';

import '../../../infra/sqlite/converter/int64_converter.dart';

class UserLoginInfoTable extends Table {
  late final userId = int64().map(const Int64Converter())();

  late final password = text()();

  late final createdDate = dateTime()();

  late final lastModifiedDate = dateTime()();

  @override
  String get tableName => 'user_login_info';

  @override
  Set<Column> get primaryKey => {userId};

  @override
  bool get withoutRowId => true;

  @override
  bool get isStrict => true;
}
