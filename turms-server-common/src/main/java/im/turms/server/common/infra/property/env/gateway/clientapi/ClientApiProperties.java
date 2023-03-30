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

package im.turms.server.common.infra.property.env.gateway.clientapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.metadata.Description;

import static im.turms.server.common.infra.unit.ByteSizeUnit.KB;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class ClientApiProperties {

    @Description("Whether to return the reason for the server error to the client. "
            + "Note: 1. It may reveal sensitive data like the IP of internal servers if true; "
            + "2. turms-gateway never return the information of stack traces no matter it is true or false.")
    private boolean returnReasonForServerError;

    @Description("The client session will be closed and may be blocked if it tries "
            + "to send a request larger than the size. "
            + "Note: The average size of turms requests is 16~64 bytes")
    private int maxRequestSizeBytes = 16 * KB;

    @NestedConfigurationProperty
    private ClientApiLoggingProperties logging = new ClientApiLoggingProperties();

    @NestedConfigurationProperty
    private ClientApiRateLimitingProperties rateLimiting = new ClientApiRateLimitingProperties();

}