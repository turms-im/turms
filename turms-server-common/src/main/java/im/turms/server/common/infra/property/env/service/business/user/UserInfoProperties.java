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

package im.turms.server.common.infra.property.env.service.business.user;

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;
import im.turms.server.common.infra.validation.LessThanOrEqualTo;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class UserInfoProperties {

    @Description("The minimum allowed length for a user's password. "
            + "If 0, it means the password can be an empty string \"\". "
            + "If -1, it means the password can be null")
    @GlobalProperty
    @MutableProperty
    @Min(-1)
    @LessThanOrEqualTo("maxPasswordLength")
    private int minPasswordLength = -1;

    @Description("The maximum allowed length for a user's password")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxPasswordLength = 16;

    @Description("The maximum allowed length for a user's name")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxNameLength = 20;

    @Description("The maximum allowed length for a user's intro")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxIntroLength = 100;

    @Description("The maximum allowed length for a user's profile picture")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxProfilePictureLength = 100;

    @NestedConfigurationProperty
    private UserInfoUserDefinedAttributesProperties userDefinedAttributes =
            new UserInfoUserDefinedAttributesProperties();

}