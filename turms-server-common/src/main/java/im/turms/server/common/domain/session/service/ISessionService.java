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

package im.turms.server.common.domain.session.service;

import java.util.List;
import java.util.Set;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.domain.session.bo.UserSessionsInfo;
import im.turms.server.common.infra.validation.ValidDeviceType;

/**
 * @author James Chen
 */
public interface ISessionService {

    Mono<Integer> closeLocalSessions(@NotNull byte[] ip, @NotNull CloseReason closeReason);

    Mono<Integer> closeLocalSession(
            @NotNull Long userId,
            @NotEmpty Set<@ValidDeviceType DeviceType> deviceTypes,
            @NotNull CloseReason closeReason);

    Mono<Integer> closeLocalSession(@NotNull Long userId, @NotNull CloseReason closeReason);

    List<UserSessionsInfo> getSessions(@NotEmpty Set<Long> userIds);

}