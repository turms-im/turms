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

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.netty.http.server.HttpServerRequest;

/**
 * @author James Chen
 */
public class HttpUtil {

    private HttpUtil() {
    }

    public static void allowAnyRequest(HttpHeaders headers) {
        headers.set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "*")
                .set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*")
                .set(HttpHeaderNames.ACCESS_CONTROL_MAX_AGE, "7200");
    }

    public static boolean isPreFlightRequest(HttpServerRequest request) {
        HttpHeaders headers = request.requestHeaders();
        return request.method() == HttpMethod.OPTIONS
                && headers.contains(HttpHeaderNames.ORIGIN)
                && headers.contains(HttpHeaderNames.ACCESS_CONTROL_REQUEST_METHOD);
    }

    public static boolean isServerError(HttpResponseStatus status) {
        return status.code() >= 500;
    }

}
