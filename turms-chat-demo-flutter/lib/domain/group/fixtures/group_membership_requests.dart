import 'package:fixnum/fixnum.dart';

import '../../common/fixtures/fixtures.dart';
import '../../common/models/request_status.dart';
import '../../user/models/user.dart';
import '../models/group.dart';
import '../models/group_membership_request.dart';

final _now = DateTime.now();

final _groupMembershipRequests = [
  GroupMembershipRequest(
    id: Int64(22),
    status: RequestStatus.pending,
    sender: User(userId: Int64(22), name: 'fake name'),
    creationDate: _now.subtract(const Duration(days: 365)),
    message: 'hi',
    group: Group(id: Int64(22), name: 'fake name'),
  ),
  GroupMembershipRequest(
    id: Int64(23),
    status: RequestStatus.accepted,
    sender: User(userId: Int64(23), name: 'fake name'),
    creationDate: _now.subtract(const Duration(days: 365)),
    message: 'a very long message. ' * 50,
    group: Group(id: Int64(23), name: 'fake name'),
  ),
];

extension FixturesExtensions on Fixtures {
  List<GroupMembershipRequest> get groupMembershipRequests =>
      _groupMembershipRequests;
}
