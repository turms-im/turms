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

package im.turms.server.common.cluster.service.codec;


import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.codec.codec.Codec;
import im.turms.server.common.cluster.service.codec.codec.CodecPool;
import im.turms.server.common.cluster.service.codec.exception.CodecNotFoundException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.util.IllegalReferenceCountException;
import lombok.extern.log4j.Log4j2;

/**
 * Note that to get a better performance do NOT use reflection to serialize/deserialize data.
 * <p>
 * The reason to use codec (Use independent codec to serialize data)
 * instead of something like Writeable (The code used to (de)serialize data is included in the data class)
 * is to separate the (de)serialization logic from the business logic or the code will tend to be messy.
 *
 * @author James Chen
 */
@Log4j2
public class CodecService implements ClusterService {

    public CodecService() {
        // Init here so the application can exit if an error is detected
        CodecPool.init();
    }

    public <T> ByteBuf serializeWithoutCodecId(T data) {
        Class<T> clazz = (Class<T>) data.getClass();
        Codec<T> codec = CodecPool.getCodec(clazz);
        if (codec == null) {
            throw new CodecNotFoundException("Cannot find a codec for class: " + clazz.getName());
        }
        ByteBuf byteBufToComposite = codec.byteBufToComposite(data);
        if (byteBufToComposite != null && byteBufToComposite.refCnt() == 0) {
            throw new IllegalReferenceCountException("byteBufToComposite of the data has been released: " + data);
        }
        int initialCapacity = codec.initialCapacity(data);
        ByteBuf output = PooledByteBufAllocator.DEFAULT
                .directBuffer(initialCapacity > -1 ? initialCapacity : 128);
        codec.write(output, data);
        if (byteBufToComposite != null) {
            output = PooledByteBufAllocator.DEFAULT
                    .compositeDirectBuffer(2)
                    .addComponents(true, output, byteBufToComposite);
        }
        return output;
    }

}
