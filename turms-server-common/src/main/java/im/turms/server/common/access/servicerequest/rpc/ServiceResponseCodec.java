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

import java.io.IOException;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.access.servicerequest.dto.ServiceResponse;
import im.turms.server.common.infra.cluster.service.codec.codec.Codec;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamOutput;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.proto.ProtoDecoder;
import im.turms.server.common.infra.proto.ProtoEncoder;

/**
 * @author James Chen
 */
public class ServiceResponseCodec implements Codec<ServiceResponse> {

    @Override
    public void write(CodecStreamOutput output, ServiceResponse data) {
        output.writeShort((short) data.code()
                .getBusinessCode());
        output.writeNullableString(data.reason());
    }

    @Override
    public ServiceResponse read(CodecStreamInput input) {
        ResponseStatusCode statusCode = ResponseStatusCode.from(input.readShort());
        String reason = input.readNullableString();
        TurmsNotification.Data data;
        if (input.readableBytes() > 0) {
            ByteBuf dataBuffer = input.readSlice(input.readableBytes());
            try {
                // Note that "parseFrom" won't block because the buffer is fully read
                data = TurmsNotification.Data.parseFrom(ProtoDecoder.newInputStream(dataBuffer));
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse the buffer of service response", e);
            }
        } else {
            data = null;
        }
        return new ServiceResponse(data, statusCode, reason);
    }

    @Override
    public int initialCapacity(ServiceResponse data) {
        String reason = data.reason();
        int reasonLength = reason == null
                ? 0
                : StringUtil.getLength(reason) + 1;
        return Short.BYTES * 2 + reasonLength;
    }

    @Override
    public ByteBuf byteBufToComposite(ServiceResponse data) {
        TurmsNotification.Data dataForRequester = data.dataForRequester();
        return dataForRequester == null
                ? null
                : ProtoEncoder.getDirectByteBuffer(dataForRequester);
    }

    @Override
    public CodecId getCodecId() {
        return CodecId.DTO_SERVICE_RESPONSE;
    }

}