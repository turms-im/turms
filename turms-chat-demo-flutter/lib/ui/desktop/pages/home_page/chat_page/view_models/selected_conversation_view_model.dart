import 'package:fixnum/fixnum.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../domain/conversation/models/conversation.dart';
import '../../../../../../domain/user/models/index.dart';
import '../../home_page_tab.dart';
import '../../shared_view_models/home_page_tab_view_model.dart';
import '../chat_session_pane/message.dart';
import 'conversations_view_model.dart';

class SelectedConversationViewModelNotifier extends Notifier<Conversation?> {
  Conversation? get value => state;

  @override
  Conversation? build() => null;

  void update(Conversation conversation) {
    state = conversation;
  }

  void selectByContact(Contact contact) {
    // 1. Go to the chat page
    ref.read(homePageTabViewModel.notifier).state = HomePageTab.chat;
    // 2. Check if the conversation already selected
    final selectedConversation = state;
    if (selectedConversation?.hasSameContact(contact) ?? false) {
      return;
    }
    // 3. Check if the conversation already exists
    final conversationsData = ref.read(conversationsDataViewModel);
    final conversations = conversationsData.value;
    if (conversations == null) {
      return;
    }
    for (final conversation in conversations) {
      if (conversation.hasSameContact(contact)) {
        state = conversation;
        return;
      }
    }
    // 4. Create a new conversation
    final newConversation = Conversation.from(
        contact: contact,
        // TODO: get history messages from local database
        // and (optional) server.
        messages: []);
    ref
        .read(conversationsDataViewModel.notifier)
        .addConversation(newConversation);
    state = newConversation;
  }

  void replaceMessage(Int64 messageId, ChatMessage message) {
    final messages = state!.messages;
    final index = messages.indexWhere((e) => messageId == e.messageId);
    if (index < 0) {
      return;
    }
    messages[index] = message;
    ref.notifyListeners();
  }

  void addMessage(ChatMessage message) {
    state!.messages.add(message);
    ref.notifyListeners();
  }

  void notifyListeners() {
    ref.notifyListeners();
  }

  void removeMessage(Int64 messageId) {
    state!.messages.removeWhere((element) => element.messageId == messageId);
  }
}

final selectedConversationViewModel =
    NotifierProvider<SelectedConversationViewModelNotifier, Conversation?>(
        SelectedConversationViewModelNotifier.new);
