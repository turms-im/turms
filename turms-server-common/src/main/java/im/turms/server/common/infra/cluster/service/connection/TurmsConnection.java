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

package im.turms.server.common.infra.cluster.service.connection;

import java.util.Collections;
import java.util.List;
import jakarta.annotation.Nullable;

import lombok.Data;
import reactor.netty.channel.ChannelOperations;

import im.turms.server.common.infra.cluster.service.discovery.MemberConnectionListener;

/**
 * @author James Chen
 */
@Data
public class TurmsConnection {
    /**
     * Is null when the connection is accepted on the server side but no handshake request is
     * accepted. For the connection client, it is never null.
     */
    @Nullable
    private String nodeId;

    private final ChannelOperations<?, ?> connection;
    /**
     * True if it sent a closing handshake request on the client side, or receives a closing
     * handshake request on the server side.
     */
    private boolean isClosing;

    private final boolean isLocalNodeClient;
    private volatile long lastKeepaliveTimestamp;

    private final List<MemberConnectionListener> listeners;

    public TurmsConnection(
            @Nullable String nodeId,
            ChannelOperations<?, ?> connection,
            boolean isLocalNodeClient,
            List<MemberConnectionListener> listeners) {
        this.nodeId = nodeId;
        this.connection = connection;
        this.isLocalNodeClient = isLocalNodeClient;
        this.listeners = listeners == null
                ? Collections.emptyList()
                : listeners;

        lastKeepaliveTimestamp = System.currentTimeMillis();
        isClosing = false;
    }
}