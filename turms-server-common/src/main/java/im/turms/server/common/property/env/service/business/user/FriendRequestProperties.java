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

package im.turms.server.common.property.env.service.business.user;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.constant.CronConstant;
import im.turms.server.common.constraint.ValidCron;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.annotation.GlobalProperty;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@Data
public class FriendRequestProperties {

    @Description("The maximum allowed length for the text of a friend request")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(1)
    private int contentLimit = 200;

    @Description("Whether to allow resending a friend request after the previous request has been declined, ignored, or expired")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowResendingRequestAfterDeclinedOrIgnoredOrExpired = false;

    @Description("A friend request will become expired after the TTL has elapsed. Cannot be infinite for performance reason")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(1)
    private int friendRequestTimeToLiveHours = 30 * 24;

    @Description("Clean or update the expired friend requests when the cron expression is triggered." +
            " Clean if deleteExpiredRequestsWhenCronTriggered is true and update if deleteExpiredRequestsWhenCronTriggered is false")
    @ValidCron
    private String expiredUserFriendRequestsCheckerCron = CronConstant.DEFAULT_EXPIRED_USER_FRIEND_REQUESTS_CHECKER_CRON;

    @Description("Whether to delete expired when the cron expression is triggered")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean deleteExpiredRequestsWhenCronTriggered = false;

}