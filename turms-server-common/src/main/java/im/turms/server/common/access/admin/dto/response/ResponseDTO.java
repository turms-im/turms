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

package im.turms.server.common.access.admin.dto.response;

import java.util.Date;
import jakarta.annotation.Nullable;

import im.turms.server.common.access.common.ResponseStatusCode;

/**
 * @author James Chen
 */
public record ResponseDTO<T>(
        Integer code,
        @Nullable String reason,
        String exception,
        Date timestamp,
        @Nullable T data
) {

    public ResponseDTO(ResponseStatusCode responseStatusCode) {
        this(responseStatusCode
                .getBusinessCode(), responseStatusCode.getReason(), null, new Date(), null);
    }

    public ResponseDTO(ResponseStatusCode responseStatusCode, @Nullable String reason) {
        this(responseStatusCode.getBusinessCode(),
                reason == null
                        ? responseStatusCode.getReason()
                        : reason,
                null,
                new Date(),
                null);
    }

    public ResponseDTO(
            ResponseStatusCode responseStatusCode,
            @Nullable String reason,
            @Nullable Throwable throwable) {
        this(responseStatusCode.getBusinessCode(),
                reason == null
                        ? responseStatusCode.getReason()
                        : reason,
                throwable == null
                        ? null
                        : throwable.toString(),
                new Date(),
                null);
    }

    public ResponseDTO(ResponseStatusCode responseStatusCode, @Nullable T data) {
        this(responseStatusCode
                .getBusinessCode(), responseStatusCode.getReason(), null, new Date(), data);
    }

}