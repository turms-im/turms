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

package im.turms.plugin.livekit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import jakarta.validation.constraints.NotNull;

import reactor.core.publisher.Mono;

import im.turms.plugin.livekit.core.LiveKitClient;
import im.turms.plugin.livekit.core.webhook.WebhookEventNameConst;
import im.turms.plugin.livekit.property.LiveKitProperties;
import im.turms.server.common.infra.lang.MathUtil;
import im.turms.server.common.infra.plugin.ExtensionPointMethod;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.service.domain.conference.po.Meeting;
import im.turms.service.infra.plugin.extension.ConferenceServiceProvider;
import im.turms.service.infra.plugin.extension.model.AcceptMeetingInvitationResult;
import im.turms.service.infra.plugin.extension.model.ConferenceEvent;
import im.turms.service.infra.plugin.extension.model.CreateMeetingOptions;
import im.turms.service.infra.plugin.extension.model.CreateMeetingResult;
import im.turms.service.infra.plugin.extension.model.MeetingEndedEvent;

/**
 * The class is an bridge between Turms and LiveKit.
 *
 * @author James Chen
 */
public class LiveKitConferenceServiceProvider extends TurmsExtension
        implements ConferenceServiceProvider {

    private LiveKitClient liveKitClient;
    private Map<String, Consumer<? extends ConferenceEvent>> eventToListener;

    @Override
    protected Mono<Void> start() {
        eventToListener = new ConcurrentHashMap<>(8);
        LiveKitProperties properties = loadProperties(LiveKitProperties.class);
        liveKitClient = new LiveKitClient(properties, webhookEvent -> {
            Consumer consumer = eventToListener.get(webhookEvent.getEvent());
            if (consumer != null) {
                consumer.accept(webhookEvent);
            }
        });
        return liveKitClient.start();
    }

    @Override
    protected Mono<Void> stop() {
        eventToListener.clear();
        return liveKitClient.stop();
    }

    @ExtensionPointMethod
    @Override
    public Mono<Void> addMeetingEndedEventListener(@NotNull Consumer<MeetingEndedEvent> listener) {
        eventToListener.put(WebhookEventNameConst.ROOM_FINISHED, listener);
        return Mono.empty();
    }

    @ExtensionPointMethod
    @Override
    public Mono<CreateMeetingResult> createMeeting(
            @NotNull Long requesterId,
            @NotNull Meeting meeting,
            @NotNull CreateMeetingOptions options) {
        String roomName = getRoomName(meeting.getId());
        Long idleTimeoutMillis = options.idleTimeoutMillis();
        return liveKitClient.getRoomService()
                // TODO: support configuring these parameters.
                .createRoom(roomName,
                        idleTimeoutMillis == null
                                ? null
                                : MathUtil.toInt(idleTimeoutMillis / 1000),
                        options.maxParticipants(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null)
                .map(room -> new CreateMeetingResult(
                        liveKitClient.generateRoomAccessToken(roomName)));
    }

    @ExtensionPointMethod
    @Override
    public Mono<Void> cancelMeeting(@NotNull Long requesterId, @NotNull Long meetingId) {
        return liveKitClient.getRoomService()
                .deleteRoom(String.valueOf(meetingId));
    }

    @ExtensionPointMethod
    @Override
    public Mono<Integer> countActiveMeetingsByUserId(@NotNull Long userId) {
        // TODO: the latest version of livekit (v1.6.1) does not support this feature.
        return Mono.empty();
    }

    @ExtensionPointMethod
    @Override
    public Mono<AcceptMeetingInvitationResult> acceptMeetingInvitation(
            @NotNull Long requesterId,
            @NotNull Long meetingId) {
        String accessToken = liveKitClient.generateRoomAccessToken(getRoomName(meetingId));
        return Mono.just(new AcceptMeetingInvitationResult(accessToken));
    }

    /**
     * @implNote Note that LiveKit uses the room name as the ID internally (e.g. LiveKit server uses
     *           the room name as the key of the room in Redis), so we use the Turms meeting ID as
     *           the LiveKit room name.
     */
    private String getRoomName(Long meetingId) {
        return String.valueOf(meetingId);
    }
}