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

package im.turms.server.common.infra.property.env.gateway.identityaccessmanagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.constant.IdentityAccessManagementType;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.http.HttpIdentityAccessManagementProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtIdentityAccessManagementProperties;
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
public class IdentityAccessManagementProperties {

    @Description("Whether to authenticate and authorize users when logging in. "
            + "Note that user ID is always required even if enabled is false. "
            + "If false at startup, turms-gateway will not connect to the MongoDB server for user records")
    @GlobalProperty
    @MutableProperty
    private boolean enabled = true;

    @Description("Note that if the type is not PASSWORD, "
            + "turms-gateway will not connect to the MongoDB server for user records")
    private IdentityAccessManagementType type = IdentityAccessManagementType.PASSWORD;

    @NestedConfigurationProperty
    private JwtIdentityAccessManagementProperties jwt = new JwtIdentityAccessManagementProperties();

    @NestedConfigurationProperty
    private HttpIdentityAccessManagementProperties http =
            new HttpIdentityAccessManagementProperties();

}