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

import jakarta.annotation.Nullable;

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
import im.turms.server.common.infra.property.env.common.SslProperties;
import im.turms.server.common.infra.property.env.gateway.TcpProperties;

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
        TcpServer server = TcpServer.create()
                .host(host)
                .port(port)
                .option(CONNECT_TIMEOUT_MILLIS, tcpProperties.getConnectionTimeout())
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
                // Note that the elements from "in.receive()" is emitted by FluxReceive,
                // which will release buffer after "onNext" returns
                .handle((in, out) -> connectionListener.onAdded((Connection) in,
                        false,
                        in.receive(),
                        out,
                        ((Connection) in).onDispose()))
                .doOnChannelInit((connectionObserver, channel, remoteAddress) -> channel.pipeline()
                        .addFirst("serviceAvailabilityHandler", serviceAvailabilityHandler))
                .doOnConnection(connection -> {
                    // Inbound
                    connection.addHandlerLast("varintLengthBasedFrameDecoder",
                            CodecFactory.getExtendedVarintLengthBasedFrameDecoder(maxFrameLength));

                    // Outbound
                    connection.addHandlerLast("varintLengthFieldPrepender",
                            CodecFactory.getVarintLengthFieldPrepender());
                    // For advanced operations, they encode objects to buffers themselves,
                    // "protobufFrameEncoder" will just ignore them. But some simple
                    // operations will pass TurmsNotification instances down, so we still need to
                    // encode them.
                    connection.addHandlerLast("protobufFrameEncoder",
                            CodecFactory.getProtobufFrameEncoder());
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