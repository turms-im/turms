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

package im.turms.gateway.access.tcp.model;

import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.pojo.bo.session.connection.NetConnection;
import im.turms.server.common.dto.CloseReason;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author James Chen
 */
@Data
public class UserSessionWrapper {

    private static final HashedWheelTimer IDLE_CONNECTION_TIMEOUT_TIMER = new HashedWheelTimer();
    private final String ip;
    private final Timeout connectionTimeoutTask;
    private final Consumer<UserSession> onSessionEstablished;
    private NetConnection connection;
    private UserSession userSession;

    public UserSessionWrapper(NetConnection connection, String ip, int closeAfter, Consumer<UserSession> onSessionEstablished) {
        this.connection = connection;
        this.ip = ip;
        this.onSessionEstablished = onSessionEstablished;
        connectionTimeoutTask = closeAfter > 0
                ? addIdleConnectionTimeoutTask(closeAfter)
                : null;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
        userSession.setConnection(connection);
        onSessionEstablished.accept(userSession);
    }

    public boolean hasUserSession() {
        return userSession != null;
    }

    private Timeout addIdleConnectionTimeoutTask(int closeIdleConnectionAfter) {
        return IDLE_CONNECTION_TIMEOUT_TIMER.newTimeout(timeout -> {
            CloseReason closeReason = CloseReason.get(SessionCloseStatus.LOGIN_TIMEOUT);
            if (userSession == null || !userSession.isOpen()) {
                connection.close(closeReason);
            }
        }, closeIdleConnectionAfter, TimeUnit.SECONDS);
    }

}