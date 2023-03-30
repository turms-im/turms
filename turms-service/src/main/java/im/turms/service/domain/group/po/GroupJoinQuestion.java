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

import java.util.LinkedHashSet;

import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.IndexType;
import im.turms.server.common.storage.mongo.entity.ShardingStrategy;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;

/**
 * @author James Chen
 */
@Data
@Document(GroupJoinQuestion.COLLECTION_NAME)
@Sharded(shardKey = GroupJoinQuestion.Fields.GROUP_ID, shardingStrategy = ShardingStrategy.HASH)
public final class GroupJoinQuestion extends BaseEntity {

    public static final String COLLECTION_NAME = "groupJoinQuestion";

    @Id
    private final Long id;

    @Field(Fields.GROUP_ID)
    @Indexed(IndexType.HASH)
    private final Long groupId;

    @Field(Fields.QUESTION)
    private final String question;

    @Field(Fields.ANSWERS)
    private final LinkedHashSet<String> answers;

    /**
     * Note that a score can be negative
     */
    @Field(Fields.SCORE)
    private final Integer score;

    public static final class Fields {
        public static final String GROUP_ID = "gid";
        public static final String QUESTION = "q";
        public static final String ANSWERS = "ans";
        public static final String SCORE = "score";

        private Fields() {
        }
    }
}