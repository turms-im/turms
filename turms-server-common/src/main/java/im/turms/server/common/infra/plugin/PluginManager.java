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

import im.turms.server.common.access.admin.web.MultipartFile;
import im.turms.server.common.infra.codec.Base16Util;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.exception.FeatureDisabledException;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.invoker.FirstExtensionPointInvoker;
import im.turms.server.common.infra.plugin.invoker.SequentialExtensionPointInvoker;
import im.turms.server.common.infra.plugin.invoker.SimultaneousExtensionPointInvoker;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.plugin.JsPluginDebugProperties;
import im.turms.server.common.infra.property.env.common.plugin.JsPluginProperties;
import im.turms.server.common.infra.property.env.common.plugin.PluginProperties;
import im.turms.server.common.infra.security.MessageDigestPool;
import lombok.Getter;
import org.graalvm.polyglot.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipFile;
import jakarta.annotation.Nullable;

/**
 * @author James Chen
 */
@Component
public class PluginManager implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginManager.class);

    private static final String EXTERNAL_PLUGIN_ARCHIVE_NAME_PREFIX = "_network_.";

    @Getter
    private final boolean enabled;
    private final Path pluginDir;

    private final boolean allowSaveJavaPlugins;

    private final boolean allowSaveJsPlugins;
    private final boolean isJsScriptEnabled;
    private final boolean isJsDebugEnabled;
    private final String jsInspectHost;
    private final int jsInspectPort;

    private final PluginRepository pluginRepository;
    private final ApplicationContext context;
    /**
     * The object is {@link Engine} in fact,
     * but we don't declare it as {@link Engine} because it is an optional class
     * and Spring will throw when creating the bean when it cannot find the class
     */
    @Nullable
    private Object engine;

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
        pluginDir = getPluginDir(applicationContext.getHome(), pluginProperties.getDir());
        isJsScriptEnabled = ClassUtil.exists("org.graalvm.polyglot.Engine");
        PluginFinder.FindResult findResult = PluginFinder.find(pluginDir, isJsScriptEnabled);
        loadJavaPlugins(findResult.zipFiles());
        allowSaveJavaPlugins = pluginProperties.getJava().isAllowSave();
        if (isJsScriptEnabled) {
            JsPluginProperties jsPluginProperties = pluginProperties.getJs();
            JsPluginDebugProperties debugProperties = jsPluginProperties.getDebug();
            allowSaveJsPlugins = jsPluginProperties.isAllowSave();
            isJsDebugEnabled = debugProperties.isEnabled();
            jsInspectHost = debugProperties.getInspectHost();
            jsInspectPort = debugProperties.getInspectPort();
            engine = Engine.newBuilder()
                    .option("engine.WarnInterpreterOnly", "false")
                    .build();
            loadJsPlugins(findResult.jsFiles());
        } else {
            allowSaveJsPlugins = false;
            isJsDebugEnabled = false;
            jsInspectHost = null;
            jsInspectPort = 0;
        }
        applicationContext.addShutdownHook(JobShutdownOrder.CLOSE_PLUGINS, timeoutMillis -> destroy());
    }

    /**
     * @implNote Start plugins after all beans are ready
     * so that plugins can get any bean when starting
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (enabled) {
            startPlugins();
        }
    }

    private Path getPluginDir(Path home, String pluginsDir) {
        return home.resolve(pluginsDir).toAbsolutePath();
    }

    private Mono<Void> destroy() {
        Exception stopPluginsException = null;
        Exception closeEngineException = null;
        try {
            stopPlugins();
        } catch (Exception e) {
            stopPluginsException = e;
        }
        if (engine != null) {
            try {
                ((Engine) engine).close(true);
            } catch (Exception e) {
                closeEngineException = e;
            }
        }
        if (stopPluginsException != null || closeEngineException != null) {
            Exception e = new RuntimeException("Caught errors while destroying");
            if (stopPluginsException != null) {
                e.addSuppressed(stopPluginsException);
            }
            if (closeEngineException != null) {
                e.addSuppressed(closeEngineException);
            }
            return Mono.error(e);
        }
        return Mono.empty();
    }

    public void loadJavaPlugins(List<MultipartFile> files, boolean save) {
        if (!allowSaveJavaPlugins && save) {
            throw new FeatureDisabledException("Cannot save Java plugins since it has been disabled");
        }
        for (MultipartFile file : files) {
            String fileName = file.name();
            ZipFile zipFile;
            File jarFile;
            if (save) {
                fileName = file.basename() + ".jar";
                Path target = pluginDir.resolve(EXTERNAL_PLUGIN_ARCHIVE_NAME_PREFIX + fileName);
                if (Files.exists(target)) {
                    throw new IllegalArgumentException("The plugin jar file (" +
                            fileName +
                            ") already exists");
                }
                // Ensure the plugin directory exists every time
                // because it may be removed by users unexpectedly
                try {
                    Files.createDirectories(pluginDir);
                } catch (IOException e) {
                    throw new InputOutputException("Failed to create the plugin directory: " +
                            pluginDir, e);
                }
                Path source = file.file().toPath();
                try {
                    Files.move(source, target);
                } catch (IOException e) {
                    throw new InputOutputException("Failed to move the plugin JAR file from (" +
                            source +
                            ") to (" +
                            target +
                            ")", e);
                }
                jarFile = target.toFile();
            } else {
                jarFile = file.file();
                jarFile.deleteOnExit();
                // We need to retain the JAR file because there may some
                // classes or resources in the JAR file haven't been loaded
                file.retain();
            }
            try {
                zipFile = new ZipFile(jarFile);
            } catch (IOException e) {
                throw new MalformedPluginArchiveException("Failed to load the jar file: " + fileName, e);
            }
            JavaPluginDescriptor descriptor = JavaPluginDescriptorFactory.load(zipFile);
            if (descriptor == null) {
                throw new MalformedPluginArchiveException("Could not load a Java plugin from the file (" +
                        fileName + ") because it is not a Java plugin JAR file");
            }
            Plugin plugin = JavaPluginFactory.create(descriptor, context);
            pluginRepository.register(plugin);
        }
    }

    public void loadJavaPlugins(List<ZipFile> zipFiles) {
        List<JavaPluginDescriptor> descriptors = JavaPluginDescriptorFactory.load(zipFiles);
        for (JavaPluginDescriptor descriptor : descriptors) {
            Plugin plugin = JavaPluginFactory.create(descriptor, context);
            pluginRepository.register(plugin);
        }
    }

    public void loadJsPlugins(Collection<JsFile> files) {
        if (CollectionUtil.isEmpty(files)) {
            return;
        }
        for (JsFile file : files) {
            loadJsPlugin(file.script(), file.path());
        }
    }

    public void loadJsPlugins(Collection<JsPluginScript> scripts, boolean save) {
        if (!isJsScriptEnabled) {
            throw new UnsupportedOperationException("JavaScript plugins are disabled because the classes of GraalJS are not loaded");
        }
        if (!allowSaveJsPlugins && save) {
            throw new FeatureDisabledException("Cannot not save JavaScript plugins since it has been disabled");
        }
        if (CollectionUtil.isEmpty(scripts)) {
            return;
        }
        for (JsPluginScript script : scripts) {
            String code = script.code();
            Plugin plugin = loadJsPlugin(code, null);
            if (save) {
                Path path = saveJsPlugin(script.fileName(), code);
                plugin.descriptor().setPath(path);
            }
        }
    }

    public JsPlugin loadJsPlugin(String script, @Nullable Path path) {
        if (!isJsScriptEnabled) {
            throw new UnsupportedOperationException("JavaScript plugins are disabled because the classes of GraalJS are not loaded");
        }
        if (script.isBlank()) {
            throw new IllegalArgumentException("The JavaScript plugin script must not be blank");
        }
        JsPlugin jsPlugin = JsPluginFactory.create(context,
                (Engine) engine,
                script,
                path,
                isJsDebugEnabled,
                jsInspectHost,
                jsInspectPort);
        pluginRepository.register(jsPlugin);
        return jsPlugin;
    }

    private Path saveJsPlugin(@Nullable String fileName, String code) {
        byte[] bytes = code.getBytes(StandardCharsets.UTF_8);
        boolean isCustomFileName = fileName != null;
        if (fileName == null) {
            byte[] digest = MessageDigestPool.getSha1().digest(bytes);
            fileName = Base16Util.encodeAsString(digest, false);
        }
        Path path = pluginDir.resolve(EXTERNAL_PLUGIN_ARCHIVE_NAME_PREFIX + fileName + ".js");
        if (Files.exists(path)) {
            if (isCustomFileName) {
                throw new IllegalArgumentException("The JavaScript plugin file (" + fileName + ") already exists");
            } else {
                return path;
            }
        }
        try {
            Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            throw new InputOutputException("Failed to write the JavaScript plugin to the path: " + path, e);
        }
        return path;
    }

    public <T extends ExtensionPoint> List<T> getExtensionPoints(Class<T> clazz) {
        return pluginRepository.getExtensionPoints(clazz);
    }

    public List<Class<? extends ExtensionPoint>> getExtensionPoints(TurmsExtension extension) {
        return extension.getExtensionPointClasses();
    }

    public Collection<Plugin> getPlugins() {
        return pluginRepository.getPlugins();
    }

    public List<Plugin> getPlugins(Set<String> ids) {
        return pluginRepository.getPlugins(ids);
    }

    public int startPlugins() {
        Collection<Plugin> plugins = pluginRepository.getPlugins();
        return startPlugins(plugins);
    }

    public int startPlugins(Set<String> ids) {
        List<Plugin> plugins = pluginRepository.getPlugins(ids);
        return startPlugins(plugins);
    }

    public int startPlugins(Collection<Plugin> plugins) {
        List<Runnable> runnables = new ArrayList<>(plugins.size());
        for (Plugin plugin : plugins) {
            runnables.add(plugin::start);
        }
        ThrowableUtil.delayError(runnables, "Caught errors while starting plugins");
        return plugins.size();
    }

    public int stopPlugins() {
        Collection<Plugin> plugins = pluginRepository.getPlugins();
        return stopPlugins(plugins);
    }

    public int stopPlugins(Set<String> ids) {
        List<Plugin> plugins = pluginRepository.getPlugins(ids);
        return stopPlugins(plugins);
    }

    public int stopPlugins(Collection<Plugin> plugins) {
        List<Runnable> runnables = new ArrayList<>(plugins.size());
        for (Plugin plugin : plugins) {
            runnables.add(plugin::stop);
        }
        ThrowableUtil.delayError(runnables, "Caught errors while stopping plugins");
        return plugins.size();
    }

    public int resumePlugins(Set<String> ids) {
        List<Plugin> plugins = pluginRepository.getPlugins(ids);
        List<Runnable> runnables = new ArrayList<>(plugins.size());
        for (Plugin plugin : plugins) {
            runnables.add(plugin::resume);
        }
        ThrowableUtil.delayError(runnables, "Caught errors while resuming plugins");
        return plugins.size();
    }

    public int pausePlugins(Set<String> ids) {
        List<Plugin> plugins = pluginRepository.getPlugins(ids);
        List<Runnable> runnables = new ArrayList<>(plugins.size());
        for (Plugin plugin : plugins) {
            runnables.add(plugin::pause);
        }
        ThrowableUtil.delayError(runnables, "Caught errors while pausing plugins");
        return plugins.size();
    }

    public void deletePlugins(Set<String> ids, boolean deleteLocalFiles) {
        List<Plugin> plugins = pluginRepository.removePlugins(ids);
        stopPlugins(plugins);
        if (deleteLocalFiles) {
            for (Plugin plugin : plugins) {
                try {
                    Path path = plugin.descriptor().getPath();
                    if (path != null) {
                        Files.deleteIfExists(path);
                    }
                } catch (Exception e) {
                    // ignored
                }
            }
        }
    }

    public <T extends ExtensionPoint> boolean hasRunningExtensions(Class<T> extensionPointClass) {
        return pluginRepository.hasRunningExtensions(extensionPointClass);
    }

    public <T extends ExtensionPoint, R> Mono<R> invokeFirstExtensionPoint(Class<T> extensionPointClass,
                                                                           Method method,
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
                    .onErrorMap(t -> translateException(t, method, extension));
        } catch (Exception e) {
            return Mono.error(translateException(e, method, extension));
        }
    }

    public <T extends ExtensionPoint, R> Mono<R> invokeExtensionPointsSequentially(Class<T> extensionPointClass,
                                                                                   Method method,
                                                                                   SequentialExtensionPointInvoker<T, R> invoker) {
        return invokeExtensionPointsSequentially(extensionPointClass, method, null, invoker);
    }

    public <T extends ExtensionPoint, R> Mono<R> invokeExtensionPointsSequentially(Class<T> extensionPointClass,
                                                                                   Method method,
                                                                                   @Nullable R initialValue,
                                                                                   SequentialExtensionPointInvoker<T, R> invoker) {
        List<T> extensionPoints = pluginRepository.getExtensionPoints(extensionPointClass);
        int size = extensionPoints.size();
        if (size == 0) {
            return initialValue == null ? Mono.empty() : Mono.just(initialValue);
        }
        Mono<R> result = initialValue == null ? Mono.empty() : Mono.just(initialValue);
        for (ExtensionPoint extensionPoint : extensionPoints) {
            TurmsExtension extension = (TurmsExtension) extensionPoint;
            if (!extension.isRunning()) {
                continue;
            }
            try {
                result = invoker.invoke((T) extensionPoint, result)
                        .onErrorMap(t -> translateException(t, method, extension));
            } catch (Exception e) {
                Mono<R> error = Mono.error(translateException(e, method, extension));
                return result.then(error);
            }
        }
        return result;
    }

    public <T extends ExtensionPoint> Mono<Void> invokeExtensionPointsSimultaneously(Class<T> extensionPointClass,
                                                                                     Method method,
                                                                                     SimultaneousExtensionPointInvoker<T> invoker) {
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
                list.add(invoker.invoke((T) extensionPoint)
                        .onErrorMap(t -> translateException(t, method, extension)));
            } catch (Exception e) {
                list.add(Mono.error(translateException(e, method, extension)));
            }
        }
        if (list.isEmpty()) {
            return Mono.empty();
        }
        return Mono.whenDelayError(list);
    }

    private Exception translateException(Throwable t, Method method, TurmsExtension extension) {
        String message = "Failed to invoke the method \"" +
                method.getName() +
                "\" of the extension (" +
                extension.getClass().getName() +
                ") of the plugin: " +
                extension.getPlugin().descriptor().getId();
        return new ExtensionPointExecutionException(message, t);
    }

}