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

package im.turms.server.common.infra.exception;

import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;

/**
 * @author James Chen
 */
public class ResponseExceptionPublisherPool {

    private ResponseExceptionPublisherPool() {
    }

    private static final Mono ADD_BLOCKED_USER_TO_GROUP =
            Mono.error(ResponseException.get(ResponseStatusCode.ADD_BLOCKED_USER_TO_GROUP));

    private static final Mono ADD_BLOCKED_USER_TO_INACTIVE_GROUP = Mono
            .error(ResponseException.get(ResponseStatusCode.ADD_BLOCKED_USER_TO_INACTIVE_GROUP));

    private static final Mono ALREADY_UP_TO_DATE =
            Mono.error(ResponseException.get(ResponseStatusCode.ALREADY_UP_TO_DATE));

    private static final Mono IP_BLOCKLIST_IS_DISABLED =
            Mono.error(ResponseException.get(ResponseStatusCode.IP_BLOCKLIST_IS_DISABLED));

    private static final Mono MOVING_READ_DATE_FORWARD_IS_DISABLED = Mono
            .error(ResponseException.get(ResponseStatusCode.MOVING_READ_DATE_FORWARD_IS_DISABLED));

    private static final Mono NO_CONTENT =
            Mono.error(ResponseException.get(ResponseStatusCode.NO_CONTENT));

    private static final Mono OK = Mono.error(ResponseException.get(ResponseStatusCode.OK));

    private static final Mono RESOURCE_NOT_FOUND =
            Mono.error(ResponseException.get(ResponseStatusCode.RESOURCE_NOT_FOUND));

    private static final Mono SEND_REQUEST_FROM_NON_EXISTING_SESSION = Mono.error(
            ResponseException.get(ResponseStatusCode.SEND_REQUEST_FROM_NON_EXISTING_SESSION));

    private static final Mono SERVER_UNAVAILABLE =
            Mono.error(ResponseException.get(ResponseStatusCode.SERVER_UNAVAILABLE));

    private static final Mono SESSION_SIMULTANEOUS_CONFLICTS_DECLINE = Mono.error(
            ResponseException.get(ResponseStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE));

    private static final Mono TRANSFER_NON_EXISTING_GROUP =
            Mono.error(ResponseException.get(ResponseStatusCode.TRANSFER_NON_EXISTING_GROUP));

    private static final Mono UNAUTHORIZED =
            Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED));

    private static final Mono UPDATING_READ_DATE_IS_DISABLED =
            Mono.error(ResponseException.get(ResponseStatusCode.UPDATING_READ_DATE_IS_DISABLED));

    private static final Mono USER_ID_BLOCKLIST_IS_DISABLED =
            Mono.error(ResponseException.get(ResponseStatusCode.USER_ID_BLOCKLIST_IS_DISABLED));

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> addBlockedUserToGroup() {
        return ADD_BLOCKED_USER_TO_GROUP;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> addBlockedUserToInactiveGroup() {
        return ADD_BLOCKED_USER_TO_INACTIVE_GROUP;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> alreadyUpToUpdate() {
        return ALREADY_UP_TO_DATE;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> ipBlocklistIsDisabled() {
        return IP_BLOCKLIST_IS_DISABLED;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> movingReadDateForwardIsDisabled() {
        return MOVING_READ_DATE_FORWARD_IS_DISABLED;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> noContent() {
        return NO_CONTENT;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> ok() {
        return OK;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> resourceNotFound() {
        return RESOURCE_NOT_FOUND;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> sendRequestFromNonExistingSession() {
        return SEND_REQUEST_FROM_NON_EXISTING_SESSION;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> serverUnavailable() {
        return SERVER_UNAVAILABLE;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> transferNonExistingGroup() {
        return TRANSFER_NON_EXISTING_GROUP;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> sessionSimultaneousConflictsDecline() {
        return SESSION_SIMULTANEOUS_CONFLICTS_DECLINE;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> updatingReadDateIsDisabled() {
        return UPDATING_READ_DATE_IS_DISABLED;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> userIdBlocklistIsDisabled() {
        return USER_ID_BLOCKLIST_IS_DISABLED;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> unauthorized() {
        return UNAUTHORIZED;
    }

}
