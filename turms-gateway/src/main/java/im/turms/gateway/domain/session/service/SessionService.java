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

package im.turms.gateway.domain.session.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import im.turms.gateway.access.client.common.UserSession;
import im.turms.gateway.domain.observation.service.MetricsService;
import im.turms.gateway.domain.session.manager.HeartbeatManager;
import im.turms.gateway.domain.session.manager.UserSessionsManager;
import im.turms.gateway.infra.plugin.extension.UserOnlineStatusChangeHandler;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.util.DeviceTypeUtil;
import im.turms.server.common.domain.location.bo.Location;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.session.bo.UserSessionInfo;
import im.turms.server.common.domain.session.bo.UserSessionsInfo;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.rpc.SetUserOfflineRequest;
import im.turms.server.common.domain.session.service.ISessionService;
import im.turms.server.common.domain.session.service.SessionLocationService;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.rpc.exception.ConnectionNotFound;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.lang.ByteArrayWrapper;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.gateway.session.DeviceDetailsItemProperties;
import im.turms.server.common.infra.property.env.gateway.session.DeviceDetailsProperties;
import im.turms.server.common.infra.property.env.gateway.session.SessionProperties;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.reactor.PublisherUtil;
import im.turms.server.common.infra.validation.ValidDeviceType;
import im.turms.server.common.infra.validation.Validator;

import static im.turms.gateway.infra.metrics.MetricNameConst.LOGGED_IN_USERS_COUNTER;
import static im.turms.gateway.infra.metrics.MetricNameConst.ONLINE_USERS_GAUGE;

/**
 * @author James Chen
 */
@Service
public class SessionService implements ISessionService, SessionIdentityAccessManagementSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);

    private static final Method GO_OFFLINE_METHOD;
    private static final Method GO_ONLINE_METHOD;

    private final Node node;
    private final HeartbeatManager heartbeatManager;
    private final PluginManager pluginManager;

    @Delegate
    private final SessionIdentityAccessManager sessionAuthenticationManager;

    private final SessionLocationService sessionLocationService;
    private final UserStatusService userStatusService;
    private final UserSimultaneousLoginService userSimultaneousLoginService;

    private final ConcurrentHashMap<Long, UserSessionsManager> userIdToSessionsManager;
    /**
     * We don't use "NonBlockingHashMapLong" because we need to support IPv6 addresses, which takes
     * 16 bytes per address. So we can only eliminate unnecessary objects after Valhalla publish
     * value objects.
     */
    private final ConcurrentHashMap<ByteArrayWrapper, ConcurrentLinkedQueue<UserSession>> ipToSessions;

    private final List<Consumer<UserSession>> onSessionClosedListeners = new LinkedList<>();

    private final Counter loggedInUsersCounter;

    private int closeIdleSessionAfterSeconds;
    private boolean notifyClientsOfSessionInfoAfterConnected;
    private List<DeviceDetailsItemProperties> detailsItem;
    private String serverId;

    static {
        try {
            GO_OFFLINE_METHOD = UserOnlineStatusChangeHandler.class
                    .getDeclaredMethod("goOffline", UserSessionsManager.class, CloseReason.class);
            GO_ONLINE_METHOD = UserOnlineStatusChangeHandler.class
                    .getDeclaredMethod("goOnline", UserSessionsManager.class, UserSession.class);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleInternalChangeException(e);
        }
    }

    public SessionService(
            Node node,
            TurmsApplicationContext context,
            TurmsPropertiesManager propertiesManager,
            PluginManager pluginManager,
            SessionLocationService sessionLocationService,
            UserService userService,
            UserStatusService userStatusService,
            UserSimultaneousLoginService userSimultaneousLoginService,
            MetricsService metricsService) {
        this.node = node;
        this.sessionLocationService = sessionLocationService;
        this.pluginManager = pluginManager;
        this.sessionAuthenticationManager =
                new SessionIdentityAccessManager(propertiesManager, pluginManager, userService);
        this.userStatusService = userStatusService;
        this.userSimultaneousLoginService = userSimultaneousLoginService;
        userIdToSessionsManager = new ConcurrentHashMap<>(4096);
        ipToSessions = new ConcurrentHashMap<>(4096);

        updateGlobalProperties(propertiesManager.getGlobalProperties());
        updateLocalProperties(propertiesManager.getLocalProperties());

        SessionProperties sessionProperties = propertiesManager.getGlobalProperties()
                .getGateway()
                .getSession();
        heartbeatManager = new HeartbeatManager(
                this,
                userStatusService,
                userIdToSessionsManager,
                sessionProperties.getClientHeartbeatIntervalSeconds(),
                closeIdleSessionAfterSeconds,
                sessionProperties.getMinHeartbeatIntervalSeconds(),
                sessionProperties.getSwitchProtocolAfterSeconds());

        propertiesManager.addGlobalPropertiesChangeListener(newProperties -> {
            updateGlobalProperties(newProperties);
            SessionProperties newSessionProperties = newProperties.getGateway()
                    .getSession();
            heartbeatManager.setClientHeartbeatIntervalSeconds(
                    newSessionProperties.getClientHeartbeatIntervalSeconds());
            heartbeatManager.setCloseIdleSessionAfterSeconds(
                    newSessionProperties.getCloseIdleSessionAfterSeconds());
            heartbeatManager.setMinHeartbeatIntervalMillis(
                    newSessionProperties.getMinHeartbeatIntervalSeconds() * 1000);
            heartbeatManager.setSwitchProtocolAfterMillis(
                    newSessionProperties.getSwitchProtocolAfterSeconds() * 1000);
        });
        propertiesManager.addLocalPropertiesChangeListener(this::updateLocalProperties);

        MeterRegistry registry = metricsService.getRegistry();
        loggedInUsersCounter = registry.counter(LOGGED_IN_USERS_COUNTER);
        registry.gaugeMapSize(ONLINE_USERS_GAUGE, Tags.empty(), userIdToSessionsManager);

        context.addShutdownHook(JobShutdownOrder.CLOSE_SESSIONS, this::destroy);
    }

    private void updateGlobalProperties(TurmsProperties properties) {
        SessionProperties sessionProperties = properties.getGateway()
                .getSession();
        closeIdleSessionAfterSeconds = sessionProperties.getCloseIdleSessionAfterSeconds();
        notifyClientsOfSessionInfoAfterConnected =
                sessionProperties.isNotifyClientsOfSessionInfoAfterConnected();
        DeviceDetailsProperties detailsProperties = sessionProperties.getDeviceDetails();
        detailsItem = detailsProperties.getItems();
    }

    private void updateLocalProperties(TurmsProperties properties) {
        serverId = properties.getGateway()
                .getServiceDiscovery()
                .getIdentity();
    }

    public Mono<Void> destroy(long timeoutMillis) {
        heartbeatManager.destroy(timeoutMillis);
        CloseReason closeReason = CloseReason.get(SessionCloseStatus.SERVER_CLOSED);
        return closeAllLocalSessions(closeReason).onErrorMap(
                t -> new RuntimeException("Caught an error while closing local sessions", t))
                .then();
    }

    public void handleHeartbeatUpdateRequest(UserSession session) {
        session.setLastHeartbeatRequestTimestampMillis(System.currentTimeMillis());
    }

    public Mono<UserSession> handleLoginRequest(
            int version,
            @NotNull ByteArrayWrapper ip,
            @NotNull Long userId,
            @Nullable String password,
            @NotNull DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            @Nullable Location location,
            @Nullable String ipStr) {
        if (version != 1) {
            return Mono.error(ResponseException.get(ResponseStatusCode.UNSUPPORTED_CLIENT_VERSION,
                    "Supported client versions are: [1], but got: "
                            + version));
        }
        if (userSimultaneousLoginService.isForbiddenDeviceType(deviceType)) {
            return Mono.error(
                    ResponseException.get(ResponseStatusCode.LOGIN_FROM_FORBIDDEN_DEVICE_TYPE));
        }
        return verifyAndGrant(version,
                userId,
                password,
                deviceType,
                deviceDetails,
                userStatus,
                location,
                ipStr).flatMap(permissionInfo -> {
                    ResponseStatusCode statusCode = permissionInfo.authenticationCode();
                    return statusCode == ResponseStatusCode.OK
                            ? tryRegisterOnlineUser(version,
                                    permissionInfo.permissions(),
                                    ip,
                                    userId,
                                    deviceType,
                                    deviceDetails,
                                    userStatus,
                                    location)
                            : Mono.error(ResponseException.get(statusCode));
                });
    }

    /**
     * @return closed session count
     */
    public Mono<Integer> closeLocalSessions(
            @NotNull List<byte[]> ips,
            @NotNull CloseReason closeReason) {
        try {
            Validator.notNull(ips, "ips");
            Validator.notNull(closeReason, "closeReason");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (ips.isEmpty()) {
            return PublisherPool.INT_ZERO;
        }
        AtomicInteger sessionCount = new AtomicInteger();
        List<Mono<Integer>> monos = new ArrayList<>(ips.size());
        for (byte[] ip : ips) {
            Mono<Integer> mono =
                    closeLocalSessions(ip, closeReason).doOnNext(sessionCount::addAndGet);
            monos.add(mono);
        }
        return Mono.whenDelayError(monos)
                .then(Mono.fromCallable(sessionCount::get));
    }

    /**
     * @return closed session count
     */
    public Mono<Integer> closeLocalSessions(@NotNull byte[] ip, @NotNull CloseReason closeReason) {
        try {
            Validator.notNull(ip, "ip");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Queue<UserSession> sessions = ipToSessions.get(new ByteArrayWrapper(ip));
        Iterator<UserSession> iterator = sessions.iterator();
        if (!iterator.hasNext()) {
            return PublisherPool.INT_ZERO;
        }
        Mono<Integer> first = closeLocalSession(iterator.next()
                .getUserId(), DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES_SET, closeReason);
        // Fast path
        if (!iterator.hasNext()) {
            return first;
        }
        // Slow path
        AtomicInteger sessionCount = new AtomicInteger();
        // Use ArrayList instead of LinkedList because it is heavy
        List<Mono<Integer>> monos = new ArrayList<>(4);
        monos.add(first.doOnNext(sessionCount::addAndGet));
        monos.add(closeLocalSession(iterator.next()
                .getUserId(), DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES_SET, closeReason)
                .doOnNext(sessionCount::addAndGet));
        while (iterator.hasNext()) {
            monos.add(closeLocalSession(iterator.next()
                    .getUserId(), DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES_SET, closeReason)
                    .doOnNext(sessionCount::addAndGet));
        }
        return Mono.whenDelayError(monos)
                .then(Mono.fromCallable(sessionCount::get));
    }

    /**
     * @return closed session count
     */
    public Mono<Integer> closeLocalSession(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            @NotNull SessionCloseStatus closeStatus) {
        try {
            Validator.notNull(deviceType, "deviceType");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return closeLocalSession(userId,
                Collections.singleton(deviceType),
                CloseReason.get(closeStatus));
    }

    /**
     * @return closed session count
     */
    public Mono<Integer> closeLocalSession(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            @NotNull CloseReason closeReason) {
        try {
            Validator.notNull(deviceType, "deviceType");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return closeLocalSession(userId, Collections.singleton(deviceType), closeReason);
    }

    /**
     * @return closed session count
     */
    @Override
    public Mono<Integer> closeLocalSession(
            @NotNull Long userId,
            @NotEmpty Set<@ValidDeviceType DeviceType> deviceTypes,
            @NotNull CloseReason closeReason) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        UserSessionsManager manager = getUserSessionsManager(userId);
        if (manager == null) {
            return PublisherPool.INT_ZERO;
        }
        return closeLocalSessions(userId, deviceTypes, closeReason, manager);
    }

    /**
     * @return closed session count
     */
    public Mono<Integer> closeLocalSessions(
            @NotNull Set<Long> userIds,
            @NotNull CloseReason closeReason) {
        try {
            Validator.notNull(userIds, "userIds");
            Validator.notNull(closeReason, "closeReason");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (userIds.isEmpty()) {
            return PublisherPool.INT_ZERO;
        }
        AtomicInteger sessionCount = new AtomicInteger();
        List<Mono<Integer>> monos = new ArrayList<>(userIds.size());
        for (Long userId : userIds) {
            Mono<Integer> mono =
                    closeLocalSession(userId, closeReason).doOnNext(sessionCount::addAndGet);
            monos.add(mono);
        }
        return Mono.whenDelayError(monos)
                .then(Mono.fromCallable(sessionCount::get));
    }

    /**
     * @return closed session count
     */
    public Mono<Integer> authAndCloseLocalSession(
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @NotNull CloseReason closeReason,
            int sessionId) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            Validator.notNull(closeReason, "closeReason");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        UserSessionsManager manager = getUserSessionsManager(userId);
        if (manager == null) {
            return PublisherPool.INT_ZERO;
        }
        UserSession session = manager.getSession(deviceType);
        if (session != null && session.getId() == sessionId) {
            return closeLocalSessions(userId,
                    Collections.singleton(deviceType),
                    closeReason,
                    manager);
        }
        return PublisherPool.INT_ZERO;
    }

    /**
     * @return closed session count
     */
    public Mono<Integer> closeAllLocalSessions(@NotNull CloseReason closeReason) {
        try {
            Validator.notNull(closeReason, "closeReason");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Set<Map.Entry<Long, UserSessionsManager>> entries = userIdToSessionsManager.entrySet();
        List<Mono<Integer>> monos = new ArrayList<>(entries.size());
        AtomicInteger sessionCount = new AtomicInteger(0);
        for (Map.Entry<Long, UserSessionsManager> entry : entries) {
            Long userId = entry.getKey();
            Set<DeviceType> loggedInDeviceTypes = entry.getValue()
                    .getLoggedInDeviceTypes();
            Mono<Integer> mono = closeLocalSession(userId, loggedInDeviceTypes, closeReason)
                    .doOnNext(sessionCount::addAndGet);
            monos.add(mono);
        }
        return Mono.whenDelayError(monos)
                .then(Mono.fromCallable(sessionCount::get));
    }

    /**
     * @return closed session count
     */
    @Override
    public Mono<Integer> closeLocalSession(Long userId, CloseReason closeReason) {
        return closeLocalSession(userId,
                DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES_SET,
                closeReason);
    }

    /**
     * @return closed session count
     * @implNote The method will be called definitely when a session is closed no matter it is
     *           closed by the client or the server, and the method will clean up sessions in both
     *           local and Redis.
     */
    private Mono<Integer> closeLocalSessions(
            @NotNull Long userId,
            @NotEmpty Set<@ValidDeviceType DeviceType> deviceTypes,
            @NotNull CloseReason closeReason,
            @NotNull UserSessionsManager manager) {
        try {
            Validator.notNull(closeReason, "closeReason");
            Validator.notNull(manager, "manager");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        // Don't close the session (connection) first and then remove the session status in Redis
        // because it will make trouble if a client logins again while the session status in Redis
        // hasn't been removed
        return userStatusService.removeStatusByUserIdAndDeviceTypes(userId, deviceTypes)
                .map(ignored -> {
                    int sessionCount = 0;
                    for (DeviceType deviceType : deviceTypes) {
                        UserSession session = manager.getSession(deviceType);
                        if (session == null) {
                            continue;
                        }
                        boolean wasSessionOpen =
                                manager.closeSession(session.getDeviceType(), closeReason);
                        ByteArrayWrapper ip = session.getIp();
                        if (ip != null) {
                            ipToSessions.computeIfPresent(ip,
                                    (key, sessions) -> sessions.remove(session)
                                            ? (sessions.isEmpty()
                                                    ? null
                                                    : sessions)
                                            : sessions);
                        }
                        if (sessionLocationService.isLocationEnabled()) {
                            sessionLocationService
                                    .removeUserLocation(session.getUserId(),
                                            session.getDeviceType())
                                    .subscribe(null,
                                            t -> LOGGER.error(
                                                    "Failed to remove the location of the user session: {user={}, device={}}",
                                                    session.getUserId(),
                                                    session.getDeviceType(),
                                                    t));
                        }
                        if (wasSessionOpen) {
                            sessionCount++;
                            notifyOnSessionClosedListeners(session);
                        }
                    }
                    removeSessionsManagerIfEmpty(closeReason, manager, userId);
                    return sessionCount;
                });
    }

    @Override
    public List<UserSessionsInfo> getSessions(Set<Long> userIds) {
        List<UserSessionsInfo> sessions = new ArrayList<>(userIds.size());
        for (Long userId : userIds) {
            sessions.add(getUserSessions(userId));
        }
        return sessions;
    }

    private UserSessionsInfo getUserSessions(Long userId) {
        UserSessionsManager manager = userIdToSessionsManager.get(userId);
        if (manager == null) {
            return new UserSessionsInfo(userId, UserStatus.OFFLINE, Collections.emptyList());
        }
        Collection<UserSession> sessions = manager.getDeviceTypeToSession()
                .values();
        int size = sessions.size();
        if (size == 0) {
            return new UserSessionsInfo(userId, UserStatus.OFFLINE, Collections.emptyList());
        }
        ArrayList<UserSessionInfo> sessionInfos = new ArrayList<>(size);
        for (UserSession session : sessions) {
            ByteArrayWrapper ip = session.getIp();
            sessionInfos.add(new UserSessionInfo(
                    session.getId(),
                    session.getVersion(),
                    session.getDeviceType(),
                    session.getDeviceDetails(),
                    session.getLoginDate(),
                    session.getLoginLocation(),
                    new Date(session.getLastHeartbeatRequestTimestampMillis()),
                    new Date(session.getLastRequestTimestampMillis()),
                    session.isSessionOpen(),
                    ip == null
                            ? null
                            : ip.getBytes(),
                    null));
        }
        return new UserSessionsInfo(userId, manager.getUserStatus(), sessionInfos);
    }

    @Nullable
    public UserSession authAndUpdateHeartbeatTimestamp(
            long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            int sessionId) {
        Validator.notNull(deviceType, "deviceType");
        DeviceTypeUtil.validDeviceType(deviceType);
        UserSessionsManager userSessionsManager = getUserSessionsManager(userId);
        if (userSessionsManager != null) {
            UserSession session = userSessionsManager.getSession(deviceType);
            if (session != null
                    && session.getId() == sessionId
                    && !session.getConnection()
                            .isConnectionRecovering()) {
                session.setLastHeartbeatRequestTimestampMillis(System.currentTimeMillis());
                return session;
            }
        }
        return null;
    }

    /**
     * For the case that the client recovers from UDP to TCP/WebSocket: Return the local session if
     * the client connects to the machine that owns the existing session; Return a new session and
     * disconnect the remote session if the existing session is on a different machine.
     */
    public Mono<UserSession> tryRegisterOnlineUser(
            int version,
            @NotNull Set<TurmsRequest.KindCase> permissions,
            @NotNull ByteArrayWrapper ip,
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            @Nullable Location location) {
        try {
            Validator.notNull(ip, "ip");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            Validator.notEquals(userStatus,
                    UserStatus.UNRECOGNIZED,
                    "The user status must not be UNRECOGNIZED");
            Validator.notEquals(userStatus,
                    UserStatus.OFFLINE,
                    "The user status must not be OFFLINE");
            if (location != null) {
                Validator.inRange(location.longitude(),
                        "longitude",
                        Location.LONGITUDE_MIN,
                        Location.LONGITUDE_MAX);
                Validator.inRange(location.latitude(),
                        "latitude",
                        Location.LATITUDE_MIN,
                        Location.LATITUDE_MAX);
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        // Must fetch the latest status instead of the status in the cache
        return userStatusService.fetchUserSessionsStatus(userId)
                .flatMap(sessionsStatus -> {
                    // getSessionStatusFromSharedAndLocalInfo() is used to handle the following edge
                    // cases to avoid bugs when the session info in local node is inconsistent with
                    // the one
                    // in Redis.

                    // Cases: The session exists in the local node, but:
                    // 1. Though the local node works well, Redis crashes and restart,
                    // so all session info was lost in Redis, but sessions still exist indeed.
                    // 2. The local node lost the connection to Redis, which causes
                    // the local node failed to refresh the heartbeat info of users in Redis.
                    sessionsStatus = getSessionStatusFromSharedAndLocalInfo(userId, sessionsStatus);
                    // Check the current sessions status
                    UserStatus existingUserStatus = sessionsStatus.userStatus();
                    if (existingUserStatus == UserStatus.OFFLINE) {
                        return addOnlineDeviceIfAbsent(version,
                                permissions,
                                ip,
                                userId,
                                deviceType,
                                deviceDetails,
                                userStatus,
                                location);
                    }
                    boolean conflicts = sessionsStatus.getLoggedInDeviceTypes()
                            .contains(deviceType);
                    if (conflicts) {
                        UserSession session = getLocalUserSession(userId, deviceType);
                        boolean isClosedSessionOnLocal = session != null
                                && session.getConnection() != null
                                && !session.getConnection()
                                        .isConnected();
                        if (isClosedSessionOnLocal) {
                            // Note that the downstream should replace the disconnected connection
                            // with the connected TCP/WebSocket connection
                            Mono<Void> updateSessionInfoMono =
                                    userStatus == null || existingUserStatus == userStatus
                                            ? Mono.empty()
                                            : userStatusService
                                                    .updateOnlineUserStatusIfPresent(userId,
                                                            userStatus)
                                                    .then()
                                                    .onErrorResume(t -> {
                                                        LOGGER.error(
                                                                "Failed to update the online status of the user: "
                                                                        + userId,
                                                                t);
                                                        return Mono.empty();
                                                    });
                            if (location != null) {
                                updateSessionInfoMono = updateSessionInfoMono
                                        .flatMap(unused -> sessionLocationService
                                                .upsertUserLocation(userId,
                                                        deviceType,
                                                        new Date(),
                                                        location.longitude(),
                                                        location.latitude())
                                                .onErrorResume(t -> {
                                                    LOGGER.error(
                                                            "Failed to upsert the location of the user session: {user={}, device={}}",
                                                            session.getUserId(),
                                                            session.getDeviceType(),
                                                            t);
                                                    return Mono.empty();
                                                }));
                            }
                            return updateSessionInfoMono.thenReturn(session);
                        } else if (userSimultaneousLoginService
                                .shouldDisconnectLoggingInDeviceIfConflicts()) {
                            return Mono.error(ResponseException.get(
                                    ResponseStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE));
                        }
                    }
                    return closeSessionsWithConflictedDeviceTypes(userId,
                            deviceType,
                            sessionsStatus).flatMap(
                                    wasSuccessful -> wasSuccessful
                                            ? addOnlineDeviceIfAbsent(version,
                                                    permissions,
                                                    ip,
                                                    userId,
                                                    deviceType,
                                                    deviceDetails,
                                                    userStatus,
                                                    location)
                                            : Mono.error(ResponseException.get(
                                                    ResponseStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE)));
                });
    }

    @Nullable
    public UserSessionsManager getUserSessionsManager(@NotNull Long userId) {
        Validator.notNull(userId, "userId");
        return userIdToSessionsManager.get(userId);
    }

    @Nullable
    public UserSession getLocalUserSession(@NotNull Long userId, @NotNull DeviceType deviceType) {
        Validator.notNull(userId, "userId");
        Validator.notNull(deviceType, "deviceType");
        UserSessionsManager userSessionsManager = userIdToSessionsManager.get(userId);
        return userSessionsManager == null
                ? null
                : userSessionsManager.getSession(deviceType);
    }

    @Nullable
    public Queue<UserSession> getLocalUserSession(ByteArrayWrapper ip) {
        return ipToSessions.get(ip);
    }

    public int countLocalOnlineUsers() {
        return userIdToSessionsManager.size();
    }

    private Mono<Boolean> closeSessionsWithConflictedDeviceTypes(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            @NotNull UserSessionsStatus sessionsStatus) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            Validator.notNull(sessionsStatus, "sessionsStatus");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Set<DeviceType> conflictedDeviceTypes =
                userSimultaneousLoginService.getConflictedDeviceTypes(deviceType);
        Map<String, Set<DeviceType>> nodeIdToDeviceTypes = null;
        for (DeviceType conflictedDeviceType : conflictedDeviceTypes) {
            String nodeId = sessionsStatus.getNodeIdByDeviceType(conflictedDeviceType);
            if (nodeId != null) {
                if (nodeIdToDeviceTypes == null) {
                    nodeIdToDeviceTypes = CollectionUtil.newMapWithExpectedSize(3);
                }
                nodeIdToDeviceTypes
                        .computeIfAbsent(nodeId, key -> CollectionUtil.newSetWithExpectedSize(3))
                        .add(deviceType);
            }
        }
        if (nodeIdToDeviceTypes == null) {
            return PublisherPool.TRUE;
        }
        Set<String> nodeIds = nodeIdToDeviceTypes.keySet();
        List<Mono<Boolean>> requests = new ArrayList<>(nodeIds.size());
        for (String nodeId : nodeIds) {
            Set<DeviceType> deviceTypes = nodeIdToDeviceTypes.get(nodeId);
            SetUserOfflineRequest request = new SetUserOfflineRequest(
                    userId,
                    deviceTypes,
                    SessionCloseStatus.DISCONNECTED_BY_CLIENT);
            requests.add(node.getRpcService()
                    .requestResponse(nodeId, request)
                    .onErrorResume(ConnectionNotFound.class, t -> {
                        // The connection may not exist if a network error occurred between the
                        // local node
                        // and the target node, or the target node is dead (if it is an unknown
                        // node)
                        // without clearing its user sessions in Redis.

                        // For the first case (network problem), or we are not sure whether the
                        // target node is dead,
                        // we keep returning the expected INTERNAL_SERVER_ERROR to the client until
                        // its TTL expires.
                        if (node.getDiscoveryService()
                                .isKnownMember(nodeId)) {
                            return Mono.error(t);
                        }
                        // For the second case (dead target node), we consider the user sessions
                        // already offline,
                        // so we return true for the client to log in for better user experience.
                        return PublisherPool.TRUE;
                    }));
        }
        return PublisherUtil.areAllTrue(requests);
    }

    public void onSessionEstablished(
            @NotNull UserSessionsManager userSessionsManager,
            @NotNull @ValidDeviceType DeviceType deviceType) {
        loggedInUsersCounter.increment();
        if (notifyClientsOfSessionInfoAfterConnected) {
            userSessionsManager.pushSessionNotification(deviceType, serverId);
        }
    }

    private Mono<UserSession> addOnlineDeviceIfAbsent(
            int version,
            @NotNull Set<TurmsRequest.KindCase> permissions,
            @NotNull ByteArrayWrapper ip,
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            @Nullable Location location) {
        // TODO: Add extension point https://github.com/turms-im/turms/issues/1187
        Map<String, String> details;
        List<DeviceDetailsItemProperties> localDetailsItem = detailsItem;
        if (localDetailsItem.isEmpty() || CollectionUtil.isEmpty(deviceDetails)) {
            details = null;
        } else {
            details = CollectionUtil.newMapWithExpectedSize(localDetailsItem.size());
            for (DeviceDetailsItemProperties itemProperties : localDetailsItem) {
                String value = deviceDetails.get(itemProperties.getFieldName());
                if (value != null) {
                    details.put(itemProperties.getRedisFieldName(), value);
                }
            }
        }
        // Try to update the global user status
        return userStatusService
                .addOnlineDeviceIfAbsent(userId,
                        deviceType,
                        details,
                        userStatus,
                        closeIdleSessionAfterSeconds)
                .flatMap(wasSuccessful -> {
                    if (!wasSuccessful) {
                        return Mono.error(ResponseException
                                .get(ResponseStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE));
                    }
                    UserStatus finalUserStatus = null == userStatus
                            ? UserStatus.AVAILABLE
                            : userStatus;
                    UserSessionsManager manager = userIdToSessionsManager.computeIfAbsent(userId,
                            key -> new UserSessionsManager(key, finalUserStatus));
                    UserSession session = manager.addSessionIfAbsent(version,
                            permissions,
                            deviceType,
                            deviceDetails,
                            location);
                    // This should never happen
                    if (null == session) {
                        manager.closeSession(deviceType,
                                CloseReason.get(SessionCloseStatus.DISCONNECTED_BY_OTHER_DEVICE));
                        ipToSessions.computeIfPresent(ip, (key, sessions) -> {
                            boolean removed =
                                    sessions.removeIf(userSession -> userSession.getUserId()
                                            .equals(userId)
                                            && userSession.getDeviceType()
                                                    .equals(deviceType));
                            return removed
                                    ? (sessions.isEmpty()
                                            ? null
                                            : sessions)
                                    : sessions;
                        });
                        session = manager.addSessionIfAbsent(version,
                                permissions,
                                deviceType,
                                deviceDetails,
                                location);
                        if (null == session) {
                            return Mono.error(ResponseException
                                    .get(ResponseStatusCode.SERVER_INTERNAL_ERROR));
                        }
                    }
                    ipToSessions.computeIfAbsent(ip, key -> new ConcurrentLinkedQueue<>())
                            .add(session);
                    Date now = new Date();
                    if (null != location && sessionLocationService.isLocationEnabled()) {
                        return sessionLocationService
                                .upsertUserLocation(userId,
                                        deviceType,
                                        now,
                                        location.longitude(),
                                        location.latitude())
                                .thenReturn(session);
                    }
                    return Mono.just(session);
                });
    }

    private void removeSessionsManagerIfEmpty(
            @NotNull CloseReason closeReason,
            @NotNull UserSessionsManager manager,
            @NotNull Long userId) {
        if (manager.countSessions() == 0) {
            userIdToSessionsManager.remove(userId);
        }
        pluginManager
                .invokeExtensionPointsSimultaneously(UserOnlineStatusChangeHandler.class,
                        GO_OFFLINE_METHOD,
                        handler -> handler.goOffline(manager, closeReason))
                .subscribe(null, LOGGER::error);
    }

    private UserSessionsStatus getSessionStatusFromSharedAndLocalInfo(
            @NotNull Long userId,
            @NotNull UserSessionsStatus sharedSessionsStatus) {
        UserSessionsManager manager = userIdToSessionsManager.get(userId);
        if (manager == null) {
            return sharedSessionsStatus;
        }
        Map<DeviceType, String> sharedOnlineDeviceTypeToNodeId =
                sharedSessionsStatus.deviceTypeToNodeId();
        for (DeviceType deviceType : manager.getDeviceTypeToSession()
                .keySet()) {
            // Don't just merge two maps for convenience to avoiding creating a new map
            if (!sharedOnlineDeviceTypeToNodeId.containsKey(deviceType)) {
                Map<DeviceType, String> onlineDeviceTypeToNodeId =
                        CollectionUtil.merge(sharedOnlineDeviceTypeToNodeId,
                                CollectionUtil.transformValues(manager.getDeviceTypeToSession(),
                                        Node.getNodeId()));
                return new UserSessionsStatus(
                        userId,
                        manager.getUserStatus(),
                        onlineDeviceTypeToNodeId);
            }
        }
        return sharedSessionsStatus;
    }

    // Listener

    public void addOnSessionClosedListeners(Consumer<UserSession> onSessionClosed) {
        onSessionClosedListeners.add(onSessionClosed);
    }

    private void notifyOnSessionClosedListeners(UserSession session) {
        for (Consumer<UserSession> onSessionClosedListener : onSessionClosedListeners) {
            try {
                onSessionClosedListener.accept(session);
            } catch (Exception e) {
                LOGGER.error("Caught an error while notifying the onSessionClosed listener: "
                        + onSessionClosedListener.getClass()
                                .getName(),
                        e);
            }
        }
    }

    // Plugin
    public Mono<Void> invokeGoOnlineHandlers(
            @NotNull UserSessionsManager userSessionsManager,
            @NotNull UserSession userSession) {
        return pluginManager.invokeExtensionPointsSimultaneously(
                UserOnlineStatusChangeHandler.class,
                GO_ONLINE_METHOD,
                handler -> handler.goOnline(userSessionsManager, userSession));
    }

}