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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.constant.CustomSettingType;
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
public class CustomSettingValueOneOfProperties {

    @Description("The setting value type")
    @GlobalProperty
    @MutableProperty
    private CustomSettingType type = CustomSettingType.STRING;

    @NestedConfigurationProperty
    private CustomSettingIntValueProperties intValue = new CustomSettingIntValueProperties();

    @NestedConfigurationProperty
    private CustomSettingLongValueProperties longValue = new CustomSettingLongValueProperties();

    @NestedConfigurationProperty
    private CustomSettingDoubleValueProperties doubleValue =
            new CustomSettingDoubleValueProperties();

    @NestedConfigurationProperty
    private CustomSettingStringValueProperties stringValue =
            new CustomSettingStringValueProperties();

    @NestedConfigurationProperty
    private CustomSettingArrayValueProperties arrayValue = new CustomSettingArrayValueProperties();

}