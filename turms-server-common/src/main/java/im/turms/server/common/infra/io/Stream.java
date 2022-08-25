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

package im.turms.server.common.infra.io;

import com.google.common.collect.Maps;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.StringUtil;
import io.netty.buffer.ByteBuf;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static im.turms.server.common.infra.net.InetAddressUtil.IPV4_BYTE_LENGTH;
import static im.turms.server.common.infra.net.InetAddressUtil.IPV6_BYTE_LENGTH;

/**
 * @author James Chen
 */
public class Stream implements StreamInput, StreamOutput {

    final ByteBuf buf;

    public Stream(ByteBuf buf) {
        this.buf = buf;
    }

    @Override
    public byte readByte() {
        return buf.readByte();
    }

    @Override
    public Stream writeByte(int value) {
        buf.writeByte(value);
        return this;
    }

    @Override
    public byte[] readBytes(int length) {
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        return bytes;
    }

    @Override
    public Stream writeBytes(byte[] bytes) {
        buf.writeBytes(bytes);
        return this;
    }

    @Override
    public short readShort() {
        return buf.readShort();
    }

    @Override
    public Stream writeShort(int value) {
        buf.writeShort(value);
        return this;
    }

    @Override
    public int readInt() {
        return buf.readInt();
    }

    @Override
    public Stream writeInt(int value) {
        buf.writeInt(value);
        return this;
    }

    @Override
    public List<Integer> readInts() {
        int length = readVarint32();
        List<Integer> integers = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            integers.add(readInt());
        }
        return integers;
    }

    @Override
    public Stream writeInts(Collection<Integer> integers) {
        writeVarint32(integers.size());
        for (int integer : integers) {
            writeInt(integer);
        }
        return this;
    }

    @Override
    public long readLong() {
        return buf.readLong();
    }

    @Override
    public Stream writeLong(long value) {
        buf.writeLong(value);
        return this;
    }

    @Override
    public List<Long> readLongs() {
        int length = readVarint32();
        List<Long> longs = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            longs.add(readLong());
        }
        return longs;
    }

    @Override
    public Stream writeLongs(Collection<Long> longs) {
        writeVarint32(longs.size());
        for (long l : longs) {
            writeLong(l);
        }
        return this;
    }

    @Override
    public Set<Long> readLongSet() {
        int length = readVarint32();
        Set<Long> longs = CollectionUtil.newSetWithExpectedSize(length);
        for (int i = 0; i < length; i++) {
            longs.add(readLong());
        }
        return longs;
    }

    @Override
    public float readFloat() {
        return buf.readFloat();
    }

    @Override
    public Stream writeFloat(float value) {
        buf.writeFloat(value);
        return this;
    }

    @Override
    public double readDouble() {
        return buf.readDouble();
    }

    @Override
    public Stream writeDouble(double value) {
        buf.writeDouble(value);
        return this;
    }

    @Override
    public char readChar() {
        return buf.readChar();
    }

    @Override
    public Stream writeChar(int value) {
        buf.writeChar(value);
        return this;
    }

    @Override
    public boolean readBoolean() {
        return buf.readBoolean();
    }

    @Override
    public Stream writeBoolean(boolean value) {
        buf.writeBoolean(value);
        return this;
    }

    @Override
    @Nullable
    public String readString() {
        int length = readVarint32();
        if (length == 0) {
            return null;
        }
        return StringUtil.newString(readBytes(length), readByte());
    }

    @Override
    public Stream writeString(@Nullable String str) {
        if (str == null) {
            writeByte(0);
            return this;
        }
        byte[] bytes = StringUtil.getBytes(str);
        int length = bytes.length;
        writeVarint32(length);
        if (length > 0) {
            writeBytes(bytes).writeByte(StringUtil.getCoder(str));
        }
        return this;
    }

    @Override
    public List<String> readStrings() {
        int length = readVarint32();
        List<String> strings = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            strings.add(readString());
        }
        return strings;
    }

    @Override
    public Stream writeStrings(Collection<String> strings) {
        int size = strings.size();
        writeVarint32(size);
        for (String string : strings) {
            writeString(string);
        }
        return this;
    }

    @Override
    public Map<String, String> readStringMap() {
        int size = readVarint32();
        if (size == 0) {
            return Collections.emptyMap();
        }
        Map<String, String> map = Maps.newHashMapWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            map.put(readString(), readString());
        }
        return map;
    }

    @Override
    public Stream writeStringMap(@Nullable Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            writeByte(0);
            return this;
        }
        writeVarint32(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            writeString(entry.getKey());
            writeString(entry.getValue());
        }
        return this;
    }

    // Varint / ZigZag

    public static int computeVarint32Size(int value) {
        if ((value & (~0 << 7)) == 0) {
            return 1;
        }
        if ((value & (~0 << 14)) == 0) {
            return 2;
        }
        if ((value & (~0 << 21)) == 0) {
            return 3;
        }
        if ((value & (~0 << 28)) == 0) {
            return 4;
        }
        return 5;
    }

    @Override
    public Stream writeVarint32(int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                writeByte(value);
                return this;
            } else {
                writeByte((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    @Override
    public int readVarint32() {
        byte tmp = readByte();
        if (0 <= tmp) {
            return tmp;
        }
        int out = tmp & 0x7F;
        if ((tmp = readByte()) >= 0) {
            out |= tmp << 7;
        } else {
            out |= (tmp & 0x7F) << 7;
            if ((tmp = readByte()) >= 0) {
                out |= tmp << 14;
            } else {
                out |= (tmp & 0x7F) << 14;
                if ((tmp = readByte()) >= 0) {
                    out |= tmp << 21;
                } else {
                    out |= (tmp & 0x7F) << 21;
                    out |= (tmp = readByte()) << 28;
                    if (tmp < 0) {
                        // Discard upper 32 bits.
                        for (int i = 0; i < 5; i++) {
                            if (0 <= readByte()) {
                                return out;
                            }
                        }
                        throw new IllegalArgumentException("Failed to read varint32");
                    }
                }
            }
        }
        return out;
    }

    @Override
    public Stream writeIp(@Nullable byte[] ipBytes) {
        if (ipBytes == null) {
            writeByte(-1);
        } else {
            writeByte(ipBytes.length == IPV4_BYTE_LENGTH ? 0 : 1);
        }
        writeBytes(ipBytes);
        return this;
    }

    @Override
    @Nullable
    public byte[] readIp() {
        int flag = readByte();
        if (flag == -1) {
            return null;
        }
        return readBytes(flag == 0 ? IPV4_BYTE_LENGTH : IPV6_BYTE_LENGTH);
    }

    @Override
    public int readableBytes() {
        return buf.readableBytes();
    }

    @Override
    public ByteBuf readSlice(int length) {
        return buf.readSlice(length);
    }

    @Override
    public ByteBuf readRetainedSlice(int length) {
        return buf.readRetainedSlice(length);
    }

}