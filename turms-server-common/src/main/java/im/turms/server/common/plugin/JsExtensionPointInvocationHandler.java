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

import im.turms.server.common.plugin.script.ScriptExceptionSource;
import im.turms.server.common.plugin.script.ScriptExecutionException;
import im.turms.server.common.plugin.script.ValueDecoder;
import org.graalvm.polyglot.Value;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author James Chen
 */
public class JsExtensionPointInvocationHandler implements InvocationHandler {

    private static final Set<String> OBJECT_METHODS = Set.of("equals",
            "hashCode",
            "toString");

    private final Map<Class<? extends ExtensionPoint>, Map<String, Value>> functions;

    public JsExtensionPointInvocationHandler(
            Map<Class<? extends ExtensionPoint>, Map<String, Value>> functions) {
        this.functions = functions;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (OBJECT_METHODS.contains(method.getName())) {
            return method.invoke(proxy, args);
        }
        Map<String, Value> functionMap = functions.get(method.getDeclaringClass());
        boolean isAsync = method.getReturnType().isAssignableFrom(Mono.class);
        if (functionMap == null) {
            return isAsync ? Mono.empty() : null;
        }
        Value scriptFunction = functionMap.get(method.getName());
        Value returnValue = args == null
                ? scriptFunction.execute()
                : scriptFunction.execute(args);
        if (returnValue.getMetaObject().getMetaSimpleName().equals("Promise")) {
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
                    Consumer<Object> reject = error -> sink.error(translateException(error));
                    returnValue
                            .invokeMember("then", resolve)
                            .invokeMember("catch", reject);
                } catch (Exception ex) {
                    sink.error(translateException(ex));
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

    private ScriptExecutionException translateException(Object exception) {
        if (exception instanceof ScriptExecutionException e) {
            return e;
        } else if (exception instanceof Throwable t) {
            return new ScriptExecutionException(t, ScriptExceptionSource.HOST);
        } else {
            Value errorValue = Value.asValue(exception);
            if (errorValue.isException()) {
                return new ScriptExecutionException(errorValue.throwException(), ScriptExceptionSource.SCRIPT);
            }
            return new ScriptExecutionException(errorValue.toString(), ScriptExceptionSource.SCRIPT);
        }
    }

}
