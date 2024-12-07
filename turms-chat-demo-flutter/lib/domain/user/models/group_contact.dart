part of 'contact.dart';

class GroupContact extends Contact {
  GroupContact(
      {required this.groupId,
      required this.members,
      required super.name,
      super.intro,
      super.imageUrl,
      super.imageBytes})
      : super(recordId: 'group:$groupId', id: groupId);

  final Int64 groupId;
  final List<GroupMember> members;
}
