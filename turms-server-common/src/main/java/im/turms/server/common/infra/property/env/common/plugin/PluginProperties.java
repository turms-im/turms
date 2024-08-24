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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.metadata.Description;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class PluginProperties {

    @Description("Whether to enable plugins")
    protected boolean enabled = true;

    @Description("The relative path of plugins")
    protected String dir = "plugins";

    @Description("Whether to watch the plugin directory. If enabled, the server will load and unload plugins automatically when a plugin is added or removed from the directory")
    protected boolean watchDir;

    @NestedConfigurationProperty
    protected JavaPluginProperties java = new JavaPluginProperties();

    @NestedConfigurationProperty
    protected JsPluginProperties js = new JsPluginProperties();

    @NestedConfigurationProperty
    protected NetworkProperties network = new NetworkProperties();

}