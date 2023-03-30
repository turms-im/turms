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

package im.turms.service.domain.group.po;

import java.util.Date;

import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;

import static im.turms.server.common.storage.mongo.entity.IndexType.HASH;
import static im.turms.server.common.storage.mongo.entity.annotation.IndexedReason.EXPIRABLE;
import static im.turms.server.common.storage.mongo.entity.annotation.IndexedReason.EXTENDED_FEATURE;
import static im.turms.server.common.storage.mongo.entity.annotation.IndexedReason.SMALL_COLLECTION;

/**
 * @author James Chen
 */
@Data
@Document(Group.COLLECTION_NAME)
@Sharded
public final class Group extends BaseEntity {

    public static final String COLLECTION_NAME = "group";

    @Id
    private final Long id;

    @Field(Fields.TYPE_ID)
    @Indexed(optional = true, value = HASH, reason = SMALL_COLLECTION)
    private final Long typeId;

    @Field(Fields.CREATOR_ID)
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
    private final Long creatorId;

    /**
     * Used by countOwnedGroups to limit the maximum number of groups that a user can have
     */
    @Field(Fields.OWNER_ID)
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
    private final Long ownerId;

    @Field(Fields.NAME)
    private final String name;

    @Field(Fields.INTRO)
    private final String intro;

    @Field(Fields.ANNOUNCEMENT)
    private final String announcement;

    @Field(Fields.MINIMUM_SCORE)
    private final Integer minimumScore;

    @Field(Fields.CREATION_DATE)
    @Indexed(optional = true, reason = EXTENDED_FEATURE)
    private final Date creationDate;

    @Field(Fields.DELETION_DATE)
    @Indexed(
            optional = true,
            reason = EXPIRABLE,
            partialFilter = "{"
                    + Fields.DELETION_DATE
                    + ":{$exists:true}}")
    private final Date deletionDate;

    @Field(Fields.LAST_UPDATED_DATE)
    private final Date lastUpdatedDate;

    @Field(Fields.MUTE_END_DATE)
    @Indexed(
            optional = true,
            reason = EXTENDED_FEATURE,
            partialFilter = "{"
                    + Fields.MUTE_END_DATE
                    + ":{$exists:true}}")
    private final Date muteEndDate;

    @Field(Fields.IS_ACTIVE)
    private final Boolean isActive;

    public static final class Fields {
        public static final String TYPE_ID = "tid";
        public static final String CREATOR_ID = "cid";
        public static final String OWNER_ID = "oid";
        public static final String NAME = "n";
        public static final String INTRO = "intro";
        public static final String ANNOUNCEMENT = "annt";
        public static final String MINIMUM_SCORE = "ms";
        public static final String CREATION_DATE = "cd";
        public static final String DELETION_DATE = "dd";
        public static final String LAST_UPDATED_DATE = "lud";
        public static final String MUTE_END_DATE = "med";
        public static final String IS_ACTIVE = "ac";

        private Fields() {
        }
    }
}