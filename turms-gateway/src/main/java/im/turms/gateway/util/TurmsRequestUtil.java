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
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * @author James Chen
 */
@Log4j2
public class TurmsRequestUtil {

    /**
     * @see im.turms.common.model.dto.request.TurmsRequest:83
     */
    private static final int TURMS_REQUEST_REQUEST_ID_TAG = 10;

    private TurmsRequestUtil() {
    }

    public static long parseRequestId(ByteBuffer turmsRequestBuffer) {
        Objects.requireNonNull(turmsRequestBuffer);
        // The CodedInputStream.newInstance should be efficient because it reuses the direct buffer
        // see com.google.protobuf.CodedInputStream.newInstance(java.nio.ByteBuffer, boolean)
        CodedInputStream stream = CodedInputStream.newInstance(turmsRequestBuffer);
        int tag;
        do {
            try {
                tag = stream.readTag();
                if (tag == TURMS_REQUEST_REQUEST_ID_TAG) {
                    Int64Value value = stream.readMessage(Int64Value.parser(), ExtensionRegistry.getEmptyRegistry());
                    if (value != null && !value.equals(value.getDefaultInstanceForType())) {
                        return value.getValue();
                    } else {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The requestId of TurmsRequest is null");
                    }
                } else {
                    stream.skipField(tag);
                }
            } catch (IOException e) {
                throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "Not a valid TurmsRequest");
            }
        } while (tag != 0);
        // This should never happen because of the code "tag = stream.readTag();" above
        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The requestId field of TurmsRequest is missing");
    }

}
