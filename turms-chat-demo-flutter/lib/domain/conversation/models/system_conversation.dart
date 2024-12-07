part of './conversation.dart';

class SystemConversation extends PrivateConversation {
  SystemConversation(
      {required super.messages,
      super.unreadMessageCount,
      super.draft,
      required this.contact})
      : super(id: Conversation.generateId(systemContactType: contact.type));

  @override
  final SystemContact contact;

  @override
  bool hasSameContact(Contact contact) =>
      contact is SystemContact && contact.id == this.contact.id;
}
