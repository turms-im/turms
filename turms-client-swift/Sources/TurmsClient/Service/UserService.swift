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

    /// Add an online listener that will be called when the user becomes online.
    /// A session is considered online when it has a TCP connection with the server,
    /// and the user is logged in by ``login``.
    public func addOnlineListener(_ listener: @escaping () -> Void) {
        onOnlineListeners.append(listener)
    }

    /// Add an offline listener that will be called when the user becomes offline.
    /// A session is considered offline when it has no TCP connection with the server,
    /// or has a connected TCP connection with the server, but the user is not logged in by ``login``.
    public func addOfflineListener(_ listener: @escaping (SessionCloseInfo) -> Void) {
        onOfflineListeners.append(listener)
    }

    /// Log in.
    ///
    /// * If the underlying TCP connection is not connected,
    ///   the method will connect it first under the hood.
    /// * If log in successfully, the session is considered online.
    ///   And the listener registered by ``addOnOnlineListener`` will be called.
    ///
    /// Related docs:
    /// * ``Turms Identity and Access Management``(https://turms-im.github.io/docs/server/module/identity-access-management.html)
    ///
    /// - Parameters:
    ///   - userId: The user ID
    ///   - password: The user password.
    ///   - deviceType: The device type.
    ///     If null, the detected device type will be used.
    ///     Note: The device types of online session that conflicts with `deviceType`
    ///     will be closed by the server if logged in successfully.
    ///   - deviceDetails: The device details.
    ///     Some plugins use this to pass additional information about the device.
    ///     e.g. Push notification token.
    ///   - onlineStatus: The online status.
    ///   - location: The location of the user.
    ///   - storePassword: Whether to store the password in ``userInfo``.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    /// 1. If the client is not compatible with the server, throws
    /// with the code ``ResponseStatusCode/unsupportedClientVersion``.
    /// 2. Depending on the server property `turms.gateway.simultaneous-login.strategy`,
    /// throws with the code ``ResponseStatusCode/loginFromForbiddenDeviceType``
    /// if the specified device type is forbidden.
    /// 3. If provided credentials are invalid,
    /// throws with the code ``ResponseStatusCode/loginAuthenticationFailed``.
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
            self.turmsClient.driver.send {
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

    /// Log out.
    ///
    /// After logging out, the session is considered offline.
    /// And the listener registered by ``addOnOfflineListener`` will be called.
    ///
    /// - Parameters:
    ///   - disconnect: Whether to close the underlying TCP connection immediately
    ///     rather than sending a delete session request first and then closing the connection.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Update the online status of the logged-in user.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.user-online-status-updated.notify-requester-other-online-sessions`
    ///   is true （true by default）,
    ///   the server will send an update online status notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.user-online-status-updated.notify-non-blocked-related-users`,
    ///   is true (false by default),
    ///   the server will send an update online status notification to all non-blocked related users of the logged-in user actively.
    ///
    /// - Parameters:
    ///   - onlineStatus: The new online status.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Disconnect the online devices of the logged-in user.
    ///
    /// If the specified device types are not online, nothing will happen and
    /// no exception will be thrown.
    ///
    /// - Parameters:
    ///   - deviceTypes: The device types to disconnect.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Update the password of the logged-in user.
    ///
    /// - Parameters:
    ///   - password: The new password.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Update the profile of the logged-in user.
    ///
    /// - Parameters:
    ///   - name: The new name.
    ///     If null, the name will not be updated.
    ///   - intro: The new intro.
    ///     If null, the intro will not be updated.
    ///   - profilePicture: The new profile picture.
    ///     If null, the profile picture will not be updated.
    ///     The profile picture can be anything you want.
    ///     e.g. an image URL or a base64 encoded string.
    ///     Note: You can use ``StorageService/uploadUserProfilePicture``
    ///     to upload the profile picture and use the returned URL as `profilePicture`.
    ///   - profileAccessStrategy: The new profile access strategy.
    ///     If null, the profile access strategy will not be updated.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Find user profiles.
    ///
    /// - Parameters:
    ///   - userIds: The target user IDs.
    ///   - lastUpdatedDate: The last updated date of user profiles stored locally.
    ///     The server will only return user profiles that are updated after `lastUpdatedDate`.
    ///     If null, all user profiles will be returned.
    ///
    /// - Returns: A list of user profiles.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Find nearby users.
    ///
    /// - Parameters:
    ///   - latitude: The latitude.
    ///   - longitude: The longitude.
    ///   - maxCount: The max count.
    ///   - maxDistance: The max distance.
    ///   - withCoordinates: Whether to include coordinates.
    ///   - withDistance: Whether to include distance.
    ///   - withUserInfo: Whether to include user info.
    ///
    /// - Returns: A list of nearby users.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Find online status of users.
    ///
    /// - Parameters:
    ///   - userIds: The target user IDs.
    ///
    /// - Returns: A list of online status of users.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Find relationships.
    ///
    /// - Parameters:
    ///   - relatedUserIds: The target related user IDs.
    ///   - isBlocked: Whether to query blocked relationships.
    ///     If null, all relationships will be returned.
    ///     If true, only blocked relationships will be returned.
    ///     If false, only non-blocked relationships will be returned.
    ///   - groupIndexes: The target group indexes for querying.
    ///   - lastUpdatedDate: The last updated date of user relationships stored locally.
    ///     The server will only return relationships that are created after `lastUpdatedDate`.
    ///     If null, all relationships will be returned.
    ///
    /// - Returns: Relationships and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Find related user IDs.
    ///
    /// - Parameters:
    ///   - isBlocked: Whether to query blocked relationships.
    ///     If null, all relationships will be returned.
    ///     If true, only blocked relationships will be returned.
    ///     If false, only non-blocked relationships will be returned.
    ///   - groupIndexes: The target group indexes for querying.
    ///   - lastUpdatedDate: The last updated date of related user IDs stored locally.
    ///     The server will only return related user IDs that are created after `lastUpdatedDate`.
    ///     If null, all related user IDs will be returned.
    ///
    /// - Returns: Related user IDs and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Find friends.
    ///
    /// - Parameters:
    ///   - groupIndexes: The target group indexes for finding.
    ///   - lastUpdatedDate: The last updated date of friends stored locally.
    ///     The server will only return friends that are created after `lastUpdatedDate`.
    ///     If null, all friends will be returned.
    ///
    /// - Returns: Friends and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryFriends(groupIndexes: [Int32]? = nil, lastUpdatedDate: Date? = nil) -> Promise<Response<UserRelationshipsWithVersion?>> {
        return queryRelationships(
            isBlocked: false,
            groupIndexes: groupIndexes,
            lastUpdatedDate: lastUpdatedDate
        )
    }

    /// Find blocked users.
    ///
    /// - Parameters:
    ///   - groupIndexes: The target group indexes for finding.
    ///   - lastUpdatedDate: The last updated date of blocked users stored locally.
    ///     The server will only return friends that are created after `lastUpdatedDate`.
    ///     If null, all blocked users will be returned.
    ///
    /// - Returns: Blocked users and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryBlockedUsers(groupIndexes: [Int32]? = nil, lastUpdatedDate: Date? = nil) -> Promise<Response<UserRelationshipsWithVersion?>> {
        return queryRelationships(
            isBlocked: true,
            groupIndexes: groupIndexes,
            lastUpdatedDate: lastUpdatedDate
        )
    }

    /// Create a relationship.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
    ///   is true (false by default), the server will send a new relationship notification to `userId` actively.
    ///
    /// - Parameters:
    ///   - userId: The target user ID.
    ///   - isBlocked: Whether to create a blocked relationship.
    ///     If true, a blocked relationship will be created,
    ///     and the target user will not be able to send messages to the logged-in user.
    ///   - groupIndex: The target group index in which create the relationship.
    ///     If null, the relationship will be created in the default group.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Create a friend (non-blocked) relationship.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
    ///   is true (false by default), the server will send a new relationship notification to `userId` actively.
    ///
    /// - Parameters:
    ///   - userId: The target user ID.
    ///   - groupIndex: The target group index in which create the relationship.
    ///     If null, the relationship will be created in the default group.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func createFriendRelationship(userId: Int64, groupIndex: Int32? = nil) -> Promise<Response<Void>> {
        return createRelationship(
            userId: userId,
            isBlocked: false,
            groupIndex: groupIndex
        )
    }

    /// Create a blocked user relationship.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
    ///   is true (false by default), the server will send a new relationship notification to `userId` actively.
    ///
    /// - Parameters:
    ///   - userId: The target user ID.
    ///   - groupIndex: The target group index in which create the relationship.
    ///     If null, the relationship will be created in the default group.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func createBlockedUserRelationship(userId: Int64, groupIndex: Int32? = nil) -> Promise<Response<Void>> {
        return createRelationship(
            userId: userId,
            isBlocked: true,
            groupIndex: groupIndex
        )
    }

    /// Delete a relationship.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-deleted.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a delete relationship notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-deleted.notify-group-members`,
    ///   is true (true by default), the server will send a delete relationship notification to all group members in groups.
    ///
    /// - Parameters:
    ///   - relatedUserId: The target user ID.
    ///   - deleteGroupIndex: The target group index in which delete the relationship.
    ///     If null, the relationship will be deleted in all groups.
    ///   - targetGroupIndex: TODO: not implemented yet.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Update a relationship.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.one-sided-relationship-updated.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a update relationship notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.one-sided-relationship-updated.notify-related-user`,
    ///   is true (false by default), the server will send a update relationship notification to `relatedUserId` actively.
    ///
    /// - Parameters:
    ///   - relatedUserId: The target user ID.
    ///   - isBlocked: Whether to update a blocked relationship.
    ///     If null, the relationship will not be updated.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Send a friend request.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.friend-request-created.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a new friend request notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.friend-request-created.notify-friend-request-recipient`,
    ///   is true (true by default), the server will send a new friend request notification to `recipientId` actively.
    ///
    /// - Parameters:
    ///   - recipientId: The target user ID.
    ///   - content: The content of the friend request.
    ///
    /// - Returns: The request ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Delete/Recall a friend request.
    ///
    /// Authorization:
    /// * If the server property `turms.service.user.friend-request.allow-recall-pending-friend-request-by-sender`
    ///   is true (false by default), the logged-in user can recall pending friend requests sent by themselves.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/recallingFriendRequestIsDisabled``.
    /// * If the logged-in user is not the sender of the friend request,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notSenderToRecallFriendRequest``.
    /// * If the friend request is not pending (e.g. expired, accepted, deleted, etc),
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/recallNonPendingFriendRequest``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.friend-request-recalled.notify-requester-other-online-sessions`
    ///   is true (true by default), the server will send a delete friend request notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.friend-request-recalled.notify-friend-request-recipient`
    ///   is true (true by default), the server will send a delete friend request notification to the recipient of the friend request actively.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func deleteFriendRequest(_ requestId: Int64) -> Promise<Response<Int64>> {
        return turmsClient.driver
            .send {
                $0.deleteFriendRequestRequest = .with {
                    $0.requestID = requestId
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Reply to a friend request.
    ///
    /// If the logged-in user accepts a friend request sent by another user,
    /// the server will create a relationship between the logged-in user and the friend request sender.
    ///
    /// Authorization:
    /// * If the logged-in user is not the recipient of the friend request,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notRecipientToUpdateFriendRequest``.
    /// * If the friend request is not pending (e.g. expired, accepted, deleted, etc),
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/updateNonPendingFriendRequest``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.friend-request-replied.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a reply friend request notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.friend-request-replied.notify-friend-request-sender`,
    ///   is true (true by default), the server will send a reply friend request notification to the friend request sender actively.
    ///
    /// - Parameters:
    ///   - requestId: The target friend request ID.
    ///   - responseAction: The response action.
    ///   - reason: The reason of the response.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Find friend requests.
    ///
    /// - Parameters:
    ///   - areSentByMe: Whether to find the friend requests sent by the logged-in user.
    ///     If true, find the friend requests sent by the logged-in user.
    ///     If false, find the friend requests not sent to the logged-in user.
    ///   - lastUpdatedDate: The last updated date of friend requests stored locally.
    ///     The server will only return friend requests that are updated after `lastUpdatedDate`.
    ///     If null, all friend requests will be returned.
    ///
    /// - Returns: Friend requests and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Create a relationship group.
    ///
    /// - Parameters:
    ///   - name: The name of the group.
    ///
    /// - Returns: The index of the created group.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Delete relationship groups.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.one-sided-relationship-group-deleted.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a delete relationship groups relationship notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.one-sided-relationship-group-deleted.notify-relationship-group-members`,
    ///   is true (false by default), the server will send a delete relationship groups relationship notification to all group members in groups.
    ///
    /// - Parameters:
    ///   - groupIndex: The target group index to delete.
    ///   - targetGroupIndex: Move the group members of `groupIndex` to `targetGroupIndex`
    ///     when the group is deleted.
    ///     If null, the group members of `groupIndex` will be moved to the default group.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Update a relationship group.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.one-sided-relationship-group-updated.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a updated relationship groups relationship notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.one-sided-relationship-group-updated.notify-relationship-group-members`,
    ///   is true (false by default), the server will send a updated relationship groups relationship notification to all group members in groups.
    ///
    /// - Parameters:
    ///   - groupIndex: The target group index.
    ///   - newName: The new name of the group.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Find relationship groups.
    ///
    /// - Parameters:
    ///   - lastUpdatedDate: The last updated date of relationship groups stored locally.
    ///     The server will only return relationship groups that are updated after `lastUpdatedDate`.
    ///     If null, all relationship groups will be returned.
    ///
    /// - Returns: Relationship groups and the version.
    /// Note: The version can be used to update the last updated date stored locally.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Move a related user to a group.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.one-sided-relationship-updated.notify-requester-other-online-sessions`,
    ///   is true (true by default), the server will send a update relationship notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.one-sided-relationship-updated.notify-related-user`,
    ///   is true (false by default), the server will send a update relationship notification to `relatedUserId` actively.
    ///
    /// - Parameters:
    ///   - relatedUserId: The target user ID.
    ///   - groupIndex: The target group index to which move the user.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Update the location of the logged-in user.
    ///
    /// Note:
    /// * ``UserService/updateLocation`` is different from
    ///   ``MessageService/sendMessage`` with records of location.
    ///   ``UserService/updateLocation`` sends the location of user to
    ///   the server only.
    ///   ``MessageService/sendMessage`` with records of location sends the user's location
    ///   to both server and its recipients.
    ///
    /// - Parameters:
    ///   - latitude: The latitude.
    ///   - longitude: The longitude.
    ///   - details: The location details
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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
            for onOnlineListener in onOnlineListeners {
                onOnlineListener()
            }
        }
    }

    private func changeToOffline(_ sessionCloseInfo: SessionCloseInfo) {
        if isLoggedIn {
            userInfo?.onlineStatus = .offline
            turmsClient.driver.stateStore.isSessionOpen = false
            for onOfflineListener in onOfflineListeners {
                onOfflineListener(sessionCloseInfo)
            }
        }
    }
}
