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

package im.turms.server.common.property.env.service.business.message;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.constant.CronConstant;
import im.turms.server.common.constraint.ValidCron;
import im.turms.server.common.property.constant.TimeType;
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
public class MessageProperties {

    @Description("The time type for the delivery time of message")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private TimeType timeType = TimeType.LOCAL_SERVER_TIME;

    @Description("Whether to check if the target (recipient or group) of a message is active and not deleted")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean checkIfTargetActiveAndNotDeleted = true;

    @Description("The maximum allowed length for the text of a message")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int maxTextLimit = 500;

    @Description("The maximum allowed size for the records of a message")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int maxRecordsSizeBytes = 15 * 1024 * 1024;

    @Description("Whether to persist messages in databases.\n" +
            "Note: If false, senders will not get the message ID after the message has sent and cannot edit it")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean messagePersistent = true;

    @Description("Whether to persist the records of messages in databases")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean recordsPersistent;

    @JsonView(MutablePropertiesView.class)
    @Description("A message will become expired after the TTL has elapsed")
    @Min(0)
    private int messageExpireAfterHours;

    @Description("Clean the expired messages when the cron expression is triggered")
    @GlobalProperty
    @ValidCron
    private String expiredMessagesCleanupCron = CronConstant.DEFAULT_EXPIRED_MESSAGES_CLEANUP_CRON;

    @Description("Whether to delete messages logically by default")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean deleteMessageLogicallyByDefault = true;

    @Description("Whether to allow users to send messages to a stranger")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowSendMessagesToStranger = true;

    @Description("Whether to allow users to send messages to themselves")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowSendMessagesToOneself;

    @Description("Whether to allow users to recall messages.\n" +
            "Note: To recall messages, more system resources are needed")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowRecallMessage = true;

    @Description("Whether to allow the sender of a message to edit the message")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowEditMessageBySender = true;

    @Description("The available recall duration for the sender of a message")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int availableRecallDurationSeconds = 60 * 5;

    @Description("The default available messages number with the \"total\" field that users request")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int defaultAvailableMessagesNumberWithTotal = 1;

    @Description("Whether to send the message to the other sender's online devices when sending a message")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean sendMessageToOtherSenderOnlineDevices = true;

    /**
     * Cache
     */

    @Description("The maximum size of the cache of sent messages.")
    @Min(0)
    private int sentMessageCacheMaxSize = 10240;

    @Description("The life duration of each sent message in the cache." +
            "For a better performance, it is a good practice to keep the value greater than the allowed recall duration")
    @Min(1)
    private int sentMessageExpireAfter = 30;

}