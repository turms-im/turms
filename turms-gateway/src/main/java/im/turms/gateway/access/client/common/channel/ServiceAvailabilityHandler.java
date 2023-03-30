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

package im.turms.gateway.access.client.common.channel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Queue;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.util.internal.OutOfDirectMemoryError;

import im.turms.gateway.access.client.common.UserSession;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.healthcheck.ServerStatusManager;
import im.turms.server.common.infra.lang.ByteArrayWrapper;

/**
 * @author James Chen
 */
@ChannelHandler.Sharable
public class ServiceAvailabilityHandler extends ChannelInboundHandlerAdapter {

    private final BlocklistService blocklistService;
    private final ServerStatusManager serverStatusManager;
    private final SessionService sessionService;

    public ServiceAvailabilityHandler(
            BlocklistService blocklistService,
            ServerStatusManager serverStatusManager,
            SessionService sessionService) {
        this.blocklistService = blocklistService;
        this.serverStatusManager = serverStatusManager;
        this.sessionService = sessionService;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        if (serverStatusManager.isActive()) {
            SocketAddress socketAddress = ctx.channel()
                    .remoteAddress();
            if (socketAddress instanceof InetSocketAddress address
                    && blocklistService.isIpBlocked(address.getAddress()
                            .getAddress())) {
                ctx.close();
                return;
            }
            ctx.fireChannelRegistered();
        } else {
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // CorruptedFrameException can be caused by:
        // 1. Illegal WebSocket frame or the frame is too large
        // 2. The varint-length header declares that it will send a large payload
        if (cause instanceof CorruptedFrameException) {
            InetSocketAddress address = (InetSocketAddress) ctx.channel()
                    .remoteAddress();
            ByteArrayWrapper ip = new ByteArrayWrapper(
                    address.getAddress()
                            .getAddress());
            blocklistService.tryBlockIpForCorruptedFrame(ip);
            Queue<UserSession> sessions = sessionService.getLocalUserSession(ip);
            if (sessions != null) {
                for (UserSession session : sessions) {
                    blocklistService.tryBlockUserIdForCorruptedFrame(session.getUserId());
                }
            }
        } else if (cause instanceof OutOfDirectMemoryError) {
            ctx.close();
        }
        ctx.fireExceptionCaught(cause);
    }

}