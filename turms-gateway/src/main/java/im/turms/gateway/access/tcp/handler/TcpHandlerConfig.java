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

import im.turms.gateway.access.tcp.codec.TurmsProtobufEncoder;
import im.turms.gateway.access.tcp.codec.TurmsProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import reactor.netty.Connection;

/**
 * @author James Chen
 */
public class TcpHandlerConfig {

    private final ProtobufVarint32FrameDecoder protobufVarint32FrameDecoder = new TurmsProtobufVarint32FrameDecoder();
    private final ProtobufVarint32LengthFieldPrepender protobufVarint32LengthFieldPrepender = new ProtobufVarint32LengthFieldPrepender();
    private final TurmsProtobufEncoder turmsProtobufEncoder = new TurmsProtobufEncoder();

    public void configure(Connection connection) {
        connection.addHandlerLast("protobufFrameDecoder", protobufVarint32FrameDecoder);

        connection.addHandlerLast("protobufFrameEncoder", protobufVarint32LengthFieldPrepender);
        connection.addHandlerLast("protobufEncoder", turmsProtobufEncoder);
    }

}