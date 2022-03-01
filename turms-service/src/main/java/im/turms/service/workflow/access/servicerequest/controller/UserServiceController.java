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

package im.turms.service.workflow.access.servicerequest.controller;

import com.google.common.collect.Sets;
import im.turms.common.constant.DeviceType;
import im.turms.common.constant.ProfileAccessStrategy;
import im.turms.common.constant.UserStatus;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.model.bo.user.NearbyUsers;
import im.turms.common.model.bo.user.UsersInfosWithVersion;
import im.turms.common.model.bo.user.UsersOnlineStatuses;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.user.QueryNearbyUsersRequest;
import im.turms.common.model.dto.request.user.QueryUserOnlineStatusesRequest;
import im.turms.common.model.dto.request.user.QueryUserProfileRequest;
import im.turms.common.model.dto.request.user.UpdateUserLocationRequest;
import im.turms.common.model.dto.request.user.UpdateUserOnlineStatusRequest;
import im.turms.common.model.dto.request.user.UpdateUserRequest;
import im.turms.server.common.bo.location.Coordinates;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.service.session.SessionLocationService;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.server.common.util.CollectionUtil;
import im.turms.service.util.ProtoModelUtil;
import im.turms.service.workflow.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.workflow.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.workflow.service.impl.group.GroupMemberService;
import im.turms.service.workflow.service.impl.user.UserService;
import im.turms.service.workflow.service.impl.user.onlineuser.SessionService;
import im.turms.service.workflow.service.impl.user.onlineuser.UsersNearbyService;
import im.turms.service.workflow.service.impl.user.relationship.UserRelationshipService;
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

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.QUERY_NEARBY_USERS_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.QUERY_USER_ONLINE_STATUSES_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.QUERY_USER_PROFILE_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.UPDATE_USER_LOCATION_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.UPDATE_USER_ONLINE_STATUS_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.UPDATE_USER_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class UserServiceController {

    private final Node node;
    private final UserService userService;
    private final UserRelationshipService userRelationshipService;
    private final UserStatusService userStatusService;
    private final SessionLocationService sessionLocationService;
    private final UsersNearbyService usersNearbyService;
    private final SessionService sessionService;
    private final GroupMemberService groupMemberService;

    public UserServiceController(
            Node node,
            UserService userService,
            SessionLocationService sessionLocationService,
            UsersNearbyService usersNearbyService,
            GroupMemberService groupMemberService,
            UserRelationshipService userRelationshipService,
            UserStatusService userStatusService,
            SessionService sessionService) {
        this.node = node;
        this.userService = userService;
        this.sessionLocationService = sessionLocationService;
        this.usersNearbyService = usersNearbyService;
        this.groupMemberService = groupMemberService;
        this.userRelationshipService = userRelationshipService;
        this.userStatusService = userStatusService;
        this.sessionService = sessionService;
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
                        UsersInfosWithVersion.Builder userBuilder = UsersInfosWithVersion
                                .newBuilder()
                                .addUserInfos(ProtoModelUtil.userProfile2proto(user).build());
                        return RequestHandlerResultFactory.get(TurmsNotification.Data
                                .newBuilder()
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
                            new Coordinates(request.getLongitude(), request.getLatitude()),
                            maxNumber,
                            distance,
                            request.getWithCoordinates(),
                            request.getWithDistance(),
                            request.getWithInfo())
                    .map(nearbyUsers -> {
                        if (nearbyUsers.isEmpty()) {
                            return RequestHandlerResultFactory.NO_CONTENT;
                        }
                        NearbyUsers.Builder builder = NearbyUsers.newBuilder();
                        for (var nearbyUser : nearbyUsers) {
                            builder.addNearbyUsers(ProtoModelUtil.nearbyUser2proto(nearbyUser));
                        }
                        return RequestHandlerResultFactory.get(TurmsNotification.Data
                                .newBuilder()
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
            UsersOnlineStatuses.Builder statusesBuilder = UsersOnlineStatuses.newBuilder();
            List<Mono<Pair<Long, UserSessionsStatus>>> monos = new ArrayList<>(userIds.size());
            for (Long targetUserId : userIds) {
                monos.add(userStatusService.getUserSessionsStatus(targetUserId)
                        .map(sessionsStatus -> Pair.of(targetUserId, sessionsStatus)));
            }
            return Flux.merge(monos)
                    .collectList()
                    .map(userIdAndSessionsStatusList -> {
                        for (Pair<Long, UserSessionsStatus> userIdAndSessionsStatus : userIdAndSessionsStatusList) {
                            statusesBuilder.addUserStatuses(ProtoModelUtil
                                    .userOnlineInfo2userStatus(
                                            userIdAndSessionsStatus.getFirst(),
                                            userIdAndSessionsStatus.getSecond(),
                                            node.getSharedProperties().getService().getUser().isRespondOfflineIfInvisible())
                                    .build());
                        }
                        return RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
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
                    new Coordinates(request.getLongitude(), request.getLatitude()),
                    new Date());
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
                        .get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The user status must not be UNRECOGNIZED"));
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
            boolean notifyMembers =
                    node.getSharedProperties().getService().getNotification().isNotifyMembersAfterOtherMemberOnlineStatusUpdated();
            boolean notifyRelatedUser = node.getSharedProperties().getService().getNotification()
                    .isNotifyRelatedUsersAfterOtherRelatedUserOnlineStatusUpdated();
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
                        if (node.getSharedProperties().getService().getNotification()
                                .isNotifyRelatedUsersAfterOtherRelatedUserInfoUpdated()) {
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