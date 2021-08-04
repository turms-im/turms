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

package im.turms.server.common.cluster.service.rpc.codec;

import im.turms.server.common.cluster.service.codec.codec.Codec;
import im.turms.server.common.cluster.service.codec.codec.CodecId;
import im.turms.server.common.cluster.service.rpc.RpcErrorCode;
import im.turms.server.common.cluster.service.rpc.exception.RpcException;
import im.turms.server.common.constant.TurmsStatusCode;
import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;

/**
 * @author James Chen
 */
public class RpcExceptionCodec implements Codec<RpcException> {

    @Override
    public CodecId getCodecId() {
        return CodecId.RPC_EXCEPTION;
    }

    @Override
    public void write(ByteBuf out, RpcException data) {
        out.writeShort(data.getErrorCode().getErrorCode())
                .writeShort(data.getStatusCode().getBusinessCode());
        String description = data.getDescription();
        if (description != null) {
            out.writeBytes(description.getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public RpcException read(ByteBuf in) {
        return RpcException.get(
                parseErrorCode(in),
                parseStatusCode(in),
                parseDescription(in));
    }

    @Override
    public int initialCapacity(RpcException data) {
        return Short.BYTES * 2 + StringUtils.length(data.getDescription());
    }

    private static RpcErrorCode parseErrorCode(ByteBuf in) {
        RpcErrorCode code;
        int errorCode;
        try {
            errorCode = in.readShort() & 0xFFFF;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse the error code", e);
        }
        code = RpcErrorCode.from(errorCode);
        if (code == null) {
            throw new IllegalArgumentException("Unknown error code: " + errorCode);
        }
        return code;
    }

    private static TurmsStatusCode parseStatusCode(ByteBuf in) {
        TurmsStatusCode code;
        int statusCode;
        try {
            statusCode = in.readShort() & 0xFFFF;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse the status code", e);
        }
        code = TurmsStatusCode.from(statusCode);
        if (code == null) {
            throw new IllegalArgumentException("Unknown status code: " + statusCode);
        }
        return code;
    }

    @Nullable
    private static String parseDescription(ByteBuf in) {
        int length = in.readableBytes();
        if (length == 0) {
            return null;
        }
        try {
            byte[] bytes = new byte[length];
            in.readBytes(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse description", e);
        }
    }

}
