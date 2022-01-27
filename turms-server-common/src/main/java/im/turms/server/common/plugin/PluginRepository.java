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

import com.google.common.collect.ArrayListMultimap;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 */
public class PluginRepository {

    private final Map<String, Plugin> pluginMap = new HashMap<>(16);
    private final ArrayListMultimap<Class<? extends ExtensionPoint>, ExtensionPoint> extensionPointMap =
            ArrayListMultimap.create(8, 2);

    public void register(Plugin plugin) {
        for (TurmsExtension extension : plugin.extensions()) {
            Class<?>[] interfaceClasses;
            ExtensionPoint extensionPoint;
            if (extension instanceof JsTurmsExtensionAdaptor jsTurmsExtensionAdaptor) {
                interfaceClasses = jsTurmsExtensionAdaptor.getExtensionPointClasses().toArray(Class[]::new);
                extensionPoint = (ExtensionPoint) jsTurmsExtensionAdaptor.getProxy();
            } else {
                interfaceClasses = extension.getClass().getInterfaces();
                extensionPoint = (ExtensionPoint) extension;
            }
            for (Class<?> interfaceClass : interfaceClasses) {
                if (!ExtensionPoint.class.isAssignableFrom(interfaceClass)
                        || interfaceClass == ExtensionPoint.class) {
                    continue;
                }
                Class<? extends ExtensionPoint> clazz = (Class<? extends ExtensionPoint>) interfaceClass;
                extensionPointMap.put(clazz, extensionPoint);
            }
        }
    }

    public <T extends ExtensionPoint> List<T> getExtensionPoints(Class<T> clazz) {
        return (List<T>) extensionPointMap.get(clazz);
    }

    public Collection<Plugin> getPlugins() {
        return pluginMap.values();
    }

}
