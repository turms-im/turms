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

package unit.im.turms.server.common.infra.security;

import java.io.File;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.security.CertificateUtil;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class CertificateUtilTests {

    @Test
    void getRsaPublicKeyFromPem() {
        String path = CertificateUtilTests.class.getClassLoader()
                .getResource("rsa.pem")
                .getPath();
        RSAPublicKey publicKey = CertificateUtil.getRsaPublicKeyFromPem(new File(path));
        assertThat(publicKey).isNotNull();
    }

    @Test
    void getEcPublicKeyFromPem() {
        String path = CertificateUtilTests.class.getClassLoader()
                .getResource("ec-secp256k1.pem")
                .getPath();
        ECPublicKey publicKey = CertificateUtil.getEcPublicKeyFromPem(new File(path));
        assertThat(publicKey).isNotNull();
    }

    @Test
    void getPublicKeyFromP12() {
        // keytool -genkeypair -alias turms -keyalg EC -keysize 256 -sigalg SHA256withECDSA
        // -validity 3650 -storetype pkcs12 -keystore ectest.p12 -storepass 123456
        String path = CertificateUtilTests.class.getClassLoader()
                .getResource("ectest.p12")
                .getPath();
        ECPublicKey publicKey = (ECPublicKey) CertificateUtil
                .getPublicKeyFromP12File(new File(path), "123456", "turms");
        assertThat(publicKey).isNotNull();
    }
}