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

import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.model.user.NearbyUsers;
import im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion;
import im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses;
import im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest;
import im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest;
import im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.location.bo.NearbyUser;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.service.SessionLocationService;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.business.notification.NotificationProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupMemberOnlineStatusUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationUserInfoUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationUserOnlineStatusUpdatedProperties;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.user.service.UserRelationshipService;
import im.turms.service.domain.user.service.UserService;
import im.turms.service.domain.user.service.onlineuser.NearbyUserService;
import im.turms.service.domain.user.service.onlineuser.SessionService;
import im.turms.service.infra.proto.ProtoModelConvertor;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_NEARBY_USERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_USER_ONLINE_STATUSES_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_USER_PROFILES_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_USER_LOCATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_USER_ONLINE_STATUS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_USER_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class UserServiceController extends BaseServiceController {

    private final UserService userService;
    private final UserRelationshipService userRelationshipService;
    private final UserStatusService userStatusService;
    private final SessionLocationService sessionLocationService;
    private final NearbyUserService nearbyUserService;
    private final SessionService sessionService;
    private final GroupMemberService groupMemberService;

    private boolean notifyRequesterOtherOnlineSessionsOfUserInfoUpdated;
    private boolean notifyNonBlockedRelatedUsersOfUserInfoUpdated;

    private boolean notifyRequesterOtherOnlineSessionsOfUserOnlineStatusUpdated;
    private boolean notifyNonBlockedRelatedUsersOfUserOnlineStatusUpdated;
    private boolean notifyGroupMembersOfMemberOnlineStatusUpdated;

    private boolean respondOfflineIfInvisible;

    public UserServiceController(
            TurmsPropertiesManager propertiesManager,
            UserService userService,
            SessionLocationService sessionLocationService,
            NearbyUserService nearbyUserService,
            GroupMemberService groupMemberService,
            UserRelationshipService userRelationshipService,
            UserStatusService userStatusService,
            SessionService sessionService) {
        this.userService = userService;
        this.sessionLocationService = sessionLocationService;
        this.nearbyUserService = nearbyUserService;
        this.groupMemberService = groupMemberService;
        this.userRelationshipService = userRelationshipService;
        this.userStatusService = userStatusService;
        this.sessionService = sessionService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        NotificationProperties notificationProperties = properties.getService()
                .getNotification();

        NotificationUserInfoUpdatedProperties notificationUserInfoUpdatedProperties =
                notificationProperties.getUserInfoUpdated();
        notifyRequesterOtherOnlineSessionsOfUserInfoUpdated =
                notificationUserInfoUpdatedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyNonBlockedRelatedUsersOfUserInfoUpdated =
                notificationUserInfoUpdatedProperties.isNotifyNonBlockedRelatedUsers();

        NotificationUserOnlineStatusUpdatedProperties notificationUserOnlineStatusUpdatedProperties =
                notificationProperties.getUserOnlineStatusUpdated();
        notifyRequesterOtherOnlineSessionsOfUserOnlineStatusUpdated =
                notificationUserOnlineStatusUpdatedProperties
                        .isNotifyRequesterOtherOnlineSessions();
        notifyNonBlockedRelatedUsersOfUserOnlineStatusUpdated =
                notificationUserOnlineStatusUpdatedProperties.isNotifyNonBlockedRelatedUsers();

        NotificationGroupMemberOnlineStatusUpdatedProperties notificationGroupMemberOnlineStatusUpdatedProperties =
                notificationProperties.getGroupMemberOnlineStatusUpdated();
        notifyGroupMembersOfMemberOnlineStatusUpdated =
                notificationGroupMemberOnlineStatusUpdatedProperties.isNotifyGroupMembers();

        respondOfflineIfInvisible = properties.getService()
                .getUser()
                .isRespondOfflineIfInvisible();
    }

    @ServiceRequestMapping(QUERY_USER_PROFILES_REQUEST)
    public ClientRequestHandler handleQueryUserProfilesRequest() {
        return clientRequest -> {
            QueryUserProfilesRequest request = clientRequest.turmsRequest()
                    .getQueryUserProfilesRequest();
            return userService
                    .authAndQueryUsersProfile(clientRequest.userId(),
                            CollectionUtil.toSet(request.getUserIdsList()),
                            request.hasName()
                                    ? request.getName()
                                    : null,
                            request.hasLastUpdatedDate()
                                    ? new Date(request.getLastUpdatedDate())
                                    : null,
                            request.hasSkip()
                                    ? request.getSkip()
                                    : null,
                            request.hasLimit()
                                    ? request.getLimit()
                                    : null,
                            request.getFieldsToHighlightCount() > 0
                                    ? request.getFieldsToHighlightList()
                                    : null)
                    .map(users -> {
                        UserInfosWithVersion.Builder userInfosWithVersionBuilder =
                                ClientMessagePool.getUserInfosWithVersionBuilder();
                        for (User user : users) {
                            userInfosWithVersionBuilder
                                    .addUserInfos(ProtoModelConvertor.userProfile2proto(user)
                                            .build());
                        }
                        return RequestHandlerResult
                                .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setUserInfosWithVersion(
                                                userInfosWithVersionBuilder.build())
                                        .build());
                    });
        };
    }

    @ServiceRequestMapping(QUERY_NEARBY_USERS_REQUEST)
    public ClientRequestHandler handleQueryNearbyUsersRequest() {
        return clientRequest -> {
            QueryNearbyUsersRequest request = clientRequest.turmsRequest()
                    .getQueryNearbyUsersRequest();
            Short maxCount = request.hasMaxCount()
                    ? (short) request.getMaxCount()
                    : null;
            Integer maxDistance = request.hasMaxDistance()
                    ? request.getMaxDistance()
                    : null;
            return nearbyUserService
                    .queryNearbyUsers(clientRequest.userId(),
                            clientRequest.deviceType(),
                            request.getLongitude(),
                            request.getLatitude(),
                            maxCount,
                            maxDistance,
                            request.getWithCoordinates(),
                            request.getWithDistance(),
                            request.getWithUserInfo())
                    .map(nearbyUsers -> {
                        if (nearbyUsers.isEmpty()) {
                            return RequestHandlerResult.NO_CONTENT;
                        }
                        NearbyUsers.Builder builder = ClientMessagePool.getNearbyUsersBuilder();
                        for (NearbyUser nearbyUser : nearbyUsers) {
                            builder.addNearbyUsers(
                                    ProtoModelConvertor.nearbyUser2proto(nearbyUser));
                        }
                        return RequestHandlerResult
                                .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setNearbyUsers(builder.build())
                                        .build());
                    });
        };
    }

    @ServiceRequestMapping(QUERY_USER_ONLINE_STATUSES_REQUEST)
    public ClientRequestHandler handleQueryUserOnlineStatusesRequest() {
        return clientRequest -> {
            QueryUserOnlineStatusesRequest request = clientRequest.turmsRequest()
                    .getQueryUserOnlineStatusesRequest();
            if (request.getUserIdsCount() == 0) {
                return Mono.empty();
            }
            // TODO : Access Control
            List<Long> userIds = request.getUserIdsList();
            int size = userIds.size();
            List<Mono<Pair<Long, UserSessionsStatus>>> monos = new ArrayList<>(size);
            for (Long targetUserId : userIds) {
                monos.add(userStatusService.getUserSessionsStatus(targetUserId)
                        .map(sessionsStatus -> Pair.of(targetUserId, sessionsStatus)));
            }
            return Flux.merge(monos)
                    .collect(CollectorUtil.toList(size))
                    .map(userIdAndSessionsStatusList -> {
                        UserOnlineStatuses.Builder statusesBuilder =
                                ClientMessagePool.getUsersOnlineStatusesBuilder();
                        for (Pair<Long, UserSessionsStatus> userIdAndSessionsStatus : userIdAndSessionsStatusList) {
                            statusesBuilder.addStatuses(ProtoModelConvertor
                                    .userSessionsStatus2proto(userIdAndSessionsStatus.first(),
                                            userIdAndSessionsStatus.second(),
                                            respondOfflineIfInvisible));
                        }
                        return RequestHandlerResult
                                .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setUserOnlineStatuses(statusesBuilder)
                                        .build());
                    });
        };
    }

    @ServiceRequestMapping(UPDATE_USER_LOCATION_REQUEST)
    public ClientRequestHandler handleUpdateUserLocationRequest() {
        return clientRequest -> {
            UpdateUserLocationRequest request = clientRequest.turmsRequest()
                    .getUpdateUserLocationRequest();
            Mono<Void> updateMono =
                    sessionLocationService.upsertUserLocation(clientRequest.userId(),
                            clientRequest.deviceType(),
                            new Date(),
                            request.getLongitude(),
                            request.getLatitude());
            return updateMono.thenReturn(RequestHandlerResult.OK);
        };
    }

    /**
     * Do not notify the user status change to the related users and members of joined groups
     * because the client itself should query whether there are any user status changes, according
     * to their own business scenarios.
     */
    @ServiceRequestMapping(UPDATE_USER_ONLINE_STATUS_REQUEST)
    public ClientRequestHandler handleUpdateUserOnlineStatusRequest() {
        return clientRequest -> {
            UpdateUserOnlineStatusRequest request = clientRequest.turmsRequest()
                    .getUpdateUserOnlineStatusRequest();
            UserStatus userStatus = request.getUserStatus();
            if (userStatus == UserStatus.UNRECOGNIZED) {
                return Mono.just(RequestHandlerResult.of(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The user status must not be UNRECOGNIZED"));
            }
            Mono<Boolean> updateMono;
            if (userStatus == UserStatus.OFFLINE) {
                updateMono = request.getDeviceTypesCount() > 0
                        ? sessionService.disconnect(clientRequest.userId(),
                                CollectionUtil.newSet(request.getDeviceTypesList()),
                                SessionCloseStatus.DISCONNECTED_BY_OTHER_DEVICE)
                        : sessionService.disconnect(clientRequest.userId(),
                                SessionCloseStatus.DISCONNECTED_BY_OTHER_DEVICE);
            } else {
                updateMono = userStatusService
                        .updateOnlineUserStatusIfPresent(clientRequest.userId(), userStatus);
            }
            boolean notifyMembers = notifyGroupMembersOfMemberOnlineStatusUpdated;
            boolean notifyNonBlockedRelatedUser =
                    notifyNonBlockedRelatedUsersOfUserOnlineStatusUpdated;
            if (!notifyMembers && !notifyNonBlockedRelatedUser) {
                return updateMono.thenReturn(RequestHandlerResult.of(
                        notifyRequesterOtherOnlineSessionsOfUserOnlineStatusUpdated,
                        clientRequest.turmsRequest()));
            }
            Mono<Set<Long>> queryMemberIds = notifyMembers
                    ? groupMemberService
                            .queryMemberIdsInUsersJoinedGroups(Set.of(clientRequest.userId()), true)
                    : PublisherPool.emptySet();
            Mono<List<Long>> queryRelatedUserIds;

            Recyclable<List<Long>> recyclableList;
            if (notifyNonBlockedRelatedUser) {
                recyclableList = ListRecycler.obtain();
                queryRelatedUserIds = userRelationshipService
                        .queryRelatedUserIds(Set.of(clientRequest.userId()), false)
                        .collect(Collectors.toCollection(recyclableList::getValue));
            } else {
                recyclableList = null;
                queryRelatedUserIds = PublisherPool.emptyList();
            }
            return queryMemberIds.zipWith(queryRelatedUserIds)
                    .map(results -> {
                        Set<Long> recipients = CollectionUtil.add(results.getT1(), results.getT2());
                        return RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfUserOnlineStatusUpdated,
                                recipients,
                                clientRequest.turmsRequest());
                    })
                    .doFinally(signalType -> {
                        if (recyclableList != null) {
                            recyclableList.recycle();
                        }
                    });
        };
    }

    @ServiceRequestMapping(UPDATE_USER_REQUEST)
    public ClientRequestHandler handleUpdateUserRequest() {
        return clientRequest -> {
            UpdateUserRequest request = clientRequest.turmsRequest()
                    .getUpdateUserRequest();
            String password = request.hasPassword()
                    ? request.getPassword()
                    : null;
            String name = request.hasName()
                    ? request.getName()
                    : null;
            String intro = request.hasIntro()
                    ? request.getIntro()
                    : null;
            String profilePicture = request.hasProfilePicture()
                    ? request.getProfilePicture()
                    : null;
            ProfileAccessStrategy profileAccessStrategy = request.getProfileAccessStrategy();
            return userService
                    .updateUser(clientRequest.userId(),
                            password,
                            name,
                            intro,
                            profilePicture,
                            profileAccessStrategy,
                            null,
                            null,
                            null)
                    .flatMap(changed -> {
                        if (!changed) {
                            return Mono.just(RequestHandlerResult.OK);
                        }
                        if (!notifyNonBlockedRelatedUsersOfUserInfoUpdated
                                // TODO: make configurable.
                                || (name == null && intro == null && profilePicture == null)) {
                            return Mono.just(RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfUserInfoUpdated,
                                    clientRequest.turmsRequest()));
                        }
                        Recyclable<List<Long>> recyclableList = ListRecycler.obtain();
                        return userRelationshipService
                                .queryRelatedUserIds(Set.of(clientRequest.userId()), false)
                                .collect(Collectors.toCollection(recyclableList::getValue))
                                .map(relatedUserIds -> {
                                    if (relatedUserIds.isEmpty()) {
                                        return RequestHandlerResult.of(
                                                notifyRequesterOtherOnlineSessionsOfUserInfoUpdated,
                                                clientRequest.turmsRequest());
                                    }
                                    if (password == null) {
                                        return RequestHandlerResult.of(
                                                notifyRequesterOtherOnlineSessionsOfUserInfoUpdated,
                                                CollectionUtil.newSet(relatedUserIds),
                                                clientRequest.turmsRequest());
                                    }
                                    RequestHandlerResult.Notification notificationForRelatedUsers =
                                            RequestHandlerResult.Notification.of(false,
                                                    CollectionUtil.newSet(relatedUserIds),
                                                    ClientMessagePool.getTurmsRequestBuilder()
                                                            .setUpdateUserRequest(ClientMessagePool
                                                                    .getUpdateUserRequestBuilder()
                                                                    .mergeFrom(request)
                                                                    .clearPassword())
                                                            .build());
                                    if (notifyRequesterOtherOnlineSessionsOfUserInfoUpdated) {
                                        return RequestHandlerResult.of(List.of(
                                                RequestHandlerResult.Notification.of(true,
                                                        clientRequest.turmsRequest()),
                                                notificationForRelatedUsers));
                                    }
                                    return RequestHandlerResult.of(notificationForRelatedUsers);
                                })
                                .doFinally(signalType -> recyclableList.recycle());
                    });
        };
    }

}