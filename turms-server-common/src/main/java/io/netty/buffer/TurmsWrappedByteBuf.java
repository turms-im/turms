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

import java.nio.ByteOrder;

import io.netty.util.internal.ObjectUtil;

/**
 * @author James Chen
 */
public class TurmsWrappedByteBuf extends WrappedByteBuf {

    private SwappedByteBuf swappedBuf;

    public TurmsWrappedByteBuf(ByteBuf buf) {
        super(buf);
    }

    @Override
    public ByteBuf order(ByteOrder endianness) {
        if (ObjectUtil.checkNotNull(endianness, "endianness") == order()) {
            return this;
        }
        SwappedByteBuf swappedBuf = this.swappedBuf;
        if (swappedBuf == null) {
            this.swappedBuf = swappedBuf = new SwappedByteBuf(this);
        }
        return swappedBuf;
    }

    @Override
    public ByteBuf asReadOnly() {
        return buf.isReadOnly()
                ? this
                : new TurmsWrappedByteBuf(buf.asReadOnly());
    }

    @Override
    public ByteBuf readSlice(int length) {
        return new TurmsWrappedByteBuf(buf.readSlice(length));
    }

    @Override
    public ByteBuf readRetainedSlice(int length) {
        return readSlice(length);
    }

    @Override
    public ByteBuf slice() {
        return new TurmsWrappedByteBuf(buf.slice());
    }

    @Override
    public ByteBuf retainedSlice() {
        return slice();
    }

    @Override
    public ByteBuf slice(int index, int length) {
        return new TurmsWrappedByteBuf(buf.slice(index, length));
    }

    @Override
    public ByteBuf retainedSlice(int index, int length) {
        return slice(index, length);
    }

    @Override
    public ByteBuf duplicate() {
        return new TurmsWrappedByteBuf(buf.duplicate());
    }

    @Override
    public ByteBuf retainedDuplicate() {
        return duplicate();
    }

}