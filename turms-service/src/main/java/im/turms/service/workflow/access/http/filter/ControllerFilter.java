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

package im.turms.service.workflow.access.http.filter;

import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.logging.AdminApiLogging;
import im.turms.server.common.logging.RequestLoggingContext;
import im.turms.server.common.property.env.service.env.adminapi.LogProperties;
import im.turms.server.common.tracing.TracingCloseableContext;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.MapUtil;
import im.turms.service.bo.AdminAction;
import im.turms.service.plugin.extension.AdminActionHandler;
import im.turms.service.plugin.TurmsPluginManager;
import im.turms.service.workflow.access.http.permission.RequiredPermission;
import im.turms.service.workflow.service.impl.admin.AdminService;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.webflux.api.OpenApiWebfluxResource;
import org.springdoc.webflux.ui.SwaggerWelcomeWebFlux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.MethodInvokeInterceptor;
import org.springframework.web.reactive.result.method.TurmsHandlerMethod;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.WWW_AUTHENTICATE;

/**
 * @author James Chen
 */
@Component
public class ControllerFilter implements WebFilter {

    private static final String BASIC_AUTH_PREFIX = "Basic ";
    private static final String ATTRIBUTES_ACCOUNT = "account";

    private final Node node;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private final AdminService adminService;
    private final TurmsPluginManager turmsPluginManager;
    private final boolean pluginEnabled;
    private final boolean enableAdminApi;
    private final boolean isOpenApiEnabled;

    /**
     * @param springDocConfigProperties {@link org.springdoc.core.SpringDocConfiguration}
     */
    public ControllerFilter(
            Node node,
            RequestMappingHandlerMapping requestMappingHandlerMapping,
            AdminService adminService,
            TurmsPluginManager turmsPluginManager,
            @Autowired(required = false) SpringDocConfigProperties springDocConfigProperties) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
        this.adminService = adminService;
        this.node = node;
        this.turmsPluginManager = turmsPluginManager;
        pluginEnabled = node.getSharedProperties().getPlugin().isEnabled();
        enableAdminApi = node.getSharedProperties().getService().getAdminApi().isEnabled();
        isOpenApiEnabled = springDocConfigProperties != null && springDocConfigProperties.getApiDocs().isEnabled();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        allowAnyRequest(exchange.getResponse());
        return requestMappingHandlerMapping.getHandler(exchange)
                .switchIfEmpty(Mono.defer(() -> filterUnhandledRequest(exchange, chain)))
                .flatMap(o -> {
                    if (o instanceof TurmsHandlerMethod method) {
                        return filterHandlerMethod(method, exchange, chain);
                    }
                    if (o instanceof HandlerMethod) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SERVER_INTERNAL_ERROR,
                                "The handler method should be TurmsHandlerMethod instead of HandlerMethod"));
                    }
                    return filterUnhandledRequest(exchange, chain);
                });
    }

    private Mono<Void> filterHandlerMethod(TurmsHandlerMethod handlerMethod, ServerWebExchange exchange, WebFilterChain chain) {
        if (isOpenApiEnabledAndOpenApiRequest(handlerMethod)) {
            return chain.filter(exchange);
        }
        RequiredPermission requiredPermission = handlerMethod.getMethodAnnotation(RequiredPermission.class);
        if (requiredPermission == null) {
            return chain.filter(exchange);
        }
        if (!enableAdminApi) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return Mono.empty();
        }
        String[] credentials = parseAccountAndPassword(exchange);
        if (credentials == null) {
            return respondWith401(exchange.getResponse());
        }
        String account = credentials[0];
        String password = credentials[1];
        exchange.getAttributes().put(ATTRIBUTES_ACCOUNT, account);
        return adminService.authenticate(account, password)
                .flatMap(authenticated -> {
                    if (authenticated == null || !authenticated) {
                        return respondWith401(exchange.getResponse());
                    }
                    return adminService.isAdminAuthorized(exchange, account, requiredPermission.value())
                            .flatMap(authorized -> authorized
                                    ? tryPersistAndPass(account, exchange, chain, handlerMethod)
                                    : respondWith401(exchange.getResponse()));
                });
    }

    /**
     * metrics APIs and swagger resources APIs don't have HandlerMethod instances
     */
    private Mono<Void> filterUnhandledRequest(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        if (CorsUtils.isPreFlightRequest(exchange.getRequest())) {
            response.setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }
        if (!enableAdminApi) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return Mono.empty();
        }
        if (isOpenApiEnabledAndOpenApiRequest(exchange)) {
            return chain.filter(exchange);
        }
        String[] credentials = parseAccountAndPassword(exchange);
        if (credentials == null) {
            return respondWith401(response);
        }
        String account = credentials[0];
        String password = credentials[1];
        exchange.getAttributes().put(ATTRIBUTES_ACCOUNT, account);
        return adminService.authenticate(account, password)
                .flatMap(authenticated -> authenticated
                        ? chain.filter(exchange)
                        : respondWith401(response));
    }

    /**
     * 1. We don't expose configs for developers to customize the cors config
     * because it's better to be done by firewall/ECS/EC2
     * 2. Note that no only CORS requests but also some normal requests need these headers
     */
    private void allowAnyRequest(ServerHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        headers.set(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "7200");
    }

    private Mono<Void> respondWith401(ServerHttpResponse response) {
        response.getHeaders().set(WWW_AUTHENTICATE, "Basic realm=" + node.getNodeType().getDisplayName());
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return Mono.empty();
    }

    private String[] parseAccountAndPassword(@NotNull ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith(BASIC_AUTH_PREFIX)) {
            return null;
        }
        try {
            String encodedCredentials = authorization.substring(BASIC_AUTH_PREFIX.length());
            byte[] decode = Base64.getDecoder().decode(encodedCredentials);
            return StringUtils.split(new String(decode), ":");
        } catch (Exception e) {
            return null;
        }
    }

    private Mono<Void> tryPersistAndPass(
            String account,
            ServerWebExchange exchange,
            WebFilterChain chain,
            TurmsHandlerMethod handlerMethod) {
        LogProperties logProperties = node.getSharedProperties().getService().getAdminApi().getLog();
        boolean isLogEnabled = logProperties.isEnabled();
        boolean triggerHandlers = pluginEnabled && !turmsPluginManager.getAdminActionHandlerList().isEmpty();
        TracingContext tracingContext = new TracingContext();
        RequestLoggingContext loggingContext = new RequestLoggingContext(tracingContext);
        if (isLogEnabled || triggerHandlers) {
            ServerHttpRequest request = exchange.getRequest();
            String action = handlerMethod.getMethod().getName();
            String host = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
            long requestTime = System.currentTimeMillis();
            exchange.getAttributes().put(MethodInvokeInterceptor.ATTRIBUTE_INTERCEPTOR, new MethodInvokeInterceptor() {
                @Nullable
                @Override
                public Object beforeInvoke(ServerWebExchange exchange, Method method, Object[] args) {
                    if (method.isAnnotationPresent(DeleteMapping.class) && !isValidDeleteRequest(args)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NO_FILTER_FOR_DELETE_OPERATION));
                    }
                    return null;
                }

                @Override
                public Object afterInvoke(ServerWebExchange exchange, Method method, Object[] args,
                                          @Nullable Object response, @Nullable Throwable t) {
                    int processingTime = (int) (System.currentTimeMillis() - requestTime);
                    return logAndTriggerHandlers(handlerMethod,
                            tracingContext,
                            account,
                            host,
                            requestTime,
                            processingTime,
                            action,
                            args,
                            response,
                            t);
                }
            });
        }
        return chain.filter(exchange)
                .contextWrite(context -> {
                    exchange.getAttributes().put(RequestLoggingContext.CTX_KEY_NAME, loggingContext);
                    return context.put(RequestLoggingContext.CTX_KEY_NAME, loggingContext);
                });
    }

    private boolean isOpenApiEnabledAndOpenApiRequest(HandlerMethod handlerMethod) {
        if (!isOpenApiEnabled) {
            return false;
        }
        Class<?> beanType = handlerMethod.getBeanType();
        return OpenApiWebfluxResource.class.equals(beanType) || SwaggerWelcomeWebFlux.class.equals(beanType);
    }

    private boolean isOpenApiEnabledAndOpenApiRequest(ServerWebExchange exchange) {
        return isOpenApiEnabled && exchange.getRequest().getURI().getPath().startsWith("/webjars/swagger-ui");
    }

    private boolean isValidDeleteRequest(Object[] args) {
        if (node.getSharedProperties().getService().getAdminApi().isAllowDeleteWithoutFilter()) {
            return true;
        }
        return !Validator.areAllFalsy(args);
    }

    private Mono<?> logAndTriggerHandlers(
            TurmsHandlerMethod handlerMethod,
            TracingContext tracingContext,
            String account,
            String ip,
            long requestTime,
            int processingTime,
            String action,
            Object[] args,
            Object response,
            Throwable throwable) {
        if (response instanceof Mono<?> responseMono) {
            return responseMono
                    .doOnEach(signal -> {
                        Throwable t;
                        if (signal.isOnComplete() || signal.isOnError()) {
                            t = signal.getThrowable();
                        } else {
                            return;
                        }
                        logAndTriggerHandlers0(handlerMethod,
                                tracingContext,
                                account,
                                ip,
                                requestTime,
                                action,
                                args,
                                processingTime,
                                t);
                    });
        } else if (response instanceof Flux) {
            throw new IllegalStateException("Unexpected response type: Flux. Use Mono instead");
        } else {
            logAndTriggerHandlers0(handlerMethod,
                    tracingContext,
                    account,
                    ip,
                    requestTime,
                    action,
                    args,
                    processingTime,
                    throwable);
        }
        return null;
    }

    private void logAndTriggerHandlers0(
            TurmsHandlerMethod handlerMethod,
            TracingContext tracingContext,
            String account,
            String ip,
            long requestTime,
            String action,
            Object[] args,
            int processingTime,
            Throwable throwable) {
        Map<String, Object> params = new HashMap<>(MapUtil.getCapability(args.length));
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                continue;
            }
            MethodParameter parameter = handlerMethod.getMethodParameters()[i];
            params.put(parameter.getParameterName(), arg);
        }
        boolean triggerHandlers = turmsPluginManager.isEnabled() && !turmsPluginManager.getAdminActionHandlerList().isEmpty();
        if (triggerHandlers) {
            Mono<Void> handleAdminAction = Mono.empty();
            AdminAction adminAction = new AdminAction(
                    account,
                    ip,
                    new Date(requestTime),
                    action,
                    params,
                    processingTime);
            for (AdminActionHandler handler : turmsPluginManager.getAdminActionHandlerList()) {
                handleAdminAction = handleAdminAction
                        .then(Mono.defer(() -> handler.handleAdminAction(adminAction)));
            }
            handleAdminAction.subscribe();
        }
        try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
            AdminApiLogging.log(
                    account,
                    ip,
                    requestTime,
                    action,
                    params,
                    processingTime,
                    throwable);
        }
    }
}