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

package im.turms.service.domain.conference.po;

import java.util.Date;

import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;
import im.turms.server.common.storage.mongo.entity.annotation.TieredStorage;

import static im.turms.server.common.storage.mongo.entity.IndexType.HASH;
import static im.turms.server.common.storage.mongo.entity.annotation.IndexedReason.EXTENDED_FEATURE;

/**
 * @author James Chen
 */
@Data
@Document(Meeting.COLLECTION_NAME)
@Sharded(shardKey = Meeting.Fields.CREATION_DATE)
@TieredStorage(creationDateFieldName = Meeting.Fields.CREATION_DATE)
public final class Meeting extends BaseEntity {

    public static final String COLLECTION_NAME = "meeting";

    @Id
    private final Long id;

    @Field(Fields.CREATOR_ID)
    @Indexed
    private final Long creatorId;

    @Field(Fields.USER_ID)
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
    private final Long userId;

    @Field(Fields.GROUP_ID)
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
    private final Long groupId;

    @Field(Fields.NAME)
    private final String name;

    @Field(Fields.INTRO)
    private final String intro;

    @Field(Fields.PASSWORD)
    private final String password;

    @Field(Fields.CREATION_DATE)
    @Indexed
    private final Date creationDate;

    @Field(Fields.START_DATE)
    private final Date startDate;

    @Field(Fields.END_DATE)
    private final Date endDate;

    @Field(Fields.CANCEL_DATE)
    private final Date cancelDate;

    public static final class Fields {
        public static final String NAME = "n";
        public static final String INTRO = "intro";
        public static final String PASSWORD = "pw";
        public static final String START_DATE = "sd";
        public static final String END_DATE = "ed";
        public static final String CANCEL_DATE = "ccd";
        public static final String CREATION_DATE = "cd";
        public static final String USER_ID = "uid";
        public static final String GROUP_ID = "gid";
        public static final String CREATOR_ID = "cid";

        private Fields() {
        }
    }

}