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

package unit.im.turms.server.common.infra.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.security.jwt.Jwt;
import im.turms.server.common.infra.security.jwt.JwtHeader;
import im.turms.server.common.infra.security.jwt.JwtPayload;
import im.turms.server.common.infra.security.jwt.JwtUtil;
import im.turms.server.common.infra.security.jwt.algorithm.HmacAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.JwtAlgorithmDefinition;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class JwtUtilTests {

    // The length of the secret must be at least 32 bytes long.
    private static final byte[] secret = "1948年6月1日-2016年3月15日".getBytes(StandardCharsets.UTF_8);
    private static final String JWT =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiLnpo_lsYXoia8iLCJzdWIiOiJTY2VuZXJ5IiwiYXVkIjpbIkphbWVzIENoZW4iXSwiZXhwIjo0NDc5NDA4MDAwLCJzb25ncyI6WyJFYXJseSBTdW1tZXIiXX0.W59eSHHZmZtjcqJHz0MmD9PP3LZRNBuirtL_-9bpQ3Q";
    private static final HmacAlgorithm algorithm =
            new HmacAlgorithm(JwtAlgorithmDefinition.HS256, secret);
    private static final JwtPayload JWT_PAYLOAD = new JwtPayload(
            "福居良",
            "Scenery",
            List.of("James Chen"),
            new Date(4479408000000L),
            null,
            null,
            null,
            Map.of("songs", List.of("Early Summer")));

    @Test
    void test_encode() {
        byte[] bytes =
                JwtUtil.encode(algorithm, new JwtHeader("HS256", "JWT", null, null), JWT_PAYLOAD);
        String s = StringUtil.newLatin1String(bytes);
        assertThat(s).isEqualTo(JWT);
    }

    @SneakyThrows
    @Test
    void test_decode() {
        Jwt jwt = JwtUtil.decode(Map.of(JwtAlgorithmDefinition.HS256.getJwtAlgorithmName(),
                algorithm), Collections.emptyList(), JWT);
        assertThat(jwt.payload()).isEqualTo(JWT_PAYLOAD);
    }

}