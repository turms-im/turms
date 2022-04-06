import 'proto/model/user/user_info.pb.dart';

class UserInfoWithVersion {
  final UserInfo userInfo;

  final DateTime? lastUpdatedDate;

  UserInfoWithVersion(this.userInfo, this.lastUpdatedDate);
}
