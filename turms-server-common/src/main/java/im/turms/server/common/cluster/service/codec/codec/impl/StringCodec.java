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

package im.turms.server.common.cluster.service.codec.codec.impl;

import im.turms.server.common.cluster.service.codec.codec.Codec;
import im.turms.server.common.cluster.service.codec.codec.CodecId;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

/**
 * @author James Chen
 */
public class StringCodec implements Codec<String> {

    @Override
    public CodecId getCodecId() {
        return CodecId.STRING;
    }

    @Override
    public void write(ByteBuf output, String data) {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        if (bytes.length < Short.MAX_VALUE) {
            output.writeShort(bytes.length);
            if (bytes.length > 0) {
                output.writeBytes(bytes);
            }
        } else {
            throw new IllegalArgumentException("The bytes length of the string cannot be greater than " + Short.MAX_VALUE);
        }
    }

    @Override
    public String read(ByteBuf input) {
        int length = input.readShort();
        if (length > 0) {
            byte[] bytes = new byte[length];
            input.readBytes(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        } else {
            return "";
        }
    }

    @Override
    public int initialCapacity(String data) {
        return data.length();
    }

}
