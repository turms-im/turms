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

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.FastEnumMap;

/**
 * @author James Chen
 */
public final class RequestHandlerResultFactory {

    private static final FastEnumMap<ResponseStatusCode, RequestHandlerResult> POOL =
            new FastEnumMap<>(ResponseStatusCode.class);

    private RequestHandlerResultFactory() {
    }

    static {
        Set<Long> recipients = Collections.emptySet();
        for (ResponseStatusCode code : ResponseStatusCode.VALUES) {
            RequestHandlerResult result =
                    new RequestHandlerResult(null, false, recipients, null, code, null);
            POOL.put(code, result);
        }
    }

    public static final RequestHandlerResult OK = get(ResponseStatusCode.OK);

    public static final RequestHandlerResult NO_CONTENT = get(ResponseStatusCode.NO_CONTENT);

    public static RequestHandlerResult get(@NotNull ResponseStatusCode code) {
        return POOL.get(code);
    }

    public static RequestHandlerResult get(
            @NotNull ResponseStatusCode code,
            @Nullable String reason) {
        if (reason == null) {
            return POOL.get(code);
        }
        return new RequestHandlerResult(null, false, Collections.emptySet(), null, code, reason);
    }

    public static RequestHandlerResult get(@NotNull TurmsNotification.Data dataForRequester) {
        return new RequestHandlerResult(
                dataForRequester,
                false,
                Collections.emptySet(),
                null,
                ResponseStatusCode.OK,
                null);
    }

    public static RequestHandlerResult get(
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(
                null,
                false,
                Collections.singleton(recipientId),
                dataForRecipient,
                ResponseStatusCode.OK,
                null);
    }

    public static RequestHandlerResult get(
            @NotEmpty Set<Long> recipientIds,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(
                null,
                false,
                recipientIds,
                dataForRecipient,
                ResponseStatusCode.OK,
                null);
    }

    public static RequestHandlerResult get(
            @NotEmpty Set<Long> recipientIds,
            @NotNull TurmsRequest dataForRecipient,
            boolean forwardDataForRecipientsToOtherSenderOnlineDevices) {
        return new RequestHandlerResult(
                null,
                forwardDataForRecipientsToOtherSenderOnlineDevices,
                recipientIds,
                dataForRecipient,
                ResponseStatusCode.OK,
                null);
    }

    public static RequestHandlerResult get(
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient,
            @NotNull ResponseStatusCode code) {
        return new RequestHandlerResult(
                null,
                false,
                Collections.singleton(recipientId),
                dataForRecipient,
                code,
                null);
    }

    public static RequestHandlerResult getByDataLong(@NotNull Long value) {
        TurmsNotification.Data data = ClientMessagePool.getTurmsNotificationDataBuilder()
                .setLong(value)
                .build();
        return new RequestHandlerResult(
                data,
                false,
                Collections.emptySet(),
                null,
                ResponseStatusCode.OK,
                null);
    }

    public static RequestHandlerResult getByDataLong(
            @NotNull Long value,
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient) {
        TurmsNotification.Data data = ClientMessagePool.getTurmsNotificationDataBuilder()
                .setLong(value)
                .build();
        return new RequestHandlerResult(
                data,
                false,
                Collections.singleton(recipientId),
                dataForRecipient,
                ResponseStatusCode.OK,
                null);
    }

    public static RequestHandlerResult getByDataLong(
            @NotNull Long value,
            @NotEmpty Set<Long> recipients,
            boolean forwardDataForRecipientsToOtherSenderOnlineDevices,
            TurmsRequest dataForRecipients) {
        TurmsNotification.Data data = ClientMessagePool.getTurmsNotificationDataBuilder()
                .setLong(value)
                .build();
        return new RequestHandlerResult(
                data,
                forwardDataForRecipientsToOtherSenderOnlineDevices,
                recipients,
                dataForRecipients,
                ResponseStatusCode.OK,
                null);
    }

    public static RequestHandlerResult getByDataLongs(@NotNull Collection<Long> values) {
        TurmsNotification.Data data = ClientMessagePool.getTurmsNotificationDataBuilder()
                .setLongsWithVersion(ClientMessagePool.getLongsWithVersionBuilder()
                        .addAllLongs(values))
                .build();
        return new RequestHandlerResult(
                data,
                false,
                Collections.emptySet(),
                null,
                ResponseStatusCode.OK,
                null);
    }

}