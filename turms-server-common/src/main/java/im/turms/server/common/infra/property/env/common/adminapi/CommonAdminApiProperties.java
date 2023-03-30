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

package im.turms.server.common.infra.property.env.common.adminapi;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.env.common.AddressProperties;
import im.turms.server.common.infra.property.metadata.Description;

/**
 * @author James Chen
 */
@Data
public abstract class CommonAdminApiProperties {

    @Description("Whether to enable the APIs for administrators")
    private boolean enabled = true;

    @Description("Whether to use authentication. If false, "
            + "all HTTP requesters will personate the root user and all HTTP requests will be passed. "
            + "You may set it to false when you want to manage authentication via security groups, NACL, etc")
    private boolean useAuthentication = true;

    @NestedConfigurationProperty
    protected AdminHttpProperties http = new AdminHttpProperties();

    @NestedConfigurationProperty
    private AddressProperties address = new AddressProperties();

    @NestedConfigurationProperty
    private AdminApiRateLimitingProperties rateLimiting = new AdminApiRateLimitingProperties();

    @NestedConfigurationProperty
    private LogProperties log = new LogProperties();

}
