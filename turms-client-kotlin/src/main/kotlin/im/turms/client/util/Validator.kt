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
package im.turms.client.util

import java.lang.reflect.Array

/**
 * @author James Chen
 */
object Validator {
    fun areAllFalsy(vararg array: Any?): Boolean {
        for (value in array) {
            if (value == null) continue
            if (value is String) {
                if (value.isNotEmpty()) {
                    return false
                }
            } else if (value is Collection<*>) {
                if (value.isNotEmpty()) {
                    return false
                }
            } else if (value is Map<*, *>) {
                if (value.isNotEmpty()) {
                    return false
                }
            } else if (value.javaClass.isArray) {
                if (Array.getLength(value) > 0) {
                    return false
                }
            } else {
                return false
            }
        }
        return true
    }

    fun areAllNull(vararg array: Any?): Boolean {
        for (o in array) {
            if (o != null) {
                return false
            }
        }
        return true
    }

    fun areAllNullOrNonNull(vararg array: Any?): Boolean {
        val isFirstValueNull = array.firstOrNull() == null
        for (value in array) {
            if ((value == null) != isFirstValueNull) {
                return false
            }
        }
        return true
    }
}
