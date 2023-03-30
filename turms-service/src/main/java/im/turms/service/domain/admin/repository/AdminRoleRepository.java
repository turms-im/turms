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

package im.turms.service.domain.admin.repository;

import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;

/**
 * @author James Chen
 */
@Repository
public class AdminRoleRepository extends BaseRepository<AdminRole, Long> {

    public AdminRoleRepository(@Qualifier("adminMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, AdminRole.class);
        this.mongoClient = mongoClient;
    }

    public Mono<UpdateResult> updateAdminRoles(
            Set<Long> roleIds,
            String newName,
            @Nullable Set<AdminPermission> permissions,
            @Nullable Integer rank) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, roleIds);
        Update update = Update.newBuilder(3)
                .setIfNotNull(AdminRole.Fields.NAME, newName)
                .setIfNotNull(AdminRole.Fields.PERMISSIONS, permissions)
                .setIfNotNull(AdminRole.Fields.RANK, rank);
        return mongoClient.updateMany(entityClass, filter, update);
    }

    public Mono<Long> countAdminRoles(
            @Nullable Set<Long> ids,
            @Nullable Set<String> names,
            @Nullable Set<AdminPermission> includedPermissions,
            @Nullable Set<Integer> ranks) {
        Filter filter = Filter.newBuilder(4)
                .inIfNotNull(DomainFieldName.ID, ids)
                .inIfNotNull(AdminRole.Fields.NAME, names)
                .inIfNotNull(AdminRole.Fields.PERMISSIONS, includedPermissions)
                .inIfNotNull(AdminRole.Fields.RANK, ranks);
        return mongoClient.count(entityClass, filter);
    }

    public Flux<AdminRole> findAdminRoles(Set<Long> roleIds) {
        Filter query = Filter.newBuilder(1)
                .in(DomainFieldName.ID, roleIds);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(AdminRole.Fields.RANK);
        return mongoClient.findMany(entityClass, query, options);
    }

    public Flux<AdminRole> findAdminRoles(
            @Nullable Set<Long> roleIds,
            @Nullable Set<String> names,
            @Nullable Set<AdminPermission> includedPermissions,
            @Nullable Set<Integer> ranks,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(4)
                .inIfNotNull(DomainFieldName.ID, roleIds)
                .inIfNotNull(AdminRole.Fields.NAME, names)
                .inIfNotNull(AdminRole.Fields.PERMISSIONS, includedPermissions)
                .inIfNotNull(AdminRole.Fields.RANK, ranks);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Mono<Integer> findRank(Long roleId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, roleId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(AdminRole.Fields.RANK);
        return mongoClient.findOne(entityClass, filter, options)
                .map(AdminRole::getRank);
    }
}
