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

package im.turms.server.common.infra.metrics;

/**
 * @author James Chen
 * @implNote 1. A metric name must start with "turms" if it belongs to a component specific to
 *           Turms. If a component is just used by Turms (e.g., Lettuce, LDAP client, JVM), their
 *           metric names should not start with "turms".
 *           <p>
 *           2. Always use singular nouns.
 * @see <a href="https://prometheus.io/docs/practices/naming">Metric and label naming</a>
 */
public final class CommonMetricNameConst {

    private CommonMetricNameConst() {
    }

    // Admin API

    public static final String TURMS_ADMIN_API_SERVER_HTTP = "turms.admin.api.server.http";

    // RPC

    public static final String TURMS_RPC_SERVER_TCP = "turms.rpc.server.tcp";
    public static final String TURMS_RPC_CLIENT_TCP = "turms.rpc.client.tcp";

    // Client Request

    public static final String TURMS_CLIENT_REQUEST = "turms.client.request";
    public static final String TURMS_CLIENT_REQUEST_PENDING = TURMS_CLIENT_REQUEST
            + ".pending";
    public static final String TURMS_CLIENT_REQUEST_TAG_TYPE = "type";

}