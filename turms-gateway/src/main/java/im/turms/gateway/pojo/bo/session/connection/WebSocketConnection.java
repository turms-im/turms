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

import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.util.CloseReasonUtil;
import org.springframework.web.reactive.socket.CloseStatus;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
public class WebSocketConnection extends NetConnection {

    private final WebSocketSession connection;

    public WebSocketConnection(WebSocketSession connection, boolean isConnected) {
        super(isConnected);
        this.connection = connection;
    }

    /**
     * It's acceptable that the method isn't thread-safe
     */
    @Override
    public void close(@NotNull CloseReason closeReason) {
        if (isConnected() && connection.isOpen()) {
            super.close(closeReason);
            CloseStatus closeStatus;
            if (closeReason.isTurmsStatusCode()) {
                TurmsStatusCode code = TurmsStatusCode.from(closeReason.getCode());
                SessionCloseStatus sessionCloseStatus = CloseReasonUtil.statusCodeToCloseStatus(code);
                closeStatus = new CloseStatus(sessionCloseStatus.getCode(), closeReason.getReason());
            } else {
                closeStatus = new CloseStatus(closeReason.getCode(), closeReason.getReason());
            }
            connection.close(closeStatus)
                    .onErrorResume(throwable -> Mono.empty())
                    .subscribe();
        }
    }

}