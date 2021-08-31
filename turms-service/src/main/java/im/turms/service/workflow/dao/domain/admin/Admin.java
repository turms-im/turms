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

package im.turms.service.workflow.dao.domain.admin;

import im.turms.server.common.mongo.entity.annotation.Document;
import im.turms.server.common.mongo.entity.annotation.Field;
import im.turms.server.common.mongo.entity.annotation.Id;
import im.turms.server.common.property.env.common.security.SecurityProperties;
import im.turms.service.workflow.dao.index.OptionalIndexedForDifferentAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author James Chen
 * @implNote No need to shard because there should be only a few admins for most groups.
 */
@Data
@AllArgsConstructor
@Document(Admin.COLLECTION_NAME)
@Builder(toBuilder = true)
public final class Admin {

    public static final String COLLECTION_NAME = "admin";

    @Id
    private final String account;

    /**
     * @see SecurityProperties#adminPasswordEncodingAlgorithm
     */
    @Field(Fields.PASSWORD)
    private final String password;

    @Field(Fields.NAME)
    private final String name;

    @Field(Fields.ROLE_ID)
    @OptionalIndexedForDifferentAmount
    private final Long roleId;

    @Field(Fields.REGISTRATION_DATE)
    @OptionalIndexedForDifferentAmount
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