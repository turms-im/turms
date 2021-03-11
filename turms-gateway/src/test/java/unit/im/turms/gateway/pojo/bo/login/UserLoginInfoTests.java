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

package unit.im.turms.gateway.pojo.bo.login;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.gateway.pojo.bo.login.UserLoginInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.data.geo.Point;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
@Getter
@AllArgsConstructor
class UserLoginInfoTests {

    private final Long userId = 1L;
    private final String password = "123";
    private final DeviceType loggingInDeviceType = DeviceType.ANDROID;
    private final UserStatus userStatus = UserStatus.BUSY;
    private final Point location = new Point(1L, 1L);
    private final String ip = "1.1.1.1";
    private final String deviceDetails =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36";

    @Test
    void constructor_shouldReturnInstance() {
        UserLoginInfo userLoginInfo = new UserLoginInfo(userId, password, loggingInDeviceType, userStatus, location, ip, deviceDetails);
        assertThat(userLoginInfo).isNotNull();
    }

    @Test
    void getters_shouldGetValues() {
        UserLoginInfo userLoginInfo = new UserLoginInfo(userId, password, loggingInDeviceType, userStatus, location, ip, deviceDetails);
        assertThat(userLoginInfo.getUserId()).isEqualTo(userId);
        assertThat(userLoginInfo.getPassword()).isEqualTo(password);
        assertThat(userLoginInfo.getLoggingInDeviceType()).isEqualTo(loggingInDeviceType);
        assertThat(userLoginInfo.getUserStatus()).isEqualTo(userStatus);
        assertThat(userLoginInfo.getLocation()).isEqualTo(location);
        assertThat(userLoginInfo.getIp()).isEqualTo(ip);
        assertThat(userLoginInfo.getDeviceDetails()).isEqualTo(deviceDetails);
    }

}