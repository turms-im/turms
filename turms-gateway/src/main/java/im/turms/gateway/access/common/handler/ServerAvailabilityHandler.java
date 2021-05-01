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

package im.turms.gateway.access.common.handler;

import im.turms.server.common.manager.ServerStatusManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author James Chen
 */
@ChannelHandler.Sharable
public class ServerAvailabilityHandler extends ChannelInboundHandlerAdapter {

    private final ServerStatusManager serverStatusManager;

    public ServerAvailabilityHandler(ServerStatusManager serverStatusManager) {
        this.serverStatusManager = serverStatusManager;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        if (serverStatusManager.isActive()) {
            ctx.fireChannelRegistered();
        } else {
            ctx.close();
        }
    }

}