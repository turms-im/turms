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
import im.turms.client.exception.ResponseException
import im.turms.client.extension.toResponse
import im.turms.client.model.Response
import im.turms.client.model.ResponseStatusCode
import im.turms.client.model.proto.model.common.Value
import im.turms.client.model.proto.model.conversation.ConversationSettings
import im.turms.client.model.proto.model.conversation.GroupConversation
import im.turms.client.model.proto.model.conversation.PrivateConversation
import im.turms.client.model.proto.request.conversation.DeleteConversationSettingsRequest
import im.turms.client.model.proto.request.conversation.QueryConversationSettingsRequest
import im.turms.client.model.proto.request.conversation.QueryConversationsRequest
import im.turms.client.model.proto.request.conversation.UpdateConversationRequest
import im.turms.client.model.proto.request.conversation.UpdateConversationSettingsRequest
import im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest
import im.turms.client.util.Validator
import java.util.Date

/**
 * @author James Chen
 */
class ConversationService(private val turmsClient: TurmsClient) {
    /**
     * Find private conversations between the logged-in user and another user.
     *
     * Common Scenarios:
     * * If you want to find all private conversations between
     *   the current logged-in user and other users,
     *   you can call methods like [UserService.queryRelatedUserIds],
     *   [UserService.queryFriends] to get all user IDs first,
     *   and pass these user IDs as [userIds] to get all private conversations.
     * * The returned [PrivateConversation] does NOT contain messages.
     *   To find messages in conversations, you can use methods like
     *   [MessageService.queryMessages] and [MessageService.queryMessagesWithTotal].
     *
     * @param userIds the target user IDs.
     * If empty, an empty list of conversations is returned.
     * @return a list of private conversations.
     * Note that the list only contains conversations in which the logged-in user is a participant.
     * If the logged-in user is not a participant of a specified conversation,
     * these conversations will be filtered out on the server, and no error will be thrown.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryPrivateConversations(userIds: Set<Long>?): Response<List<PrivateConversation>> =
        if (Validator.areAllFalsy(userIds)) {
            Response.emptyList()
        } else {
            turmsClient.driver
                .send(
                    QueryConversationsRequest.newBuilder().apply {
                        addAllUserIds(userIds)
                    },
                ).toResponse {
                    it.conversations.privateConversationsList
                }
        }

    /**
     * Find group conversations in which the logged-in user is a member.
     *
     * Common Scenarios:
     * * If you want to find all group conversations between
     *   the current logged-in user and groups in which the logged-in user is a member,
     *   you can call methods like [GroupService.queryJoinedGroupIds],
     *   [GroupService.queryJoinedGroupInfos] to get all group IDs first,
     *   and pass these group IDs as [groupIds] to get all group conversations.
     * * [GroupConversation] does NOT contain messages.
     *   To find messages in conversations, you can use methods like
     *   [MessageService.queryMessages] and [MessageService.queryMessagesWithTotal].
     *
     * @param groupIds the target group IDs.
     * If empty, an empty list of conversations is returned.
     * @return a list of group conversations.
     * Note that the list only contains conversations in which the logged-in user is a participant.
     * If the logged-in user is not a participant of a specified conversation,
     * these conversations will be filtered out on the server, and no error will be thrown.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryGroupConversations(groupIds: Set<Long>?): Response<List<GroupConversation>> =
        if (Validator.areAllFalsy(groupIds)) {
            Response.emptyList()
        } else {
            turmsClient.driver
                .send(
                    QueryConversationsRequest.newBuilder().apply {
                        addAllGroupIds(groupIds)
                    },
                ).toResponse {
                    it.conversations.groupConversationsList
                }
        }

    /**
     * Update the read date of the target private conversation.
     *
     * Common Scenarios:
     * * To find the read date of a conversation actively (if no notification is received from the server),
     *   you can call [queryPrivateConversations].
     *
     * Authorization:
     * * If the server property `turms.service.conversation.read-receipt.enabled`
     *   is false (true by default), throws [ResponseException] with the code [ResponseStatusCode.UPDATING_READ_DATE_IS_DISABLED].
     *
     * Notifications:
     * * If the server property `turms.service.notification.private-conversation-read-date-updated.notify-contact`
     *   is true (false by default),
     *   the server will send a read date update notification to the participant actively.
     * * If the server property `turms.service.notification.private-conversation-read-date-updated.notify-requester-other-online-sessions`
     *   is true (true by default),
     *   the server will send a read date update notification to all other online sessions of the logged-in user actively.
     *
     * @param userId the target user ID.
     * @param readDate the read date.
     * If null, the current time is used.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updatePrivateConversationReadDate(
        userId: Long,
        readDate: Date? = null,
    ): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateConversationRequest.newBuilder().apply {
                    this.userId = userId
                    this.readDate = readDate?.time ?: Date().time
                },
            )
            .toResponse()

    /**
     * Update the read date of the target group conversation.
     *
     * Common Scenarios:
     * * To find the read date of a conversation actively (if no notification is received from the server),
     *   you can call [queryGroupConversations].
     *
     * Authorization:
     * * If the server property `turms.service.conversation.read-receipt.enabled`
     *   is false (true by default), throws [ResponseException] with the code [ResponseStatusCode.UPDATING_READ_DATE_IS_DISABLED].
     * * If the target group doesn't exist, throws [ResponseException] with the code [ResponseStatusCode.UPDATING_READ_DATE_OF_NONEXISTENT_GROUP_CONVERSATION].
     * * If the target group has disabled read receipts, throws [ResponseException] with the code [ResponseStatusCode.UPDATING_READ_DATE_IS_DISABLED_BY_GROUP].
     * * If the logged-in user is not a member of the target group, throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_MEMBER_TO_UPDATE_READ_DATE_OF_GROUP_CONVERSATION].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-conversation-read-date-updated.notify-other-group-members`
     *   is true (false by default),
     *   the server will send a read date update notification to all participants actively.
     * * If the server property `turms.service.notification.group-conversation-read-date-updated.notify-requester-other-online-sessions`
     *   is true (true by default),
     *   the server will send a read date update notification to all other online sessions of the logged-in user actively.
     *
     * @param groupId the target group ID.
     * @param readDate the read date.
     * If null, the current time is used.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateGroupConversationReadDate(
        groupId: Long,
        readDate: Date? = null,
    ): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateConversationRequest.newBuilder().apply {
                    this.groupId = groupId
                    this.readDate = readDate?.time ?: Date().time
                },
            )
            .toResponse()

    /**
     * Upsert private conversation settings, such as "sticky", "new message alert", etc.
     * Note that only the settings specified in `turms.service.conversation.settings.allowed-settings` can be upserted.
     *
     * Notifications:
     * * If the server property `turms.service.notification.private-conversation-setting-updated.notify-requester-other-online-sessions` is true (true by default),
     *   the server will send a conversation settings updated notification to all other online sessions of the logged-in user actively.
     *
     * @param userId the target user ID.
     * @param settings the private conversation settings to upsert.
     * @throws ResponseException if an error occurs.
     * * If trying to update any existing immutable setting, throws [ResponseException] with the code [ResponseStatusCode.ILLEGAL_ARGUMENT]
     * * If trying to upsert an unknown setting and the server property `turms.service.conversation.settings.ignore-unknown-settings-on-upsert` is
     *   false (false by default), throws [ResponseException] with the code [ResponseStatusCode.ILLEGAL_ARGUMENT].
     */
    suspend fun upsertPrivateConversationSettings(
        userId: Long,
        settings: Map<String, Value>,
    ): Response<Unit> {
        if (settings.isEmpty()) {
            return Response.unitValue()
        }
        return turmsClient.driver
            .send(
                UpdateConversationSettingsRequest.newBuilder().apply {
                    this.userId = userId
                    putAllSettings(settings)
                },
            ).toResponse()
    }

    /**
     * Upsert group conversation settings, such as "sticky", "new message alert", etc.
     * Note that only the settings specified in `turms.service.conversation.settings.allowed-settings` can be upserted.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-conversation-setting-updated.notify-requester-other-online-sessions` is true (true by default),
     *   the server will send a conversation settings updated notification to all other online sessions of the logged-in user actively.
     *
     * @param groupId the target group ID.
     * @param settings the group conversation settings to upsert.
     * @throws ResponseException if an error occurs.
     * * If trying to update any existing immutable setting, throws [ResponseException] with the code [ResponseStatusCode.ILLEGAL_ARGUMENT]
     * * If trying to upsert an unknown setting and the server property `turms.service.conversation.settings.ignore-unknown-settings-on-upsert` is
     *   false (false by default), throws [ResponseException] with the code [ResponseStatusCode.ILLEGAL_ARGUMENT].
     */
    suspend fun upsertGroupConversationSettings(
        groupId: Long,
        settings: Map<String, Value>,
    ): Response<Unit> {
        if (settings.isEmpty()) {
            return Response.unitValue()
        }
        return turmsClient.driver
            .send(
                UpdateConversationSettingsRequest.newBuilder().apply {
                    this.groupId = groupId
                    putAllSettings(settings)
                },
            ).toResponse()
    }

    /**
     * Delete conversation settings.
     *
     * Notifications:
     * * If the server property `turms.service.notification.private-conversation-setting-deleted.notify-requester-other-online-sessions` is true (true by default),
     *   and a private conversation setting is deleted, the server will send a user settings deleted notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-conversation-setting-deleted.notify-requester-other-online-sessions` is true (true by default),
     *   and a group conversation setting is deleted, the server will send a group settings deleted notification to all other online sessions of the logged-in user actively.
     *
     * @param userIds the target user IDs. If both [userIds] and [groupIds] are null, all deletable conversation settings will be deleted.
     * @param groupIds the target group IDs. If both [userIds] and [groupIds] are null, all deletable conversation settings will be deleted.
     * @param names the names of the conversation settings to delete. If null, all deletable conversation settings will be deleted.
     * @throws ResponseException if an error occurs.
     * * If trying to delete any non-deletable setting, throws [ResponseException] with the code [ResponseStatusCode.ILLEGAL_ARGUMENT].
     */
    suspend fun deleteConversationSettings(
        userIds: Set<Long>? = null,
        groupIds: Set<Long>? = null,
        names: Set<String>? = null,
    ): Response<Unit> {
        return turmsClient.driver
            .send(
                DeleteConversationSettingsRequest.newBuilder().apply {
                    userIds?.let { addAllUserIds(it) }
                    groupIds?.let { addAllGroupIds(it) }
                    names?.let { addAllNames(it) }
                },
            ).toResponse()
    }

    /**
     * Find conversation settings.
     *
     * @param userIds the target user IDs. If both [userIds] and [groupIds] are null, the settings of all private and group conversations will be returned.
     * @param groupIds the target group IDs. If both [userIds] and [groupIds] are null, the settings of all private and group conversations will be returned.
     * @param names the target setting names.
     * @param lastUpdatedDate the last updated date of conversation settings stored locally.
     * The server will only return conversation settings if a setting has been updated after [lastUpdatedDate].
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryConversationSettings(
        userIds: Set<Long>? = null,
        groupIds: Set<Long>? = null,
        names: Set<String>? = null,
        lastUpdatedDate: Date? = null,
    ): Response<List<ConversationSettings>> {
        return turmsClient.driver
            .send(
                QueryConversationSettingsRequest.newBuilder().apply {
                    userIds?.let { addAllUserIds(it) }
                    groupIds?.let { addAllGroupIds(it) }
                    names?.let { addAllNames(it) }
                    lastUpdatedDate?.let { this.lastUpdatedDateStart = it.time }
                },
            ).toResponse {
                it.conversationSettingsList.conversationSettingsListList
            }
    }

    /**
     * Update the typing status of the target private conversation.
     *
     * Authorization:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default), throws [ResponseException] with the code [ResponseStatusCode.UPDATING_TYPING_STATUS_IS_DISABLED].
     * * If the logged-in user is not a friend of [userId], throws [ResponseException] with the code [ResponseStatusCode.NOT_FRIEND_TO_SEND_TYPING_STATUS].
     *
     * Notifications:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default),
     *   the server will send a typing status update notification to the participant actively.
     *
     * @param userId the target user ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updatePrivateConversationTypingStatus(userId: Long): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateTypingStatusRequest.newBuilder().apply {
                    toId = userId
                    isGroupMessage = false
                },
            )
            .toResponse()

    /**
     * Update the typing status of the target group conversation.
     *
     * Authorization:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default), throws [ResponseException] with the code [ResponseStatusCode.UPDATING_TYPING_STATUS_IS_DISABLED].
     * * If the logged-in user is not a member of the target group, throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_MEMBER_TO_SEND_TYPING_STATUS].
     *
     * Notifications:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default),
     *   the server will send a typing status update notification to all participants actively.
     *
     * @param groupId the target group ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateGroupConversationTypingStatus(groupId: Long): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateTypingStatusRequest.newBuilder().apply {
                    toId = groupId
                    isGroupMessage = true
                },
            )
            .toResponse()
}
