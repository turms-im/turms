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

package im.turms.server.common.cluster.service.connection.codec;

import im.turms.server.common.cluster.service.codec.codec.CodecId;
import im.turms.server.common.cluster.service.connection.request.OpeningHandshakeRequest;
import im.turms.server.common.rpc.codec.request.RpcRequestCodec;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

/**
 * @author James Chen
 */
public class OpeningHandshakeRequestCodec extends RpcRequestCodec<OpeningHandshakeRequest> {

    @Override
    public CodecId getCodecId() {
        return CodecId.RPC_OPENING_HANDSHAKE;
    }

    @Override
    public int initialCapacityForRequest(OpeningHandshakeRequest data) {
        // 1. Don't get the length from bytes because it
        // introduces new unnecessary heap memory
        // 2. nodeId consists of only letters and digits
        return data.getNodeId().length();
    }

    @Override
    protected void writeRequestData(ByteBuf output, OpeningHandshakeRequest data) {
        byte[] bytes = data.getNodeId().getBytes(StandardCharsets.UTF_8);
        output.writeBytes(bytes);
    }

    @Override
    public OpeningHandshakeRequest readRequestData(ByteBuf input) {
        byte[] bytes = new byte[input.readableBytes()];
        input.readBytes(bytes);
        String nodeId = new String(bytes, StandardCharsets.UTF_8);
        return new OpeningHandshakeRequest(nodeId);
    }

}
