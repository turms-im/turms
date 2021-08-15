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
import im.turms.gateway.service.impl.SessionService;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.service.session.UserStatusService;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
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
@Log4j2
public class HeartbeatManager {

    private static final int UPDATE_HEARTBEAT_INTERVAL_MILLIS = 500;
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
                    log.error(e);
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
        long now = System.currentTimeMillis();
        List<Long> users = collectOnlineUsersAndUpdateStatus(entries, now);
        userStatusService.updateOnlineUsersTtl(users, closeIdleSessionAfterSeconds)
                .subscribe();
    }

    private List<Long> collectOnlineUsersAndUpdateStatus(Set<Map.Entry<Long, UserSessionsManager>> entries, long now) {
        int onlineUserCount = entries.size();
        int expectedUserCountToRefreshPerInterval = (int) (UPDATE_HEARTBEAT_INTERVAL_FACTOR * onlineUserCount / expectedFractionPerSecond);
        List<Long> userIdToUpdateHeartbeat = new ArrayList<>(expectedUserCountToRefreshPerInterval);
        for (Map.Entry<Long, UserSessionsManager> entry : entries) {
            Map<DeviceType, UserSession> sessionMap = entry.getValue().getSessionMap();
            for (Map.Entry<DeviceType, UserSession> sessionEntry : sessionMap.entrySet()) {
                Long userId = handleSession(sessionEntry.getValue(), now);
                if (userId != null) {
                    userIdToUpdateHeartbeat.add(userId);
                    break;
                }
            }
        }
        return userIdToUpdateHeartbeat;
    }

    private Long handleSession(UserSession session, long now) {
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
            CloseReason closeReason = CloseReason.get(SessionCloseStatus.HEARTBEAT_TIMEOUT);
            sessionService.setLocalSessionOfflineByUserIdAndDeviceType(
                            session.getUserId(),
                            session.getDeviceType(),
                            closeReason)
                    .onErrorResume(t -> {
                        log.error("Caught an error when disconnecting the local session: {} with the close reason: {}",
                                session, closeReason);
                        return Mono.empty();
                    })
                    .subscribe();
        }
        session.setLastHeartbeatUpdateTimestampMillis(now);
        return session.getUserId();
    }
}
