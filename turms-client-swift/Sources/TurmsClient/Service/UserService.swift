import Foundation
import PromiseKit

public class UserService {    
    private weak var turmsClient: TurmsClient!
    var userId: Int64?
    var password: String?
    var location: Position?
    var userOnlineStatus: UserStatus = .available
    var deviceType: DeviceType = .unknown

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
    }

    public func login(
        userId: Int64,
        password: String,
        location: Position? = nil,
        userOnlineStatus: UserStatus? = .available,
        deviceType: DeviceType? = .unknown) -> Promise<Void> {
        self.userId = userId
        self.password = password
        self.userOnlineStatus = userOnlineStatus!
        self.deviceType = deviceType!
        self.location = location
        return turmsClient.driver.connect(
            userId: userId,
            password: password)
    }

    public func relogin() -> Promise<Void> {
        if userId == nil || password == nil {
            return Promise(error: TurmsBusinessError(.clientUserIdAndPasswordMustNotNull))
        } else {
            return login(
                userId: userId!,
                password: password!,
                location: location,
                userOnlineStatus: userOnlineStatus,
                deviceType: deviceType)
        }
    }

    public func logout() -> Promise<Void> {
        return turmsClient.driver.disconnect()
    }

    public func updateUserOnlineStatus(onlineStatus: UserStatus) -> Promise<Void> {
        if onlineStatus == .offline {
            return Promise(error: TurmsBusinessError(.illegalArguments))
        }
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateUserOnlineStatusRequest")
                .field("userStatus", onlineStatus))
            .map { _ in () }
    }

    public func updatePassword(password: String) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateUserRequest")
                .wrappedField("password", password))
            .map { _ in () }
    }

    public func updateProfile(
        name: String? = nil,
        intro: String? = nil,
        profilePictureUrl: String? = nil,
        profileAccessStrategy: ProfileAccessStrategy? = nil) -> Promise<Void> {
        if Validator.areAllNil(name, intro, profilePictureUrl, profileAccessStrategy) {
            return Promise.value(())
        }
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateUserRequest")
                .wrappedField("name", name)
                .wrappedField("intro", intro)
                .wrappedField("profilePictureUrl", profilePictureUrl)
                .field("profileAccessStrategy", profileAccessStrategy))
            .map { _ in () }
    }

    public func queryUserGroupInvitations(lastUpdatedDate: Date? = nil) -> Promise<GroupInvitationsWithVersion> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryUserGroupInvitationsRequest")
                .wrappedField("lastUpdatedDate", lastUpdatedDate))
            .map { $0.data.groupInvitationsWithVersion }
    }

    public func queryUserProfile(userId: Int64, lastUpdatedDate: Date? = nil) -> Promise<UserInfoWithVersion> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryUserProfileRequest")
                .field("userId", userId)
                .wrappedField("lastUpdatedDate", lastUpdatedDate))
            .map { UserInfoWithVersion.from($0) }
    }

    public func queryUsersIdsNearby(latitude: Float, longitude: Float, distance: Int32? = nil, maxNumber: Int32? = nil) -> Promise<[Int64]> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryUsersIdsNearbyRequest")
                .field("latitude", latitude)
                .field("longitude", longitude)
                .wrappedField("distance", distance)
                .wrappedField("maxNumber", maxNumber))
            .map { $0.data.ids.values }
    }

    public func queryUsersInfosNearby(latitude: Float, longitude: Float, distance: Int32? = nil, maxNumber: Int32? = nil) -> Promise<[UserInfo]> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryUsersInfosNearbyRequest")
                .field("latitude", latitude)
                .field("longitude", longitude)
                .wrappedField("distance", distance)
                .wrappedField("maxNumber", maxNumber))
            .map { $0.data.usersInfosWithVersion.userInfos }
    }

    public func queryUsersOnlineStatusRequest(usersIds: [Int64]) -> Promise<[UserStatusDetail]> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryUsersOnlineStatusRequest")
                .field("usersIds", usersIds))
            .map { $0.data.usersOnlineStatuses.userStatuses }
    }

    // Relationship

    public func queryRelationships(
        relatedUsersIds: [Int64]? = nil,
        isBlocked: Bool? = nil,
        groupIndex: Int32? = nil,
        lastUpdatedDate: Date? = nil) -> Promise<UserRelationshipsWithVersion> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryRelationshipsRequest")
                .field("relatedUsersIds", relatedUsersIds)
                .wrappedField("isBlocked", isBlocked)
                .wrappedField("groupIndex", groupIndex)
                .wrappedField("lastUpdatedDate", lastUpdatedDate))
            .map { $0.data.userRelationshipsWithVersion }
    }

    public func queryRelatedUsersIds(isBlocked: Bool? = nil, groupIndex: Int32? = nil, lastUpdatedDate: Date? = nil) -> Promise<Int64ValuesWithVersion> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryRelatedUsersIdsRequest")
                .wrappedField("isBlocked", isBlocked)
                .wrappedField("groupIndex", groupIndex)
                .wrappedField("lastUpdatedDate", lastUpdatedDate))
            .map { $0.data.idsWithVersion }
    }

    public func queryFriends(groupIndex: Int32? = nil, lastUpdatedDate: Date? = nil) -> Promise<UserRelationshipsWithVersion> {
        return queryRelationships(
            isBlocked: false,
            groupIndex: groupIndex,
            lastUpdatedDate: lastUpdatedDate)
    }

    public func queryBlacklistedUsers(groupIndex: Int32? = nil, lastUpdatedDate: Date? = nil) -> Promise<UserRelationshipsWithVersion> {
        return queryRelationships(
            isBlocked: true,
            groupIndex: groupIndex,
            lastUpdatedDate: lastUpdatedDate)
    }

    public func createRelationship(userId: Int64, isBlocked: Bool, groupIndex: Int32? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("createRelationshipRequest")
                .field("userId", userId)
                .field("isBlocked", isBlocked)
                .wrappedField("groupIndex", groupIndex))
            .map { _ in () }
    }

    public func createFriendRelationship(userId: Int64, groupIndex: Int32? = nil) -> Promise<Void> {
        return createRelationship(
            userId: userId,
            isBlocked: false,
            groupIndex: groupIndex)
    }

    public func createBlacklistedUserRelationship(userId: Int64, groupIndex: Int32? = nil) -> Promise<Void> {
        return createRelationship(
            userId: userId,
            isBlocked: true,
            groupIndex: groupIndex)
    }

    public func deleteRelationship(relatedUserId: Int64, deleteGroupIndex: Int32? = nil, targetGroupIndex: Int32? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("deleteRelationshipRequest")
                .field("relatedUserId", relatedUserId)
                .wrappedField("groupIndex", deleteGroupIndex)
                .wrappedField("targetGroupIndex", targetGroupIndex))
            .map { _ in () }
    }

    public func updateRelationship(relatedUserId: Int64, isBlocked: Bool? = nil, groupIndex: Int32? = nil) -> Promise<Void> {
        if Validator.areAllNil(isBlocked, groupIndex) {
            return Promise.value(())
        }
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateRelationshipRequest")
                .field("relatedUserId", relatedUserId)
                .wrappedField("blocked", isBlocked)
                .wrappedField("newGroupIndex", groupIndex))
            .map { _ in () }
    }

    public func sendFriendRequest(recipientId: Int64, content: String) -> Promise<Int64> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("createFriendRequestRequest")
                .field("recipientId", recipientId)
                .field("content", content))
            .map { $0.data.ids.values[0] }
    }

    public func replyFriendRequest(requestId: Int64, responseAction: ResponseAction, reason: String? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateFriendRequestRequest")
                .field("requestId", requestId)
                .field("responseAction", responseAction)
                .wrappedField("reason", reason))
            .map { _ in () }
    }

    public func queryFriendRequests(lastUpdatedDate: Date? = nil) -> Promise<UserFriendRequestsWithVersion> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryFriendRequestsRequest")
                .wrappedField("lastUpdatedDate", lastUpdatedDate))
            .map { $0.data.userFriendRequestsWithVersion }
    }

    public func createRelationshipGroup(name: String) -> Promise<Int32> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("createRelationshipGroupRequest")
                .field("name", name))
            .map { Int32($0.data.ids.values[0]) }
    }

    public func deleteRelationshipGroups(groupIndex: Int32, targetGroupIndex: Int32? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("deleteRelationshipGroupRequest")
                .field("groupIndex", groupIndex)
                .wrappedField("targetGroupIndex", targetGroupIndex))
            .map { _ in () }
    }

    public func updateRelationshipGroup(groupIndex: Int32, newName: String) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateRelationshipGroupRequest")
                .field("groupIndex", groupIndex)
                .field("newName", newName))
            .map { _ in () }
    }

    public func queryRelationshipGroups(lastUpdatedDate: Date? = nil) -> Promise<UserRelationshipGroupsWithVersion> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("queryRelationshipGroupsRequest")
                .wrappedField("lastUpdatedDate", lastUpdatedDate))
            .map { $0.data.userRelationshipGroupsWithVersion }
    }

    public func moveRelatedUserToGroup(relatedUserId: Int64, groupIndex: Int32) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateRelationshipRequest")
                .field("relatedUserId", relatedUserId)
                .wrappedField("newGroupIndex", groupIndex))
            .map { _ in () }
    }

    /**
     * updateLocation() in UserService is different from sendMessage() with records of location in MessageService
     * updateLocation() in UserService sends the location of user to the server only.
     * sendMessage() with records of location sends user's location to both server and its recipients.
     */
    public func updateLocation(latitude: Float, longitude: Float, name: String? = nil, address: String? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send(RequestBuilder.newInstance()
                .request("updateUserLocationRequest")
                .field("latitude", latitude)
                .field("longitude", longitude)
                .wrappedField("name", name)
                .wrappedField("address", address))
            .map { _ in () }
    }
}
