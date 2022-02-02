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

package im.turms.server.common.access.http.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.reactive.result.method.TurmsHandlerMethod;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Chen
 * @implNote Note that actuator endpoints won't use our own handler method
 * because their endpoints use their own implementation and are not annotated by {@link RequestMapping}
 */
public class TurmsRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private final ConcurrentHashMap<RequestHandlerId, TurmsHandlerMethod> methodCache = new ConcurrentHashMap<>(256);

    @Override
    protected HandlerMethod createHandlerMethod(Object handler, Method method) {
        HandlerMethod handlerMethod = super.createHandlerMethod(handler, method);
        return new TurmsHandlerMethod(handlerMethod);
    }

    /**
     * Note {@link RequestMappingInfoHandlerMapping} won't cache handlers.
     * Our scenario is easy, so we cache them and this can eliminate a lot of objects.
     */
    @Override
    public Mono<HandlerMethod> getHandlerInternal(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        RequestHandlerId handlerId = new RequestHandlerId(request.getMethod(), request.getURI().getPath());
        TurmsHandlerMethod method = methodCache.get(handlerId);
        if (method != null) {
            return Mono.just(method);
        }
        Mono<HandlerMethod> handlerInternal = super.getHandlerInternal(exchange);
        return handlerInternal
                // It's fine to overwrite
                .doOnNext(handlerMethod -> methodCache.put(handlerId, (TurmsHandlerMethod) handlerMethod));
    }

    private record RequestHandlerId(HttpMethod method, String path) {
    }

}
