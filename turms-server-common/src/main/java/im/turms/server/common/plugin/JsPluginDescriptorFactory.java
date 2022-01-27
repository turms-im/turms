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

package im.turms.server.common.plugin;

import im.turms.server.common.plugin.script.CorruptedScriptException;
import org.graalvm.polyglot.Value;

import java.util.Map;

/**
 * @author James Chen
 */
public class JsPluginDescriptorFactory extends PluginDescriptorFactory {

    private static final String GET_PLUGIN_DESCRIPTOR = "getPluginDescriptor";

    public static JsPluginDescriptor parsePluginDescriptor(Value bindings, String script) {
        Map<String, Object> map = executeGetPluginDescriptor(bindings, script);
        return createJsPluginDescriptor(map);
    }

    public static Map<String, Object> executeGetPluginDescriptor(Value bindings, String script) {
        if (!bindings.hasMember(GET_PLUGIN_DESCRIPTOR)) {
            String message = "The script should have a function called \"%s\". Actual: %n%s"
                    .formatted(GET_PLUGIN_DESCRIPTOR, script);
            throw new CorruptedScriptException(message);
        }
        Value getPluginDescriptor = bindings.getMember(GET_PLUGIN_DESCRIPTOR);
        if (!getPluginDescriptor.canExecute()) {
            String message = "The method \"%s\" should be a function. Actual: %n%s"
                    .formatted(GET_PLUGIN_DESCRIPTOR, script);
            throw new CorruptedScriptException(message);
        }
        Value descriptor;
        try {
            descriptor = getPluginDescriptor.execute();
        } catch (Exception e) {
            String message = "Failed to run the function \"%s\""
                    .formatted(GET_PLUGIN_DESCRIPTOR);
            throw new CorruptedScriptException(message, e);
        }
        try {
            return descriptor.as(Map.class);
        } catch (Exception e) {
            String message = "The method \"%s\" should return a plugin descriptor object. Actual: %n%s"
                    .formatted(GET_PLUGIN_DESCRIPTOR, descriptor.toString());
            throw new CorruptedScriptException(message, e);
        }
    }

    private static JsPluginDescriptor createJsPluginDescriptor(Map<String, Object> map) {
        PluginDescriptor descriptor = createPluginDescriptor(map);
        return new JsPluginDescriptor(descriptor.getId(),
                descriptor.getVersion(),
                descriptor.getProvider(),
                descriptor.getLicense(),
                descriptor.getDescription());
    }

}
