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

package unit.im.turms.server.common.domain.session.rpc;

import java.util.Set;

import org.junit.jupiter.api.Test;
import unit.im.turms.server.common.infra.cluster.service.rpc.codec.BaseCodecTest;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.session.rpc.SetUserOfflineRequest;
import im.turms.server.common.domain.session.rpc.SetUserOfflineRequestCodec;

import static org.assertj.core.api.Assertions.assertThat;

class SetUserOfflineRequestCodecTests extends BaseCodecTest {

    @Test
    void shouldGetTheSameRequest_afterWriteAndRead_forLegalRequest() {
        SetUserOfflineRequest expectedRequest = new SetUserOfflineRequest(
                1L,
                Set.of(DeviceType.ANDROID, DeviceType.IOS, DeviceType.DESKTOP),
                SessionCloseStatus.ILLEGAL_REQUEST);
        SetUserOfflineRequest actualRequest =
                writeRequestAndReadBuffer(new SetUserOfflineRequestCodec(), expectedRequest);

        assertThat(actualRequest.getUserId()).isEqualTo(expectedRequest.getUserId());
        assertThat(actualRequest.getDeviceTypes())
                .containsExactlyInAnyOrderElementsOf(expectedRequest.getDeviceTypes());
        assertThat(actualRequest.getCloseStatus()).isEqualTo(expectedRequest.getCloseStatus());
    }

}
