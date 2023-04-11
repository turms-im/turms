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

import java.util.ArrayList;
import java.util.List;

import im.turms.server.common.infra.thread.ThreadSafe;

/**
 * @author James Chen
 */
@ThreadSafe
public class ListRecycler<T> extends Recycler<Recyclable<List<T>>> {

    private static final ListRecycler<?> INSTANCE = new ListRecycler<>();
    private static final int INITIAL_SIZE = 1024;
    /**
     * Use a large max size to try to avoid growing the list, and {@link ArrayList#clear} method
     * clears elements according to its size instead of capacity, so the clear/recycle operation is
     * efficient.
     */
    private static final int MAX_SIZE = 1024;

    @Override
    Recyclable<List<T>> newInstance() {
        return new Recyclable<>(this, new ArrayList<>(INITIAL_SIZE));
    }

    public static <T> Recyclable<List<T>> obtain() {
        return (Recyclable) INSTANCE.get();
    }

    @Override
    public void recycle(Recyclable<List<T>> recyclable) {
        List<T> list = recyclable.getValue();
        if (list.size() > MAX_SIZE) {
            recyclable = newInstance();
        } else {
            list.clear();
        }
        super.recycle(recyclable);
    }

}