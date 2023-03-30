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

package im.turms.gateway.infra.proto;

import java.io.IOException;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.WireFormat;
import org.springframework.util.Assert;

import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.user.CreateSessionRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_SESSION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.KIND_NOT_SET;

/**
 * @author James Chen
 */
public final class TurmsRequestParser {

    /**
     * {@link TurmsRequest:73}
     */
    private static final int REQUEST_ID_TAG = 8;
    private static final ExtensionRegistry REGISTRY = ExtensionRegistry.getEmptyRegistry();
    private static final long UNDEFINED_REQUEST_ID = Long.MIN_VALUE;

    private TurmsRequestParser() {
    }

    public static SimpleTurmsRequest parseSimpleRequest(CodedInputStream turmsRequestInputStream) {
        Assert.notNull(turmsRequestInputStream, "The input stream must not be null");
        try {
            long requestId = UNDEFINED_REQUEST_ID;
            TurmsRequest.KindCase type;
            while (true) {
                int tag = turmsRequestInputStream.readTag();
                if (tag == REQUEST_ID_TAG) {
                    if (requestId == UNDEFINED_REQUEST_ID) {
                        requestId = turmsRequestInputStream.readInt64();
                    } else {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "Not a valid request: Duplicate request ID");
                    }
                } else {
                    int kindFieldNumber = WireFormat.getTagFieldNumber(tag);
                    type = TurmsRequest.KindCase.forNumber(kindFieldNumber);
                    break;
                }
            }
            if (requestId == UNDEFINED_REQUEST_ID) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Not a valid request: No request ID");
            }
            if (type == null || type == KIND_NOT_SET) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Not a valid request: Unknown request type: "
                                + type);
            }
            CreateSessionRequest createSessionRequest = null;
            if (type == CREATE_SESSION_REQUEST) {
                createSessionRequest = turmsRequestInputStream
                        .readMessage(CreateSessionRequest.parser(), REGISTRY);
            }
            return new SimpleTurmsRequest(requestId, type, createSessionRequest);
        } catch (IOException e) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Not a valid request: "
                            + e.getMessage());
        }
    }

}