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

package im.turms.service.workflow.access.http.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.reactive.result.method.InvocableHandlerMethod;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.reactive.result.method.annotation.TurmsControllerMethodResolver;
import org.springframework.web.reactive.result.method.annotation.TurmsInitBinderBindingContext;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * The class is used to add our custom handlers around the method invoke
 * because {@link WebFilter} isn't what we want as we need the final parsed parameters
 * and avoid using AOP, which is an awful design with a terrible performance.
 *
 * @author James Chen
 * @implNote Replace the bean {@link WebFluxConfigurationSupport#requestMappingHandlerAdapter}
 */
public class TurmsRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {

    private ConfigurableApplicationContext context;
    private TurmsControllerMethodResolver methodResolver;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        if (applicationContext instanceof ConfigurableApplicationContext) {
            context = (ConfigurableApplicationContext) applicationContext;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        methodResolver = new TurmsControllerMethodResolver(getArgumentResolverConfigurer(),
                getReactiveAdapterRegistry(),
                context,
                getMessageReaders());
    }

    @Override
    public Mono<HandlerResult> handle(ServerWebExchange exchange, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        TurmsInitBinderBindingContext bindingContext = new TurmsInitBinderBindingContext(
                getWebBindingInitializer(), methodResolver.getInitBinderMethods(handlerMethod));

        InvocableHandlerMethod invocableMethod = methodResolver.getRequestMappingMethod(handlerMethod);

        Function<Throwable, Mono<HandlerResult>> exceptionHandler =
                ex -> handleException(ex, handlerMethod, bindingContext, exchange);

        return Mono.defer(() -> invocableMethod.invoke(exchange, bindingContext))
                .doOnNext(result -> result.setExceptionHandler(exceptionHandler))
                .onErrorResume(exceptionHandler);
    }

    private Mono<HandlerResult> handleException(Throwable exception, HandlerMethod handlerMethod,
                                                BindingContext bindingContext, ServerWebExchange exchange) {
        // Success and error responses may use different content types
        exchange.getAttributes().remove(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);
        exchange.getResponse().getHeaders().clearContentHeaders();

        InvocableHandlerMethod invocable = this.methodResolver.getExceptionHandlerMethod(exception, handlerMethod);
        if (invocable != null) {
            try {
                bindingContext.getModel().asMap().clear();
                Throwable cause = exception.getCause();
                if (cause != null) {
                    return invocable.invoke(exchange, bindingContext, exception, cause, handlerMethod);
                } else {
                    return invocable.invoke(exchange, bindingContext, exception, handlerMethod);
                }
            } catch (Exception e) {
            }
        }
        return Mono.error(exception);
    }
}
