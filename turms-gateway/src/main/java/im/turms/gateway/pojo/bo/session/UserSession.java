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
import io.netty.buffer.ByteBuf;
import io.netty.util.Timeout;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Sinks.StandaloneFluxSink;

import java.util.Date;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Data
public final class UserSession {

    private final int id;

    private final DeviceType deviceType;
    private final Date loginDate;
    private Point loginLocation;

    /**
     * We don't use something like "Object attachment" to separate the business logic
     * and technical implementation for better performance and code clarity
     */
    private WebSocketSession webSocketSession;

    /**
     * 1. Use StandaloneFluxSink<ByteBuf> instead of StandaloneFluxSink<TurmsNotification>
     * so that turms-gateway can transfer data through zero copy (if SSL is disabled)
     * and don't need to parse it when the data comes from turms and don't need to parse them.
     * <p>
     * 2. Although we can forward the same WebSocketMessage when there are different recipients connecting to the local turms-gateway,
     * we still use ByteBuf for code clarity and extensibility (we will integrate UDP in the future) and ByteBuf won't be copied in the scenario
     * so it's acceptable.
     * Note that the ByteBuf (TurmsNotification) comes from turms servers in most scenarios.
     */
    private final StandaloneFluxSink<ByteBuf> notificationSink;
    private Timeout heartbeatTimeout;
    private Long logId;
    private volatile long lastHeartbeatTimestampMillis;
    private volatile long lastRequestTimestampMillis;

}
