import 'package:drift/drift.dart';
import 'package:fixnum/fixnum.dart';

import '../../../infra/sqlite/app_database.dart';

class UserLoginInfoRepository {
  Future<List<UserLoginInfoTableData>> selectUserLoginInfos() {
    final selectResult = appDatabase.select(appDatabase.userLoginInfoTable)
      ..orderBy([
        (record) => OrderingTerm(
          expression: record.lastModifiedDate,
          mode: OrderingMode.desc,
        ),
      ]);
    return selectResult.get();
  }

  /// TODO: encrypt password for security.
  Future<void> upsert(Int64 userId, String password) async {
    final now = DateTime.now();
    await appDatabase
        .into(appDatabase.userLoginInfoTable)
        .insert(
          UserLoginInfoTableCompanion.insert(
            userId: userId,
            password: password,
            createdDate: now,
            lastModifiedDate: now,
          ),
          onConflict: DoUpdate(
            (old) => UserLoginInfoTableCompanion.custom(
              password: Constant(password),
              lastModifiedDate: Constant(now),
            ),
          ),
        );
  }

  Future<void> deleteAll() =>
      appDatabase.delete(appDatabase.userLoginInfoTable).go();
}

final userLoginInfoRepository = UserLoginInfoRepository();
