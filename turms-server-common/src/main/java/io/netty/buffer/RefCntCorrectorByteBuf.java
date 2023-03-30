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

import io.netty.util.IllegalReferenceCountException;

/**
 * Used to avoiding deallocating a pooled ByteBuf more than once.
 * <p>
 * For some code that we cannot control may not release the ByteBuf if an error occurs, and if we
 * add a callback to ensure the pooled ByteBuf is released finally, it will cause the bug mentioned
 * in https://github.com/turms-im/turms/issues/786. So we wrap the pooled ByteBuf to ensure the
 * pooled ByteBuf will be deallocated once at most.
 *
 * @author James Chen
 */
public class RefCntCorrectorByteBuf extends TurmsWrappedByteBuf {

    private int refCnt;

    public RefCntCorrectorByteBuf(ByteBuf buf) {
        super(buf);
        refCnt = buf.refCnt();
    }

    @Override
    public boolean release() {
        if (refCnt <= 0) {
            return true;
        }
        refCnt--;
        return super.release();
    }

    @Override
    public boolean release(int decrement) {
        if (refCnt <= 0) {
            return true;
        }
        refCnt -= decrement;
        return super.release(decrement);
    }

    @Override
    public ByteBuf retain(int increment) {
        if (refCnt <= 0) {
            throw new IllegalReferenceCountException(0, increment);
        }
        refCnt += increment;
        return super.retain(increment);
    }

    @Override
    public ByteBuf retain() {
        if (refCnt <= 0) {
            throw new IllegalReferenceCountException(0, 1);
        }
        refCnt++;
        return super.retain();
    }

}