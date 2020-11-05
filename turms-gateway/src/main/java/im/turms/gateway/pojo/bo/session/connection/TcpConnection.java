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

package im.turms.gateway.pojo.bo.session.connection;

import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.util.CloseReasonUtil;
import lombok.extern.log4j.Log4j2;
import reactor.netty.Connection;

import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
@Log4j2
public class TcpConnection extends NetConnection {

    private final Connection connection;

    public TcpConnection(Connection connection, boolean isConnected) {
        super(isConnected);
        this.connection = connection;
    }

    @Override
    public void close(@NotNull CloseReason closeReason) {
        TurmsNotification closeNotification = CloseReasonUtil.toNotification(closeReason);
        super.close(closeReason);
        connection
                .outbound()
                .sendObject(closeNotification)
                .then()
                .subscribe(unused -> connection.dispose(),
                        throwable -> log.error("Failed to send the close frame", throwable));
    }

}