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

package im.turms.service.domain.user.po;

import java.util.Date;
import java.util.Map;

import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;

/**
 * For most applications, {@link UserSettings} represents the application-level settings related to
 * the user. For example, "language", "theme", etc. So it is more accurate to call
 * "UserApplicationSetting", but we still call it "UserSetting" because we don't want to limit its
 * usage and scope to have to be application-level.
 *
 * @author James Chen
 */
@Data
@Document(UserSettings.COLLECTION_NAME)
@Sharded
public final class UserSettings extends BaseEntity {

    public static final String COLLECTION_NAME = "userSettings";

    @Id
    private final Long userId;

    @Field(Fields.SETTINGS)
    private final Map<String, Object> settings;

    @Field(Fields.LAST_UPDATED_DATE)
    private final Date lastUpdatedDate;

    public static final class Fields {

        public static final String SETTINGS = "s";
        public static final String LAST_UPDATED_DATE = "lud";

        private Fields() {
        }

    }

}