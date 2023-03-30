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

package unit.im.turms.server.common.infra.cluster.service.rpc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;

import im.turms.server.common.infra.cluster.service.codec.codec.Codec;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecPool;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStream;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcRequestCodec;
import im.turms.server.common.infra.cluster.service.rpc.dto.RpcRequest;
import im.turms.server.common.infra.tracing.TracingContext;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseCodecTest {

    static {
        CodecPool.init();
    }

    public <T extends RpcRequest<?>> T writeRequestAndReadBuffer(
            RpcRequestCodec<T> codec,
            T request) {
        TracingContext context = new TracingContext();
        request.setTracingContext(context);

        T parsedRequest = writeDataAndReadBuffer(codec, request);

        assertThat(parsedRequest.getTracingContext()
                .getTraceId()).isEqualTo(context.getTraceId());

        return parsedRequest;
    }

    public <T> T writeDataAndReadBuffer(Codec<T> codec, T data) {
        int initialCapacity = codec.initialCapacity(data);
        ByteBuf baseBuffer = UnpooledByteBufAllocator.DEFAULT.buffer(initialCapacity);
        codec.write(new CodecStream(baseBuffer), data);

        CompositeByteBuf buffers = UnpooledByteBufAllocator.DEFAULT.compositeBuffer(2)
                .addComponent(true, baseBuffer);

        ByteBuf buffer = codec.byteBufToComposite(data);
        if (buffer != null) {
            buffers.addComponent(true, buffer);
        }

        T parsedData = codec.read(new CodecStream(buffers));

        assertThat(baseBuffer.capacity()).isEqualTo(initialCapacity);

        return parsedData;
    }

}
