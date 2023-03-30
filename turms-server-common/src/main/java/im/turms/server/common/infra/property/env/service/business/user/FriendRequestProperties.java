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

package im.turms.server.common.infra.property.env.service.business.user;

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;
import im.turms.server.common.infra.task.CronConst;
import im.turms.server.common.infra.validation.ValidCron;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class FriendRequestProperties {

    @Description("The maximum allowed length for the text of a friend request")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxContentLength = 200;

    @Description("Whether to allow resending a friend request after the previous request has been declined, ignored, or expired")
    @GlobalProperty
    @MutableProperty
    private boolean allowSendRequestAfterDeclinedOrIgnoredOrExpired;

    @Description("A friend request will become expired after the specified time has elapsed")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int friendRequestExpireAfterSeconds = 30 * 24 * 3600;

    @Description("Clean expired friend requests when the cron expression is triggered"
            + " if deleteExpiredRequestsWhenCronTriggered is true")
    @ValidCron
    private String expiredUserFriendRequestsCleanupCron =
            CronConst.DEFAULT_EXPIRED_USER_FRIEND_REQUESTS_CLEANUP_CRON;

    @Description("Whether to delete expired when the cron expression is triggered")
    @GlobalProperty
    @MutableProperty
    private boolean deleteExpiredRequestsWhenCronTriggered;

}