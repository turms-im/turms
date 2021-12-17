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

package im.turms.gateway.access.tcp.factory;

import im.turms.gateway.access.common.function.ConnectionHandler;
import im.turms.gateway.access.tcp.handler.TcpHandlerConfig;
import im.turms.gateway.constant.MetricsConstant;
import im.turms.gateway.service.impl.session.SessionService;
import im.turms.server.common.access.common.resource.LoopResourcesFactory;
import im.turms.server.common.healthcheck.ServerStatusManager;
import im.turms.server.common.metrics.TurmsMicrometerChannelMetricsRecorder;
import im.turms.server.common.property.env.gateway.TcpProperties;
import im.turms.server.common.service.blocklist.BlocklistService;
import im.turms.server.common.util.SslUtil;
import org.springframework.boot.web.server.Ssl;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

import javax.annotation.Nullable;

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
    public static DisposableServer create(TcpProperties tcpProperties,
                                          BlocklistService blocklistService,
                                          ServerStatusManager serverStatusManager,
                                          SessionService sessionService,
                                          ConnectionHandler handler,
                                          int maxFrameLength) {
        TcpHandlerConfig handlerConfig = new TcpHandlerConfig(blocklistService, serverStatusManager, sessionService, maxFrameLength);
        TcpServer server = TcpServer.create()
                .host(tcpProperties.getHost())
                .port(tcpProperties.getPort())
                .option(CONNECT_TIMEOUT_MILLIS, tcpProperties.getConnectionTimeout())
                // Don't set SO_SNDBUF and SO_RCVBUF because of
                // the reasons mentioned in https://developer.aliyun.com/article/724580
                .option(SO_REUSEADDR, true)
                .option(SO_BACKLOG, tcpProperties.getBacklog())
                .childOption(SO_REUSEADDR, true)
                .childOption(SO_LINGER, 0)
                .childOption(TCP_NODELAY, true)
                .wiretap(tcpProperties.isWiretap())
                .runOn(LoopResourcesFactory.createForServer("gateway-tcp"))
                .metrics(true, () -> new TurmsMicrometerChannelMetricsRecorder(MetricsConstant.CLIENT_NETWORK, "tcp"))
                // Note that the elements from "in.receive()" is emitted by FluxReceive,
                // which will release buffer after "onNext" returns
                .handle((in, out) -> handler.handle((Connection) in, false, in.receive(), out, ((Connection) in).onDispose()))
                .doOnChannelInit((connectionObserver, channel, remoteAddress) -> handlerConfig.configureChannel(channel))
                .doOnConnection(handlerConfig::configureConnection);
        Ssl ssl = tcpProperties.getSsl();
        if (ssl.isEnabled()) {
            server.secure(spec -> SslUtil.configureSslContextSpec(spec, ssl, true));
        }
        return server
                .bind()
                .block();
    }

}