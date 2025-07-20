import 'dart:io';

import 'package:drift/drift.dart';
import 'package:drift/native.dart';

import '../../io/path_utils.dart';
import 'sql_logging_query_executor.dart';

class DatabaseUtils {
  DatabaseUtils._();

  static QueryExecutor createDatabase({
    required String dbName,
    required bool isAppDatabase,
    bool inMemory = false,
    required bool logStatements,
  }) {
    QueryExecutor database;
    if (inMemory) {
      database = NativeDatabase.memory();
    } else {
      final path = isAppDatabase
          ? PathUtils.joinPathInAppScope(['database', '$dbName.sqlite'])
          : PathUtils.joinPathInUserScope(['database', '$dbName.sqlite']);
      database = LazyDatabase(() async {
        final file = File(path);
        return NativeDatabase.createInBackground(
          file,
          setup: (database) {
            // Configure for better performance.
            database
              ..execute('PRAGMA journal_mode=WAL;')
              ..execute('PRAGMA synchronous=NORMAL;');
          },
        );
      });
    }
    if (logStatements) {
      database = database.interceptWith(SqlLoggingQueryInterceptor());
    }
    return database;
  }
}
