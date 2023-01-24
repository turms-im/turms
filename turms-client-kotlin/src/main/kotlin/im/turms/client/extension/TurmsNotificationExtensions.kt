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
import im.turms.client.model.Response
import im.turms.client.model.ResponseStatusCode
import im.turms.client.model.proto.notification.TurmsNotification

/**
 * @author James Chen
 */
val TurmsNotification.isSuccess: Boolean
    get() = hasCode() && ResponseStatusCode.isSuccessCode(code)

val TurmsNotification.isError: Boolean
    get() = hasCode() && ResponseStatusCode.isErrorCode(code)

fun <T> TurmsNotification.toResponse(
    dataTransformer: ((TurmsNotification.Data) -> T)? = null
): Response<T> = Response.fromNotification(this, dataTransformer)

fun TurmsNotification.Data.getLongOrThrow(): Long {
    if (!hasLong()) {
        throw ResponseException.from(
            ResponseStatusCode.INVALID_RESPONSE,
            "Could not get a long value from the invalid response: $this"
        )
    }
    return long
}
