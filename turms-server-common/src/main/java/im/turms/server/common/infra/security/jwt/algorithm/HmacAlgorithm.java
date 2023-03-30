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

import javax.crypto.spec.SecretKeySpec;

import im.turms.server.common.infra.security.jwt.Jwt;

/**
 * @author James Chen
 */
public class HmacAlgorithm extends SymmetricAlgorithm {

    private final SecretKeySpec secretKeySpec;

    public HmacAlgorithm(JwtAlgorithmDefinition definition, byte[] secretBytes) {
        super(definition);
        int minLength = switch (definition) {
            case HS256 -> 256 / 8;
            case HS384 -> 384 / 8;
            case HS512 -> 512 / 8;
            default -> throw new IllegalArgumentException(
                    "Unknown HMAC algorithm: "
                            + definition.getJwtAlgorithmName());
        };
        if (secretBytes.length < minLength) {
            throw new IllegalArgumentException(
                    "The length of secret must be at least "
                            + minLength
                            + " bytes long");
        }
        secretKeySpec = new SecretKeySpec(secretBytes, definition.getJavaAlgorithmName());
    }

    @Override
    public boolean verify(Jwt jwt) {
        return verifySignature(getJavaAlgorithmName(),
                secretKeySpec,
                jwt.encodedHeaderBytes(),
                jwt.encodedPayloadBytes(),
                jwt.decodedSignatureBytes());
    }

}
