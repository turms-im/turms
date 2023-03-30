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
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class BlocklistProperties {

    @NestedConfigurationProperty
    private IpBlocklistTypeProperties ip = new IpBlocklistTypeProperties();

    @NestedConfigurationProperty
    private UserIdBlocklistTypeProperties userId = new UserIdBlocklistTypeProperties();

    @AllArgsConstructor
    @Builder(toBuilder = true)
    @Data
    @NoArgsConstructor
    public static class IpAutoBlockProperties {

        @NestedConfigurationProperty
        private AutoBlockItemProperties corruptedFrame = new AutoBlockItemProperties();

        @NestedConfigurationProperty
        private AutoBlockItemProperties corruptedRequest = new AutoBlockItemProperties();

        @NestedConfigurationProperty
        private AutoBlockItemProperties frequentRequest = new AutoBlockItemProperties();

    }

    @AllArgsConstructor
    @Builder(toBuilder = true)
    @Data
    @NoArgsConstructor
    public static class UserIdAutoBlockProperties {

        @NestedConfigurationProperty
        private AutoBlockItemProperties corruptedFrame = new AutoBlockItemProperties();

        @NestedConfigurationProperty
        private AutoBlockItemProperties corruptedRequest = new AutoBlockItemProperties();

        @NestedConfigurationProperty
        private AutoBlockItemProperties frequentRequest = new AutoBlockItemProperties();

    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    @SuperBuilder(toBuilder = true)
    public abstract static class BaseBlocklistTypeProperties {

        private boolean enabled = true;

        private int syncBlocklistIntervalMillis = 10 * 1000;

    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    @SuperBuilder(toBuilder = true)
    public static class IpBlocklistTypeProperties extends BaseBlocklistTypeProperties {

        @NestedConfigurationProperty
        private IpAutoBlockProperties autoBlock = new IpAutoBlockProperties();

    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    @SuperBuilder(toBuilder = true)
    public static class UserIdBlocklistTypeProperties extends BaseBlocklistTypeProperties {

        @NestedConfigurationProperty
        private UserIdAutoBlockProperties autoBlock = new UserIdAutoBlockProperties();

    }

}