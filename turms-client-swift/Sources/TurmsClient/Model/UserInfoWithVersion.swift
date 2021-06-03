import Foundation

public struct UserInfoWithVersion {
    private let userInfo: UserInfo
    private let lastUpdatedDate: Int64

    public static func from(_ notification: TurmsNotification) throws -> UserInfoWithVersion? {
        if notification.data.usersInfosWithVersion.userInfos.count <= 0 {
            return nil
        }
        return UserInfoWithVersion(
            userInfo: notification.data.usersInfosWithVersion.userInfos[0],
            lastUpdatedDate: notification.data.usersInfosWithVersion.lastUpdatedDate
        )
    }
}
