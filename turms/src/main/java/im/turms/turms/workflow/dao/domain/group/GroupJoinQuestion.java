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

package im.turms.turms.workflow.dao.domain.group;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

import java.util.Set;

/**
 * @author James Chen
 */
@Data
@Document
@Sharded(shardKey = GroupJoinQuestion.Fields.GROUP_ID, shardingStrategy = ShardingStrategy.HASH, immutableKey = true)
public final class GroupJoinQuestion {

    public static final String COLLECTION_NAME = "groupJoinQuestion";

    @Id
    private final Long id;

    @Field(Fields.GROUP_ID)
    @Indexed
    private final Long groupId;

    @Field(Fields.QUESTION)
    private final String question;

    @Field(Fields.ANSWERS)
    private final Set<String> answers;

    // Note that score can be a negative number
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