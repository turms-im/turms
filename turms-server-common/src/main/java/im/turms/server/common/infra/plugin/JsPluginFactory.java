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

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.plugin.script.CorruptedScriptException;
import im.turms.server.common.infra.plugin.script.JsContext;
import im.turms.server.common.infra.plugin.script.ValueInspector;
import lombok.Data;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author James Chen
 */
@Data
public class JsPluginFactory {

    private static final String JS_LANGUAGE_TYPE = "js";
    private static final String GET_PLUGIN_DESCRIPTOR = "getPluginDescriptor";
    private static final String GET_EXTENSION_POINTS = "getExtensionPoints";
    private static final String IS_TURMS_EXTENSION = "isTurmsExtension";
    private static final Set<String> BUILTIN_ROOT_MEMBER_KEYS = Set.of(
            "TurmsExtension",
            GET_PLUGIN_DESCRIPTOR
    );
    //language=JS
    private static final Source PLUGIN_CONTEXT = parseScript("""
            class TurmsExtension {
                static isTurmsExtension = true;
            }
            """);

    private JsPluginFactory() {
    }

    public static Source parseScript(String script) {
        try {
            return Source.newBuilder("js", script, "script")
                    .cached(true)
                    .build();
        } catch (IOException e) {
            throw new CorruptedScriptException("Failed to parse script", e);
        }
    }

    public static JsPlugin create(Engine engine,
                                  String script,
                                  @Nullable Path path,
                                  boolean isDebugEnabled,
                                  String inspectHost,
                                  int inspectPort) {
        Source source = parseScript(script);
        Context.Builder builder = Context.newBuilder(JS_LANGUAGE_TYPE)
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLookup(className -> true);
        if (isDebugEnabled) {
            builder.option("inspect", inspectHost + ":" + inspectPort)
                    .option("inspect.Path", UUID.randomUUID().toString());
        } else {
            builder.engine(engine);
        }
        Context context = builder.build();
        context.eval(PLUGIN_CONTEXT);
        context.eval(source);
        Value bindings = context.getBindings(JS_LANGUAGE_TYPE);
        JsPluginDescriptor descriptor = JsPluginDescriptorFactory.parsePluginDescriptor(bindings, script, path);
        List<TurmsExtension> extensions = createExtensions(context, bindings, bindings.getMemberKeys());
        new JsContext(descriptor.getId()).bind(context, bindings);
        return new JsPlugin(context, descriptor, extensions);
    }

    private static List<TurmsExtension> createExtensions(Context context, Value bindings, Set<String> memberKeys) {
        List<TurmsExtension> extensions = new ArrayList<>(memberKeys.size() - BUILTIN_ROOT_MEMBER_KEYS.size());
        for (String memberKey : memberKeys) {
            if (BUILTIN_ROOT_MEMBER_KEYS.contains(memberKey)) {
                continue;
            }
            Value extensionClass = bindings.getMember(memberKey);
            Value isTurmsExtension = extensionClass.getMember(IS_TURMS_EXTENSION);
            if (extensionClass.canInstantiate() && ValueInspector.getBool(isTurmsExtension)) {
                extensions.add(createExtension(context, extensionClass));
            }
        }
        return extensions;
    }

    private static JsTurmsExtensionAdaptor createExtension(Context context, Value extensionClass) {
        Value extension = extensionClass.newInstance();
        Value getExtensionPoints = extension.getMember(GET_EXTENSION_POINTS);
        if (getExtensionPoints == null || !getExtensionPoints.canExecute()) {
            String message = "The method \"%s\" should return the name of extension points"
                    .formatted(GET_EXTENSION_POINTS);
            throw new CorruptedScriptException(message);
        }
        Value extensionPointStrings = getExtensionPoints.execute();
        ExtensionClassInfo info = parseExtensionClassInfo(extension, extensionPointStrings);
        ExtensionPoint extensionPointProxy = (ExtensionPoint) Proxy.newProxyInstance(JsPluginFactory.class.getClassLoader(),
                info.extensionPointClasses.toArray(new Class[0]),
                new JsExtensionPointInvocationHandler(info.functions));
        return new JsTurmsExtensionAdaptor(context, extensionPointProxy, info.extensionPointClasses, extension);
    }

    private static ExtensionClassInfo parseExtensionClassInfo(Value extension,
                                                              Value extensionPointStrings) {
        if (!extensionPointStrings.hasArrayElements()) {
            String message = "The method \"%s\" should return the name of extension points"
                    .formatted(GET_EXTENSION_POINTS);
            throw new CorruptedScriptException(message);
        }
        int size = (int) extensionPointStrings.getArraySize();
        Map<Class<? extends ExtensionPoint>, Map<String, Value>> extensionPointToFunction =
                new IdentityHashMap<>(size);
        Value iterator = extensionPointStrings.getIterator();
        List<Class<? extends ExtensionPoint>> extensionPointClasses = new ArrayList<>(size);
        while (iterator.hasIteratorNextElement()) {
            Class<? extends ExtensionPoint> extensionPointClass = parseExtensionPointClass(iterator.getIteratorNextElement());
            Method[] methods = extensionPointClass.getMethods();
            for (Method method : methods) {
                Map<String, Value> nameToFunction = extensionPointToFunction
                        .computeIfAbsent(extensionPointClass, clazz -> CollectionUtil.newMapWithExpectedSize(methods.length));
                String methodName = method.getName();
                Value function = extension.getMember(methodName);
                if (function != null && function.canExecute()) {
                    nameToFunction.put(methodName, function);
                }
            }
            extensionPointClasses.add(extensionPointClass);
        }
        return new ExtensionClassInfo(extensionPointClasses, extensionPointToFunction);
    }

    private static Class<? extends ExtensionPoint> parseExtensionPointClass(Value classString) {
        if (!classString.isString()) {
            String message = "The name of extension points should be a string";
            throw new CorruptedScriptException(message);
        }
        String extensionPointClassName = classString.asString();
        try {
            return (Class<? extends ExtensionPoint>) Class
                    .forName(extensionPointClassName);
        } catch (Exception e) {
            String message = "Cannot find the class of the extension point \"%s\""
                    .formatted(extensionPointClassName);
            throw new CorruptedScriptException(message, e);
        }
    }

    private static record ExtensionClassInfo(
            List<Class<? extends ExtensionPoint>> extensionPointClasses,
            Map<Class<? extends ExtensionPoint>, Map<String, Value>> functions
    ) {
    }

}