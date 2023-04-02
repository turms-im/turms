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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.CharsetUtil;

import im.turms.plugin.push.core.smtp.model.SmtpResponse;
import im.turms.server.common.infra.lang.CharUtil;

/**
 * @author James Chen
 */
public class Utf8SmtpResponseDecoder extends LineBasedFrameDecoder {

    private static final int MAX_LINE_LENGTH = 1000;
    private static final int MAX_DETAIL_COUNT = 128;

    private final List<String> details = new ArrayList<>(MAX_DETAIL_COUNT);

    public Utf8SmtpResponseDecoder() {
        super(MAX_LINE_LENGTH, false, true);
    }

    @Nullable
    @Override
    protected SmtpResponse decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, buffer);
        if (frame == null) {
            return null;
        }
        try {
            return decode0(buffer, frame);
        } finally {
            frame.release();
        }
    }

    @Nullable
    private SmtpResponse decode0(ByteBuf buffer, ByteBuf frame) {
        int readableBytes = frame.readableBytes();
        if (readableBytes < 3) {
            throw new DecoderException("The frame size must be greater than or equal to 3");
        }
        int readerIndex = frame.readerIndex();
        int code = parseStatusCode(frame);
        if (readableBytes < 4) {
            return new SmtpResponse(code, Collections.emptyList());
        }
        byte separator = frame.readByte();
        String detail = frame.isReadable()
                ? frame.toString(CharsetUtil.UTF_8)
                : null;
        List<String> localDetails = details;
        switch (separator) {
            case ' ' -> {
                List<String> finalDetails;
                if (detail == null) {
                    finalDetails = localDetails.isEmpty()
                            ? Collections.emptyList()
                            : new ArrayList<>(localDetails);
                } else if (localDetails.isEmpty()) {
                    finalDetails = Collections.singletonList(detail);
                } else if (localDetails.size() == MAX_DETAIL_COUNT) {
                    throw new DecoderException(
                            "The detail count has exceeded "
                                    + MAX_DETAIL_COUNT);
                } else {
                    localDetails.add(detail);
                    finalDetails = new ArrayList<>(localDetails);
                }
                localDetails.clear();
                return new SmtpResponse(code, finalDetails);
            }
            case '-' -> {
                if (detail != null) {
                    if (localDetails.size() == MAX_DETAIL_COUNT) {
                        throw new DecoderException(
                                "The detail count has exceeded "
                                        + MAX_DETAIL_COUNT);
                    }
                    localDetails.add(detail);
                }
            }
            default -> throw new DecoderException(
                    "Received an invalid line: \""
                            + buffer.toString(readerIndex, readableBytes, CharsetUtil.UTF_8)
                            + '\"');
        }
        return null;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        details.clear();
        super.exceptionCaught(ctx, cause);
    }

    private int parseStatusCode(ByteBuf buffer) {
        int first = parseNumber(buffer.readByte()) * 100;
        int second = parseNumber(buffer.readByte()) * 10;
        int third = parseNumber(buffer.readByte());
        return first + second + third;
    }

    private int parseNumber(byte b) {
        int digit = CharUtil.digit((char) b);
        if (digit == -1) {
            throw new DecoderException(
                    "Detected an illegal char in the status code: "
                            + (char) b);
        }
        return digit;
    }
}