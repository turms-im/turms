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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import jakarta.annotation.Nullable;

import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import im.turms.server.common.infra.exception.IncompatibleJvmException;
import im.turms.server.common.infra.lang.PrimitiveUtil;
import im.turms.server.common.infra.lang.StrJoiner;

/**
 * @author James Chen
 */
public final class CollectionUtil {

    private static final Map.Entry[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];
    private static final Class<?> IMMUTABLE_COLLECTION_CLASS;
    private static final Class<?> IMMUTABLE_SET_CLASS;
    private static final Class<?> IMMUTABLE_MAP_CLASS;

    static {
        IMMUTABLE_SET_CLASS = Set.of()
                .getClass()
                .getSuperclass();
        if (!IMMUTABLE_SET_CLASS.getName()
                .equals("java.util.ImmutableCollections$AbstractImmutableSet")) {
            throw new IncompatibleJvmException(
                    "Could not find the class: java.util.ImmutableCollections$AbstractImmutableSet");
        }
        IMMUTABLE_COLLECTION_CLASS = IMMUTABLE_SET_CLASS.getSuperclass();
        if (!IMMUTABLE_COLLECTION_CLASS.getName()
                .equals("java.util.ImmutableCollections$AbstractImmutableCollection")) {
            throw new IncompatibleJvmException(
                    "Could not find the class: java.util.ImmutableCollections$AbstractImmutableCollection");
        }
        IMMUTABLE_MAP_CLASS = Map.of()
                .getClass()
                .getSuperclass();
        if (!IMMUTABLE_MAP_CLASS.getName()
                .equals("java.util.ImmutableCollections$AbstractImmutableMap")) {
            throw new IncompatibleJvmException(
                    "Could not find the class: java.util.ImmutableCollections$AbstractImmutableMap");
        }
    }

    private CollectionUtil() {
    }

    // region new instance
    public static int getMapCapability(int expectedSize) {
        return (int) (expectedSize / 0.75F + 1.0F);
    }

    public static <T> List<T> newList(Iterator<T> iterator) {
        List<T> list = new LinkedList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <T> List<T> newList(Iterator<T> iterator1, Iterator<T> iterator2) {
        Iterator<T> iterator = new ConcatIterator<>(iterator1, iterator2);
        return newList(iterator);
    }

    public static <T> Set<T> newSet(Collection<T> values) {
        return new UnifiedSet<>(values);
    }

    public static <T> Set<T> newSet(Collection<T> collection1, Collection<T> collection2) {
        int count = collection1.size() + collection2.size();
        Set<T> set = newSetWithExpectedSize(count);
        set.addAll(collection1);
        set.addAll(collection2);
        return set;
    }

    public static <T> Set<T> newSet(Collection<T>... collections) {
        int count = 0;
        for (Collection<T> values : collections) {
            count += values.size();
        }
        Set<T> set = newSetWithExpectedSize(count);
        for (Collection<T> values : collections) {
            set.addAll(values);
        }
        return set;
    }

    public static <T> Set<T> newSet(List<Collection<T>> collections) {
        int count = 0;
        for (Collection<T> values : collections) {
            count += values.size();
        }
        Set<T> set = newSetWithExpectedSize(count);
        for (Collection<T> values : collections) {
            set.addAll(values);
        }
        return set;
    }

    public static <T> Set<T> newSetWithExpectedSize(int expectedSize) {
        return UnifiedSet.newSet(expectedSize);
    }

    public static <T> Set<T> newConcurrentSetWithExpectedSize(int expectedSize) {
        return ConcurrentHashMap.newKeySet(getMapCapability(expectedSize));
    }

    public static <K, V> Map<K, V> newMap(Collection<K> keys, Function<K, V> valueMapper) {
        Map<K, V> map = newMapWithExpectedSize(keys.size());
        for (K key : keys) {
            map.put(key, valueMapper.apply(key));
        }
        return map;
    }

    public static <K, V> Map<K, V> newMapWithExpectedSize(int expectedSize) {
        return new HashMap<>(getMapCapability(expectedSize));
    }

    public static <K, V> Map<K, V> newImmutableMap(Collection<Map.Entry<K, V>> entries) {
        return Map.ofEntries(entries.toArray(EMPTY_ENTRY_ARRAY));
    }

    // endregion

    // region introspection
    public static int getSize(@Nullable Collection<?> collection) {
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    public static int getSize(@Nullable Map<?, ?> map) {
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public static int getSize(@Nullable Iterable<?> iterable) {
        if (iterable == null) {
            return 0;
        } else if (iterable instanceof Collection<?> collection) {
            return collection.size();
        } else if (iterable instanceof Map<?, ?> map) {
            return map.size();
        } else {
            int size = 0;
            for (Object ignored : iterable) {
                size++;
            }
            return size;
        }
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
        return IMMUTABLE_COLLECTION_CLASS.isInstance(iterable)
                || iterable instanceof ImmutableCollection;
    }

    public static boolean isImmutable(Map<?, ?> map) {
        return IMMUTABLE_MAP_CLASS.isInstance(map) || map instanceof ImmutableMap<?, ?>;
    }

    public static boolean isImmutableSet(Iterable<?> iterable) {
        return IMMUTABLE_SET_CLASS.isInstance(iterable);
    }
    // endregion

    // region contains
    public static boolean containsAll(Map<?, ?> map1, Map<?, ?> map2) {
        for (Map.Entry<?, ?> entry : map2.entrySet()) {
            if (!Objects.equals(entry.getValue(), map1.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsAllLooseComparison(Map<?, ?> map1, Map<?, ?> map2) {
        for (Map.Entry<?, ?> entry : map2.entrySet()) {
            if (!areTwoObjectsLooselyEqual(map1.get(entry.getKey()), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean containsExactly(Collection<T> collection, T value) {
        return collection.size() == 1 && collection.contains(value);
    }

    private static boolean areTwoObjectsLooselyEqual(
            @Nullable Object actualValue,
            @Nullable Object expectedValue) {
        if (actualValue == null) {
            return null == expectedValue;
        } else if (expectedValue == null) {
            return false;
        }
        if (expectedValue.equals(actualValue)) {
            return true;
        }
        // For strings and primitives, check if their strings are equal
        if (expectedValue instanceof String
                || PrimitiveUtil.isPrimitiveOrWrapperClass(expectedValue.getClass())) {
            return (actualValue instanceof String
                    || PrimitiveUtil.isPrimitiveOrWrapperClass(actualValue.getClass()))
                    && expectedValue.toString()
                            .equals(actualValue.toString());
        }
        if (expectedValue.getClass()
                .isArray()) {
            // Compare for arrays and collections
            return areCollectionsLooselyEqual(ArrayUtil.getArray(expectedValue), actualValue);
        } else if (expectedValue instanceof Collection<?> expectedValueCollection) {
            // Compare for arrays and collections
            return areCollectionsLooselyEqual(expectedValueCollection, actualValue);
        } else if (expectedValue instanceof Map<?, ?> expectedValueMap) {
            // Compare for maps
            return actualValue instanceof Map<?, ?> actualValueMap
                    && containsAllLooseComparison(actualValueMap, expectedValueMap);
        }
        return false;
    }

    private static boolean areCollectionsLooselyEqual(Object[] value1, Object value2) {
        if (value2.getClass()
                .isArray()) {
            Object[] values = ArrayUtil.getArray(value2);
            return areArraysLooselyEqual(value1, values);
        } else if (value2 instanceof Collection<?> values) {
            return areCollectionLooselyEqual(values, value1);
        }
        return false;
    }

    private static boolean areCollectionsLooselyEqual(Collection<?> values1, Object values2) {
        if (values2.getClass()
                .isArray()) {
            return areCollectionLooselyEqual(values1, (Object[]) values2);
        } else if (values2 instanceof Collection<?> values) {
            if (values1.size() != values.size()) {
                return false;
            }
            Iterator<?> firstIterator = values1.iterator();
            Iterator<?> secondIterator = values.iterator();
            while (firstIterator.hasNext()) {
                if (!areTwoObjectsLooselyEqual(firstIterator.next(), secondIterator.next())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean areCollectionLooselyEqual(Collection<?> values1, Object[] values2) {
        if (values1.size() != values2.length) {
            return false;
        }
        int i = 0;
        for (Object value : values1) {
            if (!areTwoObjectsLooselyEqual(values2[i++], value)) {
                return false;
            }
        }
        return true;
    }

    private static boolean areArraysLooselyEqual(Object[] values1, Object[] values2) {
        int values1Length = values1.length;
        if (values1Length != values2.length) {
            return false;
        }
        for (int i = 0; i < values1Length; i++) {
            if (!areTwoObjectsLooselyEqual(values1[i], values2[i])) {
                return false;
            }
        }
        return true;
    }
    // endregion

    // region conversion

    public static <T> List<T> toList(Collection<T> collection) {
        if (collection instanceof List<T> list) {
            return list;
        }
        if (collection.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<>(collection);
    }

    public static <T> List<T> toListSupportRandomAccess(Collection<T> collection) {
        if (collection instanceof List<T> list && collection instanceof RandomAccess) {
            return list;
        }
        return new ArrayList<>(collection);
    }

    public static <T> Set<T> toSet(Collection<T> values) {
        if (values instanceof Set<T> set) {
            return set;
        }
        if (values.isEmpty()) {
            return Collections.emptySet();
        }
        return new UnifiedSet<>(values);
    }

    public static <T> Set<T> toImmutableSet(Collection<? extends T> collection) {
        if (isImmutableSet(collection)) {
            return (Set<T>) collection;
        }
        if (collection.isEmpty()) {
            return Collections.emptySet();
        }
        return (Set<T>) Set.of(collection.toArray());
    }

    public static <T> String toLatin1String(Collection<T> values, Function<T, String> mapper) {
        StrJoiner joiner = new StrJoiner(values.size());
        for (T value : values) {
            joiner.add(mapper.apply(value));
        }
        return joiner.toStringWithBrackets();
    }
    // endregion

    // region transform
    public static <K, V> Map<V, Set<K>> reverseAsSetValues(
            Map<K, V> map,
            int expectedValuesPerKey) {
        Map<V, Set<K>> result = newMapWithExpectedSize(map.size());
        for (Map.Entry<K, V> keyAndValue : map.entrySet()) {
            result.computeIfAbsent(keyAndValue.getValue(),
                    key -> CollectionUtil.newSetWithExpectedSize(expectedValuesPerKey))
                    .add(keyAndValue.getKey());
        }
        return result;
    }

    public static <T, R> List<R> transformAsList(Collection<T> values, Function<T, R> mapper) {
        List<R> list = new ArrayList<>(values.size());
        for (T value : values) {
            list.add(mapper.apply(value));
        }
        return list;
    }

    public static <T, R> List<R> transformAsList(T[] values, Function<T, R> mapper) {
        List<R> list = new ArrayList<>(values.length);
        for (T value : values) {
            list.add(mapper.apply(value));
        }
        return list;
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
    // endregion

    // region add/remove
    public static <T> List<T> add(List<T> list, Collection<T> values) {
        if (isImmutable(list)) {
            List<T> newList = new ArrayList<>(list.size() + values.size());
            newList.addAll(list);
            list = newList;
        }
        list.addAll(values);
        return list;
    }

    public static <T> List<T> add(List<T> list, T value) {
        if (isImmutable(list)) {
            List<T> newList = new ArrayList<>(list.size() + 1);
            newList.addAll(list);
            list = newList;
        }
        list.add(value);
        return list;
    }

    public static <T> Set<T> add(Set<T> set, Collection<T> values) {
        if (isImmutable(set)) {
            Set<T> newSet = newSetWithExpectedSize(set.size() + values.size());
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

    public static <T> Set<T> remove(Set<T> set, Collection<T> values) {
        if (isImmutable(set)) {
            Set<T> newSet = newSetWithExpectedSize(set.size());
            newSet.addAll(set);
            set = newSet;
        }
        set.removeAll(values);
        return set;
    }

    public static <K, V> Map<K, V> add(Map<K, V> map, K key, V value) {
        if (isImmutable(map)) {
            Map<K, V> newMap = newMapWithExpectedSize(map.size() + 1);
            newMap.putAll(map);
            map = newMap;
        }
        map.put(key, value);
        return map;
    }
    // endregion

    // region merge

    public static <K, V> Map<K, V> merge(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> result = newMapWithExpectedSize(map1.size() + map2.size());
        result.putAll(map1);
        result.putAll(map2);
        return result;
    }

    public static <K, V> Map<K, V> deepMerge(
            Map<K, V> baseMap,
            Map<? extends K, ? extends V> mapToMerge,
            boolean appendCollectionElements) {
        for (Map.Entry<? extends K, ? extends V> entry : mapToMerge.entrySet()) {
            K key = entry.getKey();
            V existingValue = baseMap.get(key);
            V valueToMerge = entry.getValue();
            // We don't need to handle the case of arrays
            // just because we don't use arrays.
            if (appendCollectionElements
                    && existingValue instanceof Collection existingValues
                    && valueToMerge instanceof Collection valuesToMerge) {
                if (!existingValues.containsAll(valuesToMerge)) {
                    existingValues.addAll(valuesToMerge);
                }
            } else if (existingValue instanceof Map && valueToMerge instanceof Map) {
                deepMerge((Map) existingValue, (Map) valueToMerge, appendCollectionElements);
            } else if (existingValue == null || !existingValue.equals(valueToMerge)) {
                baseMap.put(key, valueToMerge);
            }
        }
        return baseMap;
    }
    // endregion

    // region intersection/union
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

    public static <T> Set<T> union(Collection<Set<T>> collections) {
        int count = 0;
        for (Collection<? extends T> collection : collections) {
            count += collection.size();
        }
        if (count == 0) {
            return Collections.emptySet();
        }
        Set<T> set = newSetWithExpectedSize(count);
        for (Collection<? extends T> collection : collections) {
            set.addAll(collection);
        }
        return set;
    }

    public static <T> boolean contains(@Nullable List<T> list, T value) {
        if (list == null) {
            return false;
        }
        return list.contains(value);
    }
    // endregion

}