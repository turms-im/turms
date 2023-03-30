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

package im.turms.server.common.access.client.codec;

import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author James Chen
 */
public final class CodecFactory {
    private static final ProtobufVarint32LengthFieldPrepender VARINT_LENGTH_FIELD_PREPENDER =
            new ProtobufVarint32LengthFieldPrepender();
    private static final ProtobufFrameEncoder PROTOBUF_FRAME_ENCODER = new ProtobufFrameEncoder();

    private CodecFactory() {
    }

    public static ProtobufVarint32FrameDecoder getVarintLengthBasedFrameDecoder() {
        return new ProtobufVarint32FrameDecoder();
    }

    public static ExtendedProtobufVarint32FrameDecoder getExtendedVarintLengthBasedFrameDecoder(
            int maxFrameLength) {
        return new ExtendedProtobufVarint32FrameDecoder(maxFrameLength);
    }

    public static ProtobufVarint32LengthFieldPrepender getVarintLengthFieldPrepender() {
        return VARINT_LENGTH_FIELD_PREPENDER;
    }

    public static TurmsNotificationDecoder getTurmsNotificationDecoder() {
        return new TurmsNotificationDecoder();
    }

    public static ProtobufFrameEncoder getProtobufFrameEncoder() {
        return PROTOBUF_FRAME_ENCODER;
    }

}