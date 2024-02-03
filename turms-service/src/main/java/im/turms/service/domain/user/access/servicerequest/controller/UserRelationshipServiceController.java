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

package im.turms.service.domain.user.access.servicerequest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mongodb.client.result.UpdateResult;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.ResponseAction;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.DeleteFriendRequestRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.business.notification.NotificationProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationFriendRequestCreatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationFriendRequestRecalledProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationFriendRequestRepliedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationOneSidedRelationshipGroupDeletedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationOneSidedRelationshipGroupMemberAddedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationOneSidedRelationshipGroupMemberRemovedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationOneSidedRelationshipGroupUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationOneSidedRelationshipUpdatedProperties;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.recycler.SetRecycler;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.user.po.UserFriendRequest;
import im.turms.service.domain.user.service.UserFriendRequestService;
import im.turms.service.domain.user.service.UserRelationshipGroupService;
import im.turms.service.domain.user.service.UserRelationshipService;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_FRIEND_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_RELATIONSHIP_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_RELATIONSHIP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_FRIEND_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_RELATIONSHIP_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_RELATIONSHIP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_FRIEND_REQUESTS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RELATED_USER_IDS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RELATIONSHIPS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RELATIONSHIP_GROUPS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_FRIEND_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_RELATIONSHIP_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_RELATIONSHIP_REQUEST;
import static im.turms.server.common.domain.user.constant.UserConst.DEFAULT_RELATIONSHIP_GROUP_INDEX;

/**
 * @author James Chen
 */
@Controller
public class UserRelationshipServiceController extends BaseServiceController {

    private final UserRelationshipService userRelationshipService;
    private final UserRelationshipGroupService userRelationshipGroupService;
    private final UserFriendRequestService userFriendRequestService;

    private boolean deleteTwoSidedRelationships;

    private boolean notifyRequesterOtherOnlineSessionsOfFriendRequestCreated;
    private boolean notifyFriendRequestRecipientOfFriendRequestCreated;

    private boolean notifyRequesterOtherOnlineSessionsOfFriendRequestRecalled;
    private boolean notifyFriendRequestRecipientOfFriendRequestRecalled;

    private boolean notifyRequesterOtherOnlineSessionsOfFriendRequestReplied;
    private boolean notifyFriendRequestSenderOfFriendRequestReplied;

    private boolean notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipUpdated;
    private boolean notifyRelatedUserOfOneSidedRelationshipUpdated;

    private boolean notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupDeleted;
    private boolean notifyRelationshipGroupMembersOfOneSidedRelationshipGroupDeleted;

    private boolean notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupUpdated;
    private boolean notifyRelationshipGroupMembersOfOneSidedRelationshipGroupUpdated;

    private boolean notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupMemberAdded;
    private boolean notifyNewRelationshipGroupMemberOfOneSidedRelationshipGroupMemberAdded;

    private boolean notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupMemberRemoved;
    private boolean notifyRemovedRelationshipGroupMemberOfOneSidedRelationshipGroupMemberRemoved;

    public UserRelationshipServiceController(
            TurmsPropertiesManager propertiesManager,
            UserRelationshipService userRelationshipService,
            UserRelationshipGroupService userRelationshipGroupService,
            UserFriendRequestService userFriendRequestService) {
        this.userFriendRequestService = userFriendRequestService;
        this.userRelationshipService = userRelationshipService;
        this.userRelationshipGroupService = userRelationshipGroupService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        deleteTwoSidedRelationships = properties.getService()
                .getUser()
                .isDeleteTwoSidedRelationships();

        NotificationProperties notificationProperties = properties.getService()
                .getNotification();

        NotificationFriendRequestCreatedProperties notificationFriendRequestCreatedProperties =
                notificationProperties.getFriendRequestCreated();
        notifyRequesterOtherOnlineSessionsOfFriendRequestCreated =
                notificationFriendRequestCreatedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyFriendRequestRecipientOfFriendRequestCreated =
                notificationFriendRequestCreatedProperties.isNotifyFriendRequestRecipient();

        NotificationFriendRequestRecalledProperties notificationFriendRequestRecalledProperties =
                notificationProperties.getFriendRequestRecalled();
        notifyRequesterOtherOnlineSessionsOfFriendRequestRecalled =
                notificationFriendRequestRecalledProperties.isNotifyRequesterOtherOnlineSessions();
        notifyFriendRequestRecipientOfFriendRequestRecalled =
                notificationFriendRequestRecalledProperties.isNotifyFriendRequestRecipient();

        NotificationFriendRequestRepliedProperties notificationFriendRequestRepliedProperties =
                notificationProperties.getFriendRequestReplied();
        notifyRequesterOtherOnlineSessionsOfFriendRequestReplied =
                notificationFriendRequestRepliedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyFriendRequestSenderOfFriendRequestReplied =
                notificationFriendRequestRepliedProperties.isNotifyFriendRequestSender();

        NotificationOneSidedRelationshipUpdatedProperties notificationOneSidedRelationshipUpdatedProperties =
                notificationProperties.getOneSidedRelationshipUpdated();
        notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipUpdated =
                notificationOneSidedRelationshipUpdatedProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyRelatedUserOfOneSidedRelationshipUpdated =
                notificationOneSidedRelationshipUpdatedProperties.isNotifyRelatedUser();

        NotificationOneSidedRelationshipGroupDeletedProperties notificationOneSidedRelationshipGroupDeletedProperties =
                notificationProperties.getOneSidedRelationshipGroupDeleted();
        notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupDeleted =
                notificationOneSidedRelationshipGroupDeletedProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyRelationshipGroupMembersOfOneSidedRelationshipGroupDeleted =
                notificationOneSidedRelationshipGroupDeletedProperties
                        .isNotifyRelationshipGroupMembers();

        NotificationOneSidedRelationshipGroupUpdatedProperties notificationOneSidedRelationshipGroupUpdatedProperties =
                notificationProperties.getOneSidedRelationshipGroupUpdated();
        notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupUpdated =
                notificationOneSidedRelationshipGroupUpdatedProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyRelationshipGroupMembersOfOneSidedRelationshipGroupUpdated =
                notificationOneSidedRelationshipGroupUpdatedProperties
                        .isNotifyRelationshipGroupMembers();

        NotificationOneSidedRelationshipGroupMemberAddedProperties notificationOneSidedRelationshipGroupMemberAddedProperties =
                notificationProperties.getOneSidedRelationshipGroupMemberAdded();
        notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupMemberAdded =
                notificationOneSidedRelationshipGroupMemberAddedProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyNewRelationshipGroupMemberOfOneSidedRelationshipGroupMemberAdded =
                notificationOneSidedRelationshipGroupMemberAddedProperties
                        .isNotifyNewRelationshipGroupMember();

        NotificationOneSidedRelationshipGroupMemberRemovedProperties notificationOneSidedRelationshipGroupMemberRemovedProperties =
                notificationProperties.getOneSidedRelationshipGroupMemberRemoved();
        notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupMemberRemoved =
                notificationOneSidedRelationshipGroupMemberRemovedProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyRemovedRelationshipGroupMemberOfOneSidedRelationshipGroupMemberRemoved =
                notificationOneSidedRelationshipGroupMemberRemovedProperties
                        .isNotifyRemovedRelationshipGroupMember();
    }

    @ServiceRequestMapping(CREATE_FRIEND_REQUEST_REQUEST)
    public ClientRequestHandler handleCreateFriendRequestRequest() {
        return clientRequest -> {
            CreateFriendRequestRequest request = clientRequest.turmsRequest()
                    .getCreateFriendRequestRequest();
            return userFriendRequestService
                    .authAndCreateFriendRequest(clientRequest.userId(),
                            request.getRecipientId(),
                            request.getContent(),
                            new Date())
                    .map(friendRequest -> notifyFriendRequestRecipientOfFriendRequestCreated
                            ? RequestHandlerResult.ofDataLong(friendRequest.getId(),
                                    notifyRequesterOtherOnlineSessionsOfFriendRequestCreated,
                                    request.getRecipientId(),
                                    clientRequest.turmsRequest())
                            : RequestHandlerResult.ofDataLong(friendRequest.getId(),
                                    notifyRequesterOtherOnlineSessionsOfFriendRequestCreated,
                                    clientRequest.turmsRequest()));
        };
    }

    @ServiceRequestMapping(CREATE_RELATIONSHIP_GROUP_REQUEST)
    public ClientRequestHandler handleCreateRelationshipGroupRequest() {
        return clientRequest -> {
            CreateRelationshipGroupRequest request = clientRequest.turmsRequest()
                    .getCreateRelationshipGroupRequest();
            return userRelationshipGroupService
                    .createRelationshipGroup(clientRequest
                            .userId(), null, request.getName(), new Date(), null)
                    .map(group -> RequestHandlerResult.ofDataLong(group.getKey()
                            .getGroupIndex()
                            .longValue()));
        };
    }

    @ServiceRequestMapping(CREATE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleCreateRelationshipRequest() {
        return clientRequest -> {
            CreateRelationshipRequest request = clientRequest.turmsRequest()
                    .getCreateRelationshipRequest();
            String name = request.hasName()
                    ? request.getName()
                    : null;
            int groupIndex = request.hasGroupIndex()
                    ? request.getGroupIndex()
                    : DEFAULT_RELATIONSHIP_GROUP_INDEX;
            Date blockDate = request.getBlocked()
                    ? new Date()
                    : null;
            Long relatedUserId = request.getUserId();
            return userRelationshipService
                    .upsertOneSidedRelationship(clientRequest.userId(),
                            relatedUserId,
                            name,
                            blockDate,
                            groupIndex,
                            null,
                            new Date(),
                            false,
                            null)
                    .then(Mono.fromCallable(() -> {
                        TurmsRequest notification = clientRequest.turmsRequest();
                        if (!request.hasGroupIndex()) {
                            TurmsRequest.Builder builder =
                                    ClientMessagePool.getTurmsRequestBuilder()
                                            .mergeFrom(notification);
                            notification = builder
                                    .setCreateRelationshipRequest(
                                            builder.getCreateRelationshipRequestBuilder()
                                                    .setGroupIndex(groupIndex))
                                    .build();
                        }
                        return notifyNewRelationshipGroupMemberOfOneSidedRelationshipGroupMemberAdded
                                ? RequestHandlerResult.of(
                                        notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupMemberAdded,
                                        relatedUserId,
                                        notification)
                                : RequestHandlerResult.of(
                                        notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupMemberAdded,
                                        notification);
                    }));
        };
    }

    @ServiceRequestMapping(DELETE_FRIEND_REQUEST_REQUEST)
    public ClientRequestHandler handleDeleteFriendRequestRequest() {
        return clientRequest -> {
            DeleteFriendRequestRequest request = clientRequest.turmsRequest()
                    .getDeleteFriendRequestRequest();
            return userFriendRequestService
                    .authAndRecallFriendRequest(clientRequest.userId(), request.getRequestId())
                    .flatMap(friendRequest -> notifyFriendRequestRecipientOfFriendRequestRecalled
                            ? Mono.just(RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfFriendRequestRecalled,
                                    friendRequest.getRecipientId(),
                                    clientRequest.turmsRequest()))
                            : Mono.just(RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfFriendRequestRecalled,
                                    clientRequest.turmsRequest())));
        };
    }

    @ServiceRequestMapping(DELETE_RELATIONSHIP_GROUP_REQUEST)
    public ClientRequestHandler handleDeleteRelationshipGroupRequest() {
        return clientRequest -> {
            DeleteRelationshipGroupRequest request = clientRequest.turmsRequest()
                    .getDeleteRelationshipGroupRequest();
            Integer groupIndex = request.getGroupIndex();
            int targetGroupIndex = request.hasTargetGroupIndex()
                    ? request.getTargetGroupIndex()
                    : DEFAULT_RELATIONSHIP_GROUP_INDEX;
            if (notifyRelationshipGroupMembersOfOneSidedRelationshipGroupDeleted) {
                Recyclable<List<Long>> recyclableList = ListRecycler.obtain();
                return userRelationshipGroupService
                        .queryRelationshipGroupMemberIds(clientRequest.userId(), groupIndex)
                        .collect(Collectors.toCollection(recyclableList::getValue))
                        .flatMap(
                                memberIds -> userRelationshipGroupService
                                        .deleteRelationshipGroupAndMoveMembers(clientRequest
                                                .userId(), groupIndex, targetGroupIndex)
                                        .then(Mono.fromCallable(() -> {
                                            TurmsRequest notification =
                                                    clientRequest.turmsRequest();
                                            if (!request.hasTargetGroupIndex()) {
                                                TurmsRequest.Builder builder =
                                                        ClientMessagePool.getTurmsRequestBuilder()
                                                                .mergeFrom(notification);
                                                notification = builder
                                                        .setDeleteRelationshipGroupRequest(builder
                                                                .getDeleteRelationshipGroupRequestBuilder()
                                                                .setTargetGroupIndex(
                                                                        targetGroupIndex))
                                                        .build();
                                            }
                                            return memberIds.isEmpty()
                                                    ? RequestHandlerResult.of(
                                                            notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupDeleted,
                                                            notification)
                                                    : RequestHandlerResult.of(
                                                            notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupDeleted,
                                                            CollectionUtil.newSet(memberIds),
                                                            notification);
                                        })))
                        .doFinally(signalType -> recyclableList.recycle());
            }
            TurmsRequest notification = clientRequest.turmsRequest();
            if (!request.hasTargetGroupIndex()) {
                TurmsRequest.Builder builder = ClientMessagePool.getTurmsRequestBuilder()
                        .mergeFrom(notification);
                notification = builder
                        .setDeleteRelationshipGroupRequest(
                                builder.getDeleteRelationshipGroupRequestBuilder()
                                        .setTargetGroupIndex(targetGroupIndex))
                        .build();
            }
            return userRelationshipGroupService
                    .deleteRelationshipGroupAndMoveMembers(clientRequest.userId(),
                            groupIndex,
                            targetGroupIndex)
                    .thenReturn(RequestHandlerResult.of(
                            notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupDeleted,
                            notification));
        };
    }

    @ServiceRequestMapping(DELETE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleDeleteRelationshipRequest() {
        return clientRequest -> {
            DeleteRelationshipRequest request = clientRequest.turmsRequest()
                    .getDeleteRelationshipRequest();
            Mono<?> deleteMono;
            if (deleteTwoSidedRelationships) {
                deleteMono = userRelationshipService.tryDeleteTwoSidedRelationships(
                        clientRequest.userId(),
                        request.getUserId(),
                        request.hasGroupIndex()
                                ? request.getGroupIndex()
                                : null);
            } else {
                deleteMono =
                        userRelationshipService.deleteOneSidedRelationship(clientRequest.userId(),
                                request.getUserId(),
                                request.hasGroupIndex()
                                        ? request.getGroupIndex()
                                        : null,
                                null);
            }
            return deleteMono.then(Mono.fromCallable(
                    () -> notifyRemovedRelationshipGroupMemberOfOneSidedRelationshipGroupMemberRemoved
                            ? RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupMemberRemoved,
                                    request.getUserId(),
                                    clientRequest.turmsRequest())
                            : RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupMemberRemoved,
                                    clientRequest.turmsRequest())));
        };
    }

    @ServiceRequestMapping(QUERY_FRIEND_REQUESTS_REQUEST)
    public ClientRequestHandler handleQueryFriendRequestsRequest() {
        return clientRequest -> {
            QueryFriendRequestsRequest request = clientRequest.turmsRequest()
                    .getQueryFriendRequestsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return userFriendRequestService
                    .queryFriendRequestsWithVersion(clientRequest.userId(),
                            request.getAreSentByMe(),
                            lastUpdatedDate)
                    .map(friendRequestsWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setUserFriendRequestsWithVersion(friendRequestsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_RELATED_USER_IDS_REQUEST)
    public ClientRequestHandler handleQueryRelatedUserIdsRequest() {
        return clientRequest -> {
            QueryRelatedUserIdsRequest request = clientRequest.turmsRequest()
                    .getQueryRelatedUserIdsRequest();
            List<Integer> groupIndexesList = request.getGroupIndexesList();
            Set<Integer> groupIndexes = groupIndexesList.isEmpty()
                    ? null
                    : CollectionUtil.newSet(groupIndexesList);
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            Boolean isBlocked = request.hasBlocked()
                    ? request.getBlocked()
                    : null;
            return userRelationshipService
                    .queryRelatedUserIdsWithVersion(clientRequest.userId(),
                            groupIndexes,
                            isBlocked,
                            lastUpdatedDate)
                    .map(idsWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setLongsWithVersion(idsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_RELATIONSHIP_GROUPS_REQUEST)
    public ClientRequestHandler handleQueryRelationshipGroupsRequest() {
        return clientRequest -> {
            QueryRelationshipGroupsRequest request = clientRequest.turmsRequest()
                    .getQueryRelationshipGroupsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return userRelationshipGroupService
                    .queryRelationshipGroupsInfosWithVersion(clientRequest.userId(),
                            lastUpdatedDate)
                    .map(groupsWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setUserRelationshipGroupsWithVersion(groupsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_RELATIONSHIPS_REQUEST)
    public ClientRequestHandler handleQueryRelationshipsRequest() {
        return clientRequest -> {
            QueryRelationshipsRequest request = clientRequest.turmsRequest()
                    .getQueryRelationshipsRequest();
            List<Long> userIdsList = request.getUserIdsList();
            List<Integer> groupIndexesList = request.getGroupIndexesList();
            Set<Long> relatedUserIds = userIdsList.isEmpty()
                    ? null
                    : CollectionUtil.newSet(userIdsList);
            Set<Integer> groupIndexes = groupIndexesList.isEmpty()
                    ? null
                    : CollectionUtil.newSet(groupIndexesList);
            Boolean isBlocked = request.hasBlocked()
                    ? request.getBlocked()
                    : null;
            Date lastUpdatedDate = request.hasLastUpdatedDate()
                    ? new Date(request.getLastUpdatedDate())
                    : null;
            return userRelationshipService
                    .queryRelationshipsWithVersion(clientRequest
                            .userId(), relatedUserIds, groupIndexes, isBlocked, lastUpdatedDate)
                    .map(relationshipsWithVersion -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setUserRelationshipsWithVersion(relationshipsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(UPDATE_FRIEND_REQUEST_REQUEST)
    public ClientRequestHandler handleUpdateFriendRequestRequest() {
        return clientRequest -> {
            UpdateFriendRequestRequest request = clientRequest.turmsRequest()
                    .getUpdateFriendRequestRequest();
            ResponseAction action = request.getResponseAction();
            String reason = request.hasReason()
                    ? request.getReason()
                    : null;
            return userFriendRequestService
                    .authAndHandleFriendRequest(request.getRequestId(),
                            clientRequest.userId(),
                            action,
                            reason)
                    .map(handleResult -> {
                        List<RequestHandlerResult.Notification> notifications = new ArrayList<>(3);
                        UserFriendRequest friendedRequest = handleResult.friendRequest();
                        Long friendedRequestRequesterId = friendedRequest.getRequesterId();
                        notifications.add(notifyFriendRequestSenderOfFriendRequestReplied
                                ? RequestHandlerResult.Notification.of(
                                        notifyRequesterOtherOnlineSessionsOfFriendRequestReplied,
                                        friendedRequestRequesterId,
                                        clientRequest.turmsRequest())
                                : RequestHandlerResult.Notification.of(
                                        notifyRequesterOtherOnlineSessionsOfFriendRequestReplied,
                                        clientRequest.turmsRequest()));

                        if (notifyNewRelationshipGroupMemberOfOneSidedRelationshipGroupMemberAdded) {
                            Integer newGroupIndexForFriendRequestRequester =
                                    handleResult.newGroupIndexForFriendRequestRequester();
                            if (newGroupIndexForFriendRequestRequester != null) {
                                notifications.add(RequestHandlerResult.Notification.of(
                                        // The client request sender is always the friend request
                                        // recipient,
                                        // so this is always false.
                                        false,
                                        friendedRequestRequesterId,
                                        ClientMessagePool.getTurmsRequestBuilder()
                                                .setCreateRelationshipRequest(ClientMessagePool
                                                        .getCreateRelationshipRequestBuilder()
                                                        .setUserId(friendedRequestRequesterId)
                                                        .setGroupIndex(
                                                                newGroupIndexForFriendRequestRequester)
                                                        .build())
                                                .build()));
                            }
                            Integer newGroupIndexForFriendRequestRecipient =
                                    handleResult.newGroupIndexForFriendRequestRecipient();
                            if (newGroupIndexForFriendRequestRecipient != null) {
                                Long recipientId = friendedRequest.getRecipientId();
                                notifications.add(RequestHandlerResult.Notification.of(
                                        // The client request sender is always the friend request
                                        // recipient,
                                        notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupMemberAdded,
                                        recipientId,
                                        ClientMessagePool.getTurmsRequestBuilder()
                                                .setCreateRelationshipRequest(ClientMessagePool
                                                        .getCreateRelationshipRequestBuilder()
                                                        .setUserId(recipientId)
                                                        .setGroupIndex(
                                                                newGroupIndexForFriendRequestRecipient)
                                                        .build())
                                                .build()));
                            }
                        }
                        return RequestHandlerResult.of(notifications);
                    });
        };
    }

    @ServiceRequestMapping(UPDATE_RELATIONSHIP_GROUP_REQUEST)
    public ClientRequestHandler handleUpdateRelationshipGroupRequest() {
        return clientRequest -> {
            UpdateRelationshipGroupRequest request = clientRequest.turmsRequest()
                    .getUpdateRelationshipGroupRequest();
            Long ownerId = clientRequest.userId();
            Integer groupIndex = request.getGroupIndex();
            Mono<UpdateResult> updateRelationshipGroup = userRelationshipGroupService
                    .updateRelationshipGroupName(ownerId, groupIndex, request.getNewName());
            if (notifyRelationshipGroupMembersOfOneSidedRelationshipGroupUpdated) {
                Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                return userRelationshipGroupService
                        .queryRelationshipGroupMemberIds(ownerId, groupIndex)
                        .collect(Collectors.toCollection(recyclableSet::getValue))
                        .map(memberIds -> memberIds.isEmpty()
                                ? RequestHandlerResult.of(
                                        notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupUpdated,
                                        clientRequest.turmsRequest())
                                : RequestHandlerResult.of(
                                        notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupUpdated,
                                        CollectionUtil.newSet(memberIds),
                                        clientRequest.turmsRequest()))
                        .doFinally(signalType -> recyclableSet.recycle());
            }
            return updateRelationshipGroup.thenReturn(RequestHandlerResult.of(
                    notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipGroupUpdated,
                    clientRequest.turmsRequest()));
        };
    }

    @ServiceRequestMapping(UPDATE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleUpdateRelationshipRequest() {
        return clientRequest -> {
            UpdateRelationshipRequest request = clientRequest.turmsRequest()
                    .getUpdateRelationshipRequest();
            String name = request.hasName()
                    ? request.getName()
                    : null;
            Date blockDate = request.hasBlocked() && request.getBlocked()
                    ? new Date()
                    : null;
            Integer newGroupIndex = request.hasNewGroupIndex()
                    ? request.getNewGroupIndex()
                    : null;
            Integer deleteGroupIndex = request.hasDeleteGroupIndex()
                    ? request.getDeleteGroupIndex()
                    : null;
            return userRelationshipService
                    // TODO: We should update the relationship if it exists rather than upserting
                    // so that the usages of the update request won't overlay with the create and
                    // delete request.
                    .upsertOneSidedRelationship(clientRequest.userId(),
                            request.getUserId(),
                            name,
                            blockDate,
                            newGroupIndex,
                            deleteGroupIndex,
                            null,
                            true,
                            null)
                    .then(Mono.fromCallable(() -> notifyRelatedUserOfOneSidedRelationshipUpdated
                            ? RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipUpdated,
                                    request.getUserId(),
                                    clientRequest.turmsRequest())
                            : RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfOneSidedRelationshipUpdated,
                                    clientRequest.turmsRequest())));
        };
    }

}