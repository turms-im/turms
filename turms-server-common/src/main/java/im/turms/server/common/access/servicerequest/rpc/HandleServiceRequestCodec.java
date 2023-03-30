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

package im.turms.server.common.access.servicerequest.rpc;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.servicerequest.dto.ServiceRequest;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamOutput;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcRequestCodec;

import static im.turms.server.common.infra.net.InetAddressUtil.IPV4_BYTE_LENGTH;
import static im.turms.server.common.infra.net.InetAddressUtil.IPV6_BYTE_LENGTH;

/**
 * @author James Chen
 */
public class HandleServiceRequestCodec extends RpcRequestCodec<HandleServiceRequest> {

    private static final int FIXED_FIELDS_LENGTH = Byte.BYTES * 2 + Long.BYTES;
    private static final int IS_IPV4_FLAG = 0;
    private static final int IS_IPV6_FLAG = 1;

    @Override
    public CodecId getCodecId() {
        return CodecId.RPC_HANDLE_SERVICE_REQUEST;
    }

    @Override
    public void writeRequestData(CodecStreamOutput output, HandleServiceRequest data) {
        ServiceRequest request = data.getServiceRequest();
        byte[] ip = request.getIp();
        int ipFormatFlag = switch (ip.length) {
            case IPV4_BYTE_LENGTH -> IS_IPV4_FLAG;
            case IPV6_BYTE_LENGTH -> IS_IPV6_FLAG;
            default -> throw new IllegalArgumentException(
                    "Illegal IP length: "
                            + ip.length);
        };
        output.writeByte(ipFormatFlag);
        output.writeBytes(request.getIp());
        output.writeLong(request.getUserId());
        output.writeByte(request.getDeviceType()
                .getNumber());
    }

    @Override
    public HandleServiceRequest readRequestData(CodecStreamInput in) {
        boolean isIpV4 = in.readByte() == IS_IPV4_FLAG;
        int ipByteLength = isIpV4
                ? IPV4_BYTE_LENGTH
                : IPV6_BYTE_LENGTH;
        byte[] ip = in.readBytes(ipByteLength);
        long userId = in.readLong();
        DeviceType deviceType = DeviceType.forNumber(in.readByte());

        ByteBuf turmsRequestBuffer = in.readRetainedSlice(in.readableBytes());
        ServiceRequest serviceRequest =
                new ServiceRequest(ip, userId, deviceType, null, null, turmsRequestBuffer);
        return new HandleServiceRequest(serviceRequest);
    }

    @Override
    public int initialCapacityForRequest(HandleServiceRequest data) {
        return FIXED_FIELDS_LENGTH + data.getServiceRequest()
                .getIp().length;
    }

    @Override
    public ByteBuf byteBufToComposite(HandleServiceRequest data) {
        return data.getServiceRequest()
                .getTurmsRequestBuffer();
    }

}
