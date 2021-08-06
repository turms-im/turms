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

package io.netty.buffer;

import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author James Chen
 */
public class RefCntAwareByteBuf extends WrappedByteBuf {

    public static final AtomicReferenceFieldUpdater<RefCntAwareByteBuf, Runnable>
            UPDATER = AtomicReferenceFieldUpdater.newUpdater(RefCntAwareByteBuf.class, Runnable.class, "onCountdownRefCntZero");

    private volatile Runnable onCountdownRefCntZero;
    private boolean countingRetain;
    private int countdownRefCnt;

    public RefCntAwareByteBuf(ByteBuf buf, @NotNull Runnable onCountdownRefCntZero) {
        super(buf);
        this.onCountdownRefCntZero = onCountdownRefCntZero;
    }

    @Override
    public ByteBuf retain(int increment) {
        ByteBuf buf = super.retain(increment);
        if (countingRetain) {
            countdownRefCnt += increment;
        }
        return buf;
    }

    @Override
    public ByteBuf retain() {
        ByteBuf buf = super.retain();
        if (countingRetain) {
            countdownRefCnt++;
        }
        return buf;
    }

    @Override
    public boolean release() {
        boolean isReleased = super.release();
        countdownRefCnt--;
        if (!countingRetain && countdownRefCnt == 0) {
            tryCallOnCountdownRefCntZero();
        }
        return isReleased;
    }

    @Override
    public boolean release(int decrement) {
        boolean isReleased = super.release(decrement);
        countdownRefCnt -= decrement;
        if (!countingRetain && countdownRefCnt == 0) {
            tryCallOnCountdownRefCntZero();
        }
        return isReleased;
    }

    public void startRetainCounter() {
        countingRetain = true;
    }

    public void stopRetainCounter() {
        countingRetain = false;
        if (countdownRefCnt == 0) {
            tryCallOnCountdownRefCntZero();
        }
    }

    private void tryCallOnCountdownRefCntZero() {
        Runnable onCountdownZero = UPDATER.getAndSet(this, null);
        if (onCountdownZero != null) {
            onCountdownZero.run();
        }
    }

}