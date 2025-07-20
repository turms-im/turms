part of './conversation.dart';

class GroupConversation extends Conversation {
  GroupConversation({
    required super.messages,
    super.unreadMessageCount,
    super.draft,
    required this.contact,
  }) : super(id: Conversation.generateId(groupId: contact.groupId));

  @override
  final GroupContact contact;

  @override
  bool hasSameContact(Contact contact) =>
      contact is GroupContact && contact.groupId == this.contact.groupId;
}
