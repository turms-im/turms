import 'package:drift/drift.dart';
import 'package:fixnum/fixnum.dart';

import '../../../infra/sqlite/user_database.dart';
import '../models/user_setting.dart';

class UserSettingRepository {
  Int64? userId;
  String? userPassword;

  Future<void> upsert(
    Int64 userId,
    UserSetting<dynamic, dynamic> setting,
    dynamic settingValue,
  ) async {
    final sqlValue = setting.convertValueToSql(settingValue) as Object;
    final now = DateTime.now();
    final database = createUserDatabaseIfNotExists(userId);
    await database
        .into(database.userSettingTable)
        .insert(
          UserSettingTableCompanion.insert(
            id: setting.id,
            value: DriftAny(sqlValue),
            createdDate: now,
            lastModifiedDate: now,
          ),
          onConflict: DoUpdate(
            (old) => UserSettingTableCompanion.custom(
              value: Constant(DriftAny(sqlValue)),
              lastModifiedDate: Constant(now),
            ),
          ),
        );
  }

  Future<int> delete(
    Int64 userId,
    UserSetting<dynamic, dynamic> setting,
  ) async {
    final database = createUserDatabaseIfNotExists(userId);
    final delete = database.delete(database.userSettingTable)
      ..where((t) => t.id.equals(setting.id));
    return delete.go();
  }

  Future<List<UserSettingTableData>> selectAll(Int64 userId) {
    final database = createUserDatabaseIfNotExists(userId);
    return database.select(database.userSettingTable).get();
  }
}

final userSettingRepository = UserSettingRepository();
