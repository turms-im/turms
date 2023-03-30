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

package im.turms.server.common.infra.proto;

import java.io.InputStream;
import java.util.Arrays;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedInputStreamUtil;
import io.netty.buffer.ByteBuf;

/**
 * @author James Chen
 */
public final class ProtoDecoder {

    private ProtoDecoder() {
    }

    /**
     * @implNote 1. {@link CodedInputStream} is efficient because it reuses the underlying buffers
     *           2. Don't use {@link CodedInputStream#newInstance(InputStream)} for
     *           {@link io.netty.buffer.ByteBufInputStream} because it is inefficient.
     */
    public static CodedInputStream newInputStream(ByteBuf byteBuf) {
        int count = byteBuf.nioBufferCount();
        if (count == 1) {
            return CodedInputStreamUtil.newInstance(byteBuf.nioBuffer());
        } else if (count > 1) {
            return CodedInputStreamUtil.newInstance(Arrays.asList(byteBuf.nioBuffers()));
        }
        throw new IllegalArgumentException("The buffer must have at least one NIO buffer");
    }

}
