import '../transport/tcp_client.dart';

class StateStore {
  TcpClient? tcp;

  bool isConnected = false;

  // Session
  bool isSessionOpen = false;
  String? sessionId;
  String? serverId;

  // Request
  int lastRequestDate = 0;

  void reset() {
    tcp = null;
    isConnected = false;
    isSessionOpen = false;
    sessionId = null;
    serverId = null;
    lastRequestDate = 0;
  }
}
