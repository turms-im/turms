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

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author James Chen
 */
public class TurmsIterableCodec extends MongoCodec<Iterable> {

    private final Class iterableClass;
    private final Class elementClazz;

    public TurmsIterableCodec(Class iterableClass, Class elementClazz) {
        super(iterableClass);
        this.iterableClass = iterableClass;
        this.elementClazz = elementClazz;
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
            collection = new ArrayList<>(8);
        }
        Codec codec = registry.get(elementClazz);
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            collection.add(codec.decode(reader, decoderContext));
        }
        reader.readEndArray();
        return collection;
    }

    @Override
    public void encode(final BsonWriter writer, final Iterable values, final EncoderContext encoderContext) {
        writer.writeStartArray();
        for (final Object value : values) {
            if (value != null) {
                Codec codec = registry.get(value.getClass());
                encoderContext.encodeWithChildContext(codec, writer, value);
            }
        }
        writer.writeEndArray();
    }

}