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

package im.turms.turms.workflow.access.http.dto.response;

import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.mongo.exception.DuplicateKeyException;
import org.springframework.core.io.buffer.DataBufferLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.Date;

/**
 * @author James Chen
 */
public final class ErrorAttributesFactory {

    private ErrorAttributesFactory() {
    }

    public static ErrorAttributes parse(Throwable throwable) {
        SimpleErrorAttributes attributes;
        if (throwable instanceof ResponseStatusException e) {
            attributes = SimpleErrorAttributes.fromResponseStatusException(e);
        } else if (throwable instanceof TurmsBusinessException e) {
            attributes = SimpleErrorAttributes.fromTurmsBusinessException(e);
        } else {
            attributes = SimpleErrorAttributes.fromTrivialException(throwable);
        }
        return new ErrorAttributes(attributes.httpStatus,
                attributes.statusCode.getBusinessCode(),
                attributes.reason,
                new Date(),
                throwable.getClass().getName());
    }

    private static record SimpleErrorAttributes(
            int httpStatus,
            TurmsStatusCode statusCode,
            String reason
    ) {

        private static SimpleErrorAttributes fromTurmsBusinessException(TurmsBusinessException exception) {
            TurmsStatusCode statusCode = exception.getCode();
            HttpStatus httpStatus = HttpStatus.valueOf(statusCode.getHttpStatusCode());
            String reason = exception.getReason();
            if (reason == null) {
                reason = statusCode.getReason();
            }
            return new SimpleErrorAttributes(httpStatus.value(), statusCode, reason);
        }

        private static SimpleErrorAttributes fromResponseStatusException(ResponseStatusException exception) {
            HttpStatus httpStatus = exception.getStatus();
            String reason = exception.getReason();
            TurmsStatusCode statusCode = switch (httpStatus.series()) {
                case INFORMATIONAL, SUCCESSFUL, REDIRECTION -> TurmsStatusCode.OK;
                case CLIENT_ERROR -> TurmsStatusCode.INVALID_REQUEST;
                case SERVER_ERROR -> TurmsStatusCode.SERVER_INTERNAL_ERROR;
                default -> throw new IllegalStateException("Unexpected value: " + httpStatus.series());
            };
            return new SimpleErrorAttributes(httpStatus.value(), statusCode, reason);
        }

        private static SimpleErrorAttributes fromTrivialException(Throwable throwable) {
            TurmsStatusCode statusCode;
            if (throwable instanceof ConstraintViolationException) {
                statusCode = TurmsStatusCode.ILLEGAL_ARGUMENT;
            } else if (throwable instanceof DuplicateKeyException) {
                statusCode = TurmsStatusCode.RECORD_CONTAINS_DUPLICATE_KEY;
            } else if (throwable instanceof DataBufferLimitException) {
                statusCode = TurmsStatusCode.FILE_TOO_LARGE;
            } else {
                statusCode = TurmsStatusCode.SERVER_INTERNAL_ERROR;
            }
            String reason = throwable.getMessage();
            return new SimpleErrorAttributes(statusCode.getHttpStatusCode(), statusCode, reason);
        }
    }

}
