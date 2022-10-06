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

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Set;

/**
 * @author James Chen
 */
@ThreadSafe
public class SetRecycler<T> extends Recycler<Recyclable<Set<T>>> {

    private static final SetRecycler<?> INSTANCE = new SetRecycler<>();
    private static final int MAX_SIZE = 1024 * 10;

    @Override
    Recyclable<Set<T>> newInstance() {
        return new Recyclable<>(this, UnifiedSet.newSet(512));
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