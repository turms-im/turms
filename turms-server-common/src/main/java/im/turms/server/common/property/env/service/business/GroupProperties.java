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
public class GroupProperties {

    @Description("The maximum allowed length for the text of a group invitation")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int groupInvitationContentLimit = 200;

    @Description("A group invitation will become expired after the TTL has elapsed. Cannot be infinite for performance reason")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(1)
    private int groupInvitationTimeToLiveHours = 30 * 24;

    @Description("The maximum allowed length for the text of a group join request")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int groupJoinRequestContentLimit = 200;

    @Description("A group join request will become expired after the TTL has elapsed. Cannot be infinite for performance reason")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(1)
    private int groupJoinRequestTimeToLiveHours = 30 * 24;

    @Description("Whether to allow users to recall the join requests sent by themselves")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowRecallingJoinRequestSentByOneself;

    @Description("Whether to allow the owner and managers of a group to recall pending group invitations")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowRecallingPendingGroupInvitationByOwnerAndManager;

    @Description("Whether to delete groups logically by default")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean deleteGroupLogicallyByDefault = true;

    @Description("Whether to delete expired group join requests when the cron expression is triggered")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean deleteExpiredGroupJoinRequestsWhenCronTriggered;

    @Description("Whether to delete expired group invitations when the cron expression is triggered")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean deleteExpiredGroupInvitationsWhenCronTriggered;

    @Description("Clean or update the expired group join requests when the cron expression is triggered. " +
            "Clean if deleteExpiredGroupJoinRequestsWhenCronTriggered is true " +
            "and update if deleteExpiredGroupJoinRequestsWhenCronTriggered is false")
    @ValidCron
    private String expiredGroupJoinRequestsCheckerCron = CronConstant.DEFAULT_EXPIRED_GROUP_JOIN_REQUESTS_CHECKER_CRON;

    @Description("Clean or update the expired group invitations when the cron expression is triggered. " +
            "Clean if deleteExpiredGroupInvitationsWhenCronTriggered is true " +
            "and update if deleteExpiredGroupInvitationsWhenCronTriggered is false")
    private String expiredGroupInvitationsCheckerCron = CronConstant.DEFAULT_EXPIRED_GROUP_INVITATIONS_CHECKER_CRON;

    @Description("Whether to activate a group when created by default")
    @GlobalProperty
    private boolean activateGroupWhenCreated = true;

}