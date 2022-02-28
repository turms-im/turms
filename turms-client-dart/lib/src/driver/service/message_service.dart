import 'dart:async';
import 'dart:math';

import 'package:fixnum/fixnum.dart' show Int64;

import '../../model/notification/turms_notification.pb.dart';
import '../../model/request/turms_request.pb.dart';
import '../../model/turms_business_exception.dart';
import '../../model/turms_status_code.dart';
import '../state_store.dart';
import 'base_service.dart';

typedef NotificationListener = void Function(TurmsNotification notification);

class TurmsRequestContext {
  final Completer<TurmsNotification> completer;
  final Timer? timeoutTimer;

  TurmsRequestContext(this.completer, this.timeoutTimer);
}

class MessageService extends BaseService {
  static const int randomMax = 1 << 32;

  final Random _random = Random();

  late final int _requestTimeoutMillis;
  late final int _minRequestIntervalMillis;
  final List<NotificationListener> _notificationListeners = [];
  final Map<int, TurmsRequestContext> _requestMap = {};

  MessageService(StateStore stateStore, int? requestTimeoutMillis,
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
      } catch (e) {
        print(e);
      }
    }
  }

  // Request and notification

  Future<TurmsNotification> sendRequest(TurmsRequest request) async {
    if (request.hasCreateSessionRequest()) {
      if (stateStore.isSessionOpen) {
        throw TurmsBusinessException.fromCode(
            TurmsStatusCode.clientSessionAlreadyEstablished);
      }
    } else if (!stateStore.isConnected || !stateStore.isSessionOpen) {
      throw TurmsBusinessException.fromCode(
          TurmsStatusCode.clientSessionHasBeenClosed);
    }
    final now = DateTime.now().millisecondsSinceEpoch;
    final difference = now - stateStore.lastRequestDate;
    final isFrequent = _minRequestIntervalMillis > 0 &&
        difference <= _minRequestIntervalMillis;
    if (isFrequent) {
      throw TurmsBusinessException.fromCode(
          TurmsStatusCode.clientRequestsTooFrequent);
    }
    final requestId = _generateRandomId();
    request.requestId = Int64(requestId);
    final payload = request.writeToBuffer();
    stateStore.tcp!.writeVarIntLengthAndBytes(payload);
    final timeoutTimer = _requestTimeoutMillis > 0
        ? Timer(Duration(milliseconds: _requestTimeoutMillis), () {
            final context = _requestMap.remove(requestId);
            context?.completer.completeError(TurmsBusinessException.fromCode(
                TurmsStatusCode.requestTimeout));
          })
        : null;
    final completer = Completer<TurmsNotification>();
    final requestContext = TurmsRequestContext(completer, timeoutTimer);
    _requestMap[requestId] = requestContext;
    stateStore.lastRequestDate = now;
    return completer.future;
  }

  void didReceiveNotification(TurmsNotification notification) {
    final isResponse =
        !notification.hasRelayedRequest() && notification.hasRequestId();
    if (isResponse) {
      final requestId = notification.requestId;
      final context = _requestMap.remove(requestId);
      if (context != null) {
        context.timeoutTimer?.cancel();
        if (notification.hasCode()) {
          if (TurmsStatusCode.isSuccessCode(notification.code)) {
            context.completer.complete(notification);
          } else {
            context.completer.completeError(
                TurmsBusinessException.fromNotification(notification));
          }
        } else {
          context.completer.completeError(TurmsBusinessException(
              TurmsStatusCode.invalidNotification, 'The code is missing'));
        }
      }
    }
    _notifyNotificationListeners(notification);
  }

  int _generateRandomId() {
    int id;
    do {
      id = _random.nextInt(randomMax);
    } while (_requestMap.containsKey(id));
    return id;
  }

  void _rejectRequestCompleter(TurmsBusinessException exception) {
    _requestMap.removeWhere((key, context) {
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
  void onDisconnected() {
    final exception = TurmsBusinessException.fromCode(
        TurmsStatusCode.clientSessionHasBeenClosed);
    _rejectRequestCompleter(exception);
  }
}
