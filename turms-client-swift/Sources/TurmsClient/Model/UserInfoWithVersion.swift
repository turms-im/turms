import Foundation

public struct UserInfoWithVersion {
    private let userInfo: UserInfo
    private let lastUpdatedDate: Int64

    public static func from(_ data: TurmsNotification.DataMessage) throws -> UserInfoWithVersion? {
        if data.usersInfosWithVersion.userInfos.count <= 0 {
            return nil
        }
        return UserInfoWithVersion(
            userInfo: data.usersInfosWithVersion.userInfos[0],
            lastUpdatedDate: data.usersInfosWithVersion.lastUpdatedDate
        )
    }
}
