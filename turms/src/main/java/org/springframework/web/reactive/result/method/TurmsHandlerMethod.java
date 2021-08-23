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

package org.springframework.web.reactive.result.method;

import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author James Chen
 */
public class TurmsHandlerMethod extends InvocableHandlerMethod {

    private static final Mono<Object[]> EMPTY_ARGS = Mono.just(new Object[0]);
    private static final Object NO_ARG_VALUE = new Object();

    public TurmsHandlerMethod(HandlerMethod handlerMethod) {
        super(handlerMethod);
    }

    private final TurmsHandlerMethodArgumentResolverComposite resolvers = new TurmsHandlerMethodArgumentResolverComposite();

    @Override
    public void setArgumentResolvers(List<? extends HandlerMethodArgumentResolver> resolvers) {
        this.resolvers.addResolvers(resolvers);
    }

    @Override
    public Mono<HandlerResult> invoke(ServerWebExchange exchange, BindingContext bindingContext, Object... providedArgs) {
        return getMethodArgumentValues(exchange, bindingContext, providedArgs).flatMap(args -> {
            Object value;
            try {
                Method method = getBridgedMethod();
                ReflectionUtils.makeAccessible(method);
                value = method.invoke(getBean(), args);
            } catch (IllegalArgumentException ex) {
                String text = ex.getMessage() != null ? ex.getMessage() : "Illegal argument";
                return Mono.error(new IllegalStateException(formatInvokeError(text, args), ex));
            } catch (InvocationTargetException ex) {
                return Mono.error(ex.getTargetException());
            } catch (Throwable ex) {
                // Unlikely to ever get here, but it must be handled...
                return Mono.error(
                        new IllegalStateException(formatInvokeError("Invocation failure", args), ex));
            }

            HttpStatus status = getResponseStatus();
            exchange.getResponse().setStatusCode(status);

            MethodParameter returnType = getReturnType();
            ReactiveAdapter adapter = ReactiveAdapterRegistry.getSharedInstance().getAdapter(returnType.getParameterType());
            boolean asyncVoid = isAsyncVoidReturnType(returnType, adapter);
            if ((value == null || asyncVoid) && isResponseHandled(args, exchange)) {
                return asyncVoid ? Mono.from(adapter.toPublisher(value)) : Mono.empty();
            }
            HandlerResult result = new HandlerResult(this, value, returnType, bindingContext);
            return Mono.just(result);
        });
    }

    private Mono<Object[]> getMethodArgumentValues(
            ServerWebExchange exchange, BindingContext bindingContext, Object... providedArgs) {
        MethodParameter[] parameters = getMethodParameters();
        if (ObjectUtils.isEmpty(parameters)) {
            return EMPTY_ARGS;
        }

        List<Mono<Object>> argMonos = new ArrayList<>(parameters.length);
        for (MethodParameter parameter : parameters) {
            parameter.initParameterNameDiscovery(getParameterNameDiscoverer());
            Object providedArg = findProvidedArgument(parameter, providedArgs);
            if (providedArg != null) {
                argMonos.add(Mono.just(providedArg));
                continue;
            }
            if (!resolvers.supportsParameter(parameter)) {
                return Mono.error(new IllegalStateException(
                        formatArgumentError(parameter, "No suitable resolver")));
            }
            try {
                argMonos.add(resolvers.resolveArgument(parameter, bindingContext, exchange)
                        .defaultIfEmpty(NO_ARG_VALUE));
            } catch (Exception ex) {
                argMonos.add(Mono.error(ex));
            }
        }
        return Mono.zip(argMonos, values ->
                Stream.of(values).map(value -> value != NO_ARG_VALUE ? value : null).toArray());
    }

    private boolean isAsyncVoidReturnType(MethodParameter returnType, @Nullable ReactiveAdapter adapter) {
        if (adapter != null && adapter.supportsEmpty()) {
            if (adapter.isNoValue()) {
                return true;
            }
            Type parameterType = returnType.getGenericParameterType();
            if (parameterType instanceof ParameterizedType) {
                ParameterizedType type = (ParameterizedType) parameterType;
                if (type.getActualTypeArguments().length == 1) {
                    return Void.class.equals(type.getActualTypeArguments()[0]);
                }
            }
        }
        return false;
    }

    private boolean isResponseHandled(Object[] args, ServerWebExchange exchange) {
        if (getResponseStatus() != null || exchange.isNotModified()) {
            return true;
        }
        for (Object arg : args) {
            if (arg instanceof ServerHttpResponse || arg instanceof ServerWebExchange) {
                return true;
            }
        }
        return false;
    }

}