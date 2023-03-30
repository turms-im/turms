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

package im.turms.server.common.infra.cluster.service.connection;

import java.util.function.Consumer;

import lombok.Getter;
import reactor.core.publisher.Mono;
import reactor.netty.ChannelBindException;
import reactor.netty.DisposableServer;
import reactor.netty.channel.ChannelOperations;
import reactor.netty.channel.MicrometerChannelMetricsRecorder;
import reactor.netty.resources.LoopResources;
import reactor.netty.tcp.TcpServer;

import im.turms.server.common.access.common.LoopResourcesFactory;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.net.BindException;
import im.turms.server.common.infra.net.SslUtil;
import im.turms.server.common.infra.property.env.common.SslProperties;
import im.turms.server.common.infra.thread.ThreadNameConst;
import im.turms.server.common.infra.time.DurationConst;

import static im.turms.server.common.infra.metrics.CommonMetricNameConst.NODE_TCP_SERVER;

/**
 * @author James Chen
 */
public class ConnectionServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionServer.class);

    private final String host;
    private final int proposedPort;
    private final boolean portAutoIncrement;
    private final int portCount;
    private final SslProperties ssl;
    private final Consumer<ChannelOperations<?, ?>> connectionConsumer;

    @Getter
    private int port = -1;

    private DisposableServer server;

    public ConnectionServer(
            String host,
            int port,
            boolean portAutoIncrement,
            int portCount,
            SslProperties ssl,
            Consumer<ChannelOperations<?, ?>> connectionConsumer) {
        this.host = host;
        this.proposedPort = port;
        this.portAutoIncrement = portAutoIncrement;
        this.portCount = portCount;
        this.ssl = ssl;
        this.connectionConsumer = connectionConsumer;
    }

    public synchronized void blockUntilConnect() {
        if (server != null) {
            return;
        }
        // Loop until the server is set up, or an exception occurs
        int currentPort = proposedPort;
        LoopResources loopResources =
                LoopResourcesFactory.createForServer(ThreadNameConst.NODE_CONNECTION_SERVER);
        while (true) {
            try {
                TcpServer tcpServer = TcpServer.create()
                        .runOn(loopResources)
                        .host(host)
                        .port(currentPort)
                        .metrics(true,
                                () -> new MicrometerChannelMetricsRecorder(NODE_TCP_SERVER, "tcp"))
                        .doOnConnection(connection -> connectionConsumer
                                .accept((ChannelOperations<?, ?>) connection));
                if (ssl.isEnabled()) {
                    tcpServer.secure(spec -> SslUtil.configureSslContextSpec(spec, ssl, true));
                }
                server = tcpServer.bindNow(DurationConst.ONE_MINUTE);
                LOGGER.info("The local node server started on: {}:{}", host, currentPort);
                break;
            } catch (Exception e) { // e.g. port in use
                if (e instanceof ChannelBindException
                        && portAutoIncrement
                        && currentPort <= proposedPort + portCount) {
                    LOGGER.warn(
                            "Failed to bind on the port: {}. Trying to bind on the next port: {}",
                            currentPort++,
                            currentPort,
                            e);
                } else {
                    throw new BindException("Failed to set up the local discovery server", e);
                }
            }
        }
        port = currentPort;
    }

    public Mono<Void> dispose() {
        server.dispose();
        return server.onDispose();
    }

}
