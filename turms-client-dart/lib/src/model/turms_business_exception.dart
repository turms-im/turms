import 'notification/turms_notification.pb.dart';

class TurmsBusinessException implements Exception {
  final int code;
  final String? reason;

  TurmsBusinessException(this.code, this.reason);

  TurmsBusinessException.fromCode(int code) : this(code, null);

  TurmsBusinessException.fromNotification(TurmsNotification notification)
      : this(notification.code,
            notification.hasReason() ? notification.reason : null);

  @override
  String toString() => '$code:$reason';
}
