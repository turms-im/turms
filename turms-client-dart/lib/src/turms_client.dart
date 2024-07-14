import 'dart:async';

import 'driver/turms_driver.dart';
import 'service/conference_service.dart';
import 'service/conversation_service.dart';
import 'service/group_service.dart';
import 'service/message_service.dart';
import 'service/notification_service.dart';
import 'service/storage_service.dart';
import 'service/user_service.dart';

class TurmsClient {
  late final TurmsDriver _driver;
  late final UserService _userService;
  late final GroupService _groupService;
  late final ConversationService _conversationService;
  late final MessageService _messageService;
  late final StorageService _storageService;
  late final ConferenceService _conferenceService;
  late final NotificationService _notificationService;

  TurmsClient(
      {String? host,
      int? port,
      int? connectTimeoutMillis,
      int? requestTimeoutMillis,
      int? minRequestIntervalMillis,
      int? heartbeatIntervalMillis,
      String? storageServerUrl}) {
    _driver = TurmsDriver(
        host,
        port,
        connectTimeoutMillis,
        requestTimeoutMillis,
        minRequestIntervalMillis,
        heartbeatIntervalMillis);
    _userService = UserService(this);
    _groupService = GroupService(this);
    _conversationService = ConversationService(this);
    _messageService = MessageService(this);
    _storageService = StorageService(this, storageServerUrl);
    _conferenceService = ConferenceService(this);
    _notificationService = NotificationService(this);
  }

  Future<void> close() => driver.close();

  TurmsDriver get driver => _driver;

  UserService get userService => _userService;

  GroupService get groupService => _groupService;

  ConversationService get conversationService => _conversationService;

  MessageService get messageService => _messageService;

  StorageService get storageService => _storageService;

  ConferenceService get conferenceService => _conferenceService;

  NotificationService get notificationService => _notificationService;
}
