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
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.Timeout

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation::class)
internal class StorageServiceET {

    @BeforeAll
    @Timeout(5)
    fun setup() = runBlocking {
        turmsClient = TurmsClient(HOST)
        turmsClient.userService.login(USER_ID, "123")
        return@runBlocking
    }

    @AfterAll
    @Timeout(5)
    fun tearDown() = runBlocking {
        turmsClient.driver.disconnect()
        return@runBlocking
    }

    /** Create */

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun uploadUserProfilePicture_shouldReturnUploadResult() = runBlocking {
        val result = turmsClient.storageService.uploadUserProfilePicture(PROFILE_PICTURE, MEDIA_TYPE)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun uploadGroupProfilePicture_shouldReturnUploadResult() = runBlocking {
        val result = turmsClient.storageService.uploadGroupProfilePicture(GROUP_ID, PROFILE_PICTURE, MEDIA_TYPE)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun uploadMessageAttachment_shouldReturnUploadResult() = runBlocking {
        val result = turmsClient.storageService.uploadMessageAttachment(ATTACHMENT, MEDIA_TYPE)
        assertNotNull(result)
    }

    /** Query */

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryUserProfilePicture_shouldEqualUploadedPicture() = runBlocking {
        val bytes = turmsClient.storageService.queryUserProfilePicture(USER_ID)
            .data
            .data
        assertArrayEquals(PROFILE_PICTURE, bytes)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryGroupProfilePicture_shouldEqualUploadedPicture() = runBlocking {
        val bytes = turmsClient.storageService.queryGroupProfilePicture(GROUP_ID)
            .data
            .data
        assertArrayEquals(PROFILE_PICTURE, bytes)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryMessageAttachment_shouldEqualUploadedAttachment() = runBlocking {
        val bytes = turmsClient.storageService.queryMessageAttachment(messageId)
            .data
            .data
        assertArrayEquals(PROFILE_PICTURE, bytes)
    }

    /** Delete */

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun deleteUserProfilePicture_shouldSucceed() = runBlocking {
        val result = turmsClient.storageService.deleteUserProfilePicture()
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun deleteGroupProfilePicture_shouldSucceed() = runBlocking {
        val result = turmsClient.storageService.deleteGroupProfilePicture(GROUP_ID)
            .data
        assertNotNull(result)
    }

    companion object {
        private lateinit var turmsClient: TurmsClient
        private const val USER_ID = 1L
        private const val GROUP_ID: Long = 1
        private const val MEDIA_TYPE = "image/png"
        private val PROFILE_PICTURE = byteArrayOf(1, 2, 3, 4, 9, 8, 7, 6, 5)
        private val ATTACHMENT = PROFILE_PICTURE
        private var messageId: Long = 0
    }
}
