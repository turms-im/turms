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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

/**
 * @author James Chen
 */
public final class CollectorUtil {

    private CollectorUtil() {
    }

    public static <T> Collector<T, ?, List<T>> toChunkedList() {
        return Collectors.toCollection(ChunkedArrayList::new);
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return Collectors.toCollection(LinkedList::new);
    }

    public static <T> Collector<T, ?, List<T>> toList(int size) {
        return Collectors.toCollection(() -> new ArrayList<>(size));
    }

    public static <T> Collector<T, ?, Set<T>> toSet(int expectedSize) {
        return Collectors.toCollection(() -> UnifiedSet.newSet(expectedSize));
    }

    public static <K, V> Supplier<Map<K, V>> toMap(int expectedSize) {
        return () -> CollectionUtil.newMapWithExpectedSize(expectedSize);
    }

}