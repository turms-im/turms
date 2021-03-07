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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Nullable;

/**
 * @author James Chen
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class CloseReason {
    private final SessionCloseStatus closeStatus;
    @Nullable
    private final TurmsStatusCode businessStatusCode;
    @Nullable
    private final String reason;

    public static CloseReason get(SessionCloseStatus closeStatus) {
        return new CloseReason(closeStatus, null, null);
    }

    public static CloseReason get(TurmsStatusCode statusCode, String reason) {
        return new CloseReason(null, statusCode, reason);
    }

    public static CloseReason get(Throwable throwable) {
        ThrowableInfo info = ThrowableInfo.get(throwable);
        return get(info.getCode(), info.getReason());
    }

    public boolean isServerError() {
        return (businessStatusCode != null && businessStatusCode.isServerError())
                || (closeStatus != null && closeStatus.isServerError());
    }

}