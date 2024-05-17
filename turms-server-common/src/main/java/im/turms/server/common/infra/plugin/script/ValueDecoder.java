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

package im.turms.server.common.infra.plugin.script;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;

import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.collection.CollectionUtil;

/**
 * @author James Chen
 */
public class ValueDecoder {

    private ValueDecoder() {
    }

    @Nullable
    public static Object decode(@Nullable Value value) {
        if (value == null || value.isNull()) {
            return null;
        } else if (value.isString()) {
            return value.asString();
        } else if (value.isBoolean()) {
            return value.asBoolean();
        } else if (value.isNumber()) {
            if (value.fitsInByte()) {
                return value.asByte();
            } else if (value.fitsInShort()) {
                return value.asShort();
            } else if (value.fitsInInt()) {
                return value.asInt();
            } else if (value.fitsInLong()) {
                return value.asLong();
            } else if (value.fitsInFloat()) {
                return value.asFloat();
            } else if (value.fitsInDouble()) {
                return value.asDouble();
            }
            throw new IllegalArgumentException(
                    "Unknown number value: "
                            + value);
        } else if (value.isHostObject()) {
            return value.asHostObject();
        } else if (value.isProxyObject()) {
            return value.asProxyObject();
        } else if (value.isNativePointer()) {
            return value.asNativePointer();
        } else if (value.isMetaObject()) {
            return value.getMetaObject();
        } else if (value.isDate()) {
            return value.asDate();
        } else if (value.isTime()) {
            return value.asTime();
        } else if (value.isTimeZone()) {
            return value.asTimeZone();
        } else if (value.isDuration()) {
            return value.asDuration();
        } else if (value.isException()) {
            return translateException(value);
        } else if (value.isIterator() || value.hasArrayElements()) {
            return decodeArray(value);
        } else if (value.hasHashEntries()) {
            Value iterator = value.getHashKeysIterator();
            Map<Object, Object> map =
                    CollectionUtil.newMapWithExpectedSize((int) value.getHashSize());
            while (iterator.hasIteratorNextElement()) {
                Value entry = iterator.getIteratorNextElement();
                map.put(decode(entry), decode(value.getHashValue(entry)));
            }
            return map;
        }
        Mono<?> mono = decodeAsMonoIfPromise(value, true);
        if (mono != null) {
            return mono;
        } else if (value.hasMembers()) {
            Set<String> memberKeys = value.getMemberKeys();
            Map<Object, Object> map = CollectionUtil.newMapWithExpectedSize(memberKeys.size());
            for (String memberKey : memberKeys) {
                map.put(memberKey, decode(value.getMember(memberKey)));
            }
            return map;
        }
        throw new IllegalArgumentException(
                "Unknown value: "
                        + value);
    }

    public static List<Object> decodeArray(Value value) {
        Value iterator = value.getIterator();
        List<Object> list = new ArrayList<>((int) value.getArraySize());
        while (iterator.hasIteratorNextElement()) {
            Value element = iterator.getIteratorNextElement();
            list.add(decode(element));
        }
        return list;
    }

    @Nullable
    public static Mono<?> decodeAsMonoIfPromise(Value value, boolean decodePromiseValue) {
        if (!"Promise".equals(value.getMetaObject()
                .getMetaSimpleName())) {
            return null;
        }
        return Mono.create(sink -> {
            try {
                Consumer<Object> resolve = o -> {
                    if (o == null) {
                        sink.success();
                    } else if (o instanceof Value v) {
                        if (decodePromiseValue) {
                            sink.success(ValueDecoder.decode(v));
                        } else {
                            sink.success(v);
                        }
                    } else {
                        sink.success(o);
                    }
                };
                Consumer<Object> reject =
                        error -> sink.error(ValueDecoder.translateException(error));
                value.invokeMember("then", resolve)
                        .invokeMember("catch", reject);
            } catch (Exception e) {
                sink.error(new ScriptExecutionException(
                        "Failed to run the promise",
                        e,
                        ScriptExceptionSource.HOST));
            }
        });
    }

    public static ScriptExecutionException translateException(Object exception) {
        return switch (exception) {
            case ScriptExecutionException e -> e;
            case PolyglotException e -> new ScriptExecutionException(
                    e,
                    e.isHostException()
                            ? ScriptExceptionSource.HOST
                            : ScriptExceptionSource.SCRIPT);
            case Throwable t -> new ScriptExecutionException(t, ScriptExceptionSource.HOST);
            case Value value when value.isException() -> {
                Throwable t;
                try {
                    t = value.throwException();
                } catch (Exception e) {
                    t = e;
                }
                yield new ScriptExecutionException(t, ScriptExceptionSource.SCRIPT);
            }
            default ->
                new ScriptExecutionException(exception.toString(), ScriptExceptionSource.SCRIPT);
        };
    }

}