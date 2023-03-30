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

package im.turms.server.common.domain.admin.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.infra.property.env.common.security.PasswordProperties;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;

import static im.turms.server.common.storage.mongo.entity.IndexType.HASH;
import static im.turms.server.common.storage.mongo.entity.annotation.IndexedReason.SMALL_COLLECTION;

/**
 * @author James Chen
 * @implNote No need to shard because there should be only a few admins for most groups.
 */
@Data
@AllArgsConstructor
@Document(Admin.COLLECTION_NAME)
@Builder(toBuilder = true)
public final class Admin extends BaseEntity {

    public static final String COLLECTION_NAME = "admin";

    @Id
    private final String account;

    /**
     * @see PasswordProperties#adminPasswordEncodingAlgorithm
     */
    @Field(Fields.PASSWORD)
    private final byte[] password;

    @Field(Fields.NAME)
    private final String name;

    @Field(Fields.ROLE_ID)
    @Indexed(optional = true, value = HASH, reason = SMALL_COLLECTION)
    private final Long roleId;

    @Field(Fields.REGISTRATION_DATE)
    @Indexed(optional = true, reason = SMALL_COLLECTION)
    private final Date registrationDate;

    public static final class Fields {
        public static final String PASSWORD = "pw";
        public static final String NAME = "n";
        public static final String ROLE_ID = "rid";
        public static final String REGISTRATION_DATE = "rd";

        private Fields() {
        }
    }
}