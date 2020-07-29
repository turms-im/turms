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

package im.turms.server.common.cluster.service.serialization.serializer.impl;

import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.cluster.service.serialization.serializer.SerializerId;
import io.netty.buffer.ByteBuf;

/**
 * @author James Chen
 */
public class IntegerSerializer implements Serializer<Integer> {

    @Override
    public SerializerId getSerializerId() {
        return SerializerId.PRIMITIVE_INTEGER;
    }

    @Override
    public void write(ByteBuf output, Integer data) {
        output.writeInt(data);
    }

    @Override
    public Integer read(ByteBuf input) {
        return input.readInt();
    }

    @Override
    public int initialCapacity(Integer data) {
        return Integer.BYTES;
    }

}
