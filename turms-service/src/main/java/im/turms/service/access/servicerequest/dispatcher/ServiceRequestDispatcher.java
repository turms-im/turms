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

package im.turms.service.access.servicerequest.dispatcher;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;
import jakarta.validation.constraints.NotNull;

import com.google.protobuf.CodedInputStream;
import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.TurmsRequestOrBuilder;
import im.turms.server.common.access.client.dto.request.TurmsRequestTypePool;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.access.servicerequest.dispatcher.IServiceRequestDispatcher;
import im.turms.server.common.access.servicerequest.dto.ServiceRequest;
import im.turms.server.common.access.servicerequest.dto.ServiceResponse;
import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.FastEnumMap;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.exception.ThrowableInfo;
import im.turms.server.common.infra.healthcheck.HealthCheckManager;
import im.turms.server.common.infra.healthcheck.ServerStatusManager;
import im.turms.server.common.infra.lang.ByteArrayWrapper;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.proto.ProtoDecoder;
import im.turms.server.common.infra.proto.ProtoEncoder;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.service.access.servicerequest.dto.ClientRequest;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.access.servicerequest.dto.ServiceResponseFactory;
import im.turms.service.domain.message.service.OutboundMessageService;
import im.turms.service.infra.logging.ApiLoggingContext;
import im.turms.service.infra.logging.ClientApiLogging;
import im.turms.service.infra.plugin.extension.ClientRequestTransformer;
import im.turms.service.infra.plugin.extension.RequestHandlerResultHandler;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_SESSION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_SESSION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.KIND_NOT_SET;
import static im.turms.server.common.infra.metrics.CommonMetricNameConst.CLIENT_REQUEST;
import static im.turms.server.common.infra.metrics.CommonMetricNameConst.CLIENT_REQUEST_TAG_TYPE;

/**
 * @author James Chen
 */
@Service
public class ServiceRequestDispatcher implements IServiceRequestDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequestDispatcher.class);

    private static final Method REQUEST_TRANSFORM_METHOD;
    private static final Method REQUEST_HANDLE_METHOD;
    private static final Method RESULT_BEFORE_NOTIFY_METHOD;
    private static final Method RESULT_AFTER_NOTIFY_METHOD;

    private final ApiLoggingContext apiLoggingContext;
    private final BlocklistService blocklistService;
    private final ServerStatusManager serverStatusManager;
    private final OutboundMessageService outboundMessageService;
    private final PluginManager pluginManager;

    private final FastEnumMap<TurmsRequest.KindCase, ClientRequestHandler> requestTypeToHandler;

    static {
        try {
            REQUEST_TRANSFORM_METHOD = ClientRequestTransformer.class.getDeclaredMethod("transform",
                    ClientRequest.class);
            REQUEST_HANDLE_METHOD =
                    ClientRequestHandler.class.getDeclaredMethod("handle", ClientRequest.class);
            RESULT_BEFORE_NOTIFY_METHOD =
                    RequestHandlerResultHandler.class.getDeclaredMethod("beforeNotify",
                            RequestHandlerResult.class,
                            Long.class,
                            DeviceType.class);
            RESULT_AFTER_NOTIFY_METHOD =
                    RequestHandlerResultHandler.class.getDeclaredMethod("afterNotify",
                            RequestHandlerResult.class,
                            Long.class,
                            DeviceType.class,
                            Set.class);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleInternalChangeException(e);
        }
    }

    public ServiceRequestDispatcher(
            ApiLoggingContext apiLoggingContext,
            ApplicationContext context,
            BlocklistService blocklistService,
            ServerStatusManager serverStatusManager,
            OutboundMessageService outboundMessageService,
            PluginManager pluginManager,
            TurmsPropertiesManager propertiesManager) {
        this.apiLoggingContext = apiLoggingContext;
        this.blocklistService = blocklistService;
        this.serverStatusManager = serverStatusManager;
        this.outboundMessageService = outboundMessageService;
        this.pluginManager = pluginManager;
        Set<TurmsRequest.KindCase> disabledEndpoints = propertiesManager.getLocalProperties()
                .getService()
                .getClientApi()
                .getDisabledEndpoints();
        requestTypeToHandler =
                getMappings((ConfigurableApplicationContext) context, disabledEndpoints);
        for (TurmsRequest.KindCase requestType : TurmsRequestTypePool.ALL) {
            if (!requestTypeToHandler.containsKey(requestType)
                    && !isRequestForGateway(requestType)) {
                throw new RuntimeException(
                        "No client request handler for the request type: "
                                + requestType);
            }
        }
    }

    private FastEnumMap<TurmsRequest.KindCase, ClientRequestHandler> getMappings(
            ConfigurableApplicationContext context,
            Set<TurmsRequest.KindCase> disabledEndpoints) {
        FastEnumMap<TurmsRequest.KindCase, ClientRequestHandler> mappingMap =
                new FastEnumMap<>(TurmsRequest.KindCase.class);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        String[] definitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : definitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isAbstract()) {
                continue;
            }
            ServiceRequestMapping requestMapping =
                    beanFactory.findAnnotationOnBean(beanName, ServiceRequestMapping.class);
            if (requestMapping == null) {
                continue;
            }
            ClientRequestHandler beanInstance =
                    (ClientRequestHandler) beanFactory.getBean(beanName);
            TurmsRequest.KindCase endpointType = requestMapping.value();
            if (!disabledEndpoints.contains(endpointType)) {
                mappingMap.put(endpointType, beanInstance);
            }
        }
        return mappingMap;
    }

    private boolean isRequestForGateway(TurmsRequest.KindCase type) {
        return type == CREATE_SESSION_REQUEST || type == DELETE_SESSION_REQUEST;
    }

    /**
     * @implNote 1. Flow Control: turms-gateway is responsible for the rate limiting of client
     *           requests and {@link HealthCheckManager} ensures that when dispatch() is called the
     *           status of the local node is healthy, so we don't need to check the request rate
     *           here.
     *           <p>
     *           2. The method should never return MonoError, and it should be considered as a bug
     *           if it occurs because the method itself should map all kinds of Throwable to a
     *           ServiceResponse instance. 3. The method ensures turmsRequestBuffer in
     *           serviceRequest will be released by 1
     */
    @Override
    public Mono<ServiceResponse> dispatch(TracingContext context, ServiceRequest serviceRequest) {
        ByteBuf requestBuffer = serviceRequest.getTurmsRequestBuffer();
        try {
            requestBuffer.touch(serviceRequest);
            return dispatch0(context, serviceRequest);
        } catch (Exception e) {
            LOGGER.error("Failed to handle the request: {}", serviceRequest, e);
            return Mono.just(ServiceResponseFactory.get(ResponseStatusCode.SERVER_INTERNAL_ERROR,
                    e.toString()));
        } finally {
            requestBuffer.release();
        }
    }

    private Mono<ServiceResponse> dispatch0(TracingContext context, ServiceRequest serviceRequest) {
        long requestTime = System.currentTimeMillis();
        // 1. Validate ServiceResponse
        Long userId = serviceRequest.getUserId();
        DeviceType deviceType = serviceRequest.getDeviceType();
        if (userId == null) {
            String message = "The user ID is missing in the request: "
                    + serviceRequest;
            return Mono.just(
                    new ServiceResponse(null, ResponseStatusCode.SERVER_INTERNAL_ERROR, message));
        }
        if (deviceType == null) {
            String message = "The device type is missing in the request: "
                    + serviceRequest;
            return Mono.just(
                    new ServiceResponse(null, ResponseStatusCode.SERVER_INTERNAL_ERROR, message));
        }
        if (!serverStatusManager.isActive()) {
            return Mono.just(ServiceResponseFactory.get(ResponseStatusCode.SERVER_UNAVAILABLE));
        }
        boolean hasRunningExtensions =
                pluginManager.hasRunningExtensions(ClientRequestTransformer.class);
        TurmsRequestOrBuilder request;
        ByteBuf turmsRequestBuffer = serviceRequest.getTurmsRequestBuffer();
        int requestSize = turmsRequestBuffer.readableBytes();
        try {
            // Note that "mergeFrom" won't block because the buffer is fully read
            CodedInputStream stream = ProtoDecoder.newInputStream(turmsRequestBuffer);
            request = hasRunningExtensions
                    ? TurmsRequest.newBuilder()
                            .mergeFrom(stream)
                    : ClientMessagePool.getTurmsRequestBuilder()
                            .mergeFrom(stream)
                            .build();
        } catch (IOException e) {
            blocklistService
                    .tryBlockIpForCorruptedRequest(new ByteArrayWrapper(serviceRequest.getIp()));
            blocklistService.tryBlockUserIdForCorruptedRequest(serviceRequest.getUserId());
            return Mono.just(
                    ServiceResponseFactory.get(ResponseStatusCode.INVALID_REQUEST, e.getMessage()));
        }
        turmsRequestBuffer.touch(request);

        // 2. Transform and handle the request
        Mono<ClientRequest> clientRequestMono;
        if (hasRunningExtensions) {
            ClientRequest clientRequest = new ClientRequest(
                    serviceRequest.getUserId(),
                    serviceRequest.getDeviceType(),
                    serviceRequest.getIp(),
                    request.getRequestId(),
                    (TurmsRequest.Builder) request,
                    null);
            clientRequestMono = pluginManager
                    .invokeExtensionPointsSequentially(ClientRequestTransformer.class,
                            REQUEST_TRANSFORM_METHOD,
                            clientRequest,
                            (transformer, req) -> req.flatMap(transformer::transform))
                    .defaultIfEmpty(clientRequest);
        } else {
            clientRequestMono = Mono.just(new ClientRequest(
                    serviceRequest.getUserId(),
                    serviceRequest.getDeviceType(),
                    serviceRequest.getIp(),
                    request.getRequestId(),
                    null,
                    (TurmsRequest) request));
        }
        return clientRequestMono.flatMap(lastClientRequest -> {
            // 3. Validate ClientRequest
            TurmsRequest lastRequest = lastClientRequest.turmsRequest();
            if (lastRequest == null) {
                String message = "The TurmsRequest instance is null in the client request: "
                        + lastClientRequest;
                return Mono.just(ServiceResponseFactory
                        .get(ResponseStatusCode.SERVER_INTERNAL_ERROR, message));
            }
            TurmsRequest.KindCase requestType = lastRequest.getKindCase();
            if (requestType == KIND_NOT_SET) {
                return Mono.just(ServiceResponseFactory.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The request type cannot be KIND_NOT_SET"));
            }
            ClientRequestHandler handler = requestTypeToHandler.get(requestType);
            if (handler == null) {
                return Mono.just(ServiceResponseFactory.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The request type is unsupported"));
            }
            // 4. Pass the request to the controller and get a response
            Mono<RequestHandlerResult> result = pluginManager.invokeExtensionPointsSequentially(
                    im.turms.service.infra.plugin.extension.ClientRequestHandler.class,
                    REQUEST_HANDLE_METHOD,
                    (requestHandler, pre) -> pre.switchIfEmpty(
                            Mono.defer(() -> requestHandler.handle(lastClientRequest))));
            result = result.switchIfEmpty(Mono.defer(() -> handler.handle(lastClientRequest)));
            // 5. Metrics and transform to ServiceResponse
            return result.name(CLIENT_REQUEST)
                    .tag(CLIENT_REQUEST_TAG_TYPE, requestType.name())
                    .metrics()
                    .defaultIfEmpty(RequestHandlerResultFactory.NO_CONTENT)
                    .doOnEach(signal -> {
                        if (!signal.isOnNext()) {
                            return;
                        }
                        RequestHandlerResult requestResult = signal.get();
                        if (requestResult == null
                                || requestResult.code() != ResponseStatusCode.OK) {
                            return;
                        }
                        notifyRelatedUsersOfAction(requestResult, userId, deviceType)
                                .contextWrite(signal.getContextView())
                                .subscribe(null, t -> {
                                    try (TracingCloseableContext ignored = context.asCloseable()) {
                                        LOGGER.error("Failed to notify related users of the action",
                                                t);
                                    }
                                });
                    })
                    .onErrorResume(t -> {
                        ThrowableInfo info = ThrowableInfo.get(t);
                        if (info.code()
                                .isServerError()) {
                            // We update the thread context but don't clear because we know the
                            // downstream will clear
                            context.updateThreadContext();
                            // Note we log the whole request instead of the request ID for
                            // troubleshooting
                            // because CommonClientApiLogging only logs a brief description,
                            // which isn't enough for debugging, but it is enough for statistics
                            // and user behavior analysis, so we don't plan to change it
                            LOGGER.error(
                                    "Caught an internal server error while handling the request: "
                                            + lastClientRequest,
                                    t);
                        }
                        return Mono
                                .just(RequestHandlerResultFactory.get(info.code(), info.reason()));
                    })
                    .map(handlerResult -> {
                        ServiceResponse response =
                                ServiceResponseFactory.get(handlerResult.dataForRequester(),
                                        handlerResult.code(),
                                        handlerResult.reason());
                        // 6. Log
                        if (response.code()
                                .isServerError()
                                || apiLoggingContext.shouldLogRequest(requestType)) {
                            context.updateThreadContext();
                            ClientApiLogging.log(lastClientRequest,
                                    serviceRequest,
                                    requestSize,
                                    requestTime,
                                    response,
                                    System.currentTimeMillis() - requestTime);
                        }
                        return response;
                    });
        });
    }

    private Mono<Void> notifyRelatedUsersOfAction(
            @NotNull RequestHandlerResult result,
            @NotNull Long requesterId,
            @NotNull DeviceType requesterDevice) {
        Mono<RequestHandlerResult> notify =
                pluginManager.invokeExtensionPointsSequentially(RequestHandlerResultHandler.class,
                        RESULT_BEFORE_NOTIFY_METHOD,
                        result,
                        (resultNotifier, pre) -> pre.flatMap(handlerResult -> resultNotifier
                                .beforeNotify(handlerResult, requesterId, requesterDevice)));
        return notify
                .flatMap(handlerResult -> notifyRelatedUsersOfAction0(handlerResult,
                        requesterId,
                        requesterDevice))
                .then();
    }

    private Mono<Void> notifyRelatedUsersOfAction0(
            @NotNull RequestHandlerResult result,
            @NotNull Long requesterId,
            @NotNull DeviceType requesterDevice) {
        TurmsRequest dataForRecipients = result.dataForRecipients();
        Set<Long> recipients = result.recipients();
        boolean noRecipient = recipients.isEmpty();
        boolean forwardDataForRecipientsToOtherSenderOnlineDevices =
                result.forwardDataForRecipientsToOtherSenderOnlineDevices();
        if (dataForRecipients == null
                || (noRecipient && !forwardDataForRecipientsToOtherSenderOnlineDevices)) {
            return Mono.empty();
        }
        TurmsNotification notificationForRecipients =
                ClientMessagePool.getTurmsNotificationBuilder()
                        .setTimestamp(System.currentTimeMillis())
                        .setRelayedRequest(dataForRecipients)
                        .setRequesterId(requesterId)
                        .build();
        ByteBuf notificationByteBuf = ProtoEncoder.getDirectByteBuffer(notificationForRecipients);
        Mono<Set<Long>> mono;
        if (forwardDataForRecipientsToOtherSenderOnlineDevices) {
            if (noRecipient) {
                mono = outboundMessageService.forwardNotification(notificationForRecipients,
                        notificationByteBuf,
                        requesterId,
                        requesterDevice);
            } else {
                notificationByteBuf.retain(2);
                Mono<Set<Long>> notifyRequesterMono =
                        outboundMessageService.forwardNotification(notificationForRecipients,
                                notificationByteBuf,
                                requesterId,
                                requesterDevice);
                Mono<Set<Long>> notifyRecipientsMono =
                        outboundMessageService.forwardNotification(notificationForRecipients,
                                notificationByteBuf,
                                recipients);
                mono = Flux.mergeDelayError(2, notifyRequesterMono, notifyRecipientsMono)
                        .collectList()
                        .map(CollectionUtil::union)
                        .doFinally(signal -> notificationByteBuf.release());
            }
        } else {
            mono = outboundMessageService.forwardNotification(notificationForRecipients,
                    notificationByteBuf,
                    recipients);
        }
        return mono.map(offlineRecipientIds -> pluginManager.invokeExtensionPointsSequentially(
                RequestHandlerResultHandler.class,
                RESULT_AFTER_NOTIFY_METHOD,
                (cur, pre) -> pre.switchIfEmpty(Mono.defer(() -> cur
                        .afterNotify(result, requesterId, requesterDevice, offlineRecipientIds)))))
                .then();
    }

}