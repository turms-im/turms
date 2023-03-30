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

package unit.im.turms.server.common.domain.servicerequest.rpc;

import org.junit.jupiter.api.Test;
import unit.im.turms.server.common.infra.cluster.service.rpc.codec.BaseCodecTest;

import im.turms.server.common.access.client.dto.model.common.LongsWithVersion;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.access.servicerequest.dto.ServiceResponse;
import im.turms.server.common.access.servicerequest.rpc.ServiceResponseCodec;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceResponseCodecTests extends BaseCodecTest {

    @Test
    void shouldGetTheSameRequest_afterWriteAndRead_forLegalRequest() {
        TurmsNotification.Data dataForRequester = TurmsNotification.Data.newBuilder()
                .setLongsWithVersion(LongsWithVersion.newBuilder()
                        .addLongs(100))
                .build();
        ServiceResponse expectedResponse = new ServiceResponse(
                dataForRequester,
                ResponseStatusCode.OK,
                "response for the status code");
        ServiceResponse actualRequest =
                writeDataAndReadBuffer(new ServiceResponseCodec(), expectedResponse);

        assertThat(actualRequest.code()).isEqualTo(expectedResponse.code());
        assertThat(actualRequest.reason()).isEqualTo(expectedResponse.reason());
        assertThat(actualRequest.dataForRequester()).isEqualTo(expectedResponse.dataForRequester());
    }

}
