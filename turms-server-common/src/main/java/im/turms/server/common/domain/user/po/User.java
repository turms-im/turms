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

package im.turms.server.common.domain.user.po;

import java.util.Date;

import lombok.Data;

import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.EnumNumber;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;

/**
 * @author James Chen
 */
@Data
@Document(User.COLLECTION_NAME)
@Sharded
public final class User extends BaseEntity {

    public static final String COLLECTION_NAME = "user";

    @Id
    private final Long id;

    @Field(Fields.PASSWORD)
    private final byte[] password;

    @Field(Fields.NAME)
    private final String name;

    @Field(Fields.INTRO)
    private final String intro;

    @Field(Fields.PROFILE_PICTURE)
    private final String profilePicture;

    /**
     * The field is reserved for future use because there is no field needing user access control
     * currently
     */
    @Field(Fields.PROFILE_ACCESS_STRATEGY)
    @EnumNumber
    private final ProfileAccessStrategy profileAccessStrategy;

    @Field(Fields.PERMISSION_GROUP_ID)
    private final Long permissionGroupId;

    @Field(Fields.REGISTRATION_DATE)
    private final Date registrationDate;

    @Field(Fields.DELETION_DATE)
    private final Date deletionDate;

    @Field(Fields.LAST_UPDATED_DATE)
    private final Date lastUpdatedDate;

    @Field(Fields.IS_ACTIVE)
    private final Boolean isActive;

    public static final class Fields {

        public static final String PASSWORD = "pw";
        public static final String NAME = "n";
        public static final String INTRO = "intro";
        public static final String PROFILE_PICTURE = "pp";
        public static final String PROFILE_ACCESS_STRATEGY = "pas";
        public static final String PERMISSION_GROUP_ID = "pgid";
        public static final String REGISTRATION_DATE = "rd";
        public static final String DELETION_DATE = "dd";
        public static final String LAST_UPDATED_DATE = "lud";
        public static final String IS_ACTIVE = "act";

        private Fields() {
        }

    }
}