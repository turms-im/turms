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
import java.util.List;
import java.util.function.Consumer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ProtocolDetectionResult;
import io.netty.handler.codec.haproxy.HAProxyMessageDecoder;
import io.netty.handler.codec.haproxy.HAProxyProtocolVersion;
import reactor.netty.NettyPipeline;

/**
 * @author James Chen
 * @see {@link reactor.netty.http.server.HAProxyMessageDetector}
 */
public class ExtendedHAProxyMessageDetector extends ByteToMessageDecoder {

    private final Consumer<InetSocketAddress> onRemoteAddressConfirmed;

    public ExtendedHAProxyMessageDetector(Consumer<InetSocketAddress> onRemoteAddressConfirmed) {
        this.onRemoteAddressConfirmed = onRemoteAddressConfirmed;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        ProtocolDetectionResult<HAProxyProtocolVersion> detectionResult =
                HAProxyMessageDecoder.detectProtocol(in);
        if (detectionResult.equals(ProtocolDetectionResult.needsMoreData())) {
            return;
        }
        ChannelPipeline pipeline = ctx.pipeline();
        if (detectionResult.equals(ProtocolDetectionResult.invalid())) {
            pipeline.remove(this);
            onRemoteAddressConfirmed.accept((InetSocketAddress) ctx.channel()
                    .remoteAddress());
        } else {
            pipeline.replace(this, NettyPipeline.ProxyProtocolDecoder, new HAProxyMessageDecoder());
            pipeline.addAfter(NettyPipeline.ProxyProtocolDecoder,
                    NettyPipeline.ProxyProtocolReader,
                    new ExtendedHAProxyMessageReader(onRemoteAddressConfirmed));
        }
    }
}