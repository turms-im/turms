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

package im.turms.service.workflow.access.servicerequest.controller;

import com.google.common.collect.Collections2;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.protobuf.ByteString;
import im.turms.common.model.bo.message.Messages;
import im.turms.common.model.bo.message.MessagesWithTotal;
import im.turms.common.model.bo.message.MessagesWithTotalList;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.message.CreateMessageRequest;
import im.turms.common.model.dto.request.message.QueryMessagesRequest;
import im.turms.common.model.dto.request.message.UpdateMessageRequest;
import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.property.constant.TimeType;
import im.turms.server.common.util.CollectorUtil;
import im.turms.service.bo.MessageFromKey;
import im.turms.service.util.ProtoModelUtil;
import im.turms.service.workflow.access.http.util.PageUtil;
import im.turms.service.workflow.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.workflow.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.workflow.dao.domain.message.Message;
import im.turms.service.workflow.service.impl.conversation.ConversationService;
import im.turms.service.workflow.service.impl.message.MessageService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.QUERY_MESSAGES_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.UPDATE_MESSAGE_REQUEST;

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
            CreateMessageRequest request = clientRequest.turmsRequest().getCreateMessageRequest();
            if (request.hasIsSystemMessage() && request.getIsSystemMessage()) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "Users cannot send the system message"));
            }
            Mono<Pair<Message, Set<Long>>> messageAndRelatedUserIdsMono;
            boolean isGroupMessage = request.hasGroupId();
            if (!isGroupMessage && !request.hasRecipientId()) {
                return Mono.error(TurmsBusinessException
                        .get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The recipientId must not be null for private messages"));
            }
            long targetId = isGroupMessage ? request.getGroupId() : request.getRecipientId();
            if (request.hasMessageId()) {
                messageAndRelatedUserIdsMono = messageService.authAndCloneAndSaveMessage(
                        clientRequest.userId(),
                        request.getMessageId(),
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
                Integer burnAfter = request.hasBurnAfter() ? request.getBurnAfter() : null;
                Date deliveryDate = new Date(request.getDeliveryDate());
                messageAndRelatedUserIdsMono = messageService.authAndSaveMessage(
                        null,
                        clientRequest.userId(),
                        targetId,
                        isGroupMessage,
                        false,
                        request.hasText() ? request.getText() : null,
                        records,
                        burnAfter,
                        deliveryDate,
                        null);
            }
            return messageAndRelatedUserIdsMono.map(pair -> {
                Message message = pair.getLeft();
                Long messageId = message == null ? null : message.getId();
                Set<Long> recipientIds = pair.getRight();
                boolean hasDataForRecipients = recipientIds != null && !recipientIds.isEmpty();
                if (messageId == null) {
                    if (hasDataForRecipients) {
                        TurmsRequest dataForRecipients = clientRequest.turmsRequest();
                        if (messageService.getTimeType() == TimeType.LOCAL_SERVER_TIME) {
                            dataForRecipients = clientRequest.turmsRequest().toBuilder()
                                    .setCreateMessageRequest(request.toBuilder().setDeliveryDate(System.currentTimeMillis()))
                                    .build();
                        }
                        return RequestHandlerResultFactory.get(
                                recipientIds,
                                dataForRecipients);
                    }
                } else {
                    if (!hasDataForRecipients) {
                        return RequestHandlerResultFactory.get(messageId);
                    }
                    TurmsRequest dataForRecipients;
                    if (request.hasMessageId()) {
                        dataForRecipients = clientRequest.turmsRequest()
                                .toBuilder()
                                .setCreateMessageRequest(ProtoModelUtil.cloneAndFillMessageRequest(request, message))
                                .build();
                    } else {
                        CreateMessageRequest.Builder requestBuilder = request.toBuilder()
                                .setMessageId(messageId);
                        if (messageService.getTimeType() == TimeType.LOCAL_SERVER_TIME) {
                            requestBuilder.setDeliveryDate(message.getDeliveryDate().getTime());
                        }
                        dataForRecipients = clientRequest.turmsRequest()
                                .toBuilder()
                                .setCreateMessageRequest(requestBuilder)
                                .build();
                    }
                    return RequestHandlerResultFactory.get(
                            messageId,
                            recipientIds,
                            node.getSharedProperties().getService().getMessage().isSendMessageToOtherSenderOnlineDevices(),
                            dataForRecipients);
                }
                return RequestHandlerResultFactory.get(TurmsStatusCode.OK);
            });
        };
    }

    @ServiceRequestMapping(QUERY_MESSAGES_REQUEST)
    public ClientRequestHandler handleQueryMessagesRequest() {
        return clientRequest -> {
            QueryMessagesRequest request = clientRequest.turmsRequest().getQueryMessagesRequest();
            List<Long> idList = request.getIdsCount() > 0 ? request.getIdsList() : null;
            Boolean areGroupMessages = request.hasAreGroupMessages() ? request.getAreGroupMessages() : null;
            Boolean areSystemMessages = request.hasAreSystemMessages() ? request.getAreSystemMessages() : null;
            Long fromId = request.hasFromId() ? request.getFromId() : null;
            Date deliveryDateAfter = request.hasDeliveryDateAfter() ? new Date(request.getDeliveryDateAfter()) : null;
            Date deliveryDateBefore = request.hasDeliveryDateBefore() && deliveryDateAfter == null ?
                    new Date(request.getDeliveryDateBefore()) : null;
            boolean withTotal = request.getWithTotal();
            Integer size;
            if (request.hasSize()) {
                size = pageUtil.getSize(request.getSize());
            } else {
                size = withTotal
                        ? node.getSharedProperties().getService().getMessage().getDefaultAvailableMessagesNumberWithTotal()
                        : null;
            }
            Long userId = clientRequest.userId();
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
                                Mono<MessagesWithTotal> messagesWithTotalMono = messageService.countMessages(
                                                null,
                                                senderKey.isGroupMessage(),
                                                null,
                                                Set.of(senderKey.fromId()),
                                                Set.of(clientRequest.userId()),
                                                dateRange,
                                                null)
                                        .map(total -> MessagesWithTotal.newBuilder()
                                                .setTotal(total.intValue())
                                                .setIsGroupMessage(senderKey.isGroupMessage())
                                                .setFromId(senderKey.fromId())
                                                .addAllMessages(
                                                        Collections2.transform(messages, m -> ProtoModelUtil.message2proto(m).build()))
                                                .build());
                                messagesWithTotalMonos.add(messagesWithTotalMono);
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
                                    .addAllMessages(Collections2.transform(messages, m -> ProtoModelUtil.message2proto(m).build()))
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
                                        : conversationService
                                        .upsertPrivateConversationReadDate(userId, messages.get(0).getTargetId(), new Date());
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
            UpdateMessageRequest request = clientRequest.turmsRequest().getUpdateMessageRequest();
            long messageId = request.getMessageId();
            String text = request.hasText() ? request.getText() : null;
            List<byte[]> records = null;
            if (request.getRecordsCount() > 0) {
                records = new ArrayList<>(request.getRecordsCount());
                for (ByteString byteString : request.getRecordsList()) {
                    records.add(byteString.toByteArray());
                }
            }
            Date recallDate = request.hasRecallDate() ? new Date(request.getRecallDate()) : null;
            return messageService.authAndUpdateMessage(
                            clientRequest.userId(),
                            messageId,
                            text,
                            records,
                            recallDate)
                    .then(Mono.defer(() -> {
                        if (node.getSharedProperties().getService().getNotification().isNotifyRecipientsAfterMessageUpdatedBySender()) {
                            return messageService.queryMessageRecipients(messageId)
                                    .collect(Collectors.toSet())
                                    .map(recipientIds -> recipientIds.isEmpty()
                                            ? RequestHandlerResultFactory.OK
                                            : RequestHandlerResultFactory.get(recipientIds, clientRequest.turmsRequest()));
                        }
                        return Mono.just(RequestHandlerResultFactory.OK);
                    }));
        };
    }

}
