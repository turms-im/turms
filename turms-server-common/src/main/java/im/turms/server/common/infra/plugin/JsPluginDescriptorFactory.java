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

package im.turms.server.common.infra.plugin;

import java.nio.file.Path;
import java.util.Map;
import jakarta.annotation.Nullable;

import org.graalvm.polyglot.Value;

import im.turms.server.common.infra.plugin.script.CorruptedScriptException;

/**
 * @author James Chen
 */
public class JsPluginDescriptorFactory extends PluginDescriptorFactory {

    private static final String GET_DESCRIPTOR = "getDescriptor";

    private JsPluginDescriptorFactory() {
    }

    public static JsPluginDescriptor parsePluginDescriptor(Value plugin, @Nullable Path path) {
        Map<String, String> properties = executeGetPluginDescriptor(plugin);
        return createJsPluginDescriptor(properties, path);
    }

    public static Map<String, String> executeGetPluginDescriptor(Value plugin) {
        Value getDescriptor = plugin.getMember(GET_DESCRIPTOR);
        if (getDescriptor == null) {
            String message = "The plugin must have a function named: \""
                    + GET_DESCRIPTOR
                    + "\"";
            throw new CorruptedScriptException(message);
        }
        if (!getDescriptor.canExecute()) {
            String message = "The member \""
                    + GET_DESCRIPTOR
                    + "\" must be a function, but got: "
                    + getDescriptor;
            throw new CorruptedScriptException(message);
        }
        Value descriptor;
        try {
            descriptor = getDescriptor.execute();
        } catch (Exception e) {
            String message = "Failed to run the function: \""
                    + GET_DESCRIPTOR
                    + "\"";
            throw new CorruptedScriptException(message, e);
        }
        Map<String, String> map;
        try {
            map = descriptor.as(Map.class);
        } catch (Exception e) {
            String message = "The function \""
                    + GET_DESCRIPTOR
                    + "\" must return a plugin descriptor object, but got: "
                    + descriptor;
            throw new CorruptedScriptException(message, e);
        }
        if (map == null) {
            String message = "The function \""
                    + GET_DESCRIPTOR
                    + "\" must return a plugin descriptor object, but got: "
                    + descriptor;
            throw new CorruptedScriptException(message);
        }
        return map;
    }

    private static JsPluginDescriptor createJsPluginDescriptor(
            Map<String, String> properties,
            @Nullable Path path) {
        PluginDescriptor descriptor = createPluginDescriptor(properties);
        return new JsPluginDescriptor(
                descriptor.getId(),
                descriptor.getVersion(),
                descriptor.getProvider(),
                descriptor.getLicense(),
                descriptor.getDescription(),
                path);
    }

}
