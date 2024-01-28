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
import im.turms.client.model.proto.model.conversation.GroupConversation
import im.turms.client.model.proto.model.conversation.PrivateConversation
import im.turms.client.model.proto.request.conversation.QueryConversationsRequest
import im.turms.client.model.proto.request.conversation.UpdateConversationRequest
import im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest
import im.turms.client.util.Validator
import java.util.*

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
     *   and pass these user IDs as [targetIds] to get all private conversations.
     * * The returned [PrivateConversation] does NOT contain messages.
     *   To find messages in conversations, you can use methods like
     *   [MessageService.queryMessages] and [MessageService.queryMessagesWithTotal].
     *
     * @param targetIds the target user IDs.
     * If empty, an empty list of conversations is returned.
     * @return a list of private conversations.
     * Note that the list only contains conversations in which the logged-in user is a participant.
     * If the logged-in user is not a participant of a specified conversation,
     * these conversations will be filtered out on the server, and no error will be thrown.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryPrivateConversations(targetIds: Set<Long>?): Response<List<PrivateConversation>> =
        if (Validator.areAllFalsy(targetIds)) {
            Response.emptyList()
        } else {
            turmsClient.driver
                .send(
                    QueryConversationsRequest.newBuilder().apply {
                        addAllTargetIds(targetIds)
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
     * @param targetId the target user ID.
     * @param readDate the read date.
     * If null, the current time is used.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updatePrivateConversationReadDate(targetId: Long, readDate: Date? = null): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateConversationRequest.newBuilder().apply {
                    this.targetId = targetId
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
    suspend fun updateGroupConversationReadDate(groupId: Long, readDate: Date? = null): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateConversationRequest.newBuilder().apply {
                    this.groupId = groupId
                    this.readDate = readDate?.time ?: Date().time
                },
            )
            .toResponse()

    /**
     * Update the typing status of the target private conversation.
     *
     * Authorization:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default), throws [ResponseException] with the code [ResponseStatusCode.UPDATING_TYPING_STATUS_IS_DISABLED].
     * * If the logged-in user is not a friend of [targetId], throws [ResponseException] with the code [ResponseStatusCode.NOT_FRIEND_TO_SEND_TYPING_STATUS].
     *
     * Notifications:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default),
     *   the server will send a typing status update notification to the participant actively.
     *
     * @param targetId the target user ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updatePrivateConversationTypingStatus(targetId: Long): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateTypingStatusRequest.newBuilder().apply {
                    toId = targetId
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
