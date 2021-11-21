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
import im.turms.client.constant.TurmsStatusCode
import im.turms.common.constant.GroupMemberRole
import im.turms.common.model.bo.group.Group
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.Timeout
import java.util.*
import java.util.concurrent.ExecutionException
import kotlin.properties.Delegates

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class GroupServiceET {

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
        assertNotNull(groupId)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun addGroupJoinQuestion_shouldReturnQuestionId() = runBlocking {
        groupJoinQuestionId =
            client.groupService.addGroupJoinQuestion(groupId, "question", listOf("answer1", "answer2"), 10)
        assertNotNull(groupJoinQuestionId)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun createJoinRequest_shouldReturnJoinRequestId() = runBlocking {
        groupJoinRequestId = client.groupService.createJoinRequest(groupId, "content")
        assertNotNull(groupJoinRequestId)
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    @Timeout(5)
    fun addGroupMember_shouldSucceed() = runBlocking {
        val result = client.groupService.addGroupMember(groupId, GROUP_MEMBER_ID, "name", GroupMemberRole.MEMBER)
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
        assertNotNull(groupInvitationId)
    }

    /** Update */

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateGroup_shouldSucceed() = runBlocking {
        val result = client.groupService.updateGroup(groupId, "name", "intro", "announcement", 10)
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LAST - 1)
    @Timeout(5)
    fun transferOwnership_shouldSucceed() = runBlocking {
        val result = client.groupService.transferOwnership(groupId, GROUP_SUCCESSOR, true)
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun muteGroup_shouldSucceed() = runBlocking {
        val result = client.groupService.muteGroup(groupId, Date())
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun unmuteGroup_shouldSucceed() = runBlocking {
        val result = client.groupService.unmuteGroup(groupId)
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateGroupJoinQuestion_shouldSucceed() = runBlocking {
        val result = client.groupService.updateGroupJoinQuestion(groupId, "new-question", listOf("answer"))
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun updateGroupMemberInfo_shouldSucceed() = runBlocking {
        val result =
            client.groupService.updateGroupMemberInfo(groupId, GROUP_MEMBER_ID, "myname")
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
        )
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun unmuteGroupMember_shouldSucceed() = runBlocking {
        val result = client.groupService.unmuteGroupMember(groupId, GROUP_MEMBER_ID)
        assertNotNull(result)
    }

    // Query
    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryGroup_shouldReturnGroupWithVersion() = runBlocking {
        val groupWithVersion = client.groupService.queryGroup(groupId)
        assertEquals(groupId, groupWithVersion!!.group!!.id)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryJoinedGroupIds_shouldEqualNewGroupId() = runBlocking {
        val joinedGroupIdsWithVersion = client.groupService.queryJoinedGroupIds()
        assertTrue(joinedGroupIdsWithVersion!!.valuesList.contains(groupId))
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryJoinedGroupInfos_shouldEqualNewGroupId() = runBlocking {
        val groupWithVersion = client.groupService.queryJoinedGroupInfos()
        val groups: List<Group> = groupWithVersion!!.groupsList
        val groupIds: MutableSet<Long?> = HashSet(groups.size)
        for (group in groups) {
            groupIds.add(group.id)
        }
        assertTrue(groupIds.contains(groupId))
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryBlockedUserIds_shouldEqualBlockedUserId() = runBlocking {
        val blockedUserIdsWithVersion = client.groupService.queryBlockedUserIds(groupId)
        assertEquals(GROUP_BLOCKED_USER_ID, blockedUserIdsWithVersion!!.getValues(0))
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryBlockedUserInfos_shouldEqualBlockedUserId() = runBlocking {
        val usersInfosWithVersion = client.groupService.queryBlockedUserInfos(groupId)
        assertEquals(GROUP_BLOCKED_USER_ID, usersInfosWithVersion!!.getUserInfos(0).id)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryInvitations_shouldEqualNewInvitationId() = runBlocking {
        val groupInvitationsWithVersion = client.groupService.queryInvitations(groupId)
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
        assertEquals(
            groupJoinRequestId,
            groupJoinRequestsWithVersion!!.getGroupJoinRequests(0).id
        )
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryGroupJoinQuestionsRequest_shouldEqualNewGroupQuestionId() = runBlocking {
        val groupJoinQuestionsWithVersion = client.groupService.queryGroupJoinQuestionsRequest(groupId, true)
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
        assertEquals(GROUP_MEMBER_ID, groupMembersWithVersion!!.getGroupMembers(1).userId)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun queryGroupMembersByMemberIds_shouldEqualNewMemberId() = runBlocking {
        val groupMembersWithVersion =
            client.groupService.queryGroupMembersByMemberIds(groupId, setOf(GROUP_MEMBER_ID), true)
        assertEquals(GROUP_MEMBER_ID, groupMembersWithVersion!!.getGroupMembers(0).userId)
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    @Timeout(5)
    fun answerGroupQuestions_shouldReturnAnswerResult() = runBlocking {
        val map = mapOf(Pair(groupJoinQuestionId, "answer"))
        try {
            val answerResult = client.groupService
                .answerGroupQuestions(map)
            val isCorrect = answerResult.questionIdsList.contains(groupJoinQuestionId)
            assertTrue(isCorrect)
        } catch (e: ExecutionException) {
            assertTrue(
                ExceptionUtil.isTurmsStatusCode(
                    e,
                    TurmsStatusCode.MEMBER_CANNOT_ANSWER_GROUP_QUESTION
                )
            )
        }
    }

    /** Delete */

    @Test
    @Order(ORDER_LOW_PRIORITY)
    @Timeout(5)
    fun removeGroupMember_shouldSucceed() = runBlocking {
        val result = client.groupService.removeGroupMember(groupId, GROUP_MEMBER_ID)
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    @Timeout(5)
    fun deleteGroupJoinQuestion_shouldSucceed() = runBlocking {
        val result = client.groupService.deleteGroupJoinQuestion(groupJoinQuestionId)
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    fun unblockUser_shouldSucceed() = runBlocking {
        val result = client.groupService.unblockUser(groupId, GROUP_BLOCKED_USER_ID)
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
            ExceptionUtil.isTurmsStatusCode(e, TurmsStatusCode.RECALLING_GROUP_INVITATION_IS_DISABLED)
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
            ExceptionUtil.isTurmsStatusCode(e, TurmsStatusCode.RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED)
        }
        assertTrue(isSuccessful)
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    @Timeout(5)
    fun quitGroup_shouldSucceed() = runBlocking {
        val result = client.groupService.quitGroup(groupId, quitAfterTransfer = false)
        assertNotNull(result)
    }

    @Test
    @Order(ORDER_LAST)
    @Timeout(5)
    fun deleteGroup_shouldSucceed() = runBlocking {
        val readyToDeleteGroupId = client.groupService.createGroup("readyToDelete")
        val result = client.groupService.deleteGroup(readyToDeleteGroupId)
        assertNotNull(result)
    }

    companion object {
        private const val GROUP_MEMBER_ID: Long = 3
        private const val GROUP_INVITATION_INVITEE: Long = 4
        private const val GROUP_SUCCESSOR: Long = 1
        private const val GROUP_BLOCKED_USER_ID: Long = 5
        private lateinit var client: TurmsClient
        private var groupId by Delegates.notNull<Long>()
        private var groupJoinQuestionId by Delegates.notNull<Long>()
        private var groupJoinRequestId by Delegates.notNull<Long>()
        private var groupInvitationId by Delegates.notNull<Long>()

        @BeforeAll
        @Timeout(5)
        @JvmStatic
        fun setup() = runBlocking {
            client = TurmsClient(HOST)
            client.userService.login(1L, "123")
        }

        @AfterAll
        @Timeout(5)
        @JvmStatic
        fun tearDown() = runBlocking {
            client.driver.disconnect()
        }
    }
}