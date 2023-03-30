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

package im.turms.server.common.infra.cluster.service.rpc.channel;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import reactor.netty.ReactorNetty;

import im.turms.server.common.infra.cluster.service.codec.codec.Codec;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecPool;
import im.turms.server.common.infra.cluster.service.codec.exception.CodecNotFoundException;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStream;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcRequestCodec;
import im.turms.server.common.infra.cluster.service.rpc.dto.RpcRequest;
import im.turms.server.common.infra.cluster.service.rpc.dto.RpcResponse;
import im.turms.server.common.infra.cluster.service.rpc.exception.RpcException;

/**
 * @author James Chen
 */
public class RpcFrameDecoder extends ProtobufVarint32FrameDecoder {

    // Header = Codec ID (2 Bytes)
    private static final int HEADER_LENGTH = Short.BYTES;
    private static final int UNSET_CODEC_ID = Integer.MIN_VALUE;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
            throws Exception {
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
            Object val = decode(ctx, frame);
            frame.touch(val);
            out.set(i, val);
        }
    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf frame) {
        try {
            // Validate
            int length = frame.readableBytes();
            if (length < HEADER_LENGTH) {
                String reason = ReactorNetty.format(ctx.channel(),
                        "The buffer is too small to parse the header. "
                                + "Expecting the buffer length ("
                                + length
                                + ") to be greater than or equal to "
                                + HEADER_LENGTH);
                throw new CorruptedFrameException(reason);
            }
            return decodePayload(ctx, frame);
        } finally {
            // For some RPC requests, we deallocate the frame here,
            // but for other requests like "HandleServiceRequest",
            // they will retain the frame by 1 in their codec so
            // the frame will NOT be just deallocated here
            frame.release();
        }
    }

    public Object decodePayload(ChannelHandlerContext ctx, ByteBuf frame) {
        int codecId = UNSET_CODEC_ID;
        try {
            codecId = frame.readShort() & 0xFFFF;
            Codec<?> codec = CodecPool.getCodec(codecId);
            if (codec == null) {
                String reason = ReactorNetty.format(ctx.channel(),
                        "The codec with the ID ("
                                + codecId
                                + ") does not exist");
                throw new CodecNotFoundException(reason);
            }
            int requestId = frame.readInt();
            if (requestId < 0) {
                String reason = ReactorNetty.format(ctx.channel(),
                        "Expected the request ID to be greater than 0, but got: "
                                + requestId);
                throw new CorruptedFrameException(reason);
            }
            if (codec instanceof RpcRequestCodec<?> requestCodec) {
                RpcRequest<?> request = requestCodec.read(new CodecStream(frame));
                request.setRequestId(requestId);
                return request;
            }
            Object responseValue = codec.read(new CodecStream(frame));
            return responseValue instanceof RpcException exception
                    ? new RpcResponse(requestId, null, exception)
                    : new RpcResponse(requestId, responseValue, null);
        } catch (Exception e) {
            String msg = codecId == UNSET_CODEC_ID
                    ? "Failed to parse the buffer: "
                            + frame
                    : "Failed to parse the buffer ("
                            + frame
                            + ") by the codec with the ID: "
                            + codecId;
            String reason = ReactorNetty.format(ctx.channel(), msg);
            throw new CorruptedFrameException(reason, e);
        }
    }

}