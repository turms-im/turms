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

package im.turms.turms.workflow.access.servicerequest.controller;

import im.turms.common.constant.GroupMemberRole;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.group.*;
import im.turms.common.model.dto.request.group.blacklist.CreateGroupBlacklistedUserRequest;
import im.turms.common.model.dto.request.group.blacklist.DeleteGroupBlacklistedUserRequest;
import im.turms.common.model.dto.request.group.blacklist.QueryGroupBlacklistedUserIdsRequest;
import im.turms.common.model.dto.request.group.blacklist.QueryGroupBlacklistedUserInfosRequest;
import im.turms.common.model.dto.request.group.enrollment.*;
import im.turms.common.model.dto.request.group.member.CreateGroupMemberRequest;
import im.turms.common.model.dto.request.group.member.DeleteGroupMemberRequest;
import im.turms.common.model.dto.request.group.member.QueryGroupMembersRequest;
import im.turms.common.model.dto.request.group.member.UpdateGroupMemberRequest;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.util.MapUtil;
import im.turms.turms.bo.GroupQuestionIdAndAnswer;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.turms.workflow.service.impl.group.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.*;

/**
 * @author James Chen
 */
@Controller
public class GroupServiceController {

    private final Node node;
    private final GroupService groupService;
    private final GroupBlacklistService groupBlacklistService;
    private final GroupQuestionService groupQuestionService;
    private final GroupInvitationService groupInvitationService;
    private final GroupJoinRequestService groupJoinRequestService;
    private final GroupMemberService groupMemberService;

    public GroupServiceController(
            Node node,
            GroupService groupService,
            GroupBlacklistService groupBlacklistService,
            GroupQuestionService groupQuestionService,
            GroupInvitationService groupInvitationService,
            GroupJoinRequestService groupJoinRequestService,
            GroupMemberService groupMemberService) {
        this.node = node;
        this.groupService = groupService;
        this.groupBlacklistService = groupBlacklistService;
        this.groupQuestionService = groupQuestionService;
        this.groupInvitationService = groupInvitationService;
        this.groupJoinRequestService = groupJoinRequestService;
        this.groupMemberService = groupMemberService;
    }

    @ServiceRequestMapping(CREATE_GROUP_REQUEST)
    public ClientRequestHandler handleCreateGroupRequest() {
        return clientRequest -> {
            CreateGroupRequest request = clientRequest.getTurmsRequest().getCreateGroupRequest();
            String intro = request.hasIntro() ? request.getIntro().getValue() : null;
            String announcement = request.hasAnnouncement() ? request.getAnnouncement().getValue() : null;
            Integer minimumScore = request.hasMinimumScore() ? request.getMinimumScore().getValue() : null;
            Long groupTypeId = request.hasGroupTypeId() ? request.getGroupTypeId().getValue() : null;
            Date muteEndDate = request.hasMuteEndDate() ? new Date(request.getMuteEndDate().getValue()) : null;
            Long creatorIdAndOwnerId = clientRequest.getUserId();
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
            DeleteGroupRequest request = clientRequest.getTurmsRequest().getDeleteGroupRequest();
            return groupMemberService
                    .isOwner(clientRequest.getUserId(), request.getGroupId())
                    .flatMap(authenticated -> {
                        if (!authenticated) {
                            return Mono.just(RequestHandlerResultFactory.get(TurmsStatusCode.UNAUTHORIZED));
                        }
                        if (node.getSharedProperties().getService().getNotification().isNotifyMembersAfterGroupDeleted()) {
                            return groupService.queryGroupMembersIds(request.getGroupId())
                                    .collect(Collectors.toSet())
                                    .flatMap(memberIds -> groupService.deleteGroupsAndGroupMembers(Set.of(request.getGroupId()), null)
                                            .map(deleted -> {
                                                if (deleted != null && deleted) {
                                                    return memberIds.isEmpty()
                                                            ? RequestHandlerResultFactory.ok()
                                                            : RequestHandlerResultFactory.get(memberIds, clientRequest.getTurmsRequest());
                                                } else {
                                                    return RequestHandlerResultFactory.fail();
                                                }
                                            }));
                        } else {
                            return groupService.deleteGroupsAndGroupMembers(
                                    Set.of(request.getGroupId()),
                                    null)
                                    .map(RequestHandlerResultFactory::okIfTrue);
                        }
                    });
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_REQUEST)
    public ClientRequestHandler handleQueryGroupRequest() {
        return clientRequest -> {
            QueryGroupRequest request = clientRequest.getTurmsRequest().getQueryGroupRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate().getValue())
                    : null;
            return groupService.queryGroupWithVersion(request.getGroupId(), lastUpdatedDate)
                    .map(groupsWithVersion -> RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                            .setGroupsWithVersion(groupsWithVersion)
                            .build()));
        };
    }

    @ServiceRequestMapping(QUERY_JOINED_GROUP_IDS_REQUEST)
    public ClientRequestHandler handleQueryJoinedGroupsIdsRequest() {
        return clientRequest -> {
            QueryJoinedGroupIdsRequest request = clientRequest.getTurmsRequest()
                    .getQueryJoinedGroupIdsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate().getValue()) : null;
            return groupService.queryJoinedGroupIdsWithVersion(
                    clientRequest.getUserId(),
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
            QueryJoinedGroupInfosRequest request = clientRequest.getTurmsRequest()
                    .getQueryJoinedGroupInfosRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate().getValue()) : null;
            return groupService.queryJoinedGroupsWithVersion(
                    clientRequest.getUserId(),
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
            UpdateGroupRequest request = clientRequest.getTurmsRequest().getUpdateGroupRequest();
            Integer minimumScore = request.hasMinimumScore() ? request.getMinimumScore().getValue() : null;
            Long groupTypeId = request.hasGroupTypeId() ? request.getGroupTypeId().getValue() : null;
            Long successorId = request.hasSuccessorId() ? request.getSuccessorId().getValue() : null;
            String groupName = request.hasGroupName() ? request.getGroupName().getValue() : null;
            String intro = request.hasIntro() ? request.getIntro().getValue() : null;
            String announcement = request.hasAnnouncement() ? request.getAnnouncement().getValue() : null;
            Date muteEndDate = request.hasMuteEndDate() ? new Date(request.getMuteEndDate().getValue()) : null;
            boolean quitAfterTransfer = request.hasQuitAfterTransfer() && request.getQuitAfterTransfer().getValue();
            return groupService.authAndUpdateGroup(
                    clientRequest.getUserId(),
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
                    successorId,
                    quitAfterTransfer)
                    .flatMap(updated -> {
                        if (updated == null || !updated) {
                            return Mono.just(RequestHandlerResultFactory.fail());
                        }
                        if (node.getSharedProperties().getService().getNotification().isNotifyMembersAfterGroupUpdated()) {
                            return groupMemberService.queryGroupMembersIds(request.getGroupId())
                                    .collect(Collectors.toSet())
                                    .map(memberIds -> memberIds.isEmpty()
                                            ? RequestHandlerResultFactory.ok()
                                            : RequestHandlerResultFactory.get(memberIds, clientRequest.getTurmsRequest()));
                        } else {
                            return Mono.just(RequestHandlerResultFactory.ok());
                        }
                    });
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_BLACKLISTED_USER_REQUEST)
    public ClientRequestHandler handleCreateGroupBlacklistedUserRequest() {
        return clientRequest -> {
            CreateGroupBlacklistedUserRequest request = clientRequest.getTurmsRequest()
                    .getCreateGroupBlacklistedUserRequest();
            return groupBlacklistService.blacklistUser(
                    clientRequest.getUserId(),
                    request.getGroupId(),
                    request.getUserId(),
                    null)
                    .map(success -> success != null && success
                            && node.getSharedProperties().getService().getNotification().isNotifyUserAfterBlacklistedByGroup()
                            ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.getTurmsRequest())
                            : RequestHandlerResultFactory.okIfTrue(success));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_BLACKLISTED_USER_REQUEST)
    public ClientRequestHandler handleDeleteGroupBlacklistedUserRequest() {
        return clientRequest -> {
            DeleteGroupBlacklistedUserRequest request = clientRequest.getTurmsRequest()
                    .getDeleteGroupBlacklistedUserRequest();
            return groupBlacklistService.unblacklistUser(
                    clientRequest.getUserId(),
                    request.getGroupId(),
                    request.getUserId(),
                    null,
                    true)
                    .map(success -> success != null && success
                            && node.getSharedProperties().getService().getNotification().isNotifyUserAfterUnblacklistedByGroup()
                            ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.getTurmsRequest())
                            : RequestHandlerResultFactory.okIfTrue(success));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_BLACKLISTED_USER_IDS_REQUEST)
    public ClientRequestHandler handleQueryGroupBlacklistedUserIdsRequest() {
        return clientRequest -> {
            QueryGroupBlacklistedUserIdsRequest request = clientRequest.getTurmsRequest()
                    .getQueryGroupBlacklistedUserIdsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ?
                    new Date(request.getLastUpdatedDate().getValue()) : null;
            return groupBlacklistService.queryGroupBlacklistedUserIdsWithVersion(
                    request.getGroupId(),
                    lastUpdatedDate)
                    .map(version -> RequestHandlerResultFactory.get(TurmsNotification.Data
                            .newBuilder()
                            .setIdsWithVersion(version)
                            .build()));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_BLACKLISTED_USER_INFOS_REQUEST)
    public ClientRequestHandler handleQueryGroupBlacklistedUsersInfosRequest() {
        return clientRequest -> {
            QueryGroupBlacklistedUserInfosRequest request = clientRequest.getTurmsRequest()
                    .getQueryGroupBlacklistedUserInfosRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate().getValue()) : null;
            return groupBlacklistService.queryGroupBlacklistedUserInfosWithVersion(
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
            CheckGroupJoinQuestionsAnswersRequest request = clientRequest.getTurmsRequest()
                    .getCheckGroupJoinQuestionsAnswersRequest();
            Set<GroupQuestionIdAndAnswer> idAndAnswers = new HashSet<>(MapUtil.getCapability(request.getQuestionIdAndAnswerCount()));
            for (Map.Entry<Long, String> entry : request.getQuestionIdAndAnswerMap().entrySet()) {
                idAndAnswers.add(new GroupQuestionIdAndAnswer(entry.getKey(), entry.getValue()));
            }
            return groupQuestionService.checkGroupQuestionAnswerAndJoin(clientRequest.getUserId(), idAndAnswers)
                    .map(answerResult -> RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                            .setGroupJoinQuestionAnswerResult(answerResult).build()));
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_INVITATION_REQUEST)
    public ClientRequestHandler handleCreateGroupInvitationRequestRequest() {
        return clientRequest -> {
            CreateGroupInvitationRequest request = clientRequest.getTurmsRequest()
                    .getCreateGroupInvitationRequest();
            return groupInvitationService.authAndCreateGroupInvitation(
                    request.getGroupId(),
                    clientRequest.getUserId(),
                    request.getInviteeId(),
                    request.getContent())
                    .map(invitation -> node.getSharedProperties().getService().getNotification().isNotifyUserAfterInvitedByGroup()
                            ? RequestHandlerResultFactory.get(invitation.getId(), request.getInviteeId(), clientRequest.getTurmsRequest())
                            : RequestHandlerResultFactory.ok());
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_JOIN_REQUEST_REQUEST)
    public ClientRequestHandler handleCreateGroupJoinRequestRequest() {
        return clientRequest -> {
            CreateGroupJoinRequestRequest request = clientRequest.getTurmsRequest()
                    .getCreateGroupJoinRequestRequest();
            return groupJoinRequestService.authAndCreateGroupJoinRequest(
                    clientRequest.getUserId(),
                    request.getGroupId(),
                    request.getContent())
                    .flatMap(joinRequest -> {
                        if (node.getSharedProperties().getService().getNotification().isNotifyOwnerAndManagersAfterReceivingJoinRequest()) {
                            return groupMemberService.queryGroupManagersAndOwnerId(request.getGroupId())
                                    .collect(Collectors.toSet())
                                    .map(recipientsIds -> recipientsIds.isEmpty()
                                            ? RequestHandlerResultFactory.ok()
                                            : RequestHandlerResultFactory.get(joinRequest.getId(), recipientsIds, false, clientRequest.getTurmsRequest()));
                        } else {
                            return Mono.just(RequestHandlerResultFactory.ok());
                        }
                    });
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_JOIN_QUESTION_REQUEST)
    public ClientRequestHandler handleCreateGroupQuestionRequest() {
        return clientRequest -> {
            CreateGroupJoinQuestionRequest request = clientRequest.getTurmsRequest()
                    .getCreateGroupJoinQuestionRequest();
            if (request.getAnswersCount() == 0) {
                return Mono.just(RequestHandlerResultFactory.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The answers must not be empty"));
            } else {
                Set<String> answers = new HashSet<>(request.getAnswersList());
                int score = request.getScore();
                return score >= 0
                        ? groupQuestionService.authAndCreateGroupJoinQuestion(clientRequest.getUserId(), request.getGroupId(), request.getQuestion(), answers, score)
                        .map(question -> RequestHandlerResultFactory.get(question.getId()))
                        : Mono.just(RequestHandlerResultFactory.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The score must be greater than or equal to 0"));
            }
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_INVITATION_REQUEST)
    public ClientRequestHandler handleDeleteGroupInvitationRequest() {
        return clientRequest -> {
            DeleteGroupInvitationRequest request = clientRequest.getTurmsRequest().getDeleteGroupInvitationRequest();
            return groupInvitationService.queryInviteeIdByInvitationId(request.getInvitationId())
                    .flatMap(inviteeId -> groupInvitationService.recallPendingGroupInvitation(
                            clientRequest.getUserId(),
                            request.getInvitationId())
                            .map(recalled -> recalled != null && recalled
                                    && node.getSharedProperties().getService().getNotification().isNotifyInviteeAfterGroupInvitationRecalled()
                                    ? RequestHandlerResultFactory.get(inviteeId, clientRequest.getTurmsRequest())
                                    : RequestHandlerResultFactory.okIfTrue(recalled)));
        };
    }

    //TODO
    @ServiceRequestMapping(DELETE_GROUP_JOIN_REQUEST_REQUEST)
    public ClientRequestHandler handleDeleteGroupJoinRequestRequest() {
        return clientRequest -> {
            DeleteGroupJoinRequestRequest request = clientRequest.getTurmsRequest()
                    .getDeleteGroupJoinRequestRequest();
            return groupJoinRequestService.recallPendingGroupJoinRequest(
                    clientRequest.getUserId(),
                    request.getRequestId())
                    .flatMap(recalled -> {
                        if (recalled != null && recalled
                                && node.getSharedProperties().getService().getNotification().isNotifyOwnerAndManagersAfterGroupJoinRequestRecalled()) {
                            return groupJoinRequestService.queryGroupId(request.getRequestId())
                                    .flatMap(groupId -> groupMemberService.queryGroupManagersAndOwnerId(groupId)
                                            .collect(Collectors.toSet())
                                            .map(ids -> ids.isEmpty()
                                                    ? RequestHandlerResultFactory.ok()
                                                    : RequestHandlerResultFactory.get(ids, clientRequest.getTurmsRequest())));
                        } else {
                            return Mono.just(RequestHandlerResultFactory.okIfTrue(recalled));
                        }
                    });
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_JOIN_QUESTION_REQUEST)
    public ClientRequestHandler handleDeleteGroupJoinQuestionRequest() {
        return clientRequest -> {
            DeleteGroupJoinQuestionRequest request = clientRequest.getTurmsRequest()
                    .getDeleteGroupJoinQuestionRequest();
            return groupQuestionService.authAndDeleteGroupJoinQuestion(
                    clientRequest.getUserId(),
                    request.getQuestionId())
                    .map(RequestHandlerResultFactory::okIfTrue);
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_INVITATIONS_REQUEST)
    public ClientRequestHandler handleQueryGroupInvitationsRequest() {
        return clientRequest -> {
            QueryGroupInvitationsRequest request = clientRequest.getTurmsRequest()
                    .getQueryGroupInvitationsRequest();
            Long groupId = request.hasGroupId() ? request.getGroupId().getValue() : null;
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate().getValue()) : null;
            if (groupId != null) {
                return groupInvitationService.queryGroupInvitationsWithVersion(
                        clientRequest.getUserId(),
                        groupId,
                        lastUpdatedDate)
                        .map(groupInvitationsWithVersion -> RequestHandlerResultFactory.get(
                                TurmsNotification.Data.newBuilder()
                                        .setGroupInvitationsWithVersion(groupInvitationsWithVersion)
                                        .build()));
            } else {
                return groupInvitationService.queryUserGroupInvitationsWithVersion(
                        clientRequest.getUserId(),
                        request.hasAreSentByMe() && request.getAreSentByMe().getValue(),
                        lastUpdatedDate)
                        .map(groupInvitationsWithVersion -> RequestHandlerResultFactory.get(TurmsNotification.Data
                                .newBuilder()
                                .setGroupInvitationsWithVersion(groupInvitationsWithVersion)
                                .build()));
            }
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_JOIN_REQUESTS_REQUEST)
    public ClientRequestHandler handleQueryGroupJoinRequestsRequest() {
        return clientRequest -> {
            QueryGroupJoinRequestsRequest request = clientRequest.getTurmsRequest()
                    .getQueryGroupJoinRequestsRequest();
            Long groupId = request.hasGroupId() ? request.getGroupId().getValue() : null;
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate().getValue()) : null;
            return groupJoinRequestService.queryGroupJoinRequestsWithVersion(
                    clientRequest.getUserId(),
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
            QueryGroupJoinQuestionsRequest request = clientRequest.getTurmsRequest()
                    .getQueryGroupJoinQuestionsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate().getValue()) : null;
            return groupQuestionService.queryGroupJoinQuestionsWithVersion(
                    clientRequest.getUserId(),
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
            UpdateGroupJoinQuestionRequest request = clientRequest.getTurmsRequest()
                    .getUpdateGroupJoinQuestionRequest();
            Set<String> answers = request.getAnswersList().isEmpty() ?
                    null : new HashSet<>(request.getAnswersList());
            String question = request.hasQuestion() ? request.getQuestion().getValue() : null;
            Integer score = request.hasScore() ? request.getScore().getValue() : null;
            return groupQuestionService.authAndUpdateGroupJoinQuestion(
                    clientRequest.getUserId(),
                    request.getQuestionId(),
                    question,
                    answers,
                    score)
                    .map(RequestHandlerResultFactory::okIfTrue);
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_MEMBER_REQUEST)
    public ClientRequestHandler handleCreateGroupMemberRequest() {
        return clientRequest -> {
            CreateGroupMemberRequest request = clientRequest.getTurmsRequest().getCreateGroupMemberRequest();
            String name = request.hasName() ? request.getName().getValue() : null;
            Date muteEndDate = request.hasMuteEndDate() ? new Date(request.getMuteEndDate().getValue()) : null;
            GroupMemberRole role = request.getRole();
            if (role == null || role == GroupMemberRole.UNRECOGNIZED) {
                role = GroupMemberRole.MEMBER;
            } else if (role == GroupMemberRole.OWNER) {
                return Mono.just(RequestHandlerResultFactory.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The role of the new member must not be OWNER"));
            }
            return groupMemberService.authAndAddGroupMember(
                    clientRequest.getUserId(),
                    request.getGroupId(),
                    request.getUserId(),
                    role,
                    name,
                    muteEndDate,
                    null)
                    .map(member -> member != null && node.getSharedProperties().getService().getNotification().isNotifyUserAfterAddedToGroupByOthers()
                            ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.getTurmsRequest())
                            : RequestHandlerResultFactory.okIfTrue(true));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_MEMBER_REQUEST)
    public ClientRequestHandler handleDeleteGroupMemberRequest() {
        return clientRequest -> {
            DeleteGroupMemberRequest request = clientRequest.getTurmsRequest().getDeleteGroupMemberRequest();
            Long successorId = request.hasSuccessorId() ? request.getSuccessorId().getValue() : null;
            Boolean quitAfterTransfer = request.hasQuitAfterTransfer() ? request.getQuitAfterTransfer().getValue() : null;
            return groupMemberService.authAndDeleteGroupMember(
                    clientRequest.getUserId(),
                    request.getGroupId(),
                    request.getMemberId(),
                    successorId,
                    quitAfterTransfer)
                    .map(deleted -> deleted != null && deleted
                            && node.getSharedProperties().getService().getNotification().isNotifyUserAfterRemovedFromGroupByOthers()
                            && !clientRequest.getUserId().equals(request.getMemberId())
                            ? RequestHandlerResultFactory.get(request.getMemberId(), clientRequest.getTurmsRequest())
                            : RequestHandlerResultFactory.okIfTrue(deleted));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_MEMBERS_REQUEST)
    public ClientRequestHandler handleQueryGroupMembersRequest() {
        return clientRequest -> {
            QueryGroupMembersRequest request = clientRequest.getTurmsRequest().getQueryGroupMembersRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate().getValue()) : null;
            Set<Long> memberIds = request.getMemberIdsCount() != 0 ?
                    new HashSet<>(request.getMemberIdsList()) : null;
            boolean withStatus = request.hasWithStatus() && request.getWithStatus().getValue();
            if (request.getMemberIdsCount() > 0) {
                return groupMemberService.authAndQueryGroupMembers(
                        clientRequest.getUserId(),
                        request.getGroupId(),
                        memberIds,
                        withStatus)
                        .map(groupMembersWithVersion -> RequestHandlerResultFactory.get(
                                TurmsNotification.Data.newBuilder()
                                        .setGroupMembersWithVersion(groupMembersWithVersion)
                                        .build()));
            } else {
                return groupMemberService.authAndQueryGroupMembersWithVersion(
                        clientRequest.getUserId(),
                        request.getGroupId(),
                        lastUpdatedDate,
                        withStatus)
                        .map(groupMembersWithVersion -> RequestHandlerResultFactory.get(
                                TurmsNotification.Data.newBuilder()
                                        .setGroupMembersWithVersion(groupMembersWithVersion)
                                        .build()));
            }
        };
    }

    @ServiceRequestMapping(UPDATE_GROUP_MEMBER_REQUEST)
    public ClientRequestHandler handleUpdateGroupMemberRequest() {
        return clientRequest -> {
            UpdateGroupMemberRequest request = clientRequest.getTurmsRequest().getUpdateGroupMemberRequest();
            String name = request.hasName() ? request.getName().getValue() : null;
            GroupMemberRole role = request.getRole() != GroupMemberRole.UNRECOGNIZED ? request.getRole() : null;
            Date muteEndDate = request.hasMuteEndDate() ? new Date(request.getMuteEndDate().getValue()) : null;
            return groupMemberService.authAndUpdateGroupMember(
                    clientRequest.getUserId(),
                    request.getGroupId(),
                    request.getMemberId(),
                    name,
                    role,
                    muteEndDate)
                    .flatMap(updated -> {
                        if (updated != null && updated) {
                            if (node.getSharedProperties().getService().getNotification()
                                    .isNotifyMembersAfterOtherMemberInfoUpdated()) {
                                return groupMemberService.queryGroupMembersIds(request.getGroupId())
                                        .collect(Collectors.toSet())
                                        .map(groupMembersIds -> groupMembersIds.isEmpty()
                                                ? RequestHandlerResultFactory.ok()
                                                : RequestHandlerResultFactory.get(groupMembersIds, clientRequest.getTurmsRequest()));
                            } else if (!clientRequest.getUserId().equals(request.getMemberId())
                                    && node.getSharedProperties().getService().getNotification().isNotifyMemberAfterInfoUpdatedByOthers()) {
                                return Mono.just(RequestHandlerResultFactory.get(
                                        clientRequest.getUserId(),
                                        clientRequest.getTurmsRequest()));
                            }
                        }
                        return Mono.just(RequestHandlerResultFactory.okIfTrue(updated));
                    });
        };
    }

}
