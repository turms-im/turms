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

package im.turms.gateway.domain.servicerequest.service;

import jakarta.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import im.turms.gateway.access.client.common.UserSession;
import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.access.servicerequest.dto.ServiceRequest;
import im.turms.server.common.access.servicerequest.dto.ServiceResponse;
import im.turms.server.common.access.servicerequest.rpc.HandleServiceRequest;
import im.turms.server.common.infra.cluster.node.Node;

/**
 * @author James Chen
 */
@Service
public class ServiceRequestService {

    private static final ServiceResponse REQUEST_RESPONSE_NO_CONTENT =
            new ServiceResponse(null, ResponseStatusCode.NO_CONTENT, null);

    private final Node node;

    public ServiceRequestService(Node node) {
        this.node = node;
    }

    /**
     * @return a response to the request.
     * @implNote The method ensures turmsRequestBuffer in serviceRequest will be released by 1
     */
    public Mono<TurmsNotification> handleServiceRequest(
            UserSession session,
            ServiceRequest serviceRequest) {
        try {
            // Update request timestamp
            session.setLastRequestTimestampMillis(System.currentTimeMillis());
            // Forward request
            serviceRequest.getTurmsRequestBuffer()
                    .retain();
            HandleServiceRequest request = new HandleServiceRequest(serviceRequest);
            return node.getRpcService()
                    .requestResponse(request)
                    .defaultIfEmpty(REQUEST_RESPONSE_NO_CONTENT)
                    .map(response -> getNotificationFromResponse(response,
                            serviceRequest.getRequestId()));
        } catch (Exception e) {
            return Mono.error(e);
        } finally {
            serviceRequest.getTurmsRequestBuffer()
                    .release();
        }
    }

    private TurmsNotification getNotificationFromResponse(
            @NotNull ServiceResponse response,
            long requestId) {
        ResponseStatusCode code = response.code();
        if (code == null) {
            throw new IllegalArgumentException(
                    "The business code should not be null in the service response: "
                            + response);
        }
        TurmsNotification.Builder builder = ClientMessagePool.getTurmsNotificationBuilder();
        String reason = response.reason();
        if (reason != null) {
            builder.setReason(reason);
        }
        TurmsNotification.Data dataForRequester = response.dataForRequester();
        if (dataForRequester != null) {
            builder.setData(dataForRequester);
        }
        return builder.setTimestamp(System.currentTimeMillis())
                .setCode(code.getBusinessCode())
                .setRequestId(requestId)
                .build();
    }

}