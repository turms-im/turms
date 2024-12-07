import 'dart:io';

import 'package:drift/drift.dart';
import 'package:drift/native.dart';

import '../../io/path_utils.dart';

class DatabaseUtils {
  DatabaseUtils._();

  static QueryExecutor createDatabase({
    required String dbName,
    required bool isAppDatabase,
    bool inMemory = false,
    required bool logStatements,
  }) {
    if (inMemory) {
      return NativeDatabase.memory(logStatements: logStatements);
    }
    return LazyDatabase(() async {
      final path = isAppDatabase
          ? PathUtils.joinPathInAppScope(['database', '$dbName.sqlite'])
          : PathUtils.joinPathInUserScope(['database', '$dbName.sqlite']);
      final file = File(path);
      return NativeDatabase.createInBackground(file, setup: (database) {
        // Configure for better performance.
        database
          ..execute('PRAGMA journal_mode=WAL;')
          ..execute('PRAGMA synchronous=NORMAL;');
      }, logStatements: logStatements);
    });
  }
}
