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
import im.turms.gateway.manager.UserSessionsManager;
import im.turms.gateway.plugin.extension.UserOnlineStatusChangeHandler;
import im.turms.gateway.plugin.manager.TurmsPluginManager;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.impl.log.UserLoginActionService;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.constraint.ValidDeviceType;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.SessionProperties;
import im.turms.server.common.rpc.request.SetUserOfflineRequest;
import im.turms.server.common.rpc.service.ISessionService;
import im.turms.server.common.service.session.SessionLocationService;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.DeviceTypeUtil;
import im.turms.server.common.util.ReactorUtil;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.annotation.PreDestroy;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static im.turms.gateway.constant.MetricsConstant.LOGGED_IN_USERS_COUNTER_NAME;
import static im.turms.gateway.constant.MetricsConstant.ONLINE_USERS_GAUGE_NAME;

/**
 * @author James Chen
 */
@Service
public class SessionService implements ISessionService {

    private final Node node;
    private final TurmsPropertiesManager turmsPropertiesManager;
    private final TurmsPluginManager turmsPluginManager;
    private final SessionLocationService sessionLocationService;
    private final UserStatusService userStatusService;
    private final UserLoginActionService userLoginActionService;
    private final UserSimultaneousLoginService userSimultaneousLoginService;
    private final boolean pluginEnabled;
    private final Map<Long, UserSessionsManager> sessionsManagerByUserId;
    private int closeIdleSessionAfterMillis;
    private Duration closeIdleSessionAfterDuration;
    private int minHeartbeatIntervalMillis;
    private int switchProtocolAfterMillis;

    private final Counter loggedInUsersCounter;

    public SessionService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            TurmsPluginManager turmsPluginManager,
            UserLoginActionService userLoginActionService,
            SessionLocationService sessionLocationService,
            UserStatusService userStatusService,
            UserSimultaneousLoginService userSimultaneousLoginService,
            MetricsService metricsService) {
        this.node = node;
        this.userLoginActionService = userLoginActionService;
        this.sessionLocationService = sessionLocationService;
        this.turmsPropertiesManager = turmsPropertiesManager;
        this.turmsPluginManager = turmsPluginManager;
        this.userStatusService = userStatusService;
        this.userSimultaneousLoginService = userSimultaneousLoginService;
        TurmsProperties turmsProperties = node.getSharedProperties();
        // Use ConcurrentHashMap to avoid overwriting an existing sessions manager by accident
        sessionsManagerByUserId = new ConcurrentHashMap<>(4096);
        pluginEnabled = turmsProperties.getPlugin().isEnabled();

        SessionProperties sessionProperties = turmsProperties.getGateway().getSession();
        closeIdleSessionAfterMillis = sessionProperties.getCloseIdleSessionAfterSeconds() * 1000;
        closeIdleSessionAfterDuration = Duration.ofMillis(closeIdleSessionAfterMillis);
        minHeartbeatIntervalMillis = sessionProperties.getMinHeartbeatIntervalSeconds() * 1000;
        switchProtocolAfterMillis = sessionProperties.getSwitchProtocolAfterSeconds() * 1000;
        node.addPropertiesChangeListener(newProperties -> {
            SessionProperties newSessionProperties = newProperties.getGateway().getSession();
            closeIdleSessionAfterMillis = newSessionProperties.getCloseIdleSessionAfterSeconds() * 1000;
            closeIdleSessionAfterDuration = Duration.ofMillis(closeIdleSessionAfterMillis);
            minHeartbeatIntervalMillis = newSessionProperties.getMinHeartbeatIntervalSeconds() * 1000;
            switchProtocolAfterMillis = newSessionProperties.getSwitchProtocolAfterSeconds() * 1000;
        });

        MeterRegistry registry = metricsService.getRegistry();
        loggedInUsersCounter = registry.counter(LOGGED_IN_USERS_COUNTER_NAME);
        registry.gaugeMapSize(ONLINE_USERS_GAUGE_NAME, Tags.empty(), sessionsManagerByUserId);
    }

    @PreDestroy
    public void destroy() {
        CloseReason closeReason = CloseReason.get(SessionCloseStatus.SERVER_CLOSED);
        clearAllLocalSessions(new Date(), closeReason)
                .subscribe();
    }

    /**
     * @return true if the user was online
     */
    public Mono<Boolean> setLocalSessionOfflineByUserIdAndDeviceType(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            @NotNull CloseReason closeReason) {
        try {
            AssertUtil.notNull(deviceType, "deviceType");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return setLocalSessionOfflineByUserIdAndDeviceTypes(userId, Collections.singleton(deviceType), closeReason, new Date());
    }

    /**
     * @return true if the user was online
     */
    @Override
    public Mono<Boolean> setLocalSessionOfflineByUserIdAndDeviceTypes(
            @NotNull Long userId,
            @NotEmpty Set<@ValidDeviceType DeviceType> deviceTypes,
            @NotNull CloseReason closeReason) {
        return setLocalSessionOfflineByUserIdAndDeviceTypes(userId, deviceTypes, closeReason, new Date());
    }

    public Mono<Boolean> authAndSetLocalSessionOfflineByUserIdAndDeviceType(@NotNull Long userId,
                                                                            @NotNull DeviceType deviceType,
                                                                            @NotNull CloseReason closeReason,
                                                                            int sessionId) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            AssertUtil.notNull(closeReason, "closeReason");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        UserSessionsManager manager = getUserSessionsManager(userId);
        if (manager == null) {
            return Mono.just(false);
        }
        UserSession session = manager.getSession(deviceType);
        if (session.getId() == sessionId) {
            return setLocalSessionOfflineByUserIdAndDeviceTypes0(userId, Collections.singleton(deviceType), closeReason, new Date(), manager);
        } else {
            return Mono.just(false);
        }
    }

    public Mono<Boolean> setLocalSessionOfflineByUserIdAndDeviceTypes(
            @NotNull Long userId,
            @NotEmpty Set<@ValidDeviceType DeviceType> deviceTypes,
            @NotNull CloseReason closeReason,
            @NotNull Date disconnectionDate) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        UserSessionsManager manager = getUserSessionsManager(userId);
        if (manager == null) {
            return Mono.just(false);
        }
        return setLocalSessionOfflineByUserIdAndDeviceTypes0(userId, deviceTypes, closeReason, disconnectionDate, manager);
    }

    public Mono<Boolean> setLocalSessionOfflineByUserIdAndDeviceTypes0(
            @NotNull Long userId,
            @NotEmpty Set<@ValidDeviceType DeviceType> deviceTypes,
            @NotNull CloseReason closeReason,
            @NotNull Date disconnectionDate,
            @NotNull UserSessionsManager manager) {
        try {
            AssertUtil.notNull(closeReason, "closeReason");
            AssertUtil.notNull(disconnectionDate, "disconnectionDate");
            AssertUtil.notNull(manager, "manager");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        // Don't close the session (connection) first and then remove the session status in Redis
        // because it will make problem if a client logins again while the session status in Redis hasn't been removed
        return userStatusService.removeStatusByUserIdAndDeviceTypes(userId, deviceTypes)
                .flatMap(ignored -> {
                    // Update the session information in Redis
                    List<Mono<UserSession>> disconnectMonos = new ArrayList<>(deviceTypes.size());
                    for (DeviceType deviceType : deviceTypes) {
                        UserSession session = manager.getSession(deviceType);
                        if (session != null && sessionLocationService.isLocationEnabled()) {
                            disconnectMonos.add(sessionLocationService.removeUserLocation(userId, deviceType)
                                    .thenReturn(session));
                        }
                    }
                    return Flux.merge(disconnectMonos)
                            .doOnNext(session -> {
                                // Log and disconnect
                                Long logId = session.getLogId();
                                if (logId != null) {
                                    userLoginActionService.tryLogLogoutActionAndTriggerHandlers(logId, userId, disconnectionDate);
                                }
                                manager.setDeviceOffline(session.getDeviceType(), closeReason);
                                removeSessionsManagerIfEmpty(closeReason, manager, userId);
                            })
                            .then(Mono.just(true));
                });
    }

    public Mono<Void> clearAllLocalSessions(@NotNull Date disconnectionDate, @NotNull CloseReason closeReason) {
        try {
            AssertUtil.notNull(disconnectionDate, "disconnectionDate");
            AssertUtil.notNull(closeReason, "closeReason");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Set<Map.Entry<Long, UserSessionsManager>> entries = sessionsManagerByUserId.entrySet();
        List<Mono<Boolean>> monos = new ArrayList<>(entries.size());
        for (Map.Entry<Long, UserSessionsManager> entry : entries) {
            Long userId = entry.getKey();
            Set<DeviceType> loggedInDeviceTypes = entry.getValue().getLoggedInDeviceTypes();
            Mono<Boolean> mono = setLocalSessionOfflineByUserIdAndDeviceTypes(userId, loggedInDeviceTypes, closeReason, disconnectionDate)
                    .onErrorResume(t -> Mono.empty());
            monos.add(mono);
        }
        return Mono.when(monos);
    }

    @Override
    public Mono<Boolean> setLocalUserOffline(
            @NotNull Long userId,
            @NotNull CloseReason closeReason) {
        return setLocalSessionOfflineByUserIdAndDeviceTypes(userId, DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES_SET, closeReason, new Date());
    }

    /**
     * @return true if the session exists and the update operation was successful
     */
    public Mono<Boolean> updateHeartbeatTimestamp(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        UserSessionsManager userSessionsManager = getUserSessionsManager(userId);
        if (userSessionsManager != null) {
            UserSession session = userSessionsManager.getSession(deviceType);
            if (session != null && !session.getConnection().isConnectionRecovering()) {
                return updateHeartbeatTimestamp(userId, session);
            }
        }
        return Mono.just(false);
    }

    public Mono<Boolean> updateHeartbeatTimestamp(@NotNull Long userId, @NotNull UserSession session) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(session, "session");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        long lastHeartbeatTimestampMillis = session.getLastHeartbeatTimestampMillis();
        boolean isAllowedToUpdate = System.currentTimeMillis() - lastHeartbeatTimestampMillis > minHeartbeatIntervalMillis;
        return isAllowedToUpdate
                ? userStatusService.updateTtl(userId, closeIdleSessionAfterDuration)
                .doOnNext(exists -> session.setLastHeartbeatTimestampMillis(System.currentTimeMillis()))
                : Mono.just(true);
    }

    public Mono<UserSession> authAndUpdateHeartbeatTimestamp(long userId, @NotNull @ValidDeviceType DeviceType deviceType, int sessionId) {
        try {
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        UserSessionsManager userSessionsManager = getUserSessionsManager(userId);
        if (userSessionsManager != null) {
            UserSession session = userSessionsManager.getSession(deviceType);
            if (session != null && session.getId() == sessionId && !session.getConnection().isConnectionRecovering()) {
                return updateHeartbeatTimestamp(userId, session)
                        .flatMap(updated -> updated ? Mono.just(session) : Mono.empty());
            }
        }
        return Mono.empty();
    }

    /**
     * For the case that the client recovers from UDP to TCP/WebSocket:
     * Return the local session if the client connects to the machine that owns the existing session;
     * Return a new session and disconnect the remote session if the existing session is on a different machine.
     */
    public Mono<UserSession> tryRegisterOnlineUser(
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable UserStatus userStatus,
            @Nullable Point position,
            @Nullable String ip,
            @Nullable String deviceDetails) {
        try {
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            AssertUtil.state(userStatus != UserStatus.UNRECOGNIZED, "The user status must not be UNRECOGNIZED");
            AssertUtil.state(userStatus != UserStatus.OFFLINE, "The user status must not be OFFLINE");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        // Must fetch the latest status instead of the status in the cache
        return userStatusService.fetchUserSessionsStatus(userId)
                .flatMap(sessionsStatus -> {
                    // Check the current sessions status
                    if (sessionsStatus.getUserStatus() == UserStatus.OFFLINE) {
                        return addOnlineDeviceIfAbsent(userId, deviceType, userStatus, position, ip, deviceDetails, sessionsStatus);
                    } else {
                        boolean conflicts = sessionsStatus.getLoggedInDeviceTypes().contains(deviceType);
                        if (conflicts) {
                            UserSession session = getLocalUserSession(userId, deviceType);
                            boolean isDisconnectedSessionOnLocal = session != null
                                    && session.getConnection() != null
                                    && !session.getConnection().isConnected();
                            if (isDisconnectedSessionOnLocal) {
                                // Note that the downstream should replace the disconnected connection
                                // with the connected TCP/WebSocket connection
                                Mono<Void> updateSessionInfoMono = userStatus == null || sessionsStatus.getUserStatus() == userStatus
                                        ? Mono.empty()
                                        : userStatusService.updateOnlineUserStatus(userId, userStatus)
                                        .then()
                                        .onErrorResume(throwable -> Mono.empty());
                                if (position != null) {
                                    updateSessionInfoMono = updateSessionInfoMono
                                            .flatMap(unused -> sessionLocationService.upsertUserLocation(userId, deviceType, position, new Date())
                                                    .onErrorResume(throwable -> Mono.empty()));
                                }
                                return updateSessionInfoMono.thenReturn(session);
                            } else if (userSimultaneousLoginService.shouldDisconnectLoggingInDeviceIfConflicts()) {
                                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE));
                            }
                        }
                        return disconnectConflictedDeviceTypes(userId, deviceType, sessionsStatus)
                                .flatMap(wasSuccessful -> wasSuccessful
                                        ? addOnlineDeviceIfAbsent(userId, deviceType, userStatus, position, ip, deviceDetails, sessionsStatus)
                                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE)));
                    }
                });
    }

    @Nullable
    public UserSessionsManager getUserSessionsManager(@NotNull Long userId) {
        AssertUtil.notNull(userId, "userId");
        return sessionsManagerByUserId.get(userId);
    }

    @Nullable
    public UserSession getLocalUserSession(@NotNull Long userId, @NotNull DeviceType deviceType) {
        AssertUtil.notNull(userId, "userId");
        AssertUtil.notNull(deviceType, "deviceType");
        UserSessionsManager userSessionsManager = sessionsManagerByUserId.get(userId);
        return userSessionsManager != null ? userSessionsManager.getSession(deviceType) : null;
    }

    public int countLocalOnlineUsers() {
        return sessionsManagerByUserId.size();
    }

    private Mono<Boolean> disconnectConflictedDeviceTypes(@NotNull Long userId,
                                                          @NotNull @ValidDeviceType DeviceType deviceType,
                                                          @NotNull UserSessionsStatus sessionsStatus) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            AssertUtil.notNull(sessionsStatus, "sessionsStatus");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
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
            Set<String> nodeIds = nodeIdAndDeviceTypesMap.keySet();
            List<Mono<Boolean>> disconnectionRequests = new ArrayList<>(nodeIds.size());
            for (String nodeId : nodeIds) {
                Set<DeviceType> deviceTypes = nodeIdAndDeviceTypesMap.get(nodeId);
                SetUserOfflineRequest request = new SetUserOfflineRequest(userId, deviceTypes, SessionCloseStatus.DISCONNECTED_BY_CLIENT);
                disconnectionRequests.add(node.getRpcService().requestResponse(nodeId, request));
            }
            return ReactorUtil.areAllTrue(disconnectionRequests);
        } else {
            return Mono.just(true);
        }
    }

    public void onSessionEstablished(@NotNull UserSessionsManager userSessionsManager,
                                     @NotNull @ValidDeviceType DeviceType deviceType) {
        loggedInUsersCounter.increment();
        if (node.getSharedProperties().getGateway().getSession().isNotifyClientsOfSessionInfoAfterConnected()) {
            String serverId = turmsPropertiesManager.getLocalProperties().getGateway().getDiscovery().getIdentity();
            userSessionsManager.pushSessionNotification(deviceType, serverId);
        }
    }

    private Mono<UserSession> addOnlineDeviceIfAbsent(
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable UserStatus userStatus,
            @Nullable Point position,
            @Nullable String ip,
            @Nullable String deviceDetails,
            @NotNull UserSessionsStatus sessionsStatus) {
        // Try to update the global user status
        return userStatusService.addOnlineDeviceIfAbsent(userId, deviceType, userStatus, closeIdleSessionAfterDuration, sessionsStatus)
                .flatMap(wasSuccessful -> {
                    if (!wasSuccessful) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE));
                    }
                    UserStatus finalUserStatus = userStatus != null ? userStatus : UserStatus.AVAILABLE;
                    UserSessionsManager manager = sessionsManagerByUserId.computeIfAbsent(userId, key ->
                            new UserSessionsManager(key, finalUserStatus));
                    UserSession session = manager.addSessionIfAbsent(deviceType, position, null, closeIdleSessionAfterMillis, switchProtocolAfterMillis);
                    // This should never happen
                    if (session == null) {
                        manager.setDeviceOffline(deviceType, CloseReason.get(SessionCloseStatus.LOGIN_CONFLICT));
                        session = manager.addSessionIfAbsent(deviceType, position, null, closeIdleSessionAfterMillis, switchProtocolAfterMillis);
                        if (session == null) {
                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SERVER_INTERNAL_ERROR));
                        }
                    }

                    long logId = node.nextRandomId(ServiceType.LOG);
                    Date now = new Date();
                    if (position != null && sessionLocationService.isLocationEnabled()) {
                        return sessionLocationService.upsertUserLocation(userId, deviceType, position, now)
                                .doOnSuccess(ignored -> userLoginActionService
                                        .tryLogLoginActionAndTriggerHandlers(logId, userId, finalUserStatus, deviceType, position, ip, deviceDetails, now))
                                .thenReturn(session);
                    } else {
                        userLoginActionService
                                .tryLogLoginActionAndTriggerHandlers(logId, userId, finalUserStatus, deviceType, position, ip, deviceDetails, now);
                        return Mono.just(session);
                    }
                });
    }

    private void removeSessionsManagerIfEmpty(@NotNull CloseReason closeReason, @NotNull UserSessionsManager manager, @NotNull Long userId) {
        if (manager.getSessionsNumber() == 0) {
            sessionsManagerByUserId.remove(userId);
        }
        if (pluginEnabled) {
            List<UserOnlineStatusChangeHandler> handlerList = turmsPluginManager.getUserOnlineStatusChangeHandlerList();
            if (!handlerList.isEmpty()) {
                for (UserOnlineStatusChangeHandler handler : handlerList) {
                    handler.goOffline(manager, closeReason).subscribe();
                }
            }
        }
    }

}