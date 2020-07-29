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

package im.turms.server.common.cluster.service.serialization;


import im.turms.server.common.cluster.exception.SerializerNotFoundException;
import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.cluster.service.serialization.serializer.SerializerPool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.extern.log4j.Log4j2;

/**
 * Note that to get a better performance do NOT use reflection to serialize/deserialize data.
 * <p>
 * The reason to use serializer (Use independent serializers to serialize data)
 * instead of something like Writeable (The code used to (de)serialize data is included in the data class)
 * is to separate the (de)serialization logic from the business logic or the code will tend to be messy.
 *
 * @author James Chen
 */
@Log4j2
public class SerializationService implements ClusterService {

    public static final ByteBufAllocator BYTE_BUF_ALLOCATOR = PooledByteBufAllocator.DEFAULT;

    public ByteBuf serialize(Object data) {
        Class<?> clazz = data.getClass();
        Serializer<Object> serializer = SerializerPool.getSerializer(clazz);
        if (serializer != null) {
            int initialCapacity = serializer.initialCapacity(data);
            ByteBuf output = BYTE_BUF_ALLOCATOR.directBuffer(initialCapacity > -1 ? initialCapacity : 256);
            output.writeShort(serializer.getSerializerId().getId());
            serializer.write(output, data);
            ByteBuf byteBufToComposite = serializer.byteBufToComposite(data);
            if (byteBufToComposite != null) {
                output = PooledByteBufAllocator.DEFAULT.compositeDirectBuffer(2)
                        .addComponents(true, output, byteBufToComposite);
            }
            return output;
        } else {
            throw new SerializerNotFoundException("Cannot find a serializer for class: " + clazz.getName());
        }
    }

}
