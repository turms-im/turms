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

package im.turms.gateway.manager;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.gateway.access.udp.UdpDispatcher;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.impl.session.SessionService;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.service.session.UserStatusService;
import io.lettuce.core.protocol.LongKeyGenerator;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Setter;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 * @implNote Strategy Candidates:
 * 1. Refresh the heartbeat info in Redis when a user sends a heartbeat request:
 * It's a disaster for performance when there are many online users
 * <p>
 * 2. Store heartbeat requests in a MPSC queue, and merge them into one Redis command
 * and send them to Redis periodically:
 * Waste memory and loss performance due to the operations on the MPSC queue
 * <p>
 * 3. (Adopted) Do not store the requests, and just iterate over the original
 * online user map to get a snapshot of the online users that need to update
 * online status (go offline or refresh heartbeat)
 */
public class HeartbeatManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatManager.class);

    private static final CloseReason HEARTBEAT_TIMEOUT = CloseReason.get(SessionCloseStatus.HEARTBEAT_TIMEOUT);

    private static final int UPDATE_HEARTBEAT_INTERVAL_MILLIS = 1000;
    private static final float UPDATE_HEARTBEAT_INTERVAL_FACTOR = UPDATE_HEARTBEAT_INTERVAL_MILLIS / 1000f;

    private final SessionService sessionService;
    private final UserStatusService userStatusService;
    private final Map<Long, UserSessionsManager> sessionsManagerByUserId;
    private final Thread workerThread;
    private int closeIdleSessionAfterSeconds;
    private int closeIdleSessionAfterMillis;
    private int expectedFractionPerSecond;
    @Setter
    private int minHeartbeatIntervalMillis;
    @Setter
    private int switchProtocolAfterMillis;

    public HeartbeatManager(SessionService sessionService,
                            UserStatusService userStatusService,
                            Map<Long, UserSessionsManager> sessionsManagerByUserId,
                            int clientHeartbeatIntervalSeconds,
                            int closeIdleSessionAfterSeconds,
                            int minHeartbeatIntervalSeconds,
                            int switchProtocolAfterSeconds) {
        this.sessionService = sessionService;
        this.userStatusService = userStatusService;
        this.sessionsManagerByUserId = sessionsManagerByUserId;
        setClientHeartbeatIntervalSeconds(clientHeartbeatIntervalSeconds);
        setCloseIdleSessionAfterSeconds(closeIdleSessionAfterSeconds);
        this.minHeartbeatIntervalMillis = minHeartbeatIntervalSeconds * 1000;
        this.switchProtocolAfterMillis = switchProtocolAfterSeconds * 1000;
        DefaultThreadFactory factory = new DefaultThreadFactory("heartbeat-update", true);
        workerThread = factory.newThread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    updateOnlineUsersTtl();
                } catch (Exception e) {
                    LOGGER.error("Failed to update the TTL of users on Redis", e);
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
        closeIdleSessionAfterMillis = closeIdleSessionAfterSeconds * 1000;
    }

    public void setClientHeartbeatIntervalSeconds(int clientHeartbeatIntervalSeconds) {
        expectedFractionPerSecond = clientHeartbeatIntervalSeconds > 0
                ? clientHeartbeatIntervalSeconds
                : 30;
    }

    public void destroy() {
        workerThread.interrupt();
    }

    private void updateOnlineUsersTtl() {
        Set<Map.Entry<Long, UserSessionsManager>> entries = sessionsManagerByUserId.entrySet();
        LongKeyGenerator userIds = collectOnlineUsersAndUpdateStatus(entries);
        userStatusService.updateOnlineUsersTtl(userIds, closeIdleSessionAfterSeconds)
                .doOnError(t -> LOGGER.error("Failed to update online users", t))
                .subscribe();
    }

    private LongKeyGenerator collectOnlineUsersAndUpdateStatus(Set<Map.Entry<Long, UserSessionsManager>> entries) {
        int onlineUserCount = entries.size();
        int expectedUserCountToRefreshPerInterval = (int) (UPDATE_HEARTBEAT_INTERVAL_FACTOR * onlineUserCount / expectedFractionPerSecond);
        Iterator<Map.Entry<Long, UserSessionsManager>> iterator = entries.iterator();
        return new LongKeyGenerator() {
            final long now = System.currentTimeMillis();

            @Override
            public int expectedSize() {
                return expectedUserCountToRefreshPerInterval;
            }

            @Override
            public long next() {
                while (iterator.hasNext()) {
                    Map.Entry<Long, UserSessionsManager> entry = iterator.next();
                    Map<DeviceType, UserSession> sessionMap = entry.getValue().getSessionMap();
                    for (Map.Entry<DeviceType, UserSession> sessionEntry : sessionMap.entrySet()) {
                        Long userId = closeOrUpdateSession(sessionEntry.getValue(), now);
                        if (userId != null) {
                            return userId;
                        }
                    }
                }
                return -1;
            }
        };
    }

    @Nullable
    private Long closeOrUpdateSession(UserSession session, long now) {
        if (!session.isOpen()) {
            return null;
        }
        int requestElapsedTime = (int) (now - session.getLastRequestTimestampMillis());
        if (requestElapsedTime > switchProtocolAfterMillis
                && session.isConnected()
                && UdpDispatcher.isEnabled()
                && session.supportsSwitchingToUdp()) {
            session.getConnection().switchToUdp();
            return null;
        }
        long lastHeartbeatUpdateTimestamp = session.getLastHeartbeatUpdateTimestampMillis();
        if (minHeartbeatIntervalMillis > 0
                && now - lastHeartbeatUpdateTimestamp < minHeartbeatIntervalMillis) {
            return null;
        }
        long lastHeartbeatRequestTimestamp = session.getLastHeartbeatRequestTimestampMillis();
        if (lastHeartbeatRequestTimestamp <= lastHeartbeatUpdateTimestamp) {
            return null;
        }
        int heartbeatElapsedTime = (int) (now - lastHeartbeatRequestTimestamp);
        if (closeIdleSessionAfterMillis > 0 && heartbeatElapsedTime > closeIdleSessionAfterMillis) {
            sessionService.setLocalSessionOfflineByUserIdAndDeviceType(
                            session.getUserId(),
                            session.getDeviceType(),
                            HEARTBEAT_TIMEOUT)
                    .onErrorResume(t -> {
                        LOGGER.error("Caught an error when disconnecting the local session: {} with the close reason: {}",
                                session, HEARTBEAT_TIMEOUT);
                        return Mono.empty();
                    })
                    .subscribe();
        }
        session.setLastHeartbeatUpdateTimestampMillis(now);
        return session.getUserId();
    }
}
