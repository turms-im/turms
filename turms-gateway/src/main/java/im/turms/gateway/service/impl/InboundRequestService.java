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
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.rpc.RpcException;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.rpc.request.HandleServiceRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
@Service
@Validated
@Log4j2
public class InboundRequestService {

    private final Node node;
    private final SessionService sessionService;

    public InboundRequestService(Node node, SessionService sessionService) {
        this.node = node;
        this.sessionService = sessionService;
    }

    /**
     * @return a response to the request.
     * If the return value is an exception, the session should be closed.
     */
    public Mono<TurmsNotification> processServiceRequest(ServiceRequest serviceRequest) {
        // Validate
        if (!node.isActive()) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAVAILABLE));
        }
        Long userId = serviceRequest.getUserId();
        DeviceType deviceType = serviceRequest.getDeviceType();
        UserSession session = sessionService.getLocalUserSession(userId, deviceType);
        if (session == null) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED));
        }

        // Flow control
        Long requestId = serviceRequest.getRequestId();
        if (areRequestsTooFrequent(session)) {
            TurmsNotification notification = getNotificationFromStatusCode(TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT, requestId);
            return Mono.just(notification);
        }

        // Update heartbeat and forward request
        return sessionService.updateHeartbeatTimestamp(userId, session)
                .flatMap(wasSuccessful -> {
                    if (!wasSuccessful) {
                        log.error("Failed to update session's heartbeat for user: " + userId);
                        return Mono.just(getNotificationFromStatusCode(TurmsStatusCode.SERVER_INTERNAL_ERROR, requestId));
                    }
                    return sendServiceRequest(serviceRequest)
                            .onErrorResume(throwable -> {
                                ServiceResponse serviceResponse;
                                if (throwable instanceof RpcException) {
                                    RpcException rpcException = (RpcException) throwable;
                                    if (rpcException.isServerError()) {
                                        log.error("Failed to respond to the request: {}. {}", serviceRequest, throwable);
                                    }
                                    serviceResponse = new ServiceResponse(null, rpcException.getStatusCode(), throwable.toString());
                                } else {
                                    log.error("Failed to respond to the request: {}", serviceRequest, throwable);
                                    serviceResponse = new ServiceResponse(null, TurmsStatusCode.SERVER_INTERNAL_ERROR, throwable.toString());
                                }
                                return Mono.just(serviceResponse);
                            })
                            .map(serviceResponse -> getNotificationFromResponse(serviceResponse, requestId))
                            .defaultIfEmpty(getNotificationFromStatusCode(TurmsStatusCode.NO_CONTENT, requestId));
                });
    }

    private Mono<ServiceResponse> sendServiceRequest(ServiceRequest serviceRequest) {
        HandleServiceRequest request = new HandleServiceRequest(serviceRequest);
        return node.getRpcService().requestResponse(request);
    }

    private boolean areRequestsTooFrequent(UserSession session) {
        int requestInterval = node.getSharedProperties().getGateway().getClientApi().getMinClientRequestsIntervalMillis();
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
            IllegalArgumentException exception = new IllegalArgumentException("The business code must not be null");
            log.error(exception);
            throw exception;
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
