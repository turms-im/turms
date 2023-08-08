import 'package:fixnum/fixnum.dart';

import '../../turms_client.dart';

class Notification {
  final DateTime timestamp;
  final Int64 requesterId;
  final TurmsRequest relayedRequest;

  Notification(this.timestamp, this.requesterId, this.relayedRequest);

  @override
  int get hashCode =>
      timestamp.hashCode ^ requesterId.hashCode ^ relayedRequest.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is Notification &&
          timestamp == other.timestamp &&
          requesterId == other.requesterId &&
          relayedRequest == other.relayedRequest;

  @override
  String toString() =>
      'Notification{timestamp: $timestamp, requesterId: $requesterId, relayedRequest: $relayedRequest}';
}
