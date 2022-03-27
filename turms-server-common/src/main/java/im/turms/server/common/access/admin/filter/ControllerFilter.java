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

package im.turms.server.common.access.admin.filter;

import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.throttle.BaseAdminApiRateLimitingManager;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.admin.service.BaseAdminService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.plugin.extension.AdminActionHandler;
import im.turms.server.common.infra.property.env.common.adminapi.CommonAdminApiProperties;
import im.turms.server.common.infra.property.env.common.adminapi.LogProperties;
import im.turms.server.common.infra.tracing.TracingContext;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.webflux.api.OpenApiWebfluxResource;
import org.springdoc.webflux.ui.SwaggerWelcomeWebFlux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.MethodInvokeInterceptor;
import org.springframework.web.reactive.result.method.TurmsHandlerMethod;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.WWW_AUTHENTICATE;

/**
 * @author James Chen
 */
@Component
public class ControllerFilter implements WebFilter {

    private static final String X_REQUEST_ID = "X-Request-ID";

    private static final String BASIC_AUTH_PREFIX = "Basic ";
    private static final String ATTRIBUTES_ACCOUNT = "account";

    private final Node node;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private final BaseAdminApiRateLimitingManager adminApiRateLimitingManager;
    private final BaseAdminService adminService;
    private final PluginManager pluginManager;
    private final boolean enableAdminApi;
    private final boolean isOpenApiEnabled;

    /**
     * @param springDocConfigProperties {@link org.springdoc.core.SpringDocConfiguration}
     */
    public ControllerFilter(
            Node node,
            RequestMappingHandlerMapping requestMappingHandlerMapping,
            BaseAdminApiRateLimitingManager adminApiRateLimitingManager,
            BaseAdminService adminService,
            PluginManager pluginManager,
            @Autowired(required = false) SpringDocConfigProperties springDocConfigProperties) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
        this.adminApiRateLimitingManager = adminApiRateLimitingManager;
        this.adminService = adminService;
        this.node = node;
        this.pluginManager = pluginManager;
        enableAdminApi = getCommonAdminApiProperties().isEnabled();
        isOpenApiEnabled = springDocConfigProperties != null && springDocConfigProperties.getApiDocs().isEnabled();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders headers = exchange.getResponse().getHeaders();
        allowAnyRequest(headers);
        headers.set(X_REQUEST_ID, exchange.getRequest().getId());
        return requestMappingHandlerMapping.getHandler(exchange)
                .switchIfEmpty(Mono.defer(() -> filterUnhandledRequest(exchange, chain)))
                .flatMap(o -> {
                    if (o instanceof TurmsHandlerMethod method) {
                        return filterHandlerMethod(method, exchange, chain);
                    }
                    if (o instanceof HandlerMethod) {
                        return Mono.error(ResponseException.get(ResponseStatusCode.SERVER_INTERNAL_ERROR,
                                "The handler method should be TurmsHandlerMethod instead of HandlerMethod"));
                    }
                    return filterUnhandledRequest(exchange, chain);
                });
    }

    private Mono<Void> filterHandlerMethod(TurmsHandlerMethod handlerMethod, ServerWebExchange exchange, WebFilterChain chain) {
        if (isOpenApiEnabledAndOpenApiRequest(handlerMethod)) {
            return checkFrequencyAndPass(chain, exchange);
        }
        RequiredPermission requiredPermission = handlerMethod.getMethodAnnotation(RequiredPermission.class);
        if (requiredPermission == null) {
            return checkFrequencyAndPass(chain, exchange);
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
     * Actuator APIs and swagger resources APIs don't have HandlerMethod instances.
     * By the way, we have removed metrics endpoint of actuator because of its terrible performance
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
            return checkFrequencyAndPass(chain, exchange);
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
                        ? checkFrequencyAndPass(chain, exchange)
                        : respondWith401(response));
    }

    /**
     * 1. We don't expose configs for developers to customize the CORS config
     * because it's better to be done by firewall/ECS/EC2
     * 2. Note that both CORS requests and some normal requests need these headers
     */
    private void allowAnyRequest(HttpHeaders headers) {
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        headers.set(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "7200");
    }

    private Mono<Void> checkFrequencyAndPass(WebFilterChain chain, ServerWebExchange exchange) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        if (adminApiRateLimitingManager.tryAcquireTokenByIp(ip)) {
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return Mono.empty();
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
        LogProperties logProperties = getCommonAdminApiProperties().getLog();
        boolean isLogEnabled = logProperties.isEnabled();
        TracingContext tracingContext = new TracingContext();
        if (isLogEnabled || pluginManager.hasRunningExtensions(AdminActionHandler.class)) {
            long requestTime = System.currentTimeMillis();
            ServerHttpRequest request = exchange.getRequest();
            String action = handlerMethod.getMethod().getName();
            String ip = request.getRemoteAddress().getAddress().getHostAddress();
            boolean allowDeleteWithoutFilter = node.getNodeType() == NodeType.SERVICE
                    && node.getSharedProperties().getService().getAdminApi().isAllowDeleteWithoutFilter();
            exchange.getAttributes().put(MethodInvokeInterceptor.ATTRIBUTE_INTERCEPTOR,
                    new EndpointInvokeInterceptor(pluginManager,
                            allowDeleteWithoutFilter,
                            handlerMethod,
                            request.getId(),
                            requestTime,
                            tracingContext,
                            account,
                            ip,
                            action));
        }
        return checkFrequencyAndPass(chain, exchange)
                .contextWrite(context -> {
                    exchange.getAttributes().put(TracingContext.CTX_KEY_NAME, tracingContext);
                    return context.put(TracingContext.CTX_KEY_NAME, tracingContext);
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

    private CommonAdminApiProperties getCommonAdminApiProperties() {
        return node.getNodeType() == NodeType.GATEWAY
                ? node.getSharedProperties().getGateway().getAdminApi()
                : node.getSharedProperties().getService().getAdminApi();
    }

}