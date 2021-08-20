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

package im.turms.server.common.rpc.codec.response;

import com.google.protobuf.InvalidProtocolBufferException;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.cluster.service.codec.codec.Codec;
import im.turms.server.common.cluster.service.codec.codec.CodecId;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.util.CodecUtil;
import im.turms.server.common.util.ProtoUtil;
import io.netty.buffer.ByteBuf;

/**
 * @author James Chen
 */
public class ServiceResponseCodec implements Codec<ServiceResponse> {

    @Override
    public void write(ByteBuf output, ServiceResponse data) {
        output.writeShort((short) data.code().getBusinessCode());
        CodecUtil.writeString(output, data.reason());
    }

    @Override
    public ServiceResponse read(ByteBuf input) {
        TurmsStatusCode statusCode = TurmsStatusCode.from(input.readShort());
        String reason = CodecUtil.readString(input);
        TurmsNotification.Data data;
        if (input.readableBytes() > 0) {
            ByteBuf dataBuffer = input.readSlice(input.readableBytes());
            try {
                // Note that "parseFrom" won't block because the buffer is fully read
                data = TurmsNotification.Data.parseFrom(dataBuffer.nioBuffer());
            } catch (InvalidProtocolBufferException e) {
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
        int reasonLength = reason == null ? 0 : reason.length();
        return Short.BYTES * 2 + reasonLength;
    }

    @Override
    public ByteBuf byteBufToComposite(ServiceResponse data) {
        TurmsNotification.Data dataForRequester = data.dataForRequester();
        return dataForRequester != null
                ? ProtoUtil.getDirectByteBuffer(dataForRequester)
                : null;
    }

    @Override
    public CodecId getCodecId() {
        return CodecId.DTO_SERVICE_RESPONSE;
    }

}
