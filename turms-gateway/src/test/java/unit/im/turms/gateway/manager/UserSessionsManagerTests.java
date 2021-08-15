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

package unit.im.turms.gateway.manager;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.gateway.manager.UserSessionsManager;
import im.turms.gateway.pojo.bo.session.connection.NetConnection;
import im.turms.server.common.dto.CloseReason;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

/**
 * @author James Chen
 */
class UserSessionsManagerTests {

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
        manager.addSessionIfAbsent(deviceType, null);
        NetConnection connection = mock(NetConnection.class);
        manager.getSession(deviceType).setConnection(connection);

        assertThat(manager.getSessionMap()).hasSize(1);
        manager.setDeviceOffline(deviceType, CloseReason.get(SessionCloseStatus.SERVER_CLOSED));
        assertThat(manager.getSessionMap()).isEmpty();
    }

    @Test
    void pushSessionNotification_shouldReturnTrue_ifSessionExists() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(deviceType, null);
        assertThat(manager.pushSessionNotification(deviceType, serverId)).isTrue();
    }

    @Test
    void pushSessionNotification_shouldReturnFalse_ifSessionNotExists() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(DeviceType.ANDROID, null);
        assertThat(manager.pushSessionNotification(DeviceType.IOS, serverId)).isFalse();
    }

    @Test
    void getSession_shouldReturnSession() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(deviceType, null);
        assertThat(manager.getSession(deviceType)).isNotNull();
    }

    @Test
    void getSessionsNumber_shouldBeThree_forThreeSessions() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(DeviceType.ANDROID, null);
        manager.addSessionIfAbsent(DeviceType.IOS, null);
        manager.addSessionIfAbsent(DeviceType.DESKTOP, null);
        assertThat(manager.getSessionsNumber()).isEqualTo(3);
    }

    @Test
    void getLoggedInDeviceTypes_shouldBeSame() {
        UserSessionsManager manager = new UserSessionsManager(userId, userStatus);
        manager.addSessionIfAbsent(DeviceType.ANDROID, null);
        manager.addSessionIfAbsent(DeviceType.IOS, null);
        manager.addSessionIfAbsent(DeviceType.DESKTOP, null);

        Set<DeviceType> loggedInDeviceTypes = manager.getLoggedInDeviceTypes();

        assertThat(loggedInDeviceTypes.contains(DeviceType.ANDROID)).isTrue();
        assertThat(loggedInDeviceTypes.contains(DeviceType.IOS)).isTrue();
        assertThat(loggedInDeviceTypes.contains(DeviceType.DESKTOP)).isTrue();
    }

}