part of './conversation.dart';

class UserConversation extends PrivateConversation {
  UserConversation(
      {required super.messages,
      super.unreadMessageCount,
      super.draft,
      required this.contact})
      : super(id: Conversation.generateId(userId: contact.userId));

  @override
  final UserContact contact;

  @override
  bool hasSameContact(Contact contact) =>
      contact is UserContact && contact.id == this.contact.id;
}
