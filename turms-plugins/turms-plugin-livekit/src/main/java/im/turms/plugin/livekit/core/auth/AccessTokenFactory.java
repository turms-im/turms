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

package im.turms.plugin.livekit.core.auth;

import java.util.Date;
import java.util.Map;

import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.security.jwt.JwtHeader;
import im.turms.server.common.infra.security.jwt.JwtPayload;
import im.turms.server.common.infra.security.jwt.JwtUtil;
import im.turms.server.common.infra.security.jwt.algorithm.JwtAlgorithm;

/**
 * @author James Chen
 */
public final class AccessTokenFactory {

    public static final JwtHeader HEADER = new JwtHeader("HS256", "JWT", null, null);

    private AccessTokenFactory() {
    }

    public static String createJwt(
            JwtAlgorithm algorithm,
            String apiKey,
            Map<String, Object> videoGrants,
            long expiresAtMillis) {
        byte[] bytes = JwtUtil.encode(algorithm,
                HEADER,
                new JwtPayload(
                        apiKey,
                        null,
                        null,
                        new Date(expiresAtMillis),
                        null,
                        null,
                        null,
                        Map.of("video", videoGrants)));
        return StringUtil.newLatin1String(bytes);
    }

}