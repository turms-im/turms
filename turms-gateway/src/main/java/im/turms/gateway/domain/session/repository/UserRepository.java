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

package im.turms.gateway.domain.session.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;

/**
 * @author James Chen
 */
@Repository
public class UserRepository extends BaseRepository<User, Long> {

    public UserRepository(
            @Autowired(
                    required = false) @Qualifier("userMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, User.class);
    }

    public Mono<User> findPassword(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(User.Fields.PASSWORD);
        return mongoClient.findOne(User.class, filter, options);
    }

    public Mono<Boolean> isActiveAndNotDeleted(Long userId) {
        Filter filter = Filter.newBuilder(3)
                .eq(DomainFieldName.ID, userId)
                .eq(User.Fields.IS_ACTIVE, true)
                .eq(User.Fields.DELETION_DATE, null);
        return mongoClient.exists(User.class, filter);
    }

}
