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

package im.turms.service.domain.user.access.admin.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.domain.location.bo.NearbyUser;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.session.bo.UserSessionsInfo;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.service.SessionLocationService;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.observation.service.StatisticsService;
import im.turms.service.domain.user.access.admin.dto.request.UpdateOnlineStatusDTO;
import im.turms.service.domain.user.access.admin.dto.response.OnlineUserCountDTO;
import im.turms.service.domain.user.access.admin.dto.response.UserLocationDTO;
import im.turms.service.domain.user.service.UserService;
import im.turms.service.domain.user.service.onlineuser.NearbyUserService;
import im.turms.service.domain.user.service.onlineuser.SessionService;

/**
 * @author James Chen
 */
@RestController("users/online-infos")
public class UserOnlineInfoController extends BaseController {

    private final UserService userService;
    private final StatisticsService statisticsService;
    private final SessionLocationService sessionLocationService;
    private final UserStatusService userStatusService;
    private final NearbyUserService nearbyUserService;
    private final SessionService sessionService;

    public UserOnlineInfoController(
            TurmsPropertiesManager propertiesManager,
            UserService userService,
            StatisticsService statisticsService,
            SessionLocationService sessionLocationService,
            UserStatusService userStatusService,
            NearbyUserService nearbyUserService,
            SessionService sessionService) {
        super(propertiesManager);
        this.userService = userService;
        this.statisticsService = statisticsService;
        this.sessionLocationService = sessionLocationService;
        this.userStatusService = userStatusService;
        this.nearbyUserService = nearbyUserService;
        this.sessionService = sessionService;
    }

    @GetMapping("count")
    @RequiredPermission(AdminPermission.STATISTICS_USER_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<OnlineUserCountDTO>>> countOnlineUsers(
            boolean countByNodes) {
        if (!countByNodes) {
            return HttpHandlerResult.okIfTruthy(statisticsService.countOnlineUsers()
                    .map(total -> new OnlineUserCountDTO(total, null)));
        }
        return HttpHandlerResult.okIfTruthy(statisticsService.countOnlineUsersByNodes()
                .map(nodeIdToUserCount -> {
                    int sum = 0;
                    for (int onlineUserCount : nodeIdToUserCount.values()) {
                        sum += onlineUserCount;
                    }
                    return new OnlineUserCountDTO(sum, nodeIdToUserCount);
                }));
    }

    @GetMapping("sessions")
    @RequiredPermission(AdminPermission.USER_ONLINE_INFO_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<UserSessionsInfo>>>> queryUserSessions(
            Set<Long> ids,
            boolean returnNonExistingUsers) {
        Mono<Collection<UserSessionsInfo>> queryUserSessions;
        if (returnNonExistingUsers) {
            queryUserSessions = sessionService.queryUserSessions(ids);
        } else {
            queryUserSessions = sessionService.queryUserSessions(ids)
                    .flatMap(infos -> {
                        int size = infos.size();
                        List<Mono<UserSessionsInfo>> newInfos = new ArrayList<>(size);
                        for (UserSessionsInfo info : infos) {
                            if (info.status() == UserStatus.OFFLINE) {
                                newInfos.add(userService.checkIfUserExists(info.userId(), false)
                                        .flatMap(exists -> exists
                                                ? Mono.just(info)
                                                : Mono.empty()));
                            } else {
                                newInfos.add(Mono.just(info));
                            }
                        }
                        return Flux.merge(newInfos)
                                .collect(CollectorUtil.toList(size));
                    });
        }
        return HttpHandlerResult.okIfTruthy(queryUserSessions);
    }

    @GetMapping("statuses")
    @RequiredPermission(AdminPermission.USER_ONLINE_INFO_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<UserSessionsStatus>>>> queryUserStatuses(
            Set<Long> ids,
            boolean returnNonExistingUsers) {
        List<Mono<UserSessionsStatus>> statusMonos = new ArrayList<>(ids.size());
        for (Long userId : ids) {
            if (returnNonExistingUsers) {
                statusMonos.add(userStatusService.getUserSessionsStatus(userId));
            } else {
                statusMonos.add(userStatusService.getUserSessionsStatus(userId)
                        .flatMap(info -> {
                            if (info.userStatus() == UserStatus.OFFLINE) {
                                return userService.checkIfUserExists(userId, false)
                                        .flatMap(exists -> exists
                                                ? Mono.just(info)
                                                : Mono.empty());
                            }
                            return Mono.just(info);
                        }));
            }
        }
        return HttpHandlerResult.okIfTruthy(Flux.merge(statusMonos));
    }

    @GetMapping("nearby-users")
    @RequiredPermission(AdminPermission.USER_ONLINE_INFO_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<List<NearbyUser>>>> queryUsersNearby(
            Long userId,
            @QueryParam(required = false) DeviceType deviceType,
            @QueryParam(required = false) Short maxCount,
            @QueryParam(required = false) Integer maxDistance,
            boolean withCoordinates,
            boolean withDistance,
            boolean withUserInfo) {
        Mono<List<NearbyUser>> nearbyUsers = nearbyUserService.queryNearbyUsers(userId,
                deviceType,
                null,
                null,
                maxCount,
                maxDistance,
                withCoordinates,
                withDistance,
                withUserInfo);
        return HttpHandlerResult.okIfTruthy(nearbyUsers);
    }

    @GetMapping("locations")
    @RequiredPermission(AdminPermission.USER_ONLINE_INFO_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<UserLocationDTO>>>> queryUserLocations(
            Set<Long> ids,
            @QueryParam(required = false) DeviceType deviceType) {
        int size = ids.size();
        List<Mono<UserLocationDTO>> monos = new ArrayList<>(size);
        for (Long userId : ids) {
            monos.add(sessionLocationService.getUserLocation(userId, deviceType)
                    .map(coordinates -> new UserLocationDTO(
                            userId,
                            deviceType,
                            coordinates.getX()
                                    .doubleValue(),
                            coordinates.getY()
                                    .doubleValue())));
        }
        return HttpHandlerResult.okIfTruthy(Flux.merge(monos), size);
    }

    @PutMapping("statuses")
    @RequiredPermission(AdminPermission.USER_ONLINE_INFO_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<Void>>> updateUserOnlineStatus(
            Set<Long> ids,
            @QueryParam(required = false) Set<DeviceType> deviceTypes,
            @RequestBody UpdateOnlineStatusDTO updateOnlineStatusDTO) {
        Mono<Boolean> updateMono;
        UserStatus onlineStatus = updateOnlineStatusDTO.onlineStatus();
        if (onlineStatus == UserStatus.OFFLINE) {
            updateMono = deviceTypes == null
                    ? sessionService.disconnect(ids, SessionCloseStatus.DISCONNECTED_BY_ADMIN)
                    : sessionService
                            .disconnect(ids, deviceTypes, SessionCloseStatus.DISCONNECTED_BY_ADMIN);
        } else {
            updateMono = userStatusService.updateOnlineUsersStatus(ids, onlineStatus);
        }
        return updateMono.thenReturn(HttpHandlerResult.RESPONSE_OK);
    }

}
