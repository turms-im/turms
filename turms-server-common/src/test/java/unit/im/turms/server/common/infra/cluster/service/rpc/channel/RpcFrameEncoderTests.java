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

package unit.im.turms.server.common.infra.cluster.service.rpc.channel;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import unit.im.turms.server.common.infra.cluster.service.rpc.codec.BaseCodecTest;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.access.servicerequest.dto.ServiceRequest;
import im.turms.server.common.access.servicerequest.rpc.HandleServiceRequest;
import im.turms.server.common.infra.cluster.service.rpc.channel.RpcFrameDecoder;
import im.turms.server.common.infra.cluster.service.rpc.channel.RpcFrameEncoder;
import im.turms.server.common.infra.tracing.TracingContext;

import static org.assertj.core.api.Assertions.assertThat;

class RpcFrameEncoderTests extends BaseCodecTest {

    @Test
    void test() throws InvalidProtocolBufferException {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .setGroupId(9L)
                        .setDeliveryDate(System.currentTimeMillis())
                        .setText("hello")
                        .build())
                .build();
        ServiceRequest serviceRequest = new ServiceRequest(
                new byte[]{1, 2, 3, 4},
                123L,
                DeviceType.ANDROID,
                1L,
                request.getKindCase(),
                Unpooled.wrappedBuffer(request.toByteArray()));
        HandleServiceRequest rpcRequest = new HandleServiceRequest(serviceRequest);
        rpcRequest.setTracingContext(new TracingContext(999L));
        rpcRequest.setRequestId(111);
        rpcRequest.setFromNodeId("testNode");

        ByteBuf rpcRequestBuffer =
                RpcFrameEncoder.INSTANCE.encode(rpcRequest.getRequestId(), rpcRequest);

        assertThat(rpcRequestBuffer.refCnt())
                .as("The initial refCnt of RPC request buffer should be 1")
                .isEqualTo(1);

        HandleServiceRequest parsedRequest =
                (HandleServiceRequest) new RpcFrameDecoder().decodePayload(null, rpcRequestBuffer);

        assertThat(rpcRequestBuffer.refCnt())
                // It should be 2 because HandleServiceRequest will retainSlice the buffer
                .as("The refCnt of RPC request buffer should be retained to 2")
                .isEqualTo(2);

        ByteBuf turmsRequestBuffer = parsedRequest.getServiceRequest()
                .getTurmsRequestBuffer();
        TurmsRequest parsedTurmsRequest = TurmsRequest.parseFrom(turmsRequestBuffer.nioBuffer());

        // Note refCnf can be 1 or 0 depending on different ByteBuf,
        // so we don't assert it here, and we just need to ensure it will
        // be released finally
        turmsRequestBuffer.release();

        assertThat(rpcRequestBuffer.refCnt())
                // It should be 1 because the sliced buffer of TurmsRequest has been released by 1
                .as("The refCnt of RPC request buffer should be released to 1")
                .isEqualTo(1);

        rpcRequestBuffer.release();

        assertThat(rpcRequestBuffer.refCnt())
                .as("The refCnt of RPC request buffer should be released to 0")
                .isZero();
        assertThat(turmsRequestBuffer.refCnt())
                .as("The refCnt of TurmsRequest buffer should be released to 0")
                .isZero();
        assertThat(parsedTurmsRequest).isNotNull();
    }

}
