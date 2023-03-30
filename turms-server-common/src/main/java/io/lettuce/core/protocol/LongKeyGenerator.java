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

package io.lettuce.core.protocol;

/**
 * The method is used to eliminate a lot of objects for large volume of data e.g. If there is 10,000
 * keys, we need to create a List<Long> to collect the keys and then pass the keys with other keys
 * (e.g. TTL) to encode them to one ByteBuf[], and then pass the ByteBuf[] to Lettuce, which create
 * a List<KeyArgument>(singularArguments) to hold them. Obviously, it is a disaster for performance.
 * <p>
 * LongKeyGenerator works as a mediator between the key collector and the Redis encoder, and it
 * collects and encodes at the same time, and put all keys into one ByteBuf as one KeyArgument
 *
 * @author James Chen
 */
public abstract class LongKeyGenerator {
    public abstract int estimatedSize();

    public abstract long next();
}
