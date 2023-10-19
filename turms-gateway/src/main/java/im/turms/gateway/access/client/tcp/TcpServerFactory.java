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

package im.turms.gateway.access.client.tcp;

import java.net.InetSocketAddress;
import jakarta.annotation.Nullable;

import io.netty.channel.Channel;
import reactor.core.publisher.Sinks;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

import im.turms.gateway.access.client.common.channel.ServiceAvailabilityHandler;
import im.turms.gateway.access.client.common.connection.ConnectionListener;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.gateway.infra.metrics.MetricNameConst;
import im.turms.gateway.infra.thread.ThreadNameConst;
import im.turms.server.common.access.client.codec.CodecFactory;
import im.turms.server.common.access.common.LoopResourcesFactory;
import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.healthcheck.ServerStatusManager;
import im.turms.server.common.infra.metrics.TurmsMicrometerChannelMetricsRecorder;
import im.turms.server.common.infra.net.BindException;
import im.turms.server.common.infra.net.SslUtil;
import im.turms.server.common.infra.property.constant.RemoteAddressSourceProxyProtocolMode;
import im.turms.server.common.infra.property.env.common.SslProperties;
import im.turms.server.common.infra.property.env.gateway.network.TcpProperties;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static io.netty.channel.ChannelOption.SO_BACKLOG;
import static io.netty.channel.ChannelOption.SO_LINGER;
import static io.netty.channel.ChannelOption.SO_REUSEADDR;
import static io.netty.channel.ChannelOption.TCP_NODELAY;

/**
 * @author James Chen
 */
public final class TcpServerFactory {

    private TcpServerFactory() {
    }

    @Nullable
    public static DisposableServer create(
            TcpProperties tcpProperties,
            BlocklistService blocklistService,
            ServerStatusManager serverStatusManager,
            SessionService sessionService,
            ConnectionListener connectionListener,
            int maxFrameLength) {
        ServiceAvailabilityHandler serviceAvailabilityHandler = new ServiceAvailabilityHandler(
                blocklistService,
                serverStatusManager,
                sessionService);
        String host = tcpProperties.getHost();
        int port = tcpProperties.getPort();
        RemoteAddressSourceProxyProtocolMode proxyProtocolMode =
                tcpProperties.getRemoteAddressSource()
                        .getProxyProtocolMode();
        TcpServer server = TcpServer.create()
                .host(host)
                .port(port)
                .option(CONNECT_TIMEOUT_MILLIS, tcpProperties.getConnectTimeoutMillis())
                // Don't set SO_SNDBUF and SO_RCVBUF because of
                // the reasons mentioned in https://developer.aliyun.com/article/724580
                .option(SO_REUSEADDR, true)
                .option(SO_BACKLOG, tcpProperties.getBacklog())
                .childOption(SO_REUSEADDR, true)
                .childOption(SO_LINGER, 0)
                .childOption(TCP_NODELAY, true)
                .wiretap(tcpProperties.isWiretap())
                .runOn(LoopResourcesFactory.createForServer(ThreadNameConst.GATEWAY_TCP_PREFIX))
                .metrics(true,
                        () -> new TurmsMicrometerChannelMetricsRecorder(
                                MetricNameConst.CLIENT_NETWORK,
                                "tcp"))
                .doOnChannelInit((connectionObserver, channel, remoteAddress) -> channel.pipeline()
                        .addFirst("serviceAvailabilityHandler", serviceAvailabilityHandler))
                .handle((in, out) -> {
                    Connection connection = (Connection) in;
                    Sinks.One<InetSocketAddress> remoteAddressSink = Sinks.one();
                    // Inbound
                    connection.addHandlerLast("varintLengthBasedFrameDecoder",
                            CodecFactory.getExtendedVarintLengthBasedFrameDecoder(maxFrameLength));
                    Channel channel = connection.channel();
                    if (RemoteAddressSourceProxyProtocolMode.REQUIRED == proxyProtocolMode) {
                        HAProxyUtil.addProxyProtocolHandlers(channel.pipeline(), address -> {
                            if (blocklistService.isIpBlocked(address.getAddress()
                                    .getAddress())) {
                                remoteAddressSink.tryEmitEmpty();
                            } else {
                                remoteAddressSink.tryEmitValue(address);
                            }
                        });
                        channel.config()
                                .setAutoRead(true);
                    } else if (RemoteAddressSourceProxyProtocolMode.OPTIONAL == proxyProtocolMode) {
                        HAProxyUtil.addProxyProtocolDetectorHandler(channel.pipeline(), address -> {
                            if (blocklistService.isIpBlocked(address.getAddress()
                                    .getAddress())) {
                                remoteAddressSink.tryEmitEmpty();
                            } else {
                                remoteAddressSink.tryEmitValue(address);
                            }
                        });
                        channel.config()
                                .setAutoRead(true);
                    } else {
                        remoteAddressSink.tryEmitValue((InetSocketAddress) channel.remoteAddress());
                    }

                    // Outbound
                    connection.addHandlerLast("varintLengthFieldPrepender",
                            CodecFactory.getVarintLengthFieldPrepender());
                    // For advanced operations, they encode objects to buffers themselves,
                    // "protobufFrameEncoder" will just ignore them. But some simple
                    // operations will pass TurmsNotification instances down, so we still need to
                    // encode them.
                    connection.addHandlerLast("protobufFrameEncoder",
                            CodecFactory.getProtobufFrameEncoder());

                    return remoteAddressSink.asMono()
                            .flatMap(remoteAddress -> connectionListener.onAdded(connection,
                                    remoteAddress,
                                    in.receive(),
                                    out,
                                    connection.onDispose()));
                });
        SslProperties ssl = tcpProperties.getSsl();
        if (ssl.isEnabled()) {
            server.secure(spec -> SslUtil.configureSslContextSpec(spec, ssl, true));
        }
        try {
            return server.bind()
                    .block();
        } catch (Exception e) {
            String message = "Failed to bind the TCP server on: "
                    + host
                    + ":"
                    + port;
            throw new BindException(message, e);
        }
    }

}