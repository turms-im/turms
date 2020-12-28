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

package im.turms.turms.workflow.service.impl.message;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.primitives.Longs;
import com.google.protobuf.Int64Value;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dao.util.OperationResultUtil;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.manager.TrivialTaskManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.constant.TimeType;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.CollectorUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.bo.ServicePermission;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.plugin.extension.handler.ExpiredMessageAutoDeletionNotificationHandler;
import im.turms.turms.plugin.manager.TurmsPluginManager;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.message.Message;
import im.turms.turms.workflow.dao.util.AggregationUtil;
import im.turms.turms.workflow.service.impl.conversation.ConversationService;
import im.turms.turms.workflow.service.impl.group.GroupMemberService;
import im.turms.turms.workflow.service.impl.statistics.MetricsService;
import im.turms.turms.workflow.service.impl.user.UserService;
import io.micrometer.core.instrument.Counter;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static im.turms.server.common.constant.TurmsStatusCode.*;
import static im.turms.turms.constant.MetricsConstant.SENT_MESSAGES_COUNTER_NAME;

/**
 * @author James Chen
 */
@Service
@Log4j2
public class MessageService {

    private final ReactiveMongoTemplate mongoTemplate;
    private final Node node;
    private final ConversationService conversationService;
    private final OutboundMessageService outboundMessageService;
    private final GroupMemberService groupMemberService;
    private final UserService userService;
    private final TurmsPluginManager turmsPluginManager;
    private final boolean pluginEnabled;
    @Getter
    private final TimeType timeType;
    private final Cache<Long, Message> sentMessageCache;

    private final Counter sentMessageCounter;

    @Autowired
    public MessageService(
            @Qualifier("messageMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            ConversationService conversationService,
            GroupMemberService groupMemberService,
            UserService userService,
            OutboundMessageService outboundMessageService,
            TurmsPluginManager turmsPluginManager,
            TrivialTaskManager taskManager,
            MetricsService metricsService) {
        this.mongoTemplate = mongoTemplate;
        this.node = node;
        this.conversationService = conversationService;
        this.groupMemberService = groupMemberService;
        this.userService = userService;
        this.outboundMessageService = outboundMessageService;
        this.turmsPluginManager = turmsPluginManager;
        pluginEnabled = node.getSharedProperties().getPlugin().isEnabled();
        timeType = turmsPropertiesManager.getLocalProperties().getService().getMessage().getTimeType();
        int relayedMessageCacheMaxSize = turmsPropertiesManager.getLocalProperties().getService().getMessage().getSentMessageCacheMaxSize();
        if (relayedMessageCacheMaxSize > 0 && turmsPropertiesManager.getLocalProperties().getService().getMessage().isMessagePersistent()) {
            this.sentMessageCache = Caffeine
                    .newBuilder()
                    .maximumSize(relayedMessageCacheMaxSize)
                    .expireAfterWrite(Duration.ofSeconds(turmsPropertiesManager.getLocalProperties().getService().getMessage().getSentMessageExpireAfter()))
                    .build();
        } else {
            sentMessageCache = null;
        }

        sentMessageCounter = metricsService.getRegistry().counter(SENT_MESSAGES_COUNTER_NAME);
        // Set up the checker for expired messages join requests
        taskManager.reschedule(
                "messagesChecker",
                turmsPropertiesManager.getLocalProperties().getService().getMessage().getExpiredMessagesCheckerCron(),
                () -> {
                    if (node.isLocalNodeMaster()) {
                        int messagesTimeToLiveHours = node.getSharedProperties()
                                .getService()
                                .getMessage()
                                .getMessageTimeToLiveHours();
                        deleteExpiredMessagesAndStatuses(messagesTimeToLiveHours).subscribe();
                    }
                });
    }

    public Mono<Boolean> isMessageSentByUser(@NotNull Long messageId, @NotNull Long senderId) {
        try {
            AssertUtil.notNull(messageId, "messageId");
            AssertUtil.notNull(senderId, "senderId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null) {
                return Mono.just(message.getSenderId().equals(senderId));
            }
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(messageId))
                .addCriteria(Criteria.where(Message.Fields.SENDER_ID).is(senderId));
        return mongoTemplate.exists(query, Message.class, Message.COLLECTION_NAME);
    }

    public Mono<Boolean> isMessageRecipient(@NotNull Long messageId, @NotNull Long recipientId) {
        try {
            AssertUtil.notNull(messageId, "messageId");
            AssertUtil.notNull(recipientId, "recipientId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null && !message.getIsGroupMessage()) {
                return Mono.just(message.getTargetId().equals(recipientId));
            }
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(messageId))
                .addCriteria(Criteria.where(Message.Fields.TARGET_ID).is(recipientId))
                .addCriteria(Criteria.where(Message.Fields.IS_GROUP_MESSAGE).is(false));
        return mongoTemplate.exists(query, Message.class, Message.COLLECTION_NAME);
    }

    public Mono<Boolean> isMessageRecipientOrSender(@NotNull Long messageId, @NotNull Long userId) {
        return isMessageRecipient(messageId, userId)
                .flatMap(isSentToUser -> isSentToUser
                        ? Mono.just(true)
                        : isMessageSentByUser(messageId, userId));
    }

    public Mono<ServicePermission> isMessageRecallable(@NotNull Long messageId) {
        try {
            AssertUtil.notNull(messageId, "messageId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Mono<Message> messageMono = null;
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null) {
                messageMono = Mono.just(message);
            }
        }
        if (messageMono == null) {
            Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(messageId));
            query.fields().include(Message.Fields.DELIVERY_DATE);
            messageMono = mongoTemplate.findOne(query, Message.class, Message.COLLECTION_NAME);
        }
        return messageMono
                .map(message -> {
                    Date deliveryDate = message.getDeliveryDate();
                    long elapsedTime = (deliveryDate.getTime() - System.currentTimeMillis()) / 1000;
                    boolean isRecallable = elapsedTime < node.getSharedProperties()
                            .getService()
                            .getMessage()
                            .getAvailableRecallDurationSeconds();
                    return isRecallable
                            ? ServicePermission.OK
                            : ServicePermission.get(MESSAGE_RECALL_TIMEOUT);
                })
                .defaultIfEmpty(ServicePermission.get(RECALL_NON_EXISTING_MESSAGE));
    }

    public Flux<Message> authAndQueryCompleteMessages(
            boolean closeToDate,
            @Nullable Collection<Long> messageIds,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages,
            @Nullable Long senderId,
            @Nullable Long targetId,
            @Nullable DateRange deliveryDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        return queryMessages(
                closeToDate,
                messageIds,
                areGroupMessages,
                areSystemMessages,
                senderId != null ? Set.of(senderId) : null,
                targetId != null ? Set.of(targetId) : null,
                deliveryDateRange,
                deletionDateRange,
                page,
                size);
    }

    public Mono<Message> queryMessage(@NotNull Long messageId) {
        try {
            AssertUtil.notNull(messageId, "messageId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(messageId));
        return mongoTemplate.findOne(query, Message.class, Message.COLLECTION_NAME);
    }

    public Flux<Message> queryMessages(
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
        QueryBuilder builder = QueryBuilder.newBuilder()
                .addIsIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .addIsIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .addInIfNotNull(Message.Fields.SENDER_ID, senderIds)
                .addInIfNotNull(Message.Fields.TARGET_ID, targetIds)
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, deliveryDateRange)
                .addBetweenIfNotNull(Message.Fields.DELETION_DATE, deletionDateRange);
        Sort.Direction direction = null;
        if (closeToDate) {
            boolean isAsc = deliveryDateRange != null && deliveryDateRange.getStart() != null;
            direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        }
        builder.addInIfNotNull(DaoConstant.ID_FIELD_NAME, messageIds);
        Query query;
        if (direction != null) {
            query = builder.paginateIfNotNull(page, size, direction, Message.Fields.DELIVERY_DATE);
        } else {
            query = builder.paginateIfNotNull(page, size);
        }
        return mongoTemplate.find(query, Message.class, Message.COLLECTION_NAME);
    }

    public Mono<Message> saveMessage(
            @Nullable Long messageId,
            @NotNull Long senderId,
            @NotNull Long targetId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date deliveryDate,
            @Nullable @PastOrPresent Date recallDate,
            @Nullable Long referenceId) {
        try {
            AssertUtil.notNull(senderId, "senderId");
            AssertUtil.notNull(targetId, "targetId");
            AssertUtil.notNull(isGroupMessage, "isGroupMessage");
            AssertUtil.notNull(isSystemMessage, "isSystemMessage");
            AssertUtil.maxLength(text, "text", node.getSharedProperties().getService().getMessage().getMaxTextLimit());
            validRecordsLength(records);
            AssertUtil.min(burnAfter, "burnAfter", 0);
            AssertUtil.pastOrPresent(deliveryDate, "deliveryDate");
            AssertUtil.pastOrPresent(recallDate, "recallDate");
            AssertUtil.before(deliveryDate, recallDate, "deliveryDate", "recallDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (timeType == TimeType.LOCAL_SERVER_TIME || deliveryDate == null) {
            deliveryDate = new Date();
        }
        if (messageId == null) {
            messageId = node.nextId(ServiceType.MESSAGE);
        }
        if (!node.getSharedProperties().getService().getMessage().isRecordsPersistent()) {
            records = null;
        }
        Message message = new Message(
                messageId,
                isGroupMessage,
                isSystemMessage,
                deliveryDate,
                null,
                null,
                null,
                text,
                senderId,
                targetId,
                records,
                burnAfter,
                referenceId);
        Mono<Message> saveMessage = mongoTemplate.insert(message, Message.COLLECTION_NAME);
        if (node.getSharedProperties().getService().getConversation().getReadReceipt().isUpdateReadDateAfterMessageSent()) {
            Mono<Void> upsertConversation = isGroupMessage
                    ? conversationService.upsertGroupConversationReadDate(targetId, senderId, deliveryDate)
                    : conversationService.upsertPrivateConversationReadDate(senderId, targetId, deliveryDate);
            return saveMessage
                    .doOnNext(ignored -> upsertConversation.subscribe());
        } else {
            return saveMessage;
        }
    }

    public Flux<Long> queryExpiredMessageIds(@NotNull Integer timeToLiveHours) {
        try {
            AssertUtil.notNull(timeToLiveHours, "timeToLiveHours");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Date beforeDate = Date.from(Instant.now().minus(timeToLiveHours, ChronoUnit.HOURS));
        Query query = new Query()
                .addCriteria(Criteria.where(Message.Fields.DELIVERY_DATE).lt(beforeDate));
        query.fields().include(DaoConstant.ID_FIELD_NAME);
        return mongoTemplate.find(query, Message.class, Message.COLLECTION_NAME)
                .map(Message::getId);
    }

    public Mono<Void> deleteExpiredMessagesAndStatuses(@NotNull Integer timeToLiveHours) {
        return queryExpiredMessageIds(timeToLiveHours)
                .collectList()
                .flatMap(expiredMessageIds -> {
                    if (expiredMessageIds.isEmpty()) {
                        return Mono.empty();
                    } else {
                        Mono<List<Long>> messageIdsToDeleteMono = Mono.just(expiredMessageIds);
                        List<ExpiredMessageAutoDeletionNotificationHandler> handlerList = turmsPluginManager.getExpiredMessageAutoDeletionNotificationHandlerList();
                        if (pluginEnabled && !handlerList.isEmpty()) {
                            Query messagesQuery = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(expiredMessageIds));
                            messageIdsToDeleteMono = mongoTemplate.find(messagesQuery, Message.class, Message.COLLECTION_NAME)
                                    .collectList()
                                    .flatMap(messages -> {
                                        Mono<List<Message>> mono = Mono.just(messages);
                                        for (ExpiredMessageAutoDeletionNotificationHandler handler : handlerList) {
                                            mono = mono.flatMap(handler::getMessagesToDelete);
                                        }
                                        return mono;
                                    })
                                    .map(messages -> {
                                        List<Long> messageIds = new ArrayList<>(messages.size());
                                        for (Message message : messages) {
                                            messageIds.add(message.getId());
                                        }
                                        return messageIds;
                                    });
                        }
                        return messageIdsToDeleteMono
                                .flatMap(messageIds -> {
                                    Query messagesQuery = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(messageIds));
                                    return mongoTemplate.remove(messagesQuery, Message.class, Message.COLLECTION_NAME).then();
                                });
                    }
                });
    }

    public Mono<DeleteResult> deleteMessages(
            @Nullable Set<Long> messageIds,
            @Nullable Boolean deleteLogically) {
        Query queryMessage = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, messageIds)
                .buildQuery();
        if (deleteLogically == null) {
            deleteLogically = node.getSharedProperties()
                    .getService().getMessage()
                    .isDeleteMessageLogicallyByDefault();
        }
        if (deleteLogically) {
            Update update = new Update().set(Message.Fields.DELETION_DATE, new Date());
            return mongoTemplate.updateMulti(queryMessage, update, Message.class, Message.COLLECTION_NAME)
                    .map(OperationResultUtil::update2delete);
        } else {
            return mongoTemplate.remove(queryMessage, Message.class, Message.COLLECTION_NAME);
        }
    }

    public Mono<UpdateResult> updateMessages(
            @NotEmpty Set<Long> messageIds,
            @Nullable Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date recallDate,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notEmpty(messageIds, "messageIds");
            AssertUtil.maxLength(text, "text", node.getSharedProperties().getService().getMessage().getMaxTextLimit());
            AssertUtil.min(burnAfter, "burnAfter", 0);
            AssertUtil.pastOrPresent(recallDate, "recallDate");
            validRecordsLength(records);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(isSystemMessage, text, records, burnAfter, recallDate)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(messageIds));
        Update update = UpdateBuilder.newBuilder()
                .setIfNotNull(Message.Fields.MODIFICATION_DATE, new Date())
                .setIfNotNull(Message.Fields.TEXT, text)
                .setIfNotNull(Message.Fields.RECORDS, records)
                .setIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, isSystemMessage)
                .setIfNotNull(Message.Fields.BURN_AFTER, burnAfter)
                .setIfNotNull(Message.Fields.RECALL_DATE, recallDate)
                .build();
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        if (recallDate == null) {
            return mongoOperations.updateMulti(query, update, Message.class, Message.COLLECTION_NAME);
        } else {
            return mongoOperations.find(query, Message.class, Message.COLLECTION_NAME)
                    .map(message -> {
                        byte[] messageType = {BuiltinSystemMessageType.RECALL_MESSAGE};
                        byte[] messageId = Longs.toByteArray(message.getId());
                        return authAndSaveAndSendMessage(true,
                                null,
                                message.getIsGroupMessage(),
                                true,
                                null,
                                List.of(messageType, messageId),
                                null,
                                message.getTargetId(),
                                null,
                                null);
                    })
                    .collect(CollectorUtil.toList(messageIds.size()))
                    .flatMap(messageMonos -> {
                        int size = messageMonos.size();
                        return Mono.when(messageMonos)
                                .thenReturn(UpdateResult.acknowledged(size, (long) size, null));
                    });
        }
    }

    public Mono<UpdateResult> updateMessage(
            @NotNull Long messageId,
            @Nullable Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date recallDate,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(messageId, "messageId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return updateMessages(Set.of(messageId), isSystemMessage, text, records, burnAfter, recallDate, operations);
    }

    public Mono<Long> countMessages(
            @Nullable Set<Long> messageIds,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages,
            @Nullable Set<Long> senderIds,
            @Nullable Set<Long> targetIds,
            @Nullable DateRange deliveryDateRange,
            @Nullable DateRange deletionDateRange) {
        QueryBuilder builder = QueryBuilder.newBuilder()
                .addIsIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .addIsIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .addIsIfNotNull(Message.Fields.SENDER_ID, senderIds)
                .addIsIfNotNull(Message.Fields.TARGET_ID, targetIds)
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, deliveryDateRange)
                .addBetweenIfNotNull(Message.Fields.DELETION_DATE, deletionDateRange);
        Query query = builder.addInIfNotNull(DaoConstant.ID_FIELD_NAME, messageIds).buildQuery();
        return mongoTemplate.count(query, Message.class, Message.COLLECTION_NAME);
    }

    public Mono<Long> countUsersWhoSentMessage(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        Criteria criteria = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, dateRange)
                .addIsIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .addIsIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .buildCriteria();
        return AggregationUtil.countDistinct(
                mongoTemplate,
                criteria,
                Message.Fields.SENDER_ID,
                Message.class);
    }

    public Mono<Long> countGroupsThatSentMessages(@Nullable DateRange dateRange) {
        Criteria criteria = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, dateRange)
                .add(Criteria.where(Message.Fields.IS_GROUP_MESSAGE).is(true))
                .buildCriteria();
        return AggregationUtil.countDistinct(
                mongoTemplate,
                criteria,
                Message.Fields.TARGET_ID,
                Message.class);
    }

//    public Mono<Long> countUsersWhoAcknowledgedMessage(
//            @Nullable DateRange dateRange,
//            @Nullable Boolean areGroupMessage) {
//        Criteria criteria = QueryBuilder.newBuilder()
//                .addBetweenIfNotNull(MessageStatus.Fields.RECEPTION_DATE, dateRange)
//                .buildCriteria();
//        if (areGroupMessage != null) {
//            if (areGroupMessage) {
//                criteria.and(MessageStatus.Fields.GROUP_ID).ne(null);
//            } else {
//                criteria.and(MessageStatus.Fields.GROUP_ID).is(null);
//            }
//        }
//        return AggregationUtil.countDistinct(
//                mongoTemplate,
//                criteria,
//                MessageStatus.Fields.ID_RECIPIENT_ID,
//                MessageStatus.class);
//    }

    public Mono<Long> countSentMessages(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        Query query = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, dateRange)
                .addIsIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .addIsIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .buildQuery();
        return mongoTemplate.count(query, Message.class, Message.COLLECTION_NAME);
    }

    public Mono<Long> countSentMessagesOnAverage(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        return countSentMessages(dateRange, areGroupMessages, areSystemMessages)
                .flatMap(totalDeliveredMessages -> {
                    if (totalDeliveredMessages == 0) {
                        return Mono.just(0L);
                    } else {
                        return countUsersWhoSentMessage(dateRange, areGroupMessages, areSystemMessages)
                                .map(totalUsers -> {
                                    if (totalUsers == 0) {
                                        return Long.MAX_VALUE;
                                    } else {
                                        return totalDeliveredMessages / totalUsers;
                                    }
                                });
                    }
                });
    }

//    public Mono<Long> countAcknowledgedMessages(
//            @Nullable DateRange dateRange,
//            @Nullable Boolean areGroupMessages,
//            @Nullable Boolean areSystemMessages) {
//        Query query = QueryBuilder.newBuilder()
//                .addBetweenIfNotNull(MessageStatus.Fields.RECEPTION_DATE, dateRange)
//                .addIsIfNotNull(MessageStatus.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
//                .buildQuery();
//        if (areGroupMessages != null) {
//            if (areGroupMessages) {
//                query.addCriteria(Criteria.where(MessageStatus.Fields.GROUP_ID).ne(null));
//            } else {
//                query.addCriteria(Criteria.where(MessageStatus.Fields.GROUP_ID).is(null));
//            }
//        }
//        return mongoTemplate.count(query, MessageStatus.class, MessageStatus.COLLECTION_NAME);
//    }

//    public Mono<Long> countAcknowledgedMessagesOnAverage(
//            @Nullable DateRange dateRange,
//            @Nullable Boolean areGroupMessages,
//            @Nullable Boolean areSystemMessages) {
//        return countAcknowledgedMessages(dateRange, areGroupMessages, areSystemMessages)
//                .flatMap(totalAcknowledgedMessages -> {
//                    if (totalAcknowledgedMessages == 0) {
//                        return Mono.just(0L);
//                    } else {
//                        return countUsersWhoAcknowledgedMessage(dateRange, areGroupMessages)
//                                .map(totalUsers -> totalUsers == 0
//                                        ? Long.MAX_VALUE
//                                        : totalAcknowledgedMessages / totalUsers);
//                    }
//                });
//    }

    public Mono<UpdateResult> authAndUpdateMessage(
            @NotNull Long requesterId,
            @NotNull Long messageId,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @PastOrPresent Date recallDate) {
        boolean updateMessageContent = text != null || (records != null && !records.isEmpty());
        if (!updateMessageContent && recallDate == null) {
            return Mono.empty();
        }
        if (recallDate != null && !node.getSharedProperties()
                .getService()
                .getMessage()
                .isAllowRecallingMessage()) {
            return Mono.error(TurmsBusinessException.get(RECALLING_MESSAGE_IS_DISABLED));
        }
        if (updateMessageContent && !node.getSharedProperties()
                .getService()
                .getMessage().isAllowEditingMessageBySender()) {
            return Mono.error(TurmsBusinessException.get(UPDATING_MESSAGE_BY_SENDER_IS_DISABLED));
        }
        return isMessageSentByUser(messageId, requesterId)
                .flatMap(isSentByUser -> {
                    if (!isSentByUser) {
                        return Mono.error(TurmsBusinessException.get(NOT_SENDER_TO_UPDATE_MESSAGE));
                    }
                    if (recallDate != null) {
                        return isMessageRecallable(messageId)
                                .flatMap(permission -> {
                                    TurmsStatusCode code = permission.getCode();
                                    if (code != OK) {
                                        return Mono.error(TurmsBusinessException.get(code, permission.getReason()));
                                    }
                                    return updateMessage(messageId, null, text, records, null, recallDate, null);
                                });
                    } else {
                        return updateMessage(messageId, null, text, records, null, null, null);
                    }
                });
    }

    public Flux<Long> queryMessageRecipients(@NotNull Long messageId) {
        try {
            AssertUtil.notNull(messageId, "messageId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(messageId));
        query.fields()
                .include(Message.Fields.TARGET_ID)
                .include(Message.Fields.IS_GROUP_MESSAGE);
        return mongoTemplate.findOne(query, Message.class, Message.COLLECTION_NAME)
                .flatMapMany(message -> {
                    if (message.getIsGroupMessage()) {
                        return groupMemberService.queryGroupMemberIds(message.groupId());
                    } else {
                        return Mono.just(message.getTargetId());
                    }
                });
    }

    // message - recipientsIds
    public Mono<Pair<Message, Set<Long>>> authAndSaveMessage(
            @Nullable Long messageId,
            @NotNull Long senderId,
            @NotNull Long targetId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date deliveryDate,
            @Nullable Long referenceId) {
        try {
            AssertUtil.maxLength(text, "text", node.getSharedProperties().getService().getMessage().getMaxTextLimit());
            validRecordsLength(records);
            AssertUtil.pastOrPresent(deliveryDate, "deliveryDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return userService.isAllowedToSendMessageToTarget(isGroupMessage, isSystemMessage, senderId, targetId)
                .flatMap(permission -> {
                    TurmsStatusCode code = permission.getCode();
                    if (code != OK) {
                        return Mono.error(TurmsBusinessException.get(code, permission.getReason()));
                    }
                    Mono<Set<Long>> recipientIdsMono;
                    if (isGroupMessage) {
                        recipientIdsMono = groupMemberService.getMemberIdsByGroupId(targetId)
                                .collect(Collectors.toSet());
                    } else {
                        recipientIdsMono = Mono.just(Collections.singleton(targetId));
                    }
                    return recipientIdsMono.flatMap(recipientsIds -> {
                        if (!node.getSharedProperties().getService().getMessage().isMessagePersistent()) {
                            if (recipientsIds.isEmpty()) {
                                return Mono.empty();
                            } else {
                                return Mono.just(Pair.of(null, recipientsIds));
                            }
                        }
                        Mono<Message> saveMono = saveMessage(messageId, senderId, targetId, isGroupMessage,
                                isSystemMessage, text, records, burnAfter, deliveryDate, null, referenceId);
                        return saveMono.map(message -> {
                            if (message.getId() != null && sentMessageCache != null) {
                                cacheSentMessage(message);
                            }
                            return Pair.of(message, recipientsIds);
                        });
                    });
                });
    }

    /**
     * clone a new message rather than using its ID as a reference
     */
    public Mono<Pair<Message, Set<Long>>> authAndCloneAndSaveMessage(
            @NotNull Long requesterId,
            @NotNull Long referenceId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @NotNull Long targetId) {
        return queryMessage(referenceId)
                .flatMap(message -> authAndSaveMessage(
                        node.nextId(ServiceType.MESSAGE),
                        requesterId,
                        targetId,
                        isGroupMessage,
                        isSystemMessage,
                        message.getText(),
                        message.getRecords(),
                        message.getBurnAfter(),
                        message.getDeliveryDate(),
                        referenceId));
    }

    /**
     * @return true if no recipient, or at least one recipient has received the notification
     */
    public Mono<Boolean> authAndSaveAndSendMessage(
            boolean send,
            @Nullable Long messageId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable Long senderId,
            @NotNull Long targetId,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable Long referenceId) {
        try {
            AssertUtil.notNull(isGroupMessage, "isGroupMessage");
            AssertUtil.notNull(isSystemMessage, "isSystemMessage");
            AssertUtil.notNull(targetId, "targetId");
            AssertUtil.min(burnAfter, "burnAfter", 0);
            AssertUtil.throwIfAllFalsy("text and records cannot be both null", text, records);
            AssertUtil.maxLength(text, "text", node.getSharedProperties().getService().getMessage().getMaxTextLimit());
            validRecordsLength(records);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (senderId == null) {
            if (isSystemMessage) {
                senderId = DaoConstant.ADMIN_REQUESTER_ID;
            } else {
                return Mono.error(TurmsBusinessException.get(ILLEGAL_ARGUMENT, "senderId must not be null for user messages"));
            }
        }
        Date deliveryDate = new Date();
        Mono<Pair<Message, Set<Long>>> saveMono = referenceId != null
                ? authAndCloneAndSaveMessage(senderId, referenceId, isGroupMessage, isSystemMessage, targetId)
                : authAndSaveMessage(messageId, senderId, targetId, isGroupMessage, isSystemMessage, text, records, burnAfter, deliveryDate, null);
        return saveMono
                .flatMap(pair -> {
                    Message message = pair.getLeft();
                    sentMessageCounter.increment();
                    if (message != null && message.getId() != null && sentMessageCache != null) {
                        cacheSentMessage(message);
                    }
                    if (send) {
                        return sendMessage(message, pair.getRight());
                    } else {
                        return Mono.just(true);
                    }
                });
    }

    private Mono<Boolean> sendMessage(@NotNull Message message, @NotNull Set<Long> recipientIds) {
        TurmsRequest request = TurmsRequest
                .newBuilder()
                .setCreateMessageRequest(ProtoUtil.message2createMessageRequest(message))
                .build();
        TurmsNotification notification = TurmsNotification
                .newBuilder()
                .setRelayedRequest(request)
                .setRequestId(Int64Value.newBuilder().setValue(DaoConstant.ADMIN_REQUEST_ID).build())
                .build();
        if (node.getSharedProperties().getService().getMessage().isSendMessageToOtherSenderOnlineDevices()) {
            recipientIds.add(message.getSenderId());
        }
        return outboundMessageService.forwardNotification(
                notification,
                recipientIds);
    }

    private void cacheSentMessage(@NotNull Message message) {
        sentMessageCache.put(message.getId(), new Message(
                message.getId(),
                message.getIsGroupMessage(),
                message.getIsSystemMessage(),
                message.getDeliveryDate(),
                null,
                null,
                null,
                null,
                message.getSenderId(),
                message.getTargetId(),
                null,
                null,
                null));
    }

    private void validRecordsLength(List<byte[]> records) {
        if (records != null) {
            int maxRecordsSize = node.getSharedProperties()
                    .getService()
                    .getMessage()
                    .getMaxRecordsSizeBytes();
            if (maxRecordsSize > -1) {
                int count = 0;
                for (byte[] record : records) {
                    count += record.length;
                }
                if (count > maxRecordsSize) {
                    throw TurmsBusinessException.get(ILLEGAL_ARGUMENT, "The total size of records must be less than or equal to " + maxRecordsSize);
                }
            }
        }
    }

    private static class BuiltinSystemMessageType {
        /**
         * NORMAL is only used as a placeholder and won't be set for normal messages
         * because the client implementations consider a system message as a normal message
         * if no message type specified
         */
        private static final int NORMAL = 0;
        private static final int RECALL_MESSAGE = 1;
    }

}