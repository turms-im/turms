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

package im.turms.server.common.infra.context;

/**
 * @author James Chen
 */
public enum JobShutdownOrder {
    CLOSE_FAKE_CLIENTS,
//    DECLINE_NEW_CONNECTIONS,
//    DECLINE_NEW_REQUESTS,
    CLOSE_SESSIONS,

    CLOSE_GATEWAY_UDP_SERVER,
    CLOSE_GATEWAY_TCP_SERVER,
    CLOSE_GATEWAY_WEBSOCKET_SERVER,
    CLOSE_ADMIN_SERVER,

    CLOSE_BLOCKLIST,
    CLOSE_NODE,
    CLOSE_PUSH_NOTIFICATION,
    CLOSE_PLUGINS,

    CLOSE_REDIS_CONNECTIONS,
    CLOSE_MONGODB_CONNECTIONS,

    CLOSE_LOG_PROCESSOR
}