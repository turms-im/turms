import 'package:fixnum/fixnum.dart';

import '../exception/response_exception.dart';
import '../model/proto/notification/turms_notification.pb.dart';
import '../model/response.dart';
import '../model/response_status_code.dart';

extension NotificationExtensions on TurmsNotification {
  Response<T> toResponse<T>(
          T Function(TurmsNotification_Data data) dataTransformer) =>
      Response.fromNotification(this, dataTransformer);

  Response<T?> toNullResponse<T>() =>
      Response.fromNotification(this, (data) => null);

  bool get isSuccess => hasCode() && ResponseStatusCode.isSuccessCode(code);

  bool get isError => hasCode() && ResponseStatusCode.isErrorCode(code);
}

extension NotificationDataExtensions on TurmsNotification_Data {
  Int64 getLongOrThrow() {
    if (!hasLong()) {
      throw ResponseException(
          code: ResponseStatusCode.invalidResponse,
          reason:
              'Could not get a long value from the invalid response: $this');
    }
    return long;
  }
}
