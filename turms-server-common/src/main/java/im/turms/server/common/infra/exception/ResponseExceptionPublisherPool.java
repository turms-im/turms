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

    private static final Mono ALREADY_UP_TO_DATE =
            Mono.error(ResponseException.get(ResponseStatusCode.ALREADY_UP_TO_DATE));

    private static final Mono IP_BLOCKLIST_IS_DISABLED =
            Mono.error(ResponseException.get(ResponseStatusCode.IP_BLOCKLIST_IS_DISABLED));

    private static final Mono MOVING_READ_DATE_FORWARD_IS_DISABLED = Mono
            .error(ResponseException.get(ResponseStatusCode.MOVING_READ_DATE_FORWARD_IS_DISABLED));

    private static final Mono NO_CONTENT =
            Mono.error(ResponseException.get(ResponseStatusCode.NO_CONTENT));

    private static final Mono NOT_FRIEND_REQUEST_SENDER_TO_RECALL_REQUEST = Mono
            .error(ResponseException.get(ResponseStatusCode.NOT_SENDER_TO_RECALL_FRIEND_REQUEST));

    private static final Mono NOT_GROUP_JOIN_REQUEST_SENDER_TO_RECALL_REQUEST = Mono.error(
            ResponseException.get(ResponseStatusCode.NOT_SENDER_TO_RECALL_GROUP_JOIN_REQUEST));

    private static final Mono OK = Mono.error(ResponseException.get(ResponseStatusCode.OK));

    private static final Mono RESOURCE_NOT_FOUND =
            Mono.error(ResponseException.get(ResponseStatusCode.RESOURCE_NOT_FOUND));

    private static final Mono SEND_REQUEST_FROM_NONEXISTENT_SESSION = Mono
            .error(ResponseException.get(ResponseStatusCode.SEND_REQUEST_FROM_NONEXISTENT_SESSION));

    private static final Mono SERVER_UNAVAILABLE =
            Mono.error(ResponseException.get(ResponseStatusCode.SERVER_UNAVAILABLE));

    private static final Mono SESSION_SIMULTANEOUS_CONFLICTS_DECLINE = Mono.error(
            ResponseException.get(ResponseStatusCode.SESSION_SIMULTANEOUS_CONFLICTS_DECLINE));

    private static final Mono TRANSFER_NONEXISTENT_GROUP =
            Mono.error(ResponseException.get(ResponseStatusCode.TRANSFER_NONEXISTENT_GROUP));

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

    public static <T> Mono<T> notFriendRequestSenderToRecallRequest() {
        return NOT_FRIEND_REQUEST_SENDER_TO_RECALL_REQUEST;
    }

    public static <T> Mono<T> notGroupJoinRequestSenderToRecallRequest() {
        return NOT_GROUP_JOIN_REQUEST_SENDER_TO_RECALL_REQUEST;
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
    public static <T> Mono<T> sendRequestFromNonexistentSession() {
        return SEND_REQUEST_FROM_NONEXISTENT_SESSION;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> serverUnavailable() {
        return SERVER_UNAVAILABLE;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> transferNonexistentGroup() {
        return TRANSFER_NONEXISTENT_GROUP;
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