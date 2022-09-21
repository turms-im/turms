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

package im.turms.server.common.infra.security.jwt.algorithm;

import im.turms.server.common.infra.lang.AsciiCode;
import im.turms.server.common.infra.security.MacPool;
import im.turms.server.common.infra.security.SignaturePool;
import lombok.SneakyThrows;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;

/**
 * @author James Chen
 */
public final class JwtSignatureUtil {

    private static final byte JWT_PART_SEPARATOR = AsciiCode.PERIOD;

    private JwtSignatureUtil() {
    }

    public static boolean verifySignature(
            String algorithm,
            byte[] secretBytes,
            byte[] headerBytes,
            byte[] payloadBytes,
            byte[] signatureBytes
    ) {
        return MessageDigest.isEqual(signatureBytes,
                createSignature(algorithm, secretBytes, headerBytes, payloadBytes));
    }

    @SneakyThrows
    public static boolean verifySignature(
            String algorithm,
            PublicKey publicKey,
            byte[] headerBytes,
            byte[] payloadBytes,
            byte[] signatureBytes
    ) {
        Signature signature = SignaturePool.get(algorithm);
        signature.initVerify(publicKey);
        signature.update(headerBytes);
        signature.update(JWT_PART_SEPARATOR);
        signature.update(payloadBytes);
        return signature.verify(signatureBytes);
    }

    @SneakyThrows
    private static byte[] createSignature(
            String algorithm,
            byte[] secretBytes,
            byte[] headerBytes,
            byte[] payloadBytes
    ) {
        Mac mac = MacPool.get(algorithm);
        mac.init(new SecretKeySpec(secretBytes, algorithm));
        mac.update(headerBytes);
        mac.update(JWT_PART_SEPARATOR);
        return mac.doFinal(payloadBytes);
    }

}
