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

import im.turms.server.common.access.client.dto.model.common.Int64Values;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.FastEnumMap;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

/**
 * @author James Chen
 */
public final class RequestHandlerResultFactory {

    private static final FastEnumMap<ResponseStatusCode, RequestHandlerResult> POOL = new FastEnumMap<>(ResponseStatusCode.class);

    private RequestHandlerResultFactory() {
    }

    static {
        Set<Long> recipients = Collections.emptySet();
        for (ResponseStatusCode code : ResponseStatusCode.values()) {
            RequestHandlerResult result = new RequestHandlerResult(null,
                    false,
                    recipients,
                    null,
                    code,
                    null);
            POOL.put(code, result);
        }
    }

    public static final RequestHandlerResult OK = get(ResponseStatusCode.OK);

    public static final RequestHandlerResult NO_CONTENT = get(ResponseStatusCode.NO_CONTENT);

    public static RequestHandlerResult get(@NotNull ResponseStatusCode code) {
        return POOL.get(code);
    }

    public static RequestHandlerResult get(@NotNull ResponseStatusCode code, @Nullable String reason) {
        if (reason == null) {
            return POOL.get(code);
        }
        return new RequestHandlerResult(null, false, Collections.emptySet(), null, code, reason);
    }

    public static RequestHandlerResult get(@NotNull Long id) {
        TurmsNotification.Data data = TurmsNotification.Data
                .newBuilder()
                .setIds(Int64Values.newBuilder().addValues(id).build())
                .build();
        return new RequestHandlerResult(
                data,
                false,
                Collections.emptySet(),
                null,
                ResponseStatusCode.OK,
                null);
    }

    public static RequestHandlerResult get(
            @NotNull Long id,
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient) {
        TurmsNotification.Data data = TurmsNotification.Data
                .newBuilder()
                .setIds(Int64Values.newBuilder().addValues(id).build())
                .build();
        return new RequestHandlerResult(data, false, Collections.singleton(recipientId), dataForRecipient, ResponseStatusCode.OK, null);
    }

    public static RequestHandlerResult get(
            @NotNull Long id,
            @NotEmpty Set<Long> recipients,
            boolean relayDataToOtherSenderOnlineDevices,
            TurmsRequest dataForRecipients) {
        TurmsNotification.Data data = TurmsNotification.Data
                .newBuilder()
                .setIds(Int64Values.newBuilder().addValues(id).build())
                .build();
        return new RequestHandlerResult(
                data,
                relayDataToOtherSenderOnlineDevices,
                recipients,
                dataForRecipients,
                ResponseStatusCode.OK,
                null);
    }

    public static RequestHandlerResult get(@NotNull TurmsNotification.Data dataForRequester) {
        return new RequestHandlerResult(dataForRequester, false, Collections.emptySet(), null, ResponseStatusCode.OK, null);
    }

    public static RequestHandlerResult get(
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(null, false, Collections.singleton(recipientId), dataForRecipient, ResponseStatusCode.OK, null);
    }

    public static RequestHandlerResult get(
            @NotEmpty Set<Long> recipientIds,
            @NotNull TurmsRequest dataForRecipient) {
        return new RequestHandlerResult(null, false, recipientIds, dataForRecipient, ResponseStatusCode.OK, null);
    }

    public static RequestHandlerResult get(
            @NotNull Long recipientId,
            @NotNull TurmsRequest dataForRecipient,
            @NotNull ResponseStatusCode code) {
        return new RequestHandlerResult(null, false, Collections.singleton(recipientId), dataForRecipient, code, null);
    }

}