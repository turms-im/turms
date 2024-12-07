import '../../common/models/new_relationship_request.dart';
import '../../common/models/request_status.dart';

class FriendRequest extends NewRelationshipRequest {
  FriendRequest(
      {required super.id,
      required super.status,
      required super.sender,
      required super.creationDate,
      required super.message});

  FriendRequest copyWith({required RequestStatus status}) => FriendRequest(
        id: id,
        status: status,
        sender: sender,
        creationDate: creationDate,
        message: message,
      );
}
