import 'package:drift/backends.dart';
import 'package:drift/web.dart';

class DatabaseUtils {
  DatabaseUtils._();

  static QueryExecutor createDatabase({
    required String dbName,
    required bool isAppDatabase,
    bool inMemory = false,
    required bool logStatements,
  }) =>
      inMemory
          ? WebDatabase.withStorage(DriftWebStorage.volatile(),
              logStatements: logStatements,
              // We set this to true because we need to store int64
              readIntsAsBigInt: true)
          : WebDatabase('$dbName.sqlite',
              logStatements: logStatements, readIntsAsBigInt: true);
}
