import '../../turms_client.dart';

class Notification {
  final DateTime timestamp;
  final TurmsRequest relayedRequest;

  Notification(this.timestamp, this.relayedRequest);

  @override
  int get hashCode => timestamp.hashCode ^ relayedRequest.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is Notification &&
          timestamp == other.timestamp &&
          relayedRequest == other.relayedRequest;

  @override
  String toString() =>
      'Notification{timestamp: $timestamp, relayedRequest: $relayedRequest}';
}
