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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

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
    @JsonView(MutablePropertiesView.class)
    @Min(1)
    private int contentLimit = 200;

    @Description("Whether to allow resending a friend request after the previous request has been declined, ignored, or expired")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowSendRequestAfterDeclinedOrIgnoredOrExpired;

    @Description("A friend request will become expired after the expireAfter has elapsed")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int friendRequestExpireAfterSeconds = 30 * 24 * 3600;

    @Description("Clean expired friend requests when the cron expression is triggered" +
            " if deleteExpiredRequestsWhenCronTriggered is true")
    @ValidCron
    private String expiredUserFriendRequestsCleanupCron = CronConstant.DEFAULT_EXPIRED_USER_FRIEND_REQUESTS_CLEANUP_CRON;

    @Description("Whether to delete expired when the cron expression is triggered")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean deleteExpiredRequestsWhenCronTriggered;

}