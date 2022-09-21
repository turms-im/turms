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

import im.turms.server.common.infra.property.env.gateway.authentication.JwtAuthenticationVerificationProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.JwtKeyAlgorithmProperties;
import im.turms.server.common.infra.security.jwt.Jwt;
import im.turms.server.common.infra.security.jwt.JwtManager;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class JwtManagerTests {

    @Test
    void decode() {
        ClassLoader classLoader = JwtManagerTests.class.getClassLoader();
        String rsaPemFilePath = new File(classLoader.getResource("rsa.pem").getPath())
                .getAbsolutePath();
        String hmacSecretFilePath = new File(classLoader.getResource("hmac256-secret.text").getPath())
                .getAbsolutePath();
        JwtKeyAlgorithmProperties keyAuthenticationProperties = new JwtKeyAlgorithmProperties();
        JwtKeyAlgorithmProperties rsaKeyAuthenticationProperties = new JwtKeyAlgorithmProperties()
                .toBuilder()
                .pemFilePath(rsaPemFilePath)
                .build();
        JwtManager jwtManager = new JwtManager(new JwtAuthenticationVerificationProperties(),
                keyAuthenticationProperties,
                keyAuthenticationProperties,
                keyAuthenticationProperties,
                rsaKeyAuthenticationProperties,
                rsaKeyAuthenticationProperties,
                rsaKeyAuthenticationProperties,
                hmacSecretFilePath,
                "",
                "");
        Jwt jwt = jwtManager.decode(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJULVNxdWFyZSIsImlhdCI6MTY0MDk5ODg2MSwiZXhwIjoxNjcyNTM0ODYxLCJhdWQiOiJKYW1lcyBDaGVuIiwic3ViIjoi5YWs5ryUIiwic29uZ3MiOlsi5q6L54WnIiwiQ29sb3JzIE9mIFRoZSBTbWlsZSJdLCJsaWtlIjp0cnVlfQ.yLsCgYkTKtgzUqQfUJ_c2AVqIw3WYIPD2ExJsvWjDQA");

        assertThat(jwt.header().algorithm()).isEqualTo("HS256");
        assertThat(jwt.header().type()).isEqualTo("JWT");

        assertThat(jwt.payload().issuer()).isEqualTo("T-Square");
        assertThat(jwt.payload().subject()).isEqualTo("公演");
        assertThat(jwt.payload().audiences()).containsExactly("James Chen");
        assertThat(jwt.payload().issuedAt()).isEqualTo("2022-01-01T01:01:01.000Z");
        assertThat(jwt.payload().expiresAt()).isEqualTo("2023-01-01T01:01:01.000Z");
        assertThat(jwt.payload().notBefore()).isNull();
        assertThat(jwt.payload().jwtId()).isNull();
        assertThat(jwt.payload().customClaims()).containsExactlyInAnyOrderEntriesOf(Map.of(
                "songs", List.of("残照", "Colors Of The Smile"),
                "like", true
        ));
    }

}