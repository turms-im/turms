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

package im.turms.server.common.property.env.common.cluster;

import im.turms.server.common.property.metadata.annotation.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class NodeProperties {

    @Description("e.g. \"turms001\", \"turms002\"")
    private String id = "";

    @NestedConfigurationProperty
    private NetworkProperties network = new NetworkProperties();

    private boolean activeByDefault = true;

    @Description("Only works when it's a service node")
    private boolean leaderEligible = true;

    @Data
    public static class NetworkProperties {
        private String host = "0.0.0.0";
        private int port = 7510;
        private boolean autoIncrement = true;
        private int portCount = 100;
    }

}