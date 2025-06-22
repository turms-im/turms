import 'package:drift/drift.dart';
import 'package:drift_dev/api/migrations.dart';
import 'package:fixnum/fixnum.dart';
import 'package:flutter/foundation.dart';

import '../../domain/message/models/message_type.dart';
import '../../domain/message/tables/message_table.dart';
import '../env/env_vars.dart';
import 'converter/int64_converter.dart';
import 'converter/uint8_matrix_converter.dart';
import 'core/database_utils.dart';

part 'user_message_database.g.dart';

@DriftDatabase(tables: [MessageTable])
class UserMessageDatabase extends _$UserMessageDatabase {
  UserMessageDatabase(super.e);

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

final _userIdToDatabase = <Int64, UserMessageDatabase>{};

UserMessageDatabase createUserMessageDatabaseIfNotExists(Int64 userId) =>
    _userIdToDatabase.putIfAbsent(
      userId,
      () => UserMessageDatabase(
        DatabaseUtils.createDatabase(
          dbName: 'user_message_${userId.toString()}',
          isAppDatabase: false,
          logStatements: EnvVars.databaseLogStatements,
        ),
      ),
    );
