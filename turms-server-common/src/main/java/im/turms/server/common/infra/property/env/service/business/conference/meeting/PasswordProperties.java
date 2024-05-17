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

package im.turms.server.common.infra.property.env.service.business.conference.meeting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.constant.PasswordPolicy;
import im.turms.server.common.infra.property.constant.PasswordType;
import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class PasswordProperties {

    @Description("The password policy")
    @GlobalProperty
    @MutableProperty
    private PasswordPolicy policy = PasswordPolicy.PROHIBITED;

    @Description("The password type")
    @GlobalProperty
    @MutableProperty
    private PasswordType type = PasswordType.NUMERIC;

    /**
     * @implNote Use 6 by default because it is easy for mobile users to input.
     */
    @Description("The minimum length of the password")
    @GlobalProperty
    @MutableProperty
    private int minLength = 6;

    @Description("The maximum length of the password")
    @GlobalProperty
    @MutableProperty
    private int maxLength = 6;

}