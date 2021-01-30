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
package im.turms.client.driver

import helper.Constants.ORDER_FIRST
import helper.Constants.ORDER_HIGHEST_PRIORITY
import helper.Constants.ORDER_HIGH_PRIORITY
import helper.Constants.ORDER_LOWEST_PRIORITY
import helper.Constants.ORDER_MIDDLE_PRIORITY
import helper.Constants.WS_URL
import im.turms.client.TurmsClient
import im.turms.client.extension.isSuccessful
import im.turms.common.model.dto.request.TurmsRequest
import im.turms.common.model.dto.request.user.QueryUserProfileRequest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class TurmsDriverST {

    @Test
    @Order(ORDER_FIRST)
    fun constructor_shouldReturnNotNullDriverInstance() {
        assertNotNull(driver)
    }

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    @Timeout(5)
    fun connect_shouldSucceed() = runBlocking {
        driver.connect()
        assertTrue(driver.isConnected)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun login_shouldSucceed() = runBlocking {
        client.userService.login(1L, "123")
        assertTrue(client.userService.isLoggedIn)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun sendHeartbeat_shouldSucceed() = runBlocking {
        val result = driver.sendHeartbeat()
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun sendTurmsRequest_shouldSucceed() = runBlocking {
        val profileRequest = QueryUserProfileRequest.newBuilder()
            .setUserId(1)
            .build()
        val builder = TurmsRequest.newBuilder()
            .setQueryUserProfileRequest(profileRequest)
        val result = driver.send(builder)
        assertTrue(result.isSuccessful())
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    @Timeout(5)
    fun disconnect_shouldSucceed() = runBlocking {
        driver.disconnect()
        assertFalse(driver.isConnected)
    }

    companion object {
        private lateinit var client: TurmsClient
        private lateinit var driver: TurmsDriver

        @BeforeAll
        @JvmStatic
        fun setup() {
            client = TurmsClient(WS_URL)
            driver = client.driver
        }

        @AfterAll
        @JvmStatic
        @Timeout(5)
        fun tearDown() = runBlocking {
            driver.disconnect()
        }
    }
}