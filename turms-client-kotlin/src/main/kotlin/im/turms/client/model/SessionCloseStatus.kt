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

/**
 * @author James Chen
 */
object SessionCloseStatus {
    const val ILLEGAL_REQUEST = 100
    const val HEARTBEAT_TIMEOUT = 110
    const val LOGIN_TIMEOUT = 111
    const val SWITCH = 112

    const val SERVER_ERROR = 200
    const val SERVER_CLOSED = 201
    const val SERVER_UNAVAILABLE = 202

    const val CONNECTION_CLOSED = 300

    const val UNKNOWN_ERROR = 400

    const val DISCONNECTED_BY_CLIENT = 500
    const val DISCONNECTED_BY_OTHER_DEVICE = 501

    const val DISCONNECTED_BY_ADMIN = 600

    const val USER_IS_DELETED_OR_INACTIVATED = 700
    const val USER_IS_BLOCKED = 701
}
