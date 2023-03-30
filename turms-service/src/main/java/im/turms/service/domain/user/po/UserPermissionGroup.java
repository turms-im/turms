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

import java.util.Map;
import java.util.Set;

import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;

/**
 * No need to shard because there should be only a few user permission groups.
 *
 * @author James Chen
 */
@Data
@Document(UserPermissionGroup.COLLECTION_NAME)
public final class UserPermissionGroup extends BaseEntity {

    public static final String COLLECTION_NAME = "userPermissionGroup";

    @Id
    private final Long id;

    @Field(Fields.CREATABLE_GROUP_TYPE_IDS)
    private final Set<Long> creatableGroupTypeIds;

    @Field(Fields.OWNED_GROUP_LIMIT)
    private final Integer ownedGroupLimit;

    @Field(Fields.OWNED_GROUP_LIMIT_FOR_EACH_GROUP_TYPE)
    private final Integer ownedGroupLimitForEachGroupType;

    @Field(Fields.GROUP_TYPE_TO_LIMIT)
    private final Map<Long, Integer> groupTypeIdToLimit;

    public static final class Fields {
        public static final String CREATABLE_GROUP_TYPE_IDS = "cgtid";
        public static final String OWNED_GROUP_LIMIT = "ogl";
        public static final String OWNED_GROUP_LIMIT_FOR_EACH_GROUP_TYPE = "oglegt";
        public static final String GROUP_TYPE_TO_LIMIT = "gtl";

        private Fields() {
        }
    }

}
