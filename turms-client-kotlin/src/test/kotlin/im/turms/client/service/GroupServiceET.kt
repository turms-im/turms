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
import helper.ExceptionUtil
import im.turms.client.TurmsClient
import im.turms.client.model.NewGroupJoinQuestion
import im.turms.client.model.ResponseStatusCode
import im.turms.client.model.proto.constant.GroupMemberRole
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.Timeout
import java.util.Date
import java.util.concurrent.ExecutionException
import kotlin.properties.Delegates

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class GroupServiceET {

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
    fun constructor_shouldReturnNotNullGroupServiceInstance() {
        assertNotNull(client.groupService)
    }

    /** Create */

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    @Timeout(5)
    fun createGroup_shouldReturnGroupId() = runBlocking {
        groupId = client.groupService.createGroup("name", "intro", "announcement", 10)
            .data
        assertNotNull(groupId)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun addGroupJoinQuestions_shouldReturnQuestionIds() = runBlocking {
        val questions = listOf(NewGroupJoinQuestion("question", setOf("answer1", "answer2"), 10))
        groupJoinQuestionId = client.groupService.addGroupJoinQuestions(groupId, questions)
            .data[0]
        assertNotNull(groupJoinQuestionId)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun createJoinRequest_shouldReturnJoinRequestId() = runBlocking {
        groupJoinRequestId = client.groupService.createJoinRequest(groupId, "content")
            .data
        assertNotNull(groupJoinRequestId)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun addGroupMembers_shouldSucceed() = runBlocking {
        val result = client.groupService.addGroupMembers(groupId, setOf(GROUP_MEMBER_ID), "name", GroupMemberRole.MEMBER)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun blockUser_shouldSucceed() = runBlocking {
        val result = client.groupService.blockUser(groupId, GROUP_BLOCKED_USER_ID)
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun createInvitation_shouldReturnInvitationId() = runBlocking {
        groupInvitationId = client.groupService.createInvitation(groupId, GROUP_INVITATION_INVITEE, "content")
            .data
        assertNotNull(groupInvitationId)
    }

    /** Update */

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateGroup_shouldSucceed() = runBlocking {
        val result = client.groupService.updateGroup(groupId, "name", "intro", "announcement", 10)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LAST - 1)
    @Timeout(5)
    fun transferOwnership_shouldSucceed() = runBlocking {
        val result = client.groupService.transferOwnership(groupId, GROUP_SUCCESSOR, true)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun muteGroup_shouldSucceed() = runBlocking {
        val result = client.groupService.muteGroup(groupId, Date())
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun unmuteGroup_shouldSucceed() = runBlocking {
        val result = client.groupService.unmuteGroup(groupId)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateGroupJoinQuestion_shouldSucceed() = runBlocking {
        val result = client.groupService.updateGroupJoinQuestion(groupJoinQuestionId, "new-question", listOf("answer"))
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateGroupMemberInfo_shouldSucceed() = runBlocking {
        val result =
            client.groupService.updateGroupMemberInfo(groupId, GROUP_MEMBER_ID, "myname")
                .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun muteGroupMember_shouldSucceed() = runBlocking {
        val result = client.groupService.muteGroupMember(
            groupId,
            GROUP_MEMBER_ID,
            Date(System.currentTimeMillis() + 100000)
        ).data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun unmuteGroupMember_shouldSucceed() = runBlocking {
        val result = client.groupService.unmuteGroupMember(groupId, GROUP_MEMBER_ID)
            .data
        assertNotNull(result)
    }

    // Query
    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryGroups_shouldReturnGroups() = runBlocking {
        val groups = client.groupService.queryGroups(setOf(groupId))
            .data
        assertEquals(groupId, groups[0].id)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryJoinedGroupIds_shouldEqualNewGroupId() = runBlocking {
        val joinedGroupIdsWithVersion = client.groupService.queryJoinedGroupIds()
            .data
        assertTrue(joinedGroupIdsWithVersion!!.longsList.contains(groupId))
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryJoinedGroupInfos_shouldEqualNewGroupId() = runBlocking {
        val groupWithVersion = client.groupService.queryJoinedGroupInfos()
            .data
        val groupIds = groupWithVersion!!.groupsList.map { it.id }.toSet()
        assertTrue(groupIds.contains(groupId))
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryBlockedUserIds_shouldEqualBlockedUserId() = runBlocking {
        val blockedUserIdsWithVersion = client.groupService.queryBlockedUserIds(groupId)
            .data
        assertEquals(GROUP_BLOCKED_USER_ID, blockedUserIdsWithVersion!!.longsList.first())
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryBlockedUserInfos_shouldEqualBlockedUserId() = runBlocking {
        val usersInfosWithVersion = client.groupService.queryBlockedUserInfos(groupId)
            .data
        assertEquals(GROUP_BLOCKED_USER_ID, usersInfosWithVersion!!.getUserInfos(0).id)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryInvitations_shouldEqualNewInvitationId() = runBlocking {
        val groupInvitationsWithVersion = client.groupService.queryInvitations(groupId)
            .data
        assertEquals(
            groupInvitationId,
            groupInvitationsWithVersion!!.getGroupInvitations(0).id
        )
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryJoinRequests_shouldEqualNewJoinRequestId() = runBlocking {
        val groupJoinRequestsWithVersion = client.groupService.queryJoinRequests(groupId)
            .data
        assertEquals(
            groupJoinRequestId,
            groupJoinRequestsWithVersion!!.getGroupJoinRequests(0).id
        )
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryGroupJoinQuestions_shouldEqualNewGroupQuestionId() = runBlocking {
        val groupJoinQuestionsWithVersion = client.groupService.queryGroupJoinQuestions(groupId, true)
            .data
        assertEquals(
            groupJoinQuestionId,
            groupJoinQuestionsWithVersion!!.getGroupJoinQuestions(0).id
        )
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryGroupMembers_shouldEqualNewMemberId() = runBlocking {
        val groupMembersWithVersion = client.groupService.queryGroupMembers(groupId, true)
            .data
        assertEquals(GROUP_MEMBER_ID, groupMembersWithVersion!!.getGroupMembers(1).userId)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryGroupMembersByMemberIds_shouldEqualNewMemberId() = runBlocking {
        val groupMembersWithVersion =
            client.groupService.queryGroupMembersByMemberIds(groupId, setOf(GROUP_MEMBER_ID), true)
                .data
        assertEquals(GROUP_MEMBER_ID, groupMembersWithVersion!!.getGroupMembers(0).userId)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun answerGroupQuestions_shouldReturnAnswerResult() = runBlocking {
        val map = mapOf(Pair(groupJoinQuestionId, "answer"))
        try {
            val answerResult = client.groupService.answerGroupQuestions(map)
                .data
            val isCorrect = answerResult.questionIdsList.contains(groupJoinQuestionId)
            assertTrue(isCorrect)
        } catch (e: ExecutionException) {
            assertTrue(
                ExceptionUtil.isResponseStatusCode(
                    e,
                    ResponseStatusCode.MEMBER_CANNOT_ANSWER_GROUP_QUESTION
                )
            )
        }
    }

    /** Delete */

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun removeGroupMembers_shouldSucceed() = runBlocking {
        val result = client.groupService.removeGroupMembers(groupId, setOf(GROUP_MEMBER_ID))
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    @Timeout(5)
    fun deleteGroupJoinQuestions_shouldSucceed() = runBlocking {
        val result = client.groupService.deleteGroupJoinQuestions(setOf(groupJoinQuestionId))
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    fun unblockUser_shouldSucceed() = runBlocking {
        val result = client.groupService.unblockUser(groupId, GROUP_BLOCKED_USER_ID)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    @Timeout(5)
    fun deleteInvitation_shouldSucceedOrThrowDisabledFunction() = runBlocking {
        val isSuccessful = try {
            client.groupService.deleteInvitation(groupInvitationId)
            true
        } catch (e: ExecutionException) {
            ExceptionUtil.isResponseStatusCode(e, ResponseStatusCode.RECALLING_GROUP_INVITATION_IS_DISABLED)
        }
        assertTrue(isSuccessful)
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    @Timeout(5)
    fun deleteJoinRequest_shouldSucceedOrThrowDisabledFunction() = runBlocking {
        val isSuccessful = try {
            client.groupService.deleteJoinRequest(groupJoinRequestId)
            true
        } catch (e: ExecutionException) {
            ExceptionUtil.isResponseStatusCode(e, ResponseStatusCode.RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED)
        }
        assertTrue(isSuccessful)
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    @Timeout(5)
    fun quitGroup_shouldSucceed() = runBlocking {
        val result = client.groupService.quitGroup(groupId, quitAfterTransfer = false)
            .data
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LAST)
    @Timeout(5)
    fun deleteGroup_shouldSucceed() = runBlocking {
        val readyToDeleteGroupId = client.groupService.createGroup("readyToDelete")
            .data
        val result = client.groupService.deleteGroup(readyToDeleteGroupId)
            .data
        assertNotNull(result)
    }

    companion object {
        private const val USER_ID: Long = 1
        private const val GROUP_MEMBER_ID: Long = 3
        private const val GROUP_INVITATION_INVITEE: Long = 4
        private const val GROUP_SUCCESSOR: Long = 1
        private const val GROUP_BLOCKED_USER_ID: Long = 5
        private lateinit var client: TurmsClient
        private var groupId by Delegates.notNull<Long>()
        private var groupJoinQuestionId by Delegates.notNull<Long>()
        private var groupJoinRequestId by Delegates.notNull<Long>()
        private var groupInvitationId by Delegates.notNull<Long>()
    }
}
