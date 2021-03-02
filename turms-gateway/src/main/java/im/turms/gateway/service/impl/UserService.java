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

package im.turms.gateway.service.impl;

import im.turms.gateway.constant.DomainFieldName;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.manager.PasswordManager;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
@Service
public class UserService {

    private final TurmsMongoClient mongoClient;
    private final PasswordManager passwordManager;

    /**
     * @param mongoClient can be null if SessionProperties#enableAuthentication is false
     */
    public UserService(
            @Autowired(required = false) @Qualifier("userMongoClient") TurmsMongoClient mongoClient,
            PasswordManager passwordManager) {
        this.mongoClient = mongoClient;
        this.passwordManager = passwordManager;
    }

    public Mono<Boolean> authenticate(
            @NotNull Long userId,
            @Nullable String rawPassword) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(DomainFieldName.ID_FIELD_NAME, userId);
        QueryOptions options = QueryOptions.newBuilder()
                .include(User.Fields.PASSWORD);
        return mongoClient.findOne(User.class, filter, options)
                .map(user -> passwordManager.matchesUserPassword(rawPassword, user.getPassword()))
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isActiveAndNotDeleted(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(DomainFieldName.ID_FIELD_NAME, userId)
                .eq(User.Fields.IS_ACTIVE, true)
                .eq(User.Fields.DELETION_DATE, null);
        return mongoClient.exists(User.class, filter);
    }

}

