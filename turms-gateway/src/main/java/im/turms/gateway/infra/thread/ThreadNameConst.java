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

package im.turms.gateway.infra.thread;

/**
 * @author James Chen
 */
public class ThreadNameConst {

    private ThreadNameConst() {
    }

    // Client
    public static final String FAKE_CLIENT = "turms-fake-client";
    public static final String FAKE_CLIENT_MANAGER = "turms-fake-client-manager";
    public static final String CLIENT_HEARTBEAT_REFRESHER = "turms-client-heartbeat-refresher";

    // Gateway
    // "turms-gateway-udp" -> "turms-gateway-udp-acceptor" + "turms-gateway-udp-worker"
    public static final String GATEWAY_UDP_PREFIX = "turms-gateway-udp";
    public static final String GATEWAY_TCP_PREFIX = "turms-gateway-tcp";
    public static final String GATEWAY_WS_PREFIX = "turms-gateway-ws";

    public static final String GATEWAY_IDLE_CONNECTION_TIMEOUT_TIMER =
            "turms-gateway-idle-connection-timeout-timer";

    // Throttler
    public static final String IP_REQUEST_TOKEN_BUCKET_CLEANER =
            "turms-ip-request-token-bucket-cleaner";

}
