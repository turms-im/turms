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

package unit.im.turms.server.common.cluster.exception;

import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.server.common.cluster.exception.RpcException;
import im.turms.server.common.cluster.service.rpc.RpcErrorCode;
import io.rsocket.exceptions.ApplicationErrorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author James Chen
 */
class RpcExceptionTests {

    @Test
    void get_shouldEqual_ifParamsAreSame() {
        RpcErrorCode errorCode = RpcErrorCode.UNKNOWN_ERROR;
        TurmsStatusCode statusCode = TurmsStatusCode.ALREADY_GROUP_MEMBER;
        String message = "My error message";
        RpcException exception = RpcException.get(errorCode, statusCode, message);

        assertEquals(exception.getErrorCode(), errorCode);
        assertEquals(exception.getStatusCode(), statusCode);
        assertEquals(exception.getDescription(), message);
    }

    @Test
    void parse_shouldEqual_ifParamsAreSame() {
        RpcErrorCode errorCode = RpcErrorCode.UNKNOWN_ERROR;
        TurmsStatusCode statusCode = TurmsStatusCode.ALREADY_GROUP_MEMBER;
        String description = "My error message";
        RpcException exception = RpcException.get(errorCode, statusCode, description);

        RpcException parsedException = RpcException.parse(new ApplicationErrorException(exception.getMessage()));
        assertEquals(exception, parsedException);
    }

}
