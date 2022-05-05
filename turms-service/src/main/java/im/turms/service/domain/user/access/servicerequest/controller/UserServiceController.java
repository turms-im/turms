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

import com.google.common.collect.Sets;
import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.model.user.NearbyUsers;
import im.turms.server.common.access.client.dto.model.user.UsersInfosWithVersion;
import im.turms.server.common.access.client.dto.model.user.UsersOnlineStatuses;
import im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest;
import im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest;
import im.turms.server.common.access.client.dto.request.user.QueryUserProfileRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.service.SessionLocationService;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.user.service.UserRelationshipService;
import im.turms.service.domain.user.service.UserService;
import im.turms.service.domain.user.service.onlineuser.SessionService;
import im.turms.service.domain.user.service.onlineuser.UsersNearbyService;
import im.turms.service.infra.proto.ProtoModelConvertor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_NEARBY_USERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_USER_ONLINE_STATUSES_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_USER_PROFILE_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_USER_LOCATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_USER_ONLINE_STATUS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_USER_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class UserServiceController {

    private final UserService userService;
    private final UserRelationshipService userRelationshipService;
    private final UserStatusService userStatusService;
    private final SessionLocationService sessionLocationService;
    private final UsersNearbyService usersNearbyService;
    private final SessionService sessionService;
    private final GroupMemberService groupMemberService;

    private boolean notifyMembersAfterOtherMemberOnlineStatusUpdated;
    private boolean notifyRelatedUsersAfterOtherRelatedUserOnlineStatusUpdated;
    private boolean notifyRelatedUsersAfterOtherRelatedUserInfoUpdated;
    private boolean respondOfflineIfInvisible;

    public UserServiceController(
            TurmsPropertiesManager propertiesManager,
            UserService userService,
            SessionLocationService sessionLocationService,
            UsersNearbyService usersNearbyService,
            GroupMemberService groupMemberService,
            UserRelationshipService userRelationshipService,
            UserStatusService userStatusService,
            SessionService sessionService) {
        this.userService = userService;
        this.sessionLocationService = sessionLocationService;
        this.usersNearbyService = usersNearbyService;
        this.groupMemberService = groupMemberService;
        this.userRelationshipService = userRelationshipService;
        this.userStatusService = userStatusService;
        this.sessionService = sessionService;

        propertiesManager.triggerAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        notifyMembersAfterOtherMemberOnlineStatusUpdated = properties.getService().getNotification()
                .isNotifyMembersAfterOtherMemberOnlineStatusUpdated();
        notifyRelatedUsersAfterOtherRelatedUserOnlineStatusUpdated = properties.getService().getNotification()
                .isNotifyRelatedUsersAfterOtherRelatedUserOnlineStatusUpdated();
        notifyRelatedUsersAfterOtherRelatedUserInfoUpdated = properties.getService().getNotification()
                .isNotifyRelatedUsersAfterOtherRelatedUserInfoUpdated();
        respondOfflineIfInvisible = properties.getService().getUser().isRespondOfflineIfInvisible();
    }

    @ServiceRequestMapping(QUERY_USER_PROFILE_REQUEST)
    public ClientRequestHandler handleQueryUserProfileRequest() {
        return clientRequest -> {
            QueryUserProfileRequest request = clientRequest.turmsRequest().getQueryUserProfileRequest();
            return userService.authAndQueryUserProfile(
                            clientRequest.userId(),
                            request.getUserId(),
                            false)
                    .map(user -> {
                        UsersInfosWithVersion.Builder userBuilder = ClientMessagePool
                                .getUsersInfosWithVersionBuilder()
                                .addUserInfos(ProtoModelConvertor.userProfile2proto(user).build());
                        return RequestHandlerResultFactory.get(ClientMessagePool
                                .getTurmsNotificationDataBuilder()
                                .setUsersInfosWithVersion(userBuilder)
                                .build());
                    });
        };
    }

    @ServiceRequestMapping(QUERY_NEARBY_USERS_REQUEST)
    public ClientRequestHandler handleQueryNearbyUsersRequest() {
        return clientRequest -> {
            QueryNearbyUsersRequest request = clientRequest.turmsRequest().getQueryNearbyUsersRequest();
            Integer distance = request.hasDistance() ? (int) request.getDistance() : null;
            Short maxNumber = request.hasMaxNumber() ? (short) request.getMaxNumber() : null;
            return usersNearbyService.queryNearbyUsers(
                            clientRequest.userId(),
                            clientRequest.deviceType(),
                            request.getLongitude(),
                            request.getLatitude(),
                            maxNumber,
                            distance,
                            request.getWithCoordinates(),
                            request.getWithDistance(),
                            request.getWithInfo())
                    .map(nearbyUsers -> {
                        if (nearbyUsers.isEmpty()) {
                            return RequestHandlerResultFactory.NO_CONTENT;
                        }
                        NearbyUsers.Builder builder = ClientMessagePool.getNearbyUsersBuilder();
                        for (var nearbyUser : nearbyUsers) {
                            builder.addNearbyUsers(ProtoModelConvertor.nearbyUser2proto(nearbyUser));
                        }
                        return RequestHandlerResultFactory.get(ClientMessagePool
                                .getTurmsNotificationDataBuilder()
                                .setNearbyUsers(builder.build())
                                .build());
                    });
        };
    }

    @ServiceRequestMapping(QUERY_USER_ONLINE_STATUSES_REQUEST)
    public ClientRequestHandler handleQueryUsersOnlineStatusRequest() {
        return clientRequest -> {
            QueryUserOnlineStatusesRequest request = clientRequest.turmsRequest().getQueryUserOnlineStatusesRequest();
            if (request.getUserIdsCount() == 0) {
                return Mono.empty();
            }
            //TODO : Access Control
            List<Long> userIds = request.getUserIdsList();
            List<Mono<Pair<Long, UserSessionsStatus>>> monos = new ArrayList<>(userIds.size());
            for (Long targetUserId : userIds) {
                monos.add(userStatusService.getUserSessionsStatus(targetUserId)
                        .map(sessionsStatus -> Pair.of(targetUserId, sessionsStatus)));
            }
            return Flux.merge(monos)
                    .collectList()
                    .map(userIdAndSessionsStatusList -> {
                        UsersOnlineStatuses.Builder statusesBuilder = ClientMessagePool.getUsersOnlineStatusesBuilder();
                        for (Pair<Long, UserSessionsStatus> userIdAndSessionsStatus : userIdAndSessionsStatusList) {
                            statusesBuilder.addUserStatuses(ProtoModelConvertor
                                    .userOnlineInfo2userStatus(
                                            userIdAndSessionsStatus.getFirst(),
                                            userIdAndSessionsStatus.getSecond(),
                                            respondOfflineIfInvisible));
                        }
                        return RequestHandlerResultFactory.get(ClientMessagePool
                                .getTurmsNotificationDataBuilder()
                                .setUsersOnlineStatuses(statusesBuilder)
                                .build());
                    });
        };
    }

    @ServiceRequestMapping(UPDATE_USER_LOCATION_REQUEST)
    public ClientRequestHandler handleUpdateUserLocationRequest() {
        return clientRequest -> {
            UpdateUserLocationRequest request = clientRequest.turmsRequest().getUpdateUserLocationRequest();
            Mono<Void> updateMono = sessionLocationService.upsertUserLocation(
                    clientRequest.userId(),
                    clientRequest.deviceType(),
                    new Date(),
                    request.getLongitude(),
                    request.getLatitude());
            return updateMono.thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    /**
     * Do not notify the user status change to somebodies like her/his related users.
     * The client itself should query whether there is any user status changes according to your own
     * business scenarios.
     */
    @ServiceRequestMapping(UPDATE_USER_ONLINE_STATUS_REQUEST)
    public ClientRequestHandler handleUpdateUserOnlineStatusRequest() {
        return clientRequest -> {
            UpdateUserOnlineStatusRequest request = clientRequest.turmsRequest().getUpdateUserOnlineStatusRequest();
            UserStatus userStatus = request.getUserStatus();
            if (userStatus == UserStatus.UNRECOGNIZED) {
                return Mono.just(RequestHandlerResultFactory
                        .get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The user status must not be UNRECOGNIZED"));
            }
            Set<DeviceType> deviceTypes = request.getDeviceTypesCount() > 0 ? Sets.newHashSet(request.getDeviceTypesList()) : null;
            Mono<Boolean> updateMono;
            if (userStatus == UserStatus.OFFLINE) {
                updateMono = deviceTypes == null
                        ? sessionService.disconnect(clientRequest.userId(), SessionCloseStatus.DISCONNECTED_BY_OTHER_DEVICE)
                        : sessionService.disconnect(clientRequest.userId(), deviceTypes, SessionCloseStatus.DISCONNECTED_BY_OTHER_DEVICE);
            } else {
                updateMono = userStatusService.updateOnlineUserStatusIfPresent(clientRequest.userId(), userStatus);
            }
            boolean notifyMembers = notifyMembersAfterOtherMemberOnlineStatusUpdated;
            boolean notifyRelatedUser = notifyRelatedUsersAfterOtherRelatedUserOnlineStatusUpdated;
            if (!notifyMembers && !notifyRelatedUser) {
                return updateMono.thenReturn(RequestHandlerResultFactory.OK);
            }
            Mono<Set<Long>> queryMemberIds = notifyMembers
                    ? groupMemberService.queryMemberIdsInUsersJoinedGroups(Set.of(clientRequest.userId()))
                    : Mono.just(Collections.emptySet());
            Mono<Set<Long>> queryRelatedUserIds = notifyRelatedUser
                    ? userRelationshipService.queryRelatedUserIds(Set.of(clientRequest.userId()), false)
                    .collect(Collectors.toSet())
                    : Mono.just(Collections.emptySet());
            return queryMemberIds.zipWith(queryRelatedUserIds)
                    .map(results -> {
                        Set<Long> recipients = CollectionUtil.add(results.getT1(), results.getT2());
                        return recipients.isEmpty()
                                ? RequestHandlerResultFactory.OK
                                : RequestHandlerResultFactory.get(recipients, clientRequest.turmsRequest());
                    });
        };
    }

    @ServiceRequestMapping(UPDATE_USER_REQUEST)
    public ClientRequestHandler handleUpdateUserRequest() {
        return clientRequest -> {
            UpdateUserRequest request = clientRequest.turmsRequest().getUpdateUserRequest();
            String password = request.hasPassword() ? request.getPassword() : null;
            String name = request.hasName() ? request.getName() : null;
            String intro = request.hasIntro() ? request.getIntro() : null;
            ProfileAccessStrategy profileAccessStrategy = request.getProfileAccessStrategy();
            return userService.updateUser(
                            clientRequest.userId(),
                            password,
                            name,
                            intro,
                            profileAccessStrategy,
                            null,
                            null,
                            null)
                    .then(Mono.defer(() -> {
                        if (notifyRelatedUsersAfterOtherRelatedUserInfoUpdated) {
                            return userRelationshipService.queryRelatedUserIds(Set.of(clientRequest.userId()), false)
                                    .collect(Collectors.toSet())
                                    .map(relatedUserIds -> relatedUserIds.isEmpty()
                                            ? RequestHandlerResultFactory.OK
                                            : RequestHandlerResultFactory.get(relatedUserIds, clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResultFactory.OK);
                    }));
        };
    }

}