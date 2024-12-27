import 'package:flutter/foundation.dart';

class WorkerManager {
  WorkerManager._();

  static Future<R> schedule<M, R>(ComputeCallback<M, R> callback, M message) =>
      // TODO: migrate to use isolate_manager
      compute(callback, message);
}
