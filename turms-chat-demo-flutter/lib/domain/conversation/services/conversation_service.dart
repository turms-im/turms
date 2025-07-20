import 'package:fixnum/fixnum.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../infra/collection/list_holder.dart';
import '../../../ui/desktop/pages/app.dart';
import '../../common/fixtures/fixtures.dart';
import '../../conversation/fixtures/conversations.dart';
import '../../user/models/contact.dart';
import '../../user/models/user.dart';
import '../models/conversation.dart';
import '../models/conversation_setting.dart';
import '../repositories/conversation_setting_repository.dart';
import '../view_models/id_to_conversation_settings_view_model.dart';

class ConversationService {
  const ConversationService(this._loggedInUser);

  final User _loggedInUser;

  Future<List<Conversation>> queryConversations() async {
    await Future<void>.delayed(const Duration(seconds: 3));
    return Fixtures.instance.getConversations(_loggedInUser);
  }

  Future<void> resetSharedUnreadMessageCount({Int64? groupId, Int64? userId}) =>
      Future<void>.delayed(const Duration(seconds: 1));

  Future<void> updateSettingPinned({
    required IntListHolder conversationId,
    required bool newValue,
    required Contact contact,
  }) async {
    readGlobalState(
      idToConversationSettingsViewModel.notifier,
    ).update(conversationId, pinned: newValue);
    await readGlobalState(conversationSettingRepositoryProvider)!.upsert(
      contactId: contact is SystemContact ? _loggedInUser.userId : contact.id,
      isGroupConversation: contact is GroupContact,
      setting: ConversationSetting.pinned,
      settingValue: newValue,
    );
  }

  Future<void> updateSettingEnableNewMessageNotification({
    required IntListHolder conversationId,
    required bool newValue,
    required Contact contact,
  }) async {
    readGlobalState(
      idToConversationSettingsViewModel.notifier,
    ).update(conversationId, enableNewMessageNotification: newValue);
    await readGlobalState(conversationSettingRepositoryProvider)!.upsert(
      contactId: contact is SystemContact ? _loggedInUser.userId : contact.id,
      isGroupConversation: contact is GroupContact,
      setting: ConversationSetting.enableNewMessageNotification,
      settingValue: newValue,
    );
  }
}

final conversationServiceProvider = StateProvider<ConversationService?>(
  (ref) => null,
);
