import 'package:drift/drift.dart';
import 'package:drift/web.dart';

import 'sql_logging_query_executor.dart';

class DatabaseUtils {
  DatabaseUtils._();

  static QueryExecutor createDatabase({
    required String dbName,
    required bool isAppDatabase,
    bool inMemory = false,
    required bool logStatements,
  }) {
    QueryExecutor database = inMemory
        ? WebDatabase.withStorage(DriftWebStorage.volatile(),
            // We set this to true because we need to store int64
            readIntsAsBigInt: true)
        : WebDatabase('$dbName.sqlite', readIntsAsBigInt: true);
    if (logStatements) {
      database = database.interceptWith(SqlLoggingQueryInterceptor());
    }
    return database;
  }
}
