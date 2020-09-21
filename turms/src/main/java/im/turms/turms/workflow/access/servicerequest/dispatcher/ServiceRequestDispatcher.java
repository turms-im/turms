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

import com.google.protobuf.Int64Value;
import com.google.protobuf.InvalidProtocolBufferException;
import im.turms.common.constant.DeviceType;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.rpc.service.IServiceRequestDispatcher;
import im.turms.server.common.util.ProtoUtil;
import im.turms.turms.plugin.manager.TurmsPluginManager;
import im.turms.turms.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResult;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.turms.workflow.access.servicerequest.dto.ServiceResponseFactory;
import im.turms.turms.workflow.service.impl.log.ActivityLogService;
import im.turms.turms.workflow.service.impl.log.UserActionLogService;
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

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.KIND_NOT_SET;
import static im.turms.turms.constant.MetricsConstant.CLIENT_REQUEST_NAME;
import static im.turms.turms.constant.MetricsConstant.CLIENT_REQUEST_TAG_TYPE;

/**
 * @author James Chen
 */
@Log4j2
@Service
public class ServiceRequestDispatcher implements IServiceRequestDispatcher {

    private final Node node;
    private final OutboundMessageService outboundMessageService;
    private final UserActionLogService userActionLogService;
    private final ActivityLogService activityLogService;
    private final TurmsPluginManager turmsPluginManager;

    private final Map<TurmsRequest.KindCase, ClientRequestHandler> router;
    private final boolean pluginEnabled;

    public ServiceRequestDispatcher(ApplicationContext context,
                                    Node node,
                                    OutboundMessageService outboundMessageService,
                                    TurmsPropertiesManager turmsPropertiesManager,
                                    TurmsPluginManager turmsPluginManager,
                                    UserActionLogService userActionLogService,
                                    ActivityLogService activityLogService) {
        this.outboundMessageService = outboundMessageService;
        router = getMappings((ConfigurableApplicationContext) context);
        this.activityLogService = activityLogService;
        for (TurmsRequest.KindCase kindCase : TurmsRequest.KindCase.values()) {
            if (!router.containsKey(kindCase) && kindCase != KIND_NOT_SET) {
                throw new IllegalStateException("No client request handler for the request type: " + kindCase.name());
            }
        }
        this.node = node;
        this.turmsPluginManager = turmsPluginManager;
        this.userActionLogService = userActionLogService;
        pluginEnabled = turmsPropertiesManager.getLocalProperties().getPlugin().isEnabled();
    }

    private Map<TurmsRequest.KindCase, ClientRequestHandler> getMappings(ConfigurableApplicationContext context) {
        Map<TurmsRequest.KindCase, ClientRequestHandler> mappingMap = new EnumMap<>(TurmsRequest.KindCase.class);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        String[] definitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : definitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (!beanDefinition.isAbstract()) {
                ServiceRequestMapping requestMapping = beanFactory.findAnnotationOnBean(beanName, ServiceRequestMapping.class);
                if (requestMapping != null) {
                    Object beanInstance = beanFactory.getBean(beanName);
                    mappingMap.put(requestMapping.value(), (ClientRequestHandler) beanInstance);
                }
            }
        }
        return mappingMap;
    }

    /**
     * turms-gateway is responsible for the flow control of client requests
     * and RSocket is responsible for the backpressure between turms and turms-gateway
     * so we don't check the request rate here
     */
    @Override
    public Mono<ServiceResponse> dispatch(ServiceRequest serviceRequest) {
        // 1. Validate
        Long userId = serviceRequest.getUserId();
        DeviceType deviceType = serviceRequest.getDeviceType();
        if (userId == null) {
            String message = "The user ID is missing for the request: " + serviceRequest;
            return Mono.just(new ServiceResponse(null, TurmsStatusCode.SERVER_INTERNAL_ERROR, message));
        }
        if (deviceType == null) {
            String message = "The device type is missing for the request: " + serviceRequest;
            return Mono.just(new ServiceResponse(null, TurmsStatusCode.SERVER_INTERNAL_ERROR, message));
        }
        if (!node.isActive()) {
            return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.UNAVAILABLE));
        }
        TurmsRequest request;
        try {
            request = TurmsRequest.parseFrom(serviceRequest.getTurmsRequestBuffer().nioBuffer());
        } catch (InvalidProtocolBufferException e) {
            return Mono.just(ServiceResponseFactory.get(TurmsStatusCode.ILLEGAL_ARGUMENTS));
        }

        // 2. Transform and handle the request
        ClientRequest clientRequest = new ClientRequest(
                serviceRequest.getUserId(),
                serviceRequest.getDeviceType(),
                request.getRequestId().getValue(),
                request,
                serviceRequest.getTurmsRequestBuffer());
        Mono<ClientRequest> clientRequestMono = Mono.just(clientRequest);
        List<im.turms.turms.plugin.extension.handler.ClientRequestHandler> clientClientRequestHandlerList = turmsPluginManager.getClientRequestHandlerList();
        if (pluginEnabled) {
            for (im.turms.turms.plugin.extension.handler.ClientRequestHandler clientRequestHandler : clientClientRequestHandlerList) {
                clientRequestMono = clientRequestMono.flatMap(clientRequestHandler::transform);
            }
        }
        // 3. Handle the result of the request
        Mono<RequestHandlerResult> resultMono = clientRequestMono.flatMap(lastClientRequest -> {
            TurmsRequest lastRequest = lastClientRequest.getTurmsRequest();
            if (lastRequest == null) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS));
            }
            TurmsRequest.KindCase kindCase = lastRequest.getKindCase();
            if (kindCase == KIND_NOT_SET) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS));
            }
            ClientRequestHandler handler = router.get(kindCase);
            if (handler == null) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS));
            }
            userActionLogService.tryLogAndTriggerLogHandlers(userId, deviceType, lastRequest);
            activityLogService.tryLogClientRequest(lastClientRequest);
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
            return result
                    .name(CLIENT_REQUEST_NAME)
                    .tag(CLIENT_REQUEST_TAG_TYPE, kindCase.name())
                    .metrics();
        });
        return handleResult(resultMono, userId, deviceType);
    }

    private Mono<ServiceResponse> handleResult(
            @NotNull Mono<RequestHandlerResult> result,
            @NotNull Long requesterId,
            @NotNull DeviceType requesterDevice) {
        return result
                .defaultIfEmpty(RequestHandlerResultFactory.NO_CONTENT)
                .doOnSuccess(requestResult -> {
                    if (requestResult.getCode() == TurmsStatusCode.OK) {
                        notifyRelatedUsersOfAction(requestResult, requesterId, requesterDevice)
                                .subscribe();
                    }
                })
                .onErrorResume(throwable -> {
                    if (throwable instanceof TurmsBusinessException) {
                        TurmsBusinessException exception = (TurmsBusinessException) throwable;
                        if (exception.getCode().isServerError()) {
                            log.error("Failed to handle the client request", throwable);
                        }
                        return Mono.just(RequestHandlerResultFactory.get(exception.getCode()));
                    } else {
                        log.error("Failed to handle the client request", throwable);
                        return Mono.just(RequestHandlerResultFactory.get(TurmsStatusCode.FAILED, throwable.toString()));
                    }
                })
                .map(requestHandlerResult -> ServiceResponseFactory.get(
                        requestHandlerResult.getDataForRequester(),
                        requestHandlerResult.getCode(),
                        requestHandlerResult.getReason()));
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
        ByteBuf notificationForRecipients = ProtoUtil.getByteBuffer(TurmsNotification
                .newBuilder()
                .setRelayedRequest(dataForRecipients)
                .setRequesterId(Int64Value.newBuilder().setValue(requesterId).build())
                .build());
        if (result.isForwardDataForRecipientsToOtherSenderOnlineDevices()) {
            notificationForRecipients.retain();
            Mono<Boolean> notifyRequesterMono = outboundMessageService.forwardNotification(notificationForRecipients, requesterId, requesterDevice);
            Mono<Boolean> notifyRecipientsMono = outboundMessageService.forwardNotification(notificationForRecipients, recipients);
            return Mono.when(notifyRequesterMono, notifyRecipientsMono)
                    .doOnTerminate(notificationForRecipients::release);
        } else {
            return outboundMessageService.forwardNotification(notificationForRecipients, recipients)
                    .then();
        }
    }

}