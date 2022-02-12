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

import im.turms.server.common.context.TurmsApplicationContext;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.PluginProperties;
import im.turms.server.common.util.ClassUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import org.graalvm.polyglot.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.annotation.PreDestroy;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipFile;

/**
 * @author James Chen
 */
@Component
public class PluginManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginManager.class);

    @Getter
    private final boolean enabled;

    private final PluginRepository pluginRepository;
    private final ApplicationContext context;
    /**
     * The object is {@link Engine} in fact,
     * but we don't declare it as {@link Engine} because it's an optional class
     * and Spring will throw when creating the bean when it cannot find the class
     */
    @Nullable
    private Object engine;

    @SneakyThrows
    public PluginManager(ApplicationContext context,
                         TurmsApplicationContext applicationContext,
                         TurmsPropertiesManager propertiesManager,
                         @Autowired(required = false) Set<Class<? extends ExtensionPoint>> singletonExtensionPoints) {
        this.context = context;
        PluginProperties pluginProperties = propertiesManager.getLocalProperties().getPlugin();
        enabled = pluginProperties.isEnabled();
        pluginRepository = new PluginRepository(singletonExtensionPoints == null
                ? Collections.emptySet()
                : singletonExtensionPoints);
        Path dir = getPluginDir(applicationContext.getHome(), pluginProperties.getDir());
        boolean isJsScriptEnabled = ClassUtil.exists("org.graalvm.polyglot.Engine");
        PluginFinder.FindResult findResult = PluginFinder.find(dir, isJsScriptEnabled);
        loadJavaPlugins(findResult.zipFiles());
        if (isJsScriptEnabled) {
            loadJsPlugins(findResult.jsScripts());
        }
        if (enabled) {
            startPlugins();
        }
    }

    private Path getPluginDir(Path home, String pluginsDir) {
        return home.resolve(pluginsDir).toAbsolutePath();
    }

    private void startPlugins() {
        for (Plugin plugin : getPlugins()) {
            for (TurmsExtension extension : plugin.extensions()) {
                try {
                    extension.start();
                } catch (Exception e) {
                    LOGGER.error("Caught an error while starting the extension " + extension.getClass().getName(), e);
                }
            }
        }
    }

    @PreDestroy
    public void destroy() {
        for (Plugin plugin : getPlugins()) {
            for (TurmsExtension extension : plugin.extensions()) {
                try {
                    extension.stop();
                } catch (Exception e) {
                    LOGGER.error("Caught an error while stopping the extension " + extension.getClass().getName(), e);
                }
            }
        }
        if (engine != null) {
            ((Engine) engine).close();
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
        Engine engine = Engine.newBuilder()
                .option("engine.WarnInterpreterOnly", "false")
                .build();
        this.engine = engine;
        for (String script : scripts) {
            JsPlugin jsPlugin = JsPluginFactory.create(engine, script);
            Plugin plugin = JavaPluginFactory.create(jsPlugin.descriptor(), jsPlugin.extensions(), context);
            pluginRepository.register(plugin);
        }
    }

    @SneakyThrows
    public <T> T loadProperties(Class<T> propertiesClass) {
        T properties = propertiesClass.getDeclaredConstructor().newInstance();
        String s = propertiesClass.getName() + UUID.randomUUID();
        return (T) context.getBean(ConfigurationPropertiesBindingPostProcessor.class)
                .postProcessBeforeInitialization(properties, s);
    }

    public <T extends ExtensionPoint> List<T> getExtensionPoints(Class<T> clazz) {
        return pluginRepository.getExtensionPoints(clazz);
    }

    public Collection<Plugin> getPlugins() {
        return pluginRepository.getPlugins();
    }

    public <T extends ExtensionPoint> boolean hasRunningExtensions(Class<T> extensionPointClass) {
        return pluginRepository.hasRunningExtensions(extensionPointClass);
    }

    public <T extends ExtensionPoint, R> Mono<R> invokeFirstExtensionPoint(Class<T> extensionPointClass,
                                                                           String methodName,
                                                                           @Nullable Mono<R> defaultValue,
                                                                           FirstExtensionPointInvoker<T, R> invoker) {
        List<T> extensionPoints = pluginRepository.getExtensionPoints(extensionPointClass);
        int size = extensionPoints.size();
        if (size == 0) {
            return defaultValue == null ? Mono.empty() : defaultValue;
        }
        T extensionPoint = extensionPoints.get(0);
        TurmsExtension extension = (TurmsExtension) extensionPoint;
        if (!extension.isRunning()) {
            return defaultValue == null ? Mono.empty() : defaultValue;
        }
        try {
            return invoker.invoke(extensionPoint)
                    .onErrorMap(t -> translateException(t, methodName, extension));
        } catch (Exception e) {
            return Mono.error(translateException(e, methodName, extension));
        }
    }

    public <T extends ExtensionPoint, R> Mono<R> invokeExtensionPointsSequentially(Class<T> extensionPointClass,
                                                                                   String methodName,
                                                                                   SequentialExtensionPointInvoker<T, R> invoker) {
        return invokeExtensionPointsSequentially(extensionPointClass, methodName, null, invoker);
    }

    public <T extends ExtensionPoint, R> Mono<R> invokeExtensionPointsSequentially(Class<T> extensionPointClass,
                                                                                   String methodName,
                                                                                   @Nullable R initialValue,
                                                                                   SequentialExtensionPointInvoker<T, R> invoker) {
        List<T> extensionPoints = pluginRepository.getExtensionPoints(extensionPointClass);
        int size = extensionPoints.size();
        if (size == 0) {
            return Mono.empty();
        }
        Mono<R> result = initialValue == null ? Mono.empty() : Mono.just(initialValue);
        for (ExtensionPoint extensionPoint : extensionPoints) {
            TurmsExtension extension = (TurmsExtension) extensionPoint;
            if (!extension.isRunning()) {
                continue;
            }
            try {
                result = invoker.invoke((T) extensionPoint, result)
                        .onErrorMap(t -> translateException(t, methodName, extension));
            } catch (Exception e) {
                Mono<R> error = Mono.error(translateException(e, methodName, extension));
                return result.then(error);
            }
        }
        return result;
    }

    public <T extends ExtensionPoint> Mono<Void> invokeExtensionPoints(Class<T> extensionPointClass,
                                                                       String methodName,
                                                                       ExtensionPointInvoker<T> invoker) {
        List<T> extensionPoints = pluginRepository.getExtensionPoints(extensionPointClass);
        int size = extensionPoints.size();
        if (size == 0) {
            return Mono.empty();
        }
        List<Mono<?>> list = new ArrayList<>(size);
        for (ExtensionPoint extensionPoint : extensionPoints) {
            TurmsExtension extension = (TurmsExtension) extensionPoint;
            if (!extension.isRunning()) {
                continue;
            }
            try {
                Mono<Void> mono = invoker.invoke((T) extensionPoint)
                        .onErrorMap(t -> translateException(t, methodName, extension));
                list.add(mono);
            } catch (Exception e) {
                Mono<Void> error = Mono.error(translateException(e, methodName, extension));
                list.add(error);
            }
        }
        if (list.isEmpty()) {
            return Mono.empty();
        }
        return Mono.whenDelayError(list);
    }

    private Exception translateException(Throwable t, String methodName, TurmsExtension extension) {
        // TODO: add plugin ID
        String message = "Failed to invoke the method \"%s\" in the extension %s"
                .formatted(methodName, extension.getClass().getName());
        return new ExtensionPointExecutionException(message, t);
    }

}