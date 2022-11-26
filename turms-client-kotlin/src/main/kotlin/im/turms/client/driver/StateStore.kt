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
package im.turms.client.driver

import im.turms.client.transport.TcpClient

/**
 * @author James Chen
 */
class StateStore {
    // Connection
    var tcp: TcpClient? = null

    @Volatile
    var isConnected = false

    // Session
    @Volatile
    var isSessionOpen = false
    var sessionId: String? = null
    var serverId: String? = null

    // Request
    var lastRequestDate = 0L

    fun reset() {
        tcp = null
        isConnected = false
        isSessionOpen = false
        sessionId = null
        serverId = null
        lastRequestDate = 0
    }
}
