import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../../domain/common/fixtures/fixtures.dart';
import '../../../../../../../domain/group/fixtures/group_membership_requests.dart';
import '../../../../../../../domain/group/models/group_membership_request.dart';
import '../../../../../../../infra/built_in_types/built_in_type_helpers.dart';

class GroupMembershipRequestsViewModelNotifier
    extends Notifier<List<GroupMembershipRequest>> {
  @override
  List<GroupMembershipRequest> build() =>
      Fixtures.instance.groupMembershipRequests;

  void replace(
      GroupMembershipRequest oldRequest, GroupMembershipRequest newRequest) {
    state.replace(oldRequest, newRequest);
    ref.notifyListeners();
  }
}

final groupMembershipRequestsViewModel = NotifierProvider<
    GroupMembershipRequestsViewModelNotifier,
    List<GroupMembershipRequest>>(GroupMembershipRequestsViewModelNotifier.new);
