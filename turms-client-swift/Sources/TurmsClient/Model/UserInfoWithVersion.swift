import Foundation

public class UserInfoWithVersion {
    var userInfo: UserInfo
    var lastUpdatedDate: Int64

    init(userInfo: UserInfo, lastUpdatedDate: Int64) {
        self.userInfo = userInfo
        self.lastUpdatedDate = lastUpdatedDate
    }

    public static func from(_ notification: TurmsNotification) throws -> UserInfoWithVersion {
        if notification.data.usersInfosWithVersion.userInfos.count > 0 {
            return UserInfoWithVersion(
                userInfo: notification.data.usersInfosWithVersion.userInfos[0],
                lastUpdatedDate: notification.data.usersInfosWithVersion.lastUpdatedDate.value
            )
        } else {
            throw TurmsBusinessError(.missingData)
        }
    }
}
