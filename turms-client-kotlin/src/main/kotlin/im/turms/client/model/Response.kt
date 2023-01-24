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

package im.turms.client.model

import im.turms.client.exception.ResponseException
import im.turms.client.extension.isError
import im.turms.client.model.proto.notification.TurmsNotification
import java.util.Collections
import java.util.Date

data class Response<T>(
    val timestamp: Date,
    val requestId: Long?,
    val code: Int,
    val data: T
) {

    fun <T> withData(data: T): Response<T> {
        return Response(timestamp, requestId, code, data)
    }

    companion object {

        @JvmStatic
        fun <T> value(data: T): Response<T> = Response(Date(), null, ResponseStatusCode.OK, data)

        @JvmStatic
        fun unitValue(): Response<Unit> = Response(Date(), null, ResponseStatusCode.OK, Unit)

        @JvmStatic
        fun <T> emptyList(): Response<List<T>> = Response(Date(), null, ResponseStatusCode.OK, Collections.emptyList())

        @JvmStatic
        fun <T> fromNotification(
            notification: TurmsNotification,
            dataTransformer: ((TurmsNotification.Data) -> T)? = null
        ): Response<T> {
            if (!notification.hasCode()) {
                throw ResponseException.from(
                    ResponseStatusCode.INVALID_NOTIFICATION,
                    "Could not parse a success response from a notification without code"
                )
            }
            if (notification.isError) {
                throw ResponseException.from(
                    ResponseStatusCode.INVALID_NOTIFICATION,
                    "Could not parse a success response from non-success notification"
                )
            }
            val data = if (dataTransformer == null) {
                Unit as T
            } else {
                try {
                    dataTransformer.invoke(notification.data)
                } catch (e: Exception) {
                    throw ResponseException.from(
                        ResponseStatusCode.INVALID_NOTIFICATION,
                        "Failed to transform notification data: ${notification.data}. Error: ${e.message}"
                    )
                }
            }
            return Response(
                Date(notification.timestamp),
                if (notification.hasRequestId()) notification.requestId else null,
                notification.code,
                data
            )
        }
    }
}
