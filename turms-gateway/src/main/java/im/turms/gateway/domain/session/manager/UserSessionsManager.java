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

import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.util.Assert;

import im.turms.gateway.access.client.common.UserSession;
import im.turms.server.common.access.client.dto.ClientMessageEncoder;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.domain.location.bo.Location;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.infra.collection.ConcurrentEnumMap;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * @author James Chen
 */
@Data
public final class UserSessionsManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionsManager.class);

    private final Long userId;
    private UserStatus userStatus;
    /**
     * The online session map of a user
     */
    private final Map<DeviceType, UserSession> deviceTypeToSession =
            new ConcurrentEnumMap<>(DeviceType.class);

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
    public UserSession addSessionIfAbsent(
            int version,
            Set<TurmsRequest.KindCase> permissions,
            DeviceType loggingInDeviceType,
            Map<String, String> deviceDetails,
            @Nullable Location location) {
        Assert.notNull(loggingInDeviceType, "loggingInDeviceType must not be null");
        UserSession userSession = new UserSession(
                version,
                permissions,
                userId,
                loggingInDeviceType,
                deviceDetails,
                location);
        boolean added = deviceTypeToSession.putIfAbsent(loggingInDeviceType, userSession) == null;
        return added
                ? userSession
                : null;
    }

    /**
     * @return true if the session was online
     */
    public boolean closeSession(@NotNull DeviceType deviceType, @NotNull CloseReason closeReason) {
        UserSession session = deviceTypeToSession.remove(deviceType);
        if (session != null) {
            return session.close(closeReason);
        }
        return false;
    }

    /**
     * @return true if the notification is sent
     */
    public boolean pushSessionNotification(DeviceType deviceType, String serverId) {
        UserSession userSession = deviceTypeToSession.get(deviceType);
        if (userSession == null) {
            return false;
        }
        try {
            userSession
                    .sendNotification(ClientMessageEncoder.encodeUserSessionNotification(System
                            .currentTimeMillis(), Integer.toString(userSession.getId()), serverId))
                    .subscribe(null,
                            t -> LOGGER.error(
                                    "Failed to send the session notification to the user session: "
                                            + userSession,
                                    t));
            return true;
        } catch (Exception e) {
            LOGGER.error("Failed to send the session notification to the user session: "
                    + userSession, e);
            return false;
        }
    }

    @Nullable
    public UserSession getSession(@NotNull DeviceType deviceType) {
        return deviceTypeToSession.get(deviceType);
    }

    public int countSessions() {
        return deviceTypeToSession.size();
    }

    public Set<DeviceType> getLoggedInDeviceTypes() {
        return deviceTypeToSession.keySet();
    }

}