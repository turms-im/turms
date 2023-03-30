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

package system.im.turms.service.domain.group.access.servicerequest.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import helper.NotificationUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;
import system.im.turms.service.domain.common.access.servicerequest.controller.BaseServiceControllerTest;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.group.CreateGroupRequest;
import im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest;
import im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest;
import im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest;
import im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest;
import im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest;
import im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest;
import im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest;
import im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest;
import im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest;
import im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest;
import im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest;
import im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest;
import im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.service.access.servicerequest.dto.ClientRequest;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.group.access.servicerequest.controller.GroupServiceController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static im.turms.server.common.testing.Constants.ORDER_HIGHEST_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_HIGH_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_LAST;
import static im.turms.server.common.testing.Constants.ORDER_LOWEST_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_LOW_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_MIDDLE_PRIORITY;

@TestMethodOrder(OrderAnnotation.class)
class GroupServiceControllerST extends BaseServiceControllerTest<GroupServiceController> {

    private static final long USER_ID = 1;
    private static final DeviceType USER_DEVICE = DeviceType.DESKTOP;
    private static final byte[] USER_IP = new byte[]{127, 0, 0, 1};
    private static final long REQUEST_ID = 1;
    private static final long GROUP_SUCCESSOR = 2;
    private static final long GROUP_MEMBER_ID = 3;
    private static final long GROUP_INVITATION_INVITEE = 4;
    private static final long GROUP_BLOCKED_USER_ID = 5;

    private static Long groupId;
    private static Long groupJoinQuestionId;
    private static Long groupJoinRequestId;
    private static Long groupInvitationId;

    // Create

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateGroupRequest_createGroup_shouldReturnGroupId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupRequest(CreateGroupRequest.newBuilder()
                        .setName("group name")
                        .setIntro("group intro")
                        .setAnnouncement("announcement"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            groupId = NotificationUtil.getLongOrThrow(result.dataForRequester());
        });
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupQuestionsRequest_addGroupJoinQuestions_shouldReturnQuestionIds() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupJoinQuestionsRequest(CreateGroupJoinQuestionsRequest.newBuilder()
                        .setGroupId(groupId)
                        .addQuestions(GroupJoinQuestion.newBuilder()
                                .setQuestion("question")
                                .addAllAnswers(List.of("answer1", "answer2"))
                                .setScore(10)
                                .build()))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupQuestionsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            TurmsNotification.Data data = result.dataForRequester();
            List<Long> ids = data.getLongsWithVersion()
                    .getLongsList();
            assertThat(ids).hasSize(1);
            groupJoinQuestionId = ids.get(0);
        });
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupJoinRequestRequest_createJoinRequest_shouldReturnJoinRequestId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupJoinRequestRequest(CreateGroupJoinRequestRequest.newBuilder()
                        .setGroupId(groupId)
                        .setContent("content"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCreateGroupJoinRequestRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            groupJoinRequestId = NotificationUtil.getLongOrThrow(result.dataForRequester());
        });
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupMembersRequest_addGroupMembers_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupMembersRequest(CreateGroupMembersRequest.newBuilder()
                        .setGroupId(groupId)
                        .addAllUserIds(List.of(GROUP_SUCCESSOR, GROUP_MEMBER_ID))
                        .setName("name")
                        .setRole(GroupMemberRole.MEMBER))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupMembersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupBlockedUserRequest_blockUser_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupBlockedUserRequest(CreateGroupBlockedUserRequest.newBuilder()
                        .setGroupId(groupId)
                        .setUserId(GROUP_BLOCKED_USER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCreateGroupBlockedUserRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupInvitationRequestRequest_createInvitation_shouldReturnInvitationId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupInvitationRequest(CreateGroupInvitationRequest.newBuilder()
                        .setGroupId(groupId)
                        .setInviteeId(GROUP_INVITATION_INVITEE)
                        .setContent("content"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCreateGroupInvitationRequestRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            groupInvitationId = NotificationUtil.getLongOrThrow(result.dataForRequester());
        });
    }

    // Update

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupRequest_updateGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupId)
                        .setName("new name")
                        .setIntro("new intro"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_LAST - 1)
    void handleUpdateGroupRequest_transferOwnership_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupId)
                        .setSuccessorId(GROUP_SUCCESSOR)
                        .setQuitAfterTransfer(false))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);

        request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupId)
                        .setSuccessorId(USER_ID)
                        .setQuitAfterTransfer(false))
                .build();
        clientRequest =
                new ClientRequest(GROUP_SUCCESSOR, USER_DEVICE, USER_IP, REQUEST_ID, request);
        resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupRequest_muteGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupId)
                        .setMuteEndDate(System.currentTimeMillis() + 100_000))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupRequest_unmuteGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupId)
                        .setMuteEndDate(0))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupJoinQuestionRequest_updateGroupJoinQuestion_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupJoinQuestionRequest(UpdateGroupJoinQuestionRequest.newBuilder()
                        .setQuestionId(groupJoinQuestionId)
                        .setQuestion("new question")
                        .addAnswers("answers"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleUpdateGroupJoinQuestionRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupMemberRequest_updateGroupMemberInfo_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupMemberRequest(UpdateGroupMemberRequest.newBuilder()
                        .setGroupId(groupId)
                        .setMemberId(GROUP_MEMBER_ID)
                        .setName("myname"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupMemberRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupMemberRequest_muteGroupMember_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupMemberRequest(UpdateGroupMemberRequest.newBuilder()
                        .setGroupId(groupId)
                        .setMemberId(GROUP_MEMBER_ID)
                        .setMuteEndDate(System.currentTimeMillis() + 100_000))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupMemberRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupMemberRequest_unmuteGroupMember_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupMemberRequest(UpdateGroupMemberRequest.newBuilder()
                        .setGroupId(groupId)
                        .setMemberId(GROUP_MEMBER_ID)
                        .setMuteEndDate(0))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupMemberRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    // Query

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupsRequest_queryGroups_shouldReturnGroups() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupsRequest(QueryGroupsRequest.newBuilder()
                        .addGroupIds(groupId))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getGroupsWithVersion()
                        .getGroups(0)
                        .getId()).isEqualTo(groupId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryJoinedGroupIdsRequest_queryJoinedGroupIds_shouldEqualNewGroupId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryJoinedGroupIdsRequest(QueryJoinedGroupIdsRequest.newBuilder()
                        .setLastUpdatedDate(0))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryJoinedGroupIdsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getLongsWithVersion()
                        .getLongsList()).contains(groupId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryJoinedGroupsRequest_queryJoinedGroupInfos_shouldEqualNewGroupId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryJoinedGroupInfosRequest(QueryJoinedGroupInfosRequest.newBuilder())
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryJoinedGroupsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getGroupsWithVersion()
                        .getGroupsList()).anyMatch(group -> groupId.equals(group.getId())));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupBlockedUserIdsRequest_queryBlockedUserIds_shouldEqualBlockedUserId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupBlockedUserIdsRequest(QueryGroupBlockedUserIdsRequest.newBuilder()
                        .setGroupId(groupId))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleQueryGroupBlockedUserIdsRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getLongsWithVersion()
                        .getLongsList()).contains(GROUP_BLOCKED_USER_ID));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupBlockedUsersInfosRequest_queryBlockedUserInfos_shouldEqualBlockedUserId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupBlockedUserInfosRequest(QueryGroupBlockedUserInfosRequest.newBuilder()
                        .setGroupId(groupId))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleQueryGroupBlockedUsersInfosRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getUserInfosWithVersion()
                        .getUserInfos(0)
                        .getId()).isEqualTo(GROUP_BLOCKED_USER_ID));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupInvitationsRequest_queryInvitations_shouldEqualNewInvitationId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupInvitationsRequest(QueryGroupInvitationsRequest.newBuilder()
                        .setGroupId(groupId))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupInvitationsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getGroupInvitationsWithVersion()
                        .getGroupInvitations(0)
                        .getId()).isEqualTo(groupInvitationId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupJoinRequestsRequest_queryJoinRequests_shouldEqualNewJoinRequestId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupJoinRequestsRequest(QueryGroupJoinRequestsRequest.newBuilder()
                        .setGroupId(groupId))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleQueryGroupJoinRequestsRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getGroupJoinRequestsWithVersion()
                        .getGroupJoinRequests(0)
                        .getId()).isEqualTo(groupJoinRequestId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupJoinQuestions_queryGroupJoinQuestions_shouldEqualNewGroupQuestionId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupJoinQuestionsRequest(QueryGroupJoinQuestionsRequest.newBuilder()
                        .setGroupId(groupId)
                        .setWithAnswers(true))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleQueryGroupJoinQuestionsRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getGroupJoinQuestionsWithVersion()
                        .getGroupJoinQuestions(0)
                        .getId()).isEqualTo(groupJoinQuestionId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupMembersRequest_queryGroupMembers_shouldEqualNewMemberId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupMembersRequest(QueryGroupMembersRequest.newBuilder()
                        .setGroupId(groupId)
                        .setWithStatus(true))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupMembersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getGroupMembersWithVersion()
                        .getGroupMembersList()
                        .stream()
                        .map(member -> member.getUserId())
                        .collect(Collectors.toList()))
                        .containsExactlyInAnyOrder(USER_ID, GROUP_SUCCESSOR, GROUP_MEMBER_ID));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupMembersRequest_queryGroupMembersByMemberIds_shouldEqualNewMemberId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupMembersRequest(QueryGroupMembersRequest.newBuilder()
                        .setGroupId(groupId)
                        .addMemberIds(GROUP_MEMBER_ID)
                        .setWithStatus(true))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupMembersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getGroupMembersWithVersion()
                        .getGroupMembers(0)
                        .getUserId()).isEqualTo(GROUP_MEMBER_ID));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleCheckGroupQuestionAnswerRequest_answerGroupQuestions_shouldReturnAnswerResult() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCheckGroupJoinQuestionsAnswersRequest(
                        CheckGroupJoinQuestionsAnswersRequest.newBuilder()
                                .putQuestionIdToAnswer(groupJoinQuestionId, "answer"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCheckGroupQuestionAnswerRequest()
                        .handle(clientRequest);
        assertResult(resultMono, result -> {
            assertThat(result.dataForRequester()
                    .getGroupJoinQuestionAnswerResult()
                    .getQuestionIdsList()).contains(groupJoinQuestionId);
        }, ResponseStatusCode.OK, ResponseStatusCode.MEMBER_CANNOT_ANSWER_GROUP_QUESTION);
    }

    // Delete

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleDeleteGroupMembersRequest_removeGroupMembers_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteGroupMembersRequest(DeleteGroupMembersRequest.newBuilder()
                        .setGroupId(groupId)
                        .addMemberIds(GROUP_MEMBER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleDeleteGroupMembersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    void handleDeleteGroupJoinQuestionsRequest_deleteGroupJoinQuestions_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteGroupJoinQuestionsRequest(DeleteGroupJoinQuestionsRequest.newBuilder()
                        .setGroupId(groupId)
                        .addQuestionIds(groupJoinQuestionId))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleDeleteGroupJoinQuestionsRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    void handleDeleteGroupBlockedUserRequest_unblockUser_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteGroupBlockedUserRequest(DeleteGroupBlockedUserRequest.newBuilder()
                        .setGroupId(groupId)
                        .setUserId(GROUP_BLOCKED_USER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleDeleteGroupBlockedUserRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    void handleDeleteGroupInvitationRequest_deleteInvitation_shouldSucceedOrThrowDisabledFunction() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteGroupInvitationRequest(DeleteGroupInvitationRequest.newBuilder()
                        .setInvitationId(groupInvitationId))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleDeleteGroupInvitationRequest()
                .handle(clientRequest);
        assertResultCodes(resultMono,
                ResponseStatusCode.OK,
                ResponseStatusCode.RECALLING_GROUP_INVITATION_IS_DISABLED);
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    void handleDeleteGroupJoinRequestRequest_deleteJoinRequest_shouldSucceedOrThrowDisabledFunction() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteGroupJoinRequestRequest(DeleteGroupJoinRequestRequest.newBuilder()
                        .setRequestId(groupJoinRequestId))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleDeleteGroupJoinRequestRequest()
                        .handle(clientRequest);
        assertResultCodes(resultMono,
                ResponseStatusCode.OK,
                ResponseStatusCode.RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED);
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    void handleUpdateGroupRequest_quitGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupId)
                        .setQuitAfterTransfer(false))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_LAST)
    void handleDeleteGroupRequest_deleteGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupRequest(CreateGroupRequest.newBuilder()
                        .setName("readyToDelete"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupRequest()
                .handle(clientRequest);
        AtomicLong readyToDeleteGroupId = new AtomicLong();
        assertResultIsOk(resultMono,
                result -> readyToDeleteGroupId
                        .set(NotificationUtil.getLongOrThrow(result.dataForRequester())));

        request = TurmsRequest.newBuilder()
                .setDeleteGroupRequest(DeleteGroupRequest.newBuilder()
                        .setGroupId(readyToDeleteGroupId.get()))
                .build();
        clientRequest = new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        resultMono = getController().handleDeleteGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

}