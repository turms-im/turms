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

package im.turms.turms.workflow.dao.domain;

import im.turms.turms.workflow.dao.index.documentation.OptionalIndexedForAdvancedFeature;
import im.turms.turms.workflow.dao.index.documentation.OptionalIndexedForCustomFeature;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;

import java.util.Date;
import java.util.List;

/**
 * @author James Chen
 */
@Data
@Document
@CompoundIndex(
        name = Message.Fields.TARGET_ID + "_" + Message.Fields.DELIVERY_DATE + "_idx",
        def = "{'" + Message.Fields.TARGET_ID + "': 1, '" + Message.Fields.DELIVERY_DATE + "': 1}")
@Sharded(shardKey = {Message.Fields.TARGET_ID, Message.Fields.DELIVERY_DATE}, immutableKey = true)
public final class Message {

    public static final String COLLECTION_NAME = "message";

    /**
     * Note that the ID is only used when user forwards a created message to another recipient/group
     * so that it not the common for most instant messaging scenarios and the ID doesn't be used as a part of the shard key.
     * <p>
     * WARNING: Because of the reason mentioned above, it's not
     * to avoid
     */
    @Id
    private final Long id;

    /**
     * Not indexed because it has a low index selectivity
     * and the clients cannot/shouldn't just query messages by isGroupMessage (there must come with other conditions)
     * https://github.com/turms-im/turms/issues/336
     */
    @Field(Fields.IS_GROUP_MESSAGE)
    private final Boolean isGroupMessage;

    @Field(Fields.IS_SYSTEM_MESSAGE)
    private final Boolean isSystemMessage;

    @Field(Fields.DELIVERY_DATE)
    private final Date deliveryDate;

    @Field(Fields.DELETION_DATE)
    @OptionalIndexedForAdvancedFeature
    private final Date deletionDate;

    @Field(Fields.TEXT)
    private final String text;

    @Field(Fields.SENDER_ID)
    @OptionalIndexedForCustomFeature
    private final Long senderId;

    /**
     * Use "target" rather than "recipient" because the target may be a recipient or a group.
     */
    @Field(Fields.TARGET_ID)
    private final Long targetId;

    /**
     * Use list to keep order
     */
    @Field(Fields.RECORDS)
    private final List<byte[]> records;

    @Field(Fields.BURN_AFTER)
    private final Integer burnAfter;

    @Field(Fields.REFERENCE_ID)
    @OptionalIndexedForCustomFeature
    private final Long referenceId;

    public Long groupId() {
        return isGroupMessage != null && isGroupMessage ? targetId : null;
    }

    public static class Fields {
        public static final String IS_GROUP_MESSAGE = "gm";
        public static final String IS_SYSTEM_MESSAGE = "sm";
        public static final String DELIVERY_DATE = "dyd";
        public static final String DELETION_DATE = "dd";
        public static final String TEXT = "txt";
        public static final String SENDER_ID = "sid";
        public static final String TARGET_ID = "tid";
        public static final String RECORDS = "rec";
        public static final String BURN_AFTER = "bf";
        public static final String REFERENCE_ID = "rid";

        private Fields() {
        }
    }
}