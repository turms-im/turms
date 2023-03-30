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

import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.types.MaxKey;
import org.bson.types.MinKey;

/**
 * @author James Chen
 * @implNote We can try to make BsonValue a primitive class in the future to avoid creating objects
 */
public final class BsonPool {

    private BsonPool() {
    }

    public static final BsonInt32 BSON_INT32_0 = new BsonInt32(0);
    public static final BsonInt32 BSON_INT32_1 = new BsonInt32(1);
    public static final BsonInt32 BSON_INT32_NEGATIVE_1 = new BsonInt32(-1);
    public static final BsonString BSON_STRING_HASHED = new BsonString("hashed");
    public static final BsonString BSON_STRING_EMPTY = new BsonString("");
    public static final MaxKey MAX_KEY = new MaxKey();
    public static final MinKey MIN_KEY = new MinKey();

}