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

package im.turms.gateway.service.impl;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.gateway.constant.ErrorMessage;
import im.turms.gateway.logging.ClientApiLogging;
import im.turms.gateway.manager.RateLimitingManager;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.server.common.cluster.exception.RpcException;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.logging.LoggingRequestUtil;
import im.turms.server.common.manager.ServerStatusManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.clientapi.ClientApiLoggingProperties;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingRequestProperties;
import im.turms.server.common.rpc.request.HandleServiceRequest;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.ExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author James Chen
 */
@Service
@Log4j2
public class InboundRequestService {

    private static final ServiceResponse REQUEST_RESPONSE_NO_CONTENT = new ServiceResponse(null, TurmsStatusCode.NO_CONTENT, null);

    private final Node node;
    private final RateLimitingManager rateLimitingManager;
    private final ServerStatusManager serverStatusManager;
    private final SessionService sessionService;
    private final Map<TurmsRequest.KindCase, LoggingRequestProperties> supportedLoggingRequestProperties;

    public InboundRequestService(Node node,
                                 TurmsPropertiesManager propertiesManager,
                                 ServerStatusManager serverStatusManager,
                                 SessionService sessionService) {
        this.node = node;
        rateLimitingManager = new RateLimitingManager(node);
        this.serverStatusManager = serverStatusManager;
        this.sessionService = sessionService;
        ClientApiLoggingProperties loggingProperties = propertiesManager.getLocalProperties().getGateway().getClientApi().getLogging();
        supportedLoggingRequestProperties = LoggingRequestUtil.getSupportedLoggingRequestProperties(
                loggingProperties.getIncludedRequestCategories(),
                loggingProperties.getIncludedRequests(),
                loggingProperties.getExcludedRequestCategories(),
                loggingProperties.getExcludedRequestTypes());
    }

    /**
     * If the method returns MonoError, the session should be closed by downstream.
     */
    public TurmsStatusCode processHeartbeatRequest(Long userId, DeviceType deviceType) {
        if (!serverStatusManager.isActive()) {
            return TurmsStatusCode.SERVER_UNAVAILABLE;
        }
        try {
            sessionService.updateHeartbeatTimestamp(userId, deviceType);
            return TurmsStatusCode.OK;
        } catch (Exception e) {
            if (!ExceptionUtil.isClientError(e)) {
                log.error(ErrorMessage.FAILED_TO_HANDLE_HEARTBEAT_REQUEST, e);
            }
            return TurmsStatusCode.SERVER_INTERNAL_ERROR;
        }
    }

    /**
     * @return a response to the request.
     * If the method returns MonoError, the session should be closed by downstream.
     */
    public Mono<TurmsNotification> processServiceRequest(ServiceRequest serviceRequest) {
        try {
            return processServiceRequest0(serviceRequest);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    private Mono<TurmsNotification> processServiceRequest0(ServiceRequest serviceRequest) {
        long requestTime = System.currentTimeMillis();
        // Update context
        TracingContext tracingContext = new TracingContext(serviceRequest.getTraceId());
        tracingContext.updateMdc();

        // Validate
        if (!serverStatusManager.isActive()) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        Long userId = serviceRequest.getUserId();
        DeviceType deviceType = serviceRequest.getDeviceType();
        UserSession session = sessionService.getLocalUserSession(userId, deviceType);
        if (session == null) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SEND_REQUEST_FROM_NON_EXISTING_SESSION));
        }

        // Rate limiting
        Long requestId = serviceRequest.getRequestId();
        long now = System.currentTimeMillis();
        if (rateLimitingManager.areRequestsTooFrequent(now, session)) {
            TurmsNotification notification = getNotificationFromStatusCode(TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT, requestId);
            return Mono.just(notification);
        }

        // Update heartbeat
        sessionService.updateHeartbeatTimestamp(userId, session);

        // Forward request
        int requestSize = serviceRequest.getTurmsRequestBuffer().readableBytes();
        Mono<TurmsNotification> notificationMono = sendServiceRequest(serviceRequest)
                .doOnEach(tracingContext.getMdcUpdater())
                .defaultIfEmpty(REQUEST_RESPONSE_NO_CONTENT)
                .onErrorResume(throwable -> Mono.just(getServiceResponseFromException(throwable, serviceRequest)))
                .map(response -> {
                    TurmsNotification responseNotification = getNotificationFromResponse(response, requestId);
                    // Log
                    if (LoggingRequestUtil.shouldLog(serviceRequest.getType(), supportedLoggingRequestProperties)) {
                        ClientApiLogging.log(serviceRequest,
                                requestSize,
                                requestTime,
                                responseNotification,
                                System.currentTimeMillis() - requestTime);
                    }
                    tracingContext.clearMdc();
                    return responseNotification;
                });
        return notificationMono.contextWrite(Context.of(TracingContext.CTX_KEY_NAME, tracingContext));
    }

    private Mono<ServiceResponse> sendServiceRequest(ServiceRequest serviceRequest) {
        HandleServiceRequest request = new HandleServiceRequest(serviceRequest);
        return node.getRpcService().requestResponse(request);
    }

    private TurmsNotification getNotificationFromStatusCode(@NotNull TurmsStatusCode statusCode, @Nullable Long requestId) {
        int codeBusinessCode = statusCode.getBusinessCode();
        TurmsNotification.Builder builder = TurmsNotification.newBuilder()
                .setCode(codeBusinessCode);
        if (requestId != null) {
            builder.setRequestId(requestId);
        }
        return builder
                .setCode(codeBusinessCode)
                .build();
    }

    private ServiceResponse getServiceResponseFromException(Throwable throwable, ServiceRequest serviceRequest) {
        ServiceResponse serviceResponse;
        if (throwable instanceof RpcException rpcException) {
            if (rpcException.isServerError()) {
                log.error(ErrorMessage.FAILED_TO_HANDLE_SERVICE_REQUEST_WITH_REQUEST, serviceRequest, throwable);
            }
            serviceResponse = new ServiceResponse(null, rpcException.getStatusCode(), throwable.getMessage());
        } else {
            log.error(ErrorMessage.FAILED_TO_HANDLE_SERVICE_REQUEST_WITH_REQUEST, serviceRequest, throwable);
            serviceResponse =
                    new ServiceResponse(null, TurmsStatusCode.SERVER_INTERNAL_ERROR, throwable.getMessage());
        }
        return serviceResponse;
    }

    private TurmsNotification getNotificationFromResponse(@NotNull ServiceResponse serviceResponse, long requestId) {
        TurmsStatusCode code = serviceResponse.getCode();
        if (code == null) {
            log.error("The business code is null in the service response: " + serviceResponse);
            code = TurmsStatusCode.SERVER_INTERNAL_ERROR;
        }
        TurmsNotification.Builder builder = TurmsNotification.newBuilder();
        String reason = serviceResponse.getReason();
        if (reason != null) {
            builder.setReason(reason);
        }
        TurmsNotification.Data dataForRequester = serviceResponse.getDataForRequester();
        if (dataForRequester != null) {
            builder.setData(dataForRequester);
        }
        return builder
                .setCode(code.getBusinessCode())
                .setRequestId(requestId)
                .build();
    }

}