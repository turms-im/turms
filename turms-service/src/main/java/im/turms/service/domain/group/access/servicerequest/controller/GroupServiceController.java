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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion;
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
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.business.NotificationProperties;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.recycler.SetRecycler;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.group.bo.GroupQuestionIdAndAnswer;
import im.turms.service.domain.group.bo.NewGroupQuestion;
import im.turms.service.domain.group.po.Group;
import im.turms.service.domain.group.service.GroupBlocklistService;
import im.turms.service.domain.group.service.GroupInvitationService;
import im.turms.service.domain.group.service.GroupJoinRequestService;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.group.service.GroupQuestionService;
import im.turms.service.domain.group.service.GroupService;
import im.turms.service.infra.proto.ProtoModelConvertor;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_BLOCKED_USER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_INVITATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_JOIN_QUESTIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_JOIN_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_MEMBERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_BLOCKED_USER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_INVITATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_JOIN_QUESTIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_JOIN_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_MEMBERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUPS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_BLOCKED_USER_IDS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_INVITATIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_JOIN_QUESTIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_JOIN_REQUESTS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_MEMBERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_JOINED_GROUP_IDS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_JOINED_GROUP_INFOS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_JOIN_QUESTION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_MEMBER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class GroupServiceController extends BaseServiceController {

    private final GroupService groupService;
    private final GroupBlocklistService groupBlocklistService;
    private final GroupQuestionService groupQuestionService;
    private final GroupInvitationService groupInvitationService;
    private final GroupJoinRequestService groupJoinRequestService;
    private final GroupMemberService groupMemberService;

    private boolean notifyInviteeAfterGroupInvitationRecalled;
    private boolean notifyMembersAfterGroupDeleted;
    private boolean notifyMembersAfterGroupUpdated;
    private boolean notifyMembersAfterOtherMemberInfoUpdated;
    private boolean notifyMemberAfterInfoUpdatedByOthers;
    private boolean notifyOwnerAndManagersAfterReceivingJoinRequest;
    private boolean notifyOwnerAndManagersAfterGroupJoinRequestRecalled;
    private boolean notifyUserAfterBlockedByGroup;
    private boolean notifyUserAfterUnblockedByGroup;
    private boolean notifyUserAfterInvitedByGroup;
    private boolean notifyUserAfterAddedToGroupByOthers;
    private boolean notifyUserAfterRemovedFromGroupByOthers;

    public GroupServiceController(
            TurmsPropertiesManager propertiesManager,
            GroupService groupService,
            GroupBlocklistService groupBlocklistService,
            GroupQuestionService groupQuestionService,
            GroupInvitationService groupInvitationService,
            GroupJoinRequestService groupJoinRequestService,
            GroupMemberService groupMemberService) {
        this.groupService = groupService;
        this.groupBlocklistService = groupBlocklistService;
        this.groupQuestionService = groupQuestionService;
        this.groupInvitationService = groupInvitationService;
        this.groupJoinRequestService = groupJoinRequestService;
        this.groupMemberService = groupMemberService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        NotificationProperties notificationProperties = properties.getService()
                .getNotification();
        notifyInviteeAfterGroupInvitationRecalled =
                notificationProperties.isNotifyInviteeAfterGroupInvitationRecalled();
        notifyMembersAfterGroupDeleted = notificationProperties.isNotifyMembersAfterGroupDeleted();
        notifyMembersAfterGroupUpdated = notificationProperties.isNotifyMembersAfterGroupUpdated();
        notifyMembersAfterOtherMemberInfoUpdated =
                notificationProperties.isNotifyMembersAfterOtherMemberInfoUpdated();
        notifyMemberAfterInfoUpdatedByOthers =
                notificationProperties.isNotifyMemberAfterInfoUpdatedByOthers();
        notifyOwnerAndManagersAfterReceivingJoinRequest =
                notificationProperties.isNotifyOwnerAndManagersAfterReceivingJoinRequest();
        notifyOwnerAndManagersAfterGroupJoinRequestRecalled =
                notificationProperties.isNotifyOwnerAndManagersAfterGroupJoinRequestRecalled();
        notifyUserAfterBlockedByGroup = notificationProperties.isNotifyUserAfterBlockedByGroup();
        notifyUserAfterUnblockedByGroup =
                notificationProperties.isNotifyUserAfterUnblockedByGroup();
        notifyUserAfterInvitedByGroup = notificationProperties.isNotifyUserAfterInvitedByGroup();
        notifyUserAfterAddedToGroupByOthers =
                notificationProperties.isNotifyUserAfterAddedToGroupByOthers();
        notifyUserAfterRemovedFromGroupByOthers =
                notificationProperties.isNotifyUserAfterRemovedFromGroupByOthers();
    }

    @ServiceRequestMapping(CREATE_GROUP_REQUEST)
    public ClientRequestHandler handleCreateGroupRequest() {
        return clientRequest -> {
            CreateGroupRequest request = clientRequest.turmsRequest()
                    .getCreateGroupRequest();
            String intro = request.hasIntro()
                    ? request.getIntro()
                    : null;
            String announcement = request.hasAnnouncement()
                    ? request.getAnnouncement()
                    : null;
            Integer minScore = request.hasMinScore()
                    ? request.getMinScore()
                    : null;
            Long typeId = request.hasTypeId()
                    ? request.getTypeId()
                    : null;
            Date muteEndDate = request.hasMuteEndDate()
                    ? new Date(request.getMuteEndDate())
                    : null;
            Long creatorIdAndOwnerId = clientRequest.userId();
            return groupService
                    .authAndCreateGroup(creatorIdAndOwnerId,
                            creatorIdAndOwnerId,
                            request.getName(),
                            intro,
                            announcement,
                            minScore,
                            typeId,
                            muteEndDate,
                            null,
                            null,
                            null)
                    .map(group -> RequestHandlerResultFactory.getByDataLong(group.getId()));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_REQUEST)
    public ClientRequestHandler handleDeleteGroupRequest() {
        return clientRequest -> {
            DeleteGroupRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupRequest();
            return groupService.authAndDeleteGroup(clientRequest.userId(), request.getGroupId())
                    .then(Mono.defer(() -> {
                        if (notifyMembersAfterGroupDeleted) {
                            return groupMemberService
                                    .queryGroupMemberIds(request.getGroupId(), false)
                                    .map(memberIds -> memberIds.isEmpty()
                                            ? RequestHandlerResultFactory.OK
                                            : RequestHandlerResultFactory.get(memberIds,
                                                    clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResultFactory.OK);
                    }));
        };
    }

    @ServiceRequestMapping(QUERY_GROUPS_REQUEST)
    public ClientRequestHandler handleQueryGroupsRequest() {
        return clientRequest -> {
            QueryGroupsRequest request = clientRequest.turmsRequest()
                    .getQueryGroupsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return groupService.authAndQueryGroups(request.getGroupIdsCount() > 0
                    ? CollectionUtil.newSet(request.getGroupIdsList())
                    : Collections.emptySet(), lastUpdatedDate)
                    .map(groups -> {
                        List<im.turms.server.common.access.client.dto.model.group.Group> groupProtos =
                                new ArrayList<>(groups.size());
                        for (Group group : groups) {
                            groupProtos.add(ProtoModelConvertor.group2proto(group)
                                    .build());
                        }
                        return RequestHandlerResultFactory
                                .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setGroupsWithVersion(
                                                ClientMessagePool.getGroupsWithVersionBuilder()
                                                        .addAllGroups(groupProtos)
                                                        .build())
                                        .build());
                    });
        };
    }

    @ServiceRequestMapping(QUERY_JOINED_GROUP_IDS_REQUEST)
    public ClientRequestHandler handleQueryJoinedGroupIdsRequest() {
        return clientRequest -> {
            QueryJoinedGroupIdsRequest request = clientRequest.turmsRequest()
                    .getQueryJoinedGroupIdsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return groupService
                    .queryJoinedGroupIdsWithVersion(clientRequest.userId(), lastUpdatedDate)
                    .map(idsWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setLongsWithVersion(idsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_JOINED_GROUP_INFOS_REQUEST)
    public ClientRequestHandler handleQueryJoinedGroupsRequest() {
        return clientRequest -> {
            QueryJoinedGroupInfosRequest request = clientRequest.turmsRequest()
                    .getQueryJoinedGroupInfosRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return groupService
                    .queryJoinedGroupsWithVersion(clientRequest.userId(), lastUpdatedDate)
                    .map(groupsWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setGroupsWithVersion(groupsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(UPDATE_GROUP_REQUEST)
    public ClientRequestHandler handleUpdateGroupRequest() {
        return clientRequest -> {
            UpdateGroupRequest request = clientRequest.turmsRequest()
                    .getUpdateGroupRequest();
            Long successorId = request.hasSuccessorId()
                    ? request.getSuccessorId()
                    : null;
            Mono<Void> updateMono;
            if (successorId == null) {
                Integer minimumScore = request.hasMinScore()
                        ? request.getMinScore()
                        : null;
                Long typeId = request.hasTypeId()
                        ? request.getTypeId()
                        : null;
                String name = request.hasName()
                        ? request.getName()
                        : null;
                String intro = request.hasIntro()
                        ? request.getIntro()
                        : null;
                String announcement = request.hasAnnouncement()
                        ? request.getAnnouncement()
                        : null;
                Date muteEndDate = request.hasMuteEndDate()
                        ? new Date(request.getMuteEndDate())
                        : null;
                updateMono = groupService.authAndUpdateGroupInformation(clientRequest.userId(),
                        request.getGroupId(),
                        typeId,
                        null,
                        null,
                        name,
                        intro,
                        announcement,
                        minimumScore,
                        null,
                        null,
                        null,
                        muteEndDate,
                        null);
            } else {
                boolean quitAfterTransfer =
                        request.hasQuitAfterTransfer() && request.getQuitAfterTransfer();
                updateMono = groupService.authAndTransferGroupOwnership(clientRequest
                        .userId(), request.getGroupId(), successorId, quitAfterTransfer, null);
            }
            return updateMono.then(Mono.defer(() -> {
                if (notifyMembersAfterGroupUpdated) {
                    return groupMemberService.queryGroupMemberIds(request.getGroupId(), false)
                            .map(memberIds -> memberIds.isEmpty()
                                    ? RequestHandlerResultFactory.OK
                                    : RequestHandlerResultFactory.get(memberIds,
                                            clientRequest.turmsRequest()));
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
            return groupBlocklistService
                    .authAndBlockUser(clientRequest.userId(),
                            request.getGroupId(),
                            request.getUserId(),
                            null)
                    .then(Mono.fromCallable(() -> notifyUserAfterBlockedByGroup
                            ? RequestHandlerResultFactory.get(request.getUserId(),
                                    clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_BLOCKED_USER_REQUEST)
    public ClientRequestHandler handleDeleteGroupBlockedUserRequest() {
        return clientRequest -> {
            DeleteGroupBlockedUserRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupBlockedUserRequest();
            return groupBlocklistService
                    .unblockUser(clientRequest
                            .userId(), request.getGroupId(), request.getUserId(), null, true)
                    .then(Mono.fromCallable(() -> notifyUserAfterUnblockedByGroup
                            ? RequestHandlerResultFactory.get(request.getUserId(),
                                    clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_BLOCKED_USER_IDS_REQUEST)
    public ClientRequestHandler handleQueryGroupBlockedUserIdsRequest() {
        return clientRequest -> {
            QueryGroupBlockedUserIdsRequest request = clientRequest.turmsRequest()
                    .getQueryGroupBlockedUserIdsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return groupBlocklistService
                    .queryGroupBlockedUserIdsWithVersion(request.getGroupId(), lastUpdatedDate)
                    .map(idsWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setLongsWithVersion(idsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST)
    public ClientRequestHandler handleQueryGroupBlockedUsersInfosRequest() {
        return clientRequest -> {
            QueryGroupBlockedUserInfosRequest request = clientRequest.turmsRequest()
                    .getQueryGroupBlockedUserInfosRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return groupBlocklistService
                    .queryGroupBlockedUserInfosWithVersion(request.getGroupId(), lastUpdatedDate)
                    .map(userInfos -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setUserInfosWithVersion(userInfos)
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
            for (Map.Entry<Long, String> entry : request.getQuestionIdToAnswerMap()
                    .entrySet()) {
                idAndAnswerPairs
                        .add(new GroupQuestionIdAndAnswer(entry.getKey(), entry.getValue()));
            }
            return groupQuestionService
                    .checkGroupQuestionAnswerAndJoin(clientRequest.userId(), idAndAnswerPairs)
                    .map(answerResult -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setGroupJoinQuestionAnswerResult(answerResult)
                                    .build()));
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_INVITATION_REQUEST)
    public ClientRequestHandler handleCreateGroupInvitationRequestRequest() {
        return clientRequest -> {
            CreateGroupInvitationRequest request = clientRequest.turmsRequest()
                    .getCreateGroupInvitationRequest();
            return groupInvitationService
                    .authAndCreateGroupInvitation(request.getGroupId(),
                            clientRequest.userId(),
                            request.getInviteeId(),
                            request.getContent())
                    .map(invitation -> notifyUserAfterInvitedByGroup
                            ? RequestHandlerResultFactory.getByDataLong(invitation.getId(),
                                    request.getInviteeId(),
                                    clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_JOIN_REQUEST_REQUEST)
    public ClientRequestHandler handleCreateGroupJoinRequestRequest() {
        return clientRequest -> {
            CreateGroupJoinRequestRequest request = clientRequest.turmsRequest()
                    .getCreateGroupJoinRequestRequest();
            return groupJoinRequestService
                    .authAndCreateGroupJoinRequest(clientRequest.userId(),
                            request.getGroupId(),
                            request.getContent())
                    .flatMap(joinRequest -> {
                        if (notifyOwnerAndManagersAfterReceivingJoinRequest) {
                            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                            return groupMemberService
                                    .queryGroupManagersAndOwnerId(request.getGroupId())
                                    .collect(Collectors.toCollection(recyclableSet::getValue))
                                    .map(recipientIds -> recipientIds.isEmpty()
                                            ? RequestHandlerResultFactory.OK
                                            : RequestHandlerResultFactory.getByDataLong(
                                                    joinRequest.getId(),
                                                    CollectionUtil.newSet(recipientIds),
                                                    false,
                                                    clientRequest.turmsRequest()))
                                    .doFinally(signalType -> recyclableSet.recycle());
                        }
                        return Mono.just(RequestHandlerResultFactory.OK);
                    });
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_JOIN_QUESTIONS_REQUEST)
    public ClientRequestHandler handleCreateGroupQuestionsRequest() {
        return clientRequest -> {
            CreateGroupJoinQuestionsRequest request = clientRequest.turmsRequest()
                    .getCreateGroupJoinQuestionsRequest();
            List<NewGroupQuestion> questions = new ArrayList<>(request.getQuestionsCount());
            for (GroupJoinQuestion question : request.getQuestionsList()) {
                questions.add(new NewGroupQuestion(
                        question.getQuestion(),
                        new LinkedHashSet<>(question.getAnswersList()),
                        question.getScore()));
            }
            return groupQuestionService
                    .authAndCreateGroupJoinQuestions(clientRequest.userId(),
                            request.getGroupId(),
                            questions)
                    .map(questionList -> {
                        List<Long> questionIds = new ArrayList<>(questionList.size());
                        for (var question : questionList) {
                            questionIds.add(question.getId());
                        }
                        return RequestHandlerResultFactory.getByDataLongs(questionIds);
                    });
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_INVITATION_REQUEST)
    public ClientRequestHandler handleDeleteGroupInvitationRequest() {
        return clientRequest -> {
            DeleteGroupInvitationRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupInvitationRequest();
            return groupInvitationService.queryInviteeIdByInvitationId(request.getInvitationId())
                    .flatMap(inviteeId -> groupInvitationService
                            .authAndRecallPendingGroupInvitation(clientRequest.userId(),
                                    request.getInvitationId())
                            .then(Mono.fromCallable(() -> notifyInviteeAfterGroupInvitationRecalled
                                    ? RequestHandlerResultFactory.get(inviteeId,
                                            clientRequest.turmsRequest())
                                    : RequestHandlerResultFactory.OK)));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_JOIN_REQUEST_REQUEST)
    public ClientRequestHandler handleDeleteGroupJoinRequestRequest() {
        return clientRequest -> {
            DeleteGroupJoinRequestRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupJoinRequestRequest();
            return groupJoinRequestService
                    .authAndRecallPendingGroupJoinRequest(clientRequest.userId(),
                            request.getRequestId())
                    .then(Mono.defer(() -> {
                        if (notifyOwnerAndManagersAfterGroupJoinRequestRecalled) {
                            return groupJoinRequestService.queryGroupId(request.getRequestId())
                                    .flatMap(groupId -> {
                                        Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                                        return groupMemberService
                                                .queryGroupManagersAndOwnerId(groupId)
                                                .collect(Collectors
                                                        .toCollection(recyclableSet::getValue))
                                                .map(ids -> ids.isEmpty()
                                                        ? RequestHandlerResultFactory.OK
                                                        : RequestHandlerResultFactory.get(
                                                                CollectionUtil.newSet(ids),
                                                                clientRequest.turmsRequest()))
                                                .doFinally(signalType -> recyclableSet.recycle());
                                    });
                        }
                        return Mono.just(RequestHandlerResultFactory.OK);
                    }));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_JOIN_QUESTIONS_REQUEST)
    public ClientRequestHandler handleDeleteGroupJoinQuestionsRequest() {
        return clientRequest -> {
            DeleteGroupJoinQuestionsRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupJoinQuestionsRequest();
            return groupQuestionService
                    .authAndDeleteGroupJoinQuestions(clientRequest.userId(),
                            request.getGroupId(),
                            CollectionUtil.newSet(request.getQuestionIdsList()))
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_INVITATIONS_REQUEST)
    public ClientRequestHandler handleQueryGroupInvitationsRequest() {
        return clientRequest -> {
            QueryGroupInvitationsRequest request = clientRequest.turmsRequest()
                    .getQueryGroupInvitationsRequest();
            Long groupId = request.hasGroupId()
                    ? request.getGroupId()
                    : null;
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            if (groupId == null) {
                return groupInvitationService
                        .queryUserGroupInvitationsWithVersion(clientRequest.userId(),
                                request.hasAreSentByMe() && request.getAreSentByMe(),
                                lastUpdatedDate)
                        .map(groupInvitationsWithVersion -> RequestHandlerResultFactory
                                .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setGroupInvitationsWithVersion(groupInvitationsWithVersion)
                                        .build()));
            }
            return groupInvitationService
                    .authAndQueryGroupInvitationsWithVersion(clientRequest.userId(),
                            groupId,
                            lastUpdatedDate)
                    .map(groupInvitationsWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setGroupInvitationsWithVersion(groupInvitationsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_JOIN_REQUESTS_REQUEST)
    public ClientRequestHandler handleQueryGroupJoinRequestsRequest() {
        return clientRequest -> {
            QueryGroupJoinRequestsRequest request = clientRequest.turmsRequest()
                    .getQueryGroupJoinRequestsRequest();
            Long groupId = request.hasGroupId()
                    ? request.getGroupId()
                    : null;
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return groupJoinRequestService
                    .authAndQueryGroupJoinRequestsWithVersion(clientRequest.userId(),
                            groupId,
                            lastUpdatedDate)
                    .map(groupJoinRequestsWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setGroupJoinRequestsWithVersion(groupJoinRequestsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_JOIN_QUESTIONS_REQUEST)
    public ClientRequestHandler handleQueryGroupJoinQuestionsRequest() {
        return clientRequest -> {
            QueryGroupJoinQuestionsRequest request = clientRequest.turmsRequest()
                    .getQueryGroupJoinQuestionsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return groupQuestionService
                    .authAndQueryGroupJoinQuestionsWithVersion(clientRequest.userId(),
                            request.getGroupId(),
                            request.getWithAnswers(),
                            lastUpdatedDate)
                    .map(groupJoinQuestionsWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setGroupJoinQuestionsWithVersion(groupJoinQuestionsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(UPDATE_GROUP_JOIN_QUESTION_REQUEST)
    public ClientRequestHandler handleUpdateGroupJoinQuestionRequest() {
        return clientRequest -> {
            UpdateGroupJoinQuestionRequest request = clientRequest.turmsRequest()
                    .getUpdateGroupJoinQuestionRequest();
            Set<String> answers = request.getAnswersList()
                    .isEmpty()
                            ? null
                            : CollectionUtil.newSet(request.getAnswersList());
            String question = request.hasQuestion()
                    ? request.getQuestion()
                    : null;
            Integer score = request.hasScore()
                    ? request.getScore()
                    : null;
            return groupQuestionService
                    .authAndUpdateGroupJoinQuestion(clientRequest
                            .userId(), request.getQuestionId(), question, answers, score)
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_MEMBERS_REQUEST)
    public ClientRequestHandler handleCreateGroupMembersRequest() {
        return clientRequest -> {
            CreateGroupMembersRequest request = clientRequest.turmsRequest()
                    .getCreateGroupMembersRequest();
            String name = request.hasName()
                    ? request.getName()
                    : null;
            Date muteEndDate = request.hasMuteEndDate()
                    ? new Date(request.getMuteEndDate())
                    : null;
            Set<Long> userIds = CollectionUtil.toSet(request.getUserIdsList());
            return groupMemberService
                    .authAndAddGroupMembers(clientRequest.userId(),
                            request.getGroupId(),
                            userIds,
                            request.hasRole()
                                    ? request.getRole()
                                    : null,
                            name,
                            muteEndDate,
                            null)
                    .map(member -> member != null && notifyUserAfterAddedToGroupByOthers
                            ? RequestHandlerResultFactory.get(userIds, clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_MEMBERS_REQUEST)
    public ClientRequestHandler handleDeleteGroupMembersRequest() {
        return clientRequest -> {
            DeleteGroupMembersRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupMembersRequest();
            Long successorId = request.hasSuccessorId()
                    ? request.getSuccessorId()
                    : null;
            Boolean quitAfterTransfer = request.hasQuitAfterTransfer()
                    ? request.getQuitAfterTransfer()
                    : null;
            Set<Long> memberIdsToDelete = CollectionUtil.toSet(request.getMemberIdsList());
            Long requesterId = clientRequest.userId();
            return groupMemberService
                    .authAndDeleteGroupMembers(requesterId,
                            request.getGroupId(),
                            memberIdsToDelete,
                            successorId,
                            quitAfterTransfer)
                    .map(deletedUserIds -> {
                        if (!notifyUserAfterRemovedFromGroupByOthers
                                || deletedUserIds.isEmpty()
                                || (deletedUserIds.size() == 1
                                        && deletedUserIds.contains(requesterId))) {
                            return RequestHandlerResultFactory.OK;
                        }
                        deletedUserIds.remove(requesterId);
                        return RequestHandlerResultFactory.get(deletedUserIds,
                                clientRequest.turmsRequest());
                    });
        };
    }

    @ServiceRequestMapping(QUERY_GROUP_MEMBERS_REQUEST)
    public ClientRequestHandler handleQueryGroupMembersRequest() {
        return clientRequest -> {
            QueryGroupMembersRequest request = clientRequest.turmsRequest()
                    .getQueryGroupMembersRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            Set<Long> memberIds = request.getMemberIdsCount() != 0
                    ? CollectionUtil.newSet(request.getMemberIdsList())
                    : null;
            boolean withStatus = request.hasWithStatus() && request.getWithStatus();
            if (request.getMemberIdsCount() > 0) {
                return groupMemberService
                        .authAndQueryGroupMembers(clientRequest.userId(),
                                request.getGroupId(),
                                memberIds,
                                withStatus)
                        .map(groupMembersWithVersion -> RequestHandlerResultFactory
                                .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setGroupMembersWithVersion(groupMembersWithVersion)
                                        .build()));
            }
            return groupMemberService
                    .authAndQueryGroupMembersWithVersion(clientRequest.userId(),
                            request.getGroupId(),
                            lastUpdatedDate,
                            withStatus)
                    .map(groupMembersWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setGroupMembersWithVersion(groupMembersWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(UPDATE_GROUP_MEMBER_REQUEST)
    public ClientRequestHandler handleUpdateGroupMemberRequest() {
        return clientRequest -> {
            UpdateGroupMemberRequest request = clientRequest.turmsRequest()
                    .getUpdateGroupMemberRequest();
            String name = request.hasName()
                    ? request.getName()
                    : null;
            GroupMemberRole role = request.hasRole()
                    ? request.getRole()
                    : null;
            Date muteEndDate = request.hasMuteEndDate()
                    ? new Date(request.getMuteEndDate())
                    : null;
            return groupMemberService.authAndUpdateGroupMember(clientRequest
                    .userId(), request.getGroupId(), request.getMemberId(), name, role, muteEndDate)
                    .then(Mono.defer(() -> {
                        if (notifyMembersAfterOtherMemberInfoUpdated) {
                            return groupMemberService
                                    .queryGroupMemberIds(request.getGroupId(), true)
                                    .map(groupMemberIds -> groupMemberIds.isEmpty()
                                            ? RequestHandlerResultFactory.OK
                                            : RequestHandlerResultFactory.get(groupMemberIds,
                                                    clientRequest.turmsRequest()));
                        } else if (!clientRequest.userId()
                                .equals(request.getMemberId())
                                && notifyMemberAfterInfoUpdatedByOthers) {
                            return Mono.just(RequestHandlerResultFactory.get(clientRequest.userId(),
                                    clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResultFactory.OK);
                    }));
        };
    }

}