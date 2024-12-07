import 'package:drift/backends.dart';

class DatabaseUtils {
  DatabaseUtils._();

  static QueryExecutor createDatabase({
    required String dbName,
    required bool isAppDatabase,
    bool inMemory = false,
    required bool logStatements,
  }) {
    throw UnsupportedError(
      'Unsupported platform',
    );
  }
}
