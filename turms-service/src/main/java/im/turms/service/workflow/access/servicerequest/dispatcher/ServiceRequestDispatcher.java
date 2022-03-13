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

package im.turms.service.workflow.access.servicerequest.dispatcher;

import com.google.protobuf.CodedInputStream;
import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.exception.ThrowableInfo;
import im.turms.server.common.healthcheck.HealthCheckManager;
import im.turms.server.common.healthcheck.ServerStatusManager;
import im.turms.server.common.lang.ByteArrayWrapper;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.plugin.PluginManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.rpc.service.IServiceRequestDispatcher;
import im.turms.server.common.service.blocklist.BlocklistService;
import im.turms.server.common.tracing.TracingCloseableContext;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.ProtoUtil;
import im.turms.service.logging.ApiLoggingContext;
import im.turms.service.logging.ClientApiLogging;
import im.turms.service.plugin.extension.ClientRequestTransformer;
import im.turms.service.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.workflow.access.servicerequest.dto.ServiceResponseFactory;
import im.turms.service.workflow.service.impl.message.OutboundMessageService;
import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.CREATE_SESSION_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.DELETE_SESSION_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.KIND_NOT_SET;
import static im.turms.server.common.constant.CommonMetricsConstant.CLIENT_REQUEST_NAME;
import static im.turms.server.common.constant.CommonMetricsConstant.CLIENT_REQUEST_TAG_TYPE;

/**
 * @author James Chen
 */
@Service
public class ServiceRequestDispatcher implements IServiceRequestDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequestDispatcher.class);

    private final ApiLoggingContext apiLoggingContext;
    private final BlocklistService blocklistService;
    private final ServerStatusManager serverStatusManager;
    private final OutboundMessageService outboundMessageService;
    private final PluginManager pluginManager;

    private final Map<TurmsRequest.KindCase, ClientRequestHandler> router;

    public ServiceRequestDispatcher(ApiLoggingContext apiLoggingContext,
                                    ApplicationContext context,
                                    BlocklistService blocklistService,
                                    ServerStatusManager serverStatusManager,
                                    OutboundMessageService outboundMessageService,
                                    PluginManager pluginManager,
                                    TurmsPropertiesManager turmsPropertiesManager) {
        this.apiLoggingContext = apiLoggingContext;
        this.blocklistService = blocklistService;
        this.serverStatusManager = serverStatusManager;
        this.outboundMessageService = outboundMessageService;
        this.pluginManager = pluginManager;
        Set<TurmsRequest.KindCase> disabledEndpoints =
                turmsPropertiesManager.getLocalProperties().getService().getClientApi().getDisabledEndpoints();
        router = getMappings((ConfigurableApplicationContext) context, disabledEndpoints);
        for (TurmsRequest.KindCase kindCase : TurmsRequest.KindCase.values()) {
            if (!router.containsKey(kindCase) && kindCase != KIND_NOT_SET && !isRequestForGateway(kindCase)) {
                throw new IllegalStateException("No client request handler for the request type: " + kindCase.name());
            }
        }
    }

    private Map<TurmsRequest.KindCase, ClientRequestHandler> getMappings(ConfigurableApplicationContext context,
                                                                         Set<TurmsRequest.KindCase> disabledEndpoints) {
        Map<TurmsRequest.KindCase, ClientRequestHandler> mappingMap = new EnumMap<>(TurmsRequest.KindCase.class);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        String[] definitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : definitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (!beanDefinition.isAbstract()) {
                ServiceRequestMapping requestMapping = beanFactory.findAnnotationOnBean(beanName, ServiceRequestMapping.class);
                if (requestMapping != null) {
                    Object beanInstance = beanFactory.getBean(beanName);
                    TurmsRequest.KindCase endpointType = requestMapping.value();
                    if (!disabledEndpoints.contains(endpointType)) {
                        mappingMap.put(endpointType, (ClientRequestHandler) beanInstance);
                    }
                }
            }
        }
        return mappingMap;
    }

    private boolean isRequestForGateway(TurmsRequest.KindCase type) {
        return type == CREATE_SESSION_REQUEST || type == DELETE_SESSION_REQUEST;
    }

    /**
     * @implNote 1. Flow Control:
     * turms-gateway is responsible for the rate limiting of client requests
     * and {@link HealthCheckManager} ensures that when dispatch() is called
     * the status of the local node is healthy, so we don't need to check the request rate here.
     * <p>
     * 2. The method should never return MonoError, and it should be considered as a bug if it occurs
     * because the method itself should map all kinds of Throwable to a ServiceResponse instance.
     * 3. The method ensures turmsRequestBuffer in serviceRequest will be released by 1
     */
    @Override
    public Mono<ServiceResponse> dispatch(TracingContext context, ServiceRequest serviceRequest) {
        ByteBuf requestBuffer = serviceRequest.getTurmsRequestBuffer();
        try {
            requestBuffer.touch(serviceRequest);
            return dispatch0(context, serviceRequest);
        } catch (Exception e) {
            LOGGER.error("Failed to handle the request: {}", serviceRequest, e);
            return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.SERVER_INTERNAL_ERROR, e.toString()));
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
            String message = "The user ID is missing in the request: " + serviceRequest;
            return Mono.just(new ServiceResponse(null, TurmsStatusCode.SERVER_INTERNAL_ERROR, message));
        }
        if (deviceType == null) {
            String message = "The device type is missing in the request: " + serviceRequest;
            return Mono.just(new ServiceResponse(null, TurmsStatusCode.SERVER_INTERNAL_ERROR, message));
        }
        if (!serverStatusManager.isActive()) {
            return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        TurmsRequest.Builder requestBuilder;
        ByteBuf turmsRequestBuffer = serviceRequest.getTurmsRequestBuffer();
        int requestSize = turmsRequestBuffer.readableBytes();
        try {
            // Note that "mergeFrom" won't block because the buffer is fully read
            CodedInputStream stream = CodedInputStream.newInstance(turmsRequestBuffer.nioBuffer());
            requestBuilder = TurmsRequest.newBuilder().mergeFrom(stream);
        } catch (IOException e) {
            blocklistService.tryBlockIpForCorruptedRequest(new ByteArrayWrapper(serviceRequest.getIp()));
            blocklistService.tryBlockUserIdForCorruptedRequest(serviceRequest.getUserId());
            return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.INVALID_REQUEST, e.getMessage()));
        }
        turmsRequestBuffer.touch(requestBuilder);

        // 2. Transform and handle the request
        ClientRequest clientRequest = new ClientRequest(
                serviceRequest.getUserId(),
                serviceRequest.getDeviceType(),
                requestBuilder.getRequestId(),
                requestBuilder,
                null);
        Mono<ClientRequest> clientRequestMono = pluginManager.invokeExtensionPointsSequentially(
                        ClientRequestTransformer.class,
                        "transform",
                        clientRequest,
                        (transformer, request) -> request.flatMap(transformer::transform))
                .defaultIfEmpty(clientRequest);
        return clientRequestMono.flatMap(lastClientRequest -> {
            // 3. Validate ClientRequest
            TurmsRequest lastRequest = lastClientRequest.turmsRequest();
            if (lastRequest == null) {
                String message = "The TurmsRequest instance is null in the client request: " + lastClientRequest;
                return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.SERVER_INTERNAL_ERROR, message));
            }
            TurmsRequest.KindCase requestType = lastRequest.getKindCase();
            if (requestType == KIND_NOT_SET) {
                return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The request type cannot be KIND_NOT_SET"));
            }
            ClientRequestHandler handler = router.get(requestType);
            if (handler == null) {
                return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The request type is unsupported"));
            }
            // 4. Pass the request to the controller and get a response
            Mono<RequestHandlerResult> result =
                    pluginManager.invokeExtensionPointsSequentially(im.turms.service.plugin.extension.ClientRequestHandler.class,
                            "handle",
                            (requestHandler, pre) -> pre.switchIfEmpty(Mono.defer(() -> requestHandler.handle(lastClientRequest))));
            result = result.switchIfEmpty(Mono.defer(() -> handler.handle(lastClientRequest)));
            // 5. Metrics and transform to ServiceResponse
            return result
                    .name(CLIENT_REQUEST_NAME)
                    .tag(CLIENT_REQUEST_TAG_TYPE, requestType.name())
                    .metrics()
                    .defaultIfEmpty(RequestHandlerResultFactory.NO_CONTENT)
                    .doOnEach(signal -> {
                        if (!signal.isOnNext()) {
                            return;
                        }
                        RequestHandlerResult requestResult = signal.get();
                        if (requestResult == null || requestResult.code() != TurmsStatusCode.OK) {
                            return;
                        }
                        notifyRelatedUsersOfAction(requestResult, userId, deviceType)
                                .contextWrite(signal.getContextView())
                                .subscribe(null, t -> {
                                    try (TracingCloseableContext ignored = context.asCloseable()) {
                                        LOGGER.error("Failed to notify related users of the action", t);
                                    }
                                });
                    })
                    .onErrorResume(t -> {
                        ThrowableInfo info = ThrowableInfo.get(t);
                        if (info.code().isServerError()) {
                            // We update the thread context but don't clear because we know the downstream will clear
                            context.updateThreadContext();
                            // Note we log the whole request instead of the request ID for troubleshooting
                            // because CommonClientApiLogging only logs a brief description,
                            // which isn't enough for debugging, but it's enough for statistics
                            // and user behavior analysis, so we don't plan to change it
                            LOGGER.error("Caught an internal server error while handling the request: " + lastClientRequest, t);
                        }
                        return Mono.just(RequestHandlerResultFactory.get(info.code(), info.reason()));
                    })
                    .map(handlerResult -> {
                        ServiceResponse response = ServiceResponseFactory.get(
                                handlerResult.dataForRequester(),
                                handlerResult.code(),
                                handlerResult.reason());
                        // 6. Log
                        if (response.code().isServerError() || apiLoggingContext.shouldLogRequest(requestType)) {
                            context.updateThreadContext();
                            ClientApiLogging
                                    .log(lastClientRequest,
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
        TurmsRequest dataForRecipients = result.dataForRecipients();
        Set<Long> recipients = result.recipients();
        if (dataForRecipients == null || recipients.isEmpty()) {
            return Mono.empty();
        }
        TurmsNotification notificationForRecipients = TurmsNotification
                .newBuilder()
                .setRelayedRequest(dataForRecipients)
                .setRequesterId(requesterId)
                .build();
        ByteBuf notificationByteBuf = ProtoUtil.getDirectByteBuffer(notificationForRecipients);
        if (result.forwardDataForRecipientsToOtherSenderOnlineDevices()) {
            notificationByteBuf.retain(2);
            Mono<Boolean> notifyRequesterMono = outboundMessageService
                    .forwardNotification(notificationForRecipients, notificationByteBuf, requesterId, requesterDevice);
            Mono<Boolean> notifyRecipientsMono = outboundMessageService
                    .forwardNotification(notificationForRecipients, notificationByteBuf, recipients);
            return Mono.whenDelayError(notifyRequesterMono, notifyRecipientsMono)
                    .doFinally(signal -> notificationByteBuf.release());
        }
        return outboundMessageService.forwardNotification(notificationForRecipients, notificationByteBuf, recipients)
                .then();
    }

}