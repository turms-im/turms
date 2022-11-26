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

import helper.Constants.HOST
import helper.Constants.ORDER_FIRST
import helper.Constants.ORDER_LOW_PRIORITY
import helper.Constants.ORDER_MIDDLE_PRIORITY
import im.turms.client.TurmsClient
import im.turms.client.model.proto.model.conversation.GroupConversation
import im.turms.client.model.proto.model.conversation.PrivateConversation
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.Timeout

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation::class)
internal class ConversationServiceET {

    @BeforeAll
    @Timeout(5)
    fun setup() = runBlocking {
        client = TurmsClient(HOST)
        client.userService.login(USER_ID, "123")
        return@runBlocking
    }

    @AfterAll
    @Timeout(5)
    fun tearDown() = runBlocking {
        client.driver.disconnect()
        return@runBlocking
    }

    /** Constructor */

    @Test
    @Order(ORDER_FIRST)
    fun constructor_shouldReturnNotNullConversationServiceInstance() {
        assertNotNull(client.conversationService)
    }

    /** Update */

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updatePrivateConversationReadDate_shouldSucceed() = runBlocking {
        client.conversationService.updatePrivateConversationReadDate(RELATED_USER_ID)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateGroupConversationReadDate_shouldSucceed() = runBlocking {
        client.conversationService.updateGroupConversationReadDate(GROUP_ID)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updatePrivateConversationTypingStatus_shouldSucceed() = runBlocking {
        client.conversationService.updatePrivateConversationTypingStatus(RELATED_USER_ID)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateGroupConversationTypingStatus_shouldSucceed() = runBlocking {
        client.conversationService.updateGroupConversationTypingStatus(GROUP_ID)
    }

    /** Query */

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryPrivateConversations_shouldReturnNotEmptyConversations() = runBlocking {
        val targetIds = setOf(RELATED_USER_ID)
        val conversations: List<PrivateConversation> =
            client.conversationService.queryPrivateConversations(targetIds)
                .data
        assertTrue(conversations.isNotEmpty())
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryGroupConversations_shouldReturnNotEmptyConversations() = runBlocking {
        val groupIds = setOf(GROUP_ID)
        val conversations: List<GroupConversation> = client.conversationService.queryGroupConversations(groupIds)
            .data
        assertTrue(conversations.isNotEmpty())
    }

    companion object {
        private const val USER_ID: Long = 1
        private const val RELATED_USER_ID: Long = 2
        private const val GROUP_ID: Long = 1
        private lateinit var client: TurmsClient
    }
}
