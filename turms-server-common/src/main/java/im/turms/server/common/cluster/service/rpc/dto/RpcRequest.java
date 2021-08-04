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

package im.turms.server.common.cluster.service.rpc.dto;

import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.cluster.service.connection.TurmsConnection;
import im.turms.server.common.cluster.service.rpc.NodeTypeToHandleRpc;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.ExceptionUtil;
import io.micrometer.core.instrument.Tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;

/**
 * @param <T> The data type of the response
 * @author James Chen
 * @implNote We use inheritance instead of composition because:
 * Some properties are provided for request impls indeed (e.g. fromNodeId),
 * if we use composition, we need to pass RpcRequest to request impls, which brings circular references (bad practice),
 * or extract these properties into an independent class like RequestContext, which brings redundant overhead.
 * So we still use inheritance to keep the code simple (flat classes)
 */
public abstract class RpcRequest<T> {

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

    @Override
    public String toString() {
        return "RpcRequest{" +
                "name='" + name() + "'" +
                ", tag=" + tag() +
                ", fromNodeId='" + fromNodeId + "'" +
                ", requestId=" + requestId +
                ", requestTime=" + requestTime +
                ", tracingContext=" + tracingContext +
                '}';
    }

    public void init(ApplicationContext context, @Nullable TurmsConnection connection, String fromNodeId) {
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
            NodeType nodeType = ExceptionUtil.suppress(() -> applicationContext.getBean(NodeType.class));
            String message =
                    String.format("Failed to get the bean. The request type %s may be sent to the wrong server %s", name(), nodeType);
            throw new IllegalStateException(message, e);
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
        throw new UnsupportedOperationException();
    }

    public Mono<T> callAsync() {
        throw new UnsupportedOperationException();
    }
}