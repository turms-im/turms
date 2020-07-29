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

package im.turms.server.common.property.env.service.business;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.constant.CronConstant;
import im.turms.server.common.constraint.CronConstraint;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@Data
public class GroupProperties {

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum allowed length for the text of a group invitation")
    @Min(0)
    private int groupInvitationContentLimit = 200;

    @JsonView(MutablePropertiesView.class)
    @Description("A group invitation will become expired after the TTL has elapsed. 0 means infinite")
    @Min(0)
    private int groupInvitationTimeToLiveHours = 0;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum allowed length for the text of a group join request")
    @Min(0)
    private int groupJoinRequestContentLimit = 200;

    @JsonView(MutablePropertiesView.class)
    @Description("A group join request will become expired after the TTL has elapsed. 0 means infinite")
    @Min(0)
    private int groupJoinRequestTimeToLiveHours = 0;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to allow users to recall the join requests sent by themselves")
    private boolean allowRecallingJoinRequestSentByOneself = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to allow the owner and managers of a group to recall pending group invitations")
    private boolean allowRecallingPendingGroupInvitationByOwnerAndManager = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to delete groups logically by default")
    private boolean deleteGroupLogicallyByDefault = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to delete expired group join requests when the cron expression is triggered")
    private boolean deleteExpiredGroupJoinRequestsWhenCronTriggered = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to delete expired group invitations when the cron expression is triggered")
    private boolean deleteExpiredGroupInvitationsWhenCronTriggered = false;

    @Description("Clean or update the expired group join requests when the cron expression is triggered." +
            " Clean if deleteExpiredGroupJoinRequestsWhenCronTriggered is true and update if deleteExpiredGroupJoinRequestsWhenCronTriggered is false")
    @CronConstraint
    private String expiredGroupJoinRequestsCheckerCron = CronConstant.DEFAULT_EXPIRED_GROUP_JOIN_REQUESTS_CHECKER_CRON;

    @Description("Clean or update the expired group invitations when the cron expression is triggered." +
            " Clean if deleteExpiredGroupInvitationsWhenCronTriggered is true and update if deleteExpiredGroupInvitationsWhenCronTriggered is false")
    private String expiredGroupInvitationsCheckerCron = CronConstant.DEFAULT_EXPIRED_GROUP_INVITATIONS_CHECKER_CRON;

    @Description("Whether to activate a group when created by default")
    private boolean activateGroupWhenCreated = true;

}