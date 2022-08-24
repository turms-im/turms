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

package im.turms.server.common.access.admin.web;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.throttle.BaseAdminApiRateLimitingManager;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.admin.bo.AdminAction;
import im.turms.server.common.domain.admin.service.BaseAdminService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.io.BaseFileResource;
import im.turms.server.common.infra.io.ByteBufFileResource;
import im.turms.server.common.infra.io.FileResource;
import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.json.JsonSizeCalculator;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.AdminApiLogging;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.plugin.extension.AdminActionHandler;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.adminapi.AdminHttpProperties;
import im.turms.server.common.infra.property.env.common.adminapi.CommonAdminApiProperties;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.buffer.DataBufferLimitException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.NettyOutbound;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

import javax.annotation.Nullable;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static im.turms.server.common.access.admin.web.HttpUtil.isPreFlightRequest;
import static im.turms.server.common.access.admin.web.MediaTypeConst.APPLICATION_JSON;
import static im.turms.server.common.access.admin.web.MediaTypeConst.APPLICATION_OCTET_STREAM;
import static im.turms.server.common.access.admin.web.MediaTypeConst.TEXT_PLAIN_UTF_8;

/**
 * @author James Chen
 */
@Component
public class HttpRequestDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestDispatcher.class);

    private static final String X_REQUEST_ID = "X-Request-ID";
    private static final Mono<Credentials> CREDENTIALS_ROOT = Mono.just(Credentials.ROOT);

    private final Node node;
    private final PluginManager pluginManager;
    private final BaseAdminApiRateLimitingManager adminApiRateLimitingManager;
    private final HttpRequestAuthenticator authenticator;

    private final DisposableServer server;
    @Getter
    private final Map<ApiEndpointKey, ApiEndpoint> keyToEndpoint;

    private final boolean useAuthentication;
    private boolean allowDeleteWithoutFilter;
    private boolean isLogEnabled;

    public HttpRequestDispatcher(Node node,
                                 ApplicationContext context,
                                 TurmsApplicationContext applicationContext,
                                 TurmsPropertiesManager propertiesManager,
                                 PluginManager pluginManager,
                                 BaseAdminApiRateLimitingManager adminApiRateLimitingManager,
                                 BaseAdminService adminService) {
        this.node = node;
        this.adminApiRateLimitingManager = adminApiRateLimitingManager;
        keyToEndpoint = HttpEndpointCollector.collectionEndpoints((ConfigurableApplicationContext) context);
        this.pluginManager = pluginManager;
        authenticator = new HttpRequestAuthenticator(adminService);

        CommonAdminApiProperties apiProperties = node.getNodeType() == NodeType.GATEWAY
                ? propertiesManager.getLocalProperties().getGateway().getAdminApi()
                : propertiesManager.getLocalProperties().getService().getAdminApi();
        useAuthentication = apiProperties.isUseAuthentication();
        if (apiProperties.isEnabled()) {
            propertiesManager.triggerAndAddGlobalPropertiesChangeListener(this::updateGlobalProperties);
            AdminHttpProperties httpProperties = apiProperties.getHttp();
            server = HttpServerFactory.createHttpServer(httpProperties)
                    .handle((request, response) -> {
                        handle(request, response)
                                .subscribe(null, t ->
                                        LOGGER.error("Caught an error while handling the HTTP request: " + request, t));
                        return Mono.never();
                    })
                    .bindNow(Duration.ofMinutes(1));
            applicationContext.addShutdownHook(JobShutdownOrder.CLOSE_ADMIN_SERVER, timeoutMillis -> {
                server.dispose();
                return server.onDispose();
            });
        } else {
            server = null;
        }
    }

    //region Properties
    private void updateGlobalProperties(TurmsProperties properties) {
        boolean isGateway = node.getNodeType() == NodeType.GATEWAY;
        CommonAdminApiProperties apiProperties = isGateway
                ? properties.getGateway().getAdminApi()
                : properties.getService().getAdminApi();
        allowDeleteWithoutFilter = !isGateway && properties.getService().getAdminApi().isAllowDeleteWithoutFilter();
        isLogEnabled = apiProperties.getLog().isEnabled();
    }
    //endregion

    //region Dispatch
    private Mono<Void> handle(HttpServerRequest request, HttpServerResponse response) {
        // 1. We don't expose configs for developers to customize the CORS config
        // because it's better to be done by firewall/ECS/EC2
        // 2. Note that both CORS requests and some other requests need these headers
        HttpUtil.allowAnyRequest(response.responseHeaders()
                .set(X_REQUEST_ID, request.requestId()));
        if (isPreFlightRequest(request)) {
            return response
                    .status(HttpResponseStatus.OK)
                    .send();
        }
        long requestTime = System.currentTimeMillis();
        TracingContext tracingContext = new TracingContext();
        RequestContext requestContext = new RequestContext();
        String ip = request.remoteAddress().getAddress().getHostAddress();
        Mono<HttpHandlerResult<?>> handleRequest;
        try {
            handleRequest = handleRequest(request, ip, requestContext);
        } catch (Exception e) {
            handleRequest = Mono.error(e);
        }
        return handleRequest
                .doOnEach(signal -> {
                    if (!signal.isOnComplete() && !signal.isOnError()) {
                        return;
                    }
                    if (!isLogEnabled && !pluginManager.hasRunningExtensions(AdminActionHandler.class)) {
                        return;
                    }
                    tryLogAndTriggerHandlers(requestContext.getParams(),
                            tracingContext,
                            requestContext.getAccount(),
                            ip,
                            request.requestId(),
                            requestTime,
                            requestContext.getAction(),
                            requestContext.getParamValues(),
                            (int) (System.currentTimeMillis() - requestTime),
                            signal.getThrowable());
                })
                .onErrorResume(t -> {
                    HttpHandlerResult<ResponseDTO<?>> httpResponse = translateThrowable(t);
                    if (HttpUtil.isServerError(httpResponse.status())) {
                        try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
                            LOGGER.error("Caught an error while handling the HTTP request: " + request, t);
                        }
                    }
                    return Mono.just(httpResponse);
                })
                .flatMap(result -> sendResponse(requestContext.getEndpoint(), result, response))
                .onErrorResume(t -> {
                    try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
                        LOGGER.error("Caught an error while responding to the HTTP request: " + request, t);
                    }
                    return Mono.empty();
                })
                .contextWrite(context -> context.put(TracingContext.CTX_KEY_NAME, tracingContext));
    }
    //endregion

    //region Request processing
    private Mono<HttpHandlerResult<?>> handleRequest(HttpServerRequest request,
                                                     String ip,
                                                     RequestContext requestContext) {
        // 1. parse URI
        String uri = request.uri();
        Pair<String, Map<String, List<Object>>> pathAndQueryParams = FastUriParser.parsePathAndQueryParams(uri);
        ApiEndpoint endpoint = keyToEndpoint.get(new ApiEndpointKey(pathAndQueryParams.first(), request.method()));
        if (endpoint == null) {
            return Mono.just(HttpHandlerResult.create(HttpResponseStatus.BAD_REQUEST,
                    new ResponseDTO<>(ResponseStatusCode.ILLEGAL_ARGUMENT, "There is no any resources matched to request path: " + uri)));
        }
        // 2. prepare request context
        requestContext.setEndpoint(endpoint);
        requestContext.setAction(endpoint.method().getName());
        requestContext.setParams(endpoint.parameters());
        // 3. check frequency
        return checkFrequency(ip)
                // 4. parse request parameters
                .then(Mono.defer(() -> HttpRequestParamParser.parse(request,
                        requestContext,
                        pathAndQueryParams.second(),
                        endpoint.parameters())))
                .flatMap(params -> {
                    requestContext.setParamValues(params);
                    // 5. validate request
                    if (endpoint.method().isAnnotationPresent(DeleteMapping.class) && !isValidDeleteRequest(params)) {
                        return Mono.error(new HttpResponseException(HttpHandlerResult.create(HttpResponseStatus.BAD_REQUEST,
                                new ResponseDTO<>(ResponseStatusCode.NO_FILTER_FOR_DELETE_OPERATION))));
                    }
                    // 6. authenticate + authorize
                    Mono<Credentials> authenticate = useAuthentication
                            ? authenticator.authenticate(endpoint.parameters(),
                            params,
                            request.requestHeaders(),
                            endpoint.permission())
                            : CREDENTIALS_ROOT;
                    return authenticate
                            .flatMap(credentials -> {
                                requestContext.setAccount(credentials.account());
                                // 7. pass to handler
                                return invokeHandler(endpoint, params);
                            });
                });
    }

    private Mono<Void> checkFrequency(String ip) {
        if (adminApiRateLimitingManager.tryAcquireTokenByIp(ip)) {
            return Mono.empty();
        }
        HttpHandlerResult<ResponseDTO<?>> response = HttpHandlerResult.create(HttpResponseStatus.TOO_MANY_REQUESTS,
                new ResponseDTO<>(ResponseStatusCode.CLIENT_REQUESTS_TOO_FREQUENT,
                        "Too many requests from the IP address: " + ip));
        return Mono.error(new HttpResponseException(response));
    }

    private boolean isValidDeleteRequest(Object[] args) {
        return allowDeleteWithoutFilter || !Validator.areAllFalsy(args);
    }

    private Mono<HttpHandlerResult<?>> invokeHandler(ApiEndpoint endpoint, Object[] params) {
        Object returnValue;
        try {
            returnValue = endpoint.method().invoke(endpoint.controller(), params);
        } catch (IllegalAccessException e) {
            return Mono.error(e);
        } catch (InvocationTargetException e) {
            return Mono.error(e.getCause());
        }
        if (returnValue instanceof Flux<?>) {
            return Mono.just(HttpHandlerResult.create(HttpResponseStatus.INTERNAL_SERVER_ERROR,
                    "Unexpected response type: Flux. Use Mono instead"));
        }
        // "returnValue" isn't always ResponseDTO.
        // e.g. The endpoint "/metrics/prometheus" just returns String
        Mono<?> publisher = returnValue instanceof Mono<?> mono
                ? mono
                : Mono.just(returnValue);
        return publisher.map(value -> value instanceof HttpHandlerResult result
                ? result
                : HttpHandlerResult.create(HttpResponseStatus.OK, value));
    }
    //endregion

    //region Response processing
    private Mono<Void> sendResponse(@Nullable ApiEndpoint endpoint, HttpHandlerResult<?> result, HttpServerResponse response) {
        response.status(result.status());
        Map<String, String> headers = result.headers();
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                response.header(header.getKey(), header.getValue());
            }
        }
        Object body = result.body();
        if (body instanceof BaseFileResource resource) {
            HttpServerResponse preparedResponse = response
                    .header(HttpHeaderNames.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFileName())
                    .header(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaderNames.CONTENT_LENGTH, String.valueOf(resource.getSize()));
            NettyOutbound outbound;
            if (body instanceof FileResource fileResource) {
                outbound = preparedResponse.sendFile(fileResource.getFile());
            } else {
                outbound = preparedResponse.sendObject(((ByteBufFileResource) resource).getBuffer());
            }
            return outbound
                    .then()
                    .doOnEach(signal -> {
                        if (signal.isOnComplete() || signal.isOnError()) {
                            resource.cleanup(signal.getThrowable());
                        }
                    });
        }
        Pair<String, ByteBuf> mediaTypeAndBuffer = encodeResponseBody(body);
        String mediaType = endpoint == null || endpoint.mediaType() == null
                ? mediaTypeAndBuffer.first()
                : endpoint.mediaType();
        // TODO: cache common responses
        response.header(HttpHeaderNames.CONTENT_TYPE, mediaType);
        if (endpoint != null && endpoint.encoding() != null) {
            response.header(HttpHeaderNames.CONTENT_ENCODING, endpoint.encoding());
        }
        return response
                // Duplicate the buffer to use an independent reader index
                // because we don't want to modify the reader index of the original buffer
                // if it's an unreleasable buffer internally, or it may be sent to multiple endpoints.
                // Note that the content of the buffer is not copied, so "duplicate()" is efficient.
                .sendObject(mediaTypeAndBuffer.second().duplicate())
                .then();
    }

    private static Pair<String, ByteBuf> encodeResponseBody(Object body) {
        if (body instanceof ByteBuf buffer) {
            return Pair.of(APPLICATION_OCTET_STREAM, buffer);
        } else if (body instanceof CharSequence sequence) {
            byte[] bytes = StringUtil.getUTF8Bytes(sequence.toString());
            return Pair.of(TEXT_PLAIN_UTF_8,
                    PooledByteBufAllocator.DEFAULT.buffer(bytes.length).writeBytes(bytes));
        }
        int estimatedSize = JsonSizeCalculator.estimateJson(body);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(estimatedSize);
        OutputStream byteBuilder = new ByteBufOutputStream(buffer);
        try {
            JsonCodecPool.MAPPER.writeValue(byteBuilder, body);
            return Pair.of(APPLICATION_JSON, buffer);
        } catch (IOException e) {
            try {
                byteBuilder.close();
            } catch (IOException ex) {
            }
            throw new RuntimeException(e);
        }
    }

    private static HttpHandlerResult<ResponseDTO<?>> translateThrowable(Throwable throwable) {
        if (throwable instanceof HttpResponseException e) {
            return e.getResponse();
        } else if (throwable instanceof ResponseException e) {
            ResponseStatusCode statusCode = e.getCode();
            String reason = e.getReason();
            if (reason == null) {
                reason = statusCode.getReason();
            }
            return HttpHandlerResult.create(statusCode.getHttpStatusCode(), new ResponseDTO<>(statusCode, reason));
        } else {
            ResponseStatusCode statusCode;
            if (throwable instanceof IllegalArgumentException || throwable instanceof ConstraintViolationException) {
                statusCode = ResponseStatusCode.ILLEGAL_ARGUMENT;
            } else if (throwable instanceof DuplicateKeyException) {
                statusCode = ResponseStatusCode.RECORD_CONTAINS_DUPLICATE_KEY;
            } else if (throwable instanceof DataBufferLimitException) {
                statusCode = ResponseStatusCode.FILE_TOO_LARGE;
            } else {
                statusCode = ResponseStatusCode.SERVER_INTERNAL_ERROR;
            }
            String reason = throwable.getMessage();
            return HttpHandlerResult.create(statusCode.getHttpStatusCode(), new ResponseDTO<>(statusCode, reason));
        }
    }
    //endregion

    //region Logging
    private void tryLogAndTriggerHandlers(
            @Nullable MethodParameterInfo[] parameters,
            TracingContext context,
            @Nullable String account,
            String ip,
            String requestId,
            long requestTime,
            @Nullable String action,
            @Nullable Object[] args,
            int processingTime,
            @Nullable Throwable throwable) {
        Map<String, Object> params;
        if (args == null) {
            params = Collections.emptyMap();
        } else {
            params = CollectionUtil.newMapWithExpectedSize(args.length);
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg == null || arg instanceof RequestContext) {
                    continue;
                }
                MethodParameterInfo parameter = parameters[i];
                params.put(parameter.name(), arg);
            }
        }
        AdminAction adminAction = new AdminAction(
                account,
                ip,
                new Date(requestTime),
                action,
                params,
                processingTime);
        pluginManager.invokeExtensionPoints(AdminActionHandler.class,
                        "handleAdminAction",
                        handler -> handler.handleAdminAction(adminAction))
                .subscribe(null, LOGGER::error);
        if (isLogEnabled) {
            try (TracingCloseableContext ignored = context.asCloseable()) {
                AdminApiLogging.log(
                        account,
                        ip,
                        requestId,
                        requestTime,
                        action,
                        params,
                        processingTime,
                        throwable);
            }
        }
    }
    //endregion

}
