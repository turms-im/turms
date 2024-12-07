import 'package:fixnum/fixnum.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../user/models/contact.dart';

class GroupService {
  Future<void> approveGroupMembershipRequest(Int64 id) async {
    await Future<void>.delayed(const Duration(seconds: 3));
  }

  Future<List<GroupContact>> searchGroupContacts(
      Int64 num, String value) async {
    await Future<void>.delayed(const Duration(seconds: 3));
    return [
      GroupContact(
        groupId: num,
        name: 'a fake group name: $value' * 10,
        intro: 'a fake group intro',
        members: [],
      )
    ];
  }

  Future<void> createGroup() async {
    await Future<void>.delayed(const Duration(seconds: 3));
  }

  Future<void> updateGroupName(String name) async {
    await Future<void>.delayed(const Duration(seconds: 3));
  }
}

final groupServiceProvider = StateProvider<GroupService?>(
  (ref) => null,
);
