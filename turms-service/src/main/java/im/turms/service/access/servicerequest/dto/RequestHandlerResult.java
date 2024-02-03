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

package im.turms.service.access.servicerequest.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.With;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.FastEnumMap;
import im.turms.server.common.infra.proto.ProtoFormatter;

/**
 * @param code          The status code of the response.
 * @param reason        The reason of the response.
 * @param response      The response to be sent to the requester.
 * @param notifications The notifications to be sent to users.
 * @author James Chen
 */
@With
public record RequestHandlerResult(
        ResponseStatusCode code,
        @Nullable String reason,
        @Nullable TurmsNotification.Data response,
        List<Notification> notifications
) {

    @Override
    public String toString() {
        return "RequestHandlerResult["
                + "code="
                + code
                + ", reason='"
                + reason
                + '\''
                + ", response="
                + ProtoFormatter.toLogString(response)
                + ", notifications="
                + notifications
                + ']';
    }

    private static final FastEnumMap<ResponseStatusCode, RequestHandlerResult> POOL =
            new FastEnumMap<>(ResponseStatusCode.class);

    static {
        List<Notification> notifications = Collections.emptyList();
        for (ResponseStatusCode code : ResponseStatusCode.VALUES) {
            RequestHandlerResult result = new RequestHandlerResult(code, null, null, notifications);
            POOL.put(code, result);
        }
    }

    public static final RequestHandlerResult OK = of(ResponseStatusCode.OK);

    public static final RequestHandlerResult NO_CONTENT = of(ResponseStatusCode.NO_CONTENT);

    public static RequestHandlerResult of(@NotNull ResponseStatusCode code) {
        return POOL.get(code);
    }

    public static RequestHandlerResult of(
            @NotNull ResponseStatusCode code,
            @Nullable String reason) {
        if (reason == null) {
            return POOL.get(code);
        }
        return new RequestHandlerResult(code, reason, null, Collections.emptyList());
    }

    public static RequestHandlerResult of(@NotNull TurmsNotification.Data response) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                response,
                Collections.emptyList());
    }

    public static RequestHandlerResult of(
            boolean forwardNotificationsToRequesterOtherOnlineSessions,
            @NotNull TurmsRequest notification) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                null,
                List.of(new Notification(
                        forwardNotificationsToRequesterOtherOnlineSessions,
                        Collections.emptySet(),
                        notification)));
    }

    public static RequestHandlerResult of(
            boolean forwardNotificationsToRequesterOtherOnlineSessions,
            @NotNull Long recipientId,
            @NotNull TurmsRequest notification) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                null,
                List.of(new Notification(
                        forwardNotificationsToRequesterOtherOnlineSessions,
                        Set.of(recipientId),
                        notification)));
    }

    public static RequestHandlerResult of(
            @NotNull Long recipientId,
            @NotNull TurmsRequest notification) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                null,
                List.of(new Notification(false, Set.of(recipientId), notification)));
    }

    public static RequestHandlerResult of(
            @NotEmpty Set<Long> recipientIds,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                null,
                List.of(new Notification(false, recipientIds, dataForRecipient)));
    }

    public static RequestHandlerResult of(
            boolean forwardNotificationToRequesterOtherOnlineSessions,
            @NotEmpty Set<Long> recipientIds,
            @NotNull TurmsRequest notification) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                null,
                List.of(new Notification(
                        forwardNotificationToRequesterOtherOnlineSessions,
                        recipientIds,
                        notification)));
    }

    public static RequestHandlerResult of(
            TurmsNotification.Data response,
            boolean forwardNotificationToRequesterOtherOnlineSessions,
            @NotEmpty Set<Long> recipientIds,
            @NotNull TurmsRequest notification) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                response,
                List.of(new Notification(
                        forwardNotificationToRequesterOtherOnlineSessions,
                        recipientIds,
                        notification)));
    }

    public static RequestHandlerResult of(
            @NotNull ResponseStatusCode code,
            @NotNull Long recipientId,
            @NotNull TurmsRequest notification) {
        return new RequestHandlerResult(
                code,
                null,
                null,
                List.of(new Notification(false, Set.of(recipientId), notification)));
    }

    public static RequestHandlerResult of(
            @NotNull ResponseStatusCode code,
            boolean forwardNotificationToRequesterOtherOnlineSessions,
            @NotNull Long recipientId,
            @NotNull TurmsRequest notification) {
        return new RequestHandlerResult(
                code,
                null,
                null,
                List.of(new Notification(
                        forwardNotificationToRequesterOtherOnlineSessions,
                        Set.of(recipientId),
                        notification)));
    }

    public static RequestHandlerResult of(@NotNull List<Notification> notifications) {
        return new RequestHandlerResult(ResponseStatusCode.OK, null, null, notifications);
    }

    public static RequestHandlerResult of(@NotNull Notification notification) {
        return new RequestHandlerResult(ResponseStatusCode.OK, null, null, List.of(notification));
    }

    public static RequestHandlerResult ofDataLong(@NotNull Long value) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                ClientMessagePool.getTurmsNotificationDataBuilder()
                        .setLong(value)
                        .build(),
                Collections.emptyList());
    }

    public static RequestHandlerResult ofDataLong(
            @NotNull Long value,
            @NotNull Long recipientId,
            @NotNull TurmsRequest notification) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                ClientMessagePool.getTurmsNotificationDataBuilder()
                        .setLong(value)
                        .build(),
                List.of(new Notification(false, Collections.singleton(recipientId), notification)));
    }

    public static RequestHandlerResult ofDataLong(
            @NotNull Long value,
            boolean forwardNotificationToRequesterOtherOnlineSessions,
            @NotNull Long recipientId,
            @NotNull TurmsRequest notification) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                ClientMessagePool.getTurmsNotificationDataBuilder()
                        .setLong(value)
                        .build(),
                List.of(new Notification(
                        forwardNotificationToRequesterOtherOnlineSessions,
                        Set.of(recipientId),
                        notification)));
    }

    public static RequestHandlerResult ofDataLong(
            @NotNull Long value,
            boolean forwardDataForRecipientsToRequesterOtherOnlineSessions,
            @NotNull TurmsRequest notification) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                ClientMessagePool.getTurmsNotificationDataBuilder()
                        .setLong(value)
                        .build(),
                forwardDataForRecipientsToRequesterOtherOnlineSessions
                        ? List.of(new Notification(true, Collections.emptySet(), notification))
                        : Collections.emptyList());
    }

    public static RequestHandlerResult ofDataLong(
            @NotNull Long value,
            boolean forwardNotificationsToRequesterOtherOnlineSessions,
            @NotEmpty Set<Long> recipients,
            TurmsRequest notification) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                ClientMessagePool.getTurmsNotificationDataBuilder()
                        .setLong(value)
                        .build(),
                List.of(new Notification(

                        forwardNotificationsToRequesterOtherOnlineSessions,
                        recipients,
                        notification)));
    }

    public static RequestHandlerResult ofDataLongs(@NotNull Collection<Long> values) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                ClientMessagePool.getTurmsNotificationDataBuilder()
                        .setLongsWithVersion(ClientMessagePool.getLongsWithVersionBuilder()
                                .addAllLongs(values))
                        .build(),
                Collections.emptyList());
    }

    @With
    public record Notification(
            boolean forwardToRequesterOtherOnlineSessions,
            Set<Long> recipients,
            TurmsRequest notification
    ) {

        public static Notification of(
                boolean forwardToRequesterOtherOnlineSessions,
                Set<Long> recipients,
                TurmsRequest notification) {
            return new Notification(
                    forwardToRequesterOtherOnlineSessions,
                    recipients,
                    notification);
        }

        public static Notification of(
                boolean forwardToRequesterOtherOnlineSessions,
                Long recipient,
                TurmsRequest notification) {
            return new Notification(
                    forwardToRequesterOtherOnlineSessions,
                    Set.of(recipient),
                    notification);
        }

        public static Notification of(
                boolean forwardToRequesterOtherOnlineSessions,
                TurmsRequest notification) {
            return new Notification(
                    forwardToRequesterOtherOnlineSessions,
                    Collections.emptySet(),
                    notification);
        }

        @Override
        public String toString() {
            return "Notification{"
                    + "forwardToRequesterOtherOnlineSessions="
                    + forwardToRequesterOtherOnlineSessions
                    + ", recipients="
                    + recipients
                    + ", notification="
                    + ProtoFormatter.toLogString(notification)
                    + '}';
        }
    }

}