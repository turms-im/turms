import 'package:fixnum/fixnum.dart';

import 'contact.dart';

const groupRelationshipGroupId = Int64.MIN_VALUE;

class RelationshipGroup {
  factory RelationshipGroup.forUser({
    required Int64 id,
    required String name,
    required bool isBlocked,
    required List<Contact> contacts,
  }) => RelationshipGroup(
    id: id,
    name: name,
    isBlocked: isBlocked,
    contacts: contacts,
  );

  factory RelationshipGroup.forGroup({
    required String name,
    required bool isBlocked,
    required List<Contact> contacts,
  }) => RelationshipGroup(
    id: groupRelationshipGroupId,
    name: name,
    isBlocked: isBlocked,
    contacts: contacts,
  );

  const RelationshipGroup({
    required this.id,
    required this.name,
    required this.isBlocked,
    required this.contacts,
  });

  final Int64 id;
  final String name;
  final bool isBlocked;
  final List<Contact> contacts;

  bool get isGroup => id == groupRelationshipGroupId;
}
