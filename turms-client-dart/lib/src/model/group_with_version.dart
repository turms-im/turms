import 'proto/model/group/group.pb.dart';

class GroupWithVersion {
  final Group group;
  final DateTime? lastUpdatedDate;

  GroupWithVersion(this.group, this.lastUpdatedDate);
}
