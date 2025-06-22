import 'package:fixnum/fixnum.dart';

import '../../common/fixtures/fixtures.dart';
import '../../common/models/request_status.dart';
import '../models/index.dart';

final _now = DateTime.now();

final _friendRequests = [
  FriendRequest(
    id: Int64(1),
    status: RequestStatus.pending,
    sender: User(userId: Int64(1), name: 'fake name'),
    creationDate: _now,
    message: 'hello',
  ),
  FriendRequest(
    id: Int64(2),
    status: RequestStatus.pending,
    sender: User(userId: Int64(2), name: 'fake name'),
    creationDate: _now,
    message: 'hi',
  ),
  FriendRequest(
    id: Int64(3),
    status: RequestStatus.accepted,
    sender: User(userId: Int64(3), name: 'fake name'),
    creationDate: _now,
    message: 'a very long message. ' * 50,
  ),
  FriendRequest(
    id: Int64(11),
    status: RequestStatus.pending,
    sender: User(userId: Int64(11), name: 'fake name'),
    creationDate: _now.subtract(const Duration(days: 15)),
    message: 'hello',
  ),
  FriendRequest(
    id: Int64(12),
    status: RequestStatus.pending,
    sender: User(userId: Int64(12), name: 'fake name'),
    creationDate: _now.subtract(const Duration(days: 15)),
    message: 'hi',
  ),
  FriendRequest(
    id: Int64(13),
    status: RequestStatus.accepted,
    sender: User(userId: Int64(13), name: 'fake name'),
    creationDate: _now.subtract(const Duration(days: 15)),
    message: 'a very long message. ' * 50,
  ),
  FriendRequest(
    id: Int64(21),
    status: RequestStatus.pending,
    sender: User(userId: Int64(21), name: 'fake name'),
    creationDate: _now.subtract(const Duration(days: 365)),
    message: 'hello',
  ),
  FriendRequest(
    id: Int64(22),
    status: RequestStatus.pending,
    sender: User(userId: Int64(22), name: 'fake name'),
    creationDate: _now.subtract(const Duration(days: 365)),
    message: 'hi',
  ),
  FriendRequest(
    id: Int64(23),
    status: RequestStatus.accepted,
    sender: User(userId: Int64(23), name: 'fake name'),
    creationDate: _now.subtract(const Duration(days: 365)),
    message: 'a very long message. ' * 50,
  ),
];

extension FixturesExtensions on Fixtures {
  List<FriendRequest> get friendRequests => _friendRequests;
}
