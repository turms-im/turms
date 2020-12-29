import Foundation
import PromiseKit

public class UserService {
    private weak var turmsClient: TurmsClient!
    var userId: Int64?
    var password: String?
    var location: Position?
    var userOnlineStatus: UserStatus?
    var deviceType: DeviceType?

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
    }

    public func login(
        userId: Int64,
        password: String,
        deviceType: DeviceType? = nil,
        userOnlineStatus: UserStatus? = .available,
        location: Position? = nil) -> Promise<Void> {
        self.userId = userId
        self.password = password
        self.userOnlineStatus = userOnlineStatus!
        self.deviceType = deviceType ?? .ios
        self.location = location
        return turmsClient.driver.connect(
            userId: userId,
            password: password,
            deviceType: deviceType,
            userOnlineStatus: userOnlineStatus,
            location: location)
    }

    public func relogin() -> Promise<Void> {
        if userId == nil || password == nil {
            return Promise(error: TurmsBusinessError(TurmsStatusCode.reloginShouldBeCalledAfterLogin))
        } else {
            return login(
                userId: userId!,
                password: password!,
                deviceType: deviceType,
                userOnlineStatus: userOnlineStatus,
                location: location)
        }
    }

    public func logout() -> Promise<Void> {
        return turmsClient.driver.disconnect()
    }

    public func updateUserOnlineStatus(_ onlineStatus: UserStatus) -> Promise<Void> {
        if onlineStatus == .offline {
            return Promise(error: TurmsBusinessError(TurmsStatusCode.illegalArgument, "The online status must not be OFFLINE"))
        }
        return turmsClient.driver
            .send { $0
                .request("updateUserOnlineStatusRequest")
                .field("userStatus", onlineStatus)
            }
            .asVoid()
    }

    public func disconnectOnlineDevices(_ deviceTypes: [DeviceType]) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateUserOnlineStatusRequest")
                .field("userStatus", UserStatus.offline)
                .field("deviceTypes", deviceTypes)
            }
            .asVoid()
    }

    public func updatePassword(_ password: String) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateUserRequest")
                .field("password", password)
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
            .send { $0
                .request("updateUserRequest")
                .field("name", name)
                .field("intro", intro)
                .field("profileAccessStrategy", profileAccessStrategy)
            }
            .asVoid()
    }

    public func queryUserProfile(userId: Int64, lastUpdatedDate: Date? = nil) -> Promise<UserInfoWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryUserProfileRequest")
                .field("userId", userId)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try UserInfoWithVersion.from($0) }
    }

    public func queryUserIdsNearby(latitude: Float, longitude: Float, distance: Int32? = nil, maxNumber: Int32? = nil) -> Promise<[Int64]> {
        return turmsClient.driver
            .send { $0
                .request("queryUserIdsNearbyRequest")
                .field("latitude", latitude)
                .field("longitude", longitude)
                .field("distance", distance)
                .field("maxNumber", maxNumber)
            }
            .map { $0.data.ids.values }
    }

    public func queryUserSessionIdsNearby(latitude: Float, longitude: Float, distance: Int32? = nil, maxNumber: Int32? = nil) -> Promise<[UserSessionId]> {
        return turmsClient.driver
            .send { $0
                .request("queryUserIdsNearbyRequest")
                .field("latitude", latitude)
                .field("longitude", longitude)
                .field("distance", distance)
                .field("maxNumber", maxNumber)
            }
            .map { $0.data.userSessionIds.userSessionIds }
    }

    public func queryUserInfosNearby(latitude: Float, longitude: Float, distance: Int32? = nil, maxNumber: Int32? = nil) -> Promise<[UserInfo]> {
        return turmsClient.driver
            .send { $0
                .request("queryUserInfosNearbyRequest")
                .field("latitude", latitude)
                .field("longitude", longitude)
                .field("distance", distance)
                .field("maxNumber", maxNumber)
            }
            .map { $0.data.usersInfosWithVersion.userInfos }
    }

    public func queryUserOnlineStatusesRequest(_ userIds: [Int64]) -> Promise<[UserStatusDetail]> {
        return turmsClient.driver
            .send { $0
                .request("queryUserOnlineStatusesRequest")
                .field("userIds", userIds)
            }
            .map { $0.data.usersOnlineStatuses.userStatuses }
    }

    // Relationship

    public func queryRelationships(
        relatedUserIds: [Int64]? = nil,
        isBlocked: Bool? = nil,
        groupIndex: Int32? = nil,
        lastUpdatedDate: Date? = nil) -> Promise<UserRelationshipsWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryRelationshipsRequest")
                .field("userIds", relatedUserIds)
                .field("blocked", isBlocked)
                .field("groupIndex", groupIndex)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(UserRelationshipsWithVersion.self) }
    }

    public func queryRelatedUserIds(isBlocked: Bool? = nil, groupIndex: Int32? = nil, lastUpdatedDate: Date? = nil) -> Promise<Int64ValuesWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryRelatedUserIdsRequest")
                .field("blocked", isBlocked)
                .field("groupIndex", groupIndex)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(Int64ValuesWithVersion.self) }
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
            .send { $0
                .request("createRelationshipRequest")
                .field("userId", userId)
                .field("blocked", isBlocked)
                .field("groupIndex", groupIndex)
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
            .send { $0
                .request("deleteRelationshipRequest")
                .field("userId", relatedUserId)
                .field("groupIndex", deleteGroupIndex)
                .field("targetGroupIndex", targetGroupIndex)
            }
            .asVoid()
    }

    public func updateRelationship(relatedUserId: Int64, isBlocked: Bool? = nil, groupIndex: Int32? = nil) -> Promise<Void> {
        if Validator.areAllNil(isBlocked, groupIndex) {
            return Promise.value(())
        }
        return turmsClient.driver
            .send { $0
                .request("updateRelationshipRequest")
                .field("userId", relatedUserId)
                .field("blocked", isBlocked)
                .field("newGroupIndex", groupIndex)
            }
            .asVoid()
    }

    public func sendFriendRequest(recipientId: Int64, content: String) -> Promise<Int64> {
        return turmsClient.driver
            .send { $0
                .request("createFriendRequestRequest")
                .field("recipientId", recipientId)
                .field("content", content)
            }
            .map { try NotificationUtil.getFirstId($0) }
    }

    public func replyFriendRequest(requestId: Int64, responseAction: ResponseAction, reason: String? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateFriendRequestRequest")
                .field("requestId", requestId)
                .field("responseAction", responseAction)
                .field("reason", reason)
            }
            .asVoid()
    }

    public func queryFriendRequests(_ areSentByMe: Bool, lastUpdatedDate: Date? = nil) -> Promise<UserFriendRequestsWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryFriendRequestsRequest")
                .field("areSentByMe", areSentByMe)
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(UserFriendRequestsWithVersion.self) }
    }

    public func createRelationshipGroup(_ name: String) -> Promise<Int32> {
        return turmsClient.driver
            .send { $0
                .request("createRelationshipGroupRequest")
                .field("name", name)
            }
            .map { try Int32(NotificationUtil.getFirstId($0)) }
    }

    public func deleteRelationshipGroups(groupIndex: Int32, targetGroupIndex: Int32? = nil) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("deleteRelationshipGroupRequest")
                .field("groupIndex", groupIndex)
                .field("targetGroupIndex", targetGroupIndex)
            }
            .asVoid()
    }

    public func updateRelationshipGroup(groupIndex: Int32, newName: String) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateRelationshipGroupRequest")
                .field("groupIndex", groupIndex)
                .field("newName", newName)
            }
            .asVoid()
    }

    public func queryRelationshipGroups(_ lastUpdatedDate: Date? = nil) -> Promise<UserRelationshipGroupsWithVersion?> {
        return turmsClient.driver
            .send { $0
                .request("queryRelationshipGroupsRequest")
                .field("lastUpdatedDate", lastUpdatedDate)
            }
            .map { try $0.data.kind?.getKindData(UserRelationshipGroupsWithVersion.self) }
    }

    public func moveRelatedUserToGroup(relatedUserId: Int64, groupIndex: Int32) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateRelationshipRequest")
                .field("userId", relatedUserId)
                .field("newGroupIndex", groupIndex)
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
            .send { $0
                .request("updateUserLocationRequest")
                .field("latitude", latitude)
                .field("longitude", longitude)
                .field("name", name)
                .field("address", address)
            }
            .asVoid()
    }
}
