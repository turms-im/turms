import 'dart:async';

import 'package:drift/drift.dart';

import '../../logging/logger.dart';

class SqlLoggingQueryInterceptor extends QueryInterceptor {
  SqlLoggingQueryInterceptor();

  @override
  TransactionExecutor beginTransaction(QueryExecutor parent) {
    logger.debug('[Drift] Start transaction');
    return super.beginTransaction(parent);
  }

  @override
  Future<void> commitTransaction(TransactionExecutor inner) =>
      _run('Commit transaction', () => inner.send());

  @override
  Future<void> rollbackTransaction(TransactionExecutor inner) =>
      _run('Rollback transaction', () => inner.rollback());

  @override
  Future<void> runBatched(
    QueryExecutor executor,
    BatchedStatements statements,
  ) =>
      _run(
        'Run ${statements.statements}. Args: ${statements.arguments}}',
        () => executor.runBatched(statements),
      );

  @override
  Future<int> runInsert(
    QueryExecutor executor,
    String statement,
    List<Object?> args,
  ) =>
      _run(
        'Run `$statement`. Args $args',
        () => executor.runInsert(statement, args),
      );

  @override
  Future<int> runUpdate(
    QueryExecutor executor,
    String statement,
    List<Object?> args,
  ) =>
      _run(
        'Run `$statement`. Args $args',
        () => executor.runUpdate(statement, args),
      );

  @override
  Future<int> runDelete(
    QueryExecutor executor,
    String statement,
    List<Object?> args,
  ) =>
      _run(
        'Run `$statement`. Args $args',
        () => executor.runDelete(statement, args),
      );

  @override
  Future<void> runCustom(
    QueryExecutor executor,
    String statement,
    List<Object?> args,
  ) =>
      _run(
        'Run `$statement`. Args $args',
        () => executor.runCustom(statement, args),
      );

  @override
  Future<List<Map<String, Object?>>> runSelect(
    QueryExecutor executor,
    String statement,
    List<Object?> args,
  ) =>
      _run(
        'Run `$statement`. Args $args',
        () => executor.runSelect(statement, args),
      );

  Future<T> _run<T>(
    String description,
    FutureOr<T> Function() operation,
  ) async {
    final stopwatch = Stopwatch()..start();
    try {
      final result = await operation();
      logger.debug(
          '[Drift] $description. Took: ${stopwatch.elapsedMilliseconds}');
      return result;
    } catch (e) {
      logger.error(
          '[Drift] $description. Took: ${stopwatch.elapsedMilliseconds}. Cause: ($e)');
      rethrow;
    }
  }
}
