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

import im.turms.common.exception.StacklessException
import im.turms.common.model.dto.notification.TurmsNotification
import java.util.*

/**
 * @author James Chen
 */
class TurmsBusinessException internal constructor(
    val code: Int,
    val reason: String? = null,
    override val cause: Throwable? = null
) : StacklessException(formatMessage(code, reason), cause) {

    override fun hashCode(): Int {
        return Objects.hash(code, reason, cause)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TurmsBusinessException

        if (code != other.code) return false
        if (reason != other.reason) return false
        if (cause != other.cause) return false

        return true
    }

    companion object {
        private fun formatMessage(code: Int, reason: String?): String {
            return if (reason != null) {
                "code: $code, reason: $reason"
            } else {
                "code: $code"
            }
        }

        fun get(notification: TurmsNotification): TurmsBusinessException {
            val code = notification.code
            return if (notification.hasReason())
                TurmsBusinessException(code, notification.reason)
            else
                TurmsBusinessException(code)
        }
    }

}