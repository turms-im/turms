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
import helper.Constants.ORDER_HIGH_PRIORITY
import helper.Constants.ORDER_LOW_PRIORITY
import helper.Constants.ORDER_MIDDLE_PRIORITY
import im.turms.client.TurmsClient
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.Timeout

@TestMethodOrder(OrderAnnotation::class)
internal class StorageServiceET {

    /** Create */

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun uploadProfilePicture_shouldReturnUrl() = runBlocking {
        val url = turmsClient.storageService.uploadProfilePicture(PROFILE_PICTURE)
        assertNotNull(url)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun uploadGroupProfilePicture_shouldReturnUrl() = runBlocking {
        val url = turmsClient.storageService.uploadGroupProfilePicture(PROFILE_PICTURE, GROUP_ID)
        assertNotNull(url)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun uploadAttachment_shouldReturnUrl() = runBlocking {
        messageId = turmsClient.messageService.sendMessage(false, targetId = 2L, text = "I've attached a picture")
        val url = turmsClient.storageService.uploadAttachment(messageId, ATTACHMENT)
        assertNotNull(url)
    }

    /** Query */

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryProfilePicture_shouldEqualUploadedPicture() = runBlocking {
        val bytes = turmsClient.storageService.queryProfilePicture(USER_ID)
        assertArrayEquals(PROFILE_PICTURE, bytes)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryGroupProfilePicture_shouldEqualUploadedPicture() = runBlocking {
        val bytes = turmsClient.storageService.queryGroupProfilePicture(GROUP_ID)
        assertArrayEquals(PROFILE_PICTURE, bytes)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryAttachment_shouldEqualUploadedAttachment() = runBlocking {
        val bytes = turmsClient.storageService.queryAttachment(messageId)
        assertArrayEquals(PROFILE_PICTURE, bytes)
    }

    /** Delete */

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun deleteProfile_shouldSucceed() = runBlocking {
        val result = turmsClient.storageService.deleteProfile()
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun deleteGroupProfile_shouldSucceed() = runBlocking {
        val result = turmsClient.storageService.deleteGroupProfile(GROUP_ID)
        assertNotNull(result)
    }

    companion object {
        private lateinit var turmsClient: TurmsClient
        private const val USER_ID = 1L
        private const val GROUP_ID: Long = 1
        private val PROFILE_PICTURE = byteArrayOf(1, 2, 3, 4, 9, 8, 7, 6, 5)
        private val ATTACHMENT = PROFILE_PICTURE
        private var messageId: Long = 0

        @BeforeAll
        @Timeout(5)
        @JvmStatic
        fun setup() = runBlocking {
            turmsClient = TurmsClient(HOST)
            turmsClient.userService.login(USER_ID, "123")
        }

        @AfterAll
        @Timeout(5)
        @JvmStatic
        fun tearDown() = runBlocking {
            turmsClient.driver.disconnect()
        }
    }
}