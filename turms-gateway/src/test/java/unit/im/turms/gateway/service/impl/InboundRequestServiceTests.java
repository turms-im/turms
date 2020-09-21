package unit.im.turms.gateway.service.impl;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.impl.InboundRequestService;
import im.turms.gateway.service.impl.SessionService;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.rpc.RpcService;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.env.gateway.ClientApiProperties;
import im.turms.server.common.property.env.gateway.GatewayProperties;
import im.turms.server.common.rpc.request.HandleServiceRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class InboundRequestServiceTests {

    private final ServiceResponse responseForSuccess = new ServiceResponse(
            TurmsNotification.Data.newBuilder().buildPartial(),
            TurmsStatusCode.OK,
            "reason");

    @Test
    void constructor_shouldReturnInstance() {
        InboundRequestService inboundRequestService = new InboundRequestService(null, null);

        assertNotNull(inboundRequestService);
    }

    @Test
    void processServiceRequest_shouldThrow_ifNodeIsInactive() {
        InboundRequestService inboundRequestService = newInboundRequestService(false, null, false, true, true);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof TurmsBusinessException && ((TurmsBusinessException) throwable).getCode().equals(TurmsStatusCode.UNAVAILABLE))
                .verify();
    }

    @Test
    void processServiceRequest_shouldThrow_ifUserIsOffline() {
        InboundRequestService inboundRequestService = newInboundRequestService(true, null, false, true, true);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof TurmsBusinessException && ((TurmsBusinessException) throwable).getCode().equals(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED))
                .verify();
    }

    @Test
    void processServiceRequest_shouldReturnError_ifRequestTooFrequent() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(true, session, true, true, true);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectNextMatches(notification -> notification.getCode().getValue() == TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT.getBusinessCode())
                .verifyComplete();
    }

    @Test
    void processServiceRequest_shouldReturnError_ifFailedToUpdateHeartbeat() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(true, session, false, false, true);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectNextMatches(notification -> notification.getCode().getValue() == TurmsStatusCode.SERVER_INTERNAL_ERROR.getBusinessCode())
                .verifyComplete();
    }

    @Test
    void processServiceRequest_shouldReturnError_ifFailedToHandleRequest() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(true, session, false, false, false);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectNextMatches(notification -> notification.getCode().getValue() == TurmsStatusCode.SERVER_INTERNAL_ERROR.getBusinessCode())
                .verifyComplete();
    }

    @Test
    void processServiceRequest_shouldReturnOk_ifHandleRequestSuccessfully() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(true, session, false, true, true);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectNextMatches(notification -> notification.getCode().getValue() == responseForSuccess.getCode().getBusinessCode())
                .verifyComplete();
    }

    private InboundRequestService newInboundRequestService(
            boolean isActive,
            UserSession session,
            boolean isFrequent,
            boolean updateHeartbeatSuccessfully,
            boolean handleRequestSuccessfully) {
        Node node = mock(Node.class);
        when(node.isActive())
                .thenReturn(isActive);
        RpcService rpcService = mock(RpcService.class);
        if (handleRequestSuccessfully) {
            when(rpcService.requestResponse(any(HandleServiceRequest.class)))
                    .thenReturn(Mono.just(responseForSuccess));
        } else {
            when(rpcService.requestResponse(any(HandleServiceRequest.class)))
                    .thenThrow(new IllegalStateException());
        }
        when(node.getRpcService())
                .thenReturn(rpcService);

        TurmsProperties turmsProperties = new TurmsProperties();
        GatewayProperties gateway = new GatewayProperties();
        ClientApiProperties clientApi = new ClientApiProperties();
        if (isFrequent) {
            clientApi.setMinClientRequestIntervalMillis(Integer.MAX_VALUE);
        } else {
            clientApi.setMinClientRequestIntervalMillis(0);
        }
        gateway.setClientApi(clientApi);
        turmsProperties.setGateway(gateway);
        when(node.getSharedProperties())
                .thenReturn(turmsProperties);

        SessionService sessionService = mock(SessionService.class);
        when(sessionService.getLocalUserSession(any(), any()))
                .thenReturn(session);
        when(sessionService.updateHeartbeatTimestamp(any(), any(UserSession.class)))
                .thenReturn(Mono.just(updateHeartbeatSuccessfully));

        return new InboundRequestService(node, sessionService);
    }

}
