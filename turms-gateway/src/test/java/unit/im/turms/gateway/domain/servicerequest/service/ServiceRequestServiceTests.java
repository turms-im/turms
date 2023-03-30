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

package unit.im.turms.gateway.domain.servicerequest.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.gateway.access.client.common.UserSession;
import im.turms.gateway.domain.servicerequest.service.ServiceRequestService;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.TurmsRequestTypePool;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.access.servicerequest.dto.ServiceRequest;
import im.turms.server.common.access.servicerequest.dto.ServiceResponse;
import im.turms.server.common.access.servicerequest.rpc.HandleServiceRequest;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.rpc.RpcService;
import im.turms.server.common.infra.net.InetAddressUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class ServiceRequestServiceTests {

    private static final byte[] IP_ADDRESS = InetAddressUtil.ipStringToAddress("127.0.0.1")
            .getAddress();
    private static final Throwable HANDLE_REQUEST_FAILURE_EXCEPTION =
            new RuntimeException("Mocked error for failing to handle request");

    private final ServiceResponse responseForSuccess = new ServiceResponse(
            TurmsNotification.Data.newBuilder()
                    .buildPartial(),
            ResponseStatusCode.OK,
            "reason");

    @Test
    void constructor_shouldReturnInstance() {
        Node node = mockNode(false);
        ServiceRequestService serviceRequestService = new ServiceRequestService(node);

        assertThat(serviceRequestService).isNotNull();
    }

    @Test
    void handleServiceRequest_shouldReturnError_ifFailedToHandleRequest() {
        ServiceRequestService serviceRequestService = newInboundRequestService(false);
        Mono<TurmsNotification> result =
                serviceRequestService.handleServiceRequest(newUserSession(), newServiceRequest());

        StepVerifier.create(result)
                .verifyErrorMatches(t -> t == HANDLE_REQUEST_FAILURE_EXCEPTION);
    }

    @Test
    void handleServiceRequest_shouldReturnOk_ifHandleRequestSuccessfully() {
        ServiceRequestService serviceRequestService = newInboundRequestService(true);
        Mono<TurmsNotification> result =
                serviceRequestService.handleServiceRequest(newUserSession(), newServiceRequest());

        StepVerifier.create(result)
                .expectNextMatches(
                        notification -> notification.getCode() == responseForSuccess.code()
                                .getBusinessCode())
                .verifyComplete();
    }

    private ServiceRequestService newInboundRequestService(boolean handleRequestSuccessfully) {
        // Node
        Node node = mockNode(handleRequestSuccessfully);
        return new ServiceRequestService(node);
    }

    private UserSession newUserSession() {
        return new UserSession(1, TurmsRequestTypePool.ALL, 1L, DeviceType.ANDROID, null, null);
    }

    private ServiceRequest newServiceRequest() {
        ByteBuf buffer = UnpooledByteBufAllocator.DEFAULT.buffer();
        return new ServiceRequest(
                IP_ADDRESS,
                1L,
                DeviceType.ANDROID,
                1L,
                TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST,
                buffer);
    }

    private Node mockNode(boolean handleRequestSuccessfully) {
        Node node = mock(Node.class);
        RpcService rpcService = mock(RpcService.class);
        if (handleRequestSuccessfully) {
            when(rpcService.requestResponse(any(HandleServiceRequest.class)))
                    .thenReturn(Mono.just(responseForSuccess));
        } else {
            when(rpcService.requestResponse(any(HandleServiceRequest.class)))
                    .thenReturn(Mono.error(HANDLE_REQUEST_FAILURE_EXCEPTION));
        }
        when(node.getRpcService()).thenReturn(rpcService);
        return node;
    }

}
