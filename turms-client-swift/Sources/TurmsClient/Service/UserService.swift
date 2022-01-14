import Foundation
import PromiseKit

public class UserService {
    private weak var turmsClient: TurmsClient!
    public var userInfo: User?
    private var storePassword = false

    private var onOnlineListeners: [() -> ()] = []
    private var onOfflineListeners: [(SessionCloseInfo) -> ()] = []

    var isLoggedIn: Bool {
        get {
            guard let status = userInfo?.onlineStatus else {
                return false
            }
            return status != .offline && UserStatus.allCases.contains(status)
        }
    }

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
        turmsClient.driver.addOnDisconnectedListener { [weak self] _ in
            self?.changeToOffline(SessionCloseInfo(closeStatus: Int32(TurmsCloseStatus.connectionClosed.rawValue), businessStatus: nil, reason: nil))
        }
        turmsClient.driver.addNotificationListener { [weak self] n in
            if n.hasCloseStatus && self?.isLoggedIn == true {
                let info = SessionCloseInfo(closeStatus: n.closeStatus, businessStatus: n.hasCode ? n.code : nil, reason: n.hasReason ? n.reason : nil)
                self?.changeToOffline(info)
            }
        }
    }

    public func addOnlineListener(_ listener: @escaping () -> ()) {
        onOnlineListeners.append(listener)
    }

    public func addOfflineListener(listener: @escaping (SessionCloseInfo) -> ()) {
        onOfflineListeners.append(listener)
    }

    public func login(
        userId: Int64,
        password: String? = nil,
        deviceType: DeviceType? = nil,
        deviceDetails: [String:String]? = nil,
        onlineStatus: UserStatus? = nil,
        location: Location? = nil,
        storePassword: Bool = false) -> Promise<Void> {
        var user = User(userId: userId)
        if storePassword {
            user.password = password
        }
        user.onlineStatus = onlineStatus ?? .available
        user.deviceType = deviceType ?? .ios
        user.location = location
        let connect = turmsClient.driver.isConnected
            ? Promise.value(())
            : turmsClient.driver.connect()
        return connect.then {
            return self.turmsClient.driver.send {
                $0.createSessionRequest = .with {
                    $0.version = 1
                    $0.userID = userId
                    if let v = password {
                        $0.password = v
                    }
                    if let v = deviceType {
                        $0.deviceType = v
                    }
                    if let v = deviceDetails {
                        $0.deviceDetails = v
                    }
                    if let v = onlineStatus {
                        $0.userStatus = v
                    }
                    if let v = location {
                        $0.location = .with {
                            $0.longitude = v.longitude
                            $0.latitude = v.latitude
                        }
                    }
                }
            }.done { _ in
                self.changeToOnline()
                self.userInfo = user
                self.storePassword = storePassword
            }
        }
    }

    public func logout(disconnect: Bool = true) -> Promise<Void> {
        let d = disconnect
            ? turmsClient.driver.disconnect()
            : turmsClient.driver.send {
            $0.deleteSessionRequest = DeleteSessionRequest()
        }.asVoid()
        return d.done {
            self.changeToOffline(SessionCloseInfo(closeStatus: Int32(TurmsCloseStatus.disconnectedByClient.rawValue), businessStatus: nil, reason: nil))
        }
    }

    public func updateUserOnlineStatus(_ onlineStatus: UserStatus) -> Promise<Void> {
        if onlineStatus == .offline {
            return Promise(error: TurmsBusinessError(TurmsStatusCode.illegalArgument, "The online status must not be OFFLINE"))
        }
        return turmsClient.driver
            .send {
                $0.updateUserOnlineStatusRequest = .with {
                    $0.userStatus = onlineStatus
                }
            }
            .asVoid()
    }

    public func disconnectOnlineDevices(_ deviceTypes: [DeviceType]) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateUserOnlineStatusRequest = .with {
                    $0.userStatus = UserStatus.offline
                    $0.deviceTypes = deviceTypes
                }
            }
            .asVoid()
    }

    public func updatePassword(_ password: String) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateUserRequest = .with {
                    $0.password = password
                }
            }
            .asVoid()
    }

    public func updateProfile(
        name: String? = nil,
        intro: String? = nil,
        profileAccessStrategy: ProfileAccessStrategy? = nil) -> Promise<Void> {
        if Validator.areAllNil(name, intro, profileAccessStrategy) {
            return Promise.value(())
        }
        return turmsClient.driver
            .send {
                $0.updateUserRequest = .with {
                    if let v = name {
                        $0.name = v
                    }
                    if let v = intro {
                        $0.intro = v
                    }
                    if let v = profileAccessStrategy {
                        $0.profileAccessStrategy = v
                    }
                }
            }
            .asVoid()
    }

    public func queryUserProfile(userId: Int64, lastUpdatedDate: Date? = nil) -> Promise<UserInfoWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryUserProfileRequest = .with {
                    $0.userID = userId
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try UserInfoWithVersion.from($0)
            }
    }

    public func queryNearbyUsers(latitude: Float, longitude: Float, distance: Int32? = nil, maxNumber: Int32? = nil, withCoordinates: Bool? = nil, withDistance: Bool? = nil, withInfo: Bool? = nil) -> Promise<[NearbyUser]> {
        return turmsClient.driver
            .send {
                $0.queryNearbyUsersRequest = .with {
                    $0.latitude = latitude
                    $0.longitude = longitude
                    if let v = distance {
                        $0.distance = v
                    }
                    if let v = maxNumber {
                        $0.maxNumber = v
                    }
                    if let v = withCoordinates {
                        $0.withCoordinates = v
                    }
                    if let v = withDistance {
                        $0.withDistance = v
                    }
                    if let v = withInfo {
                        $0.withInfo = v
                    }
                }
            }
            .map {
                $0.data.nearbyUsers.nearbyUsers
            }
    }

    public func queryUserOnlineStatusesRequest(_ userIds: [Int64]) -> Promise<[UserStatusDetail]> {
        return turmsClient.driver
            .send {
                $0.queryUserOnlineStatusesRequest = .with {
                    $0.userIds = userIds
                }
            }
            .map {
                $0.data.usersOnlineStatuses.userStatuses
            }
    }

    // Relationship

    public func queryRelationships(
        relatedUserIds: [Int64]? = nil,
        isBlocked: Bool? = nil,
        groupIndex: Int32? = nil,
        lastUpdatedDate: Date? = nil) -> Promise<UserRelationshipsWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryRelationshipsRequest = .with {
                    if let v = relatedUserIds {
                        $0.userIds = v
                    }
                    if let v = isBlocked {
                        $0.blocked = v
                    }
                    if let v = groupIndex {
                        $0.groupIndex = v
                    }
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(UserRelationshipsWithVersion.self)
            }
    }

    public func queryRelatedUserIds(isBlocked: Bool? = nil, groupIndex: Int32? = nil, lastUpdatedDate: Date? = nil) -> Promise<Int64ValuesWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryRelatedUserIdsRequest = .with {
                    if let v = isBlocked {
                        $0.blocked = v
                    }
                    if let v = groupIndex {
                        $0.groupIndex = v
                    }
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(Int64ValuesWithVersion.self)
            }
    }

    public func queryFriends(groupIndex: Int32? = nil, lastUpdatedDate: Date? = nil) -> Promise<UserRelationshipsWithVersion?> {
        return queryRelationships(
            isBlocked: false,
            groupIndex: groupIndex,
            lastUpdatedDate: lastUpdatedDate)
    }

    public func queryBlockedUsers(groupIndex: Int32? = nil, lastUpdatedDate: Date? = nil) -> Promise<UserRelationshipsWithVersion?> {
        return queryRelationships(
            isBlocked: true,
            groupIndex: groupIndex,
            lastUpdatedDate: lastUpdatedDate)
    }

    public func createRelationship(userId: Int64, isBlocked: Bool, groupIndex: Int32? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.createRelationshipRequest = .with {
                    $0.userID = userId
                    $0.blocked = isBlocked
                    if let v = groupIndex {
                        $0.groupIndex = v
                    }
                }
            }
            .asVoid()
    }

    public func createFriendRelationship(userId: Int64, groupIndex: Int32? = nil) -> Promise<Void> {
        return createRelationship(
            userId: userId,
            isBlocked: false,
            groupIndex: groupIndex)
    }

    public func createBlockedUserRelationship(userId: Int64, groupIndex: Int32? = nil) -> Promise<Void> {
        return createRelationship(
            userId: userId,
            isBlocked: true,
            groupIndex: groupIndex)
    }

    public func deleteRelationship(relatedUserId: Int64, deleteGroupIndex: Int32? = nil, targetGroupIndex: Int32? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.deleteRelationshipRequest = .with {
                    $0.userID = relatedUserId
                    if let v = deleteGroupIndex {
                        $0.groupIndex = v
                    }
                    if let v = targetGroupIndex {
                        $0.targetGroupIndex = v
                    }
                }
            }
            .asVoid()
    }

    public func updateRelationship(relatedUserId: Int64, isBlocked: Bool? = nil, groupIndex: Int32? = nil) -> Promise<Void> {
        if Validator.areAllNil(isBlocked, groupIndex) {
            return Promise.value(())
        }
        return turmsClient.driver
            .send {
                $0.updateRelationshipRequest = .with {
                    $0.userID = relatedUserId
                    if let v = isBlocked {
                        $0.blocked = v
                    }
                    if let v = groupIndex {
                        $0.newGroupIndex = v
                    }
                }
            }
            .asVoid()
    }

    public func sendFriendRequest(recipientId: Int64, content: String) -> Promise<Int64> {
        return turmsClient.driver
            .send {
                $0.createFriendRequestRequest = .with {
                    $0.recipientID = recipientId
                    $0.content = content
                }
            }
            .map {
                try $0.getFirstId()
            }
    }

    public func replyFriendRequest(requestId: Int64, responseAction: ResponseAction, reason: String? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateFriendRequestRequest = .with {
                    $0.requestID = requestId
                    $0.responseAction = responseAction
                    if let v = reason {
                        $0.reason = v
                    }
                }
            }
            .asVoid()
    }

    public func queryFriendRequests(_ areSentByMe: Bool, lastUpdatedDate: Date? = nil) -> Promise<UserFriendRequestsWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryFriendRequestsRequest = .with {
                    $0.areSentByMe = areSentByMe
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(UserFriendRequestsWithVersion.self)
            }
    }

    public func createRelationshipGroup(_ name: String) -> Promise<Int32> {
        return turmsClient.driver
            .send {
                $0.createRelationshipGroupRequest = .with {
                    $0.name = name
                }
            }
            .map {
                try Int32($0.getFirstId())
            }
    }

    public func deleteRelationshipGroups(groupIndex: Int32, targetGroupIndex: Int32? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.deleteRelationshipGroupRequest = .with {
                    $0.groupIndex = groupIndex
                    if let v = targetGroupIndex {
                        $0.targetGroupIndex = v
                    }
                }
            }
            .asVoid()
    }

    public func updateRelationshipGroup(groupIndex: Int32, newName: String) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateRelationshipGroupRequest = .with {
                    $0.groupIndex = groupIndex
                    $0.newName = newName
                }
            }
            .asVoid()
    }

    public func queryRelationshipGroups(_ lastUpdatedDate: Date? = nil) -> Promise<UserRelationshipGroupsWithVersion?> {
        return turmsClient.driver
            .send {
                $0.queryRelationshipGroupsRequest = .with {
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.data.kind?.getKindData(UserRelationshipGroupsWithVersion.self)
            }
    }

    public func moveRelatedUserToGroup(relatedUserId: Int64, groupIndex: Int32) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateRelationshipRequest = .with {
                    $0.userID = relatedUserId
                    $0.newGroupIndex = groupIndex
                }
            }
            .asVoid()
    }

    /**
     * updateLocation() in UserService is different from sendMessage() with records of location in MessageService
     * updateLocation() in UserService sends the location of user to the server only.
     * sendMessage() with records of location sends user's location to both server and its recipients.
     */
    public func updateLocation(latitude: Float, longitude: Float, name: String? = nil, address: String? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateUserLocationRequest = .with {
                    $0.latitude = latitude
                    $0.longitude = longitude
                    if let v = name {
                        $0.name = v
                    }
                    if let v = address {
                        $0.address = v
                    }
                }
            }
            .asVoid()
    }

    private func changeToOnline() {
        if !isLoggedIn {
            turmsClient.driver.stateStore.isSessionOpen = true
            onOnlineListeners.forEach {
                $0()
            }
        }
    }

    private func changeToOffline(_ sessionCloseInfo: SessionCloseInfo) {
        if isLoggedIn {
            userInfo?.onlineStatus = .offline
            turmsClient.driver.stateStore.isSessionOpen = false
            onOfflineListeners.forEach {
                $0(sessionCloseInfo)
            }
        }
    }

}
