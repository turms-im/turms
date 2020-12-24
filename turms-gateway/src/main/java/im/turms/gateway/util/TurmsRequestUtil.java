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

package im.turms.gateway.util;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Int64Value;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.user.CreateSessionRequest;
import im.turms.gateway.pojo.dto.SimpleTurmsRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.ByteBuffer;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.CREATE_SESSION_REQUEST;
import static im.turms.common.model.dto.request.TurmsRequest.KindCase.KIND_NOT_SET;

/**
 * @author James Chen
 */
@Log4j2
public class TurmsRequestUtil {

    /**
     * @see im.turms.common.model.dto.request.TurmsRequest:72
     */
    private static final int TURMS_REQUEST_REQUEST_ID_TAG = 10;
    private static final ExtensionRegistry REGISTRY = ExtensionRegistry.getEmptyRegistry();
    private static final long UNDEFINED_REQUEST_ID = Long.MIN_VALUE;

    private TurmsRequestUtil() {
    }

    public static SimpleTurmsRequest parseSimpleRequest(ByteBuffer turmsRequestBuffer) {
        Assert.notNull(turmsRequestBuffer, "turmsRequestBuffer must not be null");
        // The CodedInputStream.newInstance should be efficient because it reuses the direct buffer
        // see com.google.protobuf.CodedInputStream.newInstance(java.nio.ByteBuffer, boolean)
        CodedInputStream stream = CodedInputStream.newInstance(turmsRequestBuffer);
        try {
            long requestId = UNDEFINED_REQUEST_ID;
            TurmsRequest.KindCase type;
            while (true) {
                int tag = stream.readTag();
                if (tag == TURMS_REQUEST_REQUEST_ID_TAG) {
                    Int64Value value = stream.readMessage(Int64Value.parser(), REGISTRY);
                    if (value != null && !value.equals(value.getDefaultInstanceForType())) {
                        requestId = value.getValue();
                    } else {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The requestId of TurmsRequest is null");
                    }
                } else {
                    // key = (field_number << 3) | wire_type
                    int kindFieldNumber = tag >>> 3;
                    type = TurmsRequest.KindCase.forNumber(kindFieldNumber);
                    if (type == null || type == KIND_NOT_SET) {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "Not a valid TurmsRequest");
                    }
                    break;
                }
            }
            if (requestId != UNDEFINED_REQUEST_ID) {
                CreateSessionRequest createSessionRequest = null;
                if (type == CREATE_SESSION_REQUEST) {
                    createSessionRequest = stream.readMessage(CreateSessionRequest.parser(), REGISTRY);
                }
                return new SimpleTurmsRequest(requestId, type, createSessionRequest);
            } else {
                throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "Not a valid TurmsRequest");
            }
        } catch (IOException e) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "Not a valid TurmsRequest");
        }
    }

    public static boolean isSignalRequest(TurmsRequest.KindCase type) {
        return type == TurmsRequest.KindCase.ACK_REQUEST;
    }

}
