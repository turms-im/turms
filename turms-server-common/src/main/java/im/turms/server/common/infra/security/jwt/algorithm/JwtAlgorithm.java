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

import lombok.Data;

import im.turms.server.common.infra.lang.AsciiCode;
import im.turms.server.common.infra.security.jwt.Jwt;

/**
 * @author James Chen
 */
@Data
public abstract sealed class JwtAlgorithm permits AsymmetricAlgorithm, SymmetricAlgorithm {

    static final byte JWT_PART_SEPARATOR = AsciiCode.PERIOD;

    private final String jwtAlgorithmName;
    private final String javaAlgorithmName;

    protected JwtAlgorithm(JwtAlgorithmDefinition definition) {
        jwtAlgorithmName = definition.getJwtAlgorithmName();
        javaAlgorithmName = definition.getJavaAlgorithmName();
    }

    public abstract byte[] sign(byte[] encodedHeader, byte[] encodedPayload);

    public abstract boolean verify(Jwt jwt);

}