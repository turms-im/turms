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

package im.turms.gateway.access.tcp.handler;

import im.turms.gateway.access.common.handler.ServiceAvailabilityHandler;
import im.turms.server.common.access.tcp.codec.CodecFactory;
import im.turms.server.common.healthcheck.ServerStatusManager;
import im.turms.server.common.service.blocklist.BlocklistService;
import io.netty.channel.Channel;
import reactor.netty.Connection;

/**
 * @author James Chen
 */
public class TcpHandlerConfig {

    private final ServiceAvailabilityHandler serviceAvailabilityHandler;

    public TcpHandlerConfig(BlocklistService blocklistService, ServerStatusManager serverStatusManager) {
        serviceAvailabilityHandler = new ServiceAvailabilityHandler(blocklistService, serverStatusManager);
    }

    public void configureChannel(Channel channel) {
        channel.pipeline().addFirst("serviceAvailabilityHandler", serviceAvailabilityHandler);
    }

    public void configureConnection(Connection connection) {
        // Inbound
        connection.addHandlerLast("varintLengthBasedFrameDecoder", CodecFactory.getVarintLengthBasedFrameDecoder());

        // Outbound
        connection.addHandlerLast("varintLengthFieldPrepender", CodecFactory.getVarintLengthFieldPrepender());
        // For advanced operations, they encode objects to buffers themselves,
        // "protobufFrameEncoder" will just ignore them. But some simple
        // operations will pass TurmsNotification instances down, so we still need to encode them.
        connection.addHandlerLast("protobufFrameEncoder", CodecFactory.getProtobufFrameEncoder());
    }

}