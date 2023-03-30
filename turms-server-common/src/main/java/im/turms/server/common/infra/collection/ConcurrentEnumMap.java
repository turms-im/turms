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

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import im.turms.server.common.infra.exception.NotImplementedException;
import im.turms.server.common.infra.thread.ThreadSafe;

/**
 * @author James Chen
 */
@ThreadSafe
public final class ConcurrentEnumMap<K extends Enum<K>, V> implements Map<K, V> {

    private static final VarHandle VALUES_HANDLE =
            MethodHandles.arrayElementVarHandle(Object[].class);
    private static final AtomicIntegerFieldUpdater<ConcurrentEnumMap<?, ?>> SIZE_UPDATER;

    private final K[] keys;
    private final V[] values;
    private volatile int size;

    static {
        AtomicIntegerFieldUpdater<?> newUpdater =
                AtomicIntegerFieldUpdater.newUpdater(ConcurrentEnumMap.class, "size");
        SIZE_UPDATER = (AtomicIntegerFieldUpdater<ConcurrentEnumMap<?, ?>>) newUpdater;
    }

    public ConcurrentEnumMap(Class<K> keyClass) {
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
        for (int i = 0, length = values.length; i < length; i++) {
            V val = (V) VALUES_HANDLE.getVolatile(values, i);
            if (value.equals(val)) {
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
        throw new NotImplementedException();
    }

    @Override
    public V putIfAbsent(K key, V value) {
        int ordinal = key.ordinal();
        V existingValue;
        do {
            existingValue = (V) VALUES_HANDLE.getVolatile(values, ordinal);
            if (existingValue != null) {
                return existingValue;
            }
        } while (!VALUES_HANDLE.compareAndSet(values, ordinal, null, value));
        SIZE_UPDATER.getAndIncrement(this);
        return null;
    }

    @Override
    public void clear() {
        throw new NotImplementedException();
    }

    @Override
    public Set<K> keySet() {
        int localSize = size;
        if (localSize == 0) {
            return Collections.emptySet();
        }
        Set<K> set = CollectionUtil.newSetWithExpectedSize(localSize);
        for (K key : keys) {
            if (key != null) {
                set.add(key);
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        int localSize = size;
        if (localSize == 0) {
            return Collections.emptySet();
        }
        Set<V> set = CollectionUtil.newSetWithExpectedSize(localSize);
        V value;
        for (int i = 0, length = values.length; i < length; i++) {
            value = (V) VALUES_HANDLE.getVolatile(values, i);
            if (value != null) {
                set.add(value);
            }
        }
        return set;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        int localSize = size;
        if (localSize == 0) {
            return Collections.emptySet();
        }
        Set<Entry<K, V>> set = CollectionUtil.newSetWithExpectedSize(localSize);
        V value;
        for (int i = 0, length = values.length; i < length; i++) {
            value = (V) VALUES_HANDLE.getVolatile(values, i);
            if (value != null) {
                set.add(Map.entry(keys[i], value));
            }
        }
        return set;
    }

    public V put(K key, V value) {
        int ordinal = key.ordinal();
        V existingValue = (V) VALUES_HANDLE.getAndSet(values, ordinal, value);
        if (existingValue == null) {
            SIZE_UPDATER.getAndIncrement(this);
        }
        return existingValue;
    }

    public V remove(K key) {
        int ordinal = key.ordinal();
        V value = (V) VALUES_HANDLE.getAndSet(values, ordinal, null);
        if (value != null) {
            SIZE_UPDATER.getAndDecrement(this);
        }
        return value;
    }

    public V get(K key) {
        return (V) VALUES_HANDLE.getVolatile(values, key.ordinal());
    }

    public boolean containsKey(K key) {
        return ((V) VALUES_HANDLE.getVolatile(values, key.ordinal())) != null;
    }
}