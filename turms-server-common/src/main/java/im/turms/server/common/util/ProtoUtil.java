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

package im.turms.server.common.util;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import im.turms.server.common.proto.ProtoFormatter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import javax.annotation.Nullable;
import java.nio.ByteBuffer;

/**
 * @author James Chen
 */
public final class ProtoUtil {

    private ProtoUtil() {
    }

    public static ByteBuf getDirectByteBuffer(MessageLite message) {
        ByteBuf output = PooledByteBufAllocator.DEFAULT.directBuffer(message.getSerializedSize());
        try {
            ByteBuffer buffer = output.nioBuffer(0, output.writableBytes());
            CodedOutputStream stream = CodedOutputStream.newInstance(buffer);
            message.writeTo(stream);
            output.writerIndex(buffer.capacity());
        } catch (Exception e) {
            output.release();
            throw new RuntimeException(e);
        }
        return output;
    }

    @Nullable
    public static String toLogString(AbstractMessage message) {
        if (message == null) {
            return null;
        }
        return ProtoFormatter.toJSON5(message, 128);
    }

}