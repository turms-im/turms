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
import im.turms.server.common.healthcheck.ServerStatusManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.rpc.service.IServiceRequestDispatcher;
import im.turms.server.common.service.blocklist.BlocklistService;
import im.turms.server.common.tracing.TracingCloseableContext;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.ProtoUtil;
import im.turms.service.logging.ApiLoggingContext;
import im.turms.service.logging.ClientApiLogging;
import im.turms.service.plugin.TurmsPluginManager;
import im.turms.service.plugin.extension.ClientRequestTransformer;
import im.turms.service.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.workflow.access.servicerequest.dto.ServiceResponseFactory;
import im.turms.service.workflow.service.impl.message.OutboundMessageService;
import io.netty.buffer.ByteBuf;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
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
@Log4j2
@Service
public class ServiceRequestDispatcher implements IServiceRequestDispatcher {

    private final ApiLoggingContext apiLoggingContext;
    private final BlocklistService blocklistService;
    private final ServerStatusManager serverStatusManager;
    private final OutboundMessageService outboundMessageService;
    private final TurmsPluginManager turmsPluginManager;

    private final Map<TurmsRequest.KindCase, ClientRequestHandler> router;
    private final boolean pluginEnabled;

    public ServiceRequestDispatcher(ApiLoggingContext apiLoggingContext,
                                    ApplicationContext context,
                                    BlocklistService blocklistService,
                                    ServerStatusManager serverStatusManager,
                                    OutboundMessageService outboundMessageService,
                                    TurmsPropertiesManager turmsPropertiesManager,
                                    TurmsPluginManager turmsPluginManager) {
        this.apiLoggingContext = apiLoggingContext;
        this.blocklistService = blocklistService;
        this.serverStatusManager = serverStatusManager;
        this.outboundMessageService = outboundMessageService;
        Set<TurmsRequest.KindCase> disabledEndpoints =
                turmsPropertiesManager.getLocalProperties().getService().getClientApi().getDisabledEndpoints();
        router = getMappings((ConfigurableApplicationContext) context, disabledEndpoints);
        for (TurmsRequest.KindCase kindCase : TurmsRequest.KindCase.values()) {
            if (!router.containsKey(kindCase) && kindCase != KIND_NOT_SET && !isRequestForGateway(kindCase)) {
                throw new IllegalStateException("No client request handler for the request type: " + kindCase.name());
            }
        }
        this.turmsPluginManager = turmsPluginManager;
        pluginEnabled = turmsPropertiesManager.getLocalProperties().getPlugin().isEnabled();
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
     * and (TODO) we will implement backpressure between serves in https://github.com/turms-im/turms/issues/761
     * so we don't check the request rate here.
     * <p>
     * 2. The method should never return MonoError, and it should be considered as a bug if it occurs
     * because the method itself should map all kinds of Throwable to a ServiceResponse instance.
     * 3. The method ensures turmsRequestBuffer in serviceRequest will be released by 1
     */
    @Override
    public Mono<ServiceResponse> dispatch(TracingContext tracingContext, ServiceRequest serviceRequest) {
        ByteBuf requestBuffer = serviceRequest.getTurmsRequestBuffer();
        try {
            requestBuffer.touch(serviceRequest);
            return dispatch0(tracingContext, serviceRequest);
        } catch (Exception e) {
            log.error("Failed to handle the request: {}", serviceRequest, e);
            return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.SERVER_INTERNAL_ERROR, e.toString()));
        } finally {
            requestBuffer.release();
        }
    }

    private Mono<ServiceResponse> dispatch0(TracingContext tracingContext, ServiceRequest serviceRequest) {
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
            blocklistService.tryBlockIpForCorruptedRequest(serviceRequest.getIp());
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
        Mono<ClientRequest> clientRequestMono = Mono.just(clientRequest);
        if (pluginEnabled) {
            for (ClientRequestTransformer transformer : turmsPluginManager.getClientRequestTransformerList()) {
                clientRequestMono = clientRequestMono
                        .flatMap(req -> Mono.defer(() -> transformer.transform(req)));
            }
        }
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
            Mono<RequestHandlerResult> result;
            List<im.turms.service.plugin.extension.ClientRequestHandler> handlers = turmsPluginManager.getClientRequestHandlerList();
            if (pluginEnabled && !handlers.isEmpty()) {
                Mono<RequestHandlerResult> requestResultMono = Mono.empty();
                for (im.turms.service.plugin.extension.ClientRequestHandler clientRequestHandler : handlers) {
                    requestResultMono = requestResultMono
                            .switchIfEmpty(Mono.defer(() -> clientRequestHandler.handle(lastClientRequest)));
                }
                result = requestResultMono.switchIfEmpty(Mono.defer(() -> handler.handle(lastClientRequest)));
            } else {
                result = handler.handle(lastClientRequest);
            }
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
                                .onErrorResume(t -> {
                                    try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
                                        log.error("Failed to notify related users of the action", t);
                                    }
                                    return Mono.empty();
                                })
                                .contextWrite(signal.getContextView())
                                .subscribe();
                    })
                    .onErrorResume(t -> {
                        ThrowableInfo info = ThrowableInfo.get(t);
                        if (info.code().isServerError()) {
                            // We update MDC and not clear because we know the downstream will clear
                            tracingContext.updateMdc();
                            // Note we log the whole request instead of the request ID for troubleshooting
                            // because CommonClientApiLogging only logs a brief description,
                            // which isn't enough for debugging, but it's enough for statistics
                            // and user behavior analysis, so we don't plan to change it
                            log.error("Caught an internal server error when handling the request: " + lastClientRequest, t);
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
                            tracingContext.updateMdc();
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
            return Mono.when(notifyRequesterMono, notifyRecipientsMono)
                    .doFinally(signal -> notificationByteBuf.release());
        } else {
            return outboundMessageService.forwardNotification(notificationForRecipients, notificationByteBuf, recipients)
                    .then();
        }
    }

}