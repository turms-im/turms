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

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import jakarta.annotation.Nullable;

import im.turms.server.common.infra.security.jwt.Jwt;

/**
 * @author James Chen
 */
public class EcdsaAlgorithm extends AsymmetricAlgorithm {

    private final int ecNumberSize;
    @Nullable
    private final ECPublicKey publicKey;
    @Nullable
    private final ECPrivateKey privateKey;

    public EcdsaAlgorithm(
            JwtAlgorithmDefinition definition,
            int ecNumberSize,
            @Nullable ECPublicKey publicKey,
            @Nullable ECPrivateKey privateKey) {
        super(definition);
        this.ecNumberSize = ecNumberSize;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public byte[] sign(byte[] encodedHeader, byte[] encodedPayload) {
        byte[] signature =
                createSignature(getJavaAlgorithmName(), privateKey, encodedHeader, encodedPayload);
        return der2Jose(signature);
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

    byte[] der2Jose(byte[] derSignature) {
        // DER Structure: http://crypto.stackexchange.com/a/1797
        boolean derEncoded = derSignature[0] == 0x30 && derSignature.length != ecNumberSize * 2;
        if (!derEncoded) {
            throw new IllegalArgumentException("Invalid DER signature format");
        }

        final byte[] joseSignature = new byte[ecNumberSize * 2];

        // Skip 0x30
        int offset = 1;
        if (derSignature[1] == (byte) 0x81) {
            // Skip sign
            offset++;
        }

        // Convert to unsigned. Should match DER length - offset
        int encodedLength = derSignature[offset++] & 0xff;
        if (encodedLength != derSignature.length - offset) {
            throw new IllegalArgumentException("Invalid DER signature format");
        }

        // Skip 0x02
        offset++;

        // Obtain R number length (Includes padding) and skip it
        int rlength = derSignature[offset++];
        if (rlength > ecNumberSize + 1) {
            throw new IllegalArgumentException("Invalid DER signature format");
        }
        int rpadding = ecNumberSize - rlength;
        // Retrieve R number
        System.arraycopy(derSignature,
                offset + Math.max(-rpadding, 0),
                joseSignature,
                Math.max(rpadding, 0),
                rlength + Math.min(rpadding, 0));

        // Skip R number and 0x02
        offset += rlength + 1;

        // Obtain S number length. (Includes padding)
        int slength = derSignature[offset++];
        if (slength > ecNumberSize + 1) {
            throw new IllegalArgumentException("Invalid DER signature format");
        }
        int spadding = ecNumberSize - slength;
        // Retrieve R number
        System.arraycopy(derSignature,
                offset + Math.max(-spadding, 0),
                joseSignature,
                ecNumberSize + Math.max(spadding, 0),
                slength + Math.min(spadding, 0));

        return joseSignature;
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