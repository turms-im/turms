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

package im.turms.server.common.storage.mongo.codec;

import lombok.Setter;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author James Chen
 */
public class MongoCodecProvider implements CodecProvider {

    private final Map<Class<?>, MongoCodec<?>> codes = new IdentityHashMap<>(64);
    @Setter
    private CodecRegistry registry;

    public MongoCodecProvider() {
        registerCodec(new DurationCodec());
    }

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        return getCodec(clazz);
    }

    public <T> MongoCodec<T> getCodec(Class<T> clazz) {
        MongoCodec<T> codec = (MongoCodec<T>) codes.get(clazz);
        if (codec == null) {
            return registerClass(clazz);
        }
        return codec;
    }

    private <T> void registerCodec(MongoCodec<T> codec) {
        codes.put(codec.getEncoderClass(), codec);
    }

    private synchronized <T> MongoCodec<T> registerClass(Class<T> clazz) {
        return (MongoCodec<T>) codes.computeIfAbsent(clazz, key -> {
            MongoCodec<T> codec = (MongoCodec<T>) createCodec(key);
            codec.setRegistry(registry);
            return codec;
        });
    }

    private <T> MongoCodec<T> createCodec(Class<T> clazz) {
        if (clazz.isEnum()) {
            return new EnumCodec(clazz);
        } else if (clazz.isArray()) {
            return new ArrayCodec(clazz);
        } else {
            return new EntityCodec<>(registry, clazz);
        }
    }

}