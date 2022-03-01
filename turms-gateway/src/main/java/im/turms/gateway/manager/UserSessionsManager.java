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
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.server.common.bo.location.Coordinates;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.lang.ConcurrentEnumMap;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.util.ProtoUtil;
import io.netty.buffer.ByteBuf;
import io.netty.util.HashedWheelTimer;
import lombok.Data;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
@Data
public final class UserSessionsManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionsManager.class);

    /**
     * The count of pending timeouts should be roughly the same as the count of online sessions
     */
    private static final HashedWheelTimer HEARTBEAT_TIMER = new HashedWheelTimer();
    private static final EnumMap<DeviceType, UserSession> SESSION_MAP_TEMPLATE = new EnumMap<>(DeviceType.class);

    private final Long userId;
    private UserStatus userStatus;
    /**
     * The online session map of a user
     */
    private final Map<DeviceType, UserSession> sessionMap = new ConcurrentEnumMap<>(SESSION_MAP_TEMPLATE);

    public UserSessionsManager(Long userId, UserStatus userStatus) {
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(userStatus, "userStatus must not be null");
        this.userId = userId;
        this.userStatus = userStatus;
    }

    /**
     * @return new session if added
     */
    @Nullable
    public UserSession addSessionIfAbsent(int version,
                                          DeviceType loggingInDeviceType,
                                          Map<String, String> deviceDetails,
                                          @Nullable Coordinates coordinates) {
        Assert.notNull(loggingInDeviceType, "loggingInDeviceType must not be null");
        UserSession userSession = new UserSession(
                version,
                userId,
                loggingInDeviceType,
                deviceDetails,
                coordinates);
        boolean added = sessionMap.putIfAbsent(loggingInDeviceType, userSession) == null;
        return added ? userSession : null;
    }

    public void setDeviceOffline(
            @NotNull DeviceType deviceType,
            @NotNull CloseReason closeReason) {
        UserSession session = sessionMap.remove(deviceType);
        if (session != null) {
            session.close(closeReason);
        }
    }

    /**
     * @return true if the notification is sent
     */
    public boolean pushSessionNotification(DeviceType deviceType, String serverId) {
        UserSession userSession = sessionMap.get(deviceType);
        if (userSession == null) {
            return false;
        }
        var session = im.turms.common.model.bo.user.UserSession.newBuilder()
                .setSessionId(Integer.toString(userSession.getId()))
                .setServerId(serverId)
                .build();
        TurmsNotification notification = TurmsNotification.newBuilder()
                .setData(TurmsNotification.Data.newBuilder().setUserSession(session))
                .build();
        ByteBuf byteBuffer = ProtoUtil.getDirectByteBuffer(notification);
        try {
            userSession.sendNotification(byteBuffer);
            return true;
        } catch (Exception e) {
            LOGGER.error("Failed to send the session notification", e);
            return false;
        }
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

}