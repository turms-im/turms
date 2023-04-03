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

package im.turms.server.common.infra.thread;

/**
 * Gather all thread names used by Turms here so that we can know which threads Turms server use
 * clearly.
 *
 * @author James Chen
 */
public class ThreadNameConst {

    private ThreadNameConst() {
    }

    // Admin HTTP
    public static final String ADMIN_HTTP_PREFIX = "turms-admin-http";

    // Client
    public static final String CLIENT_BLOCKLIST_SYNC = "turms-client-blocklist-sync";

    // Health
    public static final String HEALTH_CHECKER = "turms-health-checker";

    // Lettuce
    public static final String LETTUCE_EVENT_LOOP = "lettuce-event-loop";

    // Log
    public static final String LOG_PROCESSOR = "turms-log-processor";

    // MongoDB
    public static final String MONGO_CHANGE_WATCHER = "turms-mongo-change-watcher";
    public static final String MONGO_EVENT_LOOP = "mongo-event-loop";

    // Node
    public static final String NODE_CONNECTION_CLIENT_IO = "turms-node-connection-client-io";
    public static final String NODE_CONNECTION_KEEPALIVE = "turms-node-connection-keepalive";
    public static final String NODE_CONNECTION_RETRY = "turms-node-connection-retry";
    public static final String NODE_CONNECTION_SERVER = "turms-node-connection-server";
    public static final String NODE_DISCOVERY_CHANGE_NOTIFIER =
            "turms-node-discovery-change-notifier";
    public static final String NODE_DISCOVERY_HEARTBEAT_REFRESHER =
            "turms-node-discovery-heartbeat-refresher";

    // Task
    public static final String TASK_MANAGER = "turms-task-manager";

    // Timer
    public static final String COMMON_TIMER = "turms-common-timer";

    // Shutdown
    public static final String SHUTDOWN = "turms-shutdown";

}