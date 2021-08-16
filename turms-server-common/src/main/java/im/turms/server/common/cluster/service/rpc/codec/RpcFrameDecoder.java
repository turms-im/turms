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

package im.turms.server.common.cluster.service.rpc.codec;

import im.turms.server.common.cluster.service.codec.codec.Codec;
import im.turms.server.common.cluster.service.codec.codec.CodecPool;
import im.turms.server.common.cluster.service.codec.exception.CodecNotFoundException;
import im.turms.server.common.cluster.service.rpc.dto.RpcRequest;
import im.turms.server.common.cluster.service.rpc.dto.RpcResponse;
import im.turms.server.common.cluster.service.rpc.exception.RpcException;
import im.turms.server.common.rpc.codec.request.RpcRequestCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import reactor.netty.ReactorNetty;

import java.util.List;

/**
 * @author James Chen
 */
public class RpcFrameDecoder extends ProtobufVarint32FrameDecoder {

    // Codec ID
    private static final int HEADER_LENGTH = Short.BYTES;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        super.decode(ctx, in, out);
        int size = out.size();
        for (int i = 0; i < size; i++) {
            // Note "frame" is "retainedSlice" from "in"
            // So after "super.decode(...)", the refCnt of "in" is 2 (but it will
            // be released once by "cumulation.release()" in
            // in io.netty.handler.codec.ByteToMessageDecoder.channelRead())
            // and the refCnt of "frame" is 1

            // Because releasing "frame" will also release "in",
            // we just need to ensure the frame is released once by us finally
            ByteBuf frame = (ByteBuf) out.get(i);
            out.set(i, decode(ctx, frame));
        }
    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf frame) {
        try {
            // Validate
            int length = frame.readableBytes();
            if (length < HEADER_LENGTH) {
                String reason = ReactorNetty.format(ctx.channel(),
                        "The buffer is too small to parse header. Actual: %d; Expected: %d".formatted(length, HEADER_LENGTH));
                throw new CorruptedFrameException(reason);
            }
            return decodePayload(ctx, frame);
        } finally {
            frame.release();
        }
    }

    public Object decodePayload(ChannelHandlerContext ctx, ByteBuf frame) {
        int codecId = Integer.MIN_VALUE;
        try {
            codecId = frame.readShort() & 0xFFFF;
            Codec<?> codec = CodecPool.getCodec(codecId);
            if (codec == null) {
                String reason = ReactorNetty.format(ctx.channel(),
                        "The codec with the ID %s doesn't exist".formatted(codecId));
                throw new CodecNotFoundException(reason);
            }
            int requestId = frame.readInt();
            if (requestId < 0) {
                String reason = ReactorNetty.format(ctx.channel(),
                        "requestId must be larger than 0. Actual: " + requestId);
                throw new CorruptedFrameException(reason);
            }
            if (codec instanceof RpcRequestCodec<?> requestCodec) {
                RpcRequest<?> request = requestCodec.read(frame);
                request.setRequestId(requestId);
                return request;
            }
            Object responseValue = codec.read(frame);
            return responseValue instanceof RpcException exception
                    ? new RpcResponse(requestId, null, exception)
                    : new RpcResponse(requestId, responseValue, null);
        } catch (Exception e) {
            String msg = "Failed to parse the buffer by the codec";
            if (codecId != Integer.MIN_VALUE) {
                msg += " with the ID %d".formatted(codecId);
            }
            String reason = ReactorNetty.format(ctx.channel(), msg);
            throw new CorruptedFrameException(reason, e);
        }
    }

}