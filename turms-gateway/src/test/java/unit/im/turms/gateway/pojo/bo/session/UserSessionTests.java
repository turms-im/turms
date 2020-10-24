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

package unit.im.turms.gateway.pojo.bo.session;

import im.turms.common.constant.DeviceType;
import im.turms.gateway.pojo.bo.session.UserSession;
import org.junit.jupiter.api.Test;
import org.springframework.data.geo.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author James Chen
 */
class UserSessionTests {

    private final Long userId = 1L;
    private final DeviceType deviceType = DeviceType.ANDROID;
    private final Point loginLocation = new Point(1F, 1F);
    private final long logId = 1L;

    @Test
    void constructor_shouldReturnInstance() {
        UserSession userSession = new UserSession(
                userId,
                deviceType,
                loginLocation,
                logId);
        assertNotNull(userSession);
    }

    @Test
    void getters_shouldGetValues() {
        UserSession userSession = new UserSession(
                userId,
                deviceType,
                loginLocation,
                logId);
        assertEquals(deviceType, userSession.getDeviceType());
        assertEquals(loginLocation, userSession.getLoginLocation());
        assertEquals(logId, userSession.getLogId());
    }

}
