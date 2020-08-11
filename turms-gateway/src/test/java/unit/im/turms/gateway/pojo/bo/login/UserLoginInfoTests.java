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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    private final Map<String, String> deviceDetails = Map.of("Hi", "Turms");

    @Test
    void constructor_shouldReturnInstance() {
        UserLoginInfo userLoginInfo = new UserLoginInfo(userId, password, loggingInDeviceType, userStatus, location, ip, deviceDetails);
        assertNotNull(userLoginInfo);
    }

    @Test
    void getters_shouldGetValues() {
        UserLoginInfo userLoginInfo = new UserLoginInfo(userId, password, loggingInDeviceType, userStatus, location, ip, deviceDetails);
        assertEquals(userId, userLoginInfo.getUserId());
        assertEquals(password, userLoginInfo.getPassword());
        assertEquals(loggingInDeviceType, userLoginInfo.getLoggingInDeviceType());
        assertEquals(userStatus, userLoginInfo.getUserStatus());
        assertEquals(location, userLoginInfo.getLocation());
        assertEquals(ip, userLoginInfo.getIp());
        assertEquals(deviceDetails, userLoginInfo.getDeviceDetails());
    }

}