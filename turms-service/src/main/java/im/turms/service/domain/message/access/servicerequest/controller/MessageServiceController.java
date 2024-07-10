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

package im.turms.service.domain.message.access.servicerequest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.protobuf.ByteString;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.model.message.MessagesWithTotal;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest;
import im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.TimeType;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.infra.property.env.service.business.notification.NotificationProperties;
import im.turms.server.common.infra.property.env.service.business.notification.message.NotificationMessageCreatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.message.NotificationMessageUpdatedProperties;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.conversation.service.ConversationService;
import im.turms.service.domain.message.bo.MessageAndRecipientIds;
import im.turms.service.domain.message.po.Message;
import im.turms.service.domain.message.service.MessageService;
import im.turms.service.infra.proto.ProtoModelConvertor;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_MESSAGE_REACTIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_MESSAGE_REACTIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_MESSAGES_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_MESSAGE_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class MessageServiceController extends BaseServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceController.class);

    private final MessageService messageService;
    private final ConversationService conversationService;

    private boolean notifyRequesterOtherOnlineSessionsOfMessageCreated;
    private boolean notifyMessageRecipientsOfMessageCreated;

    private boolean notifyRequesterOtherOnlineSessionsOfMessageUpdated;
    private boolean notifyRecipientsOfMessageUpdated;

    private boolean updateReadDateWhenUserQueryingMessage;

    public MessageServiceController(
            TurmsPropertiesManager propertiesManager,
            MessageService messageService,
            ConversationService conversationService) {
        this.messageService = messageService;
        this.conversationService = conversationService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        ServiceProperties serviceProperties = properties.getService();
        NotificationProperties notificationProperties = serviceProperties.getNotification();

        NotificationMessageCreatedProperties notificationMessageCreatedProperties =
                notificationProperties.getMessageCreated();
        notifyRequesterOtherOnlineSessionsOfMessageCreated =
                notificationMessageCreatedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyMessageRecipientsOfMessageCreated =
                notificationMessageCreatedProperties.isNotifyMessageRecipients();

        NotificationMessageUpdatedProperties notificationMessageUpdatedProperties =
                notificationProperties.getMessageUpdated();
        notifyRequesterOtherOnlineSessionsOfMessageUpdated =
                notificationMessageUpdatedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyRecipientsOfMessageUpdated =
                notificationMessageUpdatedProperties.isNotifyMessageRecipients();

        updateReadDateWhenUserQueryingMessage = serviceProperties.getConversation()
                .getReadReceipt()
                .isUpdateReadDateWhenUserQueryingMessage();
    }

    @ServiceRequestMapping(CREATE_MESSAGE_REQUEST)
    public ClientRequestHandler handleCreateMessageRequest() {
        return clientRequest -> {
            CreateMessageRequest request = clientRequest.turmsRequest()
                    .getCreateMessageRequest();
            if (request.hasIsSystemMessage() && request.getIsSystemMessage()) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Users cannot send the system message"));
            }
            Mono<MessageAndRecipientIds> messageAndRelatedUserIdsMono;
            boolean isGroupMessage = request.hasGroupId();
            if (!isGroupMessage && !request.hasRecipientId()) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The recipientId must not be null for private messages"));
            }
            long targetId = isGroupMessage
                    ? request.getGroupId()
                    : request.getRecipientId();
            if (request.hasMessageId()) {
                messageAndRelatedUserIdsMono = messageService.authAndCloneAndSaveMessage(
                        notifyMessageRecipientsOfMessageCreated,
                        clientRequest.userId(),
                        clientRequest.clientIp(),
                        request.getMessageId(),
                        isGroupMessage,
                        false,
                        targetId);
            } else {
                List<byte[]> records = null;
                int recordCount = request.getRecordsCount();
                if (recordCount > 0) {
                    records = new ArrayList<>(recordCount);
                    for (ByteString byteString : request.getRecordsList()) {
                        // We don't support storing ByteString as ByteBuffer directly
                        // because "org.bson.BsonBinaryWriter.doWriteBinaryData"
                        // doesn't support writing ByteBuffer.
                        records.add(byteString.toByteArray());
                    }
                }
                Integer burnAfter = request.hasBurnAfter()
                        ? request.getBurnAfter()
                        : null;
                Date deliveryDate = request.hasDeliveryDate()
                        ? new Date(request.getDeliveryDate())
                        : null;
                Long preMessageId = request.hasPreMessageId()
                        ? request.getPreMessageId()
                        : null;
                messageAndRelatedUserIdsMono =
                        messageService.authAndSaveMessage(notifyMessageRecipientsOfMessageCreated,
                                null,
                                null,
                                clientRequest.userId(),
                                clientRequest.clientIp(),
                                targetId,
                                isGroupMessage,
                                false,
                                request.hasText()
                                        ? request.getText()
                                        : null,
                                records,
                                burnAfter,
                                deliveryDate,
                                null,
                                preMessageId);
            }
            return messageAndRelatedUserIdsMono.map(pair -> {
                Message message = pair.message();
                // "message" is null if persisting messages is disabled
                Long messageId = message == null
                        ? null
                        : message.getId();
                Set<Long> recipientIds = pair.recipientIds();
                boolean hasNoDataForRecipients = recipientIds == null || recipientIds.isEmpty();
                if (messageId == null) {
                    if (hasNoDataForRecipients) {
                        return RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfMessageCreated,
                                clientRequest.turmsRequest());
                    }
                    TurmsRequest dataForRecipients = clientRequest.turmsRequest();
                    if (messageService.getTimeType() == TimeType.LOCAL_SERVER_TIME) {
                        dataForRecipients = ClientMessagePool.getTurmsRequestBuilder()
                                .mergeFrom(clientRequest.turmsRequest())
                                .setCreateMessageRequest(
                                        ClientMessagePool.getCreateMessageRequestBuilder()
                                                .mergeFrom(request)
                                                .setDeliveryDate(System.currentTimeMillis()))
                                .build();
                    }
                    return RequestHandlerResult.of(
                            notifyRequesterOtherOnlineSessionsOfMessageCreated,
                            recipientIds,
                            dataForRecipients);
                } else {
                    if (hasNoDataForRecipients) {
                        return RequestHandlerResult.ofDataLong(messageId,
                                notifyRequesterOtherOnlineSessionsOfMessageCreated,
                                clientRequest.turmsRequest());
                    }
                    TurmsRequest dataForRecipients;
                    if (request.hasMessageId()) {
                        dataForRecipients = ClientMessagePool.getTurmsRequestBuilder()
                                .mergeFrom(clientRequest.turmsRequest())
                                .setCreateMessageRequest(ProtoModelConvertor
                                        .cloneAndFillMessageRequest(request, message))
                                .build();
                    } else {
                        CreateMessageRequest.Builder requestBuilder =
                                ClientMessagePool.getCreateMessageRequestBuilder()
                                        .mergeFrom(request)
                                        .setMessageId(messageId);
                        if (messageService.getTimeType() == TimeType.LOCAL_SERVER_TIME) {
                            requestBuilder.setDeliveryDate(message.getDeliveryDate()
                                    .getTime());
                        }
                        dataForRecipients = ClientMessagePool.getTurmsRequestBuilder()
                                .mergeFrom(clientRequest.turmsRequest())
                                .setCreateMessageRequest(requestBuilder)
                                .build();
                    }
                    return RequestHandlerResult.ofDataLong(messageId,
                            notifyRequesterOtherOnlineSessionsOfMessageCreated,
                            recipientIds,
                            dataForRecipients);
                }
            });
        };
    }

    @ServiceRequestMapping(QUERY_MESSAGES_REQUEST)
    public ClientRequestHandler handleQueryMessagesRequest() {
        return clientRequest -> {
            QueryMessagesRequest request = clientRequest.turmsRequest()
                    .getQueryMessagesRequest();
            List<Long> ids = request.getIdsCount() > 0
                    ? request.getIdsList()
                    : null;
            Boolean areGroupMessages = request.hasAreGroupMessages()
                    ? request.getAreGroupMessages()
                    : null;
            Boolean areSystemMessages = request.hasAreSystemMessages()
                    ? request.getAreSystemMessages()
                    : null;
            Set<Long> fromIds = request.getFromIdsCount() > 0
                    ? CollectionUtil.newSet(request.getFromIdsList())
                    : null;
            Date deliveryDateStart = request.hasDeliveryDateStart()
                    ? new Date(request.getDeliveryDateStart())
                    : null;
            Date deliveryDateEnd = request.hasDeliveryDateEnd()
                    ? new Date(request.getDeliveryDateEnd())
                    : null;
            Integer maxCount = request.hasMaxCount()
                    ? request.getMaxCount()
                    : null;
            boolean ascending = !request.getDescending();
            boolean withTotal = request.getWithTotal();
            Long userId = clientRequest.userId();
            DateRange deliveryDate = DateRange.of(deliveryDateStart, deliveryDateEnd);
            return messageService
                    .authAndQueryCompleteMessages(userId,
                            ids,
                            areGroupMessages,
                            areSystemMessages,
                            fromIds,
                            deliveryDate,
                            maxCount,
                            ascending,
                            withTotal)
                    .collect(CollectorUtil.toChunkedList())
                    .flatMap(messages -> {
                        if (messages.isEmpty()) {
                            return Mono.empty();
                        }
                        Mono<TurmsNotification.Data> dataMono;
                        if (withTotal) {
                            int fromIdCount = CollectionUtil.getSize(fromIds);
                            Map<MessageFromKey, List<Message>> keyToMessages =
                                    CollectionUtil.newMapWithExpectedSize(fromIdCount > 0
                                            ? messages.size() / fromIdCount
                                            : messages.size());
                            for (Message message : messages) {
                                Long targetId = message.getIsGroupMessage()
                                        ? message.getTargetId()
                                        : message.getSenderId();
                                MessageFromKey senderKey =
                                        new MessageFromKey(message.getIsGroupMessage(), targetId);
                                keyToMessages
                                        .computeIfAbsent(senderKey,
                                                messageFromKey -> new LinkedList<>())
                                        .add(message);
                            }
                            Set<Map.Entry<MessageFromKey, List<Message>>> entries =
                                    keyToMessages.entrySet();
                            List<Mono<MessagesWithTotal>> messagesWithTotalMonos =
                                    new ArrayList<>(entries.size());
                            boolean isGroupMessage;
                            for (Map.Entry<MessageFromKey, List<Message>> entry : entries) {
                                MessageFromKey senderKey = entry.getKey();
                                isGroupMessage = senderKey.isGroupMessage();
                                Mono<MessagesWithTotal> messagesWithTotalMono = messageService
                                        .countMessages(null,
                                                isGroupMessage,
                                                null,
                                                isGroupMessage
                                                        ? null
                                                        : Set.of(senderKey.fromId()),
                                                isGroupMessage
                                                        ? Set.of(senderKey.fromId())
                                                        : Set.of(userId),
                                                deliveryDate,
                                                null)
                                        .map(total -> ClientMessagePool
                                                .getMessagesWithTotalBuilder()
                                                .setTotal(total.intValue())
                                                .setIsGroupMessage(senderKey.isGroupMessage())
                                                .setFromId(senderKey.fromId())
                                                .addAllMessages(CollectionUtil.transformAsList(
                                                        entry.getValue(),
                                                        m -> ProtoModelConvertor.message2proto(m)
                                                                .build()))
                                                .build());
                                messagesWithTotalMonos.add(messagesWithTotalMono);
                            }
                            dataMono = Flux.merge(messagesWithTotalMonos)
                                    .collect(CollectorUtil.toList(messagesWithTotalMonos.size()))
                                    .map(messagesWithTotals -> ClientMessagePool
                                            .getTurmsNotificationDataBuilder()
                                            .setMessagesWithTotalList(ClientMessagePool
                                                    .getMessagesWithTotalListBuilder()
                                                    .addAllMessagesWithTotalList(
                                                            messagesWithTotals))
                                            .build());
                        } else {
                            TurmsNotification.Data data = ClientMessagePool
                                    .getTurmsNotificationDataBuilder()
                                    .setMessages(ClientMessagePool.getMessagesBuilder()
                                            .addAllMessages(CollectionUtil.transformAsList(messages,
                                                    m -> ProtoModelConvertor.message2proto(m)
                                                            .build())))
                                    .build();
                            dataMono = Mono.just(data);
                        }
                        Mono<RequestHandlerResult> resultMono =
                                dataMono.map(RequestHandlerResult::of);
                        if (updateReadDateWhenUserQueryingMessage) {
                            resultMono = resultMono.doOnSuccess(ignored -> {
                                Mono<Void> mono = areGroupMessages
                                        ? conversationService
                                                .upsertGroupConversationReadDate(messages.getFirst()
                                                        .groupId(), userId, new Date())
                                        : conversationService.upsertPrivateConversationReadDate(
                                                userId,
                                                messages.getFirst()
                                                        .getTargetId(),
                                                new Date());
                                mono.subscribe(null, t -> {
                                    Message message = messages.getFirst();
                                    if (areGroupMessages) {
                                        LOGGER.error(
                                                "Caught an error while upserting the group conversation read date: {}",
                                                message.groupId(),
                                                t);
                                    } else {
                                        LOGGER.error(
                                                "Caught an error while upserting the private conversation read date: {sender={}, recipient={}}",
                                                message.getSenderId(),
                                                message.getTargetId(),
                                                t);
                                    }
                                });
                            });
                        }
                        return resultMono;
                    });
        };
    }

    @ServiceRequestMapping(UPDATE_MESSAGE_REQUEST)
    public ClientRequestHandler handleUpdateMessageRequest() {
        return clientRequest -> {
            UpdateMessageRequest request = clientRequest.turmsRequest()
                    .getUpdateMessageRequest();
            long messageId = request.getMessageId();
            String text = request.hasText()
                    ? request.getText()
                    : null;
            List<byte[]> records = null;
            int recordCount = request.getRecordsCount();
            if (recordCount > 0) {
                records = new ArrayList<>(recordCount);
                for (ByteString byteString : request.getRecordsList()) {
                    // We don't support storing ByteString as ByteBuffer directly
                    // because "org.bson.BsonBinaryWriter.doWriteBinaryData"
                    // doesn't support writing ByteBuffer.
                    records.add(byteString.toByteArray());
                }
            }
            Date recallDate = request.hasRecallDate()
                    ? new Date(request.getRecallDate())
                    : null;
            return messageService.authAndUpdateMessage(clientRequest
                    .userId(), clientRequest.deviceType(), messageId, text, records, recallDate)
                    .then(Mono.defer(() -> {
                        if (notifyRecipientsOfMessageUpdated) {
                            return messageService.queryMessageRecipients(messageId)
                                    .map(recipientIds -> RequestHandlerResult.of(
                                            notifyRequesterOtherOnlineSessionsOfMessageUpdated,
                                            recipientIds,
                                            clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfMessageUpdated,
                                clientRequest.turmsRequest()));
                    }));
        };
    }

    @ServiceRequestMapping(CREATE_MESSAGE_REACTIONS_REQUEST)
    public ClientRequestHandler handleCreateMessageReactionsRequest() {
        // The feature hasn't published yet, so the client has no API to use it.
        return clientRequest -> Mono
                .error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT));
    }

    @ServiceRequestMapping(DELETE_MESSAGE_REACTIONS_REQUEST)
    public ClientRequestHandler handleDeleteMessageReactionsRequest() {
        // The feature hasn't published yet, so the client has no API to use it.
        return clientRequest -> Mono
                .error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT));
    }

    /**
     * @param fromId group id or sender id
     */
    private record MessageFromKey(
            boolean isGroupMessage,
            long fromId
    ) {
    }
}