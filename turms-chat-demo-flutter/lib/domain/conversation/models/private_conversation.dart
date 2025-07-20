part of './conversation.dart';

sealed class PrivateConversation extends Conversation {
  PrivateConversation({
    required super.id,
    required super.messages,
    super.unreadMessageCount,
    super.draft,
  });
}
