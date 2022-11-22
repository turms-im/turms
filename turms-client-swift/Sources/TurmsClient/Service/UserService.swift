import Foundation
import PromiseKit

public class UserService {
    private weak var turmsClient: TurmsClient!
    public var userInfo: User?
    private var storePassword = false

    private var onOnlineListeners: [() -> Void] = []
    private var onOfflineListeners: [(SessionCloseInfo) -> Void] = []

    var isLoggedIn: Bool {
        guard let status = userInfo?.onlineStatus else {
            return false
        }
        return status != .offline && UserStatus.allCases.contains(status)
    }

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
        turmsClient.driver.addOnDisconnectedListener { [weak self] error in
            self?.changeToOffline(SessionCloseInfo(closeStatus: Int32(SessionCloseStatus.connectionClosed.rawValue), businessStatus: nil, reason: nil, cause: error))
        }
        turmsClient.driver.addNotificationListener { [weak self] n in
            if n.hasCloseStatus, self?.isLoggedIn == true {
                let info = SessionCloseInfo(closeStatus: n.closeStatus, businessStatus: n.hasCode ? n.code : nil, reason: n.hasReason ? n.reason : nil, cause: nil)
                self?.changeToOffline(info)
            }
        }
    }

    public func addOnlineListener(_ listener: @escaping () -> Void) {
        onOnlineListeners.append(listener)
    }

    public func addOfflineListener(_ listener: @escaping (SessionCloseInfo) -> Void) {
        onOfflineListeners.append(listener)
    }

    public func login(
        userId: Int64,
        password: String? = nil,
        deviceType: DeviceType? = nil,
        deviceDetails: [String: String]? = nil,
        onlineStatus: UserStatus? = nil,
        location: Location? = nil,
        storePassword: Bool = false,
        certificatePinning: CertificatePinning? = nil
    ) -> Promise<Response<Void>> {
        var user = User(userId: userId)
        if storePassword {
            user.password = password
        }
        user.onlineStatus = onlineStatus ?? .available
        user.deviceType = deviceType ?? .ios
        user.location = location
        let connect: Promise<Void> = turmsClient.driver.isConnected
            ? Promise.value(())
            : turmsClient.driver.connect(certificatePinning: certificatePinning)
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
            }
            .map {
                try $0.toResponse()
            }
            .get { _ in
                self.changeToOnline()
                self.userInfo = user
                self.storePassword = storePassword
            }
        }
    }

    public func logout(disconnect: Bool = true) -> Promise<Response<Void>> {
        let d: Promise<Response<Void>> = disconnect
            ? turmsClient.driver.disconnect().map {
                Response.empty()
            }
            : turmsClient.driver.send {
                $0.deleteSessionRequest = DeleteSessionRequest()
            }
            .map {
                try $0.toResponse()
            }
        return d.get { _ in
            self.changeToOffline(SessionCloseInfo(closeStatus: Int32(SessionCloseStatus.disconnectedByClient.rawValue), businessStatus: nil, reason: nil, cause: nil))
        }
    }

    public func updateUserOnlineStatus(_ onlineStatus: UserStatus) -> Promise<Response<Void>> {
        if onlineStatus == .offline {
            return Promise(error: ResponseError(
                code: ResponseStatusCode.illegalArgument,
                reason: "The online status must not be OFFLINE"
            ))
        }
        return turmsClient.driver
            .send {
                $0.updateUserOnlineStatusRequest = .with {
                    $0.userStatus = onlineStatus
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func disconnectOnlineDevices(_ deviceTypes: [DeviceType]) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateUserOnlineStatusRequest = .with {
                    $0.userStatus = UserStatus.offline
                    $0.deviceTypes = deviceTypes
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func updatePassword(_ password: String) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateUserRequest = .with {
                    $0.password = password
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func updateProfile(
        name: String? = nil,
        intro: String? = nil,
        profileAccessStrategy: ProfileAccessStrategy? = nil
    ) -> Promise<Response<Void>> {
        if Validator.areAllNil(name, intro, profileAccessStrategy) {
            return Promise.value(Response.empty())
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
            .map {
                try $0.toResponse()
            }
    }

    public func queryUserProfiles(userIds: [Int64], lastUpdatedDate: Date? = nil) -> Promise<Response<[UserInfo]>> {
        if userIds.isEmpty {
            return Promise.value(Response.emptyArray())
        }
        return turmsClient.driver
            .send {
                $0.queryUserProfilesRequest = .with {
                    $0.userIds = userIds
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.toResponse {
                    $0.userInfosWithVersion.userInfos
                }
            }
    }

    public func queryNearbyUsers(latitude: Float, longitude: Float, maxCount: Int32? = nil, maxDistance: Int32? = nil, withCoordinates: Bool? = nil, withDistance: Bool? = nil, withUserInfo: Bool? = nil) -> Promise<Response<[NearbyUser]>> {
        return turmsClient.driver
            .send {
                $0.queryNearbyUsersRequest = .with {
                    $0.latitude = latitude
                    $0.longitude = longitude
                    if let v = maxCount {
                        $0.maxCount = v
                    }
                    if let v = maxDistance {
                        $0.maxDistance = v
                    }
                    if let v = withCoordinates {
                        $0.withCoordinates = v
                    }
                    if let v = withDistance {
                        $0.withDistance = v
                    }
                    if let v = withUserInfo {
                        $0.withUserInfo = v
                    }
                }
            }
            .map {
                try $0.toResponse {
                    $0.nearbyUsers.nearbyUsers
                }
            }
    }

    public func queryUserOnlineStatusesRequest(_ userIds: [Int64]) -> Promise<Response<[UserOnlineStatus]>> {
        return turmsClient.driver
            .send {
                $0.queryUserOnlineStatusesRequest = .with {
                    $0.userIds = userIds
                }
            }
            .map {
                try $0.toResponse {
                    $0.userOnlineStatuses.statuses
                }
            }
    }

    // Relationship

    public func queryRelationships(
        relatedUserIds: [Int64]? = nil,
        isBlocked: Bool? = nil,
        groupIndexes: [Int32]? = nil,
        lastUpdatedDate: Date? = nil
    ) -> Promise<Response<UserRelationshipsWithVersion?>> {
        return turmsClient.driver
            .send {
                $0.queryRelationshipsRequest = .with {
                    if let v = relatedUserIds {
                        $0.userIds = v
                    }
                    if let v = isBlocked {
                        $0.blocked = v
                    }
                    if let v = groupIndexes {
                        $0.groupIndexes = v
                    }
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.kind?.getKindData(UserRelationshipsWithVersion.self)
                }
            }
    }

    public func queryRelatedUserIds(isBlocked: Bool? = nil, groupIndexes: [Int32]? = nil, lastUpdatedDate: Date? = nil) -> Promise<Response<LongsWithVersion?>> {
        return turmsClient.driver
            .send {
                $0.queryRelatedUserIdsRequest = .with {
                    if let v = isBlocked {
                        $0.blocked = v
                    }
                    if let v = groupIndexes {
                        $0.groupIndexes = v
                    }
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.kind?.getKindData(LongsWithVersion.self)
                }
            }
    }

    public func queryFriends(groupIndexes: [Int32]? = nil, lastUpdatedDate: Date? = nil) -> Promise<Response<UserRelationshipsWithVersion?>> {
        return queryRelationships(
            isBlocked: false,
            groupIndexes: groupIndexes,
            lastUpdatedDate: lastUpdatedDate
        )
    }

    public func queryBlockedUsers(groupIndexes: [Int32]? = nil, lastUpdatedDate: Date? = nil) -> Promise<Response<UserRelationshipsWithVersion?>> {
        return queryRelationships(
            isBlocked: true,
            groupIndexes: groupIndexes,
            lastUpdatedDate: lastUpdatedDate
        )
    }

    public func createRelationship(userId: Int64, isBlocked: Bool, groupIndex: Int32? = nil) -> Promise<Response<Void>> {
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
            .map {
                try $0.toResponse()
            }
    }

    public func createFriendRelationship(userId: Int64, groupIndex: Int32? = nil) -> Promise<Response<Void>> {
        return createRelationship(
            userId: userId,
            isBlocked: false,
            groupIndex: groupIndex
        )
    }

    public func createBlockedUserRelationship(userId: Int64, groupIndex: Int32? = nil) -> Promise<Response<Void>> {
        return createRelationship(
            userId: userId,
            isBlocked: true,
            groupIndex: groupIndex
        )
    }

    public func deleteRelationship(relatedUserId: Int64, deleteGroupIndex: Int32? = nil, targetGroupIndex: Int32? = nil) -> Promise<Response<Void>> {
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
            .map {
                try $0.toResponse()
            }
    }

    public func updateRelationship(relatedUserId: Int64, isBlocked: Bool? = nil, groupIndex: Int32? = nil) -> Promise<Response<Void>> {
        if Validator.areAllNil(isBlocked, groupIndex) {
            return Promise.value(Response.empty())
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
            .map {
                try $0.toResponse()
            }
    }

    public func sendFriendRequest(recipientId: Int64, content: String) -> Promise<Response<Int64>> {
        return turmsClient.driver
            .send {
                $0.createFriendRequestRequest = .with {
                    $0.recipientID = recipientId
                    $0.content = content
                }
            }
            .map {
                try $0.toResponse {
                    try $0.getLongOrThrow()
                }
            }
    }

    public func replyFriendRequest(requestId: Int64, responseAction: ResponseAction, reason: String? = nil) -> Promise<Response<Void>> {
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
            .map {
                try $0.toResponse()
            }
    }

    public func queryFriendRequests(_ areSentByMe: Bool, lastUpdatedDate: Date? = nil) -> Promise<Response<UserFriendRequestsWithVersion?>> {
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
                try $0.toResponse {
                    try $0.kind?.getKindData(UserFriendRequestsWithVersion.self)
                }
            }
    }

    public func createRelationshipGroup(_ name: String) -> Promise<Response<Int32>> {
        return turmsClient.driver
            .send {
                $0.createRelationshipGroupRequest = .with {
                    $0.name = name
                }
            }
            .map {
                try $0.toResponse {
                    try Int32($0.getLongOrThrow())
                }
            }
    }

    public func deleteRelationshipGroups(groupIndex: Int32, targetGroupIndex: Int32? = nil) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.deleteRelationshipGroupRequest = .with {
                    $0.groupIndex = groupIndex
                    if let v = targetGroupIndex {
                        $0.targetGroupIndex = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func updateRelationshipGroup(groupIndex: Int32, newName: String) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateRelationshipGroupRequest = .with {
                    $0.groupIndex = groupIndex
                    $0.newName = newName
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func queryRelationshipGroups(_ lastUpdatedDate: Date? = nil) -> Promise<Response<UserRelationshipGroupsWithVersion?>> {
        return turmsClient.driver
            .send {
                $0.queryRelationshipGroupsRequest = .with {
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.kind?.getKindData(UserRelationshipGroupsWithVersion.self)
                }
            }
    }

    public func moveRelatedUserToGroup(relatedUserId: Int64, groupIndex: Int32) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateRelationshipRequest = .with {
                    $0.userID = relatedUserId
                    $0.newGroupIndex = groupIndex
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /**
     * updateLocation() in UserService is different from sendMessage() with records of location in MessageService
     * updateLocation() in UserService sends the location of user to the server only.
     * sendMessage() with records of location sends user's location to both server and its recipients.
     */
    public func updateLocation(latitude: Float, longitude: Float, details: [String: String]? = nil) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateUserLocationRequest = .with {
                    $0.latitude = latitude
                    $0.longitude = longitude
                    if let v = details {
                        $0.details = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
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
