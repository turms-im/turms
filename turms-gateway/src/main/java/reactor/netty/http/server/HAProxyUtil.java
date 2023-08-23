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

package reactor.netty.http.server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.haproxy.HAProxyMessageDecoder;
import reactor.netty.ConnectionObserver;
import reactor.netty.NettyPipeline;
import reactor.util.annotation.Nullable;

/**
 * @author James Chen
 */
public final class HAProxyUtil {

    private HAProxyUtil() {
    }

    /**
     * @see HttpServerConfig.HttpServerChannelInitializer#onChannelInit(ConnectionObserver, Channel,
     *      SocketAddress)
     */
    public static void addProxyProtocolHandlers(ChannelPipeline pipeline) {
        pipeline.addFirst(NettyPipeline.ProxyProtocolDecoder, new HAProxyMessageDecoder())
                .addAfter(NettyPipeline.ProxyProtocolDecoder,
                        NettyPipeline.ProxyProtocolReader,
                        new HAProxyMessageReader());
    }

    public static void addProxyProtocolDetectorHandler(ChannelPipeline pipeline) {
        pipeline.addFirst(NettyPipeline.ProxyProtocolDecoder, new HAProxyMessageDetector());
    }

    @Nullable
    public static InetSocketAddress resolveRemoteAddressFromProxyProtocol(Channel channel) {
        return (InetSocketAddress) HAProxyMessageReader
                .resolveRemoteAddressFromProxyProtocol(channel);
    }

}