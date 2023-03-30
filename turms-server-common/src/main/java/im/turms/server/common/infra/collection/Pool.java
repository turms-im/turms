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

package im.turms.server.common.infra.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.UnaryOperator;

import org.jctools.maps.NonBlockingHashMapLong;

import im.turms.server.common.infra.thread.ThreadSafe;

/**
 * @author James Chen
 */
@ThreadSafe
public class Pool<T> {

    public static final long RESERVED_KEY = 0;
    private static final long FALLBACK_KEY = Long.MIN_VALUE;

    private final int maxSize;
    private final NonBlockingHashMapLong<Object> keyToObject;

    public Pool(int maxSize, int initialSize) {
        this.maxSize = maxSize;
        keyToObject = new NonBlockingHashMapLong<>(initialSize);
    }

    public T poolIfAbsent(T value) {
        return poolIfAbsent(value.hashCode(), value);
    }

    public T poolIfAbsent(long key, T value) {
        if (key == RESERVED_KEY) {
            key = FALLBACK_KEY;
        }
        Object existingObject = keyToObject.get(key);
        if (existingObject == null) {
            // We don't use LRU just because we don't need it currently
            if (keyToObject.size() > maxSize) {
                Iterator<Map.Entry<Long, Object>> iterator = keyToObject.entrySet()
                        .iterator();
                if (iterator.hasNext()) {
                    iterator.remove();
                }
            }
            existingObject = keyToObject.putIfAbsent(key, value);
            if (existingObject == null) {
                return value;
            }
        }
        if (existingObject instanceof InternalArrayList existingObjects) {
            for (Object existingObj : existingObjects) {
                if (existingObj.equals(value)) {
                    return (T) existingObj;
                }
            }
            while (true) {
                InternalArrayList<T> newObjects = new InternalArrayList<>(existingObjects, value);
                Object previousObjects = keyToObject.putIfAbsent(key, newObjects);
                if (previousObjects == existingObjects) {
                    return value;
                } else {
                    existingObjects = (InternalArrayList<T>) previousObjects;
                    for (Object existingObj : existingObjects) {
                        if (existingObj.equals(value)) {
                            return (T) existingObj;
                        }
                    }
                }
            }
        } else {
            if (existingObject.equals(value)) {
                return (T) existingObject;
            }
            InternalArrayList<T> newObjects = new InternalArrayList<>((T) existingObject, value);
            Object previousObjects = keyToObject.putIfAbsent(key, newObjects);
            if (previousObjects == existingObject) {
                return value;
            }
            InternalArrayList<T> existingObjects = (InternalArrayList<T>) previousObjects;
            while (true) {
                newObjects = new InternalArrayList<>(existingObjects, value);
                previousObjects = keyToObject.putIfAbsent(key, newObjects);
                if (previousObjects == existingObjects) {
                    return value;
                } else {
                    existingObjects = (InternalArrayList<T>) previousObjects;
                    for (Object existingObj : existingObjects) {
                        if (existingObj.equals(value)) {
                            return (T) existingObj;
                        }
                    }
                }
            }
        }
    }

    public T poolIfAbsent(T value, UnaryOperator<T> transformer) {
        return poolIfAbsent(value.hashCode(), value, transformer);
    }

    public T poolIfAbsent(long key, T value, UnaryOperator<T> transformer) {
        if (key == RESERVED_KEY) {
            key = FALLBACK_KEY;
        }
        Object existingObject = keyToObject.get(key);
        boolean transformed = false;
        if (existingObject == null) {
            // We don't use LRU just because we don't need it currently
            if (keyToObject.size() > maxSize) {
                Iterator<Map.Entry<Long, Object>> iterator = keyToObject.entrySet()
                        .iterator();
                if (iterator.hasNext()) {
                    iterator.remove();
                }
            }
            value = transformer.apply(value);
            existingObject = keyToObject.putIfAbsent(key, value);
            if (existingObject == null) {
                return value;
            }
            transformed = true;
        }
        if (existingObject instanceof InternalArrayList existingObjects) {
            for (Object existingObj : existingObjects) {
                if (existingObj.equals(value)) {
                    return (T) existingObj;
                }
            }
            if (!transformed) {
                value = transformer.apply(value);
            }
            while (true) {
                InternalArrayList<T> newObjects = new InternalArrayList<>(existingObjects, value);
                Object previousObjects = keyToObject.putIfAbsent(key, newObjects);
                if (previousObjects == existingObjects) {
                    return value;
                } else {
                    existingObjects = (InternalArrayList<T>) previousObjects;
                    for (Object existingObj : existingObjects) {
                        if (existingObj.equals(value)) {
                            return (T) existingObj;
                        }
                    }
                }
            }
        } else {
            if (!transformed) {
                value = transformer.apply(value);
            }
            if (existingObject.equals(value)) {
                return (T) existingObject;
            }
            InternalArrayList<T> newObjects = new InternalArrayList<>((T) existingObject, value);
            Object previousObjects = keyToObject.putIfAbsent(key, newObjects);
            if (previousObjects == existingObject) {
                return value;
            }
            InternalArrayList<T> existingObjects = (InternalArrayList<T>) previousObjects;
            while (true) {
                newObjects = new InternalArrayList<>(existingObjects, value);
                previousObjects = keyToObject.putIfAbsent(key, newObjects);
                if (previousObjects == existingObjects) {
                    return value;
                } else {
                    existingObjects = (InternalArrayList<T>) previousObjects;
                    for (Object existingObj : existingObjects) {
                        if (existingObj.equals(value)) {
                            return (T) existingObj;
                        }
                    }
                }
            }
        }
    }

    public int size() {
        return keyToObject.size();
    }

    public Collection<T> values() {
        return (Collection<T>) keyToObject.values();
    }

    private static class InternalArrayList<T> extends ArrayList<T> {
        InternalArrayList(T element1, T element2) {
            super(2);
            add(element1);
            add(element2);
        }

        InternalArrayList(InternalArrayList<T> list, T element) {
            super(list.size() + 1);
            addAll(list);
            add(element);
        }
    }

}
