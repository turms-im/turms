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

package unit.im.turms.server.common.rpc.codec.response;

import im.turms.common.model.bo.common.Int64Values;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.rpc.codec.response.ServiceResponseCodec;
import org.junit.jupiter.api.Test;
import unit.im.turms.server.common.rpc.codec.BaseCodecTest;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceResponseSerializerTests extends BaseCodecTest {

    @Test
    void shouldGetTheSameRequest_afterWriteAndRead_forLegalRequest() {
        TurmsNotification.Data dataForRequester = TurmsNotification.Data.newBuilder()
                .setIds(Int64Values.newBuilder().addValues(100).build())
                .build();
        ServiceResponse expectedResponse = new ServiceResponse(dataForRequester,
                TurmsStatusCode.OK, "response for the status code");
        ServiceResponse actualRequest = writeDataAndReadBuffer(new ServiceResponseCodec(),
                expectedResponse);

        assertThat(actualRequest.getCode()).isEqualTo(expectedResponse.getCode());
        assertThat(actualRequest.getReason()).isEqualTo(expectedResponse.getReason());
        assertThat(actualRequest.getDataForRequester()).isEqualTo(expectedResponse.getDataForRequester());
    }

}
