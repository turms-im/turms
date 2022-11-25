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

import im.turms.server.common.infra.thread.NotThreadSafe;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
@NotThreadSafe
public final class FastEnumMap<K extends Enum<K>, V> implements Map<K, V> {

    private final K[] keys;
    private final V[] values;
    private int size = 0;

    public FastEnumMap(Class<K> keyClass) {
        keys = keyClass.getEnumConstants();
        values = (V[]) new Object[keys.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return containsKey((K) key);
    }

    @Override
    public boolean containsValue(Object value) {
        if (size == 0) {
            return false;
        }
        for (V v : values) {
            if (value.equals(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        return get((K) key);
    }

    @Override
    public V remove(Object key) {
        return remove((K) key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V putIfAbsent(K key, V value) {
        int ordinal = key.ordinal();
        V existingValue = values[ordinal];
        if (existingValue == null) {
            values[ordinal] = value;
            size++;
        }
        return existingValue;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0, length = values.length; i < length; i++) {
            values[i] = null;
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        if (size == 0) {
            return Collections.emptySet();
        }
        Set<K> set = CollectionUtil.newSetWithExpectedSize(size);
        for (int i = 0, length = values.length; i < length; i++) {
            V element = values[i];
            if (element != null) {
                set.add(keys[i]);
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        if (size == 0) {
            return Collections.emptySet();
        }
        Set<V> set = CollectionUtil.newSetWithExpectedSize(size);
        for (V element : values) {
            if (element != null) {
                set.add(element);
            }
        }
        return set;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        if (size == 0) {
            return Collections.emptySet();
        }
        Set<Entry<K, V>> set = CollectionUtil.newSetWithExpectedSize(size);
        for (int i = 0, length = values.length; i < length; i++) {
            V element = values[i];
            if (element != null) {
                set.add(new AbstractMap.SimpleEntry<>(keys[i], element));
            }
        }
        return set;
    }

    public V put(K key, V value) {
        int ordinal = key.ordinal();
        V existingValue = values[ordinal];
        values[ordinal] = value;
        if (existingValue == null) {
            size++;
        }
        return existingValue;
    }

    public V remove(K key) {
        int ordinal = key.ordinal();
        V value = values[ordinal];
        if (value != null) {
            size--;
            values[ordinal] = null;
        }
        return value;
    }

    public V get(K key) {
        return values[key.ordinal()];
    }

    public boolean containsKey(K key) {
        return values[key.ordinal()] != null;
    }

}