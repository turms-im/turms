import '../../common/models/new_relationship_request.dart';
import '../../common/models/request_status.dart';
import 'group.dart';

class GroupMembershipRequest extends NewRelationshipRequest {
  GroupMembershipRequest(
      {required super.id,
      required super.status,
      required super.sender,
      required super.creationDate,
      required super.message,
      required this.group});

  final Group group;

  GroupMembershipRequest copyWith({required RequestStatus status}) =>
      GroupMembershipRequest(
          id: id,
          status: status,
          sender: sender,
          creationDate: creationDate,
          message: message,
          group: group);
}
