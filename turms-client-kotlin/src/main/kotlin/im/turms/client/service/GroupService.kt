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
import im.turms.client.annotation.NotEmpty
import im.turms.client.constant.TurmsStatusCode
import im.turms.client.exception.TurmsBusinessException
import im.turms.client.model.GroupWithVersion
import im.turms.common.constant.GroupMemberRole
import im.turms.common.model.bo.common.Int64ValuesWithVersion
import im.turms.common.model.bo.group.GroupInvitationsWithVersion
import im.turms.common.model.bo.group.GroupJoinQuestionsAnswerResult
import im.turms.common.model.bo.group.GroupJoinQuestionsWithVersion
import im.turms.common.model.bo.group.GroupJoinRequestsWithVersion
import im.turms.common.model.bo.group.GroupMembersWithVersion
import im.turms.common.model.bo.group.GroupsWithVersion
import im.turms.common.model.bo.user.UsersInfosWithVersion
import im.turms.common.model.dto.notification.TurmsNotification
import im.turms.common.model.dto.request.group.CreateGroupRequest
import im.turms.common.model.dto.request.group.DeleteGroupRequest
import im.turms.common.model.dto.request.group.QueryGroupRequest
import im.turms.common.model.dto.request.group.QueryJoinedGroupIdsRequest
import im.turms.common.model.dto.request.group.QueryJoinedGroupInfosRequest
import im.turms.common.model.dto.request.group.UpdateGroupRequest
import im.turms.common.model.dto.request.group.blocklist.CreateGroupBlockedUserRequest
import im.turms.common.model.dto.request.group.blocklist.DeleteGroupBlockedUserRequest
import im.turms.common.model.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
import im.turms.common.model.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
import im.turms.common.model.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
import im.turms.common.model.dto.request.group.enrollment.CreateGroupInvitationRequest
import im.turms.common.model.dto.request.group.enrollment.CreateGroupJoinQuestionRequest
import im.turms.common.model.dto.request.group.enrollment.CreateGroupJoinRequestRequest
import im.turms.common.model.dto.request.group.enrollment.DeleteGroupInvitationRequest
import im.turms.common.model.dto.request.group.enrollment.DeleteGroupJoinQuestionRequest
import im.turms.common.model.dto.request.group.enrollment.DeleteGroupJoinRequestRequest
import im.turms.common.model.dto.request.group.enrollment.QueryGroupInvitationsRequest
import im.turms.common.model.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest
import im.turms.common.model.dto.request.group.enrollment.QueryGroupJoinRequestsRequest
import im.turms.common.model.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
import im.turms.common.model.dto.request.group.member.CreateGroupMemberRequest
import im.turms.common.model.dto.request.group.member.DeleteGroupMemberRequest
import im.turms.common.model.dto.request.group.member.QueryGroupMembersRequest
import im.turms.common.model.dto.request.group.member.UpdateGroupMemberRequest
import im.turms.common.util.Validator
import java.util.*

/**
 * @author James Chen
 */
class GroupService(private val turmsClient: TurmsClient) {

    suspend fun createGroup(
        name: String,
        intro: String? = null,
        announcement: String? = null,
        minimumScore: Int? = null,
        muteEndDate: Date? = null,
        groupTypeId: Long? = null
    ): Long = turmsClient.driver
        .send(
            CreateGroupRequest.newBuilder().apply {
                this.name = name
                intro?.let { this.intro = it }
                announcement?.let { this.announcement = it }
                minimumScore?.let { this.minimumScore = it }
                muteEndDate?.let { this.muteEndDate = it.time }
                groupTypeId?.let { this.groupTypeId = it }
            }
        ).data.ids.getValues(0)

    suspend fun deleteGroup(groupId: Long) =
        turmsClient.driver
            .send(DeleteGroupRequest.newBuilder().apply {
                this.groupId = groupId
            }).run {}

    suspend fun updateGroup(
        groupId: Long,
        groupName: String? = null,
        intro: String? = null,
        announcement: String? = null,
        minimumScore: Int? = null,
        groupTypeId: Long? = null,
        muteEndDate: Date? = null,
        successorId: Long? = null,
        quitAfterTransfer: Boolean? = null
    ) {
        if (!Validator.areAllFalsy(
                groupName, intro, announcement, minimumScore, groupTypeId,
                muteEndDate, successorId
            )
        ) turmsClient.driver
            .send(UpdateGroupRequest.newBuilder().apply {
                this.groupId = groupId
                groupName?.let { this.groupName = it }
                intro?.let { this.intro = it }
                announcement?.let { this.announcement = it }
                muteEndDate?.let { this.muteEndDate = it.time }
                minimumScore?.let { this.minimumScore = it }
                groupTypeId?.let { this.groupTypeId = it }
                successorId?.let { this.successorId = it }
                quitAfterTransfer?.let { this.quitAfterTransfer = it }
            }).run {}
    }

    suspend fun transferOwnership(groupId: Long, successorId: Long, quitAfterTransfer: Boolean = false) =
        updateGroup(groupId, null, null, null, null, null, null, successorId, quitAfterTransfer)

    suspend fun muteGroup(groupId: Long, muteEndDate: Date) =
        updateGroup(groupId, null, null, null, null, null, muteEndDate, null, null)

    suspend fun unmuteGroup(groupId: Long) = muteGroup(groupId, Date(0))

    suspend fun queryGroup(groupId: Long, lastUpdatedDate: Date? = null): GroupWithVersion? =
        turmsClient.driver
            .send(
                QueryGroupRequest.newBuilder().apply {
                    this.groupId = groupId
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                }
            ).let {
                GroupWithVersion.from(it)
            }

    suspend fun queryJoinedGroupIds(lastUpdatedDate: Date? = null): Int64ValuesWithVersion? =
        turmsClient.driver
            .send(
                QueryJoinedGroupIdsRequest.newBuilder().apply {
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                })
            .let {
                val data: TurmsNotification.Data = it.data
                if (data.hasIdsWithVersion()) data.idsWithVersion else null
            }

    suspend fun queryJoinedGroupInfos(lastUpdatedDate: Date? = null): GroupsWithVersion? =
        turmsClient.driver
            .send(
                QueryJoinedGroupInfosRequest.newBuilder().apply {
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                }
            )
            .let {
                val data: TurmsNotification.Data = it.data
                if (data.hasGroupsWithVersion()) data.groupsWithVersion else null
            }

    suspend fun addGroupJoinQuestion(
        groupId: Long,
        question: String,
        @NotEmpty answers: List<String>,
        score: Int
    ): Long = if (answers.isEmpty()) {
        throw TurmsBusinessException(TurmsStatusCode.ILLEGAL_ARGUMENT, "answers must not be null or empty")
    } else turmsClient.driver
        .send(
            CreateGroupJoinQuestionRequest.newBuilder().apply {
                this.groupId = groupId
                this.question = question
                addAllAnswers(answers)
                this.score = score
            }
        )
        .data.ids.getValues(0)

    suspend fun deleteGroupJoinQuestion(questionId: Long) = turmsClient.driver
        .send(
            DeleteGroupJoinQuestionRequest.newBuilder().apply {
                this.questionId = questionId
            }).run {}

    suspend fun updateGroupJoinQuestion(
        questionId: Long,
        question: String? = null,
        answers: List<String>? = null,
        score: Int? = null
    ) {
        if (!Validator.areAllNull(question, answers, score)) {
            return turmsClient.driver.send(
                UpdateGroupJoinQuestionRequest.newBuilder().apply {
                    this.questionId = questionId
                    question?.let { this.question = it }
                    answers?.let { this.addAllAnswers(it) }
                    score?.let { this.score = it }
                }
            ).run {}
        }
    }

    // Group Blocklist
    suspend fun blockUser(groupId: Long, userId: Long) = turmsClient.driver
        .send(
            CreateGroupBlockedUserRequest.newBuilder().apply {
                this.userId = userId
                this.groupId = groupId
            }
        ).run {}

    suspend fun unblockUser(groupId: Long, userId: Long) = turmsClient.driver
        .send(
            DeleteGroupBlockedUserRequest.newBuilder().apply {
                this.groupId = groupId
                this.userId = userId
            }
        ).run {}

    suspend fun queryBlockedUserIds(
        groupId: Long,
        lastUpdatedDate: Date? = null
    ): Int64ValuesWithVersion? = turmsClient.driver
        .send(
            QueryGroupBlockedUserIdsRequest.newBuilder().apply {
                this.groupId = groupId
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        )
        .let {
            val data: TurmsNotification.Data = it.data
            if (data.hasIdsWithVersion()) data.idsWithVersion else null
        }

    suspend fun queryBlockedUserInfos(
        groupId: Long,
        lastUpdatedDate: Date? = null
    ): UsersInfosWithVersion? = turmsClient.driver
        .send(
            QueryGroupBlockedUserInfosRequest.newBuilder().apply {
                this.groupId = groupId
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).let {
            val data: TurmsNotification.Data = it.data
            if (data.hasUsersInfosWithVersion()) data.usersInfosWithVersion else null
        }

    // Group Enrollment
    suspend fun createInvitation(
        groupId: Long,
        inviteeId: Long,
        content: String
    ): Long = turmsClient.driver
        .send(
            CreateGroupInvitationRequest.newBuilder().apply {
                this.groupId = groupId
                this.inviteeId = inviteeId
                this.content = content
            }
        ).data.ids.getValues(0)

    suspend fun deleteInvitation(invitationId: Long) = turmsClient.driver
        .send(
            DeleteGroupInvitationRequest.newBuilder().apply {
                this.invitationId = invitationId
            }
        ).run {}

    suspend fun queryInvitations(groupId: Long, lastUpdatedDate: Date? = null): GroupInvitationsWithVersion? =
        turmsClient.driver
            .send(
                QueryGroupInvitationsRequest.newBuilder().apply {
                    this.groupId = groupId
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                }
            ).let {
                val data: TurmsNotification.Data = it.data
                if (data.hasGroupInvitationsWithVersion()) data.groupInvitationsWithVersion else null
            }

    suspend fun queryInvitations(areSentByMe: Boolean, lastUpdatedDate: Date? = null): GroupInvitationsWithVersion? =
        turmsClient.driver
            .send(
                QueryGroupInvitationsRequest.newBuilder().apply {
                    this.areSentByMe = areSentByMe
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                }
            ).let {
                val data: TurmsNotification.Data = it.data
                if (data.hasGroupInvitationsWithVersion()) data.groupInvitationsWithVersion else null
            }

    suspend fun createJoinRequest(groupId: Long, content: String): Long = turmsClient.driver
        .send(
            CreateGroupJoinRequestRequest.newBuilder().apply {
                this.groupId = groupId
                this.content = content
            }
        ).data.ids.getValues(0)

    suspend fun deleteJoinRequest(requestId: Long) = turmsClient.driver
        .send(
            DeleteGroupJoinRequestRequest.newBuilder().apply {
                this.requestId = requestId
            }
        ).run {}

    suspend fun queryJoinRequests(
        groupId: Long, lastUpdatedDate: Date? = null
    ): GroupJoinRequestsWithVersion? = turmsClient.driver
        .send(
            QueryGroupJoinRequestsRequest.newBuilder().apply {
                this.groupId = groupId
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).let {
            val data: TurmsNotification.Data = it.data
            if (data.hasGroupJoinRequestsWithVersion()) data.groupJoinRequestsWithVersion else null
        }

    suspend fun querySentJoinRequests(lastUpdatedDate: Date? = null): GroupJoinRequestsWithVersion? =
        turmsClient.driver
            .send(
                QueryGroupJoinRequestsRequest.newBuilder().apply {
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                }
            ).let {
                val data: TurmsNotification.Data = it.data
                if (data.hasGroupJoinRequestsWithVersion()) data.groupJoinRequestsWithVersion else null
            }

    /**
     * Note: Only the owner and managers have the right to fetch questions with answers
     */
    suspend fun queryGroupJoinQuestionsRequest(
        groupId: Long,
        withAnswers: Boolean,
        lastUpdatedDate: Date? = null
    ): GroupJoinQuestionsWithVersion? = turmsClient.driver
        .send(
            QueryGroupJoinQuestionsRequest.newBuilder().apply {
                this.groupId = groupId
                this.withAnswers = withAnswers
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).let {
            val data: TurmsNotification.Data = it.data
            if (data.hasGroupJoinQuestionsWithVersion()) data.groupJoinQuestionsWithVersion else null
        }

    suspend fun answerGroupQuestions(@NotEmpty questionIdAndAnswerMap: Map<Long, String>): GroupJoinQuestionsAnswerResult =
        if (questionIdAndAnswerMap.isEmpty()) {
            throw TurmsBusinessException(
                TurmsStatusCode.ILLEGAL_ARGUMENT,
                "questionIdAndAnswerMap must not be null or empty"
            )
        } else turmsClient.driver
            .send(
                CheckGroupJoinQuestionsAnswersRequest.newBuilder().apply {
                    putAllQuestionIdAndAnswer(questionIdAndAnswerMap)
                }
            ).let {
                val data: TurmsNotification.Data = it.data
                if (data.hasGroupJoinQuestionAnswerResult()) {
                    data.groupJoinQuestionAnswerResult
                } else {
                    throw TurmsBusinessException(TurmsStatusCode.INVALID_RESPONSE)
                }
            }

    // Group Member
    suspend fun addGroupMember(
        groupId: Long,
        userId: Long,
        name: String? = null,
        role: GroupMemberRole? = null,
        muteEndDate: Date? = null
    ) = turmsClient.driver
        .send(
            CreateGroupMemberRequest.newBuilder().apply {
                this.groupId = groupId
                this.userId = userId
                name?.let { this.name = it }
                role?.let { this.role = it }
                muteEndDate?.let { this.muteEndDate = it.time }
            }
        ).run {}

    suspend fun quitGroup(
        groupId: Long,
        successorId: Long? = null,
        quitAfterTransfer: Boolean? = null
    ) = turmsClient.driver
        .send(
            DeleteGroupMemberRequest.newBuilder().apply {
                this.groupId = groupId
                memberId = turmsClient.userService.userInfo!!.userId
                successorId?.let { this.successorId = it }
                quitAfterTransfer?.let { this.quitAfterTransfer = it }
            }
        ).run {}

    suspend fun removeGroupMember(groupId: Long, memberId: Long) = turmsClient.driver
        .send(
            DeleteGroupMemberRequest.newBuilder().apply {
                this.groupId = groupId
                this.memberId = memberId
            }
        ).run {}

    suspend fun updateGroupMemberInfo(
        groupId: Long,
        memberId: Long,
        name: String? = null,
        role: GroupMemberRole? = null,
        muteEndDate: Date? = null
    ) {
        if (!Validator.areAllNull(name, role, muteEndDate)) {
            return turmsClient.driver
                .send(
                    UpdateGroupMemberRequest.newBuilder().apply {
                        this.groupId = groupId
                        this.memberId = memberId
                        name?.let { this.name = it }
                        role?.let { this.role = it }
                        muteEndDate?.let { this.muteEndDate = it.time }
                    }
                ).run {}
        }
    }

    suspend fun muteGroupMember(
        groupId: Long,
        memberId: Long,
        muteEndDate: Date
    ) = updateGroupMemberInfo(groupId, memberId, null, null, muteEndDate)

    suspend fun unmuteGroupMember(groupId: Long, memberId: Long) = muteGroupMember(groupId, memberId, Date(0))

    suspend fun queryGroupMembers(
        groupId: Long,
        withStatus: Boolean,
        lastUpdatedDate: Date? = null
    ): GroupMembersWithVersion? = turmsClient.driver
        .send(
            QueryGroupMembersRequest.newBuilder().apply {
                this.groupId = groupId
                this.withStatus = withStatus
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            }
        ).let {
            val data: TurmsNotification.Data = it.data
            if (data.hasGroupMembersWithVersion()) data.groupMembersWithVersion else null
        }

    suspend fun queryGroupMembersByMemberIds(
        groupId: Long,
        @NotEmpty memberIds: Set<Long>,
        withStatus: Boolean
    ): GroupMembersWithVersion? = if (memberIds.isEmpty()) {
        throw TurmsBusinessException(TurmsStatusCode.ILLEGAL_ARGUMENT, "memberIds must not be null or empty")
    } else turmsClient.driver
        .send(
            QueryGroupMembersRequest.newBuilder().apply {
                this.groupId = groupId
                addAllMemberIds(memberIds)
                this.withStatus = withStatus
            }
        ).let {
            val data: TurmsNotification.Data = it.data
            if (data.hasGroupMembersWithVersion()) data.groupMembersWithVersion else null
        }

}