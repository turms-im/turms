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

package im.turms.server.common.access.admin.web;

import reactor.netty.http.server.HttpServer;

import im.turms.server.common.access.common.LoopResourcesFactory;
import im.turms.server.common.infra.metrics.TurmsMicrometerChannelMetricsRecorder;
import im.turms.server.common.infra.net.SslUtil;
import im.turms.server.common.infra.property.env.common.SslProperties;
import im.turms.server.common.infra.property.env.common.adminapi.AdminHttpProperties;
import im.turms.server.common.infra.thread.ThreadNameConst;

import static io.netty.channel.ChannelOption.SO_LINGER;
import static io.netty.channel.ChannelOption.SO_REUSEADDR;
import static io.netty.channel.ChannelOption.TCP_NODELAY;

import static im.turms.server.common.infra.metrics.CommonMetricNameConst.ADMIN_API;

/**
 * @author James Chen
 */
public final class HttpServerFactory {

    private HttpServerFactory() {
    }

    public static HttpServer createHttpServer(AdminHttpProperties httpProperties) {
        SslProperties ssl = httpProperties.getSsl();
        // Don't set SO_SNDBUF and SO_RCVBUF because of
        // the reasons mentioned in https://developer.aliyun.com/article/724580
        HttpServer http = HttpServer.create()
                .host(httpProperties.getHost())
                .port(httpProperties.getPort())
                .option(SO_REUSEADDR, true)
                .childOption(SO_REUSEADDR, true)
                .childOption(SO_LINGER, 0)
                .childOption(TCP_NODELAY, true)
                .runOn(LoopResourcesFactory.createForServer(ThreadNameConst.ADMIN_HTTP_PREFIX))
                .metrics(true, () -> new TurmsMicrometerChannelMetricsRecorder(ADMIN_API, "http"));
        return SslUtil.apply(http, ssl, null);
    }

}
