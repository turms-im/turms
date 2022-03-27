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
 */
public final class CommonMetricNameConst {

    private CommonMetricNameConst() {
    }

    // Admin API

    public static final String ADMIN_API = "admin.api";

    // Node connection

    public static final String NODE_TCP_SERVER = "turms.node.tcp.server";
    public static final String NODE_TCP_CLIENT = "turms.node.tcp.client";

    // Client requests

    public static final String CLIENT_REQUEST = "turms.client.request";
    public static final String CLIENT_REQUEST_TAG_TYPE = "type";

}
