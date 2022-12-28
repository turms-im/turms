import 'dart:async';
import 'dart:math';

import '../../exception/response_exception.dart';
import '../../extension/int_extensions.dart';
import '../../model/proto/notification/turms_notification.pb.dart';
import '../../model/proto/request/turms_request.pb.dart';
import '../../model/response_status_code.dart';
import '../state_store.dart';
import 'base_service.dart';

typedef NotificationListener = void Function(TurmsNotification notification);

class TurmsRequestContext {
  final Completer<TurmsNotification> completer;
  final Timer? timeoutTimer;

  TurmsRequestContext(this.completer, this.timeoutTimer);
}

class DriverMessageService extends BaseService {
  static const int randomMax = 1 << 32;

  final Random _random = Random();

  late final int _requestTimeoutMillis;
  late final int _minRequestIntervalMillis;
  final List<NotificationListener> _notificationListeners = [];
  final Map<int, TurmsRequestContext> _idToRequest = {};

  DriverMessageService(StateStore stateStore, int? requestTimeoutMillis,
      int? minRequestIntervalMillis)
      : super(stateStore) {
    _requestTimeoutMillis =
        requestTimeoutMillis == null || requestTimeoutMillis <= 0
            ? 60 * 1000
            : requestTimeoutMillis;
    _minRequestIntervalMillis = minRequestIntervalMillis ?? 0;
  }

  // Listeners

  void addNotificationListener(NotificationListener listener) =>
      _notificationListeners.add(listener);

  void removeNotificationListener(NotificationListener listener) =>
      _notificationListeners.remove(listener);

  void _notifyNotificationListeners(TurmsNotification notification) {
    for (final listener in _notificationListeners) {
      try {
        listener.call(notification);
      } catch (e, s) {
        print('$e\n$s');
      }
    }
  }

  // Request and notification

  Future<TurmsNotification> sendRequest(TurmsRequest request) async {
    if (request.hasCreateSessionRequest()) {
      if (stateStore.isSessionOpen) {
        throw ResponseException(
            code: ResponseStatusCode.clientSessionAlreadyEstablished);
      }
    } else if (!stateStore.isConnected || !stateStore.isSessionOpen) {
      throw ResponseException(
          code: ResponseStatusCode.clientSessionHasBeenClosed);
    }
    final now = DateTime.now().millisecondsSinceEpoch;
    final difference = now - stateStore.lastRequestDate;
    final isFrequent = _minRequestIntervalMillis > 0 &&
        difference <= _minRequestIntervalMillis;
    if (isFrequent) {
      throw ResponseException(
          code: ResponseStatusCode.clientRequestsTooFrequent);
    }
    final requestId = _generateRandomId();
    request.requestId = requestId.toInt64();
    final payload = request.writeToBuffer();
    stateStore.tcp!.writeVarIntLengthAndBytes(payload);
    final timeoutTimer = _requestTimeoutMillis > 0
        ? Timer(Duration(milliseconds: _requestTimeoutMillis), () {
            final context = _idToRequest.remove(requestId);
            context?.completer.completeError(
                ResponseException(code: ResponseStatusCode.requestTimeout));
          })
        : null;
    final completer = Completer<TurmsNotification>();
    final requestContext = TurmsRequestContext(completer, timeoutTimer);
    _idToRequest[requestId] = requestContext;
    stateStore.lastRequestDate = now;
    return completer.future;
  }

  void didReceiveNotification(TurmsNotification notification) {
    final isResponse =
        !notification.hasRelayedRequest() && notification.hasRequestId();
    if (isResponse) {
      final requestId = notification.requestId.toInt();
      final context = _idToRequest.remove(requestId);
      if (context != null) {
        context.timeoutTimer?.cancel();
        if (notification.hasCode()) {
          if (ResponseStatusCode.isSuccessCode(notification.code)) {
            context.completer.complete(notification);
          } else {
            context.completer.completeError(
                ResponseException.fromNotification(notification));
          }
        } else {
          context.completer.completeError(ResponseException(
              requestId:
                  notification.hasRequestId() ? notification.requestId : null,
              code: ResponseStatusCode.invalidNotification,
              reason: 'The code is missing'));
        }
      }
    }
    _notifyNotificationListeners(notification);
  }

  int _generateRandomId() {
    int id;
    do {
      id = _random.nextInt(randomMax);
    } while (_idToRequest.containsKey(id));
    return id;
  }

  void _rejectRequestCompleter(ResponseException exception) {
    _idToRequest.removeWhere((key, context) {
      context.completer.completeError(exception);
      return true;
    });
  }

  @override
  Future<void> close() async {
    onDisconnected();
    return;
  }

  @override
  void onDisconnected({Object? error, StackTrace? stackTrace}) {
    final exception = ResponseException(
        code: ResponseStatusCode.clientSessionHasBeenClosed,
        cause: error,
        stackTrace: stackTrace);
    _rejectRequestCompleter(exception);
  }
}
