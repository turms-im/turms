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
import im.turms.server.common.util.StringUtil;
import io.netty.buffer.ByteBuf;

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
        return StringUtil.getLength(data.getNodeId()) + 1;
    }

    @Override
    protected void writeRequestData(ByteBuf output, OpeningHandshakeRequest data) {
        String nodeId = data.getNodeId();
        output.writeBytes(StringUtil.getBytes(nodeId))
                .writeByte(StringUtil.getCoder(nodeId));
    }

    @Override
    public OpeningHandshakeRequest readRequestData(ByteBuf input) {
        int length = input.readableBytes();
        byte[] bytes = new byte[length - 1];
        input.readBytes(bytes);
        String nodeId = StringUtil.newString(bytes, input.readByte());
        return new OpeningHandshakeRequest(nodeId);
    }

}
