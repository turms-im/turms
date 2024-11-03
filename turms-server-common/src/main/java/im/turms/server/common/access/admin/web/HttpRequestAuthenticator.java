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

import java.util.Base64;
import jakarta.annotation.Nullable;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.domain.admin.service.BaseAdminService;
import im.turms.server.common.infra.lang.AsciiCode;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 */
public class HttpRequestAuthenticator {

    private static final String BASIC_AUTH_PREFIX = "Basic ";
    private static final int BASIC_AUTH_PREFIX_LENGTH = BASIC_AUTH_PREFIX.length();
    public static final Mono<Long> UNAUTHENTICATED = Mono
            .error(new HttpResponseException(HttpHandlerResult.unauthorized("Unauthenticated")));

    private final BaseAdminService adminService;

    public HttpRequestAuthenticator(BaseAdminService adminService) {
        this.adminService = adminService;
    }

    public Mono<Long> authenticate(
            MethodParameterInfo[] params,
            Object[] paramValues,
            HttpHeaders headers,
            @Nullable RequiredPermission permission) {
        Pair<String, String> credentials = parseCredentials(headers);
        if (credentials == null) {
            if (permission == null) {
                return Mono.empty();
            }
            return Mono.error(new HttpResponseException(
                    HttpHandlerResult
                            .unauthorized("Could not find valid credentials from the header: \""
                                    + HttpHeaderNames.AUTHORIZATION
                                    + "\"")));
        }
        return adminService.authenticate(credentials.first(), credentials.second())
                .flatMap(id -> adminService
                        .isAdminAuthorized(params, paramValues, id, permission.value())
                        .flatMap(authorized -> {
                            if (authorized) {
                                return Mono.just(id);
                            }
                            return Mono.error(new HttpResponseException(
                                    HttpHandlerResult
                                            .unauthorized("Unauthorized to access the resource: "
                                                    + permission.value())));
                        }))
                .switchIfEmpty(UNAUTHENTICATED);
    }

    @Nullable
    private Pair<String, String> parseCredentials(HttpHeaders headers) {
        String authorization = headers.get(HttpHeaderNames.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith(BASIC_AUTH_PREFIX)) {
            return null;
        }
        String encodedCredentials = authorization.substring(BASIC_AUTH_PREFIX_LENGTH);
        byte[] decode;
        try {
            decode = Base64.getDecoder()
                    .decode(StringUtil.getBytes(encodedCredentials));
        } catch (Exception e) {
            return null;
        }
        return StringUtil.splitLatin1(StringUtil.newLatin1String(decode), AsciiCode.COLON);
    }

}