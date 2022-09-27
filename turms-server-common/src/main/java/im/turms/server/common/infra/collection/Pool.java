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

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.UnaryOperator;

/**
 * @author James Chen
 */
public class Pool<T> {

    private final int maxSize;
    /**
     * Don't use NonBlockingHashMapLong because it's inefficient for our cases
     */
    private final ConcurrentHashMap<Long, ConcurrentLinkedQueue<T>> idToObject;

    public Pool(int maxSize, int initialSize) {
        this.maxSize = maxSize;
        idToObject = new ConcurrentHashMap<>(initialSize);
    }

    public T poolIfAbsent(T value) {
        long key = value.hashCode();
        ConcurrentLinkedQueue<T> existingObjects = idToObject.get(key);
        if (existingObjects == null) {
            // We don't use LRU just because we don't it currently
            if (idToObject.size() > maxSize) {
                Iterator<Map.Entry<Long, ConcurrentLinkedQueue<T>>> iterator = idToObject.entrySet().iterator();
                if (iterator.hasNext()) {
                    iterator.remove();
                }
            }
            ConcurrentLinkedQueue<T> newObjects = new ConcurrentLinkedQueue<>();
            newObjects.add(value);
            existingObjects = idToObject.putIfAbsent(key, newObjects);
            if (existingObjects == null) {
                return value;
            }
        }
        for (T existingObject : existingObjects) {
            if (existingObject.equals(value)) {
                return existingObject;
            }
        }
        existingObjects.add(value);
        return value;
    }

    public T poolIfAbsent(T value, UnaryOperator<T> transformer) {
        long key = value.hashCode();
        ConcurrentLinkedQueue<T> existingObjects = idToObject.get(key);
        if (existingObjects == null) {
            // We don't use LRU just because we don't it currently
            if (idToObject.size() > maxSize) {
                Iterator<Map.Entry<Long, ConcurrentLinkedQueue<T>>> iterator = idToObject.entrySet().iterator();
                if (iterator.hasNext()) {
                    iterator.remove();
                }
            }
            ConcurrentLinkedQueue<T> newObjects = new ConcurrentLinkedQueue<>();
            T newValue = transformer.apply(value);
            newObjects.add(newValue);
            existingObjects = idToObject.putIfAbsent(key, newObjects);
            if (existingObjects == null) {
                return newValue;
            }
        }
        for (T existingObject : existingObjects) {
            if (existingObject.equals(value)) {
                return existingObject;
            }
        }
        T newValue = transformer.apply(value);
        existingObjects.add(newValue);
        return newValue;
    }

    public int size() {
        return idToObject.size();
    }

}
