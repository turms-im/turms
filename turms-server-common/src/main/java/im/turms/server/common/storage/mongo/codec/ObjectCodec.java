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
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

/**
 * @author James Chen
 * @implNote Limitations: Since the codec is used for {@link Object}, we cannot know the exact type
 *           it should be when decoding, so we use limited types when decoding.
 */
public class ObjectCodec extends MongoCodec<Object> {

    public static final ObjectCodec INSTANCE = new ObjectCodec();

    private ObjectCodec() {
        super(Object.class);
    }

    @Override
    public void setRegistry(CodecRegistry registry) {
        // ignore because we don't need the registry for codec.
    }

    @Override
    public Object decode(BsonReader reader, DecoderContext decoderContext) {
        return CodecUtil.read(reader);
    }

    @Override
    public void encode(BsonWriter writer, Object value, EncoderContext encoderContext) {
        CodecUtil.write(writer, value);
    }

}