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

package im.turms.server.common.infra.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import im.turms.server.common.infra.jackson.UnixTimestampDeserializer;

/**
 * @author James Chen
 */
public record JwtPayload(
        @JsonProperty("iss") String issuer,
        @JsonProperty("sub") String subject,
        @JsonProperty("aud") List<String> audiences,
        @JsonProperty("exp") @JsonDeserialize(
                using = UnixTimestampDeserializer.class) Date expiresAt,
        @JsonProperty("nbf") @JsonDeserialize(
                using = UnixTimestampDeserializer.class) Date notBefore,
        @JsonProperty("iat") @JsonDeserialize(
                using = UnixTimestampDeserializer.class) Date issuedAt,
        @JsonProperty("jti") String jwtId,
        Map<String, Object> customClaims
) {

    @JsonCreator
    public JwtPayload(
            String issuer,
            String subject,
            List<String> audiences,
            Date expiresAt,
            Date notBefore,
            Date issuedAt,
            String jwtId) {
        this(issuer, subject, audiences, expiresAt, notBefore, issuedAt, jwtId, new HashMap<>(8));
    }

    @JsonAnyGetter
    public Map<String, Object> customClaims() {
        return customClaims;
    }

    @JsonAnySetter
    public void addCustomClaim(String name, Object value) {
        customClaims.put(name, value);
    }
}
