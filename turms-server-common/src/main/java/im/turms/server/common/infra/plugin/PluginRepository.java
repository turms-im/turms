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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jctools.maps.NonBlockingIdentityHashMap;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.DuplicateResourceException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * @author James Chen
 */
public class PluginRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginRepository.class);

    private final ConcurrentHashMap<String, Plugin> idToPlugin = new ConcurrentHashMap<>(16);
    private final NonBlockingIdentityHashMap<Class<? extends ExtensionPoint>, List<ExtensionPoint>> classToExtensionPoint =
            new NonBlockingIdentityHashMap<>(16);
    private final Set<Class<? extends ExtensionPoint>> singletonExtensionPointClasses;

    public PluginRepository(Set<Class<? extends ExtensionPoint>> singletonExtensionPointClasses) {
        this.singletonExtensionPointClasses = singletonExtensionPointClasses;
    }

    public void register(Plugin plugin) {
        String id = plugin.descriptor()
                .getId();
        Plugin currentPlugin = idToPlugin.computeIfAbsent(id, pluginId -> {
            for (TurmsExtension extension : plugin.extensions()) {
                ExtensionPoint extensionPoint = extension.getExtensionPoint();
                for (Class<? extends ExtensionPoint> extensionPointClass : extension
                        .getExtensionPointClasses()) {
                    List<ExtensionPoint> extensionPoints =
                            classToExtensionPoint.computeIfAbsent(extensionPointClass,
                                    key -> new CopyOnWriteArrayList<>());
                    if (singletonExtensionPointClasses.contains(extensionPointClass)
                            && !extensionPoints.isEmpty()) {
                        throw new DuplicateResourceException(
                                "The singleton extension point ("
                                        + extensionPointClass.getName()
                                        + ") in the plugin ("
                                        + pluginId
                                        + ") cannot be registered because an extension point has been registered");
                    }
                    extensionPoints.add(extensionPoint);
                }
            }
            return plugin;
        });
        if (currentPlugin != plugin) {
            throw new DuplicateResourceException(
                    "The plugin with the ID ("
                            + id
                            + ") has been registered");
        }
        LOGGER.info(
                "A new plugin with the ID ({}) has been registered. The current number of plugins is: {}",
                id,
                idToPlugin.size());
    }

    public <T extends ExtensionPoint> boolean hasRunningExtensions(Class<T> clazz) {
        List<? extends ExtensionPoint> extensionPoints = classToExtensionPoint.get(clazz);
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
        List<T> list = (List<T>) classToExtensionPoint.get(clazz);
        if (list == null) {
            return Collections.emptyList();
        }
        return list;
    }

    public Collection<Plugin> getPlugins() {
        return idToPlugin.values();
    }

    public List<Plugin> getPlugins(Set<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<Plugin> plugins = new ArrayList<>(ids.size());
        for (String id : ids) {
            Plugin plugin = idToPlugin.get(id);
            if (plugin != null) {
                plugins.add(plugin);
            }
        }
        return plugins;
    }

    public List<Plugin> removePlugins(Set<String> ids) {
        List<Plugin> removedPlugins = new ArrayList<>(ids.size());
        for (String id : ids) {
            Plugin plugin = idToPlugin.remove(id);
            if (plugin == null) {
                continue;
            }
            for (TurmsExtension extension : plugin.extensions()) {
                ExtensionPoint extensionPoint = extension.getExtensionPoint();
                for (Class<? extends ExtensionPoint> extensionPointClass : extension
                        .getExtensionPointClasses()) {
                    List<ExtensionPoint> extensionPoints =
                            classToExtensionPoint.get(extensionPointClass);
                    if (extensionPoints != null) {
                        extensionPoints.remove(extensionPoint);
                    }
                }
            }
            removedPlugins.add(plugin);
        }
        if (!removedPlugins.isEmpty()) {
            String removedPluginIds = CollectionUtil.toLatin1String(removedPlugins,
                    plugin -> plugin.descriptor()
                            .getId());
            LOGGER.info("The plugins {} has been removed. The current number of plugins is: {}",
                    removedPluginIds,
                    idToPlugin.size());
        }
        return removedPlugins;
    }

}
