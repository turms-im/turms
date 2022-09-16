import 'proto/model/user/user_info.pb.dart';

class UserInfoWithVersion {
  final UserInfo userInfo;

  final DateTime? lastUpdatedDate;

  UserInfoWithVersion(this.userInfo, this.lastUpdatedDate);

  @override
  int get hashCode => userInfo.hashCode ^ lastUpdatedDate.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is UserInfoWithVersion &&
          userInfo == other.userInfo &&
          lastUpdatedDate == other.lastUpdatedDate;

  @override
  String toString() =>
      'UserInfoWithVersion{userInfo: $userInfo, lastUpdatedDate: $lastUpdatedDate}';
}
