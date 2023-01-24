import 'dart:io';
import 'dart:typed_data';

import 'package:protobuf/protobuf.dart';

import '../model/proto/notification/turms_notification.pb.dart';
import '../model/proto/request/turms_request.pb.dart';
import '../transport/tcp_metrics.dart';
import 'service/connection_service.dart';
import 'service/heartbeat_service.dart';
import 'service/message_service.dart';
import 'state_store.dart';

class TurmsDriver {
  final StateStore _stateStore = StateStore();

  late final ConnectionService _connectionService;
  late final HeartbeatService _heartbeatService;
  late final DriverMessageService _messageService;

  TurmsDriver(
      String? host,
      int? port,
      int? connectTimeoutMillis,
      int? requestTimeoutMillis,
      int? minRequestIntervalMillis,
      int? heartbeatIntervalMillis) {
    _connectionService =
        ConnectionService(stateStore, host, port, connectTimeoutMillis)
          ..addOnDisconnectedListener(_onConnectionDisconnected)
          ..addMessageListener(_onMessage);
    _heartbeatService = HeartbeatService(_stateStore, heartbeatIntervalMillis);
    _messageService = DriverMessageService(
        _stateStore, requestTimeoutMillis, minRequestIntervalMillis);
  }

  // Getters

  StateStore get stateStore => _stateStore;

  // Close

  Future<void> close() => Future.wait([
        _connectionService.close(),
        _heartbeatService.close(),
        _messageService.close()
      ]);

  // Heartbeat Service

  void startHeartbeat() => _heartbeatService.start();

  void stopHeartbeat() => _heartbeatService.stop();

  Future<void> sendHeartbeat() => _heartbeatService.send();

  bool get isHeartbeatRunning => _heartbeatService.isRunning;

  // Connection Service

  Future<void> connect(
          {String? host,
          int? port,
          int? connectTimeoutMillis,
          bool? useTls,
          SecurityContext? context}) =>
      _connectionService.connect(
          host: host,
          port: port,
          connectTimeoutMillis: connectTimeoutMillis,
          useTls: useTls,
          context: context);

  Future<void> disconnect() => _connectionService.disconnect();

  bool get isConnected => _stateStore.isConnected;

  TcpMetrics? get connectionMetrics => stateStore.tcp?.metrics;

  // Connection Listeners

  void addOnConnectedListener(OnConnectedListener listener) =>
      _connectionService.addOnConnectedListener(listener);

  void addOnDisconnectedListener(OnDisconnectedListener listener) =>
      _connectionService.addOnDisconnectedListener(listener);

  void removeOnConnectedListener(OnConnectedListener listener) =>
      _connectionService.removeOnConnectedListener(listener);

  void removeOnDisconnectedListener(OnDisconnectedListener listener) =>
      _connectionService.removeOnDisconnectedListener(listener);

  // Message Service

  Future<TurmsNotification> send(GeneratedMessage message) async {
    final name = message.info_.messageName;
    final fieldName = name.substring(0, 1).toLowerCase() + name.substring(1);
    final request = TurmsRequest.create();
    final fieldInfo = request.info_.byName[fieldName];
    if (fieldInfo == null) {
      throw ArgumentError('Could not find the request type: $name');
    }
    request.setField(fieldInfo.tagNumber, message);
    final notification = await _messageService.sendRequest(request);
    if (request.hasCreateSessionRequest()) {
      _heartbeatService.start();
    }
    return notification;
  }

  void addNotificationListener(NotificationListener listener) =>
      _messageService.addNotificationListener(listener);

  void removeNotificationListener(NotificationListener listener) =>
      _messageService.removeNotificationListener(listener);

  // Intermediary functions as a mediator between services

  void _onConnectionDisconnected({Object? error, StackTrace? stackTrace}) {
    _stateStore.reset();
    _heartbeatService.onDisconnected(error: error, stackTrace: stackTrace);
    _messageService.onDisconnected(error: error, stackTrace: stackTrace);
  }

  void _onMessage(Uint8List message) {
    if (message.lengthInBytes > 0) {
      TurmsNotification notification;
      try {
        notification = TurmsNotification.fromBuffer(message);
      } catch (e, s) {
        print('Failed to parse TurmsNotification: $e\n$s');
        return;
      }
      if (_heartbeatService.rejectHeartbeatCompletersIfFail(notification)) {
        return;
      }
      if (notification.data.hasUserSession()) {
        final session = notification.data.userSession;
        _stateStore
          ..sessionId = session.sessionId
          ..serverId = session.serverId;
      } else if (notification.hasCloseStatus()) {
        _stateStore.isSessionOpen = false;
      }
      _messageService.didReceiveNotification(notification);
    } else {
      _heartbeatService.resolveHeartbeatCompleters();
    }
  }
}
