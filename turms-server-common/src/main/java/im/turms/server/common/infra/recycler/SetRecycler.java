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

package im.turms.server.common.infra.recycler;

import java.util.Set;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import im.turms.server.common.infra.thread.ThreadSafe;

/**
 * @author James Chen
 */
@ThreadSafe
public class SetRecycler<T> extends Recycler<Recyclable<Set<T>>> {

    private static final SetRecycler<?> INSTANCE = new SetRecycler<>();
    private static final int INITIAL_SIZE = 256;
    /**
     * Use a small max size so that a smaller collection can clear/recycle quickly, while a larger
     * collection doesn't need to grow too frequently.
     */
    private static final int MAX_SIZE = 256;

    @Override
    Recyclable<Set<T>> newInstance() {
        return new Recyclable<>(this, UnifiedSet.newSet(INITIAL_SIZE));
    }

    public static <T> Recyclable<Set<T>> obtain() {
        return (Recyclable) INSTANCE.get();
    }

    @Override
    public void recycle(Recyclable<Set<T>> recyclable) {
        Set<T> set = recyclable.getValue();
        if (set.size() > MAX_SIZE) {
            recyclable = newInstance();
        } else {
            set.clear();
        }
        super.recycle(recyclable);
    }

}