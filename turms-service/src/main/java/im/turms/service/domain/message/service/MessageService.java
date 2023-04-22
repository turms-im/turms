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

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import io.micrometer.core.instrument.Counter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.UserSessionId;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.lang.BoolUtil;
import im.turms.server.common.infra.lang.LongUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.net.InetAddressUtil;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.TimeType;
import im.turms.server.common.infra.property.env.service.business.message.MessageProperties;
import im.turms.server.common.infra.property.env.service.business.message.SequenceIdProperties;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.recycler.SetRecycler;
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
import im.turms.service.domain.message.bo.MessageAndRecipientIds;
import im.turms.service.domain.message.po.Message;
import im.turms.service.domain.message.repository.MessageRepository;
import im.turms.service.domain.observation.service.MetricsService;
import im.turms.service.domain.user.service.UserService;
import im.turms.service.infra.plugin.extension.ExpiredMessageDeletionNotifier;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

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
    private static final Method GET_MESSAGES_TO_DELETE_METHOD;

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

    private int availableRecallDurationMillis;
    private int defaultAvailableMessagesNumberWithTotal;
    private int maxTextLimit;
    private int maxRecordsSize;
    private int messageRetentionPeriodHours;
    private boolean persistPreMessageId;
    private boolean persistRecord;
    private boolean persistMessage;
    private boolean persistSenderIp;
    private boolean updateReadDateAfterMessageSent;
    private boolean deleteMessageLogicallyByDefault;
    private boolean allowRecallMessage;
    private boolean allowEditMessageBySender;
    private boolean sendMessageToOtherSenderOnlineDevices;
    @Getter
    private TimeType timeType;
    private DateRange recalledMessageQueryDateRange;

    private final Cache<Long, Message> sentMessageCache;

    private final Counter sentMessageCounter;

    static {
        try {
            GET_MESSAGES_TO_DELETE_METHOD = ExpiredMessageDeletionNotifier.class
                    .getDeclaredMethod("getMessagesToDelete", List.class);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleInternalChangeException(e);
        }
    }

    @Autowired
    public MessageService(
            MessageRepository messageRepository,
            @Nullable @Autowired(
                    required = false) @Qualifier("sequenceIdRedisClientManager") TurmsRedisClientManager sequenceIdRedisClientManager,

            Node node,

            TurmsPropertiesManager propertiesManager,
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

        TurmsProperties globalProperties = propertiesManager.getGlobalProperties();
        MessageProperties messageProperties = globalProperties.getService()
                .getMessage();
        useConversationId = messageProperties.isUseConversationId();
        SequenceIdProperties sequenceIdProperties = messageProperties.getSequenceId();
        useSequenceIdForGroupConversation =
                sequenceIdProperties.isUseSequenceIdForGroupConversation();
        useSequenceIdForPrivateConversation =
                sequenceIdProperties.isUseSequenceIdForPrivateConversation();
        MessageProperties.CacheProperties cacheProperties = propertiesManager.getLocalProperties()
                .getService()
                .getMessage()
                .getCache();
        int relayedMessageCacheMaxSize = cacheProperties.getSentMessageCacheMaxSize();
        if (relayedMessageCacheMaxSize > 0
                && propertiesManager.getLocalProperties()
                        .getService()
                        .getMessage()
                        .isPersistMessage()) {
            this.sentMessageCache = Caffeine.newBuilder()
                    .maximumSize(relayedMessageCacheMaxSize)
                    .expireAfterWrite(
                            Duration.ofSeconds(cacheProperties.getSentMessageExpireAfter()))
                    .build();
        } else {
            sentMessageCache = null;
        }
        sentMessageCounter = metricsService.getRegistry()
                .counter(SENT_MESSAGES_COUNTER);
        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
        // Set up the checker for expired messages join requests
        taskManager.reschedule("expiredMessagesCleanup",
                propertiesManager.getLocalProperties()
                        .getService()
                        .getMessage()
                        .getExpiredMessagesCleanupCron(),
                () -> {
                    if (node.isLocalNodeLeader()) {
                        int retentionPeriodHours = messageRetentionPeriodHours;
                        if (retentionPeriodHours > 0) {
                            deleteExpiredMessages(retentionPeriodHours).subscribe(null,
                                    t -> LOGGER.error(
                                            "Caught an error while deleting expired messages",
                                            t));
                        }
                    }
                });
    }

    private void updateProperties(TurmsProperties properties) {
        MessageProperties messageProperties = properties.getService()
                .getMessage();
        availableRecallDurationMillis =
                messageProperties.getAvailableRecallDurationSeconds() * 1000;
        defaultAvailableMessagesNumberWithTotal =
                messageProperties.getDefaultAvailableMessagesNumberWithTotal();
        maxTextLimit = messageProperties.getMaxTextLimit();
        maxRecordsSize = messageProperties.getMaxRecordsSizeBytes();
        persistPreMessageId = messageProperties.isPersistPreMessageId();
        persistRecord = messageProperties.isPersistRecord();
        persistMessage = messageProperties.isPersistMessage();
        persistSenderIp = messageProperties.isPersistSenderIp();
        updateReadDateAfterMessageSent = properties.getService()
                .getConversation()
                .getReadReceipt()
                .isUpdateReadDateAfterMessageSent();
        deleteMessageLogicallyByDefault = messageProperties.isDeleteMessageLogicallyByDefault();
        recalledMessageQueryDateRange = messageProperties.isRecalledMessageVisible()
                ? null
                : DateRange.NULL;
        allowRecallMessage = messageProperties.isAllowRecallMessage();
        allowEditMessageBySender = messageProperties.isAllowEditMessageBySender();
        sendMessageToOtherSenderOnlineDevices =
                messageProperties.isSendMessageToOtherSenderOnlineDevices();
        timeType = messageProperties.getTimeType();
        messageRetentionPeriodHours = messageProperties.getMessageRetentionPeriodHours();
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
                return Mono.just(message.getSenderId()
                        .equals(senderId));
            }
        }
        return messageRepository.isMessageSender(messageId, senderId);
    }

    public Mono<Boolean> isMessageRecipientOrSender(@NotNull Long messageId, @NotNull Long userId) {
        try {
            Validator.notNull(messageId, "messageId");
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null) {
                return message.getIsGroupMessage()
                        ? groupMemberService.isGroupMember(message.getTargetId(), userId, false)
                        : Mono.just(message.getTargetId()
                                .equals(userId)
                                || message.getSenderId()
                                        .equals(userId));
            }
        }
        return messageRepository.findMessageSenderIdAndTargetIdAndIsGroupMessage(messageId)
                .flatMap(message -> message.getIsGroupMessage()
                        ? groupMemberService.isGroupMember(message.getTargetId(), userId, false)
                        : Mono.just(message.getTargetId()
                                .equals(userId)
                                || message.getSenderId()
                                        .equals(userId)));
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
                .map(deliveryDate -> deliveryDate.getTime()
                        - System.currentTimeMillis() < availableRecallDurationMillis
                                ? ServicePermission.OK
                                : ServicePermission.get(MESSAGE_RECALL_TIMEOUT))
                .defaultIfEmpty(ServicePermission.get(RECALL_NON_EXISTING_MESSAGE));
    }

    public Flux<Message> authAndQueryCompleteMessages(
            Long requesterId,
            @Nullable Collection<Long> messageIds,
            @NotNull Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages,
            @Nullable Set<Long> fromIds,
            @Nullable DateRange deliveryDateRange,
            @Nullable Integer maxCount,
            boolean ascending,
            boolean withTotal) {
        // We don't support the case when "areGroupMessages" is null currently (meaning we will
        // support it in the future)
        // because it makes the pagination implementation complex
        if (areGroupMessages == null) {
            return Flux.error(ResponseException.get(ResponseStatusCode.INVALID_REQUEST,
                    "\"areGroupMessages\" must be either true or false currently"));
        }
        if (maxCount == null) {
            if (withTotal) {
                maxCount = defaultAvailableMessagesNumberWithTotal;
            }
        } else {
            // TODO: make configurable
            maxCount = Math.min(maxCount, 1000);
        }
        if (areGroupMessages) {
            Integer finalMaxCount = maxCount;
            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
            if (CollectionUtil.isEmpty(fromIds)) {
                return groupMemberService.queryUserJoinedGroupIds(requesterId)
                        .collect(Collectors.toCollection(recyclableSet::getValue))
                        .flatMapMany(groupIds -> queryMessages(messageIds,
                                true,
                                areSystemMessages,
                                null,
                                groupIds,
                                deliveryDateRange,
                                DateRange.NULL,
                                recalledMessageQueryDateRange,
                                0,
                                finalMaxCount,
                                ascending))
                        .doFinally(signalType -> recyclableSet.recycle());
            }
            return groupMemberService.findExistentMemberGroupIds(fromIds, requesterId)
                    .collect(Collectors.toCollection(recyclableSet::getValue))
                    .flatMapMany(existentMemberGroupIds -> {
                        int diff = fromIds.size() - existentMemberGroupIds.size();
                        if (diff != 0) {
                            List<Long> nonGroupMemberGroupIds = new ArrayList<>(diff);
                            fromIds.forEach(fromId -> {
                                if (!existentMemberGroupIds.contains(fromId)) {
                                    nonGroupMemberGroupIds.add(fromId);
                                }
                            });
                            return Flux.error(ResponseException.get(
                                    ResponseStatusCode.NOT_MEMBER_TO_QUERY_GROUP_MESSAGES,
                                    "The user ("
                                            + requesterId
                                            + ") is not the member of the groups: "
                                            + nonGroupMemberGroupIds));
                        }
                        return queryMessages(messageIds,
                                true,
                                areSystemMessages,
                                null,
                                fromIds,
                                deliveryDateRange,
                                DateRange.NULL,
                                recalledMessageQueryDateRange,
                                0,
                                finalMaxCount,
                                ascending);
                    })
                    .doFinally(signalType -> recyclableSet.recycle());
        }
        return queryMessages(messageIds,
                false,
                areSystemMessages,
                fromIds,
                Set.of(requesterId),
                deliveryDateRange,
                DateRange.NULL,
                recalledMessageQueryDateRange,
                0,
                maxCount,
                ascending);
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
            @Nullable Collection<Long> messageIds,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages,
            @Nullable Set<Long> senderIds,
            @Nullable Set<Long> targetIds,
            @Nullable DateRange deliveryDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable DateRange recallDateRange,
            @Nullable Integer page,
            @Nullable Integer size,
            @Nullable Boolean ascending) {
        int targetIdCount = CollectionUtil.getSize(targetIds);
        boolean enableConversationId = useConversationId && targetIdCount > 0;
        List<byte[]> conversationIds = null;
        if (enableConversationId) {
            if (areGroupMessages == null) {
                int senderIdCount = CollectionUtil.getSize(senderIds);
                conversationIds = new ArrayList<>(targetIdCount + targetIdCount * senderIdCount);
                boolean hasSenderIds = senderIdCount > 0;
                for (long targetId : targetIds) {
                    conversationIds.add(MessageRepository.getGroupConversationId(targetId));
                    if (hasSenderIds) {
                        for (long senderId : senderIds) {
                            conversationIds.add(
                                    MessageRepository.getPrivateConversationId(senderId, targetId));
                        }
                    }
                }
                if (hasSenderIds) {
                    // Reset "senderIds" and "targetIds" because they have been
                    // converted to private conversation IDs so the senderIds/targetIds criteria is
                    // unnecessary.
                    // Note that we change the semantic from
                    // "querying messages sent by the sender IDs, and sent to the target IDs"
                    // to "querying messages sent by the sender IDs, and sent to the target IDs +
                    // messages sent by the target IDs, and sent to the sender IDs"
                    senderIds = null;
                    targetIds = null;
                } else {
                    // Reset "targetIds" because they have been converted to
                    // group conversation IDs so the targetIds criteria is duplicate and
                    // unnecessary.
                    targetIds = null;
                }
            } else if (areGroupMessages) {
                if (targetIdCount == 1) {
                    conversationIds =
                            List.of(MessageRepository.getGroupConversationId(targetIds.iterator()
                                    .next()));
                } else {
                    conversationIds = new ArrayList<>(targetIdCount);
                    for (long targetId : targetIds) {
                        conversationIds.add(MessageRepository.getGroupConversationId(targetId));
                    }
                }
                // Reset "targetIds" because they have been converted to
                // group conversation IDs so the targetIds criteria is duplicate and unnecessary.
                targetIds = null;
            } else {
                int senderIdCount = CollectionUtil.getSize(senderIds);
                if (senderIdCount > 0) {
                    if (targetIdCount == 1 && senderIdCount == 1) {
                        conversationIds = List.of(MessageRepository.getPrivateConversationId(
                                senderIds.iterator()
                                        .next(),
                                targetIds.iterator()
                                        .next()));
                    } else {
                        conversationIds = new ArrayList<>(senderIdCount * targetIdCount);
                        for (long senderId : senderIds) {
                            for (long targetId : targetIds) {
                                conversationIds.add(MessageRepository
                                        .getPrivateConversationId(senderId, targetId));
                            }
                        }
                    }
                }
                // Reset "senderIds" and "targetIds" because they have been
                // converted to private conversation IDs so the senderIds/targetIds criteria is
                // unnecessary.
                // Note that we change the semantic from
                // "querying messages sent by the sender IDs, and sent to the target IDs"
                // to "querying messages sent by the sender IDs, and sent to the target IDs +
                // messages sent by the target IDs, and sent to the sender IDs"
                if (conversationIds != null) {
                    senderIds = null;
                    targetIds = null;
                }
            }
        }
        return messageRepository.findMessages(messageIds,
                conversationIds,
                areGroupMessages,
                areSystemMessages,
                senderIds,
                targetIds,
                deliveryDateRange,
                deletionDateRange,
                recallDateRange,
                page,
                size,
                ascending);
    }

    public Mono<Message> saveMessage(
            @Nullable Long messageId,
            @NotNull Long senderId,
            @Nullable byte[] senderIp,
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
        try {
            Validator.notNull(senderId, "senderId");
            Validator.notNull(targetId, "targetId");
            Validator.notNull(isGroupMessage, "isGroupMessage");
            Validator.notNull(isSystemMessage, "isSystemMessage");
            Validator.maxLength(text, "text", maxTextLimit);
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
        if (!persistRecord) {
            records = null;
        }
        if (!persistPreMessageId) {
            preMessageId = null;
        }
        if (!persistSenderIp) {
            senderIp = null;
        }
        byte[] conversationId;
        Mono<Long> sequenceId = null;
        if (isGroupMessage) {
            conversationId = useConversationId
                    ? MessageRepository.getGroupConversationId(targetId)
                    : null;
            if (useSequenceIdForGroupConversation) {
                sequenceId = fetchSequenceId(true, targetId);
            }
        } else {
            conversationId = useConversationId
                    ? MessageRepository.getPrivateConversationId(senderId, targetId)
                    : null;
            if (useSequenceIdForPrivateConversation) {
                sequenceId = fetchSequenceId(false, targetId);
            }
        }
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
                    senderIp == null
                            ? null
                            : InetAddressUtil.ipBytesToInt(senderIp),
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
            byte[] finalSenderIp = senderIp;
            saveMessage = sequenceId.flatMap(seqId -> {
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
                        finalSenderIp == null
                                ? null
                                : InetAddressUtil.ipBytesToInt(finalSenderIp),
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
        if (updateReadDateAfterMessageSent) {
            Mono<Void> upsertConversation = isGroupMessage
                    ? conversationService
                            .upsertGroupConversationReadDate(targetId, senderId, deliveryDate)
                    : conversationService
                            .upsertPrivateConversationReadDate(senderId, targetId, deliveryDate);
            return saveMessage.doOnNext(ignored -> upsertConversation.subscribe(null,
                    t -> LOGGER.error("Caught an error while upserting the {} conversation: {}",
                            isGroupMessage
                                    ? "group"
                                    : "private",
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
        return queryExpiredMessageIds(retentionPeriodHours).collect(CollectorUtil.toChunkedList())
                .flatMap(expiredMessageIds -> {
                    if (expiredMessageIds.isEmpty()) {
                        return Mono.empty();
                    }
                    Mono<List<Long>> messageIdsToDeleteMono = Mono.just(expiredMessageIds);
                    if (pluginManager.hasRunningExtensions(ExpiredMessageDeletionNotifier.class)) {
                        messageIdsToDeleteMono = messageRepository.findByIds(expiredMessageIds)
                                .collect(CollectorUtil.toChunkedList())
                                .flatMap(
                                        messages -> pluginManager.invokeExtensionPointsSequentially(
                                                ExpiredMessageDeletionNotifier.class,
                                                GET_MESSAGES_TO_DELETE_METHOD,
                                                messages,
                                                (notifier, pre) -> pre
                                                        .flatMap(notifier::getMessagesToDelete)))
                                .map(messages -> {
                                    List<Long> messageIds = new ArrayList<>(messages.size());
                                    for (Message message : messages) {
                                        messageIds.add(message.getId());
                                    }
                                    return messageIds;
                                });
                    }
                    return messageIdsToDeleteMono
                            .flatMap(messageIds -> messageRepository.deleteByIds(messageIds)
                                    .then());
                });
    }

    public Mono<DeleteResult> deleteMessages(
            @Nullable Set<Long> messageIds,
            @Nullable Boolean deleteLogically) {
        if (deleteLogically == null) {
            deleteLogically = deleteMessageLogicallyByDefault;
        }
        if (deleteLogically) {
            return messageRepository.updateMessagesDeletionDate(messageIds)
                    .map(OperationResultConvertor::update2delete);
        }
        return messageRepository.deleteByIds(messageIds);
    }

    public Mono<UpdateResult> updateMessages(
            @Nullable Long senderId,
            @Nullable DeviceType senderDeviceType,
            @NotEmpty Set<Long> messageIds,
            @Nullable Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date recallDate,
            @Nullable byte[] senderIp,
            @Nullable ClientSession session) {
        try {
            Validator.notEmpty(messageIds, "messageIds");
            Validator.maxLength(text, "text", maxTextLimit);
            Validator.min(burnAfter, "burnAfter", 0);
            Validator.pastOrPresent(recallDate, "recallDate");
            validRecordsLength(records);
            Validator.ip(senderIp, "senderIp");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(isSystemMessage, text, records, burnAfter, recallDate, senderIp)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        if (recallDate == null) {
            return messageRepository.updateMessages(messageIds,
                    isSystemMessage,
                    senderIp == null
                            ? null
                            : InetAddressUtil.ipBytesToInt(senderIp),
                    text,
                    records,
                    burnAfter,
                    session);
        }
        return messageRepository.findByIds(messageIds)
                .map(message -> {
                    byte[] messageType = {BuiltinSystemMessageType.RECALL_MESSAGE};
                    byte[] messageId = LongUtil.toBytes(message.getId());
                    return authAndSaveAndSendMessage(true,
                            null,
                            senderId,
                            senderDeviceType,
                            senderIp,
                            null,
                            message.getIsGroupMessage(),
                            true,
                            null,
                            List.of(messageType, messageId),
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
            @Nullable Long senderId,
            @Nullable DeviceType senderDeviceType,
            @NotNull Long messageId,
            @Nullable Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date recallDate,
            @Nullable byte[] senderIp,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(messageId, "messageId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return updateMessages(senderId,
                senderDeviceType,
                Set.of(messageId),
                isSystemMessage,
                text,
                records,
                burnAfter,
                recallDate,
                senderIp,
                session);
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
        return messageRepository
                .countUsersWhoSentMessage(dateRange, areGroupMessages, areSystemMessages);
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
            @NotNull Long senderId,
            @Nullable DeviceType senderDeviceType,
            @NotNull Long messageId,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @PastOrPresent Date recallDate) {
        boolean updateMessageContent = text != null || !CollectionUtils.isEmpty(records);
        if (!updateMessageContent && recallDate == null) {
            return Mono.empty();
        }
        if (recallDate != null && !allowRecallMessage) {
            return Mono.error(ResponseException.get(RECALLING_MESSAGE_IS_DISABLED));
        }
        if (updateMessageContent && !allowEditMessageBySender) {
            return Mono.error(ResponseException.get(UPDATING_MESSAGE_BY_SENDER_IS_DISABLED));
        }
        return isMessageSentByUser(messageId, senderId).flatMap(isSentByUser -> {
            if (!isSentByUser) {
                return Mono.error(ResponseException.get(NOT_SENDER_TO_UPDATE_MESSAGE));
            }
            return recallDate == null
                    ? updateMessage(senderId,
                            senderDeviceType,
                            messageId,
                            null,
                            text,
                            records,
                            null,
                            null,
                            null,
                            null)
                    : updateMessageRecallDate(senderId,
                            senderDeviceType,
                            messageId,
                            text,
                            records,
                            recallDate);
        });
    }

    public Mono<Set<Long>> queryMessageRecipients(@NotNull Long messageId) {
        try {
            Validator.notNull(messageId, "messageId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return messageRepository.findMessageGroupId(messageId)
                .flatMap(message -> message.getIsGroupMessage()
                        ? groupMemberService.queryGroupMemberIds(message.groupId(), true)
                        : Mono.just(Set.of(message.getTargetId())));
    }

    /**
     * @return {@link reactor.core.publisher.MonoEmpty} if {@link MessageProperties#persistMessage}
     *         is false and no recipient. {@link MessageAndRecipientIds#message} is null if
     *         {@link MessageProperties#persistMessage} is false.
     */
    public Mono<MessageAndRecipientIds> authAndSaveMessage(
            @Nullable Boolean persist,
            @Nullable Long messageId,
            @NotNull Long senderId,
            @Nullable byte[] senderIp,
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
            Validator.maxLength(text, "text", maxTextLimit);
            validRecordsLength(records);
            Validator.pastOrPresent(deliveryDate, "deliveryDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userService
                .isAllowedToSendMessageToTarget(isGroupMessage, isSystemMessage, senderId, targetId)
                .flatMap(permission -> {
                    ResponseStatusCode code = permission.code();
                    if (code != OK) {
                        return Mono.error(ResponseException.get(code, permission.reason()));
                    }
                    return isGroupMessage
                            ? groupMemberService.queryGroupMemberIds(targetId, true)
                                    .map(memberIds -> CollectionUtil.remove(memberIds, senderId))
                            : Mono.just(Set.of(targetId));
                })
                .flatMap(recipientIds -> {
                    boolean save = persist == null
                            ? persistMessage
                            : persist;
                    if (!save) {
                        return recipientIds.isEmpty()
                                ? Mono.empty()
                                : Mono.just(new MessageAndRecipientIds(null, recipientIds));
                    }
                    return saveMessage(messageId,
                            senderId,
                            senderIp,
                            targetId,
                            isGroupMessage,
                            isSystemMessage,
                            text,
                            records,
                            burnAfter,
                            deliveryDate,
                            null,
                            referenceId,
                            preMessageId).map(message -> {
                                if (message.getId() != null && sentMessageCache != null) {
                                    cacheSentMessage(message);
                                }
                                return new MessageAndRecipientIds(message, recipientIds);
                            });
                });
    }

    public Mono<MessageAndRecipientIds> authAndCloneAndSaveMessage(
            @NotNull Long requesterId,
            @Nullable byte[] requesterIp,
            @NotNull Long referenceId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @NotNull Long targetId) {
        return queryMessage(referenceId).flatMap(message -> authAndSaveMessage(null,
                node.nextLargeGapId(ServiceType.MESSAGE),
                requesterId,
                requesterIp,
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
            @Nullable Boolean persist,
            @Nullable Long senderId,
            @Nullable DeviceType senderDeviceType,
            @Nullable byte[] senderIp,
            @Nullable Long messageId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
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
            Validator.maxLength(text, "text", maxTextLimit);
            validRecordsLength(records);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (senderId == null) {
            if (isSystemMessage) {
                senderId = ADMIN_REQUESTER_ID;
            } else {
                return Mono.error(ResponseException.get(ILLEGAL_ARGUMENT,
                        "senderId must not be null for user messages"));
            }
        }
        Date deliveryDate = new Date();
        Mono<MessageAndRecipientIds> saveMono = referenceId == null
                ? authAndSaveMessage(persist,
                        messageId,
                        senderId,
                        senderIp,
                        targetId,
                        isGroupMessage,
                        isSystemMessage,
                        text,
                        records,
                        burnAfter,
                        deliveryDate,
                        null,
                        preMessageId)
                : authAndCloneAndSaveMessage(senderId,
                        senderIp,
                        referenceId,
                        isGroupMessage,
                        isSystemMessage,
                        targetId);
        return saveMono.doOnNext(pair -> {
            Message message = pair.message();
            sentMessageCounter.increment();
            Long msgId = message == null
                    ? null
                    : message.getId();
            if (msgId != null && sentMessageCache != null) {
                cacheSentMessage(message);
            }
            if (send) {
                // No need to let the client wait to send notifications to recipients
                Set<Long> recipientIds = pair.recipientIds();
                sendMessage(message, recipientIds, senderDeviceType).subscribe(null,
                        t -> LOGGER.error("Failed to send the message ({}) to the recipients: {}",
                                msgId,
                                recipientIds,
                                t));
            }
        })
                .then();
    }

    /**
     * @param senderDeviceType can be null when it is a system message
     */
    private Mono<Void> sendMessage(
            @NotNull Message message,
            @NotNull Set<Long> recipientIds,
            @Nullable DeviceType senderDeviceType) {
        if (recipientIds.isEmpty()) {
            return Mono.empty();
        }
        Long senderId = message.getSenderId();
        TurmsNotification notification = ClientMessagePool.getTurmsNotificationBuilder()
                .setRequesterId(senderId)
                .setTimestamp(System.currentTimeMillis())
                .setRelayedRequest(ClientMessagePool.getTurmsRequestBuilder()
                        .setCreateMessageRequest(
                                ProtoModelConvertor.message2createMessageRequest(message)))
                .setRequestId(ADMIN_REQUEST_ID)
                .build();
        Set<UserSessionId> excludedUserSessionIds;
        if (sendMessageToOtherSenderOnlineDevices && senderDeviceType != null) {
            recipientIds = CollectionUtil.add(recipientIds, senderId);
            excludedUserSessionIds = Set.of(new UserSessionId(senderId, senderDeviceType));
        } else {
            excludedUserSessionIds = Collections.emptySet();
        }
        return outboundMessageService
                .forwardNotification(notification, recipientIds, excludedUserSessionIds)
                // TODO: Should trigger extension points
                // https://github.com/turms-im/turms/issues/1189
                .then();
    }

    private void cacheSentMessage(@NotNull Message message) {
        sentMessageCache.put(message.getId(),
                new Message(
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
                        null,
                        message.getTargetId(),
                        null,
                        null,
                        null,
                        null,
                        null));
    }

    private Mono<UpdateResult> updateMessageRecallDate(
            @Nullable Long senderId,
            @Nullable DeviceType senderDeviceType,
            @NotNull Long messageId,
            String text,
            List<byte[]> records,
            @PastOrPresent Date recallDate) {
        return isMessageRecallable(messageId).flatMap(permission -> {
            ResponseStatusCode code = permission.code();
            if (code != OK) {
                return Mono.error(ResponseException.get(code, permission.reason()));
            }
            return updateMessage(senderId,
                    senderDeviceType,
                    messageId,
                    null,
                    text,
                    records,
                    null,
                    recallDate,
                    null,
                    null);
        });
    }

    private void validRecordsLength(@Nullable List<byte[]> records) {
        if (records == null) {
            return;
        }
        int maxSize = maxRecordsSize;
        if (maxSize <= -1) {
            return;
        }
        int count = 0;
        for (byte[] record : records) {
            count += record.length;
        }
        if (count > maxSize) {
            throw ResponseException.get(ILLEGAL_ARGUMENT,
                    "The total size of records must be less than or equal to "
                            + maxSize);
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
                ByteBuf buffer =
                        PooledByteBufAllocator.DEFAULT.directBuffer(prefix.length + Long.BYTES)
                                .writeBytes(prefix)
                                .writeLong(targetId);
                keys.add(buffer);
            }
            return client.del(keys);
        })
                .then();
    }

    private Mono<Long> fetchSequenceId(boolean isGroupConversation, Long targetId) {
        if (redisClientManager == null) {
            return Mono.empty();
        }
        byte[] prefix = isGroupConversation
                ? GROUP_CONVERSATION_SEQUENCE_ID_PREFIX
                : PRIVATE_CONVERSATION_SEQUENCE_ID_PREFIX;
        ByteBuf keyBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(prefix.length + Long.BYTES)
                .writeBytes(prefix)
                .writeLong(targetId);
        return redisClientManager.incr(targetId, keyBuffer);
    }

    // conversation ID

}