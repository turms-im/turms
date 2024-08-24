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

package helper;

import java.util.Map;

import im.turms.server.common.access.client.dto.model.common.Value;

/**
 * @author James Chen
 */
public final class SharedBusinessDataConst {

    private SharedBusinessDataConst() {
    }

    public static final Map<String, Value> USER_DEFINED_ATTRIBUTES_FOR_UPSERT = Map.of("key-string",
            Value.newBuilder()
                    .setStringValue("value")
                    .build(),
            "key-int",
            Value.newBuilder()
                    .setInt32Value(123)
                    .build(),
            "key-double",
            Value.newBuilder()
                    .setDoubleValue(3.14)
                    .build(),
            "key-bool",
            Value.newBuilder()
                    .setBoolValue(true)
                    .build(),
            "key-array",
            Value.newBuilder()
                    .addListValue(Value.newBuilder()
                            .setStringValue("array-value")
                            .build())
                    .addListValue(Value.newBuilder()
                            .setInt32Value(123)
                            .build())
                    .build());

    public static final Map<String, Value> EXPECTED_FOUND_USER_DEFINED_ATTRIBUTES =
            Map.of("key-string",
                    Value.newBuilder()
                            .setStringValue("value")
                            .build(),
                    "key-int",
                    Value.newBuilder()
                            .setInt32Value(123)
                            .build(),
                    "key-double",
                    Value.newBuilder()
                            .setDoubleValue(3.14)
                            .build(),
                    "key-bool",
                    Value.newBuilder()
                            .setBoolValue(true)
                            .build(),
                    "key-array",
                    Value.newBuilder()
                            .addListValue(Value.newBuilder()
                                    .setStringValue("array-value")
                                    .build())
                            .addListValue(Value.newBuilder()
                                    // This is an int instead of string
                                    // because we only support using the same element type in an
                                    // array.
                                    .setStringValue("123")
                                    .build())
                            .build());

}