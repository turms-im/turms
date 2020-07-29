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

package im.turms.server.common.dao.domain;

import im.turms.common.constant.ProfileAccessStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;

import java.util.Date;

/**
 * @author James Chen
 */
@Data
@AllArgsConstructor
@Document
@Sharded(immutableKey = true)
public final class User {

    public static final String COLLECTION_NAME = "user";

    @Id
    private final Long id;

    @Field(Fields.PASSWORD)
    private final String password;

    @Field(Fields.NAME)
    private final String name;

    @Field(Fields.INTRO)
    private final String intro;

    @Field(Fields.PROFILE_ACCESS)
    private final ProfileAccessStrategy profileAccess;

    @Field(Fields.PERMISSION_GROUP_ID)
    private final Long permissionGroupId;

    @Field(Fields.REGISTRATION_DATE)
    private final Date registrationDate;

    @Field(Fields.DELETION_DATE)
    private final Date deletionDate;

    @Field(Fields.IS_ACTIVE)
    private final Boolean isActive;

    @Field(Fields.LAST_UPDATED_DATE)
    @Indexed
    private final Date lastUpdatedDate;

    public static class Fields {

        public static final String PASSWORD = "pw";
        public static final String NAME = "n";
        public static final String INTRO = "intro";
        public static final String PROFILE_ACCESS = "pa";
        public static final String PERMISSION_GROUP_ID = "pgid";
        public static final String REGISTRATION_DATE = "rd";
        public static final String DELETION_DATE = "dd";
        public static final String IS_ACTIVE = "act";
        public static final String LAST_UPDATED_DATE = "lud";

        private Fields() {
        }

    }
}