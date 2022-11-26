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
import im.turms.client.extension.toResponse
import im.turms.client.model.Response
import im.turms.client.model.proto.model.conversation.GroupConversation
import im.turms.client.model.proto.model.conversation.PrivateConversation
import im.turms.client.model.proto.request.conversation.QueryConversationsRequest
import im.turms.client.model.proto.request.conversation.UpdateConversationRequest
import im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest
import im.turms.client.util.Validator
import java.util.Date

/**
 * @author James Chen
 */
class ConversationService(private val turmsClient: TurmsClient) {

    suspend fun queryPrivateConversations(targetIds: Set<Long>?): Response<List<PrivateConversation>> =
        if (Validator.areAllFalsy(targetIds)) {
            Response.emptyList()
        } else {
            turmsClient.driver
                .send(
                    QueryConversationsRequest.newBuilder().apply {
                        addAllTargetIds(targetIds)
                    }
                ).toResponse {
                    it.conversations.privateConversationsList
                }
        }

    suspend fun queryGroupConversations(groupIds: Set<Long>?): Response<List<GroupConversation>> =
        if (Validator.areAllFalsy(groupIds)) {
            Response.emptyList()
        } else {
            turmsClient.driver
                .send(
                    QueryConversationsRequest.newBuilder().apply {
                        addAllGroupIds(groupIds)
                    }
                ).toResponse {
                    it.conversations.groupConversationsList
                }
        }

    suspend fun updatePrivateConversationReadDate(targetId: Long, readDate: Date? = null): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateConversationRequest.newBuilder().apply {
                    this.targetId = targetId
                    this.readDate = readDate?.time ?: Date().time
                }
            )
            .toResponse()

    suspend fun updateGroupConversationReadDate(groupId: Long, readDate: Date? = null): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateConversationRequest.newBuilder().apply {
                    this.groupId = groupId
                    this.readDate = readDate?.time ?: Date().time
                }
            )
            .toResponse()

    suspend fun updatePrivateConversationTypingStatus(targetId: Long): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateTypingStatusRequest.newBuilder().apply {
                    toId = targetId
                    isGroupMessage = false
                }
            )
            .toResponse()

    suspend fun updateGroupConversationTypingStatus(groupId: Long): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateTypingStatusRequest.newBuilder().apply {
                    toId = groupId
                    isGroupMessage = true
                }
            )
            .toResponse()
}
