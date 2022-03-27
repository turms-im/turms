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

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.WireFormat;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.ByteBuffer;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.KIND_NOT_SET;

/**
 * @author James Chen
 */
public final class TurmsNotificationParser {

    /**
     * {@link im.turms.common.model.dto.notification.TurmsNotification:99}
     */
    private static final int REQUESTER_ID_TAG = 40;
    private static final int CLOSE_STATUS_TAG = 48;
    private static final int RELAYED_REQUEST_TAG = 58;

    private TurmsNotificationParser() {
    }

    public static SimpleTurmsNotification parseSimpleNotification(ByteBuffer turmsNotificationBuffer) {
        Assert.notNull(turmsNotificationBuffer, "turmsNotificationBuffer must not be null");
        // The CodedInputStream.newInstance is efficient because it reuses the direct buffer
        CodedInputStream stream = CodedInputStream.newInstance(turmsNotificationBuffer);
        try {
            long requesterId = Long.MIN_VALUE;
            Integer closeStatus = null;
            TurmsRequest.KindCase type = null;
            boolean done = false;
            while (!done) {
                int tag = stream.readTag();
                switch (tag) {
                    case REQUESTER_ID_TAG -> {
                        if (requesterId == Long.MIN_VALUE) {
                            requesterId = stream.readInt64();
                        } else {
                            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                    "Not a valid TurmsNotification: Duplicate requester ID");
                        }
                    }
                    case CLOSE_STATUS_TAG -> {
                        if (closeStatus == null) {
                            closeStatus = stream.readInt32();
                        } else {
                            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                    "Not a valid TurmsNotification: Duplicate close status");
                        }
                    }
                    case RELAYED_REQUEST_TAG -> {
                        stream.readRawVarint32();
                        int i = stream.readTag();
                        int kindFieldNumber = WireFormat.getTagFieldNumber(i);
                        type = TurmsRequest.KindCase.forNumber(kindFieldNumber);
                        done = true;
                    }
                    default -> stream.skipField(tag);
                }
            }
            if (requesterId == Long.MIN_VALUE) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "Not a valid TurmsNotification: No requester ID");
            }
            if (type == null || type == KIND_NOT_SET) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Not a valid TurmsNotification: Unknown request type " + type);
            }
            return new SimpleTurmsNotification(requesterId, closeStatus, type);
        } catch (IOException e) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "Not a valid TurmsNotification: " + e.getMessage());
        }
    }

}