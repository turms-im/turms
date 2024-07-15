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

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

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
public class CustomSettingArrayValueProperties extends CustomSettingEnumValueProperties<String> {

    @Description("The minimum allowed number of elements")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int minElementCount = 0;

    @Description("The maximum allowed number of elements")
    @GlobalProperty
    @MutableProperty
    private int maxElementCount = 10;

    @Description("Whether the elements are unique. If true, the Turms server will deduplicate the elements")
    @GlobalProperty
    @MutableProperty
    private boolean unique;

    @Description("Whether the array element can be null")
    @GlobalProperty
    @MutableProperty
    private boolean allowNullElement;

    @NestedConfigurationProperty
    private CustomSettingValueOneOfProperties element = new CustomSettingValueOneOfProperties();

}