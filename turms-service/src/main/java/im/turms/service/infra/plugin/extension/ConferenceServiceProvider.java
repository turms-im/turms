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

package im.turms.service.infra.plugin.extension;

import java.util.function.Consumer;
import jakarta.validation.constraints.NotNull;

import reactor.core.publisher.Mono;

import im.turms.server.common.infra.exception.NotImplementedException;
import im.turms.server.common.infra.plugin.ExtensionPoint;
import im.turms.server.common.infra.plugin.Singleton;
import im.turms.service.domain.conference.po.Meeting;
import im.turms.service.infra.plugin.extension.model.AcceptMeetingInvitationResult;
import im.turms.service.infra.plugin.extension.model.CreateMeetingOptions;
import im.turms.service.infra.plugin.extension.model.CreateMeetingResult;
import im.turms.service.infra.plugin.extension.model.MeetingEndedEvent;

/**
 * @author James Chen
 */
public interface ConferenceServiceProvider extends ExtensionPoint, Singleton {

    default Mono<Void> addMeetingEndedEventListener(@NotNull Consumer<MeetingEndedEvent> listener) {
        return Mono.empty();
    }

    default Mono<CreateMeetingResult> createMeeting(
            @NotNull Long requesterId,
            @NotNull Meeting meeting,
            @NotNull CreateMeetingOptions options) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Void> cancelMeeting(@NotNull Long requesterId, @NotNull Long meetingId) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Integer> countActiveMeetingsByUserId(@NotNull Long userId) {
        return Mono.empty();
    }

    default Mono<AcceptMeetingInvitationResult> acceptMeetingInvitation(
            @NotNull Long requesterId,
            @NotNull Long meetingId) {
        return Mono.error(new NotImplementedException());
    }
}