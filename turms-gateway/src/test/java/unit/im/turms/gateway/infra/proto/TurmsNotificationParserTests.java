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

import im.turms.gateway.infra.proto.SimpleTurmsNotification;
import im.turms.gateway.infra.proto.TurmsNotificationParser;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.infra.exception.ResponseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author James Chen
 */
class TurmsNotificationParserTests {

    @Test
    void parseSimpleNotification_shouldThrow_forNullArgument() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TurmsNotificationParser.parseSimpleNotification(null));
    }

    @Test
    void parseSimpleNotification_shouldThrow_forEmptyNotification() {
        ByteBuffer emptyRequest = TurmsNotification.newBuilder()
                .buildPartial()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertThatExceptionOfType(ResponseException.class).isThrownBy(() -> TurmsNotificationParser
                .parseSimpleNotification(CodedInputStream.newInstance(emptyRequest)));
    }

    @Test
    void parseSimpleNotification_shouldThrow_forPartialNotificationWithoutRequesterId() {
        ByteBuffer partialRequestWithoutRequestId = TurmsNotification.newBuilder()
                .setCloseStatus(1)
                .setRelayedRequest(TurmsRequest.newBuilder()
                        .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                                .build()))
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        assertThatExceptionOfType(ResponseException.class)
                .isThrownBy(() -> TurmsNotificationParser.parseSimpleNotification(
                        CodedInputStream.newInstance(partialRequestWithoutRequestId)));
    }

    @Test
    void parseSimpleNotification_shouldReturnInstance_ifRequestIdAndTypeExists() {
        long requesterId = 1000L;
        ByteBuffer requestWithRequestId = TurmsNotification.newBuilder()
                .setRequesterId(requesterId)
                .setRelayedRequest(TurmsRequest.newBuilder()
                        .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                                .build()))
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        SimpleTurmsNotification notification = TurmsNotificationParser
                .parseSimpleNotification(CodedInputStream.newInstance(requestWithRequestId));
        assertThat(notification.requesterId()).isEqualTo(requesterId);
        assertThat(notification.relayedRequestType())
                .isEqualTo(TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST);
    }

    @Test
    void parseSimpleNotification_shouldReturnInstance_ifRequestIdAndCloseStatusAndTypeExists() {
        long requesterId = 1000L;
        int closeStatus = 1;
        ByteBuffer requestWithRequestId = TurmsNotification.newBuilder()
                .setRequesterId(requesterId)
                .setCloseStatus(closeStatus)
                .setRelayedRequest(TurmsRequest.newBuilder()
                        .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                                .build()))
                .build()
                .toByteString()
                .asReadOnlyByteBuffer();

        SimpleTurmsNotification notification = TurmsNotificationParser
                .parseSimpleNotification(CodedInputStream.newInstance(requestWithRequestId));
        assertThat(notification.requesterId()).isEqualTo(requesterId);
        assertThat(notification.closeStatus()).isEqualTo(closeStatus);
        assertThat(notification.relayedRequestType())
                .isEqualTo(TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST);
    }

}
