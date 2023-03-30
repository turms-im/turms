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
import jakarta.annotation.Nullable;

import im.turms.server.common.infra.lang.FastStringBuilder;

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

    public static PublicKey getPublicKeyFromPem(File file, String algorithm) {
        try {
            return getPublicKeyFromPem0(file, algorithm);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to get the public key from the PEM file: "
                            + file,
                    e);
        }
    }

    private static PublicKey getPublicKeyFromPem0(File file, String algorithm) throws Exception {
        boolean isCertificate = false;
        FastStringBuilder builder = new FastStringBuilder();
        try (BufferedReader reader =
                new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
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
        byte[] publicKeyPem = builder.buildAsBytes();
        if (isCertificate) {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            try (ByteArrayInputStream stream = new ByteArrayInputStream(publicKeyPem)) {
                X509Certificate certificate =
                        (X509Certificate) factory.generateCertificates(stream);
                return certificate.getPublicKey();
            }
        } else {
            byte[] decoded = Base64.getDecoder()
                    .decode(publicKeyPem);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            return keyFactory.generatePublic(spec);
        }
    }

    public static PublicKey getPublicKeyFromP12File(
            File file,
            String password,
            @Nullable String keyAlias) {
        try {
            return getPublicKeyFromP12File0(file, password, keyAlias);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to get the public key from the P12 file: "
                            + file,
                    e);
        }
    }

    private static PublicKey getPublicKeyFromP12File0(
            File file,
            String password,
            @Nullable String keyAlias) throws Exception {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        try (FileInputStream stream = new FileInputStream(file)) {
            keystore.load(stream, password.toCharArray());
        }
        if (keyAlias == null) {
            Enumeration<String> aliases = keystore.aliases();
            if (aliases.hasMoreElements()) {
                keyAlias = aliases.nextElement();
            }
        }
        X509Certificate certificate = (X509Certificate) keystore.getCertificate(keyAlias);
        return certificate.getPublicKey();
    }

    public static byte[] getSecretKeyFromP12File(
            File file,
            String keyStorePassword,
            @Nullable String keyAlias) {
        try {
            return getSecretKeyFromP12File0(file, keyStorePassword, keyAlias);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to get the secret key from the P12 file: "
                            + file,
                    e);
        }
    }

    private static byte[] getSecretKeyFromP12File0(
            File file,
            String keyStorePassword,
            @Nullable String keyAlias) throws Exception {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        char[] password = keyStorePassword.toCharArray();
        try (FileInputStream stream = new FileInputStream(file)) {
            keystore.load(stream, password);
        }
        if (keyAlias == null) {
            Enumeration<String> aliases = keystore.aliases();
            if (aliases.hasMoreElements()) {
                keyAlias = aliases.nextElement();
            }
        }
        return keystore.getKey(keyAlias, password)
                .getEncoded();
    }

}