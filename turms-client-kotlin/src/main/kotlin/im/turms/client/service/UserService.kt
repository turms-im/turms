/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package im.turms.client.service

import im.turms.client.TurmsClient
import im.turms.client.annotation.NotEmpty
import im.turms.client.exception.ResponseException
import im.turms.client.extension.getLongOrThrow
import im.turms.client.extension.toResponse
import im.turms.client.model.Response
import im.turms.client.model.ResponseStatusCode
import im.turms.client.model.SessionCloseInfo
import im.turms.client.model.SessionCloseStatus
import im.turms.client.model.User
import im.turms.client.model.UserLocation
import im.turms.client.model.proto.constant.DeviceType
import im.turms.client.model.proto.constant.ProfileAccessStrategy
import im.turms.client.model.proto.constant.ResponseAction
import im.turms.client.model.proto.constant.UserStatus
import im.turms.client.model.proto.model.common.LongsWithVersion
import im.turms.client.model.proto.model.user.NearbyUser
import im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion
import im.turms.client.model.proto.model.user.UserInfo
import im.turms.client.model.proto.model.user.UserOnlineStatus
import im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion
import im.turms.client.model.proto.model.user.UserRelationshipsWithVersion
import im.turms.client.model.proto.request.user.CreateSessionRequest
import im.turms.client.model.proto.request.user.DeleteSessionRequest
import im.turms.client.model.proto.request.user.QueryNearbyUsersRequest
import im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest
import im.turms.client.model.proto.request.user.QueryUserProfilesRequest
import im.turms.client.model.proto.request.user.UpdateUserLocationRequest
import im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest
import im.turms.client.model.proto.request.user.UpdateUserRequest
import im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest
import im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest
import im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest
import im.turms.client.model.proto.request.user.relationship.DeleteFriendRequestRequest
import im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest
import im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest
import im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest
import im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest
import im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest
import im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest
import im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest
import im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest
import im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest
import im.turms.client.util.SystemUtil
import im.turms.client.util.Validator
import java.util.*

/**
 * @author James Chen
 */
class UserService(private val turmsClient: TurmsClient) {

    /**
     * The user information of the logged-in user.
     */
    var userInfo: User? = null
    private var storePassword = false

    private val onOnlineListeners: MutableList<(() -> Unit)> = LinkedList()
    private val onOfflineListeners: MutableList<((SessionCloseInfo) -> Unit)> = LinkedList()

    val isLoggedIn: Boolean
        get() = userInfo?.onlineStatus != null &&
            userInfo?.onlineStatus != UserStatus.UNRECOGNIZED &&
            userInfo?.onlineStatus != UserStatus.OFFLINE

    init {
        turmsClient.driver.addOnDisconnectedListener { t ->
            changeToOffline(SessionCloseInfo(SessionCloseStatus.CONNECTION_CLOSED, cause = t))
        }
        turmsClient.driver.addNotificationListener {
            if (it.hasCloseStatus() && isLoggedIn) {
                val info = SessionCloseInfo(
                    it.closeStatus,
                    if (it.hasCode()) it.code else null,
                    if (it.hasReason()) it.reason else null,
                )
                changeToOffline(info)
            }
        }
    }

    /**
     * Add an online listener that will be called when the user becomes online.
     * A session is considered online when it has a TCP connection with the server,
     * and the user is logged in by [login].
     */
    fun addOnOnlineListener(listener: () -> Unit) = onOnlineListeners.add(listener)

    /**
     * Add an offline listener that will be called when the user becomes offline.
     * A session is considered offline when it has no TCP connection with the server,
     * or has a connected TCP connection with the server, but the user is not logged in by [login].
     */
    fun addOnOfflineListener(listener: (SessionCloseInfo) -> Unit) = onOfflineListeners.add(listener)

    /**
     * Remove an online listener.
     */
    fun removeOnOnlineListener(listener: () -> Unit) = onOnlineListeners.remove(listener)

    /**
     * Remove an offline listener.
     */
    fun removeOnOfflineListener(listener: (SessionCloseInfo) -> Unit) = onOfflineListeners.remove(listener)

    /**
     * Log in.
     *
     * * If the underlying TCP connection is not connected,
     *   the method will connect it first under the hood.
     * * If log in successfully, the session is considered online.
     *   And the listener registered by [addOnOnlineListener] will be called.
     *
     * Related docs:
     * * [Turms Identity and Access Management](https://turms-im.github.io/docs/server/module/identity-access-management.html)
     *
     * @param userId the user ID
     * @param password the user password.
     * @param deviceType the device type.
     * If null, the detected device type will be used.
     * Note: The device types of online session that conflicts with [deviceType]
     * will be closed by the server if logged in successfully.
     * @param deviceDetails the device details.
     * Some plugins use this to pass additional information about the device.
     * e.g. Push notification token.
     * @param onlineStatus the online status.
     * @param location the location of the user.
     * @param storePassword whether to store the password in [userInfo].
     * @throws ResponseException if an error occurs.
     * 1. If the client is not compatible with the server, throws
     * with the code [ResponseStatusCode.UNSUPPORTED_CLIENT_VERSION].
     * 2. Depending on the server property `turms.gateway.simultaneous-login.strategy`,
     * throws with the code [ResponseStatusCode.LOGIN_FROM_FORBIDDEN_DEVICE_TYPE]
     * if the specified device type is forbidden.
     * 3. If provided credentials are invalid,
     * throws with the code [ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED].
     */
    suspend fun login(
        userId: Long,
        password: String? = null,
        deviceType: DeviceType = SystemUtil.deviceType,
        deviceDetails: Map<String, String>? = null,
        onlineStatus: UserStatus = UserStatus.AVAILABLE,
        location: UserLocation? = null,
        storePassword: Boolean = false,
    ): Response<Unit> {
        val user = User(userId)
        if (storePassword) {
            user.password = password
        }
        user.deviceType = deviceType
        user.onlineStatus = onlineStatus
        user.location = location
        if (!turmsClient.driver.isConnected) {
            turmsClient.driver.connect()
        }
        val notification = turmsClient.driver.send(
            CreateSessionRequest.newBuilder().apply {
                this.version = 1
                this.userId = userId
                password?.let { this.password = it }
                deviceType.let { this.deviceType = it }
                deviceDetails?.let { this.putAllDeviceDetails(it) }
                onlineStatus.let { this.userStatus = it }
                location?.let {
                    this.location = im.turms.client.model.proto.model.user.UserLocation.newBuilder().apply {
                        this.longitude = it.longitude
                        this.latitude = it.latitude
                    }.build()
                }
            },
        )
        changeToOnline()
        this.storePassword = storePassword
        userInfo = user
        return notification.toResponse()
    }

    /**
     * Log out.
     *
     * After logging out, the session is considered offline.
     * And the listener registered by [addOnOfflineListener] will be called.
     *
     * @param disconnect whether to close the underlying TCP connection immediately
     * rather than sending a delete session request first and then closing the connection.
     * @throws ResponseException if an error occurs.
     */
    suspend fun logout(disconnect: Boolean = true): Response<Unit> {
        if (disconnect) {
            turmsClient.driver.disconnect()
        } else {
            try {
                turmsClient.driver.send(DeleteSessionRequest.newBuilder())
            } catch (e: ResponseException) {
                if (e.code != ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED) {
                    throw e
                }
            }
        }
        changeToOffline(SessionCloseInfo(SessionCloseStatus.DISCONNECTED_BY_CLIENT))
        return Response.unitValue()
    }

    /**
     * Update the online status of the logged-in user.
     *
     * Notifications:
     * * If the server property `turms.service.notification.user-online-status-updated.notify-requester-other-online-sessions`
     *   is true （true by default）,
     *   the server will send an update online status notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.user-online-status-updated.notify-non-blocked-related-users`,
     *   is true (false by default),
     *   the server will send an update online status notification to all non-blocked related users of the logged-in user actively.
     *
     * @param onlineStatus the new online status.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateOnlineStatus(onlineStatus: UserStatus): Response<Unit> =
        if (onlineStatus == UserStatus.OFFLINE) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The online status must not be $onlineStatus",
            )
        } else {
            turmsClient.driver
                .send(
                    UpdateUserOnlineStatusRequest.newBuilder().apply {
                        this.userStatus = onlineStatus
                    },
                ).toResponse<Unit>()
                .apply {
                    userInfo?.onlineStatus = onlineStatus
                }
        }

    /**
     * Disconnect the online devices of the logged-in user.
     *
     * If the specified device types are not online, nothing will happen and
     * no exception will be thrown.
     *
     * @param deviceTypes the device types to disconnect.
     * @throws ResponseException if an error occurs.
     */
    suspend fun disconnectOnlineDevices(@NotEmpty deviceTypes: Set<DeviceType>): Response<Unit> =
        if (deviceTypes.isEmpty()) {
            throw ResponseException.from(ResponseStatusCode.ILLEGAL_ARGUMENT, "\"deviceTypes\" must not be null or empty")
        } else {
            turmsClient.driver
                .send(
                    UpdateUserOnlineStatusRequest.newBuilder().apply {
                        this.userStatus = UserStatus.OFFLINE
                        this.addAllDeviceTypes(deviceTypes)
                    },
                ).toResponse()
        }

    /**
     * Update the password of the logged-in user.
     *
     * @param password the new password.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updatePassword(password: String): Response<Unit> = turmsClient.driver
        .send(
            UpdateUserRequest.newBuilder().apply {
                this.password = password
            },
        ).toResponse<Unit>()
        .apply {
            if (storePassword) {
                userInfo?.password = password
            }
        }

    /**
     * Update the profile of the logged-in user.
     *
     * @param name the new name.
     * If null, the name will not be updated.
     * @param intro the new intro.
     * If null, the intro will not be updated.
     * @param profilePicture the new profile picture.
     * If null, the profile picture will not be updated.
     * The profile picture can be anything you want.
     * e.g. an image URL or a base64 encoded string.
     * Note: You can use [StorageService.uploadUserProfilePicture]
     * to upload the profile picture and use the returned URL as [profilePicture].
     * @param profileAccessStrategy the new profile access strategy.
     * If null, the profile access strategy will not be updated.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateProfile(
        name: String? = null,
        intro: String? = null,
        profilePicture: String? = null,
        profileAccessStrategy: ProfileAccessStrategy? = null,
    ): Response<Unit> = if (Validator.areAllNull(name, intro, profileAccessStrategy)) {
        Response.unitValue()
    } else {
        turmsClient.driver
            .send(
                UpdateUserRequest.newBuilder().apply {
                    name?.let { this.name = it }
                    intro?.let { this.intro = it }
                    profilePicture?.let { this.profilePicture = it }
                    profileAccessStrategy?.let { this.profileAccessStrategy = it }
                },
            )
            .toResponse()
    }

    /**
     * Find user profiles.
     *
     * @param userIds the target user IDs.
     * @param lastUpdatedDate the last updated date of user profiles stored locally.
     * The server will only return user profiles that are updated after [lastUpdatedDate].
     * If null, all user profiles will be returned.
     * @return a list of user profiles.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryUserProfiles(
        userIds: Set<Long>,
        lastUpdatedDate: Date? = null,
    ): Response<List<UserInfo>> = if (userIds.isEmpty()) {
        Response.emptyList()
    } else {
        turmsClient.driver
            .send(
                QueryUserProfilesRequest.newBuilder().apply {
                    addAllUserIds(userIds)
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                },
            ).toResponse {
                it.userInfosWithVersion.userInfosList
            }
    }

    /**
     * Find nearby users.
     *
     * @param latitude the latitude.
     * @param longitude the longitude.
     * @param maxCount the max count.
     * @param maxDistance the max distance.
     * @param withCoordinates whether to include coordinates.
     * @param withDistance whether to include distance.
     * @param withUserInfo whether to include user info.
     * @return a list of nearby users.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryNearbyUsers(
        latitude: Float,
        longitude: Float,
        maxCount: Int? = null,
        maxDistance: Int? = null,
        withCoordinates: Boolean? = null,
        withDistance: Boolean? = null,
        withUserInfo: Boolean? = null,
    ): Response<List<NearbyUser>> = turmsClient.driver
        .send(
            QueryNearbyUsersRequest.newBuilder().apply {
                this.latitude = latitude
                this.longitude = longitude
                maxCount?.let { this.maxCount = it }
                maxDistance?.let { this.maxDistance = it }
                withCoordinates?.let { this.withCoordinates = it }
                withDistance?.let { this.withDistance = it }
                withUserInfo?.let { this.withUserInfo = it }
            },
        ).toResponse {
            it.nearbyUsers.nearbyUsersList
        }

    /**
     * Find online status of users.
     *
     * @param userIds the target user IDs.
     * @return a list of online status of users.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryOnlineStatusesRequest(@NotEmpty userIds: Set<Long>): Response<List<UserOnlineStatus>> =
        if (userIds.isEmpty()) {
            Response.emptyList()
        } else {
            turmsClient.driver
                .send(
                    QueryUserOnlineStatusesRequest.newBuilder().apply {
                        addAllUserIds(userIds)
                    },
                ).toResponse {
                    it.userOnlineStatuses.statusesList
                }
        }

    // Relationship

    /**
     * Find relationships.
     *
     * @param relatedUserIds the target related user IDs.
     * @param isBlocked whether to query blocked relationships.
     * If null, all relationships will be returned.
     * If true, only blocked relationships will be returned.
     * If false, only non-blocked relationships will be returned.
     * @param groupIndexes the target group indexes for querying.
     * @param lastUpdatedDate the last updated date of user relationships stored locally.
     * The server will only return relationships that are created after [lastUpdatedDate].
     * If null, all relationships will be returned.
     * @return relationships and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryRelationships(
        relatedUserIds: Set<Long>? = null,
        isBlocked: Boolean? = null,
        groupIndexes: Set<Int>? = null,
        lastUpdatedDate: Date? = null,
    ): Response<UserRelationshipsWithVersion?> = turmsClient.driver
        .send(
            QueryRelationshipsRequest.newBuilder().apply {
                relatedUserIds?.let { addAllUserIds(it) }
                isBlocked?.let { this.blocked = it }
                groupIndexes?.let { this.addAllGroupIndexes(it) }
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            },
        ).toResponse {
            if (it.hasUserRelationshipsWithVersion()) it.userRelationshipsWithVersion else null
        }

    /**
     * Find related user IDs.
     *
     * @param isBlocked whether to query blocked relationships.
     * If null, all relationships will be returned.
     * If true, only blocked relationships will be returned.
     * If false, only non-blocked relationships will be returned.
     * @param groupIndexes the target group indexes for querying.
     * @param lastUpdatedDate the last updated date of related user IDs stored locally.
     * The server will only return related user IDs that are created after [lastUpdatedDate].
     * If null, all related user IDs will be returned.
     * @return related user IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryRelatedUserIds(
        isBlocked: Boolean? = null,
        groupIndexes: Set<Int>? = null,
        lastUpdatedDate: Date? = null,
    ): Response<LongsWithVersion?> = turmsClient.driver
        .send(
            QueryRelatedUserIdsRequest.newBuilder().apply {
                isBlocked?.let { this.blocked = it }
                groupIndexes?.let { this.addAllGroupIndexes(it) }
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            },
        ).toResponse {
            if (it.hasLongsWithVersion()) it.longsWithVersion else null
        }

    /**
     * Find friends.
     *
     * @param groupIndexes the target group indexes for finding.
     * @param lastUpdatedDate the last updated date of friends stored locally.
     * The server will only return friends that are created after [lastUpdatedDate].
     * If null, all friends will be returned.
     * @return friends and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryFriends(
        groupIndexes: Set<Int>? = null,
        lastUpdatedDate: Date? = null,
    ): Response<UserRelationshipsWithVersion?> =
        queryRelationships(isBlocked = false, groupIndexes = groupIndexes, lastUpdatedDate = lastUpdatedDate)

    /**
     * Find blocked users.
     *
     * @param groupIndexes the target group indexes for finding.
     * @param lastUpdatedDate the last updated date of blocked users stored locally.
     * The server will only return friends that are created after [lastUpdatedDate].
     * If null, all blocked users will be returned.
     * @return blocked users and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryBlockedUsers(
        groupIndexes: Set<Int>? = null,
        lastUpdatedDate: Date? = null,
    ): Response<UserRelationshipsWithVersion?> =
        queryRelationships(isBlocked = true, groupIndexes = groupIndexes, lastUpdatedDate = lastUpdatedDate)

    /**
     * Create a relationship.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
     *   is true (false by default), the server will send a new relationship notification to [userId] actively.
     *
     * @param userId the target user ID.
     * @param isBlocked whether to create a blocked relationship.
     * If true, a blocked relationship will be created,
     * and the target user will not be able to send messages to the logged-in user.
     * @param groupIndex the target group index in which create the relationship.
     * If null, the relationship will be created in the default group.
     * @throws ResponseException if an error occurs.
     */
    suspend fun createRelationship(
        userId: Long,
        isBlocked: Boolean,
        groupIndex: Int? = null,
    ): Response<Unit> = turmsClient.driver
        .send(
            CreateRelationshipRequest.newBuilder().apply {
                this.userId = userId
                this.blocked = isBlocked
                groupIndex?.let { this.groupIndex = it }
            },
        )
        .toResponse()

    /**
     * Create a friend (non-blocked) relationship.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
     *   is true (false by default), the server will send a new relationship notification to [userId] actively.
     *
     * @param userId the target user ID.
     * @param groupIndex the target group index in which create the relationship.
     * If null, the relationship will be created in the default group.
     * @throws ResponseException if an error occurs.
     */
    suspend fun createFriendRelationship(
        userId: Long,
        groupIndex: Int? = null,
    ): Response<Unit> = createRelationship(userId, false, groupIndex)

    /**
     * Create a blocked user relationship.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
     *   is true (false by default), the server will send a new relationship notification to [userId] actively.
     *
     * @param userId the target user ID.
     * @param groupIndex the target group index in which create the relationship.
     * If null, the relationship will be created in the default group.
     * @throws ResponseException if an error occurs.
     */
    suspend fun createBlockedUserRelationship(
        userId: Long,
        groupIndex: Int? = null,
    ): Response<Unit> = createRelationship(userId, true, groupIndex)

    /**
     * Delete a relationship.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-deleted.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-deleted.notify-group-members`,
     *   is true (true by default), the server will send a delete relationship notification to all group members in groups.
     *
     * @param relatedUserId the target user ID.
     * @param deleteGroupIndex the target group index in which delete the relationship.
     * If null, the relationship will be deleted in all groups.
     * @param targetGroupIndex TODO: not implemented yet.
     * @throws ResponseException if an error occurs.
     */
    suspend fun deleteRelationship(
        relatedUserId: Long,
        deleteGroupIndex: Int? = null,
        targetGroupIndex: Int? = null,
    ): Response<Unit> = turmsClient.driver
        .send(
            DeleteRelationshipRequest.newBuilder().apply {
                this.userId = relatedUserId
                deleteGroupIndex?.let { this.groupIndex = it }
                targetGroupIndex?.let { this.targetGroupIndex = it }
            },
        )
        .toResponse()

    /**
     * Update a relationship.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a update relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-related-user`,
     *   is true (false by default), the server will send a update relationship notification to [relatedUserId] actively.
     *
     * @param relatedUserId the target user ID.
     * @param isBlocked whether to update a blocked relationship.
     * If null, the relationship will not be updated.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateRelationship(
        relatedUserId: Long,
        isBlocked: Boolean? = null,
        groupIndex: Int? = null,
    ): Response<Unit> = if (Validator.areAllFalsy(isBlocked, groupIndex)) {
        Response.unitValue()
    } else {
        turmsClient.driver
            .send(
                UpdateRelationshipRequest.newBuilder().apply {
                    this.userId = relatedUserId
                    isBlocked?.let { this.blocked = it }
                    groupIndex?.let { this.newGroupIndex = it }
                },
            )
            .toResponse()
    }

    /**
     * Send a friend request.
     *
     * Notifications:
     * * If the server property `turms.service.notification.friend-request-created.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a new friend request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.friend-request-created.notify-friend-request-recipient`,
     *   is true (true by default), the server will send a new friend request notification to [recipientId] actively.
     *
     * @param recipientId the target user ID.
     * @param content the content of the friend request.
     * @return the request ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun sendFriendRequest(
        recipientId: Long,
        content: String,
    ): Response<Long> = turmsClient.driver
        .send(
            CreateFriendRequestRequest.newBuilder().apply {
                this.recipientId = recipientId
                this.content = content
            },
        ).toResponse {
            it.getLongOrThrow()
        }

    /**
     * Delete/Recall a friend request.
     *
     * Authorization:
     * * If the server property `turms.service.user.friend-request.allow-recall-pending-friend-request-by-sender`
     *   is true (false by default), the logged-in user can recall pending friend requests sent by themselves.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.RECALLING_FRIEND_REQUEST_IS_DISABLED].
     * * If the logged-in user is not the sender of the friend request,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_SENDER_TO_RECALL_FRIEND_REQUEST].
     * * If the friend request is not pending (e.g. expired, accepted, deleted, etc),
     *   throws [ResponseException] with the code [ResponseStatusCode.RECALL_NON_PENDING_FRIEND_REQUEST].
     *
     * Notifications:
     * * If the server property `turms.service.notification.friend-request-recalled.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete friend request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.friend-request-recalled.notify-friend-request-recipient`
     *   is true (true by default), the server will send a delete friend request notification to the recipient of the friend request actively.
     *
     * @throws ResponseException if an error occurs.
     */
    suspend fun deleteFriendRequest(requestId: Long): Response<Unit> = turmsClient.driver
        .send(
            DeleteFriendRequestRequest.newBuilder().apply {
                this.requestId = requestId
            },
        )
        .toResponse()

    /**
     * Reply to a friend request.
     *
     * If the logged-in user accepts a friend request sent by another user,
     * the server will create a relationship between the logged-in user and the friend request sender.
     *
     * Authorization:
     * * If the logged-in user is not the recipient of the friend request,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_RECIPIENT_TO_UPDATE_FRIEND_REQUEST].
     * * If the friend request is not pending (e.g. expired, accepted, deleted, etc),
     *   throws [ResponseException] with the code [ResponseStatusCode.UPDATE_NON_PENDING_FRIEND_REQUEST].
     *
     * Notifications:
     * * If the server property `turms.service.notification.friend-request-replied.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a reply friend request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.friend-request-replied.notify-friend-request-sender`,
     *   is true (true by default), the server will send a reply friend request notification to the friend request sender actively.
     *
     * @param requestId the target friend request ID.
     * @param responseAction the response action.
     * @param reason the reason of the response.
     * @throws ResponseException if an error occurs.
     */
    suspend fun replyFriendRequest(
        requestId: Long,
        responseAction: ResponseAction,
        reason: String? = null,
    ): Response<Unit> = turmsClient.driver
        .send(
            UpdateFriendRequestRequest.newBuilder().apply {
                this.requestId = requestId
                this.responseAction = responseAction
                reason?.let { this.reason = it }
            },
        )
        .toResponse()

    /**
     * Find friend requests.
     *
     * @param areSentByMe whether to find the friend requests sent by the logged-in user.
     * If true, find the friend requests sent by the logged-in user.
     * If false, find the friend requests not sent to the logged-in user.
     * @param lastUpdatedDate the last updated date of friend requests stored locally.
     * The server will only return friend requests that are updated after [lastUpdatedDate].
     * If null, all friend requests will be returned.
     * @return friend requests and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryFriendRequests(
        areSentByMe: Boolean,
        lastUpdatedDate: Date? = null,
    ): Response<UserFriendRequestsWithVersion?> = turmsClient.driver
        .send(
            QueryFriendRequestsRequest.newBuilder().apply {
                this.areSentByMe = areSentByMe
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            },
        ).toResponse {
            if (it.hasUserFriendRequestsWithVersion()) it.userFriendRequestsWithVersion else null
        }

    /**
     * Create a relationship group.
     *
     * @param name the name of the group.
     * @return the index of the created group.
     * @throws ResponseException if an error occurs.
     */
    suspend fun createRelationshipGroup(name: String): Response<Int> = turmsClient.driver
        .send(
            CreateRelationshipGroupRequest.newBuilder().apply {
                this.name = name
            },
        ).toResponse {
            it.getLongOrThrow().toInt()
        }

    /**
     * Delete relationship groups.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-deleted.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a delete relationship groups relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-deleted.notify-relationship-group-members`,
     *   is true (false by default), the server will send a delete relationship groups relationship notification to all group members in groups.
     *
     * @param groupIndex the target group index to delete.
     * @param targetGroupIndex move the group members of [groupIndex] to [targetGroupIndex]
     * when the group is deleted.
     * If null, the group members of [groupIndex] will be moved to the default group.
     * @throws ResponseException if an error occurs.
     */
    suspend fun deleteRelationshipGroups(
        groupIndex: Int,
        targetGroupIndex: Int? = null,
    ): Response<Unit> = turmsClient.driver
        .send(
            DeleteRelationshipGroupRequest.newBuilder().apply {
                this.groupIndex = groupIndex
                targetGroupIndex?.let { this.targetGroupIndex = it }
            },
        )
        .toResponse()

    /**
     * Update a relationship group.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a updated relationship groups relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-updated.notify-relationship-group-members`,
     *   is true (false by default), the server will send a updated relationship groups relationship notification to all group members in groups.
     *
     * @param groupIndex the target group index.
     * @param newName the new name of the group.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateRelationshipGroup(
        groupIndex: Int,
        newName: String,
    ): Response<Unit> = turmsClient.driver
        .send(
            UpdateRelationshipGroupRequest.newBuilder().apply {
                this.groupIndex = groupIndex
                this.newName = newName
            },
        )
        .toResponse()

    /**
     * Find relationship groups.
     *
     * @param lastUpdatedDate the last updated date of relationship groups stored locally.
     * The server will only return relationship groups that are updated after [lastUpdatedDate].
     * If null, all relationship groups will be returned.
     * @return relationship groups and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryRelationshipGroups(lastUpdatedDate: Date? = null): Response<UserRelationshipGroupsWithVersion?> =
        turmsClient.driver
            .send(
                QueryRelationshipGroupsRequest.newBuilder().apply {
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                },
            ).toResponse {
                if (it.hasUserRelationshipGroupsWithVersion()) it.userRelationshipGroupsWithVersion else null
            }

    /**
     * Move a related user to a group.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-related-user`,
     *   is true (false by default), the server will send a update relationship notification to [relatedUserId] actively.
     *
     * @param relatedUserId the target user ID.
     * @param groupIndex the target group index to which move the user.
     * @throws ResponseException if an error occurs.
     */
    suspend fun moveRelatedUserToGroup(
        relatedUserId: Long,
        groupIndex: Int,
    ): Response<Unit> = turmsClient.driver
        .send(
            UpdateRelationshipRequest.newBuilder().apply {
                this.userId = relatedUserId
                this.newGroupIndex = groupIndex
            },
        )
        .toResponse()

    /**
     * Update the location of the logged-in user.
     *
     * Note:
     * * [UserService.updateLocation] is different from
     *   [MessageService.sendMessage] with records of location.
     *   [UserService.updateLocation] sends the location of user to
     *   the server only.
     *   [MessageService.sendMessage] with records of location sends the user's location
     *   to both server and its recipients.
     *
     * @param latitude the latitude.
     * @param longitude the longitude.
     * @param details the location details
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateLocation(
        latitude: Float,
        longitude: Float,
        details: Map<String, String>? = null,
    ): Response<Unit> = turmsClient.driver
        .send(
            UpdateUserLocationRequest.newBuilder().apply {
                this.latitude = latitude
                this.longitude = longitude
                details?.let { putAllDetails(it) }
            },
        )
        .toResponse()

    private fun changeToOnline() {
        if (!isLoggedIn) {
            turmsClient.driver.stateStore.isSessionOpen = true
            onOnlineListeners.forEach { it() }
        }
    }

    private fun changeToOffline(sessionCloseInfo: SessionCloseInfo) {
        if (isLoggedIn) {
            userInfo?.onlineStatus = UserStatus.OFFLINE
            turmsClient.driver.stateStore.isSessionOpen = false
            onOfflineListeners.forEach { it(sessionCloseInfo) }
        }
    }
}
