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

package im.turms.turms.workflow.dao.domain.group;

import im.turms.turms.workflow.dao.index.OptionalIndexedForColdData;
import im.turms.turms.workflow.dao.index.OptionalIndexedForDifferentAmount;
import im.turms.turms.workflow.dao.index.OptionalIndexedForExtendedFeature;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

import java.util.Date;

/**
 * @author James Chen
 */
@Data
@Document
@Sharded(shardingStrategy = ShardingStrategy.HASH, immutableKey = true)
public final class Group {

    public static final String COLLECTION_NAME = "group";

    @Id
    private final Long id;

    @Field(Fields.TYPE_ID)
    @OptionalIndexedForDifferentAmount
    private final Long typeId;

    @Field(Fields.CREATOR_ID)
    @OptionalIndexedForExtendedFeature
    private final Long creatorId;

    /**
     * Used by countOwnedGroups to limit the maximum number of groups that a user can have
     */
    @Field(Fields.OWNER_ID)
    @OptionalIndexedForExtendedFeature
    @Indexed
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
    @OptionalIndexedForExtendedFeature
    private final Date creationDate;

    @Field(Fields.DELETION_DATE)
    @OptionalIndexedForColdData
    @Indexed
    private final Date deletionDate;

    @Field(Fields.MUTE_END_DATE)
    @OptionalIndexedForExtendedFeature
    private final Date muteEndDate;

    @Field(Fields.IS_ACTIVE)
    private final Boolean isActive;

    public static class Fields {
        public static final String TYPE_ID = "tid";
        public static final String CREATOR_ID = "cid";
        public static final String OWNER_ID = "oid";
        public static final String NAME = "n";
        public static final String INTRO = "intro";
        public static final String ANNOUNCEMENT = "annt";
        public static final String MINIMUM_SCORE = "ms";
        public static final String CREATION_DATE = "cd";
        public static final String DELETION_DATE = "dd";
        public static final String MUTE_END_DATE = "med";
        public static final String IS_ACTIVE = "ac";

        private Fields() {
        }
    }
}