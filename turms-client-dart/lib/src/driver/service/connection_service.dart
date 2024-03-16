import 'dart:async';
import 'dart:io';

import 'package:collection/collection.dart';

import '../../../turms_client.dart';

typedef OnConnectedListener = void Function();
typedef OnDisconnectedListener = void Function(
    {Object? error, StackTrace? stackTrace});
typedef MessageListener = void Function(List<int> message);

class _MessageDecoder {
  static const int _maxReadBufferCapacity = 8 * 1024 * 1024;

  int _readIndex = 0;
  int _tempPayloadLength = 0;
  int? _payloadLength;

  final List<int> _readBuffer = [];

  final void Function(List<int> message) onDecoded;

  _MessageDecoder(this.onDecoded);

  void decodeMessages(List<int> bytes) {
    if ((_readBuffer.length + bytes.length) > _maxReadBufferCapacity) {
      throw Exception(
          'The read buffer has exceeded the maximum size $_maxReadBufferCapacity');
    }
    _readBuffer.addAll(bytes);
    while (_tryReadMessage()) {}
  }

  void clear() {
    _readIndex = 0;
    _tempPayloadLength = 0;
    _payloadLength = null;
    _readBuffer.clear();
  }

  bool _tryReadMessage() {
    _payloadLength ??= _tryReadVarInt();
    if (_payloadLength == null) {
      return false;
    }
    final end = _readIndex + _payloadLength!;
    if (_readBuffer.length < end) {
      return false;
    }
    final message = ListSlice(_readBuffer, _readIndex, end);
    onDecoded(message);
    _readBuffer.removeRange(0, end);
    _readIndex = 0;
    _payloadLength = null;
    return true;
  }

  int? _tryReadVarInt() {
    final length = _readBuffer.length;
    while (_readIndex < 5) {
      if (_readIndex >= length) {
        return null;
      }
      final byte = _readBuffer[_readIndex];
      _tempPayloadLength |= (byte & 0x7F) << (7 * _readIndex);
      _readIndex++;
      if (byte & 0x80 == 0) {
        final length = _tempPayloadLength;
        _tempPayloadLength = 0;
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

  late _MessageDecoder _decoder;

  factory ConnectionService(StateStore stateStore, String? host, int? port,
      int? connectTimeoutMillis) {
    final connectionService =
        ConnectionService._(stateStore, host, port, connectTimeoutMillis);
    connectionService._decoder =
        _MessageDecoder(connectionService._notifyMessageListeners);
    return connectionService;
  }

  ConnectionService._(
      super.stateStore, String? host, int? port, int? connectTimeoutMillis)
      : _initialHost = host ?? '127.0.0.1',
        _initialPort = port ?? 11510,
        _initialConnectTimeoutMillis = connectTimeoutMillis ?? 30 * 1000;

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

  void _notifyOnDisconnectedListeners(Object? error, StackTrace? stackTrace) {
    for (final listener in _onDisconnectedListeners) {
      listener.call(error: error, stackTrace: stackTrace);
    }
  }

  void _notifyMessageListeners(List<int> message) {
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
        throw ResponseException(
            code: ResponseStatusCode.clientSessionAlreadyEstablished);
      }
    }
    final tcp = TcpClient(_onSocketClosed, _decoder.decodeMessages);
    connectTimeoutMillis ??= _initialConnectTimeoutMillis;
    final timeout = connectTimeoutMillis > 0
        ? Duration(milliseconds: connectTimeoutMillis)
        : null;
    await tcp.connect(host ?? _initialHost, port ?? _initialPort,
        useTls ?? false, context, timeout);
    stateStore.tcp = tcp;
    _onSocketOpened();
  }

  Future<void> disconnect() async {
    if (stateStore.isConnected) {
      stateStore.isConnected = false;
      await stateStore.tcp?.close();
    }
    _decoder.clear();
  }

  // Lifecycle hooks

  void _onSocketOpened() {
    stateStore.isConnected = true;
    _notifyOnConnectedListeners();
  }

  void _onSocketClosed({Object? error, StackTrace? stackTrace}) {
    _decoder.clear();
    stateStore.isConnected = false;
    _notifyOnDisconnectedListeners(error, stackTrace);
  }

  @override
  Future<void> close() => disconnect();

  @override
  void onDisconnected({Object? error, StackTrace? stackTrace}) {}
}
