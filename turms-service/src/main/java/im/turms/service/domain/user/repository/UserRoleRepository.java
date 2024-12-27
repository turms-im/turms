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

package im.turms.service.domain.user.repository;

import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.user.po.UserRole;

/**
 * @author James Chen
 */
@Repository
public class UserRoleRepository extends BaseRepository<UserRole, Long> {

    public UserRoleRepository(@Qualifier("userMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, UserRole.class);
    }

    public Mono<UpdateResult> updateUserRoles(
            Set<Long> groupIds,
            @Nullable String name,
            @Nullable Set<Long> creatableGroupTypeIds,
            @Nullable Integer ownedGroupLimit,
            @Nullable Integer ownedGroupLimitForEachGroupType,
            @Nullable Map<Long, Integer> groupTypeIdToLimit) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, groupIds);
        Update update = Update.newBuilder(5)
                .setIfNotNull(UserRole.Fields.NAME, name)
                .setIfNotNull(UserRole.Fields.CREATABLE_GROUP_TYPE_IDS, creatableGroupTypeIds)
                .setIfNotNull(UserRole.Fields.OWNED_GROUP_LIMIT, ownedGroupLimit)
                .setIfNotNull(UserRole.Fields.OWNED_GROUP_LIMIT_FOR_EACH_GROUP_TYPE,
                        ownedGroupLimitForEachGroupType)
                .setIfNotNull(UserRole.Fields.GROUP_TYPE_TO_LIMIT, groupTypeIdToLimit);
        return mongoClient.updateMany(entityClass, filter, update);
    }

}