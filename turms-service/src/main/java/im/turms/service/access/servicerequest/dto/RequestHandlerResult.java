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
 * @author James Chen
 */
@With
public record RequestHandlerResult(
        ResponseStatusCode code,
        @Nullable TurmsNotification.Data dataForRequester,
        boolean forwardDataForRecipientsToRequesterOtherOnlineSessions,
        Set<Long> recipients,
        @Nullable TurmsRequest dataForRecipients,
        @Nullable String reason
) {

    private static final FastEnumMap<ResponseStatusCode, RequestHandlerResult> POOL =
            new FastEnumMap<>(ResponseStatusCode.class);

    static {
        Set<Long> recipients = Collections.emptySet();
        for (ResponseStatusCode code : ResponseStatusCode.VALUES) {
            RequestHandlerResult result =
                    new RequestHandlerResult(code, null, false, recipients, null, null);
            POOL.put(code, result);
        }
    }

    public static final RequestHandlerResult OK = of(ResponseStatusCode.OK);

    public static final RequestHandlerResult NO_CONTENT = of(ResponseStatusCode.NO_CONTENT);

    @Override
    public String toString() {
        return "RequestHandlerResult["
                + "code="
                + code
                + ", dataForRequester="
                + ProtoFormatter.toLogString(dataForRequester)
                + ", forwardDataForRecipientsToRequesterOtherOnlineSessions="
                + forwardDataForRecipientsToRequesterOtherOnlineSessions
                + ", recipients="
                + recipients
                + ", dataForRecipients="
                + ProtoFormatter.toLogString(dataForRecipients)
                + ", reason="
                + reason
                + ']';
    }

    public static RequestHandlerResult of(@NotNull ResponseStatusCode code) {
        return POOL.get(code);
    }

    public static RequestHandlerResult of(
            @NotNull ResponseStatusCode code,
            @Nullable String reason) {
        if (reason == null) {
            return POOL.get(code);
        }
        return new RequestHandlerResult(code, null, false, Collections.emptySet(), null, reason);
    }

    public static RequestHandlerResult of(@NotNull TurmsNotification.Data dataForRequester) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                dataForRequester,
                false,
                Collections.emptySet(),
                null,
                null);
    }

    public static RequestHandlerResult of(
            boolean forwardDataForRecipientsToRequesterOtherOnlineSessions,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                forwardDataForRecipientsToRequesterOtherOnlineSessions,
                Collections.emptySet(),
                dataForRecipient,
                null);
    }

    public static RequestHandlerResult of(
            boolean forwardDataForRecipientsToRequesterOtherOnlineSessions,
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                forwardDataForRecipientsToRequesterOtherOnlineSessions,
                Collections.singleton(recipientId),
                dataForRecipient,
                null);
    }

    public static RequestHandlerResult of(
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                false,
                Collections.singleton(recipientId),
                dataForRecipient,
                null);
    }

    public static RequestHandlerResult of(
            @NotEmpty Set<Long> recipientIds,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                false,
                recipientIds,
                dataForRecipient,
                null);
    }

    public static RequestHandlerResult of(
            boolean forwardDataForRecipientsToRequesterOtherOnlineSessions,
            @NotEmpty Set<Long> recipientIds,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                null,
                forwardDataForRecipientsToRequesterOtherOnlineSessions,
                recipientIds,
                dataForRecipient,
                null);
    }

    public static RequestHandlerResult of(
            @NotNull ResponseStatusCode code,
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(
                code,
                null,
                false,
                Collections.singleton(recipientId),
                dataForRecipient,
                null);
    }

    public static RequestHandlerResult of(
            @NotNull ResponseStatusCode code,
            boolean forwardDataForRecipientsToRequesterOtherOnlineSessions,
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(
                code,
                null,
                forwardDataForRecipientsToRequesterOtherOnlineSessions,
                Collections.singleton(recipientId),
                dataForRecipient,
                null);
    }

    public static RequestHandlerResult ofDataLong(@NotNull Long value) {
        TurmsNotification.Data data = ClientMessagePool.getTurmsNotificationDataBuilder()
                .setLong(value)
                .build();
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                data,
                false,
                Collections.emptySet(),
                null,
                null);
    }

    public static RequestHandlerResult ofDataLong(
            @NotNull Long value,
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient) {
        TurmsNotification.Data data = ClientMessagePool.getTurmsNotificationDataBuilder()
                .setLong(value)
                .build();
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                data,
                false,
                Collections.singleton(recipientId),
                dataForRecipient,
                null);
    }

    public static RequestHandlerResult ofDataLong(
            @NotNull Long value,
            boolean forwardDataForRecipientsToRequesterOtherOnlineSessions,
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient) {
        TurmsNotification.Data data = ClientMessagePool.getTurmsNotificationDataBuilder()
                .setLong(value)
                .build();
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                data,
                forwardDataForRecipientsToRequesterOtherOnlineSessions,
                Collections.singleton(recipientId),
                dataForRecipient,
                null);
    }

    public static RequestHandlerResult ofDataLong(
            @NotNull Long value,
            boolean forwardDataForRecipientsToRequesterOtherOnlineSessions,
            @NotNull TurmsRequest dataForRecipient) {
        TurmsNotification.Data data = ClientMessagePool.getTurmsNotificationDataBuilder()
                .setLong(value)
                .build();
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                data,
                forwardDataForRecipientsToRequesterOtherOnlineSessions,
                Collections.emptySet(),
                forwardDataForRecipientsToRequesterOtherOnlineSessions
                        ? dataForRecipient
                        : null,
                null);
    }

    public static RequestHandlerResult ofDataLong(
            @NotNull Long value,
            boolean forwardDataForRecipientsToRequesterOtherOnlineSessions,
            @NotEmpty Set<Long> recipients,
            TurmsRequest dataForRecipients) {
        TurmsNotification.Data data = ClientMessagePool.getTurmsNotificationDataBuilder()
                .setLong(value)
                .build();
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                data,
                forwardDataForRecipientsToRequesterOtherOnlineSessions,
                recipients,
                dataForRecipients,
                null);
    }

    public static RequestHandlerResult ofDataLongs(@NotNull Collection<Long> values) {
        TurmsNotification.Data data = ClientMessagePool.getTurmsNotificationDataBuilder()
                .setLongsWithVersion(ClientMessagePool.getLongsWithVersionBuilder()
                        .addAllLongs(values))
                .build();
        return new RequestHandlerResult(
                ResponseStatusCode.OK,
                data,
                false,
                Collections.emptySet(),
                null,
                null);
    }

}