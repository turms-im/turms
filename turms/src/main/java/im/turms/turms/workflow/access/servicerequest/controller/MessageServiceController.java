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

import com.google.common.collect.Collections2;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.protobuf.ByteString;
import com.google.protobuf.Int64Value;
import im.turms.common.model.bo.message.Messages;
import im.turms.common.model.bo.message.MessagesWithTotal;
import im.turms.common.model.bo.message.MessagesWithTotalList;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.message.CreateMessageRequest;
import im.turms.common.model.dto.request.message.QueryMessagesRequest;
import im.turms.common.model.dto.request.message.UpdateMessageRequest;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.property.constant.TimeType;
import im.turms.server.common.util.CollectorUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.bo.MessageFromKey;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResult;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.turms.workflow.dao.domain.message.Message;
import im.turms.turms.workflow.service.impl.conversation.ConversationService;
import im.turms.turms.workflow.service.impl.message.MessageService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    private final ConversationService conversationService;

    public MessageServiceController(
            Node node,
            PageUtil pageUtil,
            MessageService messageService,
            ConversationService conversationService) {
        this.messageService = messageService;
        this.node = node;
        this.pageUtil = pageUtil;
        this.conversationService = conversationService;
    }

    @ServiceRequestMapping(CREATE_MESSAGE_REQUEST)
    public ClientRequestHandler handleCreateMessageRequest() {
        return clientRequest -> {
            CreateMessageRequest request = clientRequest.getTurmsRequest().getCreateMessageRequest();
            if (request.hasIsSystemMessage() && request.getIsSystemMessage().getValue()) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "Users cannot send the system message"));
            }
            Mono<Pair<Message, Set<Long>>> messageAndRelatedUserIdsMono;
            boolean isGroupMessage = request.hasGroupId();
            if (!isGroupMessage && !request.hasRecipientId()) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The recipientId must not be null for private messages"));
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
                        CreateMessageRequest.Builder requestBuilder = request.toBuilder()
                                .setMessageId(Int64Value.of(messageId));
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

    @ServiceRequestMapping(QUERY_MESSAGES_REQUEST)
    public ClientRequestHandler handleQueryMessagesRequest() {
        return clientRequest -> {
            QueryMessagesRequest request = clientRequest.getTurmsRequest().getQueryMessagesRequest();
            List<Long> idList = request.getIdsCount() > 0 ? request.getIdsList() : null;
            Boolean areGroupMessages = request.hasAreGroupMessages() ? request.getAreGroupMessages().getValue() : null;
            Boolean areSystemMessages = request.hasAreSystemMessages() ? request.getAreSystemMessages().getValue() : null;
            Long fromId = request.hasFromId() ? request.getFromId().getValue() : null;
            Date deliveryDateAfter = request.hasDeliveryDateAfter() ? new Date(request.getDeliveryDateAfter().getValue()) : null;
            Date deliveryDateBefore = request.hasDeliveryDateBefore() && deliveryDateAfter == null ?
                    new Date(request.getDeliveryDateBefore().getValue()) : null;
            boolean withTotal = request.getWithTotal();
            Integer size;
            if (request.hasSize()) {
                size = pageUtil.getSize(request.getSize().getValue());
            } else {
                size = withTotal
                        ? node.getSharedProperties().getService().getMessage().getDefaultAvailableMessagesNumberWithTotal()
                        : null;
            }
            Long userId = clientRequest.getUserId();
            DateRange dateRange = DateRange.of(deliveryDateAfter, deliveryDateBefore);
            return messageService.authAndQueryCompleteMessages(
                    true,
                    idList,
                    areGroupMessages,
                    areSystemMessages,
                    fromId,
                    userId,
                    dateRange,
                    null,
                    0,
                    size)
                    .collectList()
                    .flatMap(messages -> {
                        if (messages.isEmpty()) {
                            return Mono.empty();
                        }
                        Mono<TurmsNotification.Data> dataMono;
                        if (withTotal) {
                            Multimap<MessageFromKey, Message> conversationWithMessagesMap = LinkedListMultimap.create();
                            for (Message message : messages) {
                                Long targetId = message.getIsGroupMessage()
                                        ? message.getTargetId()
                                        : message.getSenderId();
                                MessageFromKey senderKey = new MessageFromKey(message.getIsGroupMessage(), targetId);
                                conversationWithMessagesMap.put(senderKey, message);
                            }
                            List<Mono<MessagesWithTotal>> messagesWithTotalMonos = new ArrayList<>(conversationWithMessagesMap.size());
                            for (Map.Entry<MessageFromKey, Collection<Message>> entry : conversationWithMessagesMap.asMap().entrySet()) {
                                MessageFromKey senderKey = entry.getKey();
                                Mono<MessagesWithTotal> messsagesWithTotalMono = messageService.countMessages(
                                        null,
                                        senderKey.isGroupMessage(),
                                        null,
                                        Set.of(senderKey.getFromId()),
                                        Set.of(clientRequest.getUserId()),
                                        dateRange,
                                        null)
                                        .map(total -> MessagesWithTotal.newBuilder()
                                                .setTotal(total.intValue())
                                                .setIsGroupMessage(senderKey.isGroupMessage())
                                                .setFromId(senderKey.getFromId())
                                                .addAllMessages(Collections2.transform(messages, m -> ProtoUtil.message2proto(m).build()))
                                                .build());
                                messagesWithTotalMonos.add(messsagesWithTotalMono);
                            }
                            dataMono = Flux.merge(messagesWithTotalMonos)
                                    .collect(CollectorUtil.toList(messagesWithTotalMonos.size()))
                                    .map(messagesWithTotals -> {
                                        MessagesWithTotalList.Builder messagesWithTotalList = MessagesWithTotalList.newBuilder();
                                        messagesWithTotalList.addAllMessagesWithTotalList(messagesWithTotals);
                                        return TurmsNotification.Data.newBuilder()
                                                .setMessagesWithTotalList(messagesWithTotalList).build();
                                    });
                        } else {
                            Messages messagesList = Messages
                                    .newBuilder()
                                    .addAllMessages(Collections2.transform(messages, m -> ProtoUtil.message2proto(m).build()))
                                    .build();
                            TurmsNotification.Data data = TurmsNotification.Data.newBuilder()
                                    .setMessages(messagesList)
                                    .build();
                            dataMono = Mono.just(data);
                        }
                        Mono<RequestHandlerResult> resultMono = dataMono.map(RequestHandlerResultFactory::get);
                        if (node.getSharedProperties().getService().getConversation()
                                .getReadReceipt()
                                .isUpdateReadDateWhenUserQueryingMessage()) {
                            resultMono = resultMono.doOnSuccess(ignored -> {
                                Mono<Void> mono = areGroupMessages
                                        ? conversationService.upsertGroupConversationReadDate(messages.get(0).groupId(), userId, new Date())
                                        : conversationService.upsertPrivateConversationReadDate(userId, messages.get(0).getTargetId(), new Date());
                                mono.subscribe();
                            });
                        }
                        return resultMono;
                    });
        };
    }

    @ServiceRequestMapping(UPDATE_MESSAGE_REQUEST)
    public ClientRequestHandler handleUpdateMessageRequest() {
        return clientRequest -> {
            UpdateMessageRequest request = clientRequest.getTurmsRequest().getUpdateMessageRequest();
            long messageId = request.getMessageId();
            String text = request.hasText() ? request.getText().getValue() : null;
            List<byte[]> records = null;
            if (request.getRecordsCount() > 0) {
                records = new ArrayList<>(request.getRecordsCount());
                for (ByteString byteString : request.getRecordsList()) {
                    records.add(byteString.toByteArray());
                }
            }
            Date recallDate = request.hasRecallDate() ? new Date(request.getRecallDate().getValue()) : null;
            return messageService.authAndUpdateMessage(
                    clientRequest.getUserId(),
                    messageId,
                    text,
                    records,
                    recallDate)
                    .then(Mono.defer(() -> {
                        if (node.getSharedProperties().getService().getNotification().isNotifyRecipientsAfterMessageUpdatedBySender()) {
                            return messageService.queryMessageRecipients(messageId)
                                    .collect(Collectors.toSet())
                                    .map(recipientsIds -> recipientsIds.isEmpty()
                                            ? RequestHandlerResultFactory.OK
                                            : RequestHandlerResultFactory.get(recipientsIds, clientRequest.getTurmsRequest()));
                        } else {
                            return Mono.just(RequestHandlerResultFactory.OK);
                        }
                    }));
        };
    }

}
