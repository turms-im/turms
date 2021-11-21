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
import im.turms.client.constant.TurmsStatusCode
import im.turms.client.exception.TurmsBusinessException
import im.turms.client.model.SessionCloseInfo
import im.turms.client.model.User
import im.turms.client.model.UserInfoWithVersion
import im.turms.client.model.UserLocation
import im.turms.client.util.SystemUtil
import im.turms.common.constant.DeviceType
import im.turms.common.constant.ProfileAccessStrategy
import im.turms.common.constant.ResponseAction
import im.turms.common.constant.UserStatus
import im.turms.common.constant.statuscode.SessionCloseStatus
import im.turms.common.model.bo.common.Int64ValuesWithVersion
import im.turms.common.model.bo.user.NearbyUser
import im.turms.common.model.bo.user.UserFriendRequestsWithVersion
import im.turms.common.model.bo.user.UserRelationshipGroupsWithVersion
import im.turms.common.model.bo.user.UserRelationshipsWithVersion
import im.turms.common.model.bo.user.UserStatusDetail
import im.turms.common.model.dto.notification.TurmsNotification
import im.turms.common.model.dto.request.user.CreateSessionRequest
import im.turms.common.model.dto.request.user.DeleteSessionRequest
import im.turms.common.model.dto.request.user.QueryNearbyUsersRequest
import im.turms.common.model.dto.request.user.QueryUserOnlineStatusesRequest
import im.turms.common.model.dto.request.user.QueryUserProfileRequest
import im.turms.common.model.dto.request.user.UpdateUserLocationRequest
import im.turms.common.model.dto.request.user.UpdateUserOnlineStatusRequest
import im.turms.common.model.dto.request.user.UpdateUserRequest
import im.turms.common.model.dto.request.user.relationship.CreateFriendRequestRequest
import im.turms.common.model.dto.request.user.relationship.CreateRelationshipGroupRequest
import im.turms.common.model.dto.request.user.relationship.CreateRelationshipRequest
import im.turms.common.model.dto.request.user.relationship.DeleteRelationshipGroupRequest
import im.turms.common.model.dto.request.user.relationship.DeleteRelationshipRequest
import im.turms.common.model.dto.request.user.relationship.QueryFriendRequestsRequest
import im.turms.common.model.dto.request.user.relationship.QueryRelatedUserIdsRequest
import im.turms.common.model.dto.request.user.relationship.QueryRelationshipGroupsRequest
import im.turms.common.model.dto.request.user.relationship.QueryRelationshipsRequest
import im.turms.common.model.dto.request.user.relationship.UpdateFriendRequestRequest
import im.turms.common.model.dto.request.user.relationship.UpdateRelationshipGroupRequest
import im.turms.common.model.dto.request.user.relationship.UpdateRelationshipRequest
import im.turms.common.util.Validator
import java.util.*

/**
 * @author James Chen
 */
class UserService(private val turmsClient: TurmsClient) {

    var userInfo: User? = null
    private var storePassword = false

    private val onOnlineListeners: MutableList<(() -> Unit)> = LinkedList()
    private val onOfflineListeners: MutableList<((SessionCloseInfo) -> Unit)> = LinkedList()

    val isLoggedIn: Boolean
        get() = userInfo?.onlineStatus != null
                && userInfo?.onlineStatus != UserStatus.UNRECOGNIZED
                && userInfo?.onlineStatus != UserStatus.OFFLINE

    init {
        turmsClient.driver.addOnDisconnectedListener { changeToOffline(SessionCloseInfo(SessionCloseStatus.CONNECTION_CLOSED.code)) }
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

    fun addOnOnlineListener(listener: () -> Unit) = onOnlineListeners.add(listener)

    fun addOnOfflineListener(listener: (SessionCloseInfo) -> Unit) = onOfflineListeners.add(listener)

    fun removeOnOnlineListener(listener: () -> Unit) = onOnlineListeners.remove(listener)

    fun removeOnOfflineListener(listener: (SessionCloseInfo) -> Unit) = onOfflineListeners.remove(listener)

    suspend fun login(
        userId: Long,
        password: String? = null,
        deviceType: DeviceType = SystemUtil.deviceType,
        onlineStatus: UserStatus = UserStatus.AVAILABLE,
        location: UserLocation? = null,
        storePassword: Boolean = false
    ) {
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
        turmsClient.driver.send(CreateSessionRequest.newBuilder().apply {
            this.version = 1
            this.userId = userId
            password?.let { this.password = it }
            deviceType.let { this.deviceType = it }
            onlineStatus.let { this.userStatus = it }
            location?.let {
                this.location = im.turms.common.model.bo.user.UserLocation.newBuilder().apply {
                    this.longitude = it.longitude
                    this.latitude = it.latitude
                }.build()
            }
        })
        changeToOnline()
        this.storePassword = storePassword
        userInfo = user
    }

    suspend fun logout(disconnect: Boolean = true) {
        if (disconnect) {
            turmsClient.driver.disconnect()
        } else {
            turmsClient.driver.send(DeleteSessionRequest.newBuilder())
        }
        changeToOffline(SessionCloseInfo(SessionCloseStatus.DISCONNECTED_BY_CLIENT.code))
    }

    suspend fun updateOnlineStatus(onlineStatus: UserStatus) =
        if (onlineStatus == UserStatus.OFFLINE) {
            throw TurmsBusinessException(
                TurmsStatusCode.ILLEGAL_ARGUMENT,
                "The online status must not be $onlineStatus"
            )
        } else turmsClient.driver
            .send(
                UpdateUserOnlineStatusRequest.newBuilder().apply {
                    this.userStatus = onlineStatus
                }
            ).run {
                userInfo?.onlineStatus = onlineStatus
            }

    suspend fun disconnectOnlineDevices(@NotEmpty deviceTypes: Set<DeviceType>) =
        if (deviceTypes.isEmpty()) {
            throw TurmsBusinessException(TurmsStatusCode.ILLEGAL_ARGUMENT, "deviceTypes must not be null or empty")
        } else turmsClient.driver
            .send(
                UpdateUserOnlineStatusRequest.newBuilder().apply {
                    this.userStatus = UserStatus.OFFLINE
                    this.addAllDeviceTypes(deviceTypes)
                }
            ).run { }

    suspend fun updatePassword(password: String) = turmsClient.driver
        .send(
            UpdateUserRequest.newBuilder().apply {
                this.password = password
            }
        ).run {
            if (storePassword) {
                userInfo?.password = password
            }
        }

    suspend fun updateProfile(
        name: String? = null,
        intro: String? = null,
        profileAccessStrategy: ProfileAccessStrategy? = null
    ) {
        if (!Validator.areAllNull(name, intro, profileAccessStrategy)) {
            return turmsClient.driver
                .send(
                    UpdateUserRequest.newBuilder().apply {
                        name?.let { this.name = it }
                        intro?.let { this.intro = it }
                        profileAccessStrategy?.let { this.profileAccessStrategy = it }
                    }
                ).run {}
        }
    }

    suspend fun queryUserProfile(
        userId: Long,
        lastUpdatedDate: Date? = null
    ): UserInfoWithVersion? = turmsClient.driver
        .send(
            QueryUserProfileRequest.newBuilder().apply {
                this.userId = userId
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).let { UserInfoWithVersion.from(it) }

    suspend fun queryNearbyUsers(
        latitude: Float,
        longitude: Float,
        distance: Int? = null,
        maxNumber: Int? = null,
        withCoordinates: Boolean? = null,
        withDistance: Boolean? = null,
        withInfo: Boolean? = null,
    ): List<NearbyUser> = turmsClient.driver
        .send(
            QueryNearbyUsersRequest.newBuilder().apply {
                this.latitude = latitude
                this.longitude = longitude
                distance?.let { this.distance = it }
                maxNumber?.let { this.maxNumber = it }
                withCoordinates?.let { this.withCoordinates = it }
                withDistance?.let { this.withDistance = it }
                withInfo?.let { this.withInfo = it }
            }
        ).data.nearbyUsers.nearbyUsersList

    suspend fun queryOnlineStatusesRequest(@NotEmpty userIds: Set<Long>): List<UserStatusDetail> =
        if (userIds.isEmpty()) {
            throw TurmsBusinessException(TurmsStatusCode.ILLEGAL_ARGUMENT, "userIds must not be null or empty")
        } else turmsClient.driver
            .send(
                QueryUserOnlineStatusesRequest.newBuilder().apply {
                    addAllUserIds(userIds)
                }
            ).data.usersOnlineStatuses.userStatusesList

    // Relationship
    suspend fun queryRelationships(
        relatedUserIds: Set<Long>? = null,
        isBlocked: Boolean? = null,
        groupIndex: Int? = null,
        lastUpdatedDate: Date? = null
    ): UserRelationshipsWithVersion? = turmsClient.driver
        .send(
            QueryRelationshipsRequest.newBuilder().apply {
                relatedUserIds?.let { addAllUserIds(it) }
                isBlocked?.let { this.blocked = it }
                groupIndex?.let { this.groupIndex = it }
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).let {
            val data: TurmsNotification.Data = it.data
            if (data.hasUserRelationshipsWithVersion()) data.userRelationshipsWithVersion else null
        }

    suspend fun queryRelatedUserIds(
        isBlocked: Boolean? = null,
        groupIndex: Int? = null,
        lastUpdatedDate: Date? = null
    ): Int64ValuesWithVersion? = turmsClient.driver
        .send(
            QueryRelatedUserIdsRequest.newBuilder().apply {
                isBlocked?.let { this.blocked = it }
                groupIndex?.let { this.groupIndex = it }
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).let {
            val data: TurmsNotification.Data = it.data
            if (data.hasIdsWithVersion()) data.idsWithVersion else null
        }

    suspend fun queryFriends(
        groupIndex: Int? = null,
        lastUpdatedDate: Date? = null
    ): UserRelationshipsWithVersion? =
        queryRelationships(isBlocked = false, groupIndex = groupIndex, lastUpdatedDate = lastUpdatedDate)

    suspend fun queryBlockedUsers(
        groupIndex: Int? = null,
        lastUpdatedDate: Date? = null
    ): UserRelationshipsWithVersion? =
        queryRelationships(isBlocked = true, groupIndex = groupIndex, lastUpdatedDate = lastUpdatedDate)

    suspend fun createRelationship(
        userId: Long,
        isBlocked: Boolean,
        groupIndex: Int? = null
    ) = turmsClient.driver
        .send(
            CreateRelationshipRequest.newBuilder().apply {
                this.userId = userId
                this.blocked = isBlocked
                groupIndex?.let { this.groupIndex = it }
            }
        ).run {}

    suspend fun createFriendRelationship(
        userId: Long,
        groupIndex: Int? = null
    ) = createRelationship(userId, false, groupIndex)

    suspend fun createBlockedUserRelationship(
        userId: Long,
        groupIndex: Int? = null
    ) = createRelationship(userId, true, groupIndex)

    suspend fun deleteRelationship(
        relatedUserId: Long,
        deleteGroupIndex: Int? = null,
        targetGroupIndex: Int? = null
    ) = turmsClient.driver
        .send(
            DeleteRelationshipRequest.newBuilder().apply {
                this.userId = relatedUserId
                deleteGroupIndex?.let { this.groupIndex = it }
                targetGroupIndex?.let { this.targetGroupIndex = it }
            }
        ).run {}

    suspend fun updateRelationship(
        relatedUserId: Long,
        isBlocked: Boolean? = null,
        groupIndex: Int? = null
    ) {
        if (!Validator.areAllFalsy(isBlocked, groupIndex)) {
            return turmsClient.driver
                .send(
                    UpdateRelationshipRequest.newBuilder().apply {
                        this.userId = relatedUserId
                        isBlocked?.let { this.blocked = it }
                        groupIndex?.let { this.newGroupIndex = it }
                    }
                ).run {}
        }
    }

    suspend fun sendFriendRequest(
        recipientId: Long,
        content: String
    ): Long = turmsClient.driver
        .send(
            CreateFriendRequestRequest.newBuilder().apply {
                this.recipientId = recipientId
                this.content = content
            }
        ).data.ids.getValues(0)

    suspend fun replyFriendRequest(
        requestId: Long,
        responseAction: ResponseAction,
        reason: String? = null
    ) = turmsClient.driver
        .send(
            UpdateFriendRequestRequest.newBuilder().apply {
                this.requestId = requestId
                this.responseAction = responseAction
                reason?.let { this.reason = it }
            }
        ).run {}

    suspend fun queryFriendRequests(
        areSentByMe: Boolean,
        lastUpdatedDate: Date? = null
    ): UserFriendRequestsWithVersion? = turmsClient.driver
        .send(
            QueryFriendRequestsRequest.newBuilder().apply {
                this.areSentByMe = areSentByMe
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).let {
            val data: TurmsNotification.Data = it.data
            if (data.hasUserFriendRequestsWithVersion()) data.userFriendRequestsWithVersion else null
        }

    suspend fun createRelationshipGroup(name: String): Int = turmsClient.driver
        .send(
            CreateRelationshipGroupRequest.newBuilder().apply {
                this.name = name
            }
        ).data.ids.getValues(0).toInt()

    suspend fun deleteRelationshipGroups(
        groupIndex: Int,
        targetGroupIndex: Int? = null
    ) = turmsClient.driver
        .send(
            DeleteRelationshipGroupRequest.newBuilder().apply {
                this.groupIndex = groupIndex
                targetGroupIndex?.let { this.targetGroupIndex = it }
            }
        ).run {}

    suspend fun updateRelationshipGroup(
        groupIndex: Int,
        newName: String
    ) = turmsClient.driver
        .send(
            UpdateRelationshipGroupRequest.newBuilder().apply {
                this.groupIndex = groupIndex
                this.newName = newName
            }
        ).run {}

    suspend fun queryRelationshipGroups(lastUpdatedDate: Date? = null): UserRelationshipGroupsWithVersion? =
        turmsClient.driver
            .send(
                QueryRelationshipGroupsRequest.newBuilder().apply {
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                }
            ).let {
                val data: TurmsNotification.Data = it.data
                if (data.hasUserRelationshipGroupsWithVersion()) data.userRelationshipGroupsWithVersion else null
            }

    suspend fun moveRelatedUserToGroup(
        relatedUserId: Long,
        groupIndex: Int
    ) = turmsClient.driver
        .send(
            UpdateRelationshipRequest.newBuilder().apply {
                this.userId = relatedUserId
                this.newGroupIndex = groupIndex
            }
        ).run {}

    /**
     * updateLocation() in UserService is different from sendMessage() with records of location in MessageService
     * updateLocation() in UserService sends the location of user to the server only.
     * sendMessage() with records of location sends user's location to both server and its recipients.
     */
    suspend fun updateLocation(
        latitude: Float,
        longitude: Float,
        name: String? = null,
        address: String? = null
    ) = turmsClient.driver
        .send(
            UpdateUserLocationRequest.newBuilder().apply {
                this.latitude = latitude
                this.longitude = longitude
                name?.let { this.name = it }
                address?.let { this.address = it }
            }
        ).run {}

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