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

package im.turms.server.common.testing.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Builder(toBuilder = true)
@Component
@ConfigurationProperties(prefix = TestProperties.PREFIX, ignoreUnknownFields = false)
@Data
@NoArgsConstructor
public class TestProperties {

    public static final String PREFIX = "turms-test";

    @NestedConfigurationProperty
    private ElasticsearchTestEnvironmentProperties elasticsearch =
            new ElasticsearchTestEnvironmentProperties();

    @NestedConfigurationProperty
    private MinioTestEnvironmentProperties minio = new MinioTestEnvironmentProperties();

    @NestedConfigurationProperty
    private MongoTestEnvironmentProperties mongo = new MongoTestEnvironmentProperties();

    @NestedConfigurationProperty
    private RedisTestEnvironmentProperties redis = new RedisTestEnvironmentProperties();

    @NestedConfigurationProperty
    private TurmsAdminTestEnvironmentProperties turmsAdmin =
            new TurmsAdminTestEnvironmentProperties();

    @NestedConfigurationProperty
    private TurmsGatewayTestEnvironmentProperties turmsGateway =
            new TurmsGatewayTestEnvironmentProperties();

    @NestedConfigurationProperty
    private TurmsServiceTestEnvironmentProperties turmsService =
            new TurmsServiceTestEnvironmentProperties();

}