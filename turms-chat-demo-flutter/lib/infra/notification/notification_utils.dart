import 'dart:io';

import 'package:flutter_local_notifications/flutter_local_notifications.dart';
import 'package:windows_notification/notification_message.dart';
import 'package:windows_notification/windows_notification.dart';

import '../app/app_config.dart';

const linuxDetails = LinuxNotificationDetails();
const darwinDetails = DarwinNotificationDetails();
int id = 0;

class NotificationUtils {
  NotificationUtils._();

  static final FlutterLocalNotificationsPlugin _notificationPlugin =
      FlutterLocalNotificationsPlugin();
  static final _windowsNotification =
      WindowsNotification(applicationId: AppConfig.packageInfo.appName);

  static Future<void> initPlugin() async {
    await _notificationPlugin.initialize(
      const InitializationSettings(
          linux: LinuxInitializationSettings(
            defaultActionName: 'Open Notification',
          ),
          macOS: DarwinInitializationSettings()),
    );
  }

  // TODO: Use our custom notification implementation once multi-window is supported:
  // https://github.com/flutter/flutter/issues/30701
  static Future<void> showNotification(String header, String body) async {
    id++;
    if (Platform.isLinux) {
      await _notificationPlugin.show(
        id,
        header,
        body,
        const NotificationDetails(
          linux: linuxDetails,
        ),
      );
    } else if (Platform.isMacOS) {
      await _notificationPlugin.show(
        id,
        header,
        body,
        const NotificationDetails(
          macOS: darwinDetails,
        ),
      );
    } else if (Platform.isWindows) {
      await _windowsNotification.showNotificationPluginTemplate(
          NotificationMessage.fromPluginTemplate(
        id.toString(),
        header,
        body,
      ));
    }
  }
}
