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

package im.turms.server.common.cluster.service.rpc;

import lombok.Getter;

/**
 * @author James Chen
 */
public enum RpcErrorCode {

    UNKNOWN_ERROR(0),

    FAILED_TO_RUN_RPC(1),

    SERVICE_NOT_FOUND(2),

    INVALID_BUFFER_TYPE_NOT_FOUND(5),
    SERIALIZER_FOR_REQUEST_NOT_FOUND(6),
    SERIALIZER_FOR_RETURN_VALUE_NOT_FOUND(7),
    SERIALIZER_FAILED_TO_DESERIALIZE(8);

    @Getter
    private final int errorCode;

    RpcErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public static RpcErrorCode from(int errorCode) {
        for (RpcErrorCode code : RpcErrorCode.values()) {
            if (code.errorCode == errorCode) {
                return code;
            }
        }
        return null;
    }

}
