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

package unit.im.turms.server.common.access.client.dto;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import org.junit.jupiter.api.Test;

import im.turms.server.common.access.client.dto.ClientMessageEncoder;
import im.turms.server.common.access.client.dto.model.user.UserSession;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class ClientMessageEncoderTests {

    @Test
    void encodeResponse() throws InvalidProtocolBufferException {
        long timestamp = System.currentTimeMillis();
        int requestId = 123;
        int code = 987;
        String reason = "Hello World";
        ByteBuf response = ClientMessageEncoder.encodeResponse(timestamp, requestId, code, reason);
        TurmsNotification actual = TurmsNotification.parseFrom(response.nioBuffer());
        assertThat(actual).isEqualTo(TurmsNotification.newBuilder()
                .setTimestamp(timestamp)
                .setRequestId(requestId)
                .setCode(code)
                .setReason(reason)
                .build());
    }

    @Test
    void encodeCloseNotification() throws InvalidProtocolBufferException {
        long timestamp = System.currentTimeMillis();
        SessionCloseStatus closeStatus = SessionCloseStatus.CONNECTION_CLOSED;
        ResponseStatusCode code = ResponseStatusCode.UPDATING_TYPING_STATUS_IS_DISABLED;
        String reason = "Hello World";
        ByteBuf response =
                ClientMessageEncoder.encodeCloseNotification(timestamp, closeStatus, code, reason);
        TurmsNotification actual = TurmsNotification.parseFrom(response.nioBuffer());
        assertThat(actual).isEqualTo(TurmsNotification.newBuilder()
                .setTimestamp(timestamp)
                .setCloseStatus(closeStatus.getCode())
                .setCode(code.getBusinessCode())
                .setReason(reason)
                .build());
    }

    @Test
    void encodeUserSessionNotification() throws InvalidProtocolBufferException {
        long timestamp = System.currentTimeMillis();
        String sessionId = "my session ID";
        String serverId = "my server ID";
        ByteBuf response =
                ClientMessageEncoder.encodeUserSessionNotification(timestamp, sessionId, serverId);
        TurmsNotification actual = TurmsNotification.parseFrom(response.nioBuffer());
        assertThat(actual).isEqualTo(TurmsNotification.newBuilder()
                .setTimestamp(timestamp)
                .setData(TurmsNotification.Data.newBuilder()
                        .setUserSession(UserSession.newBuilder()
                                .setSessionId(sessionId)
                                .setServerId(serverId)
                                .build()))
                .build());
    }
}
