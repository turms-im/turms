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

import im.turms.common.constant.ResponseAction;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.user.relationship.*;
import im.turms.server.common.cluster.node.Node;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.turms.workflow.service.impl.user.relationship.UserFriendRequestService;
import im.turms.turms.workflow.service.impl.user.relationship.UserRelationshipGroupService;
import im.turms.turms.workflow.service.impl.user.relationship.UserRelationshipService;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.*;

/**
 * @author James Chen
 */
@Controller
public class UserRelationshipServiceController {

    private final UserRelationshipService userRelationshipService;
    private final UserRelationshipGroupService userRelationshipGroupService;
    private final UserFriendRequestService userFriendRequestService;
    private final Node node;

    public UserRelationshipServiceController(
            Node node,
            UserRelationshipService userRelationshipService,
            UserRelationshipGroupService userRelationshipGroupService,
            UserFriendRequestService userFriendRequestService) {
        this.node = node;
        this.userFriendRequestService = userFriendRequestService;
        this.userRelationshipService = userRelationshipService;
        this.userRelationshipGroupService = userRelationshipGroupService;
    }

    @ServiceRequestMapping(CREATE_FRIEND_REQUEST_REQUEST)
    public ClientRequestHandler handleCreateFriendRequestRequest() {
        return clientRequest -> {
            CreateFriendRequestRequest request = clientRequest.getTurmsRequest().getCreateFriendRequestRequest();
            return userFriendRequestService.authAndCreateFriendRequest(
                    clientRequest.getUserId(),
                    request.getRecipientId(),
                    request.getContent(),
                    new Date())
                    .map(friendRequest -> node.getSharedProperties()
                            .getService()
                            .getNotification().isNotifyRecipientWhenReceivingFriendRequest()
                            ? RequestHandlerResultFactory.get(friendRequest.getId(), request.getRecipientId(), clientRequest.getTurmsRequest())
                            : RequestHandlerResultFactory.get(friendRequest.getId()));
        };
    }

    @ServiceRequestMapping(CREATE_RELATIONSHIP_GROUP_REQUEST)
    public ClientRequestHandler handleCreateRelationshipGroupRequest() {
        return clientRequest -> {
            CreateRelationshipGroupRequest request = clientRequest.getTurmsRequest().getCreateRelationshipGroupRequest();
            return userRelationshipGroupService.createRelationshipGroup(
                    clientRequest.getUserId(),
                    null,
                    request.getName(),
                    new Date(),
                    null)
                    .map(group -> RequestHandlerResultFactory.get(group.getKey().getGroupIndex().longValue()));
        };
    }

    @ServiceRequestMapping(CREATE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleCreateRelationshipRequest() {
        return clientRequest -> {
            CreateRelationshipRequest request = clientRequest.getTurmsRequest().getCreateRelationshipRequest();
            // It is unnecessary to check whether requester is in the blacklist of the target user
            // because only a one-sided relationship will be created here
            int groupIndex = request.hasGroupIndex() ?
                    request.getGroupIndex().getValue() : DaoConstant.DEFAULT_RELATIONSHIP_GROUP_INDEX;
            Date blockDate = request.getBlocked() ? new Date() : null;
            return userRelationshipService.upsertOneSidedRelationship(
                    clientRequest.getUserId(),
                    request.getUserId(),
                    blockDate,
                    groupIndex,
                    null,
                    new Date(),
                    false,
                    null)
                    .then(Mono.fromCallable(() ->
                            node.getSharedProperties().getService().getNotification().isNotifyRelatedUserAfterAddedToOneSidedRelationshipGroupByOthers()
                                    ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.getTurmsRequest())
                                    : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(DELETE_RELATIONSHIP_GROUP_REQUEST)
    public ClientRequestHandler handleDeleteRelationshipGroupRequest() {
        return clientRequest -> {
            DeleteRelationshipGroupRequest request = clientRequest.getTurmsRequest().getDeleteRelationshipGroupRequest();
            Integer groupIndex = request.getGroupIndex();
            int targetGroupIndex = request.hasTargetGroupIndex() ?
                    request.getTargetGroupIndex().getValue() : DaoConstant.DEFAULT_RELATIONSHIP_GROUP_INDEX;
            if (node.getSharedProperties().getService().getNotification()
                    .isNotifyMembersAfterOneSidedRelationshipGroupUpdatedByOthers()) {
                return userRelationshipGroupService.queryRelatedUserIdsInRelationshipGroup(
                        clientRequest.getUserId(),
                        groupIndex)
                        .collect(Collectors.toSet())
                        .flatMap(ids -> userRelationshipGroupService.deleteRelationshipGroupAndMoveMembers(
                                clientRequest.getUserId(),
                                groupIndex,
                                targetGroupIndex)
                                .then(Mono.fromCallable(() -> ids.isEmpty()
                                        ? RequestHandlerResultFactory.OK
                                        : RequestHandlerResultFactory.get(ids, clientRequest.getTurmsRequest()))));
            }
            return userRelationshipGroupService.deleteRelationshipGroupAndMoveMembers(
                    clientRequest.getUserId(),
                    groupIndex,
                    targetGroupIndex)
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(DELETE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleDeleteRelationshipRequest() {
        return clientRequest -> {
            DeleteRelationshipRequest request = clientRequest.getTurmsRequest().getDeleteRelationshipRequest();
            boolean deleteTwoSidedRelationships = node.getSharedProperties().getService().getUser().isDeleteTwoSidedRelationships();
            Mono<Void> deleteMono;
            if (deleteTwoSidedRelationships) {
                deleteMono = userRelationshipService.deleteTwoSidedRelationships(
                        clientRequest.getUserId(),
                        request.getUserId());
            } else {
                deleteMono = userRelationshipService.deleteOneSidedRelationship(
                        clientRequest.getUserId(),
                        request.getUserId(),
                        null);
            }
            return deleteMono.then(Mono.fromCallable(() -> node.getSharedProperties().getService().getNotification().isNotifyMemberAfterRemovedFromRelationshipGroupByOthers()
                    ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.getTurmsRequest())
                    : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(QUERY_FRIEND_REQUESTS_REQUEST)
    public ClientRequestHandler handleQueryFriendRequestsRequest() {
        return clientRequest -> {
            QueryFriendRequestsRequest request = clientRequest.getTurmsRequest().getQueryFriendRequestsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate().getValue()) : null;
            return userFriendRequestService.queryFriendRequestsWithVersion(
                    clientRequest.getUserId(),
                    request.getAreSentByMe(),
                    lastUpdatedDate)
                    .map(friendRequestsWithVersion -> RequestHandlerResultFactory
                            .get(TurmsNotification.Data
                                    .newBuilder()
                                    .setUserFriendRequestsWithVersion(friendRequestsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_RELATED_USER_IDS_REQUEST)
    public ClientRequestHandler handleQueryRelatedUserIdsRequest() {
        return clientRequest -> {
            QueryRelatedUserIdsRequest request = clientRequest.getTurmsRequest().getQueryRelatedUserIdsRequest();
            int groupIndex = request.hasGroupIndex() ? request.getGroupIndex().getValue() : DaoConstant.DEFAULT_RELATIONSHIP_GROUP_INDEX;
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate().getValue()) : null;
            Boolean isBlocked = request.hasBlocked() ? request.getBlocked().getValue() : null;
            return userRelationshipService.queryRelatedUserIdsWithVersion(
                    clientRequest.getUserId(),
                    groupIndex,
                    isBlocked,
                    lastUpdatedDate)
                    .map(idsWithVersion -> RequestHandlerResultFactory
                            .get(TurmsNotification.Data
                                    .newBuilder()
                                    .setIdsWithVersion(idsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_RELATIONSHIP_GROUPS_REQUEST)
    public ClientRequestHandler handleQueryRelationshipGroupsRequest() {
        return clientRequest -> {
            QueryRelationshipGroupsRequest request = clientRequest.getTurmsRequest()
                    .getQueryRelationshipGroupsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ?
                    new Date(request.getLastUpdatedDate().getValue()) : null;
            return userRelationshipGroupService.queryRelationshipGroupsInfosWithVersion(
                    clientRequest.getUserId(),
                    lastUpdatedDate)
                    .map(groupsWithVersion -> RequestHandlerResultFactory
                            .get(TurmsNotification.Data
                                    .newBuilder()
                                    .setUserRelationshipGroupsWithVersion(groupsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(QUERY_RELATIONSHIPS_REQUEST)
    public ClientRequestHandler handleQueryRelationshipsRequest() {
        return clientRequest -> {
            QueryRelationshipsRequest request = clientRequest.getTurmsRequest()
                    .getQueryRelationshipsRequest();
            Set<Long> ids = request.getUserIdsCount() != 0 ?
                    new HashSet<>(request.getUserIdsList()) : null;
            int groupIndex = request.hasGroupIndex() ? request.getGroupIndex().getValue() : DaoConstant.DEFAULT_RELATIONSHIP_GROUP_INDEX;
            Boolean isBlocked = request.hasBlocked() ? request.getBlocked().getValue() : null;
            Date lastUpdatedDate = request.hasLastUpdatedDate() ?
                    new Date(request.getLastUpdatedDate().getValue()) : null;
            return userRelationshipService.queryRelationshipsWithVersion(
                    clientRequest.getUserId(),
                    ids,
                    groupIndex,
                    isBlocked,
                    lastUpdatedDate)
                    .map(relationshipsWithVersion -> RequestHandlerResultFactory
                            .get(TurmsNotification.Data
                                    .newBuilder()
                                    .setUserRelationshipsWithVersion(relationshipsWithVersion)
                                    .build()));
        };
    }

    @ServiceRequestMapping(UPDATE_FRIEND_REQUEST_REQUEST)
    public ClientRequestHandler handleUpdateFriendRequestRequest() {
        return clientRequest -> {
            UpdateFriendRequestRequest request = clientRequest.getTurmsRequest().getUpdateFriendRequestRequest();
            ResponseAction action = request.getResponseAction();
            String reason = request.hasReason() ? request.getReason().getValue() : null;
            return userFriendRequestService.handleFriendRequest(
                    request.getRequestId(),
                    clientRequest.getUserId(),
                    action,
                    reason)
                    .then(Mono.fromCallable(() -> node.getSharedProperties().getService().getNotification().isNotifyRequesterAfterFriendRequestUpdated()
                            ? RequestHandlerResultFactory.get(request.getRequestId(), clientRequest.getTurmsRequest())
                            : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(UPDATE_RELATIONSHIP_GROUP_REQUEST)
    public ClientRequestHandler handleUpdateRelationshipGroupRequest() {
        return clientRequest -> {
            UpdateRelationshipGroupRequest request = clientRequest.getTurmsRequest().getUpdateRelationshipGroupRequest();
            return userRelationshipGroupService.updateRelationshipGroupName(
                    clientRequest.getUserId(),
                    request.getGroupIndex(),
                    request.getNewName())
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(UPDATE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleUpdateRelationshipRequest() {
        return clientRequest -> {
            UpdateRelationshipRequest request = clientRequest.getTurmsRequest().getUpdateRelationshipRequest();
            Date blockDate = request.hasBlocked() && request.getBlocked().getValue() ? new Date() : null;
            Integer newGroupIndex = request.hasNewGroupIndex() ? request.getNewGroupIndex().getValue() : null;
            Integer deleteGroupIndex = request.hasDeleteGroupIndex() ? request.getDeleteGroupIndex().getValue() : null;
            return userRelationshipService.upsertOneSidedRelationship(
                    clientRequest.getUserId(),
                    request.getUserId(),
                    blockDate,
                    newGroupIndex,
                    deleteGroupIndex,
                    null,
                    true,
                    null)
                    .then(Mono.fromCallable(() -> node.getSharedProperties().getService().getNotification().isNotifyRelatedUserAfterOneSidedRelationshipUpdatedByOthers()
                            ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.getTurmsRequest())
                            : RequestHandlerResultFactory.OK));
        };
    }

}