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

package im.turms.server.common.infra.property.env.common.plugin;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.security.SensitiveProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class ProxyProperties {

    @Description("Whether to enable HTTP proxy")
    protected boolean enabled;

    @Description("The HTTP proxy username")
    protected String username = "";

    @Description("The HTTP proxy password")
    @SensitiveProperty
    protected String password = "";

    @Description("The HTTP proxy host")
    protected String host = "";

    @Description("The HTTP proxy port")
    @Max(65535)
    @Min(1)
    protected int port = 8080;

    @Description("The HTTP proxy connect timeout in millis")
    @Min(1)
    protected int connectTimeoutMillis = 60_000;

}