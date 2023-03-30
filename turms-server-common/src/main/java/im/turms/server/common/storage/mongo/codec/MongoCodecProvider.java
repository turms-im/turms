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

import java.util.Map;

import lombok.Setter;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.jctools.maps.NonBlockingIdentityHashMap;

/**
 * @author James Chen
 */
public class MongoCodecProvider implements CodecProvider {

    private final Map<Class<?>, MongoCodec<?>> classToCodec = new NonBlockingIdentityHashMap<>(64);
    @Setter
    private CodecRegistry registry;

    public MongoCodecProvider() {
        registerCodec(new DurationCodec());
    }

    public static <T extends Enum> MongoCodec<T> getEnumCodec(
            boolean isEnumNumber,
            Class<T> clazz) {
        return isEnumNumber
                ? new EnumNumberCodec<>(clazz)
                : new EnumStringCodec<>(clazz);
    }

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        return getCodec(clazz);
    }

    public <T> MongoCodec<T> getCodec(Class<T> clazz) {
        return (MongoCodec<T>) classToCodec.computeIfAbsent(clazz, key -> {
            MongoCodec<T> codec = (MongoCodec<T>) createCodec(key);
            codec.setRegistry(registry);
            return codec;
        });
    }

    private <T> void registerCodec(MongoCodec<T> codec) {
        classToCodec.put(codec.getEncoderClass(), codec);
    }

    private <T> MongoCodec<T> createCodec(Class<T> clazz) {
        if (clazz.isEnum()) {
            return new EnumStringCodec(clazz);
        } else if (clazz.isArray()) {
            return new ArrayCodec(clazz);
        } else {
            return new EntityCodec<>(registry, clazz);
        }
    }

}