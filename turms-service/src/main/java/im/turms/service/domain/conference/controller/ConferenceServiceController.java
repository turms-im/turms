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

package im.turms.service.domain.conference.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.ResponseAction;
import im.turms.server.common.access.client.dto.model.conference.Meeting;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.conference.CreateMeetingRequest;
import im.turms.server.common.access.client.dto.request.conference.DeleteMeetingRequest;
import im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest;
import im.turms.server.common.access.client.dto.request.conference.UpdateMeetingInvitationRequest;
import im.turms.server.common.access.client.dto.request.conference.UpdateMeetingRequest;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.infra.property.env.service.business.notification.NotificationProperties;
import im.turms.server.common.infra.property.env.service.business.notification.meeting.NotificationMeetingCanceledProperties;
import im.turms.server.common.infra.property.env.service.business.notification.meeting.NotificationMeetingInvitationUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.meeting.NotificationMeetingUpdatedProperties;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.conference.bo.CancelMeetingResult;
import im.turms.service.domain.conference.bo.UpdateMeetingInvitationResult;
import im.turms.service.domain.conference.bo.UpdateMeetingResult;
import im.turms.service.domain.conference.service.ConferenceService;
import im.turms.service.infra.proto.ProtoModelConvertor;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_MEETING_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_MEETING_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_MEETINGS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_MEETING_INVITATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_MEETING_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class ConferenceServiceController extends BaseServiceController {

    private final ConferenceService conferenceService;

    private boolean notifyRequesterOtherOnlineSessionsOfMeetingCanceled;
    private boolean notifyMeetingParticipantsOfMeetingCanceled;

    private boolean notifyRequesterOtherOnlineSessionsOfMeetingUpdated;
    private boolean notifyMeetingParticipantsOfMeetingUpdated;

    private boolean notifyRequesterOtherOnlineSessionsOfMeetingInvitationUpdated;
    private boolean notifyMeetingParticipantsOfMeetingInvitationUpdated;

    public ConferenceServiceController(
            TurmsPropertiesManager propertiesManager,
            ConferenceService conferenceService) {
        this.conferenceService = conferenceService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        ServiceProperties serviceProperties = properties.getService();
        NotificationProperties notificationProperties = serviceProperties.getNotification();
        NotificationMeetingCanceledProperties meetingCanceledProperties =
                notificationProperties.getMeetingCanceled();
        NotificationMeetingUpdatedProperties meetingUpdatedProperties =
                notificationProperties.getMeetingUpdated();
        NotificationMeetingInvitationUpdatedProperties meetingInvitationUpdatedProperties =
                notificationProperties.getMeetingInvitationUpdated();

        notifyRequesterOtherOnlineSessionsOfMeetingCanceled =
                meetingCanceledProperties.isNotifyRequesterOtherOnlineSessions();
        notifyMeetingParticipantsOfMeetingCanceled =
                meetingCanceledProperties.isNotifyMeetingParticipants();

        notifyRequesterOtherOnlineSessionsOfMeetingUpdated =
                meetingUpdatedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyMeetingParticipantsOfMeetingUpdated =
                meetingUpdatedProperties.isNotifyMeetingParticipants();

        notifyRequesterOtherOnlineSessionsOfMeetingInvitationUpdated =
                meetingInvitationUpdatedProperties.isNotifyRequesterOtherOnlineSessions();
        notifyMeetingParticipantsOfMeetingInvitationUpdated =
                meetingInvitationUpdatedProperties.isNotifyMeetingParticipants();
    }

    @ServiceRequestMapping(CREATE_MEETING_REQUEST)
    public ClientRequestHandler handleCreateMeetingRequest() {
        return clientRequest -> {
            CreateMeetingRequest request = clientRequest.turmsRequest()
                    .getCreateMeetingRequest();
            return conferenceService
                    .authAndCreateMeeting(clientRequest.userId(),
                            clientRequest.clientIp(),
                            request.hasUserId()
                                    ? request.getUserId()
                                    : null,
                            request.hasGroupId()
                                    ? request.getGroupId()
                                    : null,
                            request.hasName()
                                    ? request.getName()
                                    : null,
                            request.hasIntro()
                                    ? request.getIntro()
                                    : null,
                            request.hasPassword()
                                    ? request.getPassword()
                                    : null,
                            request.hasStartDate()
                                    ? new Date(request.getStartDate())
                                    : null)
                    .map(meeting -> RequestHandlerResult.ofDataLong(meeting.getId()));
        };
    }

    @ServiceRequestMapping(DELETE_MEETING_REQUEST)
    public ClientRequestHandler handleDeleteMeetingRequest() {
        return clientRequest -> {
            DeleteMeetingRequest request = clientRequest.turmsRequest()
                    .getDeleteMeetingRequest();
            Mono<CancelMeetingResult> cancelMeeting =
                    conferenceService.authAndCancelMeeting(clientRequest.userId(), request.getId());
            if (notifyMeetingParticipantsOfMeetingCanceled) {
                return cancelMeeting.flatMap(result -> {
                    if (!result.success()) {
                        return Mono.just(RequestHandlerResult.OK);
                    }
                    var meeting = result.meeting();
                    return conferenceService
                            .queryMeetingParticipants(meeting.getUserId(), meeting.getGroupId())
                            .map(participantIds -> {
                                participantIds = CollectionUtil.remove(participantIds,
                                        clientRequest.userId());
                                return RequestHandlerResult
                                        .of(true, participantIds, clientRequest.turmsRequest());
                            });
                });
            } else if (notifyRequesterOtherOnlineSessionsOfMeetingCanceled) {
                return cancelMeeting.map(result -> result.success()
                        ? RequestHandlerResult.of(true, clientRequest.turmsRequest())
                        : RequestHandlerResult.OK);
            }
            return cancelMeeting.thenReturn(RequestHandlerResult.OK);
        };
    }

    @ServiceRequestMapping(UPDATE_MEETING_REQUEST)
    public ClientRequestHandler handleUpdateMeetingRequest() {
        return clientRequest -> {
            UpdateMeetingRequest request = clientRequest.turmsRequest()
                    .getUpdateMeetingRequest();
            Mono<UpdateMeetingResult> updateMeeting =
                    conferenceService.authAndUpdateMeeting(clientRequest.userId(),
                            request.getId(),
                            request.hasName()
                                    ? request.getName()
                                    : null,
                            request.hasIntro()
                                    ? request.getIntro()
                                    : null,
                            request.hasPassword()
                                    ? request.getPassword()
                                    : null);
            if (notifyMeetingParticipantsOfMeetingUpdated) {
                return updateMeeting.flatMap(result -> {
                    if (result.success() && (request.hasName() || request.hasIntro())) {
                        var meeting = result.meeting();
                        return conferenceService
                                .queryMeetingParticipants(meeting.getUserId(), meeting.getGroupId())
                                .map(participantIds -> {
                                    participantIds = CollectionUtil.remove(participantIds,
                                            clientRequest.userId());
                                    if (request.hasPassword() && !participantIds.isEmpty()) {
                                        return RequestHandlerResult.of(participantIds,
                                                ClientMessagePool.getTurmsRequestBuilder()
                                                        .mergeFrom(clientRequest.turmsRequest())
                                                        .setUpdateMeetingRequest(ClientMessagePool
                                                                .getUpdateMeetingRequestBuilder()
                                                                .mergeFrom(request)
                                                                .clearPassword())
                                                        .build(),
                                                clientRequest.turmsRequest());
                                    }
                                    return RequestHandlerResult.of(true,
                                            clientRequest.turmsRequest());
                                });
                    }
                    return Mono.just(RequestHandlerResult.OK);
                });
            }
            if (notifyRequesterOtherOnlineSessionsOfMeetingUpdated) {
                return updateMeeting
                        .map(result -> result.success() && (request.hasName() || request.hasIntro())
                                ? RequestHandlerResult.of(true, clientRequest.turmsRequest())
                                : RequestHandlerResult.OK);
            }
            return updateMeeting.thenReturn(RequestHandlerResult.OK);
        };
    }

    @ServiceRequestMapping(QUERY_MEETINGS_REQUEST)
    public ClientRequestHandler handleQueryMeetingsRequest() {
        return clientRequest -> {
            QueryMeetingsRequest request = clientRequest.turmsRequest()
                    .getQueryMeetingsRequest();
            Set<Long> ids = request.getIdsCount() > 0
                    ? CollectionUtil.newSequencedSet(request.getIdsList())
                    : null;
            Set<Long> creatorIds = request.getCreatorIdsCount() > 0
                    ? CollectionUtil.newSequencedSet(request.getCreatorIdsList())
                    : null;
            Set<Long> userIds = request.getUserIdsCount() > 0
                    ? CollectionUtil.newSequencedSet(request.getUserIdsList())
                    : null;
            Set<Long> groupIds = request.getGroupIdsCount() > 0
                    ? CollectionUtil.newSequencedSet(request.getGroupIdsList())
                    : null;
            Date creationDateStart = request.hasCreationDateStart()
                    ? new Date(request.getCreationDateStart())
                    : null;
            Date creationDateEnd = request.hasCreationDateEnd()
                    ? new Date(request.getCreationDateEnd())
                    : null;
            Integer skip = request.hasSkip()
                    ? request.getSkip()
                    : null;
            Integer limit = request.hasLimit()
                    ? request.getLimit()
                    : null;

            Recyclable<List<Meeting>> recyclableList = ListRecycler.obtain();
            return conferenceService
                    .authAndQueryMeetings(clientRequest.userId(),
                            ids,
                            creatorIds,
                            userIds,
                            groupIds,
                            creationDateStart,
                            creationDateEnd,
                            skip,
                            limit)
                    .map(meeting -> ProtoModelConvertor.meeting2proto(meeting, null)
                            .build())
                    .collect(Collectors.toCollection(recyclableList::getValue))
                    .map(meetings -> ClientMessagePool.getTurmsNotificationDataBuilder()
                            .setMeetings(ClientMessagePool.getMeetingsBuilder()
                                    .addAllMeetings(meetings))
                            .build())
                    .doFinally(signalType -> recyclableList.recycle())
                    .map(RequestHandlerResult::of);
        };
    }

    /**
     * @implNote We name it "update meeting invitation" instead of something like "query meeting
     *           access token", because the operation MAY have side effects according to different
     *           scenarios.
     */
    @ServiceRequestMapping(UPDATE_MEETING_INVITATION_REQUEST)
    public ClientRequestHandler handleUpdateMeetingInvitationRequest() {
        return clientRequest -> {
            UpdateMeetingInvitationRequest request = clientRequest.turmsRequest()
                    .getUpdateMeetingInvitationRequest();
            ResponseAction responseAction = request.getResponseAction();
            Mono<UpdateMeetingInvitationResult> updateMeetingInvitation =
                    conferenceService.authAndUpdateMeetingInvitation(clientRequest.userId(),
                            request.getMeetingId(),
                            request.hasPassword()
                                    ? request.getPassword()
                                    : null,
                            responseAction);
            if (notifyMeetingParticipantsOfMeetingInvitationUpdated) {
                return updateMeetingInvitation.flatMap(result -> {
                    if (!result.updated()) {
                        return Mono.just(RequestHandlerResult.OK);
                    }
                    var meeting = result.meeting();
                    return conferenceService
                            .queryMeetingParticipants(meeting.getUserId(), meeting.getGroupId())
                            .map(participantIds -> {
                                participantIds = CollectionUtil.remove(participantIds,
                                        clientRequest.userId());
                                TurmsNotification.Data response =
                                        responseAction == ResponseAction.ACCEPT
                                                ? ClientMessagePool
                                                        .getTurmsNotificationDataBuilder()
                                                        .setString(result.accessToken())
                                                        .build()
                                                : null;
                                if (participantIds.isEmpty() || !request.hasPassword()) {
                                    return RequestHandlerResult.of(response,
                                            true,
                                            participantIds,
                                            clientRequest.turmsRequest());
                                }
                                return RequestHandlerResult.of(response,
                                        participantIds,
                                        ClientMessagePool.getTurmsRequestBuilder()
                                                .mergeFrom(clientRequest.turmsRequest())
                                                .setUpdateMeetingInvitationRequest(ClientMessagePool
                                                        .getUpdateMeetingInvitationRequestBuilder()
                                                        .clearPassword()
                                                        .build())
                                                .build(),
                                        clientRequest.turmsRequest());
                            });
                });
            } else if (notifyRequesterOtherOnlineSessionsOfMeetingInvitationUpdated) {
                return updateMeetingInvitation.map(result -> result.updated()
                        ? RequestHandlerResult.of(responseAction == ResponseAction.ACCEPT
                                ? ClientMessagePool.getTurmsNotificationDataBuilder()
                                        .setString(result.accessToken())
                                        .build()
                                : null, true, Collections.emptySet(), clientRequest.turmsRequest())
                        : RequestHandlerResult.OK);
            }
            return updateMeetingInvitation
                    .map(result -> RequestHandlerResult.of(responseAction == ResponseAction.ACCEPT
                            ? ClientMessagePool.getTurmsNotificationDataBuilder()
                                    .setString(result.accessToken())
                                    .build()
                            : null));
        };

    }
}