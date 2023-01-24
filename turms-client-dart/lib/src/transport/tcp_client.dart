import 'dart:async';
import 'dart:io';
import 'dart:typed_data';

import 'tcp_metrics.dart';

typedef OnBytesReceived = void Function(List<int> bytes);
typedef OnClosed = void Function({Object? error, StackTrace? stackTrace});

class TcpClient {
  late int port;
  late dynamic host;

  late Socket _socket;

  bool isOpen = false;
  bool isReading = false;
  TcpMetrics metrics = TcpMetrics();

  final OnClosed _onClosed;
  final OnBytesReceived _onBytesReceived;

  TcpClient(this._onClosed, this._onBytesReceived);

  // TODO: support passing InternetAddress
  Future<void> connect(String host, int port, bool useTls,
      SecurityContext? context, Duration? timeout) async {
    if (isOpen) {
      throw StateError('The TCP client has connected');
    }
    final stopwatch = Stopwatch()..start();
    final addresses = await InternetAddress.lookup(host);
    metrics.addressResolverTime = stopwatch.elapsedMilliseconds;
    if (addresses.isEmpty) {
      throw SocketException('Could not find the addresses of the host: $host');
    }
    final address = addresses[0];
    stopwatch.reset();
    var socket = await Socket.connect(address, port, timeout: timeout)
      ..setOption(SocketOption.tcpNoDelay, true);
    metrics.connectTime = stopwatch.elapsedMilliseconds;
    if (useTls) {
      try {
        stopwatch.reset();
        socket = await SecureSocket.secure(socket,
            context: context ?? SecurityContext.defaultContext);
        metrics.tlsHandshakeTime = stopwatch.elapsedMilliseconds;
      } catch (e) {
        try {
          await socket.close();
        } catch (e) {
          // ignored
        }
        rethrow;
      }
    }
    _socket = socket;
    this.host = host;
    this.port = port;
    isOpen = true;
    _socket.listen((bytes) {
      try {
        metrics.dataReceived += bytes.length;
        _onBytesReceived.call(bytes);
      } catch (e, s) {
        close(error: e, stackTrace: s);
      }
    },
        onDone: () => _onClosed0(null, null),
        onError: (dynamic error, dynamic stackTrace) => {
              if (stackTrace is StackTrace)
                {_onClosed0(error, stackTrace)}
              else
                {_onClosed0(error, null)}
            },
        cancelOnError: true);
  }

  void write(List<int> bytes) {
    _socket.add(bytes);
    metrics.dataSent += bytes.length;
  }

  void writeVarInt(int v) {
    for (var i = 0; i < 5; i++) {
      if ((v >> 7) == 0) {
        write([v & 0x7f]);
        break;
      } else {
        write([v | 0x80]);
      }
      v >>= 7;
    }
  }

  void writeVarIntLengthAndBytes(Uint8List bytes) {
    writeVarInt(bytes.lengthInBytes);
    write(bytes);
  }

  Future<void> close({Object? error, StackTrace? stackTrace}) async {
    try {
      await _socket.flush();
      await _socket.close();
    } finally {
      _onClosed0(error, stackTrace);
    }
  }

  void _onClosed0(Object? error, StackTrace? stackTrace) {
    if (isOpen) {
      try {
        _onClosed.call(error: error, stackTrace: stackTrace);
      } catch (e) {
        // TODO: log
      }
    }
    metrics = TcpMetrics();
    isOpen = false;
  }
}
