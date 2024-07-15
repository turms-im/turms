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

package im.turms.server.common.infra.property.env.service.business.common.setting;

import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.lang.StringPattern;
import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;
import im.turms.server.common.infra.validation.MatchesStringPattern;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class CustomSettingProperties {

    @Description("The source name of the setting")
    @GlobalProperty
    @MutableProperty
    @Size(min = 1)
    @MatchesStringPattern(StringPattern.ALPHANUMERIC)
    private String sourceName = "";

    @Description("The stored name of the setting. If empty, the source name will be used")
    @GlobalProperty
    @MutableProperty
    @MatchesStringPattern(StringPattern.ALPHANUMERIC)
    private String storedName = "";

    @Description("Whether the setting is immutable")
    @GlobalProperty
    @MutableProperty
    private boolean immutable;

    @Description("Whether the setting is deletable")
    @GlobalProperty
    @MutableProperty
    private boolean deletable = true;

    @NestedConfigurationProperty
    private CustomSettingValueOneOfProperties value = new CustomSettingValueOneOfProperties();

}