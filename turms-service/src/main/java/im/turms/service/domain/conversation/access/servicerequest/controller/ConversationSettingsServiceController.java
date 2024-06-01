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
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.conversation.DeleteConversationSettingsRequest;
import im.turms.server.common.access.client.dto.request.conversation.QueryConversationSettingsRequest;
import im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.infra.property.env.service.business.notification.NotificationProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationGroupConversationSettingDeletedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationGroupConversationSettingUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationPrivateConversationSettingDeletedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationPrivateConversationSettingUpdatedProperties;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.conversation.po.ConversationSettings;
import im.turms.service.domain.conversation.service.ConversationSettingsService;
import im.turms.service.infra.proto.ProtoModelConvertor;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_CONVERSATION_SETTINGS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_CONVERSATION_SETTINGS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_CONVERSATION_SETTINGS_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class ConversationSettingsServiceController extends BaseServiceController {

    private final ConversationSettingsService conversationSettingsService;

    private boolean notifyRequesterOtherOnlineSessionsOfPrivateConversationSettingDeleted;
    private boolean notifyRequesterOtherOnlineSessionsOfPrivateConversationSettingUpdated;

    private boolean notifyRequesterOtherOnlineSessionsOfGroupConversationSettingDeleted;
    private boolean notifyRequesterOtherOnlineSessionsOfGroupConversationSettingUpdated;

    public ConversationSettingsServiceController(
            TurmsPropertiesManager propertiesManager,
            ConversationSettingsService conversationSettingsService) {
        this.conversationSettingsService = conversationSettingsService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        ServiceProperties serviceProperties = properties.getService();
        NotificationProperties notificationProperties = serviceProperties.getNotification();

        NotificationPrivateConversationSettingDeletedProperties privateConversationSettingDeletedProperties =
                notificationProperties.getPrivateConversationSettingDeleted();
        notifyRequesterOtherOnlineSessionsOfPrivateConversationSettingDeleted =
                privateConversationSettingDeletedProperties.isNotifyRequesterOtherOnlineSessions();

        NotificationPrivateConversationSettingUpdatedProperties privateConversationSettingUpdatedProperties =
                notificationProperties.getPrivateConversationSettingUpdated();
        notifyRequesterOtherOnlineSessionsOfPrivateConversationSettingUpdated =
                privateConversationSettingUpdatedProperties.isNotifyRequesterOtherOnlineSessions();

        NotificationGroupConversationSettingUpdatedProperties groupConversationSettingUpdatedProperties =
                notificationProperties.getGroupConversationSettingUpdated();
        notifyRequesterOtherOnlineSessionsOfGroupConversationSettingUpdated =
                groupConversationSettingUpdatedProperties.isNotifyRequesterOtherOnlineSessions();

        NotificationGroupConversationSettingDeletedProperties groupConversationSettingDeletedProperties =
                notificationProperties.getGroupConversationSettingDeleted();
        notifyRequesterOtherOnlineSessionsOfGroupConversationSettingDeleted =
                groupConversationSettingDeletedProperties.isNotifyRequesterOtherOnlineSessions();
    }

    @ServiceRequestMapping(UPDATE_CONVERSATION_SETTINGS_REQUEST)
    public ClientRequestHandler handleUpdateConversationSettingsRequest() {
        return clientRequest -> {
            UpdateConversationSettingsRequest request = clientRequest.turmsRequest()
                    .getUpdateConversationSettingsRequest();
            boolean hasUserId = request.hasUserId();
            if (!hasUserId && !request.hasGroupId()) {
                return Mono.just(RequestHandlerResult.of(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The userId and groupId must not both be null"));
            }
            if (hasUserId) {
                return conversationSettingsService
                        .upsertPrivateConversationSettings(clientRequest.userId(),
                                request.getUserId(),
                                request.getSettingsMap())
                        .map(updated -> updated
                                ? RequestHandlerResult.of(
                                        notifyRequesterOtherOnlineSessionsOfPrivateConversationSettingUpdated
                                                && updated,
                                        clientRequest.turmsRequest())
                                : RequestHandlerResult.OK);
            } else {
                return conversationSettingsService
                        .upsertGroupConversationSettings(clientRequest.userId(),
                                request.getGroupId(),
                                request.getSettingsMap())
                        .map(updated -> updated
                                ? RequestHandlerResult.of(
                                        notifyRequesterOtherOnlineSessionsOfGroupConversationSettingUpdated
                                                && updated,
                                        clientRequest.turmsRequest())
                                : RequestHandlerResult.OK);
            }
        };
    }

    @ServiceRequestMapping(DELETE_CONVERSATION_SETTINGS_REQUEST)
    public ClientRequestHandler handleDeleteConversationSettingsRequest() {
        return clientRequest -> {
            TurmsRequest turmsRequest = clientRequest.turmsRequest();
            DeleteConversationSettingsRequest request =
                    turmsRequest.getDeleteConversationSettingsRequest();
            boolean hasUserId = request.getUserIdsCount() > 0;
            boolean hasGroupId = request.getGroupIdsCount() > 0;
            return conversationSettingsService.unsetSettings(clientRequest.userId(),
                    hasUserId
                            ? CollectionUtil.newSet(request.getUserIdsList())
                            : null,
                    hasGroupId
                            ? CollectionUtil.newSet(request.getGroupIdsList())
                            : null,
                    request.getNamesCount() > 0
                            ? CollectionUtil.newSet(request.getNamesList())
                            : null)
                    .map(deleted -> RequestHandlerResult.of(deleted
                            && ((hasUserId
                                    && notifyRequesterOtherOnlineSessionsOfPrivateConversationSettingDeleted)
                                    || (hasGroupId
                                            && notifyRequesterOtherOnlineSessionsOfGroupConversationSettingDeleted)),
                            turmsRequest));
        };
    }

    @ServiceRequestMapping(QUERY_CONVERSATION_SETTINGS_REQUEST)
    public ClientRequestHandler handleQueryConversationSettingsRequest() {
        return clientRequest -> {
            QueryConversationSettingsRequest request = clientRequest.turmsRequest()
                    .getQueryConversationSettingsRequest();
            Recyclable<List<ConversationSettings>> recyclableList = ListRecycler.obtain();
            return conversationSettingsService
                    .querySettings(clientRequest.userId(),
                            request.getUserIdsCount() > 0
                                    ? CollectionUtil.newSet(request.getUserIdsList())
                                    : null,
                            request.getGroupIdsCount() > 0
                                    ? CollectionUtil.newSet(request.getGroupIdsList())
                                    : null,
                            request.getNamesCount() > 0
                                    ? CollectionUtil.newSet(request.getNamesList())
                                    : null,
                            request.hasLastUpdatedDateStart()
                                    ? new Date(request.getLastUpdatedDateStart())
                                    : null)
                    .collect(Collectors.toCollection(recyclableList::getValue))
                    .map(settingsList -> {
                        ConversationSettingsList.Builder builder =
                                ClientMessagePool.getConversationSettingsListBuilder();
                        for (ConversationSettings settings : settingsList) {
                            builder.addConversationSettingsList(
                                    ProtoModelConvertor.conversationSettings2proto(settings));
                        }
                        return RequestHandlerResult
                                .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setConversationSettingsList(builder)
                                        .build());
                    })
                    .doFinally(signalType -> recyclableList.recycle());
        };
    }
}