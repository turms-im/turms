import 'dart:async';
import 'dart:convert';
import 'dart:core';
import 'dart:io';

import 'package:json_rpc_2/json_rpc_2.dart';
import 'package:shelf/shelf_io.dart' as shelf_io;
import 'package:shelf_web_socket/shelf_web_socket.dart';
import 'package:web_socket_channel/web_socket_channel.dart';

import '../app/app_utils.dart';
import '../io/path_utils.dart';
import '../rust/api/system.dart';
import 'rpc_client.dart';

File getAppProcessFile() {
  final appProcessFilePath = PathUtils.joinPathInAppScope(['app-process.json']);
  return File(appProcessFilePath);
}

class _AppProcessInfo {
  factory _AppProcessInfo.fromJson(Map<String, dynamic> json) =>
      _AppProcessInfo(pid: json['pid'] as int, port: json['port'] as int);

  const _AppProcessInfo({required this.pid, required this.port});

  final int pid;
  final int port;

  Map<String, dynamic> toJson() => {'pid': pid, 'port': port};
}

/// We use WebSocket because:
/// 1. We do want to support communicating with any other applications.
/// so that other developers can develop "plugins" for our application easily.
/// 2. We rarely send RPC requests,
/// so we don't need to acquire the extreme performance.
/// 3. Use WebSockets and JSON-RPC 2.0 so that it is quite easy to debug
/// and extend (e.g. use any tools that support
/// WebSockets and JSON-RPC 2.0 to communicate).
class RpcServer {
  RpcServer._(
    this._httpServer,
    this._appProcessFile,
    this._appProcessRandomAccessFile,
  );

  static const methodHealthcheck = 'healthcheck';
  static const methodClose = 'close';

  static Future<bool> _checkIfApplicationIsAlive(File appProcessFile) async {
    final json = await appProcessFile.readAsString();
    _AppProcessInfo info;
    try {
      if (json.isEmpty) {
        return false;
      }
      final jsonMap = jsonDecode(json) as Map<String, dynamic>;
      info = _AppProcessInfo.fromJson(jsonMap);
    } catch (e) {
      return false;
    }
    final isRunning = isProcessRunning(pid: info.pid);
    if (!isRunning) {
      return false;
    }
    try {
      final client = await RpcClient.connect(info.port);
      return await client.sendHealthcheckRequest();
    } catch (e) {
      final actualException = e is WebSocketChannelException ? e.inner : e;
      if (actualException is SocketException ||
          (actualException is HttpException &&
              actualException.message.toLowerCase()
              // e.g.: "Connection closed before full header was received"
              .contains('connection closed'))) {
        return false;
      }
    }
    return true;
  }

  static Future<RpcServer> create({int port = 29510}) async {
    final appProcessFile = getAppProcessFile();
    if (await appProcessFile.exists()) {
      if (await _checkIfApplicationIsAlive(appProcessFile)) {
        throw Exception('An application is already running');
      } else {
        await appProcessFile.delete();
      }
    } else if (!await appProcessFile.parent.exists()) {
      await appProcessFile.parent.create(recursive: true);
    }

    final handler = webSocketHandler((WebSocketChannel channel) {
      final server = Server(channel.cast<String>())
        ..registerMethod(methodHealthcheck, () => {'status': 'ok'})
        ..registerMethod(methodClose, AppUtils.close);
      unawaited(server.listen());
    });

    var file = await appProcessFile.open(mode: FileMode.write);
    try {
      file = await file.lock();
    } catch (e) {
      try {
        await file.close();
      } catch (_) {}
      rethrow;
    }

    HttpServer server;
    var retry = 0;
    try {
      while (true) {
        try {
          server = await shelf_io.serve(
            handler,
            'localhost',
            port,
            poweredByHeader: null,
          );
        } on SocketException catch (e) {
          if (e.osError?.message == 'EADDRINUSE') {
            if (retry++ >= 1000) {
              rethrow;
            }
            port++;
            continue;
          } else {
            rethrow;
          }
        }
        break;
      }
      await file.writeString(
        jsonEncode(_AppProcessInfo(pid: pid, port: port).toJson()),
      );
    } catch (e) {
      await file.unlock();
      await file.close();
      rethrow;
    }

    return RpcServer._(server, appProcessFile, file);
  }

  final HttpServer _httpServer;
  final File _appProcessFile;
  final RandomAccessFile _appProcessRandomAccessFile;

  int get port => _httpServer.port;

  Future<void> close() async {
    await _appProcessRandomAccessFile.unlock();
    await _appProcessRandomAccessFile.close();
    await _appProcessFile.delete();
    return _httpServer.close();
  }
}
