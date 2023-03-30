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
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;

/**
 * @author James Chen
 */
@Data
@Document(GroupVersion.COLLECTION_NAME)
@Sharded
public final class GroupVersion extends BaseEntity {

    public static final String COLLECTION_NAME = "groupVersion";

    @Id
    private final Long groupId;

    @Field(Fields.MEMBERS)
    private final Date members;

    @Field(Fields.BLOCKLIST)
    private final Date blocklist;

    @Field(Fields.JOIN_REQUESTS)
    private final Date joinRequests;

    @Field(Fields.JOIN_QUESTIONS)
    private final Date joinQuestions;

    @Field(Fields.INVITATIONS)
    private final Date invitations;

    public static final class Fields {
        public static final String MEMBERS = "mbr";
        public static final String BLOCKLIST = "bl";
        public static final String JOIN_REQUESTS = "jr";
        public static final String JOIN_QUESTIONS = "jq";
        public static final String INVITATIONS = "invt";

        private Fields() {
        }
    }
}
