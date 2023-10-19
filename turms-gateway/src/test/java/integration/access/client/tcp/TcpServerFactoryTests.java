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

package integration.access.client.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Duration;

import io.netty.buffer.ByteBufInputStream;
import io.netty.handler.codec.haproxy.HAProxyCommand;
import io.netty.handler.codec.haproxy.HAProxyMessage;
import io.netty.handler.codec.haproxy.HAProxyMessageEncoder;
import io.netty.handler.codec.haproxy.HAProxyProtocolVersion;
import io.netty.handler.codec.haproxy.HAProxyProxiedProtocol;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpClient;
import reactor.test.StepVerifier;

import im.turms.gateway.access.client.tcp.TcpServerFactory;
import im.turms.server.common.access.client.codec.CodecFactory;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.healthcheck.ServerStatusManager;
import im.turms.server.common.infra.healthcheck.ServiceAvailability;
import im.turms.server.common.infra.property.constant.RemoteAddressSourceProxyProtocolMode;
import im.turms.server.common.infra.property.env.gateway.network.TcpProperties;
import im.turms.server.common.infra.property.env.gateway.network.TcpRemoteAddressSourceProperties;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class TcpServerFactoryTests {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 3221;
    private static final String FAKE_CLIENT_IP = "193.3.2.21";
    private static final int FAKE_CLIENT_PORT = 1933;
    private static final TurmsRequest TURMS_REQUEST = TurmsRequest.newBuilder()
            .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                    .setText("I Wish I Knew (How It Would Feel to Be Free)"))
            .build();

    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    @Test
    void haproxy_v1_tcp4() {
        verify(new HAProxyMessage(
                HAProxyProtocolVersion.V1,
                HAProxyCommand.PROXY,
                HAProxyProxiedProtocol.TCP4,
                FAKE_CLIENT_IP,
                SERVER_HOST,
                FAKE_CLIENT_PORT,
                SERVER_PORT));
    }

    @Test
    void haproxy_v2_tcp4() {
        verify(new HAProxyMessage(
                HAProxyProtocolVersion.V2,
                HAProxyCommand.PROXY,
                HAProxyProxiedProtocol.TCP4,
                FAKE_CLIENT_IP,
                SERVER_HOST,
                FAKE_CLIENT_PORT,
                SERVER_PORT));
    }

    void verify(HAProxyMessage message) {
        // 1. set up server
        Sinks.One<InetSocketAddress> remoteClientAddressSink = Sinks.one();
        Sinks.One<TurmsRequest> turmsRequestSink = Sinks.one();
        BlocklistService blocklistService = Mockito.mock(BlocklistService.class);
        ServerStatusManager serverStatusManager = Mockito.mock(ServerStatusManager.class);
        Mockito.when(blocklistService.isIpBlocked(Mockito.any(byte[].class)))
                .thenReturn(false);
        Mockito.when(blocklistService.isIpBlocked(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(serverStatusManager.getServiceAvailability())
                .thenReturn(ServiceAvailability.AVAILABLE);
        DisposableServer server = TcpServerFactory.create(new TcpProperties().toBuilder()
                .port(SERVER_PORT)
                .remoteAddressSource(TcpRemoteAddressSourceProperties.builder()
                        .proxyProtocolMode(RemoteAddressSourceProxyProtocolMode.REQUIRED)
                        .build())
                .build(),
                blocklistService,
                serverStatusManager,
                null,
                (connection, remoteAddress, in, out, onClose) -> {
                    remoteClientAddressSink.tryEmitValue(remoteAddress);
                    in.doOnNext(byteBuf -> {
                        try {
                            TurmsRequest turmsRequest = TurmsRequest.newBuilder()
                                    .mergeFrom(new ByteBufInputStream(byteBuf))
                                    .build();
                            turmsRequestSink.tryEmitValue(turmsRequest);
                        } catch (IOException e) {
                            turmsRequestSink.tryEmitError(e);
                        }
                    })
                            .subscribe(null, turmsRequestSink::tryEmitError);
                    return turmsRequestSink.asMono()
                            .then();
                },
                1024);

        try {
            // 2. set up client
            Connection fakeProxy = TcpClient.create()
                    .port(SERVER_PORT)
                    .host(SERVER_HOST)
                    .doOnChannelInit(
                            (connectionObserver, channel, remoteAddress) -> channel.pipeline()
                                    .addFirst(HAProxyMessageEncoder.INSTANCE,
                                            CodecFactory.getVarintLengthFieldPrepender(),
                                            CodecFactory.getProtobufFrameEncoder()))
                    .connectNow(TIMEOUT);
            fakeProxy.outbound()
                    .sendObject(Flux.just(message, TURMS_REQUEST))
                    .then()
                    .block(TIMEOUT);

            // 3. test
            StepVerifier.create(remoteClientAddressSink.asMono()
                    .timeout(TIMEOUT))
                    .expectNextMatches(remoteClientAddress -> {
                        assertThat(remoteClientAddress.getHostString()).isEqualTo(FAKE_CLIENT_IP);
                        assertThat(remoteClientAddress.getPort()).isEqualTo(FAKE_CLIENT_PORT);
                        return true;
                    })
                    .verifyComplete();
            StepVerifier.create(turmsRequestSink.asMono()
                    .timeout(TIMEOUT))
                    .expectNext(TURMS_REQUEST)
                    .verifyComplete();
        } finally {
            server.disposeNow();
        }
    }

}