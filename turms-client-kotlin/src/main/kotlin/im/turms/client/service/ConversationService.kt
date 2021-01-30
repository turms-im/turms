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

import com.google.protobuf.Int64Value
import im.turms.client.TurmsClient
import im.turms.common.model.bo.conversation.GroupConversation
import im.turms.common.model.bo.conversation.PrivateConversation
import im.turms.common.model.dto.request.conversation.QueryConversationsRequest
import im.turms.common.model.dto.request.conversation.UpdateConversationRequest
import im.turms.common.model.dto.request.conversation.UpdateTypingStatusRequest
import im.turms.common.util.Validator
import java.util.*

/**
 * @author James Chen
 */
class ConversationService(private val turmsClient: TurmsClient) {

    suspend fun queryPrivateConversations(targetIds: Set<Long>?): List<PrivateConversation> =
        if (Validator.areAllFalsy(targetIds)) {
            Collections.emptyList()
        } else turmsClient.driver
            .send(QueryConversationsRequest.newBuilder().apply {
                addAllTargetIds(targetIds)
            }).data.conversations.privateConversationsList

    suspend fun queryGroupConversations(groupIds: Set<Long>?): List<GroupConversation> =
        if (Validator.areAllFalsy(groupIds)) {
            Collections.emptyList()
        } else turmsClient.driver
            .send(QueryConversationsRequest.newBuilder().apply {
                addAllGroupIds(groupIds)
            }).data.conversations.groupConversationsList

    suspend fun updatePrivateConversationReadDate(targetId: Long, readDate: Date? = null) =
        turmsClient.driver
            .send(UpdateConversationRequest.newBuilder().apply {
                this.targetId = Int64Value.of(targetId)
                this.readDate = readDate?.time ?: Date().time
            }).run {}

    suspend fun updateGroupConversationReadDate(groupId: Long, readDate: Date? = null) =
        turmsClient.driver
            .send(UpdateConversationRequest.newBuilder().apply {
                this.groupId = Int64Value.of(groupId)
                this.readDate = readDate?.time ?: Date().time
            }).run {}

    suspend fun updatePrivateConversationTypingStatus(targetId: Long) =
        turmsClient.driver
            .send(UpdateTypingStatusRequest.newBuilder().apply {
                toId = targetId
                isGroupMessage = false
            }).run {}

    suspend fun updateGroupConversationTypingStatus(groupId: Long) =
        turmsClient.driver
            .send(UpdateTypingStatusRequest.newBuilder().apply {
                toId = groupId
                isGroupMessage = true
            }).run {}

}