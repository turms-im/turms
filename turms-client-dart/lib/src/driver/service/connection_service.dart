import 'dart:async';
import 'dart:io';
import 'dart:typed_data';

import '../../exception/response_exception.dart';
import '../../model/response_status_code.dart';
import '../../transport/tcp_client.dart';
import '../state_store.dart';
import 'base_service.dart';

typedef OnConnectedListener = void Function();
typedef OnDisconnectedListener = void Function(
    {Object? error, StackTrace? stackTrace});
typedef MessageListener = void Function(Uint8List message);

class _MessageDecoder {
  static const int _maxReadBufferCapacity = 8 * 1024 * 1024;

  int _readIndex = 0;
  int _tempPayloadLength = 0;
  int? _payloadLength;

  final List<int> _readBuffer = [];

  List<Uint8List> decodeMessages(List<int> bytes) {
    if ((_readBuffer.length + bytes.length) > _maxReadBufferCapacity) {
      throw Exception(
          'The read buffer has exceeded the maximum size $_maxReadBufferCapacity');
    }
    _readBuffer.addAll(bytes);
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
    _readIndex = 0;
    _tempPayloadLength = 0;
    _payloadLength = null;
    _readBuffer.clear();
  }

  Uint8List? _tryReadMessage() {
    _payloadLength ??= _tryReadVarInt();
    if (_payloadLength == null) {
      return null;
    }
    final end = _readIndex + _payloadLength!;
    if (_readBuffer.length < end) {
      return null;
    }
    final message = _readBuffer.sublist(_readIndex, end);
    _readBuffer.removeRange(0, end);
    _readIndex = 0;
    _payloadLength = null;
    return Uint8List.fromList(message);
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

  void _notifyOnDisconnectedListeners(Object? error, StackTrace? stackTrace) {
    for (final listener in _onDisconnectedListeners) {
      listener.call(error: error, stackTrace: stackTrace);
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
        throw ResponseException(
            code: ResponseStatusCode.clientSessionAlreadyEstablished);
      }
    }
    final tcp = TcpClient(_onSocketClosed, (bytes) {
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
