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

import com.google.common.collect.Sets;
import im.turms.common.constant.DeviceType;
import im.turms.common.constant.ProfileAccessStrategy;
import im.turms.common.constant.UserStatus;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.model.bo.common.Int64Values;
import im.turms.common.model.bo.user.UserSessionId;
import im.turms.common.model.bo.user.UserSessionIds;
import im.turms.common.model.bo.user.UsersInfosWithVersion;
import im.turms.common.model.bo.user.UsersOnlineStatuses;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.user.QueryUserIdsNearbyRequest;
import im.turms.common.model.dto.request.user.QueryUserInfosNearbyRequest;
import im.turms.common.model.dto.request.user.QueryUserOnlineStatusesRequest;
import im.turms.common.model.dto.request.user.QueryUserProfileRequest;
import im.turms.common.model.dto.request.user.UpdateUserLocationRequest;
import im.turms.common.model.dto.request.user.UpdateUserOnlineStatusRequest;
import im.turms.common.model.dto.request.user.UpdateUserRequest;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.service.session.SessionLocationService;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.turms.workflow.service.impl.group.GroupMemberService;
import im.turms.turms.workflow.service.impl.user.UserService;
import im.turms.turms.workflow.service.impl.user.onlineuser.SessionService;
import im.turms.turms.workflow.service.impl.user.onlineuser.UsersNearbyService;
import im.turms.turms.workflow.service.impl.user.relationship.UserRelationshipService;
import org.springframework.data.geo.Point;
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

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.QUERY_USER_IDS_NEARBY_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.QUERY_USER_INFOS_NEARBY_REQUEST;
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
            QueryUserProfileRequest request = clientRequest.getTurmsRequest().getQueryUserProfileRequest();
            return userService.authAndQueryUserProfile(
                    clientRequest.getUserId(),
                    request.getUserId(),
                    false)
                    .map(user -> {
                        UsersInfosWithVersion.Builder userBuilder = UsersInfosWithVersion
                                .newBuilder()
                                .addUserInfos(ProtoUtil.userProfile2proto(user).build());
                        return RequestHandlerResultFactory.get(TurmsNotification.Data
                                .newBuilder()
                                .setUsersInfosWithVersion(userBuilder)
                                .build());
                    });
        };
    }

    @ServiceRequestMapping(QUERY_USER_IDS_NEARBY_REQUEST)
    public ClientRequestHandler handleQueryUserIdsNearbyRequest() {
        return clientRequest -> {
            QueryUserIdsNearbyRequest request = clientRequest.getTurmsRequest().getQueryUserIdsNearbyRequest();
            Double distance = request.hasDistance() ? (double) request.getDistance().getValue() : null;
            Short maxNumber = request.hasMaxNumber() ? (short) request.getMaxNumber().getValue() : null;
            Mono<Void> upsertMono = sessionLocationService.upsertUserLocation(
                    clientRequest.getUserId(),
                    clientRequest.getDeviceType(),
                    new Point(request.getLongitude(), request.getLatitude()),
                    new Date());
            if (sessionLocationService.isTreatUserIdAndDeviceTypeAsUniqueUser()) {
                return upsertMono.then(sessionLocationService.queryNearestUserSessionIds(
                        clientRequest.getUserId(),
                        clientRequest.getDeviceType(),
                        maxNumber,
                        distance)
                        .collectList()
                        .map(userSessionIds -> {
                            if (userSessionIds.isEmpty()) {
                                return RequestHandlerResultFactory.NO_CONTENT;
                            }
                            UserSessionIds.Builder builder = UserSessionIds.newBuilder();
                            for (im.turms.server.common.bo.session.UserSessionId userSessionId : userSessionIds) {
                                UserSessionId sessionId = UserSessionId.newBuilder()
                                        .setUserId(userSessionId.getUserId())
                                        .setDeviceType(userSessionId.getDeviceType())
                                        .build();
                                builder.addUserSessionIds(sessionId);
                            }
                            return RequestHandlerResultFactory.get(TurmsNotification.Data
                                    .newBuilder()
                                    .setUserSessionIds(builder.build())
                                    .build());
                        }));
            } else {
                return upsertMono.then(sessionLocationService.queryNearestUserIds(
                        clientRequest.getUserId(),
                        clientRequest.getDeviceType(),
                        maxNumber,
                        distance)
                        .collectList()
                        .map(userIds -> userIds.isEmpty()
                                ? RequestHandlerResultFactory.NO_CONTENT
                                : RequestHandlerResultFactory.get(TurmsNotification.Data
                                .newBuilder()
                                .setIds(Int64Values.newBuilder().addAllValues(userIds))
                                .build())));
            }
        };
    }

    @ServiceRequestMapping(QUERY_USER_INFOS_NEARBY_REQUEST)
    public ClientRequestHandler handleQueryUsersInfosNearbyRequest() {
        return clientRequest -> {
            QueryUserInfosNearbyRequest request = clientRequest.getTurmsRequest().getQueryUserInfosNearbyRequest();
            Double distance = request.hasDistance() ? (double) request.getDistance().getValue() : null;
            Short maxNumber = request.hasMaxNumber() ? (short) request.getMaxNumber().getValue() : null;
            Mono<Void> upsertMono = sessionLocationService.upsertUserLocation(
                    clientRequest.getUserId(),
                    clientRequest.getDeviceType(),
                    new Point(request.getLongitude(), request.getLatitude()),
                    new Date());
            return upsertMono.then(usersNearbyService.queryUsersProfilesNearby(
                    clientRequest.getUserId(),
                    clientRequest.getDeviceType(),
                    maxNumber,
                    distance)
                    .collectList()
                    .map(users -> {
                        if (users.isEmpty()) {
                            return RequestHandlerResultFactory.NO_CONTENT;
                        }
                        UsersInfosWithVersion.Builder builder = UsersInfosWithVersion.newBuilder();
                        for (User user : users) {
                            builder.addUserInfos(ProtoUtil.userProfile2proto(user));
                        }
                        return RequestHandlerResultFactory.get(TurmsNotification.Data
                                .newBuilder()
                                .setUsersInfosWithVersion(builder)
                                .build());
                    }));
        };
    }

    @ServiceRequestMapping(QUERY_USER_ONLINE_STATUSES_REQUEST)
    public ClientRequestHandler handleQueryUsersOnlineStatusRequest() {
        return clientRequest -> {
            QueryUserOnlineStatusesRequest request = clientRequest.getTurmsRequest().getQueryUserOnlineStatusesRequest();
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
                            statusesBuilder.addUserStatuses(ProtoUtil
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
            UpdateUserLocationRequest request = clientRequest.getTurmsRequest().getUpdateUserLocationRequest();
            Mono<Void> updateMono = sessionLocationService.upsertUserLocation(
                    clientRequest.getUserId(),
                    clientRequest.getDeviceType(),
                    new Point(request.getLatitude(), request.getLongitude()),
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
            UpdateUserOnlineStatusRequest request = clientRequest.getTurmsRequest().getUpdateUserOnlineStatusRequest();
            UserStatus userStatus = request.getUserStatus();
            if (userStatus == UserStatus.UNRECOGNIZED) {
                return Mono.just(RequestHandlerResultFactory
                        .get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The user status must not be UNRECOGNIZED"));
            }
            Set<DeviceType> deviceTypes = request.getDeviceTypesCount() > 0 ? Sets.newHashSet(request.getDeviceTypesList()) : null;
            Mono<Boolean> updateMono;
            if (userStatus == UserStatus.OFFLINE) {
                updateMono = deviceTypes != null
                        ? sessionService.disconnect(clientRequest.getUserId(), deviceTypes, SessionCloseStatus.DISCONNECTED_BY_OTHER_DEVICE)
                        : sessionService.disconnect(clientRequest.getUserId(), SessionCloseStatus.DISCONNECTED_BY_OTHER_DEVICE);
            } else {
                updateMono = userStatusService.updateOnlineUserStatusIfPresent(clientRequest.getUserId(), userStatus);
            }
            boolean notifyMembers =
                    node.getSharedProperties().getService().getNotification().isNotifyMembersAfterOtherMemberOnlineStatusUpdated();
            boolean notifyRelatedUser = node.getSharedProperties().getService().getNotification()
                    .isNotifyRelatedUsersAfterOtherRelatedUserOnlineStatusUpdated();
            if (!notifyMembers && !notifyRelatedUser) {
                return updateMono.thenReturn(RequestHandlerResultFactory.OK);
            } else {
                Mono<Set<Long>> queryMemberIds = notifyMembers
                        ? groupMemberService.queryMemberIdsInUsersJoinedGroups(Set.of(clientRequest.getUserId()))
                        : Mono.just(Collections.emptySet());
                Mono<Set<Long>> queryRelatedUserIds = notifyRelatedUser
                        ? userRelationshipService.queryRelatedUserIds(Set.of(clientRequest.getUserId()), false)
                        .collect(Collectors.toSet())
                        : Mono.just(Collections.emptySet());
                return queryMemberIds.zipWith(queryRelatedUserIds)
                        .map(results -> {
                            results.getT1().addAll(results.getT2());
                            return results.getT1().isEmpty()
                                    ? RequestHandlerResultFactory.OK
                                    : RequestHandlerResultFactory.get(results.getT1(), clientRequest.getTurmsRequest());
                        });
            }
        };
    }

    @ServiceRequestMapping(UPDATE_USER_REQUEST)
    public ClientRequestHandler handleUpdateUserRequest() {
        return clientRequest -> {
            UpdateUserRequest request = clientRequest.getTurmsRequest().getUpdateUserRequest();
            String password = request.hasPassword() ? request.getPassword().getValue() : null;
            String name = request.hasName() ? request.getName().getValue() : null;
            String intro = request.hasIntro() ? request.getIntro().getValue() : null;
            ProfileAccessStrategy profileAccessStrategy = request.getProfileAccessStrategy();
            return userService.updateUser(
                    clientRequest.getUserId(),
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
                            return userRelationshipService.queryRelatedUserIds(Set.of(clientRequest.getUserId()), false)
                                    .collect(Collectors.toSet())
                                    .map(relatedUserIds -> relatedUserIds.isEmpty()
                                            ? RequestHandlerResultFactory.OK
                                            : RequestHandlerResultFactory.get(relatedUserIds, clientRequest.getTurmsRequest()));
                        } else {
                            return Mono.just(RequestHandlerResultFactory.OK);
                        }
                    }));
        };
    }

}