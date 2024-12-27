import 'package:fixnum/fixnum.dart';
import '../../common/models/request_status.dart';
import '../../user/models/index.dart';

abstract class NewRelationshipRequest {
  NewRelationshipRequest(
      {required this.id,
      required this.status,
      required this.sender,
      required this.creationDate,
      required this.message});

  final Int64 id;
  final RequestStatus status;
  final User sender;
  final DateTime creationDate;
  final String message;
}
