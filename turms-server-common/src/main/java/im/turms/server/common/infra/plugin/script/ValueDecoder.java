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
import jakarta.annotation.Nullable;

import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;

import im.turms.server.common.infra.collection.CollectionUtil;

/**
 * @author James Chen
 */
public class ValueDecoder {

    private ValueDecoder() {
    }

    public static Object decode(@Nullable Value value) {
        // TODO: pattern matching
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
            Value iterator = value.getIterator();
            List<Object> list = new ArrayList<>((int) value.getArraySize());
            while (iterator.hasIteratorNextElement()) {
                Value element = iterator.getIteratorNextElement();
                list.add(decode(element));
            }
            return list;
        } else if (value.hasHashEntries()) {
            Value iterator = value.getHashKeysIterator();
            Map<Object, Object> map =
                    CollectionUtil.newMapWithExpectedSize((int) value.getHashSize());
            while (iterator.hasIteratorNextElement()) {
                Value entry = iterator.getIteratorNextElement();
                map.put(decode(entry), decode(value.getHashValue(entry)));
            }
            return map;
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

    public static ScriptExecutionException translateException(Object exception) {
        if (exception instanceof ScriptExecutionException e) {
            return e;
        } else if (exception instanceof PolyglotException e) {
            ScriptExceptionSource source = e.isHostException()
                    ? ScriptExceptionSource.HOST
                    : ScriptExceptionSource.SCRIPT;
            return new ScriptExecutionException(e, source);
        } else if (exception instanceof Throwable t) {
            return new ScriptExecutionException(t, ScriptExceptionSource.HOST);
        } else if (exception instanceof Value value && value.isException()) {
            Throwable t;
            try {
                t = value.throwException();
            } catch (Exception e) {
                t = e;
            }
            return new ScriptExecutionException(t, ScriptExceptionSource.SCRIPT);
        } else {
            return new ScriptExecutionException(exception.toString(), ScriptExceptionSource.SCRIPT);
        }
    }

}
