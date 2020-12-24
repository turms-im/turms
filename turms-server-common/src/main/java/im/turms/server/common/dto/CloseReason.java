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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.reactive.socket.CloseStatus;

import javax.annotation.Nullable;

/**
 * @author James Chen
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class CloseReason {
    /**
     * Cases:
     * 1. The business code of TurmsStatusCode
     * 2. WebSocket status code
     * 3. The code of SessionCloseStatus that can be used by both TCP/WebSocket connection
     */
    private final int code;
    @Nullable
    private final String reason;
    private final boolean isTurmsStatusCode;

    public static CloseReason get(int code, String reason, boolean isTurmsStatusCode) {
        return new CloseReason(code, reason, isTurmsStatusCode);
    }

    public static CloseReason get(SessionCloseStatus closeStatus) {
        return new CloseReason(closeStatus.getCode(), null, false);
    }

    public static CloseReason get(TurmsStatusCode statusCode) {
        return new CloseReason(statusCode.getBusinessCode(), null, true);
    }

    public static CloseReason get(TurmsStatusCode statusCode, String reason) {
        return new CloseReason(statusCode.getBusinessCode(), reason, true);
    }

    public static CloseReason get(CloseStatus closeStatus) {
        return new CloseReason(closeStatus.getCode(), closeStatus.getReason(), false);
    }

}