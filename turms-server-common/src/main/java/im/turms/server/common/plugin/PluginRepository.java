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

import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
public class PluginRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginRepository.class);

    private final Map<String, Plugin> pluginMap = new HashMap<>(16);
    private final Map<Class<? extends ExtensionPoint>, List<ExtensionPoint>> extensionPointMap = new IdentityHashMap<>(8);
    private final Set<Class<? extends ExtensionPoint>> singletonExtensionPointClasses;

    public PluginRepository(Set<Class<? extends ExtensionPoint>> singletonExtensionPointClasses) {
        this.singletonExtensionPointClasses = singletonExtensionPointClasses;
    }

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
                extensionPointMap.compute(clazz, (extensionPointClass, extensionPoints) -> {
                    if (extensionPoints == null) {
                        extensionPoints = new ArrayList<>(2);
                    } else if (singletonExtensionPointClasses.contains(extensionPointClass)) {
                        String message = "The singleton extension point %s in the plugin %s cannot be registered because an extension point has been registered"
                                .formatted(extensionPointClass.getName(), plugin.descriptor().getId());
                        LOGGER.warn(message);
                        return extensionPoints;
                    }
                    extensionPoints.add(extensionPoint);
                    return extensionPoints;
                });
            }
        }
    }

    public <T extends ExtensionPoint> boolean hasRunningExtensions(Class<T> clazz) {
        List<? extends ExtensionPoint> extensionPoints = extensionPointMap.get(clazz);
        if (extensionPoints == null || extensionPoints.isEmpty()) {
            return false;
        }
        for (ExtensionPoint extensionPoint : extensionPoints) {
            TurmsExtension extension = (TurmsExtension) extensionPoint;
            if (extension.isRunning()) {
                return true;
            }
        }
        return false;
    }

    public <T extends ExtensionPoint> List<T> getExtensionPoints(Class<T> clazz) {
        List<T> list = (List<T>) extensionPointMap.get(clazz);
        if (list == null) {
            return Collections.emptyList();
        }
        return list;
    }

    public Collection<Plugin> getPlugins() {
        return pluginMap.values();
    }

}
