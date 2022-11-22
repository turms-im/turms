import 'package:meta/meta.dart';

import '../state_store.dart';

abstract class BaseService {
  @protected
  final StateStore stateStore;

  BaseService(this.stateStore);

  Future<void> close();

  void onDisconnected({Object? error, StackTrace? stackTrace});
}
