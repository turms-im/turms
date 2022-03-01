import 'package:fixnum/fixnum.dart';

import '../model/notification/turms_notification.pb.dart';
import '../model/turms_business_exception.dart';
import '../model/turms_status_code.dart';

extension NotificationExtensions on TurmsNotification {
  Int64 getFirstIdOrThrow() {
    if (!data.hasIds()) {
      final reason = 'Cannot get ID from the invalid response: $this';
      throw TurmsBusinessException(TurmsStatusCode.invalidResponse, reason);
    }
    return data.ids.values[0];
  }
}
