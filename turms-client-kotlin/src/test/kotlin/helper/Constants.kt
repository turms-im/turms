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
package helper

object Constants {
    const val HOST = "127.0.0.1"
    const val PORT = 11510
    const val STORAGE_SERVER_URL = "http://localhost:9000"
    const val ORDER_FIRST = Int.MIN_VALUE
    const val ORDER_HIGHEST_PRIORITY = 0
    const val ORDER_HIGH_PRIORITY = 100
    const val ORDER_MIDDLE_PRIORITY = 200
    const val ORDER_LOW_PRIORITY = 300
    const val ORDER_LOWER_PRIORITY = 400
    const val ORDER_LOWEST_PRIORITY = 500
    const val ORDER_LAST = Int.MAX_VALUE
}
