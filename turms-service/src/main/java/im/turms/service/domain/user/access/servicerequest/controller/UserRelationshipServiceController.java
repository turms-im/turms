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

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.ResponseAction;
import im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest;
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
import im.turms.server.common.infra.property.env.service.business.NotificationProperties;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.user.service.UserFriendRequestService;
import im.turms.service.domain.user.service.UserRelationshipGroupService;
import im.turms.service.domain.user.service.UserRelationshipService;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_FRIEND_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_RELATIONSHIP_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_RELATIONSHIP_REQUEST;
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
    private boolean notifyRecipientWhenReceivingFriendRequest;
    private boolean notifyRelatedUserAfterAddedToOneSidedRelationshipGroupByOthers;
    private boolean notifyMembersAfterOneSidedRelationshipGroupUpdatedByOthers;
    private boolean notifyMemberAfterRemovedFromRelationshipGroupByOthers;
    private boolean notifyRequesterAfterFriendRequestUpdated;
    private boolean notifyRelatedUserAfterOneSidedRelationshipUpdatedByOthers;

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
        notifyRecipientWhenReceivingFriendRequest =
                notificationProperties.isNotifyRecipientWhenReceivingFriendRequest();
        notifyRelatedUserAfterAddedToOneSidedRelationshipGroupByOthers = notificationProperties
                .isNotifyRelatedUserAfterAddedToOneSidedRelationshipGroupByOthers();
        notifyMembersAfterOneSidedRelationshipGroupUpdatedByOthers = notificationProperties
                .isNotifyMembersAfterOneSidedRelationshipGroupUpdatedByOthers();
        notifyMemberAfterRemovedFromRelationshipGroupByOthers =
                notificationProperties.isNotifyMemberAfterRemovedFromRelationshipGroupByOthers();
        notifyRequesterAfterFriendRequestUpdated =
                notificationProperties.isNotifyRequesterAfterFriendRequestUpdated();
        notifyRelatedUserAfterOneSidedRelationshipUpdatedByOthers = notificationProperties
                .isNotifyRelatedUserAfterOneSidedRelationshipUpdatedByOthers();
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
                    .map(friendRequest -> notifyRecipientWhenReceivingFriendRequest
                            ? RequestHandlerResultFactory.getByDataLong(friendRequest.getId(),
                                    request.getRecipientId(),
                                    clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.getByDataLong(friendRequest.getId()));
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
                    .map(group -> RequestHandlerResultFactory.getByDataLong(group.getKey()
                            .getGroupIndex()
                            .longValue()));
        };
    }

    @ServiceRequestMapping(CREATE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleCreateRelationshipRequest() {
        return clientRequest -> {
            CreateRelationshipRequest request = clientRequest.turmsRequest()
                    .getCreateRelationshipRequest();
            int groupIndex = request.hasGroupIndex()
                    ? request.getGroupIndex()
                    : DEFAULT_RELATIONSHIP_GROUP_INDEX;
            Date blockDate = request.getBlocked()
                    ? new Date()
                    : null;
            Long relatedUserId = request.getUserId();
            return userRelationshipService.upsertOneSidedRelationship(clientRequest
                    .userId(), relatedUserId, blockDate, groupIndex, null, new Date(), false, null)
                    .then(Mono.fromCallable(
                            () -> notifyRelatedUserAfterAddedToOneSidedRelationshipGroupByOthers
                                    ? RequestHandlerResultFactory.get(relatedUserId,
                                            clientRequest.turmsRequest())
                                    : RequestHandlerResultFactory.OK));
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
            if (notifyMembersAfterOneSidedRelationshipGroupUpdatedByOthers) {
                Recyclable<List<Long>> recyclableList = ListRecycler.obtain();
                return userRelationshipGroupService
                        .queryRelationshipGroupMemberIds(clientRequest.userId(), groupIndex)
                        .collect(Collectors.toCollection(recyclableList::getValue))
                        .flatMap(
                                memberIds -> userRelationshipGroupService
                                        .deleteRelationshipGroupAndMoveMembers(clientRequest
                                                .userId(), groupIndex, targetGroupIndex)
                                        .then(Mono.fromCallable(() -> memberIds.isEmpty()
                                                ? RequestHandlerResultFactory.OK
                                                : RequestHandlerResultFactory.get(
                                                        CollectionUtil.newSet(memberIds),
                                                        clientRequest.turmsRequest()))))
                        .doFinally(signalType -> recyclableList.recycle());
            }
            return userRelationshipGroupService
                    .deleteRelationshipGroupAndMoveMembers(clientRequest.userId(),
                            groupIndex,
                            targetGroupIndex)
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(DELETE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleDeleteRelationshipRequest() {
        return clientRequest -> {
            DeleteRelationshipRequest request = clientRequest.turmsRequest()
                    .getDeleteRelationshipRequest();
            Mono<Void> deleteMono;
            if (deleteTwoSidedRelationships) {
                deleteMono = userRelationshipService
                        .deleteTwoSidedRelationships(clientRequest.userId(), request.getUserId());
            } else {
                deleteMono =
                        userRelationshipService.deleteOneSidedRelationship(clientRequest.userId(),
                                request.getUserId(),
                                null);
            }
            return deleteMono.then(
                    Mono.fromCallable(() -> notifyMemberAfterRemovedFromRelationshipGroupByOthers
                            ? RequestHandlerResultFactory.get(request.getUserId(),
                                    clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.OK));
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
                    .map(friendRequestsWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                    .map(idsWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                    .map(groupsWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                    .map(relationshipsWithVersion -> RequestHandlerResultFactory
                            .get(ClientMessagePool.getTurmsNotificationDataBuilder()
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
                    .then(Mono.fromCallable(() -> notifyRequesterAfterFriendRequestUpdated
                            ? RequestHandlerResultFactory.get(request.getRequestId(),
                                    clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(UPDATE_RELATIONSHIP_GROUP_REQUEST)
    public ClientRequestHandler handleUpdateRelationshipGroupRequest() {
        return clientRequest -> {
            UpdateRelationshipGroupRequest request = clientRequest.turmsRequest()
                    .getUpdateRelationshipGroupRequest();
            return userRelationshipGroupService
                    .updateRelationshipGroupName(clientRequest.userId(),
                            request.getGroupIndex(),
                            request.getNewName())
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(UPDATE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleUpdateRelationshipRequest() {
        return clientRequest -> {
            UpdateRelationshipRequest request = clientRequest.turmsRequest()
                    .getUpdateRelationshipRequest();
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
                    .upsertOneSidedRelationship(clientRequest.userId(),
                            request.getUserId(),
                            blockDate,
                            newGroupIndex,
                            deleteGroupIndex,
                            null,
                            true,
                            null)
                    .then(Mono.fromCallable(
                            () -> notifyRelatedUserAfterOneSidedRelationshipUpdatedByOthers
                                    ? RequestHandlerResultFactory.get(request.getUserId(),
                                            clientRequest.turmsRequest())
                                    : RequestHandlerResultFactory.OK));
        };
    }

}