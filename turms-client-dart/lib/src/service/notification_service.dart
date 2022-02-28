import '../../turms_client.dart';
import '../model/request/turms_request.pb.dart';

typedef NotificationListener = void Function(TurmsRequest request);

class NotificationService {
  final TurmsClient _turmsClient;
  final List<NotificationListener> _notificationListeners = [];

  NotificationService(this._turmsClient) {
    _turmsClient.driver.addNotificationListener((notification) {
      final isBusinessNotification = notification.hasRelayedRequest() &&
          !notification.relayedRequest.hasCreateMessageRequest() &&
          !notification.hasCloseStatus();
      if (isBusinessNotification) {
        for (final listener in _notificationListeners) {
          listener.call(notification.relayedRequest);
        }
      }
    });
  }

  void addNotificationListener(NotificationListener listener) {
    _notificationListeners.add(listener);
  }

  void removeNotificationListener(NotificationListener listener) {
    _notificationListeners.remove(listener);
  }
}
