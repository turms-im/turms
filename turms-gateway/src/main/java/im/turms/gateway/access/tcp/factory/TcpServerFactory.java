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

import im.turms.gateway.access.tcp.handler.TcpHandlerConfig;
import im.turms.server.common.access.common.resource.LoopResourcesFactory;
import im.turms.server.common.manager.ServerStatusManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.TcpProperties;
import im.turms.server.common.util.SslUtil;
import org.reactivestreams.Publisher;
import org.springframework.boot.web.server.Ssl;
import reactor.netty.DisposableServer;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;
import reactor.netty.tcp.TcpServer;

import javax.annotation.Nullable;
import java.util.function.BiFunction;

import static io.netty.channel.ChannelOption.*;

/**
 * @author James Chen
 */
public class TcpServerFactory {

    private TcpServerFactory() {
    }

    @Nullable
    public static DisposableServer create(TurmsPropertiesManager propertiesManager,
                                          ServerStatusManager serverStatusManager,
                                          BiFunction<? super NettyInbound, ? super NettyOutbound, ? extends Publisher<Void>> handler) {
        TcpProperties tcpProperties = propertiesManager.getLocalProperties().getGateway().getTcp();
        if (!tcpProperties.isEnabled()) {
            return null;
        }
        TcpHandlerConfig handlerConfig = new TcpHandlerConfig(serverStatusManager);
        TcpServer server = TcpServer.create()
                // Don't set SO_SNDBUF and SO_RCVBUF because of
                // the reasons mentioned in https://developer.aliyun.com/article/724580
                .option(SO_REUSEADDR, true)
                // large enough to handle bursts and GC pauses
                // but don't set too large to prevent SYN-Flood attacks
                .option(SO_BACKLOG, 4096)
                .childOption(SO_REUSEADDR, true)
                .childOption(SO_LINGER, 0)
                .childOption(TCP_NODELAY, false)
                .host(tcpProperties.getHost())
                .port(tcpProperties.getPort())
                .runOn(LoopResourcesFactory.createForServer("gateway-tcp"))
                .handle(handler)
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