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

package unit.im.turms.server.common.infra.proto;

import java.util.List;

import com.google.protobuf.ByteString;
import org.junit.jupiter.api.Test;

import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.infra.proto.ProtoFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class ProtoFormatterTests {

    @Test
    void toJSON5() {
        TurmsRequest.Builder request = TurmsRequest.newBuilder()
                .setRequestId(123L)
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .setText("Hello World")
                        .addAllRecords(List.of(ByteString.copyFromUtf8("test record 1"),
                                ByteString.copyFromUtf8("test record 2")))
                        .setGroupId(2)
                        .setDeliveryDate(123456789)
                        .build());
        String text = ProtoFormatter.toJSON5(request, 128);

        String expected =
                "{request_id:123,create_message_request:{group_id:2,delivery_date:123456789,text:'*',records:'*'}}";
        assertThat(text).isEqualTo(expected);
    }

}
