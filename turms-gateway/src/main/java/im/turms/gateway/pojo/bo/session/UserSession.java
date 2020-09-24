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

package im.turms.gateway.pojo.bo.session;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.util.RandomUtil;
import im.turms.gateway.access.websocket.dto.CloseStatusFactory;
import io.netty.buffer.ByteBuf;
import io.netty.util.Timeout;
import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.web.reactive.socket.CloseStatus;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Sinks;

import javax.validation.constraints.NotNull;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * @author James Chen
 */
@Data
public final class UserSession {

    private final int id = RandomUtil.nextPositiveInt();

    private final DeviceType deviceType;
    private final Date loginDate;
    private Point loginLocation;

    /**
     * We don't use something like "Object attachment" to separate the business logic
     * and technical implementation for better performance and code clarity
     */
    private WebSocketSession webSocketSession;
    private InetSocketAddress address;

    /**
     * 1. Use Sinks.Many<ByteBuf> instead of Sinks.Many<TurmsNotification>
     * so that turms-gateway can transfer data through zero copy (if SSL is disabled)
     * and don't need to parse it when the data comes from turms.
     * <p>
     * 2. Although we can forward the same WebSocketMessage when there are different recipients connecting to the local turms-gateway,
     * we still use ByteBuf for code clarity and extensibility (we will integrate UDP in the future) and ByteBuf won't be copied in the scenario
     * so it's acceptable.
     * Note that the ByteBuf (TurmsNotification) comes from turms servers in most scenarios.
     */
    private Sinks.Many<ByteBuf> notificationSink = Sinks.many().unicast().onBackpressureBuffer();
    private Timeout heartbeatTimeout;
    private Long logId;
    private volatile long lastHeartbeatTimestampMillis;
    private volatile long lastRequestTimestampMillis;

    private volatile boolean isConnectionRecovering;
    private SessionStatus status = SessionStatus.CLOSED;

    public UserSession(DeviceType loggingInDeviceType,
                       Point loginLocation,
                       Long logId) {
        Date now = new Date();
        this.deviceType = loggingInDeviceType;
        this.loginDate = now;
        this.loginLocation = loginLocation;
        this.logId = logId;
        this.lastHeartbeatTimestampMillis = now.getTime();
    }

    public void setConnectionRecovering(boolean connectionRecovering) {
        isConnectionRecovering = connectionRecovering;
        updateStatus();
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
        updateStatus();
    }

    public void close(@NotNull CloseStatus closeStatus) {
        status = SessionStatus.CLOSED;
        notificationSink.tryEmitComplete();
        if (heartbeatTimeout != null) {
            heartbeatTimeout.cancel();
        }
        if (webSocketSession != null) {
            webSocketSession.close(closeStatus).subscribe();
            webSocketSession = null;
        }
    }

    public void disconnect() {
        if (webSocketSession != null) {
            CloseStatus closeStatus = CloseStatusFactory.get(SessionCloseStatus.SWITCH);
            webSocketSession.close(closeStatus).subscribe();
            webSocketSession = null;
        }
        updateStatus();
    }

    public boolean isOpen() {
        return status != SessionStatus.CLOSED;
    }

    public boolean isConnected() {
        return status != SessionStatus.CONNECTED;
    }

    public boolean isDisconnected() {
        return status != SessionStatus.DISCONNECTED;
    }

    public boolean isRecovering() {
        return status != SessionStatus.RECOVERING;
    }

    private void updateStatus() {
        if (webSocketSession == null) {
            if (isConnectionRecovering) {
                status = SessionStatus.RECOVERING;
            } else {
                status = SessionStatus.DISCONNECTED;
            }
        } else {
            status = SessionStatus.CONNECTED;
        }
    }

}
