import '../../turms_client.dart';

typedef NotificationListener = void Function(Notification notification);

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
          listener.call(Notification(
              DateTime.fromMillisecondsSinceEpoch(
                  notification.timestamp.toInt()),
              notification.requesterId,
              notification.relayedRequest));
        }
      }
    });
  }

  /// Add a notification listener to receive notifications.
  /// Note: This listener will receive all kinds of notifications excluding messages.
  /// To listen to messages, use [MessageService.addMessageListener] instead.
  void addNotificationListener(NotificationListener listener) {
    _notificationListeners.add(listener);
  }

  /// Remove a notification listener.
  void removeNotificationListener(NotificationListener listener) {
    _notificationListeners.remove(listener);
  }
}
