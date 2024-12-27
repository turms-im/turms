import 'dart:async';
import 'dart:convert';

import 'package:json_rpc_2/json_rpc_2.dart';
import 'package:web_socket_channel/web_socket_channel.dart';

import 'rpc_server.dart';

class RpcClient {
  RpcClient._({required Client client}) : _client = client;

  static Future<RpcClient> connect([int? port]) async {
    if (port == null) {
      final appProcessFile = getAppProcessFile();
      if (!await appProcessFile.exists()) {
        throw Exception('An application is not running');
      }
      final json = await appProcessFile.readAsString();
      final jsonMap = jsonDecode(json) as Map<String, dynamic>;
      port = jsonMap['port'] as int;
    }
    final socket = WebSocketChannel.connect(Uri.parse('ws://localhost:$port'));

    await socket.ready;

    final client = Client(socket.cast<String>());
    unawaited(client.listen());
    return RpcClient._(client: client);
  }

  final Client _client;

  Future<bool> sendHealthcheckRequest() async {
    final response = await _client.sendRequest(RpcServer.methodHealthcheck)
        as Map<String, dynamic>;
    return response['status'] == 'ok';
  }

  void sendCloseRequest() {
    _client.sendNotification(RpcServer.methodClose);
  }
}
