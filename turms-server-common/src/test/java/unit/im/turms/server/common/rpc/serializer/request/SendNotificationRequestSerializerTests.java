/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package unit.im.turms.server.common.rpc.serializer.request;

import im.turms.server.common.rpc.request.SendNotificationRequest;
import im.turms.server.common.rpc.serializer.request.SendNotificationRequestSerializer;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import unit.im.turms.server.common.rpc.serializer.BaseSerializerTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SendNotificationRequestSerializerTests extends BaseSerializerTest {

    @Test
    void shouldGetTheSameRequest_afterWriteAndRead_forLegalRequest() {
        byte[] expectedNotificationBytes = {1, 2, 3, 4};
        SendNotificationRequest expectedRequest = new SendNotificationRequest(
                Unpooled.wrappedBuffer(expectedNotificationBytes),
                Set.of(1L, 2L, 3L));
        SendNotificationRequest actualRequest = writeRequestAndReadBuffer(new SendNotificationRequestSerializer(),
                expectedRequest);

        assertThat(ByteBufUtil.getBytes(actualRequest.getNotificationBuffer())).isEqualTo(expectedNotificationBytes);
        assertThat(actualRequest.getRecipientIds()).containsExactlyInAnyOrderElementsOf(expectedRequest.getRecipientIds());
    }

}
