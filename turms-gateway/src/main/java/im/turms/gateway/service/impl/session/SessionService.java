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

package im.turms.gateway.service.impl.session;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.gateway.manager.HeartbeatManager;
import im.turms.gateway.manager.UserSessionsManager;
import im.turms.gateway.plugin.extension.UserOnlineStatusChangeHandler;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.impl.observability.MetricsService;
import im.turms.server.common.bo.location.Coordinates;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.rpc.exception.ConnectionNotFound;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.constraint.ValidDeviceType;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.lang.ByteArrayWrapper;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.plugin.PluginManager;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.GatewayProperties;
import im.turms.server.common.property.env.gateway.SessionProperties;
import im.turms.server.common.rpc.request.SetUserOfflineRequest;
import im.turms.server.common.rpc.service.ISessionService;
import im.turms.server.common.service.session.SessionLocationService;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.DeviceTypeUtil;
import im.turms.server.common.util.MapUtil;
import im.turms.server.common.util.ReactorUtil;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.annotation.PreDestroy;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import static im.turms.gateway.constant.MetricsConstant.LOGGED_IN_USERS_COUNTER_NAME;
import static im.turms.gateway.constant.MetricsConstant.ONLINE_USERS_GAUGE_NAME;

/**
 * @author James Chen
 */
@Service
public class SessionService implements ISessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);

    private final Node node;
    private final TurmsPropertiesManager turmsPropertiesManager;
    private final HeartbeatManager heartbeatManager;
    private final PluginManager pluginManager;

    private final SessionLocationService sessionLocationService;
    private final UserStatusService userStatusService;
    private final UserSimultaneousLoginService userSimultaneousLoginService;

    private int closeIdleSessionAfterSeconds;

    private final ConcurrentHashMap<Long, UserSessionsManager> sessionsManagerByUserId;
    private final ConcurrentHashMap<ByteArrayWrapper, ConcurrentLinkedQueue<UserSession>> sessionsByIp;

    private final List<Consumer<UserSession>> onSessionClosedListeners = new LinkedList<>();

    private final Counter loggedInUsersCounter;

    public SessionService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            PluginManager pluginManager,
            SessionLocationService sessionLocationService,
            UserStatusService userStatusService,
            UserSimultaneousLoginService userSimultaneousLoginService,
            MetricsService metricsService) {
        this.node = node;
        this.sessionLocationService = sessionLocationService;
        this.turmsPropertiesManager = turmsPropertiesManager;
        this.pluginManager = pluginManager;
        this.userStatusService = userStatusService;
        this.userSimultaneousLoginService = userSimultaneousLoginService;
        TurmsProperties turmsProperties = node.getSharedProperties();
        sessionsManagerByUserId = new ConcurrentHashMap<>(4096);
        sessionsByIp = new ConcurrentHashMap<>(4096);

        SessionProperties sessionProperties = turmsProperties.getGateway().getSession();
        closeIdleSessionAfterSeconds = sessionProperties.getCloseIdleSessionAfterSeconds();

        heartbeatManager = new HeartbeatManager(this,
                userStatusService,
                sessionsManagerByUserId,
                sessionProperties.getClientHeartbeatIntervalSeconds(),
                closeIdleSessionAfterSeconds,
                sessionProperties.getMinHeartbeatIntervalSeconds(),
                sessionProperties.getSwitchProtocolAfterSeconds());

        node.addPropertiesChangeListener(newProperties -> {
            GatewayProperties newGatewayProperties = newProperties.getGateway();
            SessionProperties newSessionProperties = newGatewayProperties.getSession();
            closeIdleSessionAfterSeconds = newSessionProperties.getCloseIdleSessionAfterSeconds();
            heartbeatManager.setClientHeartbeatIntervalSeconds(newSessionProperties.getClientHeartbeatIntervalSeconds());
            heartbeatManager.setCloseIdleSessionAfterSeconds(newSessionProperties.getCloseIdleSessionAfterSeconds());
            heartbeatManager.setMinHeartbeatIntervalMillis(newSessionProperties.getMinHeartbeatIntervalSeconds() * 1000);
            heartbeatManager.setSwitchProtocolAfterMillis(newSessionProperties.getSwitchProtocolAfterSeconds() * 1000);
        });

        MeterRegistry registry = metricsService.getRegistry();
        loggedInUsersCounter = registry.counter(LOGGED_IN_USERS_COUNTER_NAME);
        registry.gaugeMapSize(ONLINE_USERS_GAUGE_NAME, Tags.empty(), sessionsManagerByUserId);
    }

    @PreDestroy
    public void destroy() {
        heartbeatManager.destroy();
        CloseReason closeReason = CloseReason.get(SessionCloseStatus.SERVER_CLOSED);
        try {
            clearAllLocalSessions(closeReason)
                    .block(Duration.ofSeconds(60));
        } catch (Exception e) {
            throw new IllegalStateException("Caught an error while clearing local sessions", e);
        }
    }

    /**
     * @return true if the user was online
     */
    @Override
    public Mono<Boolean> setLocalSessionsOfflineByIp(
            @NotNull byte[] ip,
            @NotNull CloseReason closeReason) {
        try {
            AssertUtil.notNull(ip, "ip");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Queue<UserSession> sessions = sessionsByIp.get(new ByteArrayWrapper(ip));
        Iterator<UserSession> iterator = sessions.iterator();
        if (!iterator.hasNext()) {
            return Mono.just(false);
        }
        Mono<Boolean> first = setLocalSessionOfflineByUserIdAndDeviceTypes(iterator.next().getUserId(),
                DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES_SET,
                closeReason);
        // Fast path
        if (!iterator.hasNext()) {
            return first;
        }
        // Slow path
        // Use ArrayList instead of LinkedList because it's really heavy
        List<Mono<Boolean>> list = new ArrayList<>(4);
        list.add(first);
        list.add(setLocalSessionOfflineByUserIdAndDeviceTypes(iterator.next().getUserId(),
                DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES_SET,
                closeReason));
        while (iterator.hasNext()) {
            list.add(setLocalSessionOfflineByUserIdAndDeviceTypes(iterator.next().getUserId(),
                    DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES_SET,
                    closeReason));
        }
        return ReactorUtil.atLeastOneTrue(list);
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
        return setLocalSessionOfflineByUserIdAndDeviceTypes(userId, Collections.singleton(deviceType), closeReason);
    }

    /**
     * @return true if the user was online
     */
    @Override
    public Mono<Boolean> setLocalSessionOfflineByUserIdAndDeviceTypes(
            @NotNull Long userId,
            @NotEmpty Set<@ValidDeviceType DeviceType> deviceTypes,
            @NotNull CloseReason closeReason) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        UserSessionsManager manager = getUserSessionsManager(userId);
        if (manager == null) {
            return Mono.just(false);
        }
        return setLocalSessionOfflineByUserIdAndDeviceTypes0(userId, deviceTypes, closeReason, manager);
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
            return setLocalSessionOfflineByUserIdAndDeviceTypes0(userId, Collections.singleton(deviceType), closeReason, manager);
        }
        return Mono.just(false);
    }

    /**
     * @implNote The method will be called definitely when a session is closed
     * no matter it's closed by the client or the server.
     * And the method will clean up sessions in both local and Redis
     */
    private Mono<Boolean> setLocalSessionOfflineByUserIdAndDeviceTypes0(
            @NotNull Long userId,
            @NotEmpty Set<@ValidDeviceType DeviceType> deviceTypes,
            @NotNull CloseReason closeReason,
            @NotNull UserSessionsManager manager) {
        try {
            AssertUtil.notNull(closeReason, "closeReason");
            AssertUtil.notNull(manager, "manager");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        // Don't close the session (connection) first and then remove the session status in Redis
        // because it will make trouble if a client logins again while the session status in Redis hasn't been removed
        return userStatusService.removeStatusByUserIdAndDeviceTypes(userId, deviceTypes)
                .doOnSuccess(ignored -> {
                    for (DeviceType deviceType : deviceTypes) {
                        UserSession session = manager.getSession(deviceType);
                        if (session == null) {
                            continue;
                        }
                        manager.setDeviceOffline(session.getDeviceType(), closeReason);
                        ByteArrayWrapper ip = session.getIp();
                        if (ip != null) {
                            sessionsByIp.computeIfPresent(ip, (key, sessions) -> sessions.remove(session)
                                    ? (sessions.isEmpty() ? null : sessions)
                                    : sessions);
                        }
                        triggerOnSessionClosedListeners(session);
                        if (sessionLocationService.isLocationEnabled()) {
                            sessionLocationService.removeUserLocation(session.getUserId(), session.getDeviceType())
                                    .subscribe(null, t -> LOGGER.error("Failed to remove the user [{}:{}] location",
                                            session.getUserId(), session.getDeviceType(), t));
                        }
                    }
                    removeSessionsManagerIfEmpty(closeReason, manager, userId);
                });
    }

    public Mono<Void> clearAllLocalSessions(@NotNull CloseReason closeReason) {
        try {
            AssertUtil.notNull(closeReason, "closeReason");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Set<Map.Entry<Long, UserSessionsManager>> entries = sessionsManagerByUserId.entrySet();
        List<Mono<Boolean>> monos = new ArrayList<>(entries.size());
        for (Map.Entry<Long, UserSessionsManager> entry : entries) {
            Long userId = entry.getKey();
            Set<DeviceType> loggedInDeviceTypes = entry.getValue().getLoggedInDeviceTypes();
            Mono<Boolean> mono = setLocalSessionOfflineByUserIdAndDeviceTypes(userId, loggedInDeviceTypes, closeReason)
                    .onErrorResume(t -> Mono.empty());
            monos.add(mono);
        }
        return Mono.whenDelayError(monos);
    }

    @Override
    public Mono<Boolean> setLocalUserOffline(Long userId, CloseReason closeReason) {
        return setLocalSessionOfflineByUserIdAndDeviceTypes(userId, DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES_SET, closeReason);
    }

    public void updateHeartbeatTimestamp(UserSession session) {
        session.setLastHeartbeatRequestTimestampMillis(System.currentTimeMillis());
    }

    public UserSession authAndUpdateHeartbeatTimestamp(long userId, @NotNull @ValidDeviceType DeviceType deviceType, int sessionId) {
        AssertUtil.notNull(deviceType, "deviceType");
        DeviceTypeUtil.validDeviceType(deviceType);
        UserSessionsManager userSessionsManager = getUserSessionsManager(userId);
        if (userSessionsManager != null) {
            UserSession session = userSessionsManager.getSession(deviceType);
            if (session != null && session.getId() == sessionId && !session.getConnection().isConnectionRecovering()) {
                session.setLastHeartbeatRequestTimestampMillis(System.currentTimeMillis());
                return session;
            }
        }
        return null;
    }

    /**
     * For the case that the client recovers from UDP to TCP/WebSocket:
     * Return the local session if the client connects to the machine that owns the existing session;
     * Return a new session and disconnect the remote session if the existing session is on a different machine.
     */
    public Mono<UserSession> tryRegisterOnlineUser(
            int version,
            @NotNull ByteArrayWrapper ip,
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            @Nullable Coordinates coordinates) {
        try {
            AssertUtil.notNull(ip, "ip");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            AssertUtil.state(userStatus != UserStatus.UNRECOGNIZED, "The user status must not be UNRECOGNIZED");
            AssertUtil.state(userStatus != UserStatus.OFFLINE, "The user status must not be OFFLINE");
            if (coordinates != null) {
                AssertUtil.inRange(coordinates.longitude(), "longitude", Coordinates.LONGITUDE_MIN, Coordinates.LONGITUDE_MAX);
                AssertUtil.inRange(coordinates.latitude(), "latitude", Coordinates.LATITUDE_MIN, Coordinates.LATITUDE_MAX);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        // Must fetch the latest status instead of the status in the cache
        return userStatusService.fetchUserSessionsStatus(userId)
                .flatMap(sessionsStatus -> {
                    // getSessionStatusFromSharedAndLocalInfo() is used to handle the following edge
                    // cases to avoid bugs when the session info in local node is inconsistent with the one in Redis.

                    // Cases: The session exists in the local node, but:
                    // 1. Though the local node works well, Redis crashes and restart,
                    // so all session info was lost in Redis, but sessions still exist indeed.
                    // 2. The local node lost the connection to Redis, which causes
                    // the local node failed to refresh the heartbeat info of users in Redis.
                    sessionsStatus = getSessionStatusFromSharedAndLocalInfo(userId, sessionsStatus);
                    // Check the current sessions status
                    UserStatus existingUserStatus = sessionsStatus.userStatus();
                    if (existingUserStatus == UserStatus.OFFLINE) {
                        return addOnlineDeviceIfAbsent(version, ip, userId, deviceType, deviceDetails, userStatus, coordinates);
                    }
                    boolean conflicts = sessionsStatus.getLoggedInDeviceTypes().contains(deviceType);
                    if (conflicts) {
                        UserSession session = getLocalUserSession(userId, deviceType);
                        boolean isDisconnectedSessionOnLocal = session != null
                                && session.getConnection() != null
                                && !session.getConnection().isConnected();
                        if (isDisconnectedSessionOnLocal) {
                            // Note that the downstream should replace the disconnected connection
                            // with the connected TCP/WebSocket connection
                            Mono<Void> updateSessionInfoMono = userStatus == null || existingUserStatus == userStatus
                                    ? Mono.empty()
                                    : userStatusService.updateOnlineUserStatusIfPresent(userId, userStatus)
                                    .then()
                                    .onErrorResume(t -> {
                                        LOGGER.error("Failed to update the online status of the user " + userId, t);
                                        return Mono.empty();
                                    });
                            if (coordinates != null) {
                                updateSessionInfoMono = updateSessionInfoMono
                                        .flatMap(unused -> sessionLocationService
                                                .upsertUserLocation(userId, deviceType, coordinates, new Date())
                                                .onErrorResume(t -> {
                                                    LOGGER.error("Failed to upsert the location of the user " + userId, t);
                                                    return Mono.empty();
                                                }));
                            }
                            return updateSessionInfoMono.thenReturn(session);
                        } else if (userSimultaneousLoginService.shouldDisconnectLoggingInDeviceIfConflicts()) {
                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE));
                        }
                    }
                    return disconnectConflictedDeviceTypes(userId, deviceType, sessionsStatus)
                            .flatMap(wasSuccessful -> wasSuccessful
                                    ? addOnlineDeviceIfAbsent(version, ip, userId, deviceType, deviceDetails, userStatus, coordinates)
                                    : Mono.error(TurmsBusinessException.get(TurmsStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE)));
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
        return userSessionsManager == null ? null : userSessionsManager.getSession(deviceType);
    }

    @Nullable
    public Queue<UserSession> getLocalUserSession(ByteArrayWrapper ip) {
        return sessionsByIp.get(ip);
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
        HashMultimap<String, DeviceType> nodeIdAndDeviceTypesMap = null;
        for (DeviceType conflictedDeviceType : conflictedDeviceTypes) {
            String nodeId = sessionsStatus.getNodeIdByDeviceType(conflictedDeviceType);
            if (nodeId != null) {
                if (nodeIdAndDeviceTypesMap == null) {
                    nodeIdAndDeviceTypesMap = HashMultimap.create(3, 3);
                }
                nodeIdAndDeviceTypesMap.put(nodeId, deviceType);
            }
        }
        if (nodeIdAndDeviceTypesMap == null) {
            return Mono.just(true);
        }
        Set<String> nodeIds = nodeIdAndDeviceTypesMap.keySet();
        List<Mono<Boolean>> disconnectionRequests = new ArrayList<>(nodeIds.size());
        for (String nodeId : nodeIds) {
            Set<DeviceType> deviceTypes = nodeIdAndDeviceTypesMap.get(nodeId);
            SetUserOfflineRequest request = new SetUserOfflineRequest(userId, deviceTypes, SessionCloseStatus.DISCONNECTED_BY_CLIENT);
            disconnectionRequests.add(node.getRpcService().requestResponse(nodeId, request)
                    .onErrorResume(ConnectionNotFound.class, t -> {
                        // The connection may not exist because there is a network problem between the local node
                        // and the target node, or the target node is dead (if it's an unknown node)
                        // without clearing its user sessions in Redis.

                        // For the first case (network problem) or we are not sure whether the target node is really dead,
                        // we keep returning the expected INTERNAL_SERVER_ERROR to client until its TTL expires.
                        if (node.getDiscoveryService().isKnownMember(nodeId)) {
                            return Mono.error(t);
                        }
                        // For the second case (dead target node), we consider the user sessions already offline,
                        // so we return true for the logging in client to log in for better user experience.
                        return Mono.just(true);
                    }));
        }
        return ReactorUtil.areAllTrue(disconnectionRequests);
    }

    public void onSessionEstablished(@NotNull UserSessionsManager userSessionsManager,
                                     @NotNull @ValidDeviceType DeviceType deviceType) {
        loggedInUsersCounter.increment();
        if (node.getSharedProperties().getGateway().getSession().isNotifyClientsOfSessionInfoAfterConnected()) {
            String serverId = turmsPropertiesManager.getLocalProperties().getGateway().getServiceDiscovery().getIdentity();
            userSessionsManager.pushSessionNotification(deviceType, serverId);
        }
    }

    private Mono<UserSession> addOnlineDeviceIfAbsent(
            int version,
            @NotNull ByteArrayWrapper ip,
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            @Nullable Coordinates coordinates) {
        // Try to update the global user status
        return userStatusService.addOnlineDeviceIfAbsent(userId, deviceType, userStatus, closeIdleSessionAfterSeconds)
                .flatMap(wasSuccessful -> {
                    if (!wasSuccessful) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE));
                    }
                    UserStatus finalUserStatus = null == userStatus ? UserStatus.AVAILABLE : userStatus;
                    UserSessionsManager manager = sessionsManagerByUserId
                            .computeIfAbsent(userId, key -> new UserSessionsManager(key, finalUserStatus));
                    UserSession session = manager.addSessionIfAbsent(version, deviceType, deviceDetails, coordinates);
                    // This should never happen
                    if (null == session) {
                        manager.setDeviceOffline(deviceType, CloseReason.get(SessionCloseStatus.DISCONNECTED_BY_OTHER_DEVICE));
                        sessionsByIp.computeIfPresent(ip, (key, sessions) -> {
                            boolean removed = sessions.removeIf(userSession ->
                                    userSession.getUserId().equals(userId)
                                            && userSession.getDeviceType().equals(deviceType));
                            return removed
                                    ? (sessions.isEmpty() ? null : sessions)
                                    : sessions;
                        });
                        session = manager.addSessionIfAbsent(version, deviceType, deviceDetails, coordinates);
                        if (null == session) {
                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SERVER_INTERNAL_ERROR));
                        }
                    }
                    UserSession finalSession = session;
                    sessionsByIp.compute(ip, (key, sessions) -> {
                        if (sessions == null) {
                            sessions = new ConcurrentLinkedQueue<>();
                        }
                        sessions.add(finalSession);
                        return sessions;
                    });
                    Date now = new Date();
                    if (null != coordinates && sessionLocationService.isLocationEnabled()) {
                        return sessionLocationService.upsertUserLocation(userId, deviceType, coordinates, now)
                                .thenReturn(session);
                    }
                    return Mono.just(session);
                });
    }

    private void removeSessionsManagerIfEmpty(@NotNull CloseReason closeReason,
                                              @NotNull UserSessionsManager manager,
                                              @NotNull Long userId) {
        if (manager.getSessionsNumber() == 0) {
            sessionsManagerByUserId.remove(userId);
        }
        pluginManager.invokeExtensionPoints(UserOnlineStatusChangeHandler.class, "goOffline",
                        handler -> handler.goOffline(manager, closeReason))
                .subscribe(null, LOGGER::error);
    }

    private UserSessionsStatus getSessionStatusFromSharedAndLocalInfo(@NotNull Long userId,
                                                                      @NotNull UserSessionsStatus sharedSessionsStatus) {
        UserSessionsManager manager = sessionsManagerByUserId.get(userId);
        if (manager == null) {
            return sharedSessionsStatus;
        }
        Map<DeviceType, String> sharedOnlineDeviceTypeAndNodeIdMap = sharedSessionsStatus.onlineDeviceTypeAndNodeIdMap();
        for (Map.Entry<DeviceType, UserSession> entry : manager.getSessionMap().entrySet()) {
            // Don't just merge two maps for convenience to avoiding creating a new map
            if (!sharedOnlineDeviceTypeAndNodeIdMap.containsKey(entry.getKey())) {
                Map<DeviceType, String> onlineDeviceTypeAndNodeIdMap = MapUtil.merge(sharedOnlineDeviceTypeAndNodeIdMap,
                        Maps.transformValues(manager.getSessionMap(), ignored -> Node.getNodeId()));
                return new UserSessionsStatus(manager.getUserStatus(), onlineDeviceTypeAndNodeIdMap);
            }
        }
        return sharedSessionsStatus;
    }

    // Listener

    public void addOnSessionClosedListeners(Consumer<UserSession> onSessionClosed) {
        onSessionClosedListeners.add(onSessionClosed);
    }

    private void triggerOnSessionClosedListeners(UserSession session) {
        for (Consumer<UserSession> onSessionClosedListener : onSessionClosedListeners) {
            try {
                onSessionClosedListener.accept(session);
            } catch (Exception e) {
                LOGGER.error("Caught an error while triggering a onSessionClosed listener", e);
            }
        }
    }

}