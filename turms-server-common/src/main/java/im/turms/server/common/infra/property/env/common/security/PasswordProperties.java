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

package im.turms.server.common.infra.property.env.common.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.constant.PasswordEncodingAlgorithm;
import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.ImmutableOnceApplied;
import im.turms.server.common.infra.security.SecurityValueConst;
import im.turms.server.common.infra.security.SensitiveProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class PasswordProperties {

    @Description("The initial password of the root user")
    @SensitiveProperty
    private String initialRootPassword = "turms";

    @ImmutableOnceApplied
    @Description("The password encoding algorithm for users")
    private PasswordEncodingAlgorithm userPasswordEncodingAlgorithm =
            PasswordEncodingAlgorithm.SALTED_SHA256;

    @ImmutableOnceApplied
    @Description("The password encoding algorithm for admins")
    private PasswordEncodingAlgorithm adminPasswordEncodingAlgorithm =
            PasswordEncodingAlgorithm.BCRYPT;

    @Override
    public String toString() {
        return "PasswordProperties{"
                + "initialRootPassword='"
                + SecurityValueConst.SENSITIVE_VALUE
                + '\''
                + ", userPasswordEncodingAlgorithm="
                + userPasswordEncodingAlgorithm
                + ", adminPasswordEncodingAlgorithm="
                + adminPasswordEncodingAlgorithm
                + '}';
    }
}