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

package im.turms.server.common.rpc.serializer.request;

import im.turms.server.common.cluster.service.rpc.RpcCallable;
import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.tracing.TracingContext;
import io.netty.buffer.ByteBuf;

/**
 * @author James Chen
 */
public abstract class RpcCallableSerializer<T extends RpcCallable<?>> implements Serializer<T> {

    private static final int TRACE_ID_LENGTH = Long.BYTES;

    @Override
    public int initialCapacity(T data) {
        return TRACE_ID_LENGTH + initialCapacityForRequest(data);
    }

    @Override
    public void write(ByteBuf output, T data) {
        TracingContext tracingContext = data.getTracingContext();
        if (!tracingContext.hasTraceId()) {
            throw new IllegalArgumentException("Cannot get the trace ID in the request: " + data.name());
        }
        output.writeLong(tracingContext.getTraceId());
        writeRequestData(output, data);
    }

    @Override
    public T read(ByteBuf input) {
        long traceId = input.readLong();
        T request = readRequestData(input);
        request.setTracingContext(new TracingContext(traceId));
        return request;
    }

    int initialCapacityForRequest(T data) {
        return 0;
    }

    void writeRequestData(ByteBuf output, T data) {
    }

    abstract T readRequestData(ByteBuf input);

}