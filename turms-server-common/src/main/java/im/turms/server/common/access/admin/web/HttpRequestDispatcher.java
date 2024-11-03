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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintViolationException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.NettyOutbound;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.throttle.BaseAdminApiRateLimitingManager;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.admin.bo.AdminAction;
import im.turms.server.common.domain.admin.constant.AdminConst;
import im.turms.server.common.domain.admin.service.BaseAdminService;
import im.turms.server.common.infra.application.JobShutdownOrder;
import im.turms.server.common.infra.application.TurmsApplicationContext;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.DuplicateResourceException;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.io.BaseFileResource;
import im.turms.server.common.infra.io.ByteBufFileResource;
import im.turms.server.common.infra.io.FileResource;
import im.turms.server.common.infra.io.ResourceNotFoundException;
import im.turms.server.common.infra.json.JsonUtil;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.StringBuilderPool;
import im.turms.server.common.infra.lang.StringBuilderWriter;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.AdminApiLogging;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.plugin.extension.AdminActionHandler;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.adminapi.AdminHttpProperties;
import im.turms.server.common.infra.property.env.common.adminapi.BaseAdminApiProperties;
import im.turms.server.common.infra.time.DurationConst;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;

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
    private static final Mono<Long> ROOT_ADMIN_ID = Mono.just(AdminConst.ROOT_ADMIN_ID);

    private static final Method HANDLE_ADMIN_ACTION_METHOD;

    private final Node node;
    private final PluginManager pluginManager;
    private final BaseAdminApiRateLimitingManager adminApiRateLimitingManager;
    private final HttpRequestAuthenticator authenticator;
    private final HttpEndpointCollector endpointCollector;
    private final HttpRequestParamParser requestParamParser;

    private final DisposableServer server;
    private final boolean isApiEnabled;
    @Getter
    private Map<ApiEndpointKey, ApiEndpoint> keyToEndpoint;
    private final List<Consumer<Map<ApiEndpointKey, ApiEndpoint>>> endpointChangeListeners;

    private final boolean useAuthentication;
    private boolean allowDeleteWithoutFilter;
    private boolean isLogEnabled;

    static {
        try {
            HANDLE_ADMIN_ACTION_METHOD = AdminActionHandler.class
                    .getDeclaredMethod("handleAdminAction", AdminAction.class);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleInternalChangeException(e);
        }
    }

    public HttpRequestDispatcher(
            Node node,
            ApplicationContext context,
            TurmsApplicationContext applicationContext,
            TurmsPropertiesManager propertiesManager,
            PluginManager pluginManager,
            BaseAdminApiRateLimitingManager adminApiRateLimitingManager,
            BaseAdminService adminService) {
        this.node = node;
        this.pluginManager = pluginManager;
        this.adminApiRateLimitingManager = adminApiRateLimitingManager;
        authenticator = new HttpRequestAuthenticator(adminService);

        TurmsProperties properties = propertiesManager.getLocalProperties();
        BaseAdminApiProperties apiProperties = switch (node.getNodeType()) {
            case AI_SERVING -> properties.getAiServing()
                    .getAdminApi();
            case GATEWAY -> properties.getGateway()
                    .getAdminApi();
            case SERVICE -> properties.getService()
                    .getAdminApi();
            case MOCK -> properties.getMockNode()
                    .getAdminApi();
        };
        useAuthentication = apiProperties.isUseAuthentication();
        isApiEnabled = apiProperties.isEnabled();
        if (isApiEnabled) {
            propertiesManager
                    .notifyAndAddGlobalPropertiesChangeListener(this::updateGlobalProperties);
            AdminHttpProperties httpProperties = apiProperties.getHttp();
            this.requestParamParser =
                    new HttpRequestParamParser(httpProperties.getMaxRequestBodySizeBytes());
            endpointCollector = new HttpEndpointCollector(requestParamParser);
            keyToEndpoint =
                    endpointCollector.collectEndpoints((ConfigurableApplicationContext) context);
            endpointChangeListeners = new CopyOnWriteArrayList<>();
            server = HttpServerFactory.createHttpServer(httpProperties)
                    .handle((request, response) -> {
                        handle(request, response).subscribe(null,
                                t -> LOGGER.error(
                                        "Caught an error while handling the HTTP request: {}",
                                        request,
                                        t));
                        return Mono.never();
                    })
                    .bindNow(DurationConst.ONE_MINUTE);
            applicationContext.addShutdownHook(JobShutdownOrder.CLOSE_ADMIN_SERVER,
                    timeoutMillis -> {
                        server.dispose();
                        return server.onDispose();
                    });
        } else {
            requestParamParser = null;
            endpointCollector = null;
            keyToEndpoint = null;
            endpointChangeListeners = null;
            server = null;
        }
    }

    public int port() {
        return server.port();
    }

    // region Properties
    private void updateGlobalProperties(TurmsProperties properties) {
        NodeType nodeType = node.getNodeType();
        BaseAdminApiProperties apiProperties = switch (nodeType) {
            case AI_SERVING -> properties.getAiServing()
                    .getAdminApi();
            case GATEWAY -> properties.getGateway()
                    .getAdminApi();
            case SERVICE -> properties.getService()
                    .getAdminApi();
            case MOCK -> properties.getMockNode()
                    .getAdminApi();
        };
        allowDeleteWithoutFilter = nodeType == NodeType.SERVICE
                && properties.getService()
                        .getAdminApi()
                        .isAllowDeleteWithoutFilter();
        isLogEnabled = apiProperties.getLog()
                .isEnabled();
    }
    // endregion

    // region Endpoint
    public void registerControllers(List<Object> controllers) {
        if (controllers.isEmpty() || !isApiEnabled) {
            return;
        }
        synchronized (this) {
            Map<ApiEndpointKey, ApiEndpoint> keyToEndpoint = this.keyToEndpoint;
            Map<ApiEndpointKey, ApiEndpoint> newKeyToEndpoint = CollectionUtil
                    .newMapWithExpectedSize(keyToEndpoint.size() + (controllers.size() << 3));
            newKeyToEndpoint.putAll(keyToEndpoint);
            endpointCollector.collectEndpoints(newKeyToEndpoint, controllers);
            this.keyToEndpoint = newKeyToEndpoint;
            notifyEndpointChangeListeners(newKeyToEndpoint);
        }
    }

    public void addEndpointChangeListener(Consumer<Map<ApiEndpointKey, ApiEndpoint>> listener) {
        if (isApiEnabled) {
            endpointChangeListeners.add(listener);
        }
    }

    private void notifyEndpointChangeListeners(Map<ApiEndpointKey, ApiEndpoint> keyToEndpoint) {
        if (!isApiEnabled) {
            return;
        }
        for (Consumer<Map<ApiEndpointKey, ApiEndpoint>> listener : endpointChangeListeners) {
            try {
                listener.accept(keyToEndpoint);
            } catch (Exception e) {
                LOGGER.error("Caught an error while notifying the endpoint change listener: "
                        + listener.getClass()
                                .getName());
            }
        }
    }

    private String findSimilarEndpoints(String uri) {
        Set<ApiEndpointKey> endpointKeys = keyToEndpoint.keySet();
        Map<String, Float> pathToSimilarity =
                CollectionUtil.newMapWithExpectedSize(endpointKeys.size());
        List<Pair<Float, ApiEndpointKey>> similarityToEndpointPairs = new ArrayList<>(8);
        for (ApiEndpointKey key : endpointKeys) {
            String path = key.path();
            float similarity =
                    pathToSimilarity.computeIfAbsent(path, p -> StringUtil.findSimilarity(p, uri));
            if (similarity > 0.5F) {
                similarityToEndpointPairs.add(Pair.of(similarity, key));
            }
        }
        similarityToEndpointPairs.sort((o1, o2) -> {
            int i = o2.first()
                    .compareTo(o1.first());
            if (i == 0) {
                return o2.second()
                        .method()
                        .name()
                        .compareTo(o1.second()
                                .method()
                                .name());
            }
            return i;
        });
        String similarEndpointsStr;
        try (StringBuilderWriter writer = StringBuilderPool.getWriter()) {
            writer.append('[');
            for (int i = 0, size = similarityToEndpointPairs.size(),
                    last = size - 1; i < size; i++) {
                Pair<Float, ApiEndpointKey> pair = similarityToEndpointPairs.get(i);
                writer.append(pair.second()
                        .toEndpointString());
                if (i != last) {
                    writer.append(", ");
                }
            }
            writer.append(']');
            similarEndpointsStr = writer.toString();
        }
        return similarEndpointsStr;
    }
    // endregion

    // region Dispatch
    private Mono<Void> handle(HttpServerRequest request, HttpServerResponse response) {
        // 1. We don't expose configs for developers to customize the CORS config
        // because it is better for the firewall to manage access.
        // 2. Note that both CORS requests and some other requests need these headers
        HttpUtil.allowAnyRequest(response.responseHeaders()
                .set(X_REQUEST_ID, request.requestId()));
        if (isPreFlightRequest(request)) {
            return response.status(HttpResponseStatus.OK)
                    .send();
        }
        long requestTime = System.currentTimeMillis();
        TracingContext tracingContext = new TracingContext();
        RequestContext requestContext = new RequestContext();
        String ip = request.remoteAddress()
                .getAddress()
                .getHostAddress();
        Mono<HttpHandlerResult<?>> handleRequest;
        try {
            handleRequest = handleRequest(request, ip, requestContext);
        } catch (Exception e) {
            handleRequest = Mono.error(e);
        }
        return handleRequest.doOnEach(signal -> {
            if (!signal.isOnComplete() && !signal.isOnError()) {
                return;
            }
            if (!isLogEnabled && !pluginManager.hasRunningExtensions(AdminActionHandler.class)) {
                return;
            }
            tryLogAndInvokeHandlers(requestContext.getParams(),
                    tracingContext,
                    requestContext.getRequesterId(),
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
                            LOGGER.error("Caught an error while handling the HTTP request: {}",
                                    request,
                                    t);
                        }
                    }
                    return Mono.just(httpResponse);
                })
                .flatMap(result -> sendResponse(requestContext.getEndpoint(), result, response))
                .onErrorResume(t -> {
                    try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
                        LOGGER.error("Caught an error while responding to the HTTP request: {}",
                                request,
                                t);
                    }
                    return Mono.empty();
                })
                .contextWrite(context -> context.put(TracingContext.CTX_KEY_NAME, tracingContext));
    }
    // endregion

    // region Request processing
    private Mono<HttpHandlerResult<?>> handleRequest(
            HttpServerRequest request,
            String ip,
            RequestContext requestContext) {
        // 1. parse URI
        String uri = request.uri();
        Pair<String, Map<String, List<Object>>> pathAndQueryParams =
                FastUriParser.parsePathAndQueryParams(uri);
        ApiEndpoint endpoint =
                keyToEndpoint.get(new ApiEndpointKey(pathAndQueryParams.first(), request.method()));
        if (endpoint == null) {
            String similarEndpointsStr = findSimilarEndpoints(uri);
            String reason = "There is no any resource matched to the endpoint: "
                    + request.method()
                            .name()
                    + " "
                    + uri
                    + ". Similar endpoints: "
                    + similarEndpointsStr;
            return Mono.just(HttpHandlerResult.create(HttpResponseStatus.BAD_REQUEST,
                    new ResponseDTO<>(ResponseStatusCode.ILLEGAL_ARGUMENT, reason)));
        }
        // 2. prepare request context
        requestContext.setEndpoint(endpoint);
        requestContext.setAction(endpoint.method()
                .getName());
        requestContext.setParams(endpoint.parameters());
        // 3. check frequency
        return checkFrequency(ip)
                // 4. parse request parameters
                .then(Mono.defer(() -> requestParamParser.parse(request,
                        requestContext,
                        pathAndQueryParams.second(),
                        endpoint.parameters())))
                .flatMap(collection -> Mono.defer(() -> {
                    Object[] params = collection.paramValues();
                    requestContext.setParamValues(params);
                    // 5. validate request
                    if (endpoint.method()
                            .isAnnotationPresent(DeleteMapping.class)
                            && !isValidDeleteRequest(params)) {
                        return Mono.error(new HttpResponseException(
                                HttpHandlerResult.create(HttpResponseStatus.BAD_REQUEST,
                                        new ResponseDTO<>(
                                                ResponseStatusCode.NO_FILTER_FOR_DELETE_OPERATION))));
                    }
                    // 6. authenticate + authorize
                    Mono<Long> authenticate = useAuthentication
                            ? authenticator
                                    .authenticate(endpoint.parameters(),
                                            params,
                                            request.requestHeaders(),
                                            endpoint.permission())
                                    .defaultIfEmpty(Long.MIN_VALUE)
                            : ROOT_ADMIN_ID;
                    return authenticate.flatMap(adminId -> {
                        if (adminId != Long.MIN_VALUE) {
                            requestContext.setRequesterId(adminId);
                        }
                        // 7. pass to handler
                        return invokeHandler(endpoint, params);
                    });
                })
                        .doFinally(signalType -> {
                            // 8. release
                            for (MultipartFile file : collection.tempFiles()) {
                                try {
                                    file.release();
                                } catch (Exception e) {
                                    LOGGER.error(
                                            "Caught an error while releasing the multipart file: {}",
                                            file.name(),
                                            e);
                                }
                            }
                        }));
    }

    private Mono<Void> checkFrequency(String ip) {
        if (adminApiRateLimitingManager.tryAcquireTokenByIp(ip)) {
            return Mono.empty();
        }
        HttpHandlerResult<ResponseDTO<?>> response =
                HttpHandlerResult.create(HttpResponseStatus.TOO_MANY_REQUESTS,
                        new ResponseDTO<>(
                                ResponseStatusCode.CLIENT_REQUESTS_TOO_FREQUENT,
                                "Too many requests from the IP address: "
                                        + ip));
        return Mono.error(new HttpResponseException(response));
    }

    private boolean isValidDeleteRequest(Object[] args) {
        return allowDeleteWithoutFilter || !Validator.areAllFalsy(args);
    }

    private Mono<HttpHandlerResult<?>> invokeHandler(ApiEndpoint endpoint, Object[] params) {
        Object returnValue;
        try {
            returnValue = endpoint.method()
                    .invoke(endpoint.controller(), params);
        } catch (InvocationTargetException e) {
            return Mono.error(e.getCause());
        } catch (Exception e) {
            return Mono.error(e);
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
        return publisher.map(value -> value instanceof HttpHandlerResult<?> result
                ? result
                : HttpHandlerResult.create(HttpResponseStatus.OK, value));
    }
    // endregion

    // region Response processing
    private Mono<Void> sendResponse(
            @Nullable ApiEndpoint endpoint,
            HttpHandlerResult<?> result,
            HttpServerResponse response) {
        response.status(result.status());
        Map<String, String> headers = result.headers();
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                response.header(header.getKey(), header.getValue());
            }
        }
        Object body = result.body();
        if (body instanceof BaseFileResource resource) {
            CharSequence mediaType = endpoint == null || endpoint.mediaType() == null
                    ? HttpHeaderValues.APPLICATION_OCTET_STREAM
                    : endpoint.mediaType();
            HttpServerResponse preparedResponse = response
                    .header(HttpHeaderNames.CONTENT_DISPOSITION,
                            "attachment; filename="
                                    + resource.getFileName())
                    .header(HttpHeaderNames.CONTENT_TYPE, mediaType)
                    .header(HttpHeaderNames.CONTENT_LENGTH, String.valueOf(resource.getSize()));
            NettyOutbound outbound = body instanceof FileResource fileResource
                    ? preparedResponse.sendFile(fileResource.getFile())
                    : preparedResponse.sendObject(((ByteBufFileResource) resource).getBuffer());
            return outbound.then()
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
                // if it is an unreleasable buffer internally, or it may be sent to multiple
                // endpoints.
                // Note that the content of the buffer is not copied, so "duplicate()" is efficient.
                .sendObject(mediaTypeAndBuffer.second()
                        .duplicate())
                .then();
    }

    private static Pair<String, ByteBuf> encodeResponseBody(Object body) {
        if (body instanceof ByteBuf buffer) {
            return Pair.of(APPLICATION_OCTET_STREAM, buffer);
        } else if (body instanceof CharSequence sequence) {
            byte[] bytes = StringUtil.getUtf8Bytes(sequence.toString());
            return Pair.of(TEXT_PLAIN_UTF_8,
                    PooledByteBufAllocator.DEFAULT.buffer(bytes.length)
                            .writeBytes(bytes));
        }
        return Pair.of(APPLICATION_JSON, JsonUtil.write(body));
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
            return HttpHandlerResult.create(statusCode.getHttpStatusCode(),
                    new ResponseDTO<>(statusCode, reason));
        } else {
            ResponseStatusCode statusCode = switch (throwable) {
                case IllegalArgumentException ignored -> ResponseStatusCode.ILLEGAL_ARGUMENT;
                case ConstraintViolationException ignored -> ResponseStatusCode.ILLEGAL_ARGUMENT;
                case DuplicateKeyException ignored ->
                    ResponseStatusCode.RECORD_CONTAINS_DUPLICATE_KEY;
                case DuplicateResourceException ignored -> ResponseStatusCode.DUPLICATE_RESOURCE;
                case ResourceNotFoundException ignored -> ResponseStatusCode.RESOURCE_NOT_FOUND;
                default -> ResponseStatusCode.SERVER_INTERNAL_ERROR;
            };
            String reason = throwable.getMessage();
            return HttpHandlerResult.create(statusCode.getHttpStatusCode(),
                    new ResponseDTO<>(statusCode, reason));
        }
    }
    // endregion

    // region Logging
    private void tryLogAndInvokeHandlers(
            @Nullable MethodParameterInfo[] parameters,
            TracingContext context,
            @Nullable Long adminId,
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
            int length = args.length;
            params = CollectionUtil.newMapWithExpectedSize(length);
            for (int i = 0; i < length; i++) {
                Object arg = args[i];
                if (arg == null || arg instanceof RequestContext) {
                    continue;
                }
                MethodParameterInfo parameter = parameters[i];
                params.put(parameter.name(), arg);
            }
        }
        AdminAction adminAction =
                new AdminAction(adminId, ip, new Date(requestTime), action, params, processingTime);
        pluginManager
                .invokeExtensionPointsSimultaneously(AdminActionHandler.class,
                        HANDLE_ADMIN_ACTION_METHOD,
                        handler -> handler.handleAdminAction(adminAction))
                .subscribe(null, LOGGER::error);
        if (isLogEnabled) {
            try (TracingCloseableContext ignored = context.asCloseable()) {
                AdminApiLogging.log(adminId,
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
    // endregion

}