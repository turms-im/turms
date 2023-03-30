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

package im.turms.server.common.infra.cluster.service.codec.codec.impl;

import java.util.List;

import im.turms.server.common.infra.cluster.service.codec.codec.Codec;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamOutput;

/**
 * @author James Chen
 */
public class ByteCodec implements Codec<Byte> {

    @Override
    public CodecId getCodecId() {
        return CodecId.PRIMITIVE_BYTE;
    }

    @Override
    public List<Class<?>> getEncodableClasses() {
        return List.of(Byte.class, byte.class);
    }

    @Override
    public void write(CodecStreamOutput output, Byte data) {
        output.writeByte(data);
    }

    @Override
    public Byte read(CodecStreamInput input) {
        return input.readByte();
    }

    @Override
    public int initialCapacity(Byte data) {
        return Byte.BYTES;
    }

}
