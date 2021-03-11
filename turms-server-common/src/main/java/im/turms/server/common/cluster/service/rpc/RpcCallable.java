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

import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.util.ExceptionUtil;
import io.micrometer.core.instrument.Tag;
import lombok.Getter;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import reactor.core.publisher.Mono;

/**
 * @author James Chen
 */
public abstract class RpcCallable<T> implements ApplicationContextAware {

    @Getter
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    public <Bean> Bean getBean(Class<Bean> clazz) {
        if (context == null) {
            throw new IllegalStateException("Failed to get the bean because the context is null");
        }
        try {
            return context.getBean(clazz);
        } catch (NoSuchBeanDefinitionException e) {
            NodeType nodeType = ExceptionUtil.suppress(() -> context.getBean(NodeType.class));
            String message =
                    String.format("Failed to get the bean. The request type %s may be sent to the wrong server %s", name(), nodeType);
            throw new IllegalStateException(message, e);
        }
    }

    public abstract String name();

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
