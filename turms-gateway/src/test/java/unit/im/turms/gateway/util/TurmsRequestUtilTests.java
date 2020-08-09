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

package unit.im.turms.gateway.util;

import com.google.protobuf.Int64Value;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.signal.AckRequest;
import im.turms.gateway.util.TurmsRequestUtil;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author James Chen
 */
class TurmsRequestUtilTests {

    @Test
    void parseRequestId_shouldThrow_forNullArgument() {
        assertThrows(NullPointerException.class, () -> TurmsRequestUtil.parseRequestId(null));
    }

    @Test
    void parseRequestId_shouldThrow_forEmptyRequest() {
        ByteBuffer emptyRequest = TurmsRequest.newBuilder()
                .buildPartial()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertThrows(TurmsBusinessException.class, () -> TurmsRequestUtil.parseRequestId(emptyRequest));
    }

    @Test
    void parseRequestId_shouldThrow_forPartialRequestWithoutRequestId() {
        ByteBuffer partialRequestWithoutRequestId = TurmsRequest.newBuilder()
                .setAckRequest(AckRequest.newBuilder().build())
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertThrows(TurmsBusinessException.class, () -> TurmsRequestUtil.parseRequestId(partialRequestWithoutRequestId));
    }

    @Test
    void parseRequestId_shouldThrow_forPartialRequestWithNullRequestId() {
        ByteBuffer partialRequestWithNullRequestId = TurmsRequest.newBuilder()
                .setRequestId(Int64Value.newBuilder().build())
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertThrows(TurmsBusinessException.class, () -> TurmsRequestUtil.parseRequestId(partialRequestWithNullRequestId));
    }

    @Test
    void parseRequestId_shouldReturnRequestId_ifRequestIdExists() {
        long requestId = 1000L;
        ByteBuffer requestWithRequestId = TurmsRequest.newBuilder()
                .setRequestId(Int64Value.newBuilder().setValue(requestId).build())
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertEquals(requestId, TurmsRequestUtil.parseRequestId(requestWithRequestId));
    }

}
