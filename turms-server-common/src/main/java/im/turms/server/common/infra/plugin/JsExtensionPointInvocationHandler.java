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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import jakarta.annotation.Nullable;

import org.graalvm.polyglot.Value;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.lang.PrimitiveUtil;
import im.turms.server.common.infra.plugin.script.ScriptExceptionSource;
import im.turms.server.common.infra.plugin.script.ScriptExecutionException;
import im.turms.server.common.infra.plugin.script.ValueDecoder;

/**
 * @author James Chen
 */
public class JsExtensionPointInvocationHandler implements InvocationHandler {

    private final String className;
    private final Map<Class<? extends ExtensionPoint>, Map<String, Value>> extensionPointToFunction;

    public JsExtensionPointInvocationHandler(
            String className,
            Map<Class<? extends ExtensionPoint>, Map<String, Value>> extensionPointToFunction) {
        this.className = className;
        this.extensionPointToFunction = extensionPointToFunction;
    }

    @Nullable
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, Value> nameToFunction =
                extensionPointToFunction.get(method.getDeclaringClass());
        Value function = null;
        Class<?> returnType = method.getReturnType();
        String methodName = method.getName();
        if (nameToFunction != null) {
            function = nameToFunction.get(methodName);
        }
        // We only check if it is Mono because we
        // never use Flux for the interfaces of extension points.
        boolean isAsync = returnType.isAssignableFrom(Mono.class);
        if (function == null) {
            return switch (methodName) {
                case "equals" -> args.length >= 1 && proxy == args[0];
                case "hashCode" -> System.identityHashCode(proxy);
                case "toString" -> className
                        + "@"
                        + Integer.toHexString(System.identityHashCode(proxy));
                default -> {
                    if (isAsync) {
                        // Keep it simple because we only use
                        // Mono as the async return type of interfaces.
                        yield Mono.empty();
                    } else if (void.class == returnType
                            || Object.class.isAssignableFrom(returnType)) {
                        yield null;
                    }
                    Object defaultValue = PrimitiveUtil.getDefaultValue(returnType);
                    if (defaultValue != null) {
                        yield defaultValue;
                    }
                    String message = "Could not find a default return value for the return type: "
                            + returnType.getName();
                    throw new ScriptExecutionException(message, ScriptExceptionSource.HOST);
                }
            };
        }
        Value returnValue;
        try {
            returnValue = args == null
                    ? function.execute()
                    : function.execute(args);
        } catch (Exception e) {
            String message = "Failed to execute the function \""
                    + methodName
                    + "\" in the class: "
                    + method.getDeclaringClass()
                            .getName();
            ScriptExecutionException exception =
                    new ScriptExecutionException(message, e, ScriptExceptionSource.SCRIPT);
            if (isAsync) {
                return Mono.error(exception);
            } else {
                throw exception;
            }
        }
        return parseReturnValue(isAsync, returnValue);
    }

    private Object parseReturnValue(boolean isAsync, Value returnValue) {
        Object val = ValueDecoder.decode(returnValue);
        if (isAsync) {
            if (val instanceof Mono<?> mono) {
                return mono;
            }
            return Mono.just(val);
        } else {
            return val;
        }
    }

}