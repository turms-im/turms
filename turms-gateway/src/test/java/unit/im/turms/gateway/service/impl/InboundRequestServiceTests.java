package unit.im.turms.gateway.service.impl;

import com.google.common.net.InetAddresses;
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
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.env.gateway.GatewayProperties;
import im.turms.server.common.property.env.gateway.clientapi.ClientApiProperties;
import im.turms.server.common.property.env.gateway.clientapi.RateLimitingProperties;
import im.turms.server.common.rpc.request.HandleServiceRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
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

    private static final byte[] IP_ADDRESS = InetAddresses.forString("127.0.0.1").getAddress();
    private static final Throwable HANDLE_REQUEST_FAILURE_EXCEPTION =
            new IllegalStateException("Mocked error for failing to handle request");

    private final ServiceResponse responseForSuccess = new ServiceResponse(
            TurmsNotification.Data.newBuilder().buildPartial(),
            TurmsStatusCode.OK,
            "reason");

    @Test
    void constructor_shouldReturnInstance() {
        Node node = mockNode(false);
        InboundRequestService inboundRequestService = new InboundRequestService(node, null);

        assertThat(inboundRequestService).isNotNull();
    }

    @Test
    void processServiceRequest_shouldThrow_ifUserIsOffline() {
        InboundRequestService inboundRequestService = newInboundRequestService(null, false, true);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(newServiceRequest());

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof TurmsBusinessException e
                        && e.getCode().equals(TurmsStatusCode.SEND_REQUEST_FROM_NON_EXISTING_SESSION))
                .verify();
    }

    @Test
    void processServiceRequest_shouldReturnError_ifRequestTooFrequent() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(session, true, true);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(newServiceRequest());

        StepVerifier.create(result)
                .expectNextMatches(
                        notification -> notification.getCode() == TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT.getBusinessCode())
                .verifyComplete();
    }

    @Test
    void processServiceRequest_shouldReturnError_ifFailedToHandleRequest() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(session, false, false);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(newServiceRequest());

        StepVerifier.create(result)
                .verifyErrorMatches(t -> t == HANDLE_REQUEST_FAILURE_EXCEPTION);
    }

    @Test
    void processServiceRequest_shouldReturnOk_ifHandleRequestSuccessfully() {
        UserSession session = mock(UserSession.class);
        when(session.getLastRequestTimestampMillis())
                .thenReturn(System.currentTimeMillis());

        InboundRequestService inboundRequestService = newInboundRequestService(session, false, true);
        Mono<TurmsNotification> result = inboundRequestService.processServiceRequest(newServiceRequest());

        StepVerifier.create(result)
                .expectNextMatches(notification -> notification.getCode() == responseForSuccess.code().getBusinessCode())
                .verifyComplete();
    }

    private InboundRequestService newInboundRequestService(
            UserSession session,
            boolean isFrequent,
            boolean handleRequestSuccessfully) {
        // Node
        Node node = mockNode(handleRequestSuccessfully);

        int minClientRequestInterval = isFrequent ? Integer.MAX_VALUE : 0;
        TurmsProperties properties = new TurmsProperties().toBuilder()
                .gateway(new GatewayProperties().toBuilder()
                        .clientApi(new ClientApiProperties().toBuilder()
                                .rateLimiting(new RateLimitingProperties().toBuilder()
                                        .minClientRequestIntervalMillis(minClientRequestInterval)
                                        .build())
                                .build())
                        .build())
                .build();
        when(node.getSharedProperties())
                .thenReturn(properties);

        // SessionService
        SessionService sessionService = mock(SessionService.class);
        when(sessionService.getLocalUserSession(any(), any()))
                .thenReturn(session);

        return new InboundRequestService(node, sessionService);
    }

    private ServiceRequest newServiceRequest() {
        ByteBuf buffer = UnpooledByteBufAllocator.DEFAULT.buffer();
        return new ServiceRequest(IP_ADDRESS, 1L, DeviceType.ANDROID, 1L, TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST, buffer);
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
        when(node.getRpcService())
                .thenReturn(rpcService);
        when(node.getSharedProperties())
                .thenReturn(new TurmsProperties());
        return node;
    }

}
