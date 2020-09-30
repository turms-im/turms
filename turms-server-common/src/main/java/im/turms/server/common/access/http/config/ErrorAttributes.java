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

package im.turms.server.common.access.http.config;

import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import lombok.Getter;
import org.springframework.core.io.buffer.DataBufferLimitException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.Date;

/**
 * @author James Chen
 */
@Getter
public class ErrorAttributes {

    private final int status;
    private final String message;
    private final Date timestamp;
    private final String error;
    private final String exception;

    public ErrorAttributes(Throwable throwable) {
        HttpStatus errorStatus;
        if (throwable instanceof ResponseStatusException) {
            ResponseStatusException exception = (ResponseStatusException) throwable;
            errorStatus = exception.getStatus();
            message = exception.getReason();
        } else if (throwable instanceof TurmsBusinessException) {
            TurmsStatusCode code = ((TurmsBusinessException) throwable).getCode();
            errorStatus = HttpStatus.valueOf(code.getHttpStatusCode());
            message = code.getReason();
        } else {
            if (throwable instanceof ConstraintViolationException) {
                errorStatus = HttpStatus.valueOf(TurmsStatusCode.ILLEGAL_ARGUMENTS.getHttpStatusCode());
            } else if (throwable instanceof DuplicateKeyException) {
                errorStatus = HttpStatus.valueOf(TurmsStatusCode.DUPLICATE_KEY.getHttpStatusCode());
            } else if (throwable instanceof DataBufferLimitException) {
                errorStatus = HttpStatus.valueOf(TurmsStatusCode.FILE_TOO_LARGE.getHttpStatusCode());
            } else {
                errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            message = throwable.getMessage() != null
                    ? throwable.getMessage()
                    : "";
        }
        this.status = errorStatus.value();
        this.timestamp = new Date();
        this.error = errorStatus.getReasonPhrase();
        this.exception = throwable.getClass().getName();
    }

}
