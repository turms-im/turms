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

package im.turms.gateway.domain.session.bo;

import java.util.Collections;
import java.util.Set;

import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.TurmsRequestTypePool;
import im.turms.server.common.access.common.ResponseStatusCode;

/**
 * @author James Chen
 */
public record UserPermissionInfo(
        ResponseStatusCode authenticationCode,
        Set<TurmsRequest.KindCase> permissions
) {

    public static final UserPermissionInfo GRANTED_WITH_ALL_PERMISSIONS =
            new UserPermissionInfo(ResponseStatusCode.OK, TurmsRequestTypePool.ALL);
    public static final Mono<UserPermissionInfo> GRANTED_WITH_ALL_PERMISSIONS_MONO =
            Mono.just(GRANTED_WITH_ALL_PERMISSIONS);
    public static final UserPermissionInfo LOGIN_AUTHENTICATION_FAILED =
            new UserPermissionInfo(ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED);
    public static final Mono<UserPermissionInfo> LOGIN_AUTHENTICATION_FAILED_MONO =
            Mono.just(LOGIN_AUTHENTICATION_FAILED);
    public static final Mono<UserPermissionInfo> LOGGING_IN_USER_NOT_ACTIVE_MONO =
            Mono.just(new UserPermissionInfo(ResponseStatusCode.LOGGING_IN_USER_NOT_ACTIVE));

    public UserPermissionInfo(ResponseStatusCode authenticationCode) {
        this(authenticationCode, Collections.emptySet());
    }
}