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

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import im.turms.server.common.infra.security.MacPool;

/**
 * @author James Chen
 */
public abstract non-sealed class SymmetricAlgorithm extends JwtAlgorithm {

    protected SymmetricAlgorithm(JwtAlgorithmDefinition definition) {
        super(definition);
        MacPool.ensureAvailability(definition.getJavaAlgorithmName());
    }

    private byte[] createSignature(
            String algorithm,
            SecretKeySpec secretKeySpec,
            byte[] headerBytes,
            byte[] payloadBytes) {
        Mac mac = MacPool.get(algorithm);
        try {
            mac.init(secretKeySpec);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(
                    "Invalid secret key spec: "
                            + secretKeySpec,
                    e);
        }
        mac.update(headerBytes);
        mac.update(JWT_PART_SEPARATOR);
        return mac.doFinal(payloadBytes);
    }

    public boolean verifySignature(
            String algorithm,
            SecretKeySpec secretKeySpec,
            byte[] headerBytes,
            byte[] payloadBytes,
            byte[] signatureBytes) {
        return MessageDigest.isEqual(signatureBytes,
                createSignature(algorithm, secretKeySpec, headerBytes, payloadBytes));
    }

}
