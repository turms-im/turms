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

package im.turms.service.domain.message.repository;

import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.BitUtil;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.message.po.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author James Chen
 */
@Repository
public class MessageRepository extends BaseRepository<Message, Long> {

    public MessageRepository(@Qualifier("messageMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, Message.class);
    }

    public Mono<UpdateResult> updateMessages(Set<Long> messageIds,
                                             @Nullable Boolean isSystemMessage,
                                             @Nullable String text,
                                             @Nullable List<byte[]> records,
                                             @Nullable Integer burnAfter,
                                             @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, messageIds);
        Update update = Update.newBuilder(5)
                .setIfNotNull(Message.Fields.MODIFICATION_DATE, new Date())
                .setIfNotNull(Message.Fields.TEXT, text)
                .setIfNotNull(Message.Fields.RECORDS, records)
                .setIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, isSystemMessage)
                .setIfNotNull(Message.Fields.BURN_AFTER, burnAfter);
        return mongoClient.updateMany(session, entityClass, filter, update);
    }

    public Mono<UpdateResult> updateMessagesDeletionDate(@Nullable Set<Long> messageIds) {
        Filter filterMessage = Filter.newBuilder(1)
                .inIfNotNull(DomainFieldName.ID, messageIds);
        Update update = Update.newBuilder(1)
                .set(Message.Fields.DELETION_DATE, new Date());
        return mongoClient.updateMany(entityClass, filterMessage, update);
    }

    public Mono<Long> countMessages(
            @Nullable Set<Long> messageIds,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages,
            @Nullable Set<Long> senderIds,
            @Nullable Set<Long> targetIds,
            @Nullable DateRange deliveryDateRange,
            @Nullable DateRange deletionDateRange) {
        Filter filter = Filter.newBuilder(9)
                .eqIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .eqIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .inIfNotNull(Message.Fields.SENDER_ID, senderIds)
                .inIfNotNull(Message.Fields.TARGET_ID, targetIds)
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, deliveryDateRange)
                .addBetweenIfNotNull(Message.Fields.DELETION_DATE, deletionDateRange)
                .inIfNotNull(DomainFieldName.ID, messageIds);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<Long> countUsersWhoSentMessage(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        Filter filter = Filter.newBuilder(4)
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, dateRange)
                .eqIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .eqIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages);
        return mongoClient.countDistinct(
                entityClass,
                filter,
                Message.Fields.SENDER_ID);
    }

    public Mono<Long> countGroupsThatSentMessages(@Nullable DateRange dateRange) {
        Filter filter = Filter.newBuilder(3)
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, dateRange)
                .eq(Message.Fields.IS_GROUP_MESSAGE, true);
        return mongoClient.countDistinct(
                entityClass,
                filter,
                Message.Fields.TARGET_ID);
    }

    public Mono<Long> countSentMessages(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        Filter filter = Filter.newBuilder(4)
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, dateRange)
                .eqIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .eqIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<Date> findDeliveryDate(Long messageId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, messageId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(Message.Fields.DELIVERY_DATE);
        return mongoClient.findOne(entityClass, filter, options)
                .map(Message::getDeliveryDate);
    }

    public Flux<Long> findExpiredMessageIds(Date expirationDate) {
        Filter filter = Filter.newBuilder(1)
                .lt(Message.Fields.DELIVERY_DATE, expirationDate);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(DomainFieldName.ID);
        return mongoClient.findMany(entityClass, filter, options)
                .map(Message::getId);
    }

    public Mono<Message> findMessageGroupId(Long messageId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, messageId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(Message.Fields.TARGET_ID, Message.Fields.IS_GROUP_MESSAGE);
        return mongoClient.findOne(entityClass, filter, options);
    }

    public Flux<Message> findMessages(
            boolean useConversationId,
            boolean closeToDate,
            @Nullable Collection<Long> messageIds,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages,
            @Nullable Set<Long> senderIds,
            @Nullable Set<Long> targetIds,
            @Nullable DateRange deliveryDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        boolean enableConversationId = useConversationId
                && areGroupMessages != null
                && !CollectionUtil.isEmpty(senderIds)
                && !CollectionUtil.isEmpty(targetIds);
        Filter filter = Filter.newBuilder(enableConversationId ? 9 : 8)
                .eqIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .eqIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .inIfNotNull(Message.Fields.SENDER_ID, senderIds)
                .inIfNotNull(Message.Fields.TARGET_ID, targetIds)
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, deliveryDateRange);
        if (deletionDateRange == DateRange.NULL) {
            filter.eq(Message.Fields.DELETION_DATE, null);
        } else {
            filter.addBetweenIfNotNull(Message.Fields.DELETION_DATE, deletionDateRange);
        }
        if (enableConversationId) {
            int conversationIdSize = CollectionUtil.getSize(senderIds) * CollectionUtil.getSize(targetIds);
            // fast path
            if (conversationIdSize == 1) {
                filter.eq(Message.Fields.CONVERSATION_ID,
                        getConversationId(senderIds.iterator().next(), targetIds.iterator().next(), areGroupMessages));
            } else if (conversationIdSize > 0) {
                // slow path
                List<byte[]> conversationIds = new ArrayList<>(conversationIdSize);
                for (long senderId : senderIds) {
                    for (long targetId : targetIds) {
                        conversationIds.add(getConversationId(senderId, targetId, areGroupMessages));
                    }
                }
                filter.in(Message.Fields.CONVERSATION_ID, conversationIds);
            }
        }
        QueryOptions options = QueryOptions.newBuilder(closeToDate ? 3 : 2)
                .paginateIfNotNull(page, size);
        if (closeToDate) {
            boolean isAsc = deliveryDateRange != null && deliveryDateRange.start() != null;
            options.sort(isAsc, Message.Fields.DELIVERY_DATE);
        }
        filter.inIfNotNull(DomainFieldName.ID, messageIds);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Mono<Boolean> isMessageSender(Long messageId, Long senderId) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, messageId)
                .eq(Message.Fields.SENDER_ID, senderId);
        return mongoClient.exists(entityClass, filter);
    }

    public Mono<Boolean> isMessageRecipient(Long messageId, Long recipientId) {
        Filter filter = Filter.newBuilder(3)
                .eq(DomainFieldName.ID, messageId)
                .eq(Message.Fields.TARGET_ID, recipientId)
                .eq(Message.Fields.IS_GROUP_MESSAGE, false);
        return mongoClient.exists(entityClass, filter);
    }

    public static byte[] getConversationId(long id1, long id2, boolean isGroupMessage) {
        return id1 < id2
                ? toConversationId(id1, id2, isGroupMessage)
                : toConversationId(id2, id1, isGroupMessage);
    }

    public static byte[] toConversationId(long id1, long id2, boolean isGroupMessage) {
        // ID is always positive, meaning that the most significant bit of ID is always 0,
        // so we can use the bit to distinguish group messages and private messages
        // in 16 bytes without adding a new byte to avoid the collision of conversation ID
        byte b = (byte) (id2 >> 56);
        if (isGroupMessage) {
            b = BitUtil.setBit(b, 7);
        }
        return new byte[]{
                (byte) (id1 >> 56),
                (byte) (id1 >> 48),
                (byte) (id1 >> 40),
                (byte) (id1 >> 32),
                (byte) (id1 >> 24),
                (byte) (id1 >> 16),
                (byte) (id1 >> 8),
                (byte) id1,

                b,
                (byte) (id2 >> 48),
                (byte) (id2 >> 40),
                (byte) (id2 >> 32),
                (byte) (id2 >> 24),
                (byte) (id2 >> 16),
                (byte) (id2 >> 8),
                (byte) id2
        };
    }
}
