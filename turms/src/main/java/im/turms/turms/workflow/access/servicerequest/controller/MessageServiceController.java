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

package im.turms.turms.workflow.access.servicerequest.controller;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.protobuf.ByteString;
import com.google.protobuf.Int64Value;
import im.turms.common.constant.MessageDeliveryStatus;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.bo.message.MessageStatuses;
import im.turms.common.model.bo.message.Messages;
import im.turms.common.model.bo.message.MessagesWithTotal;
import im.turms.common.model.bo.message.MessagesWithTotalList;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.message.*;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.property.constant.TimeType;
import im.turms.turms.bo.DateRange;
import im.turms.turms.bo.PendingMessageKey;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.turms.workflow.dao.domain.Message;
import im.turms.turms.workflow.service.impl.message.MessageService;
import im.turms.turms.workflow.service.impl.message.MessageStatusService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.*;

/**
 * @author James Chen
 */
@Controller
public class MessageServiceController {

    private final Node node;
    private final PageUtil pageUtil;
    private final MessageService messageService;
    private final MessageStatusService messageStatusService;

    public MessageServiceController(
            Node node,
            PageUtil pageUtil,
            MessageService messageService,
            MessageStatusService messageStatusService) {
        this.messageService = messageService;
        this.node = node;
        this.pageUtil = pageUtil;
        this.messageStatusService = messageStatusService;
    }

    @ServiceRequestMapping(CREATE_MESSAGE_REQUEST)
    public ClientRequestHandler handleCreateMessageRequest() {
        return clientRequest -> {
            CreateMessageRequest request = clientRequest.getTurmsRequest().getCreateMessageRequest();
            if (request.hasIsSystemMessage() && request.getIsSystemMessage().getValue()) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "Users cannot send the system message"));
            }
            Mono<Pair<Message, Set<Long>>> messageAndRelatedUserIdsMono;
            boolean isGroupMessage = request.hasGroupId();
            if (!isGroupMessage && !request.hasRecipientId()) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The recipientId must not be null for private messages"));
            }
            long targetId = isGroupMessage ? request.getGroupId().getValue() : request.getRecipientId().getValue();
            if (request.hasMessageId()) {
                messageAndRelatedUserIdsMono = messageService.authAndCloneAndSaveMessage(
                        clientRequest.getUserId(),
                        request.getMessageId().getValue(),
                        isGroupMessage,
                        false,
                        targetId);
            } else {
                List<byte[]> records = null;
                if (request.getRecordsCount() != 0) {
                    records = new ArrayList<>(request.getRecordsCount());
                    for (ByteString byteString : request.getRecordsList()) {
                        records.add(byteString.toByteArray());
                    }
                }
                Integer burnAfter = request.hasBurnAfter() ? request.getBurnAfter().getValue() : null;
                Date deliveryDate = new Date(request.getDeliveryDate());
                messageAndRelatedUserIdsMono = messageService.authAndSaveMessage(
                        null,
                        clientRequest.getUserId(),
                        targetId,
                        isGroupMessage,
                        false,
                        request.hasText() ? request.getText().getValue() : null,
                        records,
                        burnAfter,
                        deliveryDate,
                        null);
            }
            return messageAndRelatedUserIdsMono.map(pair -> {
                Message message = pair.getLeft();
                Long messageId = message != null ? message.getId() : null;
                Set<Long> recipientsIds = pair.getRight();
                boolean hasDataForRecipients = recipientsIds != null && !recipientsIds.isEmpty();
                if (messageId != null) {
                    if (!hasDataForRecipients) {
                        return RequestHandlerResultFactory.get(messageId);
                    }
                    TurmsRequest dataForRecipients;
                    if (request.hasMessageId()) {
                        dataForRecipients = clientRequest.getTurmsRequest()
                                .toBuilder()
                                .setCreateMessageRequest(ProtoUtil.cloneAndFillMessageRequest(request, message))
                                .build();
                    } else {
                        Int64Value.Builder messageIdBuilder = Int64Value.newBuilder().setValue(messageId);
                        CreateMessageRequest.Builder requestBuilder = request.toBuilder().setMessageId(messageIdBuilder);
                        if (messageService.getTimeType() == TimeType.LOCAL_SERVER_TIME) {
                            requestBuilder.setDeliveryDate(message.getDeliveryDate().getTime());
                        }
                        dataForRecipients = clientRequest.getTurmsRequest()
                                .toBuilder()
                                .setCreateMessageRequest(requestBuilder)
                                .build();
                    }
                    return RequestHandlerResultFactory.get(
                            messageId,
                            recipientsIds,
                            node.getSharedProperties().getService().getMessage().isSendMessageToOtherSenderOnlineDevices(),
                            dataForRecipients);
                } else if (hasDataForRecipients) {
                    TurmsRequest dataForRecipients = clientRequest.getTurmsRequest();
                    if (messageService.getTimeType() == TimeType.LOCAL_SERVER_TIME) {
                        dataForRecipients = clientRequest.getTurmsRequest().toBuilder()
                                .setCreateMessageRequest(request.toBuilder().setDeliveryDate(System.currentTimeMillis()))
                                .build();
                    }
                    return RequestHandlerResultFactory.get(
                            recipientsIds,
                            dataForRecipients);
                } else {
                    return RequestHandlerResultFactory.get(TurmsStatusCode.OK);
                }
            });
        };
    }

    @ServiceRequestMapping(QUERY_MESSAGE_STATUSES_REQUEST)
    public ClientRequestHandler handleQueryMessageStatusRequest() {
        return clientRequest -> {
            QueryMessageStatusesRequest request = clientRequest.getTurmsRequest().getQueryMessageStatusesRequest();
            return messageStatusService.queryMessageStatus(request.getMessageId())
                    .map(messageStatus -> {
                        MessageStatuses.Builder builder = MessageStatuses.newBuilder();
                        builder.addMessageStatuses(ProtoUtil.messageStatus2proto(messageStatus));
                        TurmsNotification.Data data = TurmsNotification.Data
                                .newBuilder()
                                .setMessageStatuses(builder)
                                .build();
                        return RequestHandlerResultFactory.get(data);
                    });
        };
    }

    @ServiceRequestMapping(QUERY_PENDING_MESSAGES_WITH_TOTAL_REQUEST)
    public ClientRequestHandler handleQueryPendingMessagesWithTotalRequest() {
        return clientRequest -> {
            QueryPendingMessagesWithTotalRequest request = clientRequest.getTurmsRequest().getQueryPendingMessagesWithTotalRequest();
            Multimap<PendingMessageKey, Message> multimap = LinkedListMultimap.create();
            Integer size = request.hasSize() ? request.getSize().getValue() : null;
            if (size == null) {
                size = node.getSharedProperties().getService().getMessage().getDefaultAvailableMessagesNumberWithTotal();
            }
            size = pageUtil.getSize(size);
            return messageService.queryMessages(
                    false, null, null, null, null,
                    Set.of(clientRequest.getUserId()), null, null,
                    Set.of(MessageDeliveryStatus.READY), 0, size)
                    .doOnNext(message -> {
                        Long targetId = message.getIsGroupMessage()
                                ? message.getTargetId()
                                : message.getSenderId();
                        PendingMessageKey pendingMessageKey = new PendingMessageKey(message.getIsGroupMessage(), message.getIsSystemMessage(), targetId);
                        multimap.put(pendingMessageKey, message);
                    })
                    .collectList()
                    .flatMap(messages -> {
                        if (messages.isEmpty()) {
                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT));
                        }
                        MessagesWithTotalList.Builder messagesWithTotalList = MessagesWithTotalList.newBuilder();
                        List<Mono<Long>> countMonos = new ArrayList<>(multimap.size());
                        for (PendingMessageKey key : multimap.keys()) {
                            countMonos.add(messageStatusService.countPendingMessages(key.isGroupMessage(),
                                    key.isSystemMessage(),
                                    key.getTargetId(),
                                    clientRequest.getUserId()));
                        }
                        return Mono.zip(countMonos, objects -> objects)
                                .map(numberObjects -> {
                                    Iterator<PendingMessageKey> keyIterator = multimap.keys().iterator();
                                    int count = multimap.keys().size();
                                    for (int i = 0; i < count; i++) {
                                        PendingMessageKey key = keyIterator.next();
                                        int number = ((Long) numberObjects[i]).intValue();
                                        MessagesWithTotal.Builder messagesWithTotalBuilder = MessagesWithTotal.newBuilder()
                                                .setTotal(number)
                                                .setIsGroupMessage(key.isGroupMessage())
                                                .setFromId(key.getTargetId());
                                        for (Message message : multimap.get(key)) {
                                            messagesWithTotalBuilder.addMessages(ProtoUtil.message2proto(message));
                                        }
                                        messagesWithTotalList.addMessagesWithTotalList(messagesWithTotalBuilder);
                                    }
                                    return RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                                            .setMessagesWithTotalList(messagesWithTotalList).build());
                                });
                    });
        };
    }

    @ServiceRequestMapping(QUERY_MESSAGES_REQUEST)
    public ClientRequestHandler handleQueryMessagesRequest() {
        return clientRequest -> {
            QueryMessagesRequest request = clientRequest.getTurmsRequest().getQueryMessagesRequest();
            List<Long> idList = request.getIdsCount() != 0 ? request.getIdsList() : null;
            Boolean areGroupMessages = request.hasAreGroupMessages() ? request.getAreGroupMessages().getValue() : null;
            Boolean areSystemMessages = request.hasAreSystemMessages() ? request.getAreSystemMessages().getValue() : null;
            Long fromId = request.hasFromId() ? request.getFromId().getValue() : null;
            Date deliveryDateAfter = request.hasDeliveryDateAfter() ? new Date(request.getDeliveryDateAfter().getValue()) : null;
            Date deliveryDateBefore = request.hasDeliveryDateBefore() && deliveryDateAfter == null ?
                    new Date(request.getDeliveryDateBefore().getValue()) : null;
            MessageDeliveryStatus deliveryStatus = null;
            if (request.getDeliveryStatus() == MessageDeliveryStatus.READY
                    || request.getDeliveryStatus() == MessageDeliveryStatus.RECEIVED
                    || request.getDeliveryStatus() == MessageDeliveryStatus.RECALLING) {
                deliveryStatus = request.getDeliveryStatus();
            }
            Integer size = request.hasSize() ? pageUtil.getSize(request.getSize().getValue()) : null;
            return messageService.authAndQueryCompleteMessages(
                    true,
                    idList,
                    areGroupMessages,
                    areSystemMessages,
                    fromId,
                    clientRequest.getUserId(),
                    DateRange.of(deliveryDateAfter, deliveryDateBefore),
                    null,
                    deliveryStatus,
                    0,
                    size)
                    .collectList()
                    .flatMap(messages -> {
                        if (messages.isEmpty()) {
                            return Mono.empty();
                        }
                        Messages.Builder messagesListBuilder = Messages.newBuilder();
                        for (Message message : messages) {
                            im.turms.common.model.bo.message.Message.Builder builder = ProtoUtil.message2proto(message);
                            messagesListBuilder.addMessages(builder);
                        }
                        Messages messagesList = messagesListBuilder.build();
                        TurmsNotification.Data data = TurmsNotification.Data.newBuilder()
                                .setMessages(messagesList)
                                .build();
                        Set<Long> messagesIds = Sets.newHashSetWithExpectedSize(messages.size());
                        for (Message message : messages) {
                            messagesIds.add(message.getId());
                        }
                        return Mono.just(RequestHandlerResultFactory.get(data))
                                .flatMap(response -> messageStatusService.acknowledge(messagesIds)
                                        .thenReturn(response));
                    });
        };
    }

    @ServiceRequestMapping(UPDATE_MESSAGE_REQUEST)
    public ClientRequestHandler handleUpdateMessageRequest() {
        return clientRequest -> {
            UpdateMessageRequest request = clientRequest.getTurmsRequest().getUpdateMessageRequest();
            if (request.hasIsSystemMessage() && request.getIsSystemMessage().getValue()) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "Users cannot create system messages"));
            }
            long messageId = request.getMessageId();
            if (request.hasReadDate()) {
                long readDateValue = request.getReadDate().getValue();
                Date readDate = readDateValue > 0 ? new Date(readDateValue) : null;
                return authAndUpdateMessageReadDate(
                        clientRequest.getUserId(),
                        messageId,
                        readDate)
                        .flatMap(updatedOrDeleted -> {
                            if (updatedOrDeleted == null || !updatedOrDeleted) {
                                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.RESOURCES_HAVE_CHANGED));
                            } else {
                                return node.getSharedProperties().getService().getNotification().isNotifySenderAfterReadStatusUpdatedByRecipients()
                                        ? messageService.queryMessageSenderId(messageId)
                                        .flatMap(senderId -> Mono.just(RequestHandlerResultFactory.get(
                                                senderId,
                                                clientRequest.getTurmsRequest(),
                                                TurmsStatusCode.OK)))
                                        : Mono.just(RequestHandlerResultFactory.ok());
                            }
                        });
            } else {
                String text = request.hasText() ? request.getText().getValue() : null;
                List<byte[]> records = null;
                if (request.getRecordsCount() != 0) {
                    records = new ArrayList<>(request.getRecordsCount());
                    for (ByteString byteString : request.getRecordsList()) {
                        records.add(byteString.toByteArray());
                    }
                }
                Date recallDate = request.hasRecallDate() ? new Date(request.getRecallDate().getValue()) : null;
                return messageService.authAndUpdateMessageAndMessageStatus(
                        clientRequest.getUserId(),
                        messageId,
                        null,
                        text,
                        records,
                        recallDate,
                        null)
                        .flatMap(wasSuccessful -> {
                            if (node.getSharedProperties().getService().getNotification().isNotifyRecipientsAfterMessageUpdatedBySender()) {
                                return messageService.queryMessageRecipients(messageId)
                                        .collect(Collectors.toSet())
                                        .map(recipientsIds -> recipientsIds.isEmpty()
                                                ? RequestHandlerResultFactory.ok()
                                                : RequestHandlerResultFactory.get(recipientsIds, clientRequest.getTurmsRequest()));
                            } else {
                                return Mono.just(RequestHandlerResultFactory.ok());
                            }
                        });
            }
        };
    }

    /**
     * To save a lot of resources, allow sending typing status to recipients without checking their relationships.
     */
    @ServiceRequestMapping(UPDATE_TYPING_STATUS_REQUEST)
    public ClientRequestHandler handleUpdateTypingStatusRequest() {
        return clientRequest -> {
            if (node.getSharedProperties().getService().getMessage().getTypingStatus().isEnabled()) {
                UpdateTypingStatusRequest request = clientRequest.getTurmsRequest()
                        .getUpdateTypingStatusRequest();
                return Mono.just(RequestHandlerResultFactory.get(
                        request.getToId(),
                        clientRequest.getTurmsRequest(),
                        TurmsStatusCode.OK));
            } else {
                return Mono.just(RequestHandlerResultFactory.get(TurmsStatusCode.DISABLED_FUNCTION));
            }
        };
    }

    private Mono<Boolean> authAndUpdateMessageReadDate(
            @NotNull Long userId,
            @NotNull Long messageId,
            @Nullable Date readDate) {
        return messageService.isMessageSentToUser(messageId, userId)
                .flatMap(authenticated -> {
                    if (authenticated == null || !authenticated) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                    Date date = null;
                    if (readDate != null) {
                        date = node.getSharedProperties().getService().getMessage()
                                .getReadReceipt().isUseServerTime()
                                ? new Date()
                                : readDate;
                    } // else unset the read date
                    return messageStatusService.updateMessagesReadDate(
                            messageId,
                            date);
                });
    }

}
