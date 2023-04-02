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

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.function.LongConsumer;

import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import jdk.internal.vm.annotation.Contended;

/**
 * The implementation has been highly optimized for the case of a single producer and a single
 * consumer, and it is far more efficient than {@link org.jctools.queues.SpscGrowableArrayQueue} for
 * long primitive values.
 *
 * @author James Chen
 */
public class SpscGrowableLongRingBuffer implements RingBuffer {

    private static final AtomicIntegerFieldUpdater<SpscGrowableLongRingBuffer> LOCK_UPDATER =
            AtomicIntegerFieldUpdater.newUpdater(SpscGrowableLongRingBuffer.class, "lock");
    private static final VarHandle VALUES_HANDLE =
            MethodHandles.arrayElementVarHandle(long[].class);

    @Contended
    private volatile long[] values;
    @Contended
    private volatile int readIndex;
    @Contended
    private volatile int writeIndex;
    /**
     * The lock is used to ensure the resize operation and the drain operation won't happen
     * simultaneously.
     */
    @Contended
    private volatile int lock;

    public SpscGrowableLongRingBuffer(int initialCapacity) {
        values = new long[Pow2.roundToPowerOfTwo(initialCapacity)];
    }

    @Override
    public void offer(long value) {
        long[] localValues = values;
        int length = localValues.length;
        int localReadIndex = readIndex;
        int localWriteIndex = writeIndex;
        if ((localWriteIndex - localReadIndex) == length) {
            // grow the array
            while (!LOCK_UPDATER.compareAndSet(this, 0, 1)) {
            }
            try {
                localReadIndex = readIndex;
                int newSize = length << 1;
                long[] newValues = new long[newSize];
                int rightValueCount = length - localReadIndex;
                System.arraycopy(localValues, localReadIndex, newValues, 0, rightValueCount);
                if (localReadIndex != 0) {
                    System.arraycopy(localValues, 0, newValues, rightValueCount, localReadIndex);
                }
                newValues[localWriteIndex & (newValues.length - 1)] = value;
                values = newValues;
                readIndex = 0;
            } finally {
                LOCK_UPDATER.set(this, 0);
            }
        } else {
            VALUES_HANDLE.setVolatile(localValues, localWriteIndex & (length - 1), value);
        }
        writeIndex++;
    }

    @Override
    public int drainAll(LongConsumer consumer) {
        int count = writeIndex - readIndex;
        if (count == 0) {
            return 0;
        }
        long value;
        while (!LOCK_UPDATER.compareAndSet(this, 0, 1)) {
        }
        try {
            int localReadIndex = readIndex;
            count = writeIndex - localReadIndex;
            long[] localValues = values;
            for (int i = 0; i < count; i++) {
                value = (long) VALUES_HANDLE.getVolatile(localValues, localReadIndex);
                consumer.accept(value);
                localReadIndex = (localReadIndex + 1) & (localValues.length - 1);
            }
            readIndex = localReadIndex;
            return count;
        } finally {
            LOCK_UPDATER.set(this, 0);
        }
    }

    @Override
    public int size() {
        return writeIndex - readIndex;
    }

}
