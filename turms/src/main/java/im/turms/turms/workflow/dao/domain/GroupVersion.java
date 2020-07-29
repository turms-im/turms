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

package im.turms.turms.workflow.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

import java.util.Date;

/**
 * Use the hash-based sharding because the groupId increases monotonically.
 *
 * @author James Chen
 */
@Data
@AllArgsConstructor
@Sharded(shardingStrategy = ShardingStrategy.HASH, immutableKey = true)
public final class GroupVersion {

    public static final String COLLECTION_NAME = "groupVersion";

    @Id
    private final Long groupId;

    @Field(Fields.INFO)
    private final Date info;

    @Field(Fields.MEMBERS)
    private final Date members;

    @Field(Fields.BLACKLIST)
    private final Date blacklist;

    @Field(Fields.JOIN_REQUESTS)
    private final Date joinRequests;

    @Field(Fields.JOIN_QUESTIONS)
    private final Date joinQuestions;

    @Field(Fields.INVITATIONS)
    private final Date invitations;

    public static class Fields {
        public static final String INFO = "inf";
        public static final String MEMBERS = "mbr";
        public static final String BLACKLIST = "bl";
        public static final String JOIN_REQUESTS = "jr";
        public static final String JOIN_QUESTIONS = "jq";
        public static final String INVITATIONS = "invt";

        private Fields() {
        }
    }
}
