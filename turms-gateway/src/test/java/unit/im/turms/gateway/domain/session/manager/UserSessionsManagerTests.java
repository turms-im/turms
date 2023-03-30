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

package unit.im.turms.gateway.domain.session.manager;

import java.util.Set;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import im.turms.gateway.access.client.common.connection.NetConnection;
import im.turms.gateway.domain.session.manager.UserSessionsManager;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.request.TurmsRequestTypePool;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.infra.lang.ByteArrayWrapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

/**
 * @author James Chen
 */
class UserSessionsManagerTests {

    private final int version = 1;
    private final long userId = 1L;
    private final UserStatus userStatus = UserStatus.AVAILABLE;
    private final DeviceType deviceType = DeviceType.ANDROID;
    private final String serverId = "turms001";

    @Test
    void constructor_shouldSucceed_ifRequiredParamsExist() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        assertThat(manager).isNotNull();
    }

    @Test
    void constructor_shouldThrow_ifRequiredParamsNotExist() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new UserSessionsManager(null, userStatus));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new UserSessionsManager(userId, null));
    }

    @Test
    void setDeviceOffline_shouldSucceed() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(version, TurmsRequestTypePool.ALL, deviceType, null, null);
        NetConnection connection = mock(NetConnection.class);
        manager.getSession(deviceType)
                .setConnection(connection, new ByteArrayWrapper(new byte[]{}));

        assertThat(manager.getDeviceTypeToSession()).hasSize(1);
        manager.closeSession(deviceType, CloseReason.get(SessionCloseStatus.SERVER_CLOSED));
        assertThat(manager.getDeviceTypeToSession()).isEmpty();
    }

    @Test
    void pushSessionNotification_shouldReturnTrue_ifSessionExists() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(version, TurmsRequestTypePool.ALL, deviceType, null, null)
                .setNotificationConsumer((byteBuf, tracingContext) -> Mono.empty());
        assertThat(manager.pushSessionNotification(deviceType, serverId)).isTrue();
    }

    @Test
    void pushSessionNotification_shouldReturnFalse_ifSessionNotExists() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(version,
                TurmsRequestTypePool.ALL,
                DeviceType.ANDROID,
                null,
                null);
        assertThat(manager.pushSessionNotification(DeviceType.IOS, serverId)).isFalse();
    }

    @Test
    void getSession_shouldReturnSession() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(version, TurmsRequestTypePool.ALL, deviceType, null, null);
        assertThat(manager.getSession(deviceType)).isNotNull();
    }

    @Test
    void getSessionsNumber_shouldBeThree_forThreeSessions() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(version,
                TurmsRequestTypePool.ALL,
                DeviceType.ANDROID,
                null,
                null);
        manager.addSessionIfAbsent(version, TurmsRequestTypePool.ALL, DeviceType.IOS, null, null);
        manager.addSessionIfAbsent(version,
                TurmsRequestTypePool.ALL,
                DeviceType.DESKTOP,
                null,
                null);
        assertThat(manager.countSessions()).isEqualTo(3);
    }

    @Test
    void getLoggedInDeviceTypes_shouldBeSame() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(version,
                TurmsRequestTypePool.ALL,
                DeviceType.ANDROID,
                null,
                null);
        manager.addSessionIfAbsent(version, TurmsRequestTypePool.ALL, DeviceType.IOS, null, null);
        manager.addSessionIfAbsent(version,
                TurmsRequestTypePool.ALL,
                DeviceType.DESKTOP,
                null,
                null);

        Set<DeviceType> loggedInDeviceTypes = manager.getLoggedInDeviceTypes();

        assertThat(loggedInDeviceTypes.contains(DeviceType.ANDROID)).isTrue();
        assertThat(loggedInDeviceTypes.contains(DeviceType.IOS)).isTrue();
        assertThat(loggedInDeviceTypes.contains(DeviceType.DESKTOP)).isTrue();
    }

}