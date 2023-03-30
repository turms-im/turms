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

import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.conversation.po.GroupConversation;

/**
 * @author James Chen
 */
@Repository
public class GroupConversationRepository extends BaseRepository<GroupConversation, Long> {

    public GroupConversationRepository(
            @Qualifier("conversationMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, GroupConversation.class);
    }

    public Mono<Void> upsert(
            Long groupId,
            Long memberId,
            Date readDate,
            boolean allowMoveReadDateForward) {
        Filter filter = Filter.newBuilder(allowMoveReadDateForward
                ? 1
                : 2)
                .eq(DomainFieldName.ID, groupId);
        String fieldKey = GroupConversation.Fields.MEMBER_ID_TO_READ_DATE
                + "."
                + memberId;
        if (!allowMoveReadDateForward) {
            // Only update if no existing date or the existing date is before readDate
            filter.ltOrNull(fieldKey, readDate);
        }
        Update update = Update.newBuilder(1)
                .set(fieldKey, readDate);
        return mongoClient.upsert(entityClass, filter, update);
    }

    public Mono<Void> upsert(Long groupId, Collection<Long> memberIds, Date readDate) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        Update update = Update.newBuilder(memberIds.size());
        for (long memberId : memberIds) {
            String fieldKey = GroupConversation.Fields.MEMBER_ID_TO_READ_DATE
                    + "."
                    + memberId;
            // Ignore isAllowMoveReadDateForward()
            update.set(fieldKey, readDate);
        }
        return mongoClient.upsert(entityClass, filter, update);
    }

    public Mono<UpdateResult> deleteMemberConversations(
            Collection<Long> groupIds,
            Long memberId,
            ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, groupIds);
        Update update = Update.newBuilder(1)
                .unset(GroupConversation.Fields.MEMBER_ID_TO_READ_DATE
                        + "."
                        + memberId);
        return mongoClient.updateMany(session, entityClass, filter, update);
    }

}