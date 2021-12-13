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

package im.turms.server.common.rpc.codec.request;

import im.turms.server.common.cluster.service.codec.codec.Codec;
import im.turms.server.common.cluster.service.rpc.dto.RpcRequest;
import im.turms.server.common.tracing.TracingContext;
import io.netty.buffer.ByteBuf;

/**
 * @author James Chen
 * @implNote RpcRequestCodec and its impls are special codecs, because they only serialize
 * a part of the data (e.g. without requestId), this is because we want to decouple the header
 * and body so that we can reuse and flush the same buffer of body to multiple peers without duplicating
 * while using different headers according to different peers
 */
public abstract class RpcRequestCodec<T extends RpcRequest<?>> implements Codec<T> {

    private static final int TRACE_ID_LENGTH = Long.BYTES;

    @Override
    public int initialCapacity(T data) {
        return TRACE_ID_LENGTH + initialCapacityForRequest(data);
    }

    @Override
    public void write(ByteBuf output, T data) {
        TracingContext tracingContext = data.getTracingContext();
        long traceId = tracingContext.getTraceId();
        if (traceId == TracingContext.UNDEFINED_TRACE_ID) {
            throw new IllegalArgumentException("The trace ID is missing in the request: " + data.name());
        }
        output.writeLong(traceId);
        writeRequestData(output, data);
    }

    @Override
    public T read(ByteBuf in) {
        long traceId = in.readLong();
        T request = readRequestData(in);
        request.setTracingContext(new TracingContext(traceId));
        return request;
    }

    protected abstract int initialCapacityForRequest(T data);

    protected void writeRequestData(ByteBuf output, T data) {
        // TODO
    }

    protected abstract T readRequestData(ByteBuf input);

}