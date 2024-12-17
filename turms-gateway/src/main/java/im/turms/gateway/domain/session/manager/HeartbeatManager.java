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

package im.turms.gateway.domain.session.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;

import io.lettuce.core.protocol.LongKeyGenerator;
import lombok.Setter;
import reactor.core.publisher.Mono;

import im.turms.gateway.access.client.common.UserSession;
import im.turms.gateway.access.client.udp.UdpRequestDispatcher;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.gateway.infra.thread.ThreadNameConst;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.thread.TurmsThread;
import im.turms.server.common.infra.time.DateTimeUtil;
import im.turms.server.common.infra.time.DurationConst;

/**
 * @author James Chen
 * @implNote Strategy Candidates: 1. Refresh the heartbeat info in Redis when a user sends a
 *           heartbeat request: It is a disaster for performance when there are many online users
 *           <p>
 *           2. Store heartbeat requests in a MPSC queue, and merge them into one Redis command and
 *           send them to Redis periodically: Waste memory and loss performance due to the
 *           operations on the MPSC queue
 *           <p>
 *           3. (Adopted) Do not store the requests, and just iterate over the original online user
 *           map to get a snapshot of the online users that need to update online status (go offline
 *           or refresh heartbeat)
 */
public class HeartbeatManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatManager.class);

    private static final CloseReason HEARTBEAT_TIMEOUT =
            CloseReason.get(SessionCloseStatus.HEARTBEAT_TIMEOUT);

    private static final int UPDATE_HEARTBEAT_INTERVAL_MILLIS = 1000;
    private static final float UPDATE_HEARTBEAT_INTERVAL_FACTOR =
            UPDATE_HEARTBEAT_INTERVAL_MILLIS / 1000f;

    private final SessionService sessionService;
    private final UserStatusService userStatusService;
    private final Map<Long, UserSessionsManager> userIdToSessionsManager;
    private final TurmsThread workerThread;
    private int closeIdleSessionAfterSeconds;
    private long closeIdleSessionAfterNanos;
    private int expectedFractionPerSecond;
    @Setter
    private long minHeartbeatIntervalNanos;
    @Setter
    private long switchProtocolAfterNanos;

    public HeartbeatManager(
            SessionService sessionService,
            UserStatusService userStatusService,
            Map<Long, UserSessionsManager> userIdToSessionsManager,
            int clientHeartbeatIntervalSeconds,
            int closeIdleSessionAfterSeconds,
            int minHeartbeatIntervalSeconds,
            int switchProtocolAfterSeconds) {
        this.sessionService = sessionService;
        this.userStatusService = userStatusService;
        this.userIdToSessionsManager = userIdToSessionsManager;
        setClientHeartbeatIntervalSeconds(clientHeartbeatIntervalSeconds);
        setCloseIdleSessionAfterSeconds(closeIdleSessionAfterSeconds);
        this.minHeartbeatIntervalNanos = DateTimeUtil.secondsToNanos(minHeartbeatIntervalSeconds);
        this.switchProtocolAfterNanos = DateTimeUtil.secondsToNanos(switchProtocolAfterSeconds);
        workerThread = TurmsThread.create(ThreadNameConst.CLIENT_HEARTBEAT_REFRESHER, true, () -> {
            Thread thread = Thread.currentThread();
            while (!thread.isInterrupted()) {
                try {
                    updateOnlineUsersTtl();
                } catch (Exception e) {
                    if (ThrowableUtil.contains(e, InterruptedException.class)) {
                        break;
                    }
                    LOGGER.error(e);
                }
                try {
                    Thread.sleep(UPDATE_HEARTBEAT_INTERVAL_MILLIS);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        workerThread.start();
    }

    public void setCloseIdleSessionAfterSeconds(int closeIdleSessionAfterSeconds) {
        this.closeIdleSessionAfterSeconds = closeIdleSessionAfterSeconds;
        closeIdleSessionAfterNanos = DateTimeUtil.secondsToNanos(closeIdleSessionAfterSeconds);
    }

    public void setClientHeartbeatIntervalSeconds(int clientHeartbeatIntervalSeconds) {
        expectedFractionPerSecond = clientHeartbeatIntervalSeconds > 0
                ? clientHeartbeatIntervalSeconds
                : 30;
    }

    public Mono<Void> destroy() {
        return workerThread.terminate();
    }

    private void updateOnlineUsersTtl() {
        Collection<UserSessionsManager> managers = userIdToSessionsManager.values();
        if (managers.isEmpty()) {
            return;
        }
        LongKeyGenerator userIds = collectOnlineUsersAndUpdateStatus(managers);
        try {
            userStatusService.updateOnlineUsersTtl(userIds, closeIdleSessionAfterSeconds)
                    .flatMap(nonexistentUserIds -> {
                        if (nonexistentUserIds.isEmpty()) {
                            return Mono.empty();
                        }
                        List<Mono<Integer>> closeLocalSessions =
                                new ArrayList<>(nonexistentUserIds.size());
                        for (Long nonexistentUserId : nonexistentUserIds) {
                            closeLocalSessions.add(sessionService
                                    .closeLocalSession(nonexistentUserId,
                                            SessionCloseStatus.DISCONNECTED_BY_OTHER_DEVICE)
                                    .onErrorMap(t -> new RuntimeException(
                                            "Caught an error while closing the session of the user: "
                                                    + nonexistentUserId)));
                        }
                        return Mono.whenDelayError(closeLocalSessions);
                    })
                    .block(DurationConst.ONE_MINUTE);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Caught an error while refreshing online users' sessions in Redis",
                    e);
        }
    }

    private LongKeyGenerator collectOnlineUsersAndUpdateStatus(
            Collection<UserSessionsManager> managers) {
        int onlineUserCount = managers.size();
        int estimatedUserCountToRefreshPerInterval = (int) (UPDATE_HEARTBEAT_INTERVAL_FACTOR
                * onlineUserCount / expectedFractionPerSecond);
        Iterator<UserSessionsManager> iterator = managers.iterator();
        return new LongKeyGenerator() {
            final long now = System.nanoTime();

            @Override
            public int estimatedSize() {
                return estimatedUserCountToRefreshPerInterval;
            }

            @Override
            public long next() {
                while (iterator.hasNext()) {
                    Long userId = null;
                    for (UserSession session : iterator.next()
                            .getDeviceTypeToSession()
                            .values()) {
                        Long currentUserId = closeOrUpdateSession(session, now);
                        if (currentUserId != null && userId == null) {
                            userId = currentUserId;
                        }
                    }
                    if (userId != null) {
                        return userId;
                    }
                }
                return -1;
            }
        };
    }

    @Nullable
    private Long closeOrUpdateSession(UserSession session, long nowNanos) {
        if (!session.isOpen()) {
            return null;
        }
        if (UdpRequestDispatcher.isEnabled()
                && session.supportsSwitchingToUdp()
                && session.isConnected()
                && nowNanos - session.getLastRequestTimestampNanos() > switchProtocolAfterNanos) {
            session.getConnection()
                    .switchToUdp();
            return null;
        }
        // Limit the frequency of sending heartbeat requests to Redis
        long lastHeartbeatUpdateTimestampNanos = session.getLastHeartbeatUpdateTimestampNanos();
        long localMinHeartbeatIntervalNanos = minHeartbeatIntervalNanos;
        if (localMinHeartbeatIntervalNanos > 0
                && nowNanos - lastHeartbeatUpdateTimestampNanos < localMinHeartbeatIntervalNanos) {
            return null;
        }
        // Only sends heartbeat requests to Redis if the client has
        // sent any request to the local node after the last heartbeat update request
        long lastHeartbeatRequestTimestampNanos =
                Math.max(session.getLastHeartbeatRequestTimestampNanos(),
                        session.getLastRequestTimestampNanos());
        if (lastHeartbeatRequestTimestampNanos <= lastHeartbeatUpdateTimestampNanos) {
            return null;
        }
        long localCloseIdleSessionAfterNanos = closeIdleSessionAfterNanos;
        if (localCloseIdleSessionAfterNanos > 0
                && nowNanos
                        - lastHeartbeatRequestTimestampNanos > localCloseIdleSessionAfterNanos) {
            sessionService
                    .closeLocalSession(session.getUserId(),
                            session.getDeviceType(),
                            HEARTBEAT_TIMEOUT)
                    .subscribe(null,
                            t -> LOGGER.error(
                                    "Caught an error while closing the local user session ({}) with the close reason: {}",
                                    session,
                                    HEARTBEAT_TIMEOUT));
            return null;
        }
        session.setLastHeartbeatUpdateTimestampNanos(nowNanos);
        return session.getUserId();
    }
}