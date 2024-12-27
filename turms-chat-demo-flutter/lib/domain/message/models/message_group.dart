import '../../../ui/desktop/pages/home_page/chat_page/chat_session_pane/message.dart';

extension type MessageGroup(List<ChatMessage> messages) {
  void addMessage(ChatMessage message) {
    messages.add(message);
  }
}
