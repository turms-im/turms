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

package im.turms.server.common.rpc.serializer.request;

import im.turms.common.constant.DeviceType;
import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.cluster.service.serialization.serializer.SerializerId;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.rpc.request.HandleServiceRequest;
import io.netty.buffer.ByteBuf;

/**
 * @author James Chen
 */
public class HandleServiceRequestSerializer implements Serializer<HandleServiceRequest> {

    private static final int IPV4_BYTE_LENGTH = 4;
    private static final int IPV6_BYTE_LENGTH = 16;

    private static final int FIXED_FIELDS_LENGTH = Byte.BYTES * 2 + Long.BYTES * 2;
    private static final int IS_IPV4_FLAG = 0;

    @Override
    public void write(ByteBuf output, HandleServiceRequest data) {
        ServiceRequest request = data.getServiceRequest();
        output.writeByte(IS_IPV4_FLAG);
        output.writeBytes(request.getIp());
        output.writeLong(request.getUserId());
        output.writeByte(request.getDeviceType().getNumber());
        output.writeLong(request.getTraceId());
    }

    @Override
    public HandleServiceRequest read(ByteBuf input) {
        boolean isIpV4 = input.readByte() == IS_IPV4_FLAG;
        int ipByteLength = isIpV4 ? IPV4_BYTE_LENGTH : IPV6_BYTE_LENGTH;
        int length = FIXED_FIELDS_LENGTH + ipByteLength;
        ByteBuf firstByteBuf = input.readSlice(length);

        byte[] ip = new byte[ipByteLength];
        firstByteBuf.readBytes(ip);
        long userId = firstByteBuf.readLong();
        DeviceType deviceType = DeviceType.forNumber(firstByteBuf.readByte());
        long traceId = firstByteBuf.readLong();

        ByteBuf turmsRequestBuffer = input.slice();
        ServiceRequest serviceRequest = new ServiceRequest(ip, userId, deviceType, traceId, null, null, turmsRequestBuffer);
        return new HandleServiceRequest(serviceRequest);
    }

    @Override
    public int initialCapacity(HandleServiceRequest data) {
        return FIXED_FIELDS_LENGTH + data.getServiceRequest().getIp().length;
    }

    @Override
    public ByteBuf byteBufToComposite(HandleServiceRequest data) {
        return data.getServiceRequest().getTurmsRequestBuffer();
    }

    @Override
    public SerializerId getSerializerId() {
        return SerializerId.RPC_HANDLE_SERVICE_REQUEST;
    }

}
