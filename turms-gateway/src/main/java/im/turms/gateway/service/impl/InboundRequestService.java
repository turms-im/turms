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

import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import im.turms.common.constant.DeviceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.gateway.constant.ErrorMessage;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.util.TurmsRequestUtil;
import im.turms.server.common.cluster.exception.RpcException;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.log4j.ClientApiLogging;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.ClientApiLoggingProperties;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingRequestProperties;
import im.turms.server.common.rpc.request.HandleServiceRequest;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.ExceptionUtil;
import im.turms.server.common.util.LogUtil;
import im.turms.server.common.util.LoggingRequestUtil;
import im.turms.server.common.util.ProtoUtil;
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

    private final Node node;
    private final SessionService sessionService;
    private final Map<TurmsRequest.KindCase, LoggingRequestProperties> supportedLoggingRequestProperties;
    private final Map<TurmsRequest.KindCase, LoggingRequestProperties> supportedLoggingResponseProperties;

    public InboundRequestService(Node node, TurmsPropertiesManager propertiesManager, SessionService sessionService) {
        this.node = node;
        this.sessionService = sessionService;
        ClientApiLoggingProperties loggingProperties = propertiesManager.getLocalProperties().getGateway().getClientApi().getLogging();
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

    /**
     * If the method returns MonoError, the session should be closed by downstream.
     */
    public Mono<Boolean> processHeartbeatRequest(Long userId, DeviceType deviceType) {
        if (!node.isActive()) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        return sessionService.updateHeartbeatTimestamp(userId, deviceType)
                .onErrorResume(throwable -> {
                    if (!ExceptionUtil.isClientError(throwable)) {
                        log.error(ErrorMessage.FAILED_TO_HANDLE_HEARTBEAT_REQUEST, throwable);
                    }
                    return Mono.just(false);
                });
    }

    /**
     * @return a response to the request.
     * If the method returns MonoError, the session should be closed by downstream.
     */
    public Mono<TurmsNotification> processServiceRequest(ServiceRequest serviceRequest) {
        // Validate
        if (!node.isActive()) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        Long userId = serviceRequest.getUserId();
        DeviceType deviceType = serviceRequest.getDeviceType();
        UserSession session = sessionService.getLocalUserSession(userId, deviceType);
        if (session == null) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SEND_REQUEST_FROM_NON_EXISTING_SESSION));
        }

        // Flow control
        Long requestId = serviceRequest.getRequestId();
        TurmsRequest.KindCase requestType = serviceRequest.getType();
        if (!TurmsRequestUtil.isSignalRequest(requestType) && areRequestsTooFrequent(session)) {
            TurmsNotification notification = getNotificationFromStatusCode(TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT, requestId);
            return Mono.just(notification);
        }

        // Update heartbeat and forward request
        TracingContext tracingContext = new TracingContext(serviceRequest.getTraceId());
        Context context = Context.of(TracingContext.CTX_KEY_NAME, tracingContext);
        return sessionService.updateHeartbeatTimestamp(userId, session)
                .flatMap(wasSuccessful -> {
                    if (!wasSuccessful) {
                        LogUtil.error(log, context, "Failed to refresh the heartbeat of the user session: " + session);
                        return Mono.just(getNotificationFromStatusCode(TurmsStatusCode.SERVER_INTERNAL_ERROR, requestId));
                    }
                    tracingContext.updateMdc();
                    if (LoggingRequestUtil.shouldLog(serviceRequest.getType(), supportedLoggingRequestProperties)) {
                        ClientApiLogging.log(serviceRequest);
                    }
                    Mono<TurmsNotification> notificationMono = sendServiceRequest(serviceRequest)
                            .onErrorResume(throwable -> {
                                ServiceResponse serviceResponse;
                                if (throwable instanceof RpcException) {
                                    RpcException rpcException = (RpcException) throwable;
                                    if (rpcException.isServerError()) {
                                        LogUtil.error(log, context, ErrorMessage.FAILED_TO_HANDLE_SERVICE_REQUEST_WITH_REQUEST, serviceRequest, throwable);
                                    }
                                    serviceResponse = new ServiceResponse(null, rpcException.getStatusCode(), throwable.getMessage());
                                } else {
                                    log.error(ErrorMessage.FAILED_TO_HANDLE_SERVICE_REQUEST_WITH_REQUEST, serviceRequest, throwable);
                                    serviceResponse = new ServiceResponse(null, TurmsStatusCode.SERVER_INTERNAL_ERROR, throwable.getMessage());
                                }
                                return Mono.just(serviceResponse);
                            })
                            .map(serviceResponse -> getNotificationFromResponse(serviceResponse, requestId))
                            .defaultIfEmpty(getNotificationFromStatusCode(TurmsStatusCode.NO_CONTENT, requestId))
                            .doFinally(signalType -> tracingContext.clearMdc());
                    if (LoggingRequestUtil.shouldLog(serviceRequest.getType(), supportedLoggingResponseProperties)) {
                        notificationMono = notificationMono
                                .doOnSuccess(notification -> ClientApiLogging.log(ProtoUtil.toLogString(notification)));
                    }
                    return notificationMono;
                }).onErrorResume(throwable -> {
                    LogUtil.error(log, context, "Failed to refresh the heartbeat of the user session: " + session, throwable);
                    return Mono.just(getNotificationFromStatusCode(TurmsStatusCode.SERVER_INTERNAL_ERROR, requestId));
                }).contextWrite(context);
    }

    private Mono<ServiceResponse> sendServiceRequest(ServiceRequest serviceRequest) {
        HandleServiceRequest request = new HandleServiceRequest(serviceRequest);
        return node.getRpcService().requestResponse(request);
    }

    private boolean areRequestsTooFrequent(UserSession session) {
        int requestInterval = node.getSharedProperties().getGateway().getClientApi().getMinClientRequestIntervalMillis();
        if (requestInterval > 0) {
            long lastRequestTimestamp = session.getLastRequestTimestampMillis();
            long now = System.currentTimeMillis();
            boolean areFrequent = now - lastRequestTimestamp < requestInterval;
            if (!areFrequent) {
                session.setLastRequestTimestampMillis(now);
            }
            return areFrequent;
        } else {
            return false;
        }
    }

    private TurmsNotification getNotificationFromStatusCode(@NotNull TurmsStatusCode statusCode, @Nullable Long requestId) {
        int codeBusinessCode = statusCode.getBusinessCode();
        TurmsNotification.Builder builder = TurmsNotification.newBuilder()
                .setCode(Int32Value.newBuilder().setValue(codeBusinessCode).build());
        if (requestId != null) {
            builder.setRequestId(Int64Value.newBuilder().setValue(requestId).build());
        }
        return builder
                .setCode(Int32Value.newBuilder().setValue(codeBusinessCode).build())
                .build();
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
            builder.setReason(StringValue.newBuilder().setValue(reason).build());
        }
        TurmsNotification.Data dataForRequester = serviceResponse.getDataForRequester();
        if (dataForRequester != null) {
            builder.setData(dataForRequester);
        }
        Int32Value businessCode = Int32Value.newBuilder().setValue(code.getBusinessCode()).build();
        return builder
                .setCode(businessCode)
                .setRequestId(Int64Value.newBuilder().setValue(requestId).build())
                .build();
    }

}