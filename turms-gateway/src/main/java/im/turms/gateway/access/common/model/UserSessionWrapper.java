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

package im.turms.gateway.access.common.model;

import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.pojo.bo.session.connection.NetConnection;
import im.turms.server.common.dto.CloseReason;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import lombok.Data;

import javax.annotation.Nullable;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Bind the network connection and the user session together
 * from the perspective of the access layer
 *
 * @author James Chen
 */
@Data
public class UserSessionWrapper {

    private static final HashedWheelTimer IDLE_CONNECTION_TIMEOUT_TIMER = new HashedWheelTimer();
    private final InetSocketAddress address;
    private final Timeout connectionTimeoutTask;
    private final Consumer<UserSession> onSessionEstablished;
    private NetConnection connection;
    @Nullable
    private UserSession userSession;

    @Nullable
    private byte[] rawIpCache;
    @Nullable
    private String ipCache;

    public UserSessionWrapper(NetConnection connection,
                              InetSocketAddress address,
                              int closeAfter,
                              Consumer<UserSession> onSessionEstablished) {
        this.connection = connection;
        this.address = address;
        this.onSessionEstablished = onSessionEstablished;
        connectionTimeoutTask = closeAfter > 0
                ? addIdleConnectionTimeoutTask(closeAfter)
                : null;
    }

    public byte[] getRawIp() {
        if (rawIpCache == null) {
            rawIpCache = address.getAddress().getAddress();
        }
        return rawIpCache;
    }

    /**
     * @implNote Don't use getHostString() to avoid getting a hostname
     */
    public String getIp() {
        if (ipCache == null) {
            ipCache = address.getAddress().getHostAddress();
        }
        return ipCache;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
        userSession.setConnection(connection);
        onSessionEstablished.accept(userSession);
    }

    public boolean hasUserSession() {
        return userSession != null;
    }

    public void closeConnection() {
        connection.close();
    }

    private Timeout addIdleConnectionTimeoutTask(int closeIdleConnectionAfter) {
        return IDLE_CONNECTION_TIMEOUT_TIMER.newTimeout(timeout -> {
            if (userSession == null || !userSession.isOpen()) {
                CloseReason closeReason = CloseReason.get(SessionCloseStatus.LOGIN_TIMEOUT);
                connection.close(closeReason);
            }
        }, closeIdleConnectionAfter, TimeUnit.SECONDS);
    }

}