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
import java.util.function.Consumer;

/**
 * @author James Chen
 */
public class RefCntAwareByteBuf extends WrappedByteBuf {

    private final Consumer<Integer> onRefCntChange;

    public RefCntAwareByteBuf(ByteBuf buf, @NotNull Consumer<Integer> onRefCntChangeAfterRelease) {
        super(buf);
        this.onRefCntChange = onRefCntChangeAfterRelease;
    }

    @Override
    public boolean release() {
        boolean isReleased = super.release();
        onRefCntChange.accept(buf.refCnt());
        return isReleased;
    }

    @Override
    public boolean release(int decrement) {
        boolean isReleased = super.release(decrement);
        onRefCntChange.accept(buf.refCnt());
        return isReleased;
    }

}