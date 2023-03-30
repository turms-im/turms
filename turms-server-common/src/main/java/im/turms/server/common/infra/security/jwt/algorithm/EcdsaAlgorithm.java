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

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;

import im.turms.server.common.infra.exception.IncompatibleJvmException;
import im.turms.server.common.infra.security.jwt.Jwt;

/**
 * @author James Chen
 */
public class EcdsaAlgorithm extends AsymmetricAlgorithm {

    private final int ecNumberSize;
    private final ECPublicKey publicKey;

    static {
        KeyPair keys;
        try {
            keys = KeyPairGenerator.getInstance("EC")
                    .generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to validate the availability of EC algorithm", e);
        }
        try {
            Signature signature = Signature.getInstance("SHA256WithECDSAInP1363Format");
            signature.initVerify(keys.getPublic());
            signature.update("CVE-2022-21449".getBytes());
            if (signature.verify(new byte[64])) {
                throw new IncompatibleJvmException(
                        "The current JVM is vulnerable to CVE-2022-21449. Please upgrade to a patched Java version");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to validate the availability of EC algorithm", e);
        }
    }

    public EcdsaAlgorithm(
            JwtAlgorithmDefinition definition,
            int ecNumberSize,
            ECPublicKey publicKey) {
        super(definition);
        this.ecNumberSize = ecNumberSize;
        this.publicKey = publicKey;
    }

    @Override
    public boolean verify(Jwt jwt) {
        byte[] signatureBytes = jwt.decodedSignatureBytes();
        return verifySignature(getJavaAlgorithmName(),
                publicKey,
                jwt.encodedHeaderBytes(),
                jwt.encodedPayloadBytes(),
                jose2Der(signatureBytes));
    }

    private byte[] jose2Der(byte[] joseSignature) {
        // Retrieve R and S number's length and padding.
        int rPadding = countPadding(joseSignature, 0, ecNumberSize);
        int sPadding = countPadding(joseSignature, ecNumberSize, joseSignature.length);
        int rLength = ecNumberSize - rPadding;
        int sLength = ecNumberSize - sPadding;

        int length = 2 + rLength + 2 + sLength;

        final byte[] derSignature;
        int offset;
        if (length > 0x7f) {
            derSignature = new byte[3 + length];
            derSignature[1] = (byte) 0x81;
            offset = 2;
        } else {
            derSignature = new byte[2 + length];
            offset = 1;
        }

        // DER Structure: http://crypto.stackexchange.com/a/1797
        // Header with signature length info
        derSignature[0] = (byte) 0x30;
        derSignature[offset++] = (byte) (length & 0xff);

        // Header with "min R" number length
        derSignature[offset++] = (byte) 0x02;
        derSignature[offset++] = (byte) rLength;

        // R number
        if (rPadding < 0) {
            // Sign
            derSignature[offset++] = (byte) 0x00;
            System.arraycopy(joseSignature, 0, derSignature, offset, ecNumberSize);
            offset += ecNumberSize;
        } else {
            int copyLength = Math.min(ecNumberSize, rLength);
            System.arraycopy(joseSignature, rPadding, derSignature, offset, copyLength);
            offset += copyLength;
        }

        // Header with "min S" number length
        derSignature[offset++] = (byte) 0x02;
        derSignature[offset++] = (byte) sLength;

        // S number
        if (sPadding < 0) {
            // Sign
            derSignature[offset++] = (byte) 0x00;
            System.arraycopy(joseSignature, ecNumberSize, derSignature, offset, ecNumberSize);
        } else {
            System.arraycopy(joseSignature,
                    ecNumberSize + sPadding,
                    derSignature,
                    offset,
                    Math.min(ecNumberSize, sLength));
        }

        return derSignature;
    }

    private int countPadding(byte[] bytes, int fromIndex, int toIndex) {
        int padding = 0;
        while (fromIndex + padding < toIndex && bytes[fromIndex + padding] == 0) {
            padding++;
        }
        return (bytes[fromIndex + padding] & 0xff) > 0x7f
                ? padding - 1
                : padding;
    }

}
