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

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import jakarta.annotation.Nullable;

import lombok.Data;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.context.ApplicationContext;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.plugin.script.CorruptedScriptException;
import im.turms.server.common.infra.plugin.script.JsContext;
import im.turms.server.common.infra.plugin.script.ValueInspector;

/**
 * @author James Chen
 */
@Data
public class JsPluginFactory {

    private static final String JS_LANGUAGE_TYPE = "js";

    private static final String CLASS_TURMS_PLUGIN = "TurmsPlugin";
    private static final String CLASS_TURMS_EXTENSION = "TurmsExtension";
    private static final String MEMBER_GET_EXTENSIONS = "getExtensions";
    private static final String MEMBER_GET_EXTENSION_POINTS = "getExtensionPoints";
    private static final String MEMBER_IS_TURMS_PLUGIN = "isTurmsPlugin";
    private static final String MEMBER_IS_TURMS_EXTENSION = "isTurmsExtension";

    private JsPluginFactory() {
    }

    public static Source parseScript(String script) {
        try {
            return Source.newBuilder("js", script, "script")
                    .mimeType("application/javascript+module")
                    .cached(false)
                    .build();
        } catch (Exception e) {
            throw new CorruptedScriptException("Failed to parse script", e);
        }
    }

    public static JsPlugin create(
            ApplicationContext applicationContext,
            Engine engine,
            String script,
            @Nullable Path path,
            boolean isDebugEnabled,
            String inspectHost,
            int inspectPort) {
        Source source = parseScript(script);
        Context.Builder builder = Context.newBuilder(JS_LANGUAGE_TYPE)
                // TODO: remove once "js.esm-eval-returns-exports" isn't experimental
                .allowExperimentalOptions(true)
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLookup(className -> true)
                .option("js.ecmascript-version", "2022")
                .option("js.esm-eval-returns-exports", "true");
        if (isDebugEnabled) {
            builder.option("inspect",
                    inspectHost
                            + ":"
                            + inspectPort)
                    .option("inspect.Path",
                            UUID.randomUUID()
                                    .toString());
        } else {
            builder.engine(engine);
        }
        Context context = builder.build();
        JsContext.bindBeforeInitialization(context);
        Value exports;
        try {
            exports = context.eval(source);
        } catch (Exception e) {
            CorruptedScriptException exception =
                    new CorruptedScriptException("Failed to eval the script", e);
            try {
                context.close(true);
            } catch (Exception closeException) {
                exception.addSuppressed(new RuntimeException(
                        "Caught an error while closing the JavaScript context",
                        closeException));
            }
            throw exception;
        }
        Value pluginClass = exports.getMember("default");
        if (pluginClass == null
                || !pluginClass.canInstantiate()
                || ValueInspector.isFalse(pluginClass.getMember(MEMBER_IS_TURMS_PLUGIN))) {
            throw new InvalidPluginException(
                    "The script should define a default export to export a subclass of "
                            + CLASS_TURMS_PLUGIN);
        }
        Value plugin;
        try {
            plugin = pluginClass.newInstance();
        } catch (Exception e) {
            throw new PluginExecutionException(
                    "Failed to initialize the plugin: "
                            + pluginClass.getMetaQualifiedName(),
                    e);
        }
        Value bindings = context.getBindings(JS_LANGUAGE_TYPE);
        JsPluginDescriptor descriptor =
                JsPluginDescriptorFactory.parsePluginDescriptor(plugin, path);
        List<TurmsExtension> extensions = createExtensions(applicationContext, plugin);
        JsContext.bindAfterInitialization(context, bindings, descriptor.getId());
        JsPlugin jsPlugin = new JsPlugin(descriptor, extensions, context);
        for (TurmsExtension extension : extensions) {
            extension.setPlugin(jsPlugin);
        }
        return jsPlugin;
    }

    private static List<TurmsExtension> createExtensions(
            ApplicationContext applicationContext,
            Value plugin) {
        Value getExtensions = plugin.getMember(MEMBER_GET_EXTENSIONS);
        if (getExtensions == null) {
            String message = "The plugin must have a function named: \""
                    + MEMBER_GET_EXTENSIONS
                    + "\"";
            throw new InvalidPluginException(message);
        }
        if (!getExtensions.canExecute()) {
            String message = "The member \""
                    + MEMBER_GET_EXTENSIONS
                    + "\" must be a function, but got: "
                    + getExtensions;
            throw new InvalidPluginException(message);
        }
        Value extensionClasses;
        try {
            extensionClasses = getExtensions.execute();
        } catch (Exception e) {
            String message = "Failed to execute the function: \""
                    + MEMBER_GET_EXTENSIONS
                    + "\"";
            throw new PluginExecutionException(message, e);
        }
        if (!extensionClasses.hasArrayElements()) {
            String message = "The function \""
                    + MEMBER_GET_EXTENSIONS
                    + "\" must return an array of the subclass of "
                    + CLASS_TURMS_EXTENSION
                    + ", but got: "
                    + extensionClasses;
            throw new InvalidPluginException(message);
        }
        long extensionClassCount = extensionClasses.getArraySize();
        if (extensionClassCount <= 0) {
            throw new InvalidPluginException(
                    "The method \""
                            + MEMBER_GET_EXTENSIONS
                            + "\" must return non-empty extension classes");
        }
        Value iterator = extensionClasses.getIterator();
        List<TurmsExtension> extensions = new ArrayList<>((int) extensionClassCount);
        while (iterator.hasIteratorNextElement()) {
            Value extensionClass = iterator.getIteratorNextElement();
            if (!extensionClass.canInstantiate()
                    || ValueInspector
                            .isFalse(extensionClass.getMember(MEMBER_IS_TURMS_EXTENSION))) {
                String message = "The function \""
                        + MEMBER_GET_EXTENSIONS
                        + "\" must return an array of the subclass of "
                        + CLASS_TURMS_EXTENSION
                        + ", but got: "
                        + extensionClasses;
                throw new InvalidPluginException(message);
            }
            extensions.add(createExtension(applicationContext, extensionClass));
        }
        return extensions;
    }

    private static JsTurmsExtensionAdaptor createExtension(
            ApplicationContext applicationContext,
            Value extensionClass) {
        try {
            Value extension = extensionClass.newInstance();
            Value getExtensionPoints = extension.getMember(MEMBER_GET_EXTENSION_POINTS);
            if (getExtensionPoints == null || !getExtensionPoints.canExecute()) {
                String message = "The method \""
                        + MEMBER_GET_EXTENSION_POINTS
                        + "\" must return the name of extension points";
                throw new CorruptedScriptException(message);
            }
            Value extensionPointStrings = getExtensionPoints.execute();
            ExtensionClassInfo info = parseExtensionClassInfo(extension, extensionPointStrings);
            ExtensionPoint extensionPointProxy =
                    (ExtensionPoint) Proxy.newProxyInstance(JsPluginFactory.class.getClassLoader(),
                            info.extensionPointClasses.toArray(new Class[0]),
                            new JsExtensionPointInvocationHandler(info.functions));
            JsTurmsExtensionAdaptor adaptor = new JsTurmsExtensionAdaptor(
                    extensionPointProxy,
                    info.extensionPointClasses,
                    extension);
            adaptor.setContext(applicationContext);
            return adaptor;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to create the extension: "
                            + extensionClass,
                    e);
        }
    }

    private static ExtensionClassInfo parseExtensionClassInfo(
            Value extension,
            Value extensionPointStrings) {
        if (!extensionPointStrings.hasArrayElements()) {
            String message = "The method \""
                    + MEMBER_GET_EXTENSION_POINTS
                    + "\" must return the name of extension points";
            throw new CorruptedScriptException(message);
        }
        int size = (int) extensionPointStrings.getArraySize();
        Map<Class<? extends ExtensionPoint>, Map<String, Value>> extensionPointToFunction =
                new IdentityHashMap<>(size);
        Value iterator = extensionPointStrings.getIterator();
        List<Class<? extends ExtensionPoint>> extensionPointClasses = new ArrayList<>(size);
        while (iterator.hasIteratorNextElement()) {
            Class<? extends ExtensionPoint> extensionPointClass =
                    parseExtensionPointClass(iterator.getIteratorNextElement());
            if (extensionPointToFunction.containsKey(extensionPointClass)) {
                continue;
            }
            Method[] methods = extensionPointClass.getMethods();
            Map<String, Value> nameToFunction =
                    CollectionUtil.newMapWithExpectedSize(methods.length);
            extensionPointToFunction.put(extensionPointClass, nameToFunction);
            for (Method method : methods) {
                String methodName = method.getName();
                Value function = extension.getMember(methodName);
                if (function != null && function.canExecute()) {
                    nameToFunction.put(methodName, function);
                }
            }
            extensionPointClasses.add(extensionPointClass);
        }
        return new ExtensionClassInfo(
                extensionPointClasses,
                Map.copyOf(CollectionUtil.transformValues(extensionPointToFunction, Map::copyOf)));
    }

    private static Class<? extends ExtensionPoint> parseExtensionPointClass(Value classString) {
        if (!classString.isString()) {
            String message = "The name of extension points must be a string";
            throw new InvalidPluginException(message);
        }
        String extensionPointClassName = classString.asString();
        try {
            return (Class<? extends ExtensionPoint>) Class.forName(extensionPointClassName);
        } catch (Exception e) {
            String message = "Could not find the extension point class: "
                    + extensionPointClassName;
            throw new InvalidPluginException(message, e);
        }
    }

    private static record ExtensionClassInfo(
            List<Class<? extends ExtensionPoint>> extensionPointClasses,
            Map<Class<? extends ExtensionPoint>, Map<String, Value>> functions
    ) {
    }

}