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

package im.turms.server.common.collection;

import im.turms.server.common.util.ReflectionUtil;

import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
public class ConcurrentEnumMap<E extends Enum<E>, V> implements Map<E, V> {

    private static final VarHandle REF = ReflectionUtil.getVarHandle(ConcurrentEnumMap.class, "ref");

    private volatile EnumMap<E, V> ref;

    public ConcurrentEnumMap(EnumMap<E, V> map) {
        super();
        ref = new EnumMap<>(map);
    }

    @Override
    public boolean isEmpty() {
        return ref.isEmpty();
    }

    @Override
    public int size() {
        return ref.size();
    }

    @Override
    public boolean containsValue(Object value) {
        return ref.containsValue(value);
    }

    @Override
    public boolean containsKey(Object key) {
        return ref.containsKey(key);
    }

    @Override
    public V get(Object key) {
        return ref.get(key);
    }

    @Override
    public V put(E key, V value) {
        V added;
        EnumMap<E, V> map;
        do {
            map = new EnumMap<>(ref);
            added = map.put(key, value);
        } while (!REF.compareAndSet(this, ref, map));

        return added;
    }

    @Override
    public V putIfAbsent(E key, V value) {
        EnumMap<E, V> expected;
        EnumMap<E, V> map;
        do {
            expected = ref;
            V prev = expected.get(key);
            if (null != prev) {
                return prev;
            }
            map = new EnumMap<>(expected);
            map.put(key, value);
        } while (!REF.compareAndSet(this, expected, map));
        return null;
    }

    @Override
    public V remove(Object key) {
        V removed;
        EnumMap<E, V> map;
        do {
            map = new EnumMap<>(ref);
            removed = map.remove(key);
        } while (!REF.compareAndSet(this, ref, map));

        return removed;
    }

    @Override
    public void putAll(Map<? extends E, ? extends V> m) {
        EnumMap<E, V> map;
        do {
            map = new EnumMap<>(ref);
            map.putAll(m);
        } while (!REF.compareAndSet(this, ref, map));
    }

    @Override
    public void clear() {
        ref.clear();
    }

    @Override
    public Set<E> keySet() {
        return ref.keySet();
    }

    @Override
    public Collection<V> values() {
        return ref.values();
    }

    @Override
    public Set<Entry<E, V>> entrySet() {
        return ref.entrySet();
    }

}