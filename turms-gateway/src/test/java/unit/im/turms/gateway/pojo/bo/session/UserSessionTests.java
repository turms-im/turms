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
import io.netty.buffer.ByteBuf;
import io.netty.util.Timeout;
import org.junit.jupiter.api.Test;
import org.springframework.data.geo.Point;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.StandaloneFluxSink;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * @author James Chen
 */
class UserSessionTests {

    private final int id = 1;
    private final DeviceType deviceType = DeviceType.ANDROID;
    private final Date loginDate = new Date();
    private final Point loginLocation = new Point(1F, 1F);
    private final WebSocketSession webSocketSession = mock(WebSocketSession.class);
    private final StandaloneFluxSink<ByteBuf> notificationSink = Sinks.unicast();
    private final Timeout heartbeatTimeout = mock(Timeout.class);
    private final long logId = 1L;
    private final long lastHeartbeatTimestampMillis = 1L;
    private final long lastRequestTimestampMillis = 1L;

    @Test
    void constructor_shouldReturnInstance() {
        UserSession userSession = new UserSession(
                id,
                deviceType,
                loginDate,
                loginLocation,
                webSocketSession,
                notificationSink,
                heartbeatTimeout,
                logId,
                lastHeartbeatTimestampMillis,
                lastRequestTimestampMillis);
        assertNotNull(userSession);
    }

    @Test
    void getters_shouldGetValues() {
        UserSession userSession = new UserSession(
                id,
                deviceType,
                loginDate,
                loginLocation,
                webSocketSession,
                notificationSink,
                heartbeatTimeout,
                logId,
                lastHeartbeatTimestampMillis,
                lastRequestTimestampMillis);
        assertEquals(id, userSession.getId());
        assertEquals(deviceType, userSession.getDeviceType());
        assertEquals(loginDate, userSession.getLoginDate());
        assertEquals(loginLocation, userSession.getLoginLocation());
        assertEquals(webSocketSession, userSession.getWebSocketSession());
        assertEquals(notificationSink, userSession.getNotificationSink());
        assertEquals(heartbeatTimeout, userSession.getHeartbeatTimeout());
        assertEquals(logId, userSession.getLogId());
        assertEquals(lastHeartbeatTimestampMillis, userSession.getLastHeartbeatTimestampMillis());
        assertEquals(lastRequestTimestampMillis, userSession.getLastRequestTimestampMillis());
    }

}
