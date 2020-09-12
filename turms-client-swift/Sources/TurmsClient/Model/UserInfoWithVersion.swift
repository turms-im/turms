import Foundation

public struct UserInfoWithVersion {
    private var _userInfo: UserInfo
    private var _lastUpdatedDate: Int64
    
    public var userInfo: UserInfo {
        get { return _userInfo }
        set { _userInfo = newValue }
    }
    
    public var lastUpdatedDate: Int64 {
        get { return _lastUpdatedDate }
        set { _lastUpdatedDate = newValue }
    }

    init(userInfo: UserInfo, lastUpdatedDate: Int64) {
        self._userInfo = userInfo
        self._lastUpdatedDate = lastUpdatedDate
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
