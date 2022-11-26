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
package im.turms.client.exception

import im.turms.client.model.proto.notification.TurmsNotification

/**
 * @author James Chen
 */
data class ResponseException internal constructor(
    val requestId: Long?,
    val code: Int,
    val reason: String? = null,
    override val cause: Throwable? = null
) : RuntimeException(formatMessage(code, reason), cause) {

    companion object {
        private fun formatMessage(code: Int, reason: String?): String {
            return if (reason != null) {
                "code: $code, reason: $reason"
            } else {
                "code: $code"
            }
        }

        fun from(notification: TurmsNotification): ResponseException {
            val code = notification.code
            val requestId = if (notification.hasRequestId()) notification.requestId else null
            return if (notification.hasReason()) {
                ResponseException(requestId, code, notification.reason)
            } else {
                ResponseException(requestId, code)
            }
        }

        fun from(code: Int, reason: String? = null, cause: Throwable? = null): ResponseException {
            return ResponseException(null, code, reason, cause)
        }
    }
}
