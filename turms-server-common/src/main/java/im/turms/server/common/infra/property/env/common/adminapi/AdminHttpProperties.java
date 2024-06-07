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

import jakarta.validation.constraints.Min;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.env.common.SslProperties;
import im.turms.server.common.infra.property.metadata.Description;

import static im.turms.server.common.infra.unit.ByteSizeUnit.MB;

/**
 * @author James Chen
 */
@Data
public class AdminHttpProperties {

    @Description("The bind host")
    private String host = "0.0.0.0";

    @Description("The bind port")
    private int port = -1;

    @Description("The connect timeout")
    @Min(0)
    private int connectTimeoutMillis = 30 * 1000;

    @Description("The idle timeout on the connection when it is waiting for an HTTP request to come. "
            + "Once the timeout is reached, the connection will be closed")
    @Min(0)
    private int idleTimeoutMillis = 3 * 60 * 1000;

    @Description("The read timeout on the connection when it is waiting for an HTTP request to be fully read. "
            + "Once the timeout is reached, the connection will be closed")
    @Min(0)
    private int requestReadTimeoutMillis = 3 * 60 * 1000;

    @Description("The max request body size in bytes")
    private int maxRequestBodySizeBytes = 10 * MB;

    @NestedConfigurationProperty
    private transient SslProperties ssl = new SslProperties();

}