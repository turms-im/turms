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

package im.turms.server.common.infra.cluster.service.rpc.dto;

import jakarta.annotation.Nullable;

import io.micrometer.core.instrument.Tag;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCounted;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.cluster.service.connection.TurmsConnection;
import im.turms.server.common.infra.cluster.service.rpc.NodeTypeToHandleRpc;
import im.turms.server.common.infra.exception.NotImplementedException;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.tracing.TracingContext;

/**
 * @param <T> The data type of the response
 * @author James Chen
 * @implNote 1. We use inheritance instead of composition because: Some properties are only provided
 *           for request impls (e.g. fromNodeId), if we use composition, we need to pass RpcRequest
 *           to request impls, which brings circular references (bad practice), or extract these
 *           properties into an independent class like RequestContext, which brings redundant
 *           overhead. So we still use inheritance to keep the code simple (flat classes)
 *           <p>
 *           2. We implement {@link ReferenceCounted} so that RpcRequest can be released correctly
 *           by the middle {@link io.netty.channel.ChannelInboundHandler} that we cannot control
 *           when decoding. For example, the following method will try to release RpcRequest and
 *           just return without calling our own downstream RPC acceptor if the connection has been
 *           closed {@link reactor.netty.channel.FluxReceive#onInboundNext(java.lang.Object)}
 */
public abstract class RpcRequest<T> implements ReferenceCounted {

    /**
     * Is null if the RPC request runs on the local node
     */
    @Nullable
    @Getter
    @Setter
    private TurmsConnection connection;

    @Getter
    @Setter
    private String fromNodeId;

    @Getter
    @Setter
    private int requestId = -1;

    @Getter
    private final long requestTime = System.currentTimeMillis();

    @Getter
    @Setter
    private TracingContext tracingContext = TracingContext.NOOP;

    @Getter
    @Setter
    private ApplicationContext applicationContext;

    @Setter
    private ByteBuf boundBuffer = Unpooled.EMPTY_BUFFER;

    @Override
    public String toString() {
        return "RpcRequest{"
                + "name='"
                + name()
                + "'"
                + ", tag="
                + tag()
                + ", fromNodeId='"
                + fromNodeId
                + "'"
                + ", requestId="
                + requestId
                + ", requestTime="
                + requestTime
                + ", tracingContext="
                + tracingContext
                + '}';
    }

    public void init(
            ApplicationContext context,
            @Nullable TurmsConnection connection,
            String fromNodeId) {
        setApplicationContext(context);
        setConnection(connection);
        setFromNodeId(fromNodeId);
    }

    public <Bean> Bean getBean(Class<Bean> clazz) {
        if (applicationContext == null) {
            throw new IllegalStateException("Failed to get the bean because the context is null");
        }
        try {
            return applicationContext.getBean(clazz);
        } catch (NoSuchBeanDefinitionException e) {
            NodeType nodeType =
                    ThrowableUtil.suppress(() -> applicationContext.getBean(NodeType.class));
            String message = "Failed to get the bean. The request type \""
                    + name()
                    + "\" may be sent to the wrong server with the node type: "
                    + nodeType;
            throw new RuntimeException(message, e);
        }
    }

    public abstract String name();

    /**
     * Used to ensure we won't send requests from unexpected servers
     */
    public abstract NodeTypeToHandleRpc nodeTypeToRequest();

    /**
     * Used to ensure we won't send requests to unexpected servers
     */
    public abstract NodeTypeToHandleRpc nodeTypeToRespond();

    public Tag tag() {
        return null;
    }

    /**
     * Use a method instead of a field to avoid serializing/deserializing
     */
    public boolean isAsync() {
        return true;
    }

    public T call() {
        throw new NotImplementedException();
    }

    public Mono<T> callAsync() {
        throw new NotImplementedException();
    }

    // Adaptor to ReferenceCounted

    @Override
    public int refCnt() {
        return boundBuffer.refCnt();
    }

    @Override
    public RpcRequest<T> retain() {
        boundBuffer.retain();
        return this;
    }

    @Override
    public RpcRequest<T> retain(int increment) {
        boundBuffer.retain(increment);
        return this;
    }

    @Override
    public RpcRequest<T> touch() {
        boundBuffer.touch();
        return this;
    }

    @Override
    public RpcRequest<T> touch(Object hint) {
        boundBuffer.touch(hint);
        return this;
    }

    @Override
    public boolean release() {
        return boundBuffer.release();
    }

    @Override
    public boolean release(int decrement) {
        return boundBuffer.release(decrement);
    }

}