import 'proto/model/group/group.pb.dart';

class GroupWithVersion {
  final Group group;
  final DateTime? lastUpdatedDate;

  GroupWithVersion(this.group, this.lastUpdatedDate);

  @override
  int get hashCode => group.hashCode ^ lastUpdatedDate.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GroupWithVersion &&
          group == other.group &&
          lastUpdatedDate == other.lastUpdatedDate;

  @override
  String toString() =>
      'GroupWithVersion{group: $group, lastUpdatedDate: $lastUpdatedDate}';
}
