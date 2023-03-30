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

package im.turms.server.common.infra.netty;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufs;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.RefCntCorrectorByteBuf;
import io.netty.buffer.Unpooled;

import im.turms.server.common.infra.lang.NumberFormatter;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.test.VisibleForTesting;

/**
 * @author James Chen
 */
public final class ByteBufUtil {

    private static final int BYTE_CACHE_SIZE = Byte.MAX_VALUE + 1;
    private static final int INTEGER_CACHE_SIZE = Byte.MAX_VALUE + 1;
    private static final ByteBuf[] BYTE_CACHE;
    private static final ByteBuf[] INTEGER_CACHE;

    static {
        BYTE_CACHE = new ByteBuf[BYTE_CACHE_SIZE];
        INTEGER_CACHE = new ByteBuf[INTEGER_CACHE_SIZE];
        for (int i = 0; i < BYTE_CACHE_SIZE; i++) {
            BYTE_CACHE[i] = getUnreleasableDirectBuffer((byte) i);
        }
        for (int i = 0; i < INTEGER_CACHE_SIZE; i++) {
            INTEGER_CACHE[i] = getUnreleasableDirectBuffer(i);
        }
    }

    private ByteBufUtil() {
    }

    public static ByteBuf getPooledPreferredByteBuffer(int value) {
        if (0 <= value && value < BYTE_CACHE_SIZE) {
            return BYTE_CACHE[value];
        }
        return PooledByteBufAllocator.DEFAULT.directBuffer(Byte.BYTES)
                .writeByte(value);
    }

    public static ByteBuf getPooledPreferredIntegerBuffer(int value) {
        if (0 <= value && value < INTEGER_CACHE_SIZE) {
            return INTEGER_CACHE[value];
        }
        return PooledByteBufAllocator.DEFAULT.directBuffer(Integer.BYTES)
                .writeInt(value);
    }

    public static ByteBuf getPooledLongBuffer(long value) {
        return PooledByteBufAllocator.DEFAULT.directBuffer(Long.BYTES)
                .writeLong(value);
    }

    public static ByteBuf getUnreleasableDirectBuffer(byte b) {
        ByteBuf buffer = Unpooled.directBuffer(Byte.BYTES)
                .writeByte(b);
        return Unpooled.unreleasableBuffer(buffer);
    }

    public static ByteBuf getUnreleasableDirectBuffer(byte[] bytes) {
        ByteBuf buffer = Unpooled.directBuffer(bytes.length)
                .writeBytes(bytes);
        return Unpooled.unreleasableBuffer(buffer);
    }

    public static ByteBuf getUnreleasableDirectBuffer(int i) {
        ByteBuf buffer = Unpooled.directBuffer(Integer.BYTES)
                .writeInt(i);
        return Unpooled.unreleasableBuffer(buffer);
    }

    public static ByteBuf getUnreleasableDirectBuffer(String string) {
        byte[] bytes = StringUtil.getBytes(string);
        return Unpooled.unreleasableBuffer(Unpooled.directBuffer(bytes.length)
                .writeBytes(bytes));
    }

    public static ByteBuf writeByte(byte element) {
        return getPooledPreferredByteBuffer(element);
    }

    public static ByteBuf writeShort(short element) {
        return PooledByteBufAllocator.DEFAULT.directBuffer(Short.BYTES)
                .writeShort(element);
    }

    public static ByteBuf writeInt(int element) {
        return getPooledPreferredIntegerBuffer(element);
    }

    public static ByteBuf writeLong(long element) {
        return PooledByteBufAllocator.DEFAULT.directBuffer(Long.BYTES)
                .writeLong(element);
    }

    public static ByteBuf writeFloat(float element) {
        return PooledByteBufAllocator.DEFAULT.directBuffer(Float.BYTES)
                .writeFloat(element);
    }

    public static ByteBuf writeDouble(double element) {
        return PooledByteBufAllocator.DEFAULT.directBuffer(Double.BYTES)
                .writeDouble(element);
    }

    public static ByteBuf writeChar(char element) {
        return PooledByteBufAllocator.DEFAULT.directBuffer(Character.BYTES)
                .writeChar(element);
    }

    public static ByteBuf writeBoolean(boolean element) {
        return getPooledPreferredByteBuffer(element
                ? 1
                : 0);
    }

    public static ByteBuf writeString(String element) {
        byte[] bytes = StringUtil.getUtf8Bytes(element);
        return PooledByteBufAllocator.DEFAULT.directBuffer(bytes.length)
                .writeBytes(bytes);
    }

    public static String readString(ByteBuf buffer) {
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @VisibleForTesting
    public static String getString(ByteBuf buffer) {
        buffer.markReaderIndex();
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        buffer.resetReaderIndex();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static ByteBuf writeObject(Object obj) {
        if (obj instanceof ByteBuf element) {
            return element;
        }
        if (obj instanceof Byte element) {
            return getPooledPreferredByteBuffer(element.intValue());
        }
        if (obj instanceof Short element) {
            return PooledByteBufAllocator.DEFAULT.directBuffer(Short.BYTES)
                    .writeShort(element);
        }
        if (obj instanceof Integer element) {
            return getPooledPreferredIntegerBuffer(element);
        }
        if (obj instanceof Long element) {
            return PooledByteBufAllocator.DEFAULT.directBuffer(Long.BYTES)
                    .writeLong(element);
        }
        if (obj instanceof String element) {
            byte[] bytes = StringUtil.getUtf8Bytes(element);
            return PooledByteBufAllocator.DEFAULT.directBuffer(bytes.length)
                    .writeBytes(bytes);
        }
        if (obj instanceof Float element) {
            return PooledByteBufAllocator.DEFAULT.directBuffer(Float.BYTES)
                    .writeFloat(element);
        }
        if (obj instanceof Double element) {
            return PooledByteBufAllocator.DEFAULT.directBuffer(Double.BYTES)
                    .writeDouble(element);
        }
        if (obj instanceof Character element) {
            return PooledByteBufAllocator.DEFAULT.directBuffer(Character.BYTES)
                    .writeChar(element);
        }
        if (obj instanceof Boolean element) {
            return getPooledPreferredByteBuffer(element
                    ? 1
                    : 0);
        }
        throw new IllegalArgumentException(
                "Could not serialize the unknown value: "
                        + obj);
    }

    public static ByteBuf[] writeObjects(Object... objs) {
        int length = objs.length;
        ByteBuf[] buffers = new ByteBuf[length];
        for (int i = 0; i < length; i++) {
            try {
                buffers[i] = ByteBufUtil.writeObject(objs[i]);
            } catch (Exception e) {
                for (int j = 0; j < i; j++) {
                    buffers[j].release();
                }
                throw new RuntimeException(e);
            }
        }
        return buffers;
    }

    public static ByteBuf ensureByteBufRefCnfCorrect(ByteBuf buf) {
        if (ByteBufs.isUnreleasable(buf) || buf instanceof RefCntCorrectorByteBuf) {
            return buf;
        }
        return new RefCntCorrectorByteBuf(buf);
    }

    public static ByteBuf join(int estimatedSize, int delimiter, Object... elements) {
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(estimatedSize);
        for (int i = 0, length = elements.length, last = length - 1; i < length; i++) {
            Object element = elements[i];
            if (element instanceof Integer num) {
                buffer.writeBytes(NumberFormatter.toCharBytes(num));
            } else if (element instanceof Long num) {
                buffer.writeBytes(NumberFormatter.toCharBytes(num));
            } else if (element instanceof String s) {
                buffer.writeBytes(StringUtil.getBytes(s));
            } else if (element instanceof byte[] bytes) {
                buffer.writeBytes(bytes);
            } else if (element instanceof Character c) {
                buffer.writeChar(c);
            } else if (element != null) {
                buffer.release();
                throw new IllegalArgumentException(
                        "Unsupported class: "
                                + element.getClass()
                                        .getName());
            }
            if (i != last) {
                buffer.writeByte(delimiter);
            }
        }
        return buffer;
    }

}
