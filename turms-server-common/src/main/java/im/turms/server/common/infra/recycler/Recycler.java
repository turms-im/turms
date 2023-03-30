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

import org.jctools.queues.MpmcArrayQueue;

import im.turms.server.common.infra.thread.ThreadSafe;

/**
 * Note that if the user forgets to recycle a value, though it is a bug, it is okay because JVM GC
 * will release it finally
 *
 * @author James Chen
 */
@ThreadSafe
public abstract class Recycler<T extends Recyclable<?>> {

    private static final int MAX_SIZE = 512;

    private final MpmcArrayQueue<T> queue = new MpmcArrayQueue<>(MAX_SIZE);

    abstract T newInstance();

    public T get() {
        if (queue.isEmpty()) {
            return newInstance();
        }
        T t = queue.relaxedPoll();
        return t == null
                ? newInstance()
                : t;
    }

    public void recycle(T t) {
        if (queue.size() >= MAX_SIZE) {
            return;
        }
        queue.add(t);
    }

}
