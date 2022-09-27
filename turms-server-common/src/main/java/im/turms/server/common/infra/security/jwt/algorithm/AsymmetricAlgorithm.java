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

import im.turms.server.common.infra.security.SignaturePool;
import lombok.SneakyThrows;

import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @author James Chen
 */
public abstract non-sealed class AsymmetricAlgorithm extends JwtAlgorithm {

    protected AsymmetricAlgorithm(JwtAlgorithmDefinition definition) {
        super(definition);
        SignaturePool.ensureAvailability(definition.getJavaAlgorithmName());
    }

    @SneakyThrows
    public boolean verifySignature(
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
    public boolean verifySignature(
            String algorithm,
            AlgorithmParameterSpec parameterSpec,
            PublicKey publicKey,
            byte[] headerBytes,
            byte[] payloadBytes,
            byte[] signatureBytes
    ) {
        Signature signature = SignaturePool.get(algorithm);
        signature.setParameter(parameterSpec);
        signature.initVerify(publicKey);
        signature.update(headerBytes);
        signature.update(JWT_PART_SEPARATOR);
        signature.update(payloadBytes);
        return signature.verify(signatureBytes);
    }

}
