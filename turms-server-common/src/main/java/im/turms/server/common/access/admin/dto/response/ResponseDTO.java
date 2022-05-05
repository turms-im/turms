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


import com.fasterxml.jackson.annotation.JsonCreator;
import im.turms.server.common.access.common.ResponseStatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * @author James Chen
 */
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@Data
public class ResponseDTO<T> {

    private final Integer code;
    @Nullable
    private final String reason;
    private final String exception;
    private final Date timestamp;
    @Nullable
    private final T data;

    public ResponseDTO(ResponseStatusCode responseStatusCode) {
        this.code = responseStatusCode.getBusinessCode();
        this.reason = responseStatusCode.getReason();
        this.exception = null;
        this.timestamp = new Date();
        data = null;
    }

    public ResponseDTO(ResponseStatusCode responseStatusCode, @Nullable String reason) {
        this.code = responseStatusCode.getBusinessCode();
        this.reason = reason == null ? responseStatusCode.getReason() : reason;
        this.exception = null;
        this.timestamp = new Date();
        data = null;
    }

    public ResponseDTO(ResponseStatusCode responseStatusCode, @Nullable String reason, @Nullable Throwable throwable) {
        this.code = responseStatusCode.getBusinessCode();
        this.reason = reason == null ? responseStatusCode.getReason() : reason;
        this.exception = throwable == null ? null : throwable.toString();
        this.timestamp = new Date();
        data = null;
    }

    public ResponseDTO(ResponseStatusCode responseStatusCode, @Nullable T data) {
        this.code = responseStatusCode.getBusinessCode();
        this.reason = responseStatusCode.getReason();
        this.exception = null;
        this.timestamp = new Date();
        this.data = data;
    }

}