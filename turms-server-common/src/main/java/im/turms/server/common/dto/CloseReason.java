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

package im.turms.server.common.dto;

import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.ThrowableInfo;

import javax.annotation.Nullable;

/**
 * @author James Chen
 */
public record CloseReason(
        SessionCloseStatus closeStatus,
        @Nullable TurmsStatusCode businessStatusCode,
        @Nullable String reason
) {

    public static CloseReason get(SessionCloseStatus closeStatus) {
        return new CloseReason(closeStatus, null, null);
    }

    public static CloseReason get(Throwable throwable) {
        ThrowableInfo info = ThrowableInfo.get(throwable);
        TurmsStatusCode code = info.code();
        SessionCloseStatus closeStatus;
        if (TurmsStatusCode.isServerError(code.getBusinessCode())) {
            closeStatus = SessionCloseStatus.SERVER_ERROR;
        } else {
            if (TurmsStatusCode.isCodeClientIllegalRequest(code.getBusinessCode())) {
                closeStatus = SessionCloseStatus.ILLEGAL_REQUEST;
            } else if (code == TurmsStatusCode.SERVER_UNAVAILABLE) {
                closeStatus = SessionCloseStatus.SERVER_UNAVAILABLE;
            } else {
                closeStatus = SessionCloseStatus.UNKNOWN_ERROR;
            }
        }
        return new CloseReason(closeStatus, code, info.reason());
    }

    public boolean isServerError() {
        return businessStatusCode != null && businessStatusCode.isServerError()
                || closeStatus.isServerError();
    }

}