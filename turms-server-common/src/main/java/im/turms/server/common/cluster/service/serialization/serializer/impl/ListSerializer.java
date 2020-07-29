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
import im.turms.server.common.cluster.service.serialization.serializer.SerializerPool;
import im.turms.server.common.util.SerializerUtil;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author James Chen
 */
public class ListSerializer implements Serializer<List<?>> {

    @Override
    public SerializerId getSerializerId() {
        return SerializerId.COLLECTION_LIST;
    }

    @Override
    public void write(ByteBuf output, List<?> data) {
        int size = data.size();
        output.writeShort(size);
        if (size != 0) {
            Class<?> elementClass = data.get(0).getClass();
            SerializerId elementSerializerId = SerializerPool.getSerializer(elementClass).getSerializerId();
            output.writeShort(elementSerializerId.getId());
            for (Object element : data) {
                SerializerUtil.writeObject(output, element);
            }
        }
    }

    @Override
    public List<?> read(ByteBuf input) {
        int size = input.readShort();
        if (size == 0) {
            return Collections.emptyList();
        } else {
            int elementSerializerId = input.readShort();
            ArrayList<Object> list = new ArrayList<>(size);
            Serializer<Object> serializer = SerializerPool.getSerializer(elementSerializerId);
            for (int i = 0; i < size; i++) {
                Object obj = serializer.read(input);
                list.add(obj);
            }
            return list;
        }
    }

    @Override
    public int initialCapacity(List<?> data) {
        return -1;
    }

}
