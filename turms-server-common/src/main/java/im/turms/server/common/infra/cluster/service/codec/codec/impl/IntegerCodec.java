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
public class IntegerCodec implements Codec<Integer> {

    @Override
    public CodecId getCodecId() {
        return CodecId.PRIMITIVE_INTEGER;
    }

    @Override
    public List<Class<?>> getEncodableClasses() {
        return List.of(Integer.class, int.class);
    }

    @Override
    public void write(CodecStreamOutput output, Integer data) {
        output.writeInt(data);
    }

    @Override
    public Integer read(CodecStreamInput input) {
        return input.readInt();
    }

    @Override
    public int initialCapacity(Integer data) {
        return Integer.BYTES;
    }

}
