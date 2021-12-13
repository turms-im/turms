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

package im.turms.server.common.mongo.codec;

import lombok.Data;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author James Chen
 * @see org.bson.codecs.pojo.PojoCodecProvider
 */
@Data
public class EntityCodecProvider implements CodecProvider {

    private final Map<Class<?>, EntityCodec<?>> codecs = new IdentityHashMap<>(64);

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        return getEntityCodec(clazz, registry);
    }

    private <T> Codec<T> getEntityCodec(Class<T> clazz, CodecRegistry registry) {
        EntityCodec<T> codec = (EntityCodec<T>) codecs.get(clazz);
        if (codec == null) {
            codec = new EntityCodec<>(registry, clazz);
            registerCodec(clazz, codec);
        }
        return codec;
    }

    private synchronized <T> void registerCodec(Class<T> clazz, EntityCodec<T> codec) {
        codecs.put(clazz, codec);
    }

}