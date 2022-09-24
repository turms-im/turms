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

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author James Chen
 */
@Getter
@AllArgsConstructor
public enum JwtAlgorithmDefinition {

    RS256("RS256", "SHA256withRSA"),
    RS384("RS384", "SHA384withRSA"),
    RS512("RS512", "SHA512withRSA"),

    PS256("PS256", "SHA256withRSAandMGF1"),
    PS384("PS384", "SHA384withRSAandMGF1"),
    PS512("PS512", "SHA512withRSAandMGF1"),

    ES256("ES256", "SHA256withECDSA"),
    ES384("ES384", "SHA384withECDSA"),
    ES512("ES512", "SHA512withECDSA"),

    HS256("HS256", "HmacSHA256"),
    HS384("HS384", "HmacSHA384"),
    HS512("HS512", "HmacSHA512");

    private final String jwtAlgorithmName;
    private final String javaAlgorithmName;

}
