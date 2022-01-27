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

import im.turms.server.common.plugin.script.CorruptedScriptException;
import im.turms.server.common.plugin.script.JsContext;
import lombok.Data;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private static final Set<String> BUILTIN_CLASS_MEMBER_KEYS = Set.of(
            IS_TURMS_EXTENSION
    );
    //language=JS
    private static final String PLUGIN_CONTEXT = """
            class TurmsExtension {
                static isTurmsExtension = true;
            }
            """;

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

    public static JsPlugin create(Engine engine, String script) {
        Context context = Context.newBuilder(JS_LANGUAGE_TYPE)
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLookup(className -> true)
                .engine(engine)
                .build();
        Source source = parseScript(PLUGIN_CONTEXT + script);
        context.eval(source);
        Value jsBindings = context
                .getBindings(JS_LANGUAGE_TYPE);
        JsPluginDescriptor descriptor = JsPluginDescriptorFactory.parsePluginDescriptor(jsBindings, script);
        Set<String> memberKeys = jsBindings.getMemberKeys();
        List<TurmsExtension> extensions = new ArrayList<>(memberKeys.size() - BUILTIN_ROOT_MEMBER_KEYS.size());
        for (String memberKey : memberKeys) {
            if (BUILTIN_ROOT_MEMBER_KEYS.contains(memberKey)) {
                continue;
            }
            Value extensionClass = jsBindings.getMember(memberKey);
            Value isTurmsExtension = extensionClass.getMember(IS_TURMS_EXTENSION);
            if (extensionClass.canInstantiate() &&
                    isTurmsExtension != null
                    && isTurmsExtension.isBoolean()
                    && isTurmsExtension.asBoolean()) {
                extensions.add(createExtension(extensionClass));
            }
        }
        new JsContext(descriptor.getId())
                .bind(context, jsBindings);
        return new JsPlugin(context, descriptor, extensions);
    }

    private static JsTurmsExtensionAdaptor createExtension(Value extensionClass) {
        Value extension = extensionClass.newInstance();
        Value getExtensionPoints = extension.getMember(GET_EXTENSION_POINTS);
        if (getExtensionPoints == null || !getExtensionPoints.canExecute()) {
            String message = "The method \"%s\" should return the name of extension points"
                    .formatted(GET_EXTENSION_POINTS);
            throw new CorruptedScriptException(message);
        }
        Value extensionPoints = getExtensionPoints.execute();
        if (!extensionPoints.hasArrayElements()) {
            String message = "The method \"%s\" should return the name of extension points"
                    .formatted(GET_EXTENSION_POINTS);
            throw new CorruptedScriptException(message);
        }
        Map<Class<? extends ExtensionPoint>, Map<String, Value>> functions = new IdentityHashMap<>();
        Value iterator = extensionPoints.getIterator();
        List<Class<? extends ExtensionPoint>> extensionPointClasses = new ArrayList<>((int) extensionPoints.getArraySize());
        while (iterator.hasIteratorNextElement()) {
            Value element = iterator.getIteratorNextElement();
            if (!element.isString()) {
                String message = "The name of extension points should be a string";
                throw new CorruptedScriptException(message);
            }
            String extensionPointClassName = element.asString();
            Class<? extends ExtensionPoint> extensionPointClass;
            try {
                extensionPointClass = (Class<? extends ExtensionPoint>) Class
                        .forName(extensionPointClassName);
            } catch (Exception e) {
                String message = "Cannot find the class of the extension point \"%s\""
                        .formatted(extensionPointClassName);
                throw new CorruptedScriptException(message, e);
            }
            for (Method method : extensionPointClass.getDeclaredMethods()) {
                Map<String, Value> functionMap = functions
                        .computeIfAbsent(extensionPointClass, clazz -> new HashMap<>(8));
                String methodName = method.getName();
                Value function = extension.getMember(methodName);
                if (function.canExecute()) {
                    functionMap.put(methodName, function);
                }
            }
            extensionPointClasses.add(extensionPointClass);
        }
        Object extensionPointProxy = Proxy.newProxyInstance(JsPluginFactory.class.getClassLoader(),
                extensionPointClasses.toArray(new Class[0]),
                new JsExtensionPointInvocationHandler(functions));
        return new JsTurmsExtensionAdaptor(extensionPointProxy, extensionPointClasses, extension);
    }


}
