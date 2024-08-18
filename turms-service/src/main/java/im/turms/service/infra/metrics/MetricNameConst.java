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

package im.turms.service.infra.metrics;

/**
 * @author James Chen
 */
public final class MetricNameConst {

    private MetricNameConst() {
    }

    // User

    public static final String TURMS_BUSINESS_USER_REGISTERED = "turms.business.user.registered";
    public static final String TURMS_BUSINESS_USER_DELETED = "turms.business.user.deleted";

    // Group

    public static final String TURMS_BUSINESS_GROUP_CREATED = "turms.business.group.created";
    public static final String TURMS_BUSINESS_GROUP_DELETED = "turms.business.group.deleted";

    // Message

    public static final String TURMS_BUSINESS_MESSAGE_SENT = "turms.business.message.sent";

}