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

package im.turms.turms.workflow.access.servicerequest.dispatcher;

import com.google.protobuf.InvalidProtocolBufferException;
import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.exception.ThrowableInfo;
import im.turms.server.common.log4j.ClientApiLogging;
import im.turms.server.common.manager.ServerStatusManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.ClientApiLoggingProperties;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingRequestProperties;
import im.turms.server.common.rpc.service.IServiceRequestDispatcher;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.LoggingRequestUtil;
import im.turms.server.common.util.ProtoUtil;
import im.turms.turms.plugin.manager.TurmsPluginManager;
import im.turms.turms.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResult;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.turms.workflow.access.servicerequest.dto.ServiceResponseFactory;
import im.turms.turms.workflow.service.impl.message.OutboundMessageService;
import io.netty.buffer.ByteBuf;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.CREATE_SESSION_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.DELETE_SESSION_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.KIND_NOT_SET;
import static im.turms.turms.constant.MetricsConstant.CLIENT_REQUEST_NAME;
import static im.turms.turms.constant.MetricsConstant.CLIENT_REQUEST_TAG_TYPE;

/**
 * @author James Chen
 */
@Log4j2
@Service
public class ServiceRequestDispatcher implements IServiceRequestDispatcher {

    private final ServerStatusManager serverStatusManager;
    private final OutboundMessageService outboundMessageService;
    private final TurmsPluginManager turmsPluginManager;

    private final Map<TurmsRequest.KindCase, ClientRequestHandler> router;
    private final boolean pluginEnabled;
    private final Map<TurmsRequest.KindCase, LoggingRequestProperties> supportedLoggingRequestProperties;
    private final Map<TurmsRequest.KindCase, LoggingRequestProperties> supportedLoggingResponseProperties;

    public ServiceRequestDispatcher(ApplicationContext context,
                                    Node node,
                                    ServerStatusManager serverStatusManager,
                                    OutboundMessageService outboundMessageService,
                                    TurmsPropertiesManager turmsPropertiesManager,
                                    TurmsPluginManager turmsPluginManager) {
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
        ClientApiLoggingProperties loggingProperties = node.getSharedProperties().getService().getClientApi().getLogging();
        supportedLoggingRequestProperties = LoggingRequestUtil.getSupportedLoggingRequestProperties(
                loggingProperties.getIncludedRequestCategories(),
                loggingProperties.getIncludedRequests(),
                loggingProperties.getExcludedRequestCategories(),
                loggingProperties.getExcludedRequestTypes());
        supportedLoggingResponseProperties = LoggingRequestUtil.getSupportedLoggingRequestProperties(
                loggingProperties.getIncludedResponseCategories(),
                loggingProperties.getIncludedResponses(),
                loggingProperties.getExcludedResponseCategories(),
                loggingProperties.getExcludedResponseTypes());
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

    @Override
    public Mono<ServiceResponse> dispatch(ServiceRequest serviceRequest) {
        try {
            return dispatch0(serviceRequest);
        } catch (Exception e) {
            log.error("Failed to handle the {} request: {}", serviceRequest.getType(), serviceRequest.getRequestId());
            return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.SERVER_INTERNAL_ERROR, e.getMessage()));
        }
    }

    /**
     * @implNote 1. Flow Control:
     * turms-gateway is responsible for the flow control of client requests
     * and RSocket is responsible for the backpressure between turms and turms-gateway
     * so we don't check the request rate here.
     * <p>
     * 2. The method should never return MonoError and it should be considered as a bug if it occurs
     * because the method itself should wrap all kinds of Throwable as a ServiceResponse instance.
     */
    private Mono<ServiceResponse> dispatch0(ServiceRequest serviceRequest) {
        // 1. Validate ServiceResponse
        Long traceId = serviceRequest.getTraceId();
        Long userId = serviceRequest.getUserId();
        DeviceType deviceType = serviceRequest.getDeviceType();
        if (traceId == null) {
            String message = "The trace ID is missing for the request: " + serviceRequest;
            return Mono.just(new ServiceResponse(null, TurmsStatusCode.SERVER_INTERNAL_ERROR, message));
        }
        if (userId == null) {
            String message = "The user ID is missing for the request: " + serviceRequest;
            return Mono.just(new ServiceResponse(null, TurmsStatusCode.SERVER_INTERNAL_ERROR, message));
        }
        if (deviceType == null) {
            String message = "The device type is missing for the request: " + serviceRequest;
            return Mono.just(new ServiceResponse(null, TurmsStatusCode.SERVER_INTERNAL_ERROR, message));
        }
        if (!serverStatusManager.isActive()) {
            return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        TurmsRequest request;
        try {
            // Note that "parseFrom" won't block because the buffer is fully read
            request = TurmsRequest.parseFrom(serviceRequest.getTurmsRequestBuffer().nioBuffer());
        } catch (InvalidProtocolBufferException e) {
            return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.INVALID_REQUEST));
        }

        TracingContext tracingContext = new TracingContext(traceId);
        tracingContext.updateMdc();

        // 2. Transform and handle the request
        ClientRequest clientRequest = new ClientRequest(
                serviceRequest.getUserId(),
                serviceRequest.getDeviceType(),
                request.getRequestId(),
                request,
                serviceRequest.getTurmsRequestBuffer());
        Mono<ClientRequest> clientRequestMono = Mono.just(clientRequest);
        List<im.turms.turms.plugin.extension.handler.ClientRequestHandler> clientClientRequestHandlerList =
                turmsPluginManager.getClientRequestHandlerList();
        if (pluginEnabled) {
            for (im.turms.turms.plugin.extension.handler.ClientRequestHandler clientRequestHandler : clientClientRequestHandlerList) {
                clientRequestMono = clientRequestMono.flatMap(clientRequestHandler::transform);
            }
        }
        return clientRequestMono.flatMap(lastClientRequest -> {
            // 3. Validate ClientRequest
            TurmsRequest lastRequest = lastClientRequest.getTurmsRequest();
            if (lastRequest == null) {
                String message = "The TurmsRequest instance is null in the client request: " + lastClientRequest;
                log.error(message);
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
            // 4. Log
            if (LoggingRequestUtil.shouldLog(requestType, supportedLoggingRequestProperties)) {
                ClientApiLogging.log(lastClientRequest);
            }
            // 5. Pass the request to the controller and get a response
            Mono<RequestHandlerResult> result;
            if (pluginEnabled && !clientClientRequestHandlerList.isEmpty()) {
                Mono<RequestHandlerResult> requestResultMono = Mono.empty();
                for (im.turms.turms.plugin.extension.handler.ClientRequestHandler clientRequestHandler : clientClientRequestHandlerList) {
                    requestResultMono = requestResultMono
                            .switchIfEmpty(clientRequestHandler.handleClientRequest(lastClientRequest));
                }
                result = requestResultMono.switchIfEmpty(handler.handle(lastClientRequest));
            } else {
                result = handler.handle(lastClientRequest);
            }
            // 6. Metrics and transform to ServiceResponse
            return result
                    .name(CLIENT_REQUEST_NAME)
                    .tag(CLIENT_REQUEST_TAG_TYPE, requestType.name())
                    .metrics()
                    .defaultIfEmpty(RequestHandlerResultFactory.NO_CONTENT)
                    .doOnSuccess(requestResult -> {
                        if (requestResult.getCode() == TurmsStatusCode.OK) {
                            notifyRelatedUsersOfAction(requestResult, userId, deviceType)
                                    .subscribe();
                        }
                    })
                    .onErrorResume(throwable -> {
                        ThrowableInfo info = ThrowableInfo.get(throwable);
                        if (info.getCode().isServerError()) {
                            log.error("Failed to handle the client request: {}", lastClientRequest, throwable);
                        }
                        return Mono.just(RequestHandlerResultFactory.get(info.getCode(), info.getReason()));
                    })
                    .map(handlerResult -> {
                        ServiceResponse response = ServiceResponseFactory.get(
                                handlerResult.getDataForRequester(),
                                handlerResult.getCode(),
                                handlerResult.getReason());
                        if (LoggingRequestUtil.shouldLog(requestType, supportedLoggingResponseProperties)) {
                            ClientApiLogging.log(response);
                        }
                        tracingContext.clearMdc();
                        return response;
                    });
        });
    }

    private Mono<Void> notifyRelatedUsersOfAction(
            @NotNull RequestHandlerResult result,
            @NotNull Long requesterId,
            @NotNull DeviceType requesterDevice) {
        TurmsRequest dataForRecipients = result.getDataForRecipients();
        Set<Long> recipients = result.getRecipients();
        if (dataForRecipients == null || recipients.isEmpty()) {
            return Mono.empty();
        }
        TurmsNotification notificationForRecipients = TurmsNotification
                .newBuilder()
                .setRelayedRequest(dataForRecipients)
                .setRequesterId(requesterId)
                .build();
        ByteBuf notificationByteBuf = ProtoUtil.getDirectByteBuffer(notificationForRecipients);
        if (result.isForwardDataForRecipientsToOtherSenderOnlineDevices()) {
            notificationByteBuf.retain();
            Mono<Boolean> notifyRequesterMono = outboundMessageService
                    .forwardNotification(notificationForRecipients, notificationByteBuf, requesterId, requesterDevice);
            Mono<Boolean> notifyRecipientsMono =
                    outboundMessageService.forwardNotification(notificationForRecipients, notificationByteBuf, recipients);
            return Mono.when(notifyRequesterMono, notifyRecipientsMono)
                    .doOnTerminate(notificationByteBuf::release);
        } else {
            return outboundMessageService.forwardNotification(notificationForRecipients, notificationByteBuf, recipients)
                    .then();
        }
    }

}