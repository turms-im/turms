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

import im.turms.server.common.util.ClassUtil;
import lombok.SneakyThrows;
import org.graalvm.polyglot.Engine;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.ApplicationContext;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipFile;

/**
 * @author James Chen
 */
public class PluginManager {

    private final PluginRepository pluginRepository = new PluginRepository();
    private final ApplicationContext context;
    @Nullable
    private Engine engine;

    @SneakyThrows
    public PluginManager(Path pluginHome, ApplicationContext context) {
        this.context = context;
        boolean isJsScriptEnabled = ClassUtil.exists("org.graalvm.polyglot.Engine");
        PluginFinder.FindResult findResult = PluginFinder.find(pluginHome, isJsScriptEnabled);
        loadJavaPlugins(findResult.zipFiles());
        if (isJsScriptEnabled) {
            loadJsPlugins(findResult.jsScripts());
        }
    }

    public void destroy() {
        if (engine != null) {
            engine.close();
        }
    }

    public void loadJavaPlugins(List<ZipFile> zipFiles) throws ClassNotFoundException {
        List<JavaPluginDescriptor> descriptors = JavaPluginDescriptorFactory.load(zipFiles);
        for (JavaPluginDescriptor descriptor : descriptors) {
            Plugin plugin = JavaPluginFactory.create(descriptor, context);
            pluginRepository.register(plugin);
        }
    }

    @SneakyThrows
    public void loadJsPlugins(List<String> scripts) {
        if (scripts.isEmpty()) {
            return;
        }
        engine = Engine.newBuilder()
                .option("engine.WarnInterpreterOnly", "false")
                .build();
        for (String script : scripts) {
            JsPlugin jsPlugin = JsPluginFactory.create(engine, script);
            Plugin plugin = JavaPluginFactory.create(jsPlugin.descriptor(), jsPlugin.extensions(), context);
            pluginRepository.register(plugin);
        }
    }

    public <T extends ExtensionPoint> List<T> getExtensionPoints(Class<T> clazz) {
        return pluginRepository.getExtensionPoints(clazz);
    }

    public Collection<Plugin> getPlugins() {
        return pluginRepository.getPlugins();
    }

    public void stopExtension(TurmsExtension extension) {
        extension.stop();
    }

    @SneakyThrows
    public <T> T loadProperties(Class<T> propertiesClass) {
        T properties = propertiesClass.getDeclaredConstructor().newInstance();
        String s = propertiesClass.getName() + UUID.randomUUID();
        return (T) context.getBean(ConfigurationPropertiesBindingPostProcessor.class)
                .postProcessBeforeInitialization(properties, s);
    }
}