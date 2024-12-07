import 'dart:async';

typedef Callback = Future<bool> Function();

class TaskUtils {
  TaskUtils._();

  static final _idToCallback = <Object, Future<dynamic>>{};
  static final _idToTimer = <String, Timer>{};

  /// Use [Future] to eliminate unnecessary closure context:
  /// https://github.com/dart-lang/sdk/issues/36983
  static Future<T> cacheFuture<T>(
      {required Object id, required Future<T> future}) {
    final result = _idToCallback[id];
    if (result != null) {
      return result as Future<T>;
    }
    _idToCallback[id] = future;
    return future.whenComplete(() => _idToCallback.remove(id));
  }

  static Future<T> cacheFutureProvider<T>(
      {required Object id, required Future<T> Function() futureProvider}) {
    final result = _idToCallback[id];
    if (result != null) {
      return result as Future<T>;
    }
    final future = futureProvider.call();
    _idToCallback[id] = future;
    return future.whenComplete(() => _idToCallback.remove(id));
  }

  static Future<T> addTask<T>(
      {required Object id, required Future<T> Function() callback}) {
    final result = _idToCallback[id];
    if (result != null) {
      return result as Future<T>;
    }
    final value = callback();
    _idToCallback[id] = value;
    return value.whenComplete(() => _idToCallback.remove(id));
  }

  static Future<bool> addPeriodicTask(
      {required String id,
      required Duration duration,
      required Callback callback,
      bool runImmediately = false}) async {
    final timer = _idToTimer[id];
    if (timer != null) {
      return false;
    }
    if (runImmediately) {
      if (!await callback()) {
        return true;
      }
    }
    _idToTimer[id] = Timer.periodic(duration, (timer) async {
      if (!await callback()) {
        timer.cancel();
      }
      _idToTimer.remove(id);
    });
    return true;
  }

  static void removeTask(String id) {
    _idToTimer.remove(id)?.cancel();
  }
}
