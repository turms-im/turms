import 'package:fixnum/fixnum.dart';

import '../exception/response_exception.dart';
import '../model/proto/notification/turms_notification.pb.dart';
import '../model/response_status_code.dart';

extension NotificationExtensions on TurmsNotification {
  Int64 getFirstIdOrThrow() {
    if (!data.hasIds()) {
      final reason = 'Cannot get ID from the invalid response: $this';
      throw ResponseException(ResponseStatusCode.invalidResponse, reason);
    }
    return data.ids.values[0];
  }

  bool get isSuccessCode => hasCode() && ResponseStatusCode.isSuccessCode(code);

  bool get isErrorCode => hasCode() && ResponseStatusCode.isErrorCode(code);
}
