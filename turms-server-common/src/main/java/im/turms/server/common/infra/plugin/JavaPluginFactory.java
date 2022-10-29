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

import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author James Chen
 */
public class JavaPluginFactory {

    private JavaPluginFactory() {
    }

    public static JavaPlugin create(JavaPluginDescriptor descriptor, ApplicationContext context) {
        // Note that the loader should NOT be closed here
        // because it usually needs to load classes and resources in the JAR file later
        PluginClassLoader classLoader = new PluginClassLoader(descriptor.getJarUrl());
        try {
            Class<? extends TurmsPlugin> pluginClass;
            try {
                pluginClass = (Class<? extends TurmsPlugin>) classLoader.loadClass(descriptor.getEntryClass());
            } catch (Exception e) {
                throw new IllegalStateException("Failed to load the plugin class: " + descriptor.getEntryClass(), e);
            }
            TurmsPlugin plugin;
            try {
                plugin = pluginClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new IllegalStateException("Failed to create the plugin " +
                        pluginClass.getName() +
                        " via the no-arg constructor", e);
            }
            List<TurmsExtension> extensions = createExtensions(plugin.getExtensions(), context);
            return new JavaPlugin(descriptor, extensions, classLoader);
        } catch (Exception e) {
            try {
                classLoader.close();
            } catch (Exception ex) {
                e.addSuppressed(new RuntimeException("Caught an error while closing the plugin class loader for the jar file: "
                        + descriptor.getJarUrl(), ex));
            }
            throw e;
        }
    }

    private static List<TurmsExtension> createExtensions(Set<Class<? extends TurmsExtension>> extensionClasses,
                                                         ApplicationContext context) {
        List<TurmsExtension> extensions = new ArrayList<>(extensionClasses.size());
        for (Class<? extends TurmsExtension> extensionClass : extensionClasses) {
            Class<?>[] interfaces = extensionClass.getInterfaces();
            boolean foundExtensionPoint = false;
            for (Class<?> extensionInterface : interfaces) {
                if (extensionInterface != ExtensionPoint.class || ExtensionPoint.class.isAssignableFrom(extensionClass)) {
                    foundExtensionPoint = true;
                    break;
                }
            }
            if (!foundExtensionPoint) {
                throw new IllegalStateException("Extension " +
                        extensionClass.getName() +
                        " should implement at least one subclass of " +
                        ExtensionPoint.class.getSimpleName());
            }
            TurmsExtension extension = createExtension(extensionClass, context);
            extensions.add(extension);
        }
        return extensions;
    }

    private static TurmsExtension createExtension(Class<? extends TurmsExtension> extensionClass,
                                                  ApplicationContext context) {
        try {
            TurmsExtension extension = extensionClass.getDeclaredConstructor().newInstance();
            extension.setContext(context);
            return extension;
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create the extension " + extensionClass.getName(), e);
        }
    }

}
