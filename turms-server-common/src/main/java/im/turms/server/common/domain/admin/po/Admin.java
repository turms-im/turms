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
import java.util.Set;

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
 * @implNote 1. No need to shard because there should be only a few admin users for most groups.
 *           <p>
 *           2. We call it "admin" for "administrator" instead of "admin user" for "administrative
 *           user" that is verbose.
 */
@Data
@AllArgsConstructor
@Document(Admin.COLLECTION_NAME)
@Builder(toBuilder = true)
public final class Admin extends BaseEntity {

    public static final String COLLECTION_NAME = "admin";

    @Id
    private final Long id;

    /**
     * We call it "loginName" instead of "username" or "userId" because:
     * <p>
     * 1. Though most systems use the word "username", it is very confusing for users because a lot
     * of people consider "username" as the display name, which is not the case.
     * <p>
     * 2. "userId" is a good name, but it is error-prone for users to distinguish "userId" and "id"
     * (record ID) for our cases, especially calling admin APIs.
     */
    @Field(Fields.LOGIN_NAME)
    @Indexed(unique = true)
    private final String loginName;

    /**
     * @see PasswordProperties#adminPasswordEncodingAlgorithm
     */
    @Field(Fields.PASSWORD)
    private final byte[] password;

    /**
     * As the name indicates, this is the name for display only (e.g., "James Chen"), and is not
     * used for authentication.
     */
    @Field(Fields.DISPLAY_NAME)
    private final String displayName;

    @Field(Fields.ROLE_IDS)
    @Indexed(optional = true, value = HASH, reason = SMALL_COLLECTION)
    private final Set<Long> roleIds;

    @Field(Fields.REGISTRATION_DATE)
    @Indexed(optional = true, reason = SMALL_COLLECTION)
    private final Date registrationDate;

    public static final class Fields {
        public static final String LOGIN_NAME = "ln";
        public static final String PASSWORD = "pw";
        public static final String DISPLAY_NAME = "n";
        public static final String ROLE_IDS = "rid";
        public static final String REGISTRATION_DATE = "rd";

        private Fields() {
        }
    }
}