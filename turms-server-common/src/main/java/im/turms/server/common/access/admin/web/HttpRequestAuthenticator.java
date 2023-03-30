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

    private final BaseAdminService adminService;

    public HttpRequestAuthenticator(BaseAdminService adminService) {
        this.adminService = adminService;
    }

    public Mono<Credentials> authenticate(
            MethodParameterInfo[] params,
            Object[] paramValues,
            HttpHeaders headers,
            @Nullable RequiredPermission permission) {
        if (permission == null) {
            return Mono.just(Credentials.EMPTY);
        }
        Credentials credentials = parseCredentials(headers);
        if (credentials == null) {
            return Mono.error(new HttpResponseException(
                    HttpHandlerResult
                            .unauthorized("Could not find valid credentials from the header: \""
                                    + HttpHeaderNames.AUTHORIZATION
                                    + "\"")));
        }
        return adminService.authenticate(credentials.account(), credentials.password())
                .flatMap(authenticated -> {
                    if (authenticated) {
                        return adminService
                                .isAdminAuthorized(params,
                                        paramValues,
                                        credentials.account(),
                                        permission.value())
                                .flatMap(authorized -> {
                                    if (authorized) {
                                        return Mono.just(credentials);
                                    }
                                    return Mono.error(new HttpResponseException(
                                            HttpHandlerResult.unauthorized(
                                                    "Unauthorized to access the resource: "
                                                            + permission.value())));
                                });
                    }
                    return Mono.error(new HttpResponseException(
                            HttpHandlerResult.unauthorized("Unauthenticated")));
                });
    }

    @Nullable
    private Credentials parseCredentials(HttpHeaders headers) {
        String authorization = headers.get(HttpHeaderNames.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith(BASIC_AUTH_PREFIX)) {
            return null;
        }
        try {
            String encodedCredentials = authorization.substring(BASIC_AUTH_PREFIX.length());
            byte[] decode = Base64.getDecoder()
                    .decode(StringUtil.getBytes(encodedCredentials));
            Pair<String, String> accountAndPassword =
                    StringUtil.splitLatin1(StringUtil.newLatin1String(decode), AsciiCode.COLON);
            if (accountAndPassword == null) {
                return null;
            }
            return new Credentials(accountAndPassword.first(), accountAndPassword.second());
        } catch (Exception e) {
            return null;
        }
    }

}
