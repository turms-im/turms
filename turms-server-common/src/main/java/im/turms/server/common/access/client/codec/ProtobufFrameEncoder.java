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

package im.turms.server.common.access.client.codec;

import java.util.List;

import com.google.protobuf.MessageLite;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import static im.turms.server.common.infra.proto.ProtoEncoder.getDirectByteBuffer;

/**
 * @author James Chen The class accepts TurmsNotifcation on the server side, and accepts
 *         TurmsRequest on the client side
 */
@ChannelHandler.Sharable
public class ProtobufFrameEncoder extends MessageToMessageEncoder<MessageLite> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageLite message, List<Object> out) {
        out.add(getDirectByteBuffer(message));
    }

}