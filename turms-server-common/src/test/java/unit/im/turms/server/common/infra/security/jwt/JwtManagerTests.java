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

import java.io.File;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtIdentityAccessManagementVerificationProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtKeyAlgorithmProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtP12KeyStoreProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtSecretKeyAlgorithmProperties;
import im.turms.server.common.infra.security.jwt.Jwt;
import im.turms.server.common.infra.security.jwt.JwtManager;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * keytool -genseckey -keystore keystore.p12 -storetype pkcs12 -storepass 123456 -keyalg HMacSHA256
 * -keysize 2048 -alias HS256 -keypass 123456 keytool -genseckey -keystore keystore.p12 -storetype
 * pkcs12 -storepass 123456 -keyalg HMacSHA384 -keysize 2048 -alias HS384 -keypass 123456 keytool
 * -genseckey -keystore keystore.p12 -storetype pkcs12 -storepass 123456 -keyalg HMacSHA512 -keysize
 * 2048 -alias HS512 -keypass 123456 keytool -genkey -keystore keystore.p12 -storetype pkcs12
 * -storepass 123456 -keyalg RSA -keysize 2048 -alias RS256 -keypass 123456 -sigalg SHA256withRSA
 * -dname "CN=,OU=,O=,L=,ST=,C=" -validity 3600 keytool -genkey -keystore keystore.p12 -storetype
 * pkcs12 -storepass 123456 -keyalg RSA -keysize 2048 -alias RS384 -keypass 123456 -sigalg
 * SHA384withRSA -dname "CN=,OU=,O=,L=,ST=,C=" -validity 3600 keytool -genkey -keystore keystore.p12
 * -storetype pkcs12 -storepass 123456 -keyalg RSA -keysize 2048 -alias RS512 -keypass 123456
 * -sigalg SHA512withRSA -dname "CN=,OU=,O=,L=,ST=,C=" -validity 3600 keytool -genkeypair -keystore
 * keystore.p12 -storetype pkcs12 -storepass 123456 -keyalg EC -keysize 256 -alias ES256 -keypass
 * 123456 -sigalg SHA256withECDSA -dname "CN=,OU=,O=,L=,ST=,C=" -validity 3600 keytool -genkeypair
 * -keystore keystore.p12 -storetype pkcs12 -storepass 123456 -keyalg EC -keysize 384 -alias ES384
 * -keypass 123456 -sigalg SHA384withECDSA -dname "CN=,OU=,O=,L=,ST=,C=" -validity 3600 keytool
 * -genkeypair -keystore keystore.p12 -storetype pkcs12 -storepass 123456 -keyalg EC -keysize 521
 * -alias ES512 -keypass 123456 -sigalg SHA512withECDSA -dname "CN=,OU=,O=,L=,ST=,C=" -validity 3600
 *
 * @author James Chen
 */
class JwtManagerTests {

    @SneakyThrows
    @Test
    void decode() {
        ClassLoader classLoader = JwtManagerTests.class.getClassLoader();
        JwtKeyAlgorithmProperties keyAuthenticationProperties = new JwtKeyAlgorithmProperties();
        JwtKeyAlgorithmProperties rsaKeyAuthenticationProperties =
                new JwtKeyAlgorithmProperties().toBuilder()
                        .pemFilePath(new File(
                                classLoader.getResource("rsa.pem")
                                        .getPath())
                                .getAbsolutePath())
                        .build();
        JwtManager jwtManager = new JwtManager(
                new JwtIdentityAccessManagementVerificationProperties(),
                rsaKeyAuthenticationProperties,
                rsaKeyAuthenticationProperties,
                rsaKeyAuthenticationProperties,
                keyAuthenticationProperties,
                keyAuthenticationProperties,
                keyAuthenticationProperties,
                keyAuthenticationProperties,
                keyAuthenticationProperties,
                keyAuthenticationProperties,
                new JwtSecretKeyAlgorithmProperties().toBuilder()
                        .filePath(new File(
                                classLoader.getResource("hmac256-secret.text")
                                        .getPath())
                                .getAbsolutePath())
                        .build(),
                new JwtSecretKeyAlgorithmProperties().toBuilder()
                        .p12(new JwtP12KeyStoreProperties().toBuilder()
                                .filePath(new File(
                                        classLoader.getResource("hmac256-secret.p12")
                                                .getPath())
                                        .getAbsolutePath())
                                .password("123456")
                                .keyAlias("HS256")
                                .build())
                        .build(),
                new JwtSecretKeyAlgorithmProperties());
        Jwt jwt = jwtManager.decode(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJULVNxdWFyZSIsImlhdCI6MTY0MDk5ODg2MSwiZXhwIjoxNjcyNTM0ODYxLCJhdWQiOiJKYW1lcyBDaGVuIiwic3ViIjoi5YWs5ryUIiwic29uZ3MiOlsi5q6L54WnIiwiQ29sb3JzIE9mIFRoZSBTbWlsZSJdLCJsaWtlIjp0cnVlfQ.yLsCgYkTKtgzUqQfUJ_c2AVqIw3WYIPD2ExJsvWjDQA");

        assertThat(jwt.header()
                .algorithm()).isEqualTo("HS256");
        assertThat(jwt.header()
                .type()).isEqualTo("JWT");

        assertThat(jwt.payload()
                .issuer()).isEqualTo("T-Square");
        assertThat(jwt.payload()
                .subject()).isEqualTo("公演");
        assertThat(jwt.payload()
                .audiences()).containsExactly("James Chen");
        assertThat(jwt.payload()
                .issuedAt()).isEqualTo("2022-01-01T01:01:01.000Z");
        assertThat(jwt.payload()
                .expiresAt()).isEqualTo("2023-01-01T01:01:01.000Z");
        assertThat(jwt.payload()
                .notBefore()).isNull();
        assertThat(jwt.payload()
                .jwtId()).isNull();
        assertThat(jwt.payload()
                .customClaims()).containsExactlyInAnyOrderEntriesOf(
                        Map.of("songs", List.of("残照", "Colors Of The Smile"), "like", true));
    }

}