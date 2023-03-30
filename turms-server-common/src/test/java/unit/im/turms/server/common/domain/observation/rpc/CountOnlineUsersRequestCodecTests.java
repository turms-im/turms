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

package unit.im.turms.server.common.domain.observation.rpc;

import org.junit.jupiter.api.Test;
import unit.im.turms.server.common.infra.cluster.service.rpc.codec.BaseCodecTest;

import im.turms.server.common.domain.observation.rpc.CountOnlineUsersRequest;
import im.turms.server.common.domain.observation.rpc.CountOnlineUsersRequestCodec;

import static org.assertj.core.api.Assertions.assertThat;

class CountOnlineUsersRequestCodecTests extends BaseCodecTest {

    @Test
    void shouldGetTheSameRequest_afterWriteAndRead_forLegalRequest() {
        CountOnlineUsersRequest expectedRequest = new CountOnlineUsersRequest();
        CountOnlineUsersRequest actualRequest =
                writeRequestAndReadBuffer(new CountOnlineUsersRequestCodec(), expectedRequest);

        assertThat(actualRequest).isNotNull();
    }

}
