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

import im.turms.client.exception.ResponseException
import java.util.concurrent.ExecutionException

object ExceptionUtil {
    fun isResponseStatusCode(throwable: ExecutionException, code: Int): Boolean {
        val cause = throwable.cause
        return isResponseException(cause, code)
    }

    fun isResponseException(throwable: Throwable?, code: Int): Boolean {
        return if (throwable is ResponseException) {
            throwable.code == code
        } else {
            false
        }
    }
}
