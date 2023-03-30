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

package im.turms.server.common.storage.redis.command;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandArgsUtil;
import io.lettuce.core.protocol.CommandEncoder;
import io.lettuce.core.protocol.CommandType;
import io.lettuce.core.protocol.ProtocolKeyword;
import io.lettuce.core.protocol.RedisCommand;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.EncoderException;

/**
 * @author James Chen
 * @implNote We don't use MessageToByteEncoder because they will release the input buffer while we
 *           need to reuse the input buffer to compose a composite buffer.
 * @see CommandEncoder
 * @see CommandArgsUtil
 */
public class TurmsCommandEncoder extends ChannelOutboundHandlerAdapter {

    private static final int COMMAND_BYTEBUF_COMPONENT_COUNT = 4;

    private final Map<ProtocolKeyword, ByteBuf> protocolKeywordBufferMap =
            new ConcurrentHashMap<>(64);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
            throws Exception {
        CompositeByteBuf out;
        if (msg instanceof RedisCommand<?, ?, ?> command) {
            int argsCount = countArgs(command);
            int componentCount = COMMAND_BYTEBUF_COMPONENT_COUNT
                    + argsCount * CommandArgsUtil.ARG_BYTEBUF_COMPONENT_COUNT;
            out = UnpooledByteBufAllocator.DEFAULT.compositeDirectBuffer(componentCount);
            encode(out, command, argsCount);
        } else if (msg instanceof Collection) {
            Collection<RedisCommand<?, ?, ?>> commands = (Collection<RedisCommand<?, ?, ?>>) msg;
            int componentCount = 0;
            for (RedisCommand<?, ?, ?> command : commands) {
                componentCount += COMMAND_BYTEBUF_COMPONENT_COUNT
                        + countArgs(command) * CommandArgsUtil.ARG_BYTEBUF_COMPONENT_COUNT;
            }
            out = UnpooledByteBufAllocator.DEFAULT.compositeDirectBuffer(componentCount);
            for (RedisCommand<?, ?, ?> command : commands) {
                encode(out, command, countArgs(command));
            }
        } else {
            throw new IllegalArgumentException(
                    "Unknown message class: "
                            + msg.getClass()
                                    .getName());
        }
        ctx.write(out, promise);
    }

    private void encode(CompositeByteBuf out, RedisCommand<?, ?, ?> command, int argsCount) {
        try {
            out.markWriterIndex();
            CommandArgs<?, ?> args = command.getArgs();
            int length = 1 + argsCount; // one for the command type
            out.addComponent(true, CommandArgsUtil.COMMAND_TYPE_FLAG)
                    .addComponent(true, CommandArgsUtil.getArgLength(length))
                    .addComponent(true, CommandArgsUtil.CRLF)
                    .addComponent(true, getProtocolKeywordBuffer(command.getType()));
            // We don't use arg.encode because it cannot
            // encode data according to its type (e.g. hash key, hash field, geo member)
            CommandArgsUtil.encodeArgs(out, args);
        } catch (RuntimeException e) {
            out.resetWriterIndex();
            command.completeExceptionally(new EncoderException(
                    "Cannot encode command "
                            + command
                            + ". Please close the connection as the connection state may be out of sync.",
                    e));
        }
    }

    private int countArgs(RedisCommand<?, ?, ?> command) {
        CommandArgs<?, ?> args = command.getArgs();
        if (args == null) {
            return 0;
        }
        int count = 0;
        ProtocolKeyword type = command.getType();
        count += type == CommandType.EVAL || type == CommandType.EVALSHA
                // Add 2, one for script, another for the length of keys
                ? (int) CommandArgsUtil.getLongArgument(args, 1) + 2
                : args.count();
        return count;
    }

    /**
     * e.g. "$7\r\nEVALSHA\r\n"
     */
    private ByteBuf getProtocolKeywordBuffer(ProtocolKeyword keyword) {
        return protocolKeywordBufferMap.computeIfAbsent(keyword,
                key -> Unpooled.unreleasableBuffer(CommandArgsUtil.writeBytesArg(key.getBytes())));
    }

}
