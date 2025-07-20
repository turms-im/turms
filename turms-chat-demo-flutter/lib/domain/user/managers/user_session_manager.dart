import 'dart:async';
import 'dart:math';

import 'package:fixnum/fixnum.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../infra/autostart/autostart_manager.dart';
import '../../../infra/collection/list_holder.dart';
import '../../../infra/data/t_async_data.dart';
import '../../../infra/logging/log_appender_database.dart';
import '../../../infra/logging/logger.dart';
import '../../../infra/notification/notification_utils.dart';
import '../../../infra/random/random_utils.dart';
import '../../../infra/shortcut/shortcut.dart';
import '../../../infra/sqlite/user_database.dart';
import '../../../infra/sqlite/user_message_database.dart';
import '../../../infra/window/window_utils.dart';
import '../../../ui/desktop/components/index.dart';
import '../../../ui/desktop/pages/app.dart';
import '../../../ui/desktop/pages/home_page/chat_page/chat_session_pane/message.dart';
import '../../../ui/desktop/pages/home_page/chat_page/view_models/conversations_data_view_model.dart';
import '../../../ui/desktop/pages/home_page/chat_page/view_models/selected_conversation_view_model.dart';
import '../../../ui/desktop/pages/home_page/contacts_page/view_models/contacts_view_model.dart';
import '../../../ui/desktop/pages/home_page/contacts_page/view_models/relationship_groups_view_model.dart';
import '../../../ui/desktop/pages/home_page/home_page_action.dart';
import '../../../ui/l10n/view_models/app_localizations_view_model.dart';
import '../../app/repositories/app_setting_repository.dart';
import '../../conversation/models/conversation.dart';
import '../../conversation/models/conversation_settings.dart';
import '../../conversation/repositories/conversation_setting_repository.dart';
import '../../conversation/services/conversation_service.dart';
import '../../conversation/view_models/id_to_conversation_settings_view_model.dart';
import '../../file/services/file_service.dart';
import '../../group/services/group_service.dart';
import '../../message/models/message_delivery_status.dart';
import '../../message/models/message_type.dart';
import '../../message/repositories/message_repository.dart';
import '../../message/services/message_service.dart';
import '../models/index.dart';
import '../models/setting_action_on_close.dart';
import '../repositories/user_login_info_repository.dart';
import '../repositories/user_setting_repository.dart';
import '../services/user_service.dart';
import '../view_models/logged_in_user_info_view_model.dart';
import '../view_models/user_settings_view_model.dart';

class UserSessionManager {
  UserSessionManager({required Int64 userId}) : _loggedInUserId = userId;

  bool _isLoggedIn = true;
  final Int64 _loggedInUserId;

  StateController<User?>? _loggedInUserController;
  StateController<UserSettings?>? _userSettingsController;
  IdToConversationSettingsViewModelNotifier?
  _idToConversationSettingsController;
  StateController<TAsyncData<List<Contact>>>? _contactsDataController;
  StateController<TAsyncData<List<RelationshipGroup>>>?
  _relationshipGroupsDataController;
  ConversationsDataViewModelNotifier? _conversationsDataController;
  SelectedConversationViewModelNotifier? _selectedConversationController;

  Future<void> onLoggedIn({
    required WidgetRef ref,
    required bool rememberMe,
    required User user,
    required String password,
  }) async {
    _loggedInUserController = ref.read(loggedInUserViewModel.notifier);
    _userSettingsController = ref.read(userSettingsViewModel.notifier);
    _idToConversationSettingsController = ref.read(
      idToConversationSettingsViewModel.notifier,
    );
    _contactsDataController = ref.read(contactsDataViewModel.notifier);
    _relationshipGroupsDataController = ref.read(
      relationshipGroupsDataViewModel.notifier,
    );
    _conversationsDataController = ref.read(
      conversationsDataViewModel.notifier,
    );
    _selectedConversationController = ref.read(
      selectedConversationViewModel.notifier,
    );

    // init repositories
    final userId = user.userId;
    final userDatabase = createUserDatabaseIfNotExists(userId);
    final userMessageDatabase = createUserMessageDatabaseIfNotExists(userId);
    final conversationSettingRepository = ConversationSettingRepository(
      userDatabase,
    );
    final messageRepository = MessageRepository(userMessageDatabase);
    ref.read(conversationSettingRepositoryProvider.notifier).state =
        conversationSettingRepository;
    ref.read(messageRepositoryProvider.notifier).state = messageRepository;

    // init services
    final conversationService = ConversationService(user);
    final fileService = FileService(user);
    final groupService = GroupService();
    final userService = UserService(user);
    final messageService = MessageService(messageRepository);
    ref.read(conversationServiceProvider.notifier).state = conversationService;
    ref.read(fileServiceProvider.notifier).state = fileService;
    ref.read(groupServiceProvider.notifier).state = groupService;
    ref.read(userServiceProvider.notifier).state = userService;
    ref.read(messageServiceProvider.notifier).state = messageService;

    // store app settings
    final shouldRemember = rememberMe;
    if (shouldRemember) {
      await userLoginInfoRepository.upsert(_loggedInUserId, password);
    } else {
      await userLoginInfoRepository.deleteAll();
    }
    final logAppenderDatabase = LogAppenderDatabase(userId: _loggedInUserId);
    logger.addAppender(logAppenderDatabase);
    await appSettingRepository.upsertRememberMe(shouldRemember);
    // read user settings
    _userSettingsController!.state = await _getUserSettings();
    _idToConversationSettingsController!.state =
        await _getIdToConversationSettings(conversationSettingRepository);
    _loggedInUserController!.state = user;
    unawaited(loadData(conversationService, userService, messageRepository));
  }

  Future<UserSettings> _getUserSettings() async {
    final userSettingsTableData = await userSettingRepository.selectAll(
      _loggedInUserId,
    );
    final (userSettings, exception) = UserSettings.fromTableData(
      userSettingsTableData,
    );
    if (exception != null) {
      if (kReleaseMode) {
        logger.warn('Failed to read user settings: ${exception.toString()}');
      } else {
        throw exception;
      }
    }

    for (final type in HomePageAction.values) {
      switch (type) {
        case HomePageAction.showChatPage:
          if (!userSettings.shortcutShowChatPage.initialized) {
            userSettings.shortcutShowChatPage = Shortcut(
              type.defaultShortcutActivator,
              true,
            );
          }
          break;
        case HomePageAction.showContactsPage:
          if (!userSettings.shortcutShowContactsPage.initialized) {
            userSettings.shortcutShowContactsPage = Shortcut(
              type.defaultShortcutActivator,
              true,
            );
          }
          break;
        case HomePageAction.showFilesPage:
          if (!userSettings.shortcutShowFilesPage.initialized) {
            userSettings.shortcutShowFilesPage = Shortcut(
              type.defaultShortcutActivator,
              true,
            );
          }
          break;
        case HomePageAction.showSettingsDialog:
          if (!userSettings.shortcutShowSettingsDialog.initialized) {
            userSettings.shortcutShowSettingsDialog = Shortcut(
              type.defaultShortcutActivator,
              true,
            );
          }
          break;
        case HomePageAction.showAboutDialog:
          if (!userSettings.shortcutShowAboutDialog.initialized) {
            userSettings.shortcutShowAboutDialog = Shortcut(
              type.defaultShortcutActivator,
              true,
            );
          }
          break;
      }
    }
    return userSettings
      // Set default values if the user hasn't set them.
      ..actionOnClose ??= SettingActionOnClose.minimizeToTray
      ..newMessageNotification ??= true
      ..launchOnStartup ??= await autostartManager.isEnabled()
      ..checkForUpdatesAutomatically ??= true;
  }

  Future<Map<IntListHolder, ConversationSettings>> _getIdToConversationSettings(
    ConversationSettingRepository conversationSettingRepository,
  ) async {
    final conversationSettingsTableData = await conversationSettingRepository
        .selectAll();
    final (idToSettings, exception) = ConversationSettings.fromTableData(
      conversationSettingsTableData,
    );
    if (exception != null) {
      if (kReleaseMode) {
        logger.warn(
          'Failed to read conversation settings: ${exception.toString()}',
        );
      } else {
        throw exception;
      }
    }
    return idToSettings;
  }

  Future<void> loadData(
    ConversationService conversationService,
    UserService userService,
    MessageRepository messageRepository,
  ) async {
    await Future.wait([
      loadContacts(userService),
      loadRelationshipGroups(userService),
      loadConversations(conversationService, messageRepository),
    ]);
  }

  Future<void> loadRelationshipGroups(UserService userService) async {
    await TAsyncData.fromFuture(
      () => userService.queryRelationshipGroups(),
    ).forEach((data) {
      if (_isLoggedIn) {
        _relationshipGroupsDataController!.state = data;
      }
    });
  }

  Future<void> loadContacts(UserService userService) async {
    await TAsyncData.fromFuture(() => userService.queryContacts()).forEach((
      data,
    ) {
      if (_isLoggedIn) {
        _contactsDataController!.state = data;
      }
    });
  }

  Future<void> loadConversations(
    ConversationService conversationService,
    MessageRepository messageRepository,
  ) async {
    final user = _loggedInUserController!.state!;
    final userId = user.userId;
    final dataStream = TAsyncData.fromFuture(
      () => conversationService.queryConversations(),
    );
    // TODO: if (fakeDataEnabled) {
    if (true) {
      await messageRepository.delete(idEnd: Int64.ZERO);
    }
    await for (final data in dataStream) {
      if (_isLoggedIn) {
        final conversations = data.value;
        if (conversations != null && conversations.isNotEmpty) {
          final messages = <ChatMessage>[];
          for (final conversation in conversations) {
            for (final message in conversation.messages) {
              messages.add(message);
            }
          }
          if (messages.isNotEmpty) {
            await messageRepository.upsertMessages(messages: messages);
          }
        }
        _conversationsDataController!.setData(data);
      }
    }

    final random = Random();
    Timer.periodic(
      const Duration(seconds: 3),
      (timer) => _generateFakeMessage(messageRepository, timer, random),
    );
  }

  void _generateFakeMessage(
    MessageRepository messageRepository,
    Timer timer,
    Random random,
  ) {
    if (!_isLoggedIn) {
      timer.cancel();
      return;
    }
    final conversations = _conversationsDataController!.getConversations();
    final conversationCount = conversations.length;
    if (conversationCount == 0) {
      return;
    }

    final fakeMessage = StringBuffer();
    final maxLength = 1 + random.nextInt(200);
    for (var i = 0; i < maxLength; i++) {
      fakeMessage.writeCharCode(32 + random.nextInt(10000));
    }
    final message = 'fake message: $fakeMessage';

    Conversation conversation;
    while (true) {
      conversation = conversations[random.nextInt(conversationCount)];
      final now = DateTime.now();
      if (conversation is UserConversation) {
        final contactId = conversation.contact.userId;
        if (contactId != _loggedInUserId) {
          final chatMessage = ChatMessage.parse(
            text: message,
            // Use a negative id so that we can identify these fake message.
            messageId: -RandomUtils.nextUniquePositiveInt64(),
            senderId: contactId,
            recipientId: _loggedInUserId,
            sentByMe: false,
            isGroupMessage: false,
            timestamp: now,
            status: MessageDeliveryStatus.delivered,
          );
          _onMessageReceived(
            messageRepository,
            chatMessage,
            conversation,
            conversations,
          );
          return;
        }
      } else if (conversation is GroupConversation &&
          conversation.contact.members.length > 1) {
        final senderId = conversation.contact.members
            .firstWhere((member) => member.userId != _loggedInUserId)
            .userId;
        final chatMessage = ChatMessage.parse(
          text: message,
          messageId: -RandomUtils.nextUniquePositiveInt64(),
          senderId: senderId,
          groupId: conversation.contact.groupId,
          sentByMe: false,
          isGroupMessage: true,
          timestamp: now,
          status: MessageDeliveryStatus.delivered,
        );
        _onMessageReceived(
          messageRepository,
          chatMessage,
          conversation,
          conversations,
        );
        break;
      }
      if (conversationCount == 1) {
        return;
      }
    }
  }

  void _onMessageReceived(
    MessageRepository messageRepository,
    ChatMessage message,
    Conversation conversationForIncomingMessage,
    List<Conversation> conversations,
  ) {
    final user = _loggedInUserController!.state!;
    unawaited(messageRepository.upsertMessage(message: message));
    _conversationsDataController!.addMessage(
      conversationForIncomingMessage,
      message,
    );

    final selectedConversation = _selectedConversationController!.value;
    if (selectedConversation?.id == conversationForIncomingMessage.id) {
      _selectedConversationController!.notifyListeners();
    } else {
      conversationForIncomingMessage.unreadMessageCount++;
    }
    if ((_userSettingsController!.state?.newMessageNotification ?? false) &&
        user.presence != UserPresence.doNotDisturb) {
      WindowUtils.isVisible().then((isVisible) {
        if (isVisible) {
          return;
        }
        final name = conversationForIncomingMessage.contact.name;
        final body = switch (message.type) {
          MessageType.text => message.text!,
          MessageType.file =>
            '[${readGlobalState(appLocalizationsViewModel).file}]',
          MessageType.image =>
            '[${readGlobalState(appLocalizationsViewModel).image}]',
          MessageType.video =>
            '[${readGlobalState(appLocalizationsViewModel).video}]',
          MessageType.audio =>
            '[${readGlobalState(appLocalizationsViewModel).audio}]',
          MessageType.youtube =>
            '[${readGlobalState(appLocalizationsViewModel).youtube}]',
        };
        NotificationUtils.showNotification(name, body);
      });
    }
  }

  void onLoggedOut() {
    _isLoggedIn = false;
    _selectedConversationController = null;
    _loggedInUserController = null;
    _userSettingsController = null;
    _idToConversationSettingsController = null;
    _contactsDataController = null;
    _relationshipGroupsDataController = null;
    _conversationsDataController = null;
    _selectedConversationController = null;
  }
}

late UserSessionManager userSessionManager;
