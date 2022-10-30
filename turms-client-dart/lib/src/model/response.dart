import 'package:fixnum/fixnum.dart';

import '../../turms_client.dart';
import 'proto/notification/turms_notification.pb.dart';

class Response<T> {
  final DateTime timestamp;
  final Int64? requestId;
  final int code;
  final T data;

  Response(this.timestamp, this.requestId, this.code, this.data);

  @override
  int get hashCode =>
      timestamp.hashCode ^ requestId.hashCode ^ code.hashCode ^ data.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is Response &&
          timestamp == other.timestamp &&
          requestId == other.requestId &&
          code == other.code &&
          data == other.data;

  @override
  String toString() =>
      'Response{timestamp: $timestamp, requestId: $requestId, code: $code, data: $data}';

  Response<T> withData<T>(T data) => Response(timestamp, requestId, code, data);

  static Response<T> value<T>(T data) =>
      Response(DateTime.now(), null, ResponseStatusCode.ok, data);

  static Response<T?> nullValue<T>() =>
      Response(DateTime.now(), null, ResponseStatusCode.ok, null);

  static Response<List<T>> emptyList<T>() =>
      Response(DateTime.now(), null, ResponseStatusCode.ok, []);

  static Response<T> fromNotification<T>(TurmsNotification notification,
      T Function(TurmsNotification_Data data) dataTransformer) {
    if (!notification.hasCode()) {
      throw ResponseException(
          code: ResponseStatusCode.invalidNotification,
          reason:
              'Cannot parse a success response from a notification without code');
    }
    if (notification.isError) {
      throw ResponseException(
          code: ResponseStatusCode.invalidNotification,
          reason:
              'Cannot parse a success response from non-success notification');
    }
    T data;
    try {
      data = dataTransformer(notification.data);
    } on Exception catch (e) {
      throw ResponseException(
          code: ResponseStatusCode.invalidNotification,
          reason: 'Failed to transform notification data: ${notification.data}',
          cause: e);
    }
    return Response(
        DateTime.fromMillisecondsSinceEpoch(notification.timestamp.toInt()),
        notification.hasRequestId() ? notification.requestId : null,
        notification.code,
        data);
  }
}
