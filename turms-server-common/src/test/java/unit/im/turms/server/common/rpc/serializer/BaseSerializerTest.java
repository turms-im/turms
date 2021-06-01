/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package unit.im.turms.server.common.rpc.serializer;

import im.turms.server.common.cluster.service.rpc.RpcCallable;
import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.rpc.serializer.request.RpcCallableSerializer;
import im.turms.server.common.tracing.TracingContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseSerializerTest {

    public <T extends RpcCallable<?>> T writeRequestAndReadBuffer(RpcCallableSerializer<T> serializer, T request) {
        TracingContext tracingContext = new TracingContext();
        request.setTracingContext(tracingContext);

        T parsedRequest = writeDataAndReadBuffer(serializer, request);

        assertThat(parsedRequest.getTracingContext().getTraceId()).isEqualTo(tracingContext.getTraceId());

        return parsedRequest;
    }

    public <T> T writeDataAndReadBuffer(Serializer<T> serializer, T data) {
        int initialCapacity = serializer.initialCapacity(data);
        ByteBuf baseBuffer = PooledByteBufAllocator.DEFAULT.buffer(initialCapacity);
        serializer.write(baseBuffer, data);

        CompositeByteBuf buffers = PooledByteBufAllocator.DEFAULT.compositeBuffer(2)
                .addComponent(true, baseBuffer);

        ByteBuf buffer = serializer.byteBufToComposite(data);
        if (buffer != null) {
            buffers.addComponent(true, buffer);
        }

        T parsedData = serializer.read(buffers);

        assertThat(baseBuffer.capacity()).isEqualTo(initialCapacity);

        return parsedData;
    }

}
