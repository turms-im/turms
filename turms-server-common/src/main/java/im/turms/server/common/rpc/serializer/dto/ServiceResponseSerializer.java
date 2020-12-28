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

package im.turms.server.common.rpc.serializer.dto;

import com.google.protobuf.InvalidProtocolBufferException;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.cluster.service.serialization.serializer.SerializerId;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.util.ProtoUtil;
import im.turms.server.common.util.SerializerUtil;
import io.netty.buffer.ByteBuf;
import reactor.core.Exceptions;

/**
 * @author James Chen
 */
public class ServiceResponseSerializer implements Serializer<ServiceResponse> {

    @Override
    public void write(ByteBuf output, ServiceResponse data) {
        output.writeShort((short) data.getCode().getBusinessCode());
        SerializerUtil.writeString(output, data.getReason());
    }

    @Override
    public ServiceResponse read(ByteBuf input) {
        TurmsStatusCode statusCode = TurmsStatusCode.from(input.readShort());
        String reason = SerializerUtil.readString(input);
        TurmsNotification.Data data;
        if (input.readableBytes() > 0) {
            ByteBuf dataBuffer = input.slice();
            try {
                data = TurmsNotification.Data.parseFrom(dataBuffer.nioBuffer());
            } catch (InvalidProtocolBufferException e) {
                throw Exceptions.propagate(e);
            }
        } else {
            data = null;
        }
        return new ServiceResponse(data, statusCode, reason);
    }

    @Override
    public int initialCapacity(ServiceResponse data) {
        String reason = data.getReason();
        int reasonLength = reason != null ? reason.length() : 0;
        return Short.BYTES * 2 + reasonLength;
    }

    @Override
    public ByteBuf byteBufToComposite(ServiceResponse data) {
        TurmsNotification.Data dataForRequester = data.getDataForRequester();
        return dataForRequester != null
                ? ProtoUtil.getDirectByteBuffer(dataForRequester)
                : null;
    }

    @Override
    public SerializerId getSerializerId() {
        return SerializerId.DTO_SERVICE_RESPONSE;
    }

}
