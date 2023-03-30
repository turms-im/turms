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

import java.util.Set;

import io.netty.util.concurrent.FastThreadLocal;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

/**
 * @author James Chen
 */
public final class CollectionPool {

    private CollectionPool() {
    }

    private static final FastThreadLocal<CloseableCollection<Set<?>>> SET =
            new FastThreadLocal<>() {
                @Override
                protected CloseableCollection<Set<?>> initialValue() {
                    return new CloseableCollection<>(UnifiedSet.newSet(64));
                }
            };

    public static <T> CloseableCollection<Set<T>> getSet() {
        return (CloseableCollection) SET.get();
    }

}
