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

import java.time.Duration;

import org.bson.BsonReader;
import org.bson.BsonWriter;

/**
 * @author James Chen
 */
public class DurationCodec extends MongoCodec<Duration> {

    @Override
    public void encode(BsonWriter writer, Duration value) {
        writer.writeInt64(value.toMillis());
    }

    @Override
    public Duration decode(BsonReader reader) {
        return Duration.ofMillis(reader.readInt64());
    }

}