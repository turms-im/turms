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
import im.turms.client.exception.ResponseException
import im.turms.client.extension.getLongOrThrow
import im.turms.client.extension.toResponse
import im.turms.client.model.NewGroupJoinQuestion
import im.turms.client.model.Response
import im.turms.client.model.ResponseStatusCode
import im.turms.client.model.proto.constant.GroupMemberRole
import im.turms.client.model.proto.constant.ResponseAction
import im.turms.client.model.proto.model.common.LongsWithVersion
import im.turms.client.model.proto.model.group.Group
import im.turms.client.model.proto.model.group.GroupInvitationsWithVersion
import im.turms.client.model.proto.model.group.GroupJoinQuestion
import im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult
import im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion
import im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion
import im.turms.client.model.proto.model.group.GroupMembersWithVersion
import im.turms.client.model.proto.model.group.GroupsWithVersion
import im.turms.client.model.proto.model.user.UserInfosWithVersion
import im.turms.client.model.proto.request.group.CreateGroupRequest
import im.turms.client.model.proto.request.group.DeleteGroupRequest
import im.turms.client.model.proto.request.group.QueryGroupsRequest
import im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest
import im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest
import im.turms.client.model.proto.request.group.UpdateGroupRequest
import im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest
import im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest
import im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
import im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
import im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
import im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest
import im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest
import im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest
import im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest
import im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
import im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest
import im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest
import im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest
import im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest
import im.turms.client.model.proto.request.group.enrollment.UpdateGroupInvitationRequest
import im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest
import im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinRequestRequest
import im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest
import im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest
import im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest
import im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest
import im.turms.client.util.Validator
import java.util.Date

/**
 * @author James Chen
 */
class GroupService(private val turmsClient: TurmsClient) {

    /**
     * Create a new group.
     * The logged-in user will become the group creator and owner.
     *
     * Common Scenarios:
     * * To add new group members, you can use methods like [addGroupMembers].
     *
     * Authorization:
     * * If the groups owned by the logged-in user has exceeded the limit,
     *   throws [ResponseException] with the code [ResponseStatusCode.MAX_OWNED_GROUPS_REACHED].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-created.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a create group notification to all other online sessions of the logged-in user actively.
     *
     * @param name the group name.
     * @param intro the group introduction.
     * @param announcement the group announcement.
     * @param minScore the group minimum score that a non-member user needs to acquire
     * to join the group when answering group questions.
     * @param typeId the group type ID.
     * If null, the default group type configured in turms-service will be used.
     *
     * Authorization:
     * * If the group type ID does not exist,
     *   throws [ResponseException] with the code [ResponseStatusCode.CREATE_GROUP_WITH_NONEXISTENT_GROUP_TYPE].
     * * If the logged-in user does not have the permission to create the group with [typeId],
     *   throws [ResponseException] with the code [ResponseStatusCode.NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE].
     * @return the group ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun createGroup(
        name: String,
        intro: String? = null,
        announcement: String? = null,
        minScore: Int? = null,
        muteEndDate: Date? = null,
        typeId: Long? = null,
    ): Response<Long> = turmsClient.driver
        .send(
            CreateGroupRequest.newBuilder().apply {
                this.name = name
                intro?.let { this.intro = it }
                announcement?.let { this.announcement = it }
                minScore?.let { this.minScore = it }
                muteEndDate?.let { this.muteEndDate = it.time }
                typeId?.let { this.typeId = it }
            },
        ).toResponse {
            it.getLongOrThrow()
        }

    /**
     * Delete the target group.
     *
     * Authorization:
     * * If the logged-in user is not the group owner, or the target group does not exist,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_TO_DELETE_GROUP].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-deleted.notify-requester-other-online-sessions`
     *   is true (true by default),
     *   the server will send a delete group notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-deleted.notify-group-members`
     *   is true (true by default),
     *   the server will send a delete group notification to all group members of the target group.
     *
     * @throws ResponseException if an error occurs.
     */
    suspend fun deleteGroup(groupId: Long): Response<Unit> =
        turmsClient.driver
            .send(
                DeleteGroupRequest.newBuilder().apply {
                    this.groupId = groupId
                },
            )
            .toResponse()

    /**
     * Update the target group.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
     *   the server will send an update group notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send an update group notification to all group members of the target group actively.
     *
     * @param groupId the target group ID to find the group for updating.
     * @param name the new group name.
     * If null, the group name will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group name depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group name,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO]
     *   or [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO]
     *   or [ResponseStatusCode.NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO].
     * @param intro the new group introduction.
     * If null, the group introduction will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group introduction depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group introduction,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO]
     *   or [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO]
     *   or [ResponseStatusCode.NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO].
     * @param announcement the new group announcement.
     * If null, the group announcement will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group announcement depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group announcement,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO]
     *   or [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO]
     *   or [ResponseStatusCode.NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO].
     * @param minScore the new group minimum score that a non-member user needs to acquire
     * to join the group when answering group questions.
     * If null, the group minimum score will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group minimum score depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group minimum score,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO]
     *   or [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO]
     *   or [ResponseStatusCode.NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO].
     * @param typeId the new group type ID.
     * If null, the group type ID will not be changed.
     *
     * Authorization:
     * * If the server property `turms.service.group.allow-group-owner-change-group-type`
     *   is true (false by default), the logged-in user can change the group type.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.UPDATING_GROUP_TYPE_IS_DISABLED].
     * * If the logged-in user is not the group owner,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_TO_UPDATE_GROUP_TYPE].
     * * If the logged-in user is not allowed to use the group type,
     *   throws [ResponseException] with the code [ResponseStatusCode.NO_PERMISSION_TO_UPDATE_GROUP_TO_GROUP_TYPE].
     * * If [typeId] doesn't exist, throws [ResponseException] with the code [ResponseStatusCode.UPDATE_GROUP_TO_NONEXISTENT_GROUP_TYPE].
     * @param muteEndDate the new group mute end date.
     * Before the group mute end date, the group members will not be able
     * to send messages.
     *
     * Authorization:
     * * Only the group owner or group managers can mute or unmute the group.
     *   If the logged-in user is not the owner or manager of the group,
     *   [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER]
     *   will be thrown.
     * @param successorId the new successor ID.
     * If the logged-in user is the owner of the group, they must transfer the group ownership to the [successorId],
     * throws [ResponseException] with the code [ResponseStatusCode.GROUP_OWNER_QUIT_WITHOUT_SPECIFYING_SUCCESSOR] otherwise.
     * And the successor will become the group owner.
     * The successor must already be a member of the group, throws [ResponseException] with the code
     * [ResponseStatusCode.GROUP_SUCCESSOR_NOT_GROUP_MEMBER] otherwise.
     * @param quitAfterTransfer whether to quit the group after transfer the group ownership.
     * If false, the logged-in user will become a normal group member (not the group admin).
     * If null, the value will not be changed.
     *
     * Authorization:
     * * If the logged-in user is not the owner of the group,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_TO_TRANSFER_GROUP].
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateGroup(
        groupId: Long,
        name: String? = null,
        intro: String? = null,
        announcement: String? = null,
        minScore: Int? = null,
        typeId: Long? = null,
        muteEndDate: Date? = null,
        successorId: Long? = null,
        quitAfterTransfer: Boolean? = null,
    ): Response<Unit> = if (Validator.areAllFalsy(
            name,
            intro,
            announcement,
            minScore,
            typeId,
            muteEndDate,
            successorId,
        )
    ) {
        Response.unitValue()
    } else {
        turmsClient.driver
            .send(
                UpdateGroupRequest.newBuilder().apply {
                    this.groupId = groupId
                    name?.let { this.name = it }
                    intro?.let { this.intro = it }
                    announcement?.let { this.announcement = it }
                    muteEndDate?.let { this.muteEndDate = it.time }
                    minScore?.let { this.minScore = it }
                    typeId?.let { this.typeId = it }
                    successorId?.let { this.successorId = it }
                    quitAfterTransfer?.let { this.quitAfterTransfer = it }
                },
            )
            .toResponse()
    }

    /**
     * Transfer the group ownership.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
     *   the server will send a update group notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send a update group notification to all group members of the target group actively.
     *
     * @param groupId the target group ID to find the group for updating.
     * @param successorId the new successor ID.
     * If the logged-in user is the owner of the group, they must transfer the group ownership to the [successorId],
     * throws [ResponseException] with the code [ResponseStatusCode.GROUP_OWNER_QUIT_WITHOUT_SPECIFYING_SUCCESSOR] otherwise.
     * And the successor will become the group owner.
     * The successor must already be a member of the group, throws [ResponseException] with the code
     * [ResponseStatusCode.GROUP_SUCCESSOR_NOT_GROUP_MEMBER] otherwise.
     * @param quitAfterTransfer whether to quit the group after transfer the group ownership.
     * If false, the logged-in user will become a normal group member (not the group admin).
     * If null, the value will not be changed.
     * Authorization: If the logged-in user is not the owner of the group,
     * throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_TO_TRANSFER_GROUP].
     * @throws ResponseException if an error occurs.
     */
    suspend fun transferOwnership(groupId: Long, successorId: Long, quitAfterTransfer: Boolean = false): Response<Unit> =
        updateGroup(groupId, null, null, null, null, null, null, successorId, quitAfterTransfer)

    /**
     * Mute the target group.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
     *   the server will send a update group notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send a update group notification to all group members of the target group actively.
     *
     * @param groupId the target group ID to find the group for updating.
     * @param muteEndDate the new group mute end date.
     * Before the group mute end date, the group members will not be able
     * to send messages.
     *
     * Authorization:
     * * Only the group owner or group managers can mute or unmute the group.
     *   If the logged-in user is not the owner or manager of the group,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER].
     * @throws ResponseException if an error occurs.
     */
    suspend fun muteGroup(groupId: Long, muteEndDate: Date): Response<Unit> =
        updateGroup(groupId, null, null, null, null, null, muteEndDate, null, null)

    /**
     * Unmute the target group.
     *
     * Authorization:
     * * Only the group owner or group managers can mute or unmute the group.
     *   If the logged-in user is not the owner or manager of the group,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
     *   the server will send a update group notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send a update group notification to all group members of the target group actively.
     *
     * @param groupId the target group ID to find the group for updating.
     * @throws ResponseException if an error occurs.
     */
    suspend fun unmuteGroup(groupId: Long): Response<Unit> = muteGroup(groupId, Date(0))

    /**
     * Find groups.
     *
     * @param groupIds the target group IDs for finding groups.
     * @param lastUpdatedDate the last updated date of groups on local.
     * The server will only return groups that are updated after [lastUpdatedDate].
     * If null, all groups will be returned.
     * @return a list of groups.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryGroups(groupIds: Set<Long>, lastUpdatedDate: Date? = null): Response<List<Group>> =
        if (groupIds.isEmpty()) {
            Response.emptyList()
        } else {
            turmsClient.driver
                .send(
                    QueryGroupsRequest.newBuilder().apply {
                        addAllGroupIds(groupIds)
                        lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                    },
                ).toResponse {
                    it.groupsWithVersion.groupsList
                }
        }

    /**
     * Find group IDs that the logged-in user has joined.
     *
     * @param lastUpdatedDate the last updated date of group IDs that the logged-in user has joined stored locally.
     * The server will only return group IDs that are updated after [lastUpdatedDate].
     * If null, all group IDs will be returned.
     * @return a list of group IDs and the version.
     * Note: The version can be used to update the last updated date on local.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryJoinedGroupIds(lastUpdatedDate: Date? = null): Response<LongsWithVersion?> =
        turmsClient.driver
            .send(
                QueryJoinedGroupIdsRequest.newBuilder().apply {
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                },
            )
            .toResponse {
                if (it.hasLongsWithVersion()) it.longsWithVersion else null
            }

    /**
     * Find groups that the logged-in user has joined.
     *
     * @param lastUpdatedDate the last updated date of groups that the logged-in user has joined stored locally.
     * The server will only return groups that are updated after [lastUpdatedDate].
     * If null, all groups will be returned.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryJoinedGroupInfos(lastUpdatedDate: Date? = null): Response<GroupsWithVersion?> =
        turmsClient.driver
            .send(
                QueryJoinedGroupInfosRequest.newBuilder().apply {
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                },
            )
            .toResponse {
                if (it.hasGroupsWithVersion()) it.groupsWithVersion else null
            }

    /**
     * Add group join/membership questions.
     *
     * Authorization:
     * * Only the group owner or group managers can add group membership questions.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION].
     * * Only the group that use `question` as the join strategy can add group membership questions.
     *   Otherwise, throws [ResponseException] with the code
     *   [ResponseStatusCode.CREATE_GROUP_QUESTION_FOR_GROUP_USING_JOIN_REQUEST]
     *   or [ResponseStatusCode.CREATE_GROUP_QUESTION_FOR_GROUP_USING_INVITATION]
     *   or [ResponseStatusCode.CREATE_GROUP_QUESTION_FOR_GROUP_USING_MEMBERSHIP_REQUEST].
     * * If the group has been deleted,
     *   throws [ResponseException] with the code [ResponseStatusCode.CREATE_GROUP_QUESTION_FOR_INACTIVE_GROUP].
     *
     * @param groupId the target group ID.
     * @param questions the group membership questions.
     * @return new group questions IDs.
     * @throws ResponseException if an error occurs.
     */
    suspend fun addGroupJoinQuestions(
        groupId: Long,
        questions: List<NewGroupJoinQuestion>,
    ): Response<List<Long>> = if (questions.isEmpty()) {
        Response.emptyList()
    } else {
        turmsClient.driver
            .send(
                CreateGroupJoinQuestionsRequest.newBuilder().apply {
                    this.groupId = groupId
                    this.addAllQuestions(
                        questions.map {
                            GroupJoinQuestion.newBuilder().apply {
                                val answers = it.answers
                                if (answers.isEmpty()) {
                                    throw ResponseException.from(ResponseStatusCode.ILLEGAL_ARGUMENT, "The answers of group must not be empty")
                                }
                                this.question = it.question
                                this.addAllAnswers(answers)
                                this.score = it.score
                            }.build()
                        },
                    )
                },
            )
            .toResponse {
                it.longsWithVersion.longsList
            }
    }

    /**
     * Delete group join/membership questions.
     *
     * Authorization:
     * * Only the group owner or group managers can delete group membership questions.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION].
     *
     * @param groupId the target group ID.
     * @param questionIds the group membership question IDs.
     * @throws ResponseException if an error occurs.
     */
    suspend fun deleteGroupJoinQuestions(groupId: Long, questionIds: Set<Long>): Response<Unit> =
        if (questionIds.isEmpty()) {
            Response.unitValue()
        } else {
            turmsClient.driver
                .send(
                    DeleteGroupJoinQuestionsRequest.newBuilder().apply {
                        this.groupId = groupId
                        this.addAllQuestionIds(questionIds)
                    },
                )
                .toResponse()
        }

    /**
     * Update group join/membership questions.
     *
     * Authorization:
     * * Only the group owner or group managers can update group membership questions.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION].
     *
     * @param questionId the target question ID.
     * @param question the question.
     * If null, the question will not be updated.
     * @param answers the answers.
     * If null, the answers will not be updated.
     * @param score the score.
     * If null, the score will not be updated.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateGroupJoinQuestion(
        questionId: Long,
        question: String? = null,
        answers: List<String>? = null,
        score: Int? = null,
    ): Response<Unit> = if (Validator.areAllNull(question, answers, score)) {
        Response.unitValue()
    } else {
        turmsClient.driver.send(
            UpdateGroupJoinQuestionRequest.newBuilder().apply {
                this.questionId = questionId
                question?.let { this.question = it }
                answers?.let { this.addAllAnswers(it) }
                score?.let { this.score = it }
            },
        )
            .toResponse()
    }

    // Group Blocklist

    /**
     * Block a user in the group.
     * If the logged-in user is a group member, the server will delete the group member automatically.
     *
     * Authorization:
     * * Only the group owner or group managers can block users.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_ADD_BLOCKED_USER].
     * * If the logged-in user trys to block themselves,
     *   throws [ResponseException] with the code [ResponseStatusCode.CANNOT_BLOCK_ONESELF].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-blocked-user-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a block user notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-blocked-user-added.notify-blocked-user`,
     *   is true (false by default), the server will send a block user notification to the target user actively.
     * * If the server property `turms.service.notification.group-blocked-user-added.notify-group-members`
     *   is true (false by default), the server will send a block user notification to all group members of the target group actively.
     *
     * @param groupId the target group ID.
     * @param userId the target user ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun blockUser(groupId: Long, userId: Long): Response<Unit> = turmsClient.driver
        .send(
            CreateGroupBlockedUserRequest.newBuilder().apply {
                this.userId = userId
                this.groupId = groupId
            },
        )
        .toResponse()

    /**
     * Unblock a user in the group.
     *
     * Authorization:
     * * Only the group owner or group managers can unblock users.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-blocked-user-removed.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a unblock user notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-blocked-user-removed.notify-blocked-user`,
     *   is true (false by default), the server will send a unblock user notification to the target user actively.
     * * If the server property `turms.service.notification.group-blocked-user-removed.notify-group-members`
     *   is true (false by default), the server will send a unblock user notification to all group members of the target group actively.
     *
     * @param groupId the target group ID.
     * @param userId the target user ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun unblockUser(groupId: Long, userId: Long): Response<Unit> = turmsClient.driver
        .send(
            DeleteGroupBlockedUserRequest.newBuilder().apply {
                this.groupId = groupId
                this.userId = userId
            },
        )
        .toResponse()

    /**
     * Find blocked user IDs.
     *
     * @param groupId the target group ID.
     * @param lastUpdatedDate the last updated date of blocked user IDs stored locally.
     * The server will only return blocked user IDs that are updated after [lastUpdatedDate].
     * If null, all blocked user IDs will be returned.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryBlockedUserIds(
        groupId: Long,
        lastUpdatedDate: Date? = null,
    ): Response<LongsWithVersion?> = turmsClient.driver
        .send(
            QueryGroupBlockedUserIdsRequest.newBuilder().apply {
                this.groupId = groupId
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            },
        )
        .toResponse {
            if (it.hasLongsWithVersion()) it.longsWithVersion else null
        }

    /**
     * Find blocked user infos.
     *
     * @param groupId the target group ID.
     * @param lastUpdatedDate the last updated date of blocked user infos stored locally.
     * The server will only return blocked user infos that are updated after [lastUpdatedDate].
     * If null, all blocked user infos will be returned.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryBlockedUserInfos(
        groupId: Long,
        lastUpdatedDate: Date? = null,
    ): Response<UserInfosWithVersion?> = turmsClient.driver
        .send(
            QueryGroupBlockedUserInfosRequest.newBuilder().apply {
                this.groupId = groupId
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            },
        ).toResponse {
            if (it.hasUserInfosWithVersion()) it.userInfosWithVersion else null
        }

    // Group Enrollment

    /**
     * Create an invitation.
     *
     * Authorization:
     * * If [inviteeId] is already a group member,
     *   throws [ResponseException] with the code [ResponseStatusCode.SEND_GROUP_INVITATION_TO_GROUP_MEMBER].
     * * Depending on the group join strategy, if the group do not use the invitation strategy
     *   throws [ResponseException] with the code
     *   [ResponseStatusCode.NOT_GROUP_OWNER_TO_SEND_GROUP_INVITATION],
     *   [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_SEND_GROUP_INVITATION],
     *   or [ResponseStatusCode.NOT_GROUP_MEMBER_TO_SEND_GROUP_INVITATION].
     * * If the group allows adding users as new group members without users' approval,
     *   throws [ResponseException] with the code [ResponseStatusCode.SEND_GROUP_INVITATION_TO_GROUP_NOT_REQUIRING_USERS_APPROVAL].
     * * If the group does not exist,
     *   throws [ResponseException] with the code [ResponseStatusCode.ADD_USER_TO_INACTIVE_GROUP].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-invitation-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new invitation notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-invitation-added.notify-group-owner-and-managers`
     *   is true (true by default), the server will send a new invitation notification to the group owner and managers actively.
     * * If the server property `turms.service.notification.group-invitation-added.notify-group-members`,
     *   is true (false by default), the server will send a new invitation notification to all group members of the target group actively.
     * * If the server property `turms.service.notification.group-invitation-added.notify-invitee`,
     *   is true (true by default), the server will send a new invitation notification to the target user actively.
     *
     * @param groupId the target group ID.
     * @param inviteeId the target user ID.
     * @param content the invitation content.
     * @return the invitation ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun createInvitation(
        groupId: Long,
        inviteeId: Long,
        content: String,
    ): Response<Long> = turmsClient.driver
        .send(
            CreateGroupInvitationRequest.newBuilder().apply {
                this.groupId = groupId
                this.inviteeId = inviteeId
                this.content = content
            },
        ).toResponse {
            it.getLongOrThrow()
        }

    /**
     * Delete/Recall an invitation.
     *
     * Authorization:
     * * If the server property `turms.service.group.invitation.allow-recall-pending-invitation-by-sender`
     *   is true (false by default), the logged-in user can recall pending invitations sent by themselves.
     *   Otherwise, throws [ResponseException].
     * * If the server property `turms.service.group.invitation.allow-recall-pending-invitation-by-owner-and-manager`
     *   is true (false by default), the logged-in user can recall pending invitations only if they are the group owner or manager of the invitation.
     *   Otherwise, throws [ResponseException].
     * * For the above two cases, the following codes will be thrown according to different properties:
     *   [ResponseStatusCode.RECALLING_GROUP_INVITATION_IS_DISABLED] if the above two properties are false.
     *   [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_RECALL_GROUP_INVITATION],
     *   [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_OR_SENDER_TO_RECALL_GROUP_INVITATION]
     * * If the group invitation is not pending (e.g. expired, accepted, deleted, etc),
     *   throws [ResponseException] with the code [ResponseStatusCode.RECALL_NON_PENDING_GROUP_INVITATION].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-invitation-recalled.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete invitation notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-invitation-recalled.notify-group-owner-and-managers`
     *   is true (true by default), the server will send a delete invitation notification to the group owner and managers actively.
     * * If the server property `turms.service.notification.group-invitation-recalled.notify-group-members`,
     *   is true (false by default), the server will send a delete invitation notification to all group members of the target group actively.
     * * If the server property `turms.service.notification.group-invitation-recalled.notify-invitee`,
     *   is true (true by default), the server will send a delete invitation notification to the target user actively.
     *
     * @throws ResponseException if an error occurs.
     */
    suspend fun deleteInvitation(invitationId: Long): Response<Unit> = turmsClient.driver
        .send(
            DeleteGroupInvitationRequest.newBuilder().apply {
                this.invitationId = invitationId
            },
        )
        .toResponse()

    /**
     * Reply to a group invitation.
     *
     * If the logged-in user accepts an invitation sent by a group,
     * the user will become a group member automatically.
     *
     * Authorization:
     * * If the logged-in user is not the invitee of the group invitation,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_INVITEE_TO_UPDATE_GROUP_INVITATION].
     * * If the group invitation is not pending (e.g. expired, accepted, deleted, etc),
     *   throws [ResponseException] with the code [ResponseStatusCode.UPDATE_NON_PENDING_GROUP_INVITATION].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-invitation-replied.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a reply group invitation notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-invitation-replied.notify-group-invitation-inviter`,
     *   is true (true by default), the server will send a reply group invitation notification to the group join request sender actively.
     * * If the server property `turms.service.notification.group-invitation-replied.notify-group-members`,
     *   is true (false by default), the server will send a reply group invitation notification to all group members of the target group actively.
     * * If the server property `turms.service.notification.group-invitation-replied.notify-group-owner-and-managers`,
     *   is true (true by default), the server will send a reply group invitation notification to the group owner and managers actively.
     *
     * @param invitationId the invitation ID.
     * @param responseAction the response action.
     * @param reason the reason of the response.
     * @throws ResponseException if an error occurs.
     */
    suspend fun replyInvitation(invitationId: Long, responseAction: ResponseAction, reason: String? = null): Response<Unit> =
        turmsClient.driver
            .send(
                UpdateGroupInvitationRequest.newBuilder().apply {
                    this.invitationId = invitationId
                    this.responseAction = responseAction
                    reason?.let { this.reason = reason }
                },
            )
            .toResponse()

    /**
     * Find invitations.
     *
     * @param groupId the target group ID.
     * @param lastUpdatedDate the last updated date of invitations stored locally.
     * The server will only return groups that are updated after [lastUpdatedDate].
     * If null, all group IDs will be returned.
     * @return a list of invitation IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryInvitations(groupId: Long, lastUpdatedDate: Date? = null): Response<GroupInvitationsWithVersion?> =
        turmsClient.driver
            .send(
                QueryGroupInvitationsRequest.newBuilder().apply {
                    this.groupId = groupId
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                },
            ).toResponse {
                if (it.hasGroupInvitationsWithVersion()) it.groupInvitationsWithVersion else null
            }

    /**
     * Find invitations.
     *
     * @param areSentByMe whether the invitations are sent by me.
     * @param lastUpdatedDate the last updated date of invitations stored locally.
     * The server will only return invitations that are updated after [lastUpdatedDate].
     * If null, all invitations will be returned.
     * @return a list of invitation IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryInvitations(areSentByMe: Boolean, lastUpdatedDate: Date? = null): Response<GroupInvitationsWithVersion?> =
        turmsClient.driver
            .send(
                QueryGroupInvitationsRequest.newBuilder().apply {
                    this.areSentByMe = areSentByMe
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                },
            ).toResponse {
                if (it.hasGroupInvitationsWithVersion()) it.groupInvitationsWithVersion else null
            }

    /**
     * Create a group join/membership request.
     *
     * Authorization:
     * * If the logged-in user has been blocked by the group,
     *   throws [ResponseException] with the code [ResponseStatusCode.BLOCKED_USER_SEND_GROUP_JOIN_REQUEST].
     * * If the logged-in user trys to send a join request to the group
     *   in which they are already a member,
     *   throws [ResponseException] with the code [ResponseStatusCode.GROUP_MEMBER_SEND_GROUP_JOIN_REQUEST].
     * * If the group does not allow group join requests,
     *   throws [ResponseException] with the code:
     *   [ResponseStatusCode.SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_INVITATION],
     *   [ResponseStatusCode.SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_MEMBERSHIP_REQUEST],
     *   or [ResponseStatusCode.SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_QUESTION].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-join-request-created.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a group membership request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-join-request-created.notify-group-owner-and-managers`,
     *   is true (true by default), the server will send a group membership request notification to the group owner and managers actively.
     * * If the server property `turms.service.notification.group-join-request-created.notify-group-members`
     *   is true (false by default), the server will send a group membership request notification to all group members of the target group actively.
     *
     * @param groupId the target group ID.
     * @param content the request content.
     * @return the request ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun createJoinRequest(groupId: Long, content: String): Response<Long> = turmsClient.driver
        .send(
            CreateGroupJoinRequestRequest.newBuilder().apply {
                this.groupId = groupId
                this.content = content
            },
        ).toResponse {
            it.getLongOrThrow()
        }

    /**
     * Delete/Recall a group join/membership request.
     *
     * Authorization:
     * * If the server property `turms.service.group.join-request.allow-recall-pending-join-request-by-sender`
     *   is true (false by default), the logged-in user can recall pending join requests sent by themselves.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED].
     * * If the logged-in user is not the sender of the group join request,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_SENDER_TO_RECALL_GROUP_JOIN_REQUEST].
     * * If the group join request is not pending (e.g. expired, accepted, deleted, etc),
     *   throws [ResponseException] with the code [ResponseStatusCode.RECALL_NON_PENDING_GROUP_JOIN_REQUEST].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-join-request-recalled.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete join request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-join-request-recalled.notify-group-owner-and-managers`
     *   is true (true by default), the server will send a delete join request notification to the group owner and managers actively.
     * * If the server property `turms.service.notification.group-join-request-recalled.notify-group-members`,
     *   is true (false by default), the server will send a delete join request notification to all group members of the target group actively.
     *
     * @throws ResponseException if an error occurs.
     */
    suspend fun deleteJoinRequest(requestId: Long): Response<Unit> = turmsClient.driver
        .send(
            DeleteGroupJoinRequestRequest.newBuilder().apply {
                this.requestId = requestId
            },
        )
        .toResponse()

    /**
     * Reply a group join/membership request.
     *
     * If the logged-in user accepts/approves a join request sent by a user,
     * the user will become a group member automatically.
     *
     * Authorization:
     * 1. If the logged-in user is not the group owner or manager of the group,
     * throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_JOIN_REQUEST].
     * 2. If the group join request is not pending (e.g. expired, accepted, deleted, etc),
     * throws [ResponseException] with the code [ResponseStatusCode.UPDATE_NON_PENDING_GROUP_JOIN_REQUEST].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-join-request-replied.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a reply group join request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-join-request-replied.notify-group-join-request-sender`,
     *   is true (true by default), the server will send a reply group join request notification to the group join request sender actively.
     * * If the server property `turms.service.notification.group-join-request-replied.notify-group-members`,
     *   is true (false by default), the server will send a reply group join request notification to all group members of the target group actively.
     * * If the server property `turms.service.notification.group-join-request-replied.notify-group-owner-and-managers`,
     *   is true (true by default), the server will send a reply group join request notification to the group owner and managers actively.
     *
     * @param requestId the target group join request ID.
     * @param responseAction the response action.
     * @param reason the reason of the response.
     * @throws ResponseException if an error occurs.
     */
    suspend fun replyJoinRequest(
        requestId: Long,
        responseAction: ResponseAction,
        reason: String?,
    ): Response<Unit> = turmsClient.driver
        .send(
            UpdateGroupJoinRequestRequest.newBuilder().apply {
                this.requestId = requestId
                this.responseAction = responseAction
                reason?.let { this.reason = it }
            },
        ).toResponse()

    /**
     * Find group join/membership requests.
     *
     * @param groupId the target group ID.
     * @param lastUpdatedDate the last updated date of requests stored locally.
     * The server will only return requests that are updated after [lastUpdatedDate].
     * If null, all requests will be returned.
     * @return a list of request IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryJoinRequests(
        groupId: Long,
        lastUpdatedDate: Date? = null,
    ): Response<GroupJoinRequestsWithVersion?> = turmsClient.driver
        .send(
            QueryGroupJoinRequestsRequest.newBuilder().apply {
                this.groupId = groupId
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            },
        ).toResponse {
            if (it.hasGroupJoinRequestsWithVersion()) it.groupJoinRequestsWithVersion else null
        }

    /**
     * Find group join/membership requests sent by the logged-in user.
     *
     * @param lastUpdatedDate the last updated date of requests stored locally.
     * The server will only return requests that are updated after [lastUpdatedDate].
     * If null, all requests will be returned.
     * @return a list of request IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun querySentJoinRequests(lastUpdatedDate: Date? = null): Response<GroupJoinRequestsWithVersion?> =
        turmsClient.driver
            .send(
                QueryGroupJoinRequestsRequest.newBuilder().apply {
                    lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
                },
            ).toResponse {
                if (it.hasGroupJoinRequestsWithVersion()) it.groupJoinRequestsWithVersion else null
            }

    /**
     * Find group join/membership questions.
     *
     * Authorization:
     * * Only the owner and managers have the right to fetch questions with answers
     *
     * @param groupId the target group ID.
     * @param withAnswers Whether to return the answers.
     * @param lastUpdatedDate the last updated date of questions stored locally.
     * The server will only return questions that are updated after [lastUpdatedDate].
     * If null, all questions will be returned.
     * @return a list of question IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryGroupJoinQuestions(
        groupId: Long,
        withAnswers: Boolean,
        lastUpdatedDate: Date? = null,
    ): Response<GroupJoinQuestionsWithVersion?> = turmsClient.driver
        .send(
            QueryGroupJoinQuestionsRequest.newBuilder().apply {
                this.groupId = groupId
                this.withAnswers = withAnswers
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            },
        ).toResponse {
            if (it.hasGroupJoinQuestionsWithVersion()) it.groupJoinQuestionsWithVersion else null
        }

    /**
     * Answer group join/membership questions, and join the group automatically
     * if the logged-in user has answered some questions correctly
     * and acquire the minimum score to join.
     *
     * @param questionIdToAnswer the map of question ID to answer.
     * @return the group membership questions answer result.
     * @throws ResponseException if an error occurs.
     */
    suspend fun answerGroupQuestions(@NotEmpty questionIdToAnswer: Map<Long, String>): Response<GroupJoinQuestionsAnswerResult> =
        if (questionIdToAnswer.isEmpty()) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "\"questionIdToAnswer\" must not be null or empty",
            )
        } else {
            turmsClient.driver
                .send(
                    CheckGroupJoinQuestionsAnswersRequest.newBuilder().apply {
                        putAllQuestionIdToAnswer(questionIdToAnswer)
                    },
                ).toResponse {
                    if (it.hasGroupJoinQuestionAnswerResult()) {
                        it.groupJoinQuestionAnswerResult
                    } else {
                        throw ResponseException.from(ResponseStatusCode.INVALID_RESPONSE)
                    }
                }
        }

    // Group Member

    /**
     * Add group members.
     *
     * Authorization:
     * * If the group is inactive,
     *   throws [ResponseException] with the code [ResponseStatusCode.ADD_USER_TO_INACTIVE_GROUP].
     * * If the group has reached the maximum number of group members,
     *   throws [ResponseException] with the code [ResponseStatusCode.ADD_USER_TO_GROUP_WITH_SIZE_LIMIT_REACHED].
     * * If the group doesn't allow add users as group members directly,
     *   throws [ResponseException] with the code [ResponseStatusCode.ADD_USER_TO_GROUP_REQUIRING_USERS_APPROVAL].
     * * When the logged-in user tries to add themselves as a group member,
     *   they will become a group member if the group uses member requests as the join strategy.
     *   Otherwise, throws the following codes according to different join strategies:
     *   [ResponseStatusCode.USER_JOIN_GROUP_WITHOUT_ACCEPTING_GROUP_INVITATION],
     *   [ResponseStatusCode.USER_JOIN_GROUP_WITHOUT_ANSWERING_GROUP_QUESTION],
     *   [ResponseStatusCode.USER_JOIN_GROUP_WITHOUT_SENDING_GROUP_JOIN_REQUEST].
     * * If the logged-in user has no permission to add new group members,
     *   throws [ResponseException] with one of the following codes:
     *   [ResponseStatusCode.NOT_GROUP_OWNER_TO_ADD_GROUP_MEMBER],
     *   [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_ADD_GROUP_MEMBER],
     *   [ResponseStatusCode.NOT_GROUP_MEMBER_TO_ADD_GROUP_MEMBER],
     *   [ResponseStatusCode.NOT_GROUP_OWNER_TO_ADD_GROUP_MANAGER].
     * * If [userIds] contains a blocked user ID,
     *   throws [ResponseException] with the code [ResponseStatusCode.ADD_BLOCKED_USER_TO_GROUP].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-added.notify-added-group-member`,
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the added group member.
     * * If the server property `turms.service.notification.group-member-added.notify-other-group-members`,
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the other group members.
     *
     * @param groupId the target group ID.
     * @param userIds the target user IDs.
     * @param name the name of the group member.
     * @param role the role of the group member.
     * @param muteEndDate the mute end date of the group member.
     * @throws ResponseException if an error occurs.
     */
    suspend fun addGroupMembers(
        groupId: Long,
        userIds: Set<Long>,
        name: String? = null,
        role: GroupMemberRole? = null,
        muteEndDate: Date? = null,
    ): Response<Unit> =
        if (userIds.isEmpty()) {
            Response.unitValue()
        } else {
            turmsClient.driver
                .send(
                    CreateGroupMembersRequest.newBuilder().apply {
                        this.groupId = groupId
                        this.addAllUserIds(userIds)
                        name?.let { this.name = it }
                        role?.let { this.role = it }
                        muteEndDate?.let { this.muteEndDate = it.time }
                    },
                )
                .toResponse()
        }

    /**
     * Join a group.
     *
     * Authorization:
     * * When the logged-in user tries to add themselves as a group member,
     *   they will become a group member if the group uses member requests as the join strategy.
     *   Otherwise, throws the following codes according to different join strategies:
     *   [ResponseStatusCode.USER_JOIN_GROUP_WITHOUT_ACCEPTING_GROUP_INVITATION],
     *   [ResponseStatusCode.USER_JOIN_GROUP_WITHOUT_ANSWERING_GROUP_QUESTION],
     *   [ResponseStatusCode.USER_JOIN_GROUP_WITHOUT_SENDING_GROUP_JOIN_REQUEST].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-added.notify-added-group-member`,
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the added group member.
     * * If the server property `turms.service.notification.group-member-added.notify-other-group-members`,
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the other group members.
     *
     * @param groupId the target group ID.
     * @param name the name as the group member.
     * @throws ResponseException if an error occurs.
     */
    suspend fun joinGroup(
        groupId: Long,
        name: String? = null,
    ): Response<Unit> {
        val info = turmsClient.userService.userInfo
            ?: throw ResponseException.from(ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED)
        return addGroupMembers(groupId, setOf(info.userId), name)
    }

    /**
     * Quit a group.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a delete group member notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-removed.notify-other-group-members`,
     *   is true (true by default), the server will send a delete group member notification to all other group members of the group actively.
     *
     * @param groupId the target group ID.
     * @param successorId the new successor ID.
     * If the logged-in user is the owner of the group, they must transfer the group ownership to the [successorId],
     * throws [ResponseException] with the code [ResponseStatusCode.GROUP_OWNER_QUIT_WITHOUT_SPECIFYING_SUCCESSOR] otherwise.
     * And the successor will become the group owner.
     * The successor must already be a member of the group, throws [ResponseException] with the code
     * [ResponseStatusCode.GROUP_SUCCESSOR_NOT_GROUP_MEMBER] otherwise.
     * @param quitAfterTransfer whether to quit the group after transfer the group ownership.
     * If false, the logged-in user will become a normal group member (not the group admin).
     * If null, the value will not be changed.
     *
     * Authorization:
     * * If the logged-in user is not the owner of the group,
     *   [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_TO_TRANSFER_GROUP] will be thrown.
     * @throws ResponseException if an error occurs.
     */
    suspend fun quitGroup(
        groupId: Long,
        successorId: Long? = null,
        quitAfterTransfer: Boolean? = null,
    ): Response<Unit> = turmsClient.driver
        .send(
            DeleteGroupMembersRequest.newBuilder().apply {
                this.groupId = groupId
                addMemberIds(turmsClient.userService.userInfo!!.userId)
                successorId?.let { this.successorId = it }
                quitAfterTransfer?.let { this.quitAfterTransfer = it }
            },
        )
        .toResponse()

    /**
     * Remove group members.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a delete group member notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-removed.notify-removed-group-member`,
     *   is true (true by default), the server will send a delete group member notification to the removed group member actively.
     * * If the server property `turms.service.notification.group-member-removed.notify-other-group-members`,
     *   is true (true by default), the server will send a delete group member notification to all other group members of the group actively.
     *
     * @param groupId the target group ID.
     * @param memberIds the target member IDs.
     * @throws ResponseException if an error occurs.
     */
    suspend fun removeGroupMembers(groupId: Long, memberIds: Set<Long>): Response<Unit> =
        if (memberIds.isEmpty()) {
            Response.unitValue()
        } else {
            turmsClient.driver
                .send(
                    DeleteGroupMembersRequest.newBuilder().apply {
                        this.groupId = groupId
                        this.addAllMemberIds(memberIds)
                    },
                )
                .toResponse()
        }

    /**
     * Update group member info.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
     *   is true (false by default), the server will send a update group member info notification to the updated group member actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
     *   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
     *
     * @param groupId the target group ID.
     * @param memberId the target member ID.
     * @param name the new name of the group member.
     * If null, the name will not be updated.
     * @param role the new role of the group member.
     * If null, the role will not be updated.
     * @param muteEndDate the new mute end date of the group member.
     * If null, the mute end date will not be updated.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER]
     * * If the logged-in user is not the group owner,
     *   throws [ResponseException] with the code [ResponseStatusCode.MUTE_GROUP_MEMBER_WITH_ROLE_EQUAL_TO_OR_HIGHER_THAN_REQUESTER].
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateGroupMemberInfo(
        groupId: Long,
        memberId: Long,
        name: String? = null,
        role: GroupMemberRole? = null,
        muteEndDate: Date? = null,
    ): Response<Unit> = if (Validator.areAllNull(name, role, muteEndDate)) {
        Response.unitValue()
    } else {
        turmsClient.driver
            .send(
                UpdateGroupMemberRequest.newBuilder().apply {
                    this.groupId = groupId
                    this.memberId = memberId
                    name?.let { this.name = it }
                    role?.let { this.role = it }
                    muteEndDate?.let { this.muteEndDate = it.time }
                },
            )
            .toResponse()
    }

    /**
     * Mute group member.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER]
     * * If the logged-in user is not the group owner,
     *   throws [ResponseException] with the code [ResponseStatusCode.MUTE_GROUP_MEMBER_WITH_ROLE_EQUAL_TO_OR_HIGHER_THAN_REQUESTER].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
     *   is true (false by default), the server will send a update group member info notification to the updated group member actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
     *   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
     *
     * @param groupId the target group ID.
     * @param memberId the target member ID.
     * @param muteEndDate the new mute end date of the group member.
     * @throws ResponseException if an error occurs.
     */
    suspend fun muteGroupMember(
        groupId: Long,
        memberId: Long,
        muteEndDate: Date,
    ): Response<Unit> = updateGroupMemberInfo(groupId, memberId, null, null, muteEndDate)

    /**
     * Unmute group member.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER]
     * * If the logged-in user is not the group owner,
     *   throws [ResponseException] with the code [ResponseStatusCode.MUTE_GROUP_MEMBER_WITH_ROLE_EQUAL_TO_OR_HIGHER_THAN_REQUESTER].
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
     *   is true (false by default), the server will send a update group member info notification to the updated group member actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
     *   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
     *
     * @param groupId the target group ID.
     * @param memberId the target member ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun unmuteGroupMember(groupId: Long, memberId: Long): Response<Unit> = muteGroupMember(groupId, memberId, Date(0))

    /**
     * Find group members.
     *
     * @param groupId the target group ID.
     * @param withStatus whether to return the session status of the group members.
     * @param lastUpdatedDate the last updated date of the group members stored locally.
     * The server will only return group members that are updated after [lastUpdatedDate].
     * If null, all group members will be returned.
     * @return group members and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryGroupMembers(
        groupId: Long,
        withStatus: Boolean,
        lastUpdatedDate: Date? = null,
    ): Response<GroupMembersWithVersion?> = turmsClient.driver
        .send(
            QueryGroupMembersRequest.newBuilder().apply {
                this.groupId = groupId
                this.withStatus = withStatus
                lastUpdatedDate?.let { this.lastUpdatedDate = it.time }
            },
        ).toResponse {
            if (it.hasGroupMembersWithVersion()) it.groupMembersWithVersion else null
        }

    /**
     * Find group members.
     *
     * @param groupId the target group ID.
     * @param memberIds the target member IDs.
     * @param withStatus whether to return the session status of the group members.
     * @return group members and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryGroupMembersByMemberIds(
        groupId: Long,
        @NotEmpty memberIds: Set<Long>,
        withStatus: Boolean = false,
    ): Response<GroupMembersWithVersion?> = if (memberIds.isEmpty()) {
        throw ResponseException.from(ResponseStatusCode.ILLEGAL_ARGUMENT, "\"memberIds\" must not be null or empty")
    } else {
        turmsClient.driver
            .send(
                QueryGroupMembersRequest.newBuilder().apply {
                    this.groupId = groupId
                    addAllMemberIds(memberIds)
                    this.withStatus = withStatus
                },
            ).toResponse {
                if (it.hasGroupMembersWithVersion()) it.groupMembersWithVersion else null
            }
    }
}
