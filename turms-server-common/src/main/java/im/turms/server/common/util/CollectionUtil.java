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

import java.util.Collection;
import java.util.Set;

/**
 * @author James Chen
 */
public final class CollectionUtil {

    private CollectionUtil() {
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

}