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

package im.turms.service.domain.conversation.access.servicerequest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest;
import im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest;
import im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.infra.property.env.service.business.notification.NotificationProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationGroupConversationReadDateUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationPrivateConversationReadDateUpdatedProperties;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.conversation.service.ConversationService;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.infra.proto.ProtoModelConvertor;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_CONVERSATIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_CONVERSATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_TYPING_STATUS_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class ConversationServiceController extends BaseServiceController {

    private final ConversationService conversationService;
    private final GroupMemberService groupMemberService;

    private boolean notifyRequesterOtherOnlineSessionsOfPrivateConversationReadDateUpdated;
    private boolean notifyContactOfPrivateConversationReadDateUpdated;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupConversationReadDateUpdated;
    private boolean notifyOtherGroupMembersOfGroupConversationReadDateUpdated;

    public ConversationServiceController(
            TurmsPropertiesManager propertiesManager,
            ConversationService conversationService,
            GroupMemberService groupMemberService) {
        this.conversationService = conversationService;
        this.groupMemberService = groupMemberService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        ServiceProperties serviceProperties = properties.getService();
        NotificationProperties notificationProperties = serviceProperties.getNotification();

        NotificationPrivateConversationReadDateUpdatedProperties privateConversationReadDateUpdatedProperties =
                notificationProperties.getPrivateConversationReadDateUpdated();
        notifyRequesterOtherOnlineSessionsOfPrivateConversationReadDateUpdated =
                privateConversationReadDateUpdatedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyContactOfPrivateConversationReadDateUpdated =
                privateConversationReadDateUpdatedProperties.isNotifyContact();

        NotificationGroupConversationReadDateUpdatedProperties groupConversationReadDateUpdatedProperties =
                notificationProperties.getGroupConversationReadDateUpdated();
        notifyRequesterOtherOnlineSessionsOfGroupConversationReadDateUpdated =
                groupConversationReadDateUpdatedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyOtherGroupMembersOfGroupConversationReadDateUpdated =
                groupConversationReadDateUpdatedProperties.isNotifyOtherGroupMembers();
    }

    @ServiceRequestMapping(QUERY_CONVERSATIONS_REQUEST)
    public ClientRequestHandler handleQueryConversationsRequest() {
        return clientRequest -> {
            QueryConversationsRequest request = clientRequest.turmsRequest()
                    .getQueryConversationsRequest();
            List<Long> targetIds = request.getTargetIdsList();
            Mono<TurmsNotification.Data> dataFlux;
            if (targetIds.isEmpty()) {
                List<Long> groupIds = request.getGroupIdsList();
                if (groupIds.isEmpty()) {
                    return Mono.just(RequestHandlerResult.NO_CONTENT);
                }
                dataFlux = conversationService.queryGroupConversations(groupIds)
                        .map(conversation -> ProtoModelConvertor
                                .groupConversations2proto(conversation)
                                .build())
                        .collect(CollectorUtil.toList(targetIds.size()))
                        .map(conversations -> ClientMessagePool.getTurmsNotificationDataBuilder()
                                .setConversations(ClientMessagePool.getConversationsBuilder()
                                        .addAllGroupConversations(conversations))
                                .build());
            } else {
                dataFlux = conversationService
                        .queryPrivateConversations(targetIds, clientRequest.userId())
                        .map(conversation -> ProtoModelConvertor
                                .privateConversation2proto(conversation)
                                .build())
                        .collect(CollectorUtil.toList(targetIds.size()))
                        .map(conversations -> ClientMessagePool.getTurmsNotificationDataBuilder()
                                .setConversations(ClientMessagePool.getConversationsBuilder()
                                        .addAllPrivateConversations(conversations))
                                .build());
            }
            return dataFlux.map(RequestHandlerResult::of);
        };
    }

    @ServiceRequestMapping(UPDATE_TYPING_STATUS_REQUEST)
    public ClientRequestHandler handleUpdateTypingStatusRequest() {
        return clientRequest -> {
            TurmsRequest turmsRequest = clientRequest.turmsRequest();
            UpdateTypingStatusRequest request = turmsRequest.getUpdateTypingStatusRequest();
            return conversationService
                    .authAndUpdateTypingStatus(clientRequest.userId(),
                            request.getIsGroupMessage(),
                            request.getToId())
                    .map(recipientIds -> RequestHandlerResult.of(recipientIds, turmsRequest));
        };
    }

    @ServiceRequestMapping(UPDATE_CONVERSATION_REQUEST)
    public ClientRequestHandler handleUpdateConversationRequest() {
        return clientRequest -> {
            UpdateConversationRequest request = clientRequest.turmsRequest()
                    .getUpdateConversationRequest();
            if (!request.hasTargetId() && !request.hasGroupId()) {
                return Mono.just(RequestHandlerResult.of(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The targetId and groupId must not both be null"));
            }
            Long requesterId = clientRequest.userId();
            // todo: limit read date range
            Date readDate = new Date(request.getReadDate());
            boolean isUpdatePrivateConversationRequest = request.hasTargetId();
            long targetId;
            Mono<Void> mono;
            if (isUpdatePrivateConversationRequest) {
                targetId = request.getTargetId();
                mono = conversationService
                        .authAndUpsertPrivateConversationReadDate(requesterId, targetId, readDate);
            } else {
                targetId = request.getGroupId();
                mono = conversationService
                        .authAndUpsertGroupConversationReadDate(targetId, requesterId, readDate);
            }
            return mono.then(Mono.defer(() -> {
                if (isUpdatePrivateConversationRequest) {
                    if (notifyContactOfPrivateConversationReadDateUpdated) {
                        return Mono.just(RequestHandlerResult.of(
                                notifyRequesterOtherOnlineSessionsOfPrivateConversationReadDateUpdated,
                                targetId,
                                clientRequest.turmsRequest()));
                    }
                    return Mono.just(RequestHandlerResult.of(
                            notifyRequesterOtherOnlineSessionsOfPrivateConversationReadDateUpdated,
                            clientRequest.turmsRequest()));
                } else if (notifyOtherGroupMembersOfGroupConversationReadDateUpdated) {
                    return groupMemberService.queryGroupMemberIds(targetId, true)
                            .map(memberIds -> RequestHandlerResult.of(
                                    notifyRequesterOtherOnlineSessionsOfGroupConversationReadDateUpdated,
                                    memberIds,
                                    clientRequest.turmsRequest()));
                }
                return Mono.just(RequestHandlerResult.of(
                        notifyRequesterOtherOnlineSessionsOfGroupConversationReadDateUpdated,
                        clientRequest.turmsRequest()));
            }));
        };
    }

}