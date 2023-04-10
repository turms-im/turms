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

import java.io.Closeable;

import lombok.Getter;

/**
 * @author James Chen
 */
public class Recyclable<T> implements Closeable {

    private final Recycler<Recyclable<T>> owner;
    @Getter
    final T value;

    protected Recyclable(Recycler<Recyclable<T>> owner, T value) {
        this.owner = owner;
        this.value = value;
    }

    public void recycle() {
        owner.recycle(this);
    }

    @Override
    public void close() {
        recycle();
    }
}