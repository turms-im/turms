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

package im.turms.server.common.infra.task;

/**
 * Place all cron expressions here, so we know what happens at the specific times clearly
 *
 * @author James Chen
 * @see org.springframework.scheduling.support.CronExpression
 */
public final class CronConst {

    private CronConst() {
    }

    public static final String DEFAULT_ONLINE_USERS_NUMBER_LOGGING_CRON = "0/15 * * * * *";

    public static final String DEFAULT_EXPIRED_USER_FRIEND_REQUESTS_CLEANUP_CRON = "0 0 2 * * *";
    public static final String DEFAULT_EXPIRED_GROUP_INVITATIONS_CLEANUP_CRON = "0 15 2 * * *";
    public static final String DEFAULT_EXPIRED_GROUP_JOIN_REQUESTS_CLEANUP_CRON = "0 30 2 * * *";

    public static final String DEFAULT_EXPIRED_MESSAGES_CLEANUP_CRON = "0 45 2 * * *";

    public static final String DEFAULT_TIERED_STORAGE_TIER_RANGE_UPDATING_CRON = "0 0 3 * * *";

    public static final String EXPIRED_BLOCKED_CLIENT_CLEANUP_CRON = "0 0 1/6 * * *";

    public static final String EXPIRED_ADMIN_API_ACCESS_INFO_CLEANUP_CRON = "0 0/30 * * * *";

    public static final String CLOSED_RECORDINGS_CLEANUP_CRON = "0 0/10 * * * *";

}
