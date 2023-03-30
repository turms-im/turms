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

package im.turms.server.common.infra.property.env.service.business.group;

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
public class GroupJoinRequestProperties {

    @Description("The maximum allowed length for the text of a group join request")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxContentLength = 200;

    @Description("A group join request will become expired after the specified time has elapsed")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int expireAfterSeconds = 30 * 24 * 3600;

    @Description("Whether to allow users to recall the join requests sent by themselves")
    @GlobalProperty
    @MutableProperty
    private boolean allowRecallJoinRequestSentByOneself;

    @Description("Whether to delete expired group join requests when the cron expression is triggered")
    @GlobalProperty
    @MutableProperty
    private boolean deleteExpiredJoinRequestsWhenCronTriggered;

    @Description("Clean the expired group join requests when the cron expression is triggered"
            + " if \"deleteExpiredJoinRequestsWhenCronTriggered\" is true")
    @ValidCron
    private String expiredJoinRequestsCleanupCron =
            CronConst.DEFAULT_EXPIRED_GROUP_JOIN_REQUESTS_CLEANUP_CRON;
}