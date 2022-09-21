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

import im.turms.server.common.infra.security.jwt.Jwt;

import java.security.interfaces.RSAPublicKey;

/**
 * @author James Chen
 */
public class RsaAlgorithm extends JwtAlgorithm {

    private final RSAPublicKey publicKey;

    public RsaAlgorithm(String id, String algorithm, RSAPublicKey publicKey) {
        super(id, algorithm);
        this.publicKey = publicKey;
    }

    @Override
    public boolean verify(Jwt jwt) {
        return JwtSignatureUtil.verifySignature(getAlgorithm(),
                publicKey,
                jwt.encodedHeaderBytes(),
                jwt.encodedPayloadBytes(),
                jwt.decodedSignatureBytes());
    }

}