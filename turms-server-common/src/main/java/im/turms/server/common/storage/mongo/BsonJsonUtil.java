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

import java.util.Map;

import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import im.turms.server.common.infra.lang.StringBuilderPool;
import im.turms.server.common.infra.lang.StringBuilderWriter;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

/**
 * @author James Chen
 */
public final class BsonJsonUtil {

    private static final CodecRegistry REGISTRY =
            fromProviders(CodecPool.BSON_VALUE_CODEC_PROVIDER);
    private static final JsonWriterSettings JSON_WRITER_SETTINGS = JsonWriterSettings.builder()
            .outputMode(JsonMode.RELAXED)
            .build();

    private BsonJsonUtil() {
    }

    public static String toJson(BsonDocument document) {
        try (StringBuilderWriter builderWriter = StringBuilderPool.getWriter();
                JsonWriter writer = new JsonWriter(builderWriter, JSON_WRITER_SETTINGS)) {
            writer.writeStartDocument();
            for (Map.Entry<String, BsonValue> entry : document.entrySet()) {
                writer.writeName(entry.getKey());
                BsonValue value = entry.getValue();
                Codec codec = REGISTRY.get(value.getClass());
                CodecPool.DEFAULT_ENCODER_CONTEXT.encodeWithChildContext(codec, writer, value);
            }
            writer.writeEndDocument();
            return builderWriter.toString();
        }
    }

}
