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

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.constant.HttpMethod;
import im.turms.server.common.infra.property.constant.PluginType;
import im.turms.server.common.infra.property.metadata.Description;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class NetworkPluginProperties {

    @Description("The plugin download URL")
    protected String url = "";

    @Description("The plugin type. If \"AUTO\", "
            + "the URL must end with \".jar\" for Java plugins, "
            + "or \".js\" for JavaScript plugins")
    protected PluginType type = PluginType.AUTO;

    @Description("Whether to use the local cache. If false, turms will download the plugin every time on startup "
            + "even if the plugin has been downloaded on the local machine")
    protected boolean useLocalCache;

    @NestedConfigurationProperty
    protected DownloadProperties download = new DownloadProperties();

    @AllArgsConstructor
    @Builder(toBuilder = true)
    @Data
    @NoArgsConstructor
    public static class DownloadProperties {
        protected HttpMethod httpMethod = HttpMethod.GET;

        @Min(1)
        protected int timeoutMillis = 60_000;
    }

    // TODO
//    protected String version = "";
//    protected ProxyProperties proxy = new ProxyProperties();

}