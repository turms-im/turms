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

package im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.ldap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.env.common.SslProperties;
import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.security.SensitiveProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class LdapIdentityAccessManagementAdminProperties {

    @Description("The host of LDAP server for admin")
    private String host = "localhost";

    @Description("The port of LDAP server for admin")
    private int port = 389;

    @Description("The administrator's username for binding")
    private String username = "";

    @Description("The administrator's password for binding")
    @SensitiveProperty
    private String password = "";

    @NestedConfigurationProperty
    private transient SslProperties ssl = new SslProperties();

}