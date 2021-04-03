package unit.im.turms.gateway.service.impl;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.impl.InboundRequestService;
import im.turms.gateway.service.impl.SessionService;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.rpc.RpcService;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.manager.ServerStatusManager;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.ClientApiProperties;
import im.turms.server.common.property.env.gateway.GatewayProperties;
import im.turms.server.common.rpc.request.HandleServiceRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
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
        InboundRequestService inboundRequestService = new InboundRequestService(null, mockTurmsPropertiesManager(), null, null);

        assertThat(inboundRequestService).isNotNull();
    }

    @Test
    void processServiceRequest_shouldThrow_ifNodeIsInactive() {
        InboundRequestService inboundRequestService = newInboundRequestService(false, null, false, true, true);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, 1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof TurmsBusinessException &&
                                ((TurmsBusinessException) throwable).getCode().equals(TurmsStatusCode.SERVER_UNAVAILABLE))
                .verify();
    }

    @Test
    void processServiceRequest_shouldThrow_ifUserIsOffline() {
        InboundRequestService inboundRequestService = newInboundRequestService(true, null, false, true, true);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, 1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof TurmsBusinessException && ((TurmsBusinessException) throwable).getCode()
                                .equals(TurmsStatusCode.SEND_REQUEST_FROM_NON_EXISTING_SESSION))
                .verify();
    }

    @Test
    void processServiceRequest_shouldReturnError_ifRequestTooFrequent() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(true, session, true, true, true);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, 1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectNextMatches(
                        notification -> notification.getCode() == TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT.getBusinessCode())
                .verifyComplete();
    }

    @Test
    void processServiceRequest_shouldReturnError_ifFailedToUpdateHeartbeat() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(true, session, false, false, true);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, 1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectNextMatches(
                        notification -> notification.getCode() == TurmsStatusCode.SERVER_INTERNAL_ERROR.getBusinessCode())
                .verifyComplete();
    }

    @Test
    void processServiceRequest_shouldReturnError_ifFailedToHandleRequest() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(true, session, false, false, false);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, 1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectNextMatches(
                        notification -> notification.getCode() == TurmsStatusCode.SERVER_INTERNAL_ERROR.getBusinessCode())
                .verifyComplete();
    }

    @Test
    void processServiceRequest_shouldReturnOk_ifHandleRequestSuccessfully() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(true, session, false, true, true);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ServiceRequest request = new ServiceRequest(1L, 1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(request);

        StepVerifier.create(result)
                .expectNextMatches(notification -> notification.getCode() == responseForSuccess.getCode().getBusinessCode())
                .verifyComplete();
    }

    private InboundRequestService newInboundRequestService(
            boolean isActive,
            UserSession session,
            boolean isFrequent,
            boolean updateHeartbeatSuccessfully,
            boolean handleRequestSuccessfully) {
        // Node
        Node node = mock(Node.class);
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

        int minClientRequestInterval = isFrequent ? Integer.MAX_VALUE : 0;
        TurmsProperties properties = new TurmsProperties().toBuilder()
                .gateway(new GatewayProperties().toBuilder()
                        .clientApi(new ClientApiProperties().toBuilder()
                                .minClientRequestIntervalMillis(minClientRequestInterval)
                                .build())
                        .build())
                .build();
        when(node.getSharedProperties())
                .thenReturn(properties);

        // ServerStatusManager
        ServerStatusManager serverStatusManager = mock(ServerStatusManager.class);
        when(serverStatusManager.isActive())
                .thenReturn(isActive);

        // SessionService
        SessionService sessionService = mock(SessionService.class);
        when(sessionService.getLocalUserSession(any(), any()))
                .thenReturn(session);
        when(sessionService.updateHeartbeatTimestamp(any(), any(UserSession.class)))
                .thenReturn(Mono.just(updateHeartbeatSuccessfully));

        return new InboundRequestService(node, mockTurmsPropertiesManager(), serverStatusManager, sessionService);
    }

    private TurmsPropertiesManager mockTurmsPropertiesManager() {
        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getLocalProperties())
                .thenReturn(new TurmsProperties());
        return propertiesManager;
    }

}
