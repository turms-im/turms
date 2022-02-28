import 'dart:async';
import 'dart:io';
import 'dart:typed_data';

typedef OnBytesReceived = void Function(List<int> bytes);
typedef OnClose = void Function(dynamic error);

class TcpClient {
  late int port;
  late dynamic host;

  late Socket _socket;

  bool isOpen = false;
  bool isReading = false;

  OnClose? onClose;

  Future<void> connect(
      String host,
      int port,
      bool useTls,
      SecurityContext? context,
      Duration? timeout,
      OnBytesReceived onBytesReceived) async {
    if (isOpen) {
      throw StateError('The TCP client has connected');
    }
    var socket = await Socket.connect(host, port, timeout: timeout)
      ..setOption(SocketOption.tcpNoDelay, true);
    if (useTls) {
      try {
        socket = await SecureSocket.secure(socket,
            context: context ?? SecurityContext.defaultContext);
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
        onBytesReceived.call(bytes);
      } catch (e) {
        close(e);
      }
    }, onDone: () => tryCallOnClose(null), onError: tryCallOnClose);
  }

  void write(Uint8List bytes) {
    _socket.write(bytes);
  }

  void writeVarInt(int v) {
    for (var i = 0; i < 5; i++) {
      if ((v >> 7) == 0) {
        _socket.add([v & 0x7f]);
        break;
      } else {
        _socket.add([v | 0x80]);
      }
      v >>= 7;
    }
  }

  void writeVarIntLengthAndBytes(Uint8List bytes) {
    writeVarInt(bytes.lengthInBytes);
    _socket.add(bytes);
  }

  Future<void> close(dynamic error) async {
    await _socket.close();
    tryCallOnClose(error);
  }

  void tryCallOnClose(dynamic error) {
    if (isOpen) {
      onClose?.call(error);
    }
    isOpen = false;
  }
}
