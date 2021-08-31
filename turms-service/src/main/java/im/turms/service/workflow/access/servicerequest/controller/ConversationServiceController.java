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

import im.turms.common.model.bo.conversation.Conversations;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.conversation.QueryConversationsRequest;
import im.turms.common.model.dto.request.conversation.UpdateConversationRequest;
import im.turms.common.model.dto.request.conversation.UpdateTypingStatusRequest;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.util.CollectorUtil;
import im.turms.service.util.ProtoModelUtil;
import im.turms.service.workflow.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.workflow.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.workflow.service.impl.conversation.ConversationService;
import im.turms.service.workflow.service.impl.group.GroupMemberService;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.QUERY_CONVERSATIONS_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.UPDATE_CONVERSATION_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.UPDATE_TYPING_STATUS_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class ConversationServiceController {

    private final Node node;
    private final ConversationService conversationService;
    private final GroupMemberService groupMemberService;

    public ConversationServiceController(
            Node node,
            ConversationService conversationService,
            GroupMemberService groupMemberService) {
        this.node = node;
        this.conversationService = conversationService;
        this.groupMemberService = groupMemberService;
    }

    @ServiceRequestMapping(QUERY_CONVERSATIONS_REQUEST)
    public ClientRequestHandler handleQueryConversationsRequest() {
        return clientRequest -> {
            QueryConversationsRequest request = clientRequest.turmsRequest()
                    .getQueryConversationsRequest();
            List<Long> targetIds = request.getTargetIdsList();
            Mono<TurmsNotification.Data> dataFlux;
            if (!targetIds.isEmpty()) {
                dataFlux = conversationService.queryPrivateConversations(clientRequest.userId(), targetIds)
                        .map(conversation -> ProtoModelUtil.privateConversation2proto(conversation).build())
                        .collect(CollectorUtil.toList(targetIds.size()))
                        .map(conversations -> TurmsNotification.Data
                                .newBuilder()
                                .setConversations(Conversations.newBuilder()
                                        .addAllPrivateConversations(conversations)
                                        .build())
                                .build());
            } else {
                List<Long> groupIds = request.getGroupIdsList();
                if (groupIds.isEmpty()) {
                    return Mono.just(RequestHandlerResultFactory.NO_CONTENT);
                } else {
                    dataFlux = conversationService.queryGroupConversations(groupIds)
                            .map(conversation -> ProtoModelUtil.groupConversations2proto(conversation).build())
                            .collect(CollectorUtil.toList(targetIds.size()))
                            .map(conversations -> TurmsNotification.Data
                                    .newBuilder()
                                    .setConversations(Conversations.newBuilder()
                                            .addAllGroupConversations(conversations)
                                            .build())
                                    .build());
                }
            }
            return dataFlux.map(RequestHandlerResultFactory::get);
        };
    }

    /**
     * Allow sending typing status to recipients without checking their relationships for better performance.
     * The application itself should check their relationships on the client side.
     *
     * @implNote No need to authenticate because:
     * 1. Most requests are authenticated indeed, so avoid frequent authentication to improve the performance greatly;
     * 2. For unauthenticated requests, it's very easy for client to ignore it according to its local data.
     * And the user won't be aware of these requests
     */
    @ServiceRequestMapping(UPDATE_TYPING_STATUS_REQUEST)
    public ClientRequestHandler handleUpdateTypingStatusRequest() {
        return clientRequest -> {
            if (!node.getSharedProperties().getService().getConversation().getTypingStatus().isEnabled()) {
                return Mono.just(RequestHandlerResultFactory.get(TurmsStatusCode.UPDATING_TYPING_STATUS_IS_DISABLED));
            }
            UpdateTypingStatusRequest request = clientRequest.turmsRequest()
                    .getUpdateTypingStatusRequest();
            return Mono.just(RequestHandlerResultFactory.get(
                    request.getToId(),
                    clientRequest.turmsRequest(),
                    TurmsStatusCode.OK));
        };
    }

    @ServiceRequestMapping(UPDATE_CONVERSATION_REQUEST)
    public ClientRequestHandler handleUpdateConversationRequest() {
        return clientRequest -> {
            UpdateConversationRequest request = clientRequest.turmsRequest()
                    .getUpdateConversationRequest();
            if (!request.hasTargetId() && !request.hasGroupId()) {
                return Mono.just(RequestHandlerResultFactory
                        .get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The targetId and groupId must not all null"));
            }
            Long requesterId = clientRequest.userId();
            long readDateValue = request.getReadDate();
            Date readDate = new Date(readDateValue);
            boolean isUpdatePrivateConversationRequest = request.hasTargetId();
            long targetId;
            Mono<Void> mono;
            if (isUpdatePrivateConversationRequest) {
                targetId = request.getTargetId();
                mono = conversationService.authAndUpsertPrivateConversationReadDate(requesterId, targetId, readDate);
            } else {
                targetId = request.getGroupId();
                mono = conversationService.authAndUpsertGroupConversationReadDate(targetId, requesterId, readDate);
            }
            return mono.then(Mono.defer(() -> {
                if (isUpdatePrivateConversationRequest) {
                    if (node.getSharedProperties().getService().getNotification()
                            .isNotifyPrivateConversationParticipantAfterReadDateUpdated()) {
                        return Mono.just(RequestHandlerResultFactory.get(
                                targetId,
                                clientRequest.turmsRequest(),
                                TurmsStatusCode.OK));
                    }
                } else if (node.getSharedProperties().getService().getNotification()
                        .isNotifyGroupConversationParticipantsAfterReadDateUpdated()) {
                    return groupMemberService.queryGroupMemberIds(targetId)
                            .collect(CollectorUtil.toSet(50))
                            .map(memberIds -> RequestHandlerResultFactory.get(
                                    memberIds,
                                    clientRequest.turmsRequest()));
                }
                return Mono.just(RequestHandlerResultFactory.OK);
            }));
        };
    }

}