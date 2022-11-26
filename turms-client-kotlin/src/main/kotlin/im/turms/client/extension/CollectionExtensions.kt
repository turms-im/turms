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

package im.turms.client.extension

import im.turms.client.exception.ResponseException
import im.turms.client.model.ResponseStatusCode

/**
 * @author James Chen
 */
fun List<String>.toMap(): Map<String, String> {
    if (size % 2 != 0) {
        throw ResponseException.from(
            ResponseStatusCode.ILLEGAL_ARGUMENT,
            "The number of elements must be even"
        )
    }
    val map = mutableMapOf<String, String>()
    for (i in indices step 2) {
        map[this[i]] = this[i + 1]
    }
    return map
}
