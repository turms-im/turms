import 'package:drift/drift.dart';
import 'package:drift_dev/api/migrations.dart';
import 'package:fixnum/fixnum.dart';
import 'package:flutter/foundation.dart';

import '../../domain/app/tables/app_setting_table.dart';
import '../../domain/user/tables/user_login_info_table.dart';
import '../env/env_vars.dart';
import 'converter/int64_converter.dart';
import 'core/database_utils.dart';

part 'app_database.g.dart';

@DriftDatabase(tables: [AppSettingTable, UserLoginInfoTable])
class AppDatabase extends _$AppDatabase {
  AppDatabase(super.e);

  @override
  int get schemaVersion => 1;

  @override
  MigrationStrategy get migration => MigrationStrategy(
    beforeOpen: (details) async {
      if (kDebugMode) {
        await validateDatabaseSchema();
      }
    },
  );
}

final appDatabase = AppDatabase(
  DatabaseUtils.createDatabase(
    dbName: 'app',
    isAppDatabase: true,
    logStatements: EnvVars.databaseLogStatements,
  ),
);
