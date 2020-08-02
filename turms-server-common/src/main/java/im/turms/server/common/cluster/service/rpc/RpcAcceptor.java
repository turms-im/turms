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

package im.turms.server.common.cluster.service.rpc;

import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.cluster.service.serialization.serializer.SerializerPool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.util.DefaultPayload;
import io.rsocket.util.EmptyPayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

/**
 * @author James Chen
 */
@Log4j2
public class RpcAcceptor implements RSocket {

    private final ApplicationContext context;

    public RpcAcceptor(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Make sure we only throw RpcException in the application layer.
     * No need to release the payload here because "handleFrame" should do the job.
     *
     * @see io.rsocket.core.RSocketResponder#handleFrame(io.netty.buffer.ByteBuf)
     */
    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        ByteBuf buffer = payload.sliceData();
        try {
            RpcCallable<?> rpcRequest = parseRpcRequest(buffer);
            rpcRequest.setApplicationContext(context);
            return runRpcRequest(rpcRequest)
                    .map(this::serializeReturnValue)
                    .onErrorMap(e -> e instanceof RpcException
                            ? e
                            : RpcException.get(RpcErrorCode.UNKNOWN_ERROR, TurmsStatusCode.SERVER_INTERNAL_ERROR, e.toString()));
        } catch (RpcException e) {
            return Mono.error(e);
        } catch (Exception e) {
            return Mono.error(RpcException.get(RpcErrorCode.UNKNOWN_ERROR, TurmsStatusCode.SERVER_INTERNAL_ERROR, e.toString()));
        }
    }

    // Base methods

    private RpcCallable<?> parseRpcRequest(ByteBuf buffer) {
        if (buffer.readableBytes() < Short.BYTES) {
            throw RpcException.get(RpcErrorCode.INVALID_BUFFER_TYPE, TurmsStatusCode.SERVER_INTERNAL_ERROR);
        }
        int serializerId = buffer.readShort();
        Serializer<Object> serializer = SerializerPool.getSerializer(serializerId);
        if (serializer == null) {
            throw RpcException.get(RpcErrorCode.SERIALIZER_FOR_REQUEST_NOT_FOUND, TurmsStatusCode.SERVER_INTERNAL_ERROR);
        }
        try {
            return (RpcCallable<?>) serializer.read(buffer);
        } catch (Exception e) {
            log.error("Failed to deserialize RPC by serializer: {}({})",
                    serializer.getClass().getName(),
                    serializer.getSerializerId(),
                    e);
            throw RpcException.get(RpcErrorCode.SERIALIZER_FAILED_TO_DESERIALIZE, TurmsStatusCode.SERVER_INTERNAL_ERROR);
        }
    }

    public <T> Mono<T> runRpcRequest(RpcCallable<T> rpcRequest) {
        try {
            return rpcRequest.isAsync() ? rpcRequest.callAsync() : Mono.just(rpcRequest.call());
        } catch (TurmsBusinessException e) {
            return Mono.error(RpcException.get(RpcErrorCode.FAILED_TO_RUN_RPC, e.getCode(), e.toString()));
        } catch (Exception e) {
            return Mono.error(RpcException.get(RpcErrorCode.FAILED_TO_RUN_RPC, TurmsStatusCode.FAILED, e.toString()));
        }
    }

    private Payload serializeReturnValue(Object returnValue) {
        if (returnValue != null) {
            Serializer returnValueSerializer = SerializerPool.getSerializer(returnValue.getClass());
            if (returnValueSerializer == null) {
                throw RpcException.get(RpcErrorCode.SERIALIZER_FOR_RETURN_VALUE_NOT_FOUND, TurmsStatusCode.SERVER_INTERNAL_ERROR);
            }
            short serializerId = (short) returnValueSerializer.getSerializerId().getId();
            int initialCapacity = returnValueSerializer.initialCapacity(returnValue);
            if (initialCapacity < 1) {
                initialCapacity = 256 + Short.BYTES;
            } else {
                initialCapacity = initialCapacity + Short.BYTES;
            }
            ByteBuf outputBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(initialCapacity);
            outputBuffer.writeShort(serializerId);
            returnValueSerializer.write(outputBuffer, returnValue);
            ByteBuf byteBufToComposite = returnValueSerializer.byteBufToComposite(returnValue);
            if (byteBufToComposite != null) {
                outputBuffer = PooledByteBufAllocator.DEFAULT.compositeDirectBuffer(2)
                        .addComponents(true, outputBuffer, byteBufToComposite);
            }
            return DefaultPayload.create(outputBuffer);
        } else {
            // We don't pass TurmsStatusCode.NO_CONTENT for better performance
            return EmptyPayload.INSTANCE;
        }
    }

}