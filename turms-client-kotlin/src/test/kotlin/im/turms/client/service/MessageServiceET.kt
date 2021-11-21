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
import helper.Constants.ORDER_HIGHEST_PRIORITY
import helper.Constants.ORDER_HIGH_PRIORITY
import helper.Constants.ORDER_LOW_PRIORITY
import helper.Constants.ORDER_MIDDLE_PRIORITY
import im.turms.client.TurmsClient
import im.turms.client.service.MessageService.Companion.generateAudioRecordByData
import im.turms.client.service.MessageService.Companion.generateAudioRecordByDescription
import im.turms.client.service.MessageService.Companion.generateFileRecordByDate
import im.turms.client.service.MessageService.Companion.generateFileRecordByDescription
import im.turms.client.service.MessageService.Companion.generateImageRecordByData
import im.turms.client.service.MessageService.Companion.generateImageRecordByDescription
import im.turms.client.service.MessageService.Companion.generateLocationRecord
import im.turms.client.service.MessageService.Companion.generateVideoRecordByData
import im.turms.client.service.MessageService.Companion.generateVideoRecordByDescription
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.Timeout
import java.util.*
import kotlin.properties.Delegates

@TestMethodOrder(OrderAnnotation::class)
internal class MessageServiceET {

    /** Constructor */

    @Test
    @Order(ORDER_FIRST)
    @Timeout(5)
    fun constructor_shouldReturnNotNullMessageServiceInstance() {
        assertNotNull(senderClient.messageService)
        assertNotNull(recipientClient.messageService)
    }

    /** Create */

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    @Timeout(5)
    fun sendPrivateMessage_shouldReturnMessageId() = runBlocking {
        privateMessageId = senderClient.messageService.sendMessage(false, RECIPIENT_ID, Date(), "hello")
        assertNotNull(privateMessageId)
    }

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    @Timeout(5)
    fun sendGroupMessage_shouldReturnMessageId() = runBlocking {
        groupMessageId = senderClient.messageService.sendMessage(true, TARGET_GROUP_ID, Date(), "hello")
        assertNotNull(groupMessageId)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun forwardPrivateMessage_shouldReturnForwardedMessageId() = runBlocking {
        val messageId: Long = senderClient.messageService.forwardMessage(privateMessageId, false, RECIPIENT_ID)
        assertNotNull(messageId)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun forwardGroupMessage_shouldReturnForwardedMessageId() = runBlocking {
        val messageId: Long = senderClient.messageService.forwardMessage(groupMessageId, true, TARGET_GROUP_ID)
        assertNotNull(messageId)
    }

    /** Update */

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun recallMessage_shouldSucceed() = runBlocking {
        val result = senderClient.messageService.recallMessage(groupMessageId)
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateSentMessage_shouldSucceed() = runBlocking {
        val result = senderClient.messageService.updateSentMessage(privateMessageId, "I have modified the message")
        assertNotNull(result)
    }

    /** Query */

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryMessages_shouldReturnNotEmptyMessages() = runBlocking {
        val messages =
            recipientClient.messageService.queryMessages(areGroupMessages = false, senderId = SENDER_ID, size = 10)
        assertTrue(messages.isNotEmpty())
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryMessagesWithTotal_shouldReturnNotEmptyMessagesWithTotal() = runBlocking {
        val messagesWithTotals = senderClient.messageService.queryMessagesWithTotal(size = 1)
        assertTrue(messagesWithTotals.isNotEmpty())
    }

    /** Util */

    @Test
    fun generateLocationRecord() {
        val data = generateLocationRecord(1.0f, 1.0f, "name", "address")
        assertNotNull(data)
    }

    @Test
    fun generateAudioRecordByDescription() {
        val data = generateAudioRecordByDescription("https://abc.com")
        assertNotNull(data)
    }

    @Test
    fun generateAudioRecordByData() {
        val source = byteArrayOf(0, 1, 2)
        val data = generateAudioRecordByData(source)
        assertNotNull(data)
    }

    @Test
    fun generateVideoRecordByDescription() {
        val data = generateVideoRecordByDescription("https://abc.com")
        assertNotNull(data)
    }

    @Test
    fun generateVideoRecordByData() {
        val source = byteArrayOf(0, 1, 2)
        val data = generateVideoRecordByData(source)
        assertNotNull(data)
    }

    @Test
    fun generateImageRecordByData() {
        val source = byteArrayOf(0, 1, 2)
        val data = generateImageRecordByData(source)
        assertNotNull(data)
    }

    @Test
    fun generateFileRecordByDate() {
        val source = byteArrayOf(0, 1, 2)
        val data = generateFileRecordByDate(source)
        assertNotNull(data)
    }

    @Test
    fun generateImageRecordByDescription() {
        val data = generateImageRecordByDescription("https://abc.com")
        assertNotNull(data)
    }

    @Test
    fun generateFileRecordByDescription() {
        val data = generateFileRecordByDescription("https://abc.com")
        assertNotNull(data)
    }

    companion object {
        private const val SENDER_ID = 1L
        private const val RECIPIENT_ID = 2L
        private const val GROUP_MEMBER_ID = 3L
        private const val TARGET_GROUP_ID = 1L
        private lateinit var senderClient: TurmsClient
        private lateinit var recipientClient: TurmsClient
        private lateinit var groupMemberClient: TurmsClient
        private var privateMessageId by Delegates.notNull<Long>()
        private var groupMessageId by Delegates.notNull<Long>()

        @BeforeAll
        @Timeout(10)
        @JvmStatic
        fun setup() = runBlocking {
            senderClient = TurmsClient(HOST)
            recipientClient = TurmsClient(HOST)
            groupMemberClient = TurmsClient(HOST)
            senderClient.userService.login(SENDER_ID, "123")
            recipientClient.userService.login(RECIPIENT_ID, "123")
            groupMemberClient.userService.login(GROUP_MEMBER_ID, "123")
        }

        @AfterAll
        @Timeout(5)
        @JvmStatic
        fun tearDown() = runBlocking {
            senderClient.driver.disconnect()
            recipientClient.driver.disconnect()
            groupMemberClient.driver.disconnect()
        }
    }
}