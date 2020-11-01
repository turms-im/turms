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

    REDIRECT(4300),
    SWITCH(4301),

    ILLEGAL_REQUEST(4400),
    HEARTBEAT_TIMEOUT(4401),
    DISCONNECTED_BY_CLIENT(4402),
    DISCONNECTED_BY_OTHER_DEVICE(4403),

    SERVER_ERROR(4500),
    SERVER_CLOSED(4501),
    SERVER_UNAVAILABLE(4502),

    LOGIN_CONFLICT(4600),

    DISCONNECTED_BY_ADMIN(4700),
    USER_IS_DELETED_OR_INACTIVATED(4701),

    UNKNOWN_ERROR(4900);

    private static final Map<Integer, SessionCloseStatus> codeMap = new HashMap<>((int) (SessionCloseStatus.values().length / 0.5));

    static {
        for (SessionCloseStatus status : SessionCloseStatus.values()) {
            codeMap.put(status.code, status);
        }
    }

    private final int code;

    SessionCloseStatus(int code) {
        this.code = code;
    }

    public static SessionCloseStatus get(int code) {
        return codeMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public boolean is(int wsCode) {
        return this.code == wsCode;
    }

    public boolean isServerError() {
        return 4500 <= code && code < 4600;
    }

}