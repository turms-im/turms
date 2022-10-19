import 'package:fixnum/fixnum.dart';
import 'package:turms_client_dart/turms_client.dart';

Future<void> main() async {
  // Initialize client
  final client = TurmsClient();

  // Listen to the offline event
  client.userService.addOnOfflineListener((info) => print(
      'onOffline: ${info.closeStatus}:${info.businessStatus}:${info.reason}'));

  // Listen to inbound notifications
  client.notificationService.addNotificationListener((notification) => print(
      'onNotification: Receive a notification from other users or server: $notification'));

  // Listen to inbound messages
  client.messageService.addMessageListener((message, _) => print(
      'onMessage: Receive a message from other users or server: $message'));

  await client.userService.login(Int64(1), password: '123');

  final users = (await client.userService.queryNearbyUsers(
          35.792657, 139.667651,
          maxCount: 10, maxDistance: 100))
      .data;
  print('nearby users: $users');

  final msgId = (await client.messageService
          .sendMessage(false, Int64(1), text: 'Hello Turms', burnAfter: 30))
      .data;
  print('message $msgId has been sent');

  final groupId = (await client.groupService.createGroup(
          'Turms Developers Group',
          announcement:
              'This is a group for the developers who are interested in Turms',
          intro: 'nope'))
      .data;
  print('group $groupId has been created');
}
