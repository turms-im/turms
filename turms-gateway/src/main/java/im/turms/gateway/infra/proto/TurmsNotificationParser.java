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
import com.google.protobuf.WireFormat;
import org.springframework.util.Assert;

import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.KIND_NOT_SET;

/**
 * @author James Chen
 */
public final class TurmsNotificationParser {

    /**
     * {@link im.turms.server.common.access.client.dto.notification.TurmsNotification:87}
     */
    private static final int REQUESTER_ID_TAG = 80;
    private static final int CLOSE_STATUS_TAG = 88;
    private static final int RELAYED_REQUEST_TAG = 98;

    private static final long UNSET_VALUE = Long.MIN_VALUE;

    private TurmsNotificationParser() {
    }

    public static SimpleTurmsNotification parseSimpleNotification(
            CodedInputStream turmsRequestInputStream) {
        Assert.notNull(turmsRequestInputStream, "The input stream must not be null");
        // The CodedInputStream.newInstance is efficient because it reuses the direct buffer
        try {
            long requesterId = UNSET_VALUE;
            Integer closeStatus = null;
            TurmsRequest.KindCase type = null;
            boolean done = false;
            while (!done) {
                int tag = turmsRequestInputStream.readTag();
                switch (tag) {
                    case REQUESTER_ID_TAG -> {
                        if (requesterId == UNSET_VALUE) {
                            requesterId = turmsRequestInputStream.readInt64();
                        } else {
                            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                    "Not a valid notification: Duplicate requester ID");
                        }
                    }
                    case CLOSE_STATUS_TAG -> {
                        if (closeStatus == null) {
                            closeStatus = turmsRequestInputStream.readInt32();
                        } else {
                            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                    "Not a valid notification: Duplicate close status");
                        }
                    }
                    case RELAYED_REQUEST_TAG -> {
                        turmsRequestInputStream.readRawVarint32();
                        int i = turmsRequestInputStream.readTag();
                        int kindFieldNumber = WireFormat.getTagFieldNumber(i);
                        type = TurmsRequest.KindCase.forNumber(kindFieldNumber);
                        done = true;
                    }
                    default -> turmsRequestInputStream.skipField(tag);
                }
            }
            if (requesterId == UNSET_VALUE) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Not a valid notification: No requester ID");
            }
            if (type == null || type == KIND_NOT_SET) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Not a valid notification: Unknown request type: "
                                + type);
            }
            return new SimpleTurmsNotification(requesterId, closeStatus, type);
        } catch (IOException e) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Not a valid notification: "
                            + e.getMessage());
        }
    }

}