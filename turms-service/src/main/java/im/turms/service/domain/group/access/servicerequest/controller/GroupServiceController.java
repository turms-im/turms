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
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
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
import im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupInvitationRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest;
import im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest;
import im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest;
import im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest;
import im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest;
import im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.business.notification.NotificationProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupBlockedUserAddedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupBlockedUserRemovedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupCreatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupDeletedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupInvitationAddedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupInvitationRecalledProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupInvitationRepliedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupJoinRequestCreatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupJoinRequestRecalledProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupJoinRequestRepliedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupMemberAddedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupMemberInfoUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupMemberRemovedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupUpdatedProperties;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.recycler.SetRecycler;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.group.bo.NewGroupQuestion;
import im.turms.service.domain.group.po.Group;
import im.turms.service.domain.group.po.GroupInvitation;
import im.turms.service.domain.group.po.GroupJoinRequest;
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
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_INVITATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_JOIN_QUESTION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_JOIN_REQUEST_REQUEST;
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

    private boolean notifyRequesterOtherOnlineSessionsOfGroupCreated;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupDeleted;
    private boolean notifyGroupMembersOfGroupDeleted;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupUpdated;
    private boolean notifyGroupMembersOfGroupUpdated;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupMemberAdded;
    private boolean notifyAddedGroupMemberOfGroupMemberAdded;
    private boolean notifyOtherGroupMembersOfGroupMemberAdded;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupMemberRemoved;
    private boolean notifyRemovedGroupMemberOfGroupMemberRemoved;
    private boolean notifyOtherGroupMembersOfGroupMemberRemoved;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupMemberInfoUpdated;
    private boolean notifyUpdatedGroupMemberOfGroupMemberInfoUpdated;
    private boolean notifyOtherGroupMembersOfGroupMemberInfoUpdated;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupBlockedUserAdded;
    private boolean notifyBlockedUserOfGroupBlockedUserAdded;
    private boolean notifyGroupMembersOfGroupBlockedUserAdded;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupBlockedUserRemoved;
    private boolean notifyUnblockedUserOfGroupBlockedUserRemoved;
    private boolean notifyGroupMembersOfGroupBlockedUserRemoved;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupInvitationAdded;
    private boolean notifyInviteeOfGroupInvitationAdded;
    private boolean notifyGroupOwnerAndManagersOfGroupInvitationAdded;
    private boolean notifyGroupMembersOfGroupInvitationAdded;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupInvitationRecalled;
    private boolean notifyInviteeOfGroupInvitationRecalled;
    private boolean notifyGroupOwnerAndManagersOfGroupInvitationRecalled;
    private boolean notifyGroupMembersOfGroupInvitationRecalled;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupInvitationReplied;
    private boolean notifyGroupInvitationInviterOfGroupInvitationReplied;
    private boolean notifyGroupOwnerAndManagersOfGroupInvitationReplied;
    private boolean notifyGroupMembersOfGroupInvitationReplied;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupJoinRequestCreated;
    private boolean notifyGroupOwnerAndManagersOfGroupJoinRequestCreated;
    private boolean notifyGroupMembersOfGroupJoinRequestCreated;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupJoinRequestRecalled;
    private boolean notifyGroupOwnerAndManagersOfGroupJoinRequestRecalled;
    private boolean notifyGroupMembersOfGroupJoinRequestRecalled;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupJoinRequestReplied;
    private boolean notifyGroupJoinRequestSenderOfGroupJoinRequestReplied;
    private boolean notifyGroupOwnerAndManagersOfGroupJoinRequestReplied;
    private boolean notifyGroupMembersOfGroupJoinRequestReplied;

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
        NotificationGroupCreatedProperties notificationGroupCreatedProperties =
                notificationProperties.getGroupCreated();
        notifyRequesterOtherOnlineSessionsOfGroupCreated =
                notificationGroupCreatedProperties.isNotifyRequesterOtherOnlineSessions();

        NotificationGroupDeletedProperties notificationGroupDeletedProperties =
                notificationProperties.getGroupDeleted();
        notifyRequesterOtherOnlineSessionsOfGroupDeleted =
                notificationGroupDeletedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyGroupMembersOfGroupDeleted =
                notificationGroupDeletedProperties.isNotifyGroupMembers();

        NotificationGroupUpdatedProperties notificationGroupUpdatedProperties =
                notificationProperties.getGroupUpdated();
        notifyRequesterOtherOnlineSessionsOfGroupUpdated =
                notificationGroupUpdatedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyGroupMembersOfGroupUpdated =
                notificationGroupUpdatedProperties.isNotifyGroupMembers();

        NotificationGroupMemberAddedProperties notificationGroupMemberAddedProperties =
                notificationProperties.getGroupMemberAdded();
        notifyRequesterOtherOnlineSessionsOfGroupMemberAdded =
                notificationGroupMemberAddedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyAddedGroupMemberOfGroupMemberAdded =
                notificationGroupMemberAddedProperties.isNotifyAddedGroupMember();
        notifyOtherGroupMembersOfGroupMemberAdded =
                notificationGroupMemberAddedProperties.isNotifyOtherGroupMembers();

        NotificationGroupMemberRemovedProperties notificationGroupMemberRemovedProperties =
                notificationProperties.getGroupMemberRemoved();
        notifyRequesterOtherOnlineSessionsOfGroupMemberRemoved =
                notificationGroupMemberRemovedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyRemovedGroupMemberOfGroupMemberRemoved =
                notificationGroupMemberRemovedProperties.isNotifyRemovedGroupMember();
        notifyOtherGroupMembersOfGroupMemberRemoved =
                notificationGroupMemberRemovedProperties.isNotifyOtherGroupMembers();

        NotificationGroupMemberInfoUpdatedProperties notificationGroupMemberInfoUpdatedProperties =
                notificationProperties.getGroupMemberInfoUpdated();
        notifyRequesterOtherOnlineSessionsOfGroupMemberInfoUpdated =
                notificationGroupMemberInfoUpdatedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyUpdatedGroupMemberOfGroupMemberInfoUpdated =
                notificationGroupMemberInfoUpdatedProperties.isNotifyUpdatedGroupMember();
        notifyOtherGroupMembersOfGroupMemberInfoUpdated =
                notificationGroupMemberInfoUpdatedProperties.isNotifyOtherGroupMembers();

        NotificationGroupBlockedUserAddedProperties notificationGroupBlockedUserAddedProperties =
                notificationProperties.getGroupBlockedUserAdded();
        notifyRequesterOtherOnlineSessionsOfGroupBlockedUserAdded =
                notificationGroupBlockedUserAddedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyBlockedUserOfGroupBlockedUserAdded =
                notificationGroupBlockedUserAddedProperties.isNotifyBlockedUser();
        notifyGroupMembersOfGroupBlockedUserAdded =
                notificationGroupBlockedUserAddedProperties.isNotifyGroupMembers();

        NotificationGroupBlockedUserRemovedProperties notificationGroupBlockedUserRemovedProperties =
                notificationProperties.getGroupBlockedUserRemoved();
        notifyRequesterOtherOnlineSessionsOfGroupBlockedUserRemoved =
                notificationGroupBlockedUserRemovedProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyUnblockedUserOfGroupBlockedUserRemoved =
                notificationGroupBlockedUserRemovedProperties.isNotifyUnblockedUser();
        notifyGroupMembersOfGroupBlockedUserRemoved =
                notificationGroupBlockedUserRemovedProperties.isNotifyGroupMembers();

        NotificationGroupInvitationAddedProperties notificationGroupInvitationAddedProperties =
                notificationProperties.getGroupInvitationAdded();
        notifyRequesterOtherOnlineSessionsOfGroupInvitationAdded =
                notificationGroupInvitationAddedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyInviteeOfGroupInvitationAdded =
                notificationGroupInvitationAddedProperties.isNotifyInvitee();
        notifyGroupOwnerAndManagersOfGroupInvitationAdded =
                notificationGroupInvitationAddedProperties.isNotifyGroupOwnerAndManagers();
        notifyGroupMembersOfGroupInvitationAdded =
                notificationGroupInvitationAddedProperties.isNotifyGroupMembers();

        NotificationGroupInvitationRecalledProperties notificationGroupInvitationRecalledProperties =
                notificationProperties.getGroupInvitationRecalled();
        notifyRequesterOtherOnlineSessionsOfGroupInvitationRecalled =
                notificationGroupInvitationRecalledProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyInviteeOfGroupInvitationRecalled =
                notificationGroupInvitationRecalledProperties.isNotifyInvitee();
        notifyGroupOwnerAndManagersOfGroupInvitationRecalled =
                notificationGroupInvitationRecalledProperties.isNotifyGroupOwnerAndManagers();
        notifyGroupMembersOfGroupInvitationRecalled =
                notificationGroupInvitationRecalledProperties.isNotifyGroupMembers();

        NotificationGroupInvitationRepliedProperties notificationGroupInvitationRepliedProperties =
                notificationProperties.getGroupInvitationReplied();
        notifyRequesterOtherOnlineSessionsOfGroupInvitationReplied =
                notificationGroupInvitationRepliedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyGroupInvitationInviterOfGroupInvitationReplied =
                notificationGroupInvitationRepliedProperties.isNotifyGroupInvitationInviter();
        notifyGroupOwnerAndManagersOfGroupInvitationReplied =
                notificationGroupInvitationRepliedProperties.isNotifyGroupOwnerAndManagers();
        notifyGroupMembersOfGroupInvitationReplied =
                notificationGroupInvitationRepliedProperties.isNotifyGroupMembers();

        NotificationGroupJoinRequestCreatedProperties notificationGroupJoinRequestCreatedProperties =
                notificationProperties.getGroupJoinRequestCreated();
        notifyRequesterOtherOnlineSessionsOfGroupJoinRequestCreated =
                notificationGroupJoinRequestCreatedProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyGroupOwnerAndManagersOfGroupJoinRequestCreated =
                notificationGroupJoinRequestCreatedProperties.isNotifyGroupOwnerAndManagers();
        notifyGroupMembersOfGroupJoinRequestCreated =
                notificationGroupJoinRequestCreatedProperties.isNotifyGroupMembers();

        NotificationGroupJoinRequestRecalledProperties notificationGroupJoinRequestRecalledProperties =
                notificationProperties.getGroupJoinRequestRecalled();
        notifyRequesterOtherOnlineSessionsOfGroupJoinRequestRecalled =
                notificationGroupJoinRequestRecalledProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyGroupOwnerAndManagersOfGroupJoinRequestRecalled =
                notificationGroupJoinRequestRecalledProperties.isNotifyGroupOwnerAndManagers();
        notifyGroupMembersOfGroupJoinRequestRecalled =
                notificationGroupJoinRequestRecalledProperties.isNotifyGroupMembers();

        NotificationGroupJoinRequestRepliedProperties notificationGroupJoinRequestRepliedProperties =
                notificationProperties.getGroupJoinRequestReplied();
        notifyRequesterOtherOnlineSessionsOfGroupJoinRequestReplied =
                notificationGroupJoinRequestRepliedProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyGroupJoinRequestSenderOfGroupJoinRequestReplied =
                notificationGroupJoinRequestRepliedProperties.isNotifyGroupJoinRequestSender();
        notifyGroupOwnerAndManagersOfGroupJoinRequestReplied =
                notificationGroupJoinRequestRepliedProperties.isNotifyGroupOwnerAndManagers();
        notifyGroupMembersOfGroupJoinRequestReplied =
                notificationGroupJoinRequestRepliedProperties.isNotifyGroupMembers();
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
                    .map(group -> RequestHandlerResult.ofDataLong(group.getId(),
                            notifyRequesterOtherOnlineSessionsOfGroupCreated,
                            clientRequest.turmsRequest()));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_REQUEST)
    public ClientRequestHandler handleDeleteGroupRequest() {
        return clientRequest -> {
            DeleteGroupRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupRequest();
            return groupService
                    .authAndDeleteGroup(notifyGroupMembersOfGroupDeleted,
                            clientRequest.userId(),
                            request.getGroupId())
                    .map(memberIds -> {
                        if (notifyGroupMembersOfGroupDeleted) {
                            memberIds = CollectionUtil.remove(memberIds, clientRequest.userId());
                            return RequestHandlerResult
                                    .of(true, memberIds, clientRequest.turmsRequest());
                        }
                        return RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfGroupDeleted,
                                clientRequest.turmsRequest());
                    });
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
                        return RequestHandlerResult
                                .of(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                    .map(idsWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                    .map(groupsWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                if (notifyGroupMembersOfGroupUpdated) {
                    return groupMemberService.queryGroupMemberIds(request.getGroupId(), false)
                            .map(memberIds -> {
                                memberIds =
                                        CollectionUtil.remove(memberIds, clientRequest.userId());
                                return RequestHandlerResult.of(
                                        // This is always true because the requester is also a group
                                        // member.
                                        true,
                                        memberIds,
                                        clientRequest.turmsRequest());
                            });
                }
                return Mono.just(
                        RequestHandlerResult.of(notifyRequesterOtherOnlineSessionsOfGroupUpdated,
                                clientRequest.turmsRequest()));
            }));
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_BLOCKED_USER_REQUEST)
    public ClientRequestHandler handleCreateGroupBlockedUserRequest() {
        return clientRequest -> {
            CreateGroupBlockedUserRequest request = clientRequest.turmsRequest()
                    .getCreateGroupBlockedUserRequest();
            Long groupId = request.getGroupId();
            Long userIdToBlock = request.getUserId();
            return groupBlocklistService
                    .authAndBlockUser(clientRequest.userId(), groupId, userIdToBlock, null)
                    .then(Mono.defer(() -> {
                        if (notifyGroupMembersOfGroupBlockedUserAdded) {
                            return groupMemberService.queryGroupMemberIds(groupId, false)
                                    .map(userIds -> {
                                        userIds = CollectionUtil.remove(userIds,
                                                clientRequest.userId());
                                        if (notifyBlockedUserOfGroupBlockedUserAdded) {
                                            userIds = CollectionUtil.add(userIds, userIdToBlock);
                                        }
                                        return RequestHandlerResult.of(
                                                // This is always true because the requester is also
                                                // a group member.
                                                true,
                                                userIds,
                                                clientRequest.turmsRequest());
                                    });
                        } else if (notifyBlockedUserOfGroupBlockedUserAdded) {
                            return Mono.just(RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfGroupBlockedUserAdded,
                                    userIdToBlock,
                                    clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfGroupBlockedUserAdded,
                                clientRequest.turmsRequest()));
                    }));
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_BLOCKED_USER_REQUEST)
    public ClientRequestHandler handleDeleteGroupBlockedUserRequest() {
        return clientRequest -> {
            DeleteGroupBlockedUserRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupBlockedUserRequest();
            Long groupId = request.getGroupId();
            Long userIdToUnblock = request.getUserId();
            return groupBlocklistService
                    .unblockUser(clientRequest.userId(), groupId, userIdToUnblock, null, true)
                    .flatMap(wasBlocked -> {
                        if (!wasBlocked) {
                            return Mono.just(RequestHandlerResult.OK);
                        }
                        if (notifyGroupMembersOfGroupBlockedUserRemoved) {
                            return groupMemberService.queryGroupMemberIds(groupId, false)
                                    .map(memberIds -> {
                                        memberIds = CollectionUtil.remove(memberIds,
                                                clientRequest.userId());
                                        if (notifyUnblockedUserOfGroupBlockedUserRemoved) {
                                            memberIds =
                                                    CollectionUtil.add(memberIds, userIdToUnblock);
                                        }
                                        return RequestHandlerResult
                                                .of(true, memberIds, clientRequest.turmsRequest());
                                    });
                        } else if (notifyUnblockedUserOfGroupBlockedUserRemoved) {
                            return Mono.just(RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfGroupBlockedUserRemoved,
                                    userIdToUnblock,
                                    clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfGroupBlockedUserRemoved,
                                clientRequest.turmsRequest()));
                    });
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
                    .map(idsWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                    .map(userInfos -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setUserInfosWithVersion(userInfos)
                                    .build()));
        };
    }

    @ServiceRequestMapping(CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST)
    public ClientRequestHandler handleCheckGroupQuestionAnswerRequest() {
        return clientRequest -> {
            CheckGroupJoinQuestionsAnswersRequest request = clientRequest.turmsRequest()
                    .getCheckGroupJoinQuestionsAnswersRequest();
            return groupQuestionService
                    .authAndCheckGroupQuestionAnswerAndJoin(clientRequest.userId(),
                            request.getQuestionIdToAnswerMap())
                    .flatMap(answerResult -> {
                        boolean joined = answerResult.joined();
                        List<Long> questionIds = answerResult.questionIds();
                        TurmsNotification.Data response =
                                ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setGroupJoinQuestionAnswerResult(ClientMessagePool
                                                .getGroupJoinQuestionsAnswerResultBuilder()
                                                .setJoined(joined)
                                                .addAllQuestionIds(questionIds)
                                                .setScore(answerResult.score())
                                                .build())
                                        .build();
                        if (!joined) {
                            return Mono.just(RequestHandlerResult.of(response));
                        }
                        Long groupId = answerResult.groupId();
                        TurmsRequest notification = ClientMessagePool.getTurmsRequestBuilder()
                                .setCreateGroupMembersRequest(
                                        ClientMessagePool.getCreateGroupMembersRequest()
                                                .setGroupId(groupId)
                                                .addUserIds(clientRequest.userId())
                                                .setRole(GroupMemberRole.MEMBER))
                                .build();
                        if (notifyOtherGroupMembersOfGroupMemberAdded) {
                            return groupMemberService.queryGroupMemberIds(groupId, false)
                                    .map(userIds -> {
                                        if (!notifyAddedGroupMemberOfGroupMemberAdded) {
                                            userIds = CollectionUtil.remove(userIds,
                                                    clientRequest.userId());
                                        }
                                        return RequestHandlerResult
                                                .of(response, false, userIds, notification);
                                    });
                        } else if (notifyAddedGroupMemberOfGroupMemberAdded) {
                            return Mono.just(RequestHandlerResult.of(response,
                                    false,
                                    Set.of(clientRequest.userId()),
                                    notification));
                        }
                        return Mono.just(RequestHandlerResult.of(response));
                    });
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_INVITATION_REQUEST)
    public ClientRequestHandler handleCreateGroupInvitationRequestRequest() {
        return clientRequest -> {
            CreateGroupInvitationRequest request = clientRequest.turmsRequest()
                    .getCreateGroupInvitationRequest();
            Long groupId = request.getGroupId();
            Long inviteeId = request.getInviteeId();
            return groupInvitationService
                    .authAndCreateGroupInvitation(groupId,
                            clientRequest.userId(),
                            inviteeId,
                            request.getContent())
                    .flatMap(invitation -> {
                        if (notifyGroupMembersOfGroupInvitationAdded) {
                            return groupMemberService.queryGroupMemberIds(groupId, false)
                                    .map(userIds -> {
                                        userIds = CollectionUtil.remove(userIds,
                                                clientRequest.userId());
                                        if (notifyInviteeOfGroupInvitationAdded) {
                                            userIds = CollectionUtil.add(userIds, inviteeId);
                                        }
                                        return RequestHandlerResult.ofDataLong(invitation.getId(),
                                                true,
                                                userIds,
                                                clientRequest.turmsRequest());
                                    });
                        } else if (notifyGroupOwnerAndManagersOfGroupInvitationAdded) {
                            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                            return groupMemberService.queryGroupManagersAndOwnerId(groupId)
                                    .collect(Collectors.toCollection(recyclableSet::getValue))
                                    .map(userIds -> {
                                        userIds = CollectionUtil.newSet(userIds);
                                        Long requesterId = clientRequest.userId();
                                        boolean isRequesterOwnerOrManager =
                                                userIds.contains(requesterId);
                                        if (isRequesterOwnerOrManager) {
                                            userIds.remove(requesterId);
                                        }
                                        if (notifyInviteeOfGroupInvitationAdded) {
                                            userIds.add(inviteeId);
                                        }
                                        return RequestHandlerResult.ofDataLong(invitation.getId(),
                                                isRequesterOwnerOrManager
                                                        || notifyRequesterOtherOnlineSessionsOfGroupInvitationAdded,
                                                userIds,
                                                clientRequest.turmsRequest());
                                    })
                                    .doFinally(signalType -> recyclableSet.recycle());
                        } else if (notifyInviteeOfGroupInvitationAdded) {
                            return Mono.just(RequestHandlerResult.ofDataLong(invitation.getId(),
                                    notifyRequesterOtherOnlineSessionsOfGroupInvitationAdded,
                                    inviteeId,
                                    clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResult.ofDataLong(invitation.getId(),
                                notifyRequesterOtherOnlineSessionsOfGroupInvitationAdded,
                                clientRequest.turmsRequest()));
                    });
        };
    }

    @ServiceRequestMapping(CREATE_GROUP_JOIN_REQUEST_REQUEST)
    public ClientRequestHandler handleCreateGroupJoinRequestRequest() {
        return clientRequest -> {
            CreateGroupJoinRequestRequest request = clientRequest.turmsRequest()
                    .getCreateGroupJoinRequestRequest();
            Long groupId = request.getGroupId();
            return groupJoinRequestService
                    .authAndCreateGroupJoinRequest(clientRequest.userId(),
                            groupId,
                            request.getContent())
                    .flatMap(joinRequest -> {
                        if (notifyGroupMembersOfGroupJoinRequestCreated) {
                            return groupMemberService.queryGroupMemberIds(groupId, false)
                                    .map(memberIds -> RequestHandlerResult.ofDataLong(
                                            joinRequest.getId(),
                                            notifyRequesterOtherOnlineSessionsOfGroupJoinRequestCreated,
                                            memberIds,
                                            clientRequest.turmsRequest()));
                        } else if (notifyGroupOwnerAndManagersOfGroupJoinRequestCreated) {
                            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                            return groupMemberService.queryGroupManagersAndOwnerId(groupId)
                                    .collect(Collectors.toCollection(recyclableSet::getValue))
                                    .map(recipientIds -> recipientIds.isEmpty()
                                            ? RequestHandlerResult.ofDataLong(joinRequest.getId(),
                                                    notifyRequesterOtherOnlineSessionsOfGroupJoinRequestCreated,
                                                    clientRequest.turmsRequest())
                                            : RequestHandlerResult.ofDataLong(joinRequest.getId(),
                                                    notifyRequesterOtherOnlineSessionsOfGroupJoinRequestCreated,
                                                    CollectionUtil.newSet(recipientIds),
                                                    clientRequest.turmsRequest()))
                                    .doFinally(signalType -> recyclableSet.recycle());
                        }
                        return Mono.just(RequestHandlerResult.ofDataLong(joinRequest.getId(),
                                notifyRequesterOtherOnlineSessionsOfGroupJoinRequestCreated,
                                clientRequest.turmsRequest()));
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
                        return RequestHandlerResult.ofDataLongs(questionIds);
                    });
        };
    }

    @ServiceRequestMapping(DELETE_GROUP_INVITATION_REQUEST)
    public ClientRequestHandler handleDeleteGroupInvitationRequest() {
        return clientRequest -> {
            DeleteGroupInvitationRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupInvitationRequest();
            return groupInvitationService
                    .authAndRecallPendingGroupInvitation(clientRequest.userId(),
                            request.getInvitationId())
                    .flatMap(invitation -> {
                        if (notifyGroupMembersOfGroupInvitationRecalled) {
                            return groupMemberService
                                    .queryGroupMemberIds(invitation.getGroupId(), false)
                                    .map(userIds -> {
                                        userIds = CollectionUtil.remove(userIds,
                                                clientRequest.userId());
                                        if (notifyInviteeOfGroupInvitationRecalled) {
                                            userIds = CollectionUtil.add(userIds,
                                                    invitation.getInviteeId());
                                        }
                                        return RequestHandlerResult
                                                .of(true, userIds, clientRequest.turmsRequest());
                                    });
                        } else if (notifyGroupOwnerAndManagersOfGroupInvitationRecalled) {
                            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                            return groupMemberService
                                    .queryGroupManagersAndOwnerId(invitation.getGroupId())
                                    .collect(Collectors.toCollection(recyclableSet::getValue))
                                    .map(userIds -> {
                                        userIds = CollectionUtil.newSet(userIds);
                                        Long requesterId = clientRequest.userId();
                                        boolean isRequesterOwnerOrManager =
                                                userIds.contains(requesterId);
                                        if (isRequesterOwnerOrManager) {
                                            userIds.remove(requesterId);
                                        }
                                        if (notifyInviteeOfGroupInvitationRecalled) {
                                            userIds.add(invitation.getInviteeId());
                                        }
                                        return RequestHandlerResult.of(isRequesterOwnerOrManager
                                                || notifyRequesterOtherOnlineSessionsOfGroupInvitationRecalled,
                                                userIds,
                                                clientRequest.turmsRequest());
                                    })
                                    .doFinally(signalType -> recyclableSet.recycle());
                        } else if (notifyInviteeOfGroupInvitationRecalled) {
                            return Mono.just(RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfGroupInvitationRecalled,
                                    invitation.getInviteeId(),
                                    clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfGroupInvitationRecalled,
                                clientRequest.turmsRequest()));
                    });
        };
    }

    @ServiceRequestMapping(UPDATE_GROUP_INVITATION_REQUEST)
    public ClientRequestHandler handleUpdateGroupInvitationRequest() {
        return clientRequest -> {
            UpdateGroupInvitationRequest request = clientRequest.turmsRequest()
                    .getUpdateGroupInvitationRequest();
            return groupInvitationService
                    .authAndHandleInvitation(clientRequest.userId(),
                            request.getInvitationId(),
                            request.getResponseAction(),
                            request.getReason())
                    .flatMap(handleResult -> {
                        GroupInvitation invitation = handleResult.groupInvitation();
                        if (notifyGroupMembersOfGroupInvitationReplied) {
                            return groupMemberService
                                    .queryGroupMemberIds(invitation.getGroupId(), false)
                                    .map(userIds -> {
                                        if (handleResult.requesterAddedAsNewMember()) {
                                            List<RequestHandlerResult.Notification> notifications =
                                                    new ArrayList<>(2);

                                            Set<Long> userIdsForGroupInvitationUpdatedNotification =
                                                    CollectionUtil.newSet(userIds);
                                            userIdsForGroupInvitationUpdatedNotification
                                                    .remove(clientRequest.userId());
                                            if (notifyGroupInvitationInviterOfGroupInvitationReplied) {
                                                userIdsForGroupInvitationUpdatedNotification
                                                        .add(invitation.getInviterId());
                                            }
                                            notifications.add(RequestHandlerResult.Notification.of(
                                                    notifyRequesterOtherOnlineSessionsOfGroupInvitationReplied,
                                                    userIdsForGroupInvitationUpdatedNotification,
                                                    clientRequest.turmsRequest()));

                                            if (notifyOtherGroupMembersOfGroupMemberAdded) {
                                                if (!notifyAddedGroupMemberOfGroupMemberAdded) {
                                                    userIds = CollectionUtil.remove(userIds,
                                                            clientRequest.userId());
                                                }
                                                notifications.add(
                                                        RequestHandlerResult.Notification.of(false,
                                                                userIds,
                                                                createCreateMemberNotification(
                                                                        invitation.getGroupId(),
                                                                        clientRequest.userId())));
                                            } else if (notifyAddedGroupMemberOfGroupMemberAdded) {
                                                notifications.add(
                                                        RequestHandlerResult.Notification.of(false,
                                                                clientRequest.userId(),
                                                                createCreateMemberNotification(
                                                                        invitation.getGroupId(),
                                                                        clientRequest.userId())));
                                            }
                                            return RequestHandlerResult.of(notifications);
                                        } else {
                                            userIds = CollectionUtil.remove(userIds,
                                                    clientRequest.userId());
                                            if (notifyGroupInvitationInviterOfGroupInvitationReplied) {
                                                userIds = CollectionUtil.add(userIds,
                                                        invitation.getInviterId());
                                            }
                                            return RequestHandlerResult.of(
                                                    notifyRequesterOtherOnlineSessionsOfGroupInvitationReplied,
                                                    userIds,
                                                    clientRequest.turmsRequest());
                                        }
                                    });
                        }
                        Mono<RequestHandlerResult.Notification> notificationForGroupInvitationUpdatedMono;
                        if (notifyGroupOwnerAndManagersOfGroupInvitationReplied) {
                            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                            notificationForGroupInvitationUpdatedMono = groupMemberService
                                    .queryGroupManagersAndOwnerId(invitation.getGroupId())
                                    .collect(Collectors.toCollection(recyclableSet::getValue))
                                    .map(userIds -> {
                                        userIds = CollectionUtil.newSet(userIds);
                                        userIds.remove(clientRequest.userId());
                                        if (notifyInviteeOfGroupInvitationRecalled) {
                                            userIds.add(invitation.getInviteeId());
                                        }
                                        return RequestHandlerResult.Notification.of(
                                                notifyRequesterOtherOnlineSessionsOfGroupInvitationReplied,
                                                userIds,
                                                clientRequest.turmsRequest());
                                    })
                                    .doFinally(signalType -> recyclableSet.recycle());
                        } else if (notifyGroupInvitationInviterOfGroupInvitationReplied) {
                            notificationForGroupInvitationUpdatedMono =
                                    Mono.just(RequestHandlerResult.Notification.of(
                                            notifyRequesterOtherOnlineSessionsOfGroupInvitationReplied,
                                            invitation.getInviterId(),
                                            clientRequest.turmsRequest()));
                        } else {
                            notificationForGroupInvitationUpdatedMono =
                                    Mono.just(RequestHandlerResult.Notification.of(
                                            notifyRequesterOtherOnlineSessionsOfGroupInvitationReplied,
                                            clientRequest.turmsRequest()));
                        }
                        if (!handleResult.requesterAddedAsNewMember()) {
                            return notificationForGroupInvitationUpdatedMono
                                    .map(RequestHandlerResult::of);
                        }
                        return notificationForGroupInvitationUpdatedMono
                                .flatMap(notificationForGroupInvitationUpdated -> {
                                    if (notifyOtherGroupMembersOfGroupMemberAdded) {
                                        return groupMemberService
                                                .queryGroupMemberIds(invitation.getGroupId(), false)
                                                .map(userIds -> {
                                                    if (!notifyAddedGroupMemberOfGroupMemberAdded) {
                                                        userIds = CollectionUtil.remove(userIds,
                                                                clientRequest.userId());
                                                    }
                                                    return RequestHandlerResult.of(List.of(
                                                            notificationForGroupInvitationUpdated,
                                                            RequestHandlerResult.Notification.of(
                                                                    false,
                                                                    userIds,
                                                                    createCreateMemberNotification(
                                                                            invitation.getGroupId(),
                                                                            clientRequest
                                                                                    .userId()))));
                                                });
                                    } else if (notifyAddedGroupMemberOfGroupMemberAdded) {
                                        return Mono.just(RequestHandlerResult
                                                .of(List.of(notificationForGroupInvitationUpdated,
                                                        RequestHandlerResult.Notification.of(false,
                                                                clientRequest.userId(),
                                                                createCreateMemberNotification(
                                                                        invitation.getGroupId(),
                                                                        clientRequest.userId())))));
                                    }
                                    return Mono.just(RequestHandlerResult
                                            .of(notificationForGroupInvitationUpdated));
                                });
                    });
        };
    }

    private TurmsRequest createCreateMemberNotification(long groupId, long newMemberId) {
        return ClientMessagePool.getTurmsRequestBuilder()
                .setCreateGroupMembersRequest(ClientMessagePool.getCreateGroupMembersRequest()
                        .setGroupId(groupId)
                        .addUserIds(newMemberId)
                        .setRole(GroupMemberRole.MEMBER))
                .build();
    }

    @ServiceRequestMapping(DELETE_GROUP_JOIN_REQUEST_REQUEST)
    public ClientRequestHandler handleDeleteGroupJoinRequestRequest() {
        return clientRequest -> {
            DeleteGroupJoinRequestRequest request = clientRequest.turmsRequest()
                    .getDeleteGroupJoinRequestRequest();
            return groupJoinRequestService
                    .authAndRecallPendingGroupJoinRequest(clientRequest.userId(),
                            request.getRequestId())
                    .flatMap(joinRequest -> {
                        if (notifyGroupMembersOfGroupJoinRequestRecalled) {
                            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                            return groupMemberService
                                    .queryGroupMemberIds(joinRequest.getGroupId(), false)
                                    .map(userIds -> userIds.isEmpty()
                                            ? RequestHandlerResult.of(
                                                    notifyRequesterOtherOnlineSessionsOfGroupJoinRequestRecalled,
                                                    clientRequest.turmsRequest())
                                            : RequestHandlerResult.of(
                                                    notifyRequesterOtherOnlineSessionsOfGroupJoinRequestRecalled,
                                                    CollectionUtil.newSet(userIds),
                                                    clientRequest.turmsRequest()))
                                    .doFinally(signalType -> recyclableSet.recycle());
                        } else if (notifyGroupOwnerAndManagersOfGroupJoinRequestRecalled) {
                            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                            return groupMemberService
                                    .queryGroupManagersAndOwnerId(joinRequest.getGroupId())
                                    .collect(Collectors.toCollection(recyclableSet::getValue))
                                    .map(userIds -> userIds.isEmpty()
                                            ? RequestHandlerResult.of(
                                                    notifyRequesterOtherOnlineSessionsOfGroupJoinRequestRecalled,
                                                    clientRequest.turmsRequest())
                                            : RequestHandlerResult.of(
                                                    notifyRequesterOtherOnlineSessionsOfGroupJoinRequestRecalled,
                                                    CollectionUtil.newSet(userIds),
                                                    clientRequest.turmsRequest()))
                                    .doFinally(signalType -> recyclableSet.recycle());
                        }
                        return Mono.just(RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfGroupJoinRequestRecalled,
                                clientRequest.turmsRequest()));
                    });
        };
    }

    @ServiceRequestMapping(UPDATE_GROUP_JOIN_REQUEST_REQUEST)
    public ClientRequestHandler handleUpdateGroupJoinRequestRequest() {
        return clientRequest -> {
            UpdateGroupJoinRequestRequest request = clientRequest.turmsRequest()
                    .getUpdateGroupJoinRequestRequest();
            return groupJoinRequestService
                    .authAndHandleJoinRequest(clientRequest.userId(),
                            request.getRequestId(),
                            request.getResponseAction(),
                            request.getReason())
                    .flatMap(handleResult -> {
                        GroupJoinRequest joinRequest = handleResult.groupJoinRequest();
                        if (notifyGroupMembersOfGroupJoinRequestReplied) {
                            return groupMemberService
                                    .queryGroupMemberIds(joinRequest.getGroupId(), false)
                                    .map(userIds -> {
                                        List<RequestHandlerResult.Notification> notifications =
                                                new ArrayList<>(2);
                                        notifications.add(RequestHandlerResult.Notification.of(
                                                // Always false because the requester is already a
                                                // group member.
                                                false,
                                                userIds,
                                                clientRequest.turmsRequest()));
                                        if (handleResult.requesterAddedAsNewMember()
                                                && notifyOtherGroupMembersOfGroupMemberAdded) {
                                            notifications.add(RequestHandlerResult.Notification.of(
                                                    // Always false because the requester is already
                                                    // a group member.
                                                    false,
                                                    userIds,
                                                    createCreateMemberNotification(
                                                            joinRequest.getGroupId(),
                                                            joinRequest.getRequesterId())));
                                        }
                                        return RequestHandlerResult.of(notifications);
                                    });
                        }
                        Mono<RequestHandlerResult.Notification> notificationForGroupJoinRequestUpdatedMono;
                        if (notifyGroupOwnerAndManagersOfGroupJoinRequestReplied) {
                            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                            notificationForGroupJoinRequestUpdatedMono = groupMemberService
                                    .queryGroupManagersAndOwnerId(joinRequest.getGroupId())
                                    .collect(Collectors.toCollection(recyclableSet::getValue))
                                    .map(userIds -> RequestHandlerResult.Notification.of(
                                            // Always false because the requester is already a group
                                            // owner or manager.
                                            false,
                                            CollectionUtil.newSet(userIds),
                                            clientRequest.turmsRequest()))
                                    .doFinally(signalType -> recyclableSet.recycle());
                        } else if (notifyGroupJoinRequestSenderOfGroupJoinRequestReplied) {
                            notificationForGroupJoinRequestUpdatedMono =
                                    Mono.just(RequestHandlerResult.Notification.of(
                                            notifyRequesterOtherOnlineSessionsOfGroupJoinRequestReplied,
                                            joinRequest.getRequesterId(),
                                            clientRequest.turmsRequest()));
                        } else {
                            notificationForGroupJoinRequestUpdatedMono =
                                    Mono.just(RequestHandlerResult.Notification.of(
                                            notifyRequesterOtherOnlineSessionsOfGroupJoinRequestReplied,
                                            clientRequest.turmsRequest()));
                        }
                        if (!handleResult.requesterAddedAsNewMember()) {
                            return notificationForGroupJoinRequestUpdatedMono
                                    .map(RequestHandlerResult::of);
                        }
                        return notificationForGroupJoinRequestUpdatedMono
                                .flatMap(notificationForGroupInvitationUpdated -> {
                                    if (notifyOtherGroupMembersOfGroupMemberAdded) {
                                        return groupMemberService
                                                .queryGroupMemberIds(joinRequest.getGroupId(),
                                                        false)
                                                .map(userIds -> {
                                                    if (notifyAddedGroupMemberOfGroupMemberAdded) {
                                                        userIds = CollectionUtil.add(userIds,
                                                                joinRequest.getRequesterId());
                                                    }
                                                    return RequestHandlerResult.of(List.of(
                                                            notificationForGroupInvitationUpdated,
                                                            RequestHandlerResult.Notification.of(
                                                                    false,
                                                                    userIds,
                                                                    createCreateMemberNotification(
                                                                            joinRequest
                                                                                    .getGroupId(),
                                                                            joinRequest
                                                                                    .getRequesterId()))));
                                                });
                                    } else if (notifyAddedGroupMemberOfGroupMemberAdded) {
                                        return Mono.just(RequestHandlerResult.of(List.of(
                                                notificationForGroupInvitationUpdated,
                                                RequestHandlerResult.Notification.of(false,
                                                        joinRequest.getRequesterId(),
                                                        createCreateMemberNotification(
                                                                joinRequest.getGroupId(),
                                                                joinRequest.getRequesterId())))));
                                    }
                                    return Mono.just(RequestHandlerResult
                                            .of(notificationForGroupInvitationUpdated));
                                });
                    });
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
                    .thenReturn(RequestHandlerResult.OK);
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
                        .map(groupInvitationsWithVersion -> RequestHandlerResult
                                .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setGroupInvitationsWithVersion(groupInvitationsWithVersion)
                                        .build()));
            }
            return groupInvitationService
                    .authAndQueryGroupInvitationsWithVersion(clientRequest.userId(),
                            groupId,
                            lastUpdatedDate)
                    .map(groupInvitationsWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                    .map(groupJoinRequestsWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                    .map(groupJoinQuestionsWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                    .thenReturn(RequestHandlerResult.OK);
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
            Long groupId = request.getGroupId();
            return groupMemberService
                    .authAndAddGroupMembers(clientRequest.userId(),
                            groupId,
                            userIds,
                            request.hasRole()
                                    ? request.getRole()
                                    : null,
                            name,
                            muteEndDate,
                            null)
                    .flatMap(newMembers -> {
                        if (notifyOtherGroupMembersOfGroupMemberAdded) {
                            return groupMemberService.queryGroupMemberIds(groupId, false)
                                    .map(memberIds -> {
                                        memberIds = CollectionUtil.remove(memberIds,
                                                clientRequest.userId());
                                        if (!notifyAddedGroupMemberOfGroupMemberAdded) {
                                            memberIds.removeAll(userIds);
                                        }
                                        return RequestHandlerResult
                                                .of(true, memberIds, clientRequest.turmsRequest());
                                    });
                        } else if (notifyAddedGroupMemberOfGroupMemberAdded) {
                            return Mono.just(RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfGroupMemberAdded,
                                    userIds,
                                    clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfGroupMemberAdded,
                                Collections.emptySet(),
                                clientRequest.turmsRequest()));
                    });
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
            Long groupId = request.getGroupId();
            return groupMemberService
                    .authAndDeleteGroupMembers(requesterId,
                            groupId,
                            memberIdsToDelete,
                            successorId,
                            quitAfterTransfer)
                    .flatMap(deletedUserIds -> {
                        if (deletedUserIds.isEmpty()) {
                            // Notify nobody because nothing has happened
                            return Mono.just(RequestHandlerResult.OK);
                        }
                        if (notifyOtherGroupMembersOfGroupMemberRemoved) {
                            return groupMemberService.queryGroupMemberIds(groupId, false)
                                    .map(memberIds -> {
                                        memberIds = CollectionUtil.remove(memberIds,
                                                clientRequest.userId());
                                        if (notifyRemovedGroupMemberOfGroupMemberRemoved) {
                                            memberIds.addAll(deletedUserIds);
                                        }
                                        return RequestHandlerResult
                                                .of(true, memberIds, clientRequest.turmsRequest());
                                    });
                        } else if (notifyRemovedGroupMemberOfGroupMemberRemoved) {
                            return Mono.just(RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfGroupMemberRemoved,
                                    deletedUserIds,
                                    clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfGroupMemberRemoved,
                                clientRequest.turmsRequest()));
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
                        .map(groupMembersWithVersion -> RequestHandlerResult
                                .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setGroupMembersWithVersion(groupMembersWithVersion)
                                        .build()));
            }
            return groupMemberService
                    .authAndQueryGroupMembersWithVersion(clientRequest.userId(),
                            request.getGroupId(),
                            lastUpdatedDate,
                            withStatus)
                    .map(groupMembersWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
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
            Long groupId = request.getGroupId();
            Long updatedMemberId = request.getMemberId();
            return groupMemberService
                    .authAndUpdateGroupMember(clientRequest
                            .userId(), groupId, updatedMemberId, name, role, muteEndDate)
                    .then(Mono.defer(() -> {
                        if (notifyOtherGroupMembersOfGroupMemberInfoUpdated) {
                            return groupMemberService.queryGroupMemberIds(groupId, false)
                                    .map(memberIds -> {
                                        memberIds = CollectionUtil.remove(memberIds,
                                                clientRequest.userId());
                                        if (!notifyUpdatedGroupMemberOfGroupMemberInfoUpdated) {
                                            memberIds.remove(updatedMemberId);
                                        }
                                        return RequestHandlerResult
                                                .of(true, memberIds, clientRequest.turmsRequest());
                                    });
                        } else if (notifyUpdatedGroupMemberOfGroupMemberInfoUpdated) {
                            return Mono.just(RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfGroupMemberInfoUpdated,
                                    updatedMemberId,
                                    clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfGroupMemberInfoUpdated,
                                clientRequest.turmsRequest()));
                    }));
        };
    }

}