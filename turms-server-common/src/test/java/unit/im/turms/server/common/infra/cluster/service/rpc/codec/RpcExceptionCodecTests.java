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

package unit.im.turms.server.common.infra.cluster.service.rpc.codec;

import org.junit.jupiter.api.Test;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.service.rpc.RpcErrorCode;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcExceptionCodec;
import im.turms.server.common.infra.cluster.service.rpc.exception.RpcException;

import static org.assertj.core.api.Assertions.assertThat;

class RpcExceptionCodecTests extends BaseCodecTest {

    @Test
    void shouldGetTheSameRequest_afterWriteAndRead_forLegalRequest() {
        RpcErrorCode errorCode = RpcErrorCode.UNKNOWN_ERROR;
        ResponseStatusCode statusCode = ResponseStatusCode.SERVER_INTERNAL_ERROR;
        String message = "My error message";
        RpcException exception = RpcException.get(errorCode, statusCode, message);
        RpcException actualException = writeDataAndReadBuffer(new RpcExceptionCodec(), exception);

        assertThat(actualException).isEqualTo(exception);
    }

}
