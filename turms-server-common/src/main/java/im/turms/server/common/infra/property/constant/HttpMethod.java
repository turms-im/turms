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

package im.turms.server.common.infra.property.constant;

import lombok.Getter;

/**
 * @author James Chen
 */
public enum HttpMethod {
    DELETE(io.netty.handler.codec.http.HttpMethod.DELETE),
    GET(io.netty.handler.codec.http.HttpMethod.GET),
    POST(io.netty.handler.codec.http.HttpMethod.POST),
    PUT(io.netty.handler.codec.http.HttpMethod.PUT);

    @Getter
    private final io.netty.handler.codec.http.HttpMethod method;

    HttpMethod(io.netty.handler.codec.http.HttpMethod method) {
        this.method = method;
    }
}