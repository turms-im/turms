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

package im.turms.gateway.service.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.gateway.access.websocket.dto.CloseStatusFactory;
import im.turms.gateway.manager.UserSessionsManager;
import im.turms.gateway.plugin.extension.UserOnlineStatusChangeHandler;
import im.turms.gateway.plugin.manager.TurmsPluginManager;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.impl.log.UserLoginActionService;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constraint.DeviceTypeConstraint;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.rpc.request.SetUserOfflineRequest;
import im.turms.server.common.rpc.service.ISessionService;
import im.turms.server.common.service.session.SessionLocationService;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.server.common.util.DeviceTypeUtil;
import im.turms.server.common.util.ReactorUtil;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.socket.CloseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.annotation.PreDestroy;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Chen
 */
@Service
@Validated
public class SessionService implements ISessionService {

    private final Node node;
    private final TurmsPluginManager turmsPluginManager;
    private final ReasonCacheService reasonCacheService;
    private final SessionLocationService sessionLocationService;
    private final UserStatusService userStatusService;
    private final UserLoginActionService userLoginActionService;
    private final UserSimultaneousLoginService userSimultaneousLoginService;
    private final boolean pluginEnabled;
    private final Map<Long, UserSessionsManager> sessionsManagerByUserId;
    private int heartbeatTimeout;
    private Duration heartbeatTimeoutDuration;
    private int minimumUpdateHeartbeatIntervalSeconds;

    public SessionService(
            Node node,
            TurmsPluginManager turmsPluginManager,
            UserLoginActionService userLoginActionService,
            SessionLocationService sessionLocationService,
            ReasonCacheService reasonCacheService,
            UserStatusService userStatusService,
            UserSimultaneousLoginService userSimultaneousLoginService) {
        this.node = node;
        this.userLoginActionService = userLoginActionService;
        this.sessionLocationService = sessionLocationService;
        this.turmsPluginManager = turmsPluginManager;
        this.userStatusService = userStatusService;
        this.userSimultaneousLoginService = userSimultaneousLoginService;
        TurmsProperties turmsProperties = node.getSharedProperties();
        // Use ConcurrentHashMap to avoid overwriting an existing sessions manager by accident
        sessionsManagerByUserId = new ConcurrentHashMap<>(4096);
        pluginEnabled = turmsProperties.getPlugin().isEnabled();
        this.reasonCacheService = reasonCacheService;
        heartbeatTimeout = node.getSharedProperties().getGateway().getSession().getHeartbeatTimeoutSeconds();
        heartbeatTimeoutDuration = Duration.ofSeconds(heartbeatTimeout);
        node.addPropertiesChangeListener(newProperties -> {
            heartbeatTimeout = newProperties.getGateway().getSession().getHeartbeatTimeoutSeconds();
            heartbeatTimeoutDuration = Duration.ofSeconds(heartbeatTimeout);
            minimumUpdateHeartbeatIntervalSeconds = newProperties.getGateway().getSession().getMinimumUpdateHeartbeatIntervalSeconds();
        });
    }

    @PreDestroy
    public void destroy() {
        clearAllLocalSessions(new Date(), CloseStatusFactory.get(SessionCloseStatus.SERVER_CLOSED))
                .subscribe();
    }

    /**
     * @return true if the user was online
     */
    public Mono<Boolean> setLocalSessionOfflineByUserIdAndDeviceType(
            @NotNull Long userId,
            @NotNull @DeviceTypeConstraint DeviceType deviceType,
            @NotNull CloseStatus closeStatus) {
        return setLocalSessionOfflineByUserIdAndDeviceTypes(userId, Collections.singleton(deviceType), closeStatus);
    }

    /**
     * @return true if the user was online
     */
    @Override
    public Mono<Boolean> setLocalSessionOfflineByUserIdAndDeviceTypes(
            @NotNull Long userId,
            @NotEmpty Set<@DeviceTypeConstraint DeviceType> deviceTypes,
            @NotNull CloseStatus closeStatus) {
        return setLocalSessionOfflineByUserIdAndDeviceTypes(userId, deviceTypes, closeStatus, new Date());
    }

    public Mono<Boolean> setLocalSessionOfflineByUserIdAndDeviceTypes(
            @NotNull Long userId,
            @NotEmpty Set<@DeviceTypeConstraint DeviceType> deviceTypes,
            @NotNull CloseStatus closeStatus,
            @NotNull Date disconnectionDate) {
        UserSessionsManager manager = getUserSessionsManager(userId);
        if (manager == null) {
            return Mono.just(false);
        }
        // Don't close the real session (connection) first and then remove the session status in Redis
        // because it will make problem if a client logins again while the session status in Redis hasn't been removed
        return userStatusService.removeStatusByUserIdAndDeviceTypes(userId, deviceTypes)
                .flatMap(ignored -> {
                    List<Mono<UserSession>> disconnectMonos = new ArrayList<>(deviceTypes.size());
                    for (DeviceType deviceType : deviceTypes) {
                        UserSession session = manager.getSession(deviceType);
                        if (session != null) {
                            boolean cacheDisconnectionReason = reasonCacheService.shouldCacheDisconnectionReason(userId, deviceType);
                            if (sessionLocationService.isLocationEnabled()) {
                                if (cacheDisconnectionReason) {
                                    int sessionId = session.getId();
                                    disconnectMonos.add(reasonCacheService.cacheDisconnectionReason(userId, deviceType, sessionId, closeStatus)
                                            .then(sessionLocationService.removeUserLocation(userId, deviceType))
                                            .thenReturn(session));
                                } else {
                                    disconnectMonos.add(sessionLocationService.removeUserLocation(userId, deviceType)
                                            .thenReturn(session));
                                }
                            } else if (cacheDisconnectionReason) {
                                int sessionId = session.getId();
                                disconnectMonos.add(reasonCacheService.cacheDisconnectionReason(userId, deviceType, sessionId, closeStatus)
                                        .thenReturn(session));
                            }
                        }
                    }
                    return Flux.merge(disconnectMonos)
                            .doOnNext(session -> {
                                userLoginActionService.tryLogLogoutActionAndTriggerHandlers(session.getLogId(), userId, disconnectionDate);
                                manager.setDeviceOffline(session.getDeviceType(), closeStatus);
                                removeSessionsManagerIfEmpty(closeStatus, manager, userId);
                            })
                            .then(Mono.just(true));
                });
    }

    public Mono<Void> clearAllLocalSessions(Date disconnectionDate, CloseStatus closeStatus) {
        List<Mono<Boolean>> monos = new LinkedList<>();
        for (Map.Entry<Long, UserSessionsManager> entry : sessionsManagerByUserId.entrySet()) {
            Long userId = entry.getKey();
            Set<DeviceType> loggedInDeviceTypes = entry.getValue().getLoggedInDeviceTypes();
            Mono<Boolean> mono = setLocalSessionOfflineByUserIdAndDeviceTypes(userId, loggedInDeviceTypes, closeStatus, disconnectionDate);
            monos.add(mono);
        }
        return Mono.when(monos);
    }

    @Override
    public Mono<Boolean> setLocalUserOffline(
            @NotNull Long userId,
            @NotNull CloseStatus closeStatus) {
        return setLocalSessionOfflineByUserIdAndDeviceTypes(userId, DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES_SET, closeStatus, new Date());
    }

    /**
     * @return true if the session exists and the update operation was successful
     */
    public Mono<Boolean> updateHeartbeatTimestamp(
            @NotNull Long userId,
            @NotNull @DeviceTypeConstraint DeviceType deviceType) {
        UserSessionsManager userSessionsManager = getUserSessionsManager(userId);
        if (userSessionsManager != null) {
            UserSession session = userSessionsManager.getSession(deviceType);
            if (session != null) {
                return updateHeartbeatTimestamp(userId, session);
            }
        }
        return Mono.just(false);
    }

    public Mono<Boolean> updateHeartbeatTimestamp(@NotNull Long userId, @NotNull UserSession session) {
        long lastHeartbeatTimestampMillis = session.getLastHeartbeatTimestampMillis();
        boolean isAllowedToUpdate = (System.currentTimeMillis() - lastHeartbeatTimestampMillis) / 1000 > minimumUpdateHeartbeatIntervalSeconds;
        return isAllowedToUpdate
                ? userStatusService.updateTtl(userId, heartbeatTimeoutDuration)
                .doOnNext(exists -> session.setLastHeartbeatTimestampMillis(System.currentTimeMillis()))
                : Mono.just(true);
    }

    public Mono<TurmsStatusCode> tryRegisterOnlineUser(
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable UserStatus userStatus,
            @Nullable Point userLocation,
            @Nullable String ip,
            @Nullable Map<String, String> deviceDetails) {
        // Must fetch the latest status instead of the status in the cache
        return userStatusService.fetchUserSessionsStatus(userId)
                .flatMap(sessionsStatus -> {
                    // Check the current sessions status
                    if (sessionsStatus.getUserStatus() == UserStatus.OFFLINE) {
                        return addOnlineDeviceIfAbsent(userId, deviceType, userStatus, userLocation, ip, deviceDetails, sessionsStatus);
                    } else if (userSimultaneousLoginService.shouldDisconnectLoggingInDevice()) {
                        return Mono.just(TurmsStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE);
                    } else {
                        return disconnectConflictedDeviceTypes(userId, deviceType, sessionsStatus)
                                .flatMap(wasSuccessful -> wasSuccessful
                                        ? addOnlineDeviceIfAbsent(userId, deviceType, userStatus, userLocation, ip, deviceDetails, sessionsStatus)
                                        : Mono.just(TurmsStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE));
                    }
                });
    }

    @Nullable
    public UserSessionsManager getUserSessionsManager(@NotNull Long userId) {
        return sessionsManagerByUserId.get(userId);
    }

    @Nullable
    public UserSession getLocalUserSession(@NotNull Long userId, @NotNull DeviceType deviceType) {
        UserSessionsManager userSessionsManager = sessionsManagerByUserId.get(userId);
        return userSessionsManager != null ? userSessionsManager.getSession(deviceType) : null;
    }

    public int countLocalOnlineUsers() {
        return sessionsManagerByUserId.size();
    }

    private Mono<Boolean> disconnectConflictedDeviceTypes(Long userId, DeviceType deviceType, UserSessionsStatus sessionsStatus) {
        Set<DeviceType> conflictedDeviceTypes = userSimultaneousLoginService.getConflictedDeviceTypes(deviceType);
        SetMultimap<String, DeviceType> nodeIdAndDeviceTypesMap = null;
        for (DeviceType conflictedDeviceType : conflictedDeviceTypes) {
            String nodeId = sessionsStatus.getNodeIdByDeviceType(conflictedDeviceType);
            if (nodeId != null) {
                if (nodeIdAndDeviceTypesMap == null) {
                    nodeIdAndDeviceTypesMap = HashMultimap.create(3, 3);
                }
                nodeIdAndDeviceTypesMap.put(nodeId, deviceType);
            }
        }
        if (nodeIdAndDeviceTypesMap != null) {
            List<Mono<Boolean>> disconnectionRequests = new LinkedList<>();
            for (String nodeId : nodeIdAndDeviceTypesMap.keySet()) {
                Set<DeviceType> deviceTypes = nodeIdAndDeviceTypesMap.get(nodeId);
                SetUserOfflineRequest request = new SetUserOfflineRequest(userId, deviceTypes, CloseStatusFactory.get(SessionCloseStatus.DISCONNECTED_BY_CLIENT));
                disconnectionRequests.add(node.getRpcService().requestResponse(nodeId, request));
            }
            return ReactorUtil.areAllTrue(disconnectionRequests);
        } else {
            return Mono.just(true);
        }
    }

    private Mono<TurmsStatusCode> addOnlineDeviceIfAbsent(
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable UserStatus userStatus,
            @Nullable Point userLocation,
            @Nullable String ip,
            @Nullable Map<String, String> deviceDetails,
            @NotNull UserSessionsStatus sessionsStatus) {
        // Try to update the global user status
        return userStatusService.addOnlineDeviceIfAbsent(userId, deviceType, userStatus, heartbeatTimeoutDuration, sessionsStatus)
                .flatMap(wasSuccessful -> {
                    if (wasSuccessful) {
                        UserStatus finalUserStatus = userStatus != null ? userStatus : UserStatus.AVAILABLE;
                        boolean[] managerExists = new boolean[]{true};
                        UserSessionsManager manager = sessionsManagerByUserId.computeIfAbsent(userId, key -> {
                            managerExists[0] = false;
                            return new UserSessionsManager(userId, finalUserStatus, deviceType, userLocation, null, heartbeatTimeout, null);
                        });
                        if (managerExists[0]) {
                            boolean added = manager.addSessionIfAbsent(deviceType, userLocation, null, heartbeatTimeout);
                            // This should never happen
                            if (!added) {
                                manager.setDeviceOffline(deviceType, CloseStatusFactory.get(SessionCloseStatus.LOGIN_CONFLICT));
                                manager.addSessionIfAbsent(deviceType, userLocation, null, heartbeatTimeout);
                            }
                        }

                        long logId = node.getFlakeIdService().nextId(ServiceType.LOG);
                        Date now = new Date();
                        if (userLocation != null && sessionLocationService.isLocationEnabled()) {
                            return sessionLocationService.upsertUserLocation(userId, deviceType, userLocation, now)
                                    .doOnSuccess(hasUpsertedLocation -> {
                                        if (hasUpsertedLocation) {
                                            userLoginActionService
                                                    .tryLogLoginActionAndTriggerHandlers(logId, userId, finalUserStatus, deviceType, userLocation, ip, deviceDetails, now);
                                        }
                                    })
                                    .thenReturn(TurmsStatusCode.OK);
                        } else {
                            userLoginActionService
                                    .tryLogLoginActionAndTriggerHandlers(logId, userId, finalUserStatus, deviceType, userLocation, ip, deviceDetails, now);
                            return Mono.just(TurmsStatusCode.OK);
                        }
                    } else {
                        return Mono.just(TurmsStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE);
                    }
                });
    }

    private void removeSessionsManagerIfEmpty(@NotNull CloseStatus closeStatus, UserSessionsManager manager, Long userId) {
        if (manager.getSessionsNumber() == 0) {
            sessionsManagerByUserId.remove(userId);
        }
        if (pluginEnabled) {
            List<UserOnlineStatusChangeHandler> handlerList = turmsPluginManager.getUserOnlineStatusChangeHandlerList();
            if (!handlerList.isEmpty()) {
                for (UserOnlineStatusChangeHandler handler : handlerList) {
                    handler.goOffline(manager, closeStatus).subscribe();
                }
            }
        }
    }

}