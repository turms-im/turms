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
import java.util.Map;
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
import io.lettuce.core.ScriptOutputType;
import io.micrometer.core.instrument.Counter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.math.MathFlux;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.domain.session.bo.UserSessionId;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.lang.LongUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.message.OutboundMessageManager;
import im.turms.server.common.infra.net.InetAddressUtil;
import im.turms.server.common.infra.netty.ReferenceCountUtil;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.TimeType;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.infra.property.env.service.business.message.MessageProperties;
import im.turms.server.common.infra.property.env.service.business.message.SequenceIdProperties;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.recycler.SetRecycler;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.infra.test.VisibleForTesting;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateTimeUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.operation.OperationResultConvertor;
import im.turms.server.common.storage.redis.RedisEntryIdConst;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
import im.turms.server.common.storage.redis.script.RedisScript;
import im.turms.service.domain.conversation.service.ConversationService;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.group.service.GroupService;
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
import static im.turms.server.common.access.common.ResponseStatusCode.NOT_MESSAGE_RECIPIENT_OR_SENDER_TO_FORWARD_MESSAGE;
import static im.turms.server.common.access.common.ResponseStatusCode.NOT_SENDER_TO_RECALL_MESSAGE;
import static im.turms.server.common.access.common.ResponseStatusCode.NOT_SENDER_TO_UPDATE_MESSAGE;
import static im.turms.server.common.access.common.ResponseStatusCode.OK;
import static im.turms.server.common.access.common.ResponseStatusCode.RECALLING_MESSAGE_IS_DISABLED;
import static im.turms.server.common.access.common.ResponseStatusCode.RECALL_MESSAGE_OF_NONEXISTENT_GROUP;
import static im.turms.server.common.access.common.ResponseStatusCode.UPDATE_MESSAGE_OF_NONEXISTENT_GROUP;
import static im.turms.server.common.access.common.ResponseStatusCode.UPDATING_GROUP_MESSAGE_BY_SENDER_IS_DISABLED;
import static im.turms.server.common.access.common.ResponseStatusCode.UPDATING_MESSAGE_BY_SENDER_IS_DISABLED;
import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_REQUESTER_ID;
import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_REQUEST_ID;
import static im.turms.service.infra.metrics.MetricNameConst.TURMS_BUSINESS_MESSAGE_SENT;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class MessageService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private static final Mono<MessageAndRecipientIds> ERROR_NOT_MESSAGE_RECIPIENT_OR_SENDER_TO_FORWARD_MESSAGE =
            Mono.error(ResponseException.get(NOT_MESSAGE_RECIPIENT_OR_SENDER_TO_FORWARD_MESSAGE));

    private static final Method GET_MESSAGES_TO_DELETE_METHOD;

    private final MessageRepository messageRepository;

    private final OutboundMessageManager outboundMessageManager;
    @Nullable
    private final TurmsRedisClientManager redisClientManager;
    private final Node node;

    private final ConversationService conversationService;
    private final GroupService groupService;
    private final GroupMemberService groupMemberService;
    private final UserService userService;
    private final PluginManager pluginManager;

    private final RedisScript<Long> deletePrivateMessageSequenceIdScript;
    private final RedisScript<Long> getPrivateMessageSequenceIdScript;

    private final boolean useConversationId;
    private final boolean useSequenceIdForGroupConversation;
    private final boolean useSequenceIdForPrivateConversation;

    private int availableRecallDurationMillis;
    @Nullable
    private Integer defaultAvailableMessagesNumberWithoutTotal;
    @Nullable
    private Integer maxAvailableMessagesNumberWithoutTotal;
    @Nullable
    private Integer defaultAvailableMessagesNumberWithTotal;
    @Nullable
    private Integer maxAvailableMessagesNumberWithTotal;
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
    private boolean notifyRequesterOtherOnlineSessionsOfMessageCreated;
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
            OutboundMessageManager outboundMessageManager,
            @Nullable @Autowired(
                    required = false) @Qualifier("sequenceIdRedisClientManager") TurmsRedisClientManager sequenceIdRedisClientManager,

            Node node,

            TurmsPropertiesManager propertiesManager,
            ConversationService conversationService,
            GroupService groupService,
            GroupMemberService groupMemberService,
            UserService userService,
            MetricsService metricsService,

            PluginManager pluginManager,
            TaskManager taskManager) {
        this.messageRepository = messageRepository;
        this.outboundMessageManager = outboundMessageManager;
        this.redisClientManager = sequenceIdRedisClientManager;
        this.node = node;
        this.conversationService = conversationService;
        this.groupService = groupService;
        this.groupMemberService = groupMemberService;
        this.userService = userService;
        this.pluginManager = pluginManager;

        Map<String, Object> scriptParams = Map.of("RELATED_USER_IDS_KEY",
                "\""
                        + RedisEntryIdConst.KEY_RELATED_USER_IDS
                        + "\"",
                "PRIVATE_MESSAGE_SEQUENCE_ID_KEY",
                "\""
                        + RedisEntryIdConst.KEY_PRIVATE_MESSAGE_SEQUENCE_ID
                        + "\"");
        deletePrivateMessageSequenceIdScript = RedisScript.get(
                new ClassPathResource("redis/message/delete_private_message_sequence_id.lua"),
                ScriptOutputType.INTEGER,
                scriptParams);
        getPrivateMessageSequenceIdScript = RedisScript.get(
                new ClassPathResource("redis/message/get_private_message_sequence_id.lua"),
                ScriptOutputType.INTEGER,
                scriptParams);

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
                .counter(TURMS_BUSINESS_MESSAGE_SENT);
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
        ServiceProperties serviceProperties = properties.getService();
        MessageProperties messageProperties = serviceProperties.getMessage();
        availableRecallDurationMillis =
                messageProperties.getAvailableRecallDurationSeconds() * 1000;
        int localDefaultAvailableMessagesNumberWithoutTotal =
                messageProperties.getDefaultAvailableMessagesNumberWithoutTotal();
        defaultAvailableMessagesNumberWithoutTotal =
                localDefaultAvailableMessagesNumberWithoutTotal > 0
                        ? localDefaultAvailableMessagesNumberWithoutTotal
                        : null;
        int localMaxAvailableMessagesNumberWithoutTotal =
                messageProperties.getMaxAvailableMessagesNumberWithoutTotal();
        maxAvailableMessagesNumberWithoutTotal = localMaxAvailableMessagesNumberWithoutTotal > 0
                ? localMaxAvailableMessagesNumberWithoutTotal
                : null;
        int localDefaultAvailableMessagesNumberWithTotal =
                messageProperties.getDefaultAvailableMessagesNumberWithTotal();
        defaultAvailableMessagesNumberWithTotal = localDefaultAvailableMessagesNumberWithTotal > 0
                ? localDefaultAvailableMessagesNumberWithTotal
                : null;
        int localMaxAvailableMessagesNumberWithTotal =
                messageProperties.getMaxAvailableMessagesNumberWithTotal();
        maxAvailableMessagesNumberWithTotal = localMaxAvailableMessagesNumberWithTotal > 0
                ? localMaxAvailableMessagesNumberWithTotal
                : null;
        maxTextLimit = messageProperties.getMaxTextLimit();
        maxRecordsSize = messageProperties.getMaxRecordsSizeBytes();
        persistPreMessageId = messageProperties.isPersistPreMessageId();
        persistRecord = messageProperties.isPersistRecord();
        persistMessage = messageProperties.isPersistMessage();
        persistSenderIp = messageProperties.isPersistSenderIp();
        updateReadDateAfterMessageSent = serviceProperties.getConversation()
                .getReadReceipt()
                .isUpdateReadDateAfterMessageSent();
        deleteMessageLogicallyByDefault = messageProperties.isDeleteMessageLogicallyByDefault();
        recalledMessageQueryDateRange = messageProperties.isRecalledMessageVisible()
                ? null
                : DateRange.NULL;
        allowRecallMessage = messageProperties.isAllowRecallMessage();
        allowEditMessageBySender = messageProperties.isAllowEditMessageBySender();
        notifyRequesterOtherOnlineSessionsOfMessageCreated = serviceProperties.getNotification()
                .getMessageCreated()
                .isNotifyRequesterOtherOnlineSessions();
        timeType = messageProperties.getTimeType();
        messageRetentionPeriodHours = messageProperties.getMessageRetentionPeriodHours();
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
                .flatMap(message -> isMessageRecipientOrSender(userId,
                        message.getIsGroupMessage(),
                        message.getTargetId(),
                        message.getSenderId()));
    }

    private Mono<Boolean> isMessageRecipientOrSender(
            @NotNull Long userId,
            boolean isGroupMessage,
            @NotNull Long targetId,
            @NotNull Long senderId) {
        return isGroupMessage
                ? groupMemberService.isGroupMember(targetId, userId, false)
                : Mono.just(targetId.equals(userId) || senderId.equals(userId));
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
            maxCount = withTotal
                    ? defaultAvailableMessagesNumberWithTotal
                    : defaultAvailableMessagesNumberWithoutTotal;
        } else {
            maxCount = withTotal
                    ? Math.min(maxCount, maxAvailableMessagesNumberWithTotal)
                    : Math.min(maxCount, maxAvailableMessagesNumberWithoutTotal);
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
                                    ResponseStatusCode.NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES,
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
                    // converted to private conversation IDs, so the senderIds/targetIds criteria is
                    // unnecessary.
                    // Note that we change the semantic from
                    // "querying messages sent by the sender IDs, and sent to the target IDs"
                    // to "querying messages sent by the sender IDs, and sent to the target IDs +
                    // messages sent by the target IDs, and sent to the sender IDs".
                    senderIds = null;
                    targetIds = null;
                } else {
                    // Reset "targetIds" because they have been converted to
                    // group conversation IDs, so the targetIds criteria is duplicate and
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
                // group conversation IDs, so the targetIds criteria is duplicate and unnecessary.
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
                // converted to private conversation IDs, so the senderIds/targetIds criteria is
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
                sequenceId = fetchGroupMessageSequenceId(targetId);
            }
        } else {
            conversationId = useConversationId
                    ? MessageRepository.getPrivateConversationId(senderId, targetId)
                    : null;
            if (useSequenceIdForPrivateConversation) {
                sequenceId = fetchPrivateMessageSequenceId(senderId, targetId);
            }
        }
        Mono<Message> saveMessage;
        Integer senderIpV4;
        byte[] senderIpV6;
        if (senderIp == null) {
            senderIpV4 = null;
            senderIpV6 = null;
        } else {
            int length = senderIp.length;
            if (length == InetAddressUtil.IPV4_BYTE_LENGTH) {
                senderIpV4 = InetAddressUtil.ipV4BytesToUnsignedInt(senderIp);
                senderIpV6 = null;
            } else if (length == InetAddressUtil.IPV6_BYTE_LENGTH) {
                senderIpV4 = null;
                senderIpV6 = senderIp;
            } else {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "\"senderIp\" must be an IPv4 or IPv6 address"));
            }
        }
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
                    senderIpV4,
                    senderIpV6,
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
                        senderIpV4,
                        senderIpV6,
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
        Date expirationDate =
                DateTimeUtil.addHours(System.currentTimeMillis(), -retentionPeriodHours);
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
        Integer senderIpV4;
        byte[] senderIpV6;
        if (senderIp == null) {
            senderIpV4 = null;
            senderIpV6 = null;
        } else {
            int length = senderIp.length;
            if (length == InetAddressUtil.IPV4_BYTE_LENGTH) {
                senderIpV4 = InetAddressUtil.ipV4BytesToUnsignedInt(senderIp);
                senderIpV6 = null;
            } else if (length == InetAddressUtil.IPV6_BYTE_LENGTH) {
                senderIpV4 = null;
                senderIpV6 = senderIp;
            } else {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "\"senderIp\" must be an IPv4 or IPv6 address"));
            }
        }
        Mono<UpdateResult> update = messageRepository.updateMessages(messageIds,
                isSystemMessage,
                senderIpV4,
                senderIpV6,
                recallDate,
                text,
                records,
                burnAfter,
                session);
        if (recallDate != null) {
            update = update.flatMap(updateResult -> {
                if (updateResult.getModifiedCount() == 0) {
                    return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
                }
                // TODO: use cache
                return messageRepository.findByIds(messageIds)
                        .collect(CollectorUtil.toList(messageIds.size()))
                        .flatMap(messages -> {
                            List<Mono<Void>> saveAndSendMessageMonos =
                                    new ArrayList<>(messageIds.size());
                            for (Message message : messages) {
                                saveAndSendMessageMonos.add(saveAndSendMessage(true,
                                        null,
                                        senderId,
                                        senderDeviceType,
                                        senderIp,
                                        null,
                                        message.getIsGroupMessage(),
                                        true,
                                        null,
                                        List.of(BuiltinSystemMessageType.RECALL_MESSAGE_BYTES,
                                                LongUtil.toBytes(message.getId())),
                                        message.getTargetId(),
                                        null,
                                        null,
                                        null));
                            }
                            return Mono.whenDelayError(saveAndSendMessageMonos);
                        })
                        .thenReturn(updateResult);
            });
        }
        return update;
    }

    public Mono<Boolean> hasPrivateMessage(Long senderId, Long targetId) {
        try {
            Validator.notNull(senderId, "senderId");
            Validator.notNull(targetId, "targetId");
        } catch (Exception e) {
            return Mono.error(e);
        }
        return messageRepository.existsBySenderIdAndTargetId(senderId, targetId);
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
        Mono<Void> checkIfAllowedToUpdate;
        if (text != null || CollectionUtil.isNotEmpty(records)) {
            // TODO: use cache
            checkIfAllowedToUpdate = checkIfAllowedToUpdateMessage(messageId, senderId);
            if (recallDate != null) {
                checkIfAllowedToUpdate = checkIfAllowedToUpdate
                        .then(checkIfAllowedToRecallMessage(messageId, senderId));
            }
        } else if (recallDate == null) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        } else {
            checkIfAllowedToUpdate = checkIfAllowedToRecallMessage(messageId, senderId);
        }
        return checkIfAllowedToUpdate.then(updateMessages(senderId,
                senderDeviceType,
                Set.of(messageId),
                null,
                text,
                records,
                null,
                recallDate,
                null,
                null));
    }

    private Mono<Void> checkIfAllowedToUpdateMessage(Long messageId, Long senderId) {
        if (!allowEditMessageBySender) {
            return Mono.error(ResponseException.get(UPDATING_MESSAGE_BY_SENDER_IS_DISABLED));
        }
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null) {
                if (!message.getSenderId()
                        .equals(senderId)) {
                    return Mono.error(ResponseException.get(NOT_SENDER_TO_UPDATE_MESSAGE));
                }
                if (message.getIsGroupMessage()) {
                    return groupService.queryGroupTypeIfActiveAndNotDeleted(message.getTargetId())
                            .switchIfEmpty(Mono.defer(() -> Mono.error(
                                    ResponseException.get(UPDATE_MESSAGE_OF_NONEXISTENT_GROUP))))
                            .flatMap(groupType -> groupType.getMessageEditable()
                                    ? Mono.empty()
                                    : Mono.error(ResponseException
                                            .get(UPDATING_GROUP_MESSAGE_BY_SENDER_IS_DISABLED)));
                } else {
                    return Mono.empty();
                }
            }
        }
        return messageRepository.findIsGroupMessageAndTargetId(messageId, senderId)
                .switchIfEmpty(Mono.defer(
                        () -> Mono.error(ResponseException.get(NOT_SENDER_TO_UPDATE_MESSAGE))))
                .flatMap(message -> {
                    if (message.getIsGroupMessage()) {
                        return groupService
                                .queryGroupTypeIfActiveAndNotDeleted(message.getTargetId())
                                .switchIfEmpty(Mono.defer(() -> Mono.error(ResponseException
                                        .get(UPDATE_MESSAGE_OF_NONEXISTENT_GROUP))))
                                .flatMap(groupType -> groupType.getMessageEditable()
                                        ? Mono.empty()
                                        : Mono.error(ResponseException.get(
                                                UPDATING_GROUP_MESSAGE_BY_SENDER_IS_DISABLED)));
                    }
                    return Mono.empty();
                });
    }

    private Mono<Void> checkIfAllowedToRecallMessage(Long messageId, Long senderId) {
        if (!allowRecallMessage) {
            return Mono.error(ResponseException.get(RECALLING_MESSAGE_IS_DISABLED));
        }
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null) {
                if (!message.getSenderId()
                        .equals(senderId)) {
                    return Mono.error(ResponseException.get(NOT_SENDER_TO_RECALL_MESSAGE));
                }
                if (System.currentTimeMillis() - message.getDeliveryDate()
                        .getTime() > availableRecallDurationMillis) {
                    return Mono.error(ResponseException.get(MESSAGE_RECALL_TIMEOUT));
                }
            }
        }
        return messageRepository.findIsGroupMessageAndTargetIdAndDeliveryDate(messageId, senderId)
                .switchIfEmpty(Mono.defer(
                        () -> Mono.error(ResponseException.get(NOT_SENDER_TO_RECALL_MESSAGE))))
                .flatMap(message -> {
                    if (System.currentTimeMillis() - message.getDeliveryDate()
                            .getTime() > availableRecallDurationMillis) {
                        return Mono.error(ResponseException.get(MESSAGE_RECALL_TIMEOUT));
                    }
                    if (message.getIsGroupMessage()) {
                        return groupService
                                .queryGroupTypeIfActiveAndNotDeleted(message.getTargetId())
                                .switchIfEmpty(Mono.defer(() -> Mono.error(ResponseException
                                        .get(RECALL_MESSAGE_OF_NONEXISTENT_GROUP))))
                                .then();
                    }
                    return Mono.empty();
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
     *         is false and no recipient.
     *         <p>
     *         {@link MessageAndRecipientIds#message} is null if
     *         {@link MessageProperties#persistMessage} is false.
     */
    public Mono<MessageAndRecipientIds> authAndSaveMessage(
            boolean queryRecipientIds,
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
                    return saveMessage0(queryRecipientIds,
                            persist,
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
                            referenceId,
                            preMessageId);
                });
    }

    /**
     * @return {@link reactor.core.publisher.MonoEmpty} if {@link MessageProperties#persistMessage}
     *         is false and no recipient.
     *         <p>
     *         {@link MessageAndRecipientIds#message} is null if
     *         {@link MessageProperties#persistMessage} is false.
     */
    public Mono<MessageAndRecipientIds> saveMessage(
            boolean queryRecipientIds,
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
        return saveMessage0(queryRecipientIds,
                persist,
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
                referenceId,
                preMessageId);
    }

    /**
     * @return {@link reactor.core.publisher.MonoEmpty} if {@link MessageProperties#persistMessage}
     *         is false and no recipient.
     *         <p>
     *         {@link MessageAndRecipientIds#message} is null if
     *         {@link MessageProperties#persistMessage} is false.
     */
    private Mono<MessageAndRecipientIds> saveMessage0(
            boolean queryRecipientIds,
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
        Mono<Set<Long>> queryRecipients;
        if (queryRecipientIds) {
            queryRecipients = isGroupMessage
                    ? groupMemberService.queryGroupMemberIds(targetId, true)
                            .map(memberIds -> CollectionUtil.remove(memberIds, senderId))
                    : Mono.just(Set.of(targetId));
        } else {
            queryRecipients = PublisherPool.EMPTY_SET;
        }
        return queryRecipients.flatMap(recipientIds -> {
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
            boolean queryRecipientIds,
            @NotNull Long requesterId,
            @Nullable byte[] requesterIp,
            @NotNull Long referenceId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @NotNull Long targetId) {
        return queryMessage(referenceId).flatMap(message -> isMessageRecipientOrSender(requesterId,
                message.getIsGroupMessage(),
                message.getTargetId(),
                message.getSenderId()).flatMap(isMessageRecipientOrSender -> {
                    if (!isMessageRecipientOrSender) {
                        return ERROR_NOT_MESSAGE_RECIPIENT_OR_SENDER_TO_FORWARD_MESSAGE;
                    }
                    return authAndSaveMessage(queryRecipientIds,
                            null,
                            node.nextLargeGapId(ServiceType.MESSAGE),
                            requesterId,
                            requesterIp,
                            targetId,
                            isGroupMessage,
                            isSystemMessage,
                            message.getText(),
                            message.getRecords(),
                            message.getBurnAfter(),
                            null,
                            referenceId,
                            null);
                }))
                // We should not tell the client that the message was not found.
                // Otherwise, the user can check if any message exists or not.
                .switchIfEmpty(ERROR_NOT_MESSAGE_RECIPIENT_OR_SENDER_TO_FORWARD_MESSAGE);
    }

    public Mono<MessageAndRecipientIds> cloneAndSaveMessage(
            boolean queryRecipientIds,
            @NotNull Long senderId,
            @Nullable byte[] senderIp,
            @NotNull Long referenceId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @NotNull Long targetId) {
        return queryMessage(referenceId).flatMap(message -> saveMessage(queryRecipientIds,
                null,
                node.nextLargeGapId(ServiceType.MESSAGE),
                senderId,
                senderIp,
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
        return saveAndSendMessage0(true,
                send,
                persist,
                senderId,
                senderDeviceType,
                senderIp,
                messageId,
                isGroupMessage,
                isSystemMessage,
                text,
                records,
                targetId,
                burnAfter,
                referenceId,
                preMessageId);
    }

    public Mono<Void> saveAndSendMessage(
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
        return saveAndSendMessage0(false,
                send,
                persist,
                senderId,
                senderDeviceType,
                senderIp,
                messageId,
                isGroupMessage,
                isSystemMessage,
                text,
                records,
                targetId,
                burnAfter,
                referenceId,
                preMessageId);
    }

    private Mono<Void> saveAndSendMessage0(
            boolean auth,
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
        Mono<MessageAndRecipientIds> saveMono;
        if (auth) {
            saveMono = referenceId == null
                    ? authAndSaveMessage(send,
                            persist,
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
                    : authAndCloneAndSaveMessage(send,
                            senderId,
                            senderIp,
                            referenceId,
                            isGroupMessage,
                            isSystemMessage,
                            targetId);
        } else {
            saveMono = referenceId == null
                    ? saveMessage(send,
                            persist,
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
                    : cloneAndSaveMessage(send,
                            senderId,
                            senderIp,
                            referenceId,
                            isGroupMessage,
                            isSystemMessage,
                            targetId);
        }
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

    public Mono<Void> saveAndSendMessage(
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
        return saveMessage(true,
                persist,
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
                preMessageId).doOnNext(pair -> {
                    Message message = pair.message();
                    sentMessageCounter.increment();
                    Long msgId = message == null
                            ? null
                            : message.getId();
                    if (msgId != null && sentMessageCache != null) {
                        cacheSentMessage(message);
                    }
                    // No need to let the client wait to send notifications to recipients
                    Set<Long> recipientIds = pair.recipientIds();
                    sendMessage(message, recipientIds, senderDeviceType).subscribe(null,
                            t -> LOGGER.error(
                                    "Failed to send the message ({}) to the recipients: {}",
                                    msgId,
                                    recipientIds,
                                    t));
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
        if (notifyRequesterOtherOnlineSessionsOfMessageCreated && senderDeviceType != null) {
            recipientIds = CollectionUtil.add(recipientIds, senderId);
            excludedUserSessionIds = Set.of(new UserSessionId(senderId, senderDeviceType));
        } else {
            excludedUserSessionIds = Collections.emptySet();
        }
        return outboundMessageManager
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
                        null,
                        message.getTargetId(),
                        null,
                        null,
                        null,
                        null,
                        null));
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

    public Mono<Long> deleteGroupMessageSequenceIds(Set<Long> groupIds) {
        if (redisClientManager == null) {
            return PublisherPool.LONG_ZERO;
        }
        Flux<Long> execute = redisClientManager.execute(groupIds, (client, keyList) -> {
            ByteBuf[] keys = new ByteBuf[keyList.size()];
            int i = 0;
            try {
                for (Long groupId : keyList) {
                    ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(Long.BYTES);
                    keys[i++] = buffer;
                    buffer.writeLong(groupId);
                }
            } catch (Exception e) {
                ReferenceCountUtil.ensureReleased(keys);
                return Mono.error(e);
            }
            return client.hdel(RedisEntryIdConst.KEY_GROUP_MESSAGE_SEQUENCE_ID_BUFFER,
                    (Object[]) keys);
        });
        return MathFlux.sumLong(execute);
    }

    /**
     * Note that if there is an user1, when the sequence ID-related data of their last related user
     * is deleted, the sequence ID-related data of user1 in Redis will be deleted automatically (the
     * behavior is expected to save memory), which indicate that calling this method with user1 is
     * unnecessary, and will return 0 instead of 1.
     */
    public Mono<Long> deletePrivateMessageSequenceIds(Set<Long> userIds) {
        if (redisClientManager == null) {
            return PublisherPool.LONG_ZERO;
        }
        Flux<Long> flux = Mono.fromCallable(() -> {
            ByteBuf[] keys = new ByteBuf[userIds.size()];
            int i = 0;
            try {
                for (Long userId : userIds) {
                    ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(Long.BYTES);
                    keys[i++] = buffer;
                    buffer.writeLong(userId);
                }
            } catch (Exception e) {
                ReferenceCountUtil.ensureReleased(keys);
                throw e;
            }
            return keys;
        })
                .flatMapMany(keys -> redisClientManager.execute(client -> {
                    ReferenceCountUtil.retain(keys);
                    return client.eval(deletePrivateMessageSequenceIdScript, keys)
                            .doFinally(signalType -> ReferenceCountUtil.release(keys));
                })
                        .doFinally(signalType -> ReferenceCountUtil.release(keys)));
        return MathFlux.sumLong(flux);
    }

    /**
     * @implNote If the client application detects that the sequence ID goes backward, it should
     *           delete the existent cache for the sequence ID, and cache and use the new sequence
     *           ID so that the client application can still work fine.
     *           <p>
     *           This is an expected behavior for client applications to suffer from data loss in
     *           Redis due to any reason.
     */
    @VisibleForTesting
    public Mono<Long> fetchGroupMessageSequenceId(Long groupId) {
        if (redisClientManager == null) {
            return Mono.empty();
        }
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(Long.BYTES);
        try {
            buffer.writeLong(groupId);
        } catch (Exception e) {
            buffer.release();
            return Mono.error(e);
        }
        return redisClientManager
                .hincr(groupId, RedisEntryIdConst.KEY_GROUP_MESSAGE_SEQUENCE_ID_BUFFER, buffer);
    }

    @VisibleForTesting
    public Mono<Long> fetchPrivateMessageSequenceId(Long userId1, Long userId2) {
        if (redisClientManager == null) {
            return Mono.empty();
        }
        ByteBuf key1 = null;
        ByteBuf key2 = null;
        try {
            key1 = PooledByteBufAllocator.DEFAULT.directBuffer(Long.BYTES);
            key2 = PooledByteBufAllocator.DEFAULT.directBuffer(Long.BYTES);
            if (userId1 < userId2) {
                key1 = key1.writeLong(userId1);
                key2 = key2.writeLong(userId2);
            } else {
                key1 = key1.writeLong(userId2);
                key2 = key2.writeLong(userId1);
            }
        } catch (Exception e) {
            if (key1 != null) {
                key1.release();
            }
            if (key2 != null) {
                key2.release();
            }
            return Mono.error(e);
        }
        return redisClientManager
                .eval(userId1 ^ userId2, getPrivateMessageSequenceIdScript, key1, key2);
    }

    // conversation ID

}