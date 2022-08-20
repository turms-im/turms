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

package im.turms.server.common.infra.cluster.service.rpc;

import lombok.Getter;

/**
 * @author James Chen
 */
public enum RpcErrorCode {

    UNKNOWN_ERROR(0),

    // Execution
    FAILED_TO_RUN_RPC(1),

    // No member to run on
    MEMBER_NOT_FOUND(2),
    HEALTHY_MEMBER_NOT_FOUND(3),
    CONNECTION_NOT_FOUND(4),

    // Codec
    CORRUPTED_HEADER(10),
    CODEC_FOR_REQUEST_NOT_FOUND(11),
    CODEC_FOR_RETURN_VALUE_NOT_FOUND(12),
    CODEC_FAILED_TO_DECODE(13),
    CODEC_FAILED_TO_ENCODE(14);

    public static final int ERROR_CODE_LENGTH = 1;
    public static final RpcErrorCode[] VALUES = values();

    @Getter
    private final int errorCode;

    RpcErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public static RpcErrorCode from(int errorCode) {
        for (RpcErrorCode code : VALUES) {
            if (code.errorCode == errorCode) {
                return code;
            }
        }
        return null;
    }

}
