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

import im.turms.common.exception.TurmsBusinessException;
import im.turms.gateway.constant.DomainFieldName;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.manager.PasswordManager;
import im.turms.server.common.util.AssertUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
@Service
public class UserService {

    private final ReactiveMongoTemplate mongoTemplate;
    private final PasswordManager passwordManager;

    public UserService(
            @Qualifier("userMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            PasswordManager passwordManager) {
        this.mongoTemplate = mongoTemplate;
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
        Query query = new Query()
                .addCriteria(Criteria.where(DomainFieldName.ID_FIELD_NAME).is(userId));
        query.fields().include(User.Fields.PASSWORD);
        return mongoTemplate.findOne(query, User.class)
                .map(user -> passwordManager.matchesUserPassword(rawPassword, user.getPassword()))
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isActiveAndNotDeleted(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DomainFieldName.ID_FIELD_NAME).is(userId))
                .addCriteria(Criteria.where(User.Fields.IS_ACTIVE).is(true))
                .addCriteria(Criteria.where(User.Fields.DELETION_DATE).is(null));
        return mongoTemplate.exists(query, User.class);
    }

}

