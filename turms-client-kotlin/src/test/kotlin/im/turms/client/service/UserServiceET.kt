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
import helper.Constants.ORDER_LAST
import helper.Constants.ORDER_LOWEST_PRIORITY
import helper.Constants.ORDER_LOW_PRIORITY
import helper.Constants.ORDER_MIDDLE_PRIORITY
import helper.ExceptionUtil.isResponseStatusCode
import im.turms.client.TurmsClient
import im.turms.client.model.ResponseStatusCode
import im.turms.client.model.proto.constant.ResponseAction
import im.turms.client.model.proto.constant.UserStatus
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.Timeout
import java.util.concurrent.ExecutionException
import kotlin.properties.Delegates

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation::class)
internal class UserServiceET {

    @BeforeAll
    fun setup() {
        turmsClient = TurmsClient(HOST)
    }

    @AfterAll
    @Timeout(5)
    fun tearDown() = runBlocking {
        turmsClient.driver.disconnect()
        return@runBlocking
    }

    /** Constructor */

    @Test
    @Order(ORDER_FIRST)
    fun constructor_shouldReturnNotNullGroupServiceInstance() {
        assertNotNull(turmsClient.userService)
    }

    /** Login */

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    @Timeout(5)
    fun login_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.login(1, "123")
            .data
        assertNotNull(result)
    }

    /** Logout */

    @Test
    @Order(ORDER_LAST)
    @Timeout(5)
    fun logout_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.logout()
            .data
        assertNotNull(result)
    }

    /** Create */

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun createRelationship_shouldSucceed() = runBlocking {
        try {
            val result = turmsClient.userService.createRelationship(10L, true)
                .data
            assertNotNull(result)
        } catch (e: ExecutionException) {
            assertTrue(isResponseStatusCode(e, ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP))
        }
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun createFriendRelationship_shouldSucceed() = runBlocking {
        try {
            val result = turmsClient.userService.createFriendRelationship(10L)
                .data
            assertNotNull(result)
        } catch (e: ExecutionException) {
            assertTrue(isResponseStatusCode(e, ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP))
        }
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun createBlockedUserRelationship_shouldSucceed() = runBlocking {
        try {
            val result = turmsClient.userService.createBlockedUserRelationship(10L)
                .data
            assertNotNull(result)
        } catch (e: ExecutionException) {
            assertTrue(isResponseStatusCode(e, ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP))
        }
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun sendFriendRequest_shouldReturnFriendRequestId() = runBlocking {
        try {
            val friendRequestId = turmsClient.userService.sendFriendRequest(11L, "content")
                .data
            assertNotNull(friendRequestId)
        } catch (e: ExecutionException) {
            assertTrue(isResponseStatusCode(e, ResponseStatusCode.CREATE_EXISTING_FRIEND_REQUEST))
        }
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun createRelationshipGroup_shouldReturnRelationshipGroupIndex() = runBlocking {
        relationshipGroupIndex = turmsClient.userService.createRelationshipGroup("newGroup")
            .data
        assertNotNull(relationshipGroupIndex)
    }

    /** Update */

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateUserOnlineStatus_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.updateOnlineStatus(userStatus)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updatePassword_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.updatePassword("123")
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateProfile_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.updateProfile("123", "123")
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateRelationship_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.updateRelationship(10L, null, 1)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun replyFriendRequest_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.replyFriendRequest(10L, ResponseAction.ACCEPT, "reason")
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateRelationshipGroup_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.updateRelationshipGroup(relationshipGroupIndex, "newGroupName")
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun moveRelatedUserToGroup_shouldSucceed() = runBlocking {
        var result = turmsClient.userService.moveRelatedUserToGroup(2L, 1)
            .data
        assertNotNull(result)
        result = turmsClient.userService.moveRelatedUserToGroup(2L, 0)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateLocation_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.updateLocation(2f, 2f)
            .data
        assertNotNull(result)
    }

    /** Query */

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryUserProfiles_shouldReturnUserInfos() = runBlocking {
        val result = turmsClient.userService.queryUserProfiles(setOf(1))
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryNearbyUsers_shouldReturnNearbyUsers() = runBlocking {
        val nearbyUsers = turmsClient.userService.queryNearbyUsers(1f, 1f)
            .data
        assertNotNull(nearbyUsers)
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryUserOnlineStatusesRequest_shouldUsersOnlineStatuses() = runBlocking {
        val set = setOf(1L)
        val result = turmsClient.userService.queryOnlineStatusesRequest(set)
            .data
        assertEquals(userStatus, result[0].userStatus)
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryRelationships_shouldReturnUserRelationshipsWithVersion() = runBlocking {
        val set = setOf(2L)
        val result = turmsClient.userService.queryRelationships(set)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryRelatedUserIds_shouldReturnRelatedUserIds() = runBlocking {
        val result = turmsClient.userService.queryRelatedUserIds()
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryFriends_shouldReturnFriendRelationships() = runBlocking {
        val result = turmsClient.userService.queryFriends()
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryBlockedUsers_shouldReturnRelationshipsWithBlockedUsers() = runBlocking {
        val result = turmsClient.userService.queryBlockedUsers()
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryFriendRequests_shouldReturnFriendRequests() = runBlocking {
        val result = turmsClient.userService.queryFriendRequests(true)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun queryRelationshipGroups_shouldReturnRelationshipGroups() = runBlocking {
        val result = turmsClient.userService.queryRelationshipGroups()
            .data
        assertNotNull(result)
    }

    /** Delete */

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    @Timeout(5)
    fun deleteRelationship_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.deleteRelationship(10L)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    @Timeout(5)
    fun deleteRelationshipGroups_shouldSucceed() = runBlocking {
        val result = turmsClient.userService.deleteRelationshipGroups(relationshipGroupIndex)
            .data
        assertNotNull(result)
    }

    companion object {
        private val userStatus = UserStatus.AWAY
        private lateinit var turmsClient: TurmsClient
        private var relationshipGroupIndex by Delegates.notNull<Int>()
    }
}
