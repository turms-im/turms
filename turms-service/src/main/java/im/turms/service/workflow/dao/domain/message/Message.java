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

package im.turms.service.workflow.dao.domain.message;

import im.turms.server.common.mongo.entity.annotation.CompoundIndex;
import im.turms.server.common.mongo.entity.annotation.Document;
import im.turms.server.common.mongo.entity.annotation.Field;
import im.turms.server.common.mongo.entity.annotation.Id;
import im.turms.server.common.mongo.entity.annotation.Indexed;
import im.turms.server.common.mongo.entity.annotation.Sharded;
import im.turms.server.common.mongo.entity.annotation.TieredStorage;
import lombok.Data;

import java.util.Date;
import java.util.List;

import static im.turms.server.common.mongo.entity.IndexType.HASH;
import static im.turms.server.common.mongo.entity.annotation.IndexedReason.EXPIRABLE;
import static im.turms.server.common.mongo.entity.annotation.IndexedReason.EXTENDED_FEATURE;

/**
 * @author James Chen
 * @implNote The shard key is DELIVERY_DATE instead of TARGET_ID.
 * Being sharded by TARGET_ID, most CRUD operations are efficient for small scale applications,
 * but Turms is designed for medium and large scale applications,
 * so we use DELIVERY_DATE as the shard key to support tiered storage.
 */
@Data
@Document(Message.COLLECTION_NAME)
@CompoundIndex(value = {Message.Fields.DELIVERY_DATE, Message.Fields.TARGET_ID},
        ifNotExist = Message.Fields.CONVERSATION_ID)
@CompoundIndex(value = {Message.Fields.DELIVERY_DATE, Message.Fields.CONVERSATION_ID},
        ifExist = Message.Fields.CONVERSATION_ID)
@Sharded(shardKey = Message.Fields.DELIVERY_DATE)
@TieredStorage(creationDateFieldName = Message.Fields.DELIVERY_DATE)
public final class Message {

    public static final String COLLECTION_NAME = "message";

    /**
     * Note that because it's not common for most instant messaging scenarios to
     * query messages by ID only. Currently, the only scenario that needs to query
     * a message by ID is that a user forwards (copies) a message to another recipient/group.
     * Because it's not frequently used feature, we don't use ID as a part of the shard key.
     */
    @Id
    private final Long id;

    @Field(Fields.CONVERSATION_ID)
    private final byte[] conversationId;

    /**
     * Not indexed because it has a low index selectivity
     * and the clients cannot/shouldn't just query messages by isGroupMessage
     * (it must come with other conditions)
     * https://github.com/turms-im/turms/issues/336
     */
    @Field(Fields.IS_GROUP_MESSAGE)
    private final Boolean isGroupMessage;

    @Field(Fields.IS_SYSTEM_MESSAGE)
    private final Boolean isSystemMessage;

    @Field(Fields.DELIVERY_DATE)
    private final Date deliveryDate;

    @Field(Fields.MODIFICATION_DATE)
    private final Date modificationDate;

    @Field(Fields.DELETION_DATE)
    @Indexed(optional = true, reason = EXPIRABLE)
    private final Date deletionDate;

    @Field(Fields.RECALL_DATE)
    private final Date recallDate;

    @Field(Fields.TEXT)
    private final String text;

    @Field(Fields.SENDER_ID)
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
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
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
    private final Long referenceId;

    @Field(Fields.SEQUENCE_ID)
    private final Integer sequenceId;

    @Field(Fields.PRE_MESSAGE_ID)
    private final Long preMessageId;

    public Long groupId() {
        return isGroupMessage != null && isGroupMessage ? targetId : null;
    }

    public static final class Fields {
        public static final String CONVERSATION_ID = "cid";
        public static final String IS_GROUP_MESSAGE = "gm";
        public static final String IS_SYSTEM_MESSAGE = "sm";
        public static final String DELIVERY_DATE = "dyd";
        public static final String MODIFICATION_DATE = "md";
        public static final String DELETION_DATE = "dd";
        public static final String RECALL_DATE = "rd";
        public static final String TEXT = "txt";
        public static final String SENDER_ID = "sid";
        public static final String TARGET_ID = "tid";
        public static final String RECORDS = "rec";
        public static final String BURN_AFTER = "bf";
        public static final String REFERENCE_ID = "rid";
        public static final String SEQUENCE_ID = "sqid";
        public static final String PRE_MESSAGE_ID = "pmid";

        private Fields() {
        }
    }
}