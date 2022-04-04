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

package im.turms.service.domain.message.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.primitives.Longs;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.TimeType;
import im.turms.server.common.infra.property.env.service.business.message.MessageProperties;
import im.turms.server.common.infra.property.env.service.business.message.SequenceIdProperties;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.operation.OperationResultConvertor;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
import im.turms.service.domain.common.permission.ServicePermission;
import im.turms.service.domain.conversation.service.ConversationService;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.message.bo.BuiltinSystemMessageType;
import im.turms.service.domain.message.po.Message;
import im.turms.service.domain.message.repository.MessageRepository;
import im.turms.service.domain.observation.service.MetricsService;
import im.turms.service.domain.user.service.UserService;
import im.turms.service.infra.plugin.extension.ExpiredMessageDeletionNotifier;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.storage.mongo.OperationResultConst;
import io.micrometer.core.instrument.Counter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static im.turms.server.common.access.common.ResponseStatusCode.ILLEGAL_ARGUMENT;
import static im.turms.server.common.access.common.ResponseStatusCode.MESSAGE_RECALL_TIMEOUT;
import static im.turms.server.common.access.common.ResponseStatusCode.NOT_SENDER_TO_UPDATE_MESSAGE;
import static im.turms.server.common.access.common.ResponseStatusCode.OK;
import static im.turms.server.common.access.common.ResponseStatusCode.RECALLING_MESSAGE_IS_DISABLED;
import static im.turms.server.common.access.common.ResponseStatusCode.RECALL_NON_EXISTING_MESSAGE;
import static im.turms.server.common.access.common.ResponseStatusCode.UPDATING_MESSAGE_BY_SENDER_IS_DISABLED;
import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_REQUESTER_ID;
import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_REQUEST_ID;
import static im.turms.service.infra.metrics.MetricNameConst.SENT_MESSAGES_COUNTER;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private static final byte[] GROUP_CONVERSATION_SEQUENCE_ID_PREFIX = {'g', 'i'};
    private static final byte[] PRIVATE_CONVERSATION_SEQUENCE_ID_PREFIX = {'p', 'i'};

    private final MessageRepository messageRepository;
    @Nullable
    private final TurmsRedisClientManager redisClientManager;
    private final Node node;
    private final ConversationService conversationService;
    private final OutboundMessageService outboundMessageService;
    private final GroupMemberService groupMemberService;
    private final UserService userService;
    private final PluginManager pluginManager;

    private final boolean useConversationId;

    private final boolean useSequenceIdForGroupConversation;
    private final boolean useSequenceIdForPrivateConversation;

    @Getter
    private TimeType timeType;
    private final Cache<Long, Message> sentMessageCache;

    private final Counter sentMessageCounter;

    @Autowired
    public MessageService(
            MessageRepository messageRepository,
            @Nullable @Autowired(required = false) @Qualifier("sequenceIdRedisClientManager")
                    TurmsRedisClientManager sequenceIdRedisClientManager,

            Node node,

            TurmsPropertiesManager turmsPropertiesManager,
            ConversationService conversationService,
            GroupMemberService groupMemberService,
            UserService userService,
            OutboundMessageService outboundMessageService,
            MetricsService metricsService,

            PluginManager pluginManager,
            TaskManager taskManager) {
        this.messageRepository = messageRepository;
        this.redisClientManager = sequenceIdRedisClientManager;
        this.node = node;
        this.conversationService = conversationService;
        this.groupMemberService = groupMemberService;
        this.userService = userService;
        this.outboundMessageService = outboundMessageService;
        this.pluginManager = pluginManager;

        MessageProperties messageProperties = node.getSharedProperties().getService().getMessage();
        useConversationId = messageProperties.isUseConversationId();
        SequenceIdProperties sequenceIdProperties = messageProperties.getSequenceId();
        useSequenceIdForGroupConversation = sequenceIdProperties.isUseSequenceIdForGroupConversation();
        useSequenceIdForPrivateConversation = sequenceIdProperties.isUseSequenceIdForPrivateConversation();
        timeType = messageProperties.getTimeType();
        MessageProperties.CacheProperties cacheProperties = turmsPropertiesManager.getLocalProperties().getService().getMessage().getCache();
        int relayedMessageCacheMaxSize = cacheProperties.getSentMessageCacheMaxSize();
        if (relayedMessageCacheMaxSize > 0 && turmsPropertiesManager.getLocalProperties().getService().getMessage().isPersistMessage()) {
            this.sentMessageCache = Caffeine
                    .newBuilder()
                    .maximumSize(relayedMessageCacheMaxSize)
                    .expireAfterWrite(Duration.ofSeconds(cacheProperties.getSentMessageExpireAfter()))
                    .build();
        } else {
            sentMessageCache = null;
        }
        sentMessageCounter = metricsService.getRegistry().counter(SENT_MESSAGES_COUNTER);
        node.addPropertiesChangeListener(properties -> timeType = properties.getService().getMessage().getTimeType());
        // Set up the checker for expired messages join requests
        taskManager.reschedule(
                "expiredMessagesCleanup",
                turmsPropertiesManager.getLocalProperties().getService().getMessage().getExpiredMessagesCleanupCron(),
                () -> {
                    if (node.isLocalNodeLeader()) {
                        int retentionPeriodHours = node.getSharedProperties()
                                .getService()
                                .getMessage()
                                .getMessageRetentionPeriodHours();
                        if (retentionPeriodHours > 0) {
                            deleteExpiredMessages(retentionPeriodHours)
                                    .subscribe(null, t -> LOGGER.error("Caught an error while deleting expired messages", t));
                        }
                    }
                });
    }

    public Mono<Boolean> isMessageSentByUser(@NotNull Long messageId, @NotNull Long senderId) {
        try {
            Validator.notNull(messageId, "messageId");
            Validator.notNull(senderId, "senderId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null) {
                return Mono.just(message.getSenderId().equals(senderId));
            }
        }
        return messageRepository.isMessageSender(messageId, senderId);
    }

    public Mono<Boolean> isMessageRecipient(@NotNull Long messageId, @NotNull Long recipientId) {
        try {
            Validator.notNull(messageId, "messageId");
            Validator.notNull(recipientId, "recipientId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null && !message.getIsGroupMessage()) {
                return Mono.just(message.getTargetId().equals(recipientId));
            }
        }
        return messageRepository.isMessageRecipient(messageId, recipientId);
    }

    public Mono<Boolean> isMessageRecipientOrSender(@NotNull Long messageId, @NotNull Long userId) {
        return isMessageRecipient(messageId, userId)
                .flatMap(isSentToUser -> isSentToUser
                        ? Mono.just(true)
                        : isMessageSentByUser(messageId, userId));
    }

    public Mono<ServicePermission> isMessageRecallable(@NotNull Long messageId) {
        try {
            Validator.notNull(messageId, "messageId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<Date> deliveryDateMono = null;
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null) {
                Date deliveryDate = message.getDeliveryDate();
                if (deliveryDate != null) {
                    deliveryDateMono = Mono.just(deliveryDate);
                }
            }
        }
        if (deliveryDateMono == null) {
            deliveryDateMono = messageRepository.findDeliveryDate(messageId);
        }
        return deliveryDateMono
                .map(deliveryDate -> {
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
                senderId == null ? null : Set.of(senderId),
                targetId == null ? null : Set.of(targetId),
                deliveryDateRange,
                deletionDateRange,
                page,
                size);
    }

    public Mono<Message> queryMessage(@NotNull Long messageId) {
        try {
            Validator.notNull(messageId, "messageId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return messageRepository.findById(messageId);
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
        return messageRepository.findMessages(useConversationId, closeToDate,
                messageIds, areGroupMessages, areSystemMessages,
                senderIds,
                targetIds,
                deliveryDateRange,
                deletionDateRange,
                page,
                size);
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
            @Nullable Long referenceId,
            @Nullable Long preMessageId) {
        MessageProperties messageProperties = node.getSharedProperties().getService().getMessage();
        try {
            Validator.notNull(senderId, "senderId");
            Validator.notNull(targetId, "targetId");
            Validator.notNull(isGroupMessage, "isGroupMessage");
            Validator.notNull(isSystemMessage, "isSystemMessage");
            Validator.maxLength(text, "text", messageProperties.getMaxTextLimit());
            validRecordsLength(records);
            Validator.min(burnAfter, "burnAfter", 0);
            Validator.pastOrPresent(deliveryDate, "deliveryDate");
            Validator.pastOrPresent(recallDate, "recallDate");
            Validator.before(deliveryDate, recallDate, "deliveryDate", "recallDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (timeType == TimeType.LOCAL_SERVER_TIME || deliveryDate == null) {
            deliveryDate = new Date();
        }
        if (messageId == null) {
            messageId = node.nextLargeGapId(ServiceType.MESSAGE);
        }
        if (!messageProperties.isPersistRecord()) {
            records = null;
        }
        if (!messageProperties.isPersistPreMessageId()) {
            preMessageId = null;
        }
        Mono<Long> sequenceId = null;
        if (isGroupMessage) {
            if (useSequenceIdForGroupConversation) {
                sequenceId = fetchSequenceId(true, targetId);
            }
        } else if (useSequenceIdForPrivateConversation) {
            sequenceId = fetchSequenceId(false, targetId);
        }
        byte[] conversationId = messageRepository.getConversationId(senderId, targetId, isGroupMessage);
        Mono<Message> saveMessage;
        if (sequenceId == null) {
            Message message = new Message(
                    messageId,
                    conversationId,
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
                    referenceId,
                    null,
                    preMessageId);
            saveMessage = messageRepository.insert(message)
                    .thenReturn(message);
        } else {
            Long finalMessageId = messageId;
            Date finalDeliveryDate = deliveryDate;
            List<byte[]> finalRecords = records;
            Long finalPreMessageId = preMessageId;
            saveMessage = sequenceId
                    .flatMap(seqId -> {
                        Message message = new Message(
                                finalMessageId,
                                conversationId,
                                isGroupMessage,
                                isSystemMessage,
                                finalDeliveryDate,
                                null,
                                null,
                                null,
                                text,
                                senderId,
                                targetId,
                                finalRecords,
                                burnAfter,
                                referenceId,
                                seqId.intValue(),
                                finalPreMessageId);
                        return messageRepository.insert(message)
                                .thenReturn(message);
                    });
        }
        if (node.getSharedProperties().getService().getConversation().getReadReceipt().isUpdateReadDateAfterMessageSent()) {
            Mono<Void> upsertConversation = isGroupMessage
                    ? conversationService.upsertGroupConversationReadDate(targetId, senderId, deliveryDate)
                    : conversationService.upsertPrivateConversationReadDate(senderId, targetId, deliveryDate);
            return saveMessage
                    .doOnNext(ignored -> upsertConversation
                            .subscribe(null, t -> LOGGER.error("Caught an error while upserting the {} conversation: {}",
                                    isGroupMessage ? "group" : "private",
                                    targetId,
                                    t)));
        }
        return saveMessage;
    }

    public Flux<Long> queryExpiredMessageIds(@NotNull Integer retentionPeriodHours) {
        try {
            Validator.notNull(retentionPeriodHours, "retentionPeriodHours");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        Date expirationDate = DateUtil.addHours(System.currentTimeMillis(), -retentionPeriodHours);
        return messageRepository.findExpiredMessageIds(expirationDate);
    }

    public Mono<Void> deleteExpiredMessages(@NotNull Integer retentionPeriodHours) {
        return queryExpiredMessageIds(retentionPeriodHours)
                .collectList()
                .flatMap(expiredMessageIds -> {
                    if (expiredMessageIds.isEmpty()) {
                        return Mono.empty();
                    }
                    Mono<List<Long>> messageIdsToDeleteMono = Mono.just(expiredMessageIds);
                    if (pluginManager.hasRunningExtensions(ExpiredMessageDeletionNotifier.class)) {
                        messageIdsToDeleteMono = messageRepository.findByIds(expiredMessageIds)
                                .collectList()
                                .flatMap(messages -> pluginManager.invokeExtensionPointsSequentially(
                                        ExpiredMessageDeletionNotifier.class,
                                        "getMessagesToDelete",
                                        messages,
                                        (notifier, pre) -> pre.flatMap(notifier::getMessagesToDelete)))
                                .map(messages -> {
                                    List<Long> messageIds = new ArrayList<>(messages.size());
                                    for (Message message : messages) {
                                        messageIds.add(message.getId());
                                    }
                                    return messageIds;
                                });
                    }
                    return messageIdsToDeleteMono
                            .flatMap(messageIds -> messageRepository.deleteByIds(messageIds).then());
                });
    }

    public Mono<DeleteResult> deleteMessages(
            @Nullable Set<Long> messageIds,
            @Nullable Boolean deleteLogically) {
        if (deleteLogically == null) {
            deleteLogically = node.getSharedProperties()
                    .getService().getMessage()
                    .isDeleteMessageLogicallyByDefault();
        }
        if (deleteLogically) {
            return messageRepository.updateMessagesDeletionDate(messageIds)
                    .map(OperationResultConvertor::update2delete);
        }
        return messageRepository.deleteByIds(messageIds);
    }

    public Mono<UpdateResult> updateMessages(
            @NotEmpty Set<Long> messageIds,
            @Nullable Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date recallDate,
            @Nullable ClientSession session) {
        try {
            Validator.notEmpty(messageIds, "messageIds");
            Validator.maxLength(text, "text", node.getSharedProperties().getService().getMessage().getMaxTextLimit());
            Validator.min(burnAfter, "burnAfter", 0);
            Validator.pastOrPresent(recallDate, "recallDate");
            validRecordsLength(records);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(isSystemMessage, text, records, burnAfter, recallDate)) {
            return Mono.just(OperationResultConst.ACKNOWLEDGED_UPDATE_RESULT);
        }
        if (recallDate == null) {
            return messageRepository.updateMessages(messageIds, isSystemMessage, text, records, burnAfter, session);
        }
        return messageRepository.findByIds(messageIds)
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
                            null,
                            null);
                })
                .collect(CollectorUtil.toList(messageIds.size()))
                .flatMap(messageMonos -> {
                    int size = messageMonos.size();
                    return Mono.whenDelayError(messageMonos)
                            .thenReturn(UpdateResult.acknowledged(size, (long) size, null));
                });
    }

    public Mono<UpdateResult> updateMessage(
            @NotNull Long messageId,
            @Nullable Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date recallDate,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(messageId, "messageId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return updateMessages(Set.of(messageId), isSystemMessage, text, records, burnAfter, recallDate, session);
    }

    public Mono<Long> countMessages(
            @Nullable Set<Long> messageIds,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages,
            @Nullable Set<Long> senderIds,
            @Nullable Set<Long> targetIds,
            @Nullable DateRange deliveryDateRange,
            @Nullable DateRange deletionDateRange) {
        return messageRepository.countMessages(messageIds,
                areGroupMessages,
                areSystemMessages,
                senderIds,
                targetIds,
                deliveryDateRange,
                deletionDateRange);
    }

    public Mono<Long> countUsersWhoSentMessage(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        return messageRepository.countUsersWhoSentMessage(dateRange, areGroupMessages, areSystemMessages);
    }

    public Mono<Long> countGroupsThatSentMessages(@Nullable DateRange dateRange) {
        return messageRepository.countGroupsThatSentMessages(dateRange);
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
        return messageRepository.countSentMessages(dateRange, areGroupMessages, areSystemMessages);
    }

    public Mono<Long> countSentMessagesOnAverage(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        return countSentMessages(dateRange, areGroupMessages, areSystemMessages)
                .flatMap(totalDeliveredMessages -> {
                    if (totalDeliveredMessages == 0) {
                        return Mono.just(0L);
                    }
                    return countUsersWhoSentMessage(dateRange, areGroupMessages, areSystemMessages)
                            .map(totalUsers -> totalUsers == 0
                                    ? Long.MAX_VALUE
                                    : totalDeliveredMessages / totalUsers);
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
        boolean updateMessageContent = text != null || !CollectionUtils.isEmpty(records);
        if (!updateMessageContent && recallDate == null) {
            return Mono.empty();
        }
        if (recallDate != null && !node.getSharedProperties()
                .getService()
                .getMessage()
                .isAllowRecallMessage()) {
            return Mono.error(ResponseException.get(RECALLING_MESSAGE_IS_DISABLED));
        }
        if (updateMessageContent && !node.getSharedProperties()
                .getService()
                .getMessage().isAllowEditMessageBySender()) {
            return Mono.error(ResponseException.get(UPDATING_MESSAGE_BY_SENDER_IS_DISABLED));
        }
        return isMessageSentByUser(messageId, requesterId)
                .flatMap(isSentByUser -> {
                    if (!isSentByUser) {
                        return Mono.error(ResponseException.get(NOT_SENDER_TO_UPDATE_MESSAGE));
                    }
                    return recallDate == null
                            ? updateMessage(messageId, null, text, records, null, null, null)
                            : updateMessageRecallDate(messageId, text, records, recallDate);
                });
    }

    public Flux<Long> queryMessageRecipients(@NotNull Long messageId) {
        try {
            Validator.notNull(messageId, "messageId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return messageRepository.findMessageGroupId(messageId)
                .flatMapMany(message -> message.getIsGroupMessage()
                        ? groupMemberService.queryGroupMemberIds(message.groupId())
                        : Mono.just(message.getTargetId()));
    }

    // message - recipientIds
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
            @Nullable Long referenceId,
            @Nullable Long preMessageId) {
        try {
            Validator.maxLength(text, "text", node.getSharedProperties().getService().getMessage().getMaxTextLimit());
            validRecordsLength(records);
            Validator.pastOrPresent(deliveryDate, "deliveryDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userService.isAllowedToSendMessageToTarget(isGroupMessage, isSystemMessage, senderId, targetId)
                .flatMap(permission -> {
                    ResponseStatusCode code = permission.code();
                    if (code != OK) {
                        return Mono.error(ResponseException.get(code, permission.reason()));
                    }
                    Mono<Set<Long>> recipientIdsMono = isGroupMessage
                            ? groupMemberService.findMemberIdsByGroupId(targetId).collect(Collectors.toSet())
                            : Mono.just(Set.of(targetId));
                    return recipientIdsMono.flatMap(recipientIds -> {
                        if (!node.getSharedProperties().getService().getMessage().isPersistMessage()) {
                            return recipientIds.isEmpty()
                                    ? Mono.empty()
                                    : Mono.just(Pair.of(null, recipientIds));
                        }
                        Mono<Message> saveMono = saveMessage(messageId, senderId, targetId, isGroupMessage,
                                isSystemMessage, text, records, burnAfter, deliveryDate, null, referenceId, preMessageId);
                        return saveMono.map(message -> {
                            if (message.getId() != null && sentMessageCache != null) {
                                cacheSentMessage(message);
                            }
                            return Pair.of(message, recipientIds);
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
                        node.nextLargeGapId(ServiceType.MESSAGE),
                        requesterId,
                        targetId,
                        isGroupMessage,
                        isSystemMessage,
                        message.getText(),
                        message.getRecords(),
                        message.getBurnAfter(),
                        message.getDeliveryDate(),
                        referenceId,
                        null));
    }

    public Mono<Void> authAndSaveAndSendMessage(
            boolean send,
            @Nullable Long messageId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable Long senderId,
            @NotNull Long targetId,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable Long referenceId,
            @Nullable Long preMessageId) {
        try {
            Validator.notNull(isGroupMessage, "isGroupMessage");
            Validator.notNull(isSystemMessage, "isSystemMessage");
            Validator.notNull(targetId, "targetId");
            Validator.min(burnAfter, "burnAfter", 0);
            Validator.throwIfAllFalsy("text and records cannot be both null", text, records);
            Validator.maxLength(text, "text", node.getSharedProperties().getService().getMessage().getMaxTextLimit());
            validRecordsLength(records);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (senderId == null) {
            if (isSystemMessage) {
                senderId = ADMIN_REQUESTER_ID;
            } else {
                return Mono.error(ResponseException.get(ILLEGAL_ARGUMENT, "senderId must not be null for user messages"));
            }
        }
        Date deliveryDate = new Date();
        Mono<Pair<Message, Set<Long>>> saveMono = referenceId == null
                ? authAndSaveMessage(messageId, senderId, targetId, isGroupMessage, isSystemMessage,
                text, records, burnAfter, deliveryDate, null, preMessageId)
                : authAndCloneAndSaveMessage(senderId, referenceId, isGroupMessage, isSystemMessage, targetId);
        return saveMono
                .doOnNext(pair -> {
                    Message message = pair.getLeft();
                    sentMessageCounter.increment();
                    if (message != null && message.getId() != null && sentMessageCache != null) {
                        cacheSentMessage(message);
                    }
                    if (send) {
                        // No need to let the client wait to send notifications to recipients
                        sendMessage(message, pair.getRight())
                                .subscribe(null, t -> LOGGER.error("Failed to send message", t));
                    }
                })
                .then();
    }

    private Mono<Boolean> sendMessage(@NotNull Message message, @NotNull Set<Long> recipientIds) {
        TurmsRequest request = TurmsRequest
                .newBuilder()
                .setCreateMessageRequest(ProtoModelConvertor.message2createMessageRequest(message))
                .build();
        TurmsNotification notification = TurmsNotification
                .newBuilder()
                .setRelayedRequest(request)
                .setRequestId(ADMIN_REQUEST_ID)
                .build();
        if (node.getSharedProperties().getService().getMessage().isSendMessageToOtherSenderOnlineDevices()) {
            recipientIds = CollectionUtil.add(recipientIds, message.getSenderId());
        }
        return outboundMessageService.forwardNotification(
                notification,
                recipientIds);
    }

    private void cacheSentMessage(@NotNull Message message) {
        sentMessageCache.put(message.getId(), new Message(
                message.getId(),
                null,
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
                null,
                null,
                null));
    }

    private Mono<UpdateResult> updateMessageRecallDate(@NotNull Long messageId,
                                                       String text,
                                                       List<byte[]> records,
                                                       @PastOrPresent Date recallDate) {
        return isMessageRecallable(messageId)
                .flatMap(permission -> {
                    ResponseStatusCode code = permission.code();
                    if (code != OK) {
                        return Mono.error(ResponseException.get(code, permission.reason()));
                    }
                    return updateMessage(messageId, null, text, records, null, recallDate, null);
                });
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
                    throw ResponseException
                            .get(ILLEGAL_ARGUMENT, "The total size of records must be less than or equal to " + maxRecordsSize);
                }
            }
        }
    }

    // Sequence ID

    public Mono<Void> deleteSequenceIds(boolean isGroupConversation, Set<Long> targetIds) {
        if (redisClientManager == null) {
            return Mono.empty();
        }
        return redisClientManager.execute(targetIds, (client, keyList) -> {
            List<ByteBuf> keys = new ArrayList<>(keyList.size());
            for (Long targetId : keyList) {
                byte[] prefix = isGroupConversation
                        ? GROUP_CONVERSATION_SEQUENCE_ID_PREFIX
                        : PRIVATE_CONVERSATION_SEQUENCE_ID_PREFIX;
                ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(prefix.length + Long.BYTES)
                        .writeBytes(prefix)
                        .writeLong(targetId);
                keys.add(buffer);
            }
            return client.del(keys);
        });
    }

    private Mono<Long> fetchSequenceId(boolean isGroupConversation, Long targetId) {
        if (redisClientManager == null) {
            return Mono.empty();
        }
        byte[] prefix = isGroupConversation
                ? GROUP_CONVERSATION_SEQUENCE_ID_PREFIX
                : PRIVATE_CONVERSATION_SEQUENCE_ID_PREFIX;
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(prefix.length + Long.BYTES)
                .writeBytes(prefix)
                .writeLong(targetId);
        return redisClientManager.incr(targetId, buffer);
    }

    // conversation ID

}