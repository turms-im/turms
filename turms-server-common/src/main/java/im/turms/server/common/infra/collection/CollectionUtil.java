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

import im.turms.server.common.infra.lang.StringUtil;
import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import java.util.function.Function;

/**
 * @author James Chen
 */
public final class CollectionUtil {

    private static final Class<?> IMMUTABLE_COLLECTION_CLASS;

    static {
        IMMUTABLE_COLLECTION_CLASS = Set.of().getClass().getSuperclass().getSuperclass();
        if (!IMMUTABLE_COLLECTION_CLASS.getName().equals("java.util.ImmutableCollections$AbstractImmutableCollection")) {
            throw new IllegalStateException("Cannot find the class AbstractImmutableCollection");
        }
    }

    private CollectionUtil() {
    }

    public static <T> List<T> add(List<T> list, List<T> values) {
        if (isImmutable(list)) {
            List<T> newList = new ArrayList<>(list.size() + values.size());
            newList.addAll(list);
            list = newList;
        }
        list.addAll(values);
        return list;
    }

    public static <T> Set<T> add(Set<T> set, Set<T> values) {
        if (isImmutable(set)) {
            Set<T> newSet = UnifiedSet.newSet(set.size() + values.size());
            newSet.addAll(set);
            set = newSet;
        }
        set.addAll(values);
        return set;
    }

    public static <T> Set<T> add(Set<T> set, T value) {
        if (isImmutable(set)) {
            Set<T> newSet = newSetWithExpectedSize(set.size() + 1);
            newSet.addAll(set);
            set = newSet;
        }
        set.add(value);
        return set;
    }

    public static <T> Set<T> remove(Set<T> set, T value) {
        if (isImmutable(set)) {
            Set<T> newSet = newSetWithExpectedSize(set.size());
            newSet.addAll(set);
            set = newSet;
        }
        set.remove(value);
        return set;
    }

    public static int getSize(@Nullable Iterable<?> iterable) {
        if (iterable == null) {
            return 0;
        } else if (iterable instanceof Collection<?> collection) {
            return collection.size();
        } else {
            int size = 0;
            for (Object ignored : iterable) {
                size++;
            }
            return size;
        }
    }

    public static <T> List<T> concat(Iterator<T> iterator1, Iterator<T> iterator2) {
        Iterator<T> iterator = new ConcatIterator<>(iterator1, iterator2);
        return toList(iterator);
    }

    public static <T> Set<T> intersection(Set<T> c1, Collection<T> c2) {
        Set<T> result = newSetWithExpectedSize(Math.min(c1.size(), c2.size()));
        for (T value : c2) {
            if (c1.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }

    public static <T> List<T> union(List<? extends T> list1, List<? extends T> list2) {
        ArrayList<T> result = new ArrayList<>(list1.size() + list2.size());
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }

    public static <T> Set<T> newSet(Collection<T> values) {
        if (values instanceof Set) {
            return (Set<T>) values;
        }
        return UnifiedSet.newSet(values);
    }

    public static <T> Set<T> newSetWithExpectedSize(int expectedSize) {
        return UnifiedSet.newSet(expectedSize);
    }

    public static <K, V> Map<K, V> newMapWithExpectedSize(int expectedSize) {
        return new HashMap<>(MapUtil.getCapability(expectedSize));
    }

    public static <T> List<T> toList(Iterable<T> iterable) {
        return toList(iterable.iterator());
    }

    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> list = new LinkedList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <T> List<T> toListSupportRandomAccess(Collection<T> collection) {
        if (collection instanceof List<T> list && collection instanceof RandomAccess) {
            return list;
        }
        return new ArrayList<>(collection);
    }

    public static <T> boolean isEmpty(@Nullable Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <K, V> boolean isEmpty(@Nullable Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <T> boolean isNotEmpty(@Nullable Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static <K, V> boolean isNotEmpty(@Nullable Map<K, V> map) {
        return map != null && !map.isEmpty();
    }

    public static boolean isImmutable(Iterable<?> iterable) {
        return IMMUTABLE_COLLECTION_CLASS.isInstance(iterable) || iterable instanceof ImmutableCollection;
    }

    public static boolean containsAll(Map<?, ?> map1, Map<?, ?> map2) {
        for (Map.Entry<?, ?> entry : map2.entrySet()) {
            if (!Objects.equals(entry.getValue(), map1.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsAllLooseComparison(Map<?, ?> map1, Map<?, String> map2) {
        for (Map.Entry<?, ?> entry : map2.entrySet()) {
            if (!StringUtil.toString(entry.getValue()).equals(StringUtil.toString(map1.get(entry.getKey())))) {
                return false;
            }
        }
        return true;
    }

    public static <K, V> Map<V, Set<K>> reverseAsSetValues(Map<K, V> map, int expectedValuesPerKey) {
        Map<V, Set<K>> result = new HashMap<>(map.size());
        for (Map.Entry<K, V> keyAndValue : map.entrySet()) {
            result.computeIfAbsent(keyAndValue.getValue(),
                            key -> CollectionUtil.newSetWithExpectedSize(expectedValuesPerKey))
                    .add(keyAndValue.getKey());
        }
        return result;
    }

    public static <K, V, R> Map<K, R> transformValues(Map<K, V> map, Function<V, R> supplier) {
        Map<K, R> result = newMapWithExpectedSize(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            result.put(entry.getKey(), supplier.apply(entry.getValue()));
        }
        return result;
    }

    public static <K, V, R> Map<K, R> transformValues(Map<K, V> map, R value) {
        Map<K, R> result = newMapWithExpectedSize(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            result.put(entry.getKey(), value);
        }
        return result;
    }

}