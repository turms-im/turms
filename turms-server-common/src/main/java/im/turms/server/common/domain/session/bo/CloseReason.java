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

package im.turms.server.common.domain.session.bo;

import jakarta.annotation.Nullable;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.FastEnumMap;
import im.turms.server.common.infra.exception.ThrowableInfo;

/**
 * @author James Chen
 */
public record CloseReason(
        SessionCloseStatus closeStatus,
        @Nullable ResponseStatusCode businessStatusCode,
        @Nullable String reason
) {

    private static final FastEnumMap<SessionCloseStatus, CloseReason> POOL =
            new FastEnumMap<>(SessionCloseStatus.class);

    static {
        for (SessionCloseStatus closeStatus : SessionCloseStatus.VALUES) {
            POOL.put(closeStatus, new CloseReason(closeStatus, null, null));
        }
    }

    public static CloseReason get(SessionCloseStatus closeStatus) {
        return POOL.get(closeStatus);
    }

    public static CloseReason get(Throwable throwable) {
        ThrowableInfo info = ThrowableInfo.get(throwable);
        ResponseStatusCode code = info.code();
        SessionCloseStatus closeStatus;
        if (code.isServerError()) {
            closeStatus = SessionCloseStatus.SERVER_ERROR;
        } else if (code.isCodeClientIllegalRequest()) {
            closeStatus = SessionCloseStatus.ILLEGAL_REQUEST;
        } else if (code == ResponseStatusCode.SERVER_UNAVAILABLE) {
            closeStatus = SessionCloseStatus.SERVER_UNAVAILABLE;
        } else {
            closeStatus = SessionCloseStatus.UNKNOWN_ERROR;
        }
        return new CloseReason(closeStatus, code, info.reason());
    }

    public boolean isServerError() {
        return businessStatusCode != null && businessStatusCode.isServerError()
                || closeStatus.isServerError();
    }

}