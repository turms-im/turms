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

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import helper.NotificationUtil;
import helper.SharedBusinessDataConst;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import system.im.turms.service.domain.common.access.servicerequest.controller.BaseServiceControllerTest;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.client.dto.model.group.Group;
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
import im.turms.service.domain.group.bo.GroupInvitationStrategy;
import im.turms.service.domain.group.bo.GroupJoinStrategy;
import im.turms.service.domain.group.bo.GroupUpdateStrategy;
import im.turms.service.domain.group.po.GroupType;
import im.turms.service.domain.group.service.GroupTypeService;
import im.turms.service.domain.user.service.UserRoleService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static im.turms.server.common.testing.Constants.ORDER_HIGHEST_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_HIGH_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_LAST;
import static im.turms.server.common.testing.Constants.ORDER_LOWEST_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_LOW_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_MIDDLE_PRIORITY;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class GroupServiceControllerST extends BaseServiceControllerTest<GroupServiceController> {

    private static final long USER_ID_1 = 1;
    private static final long USER_ID_2 = 4;
    private static final DeviceType USER_DEVICE = DeviceType.DESKTOP;
    private static final byte[] USER_IP = new byte[]{127, 0, 0, 1};
    private static final long REQUEST_ID = 1;
    private static final long GROUP_SUCCESSOR = 2;
    private static final long GROUP_MEMBER_ID = 3;
    private static final long GROUP_INVITATION_INVITEE = 4;
    private static final long GROUP_BLOCKED_USER_ID = 5;

    private static GroupType groupTypeWithInvitationStrategyOwnerManager;
    private static GroupType groupTypeWithInvitationStrategyOwnerManagerRequiringApproval;
    private static GroupType groupTypeWithJoinStrategyQuestion;

    private static Long groupIdWithInvitationStrategyOwnerManager;
    private static Long groupIdWithInvitationStrategyOwnerManagerRequiringApproval;
    private static Long groupIdWithJoinStrategyQuestion;

    private static Long groupJoinQuestionId;
    private static Long groupJoinRequestId;
    private static Long groupInvitationId;

    @Autowired
    private GroupTypeService groupTypeService;

    @Autowired
    private UserRoleService userRoleService;

    // Prepare data

    @BeforeAll
    void setup() {
        Duration timeout = Duration.ofSeconds(30);
        groupTypeWithInvitationStrategyOwnerManager = groupTypeService
                .addGroupType(null,
                        "test-name",
                        100,
                        GroupInvitationStrategy.OWNER_MANAGER,
                        GroupJoinStrategy.JOIN_REQUEST,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        false,
                        true,
                        false,
                        true)
                .block(timeout);

        groupTypeWithInvitationStrategyOwnerManagerRequiringApproval = groupTypeService
                .addGroupType(null,
                        "test-name",
                        100,
                        GroupInvitationStrategy.OWNER_MANAGER_REQUIRING_APPROVAL,
                        GroupJoinStrategy.JOIN_REQUEST,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        false,
                        true,
                        false,
                        true)
                .block(timeout);

        groupTypeWithJoinStrategyQuestion = groupTypeService
                .addGroupType(null,
                        "test-name",
                        100,
                        GroupInvitationStrategy.OWNER_MANAGER,
                        GroupJoinStrategy.QUESTION,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        false,
                        true,
                        false,
                        true)
                .block(timeout);

        userRoleService.queryStoredOrDefaultUserRoleByUserId(USER_ID_1)
                .flatMap(userRole -> userRoleService.updateUserRoles(Set.of(userRole.getId()),
                        null,
                        Set.of(0L,
                                groupTypeWithInvitationStrategyOwnerManager.getId(),
                                groupTypeWithInvitationStrategyOwnerManagerRequiringApproval
                                        .getId(),
                                groupTypeWithJoinStrategyQuestion.getId()),
                        100,
                        100,
                        null))
                .block(timeout);
    }

    // Create

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateGroupRequest_createGroupWithInvitationStrategyOwnerManager_shouldReturnGroupId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupRequest(CreateGroupRequest.newBuilder()
                        .setTypeId(groupTypeWithInvitationStrategyOwnerManager.getId())
                        .setName("group name")
                        .setIntro("group intro")
                        .setAnnouncement("announcement"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            groupIdWithInvitationStrategyOwnerManager =
                    NotificationUtil.getLongOrThrow(result.response());
        });
    }

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateGroupRequest_createGroupWithInvitationStrategyOwnerManagerRequiringApproval_shouldReturnGroupId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupRequest(CreateGroupRequest.newBuilder()
                        .setTypeId(groupTypeWithInvitationStrategyOwnerManagerRequiringApproval
                                .getId())
                        .setName("group name")
                        .setIntro("group intro")
                        .setAnnouncement("announcement"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupRequest()
                .handle(clientRequest);

        assertResultIsOk(resultMono, result -> {
            groupIdWithInvitationStrategyOwnerManagerRequiringApproval =
                    NotificationUtil.getLongOrThrow(result.response());
        });
    }

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateGroupRequest_createGroupWithJoinStrategyQuestion_shouldReturnGroupId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupRequest(CreateGroupRequest.newBuilder()
                        .setTypeId(groupTypeWithJoinStrategyQuestion.getId())
                        .setName("group name")
                        .setIntro("group intro")
                        .setAnnouncement("announcement"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            groupIdWithJoinStrategyQuestion = NotificationUtil.getLongOrThrow(result.response());
        });
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupQuestionsRequest_addGroupJoinQuestions_shouldFail_forGroupNotUsingJoinRequest() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupJoinQuestionsRequest(CreateGroupJoinQuestionsRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .addQuestions(GroupJoinQuestion.newBuilder()
                                .setQuestion("question")
                                .addAllAnswers(List.of("answer1", "answer2"))
                                .setScore(10)
                                .build()))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupQuestionsRequest()
                .handle(clientRequest);
        assertResultCodes(resultMono,
                ResponseStatusCode.CREATE_GROUP_QUESTION_FOR_GROUP_USING_JOIN_REQUEST);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupQuestionsRequest_addGroupJoinQuestions_shouldReturnQuestionIds() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupJoinQuestionsRequest(CreateGroupJoinQuestionsRequest.newBuilder()
                        .setGroupId(groupIdWithJoinStrategyQuestion)
                        .addQuestions(GroupJoinQuestion.newBuilder()
                                .setQuestion("question")
                                .addAllAnswers(List.of("answer1", "answer2"))
                                .setScore(10)
                                .build()))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupQuestionsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            TurmsNotification.Data data = result.response();
            List<Long> ids = data.getLongsWithVersion()
                    .getLongsList();
            assertThat(ids).hasSize(1);
            groupJoinQuestionId = ids.getFirst();
        });
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupJoinRequestRequest_createJoinRequest_shouldThrowIfTheRequesterIsAlreadyMember() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupJoinRequestRequest(CreateGroupJoinRequestRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setContent("content"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCreateGroupJoinRequestRequest()
                        .handle(clientRequest);
        assertResultCodes(resultMono, ResponseStatusCode.GROUP_MEMBER_SEND_GROUP_JOIN_REQUEST);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupJoinRequestRequest_createJoinRequest_shouldReturnJoinRequestId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupJoinRequestRequest(CreateGroupJoinRequestRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setContent("content"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_2, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCreateGroupJoinRequestRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            groupJoinRequestId = NotificationUtil.getLongOrThrow(result.response());
        });
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupMembersRequest_addGroupMembers_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupMembersRequest(CreateGroupMembersRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .addAllUserIds(List.of(GROUP_SUCCESSOR, GROUP_MEMBER_ID))
                        .setName("name")
                        .setRole(GroupMemberRole.MEMBER))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupMembersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupBlockedUserRequest_blockUser_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupBlockedUserRequest(CreateGroupBlockedUserRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setUserId(GROUP_BLOCKED_USER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCreateGroupBlockedUserRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupInvitationRequestRequest_createInvitation_shouldFail_forGroupNotRequiringApproval() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupInvitationRequest(CreateGroupInvitationRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setInviteeId(GROUP_INVITATION_INVITEE)
                        .setContent("content"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCreateGroupInvitationRequestRequest()
                        .handle(clientRequest);
        assertResultCodes(resultMono,
                ResponseStatusCode.SEND_GROUP_INVITATION_TO_GROUP_NOT_REQUIRING_USERS_APPROVAL);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateGroupInvitationRequestRequest_createInvitation_shouldReturnInvitationId_forGroupRequiringApproval() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateGroupInvitationRequest(CreateGroupInvitationRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManagerRequiringApproval)
                        .setInviteeId(GROUP_INVITATION_INVITEE)
                        .setContent("content"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCreateGroupInvitationRequestRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            groupInvitationId = NotificationUtil.getLongOrThrow(result.response());
        });
    }

    // Update

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupRequest_updateGroupWithWrongAttributeType_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setName("new name")
                        .setIntro("new intro")
                        .putAllUserDefinedAttributes(Map.of("key-bool",
                                Value.newBuilder()
                                        .setStringValue("A wrong value")
                                        .build())))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultCodes(resultMono, ResponseStatusCode.ILLEGAL_ARGUMENT);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupRequest_updateGroupWithUnknownAttribute_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setName("new name")
                        .setIntro("new intro")
                        .putAllUserDefinedAttributes(Map.of("key-unknown",
                                Value.newBuilder()
                                        .setStringValue("A wrong value")
                                        .build())))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultCodes(resultMono, ResponseStatusCode.ILLEGAL_ARGUMENT);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupRequest_updateGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setName("new name")
                        .setIntro("new intro")
                        .putAllUserDefinedAttributes(
                                SharedBusinessDataConst.USER_DEFINED_ATTRIBUTES_FOR_UPSERT))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_LAST - 1)
    void handleUpdateGroupRequest_transferOwnership_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setSuccessorId(GROUP_SUCCESSOR)
                        .setQuitAfterTransfer(false))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);

        request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setSuccessorId(USER_ID_1)
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
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setMuteEndDate(System.currentTimeMillis() + 100_000))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupRequest_unmuteGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupRequest(UpdateGroupRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setMuteEndDate(0))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
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
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
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
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setMemberId(GROUP_MEMBER_ID)
                        .setName("myname"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupMemberRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupMemberRequest_muteGroupMember_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupMemberRequest(UpdateGroupMemberRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setMemberId(GROUP_MEMBER_ID)
                        .setMuteEndDate(System.currentTimeMillis() + 100_000))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateGroupMemberRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateGroupMemberRequest_unmuteGroupMember_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateGroupMemberRequest(UpdateGroupMemberRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setMemberId(GROUP_MEMBER_ID)
                        .setMuteEndDate(0))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
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
                        .addGroupIds(groupIdWithInvitationStrategyOwnerManager))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            Group group = result.response()
                    .getGroupsWithVersion()
                    .getGroups(0);
            assertThat(group.getId()).isEqualTo(groupIdWithInvitationStrategyOwnerManager);
            assertThat(group.getUserDefinedAttributesMap()).containsExactlyInAnyOrderEntriesOf(
                    SharedBusinessDataConst.EXPECTED_FOUND_USER_DEFINED_ATTRIBUTES);
        });
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryJoinedGroupIdsRequest_queryJoinedGroupIds_shouldEqualNewGroupId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryJoinedGroupIdsRequest(QueryJoinedGroupIdsRequest.newBuilder()
                        .setLastUpdatedDate(0))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryJoinedGroupIdsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.response()
                        .getLongsWithVersion()
                        .getLongsList()).contains(groupIdWithInvitationStrategyOwnerManager));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryJoinedGroupsRequest_queryJoinedGroupInfos_shouldEqualNewGroupId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryJoinedGroupInfosRequest(QueryJoinedGroupInfosRequest.newBuilder())
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryJoinedGroupsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.response()
                        .getGroupsWithVersion()
                        .getGroupsList())
                        .anyMatch(group -> groupIdWithInvitationStrategyOwnerManager
                                .equals(group.getId())));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupBlockedUserIdsRequest_queryBlockedUserIds_shouldEqualBlockedUserId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupBlockedUserIdsRequest(QueryGroupBlockedUserIdsRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleQueryGroupBlockedUserIdsRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.response()
                        .getLongsWithVersion()
                        .getLongsList()).contains(GROUP_BLOCKED_USER_ID));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupBlockedUsersInfosRequest_queryBlockedUserInfos_shouldEqualBlockedUserId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupBlockedUserInfosRequest(QueryGroupBlockedUserInfosRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleQueryGroupBlockedUsersInfosRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.response()
                        .getUserInfosWithVersion()
                        .getUserInfos(0)
                        .getId()).isEqualTo(GROUP_BLOCKED_USER_ID));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupInvitationsRequest_queryInvitations_shouldEqualNewInvitationId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupInvitationsRequest(QueryGroupInvitationsRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManagerRequiringApproval))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupInvitationsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.response()
                        .getGroupInvitationsWithVersion()
                        .getGroupInvitations(0)
                        .getId()).isEqualTo(groupInvitationId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupJoinRequestsRequest_queryJoinRequests_shouldEqualNewJoinRequestId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupJoinRequestsRequest(QueryGroupJoinRequestsRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleQueryGroupJoinRequestsRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.response()
                        .getGroupJoinRequestsWithVersion()
                        .getGroupJoinRequests(0)
                        .getId()).isEqualTo(groupJoinRequestId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupJoinQuestions_queryGroupJoinQuestions_shouldEqualNewGroupQuestionId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupJoinQuestionsRequest(QueryGroupJoinQuestionsRequest.newBuilder()
                        .setGroupId(groupIdWithJoinStrategyQuestion)
                        .setWithAnswers(true))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleQueryGroupJoinQuestionsRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.response()
                        .getGroupJoinQuestionsWithVersion()
                        .getGroupJoinQuestions(0)
                        .getId()).isEqualTo(groupJoinQuestionId));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupMembersRequest_queryGroupMembers_shouldEqualNewMemberId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupMembersRequest(QueryGroupMembersRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setWithStatus(true))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupMembersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.response()
                        .getGroupMembersWithVersion()
                        .getGroupMembersList()
                        .stream()
                        .map(member -> member.getUserId())
                        .collect(Collectors.toList()))
                        .containsExactlyInAnyOrder(USER_ID_1, GROUP_SUCCESSOR, GROUP_MEMBER_ID));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryGroupMembersRequest_queryGroupMembersByMemberIds_shouldEqualNewMemberId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryGroupMembersRequest(QueryGroupMembersRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .addMemberIds(GROUP_MEMBER_ID)
                        .setWithStatus(true))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryGroupMembersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.response()
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
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCheckGroupQuestionAnswerRequest()
                        .handle(clientRequest);
        assertResult(resultMono, result -> {
            assertThat(result.response()
                    .getGroupJoinQuestionAnswerResult()
                    .getQuestionIdsList()).contains(groupJoinQuestionId);
        }, ResponseStatusCode.OK, ResponseStatusCode.GROUP_MEMBER_ANSWER_GROUP_QUESTION);
    }

    // Delete

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleDeleteGroupMembersRequest_removeGroupMembers_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteGroupMembersRequest(DeleteGroupMembersRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .addMemberIds(GROUP_MEMBER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleDeleteGroupMembersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_LOWEST_PRIORITY)
    void handleDeleteGroupJoinQuestionsRequest_deleteGroupJoinQuestions_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteGroupJoinQuestionsRequest(DeleteGroupJoinQuestionsRequest.newBuilder()
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .addQuestionIds(groupJoinQuestionId))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
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
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setUserId(GROUP_BLOCKED_USER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
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
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
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
                new ClientRequest(USER_ID_2, USER_DEVICE, USER_IP, REQUEST_ID, request);
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
                        .setGroupId(groupIdWithInvitationStrategyOwnerManager)
                        .setQuitAfterTransfer(false))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
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
                new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateGroupRequest()
                .handle(clientRequest);
        AtomicLong readyToDeleteGroupId = new AtomicLong();
        assertResultIsOk(resultMono,
                result -> readyToDeleteGroupId
                        .set(NotificationUtil.getLongOrThrow(result.response())));

        request = TurmsRequest.newBuilder()
                .setDeleteGroupRequest(DeleteGroupRequest.newBuilder()
                        .setGroupId(readyToDeleteGroupId.get()))
                .build();
        clientRequest = new ClientRequest(USER_ID_1, USER_DEVICE, USER_IP, REQUEST_ID, request);
        resultMono = getController().handleDeleteGroupRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

}