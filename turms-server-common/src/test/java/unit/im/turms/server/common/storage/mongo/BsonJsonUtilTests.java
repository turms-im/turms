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

package unit.im.turms.server.common.storage.mongo;

import java.util.List;

import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.junit.jupiter.api.Test;

import im.turms.server.common.storage.mongo.BsonJsonUtil;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class BsonJsonUtilTests {

    @Test
    void toJson() {
        BsonDocument document1 = new BsonDocument().append("number", new BsonInt32(123))
                .append("map", new BsonDocument("test", new BsonDateTime(1000)))
                .append("bool", new BsonBoolean(true));
        String expectedJsonForDoc1 =
                """
                        {"number": 123, "map": {"test": {"$date": "1970-01-01T00:00:01Z"}}, "bool": true}""";
        String actualJson1 = BsonJsonUtil.toJson(document1);
        assertThat(actualJson1).isEqualTo(expectedJsonForDoc1);

        BsonDocument document2 = new BsonDocument().append("string", new BsonString("abc"))
                .append("array",
                        new BsonArray(
                                List.of(new BsonInt32(1), new BsonInt32(2), new BsonInt32(3))));

        String expectedJsonForDoc2 = """
                {"string": "abc", "array": [1, 2, 3]}""";
        String actualJson2 = BsonJsonUtil.toJson(document2);
        assertThat(actualJson2).isEqualTo(expectedJsonForDoc2);

        actualJson1 = BsonJsonUtil.toJson(document1);
        assertThat(actualJson1).isEqualTo(expectedJsonForDoc1);
    }

}
