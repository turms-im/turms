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

package im.turms.server.common.infra.security;

import im.turms.server.common.infra.lang.FastStringBuilder;
import im.turms.server.common.infra.lang.StringUtil;
import lombok.SneakyThrows;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Enumeration;

/**
 * @author James Chen
 */
public final class CertificateUtil {

    public static final String ALGORITHM_EC = "EC";
    public static final String ALGORITHM_RSA = "RSA";

    private CertificateUtil() {
    }

    public static ECPublicKey getEcPublicKeyFromPem(File file) {
        return (ECPublicKey) getPublicKeyFromPem(file, ALGORITHM_EC);
    }

    public static RSAPublicKey getRsaPublicKeyFromPem(File file) {
        return (RSAPublicKey) getPublicKeyFromPem(file, ALGORITHM_RSA);
    }

    @SneakyThrows
    public static PublicKey getPublicKeyFromPem(File file, String algorithm) {
        boolean isCertificate = false;
        FastStringBuilder builder = new FastStringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("-----")) {
                    if (line.startsWith("BEGIN CERTIFICATE", 5)) {
                        isCertificate = true;
                    }
                } else {
                    builder.append(line);
                }
            }
        }
        String publicKeyPem = builder.buildLatin1();
        if (isCertificate) {
            CertificateFactory fact = CertificateFactory.getInstance("X.509");
            try (ByteArrayInputStream stream = new ByteArrayInputStream(StringUtil.getBytes(publicKeyPem))) {
                X509Certificate cer = (X509Certificate) fact.generateCertificates(stream);
                return cer.getPublicKey();
            }
        } else {
            byte[] decoded = Base64.getDecoder().decode(publicKeyPem);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            return keyFactory.generatePublic(spec);
        }
    }

    @SneakyThrows
    public static PublicKey getPublicKeyFromP12(File file, String password, @Nullable String keyAlias) {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        try (FileInputStream stream = new FileInputStream(file)) {
            keystore.load(stream, password.toCharArray());
        }
        if (keyAlias == null) {
            Enumeration<String> aliasEnum = keystore.aliases();
            if (aliasEnum.hasMoreElements()) {
                keyAlias = aliasEnum.nextElement();
            }
        }
        X509Certificate cer = (X509Certificate) keystore.getCertificate(keyAlias);
        return cer.getPublicKey();
    }

}