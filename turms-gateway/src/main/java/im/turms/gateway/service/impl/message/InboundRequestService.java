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

package im.turms.gateway.service.impl.message;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.impl.session.SessionService;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.rpc.request.HandleServiceRequest;
import im.turms.server.common.service.blocklist.BlocklistService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
@Service
@Log4j2
public class InboundRequestService {

    private static final ServiceResponse REQUEST_RESPONSE_NO_CONTENT = new ServiceResponse(null, TurmsStatusCode.NO_CONTENT, null);

    private final Node node;
    private final BlocklistService blocklistService;
    private final SessionService sessionService;

    public InboundRequestService(Node node,
                                 BlocklistService blocklistService,
                                 SessionService sessionService) {
        this.node = node;
        this.blocklistService = blocklistService;
        this.sessionService = sessionService;
    }

    public void processHeartbeatRequest(UserSession session) {
        sessionService.updateHeartbeatTimestamp(session);
    }

    /**
     * @return a response to the request.
     * @implNote The method ensures turmsRequestBuffer in serviceRequest will be released by 1
     */
    public Mono<TurmsNotification> processServiceRequest(ServiceRequest serviceRequest) {
        try {
            return processServiceRequest0(serviceRequest);
        } catch (Exception e) {
            return Mono.error(e);
        } finally {
            serviceRequest.getTurmsRequestBuffer().release();
        }
    }

    private Mono<TurmsNotification> processServiceRequest0(ServiceRequest serviceRequest) {
        // Validate
        Long userId = serviceRequest.getUserId();
        DeviceType deviceType = serviceRequest.getDeviceType();
        UserSession session;
        try {
            session = sessionService.getLocalUserSession(userId, deviceType);
        } catch (Exception e) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.INVALID_REQUEST, e.getMessage()));
        }
        if (session == null) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SEND_REQUEST_FROM_NON_EXISTING_SESSION));
        }

        // Rate limiting
        Long requestId = serviceRequest.getRequestId();
        long now = System.currentTimeMillis();
        if (!session.tryAcquireToken(now)) {
            blocklistService.tryBlockIpForFrequentRequest(serviceRequest.getIp());
            blocklistService.tryBlockUserIdForFrequentRequest(userId);
            TurmsNotification notification = getNotificationFromStatusCode(TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT, requestId);
            return Mono.just(notification);
        }

        // Update heartbeat
        sessionService.updateHeartbeatTimestamp(session);

        // Forward request
        serviceRequest.getTurmsRequestBuffer().retain();
        return sendServiceRequest(serviceRequest)
                .defaultIfEmpty(REQUEST_RESPONSE_NO_CONTENT)
                .map(response -> getNotificationFromResponse(response, requestId));
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

    private TurmsNotification getNotificationFromResponse(@NotNull ServiceResponse response, long requestId) {
        TurmsStatusCode code = response.code();
        if (code == null) {
            throw new IllegalArgumentException("The business code should not be null in the service response: " + response);
        }
        TurmsNotification.Builder builder = TurmsNotification.newBuilder();
        String reason = response.reason();
        if (reason != null) {
            builder.setReason(reason);
        }
        TurmsNotification.Data dataForRequester = response.dataForRequester();
        if (dataForRequester != null) {
            builder.setData(dataForRequester);
        }
        return builder
                .setCode(code.getBusinessCode())
                .setRequestId(requestId)
                .build();
    }

}