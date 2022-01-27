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

import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author James Chen
 */
public class JavaPluginFactory {

    private JavaPluginFactory() {
    }

    public static Plugin create(JavaPluginDescriptor descriptor, ApplicationContext context) throws ClassNotFoundException {
        PluginClassLoader loader = new PluginClassLoader(descriptor.getJarUrl());
        try {
            Class<? extends TurmsPlugin> pluginClass;
            try {
                pluginClass = (Class<? extends TurmsPlugin>) loader.loadClass(descriptor.getEntryClass());
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Failed to load the plugin class " + descriptor.getEntryClass());
            }
            TurmsPlugin plugin;
            try {
                plugin = pluginClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new IllegalStateException("Failed to create the plugin %s via no arg constructor"
                        .formatted(pluginClass.getName()), e);
            }
            return create(descriptor, plugin.getExtensions(), context);
        } catch (Exception e) {
            try {
                loader.close();
            } catch (IOException ignored) {
            }
            throw e;
        }
    }

    public static Plugin create(PluginDescriptor descriptor,
                                Set<Class<? extends TurmsExtension>> extensionClasses,
                                ApplicationContext context) {
        List<TurmsExtension> extensions = createExtensions(extensionClasses, context);
        return new Plugin(descriptor, extensions);
    }

    public static Plugin create(PluginDescriptor descriptor,
                                List<TurmsExtension> extensions,
                                ApplicationContext context) {
        for (TurmsExtension extension : extensions) {
            extension.setContext(context);
        }
        return new Plugin(descriptor, extensions);
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
                }
            }
            if (!foundExtensionPoint) {
                throw new IllegalStateException("Extension %s should implement at least one subclass of ExtensionPoint"
                        .formatted(extensionClass.getName()));
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
