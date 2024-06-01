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

package im.turms.service.domain.user.access.servicerequest.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.user.DeleteUserSettingsRequest;
import im.turms.server.common.access.client.dto.request.user.QueryUserSettingsRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserSettingsRequest;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.business.notification.NotificationProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationUserSettingDeletedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationUserSettingUpdatedProperties;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.user.service.UserSettingsService;
import im.turms.service.infra.proto.ProtoModelConvertor;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_USER_SETTINGS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_USER_SETTINGS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_USER_SETTINGS_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class UserSettingsServiceController extends BaseServiceController {

    private final UserSettingsService userSettingsService;

    private boolean notifyRequesterOtherOnlineSessionsOfUserSettingDeleted;
    private boolean notifyRequesterOtherOnlineSessionsOfUserSettingUpdated;

    public UserSettingsServiceController(
            TurmsPropertiesManager propertiesManager,
            UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        NotificationProperties notificationProperties = properties.getService()
                .getNotification();

        NotificationUserSettingDeletedProperties notificationUserSettingDeletedProperties =
                notificationProperties.getUserSettingDeleted();
        notifyRequesterOtherOnlineSessionsOfUserSettingDeleted =
                notificationUserSettingDeletedProperties.isNotifyRequesterOtherOnlineSessions();

        NotificationUserSettingUpdatedProperties notificationUserSettingUpdatedProperties =
                notificationProperties.getUserSettingUpdated();
        notifyRequesterOtherOnlineSessionsOfUserSettingUpdated =
                notificationUserSettingUpdatedProperties.isNotifyRequesterOtherOnlineSessions();
    }

    @ServiceRequestMapping(DELETE_USER_SETTINGS_REQUEST)
    public ClientRequestHandler handleDeleteUserSettingsRequest() {
        return clientRequest -> {
            TurmsRequest turmsRequest = clientRequest.turmsRequest();
            DeleteUserSettingsRequest request = turmsRequest.getDeleteUserSettingsRequest();
            return userSettingsService
                    .unsetSettings(clientRequest.userId(),
                            request.getNamesCount() > 0
                                    ? CollectionUtil.newSet(request.getNamesList())
                                    : null)
                    .map(deleted -> RequestHandlerResult.of(
                            notifyRequesterOtherOnlineSessionsOfUserSettingDeleted && deleted,
                            turmsRequest));
        };
    }

    @ServiceRequestMapping(UPDATE_USER_SETTINGS_REQUEST)
    public ClientRequestHandler handleUpdateUserSettingsRequest() {
        return clientRequest -> {
            UpdateUserSettingsRequest request = clientRequest.turmsRequest()
                    .getUpdateUserSettingsRequest();
            return userSettingsService
                    .upsertSettings(clientRequest.userId(), request.getSettingsMap())
                    .map(updated -> updated
                            ? RequestHandlerResult
                                    .of(notifyRequesterOtherOnlineSessionsOfUserSettingUpdated
                                            && updated, clientRequest.turmsRequest())
                            : RequestHandlerResult.OK);
        };
    }

    @ServiceRequestMapping(QUERY_USER_SETTINGS_REQUEST)
    public ClientRequestHandler handleQueryUserSettingsRequest() {
        return clientRequest -> {
            QueryUserSettingsRequest request = clientRequest.turmsRequest()
                    .getQueryUserSettingsRequest();
            return userSettingsService
                    .querySettings(clientRequest.userId(),
                            request.getNamesCount() > 0
                                    ? CollectionUtil.newSet(request.getNamesList())
                                    : null,
                            request.hasLastUpdatedDateStart()
                                    ? new Date(request.getLastUpdatedDateStart())
                                    : null)
                    .map(settings -> RequestHandlerResult
                            .of(ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setUserSettings(
                                            ProtoModelConvertor.userSettings2proto(settings))
                                    .build()));
        };
    }

}