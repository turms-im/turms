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

package im.turms.service.domain.group.access.servicerequest.controller;

import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.group.CreateGroupRequest;
import im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest;
import im.turms.server.common.access.client.dto.request.group.QueryGroupRequest;
import im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest;
import im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest;
import im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest;
import im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest;
import im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest;
import im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest;
import im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest;
import im.turms.server.common.access.client.dto.request.group.member.CreateGroupMemberRequest;
import im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMemberRequest;
import im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest;
import im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.domain.group.bo.GroupQuestionIdAndAnswer;
import im.turms.service.domain.group.service.GroupBlocklistService;
import im.turms.service.domain.group.service.GroupInvitationService;
import im.turms.service.domain.group.service.GroupJoinRequestService;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.group.service.GroupQuestionService;
import im.turms.service.domain.group.service.GroupService;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_BLOCKED_USER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_INVITATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_JOIN_QUESTION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_JOIN_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_MEMBER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_BLOCKED_USER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_INVITATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_JOIN_QUESTION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_JOIN_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_MEMBER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_BLOCKED_USER_IDS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_INVITATIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_JOIN_QUESTIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_JOIN_REQUESTS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_MEMBERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_JOINED_GROUP_IDS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_JOINED_GROUP_INFOS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_JOIN_QUESTION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_MEMBER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class GroupServiceController {

    private final Node node;
    private final GroupService groupService;
    private final GroupBlocklistService groupBlocklistService;
    private final GroupQuestionService groupQuestionService;
    private final GroupInvitationService groupInvitationService;
    private final GroupJoinRequestService groupJoinRequestService;
    private final GroupMemberService groupMemberService;

    public GroupServiceController(
            Node node,
            GroupService groupService,
            GroupBlocklistService groupBlocklistService,
            GroupQuestionService groupQuestionService,
            GroupInvitationService groupInvitationService,
            GroupJoinRequestService groupJoinRequestService,
            GroupMemberService groupMemberService) {
        this.node = node;
        this.groupService = groupService;
        this.groupBlocklistService = groupBlocklistService;
        this.groupQuestionService = groupQuestionService;
        this.groupInvitationService = groupInvitationService;
        this.groupJoinRequestService = groupJoinRequestService;
        this.groupMemberService = groupMemberService;
    }

    @ServiceRequestMapping(CREATE_GROUP_REQUEST)
    public ClientRequestHandler handleCreateGroupRequest() {
        return clientRequest -> {
            CreateGroupRequest request = clientRequest.turmsRequest().getCreateGroupRequest();
            String intro = request.hasIntro() ? request.getIntro() : null;
            String announcement = request.hasAnnouncement() ? request.getAnnouncement() : null;
            Integer minimumScore = request.hasMinimumScore() ? request.getMinimumScore() : null;
            Long groupTypeId = request.hasGroupTypeId() ? request.getGroupTypeId() : null;
            Date muteEndDate = request.hasMuteEndDate() ? new Date(request.getMuteEndDate()) : null;
            Long creatorIdAndOwnerId = clientRequest.userId();
            return groupService.authAndCreateGroup(
                            creatorIdAndOwnerId,
                            creatorIdAndOwnerId,
                            request.getName(),
                            intro,
                            announcement,
                            minimumScore,
                            groupTypeId,
                            muteEndDate,
                            null,
                            null,
                            node.getSharedProperties().getService().getGroup().isActivateGroupWhenCreated())
                    .map(group -> RequestHandlerResultFactory.get(group.getId()));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_REQUEST)
    public ClientRequestHandler handleDeleteGroupRequest() {
        return clientRequest -> {
            DeleteGroupRequest request = clientRequest.turmsRequest().getDeleteGroupRequest();
            return groupService.authAndDeleteGroup(clientRequest.userId(), request.getGroupId())
                    .then(Mono.defer(() -> {
                        if (node.getSharedProperties().getService().getNotification().isNotifyMembersAfterGroupDeleted()) {
                            return groupMemberService.queryGroupMemberIds(request.getGroupId())
                                    .collect(Collectors.toSet())
                                    .map(memberIds -> memberIds.isEmpty()
                                            ? RequestHandlerResultFactory.OK
                                            : RequestHandlerResultFactory.get(memberIds, clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResultFactory.OK);
                    }));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_REQUEST)
    public ClientRequestHandler handleQueryGroupRequest() {
        return clientRequest -> {
            QueryGroupRequest request = clientRequest.turmsRequest().getQueryGroupRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return groupService.queryGroupWithVersion(request.getGroupId(), lastUpdatedDate)
                    .map(groupsWithVersion -> RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                            .setGroupsWithVersion(groupsWithVersion)
                            .build()));
        };
    }

    @ServiceRequestMapping(QUERY_JOINED_GROUP_IDS_REQUEST)
    public ClientRequestHandler handleQueryJoinedGroupIdsRequest() {
        return clientRequest -> {
            QueryJoinedGroupIdsRequest request = clientRequest.turmsRequest()
                    .getQueryJoinedGroupIdsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate()) : null;
            return groupService.queryJoinedGroupIdsWithVersion(
                            clientRequest.userId(),
                            lastUpdatedDate)
                    .map(idsWithVersion -> RequestHandlerResultFactory.get(TurmsNotification.Data
                            .newBuilder()
                            .setIdsWithVersion(idsWithVersion)
                            .build()));
        };
    }

    @ServiceRequestMapping(QUERY_JOINED_GROUP_INFOS_REQUEST)
    public ClientRequestHandler handleQueryJoinedGroupsRequest() {
        return clientRequest -> {
            QueryJoinedGroupInfosRequest request = clientRequest.turmsRequest()
                    .getQueryJoinedGroupInfosRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate()) : null;
            return groupService.queryJoinedGroupsWithVersion(
                            clientRequest.userId(),
                            lastUpdatedDate)
                    .map(groupsWithVersion -> RequestHandlerResultFactory.get(TurmsNotification.Data
                            .newBuilder()
                            .setGroupsWithVersion(groupsWithVersion)
                            .build()));
        };
    }

    @ServiceRequestMapping(UPDATE_GROUP_REQUEST)
    public ClientRequestHandler handleUpdateGroupRequest() {
        return clientRequest -> {
            UpdateGroupRequest request = clientRequest.turmsRequest().getUpdateGroupRequest();
            Long successorId = request.hasSuccessorId() ? request.getSuccessorId() : null;
            Mono<Void> updateMono;
            if (successorId == null) {
                Integer minimumScore = request.hasMinimumScore() ? request.getMinimumScore() : null;
                Long groupTypeId = request.hasGroupTypeId() ? request.getGroupTypeId() : null;
                String groupName = request.hasGroupName() ? request.getGroupName() : null;
                String intro = request.hasIntro() ? request.getIntro() : null;
                String announcement = request.hasAnnouncement() ? request.getAnnouncement() : null;
                Date muteEndDate = request.hasMuteEndDate() ? new Date(request.getMuteEndDate()) : null;
                updateMono = groupService.authAndUpdateGroupInformation(
                        clientRequest.userId(),
                        request.getGroupId(),
                        groupTypeId,
                        null,
                        null,
                        groupName,
                        intro,
                        announcement,
                        minimumScore,
                        null,
                        null,
                        null,
                        muteEndDate,
                        null);
            } else {
                boolean quitAfterTransfer = request.hasQuitAfterTransfer() && request.getQuitAfterTransfer();
                updateMono = groupService
                        .authAndTransferGroupOwnership(clientRequest.userId(), request.getGroupId(), successorId, quitAfterTransfer,
                                null);
            }
            return updateMono.then(Mono.defer(() -> {
                if (node.getSharedProperties().getService().getNotification().isNotifyMembersAfterGroupUpdated()) {
                    return groupMemberService.queryGroupMemberIds(request.getGroupId())
                            .collect(Collectors.toSet())
                            .map(memberIds -> memberIds.isEmpty()
                                    ? RequestHandlerResultFactory.OK
                                    : RequestHandlerResultFactory.get(memberIds, clientRequest.turmsRequest()));
                }
                return Mono.just(RequestHandlerResultFactory.OK);
            }));
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_BLOCKED_USER_REQUEST)
    public ClientRequestHandler handleCreateGroupBlockedUserRequest() {
        return clientRequest -> {
            CreateGroupBlockedUserRequest request = clientRequest.turmsRequest()
                    .getCreateGroupBlockedUserRequest();
            return groupBlocklistService.blockUser(
                            clientRequest.userId(),
                            request.getGroupId(),
                            request.getUserId(),
                            null)
                    .then(Mono.fromCallable(() ->
                            node.getSharedProperties().getService().getNotification().isNotifyUserAfterBlockedByGroup()
                                    ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.turmsRequest())
                                    : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_BLOCKED_USER_REQUEST)
    public ClientRequestHandler handleDeleteGroupBlockedUserRequest() {
        return clientRequest -> {
            DeleteGroupBlockedUserRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupBlockedUserRequest();
            return groupBlocklistService.unblockUser(
                            clientRequest.userId(),
                            request.getGroupId(),
                            request.getUserId(),
                            null,
                            true)
                    .then(Mono.fromCallable(() ->
                            node.getSharedProperties().getService().getNotification().isNotifyUserAfterUnblockedByGroup()
                                    ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.turmsRequest())
                                    : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_BLOCKED_USER_IDS_REQUEST)
    public ClientRequestHandler handleQueryGroupBlockedUserIdsRequest() {
        return clientRequest -> {
            QueryGroupBlockedUserIdsRequest request = clientRequest.turmsRequest()
                    .getQueryGroupBlockedUserIdsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ?
                    new Date(request.getLastUpdatedDate()) : null;
            return groupBlocklistService.queryGroupBlockedUserIdsWithVersion(
                            request.getGroupId(),
                            lastUpdatedDate)
                    .map(version -> RequestHandlerResultFactory.get(TurmsNotification.Data
                            .newBuilder()
                            .setIdsWithVersion(version)
                            .build()));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST)
    public ClientRequestHandler handleQueryGroupBlockedUsersInfosRequest() {
        return clientRequest -> {
            QueryGroupBlockedUserInfosRequest request = clientRequest.turmsRequest()
                    .getQueryGroupBlockedUserInfosRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate()) : null;
            return groupBlocklistService.queryGroupBlockedUserInfosWithVersion(
                            request.getGroupId(),
                            lastUpdatedDate)
                    .map(version -> RequestHandlerResultFactory.get(TurmsNotification.Data
                            .newBuilder()
                            .setUsersInfosWithVersion(version)
                            .build()));
        };
    }

    @ServiceRequestMapping(CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST)
    public ClientRequestHandler handleCheckGroupQuestionAnswerRequest() {
        return clientRequest -> {
            CheckGroupJoinQuestionsAnswersRequest request = clientRequest.turmsRequest()
                    .getCheckGroupJoinQuestionsAnswersRequest();
            Set<GroupQuestionIdAndAnswer> idAndAnswerPairs =
                    CollectionUtil.newSetWithExpectedSize(request.getQuestionIdToAnswerCount());
            for (Map.Entry<Long, String> entry : request.getQuestionIdToAnswerMap().entrySet()) {
                idAndAnswerPairs.add(new GroupQuestionIdAndAnswer(entry.getKey(), entry.getValue()));
            }
            return groupQuestionService.checkGroupQuestionAnswerAndJoin(clientRequest.userId(), idAndAnswerPairs)
                    .map(answerResult -> RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                            .setGroupJoinQuestionAnswerResult(answerResult).build()));
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_INVITATION_REQUEST)
    public ClientRequestHandler handleCreateGroupInvitationRequestRequest() {
        return clientRequest -> {
            CreateGroupInvitationRequest request = clientRequest.turmsRequest()
                    .getCreateGroupInvitationRequest();
            return groupInvitationService.authAndCreateGroupInvitation(
                            request.getGroupId(),
                            clientRequest.userId(),
                            request.getInviteeId(),
                            request.getContent())
                    .map(invitation -> node.getSharedProperties().getService().getNotification().isNotifyUserAfterInvitedByGroup()
                            ? RequestHandlerResultFactory.get(invitation.getId(), request.getInviteeId(), clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_JOIN_REQUEST_REQUEST)
    public ClientRequestHandler handleCreateGroupJoinRequestRequest() {
        return clientRequest -> {
            CreateGroupJoinRequestRequest request = clientRequest.turmsRequest()
                    .getCreateGroupJoinRequestRequest();
            return groupJoinRequestService.authAndCreateGroupJoinRequest(
                            clientRequest.userId(),
                            request.getGroupId(),
                            request.getContent())
                    .flatMap(joinRequest -> {
                        if (node.getSharedProperties().getService().getNotification().isNotifyOwnerAndManagersAfterReceivingJoinRequest()) {
                            return groupMemberService.queryGroupManagersAndOwnerId(request.getGroupId())
                                    .collect(Collectors.toSet())
                                    .map(recipientIds -> recipientIds.isEmpty()
                                            ? RequestHandlerResultFactory.OK
                                            : RequestHandlerResultFactory
                                            .get(joinRequest.getId(), recipientIds, false, clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResultFactory.OK);
                    });
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_JOIN_QUESTION_REQUEST)
    public ClientRequestHandler handleCreateGroupQuestionRequest() {
        return clientRequest -> {
            CreateGroupJoinQuestionRequest request = clientRequest.turmsRequest()
                    .getCreateGroupJoinQuestionRequest();
            if (request.getAnswersCount() == 0) {
                return Mono.just(RequestHandlerResultFactory.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The answers must not be empty"));
            }
            Set<String> answers = CollectionUtil.newSet(request.getAnswersList());
            int score = request.getScore();
            return score >= 0
                    ? groupQuestionService
                    .authAndCreateGroupJoinQuestion(clientRequest.userId(), request.getGroupId(), request.getQuestion(), answers,
                            score)
                    .map(question -> RequestHandlerResultFactory.get(question.getId()))
                    : Mono.just(
                    RequestHandlerResultFactory.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The score must be greater than or equal to 0"));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_INVITATION_REQUEST)
    public ClientRequestHandler handleDeleteGroupInvitationRequest() {
        return clientRequest -> {
            DeleteGroupInvitationRequest request = clientRequest.turmsRequest().getDeleteGroupInvitationRequest();
            return groupInvitationService.queryInviteeIdByInvitationId(request.getInvitationId())
                    .flatMap(inviteeId -> groupInvitationService.recallPendingGroupInvitation(
                                    clientRequest.userId(),
                                    request.getInvitationId())
                            .then(Mono.fromCallable(() -> node.getSharedProperties().getService().getNotification()
                                    .isNotifyInviteeAfterGroupInvitationRecalled()
                                    ? RequestHandlerResultFactory.get(inviteeId, clientRequest.turmsRequest())
                                    : RequestHandlerResultFactory.OK)));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_JOIN_REQUEST_REQUEST)
    public ClientRequestHandler handleDeleteGroupJoinRequestRequest() {
        return clientRequest -> {
            DeleteGroupJoinRequestRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupJoinRequestRequest();
            return groupJoinRequestService.recallPendingGroupJoinRequest(
                            clientRequest.userId(),
                            request.getRequestId())
                    .then(Mono.defer(() -> {
                        if (node.getSharedProperties().getService().getNotification()
                                .isNotifyOwnerAndManagersAfterGroupJoinRequestRecalled()) {
                            return groupJoinRequestService.queryGroupId(request.getRequestId())
                                    .flatMap(groupId -> groupMemberService.queryGroupManagersAndOwnerId(groupId)
                                            .collect(Collectors.toSet())
                                            .map(ids -> ids.isEmpty()
                                                    ? RequestHandlerResultFactory.OK
                                                    : RequestHandlerResultFactory.get(ids, clientRequest.turmsRequest())));
                        }
                        return Mono.just(RequestHandlerResultFactory.OK);
                    }));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_JOIN_QUESTION_REQUEST)
    public ClientRequestHandler handleDeleteGroupJoinQuestionRequest() {
        return clientRequest -> {
            DeleteGroupJoinQuestionRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupJoinQuestionRequest();
            return groupQuestionService.authAndDeleteGroupJoinQuestion(
                            clientRequest.userId(),
                            request.getQuestionId())
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_INVITATIONS_REQUEST)
    public ClientRequestHandler handleQueryGroupInvitationsRequest() {
        return clientRequest -> {
            QueryGroupInvitationsRequest request = clientRequest.turmsRequest()
                    .getQueryGroupInvitationsRequest();
            Long groupId = request.hasGroupId() ? request.getGroupId() : null;
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate()) : null;
            if (groupId == null) {
                return groupInvitationService.queryUserGroupInvitationsWithVersion(
                                clientRequest.userId(),
                                request.hasAreSentByMe() && request.getAreSentByMe(),
                                lastUpdatedDate)
                        .map(groupInvitationsWithVersion -> RequestHandlerResultFactory.get(TurmsNotification.Data
                                .newBuilder()
                                .setGroupInvitationsWithVersion(groupInvitationsWithVersion)
                                .build()));
            }
            return groupInvitationService.queryGroupInvitationsWithVersion(
                            clientRequest.userId(),
                            groupId,
                            lastUpdatedDate)
                    .map(groupInvitationsWithVersion -> RequestHandlerResultFactory.get(
                            TurmsNotification.Data.newBuilder()
                                    .setGroupInvitationsWithVersion(groupInvitationsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_JOIN_REQUESTS_REQUEST)
    public ClientRequestHandler handleQueryGroupJoinRequestsRequest() {
        return clientRequest -> {
            QueryGroupJoinRequestsRequest request = clientRequest.turmsRequest()
                    .getQueryGroupJoinRequestsRequest();
            Long groupId = request.hasGroupId() ? request.getGroupId() : null;
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate()) : null;
            return groupJoinRequestService.queryGroupJoinRequestsWithVersion(
                            clientRequest.userId(),
                            groupId,
                            lastUpdatedDate)
                    .map(groupJoinRequestsWithVersion -> RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                            .setGroupJoinRequestsWithVersion(groupJoinRequestsWithVersion)
                            .build()));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_JOIN_QUESTIONS_REQUEST)
    public ClientRequestHandler handleQueryGroupJoinQuestionsRequest() {
        return clientRequest -> {
            QueryGroupJoinQuestionsRequest request = clientRequest.turmsRequest()
                    .getQueryGroupJoinQuestionsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate()) : null;
            return groupQuestionService.queryGroupJoinQuestionsWithVersion(
                            clientRequest.userId(),
                            request.getGroupId(),
                            request.getWithAnswers(),
                            lastUpdatedDate)
                    .map(groupJoinQuestionsWithVersion -> RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                            .setGroupJoinQuestionsWithVersion(groupJoinQuestionsWithVersion)
                            .build()));
        };
    }

    @ServiceRequestMapping(UPDATE_GROUP_JOIN_QUESTION_REQUEST)
    public ClientRequestHandler handleUpdateGroupJoinQuestionRequest() {
        return clientRequest -> {
            UpdateGroupJoinQuestionRequest request = clientRequest.turmsRequest()
                    .getUpdateGroupJoinQuestionRequest();
            Set<String> answers = request.getAnswersList().isEmpty() ?
                    null : CollectionUtil.newSet(request.getAnswersList());
            String question = request.hasQuestion() ? request.getQuestion() : null;
            Integer score = request.hasScore() ? request.getScore() : null;
            return groupQuestionService.authAndUpdateGroupJoinQuestion(
                            clientRequest.userId(),
                            request.getQuestionId(),
                            question,
                            answers,
                            score)
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_MEMBER_REQUEST)
    public ClientRequestHandler handleCreateGroupMemberRequest() {
        return clientRequest -> {
            CreateGroupMemberRequest request = clientRequest.turmsRequest().getCreateGroupMemberRequest();
            String name = request.hasName() ? request.getName() : null;
            Date muteEndDate = request.hasMuteEndDate() ? new Date(request.getMuteEndDate()) : null;
            GroupMemberRole role = request.getRole();
            if (role == GroupMemberRole.UNRECOGNIZED) {
                role = GroupMemberRole.MEMBER;
            } else if (role == GroupMemberRole.OWNER) {
                return Mono.just(RequestHandlerResultFactory
                        .get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The role of the new member must not be OWNER"));
            }
            return groupMemberService.authAndAddGroupMember(
                            clientRequest.userId(),
                            request.getGroupId(),
                            request.getUserId(),
                            role,
                            name,
                            muteEndDate,
                            null)
                    .map(member -> member != null &&
                            node.getSharedProperties().getService().getNotification().isNotifyUserAfterAddedToGroupByOthers()
                            ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_MEMBER_REQUEST)
    public ClientRequestHandler handleDeleteGroupMemberRequest() {
        return clientRequest -> {
            DeleteGroupMemberRequest request = clientRequest.turmsRequest().getDeleteGroupMemberRequest();
            Long successorId = request.hasSuccessorId() ? request.getSuccessorId() : null;
            Boolean quitAfterTransfer = request.hasQuitAfterTransfer() ? request.getQuitAfterTransfer() : null;
            return groupMemberService.authAndDeleteGroupMember(
                            clientRequest.userId(),
                            request.getGroupId(),
                            request.getMemberId(),
                            successorId,
                            quitAfterTransfer)
                    .then(Mono.fromCallable(
                            () -> node.getSharedProperties().getService().getNotification().isNotifyUserAfterRemovedFromGroupByOthers()
                                    && !clientRequest.userId().equals(request.getMemberId())
                                    ? RequestHandlerResultFactory.get(request.getMemberId(), clientRequest.turmsRequest())
                                    : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_MEMBERS_REQUEST)
    public ClientRequestHandler handleQueryGroupMembersRequest() {
        return clientRequest -> {
            QueryGroupMembersRequest request = clientRequest.turmsRequest().getQueryGroupMembersRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate()) : null;
            Set<Long> memberIds = request.getMemberIdsCount() != 0 ?
                    CollectionUtil.newSet(request.getMemberIdsList()) : null;
            boolean withStatus = request.hasWithStatus() && request.getWithStatus();
            if (request.getMemberIdsCount() > 0) {
                return groupMemberService.authAndQueryGroupMembers(
                                clientRequest.userId(),
                                request.getGroupId(),
                                memberIds,
                                withStatus)
                        .map(groupMembersWithVersion -> RequestHandlerResultFactory.get(
                                TurmsNotification.Data.newBuilder()
                                        .setGroupMembersWithVersion(groupMembersWithVersion)
                                        .build()));
            }
            return groupMemberService.authAndQueryGroupMembersWithVersion(
                            clientRequest.userId(),
                            request.getGroupId(),
                            lastUpdatedDate,
                            withStatus)
                    .map(groupMembersWithVersion -> RequestHandlerResultFactory.get(
                            TurmsNotification.Data.newBuilder()
                                    .setGroupMembersWithVersion(groupMembersWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(UPDATE_GROUP_MEMBER_REQUEST)
    public ClientRequestHandler handleUpdateGroupMemberRequest() {
        return clientRequest -> {
            UpdateGroupMemberRequest request = clientRequest.turmsRequest().getUpdateGroupMemberRequest();
            String name = request.hasName() ? request.getName() : null;
            GroupMemberRole role = request.getRole() == GroupMemberRole.UNRECOGNIZED ? null : request.getRole();
            Date muteEndDate = request.hasMuteEndDate() ? new Date(request.getMuteEndDate()) : null;
            return groupMemberService.authAndUpdateGroupMember(
                            clientRequest.userId(),
                            request.getGroupId(),
                            request.getMemberId(),
                            name,
                            role,
                            muteEndDate)
                    .then(Mono.defer(() -> {
                        if (node.getSharedProperties().getService().getNotification()
                                .isNotifyMembersAfterOtherMemberInfoUpdated()) {
                            return groupMemberService.queryGroupMemberIds(request.getGroupId())
                                    .collect(Collectors.toSet())
                                    .map(groupMemberIds -> groupMemberIds.isEmpty()
                                            ? RequestHandlerResultFactory.OK
                                            : RequestHandlerResultFactory.get(groupMemberIds, clientRequest.turmsRequest()));
                        } else if (!clientRequest.userId().equals(request.getMemberId())
                                && node.getSharedProperties().getService().getNotification().isNotifyMemberAfterInfoUpdatedByOthers()) {
                            return Mono.just(RequestHandlerResultFactory.get(
                                    clientRequest.userId(),
                                    clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResultFactory.OK);
                    }));
        };
    }

}