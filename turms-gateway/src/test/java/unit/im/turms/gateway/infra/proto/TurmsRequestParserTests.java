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

package unit.im.turms.gateway.infra.proto;

import java.nio.ByteBuffer;

import com.google.protobuf.CodedInputStream;
import org.junit.jupiter.api.Test;

import im.turms.gateway.infra.proto.SimpleTurmsRequest;
import im.turms.gateway.infra.proto.TurmsRequestParser;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.infra.exception.ResponseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author James Chen
 */
class TurmsRequestParserTests {

    @Test
    void parseSimpleRequest_shouldThrow_forNullArgument() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TurmsRequestParser.parseSimpleRequest(null));
    }

    @Test
    void parseSimpleRequest_shouldThrow_forEmptyRequest() {
        ByteBuffer emptyRequest = TurmsRequest.newBuilder()
                .buildPartial()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertThatExceptionOfType(ResponseException.class).isThrownBy(() -> TurmsRequestParser
                .parseSimpleRequest(CodedInputStream.newInstance(emptyRequest)));
    }

    @Test
    void parseSimpleRequest_shouldThrow_forPartialRequestWithoutRequestId() {
        ByteBuffer partialRequestWithoutRequestId = TurmsRequest.newBuilder()
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .buildPartial())
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertThatExceptionOfType(ResponseException.class).isThrownBy(() -> TurmsRequestParser
                .parseSimpleRequest(CodedInputStream.newInstance(partialRequestWithoutRequestId)));
    }

    @Test
    void parseSimpleRequest_shouldReturnRequestIdAndType_ifRequestIdExists() {
        long requestId = 1000L;
        ByteBuffer requestWithRequestId = TurmsRequest.newBuilder()
                .setRequestId(requestId)
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .buildPartial())
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        SimpleTurmsRequest request = TurmsRequestParser
                .parseSimpleRequest(CodedInputStream.newInstance(requestWithRequestId));
        assertThat(request.requestId()).isEqualTo(requestId);
        assertThat(request.type()).isEqualTo(TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST);
    }

}
