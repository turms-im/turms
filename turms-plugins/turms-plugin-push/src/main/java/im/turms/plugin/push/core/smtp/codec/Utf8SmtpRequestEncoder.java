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

package im.turms.plugin.push.core.smtp.codec;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import im.turms.plugin.push.core.smtp.model.SmtpRequest;

/**
 * @author James Chen
 */
public class Utf8SmtpRequestEncoder extends MessageToMessageEncoder<Object> {
    private static final byte[] CRLF = {'\r', '\n'};
    private static final byte SP = ' ';

    @Override
    public boolean acceptOutboundMessage(Object msg) {
        return msg instanceof SmtpRequest;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) {
        SmtpRequest request = (SmtpRequest) msg;
        ByteBuf buffer = ctx.alloc()
                .buffer();
        try {
            byte[] name = request.command()
                    .getNameBytes();
            buffer.writeBytes(name);
            writeParameters(request.parameters(), buffer);
            buffer.writeBytes(CRLF);
            out.add(buffer);
        } catch (Throwable t) {
            buffer.release();
        }
    }

    private void writeParameters(List<String> parameters, ByteBuf out) {
        if (parameters.isEmpty()) {
            return;
        }
        out.writeByte(SP);
        if (parameters instanceof RandomAccess) {
            int sizeMinusOne = parameters.size() - 1;
            for (int i = 0; i < sizeMinusOne; i++) {
                ByteBufUtil.writeUtf8(out, parameters.get(i));
                out.writeByte(SP);
            }
            ByteBufUtil.writeUtf8(out, parameters.get(sizeMinusOne));
        } else {
            Iterator<String> params = parameters.iterator();
            while (true) {
                ByteBufUtil.writeUtf8(out, params.next());
                if (params.hasNext()) {
                    out.writeByte(SP);
                } else {
                    break;
                }
            }
        }
    }
}