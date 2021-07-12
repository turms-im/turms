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
 */
public enum SessionCloseStatus {

    //**********************************************************
    //* Closed due to client misbehavior
    //**********************************************************
    ILLEGAL_REQUEST(100),
    // Closed due to the timeout of user operations
    SWITCH(110),
    HEARTBEAT_TIMEOUT(111),
    LOGIN_TIMEOUT(112),

    //**********************************************************
    //* Closed due to server behavior
    //**********************************************************
    SERVER_ERROR(200),
    SERVER_CLOSED(201),
    SERVER_UNAVAILABLE(202), //TODO: reserved

    //**********************************************************
    //* Closed due to unknown error
    //**********************************************************
    UNKNOWN_ERROR(300),

    //**********************************************************
    //* Closed by user actively
    //**********************************************************
    DISCONNECTED_BY_CLIENT(400),
    DISCONNECTED_BY_OTHER_DEVICE(401),

    //**********************************************************
    //* Closed by admin actively
    //**********************************************************
    DISCONNECTED_BY_ADMIN(500),

    //**********************************************************
    //* Closed due to the change of user status
    //**********************************************************
    USER_IS_DELETED_OR_INACTIVATED(600);

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
        return 200 <= code && code < 300;
    }

}