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
public class GroupProperties {

    @Description("The maximum allowed length for the text of a group invitation")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int groupInvitationContentLimit = 200;

    @Description("A group invitation will become expired after the expireAfter has elapsed")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int groupInvitationExpireAfterSeconds = 30 * 24 * 3600;

    @Description("The maximum allowed length for the text of a group join request")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int groupJoinRequestContentLimit = 200;

    @Description("A group join request will become expired after the expireAfter has elapsed")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int groupJoinRequestExpireAfterSeconds = 30 * 24 * 3600;

    @Description("Whether to allow users to recall the join requests sent by themselves")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowRecallJoinRequestSentByOneself;

    @Description("Whether to allow the owner and managers of a group to recall pending group invitations")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowRecallPendingGroupInvitationByOwnerAndManager;

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

    @Description("Clean the expired group join requests when the cron expression is triggered" +
            " if deleteExpiredGroupJoinRequestsWhenCronTriggered is true")
    @ValidCron
    private String expiredGroupJoinRequestsCleanupCron = CronConstant.DEFAULT_EXPIRED_GROUP_JOIN_REQUESTS_CLEANUP_CRON;

    @Description("Clean the expired group invitations when the cron expression is triggered" +
            " if deleteExpiredGroupInvitationsWhenCronTriggered is true")
    private String expiredGroupInvitationsCleanupCron = CronConstant.DEFAULT_EXPIRED_GROUP_INVITATIONS_CLEANUP_CRON;

    @Description("Whether to activate a group when created by default")
    @GlobalProperty
    private boolean activateGroupWhenCreated = true;

}