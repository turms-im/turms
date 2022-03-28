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

import im.turms.server.common.access.client.dto.constant.ResponseAction;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
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
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.domain.user.service.UserFriendRequestService;
import im.turms.service.domain.user.service.UserRelationshipGroupService;
import im.turms.service.domain.user.service.UserRelationshipService;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

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
            CreateFriendRequestRequest request = clientRequest.turmsRequest().getCreateFriendRequestRequest();
            return userFriendRequestService.authAndCreateFriendRequest(
                            clientRequest.userId(),
                            request.getRecipientId(),
                            request.getContent(),
                            new Date())
                    .map(friendRequest -> node.getSharedProperties()
                            .getService()
                            .getNotification().isNotifyRecipientWhenReceivingFriendRequest()
                            ? RequestHandlerResultFactory
                            .get(friendRequest.getId(), request.getRecipientId(), clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.get(friendRequest.getId()));
        };
    }

    @ServiceRequestMapping(CREATE_RELATIONSHIP_GROUP_REQUEST)
    public ClientRequestHandler handleCreateRelationshipGroupRequest() {
        return clientRequest -> {
            CreateRelationshipGroupRequest request = clientRequest.turmsRequest().getCreateRelationshipGroupRequest();
            return userRelationshipGroupService.createRelationshipGroup(
                            clientRequest.userId(),
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
            CreateRelationshipRequest request = clientRequest.turmsRequest().getCreateRelationshipRequest();
            // It is unnecessary to check whether requester is in the blocklist of the target user
            // because only a one-sided relationship will be created here
            int groupIndex = request.hasGroupIndex() ?
                    request.getGroupIndex() : DEFAULT_RELATIONSHIP_GROUP_INDEX;
            Date blockDate = request.getBlocked() ? new Date() : null;
            return userRelationshipService.upsertOneSidedRelationship(
                            clientRequest.userId(),
                            request.getUserId(),
                            blockDate,
                            groupIndex,
                            null,
                            new Date(),
                            false,
                            null)
                    .then(Mono.fromCallable(() ->
                            node.getSharedProperties().getService().getNotification()
                                    .isNotifyRelatedUserAfterAddedToOneSidedRelationshipGroupByOthers()
                                    ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.turmsRequest())
                                    : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(DELETE_RELATIONSHIP_GROUP_REQUEST)
    public ClientRequestHandler handleDeleteRelationshipGroupRequest() {
        return clientRequest -> {
            DeleteRelationshipGroupRequest request = clientRequest.turmsRequest().getDeleteRelationshipGroupRequest();
            Integer groupIndex = request.getGroupIndex();
            int targetGroupIndex = request.hasTargetGroupIndex() ?
                    request.getTargetGroupIndex() : DEFAULT_RELATIONSHIP_GROUP_INDEX;
            if (node.getSharedProperties().getService().getNotification()
                    .isNotifyMembersAfterOneSidedRelationshipGroupUpdatedByOthers()) {
                return userRelationshipGroupService.queryRelationshipGroupMemberIds(
                                clientRequest.userId(),
                                groupIndex)
                        .collect(Collectors.toSet())
                        .flatMap(ids -> userRelationshipGroupService.deleteRelationshipGroupAndMoveMembers(
                                        clientRequest.userId(),
                                        groupIndex,
                                        targetGroupIndex)
                                .then(Mono.fromCallable(() -> ids.isEmpty()
                                        ? RequestHandlerResultFactory.OK
                                        : RequestHandlerResultFactory.get(ids, clientRequest.turmsRequest()))));
            }
            return userRelationshipGroupService.deleteRelationshipGroupAndMoveMembers(
                            clientRequest.userId(),
                            groupIndex,
                            targetGroupIndex)
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(DELETE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleDeleteRelationshipRequest() {
        return clientRequest -> {
            DeleteRelationshipRequest request = clientRequest.turmsRequest().getDeleteRelationshipRequest();
            boolean deleteTwoSidedRelationships = node.getSharedProperties().getService().getUser().isDeleteTwoSidedRelationships();
            Mono<Void> deleteMono;
            if (deleteTwoSidedRelationships) {
                deleteMono = userRelationshipService.deleteTwoSidedRelationships(
                        clientRequest.userId(),
                        request.getUserId());
            } else {
                deleteMono = userRelationshipService.deleteOneSidedRelationship(
                        clientRequest.userId(),
                        request.getUserId(),
                        null);
            }
            return deleteMono.then(Mono.fromCallable(() -> node.getSharedProperties().getService().getNotification()
                    .isNotifyMemberAfterRemovedFromRelationshipGroupByOthers()
                    ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.turmsRequest())
                    : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(QUERY_FRIEND_REQUESTS_REQUEST)
    public ClientRequestHandler handleQueryFriendRequestsRequest() {
        return clientRequest -> {
            QueryFriendRequestsRequest request = clientRequest.turmsRequest().getQueryFriendRequestsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate()) : null;
            return userFriendRequestService.queryFriendRequestsWithVersion(
                            clientRequest.userId(),
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
            QueryRelatedUserIdsRequest request = clientRequest.turmsRequest().getQueryRelatedUserIdsRequest();
            int groupIndex = request.hasGroupIndex() ? request.getGroupIndex() : DEFAULT_RELATIONSHIP_GROUP_INDEX;
            Date lastUpdatedDate = request.hasLastUpdatedDate() ? new Date(request.getLastUpdatedDate()) : null;
            Boolean isBlocked = request.hasBlocked() ? request.getBlocked() : null;
            return userRelationshipService.queryRelatedUserIdsWithVersion(
                            clientRequest.userId(),
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
            QueryRelationshipGroupsRequest request = clientRequest.turmsRequest()
                    .getQueryRelationshipGroupsRequest();
            Date lastUpdatedDate = request.hasLastUpdatedDate() ?
                    new Date(request.getLastUpdatedDate()) : null;
            return userRelationshipGroupService.queryRelationshipGroupsInfosWithVersion(
                            clientRequest.userId(),
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
            QueryRelationshipsRequest request = clientRequest.turmsRequest()
                    .getQueryRelationshipsRequest();
            Set<Long> ids = request.getUserIdsCount() == 0 ? null : CollectionUtil.newSet(request.getUserIdsList());
            int groupIndex = request.hasGroupIndex() ? request.getGroupIndex() : DEFAULT_RELATIONSHIP_GROUP_INDEX;
            Boolean isBlocked = request.hasBlocked() ? request.getBlocked() : null;
            Date lastUpdatedDate = request.hasLastUpdatedDate() ?
                    new Date(request.getLastUpdatedDate()) : null;
            return userRelationshipService.queryRelationshipsWithVersion(
                            clientRequest.userId(),
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
            UpdateFriendRequestRequest request = clientRequest.turmsRequest().getUpdateFriendRequestRequest();
            ResponseAction action = request.getResponseAction();
            String reason = request.hasReason() ? request.getReason() : null;
            return userFriendRequestService.authAndHandleFriendRequest(
                            request.getRequestId(),
                            clientRequest.userId(),
                            action,
                            reason)
                    .then(Mono.fromCallable(
                            () -> node.getSharedProperties().getService().getNotification().isNotifyRequesterAfterFriendRequestUpdated()
                                    ? RequestHandlerResultFactory.get(request.getRequestId(), clientRequest.turmsRequest())
                                    : RequestHandlerResultFactory.OK));
        };
    }

    @ServiceRequestMapping(UPDATE_RELATIONSHIP_GROUP_REQUEST)
    public ClientRequestHandler handleUpdateRelationshipGroupRequest() {
        return clientRequest -> {
            UpdateRelationshipGroupRequest request = clientRequest.turmsRequest().getUpdateRelationshipGroupRequest();
            return userRelationshipGroupService.updateRelationshipGroupName(
                            clientRequest.userId(),
                            request.getGroupIndex(),
                            request.getNewName())
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(UPDATE_RELATIONSHIP_REQUEST)
    public ClientRequestHandler handleUpdateRelationshipRequest() {
        return clientRequest -> {
            UpdateRelationshipRequest request = clientRequest.turmsRequest().getUpdateRelationshipRequest();
            Date blockDate = request.hasBlocked() && request.getBlocked() ? new Date() : null;
            Integer newGroupIndex = request.hasNewGroupIndex() ? request.getNewGroupIndex() : null;
            Integer deleteGroupIndex = request.hasDeleteGroupIndex() ? request.getDeleteGroupIndex() : null;
            return userRelationshipService.upsertOneSidedRelationship(
                            clientRequest.userId(),
                            request.getUserId(),
                            blockDate,
                            newGroupIndex,
                            deleteGroupIndex,
                            null,
                            true,
                            null)
                    .then(Mono.fromCallable(() -> node.getSharedProperties().getService().getNotification()
                            .isNotifyRelatedUserAfterOneSidedRelationshipUpdatedByOthers()
                            ? RequestHandlerResultFactory.get(request.getUserId(), clientRequest.turmsRequest())
                            : RequestHandlerResultFactory.OK));
        };
    }

}