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

package im.turms.server.common.storage.mongo;

import com.mongodb.MongoClientSettings;
import org.bson.codecs.BsonCodecProvider;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.DocumentCodecProvider;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.IterableCodecProvider;
import org.bson.codecs.MapCodecProvider;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.jsr310.Jsr310CodecProvider;

import im.turms.server.common.storage.mongo.codec.MongoCodecProvider;

/**
 * @author James Chen
 */
public final class CodecPool {

    private CodecPool() {
    }

    public static final EncoderContext DEFAULT_ENCODER_CONTEXT = EncoderContext.builder()
            .build();
    public static final BsonValueCodecProvider BSON_VALUE_CODEC_PROVIDER =
            new BsonValueCodecProvider();
    private static final MongoCodecProvider MONGO_CODEC_PROVIDER = new MongoCodecProvider();
    /**
     * {@link MongoClientSettings#DEFAULT_CODEC_REGISTRY}
     */
    public static final CodecRegistry CODEC_REGISTRY =
            CodecRegistries.fromProviders(new ValueCodecProvider(),
                    BSON_VALUE_CODEC_PROVIDER,
                    new DocumentCodecProvider(),
                    new MapCodecProvider(),
                    new IterableCodecProvider(),
                    new Jsr310CodecProvider(),
                    new BsonCodecProvider(),
                    MONGO_CODEC_PROVIDER);

    static {
        MONGO_CODEC_PROVIDER.setRegistry(CODEC_REGISTRY);
    }

}