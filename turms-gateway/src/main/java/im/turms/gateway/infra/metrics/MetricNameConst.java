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

    public static final String CLIENT_NETWORK = "turms.client.network";

    public static final String LOGGED_IN_USERS_COUNTER = "user.logged_in";
    public static final String ONLINE_USERS_GAUGE = "user.online";
}
