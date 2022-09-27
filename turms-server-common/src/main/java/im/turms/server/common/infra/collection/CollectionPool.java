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

import io.netty.util.concurrent.FastThreadLocal;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import java.util.Set;

/**
 * @author James Chen
 */
public final class CollectionPool {

    private CollectionPool() {
    }

    private static final FastThreadLocal<Set<?>> SET = new FastThreadLocal<>() {
        @Override
        protected Set<?> initialValue() {
            return UnifiedSet.newSet(64);
        }
    };

    public static <T> Set<T> getSet() {
        return (Set<T>) SET.get();
    }

}
