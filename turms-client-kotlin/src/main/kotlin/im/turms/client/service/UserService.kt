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
import java.util.Date
import java.util.LinkedList

/**
 * @author James Chen
 */
class UserService(private val turmsClient: TurmsClient) {

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
                    if (it.hasReason()) it.reason else null
                )
                changeToOffline(info)
            }
        }
    }

    fun addOnOnlineListener(listener: () -> Unit) = onOnlineListeners.add(listener)

    fun addOnOfflineListener(listener: (SessionCloseInfo) -> Unit) = onOfflineListeners.add(listener)

    fun removeOnOnlineListener(listener: () -> Unit) = onOnlineListeners.remove(listener)

    fun removeOnOfflineListener(listener: (SessionCloseInfo) -> Unit) = onOfflineListeners.remove(listener)

    suspend fun login(
        userId: Long,
        password: String? = null,
        deviceType: DeviceType = SystemUtil.deviceType,
        deviceDetails: Map<String, String>? = null,
        onlineStatus: UserStatus = UserStatus.AVAILABLE,
        location: UserLocation? = null,
        storePassword: Boolean = false
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
            }
        )
        changeToOnline()
        this.storePassword = storePassword
        userInfo = user
        return notification.toResponse()
    }

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

    suspend fun updateOnlineStatus(onlineStatus: UserStatus): Response<Unit> =
        if (onlineStatus == UserStatus.OFFLINE) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The online status must not be $onlineStatus"
            )
        } else {
            turmsClient.driver
                .send(
                    UpdateUserOnlineStatusRequest.newBuilder().apply {
                        this.userStatus = onlineStatus
                    }
                ).toResponse<Unit>()
                .apply {
                    userInfo?.onlineStatus = onlineStatus
                }
        }

    suspend fun disconnectOnlineDevices(@NotEmpty deviceTypes: Set<DeviceType>): Response<Unit> =
        if (deviceTypes.isEmpty()) {
            throw ResponseException.from(ResponseStatusCode.ILLEGAL_ARGUMENT, "\"deviceTypes\" must not be null or empty")
        } else {
            turmsClient.driver
                .send(
                    UpdateUserOnlineStatusRequest.newBuilder().apply {
                        this.userStatus = UserStatus.OFFLINE
                        this.addAllDeviceTypes(deviceTypes)
                    }
                ).toResponse()
        }

    suspend fun updatePassword(password: String): Response<Unit> = turmsClient.driver
        .send(
            UpdateUserRequest.newBuilder().apply {
                this.password = password
            }
        ).toResponse<Unit>()
        .apply {
            if (storePassword) {
                userInfo?.password = password
            }
        }

    suspend fun updateProfile(
        name: String? = null,
        intro: String? = null,
        profilePicture: String? = null,
        profileAccessStrategy: ProfileAccessStrategy? = null
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
                }
            )
            .toResponse()
    }

    suspend fun queryUserProfiles(
        userIds: Set<Long>,
        lastUpdatedDate: Date? = null
    ): Response<List<UserInfo>> = if (userIds.isEmpty()) {
        Response.emptyList()
    } else {
        turmsClient.driver
            .send(
                QueryUserProfilesRequest.newBuilder().apply {
                    addAllUserIds(userIds)
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                }
            ).toResponse {
                it.userInfosWithVersion.userInfosList
            }
    }

    suspend fun queryNearbyUsers(
        latitude: Float,
        longitude: Float,
        maxCount: Int? = null,
        maxDistance: Int? = null,
        withCoordinates: Boolean? = null,
        withDistance: Boolean? = null,
        withUserInfo: Boolean? = null
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
            }
        ).toResponse {
            it.nearbyUsers.nearbyUsersList
        }

    suspend fun queryOnlineStatusesRequest(@NotEmpty userIds: Set<Long>): Response<List<UserOnlineStatus>> =
        if (userIds.isEmpty()) {
            Response.emptyList()
        } else {
            turmsClient.driver
                .send(
                    QueryUserOnlineStatusesRequest.newBuilder().apply {
                        addAllUserIds(userIds)
                    }
                ).toResponse {
                    it.userOnlineStatuses.statusesList
                }
        }

    // Relationship
    suspend fun queryRelationships(
        relatedUserIds: Set<Long>? = null,
        isBlocked: Boolean? = null,
        groupIndexes: Set<Int>? = null,
        lastUpdatedDate: Date? = null
    ): Response<UserRelationshipsWithVersion?> = turmsClient.driver
        .send(
            QueryRelationshipsRequest.newBuilder().apply {
                relatedUserIds?.let { addAllUserIds(it) }
                isBlocked?.let { this.blocked = it }
                groupIndexes?.let { this.addAllGroupIndexes(it) }
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).toResponse {
            if (it.hasUserRelationshipsWithVersion()) it.userRelationshipsWithVersion else null
        }

    suspend fun queryRelatedUserIds(
        isBlocked: Boolean? = null,
        groupIndexes: Set<Int>? = null,
        lastUpdatedDate: Date? = null
    ): Response<LongsWithVersion?> = turmsClient.driver
        .send(
            QueryRelatedUserIdsRequest.newBuilder().apply {
                isBlocked?.let { this.blocked = it }
                groupIndexes?.let { this.addAllGroupIndexes(it) }
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).toResponse {
            if (it.hasLongsWithVersion()) it.longsWithVersion else null
        }

    suspend fun queryFriends(
        groupIndexes: Set<Int>? = null,
        lastUpdatedDate: Date? = null
    ): Response<UserRelationshipsWithVersion?> =
        queryRelationships(isBlocked = false, groupIndexes = groupIndexes, lastUpdatedDate = lastUpdatedDate)

    suspend fun queryBlockedUsers(
        groupIndexes: Set<Int>? = null,
        lastUpdatedDate: Date? = null
    ): Response<UserRelationshipsWithVersion?> =
        queryRelationships(isBlocked = true, groupIndexes = groupIndexes, lastUpdatedDate = lastUpdatedDate)

    suspend fun createRelationship(
        userId: Long,
        isBlocked: Boolean,
        groupIndex: Int? = null
    ): Response<Unit> = turmsClient.driver
        .send(
            CreateRelationshipRequest.newBuilder().apply {
                this.userId = userId
                this.blocked = isBlocked
                groupIndex?.let { this.groupIndex = it }
            }
        )
        .toResponse()

    suspend fun createFriendRelationship(
        userId: Long,
        groupIndex: Int? = null
    ): Response<Unit> = createRelationship(userId, false, groupIndex)

    suspend fun createBlockedUserRelationship(
        userId: Long,
        groupIndex: Int? = null
    ): Response<Unit> = createRelationship(userId, true, groupIndex)

    suspend fun deleteRelationship(
        relatedUserId: Long,
        deleteGroupIndex: Int? = null,
        targetGroupIndex: Int? = null
    ): Response<Unit> = turmsClient.driver
        .send(
            DeleteRelationshipRequest.newBuilder().apply {
                this.userId = relatedUserId
                deleteGroupIndex?.let { this.groupIndex = it }
                targetGroupIndex?.let { this.targetGroupIndex = it }
            }
        )
        .toResponse()

    suspend fun updateRelationship(
        relatedUserId: Long,
        isBlocked: Boolean? = null,
        groupIndex: Int? = null
    ): Response<Unit> = if (Validator.areAllFalsy(isBlocked, groupIndex)) {
        Response.unitValue()
    } else {
        turmsClient.driver
            .send(
                UpdateRelationshipRequest.newBuilder().apply {
                    this.userId = relatedUserId
                    isBlocked?.let { this.blocked = it }
                    groupIndex?.let { this.newGroupIndex = it }
                }
            )
            .toResponse()
    }

    suspend fun sendFriendRequest(
        recipientId: Long,
        content: String
    ): Response<Long> = turmsClient.driver
        .send(
            CreateFriendRequestRequest.newBuilder().apply {
                this.recipientId = recipientId
                this.content = content
            }
        ).toResponse {
            it.getLongOrThrow()
        }

    suspend fun replyFriendRequest(
        requestId: Long,
        responseAction: ResponseAction,
        reason: String? = null
    ): Response<Unit> = turmsClient.driver
        .send(
            UpdateFriendRequestRequest.newBuilder().apply {
                this.requestId = requestId
                this.responseAction = responseAction
                reason?.let { this.reason = it }
            }
        )
        .toResponse()

    suspend fun queryFriendRequests(
        areSentByMe: Boolean,
        lastUpdatedDate: Date? = null
    ): Response<UserFriendRequestsWithVersion?> = turmsClient.driver
        .send(
            QueryFriendRequestsRequest.newBuilder().apply {
                this.areSentByMe = areSentByMe
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).toResponse {
            if (it.hasUserFriendRequestsWithVersion()) it.userFriendRequestsWithVersion else null
        }

    suspend fun createRelationshipGroup(name: String): Response<Int> = turmsClient.driver
        .send(
            CreateRelationshipGroupRequest.newBuilder().apply {
                this.name = name
            }
        ).toResponse {
            it.getLongOrThrow().toInt()
        }

    suspend fun deleteRelationshipGroups(
        groupIndex: Int,
        targetGroupIndex: Int? = null
    ): Response<Unit> = turmsClient.driver
        .send(
            DeleteRelationshipGroupRequest.newBuilder().apply {
                this.groupIndex = groupIndex
                targetGroupIndex?.let { this.targetGroupIndex = it }
            }
        )
        .toResponse()

    suspend fun updateRelationshipGroup(
        groupIndex: Int,
        newName: String
    ): Response<Unit> = turmsClient.driver
        .send(
            UpdateRelationshipGroupRequest.newBuilder().apply {
                this.groupIndex = groupIndex
                this.newName = newName
            }
        )
        .toResponse()

    suspend fun queryRelationshipGroups(lastUpdatedDate: Date? = null): Response<UserRelationshipGroupsWithVersion?> =
        turmsClient.driver
            .send(
                QueryRelationshipGroupsRequest.newBuilder().apply {
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                }
            ).toResponse {
                if (it.hasUserRelationshipGroupsWithVersion()) it.userRelationshipGroupsWithVersion else null
            }

    suspend fun moveRelatedUserToGroup(
        relatedUserId: Long,
        groupIndex: Int
    ): Response<Unit> = turmsClient.driver
        .send(
            UpdateRelationshipRequest.newBuilder().apply {
                this.userId = relatedUserId
                this.newGroupIndex = groupIndex
            }
        )
        .toResponse()

    /**
     * updateLocation() in UserService is different from sendMessage() with records of location in MessageService
     * updateLocation() in UserService sends the location of user to the server only.
     * sendMessage() with records of location sends user's location to both server and its recipients.
     */
    suspend fun updateLocation(
        latitude: Float,
        longitude: Float,
        details: Map<String, String>? = null
    ): Response<Unit> = turmsClient.driver
        .send(
            UpdateUserLocationRequest.newBuilder().apply {
                this.latitude = latitude
                this.longitude = longitude
                details?.let { putAllDetails(it) }
            }
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
