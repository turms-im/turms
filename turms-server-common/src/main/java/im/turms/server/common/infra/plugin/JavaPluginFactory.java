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

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.ClassUtil;

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
                pluginClass = (Class<? extends TurmsPlugin>) classLoader
                        .loadClass(descriptor.getEntryClass());
            } catch (Exception | LinkageError e) {
                throw new InvalidPluginException(
                        "Failed to load the plugin class: "
                                + descriptor.getEntryClass(),
                        e);
            }
            Constructor<? extends TurmsPlugin> constructor;
            try {
                constructor = pluginClass.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                throw new InvalidPluginException(
                        "The plugin class ("
                                + pluginClass.getName()
                                + ") must have a public no-arg constructor",
                        e);
            }
            TurmsPlugin plugin;
            try {
                plugin = constructor.newInstance();
            } catch (Exception | LinkageError e) {
                throw new InvalidPluginException(
                        "Failed to create the plugin ("
                                + pluginClass.getName()
                                + ") via the no-arg constructor",
                        e);
            }
            Set<Class<? extends TurmsExtension>> extensionClasses = plugin.getExtensions();
            if (CollectionUtil.isEmpty(extensionClasses)) {
                throw new InvalidPluginException(
                        "The plugin method \"getExtensions\" must return non-empty extension classes");
            }
            List<TurmsExtension> extensions = createExtensions(extensionClasses, context);
            JavaPlugin javaPlugin = new JavaPlugin(descriptor, extensions, classLoader);
            for (TurmsExtension extension : extensions) {
                extension.setPlugin(javaPlugin);
            }
            return javaPlugin;
        } catch (Exception e) {
            try {
                classLoader.close();
            } catch (Exception ex) {
                e.addSuppressed(new RuntimeException(
                        "Caught an error while closing the plugin class loader for the JAR file: "
                                + descriptor.getJarUrl(),
                        ex));
            }
            throw e;
        }
    }

    private static List<TurmsExtension> createExtensions(
            Set<Class<? extends TurmsExtension>> extensionClasses,
            ApplicationContext context) {
        List<TurmsExtension> extensions = new ArrayList<>(extensionClasses.size());
        for (Class<? extends TurmsExtension> extensionClass : extensionClasses) {
            Class<?> qualifiedInterface = ClassUtil.getFirstInterface(extensionClass,
                    extensionInterface -> ClassUtil.isSuperClass(extensionInterface,
                            ExtensionPoint.class));
            if (qualifiedInterface == null) {
                throw new InvalidPluginException(
                        "The extension ("
                                + extensionClass.getName()
                                + ") must implement at least one subclass of: "
                                + ExtensionPoint.class.getName());
            }
            TurmsExtension extension = createExtension(extensionClass, context);
            extensions.add(extension);
        }
        return extensions;
    }

    private static TurmsExtension createExtension(
            Class<? extends TurmsExtension> extensionClass,
            ApplicationContext context) {
        Constructor<? extends TurmsExtension> constructor;
        try {
            constructor = extensionClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new InvalidPluginException(
                    "The extension class ("
                            + extensionClass.getName()
                            + ") must have a public no-arg constructor",
                    e);
        }
        try {
            TurmsExtension extension = constructor.newInstance();
            extension.setContext(context);
            return extension;
        } catch (Exception | LinkageError e) {
            throw new PluginExecutionException(
                    "Failed to create the extension: "
                            + extensionClass.getName(),
                    e);
        }
    }

}
