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

package im.turms.server.common.util;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;

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

    public static <T> Set<T> add(Set<T> set, T value) {
        if (IMMUTABLE_COLLECTION_CLASS.isInstance(set)) {
            Set<T> newSet = newSetWithExpectedSize(set.size() + 1);
            newSet.addAll(set);
            set = newSet;
        }
        set.add(value);
        return set;
    }

    public static int getSize(Iterable<?> iterable) {
        if (iterable instanceof Collection<?> collection) {
            return collection.size();
        } else {
            int size = 0;
            for (Object ignored : iterable) {
                size++;
            }
            return size;
        }
    }

    public static <T> Set<T> intersection(Set<T> c1, Collection<T> c2) {
        Set<T> result = UnifiedSet.newSet(MapUtil.getCapability(Math.min(c1.size(), c2.size())));
        for (T value : c2) {
            if (c1.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }

    public static <T> Set<T> newSetWithExpectedSize(int expectedSize) {
        return UnifiedSet.newSet(expectedSize);
    }

    public static <T> Set<T> newSet(Collection<T> keys) {
        if (keys instanceof Set) {
            return (Set<T>) keys;
        }
        return UnifiedSet.newSet(keys);
    }

    public static <T> List<T> toListSupportRandomAccess(Collection<T> collection) {
        if (collection instanceof List<T> list && collection instanceof RandomAccess) {
            return list;
        }
        return new ArrayList<>(collection);
    }

}