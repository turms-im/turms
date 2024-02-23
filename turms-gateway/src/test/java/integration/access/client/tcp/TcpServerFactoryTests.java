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
import java.nio.file.Path;
import java.time.Duration;

import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.haproxy.HAProxyCommand;
import io.netty.handler.codec.haproxy.HAProxyMessage;
import io.netty.handler.codec.haproxy.HAProxyMessageEncoder;
import io.netty.handler.codec.haproxy.HAProxyProtocolVersion;
import io.netty.handler.codec.haproxy.HAProxyProxiedProtocol;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;
import reactor.netty.DisposableServer;
import reactor.netty.NettyPipeline;
import reactor.netty.channel.ChannelOperations;
import reactor.netty.tcp.TcpClient;
import reactor.test.StepVerifier;

import im.turms.gateway.access.client.tcp.TcpServerFactory;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.server.common.access.client.codec.CodecFactory;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.healthcheck.ServerStatusManager;
import im.turms.server.common.infra.healthcheck.ServiceAvailability;
import im.turms.server.common.infra.net.SslContextSpecType;
import im.turms.server.common.infra.net.SslUtil;
import im.turms.server.common.infra.property.constant.RemoteAddressSourceProxyProtocolMode;
import im.turms.server.common.infra.property.env.common.SslProperties;
import im.turms.server.common.infra.property.env.gateway.network.TcpProperties;
import im.turms.server.common.infra.property.env.gateway.network.TcpRemoteAddressSourceProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static im.turms.server.common.infra.unit.ByteSizeUnit.KB;

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

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void shouldSendAndReceiveStreamSuccessfully_forBothSslAndNonSslConnections(boolean enableSsl) {
        Duration timeout = Duration.ofSeconds(10);

        // 1. Prepare properties
        SslProperties serverSslProperties;
        SslProperties clientSslProperties;
        if (enableSsl) {
            String alias = "turms";
            String storePassword = "im.turms";

            Path keyStorePath = Path.of("server-keystore-test.p12")
                    .toAbsolutePath()
                    .normalize();
            keyStorePath.toFile()
                    .deleteOnExit();
            String keyStorePathStr = keyStorePath.toString();

            Path certificatePath = Path.of("server-certificate-test.pem")
                    .toAbsolutePath()
                    .normalize();
            certificatePath.toFile()
                    .deleteOnExit();
            String certificatePathStr = certificatePath.toString();

            Path trustStorePath = Path.of("client-truststore-test.jks")
                    .toAbsolutePath()
                    .normalize();
            trustStorePath.toFile()
                    .deleteOnExit();
            String trustStorePathStr = trustStorePath.toString();

            SslUtil.generateKeyStore(keyStorePathStr,
                    "RSA",
                    alias,
                    storePassword,
                    2048,
                    3650,
                    "Turms",
                    "san=ip:127.0.0.1,dns:localhost")
                    .then(Mono.defer(() -> SslUtil.generateCertificate(certificatePathStr,
                            keyStorePathStr,
                            alias,
                            storePassword)))
                    .then(Mono.defer(() -> SslUtil.generateTrustStore(trustStorePathStr,
                            certificatePathStr,
                            storePassword,
                            storePassword)))
                    .block(timeout);
            serverSslProperties = new SslProperties().toBuilder()
                    .enabled(true)
                    .keyStore(keyStorePathStr)
                    .keyStorePassword(storePassword)
                    .build();
            clientSslProperties = new SslProperties().toBuilder()
                    .enabled(true)
                    .trustStore(trustStorePathStr)
                    .trustStorePassword(storePassword)
                    .build();
        } else {
            serverSslProperties = new SslProperties();
            clientSslProperties = new SslProperties();
        }

        TcpProperties tcpProperties = new TcpProperties().toBuilder()
                .remoteAddressSource(TcpRemoteAddressSourceProperties.builder()
                        .proxyProtocolMode(RemoteAddressSourceProxyProtocolMode.DISABLED)
                        .build())
                .ssl(serverSslProperties)
                .port(0)
                .wiretap(true)
                .build();

        // 2. Set up server
        BlocklistService blocklistService = mock(BlocklistService.class);
        when(blocklistService.isIpBlocked(any(byte[].class))).thenReturn(false);

        ServerStatusManager serverStatusManager = mock(ServerStatusManager.class);
        when(serverStatusManager.getServiceAvailability())
                .thenReturn(ServiceAvailability.AVAILABLE);

        SessionService sessionService = mock(SessionService.class);

        int requestId = 123456;
        DisposableServer server = TcpServerFactory.create(tcpProperties,
                blocklistService,
                serverStatusManager,
                sessionService,
                (connection, remoteAddress, in, out, onClose) -> {
                    out.sendObject(TurmsNotification.newBuilder()
                            .setRequestId(requestId)
                            .build())
                            .then()
                            .subscribe();
                    return Mono.never();
                },
                8 * KB);

        Sinks.One<Object> responseSink = Sinks.one();

        // 3. Set up client
        TcpClient tcpClient = TcpClient.create()
                .host("127.0.0.1")
                .port(server.port());
        if (enableSsl) {
            tcpClient = tcpClient
                    .secure(sslContextSpec -> SslUtil.configureSslContextSpec(sslContextSpec,
                            SslContextSpecType.TCP,
                            clientSslProperties,
                            false));
        }
        tcpClient.doOnChannelInit((connectionObserver, channel, remoteAddress) -> {
            ChannelPipeline pipeline = channel.pipeline();
            // Inbound
            pipeline
                    // Ensure our inbound handlers are before the handler for FluxReceive.
                    .addBefore(NettyPipeline.ReactiveBridge,
                            "varintLengthBasedFrameDecoder",
                            CodecFactory.getVarintLengthBasedFrameDecoder())
                    .addBefore(NettyPipeline.ReactiveBridge,
                            "turmsNotificationDecoder",
                            CodecFactory.getTurmsNotificationDecoder())
                    // Outbound
                    .addLast("varintLengthFieldPrepender",
                            CodecFactory.getVarintLengthFieldPrepender())
                    .addLast("protobufFrameEncoder", CodecFactory.getProtobufFrameEncoder());
        })
                .connect()
                .doOnSuccess(conn -> {
                    ChannelOperations<?, ?> connection = (ChannelOperations<?, ?>) conn;
                    connection.receiveObject()
                            .cast(TurmsNotification.class)
                            .doOnNext(responseSink::tryEmitValue)
                            .subscribe(null, responseSink::tryEmitError);
                    connection.sendObject(TurmsRequest.newBuilder()
                            .setRequestId(requestId)
                            .build())
                            .then()
                            .subscribe(null, responseSink::tryEmitError);
                })
                .subscribeOn(Schedulers.newSingle("test-tcp-client"))
                .block(Duration.ofSeconds(30));

        // 4. Verify
        StepVerifier.create(responseSink.asMono()
                .timeout(timeout))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void haproxy_v1_tcp4() {
        shouldSendAndReceiveStreamSuccessfully_whenHaproxyEnabled(new HAProxyMessage(
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
        shouldSendAndReceiveStreamSuccessfully_whenHaproxyEnabled(new HAProxyMessage(
                HAProxyProtocolVersion.V2,
                HAProxyCommand.PROXY,
                HAProxyProxiedProtocol.TCP4,
                FAKE_CLIENT_IP,
                SERVER_HOST,
                FAKE_CLIENT_PORT,
                SERVER_PORT));
    }

    void shouldSendAndReceiveStreamSuccessfully_whenHaproxyEnabled(HAProxyMessage message) {
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
            TcpClient.create()
                    .port(SERVER_PORT)
                    .host(SERVER_HOST)
                    .doOnChannelInit(
                            (connectionObserver, channel, remoteAddress) -> channel.pipeline()
                                    .addFirst(HAProxyMessageEncoder.INSTANCE,
                                            CodecFactory.getVarintLengthFieldPrepender(),
                                            CodecFactory.getProtobufFrameEncoder()))
                    .connect()
                    .doOnSuccess(connection -> connection.outbound()
                            .sendObject(Flux.just(message, TURMS_REQUEST))
                            .then()
                            .subscribe(null, t -> {
                                remoteClientAddressSink.tryEmitError(t);
                                turmsRequestSink.tryEmitError(t);
                            }))
                    .subscribe(null, t -> {
                        remoteClientAddressSink.tryEmitError(t);
                        turmsRequestSink.tryEmitError(t);
                    });

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