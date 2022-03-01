import 'dart:async';
import 'dart:io';
import 'dart:typed_data';

import '../../model/turms_business_exception.dart';
import '../../model/turms_status_code.dart';
import '../../transport/tcp_client.dart';
import '../state_store.dart';
import 'base_service.dart';

typedef OnConnectedListener = void Function();
typedef OnDisconnectedListener = void Function(dynamic error);
typedef MessageListener = void Function(Uint8List message);

class _MessageDecoder {
  int readIndex = 0;
  int tempPayloadLength = 0;
  int? payloadLength;

  // TODO: MAX LIMIT
  List<int> readBuffer = [];

  List<Uint8List> decodeMessages(List<int> bytes) {
    readBuffer.addAll(bytes);
    final messages = <Uint8List>[];
    while (true) {
      final message = _tryReadMessage();
      if (message == null) {
        break;
      }
      messages.add(message);
    }
    return messages;
  }

  void clear() {
    readBuffer.clear();
  }

  Uint8List? _tryReadMessage() {
    payloadLength ??= _tryReadVarInt();
    if (payloadLength != null) {
      final end = readIndex + payloadLength!;
      if (readBuffer.length >= end) {
        final message = readBuffer.sublist(readIndex, end);
        readBuffer.removeRange(0, end);
        readIndex = 0;
        payloadLength = null;
        return Uint8List.fromList(message);
      }
    }
    return null;
  }

  int? _tryReadVarInt() {
    final length = readBuffer.length;
    while (readIndex < 5) {
      if (readIndex >= length) {
        return null;
      }
      final byte = readBuffer[readIndex];
      tempPayloadLength |= (byte & 0x7F) << (7 * readIndex);
      readIndex++;
      if (byte & 0x80 == 0) {
        final length = tempPayloadLength;
        tempPayloadLength = 0;
        return length;
      }
    }
    throw Exception('VarInt input too big');
  }
}

class ConnectionService extends BaseService {
  final String _initialHost;
  final int _initialPort;
  final int _initialConnectTimeoutMillis;

  final List<OnConnectedListener> _onConnectedListeners = [];
  final List<OnDisconnectedListener> _onDisconnectedListeners = [];
  final List<MessageListener> _messageListeners = [];

  final _MessageDecoder _decoder = _MessageDecoder();

  ConnectionService(
      StateStore stateStore, String? host, int? port, int? connectTimeoutMillis)
      : _initialHost = host ?? '127.0.0.1',
        _initialPort = port ?? 11510,
        _initialConnectTimeoutMillis = connectTimeoutMillis ?? 30 * 1000,
        super(stateStore);

  // Listeners

  void addOnConnectedListener(OnConnectedListener listener) =>
      _onConnectedListeners.add(listener);

  void addOnDisconnectedListener(OnDisconnectedListener listener) =>
      _onDisconnectedListeners.add(listener);

  void addMessageListener(MessageListener listener) =>
      _messageListeners.add(listener);

  void removeOnConnectedListener(OnConnectedListener listener) =>
      _onConnectedListeners.remove(listener);

  void removeOnDisconnectedListener(OnDisconnectedListener listener) =>
      _onDisconnectedListeners.remove(listener);

  void removeMessageListener(MessageListener listener) =>
      _messageListeners.remove(listener);

  void _notifyOnConnectedListeners() {
    for (final listener in _onConnectedListeners) {
      listener.call();
    }
  }

  void _notifyOnDisconnectedListeners(dynamic error) {
    for (final listener in _onDisconnectedListeners) {
      listener.call(error);
    }
  }

  void _notifyMessageListeners(Uint8List message) {
    for (final listener in _messageListeners) {
      listener.call(message);
    }
  }

  // Connection

  Future<void> connect(
      {String? host,
      int? port,
      int? connectTimeoutMillis,
      bool? useTls = false,
      SecurityContext? context}) async {
    if (stateStore.isConnected) {
      if (host == stateStore.tcp?.host && port == stateStore.tcp?.port) {
        return;
      } else {
        throw TurmsBusinessException.fromCode(
            TurmsStatusCode.clientSessionAlreadyEstablished);
      }
    }
    final tcp = TcpClient(_onSocketClose, (bytes) {
      final messages = _decoder.decodeMessages(bytes);
      for (final message in messages) {
        _notifyMessageListeners(message);
      }
    });
    connectTimeoutMillis ??= _initialConnectTimeoutMillis;
    final timeout = connectTimeoutMillis > 0
        ? Duration(milliseconds: connectTimeoutMillis)
        : null;
    await tcp.connect(host ?? _initialHost, port ?? _initialPort,
        useTls ?? false, context, timeout);
    stateStore.tcp = tcp;
    _onSocketOpen();
  }

  Future<void> disconnect() async {
    _decoder.clear();
    if (stateStore.isConnected) {
      stateStore.isConnected = false;
      await stateStore.tcp?.close(null);
    }
  }

  // Lifecycle hooks

  void _onSocketOpen() {
    stateStore.isConnected = true;
    _notifyOnConnectedListeners();
  }

  void _onSocketClose(dynamic error) {
    _decoder.clear();
    stateStore.isConnected = false;
    _notifyOnDisconnectedListeners(error);
  }

  @override
  Future<void> close() => disconnect();

  @override
  void onDisconnected() {}
}
