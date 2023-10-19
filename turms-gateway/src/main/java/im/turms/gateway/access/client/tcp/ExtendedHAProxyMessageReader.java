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
import java.util.function.Consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.haproxy.HAProxyMessage;
import reactor.netty.transport.AddressUtils;

/**
 * @author James Chen
 * @see {@link reactor.netty.http.server.HAProxyMessageReader}
 */
public class ExtendedHAProxyMessageReader extends ChannelInboundHandlerAdapter {

    private final Consumer<InetSocketAddress> onRemoteAddressConfirmed;

    public ExtendedHAProxyMessageReader(Consumer<InetSocketAddress> onRemoteAddressConfirmed) {
        this.onRemoteAddressConfirmed = onRemoteAddressConfirmed;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HAProxyMessage proxyMessage) {
            InetSocketAddress remoteAddress = null;
            try {
                String sourceAddress = proxyMessage.sourceAddress();
                int sourcePort = proxyMessage.sourcePort();
                if (sourceAddress != null && sourcePort > 0) {
                    remoteAddress = AddressUtils.createUnresolved(sourceAddress, sourcePort);
                }
            } finally {
                proxyMessage.release();
            }

            ctx.channel()
                    .pipeline()
                    .remove(this);
            if (remoteAddress == null) {
                onRemoteAddressConfirmed.accept((InetSocketAddress) ctx.channel()
                        .remoteAddress());
            } else {
                onRemoteAddressConfirmed.accept(remoteAddress);
            }

            ctx.read();
        } else {
            super.channelRead(ctx, msg);
        }
    }

}