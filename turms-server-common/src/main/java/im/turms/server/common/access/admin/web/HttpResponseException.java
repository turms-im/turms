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

package im.turms.server.common.access.admin.web;

import jakarta.annotation.Nullable;

import lombok.Getter;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.common.ResponseStatusCode;

/**
 * @author James Chen
 */
public class HttpResponseException extends RuntimeException {

    @Getter
    private final HttpHandlerResult<ResponseDTO<?>> response;

    public HttpResponseException(HttpHandlerResult<ResponseDTO<?>> response) {
        this.response = response;
    }

    public HttpResponseException(ResponseStatusCode status) {
        this.response = HttpHandlerResult
                .create(status.getHttpStatusCode(), null, new ResponseDTO<>(status));
    }

    public HttpResponseException(
            HttpHandlerResult<ResponseDTO<?>> response,
            @Nullable Throwable cause) {
        super(cause);
        this.response = response;
    }

    public HttpResponseException(ResponseStatusCode status, @Nullable Throwable cause) {
        super(cause);
        this.response = HttpHandlerResult.create(status.getHttpStatusCode(),
                null,
                new ResponseDTO<>(
                        status,
                        cause == null
                                ? null
                                : cause.getMessage(),
                        cause));
    }

    public HttpResponseException(ResponseStatusCode status, @Nullable String reason) {
        this.response = HttpHandlerResult
                .create(status.getHttpStatusCode(), null, new ResponseDTO<>(status, reason));
    }

}
