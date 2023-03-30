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

package im.turms.gateway.domain.session.service;

import java.util.Map;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import reactor.core.publisher.Mono;

import im.turms.gateway.domain.session.bo.UserPermissionInfo;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.location.bo.Location;

/**
 * @author James Chen
 */
public interface SessionIdentityAccessManagementSupport {

    /**
     * @return Possible codes: {@link ResponseStatusCode#OK},
     *         {@link ResponseStatusCode#LOGIN_AUTHENTICATION_FAILED},
     *         {@link ResponseStatusCode#LOGGING_IN_USER_NOT_ACTIVE}
     */
    Mono<UserPermissionInfo> verifyAndGrant(
            int version,
            @NotNull Long userId,
            @Nullable String password,
            @NotNull DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            @Nullable Location location,
            @Nullable String ip);

}
