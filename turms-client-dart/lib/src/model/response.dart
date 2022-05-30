import 'package:fixnum/fixnum.dart';

import '../../turms_client.dart';
import 'proto/notification/turms_notification.pb.dart';

class Response<T> {
  final Int64? requestId;
  final int code;
  final T? data;

  Response(this.requestId, this.code, this.data);

  static Response<T> value<T>(T data) =>
      Response(null, ResponseStatusCode.ok, data);

  static Response<T?> nullValue<T>() =>
      Response(null, ResponseStatusCode.ok, null);

  static Response<List<T>> emptyList<T>() =>
      Response(null, ResponseStatusCode.ok, []);

  static Response<T> fromNotification<T>(TurmsNotification notification,
      T Function(TurmsNotification_Data data) dataTransformer) {
    if (!notification.hasCode()) {
      throw ResponseException.fromCodeAndReason(
          ResponseStatusCode.invalidNotification,
          'Cannot parse a success response from a notification without code');
    }
    if (notification.isError) {
      throw ResponseException.fromCodeAndReason(
          ResponseStatusCode.invalidNotification,
          'Cannot parse a success response from non-success notification');
    }
    return Response(notification.hasRequestId() ? notification.requestId : null,
        notification.code, dataTransformer(notification.data));
  }
}
