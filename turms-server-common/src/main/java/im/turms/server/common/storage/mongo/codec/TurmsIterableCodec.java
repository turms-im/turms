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

import im.turms.server.common.infra.collection.ChunkedArrayList;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author James Chen
 */
public class TurmsIterableCodec extends MongoCodec<Iterable> {

    private final Class iterableClass;
    private final Class elementClass;
    private Codec elementCodec;

    public TurmsIterableCodec(Class iterableClass, Class elementClass) {
        super(iterableClass);
        this.iterableClass = iterableClass;
        this.elementClass = elementClass;
    }

    @Override
    public void setRegistry(CodecRegistry registry) {
        super.setRegistry(registry);
        elementCodec = registry.get(elementClass);
    }

    @Override
    public Iterable decode(final BsonReader reader, final DecoderContext decoderContext) {
        reader.readStartArray();
        Collection<Object> collection;
        if (Set.class.isAssignableFrom(iterableClass)) {
            collection = LinkedHashSet.class.isAssignableFrom(iterableClass)
                    ? new LinkedHashSet<>()
                    : UnifiedSet.newSet(8);
        } else {
            collection = new ChunkedArrayList<>();
        }
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            collection.add(elementCodec.decode(reader, decoderContext));
        }
        reader.readEndArray();
        return collection;
    }

    @Override
    public void encode(final BsonWriter writer, final Iterable values, final EncoderContext encoderContext) {
        writer.writeStartArray();
        for (Object value : values) {
            if (value != null) {
                encoderContext.encodeWithChildContext(elementCodec, writer, value);
            }
        }
        writer.writeEndArray();
    }

}