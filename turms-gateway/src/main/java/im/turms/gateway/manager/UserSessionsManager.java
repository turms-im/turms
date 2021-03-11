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
import im.turms.common.constant.UserStatus;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.gateway.access.udp.UdpDispatcher;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.server.common.constraint.ValidDeviceType;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.util.DeviceTypeUtil;
import im.turms.server.common.util.MapUtil;
import im.turms.server.common.util.ProtoUtil;
import io.netty.buffer.ByteBuf;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author James Chen
 */
@Data
public final class UserSessionsManager {

    /**
     * The count of pending timeouts should be roughly the same as the count of online/connected sessions
     */
    private static final HashedWheelTimer HEARTBEAT_TIMER = new HashedWheelTimer();

    private final Long userId;
    private UserStatus userStatus;
    private final Map<DeviceType, UserSession> sessionMap =
            new ConcurrentHashMap<>(MapUtil.getCapability(DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES.length));

    public UserSessionsManager(
            @NotNull Long userId,
            @NotNull UserStatus userStatus) {
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(userStatus, "userStatus must not be null");
        this.userId = userId;
        this.userStatus = userStatus;
    }

    @Nullable
    public UserSession addSessionIfAbsent(
            @NotNull DeviceType loggingInDeviceType,
            @Nullable Point position,
            @Nullable Long logId,
            int closeIdleSessionAfterMillis,
            int switchProtocolAfterMillis) {
        Assert.notNull(loggingInDeviceType, "loggingInDeviceType must not be null");
        UserSession userSession = new UserSession(
                userId,
                loggingInDeviceType,
                position,
                logId);
        boolean added = sessionMap.putIfAbsent(loggingInDeviceType, userSession) == null;
        if (added) {
            if (closeIdleSessionAfterMillis > 0) {
                updateSessionHeartbeatTimeout(loggingInDeviceType, userSession, closeIdleSessionAfterMillis, switchProtocolAfterMillis);
            }
            return userSession;
        } else {
            return null;
        }
    }

    public void setDeviceOffline(
            @NotNull DeviceType deviceType,
            @NotNull CloseReason closeReason) {
        sessionMap.computeIfPresent(deviceType, (key, session) -> {
            session.close(closeReason);
            return null;
        });
    }

    /**
     * @return true if the session exists
     */
    public boolean pushSessionNotification(DeviceType deviceType, String serverId) {
        UserSession userSession = sessionMap.get(deviceType);
        if (userSession == null) {
            return false;
        }
        im.turms.common.model.bo.user.UserSession session = im.turms.common.model.bo.user.UserSession.newBuilder()
                .setSessionId(Integer.toString(userSession.getId()))
                .setServerId(serverId)
                .build();
        TurmsNotification notification = TurmsNotification.newBuilder()
                .setData(TurmsNotification.Data.newBuilder().setUserSession(session))
                .build();
        ByteBuf byteBuffer = ProtoUtil.getDirectByteBuffer(notification);
        userSession.tryEmitNextNotification(byteBuffer);
        return true;
    }

    public UserSession getSession(@NotNull DeviceType deviceType) {
        return sessionMap.get(deviceType);
    }

    public int getSessionsNumber() {
        return sessionMap.size();
    }

    public Set<DeviceType> getLoggedInDeviceTypes() {
        return sessionMap.keySet();
    }

    /**
     * @param session Don't remove this parameter by using "getSession(deviceType)"
     *                because it needs to call hashcode() to find session every time
     */
    private void updateSessionHeartbeatTimeout(@NotNull @ValidDeviceType DeviceType deviceType, @NotNull UserSession session,
                                               int closeIdleSessionAfterMillis, int switchProtocolAfterMillis) {
        TimerTask checkHeartbeatTask = timeout -> {
            if (!session.isOpen()) {
                return;
            }
            long now = System.currentTimeMillis();
            int heartbeatElapsedTime = (int) (now - session.getLastHeartbeatTimestampMillis());
            if (heartbeatElapsedTime > closeIdleSessionAfterMillis) {
                CloseReason closeReason = CloseReason.get(SessionCloseStatus.HEARTBEAT_TIMEOUT);
                setDeviceOffline(deviceType, closeReason);
            } else {
                int requestElapsedTime = (int) (now - session.getLastRequestTimestampMillis());
                if (requestElapsedTime > switchProtocolAfterMillis
                        && session.isConnected()
                        && UdpDispatcher.isEnabled()
                        && deviceType != DeviceType.BROWSER) {
                    session.getConnection().switchToUdp();
                }
                updateSessionHeartbeatTimeout(deviceType, session, closeIdleSessionAfterMillis, switchProtocolAfterMillis);
            }
        };
        Timeout newTimeout = HEARTBEAT_TIMER.newTimeout(checkHeartbeatTask,
                Math.max(closeIdleSessionAfterMillis / 3000, 1),
                TimeUnit.SECONDS);
        session.setHeartbeatTimeout(newTimeout);
    }

}