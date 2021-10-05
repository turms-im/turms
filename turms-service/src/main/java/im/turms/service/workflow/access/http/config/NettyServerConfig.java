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

package im.turms.service.workflow.access.http.config;

import im.turms.server.common.metrics.TurmsMicrometerChannelMetricsRecorder;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.server.HttpServer;

import static im.turms.service.constant.MetricsConstant.ADMIN_API_NAME;
import static io.netty.channel.ChannelOption.SO_LINGER;
import static io.netty.channel.ChannelOption.SO_REUSEADDR;
import static io.netty.channel.ChannelOption.TCP_NODELAY;

/**
 * @author James Chen
 * @see reactor.netty.tcp.TcpServerBind
 * @see NettyReactiveWebServerFactory#createHttpServer()
 */
@Configuration
public class NettyServerConfig implements NettyServerCustomizer {

    @Override
    public HttpServer apply(HttpServer httpServer) {
        // Don't set SO_SNDBUF and SO_RCVBUF because of
        // the reasons mentioned in https://developer.aliyun.com/article/724580
        return httpServer
                .option(SO_REUSEADDR, true)
                .childOption(SO_REUSEADDR, true)
                .childOption(SO_LINGER, 0)
                .childOption(TCP_NODELAY, true)
                .metrics(true, () -> new TurmsMicrometerChannelMetricsRecorder(ADMIN_API_NAME, "http"));
    }

}
