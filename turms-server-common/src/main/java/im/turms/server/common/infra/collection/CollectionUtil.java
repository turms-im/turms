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
import im.turms.server.common.infra.lang.PrimitiveUtil;
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
    private static final Class<?> IMMUTABLE_SET_CLASS;

    static {
        IMMUTABLE_SET_CLASS = Set.of().getClass().getSuperclass();
        if (!IMMUTABLE_SET_CLASS.getName().equals("java.util.ImmutableCollections$AbstractImmutableSet")) {
            throw new IllegalStateException("Cannot find the class AbstractImmutableSet");
        }
        IMMUTABLE_COLLECTION_CLASS = IMMUTABLE_SET_CLASS.getSuperclass();
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

    public static boolean isImmutableSet(Iterable<?> iterable) {
        return IMMUTABLE_SET_CLASS.isInstance(iterable);
    }

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

    private static boolean areTwoObjectsLooselyEqual(@Nullable Object actualValue, @Nullable Object expectedValue) {
        if (actualValue == null) {
            return null == expectedValue;
        } else if (expectedValue == null) {
            return false;
        }
        if (expectedValue.equals(actualValue)) {
            return true;
        }
        if (expectedValue instanceof String || PrimitiveUtil.isPrimitiveOrWrapperClass(expectedValue.getClass())) {
            return actualValue instanceof String || PrimitiveUtil.isPrimitiveOrWrapperClass(actualValue.getClass()) &&
                    expectedValue.toString().equals(actualValue.toString());
        }
        if (expectedValue.getClass().isArray()) {
            return areCollectionsLooselyEqual(ArrayUtil.getArray(expectedValue), actualValue);
        } else if (expectedValue instanceof Collection<?> expectedValueCollection) {
            return areCollectionsLooselyEqual(expectedValueCollection, actualValue);
        } else if (expectedValue instanceof Map<?, ?> expectedValueMap) {
            return actualValue instanceof Map<?, ?> actualValueMap
                    && containsAllLooseComparison(actualValueMap, expectedValueMap);
        }
        throw new UnsupportedOperationException("The expected value is unsupported: " + expectedValue);
    }

    private static boolean areCollectionsLooselyEqual(Object[] value1, Object value2) {
        if (value2.getClass().isArray()) {
            Object[] values = ArrayUtil.getArray(value2);
            return areArraysLooselyEqual(value1, values);
        } else if (value2 instanceof Collection<?> values) {
            return areCollectionLooselyEqual(values, value1);
        }
        return false;
    }

    private static boolean areCollectionsLooselyEqual(Collection<?> values1, Object values2) {
        if (values2.getClass().isArray()) {
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
        if (values1.length != values2.length) {
            return false;
        }
        for (int i = 0; i < values1.length; i++) {
            if (!areTwoObjectsLooselyEqual(values1[i], values2[i])) {
                return false;
            }
        }
        return true;
    }

    public static <K, V> Map<V, Set<K>> reverseAsSetValues(Map<K, V> map, int expectedValuesPerKey) {
        Map<V, Set<K>> result = newMapWithExpectedSize(map.size());
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

    public static <T> Set<T> copyAsSet(Collection<? extends T> collection) {
        if (isImmutableSet(collection)) {
            return (Set<T>) collection;
        } else {
            return (Set<T>) Set.of(collection.toArray());
        }
    }

    public static <T> Set<T> concatAsSet(Collection<T>... collections) {
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

    public static <T> Set<T> concatAsSet(List<Collection<T>> collections) {
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
}