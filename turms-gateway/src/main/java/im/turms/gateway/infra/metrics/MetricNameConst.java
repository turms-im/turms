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

package im.turms.gateway.infra.metrics;

/**
 * @author James Chen
 */
public final class MetricNameConst {

    private MetricNameConst() {
    }

    public static final String TURMS_GATEWAY_SERVER_UDP = "turms.gateway.server.udp";
    public static final String TURMS_GATEWAY_SERVER_TCP = "turms.gateway.server.tcp";
    public static final String TURMS_GATEWAY_SERVER_WEBSOCKET = "turms.gateway.server.websocket";

    public static final String TURMS_BUSINESS_USER_LOGGED_IN = "turms.business.user.logged_in";
    public static final String TURMS_BUSINESS_USER_ONLINE = "turms.business.user.online";

    public static final String LDAP_CLIENT = "ldap.client";
}