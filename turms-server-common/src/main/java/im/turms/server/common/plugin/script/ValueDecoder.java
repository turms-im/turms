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

package im.turms.server.common.plugin.script;

import org.graalvm.polyglot.Value;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            throw new IllegalArgumentException("Unknown number value: " + value);
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
            return value.throwException();
        } else if (value.isIterator()) {
            return value;
        } else if (value.hasArrayElements()) {
            Value iterator = value.getIterator();
            List<Object> list = new ArrayList<>((int) value.getArraySize());
            while (iterator.hasIteratorNextElement()) {
                Value element = iterator.getIteratorNextElement();
                list.add(decode(element));
            }
            return list;
        } else if (value.hasHashEntries()) {
            Value iterator = value.getHashKeysIterator();
            Map<Object, Object> map = new HashMap<>();
            while (iterator.hasIteratorNextElement()) {
                Value entry = iterator.getIteratorNextElement();
                map.put(decode(entry), decode(value.getHashValue(entry)));
            }
            return map;
        } else if (value.hasMembers()) {
            Map<Object, Object> map = new HashMap<>();
            value.getMemberKeys().forEach(action -> map.put(action, decode(value.getMember(action))));
            return map;
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
