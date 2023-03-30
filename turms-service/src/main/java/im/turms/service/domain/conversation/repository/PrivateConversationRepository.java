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

package im.turms.service.domain.conversation.repository;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.conversation.po.PrivateConversation;

/**
 * @author James Chen
 */
@Repository
public class PrivateConversationRepository
        extends BaseRepository<PrivateConversation, PrivateConversation.Key> {

    public PrivateConversationRepository(
            @Qualifier("conversationMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, PrivateConversation.class);
    }

    public Mono<Void> upsert(
            Set<PrivateConversation.Key> keys,
            Date readDate,
            boolean allowMoveReadDateForward) {
        Filter filter = Filter.newBuilder(allowMoveReadDateForward
                ? 1
                : 2)
                .in(DomainFieldName.ID, keys);
        if (!allowMoveReadDateForward) {
            // Only update if no existing date or the existing date is before readDate
            filter.ltOrNull(PrivateConversation.Fields.READ_DATE, readDate);
        }
        Update update = Update.newBuilder(1)
                .set(PrivateConversation.Fields.READ_DATE, readDate);
        return mongoClient.upsert(entityClass, filter, update);
    }

    public Mono<DeleteResult> deleteConversationsByOwnerIds(
            Set<Long> ownerIds,
            @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .in(PrivateConversation.Fields.ID_OWNER_ID, ownerIds);
        return mongoClient.deleteMany(session, entityClass, filter);
    }

    public Flux<PrivateConversation> findConversations(Collection<Long> ownerIds) {
        Filter filter = Filter.newBuilder(1)
                .in(PrivateConversation.Fields.ID_OWNER_ID, ownerIds);
        return mongoClient.findMany(entityClass, filter);
    }

}