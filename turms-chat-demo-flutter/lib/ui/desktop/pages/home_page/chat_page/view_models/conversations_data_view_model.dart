import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../domain/conversation/models/conversation.dart';
import '../../../../../../domain/conversation/models/conversation_settings.dart';
import '../../../../../../domain/conversation/view_models/id_to_conversation_settings_view_model.dart';
import '../../../../../../infra/collection/list_holder.dart';
import '../../../../../../infra/core/comparable_utils.dart';
import '../../../../../../infra/data/t_async_data.dart';
import '../chat_session_pane/message.dart';

class ConversationsDataViewModelNotifier
    extends Notifier<TAsyncData<List<Conversation>>> {
  @override
  TAsyncData<List<Conversation>> build() {
    final idToConversationSettings = ref.watch(
      idToConversationSettingsViewModel,
    );
    final data = stateOrNull;
    if (data == null) {
      return const TAsyncData();
    }
    if (data.isInitialized) {
      return TAsyncData(
        value: _sortConversations(data.value!, idToConversationSettings),
      );
    }
    return data;
  }

  void notifyListeners() => ref.notifyListeners();

  List<Conversation> getConversations() => state.value ?? [];

  void setData(TAsyncData<List<Conversation>> data) {
    if (data.isInitialized) {
      data = TAsyncData(
        value: _sortConversations(
          data.value!,
          ref.read(idToConversationSettingsViewModel),
        ),
      );
    }
    state = data;
  }

  void addMessage(Conversation conversation, ChatMessage message) {
    conversation.messages.add(message);
    state = TAsyncData(
      value: _sortConversations(
        state.value!,
        ref.read(idToConversationSettingsViewModel),
      ),
    );
  }

  void deleteConversation(IntListHolder conversationId) {
    state.value!.removeWhere(
      (conversation) => conversation.id == conversationId,
    );
    ref.notifyListeners();
  }

  void addConversation(Conversation newConversation) {
    final conversations = state.value!..add(newConversation);
    state = TAsyncData(
      value: _sortConversations(
        conversations,
        ref.read(idToConversationSettingsViewModel),
      ),
    );
  }

  /// Sort by:
  /// 1. pinned;
  /// 2. file transfer;
  /// 3. last message timestamp.
  List<Conversation> _sortConversations(
    List<Conversation> conversations,
    Map<IntListHolder, ConversationSettings> idToConversationSettings,
  ) => conversations
    ..sort((conversation2, conversation1) {
      final result = ComparableUtils.compareBool(
        idToConversationSettings[conversation1.id]?.pinned ?? false,
        idToConversationSettings[conversation2.id]?.pinned ?? false,
      );
      if (result != 0) {
        return result;
      } else if (conversation1.contact.isFileTransfer) {
        return 1;
      } else if (conversation2.contact.isFileTransfer) {
        return -1;
      }
      return ComparableUtils.compare(
        conversation1.messages.lastOrNull?.timestamp,
        conversation2.messages.lastOrNull?.timestamp,
      );
    });
}

final conversationsDataViewModel =
    NotifierProvider<
      ConversationsDataViewModelNotifier,
      TAsyncData<List<Conversation>>
    >(ConversationsDataViewModelNotifier.new);
