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

package im.turms.common.constant.statuscode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author James Chen
 * @see <a href="https://tools.ietf.org/html/rfc6455#page-47">Reserved Status Code Ranges</a>
 */
public enum SessionCloseStatus {
    SWITCH(300),

    ILLEGAL_REQUEST(400),
    HEARTBEAT_TIMEOUT(401),
    DISCONNECTED_BY_CLIENT(402),
    DISCONNECTED_BY_OTHER_DEVICE(403),

    SERVER_ERROR(500),
    SERVER_CLOSED(501),
    SERVER_UNAVAILABLE(502),

    LOGIN_CONFLICT(600),
    LOGIN_TIMEOUT(601),

    DISCONNECTED_BY_ADMIN(700),
    USER_IS_DELETED_OR_INACTIVATED(701),

    UNKNOWN_ERROR(900);

    private static final Map<Integer, SessionCloseStatus> CODE_POOL = new HashMap<>((int) (SessionCloseStatus.values().length / 0.5));

    static {
        for (SessionCloseStatus status : SessionCloseStatus.values()) {
            CODE_POOL.put(status.code, status);
        }
    }

    private final int code;

    SessionCloseStatus(int code) {
        this.code = code;
    }

    public static SessionCloseStatus get(int code) {
        return CODE_POOL.get(code);
    }

    public int getCode() {
        return code;
    }

    public boolean is(int code) {
        return this.code == code;
    }

    public boolean isServerError() {
        return 500 <= code && code < 600;
    }

}