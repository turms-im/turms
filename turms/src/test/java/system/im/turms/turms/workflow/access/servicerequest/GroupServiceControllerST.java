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
package system.im.turms.turms.workflow.access.servicerequest;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.GroupMemberRole;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.group.CreateGroupRequest;
import im.turms.common.model.dto.request.group.DeleteGroupRequest;
import im.turms.common.model.dto.request.group.QueryGroupRequest;
import im.turms.common.model.dto.request.group.QueryJoinedGroupIdsRequest;
import im.turms.common.model.dto.request.group.QueryJoinedGroupInfosRequest;
import im.turms.common.model.dto.request.group.UpdateGroupRequest;
import im.turms.common.model.dto.request.group.blocklist.CreateGroupBlockedUserRequest;
import im.turms.common.model.dto.request.group.blocklist.DeleteGroupBlockedUserRequest;
import im.turms.common.model.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest;
import im.turms.common.model.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest;
import im.turms.common.model.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest;
import im.turms.common.model.dto.request.group.enrollment.CreateGroupInvitationRequest;
import im.turms.common.model.dto.request.group.enrollment.CreateGroupJoinQuestionRequest;
import im.turms.common.model.dto.request.group.enrollment.CreateGroupJoinRequestRequest;
import im.turms.common.model.dto.request.group.enrollment.DeleteGroupInvitationRequest;
import im.turms.common.model.dto.request.group.enrollment.DeleteGroupJoinQuestionRequest;
import im.turms.common.model.dto.request.group.enrollment.DeleteGroupJoinRequestRequest;
import im.turms.common.model.dto.request.group.enrollment.QueryGroupInvitationsRequest;
import im.turms.common.model.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest;
import im.turms.common.model.dto.request.group.enrollment.QueryGroupJoinRequestsRequest;
import im.turms.common.model.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest;
import im.turms.common.model.dto.request.group.member.CreateGroupMemberRequest;
import im.turms.common.model.dto.request.group.member.DeleteGroupMemberRequest;
import im.turms.common.model.dto.request.group.member.QueryGroupMembersRequest;
import im.turms.common.model.dto.request.group.member.UpdateGroupMemberRequest;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.turms.workflow.access.servicerequest.controller.GroupServiceController;
import im.turms.turms.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResult;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static im.turms.server.common.testing.Constants.ORDER_HIGHEST_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_HIGH_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_LAST;
import static im.turms.server.common.testing.Constants.ORDER_LOWEST_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_LOW_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_MIDDLE_PRIORITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class GroupServiceControllerST extends BaseServiceControllerTest<GroupServiceController> {

    private static final long USER_ID = 1;
    private static final DeviceType USER_DEVICE = DeviceType.DESKTOP;
    private static final long REQUEST_ID = 1;
    private static final long GROUP_MEMBER_ID = 3;
    private static final long GROUP_INVITATION_INVITEE = 4;
    private static final long GROUP_SUCCESSOR = 1;
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            TurmsNotification.Data data = result.getDataForRequester();
            assertThat(data.hasIds()).isTrue();
            groupId = data.getIds().getValues(0);
        });
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupQuestionRequest_addGroupJoinQuestion_shouldReturnQuestionId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupJoinQuestionRequest(CreateGroupJoinQuestionRequest.newBuilder()
                        .setGroupId(groupId)
                        .setQuestion("question")
                        .addAllAnswers(List.of("answer1", "answer2"))
                        .setScore(10))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupQuestionRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            TurmsNotification.Data data = result.getDataForRequester();
            assertThat(data.hasIds()).isTrue();
            groupJoinQuestionId = data.getIds().getValues(0);
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupJoinRequestRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            TurmsNotification.Data data = result.getDataForRequester();
            assertThat(data.hasIds()).isTrue();
            groupJoinRequestId = data.getIds().getValues(0);
        });
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupMemberRequest_addGroupMember_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupMemberRequest(CreateGroupMemberRequest.newBuilder()
                        .setGroupId(groupId)
                        .setUserId(GROUP_MEMBER_ID)
                        .setName("name")
                        .setRole(GroupMemberRole.MEMBER))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupMemberRequest()
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupBlockedUserRequest()
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupInvitationRequestRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            TurmsNotification.Data data = result.getDataForRequester();
            assertThat(data.hasIds()).isTrue();
            groupInvitationId = data.getIds().getValues(0);
        });
    }

    // Update

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupRequest_updateGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupId)
                        .setGroupName("new name")
                        .setIntro("new intro"))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
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
                        .setQuitAfterTransfer(true))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupJoinQuestionRequest()
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupMemberRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    // Query

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupRequest_queryGroup_shouldReturnGroupWithVersion() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupRequest(QueryGroupRequest.newBuilder()
                        .setGroupId(groupId))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> assertThat(result.getDataForRequester().getGroupsWithVersion().getGroups(0).getId())
                .isEqualTo(groupId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryJoinedGroupsIdsRequest_queryJoinedGroupIds_shouldEqualNewGroupId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryJoinedGroupIdsRequest(QueryJoinedGroupIdsRequest.newBuilder()
                        .setLastUpdatedDate(0))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryJoinedGroupsIdsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> assertThat(result.getDataForRequester().getIdsWithVersion().getValuesList())
                .contains(groupId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryJoinedGroupsRequest_queryJoinedGroupInfos_shouldEqualNewGroupId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryJoinedGroupInfosRequest(QueryJoinedGroupInfosRequest.newBuilder())
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryJoinedGroupsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> assertThat(result.getDataForRequester().getGroupsWithVersion().getGroupsList())
                .anyMatch(group -> groupId.equals(group.getId())));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupBlockedUserIdsRequest_queryBlockedUserIds_shouldEqualBlockedUserId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupBlockedUserIdsRequest(QueryGroupBlockedUserIdsRequest.newBuilder()
                        .setGroupId(groupId))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupBlockedUserIdsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> assertThat(result.getDataForRequester().getIdsWithVersion().getValuesList())
                .contains(GROUP_BLOCKED_USER_ID));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupBlockedUsersInfosRequest_queryBlockedUserInfos_shouldEqualBlockedUserId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupBlockedUserInfosRequest(QueryGroupBlockedUserInfosRequest.newBuilder()
                        .setGroupId(groupId))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupBlockedUsersInfosRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> assertThat(result.getDataForRequester().getUsersInfosWithVersion().getUserInfos(0).getId())
                .isEqualTo(GROUP_BLOCKED_USER_ID));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupInvitationsRequest_queryInvitations_shouldEqualNewInvitationId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupInvitationsRequest(QueryGroupInvitationsRequest.newBuilder()
                        .setGroupId(groupId))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupInvitationsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.getDataForRequester().getGroupInvitationsWithVersion().getGroupInvitations(0).getId())
                        .isEqualTo(groupInvitationId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupJoinRequestsRequest_queryJoinRequests_shouldEqualNewJoinRequestId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupJoinRequestsRequest(QueryGroupJoinRequestsRequest.newBuilder()
                        .setGroupId(groupId))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupJoinRequestsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.getDataForRequester().getGroupJoinRequestsWithVersion().getGroupJoinRequests(0).getId())
                        .isEqualTo(groupJoinRequestId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupJoinQuestionsRequest_queryGroupJoinQuestionsRequest_shouldEqualNewGroupQuestionId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupJoinQuestionsRequest(QueryGroupJoinQuestionsRequest.newBuilder()
                        .setGroupId(groupId)
                        .setWithAnswers(true))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupJoinQuestionsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.getDataForRequester().getGroupJoinQuestionsWithVersion().getGroupJoinQuestions(0).getId())
                        .isEqualTo(groupJoinQuestionId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupMembersRequest_queryGroupMembers_shouldEqualNewMemberId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupMembersRequest(QueryGroupMembersRequest.newBuilder()
                        .setGroupId(groupId)
                        .setWithStatus(true))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupMembersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.getDataForRequester().getGroupMembersWithVersion().getGroupMembers(1).getUserId())
                        .isEqualTo(GROUP_MEMBER_ID));
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupMembersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.getDataForRequester().getGroupMembersWithVersion().getGroupMembers(0).getUserId())
                        .isEqualTo(GROUP_MEMBER_ID));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleCheckGroupQuestionAnswerRequest_answerGroupQuestions_shouldReturnAnswerResult() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCheckGroupJoinQuestionsAnswersRequest(CheckGroupJoinQuestionsAnswersRequest.newBuilder()
                        .putQuestionIdAndAnswer(groupJoinQuestionId, "answer"))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCheckGroupQuestionAnswerRequest()
                .handle(clientRequest);
        assertResult(resultMono, result -> {
            assertThat(result.getDataForRequester().getGroupJoinQuestionAnswerResult().getQuestionIdsList())
                    .contains(groupJoinQuestionId);
        }, TurmsStatusCode.OK, TurmsStatusCode.MEMBER_CANNOT_ANSWER_GROUP_QUESTION);
    }

    // Delete

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleDeleteGroupMemberRequest_removeGroupMember_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteGroupMemberRequest(DeleteGroupMemberRequest.newBuilder()
                        .setGroupId(groupId)
                        .setMemberId(GROUP_MEMBER_ID))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleDeleteGroupMemberRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    void handleDeleteGroupJoinQuestionRequest_deleteGroupJoinQuestion_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteGroupJoinQuestionRequest(DeleteGroupJoinQuestionRequest.newBuilder()
                        .setQuestionId(groupJoinQuestionId))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleDeleteGroupJoinQuestionRequest()
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleDeleteGroupBlockedUserRequest()
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleDeleteGroupInvitationRequest()
                .handle(clientRequest);
        assertResultCodes(resultMono, TurmsStatusCode.OK, TurmsStatusCode.RECALLING_GROUP_INVITATION_IS_DISABLED);
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    void handleDeleteGroupJoinRequestRequest_deleteJoinRequest_shouldSucceedOrThrowDisabledFunction() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteGroupJoinRequestRequest(DeleteGroupJoinRequestRequest.newBuilder()
                        .setRequestId(groupJoinRequestId))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleDeleteGroupJoinRequestRequest()
                .handle(clientRequest);
        assertResultCodes(resultMono, TurmsStatusCode.OK, TurmsStatusCode.RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED);
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    void handleUpdateGroupRequest_quitGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupId)
                        .setQuitAfterTransfer(false))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupRequest()
                .handle(clientRequest);
        AtomicLong readyToDeleteGroupId = new AtomicLong();
        assertResultIsOk(resultMono, result -> readyToDeleteGroupId.set(result.getDataForRequester().getIds().getValues(0)));

        request = TurmsRequest.newBuilder()
                .setDeleteGroupRequest(DeleteGroupRequest.newBuilder()
                        .setGroupId(readyToDeleteGroupId.get()))
                .build();
        clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request, null);
        resultMono = getController().handleDeleteGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

}