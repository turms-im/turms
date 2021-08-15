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

import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.message.CreateMessageRequest;
import im.turms.gateway.pojo.dto.SimpleTurmsRequest;
import im.turms.gateway.util.TurmsRequestUtil;
import im.turms.server.common.exception.TurmsBusinessException;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author James Chen
 */
class TurmsRequestUtilTests {

    @Test
    void parseSimpleRequest_shouldThrow_forNullArgument() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TurmsRequestUtil.parseSimpleRequest(null));
    }

    @Test
    void parseSimpleRequest_shouldThrow_forEmptyRequest() {
        ByteBuffer emptyRequest = TurmsRequest.newBuilder()
                .buildPartial()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertThatExceptionOfType(TurmsBusinessException.class)
                .isThrownBy(() -> TurmsRequestUtil.parseSimpleRequest(emptyRequest));
    }

    @Test
    void parseSimpleRequest_shouldThrow_forPartialRequestWithoutRequestId() {
        ByteBuffer partialRequestWithoutRequestId = TurmsRequest.newBuilder()
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertThatExceptionOfType(TurmsBusinessException.class)
                .isThrownBy(() -> TurmsRequestUtil.parseSimpleRequest(partialRequestWithoutRequestId));
    }

    @Test
    void parseSimpleRequest_shouldThrow_forPartialRequestWithNullRequestId() {
        ByteBuffer partialRequestWithNullRequestId = TurmsRequest.newBuilder()
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertThatExceptionOfType(TurmsBusinessException.class)
                .isThrownBy(() -> TurmsRequestUtil.parseSimpleRequest(partialRequestWithNullRequestId));
    }

    @Test
    void parseSimpleRequest_shouldReturnRequestIdAndType_ifRequestIdExists() {
        long requestId = 1000L;
        ByteBuffer requestWithRequestId = TurmsRequest.newBuilder()
                .setRequestId(requestId)
                .setCreateMessageRequest(CreateMessageRequest.newBuilder().buildPartial())
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        SimpleTurmsRequest request = TurmsRequestUtil.parseSimpleRequest(requestWithRequestId);
        assertThat(request.requestId()).isEqualTo(requestId);
        assertThat(request.type()).isEqualTo(TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST);
    }

}
