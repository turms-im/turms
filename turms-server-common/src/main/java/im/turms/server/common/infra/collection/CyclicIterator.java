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

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author James Chen
 */
public class CyclicIterator<T> implements Iterator<T> {

    private final Iterable<T> iterable;
    private Iterator<T> iterator;

    public CyclicIterator(Iterable<T> iterable) {
        this.iterable = iterable;
        iterator = Collections.emptyIterator();
    }

    @Override
    public boolean hasNext() {
        /*
         * Don't store a new Iterator until we know the user can't remove() the last returned
         * element anymore. Otherwise, when we remove from the old iterator, we may be invalidating
         * the new one. The result is a ConcurrentModificationException or other bad behavior.
         *
         * (If we decide that we really, really hate allocating two Iterators per cycle instead of
         * one, we can optimistically store the new Iterator and then be willing to throw it out if
         * the user calls remove().)
         */
        return iterator.hasNext()
                || iterable.iterator()
                        .hasNext();
    }

    @Override
    public T next() {
        if (!iterator.hasNext()) {
            iterator = iterable.iterator();
            if (!iterator.hasNext()) {
                throw new NoSuchElementException();
            }
        }
        return iterator.next();
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}
