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

import im.turms.server.common.infra.lang.ByteUtil;
import im.turms.server.common.infra.security.jwt.Jwt;
import lombok.SneakyThrows;

import java.math.BigInteger;
import java.security.SignatureException;
import java.security.interfaces.ECPublicKey;

/**
 * @author James Chen
 */
public class EcdsaAlgorithm extends JwtAlgorithm {

    private final int ecNumberSize;
    private final ECPublicKey publicKey;

    public EcdsaAlgorithm(String id, String algorithm, int ecNumberSize, ECPublicKey publicKey) {
        super(id, algorithm);
        this.ecNumberSize = ecNumberSize;
        this.publicKey = publicKey;
    }

    @SneakyThrows
    @Override
    public boolean verify(Jwt jwt) {
        byte[] signatureBytes = jwt.decodedSignatureBytes();
        validateSignatureStructure(signatureBytes, publicKey);
        return JwtSignatureUtil.verifySignature(getAlgorithm(),
                publicKey,
                jwt.encodedHeaderBytes(),
                jwt.encodedPayloadBytes(),
                jose2Der(signatureBytes));
    }

    /**
     * Added check for extra protection against CVE-2022-21449.
     * This method ensures the signature's structure is as expected.
     *
     * @param joseSignature is the signature from the JWT
     * @param publicKey     public key used to verify the JWT
     * @throws SignatureException if the signature's structure is not as per expectation
     */
    private void validateSignatureStructure(byte[] joseSignature, ECPublicKey publicKey) throws SignatureException {
        // check signature length, moved this check from JOSEToDER method
        if (joseSignature.length != ecNumberSize * 2) {
            throw new SignatureException("Invalid JOSE signature format");
        }

        if (ByteUtil.isAllZeros(joseSignature)) {
            throw new SignatureException("Invalid signature format");
        }

        // get R
        byte[] rBytes = new byte[ecNumberSize];
        System.arraycopy(joseSignature, 0, rBytes, 0, ecNumberSize);
        if (ByteUtil.isAllZeros(rBytes)) {
            throw new SignatureException("Invalid signature format");
        }

        // get S
        byte[] sBytes = new byte[ecNumberSize];
        System.arraycopy(joseSignature, ecNumberSize, sBytes, 0, ecNumberSize);
        if (ByteUtil.isAllZeros(sBytes)) {
            throw new SignatureException("Invalid signature format");
        }

        //moved this check from JOSEToDER method
        int rPadding = countPadding(joseSignature, 0, ecNumberSize);
        int sPadding = countPadding(joseSignature, ecNumberSize, joseSignature.length);
        int rLength = ecNumberSize - rPadding;
        int sLength = ecNumberSize - sPadding;

        int length = 2 + rLength + 2 + sLength;
        if (length > 255) {
            throw new SignatureException("Invalid JOSE signature format");
        }

        BigInteger order = publicKey.getParams().getOrder();
        BigInteger r = new BigInteger(1, rBytes);
        BigInteger s = new BigInteger(1, sBytes);

        // R and S must be less than N
        if (order.compareTo(r) < 1) {
            throw new SignatureException("Invalid signature format");
        }

        if (order.compareTo(s) < 1) {
            throw new SignatureException("Invalid signature format");
        }
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
            //Sign
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
            //Sign
            derSignature[offset++] = (byte) 0x00;
            System.arraycopy(joseSignature, ecNumberSize, derSignature, offset, ecNumberSize);
        } else {
            System.arraycopy(joseSignature, ecNumberSize + sPadding, derSignature, offset,
                    Math.min(ecNumberSize, sLength));
        }

        return derSignature;
    }

    private int countPadding(byte[] bytes, int fromIndex, int toIndex) {
        int padding = 0;
        while (fromIndex + padding < toIndex && bytes[fromIndex + padding] == 0) {
            padding++;
        }
        return (bytes[fromIndex + padding] & 0xff) > 0x7f ? padding - 1 : padding;
    }

}
