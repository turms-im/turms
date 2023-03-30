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
import java.util.Set;
import java.util.function.Consumer;

import org.graalvm.polyglot.Value;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.plugin.script.ScriptExceptionSource;
import im.turms.server.common.infra.plugin.script.ScriptExecutionException;
import im.turms.server.common.infra.plugin.script.ValueDecoder;

/**
 * @author James Chen
 */
public class JsExtensionPointInvocationHandler implements InvocationHandler {

    private static final Set<String> OBJECT_METHODS = Set.of("equals", "hashCode", "toString");

    private final Map<Class<? extends ExtensionPoint>, Map<String, Value>> extensionPointToFunction;

    public JsExtensionPointInvocationHandler(
            Map<Class<? extends ExtensionPoint>, Map<String, Value>> extensionPointToFunction) {
        this.extensionPointToFunction = extensionPointToFunction;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (OBJECT_METHODS.contains(method.getName())) {
            return method.invoke(proxy, args);
        }
        Map<String, Value> nameToFunction =
                extensionPointToFunction.get(method.getDeclaringClass());
        Class<?> returnType = method.getReturnType();
        // We only check Mono because we never use Flux
        // for the interfaces of extension points
        boolean isAsync = returnType.isAssignableFrom(Mono.class);
        if (nameToFunction == null) {
            return isAsync
                    ? Mono.empty()
                    : null;
        }
        Value function = nameToFunction.get(method.getName());
        if (function == null) {
            if (isAsync) {
                // Keep it simple because we have only
                // the return type of Mono currently
                return Mono.empty();
            } else if (void.class == returnType) {
                return null;
            } else {
                String message = "Could not find a default return value for the return type: "
                        + returnType.getName();
                throw new ScriptExecutionException(message, ScriptExceptionSource.HOST);
            }
        }
        Value returnValue;
        try {
            returnValue = args == null
                    ? function.execute()
                    : function.execute(args);
        } catch (Exception e) {
            String message = "Failed to execute the function \""
                    + method.getName()
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
        if (returnValue.getMetaObject()
                .getMetaSimpleName()
                .equals("Promise")) {
            return Mono.create(sink -> {
                try {
                    Consumer<Object> resolve = o -> {
                        if (o == null) {
                            sink.success();
                        } else if (o instanceof Value v) {
                            sink.success(ValueDecoder.decode(v));
                        } else {
                            sink.success(o);
                        }
                    };
                    Consumer<Object> reject =
                            error -> sink.error(ValueDecoder.translateException(error));
                    returnValue.invokeMember("then", resolve)
                            .invokeMember("catch", reject);
                } catch (Exception e) {
                    sink.error(new ScriptExecutionException(
                            "Failed to run the promise",
                            e,
                            ScriptExceptionSource.HOST));
                }
            });
        }
        Object val = ValueDecoder.decode(returnValue);
        if (isAsync) {
            return Mono.just(val);
        } else {
            return val;
        }
    }

}
