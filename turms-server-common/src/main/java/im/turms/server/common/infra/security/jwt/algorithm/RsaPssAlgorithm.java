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

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;

import im.turms.server.common.infra.security.jwt.Jwt;

/**
 * @author James Chen
 */
public class RsaPssAlgorithm extends AsymmetricAlgorithm {

    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;
    private final AlgorithmParameterSpec parameterSpec;

    public RsaPssAlgorithm(
            JwtAlgorithmDefinition definition,
            RSAPublicKey publicKey,
            RSAPrivateKey privateKey,
            AlgorithmParameterSpec parameterSpec) {
        super(definition);
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.parameterSpec = parameterSpec;
    }

    @Override
    public byte[] sign(byte[] encodedHeader, byte[] encodedPayload) {
        return createSignature(getJavaAlgorithmName(),
                parameterSpec,
                privateKey,
                encodedHeader,
                encodedPayload);
    }

    @Override
    public boolean verify(Jwt jwt) {
        return verifySignature(getJavaAlgorithmName(),
                parameterSpec,
                publicKey,
                jwt.encodedHeaderBytes(),
                jwt.encodedPayloadBytes(),
                jwt.decodedSignatureBytes());
    }
}