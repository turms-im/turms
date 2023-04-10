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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.serialization.DeserializationException;
import im.turms.server.common.infra.serialization.SerializationException;

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
        try {
            return buf.readByte();
        } catch (Exception e) {
            throw new DeserializationException("Failed to read a byte", e);
        }
    }

    @Override
    public Stream writeByte(int value) {
        try {
            buf.writeByte(value);
        } catch (Exception e) {
            throw new SerializationException("Failed to write a byte", e);
        }
        return this;
    }

    @Override
    public byte[] readBytes(int length) {
        byte[] bytes = new byte[length];
        try {
            buf.readBytes(bytes);
        } catch (Exception e) {
            throw new DeserializationException("Failed to read bytes", e);
        }
        return bytes;
    }

    @Override
    public Stream writeBytes(byte[] bytes) {
        try {
            buf.writeBytes(bytes);
        } catch (Exception e) {
            throw new SerializationException("Failed to write bytes", e);
        }
        return this;
    }

    @Override
    public short readShort() {
        try {
            return buf.readShort();
        } catch (Exception e) {
            throw new DeserializationException("Failed to read a short", e);
        }
    }

    @Override
    public Stream writeShort(int value) {
        try {
            buf.writeShort(value);
        } catch (Exception e) {
            throw new SerializationException("Failed to write a short", e);
        }
        return this;
    }

    @Override
    public int readInt() {
        try {
            return buf.readInt();
        } catch (Exception e) {
            throw new DeserializationException("Failed to read an int", e);
        }
    }

    @Override
    public Stream writeInt(int value) {
        try {
            buf.writeInt(value);
        } catch (Exception e) {
            throw new SerializationException("Failed to write an int", e);
        }
        return this;
    }

    @Override
    public List<Integer> readInts() {
        int size = readVarint32();
        if (size == 0) {
            throw new DeserializationException("The int list is null");
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The int list size is negative: "
                            + size);
        }
        size--;
        List<Integer> integers = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            integers.add(readInt());
        }
        return integers;
    }

    @Nullable
    @Override
    public List<Integer> readNullableInts() {
        int size = readVarint32();
        if (size == 0) {
            return null;
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The int list size is negative: "
                            + size);
        }
        size--;
        List<Integer> integers = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            integers.add(readInt());
        }
        return integers;
    }

    @Override
    public Stream writeInts(Collection<Integer> integers) {
        if (integers == null) {
            throw new IllegalArgumentException("The input integers must not be null");
        }
        writeVarint32(integers.size() + 1);
        for (int integer : integers) {
            writeInt(integer);
        }
        return this;
    }

    @Override
    public Stream writeNullableInts(@Nullable Collection<Integer> integers) {
        if (integers == null) {
            writeByte(0);
            return this;
        }
        writeVarint32(integers.size() + 1);
        for (int integer : integers) {
            writeInt(integer);
        }
        return this;
    }

    @Override
    public long readLong() {
        try {
            return buf.readLong();
        } catch (Exception e) {
            throw new DeserializationException("Failed to read a long", e);
        }
    }

    @Override
    public Stream writeLong(long value) {
        try {
            buf.writeLong(value);
        } catch (Exception e) {
            throw new SerializationException("Failed to write a long", e);
        }
        return this;
    }

    @Override
    public List<Long> readLongs() {
        int size = readVarint32();
        if (size == 0) {
            throw new DeserializationException("The long list is null");
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The long list size is negative: "
                            + size);
        }
        size--;
        List<Long> longs = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            longs.add(readLong());
        }
        return longs;
    }

    @Nullable
    @Override
    public List<Long> readNullableLongs() {
        int size = readVarint32();
        if (size == 0) {
            return null;
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The long list size is negative: "
                            + size);
        }
        size--;
        List<Long> longs = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            longs.add(readLong());
        }
        return longs;
    }

    @Override
    public Stream writeLongs(Collection<Long> longs) {
        if (longs == null) {
            throw new IllegalArgumentException("The input long collection must not be null");
        }
        writeVarint32(longs.size() + 1);
        for (long l : longs) {
            writeLong(l);
        }
        return this;
    }

    @Override
    public Stream writeNullableLongs(@Nullable Collection<Long> longs) {
        if (longs == null) {
            writeByte(0);
            return this;
        }
        writeVarint32(longs.size() + 1);
        for (long l : longs) {
            writeLong(l);
        }
        return this;
    }

    @Override
    public Set<Long> readLongSet() {
        int size = readVarint32();
        if (size == 0) {
            throw new DeserializationException("The long set is null");
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The long set size is negative: "
                            + size);
        }
        size--;
        Set<Long> longs = CollectionUtil.newSetWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            longs.add(readLong());
        }
        return longs;
    }

    @Nullable
    @Override
    public Set<Long> readNullableLongSet() {
        int size = readVarint32();
        if (size == 0) {
            return null;
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The long set size is negative: "
                            + size);
        }
        size--;
        Set<Long> longs = CollectionUtil.newSetWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            longs.add(readLong());
        }
        return longs;
    }

    @Override
    public float readFloat() {
        try {
            return buf.readFloat();
        } catch (Exception e) {
            throw new DeserializationException("Failed to read a float", e);
        }
    }

    @Override
    public Stream writeFloat(float value) {
        try {
            buf.writeFloat(value);
        } catch (Exception e) {
            throw new SerializationException("Failed to write a float", e);
        }
        return this;
    }

    @Override
    public double readDouble() {
        try {
            return buf.readDouble();
        } catch (Exception e) {
            throw new DeserializationException("Failed to read a double", e);
        }
    }

    @Override
    public Stream writeDouble(double value) {
        try {
            buf.writeDouble(value);
        } catch (Exception e) {
            throw new SerializationException("Failed to write a double", e);
        }
        return this;
    }

    @Override
    public char readChar() {
        try {
            return buf.readChar();
        } catch (Exception e) {
            throw new DeserializationException("Failed to read a char", e);
        }
    }

    @Override
    public Stream writeChar(int value) {
        try {
            buf.writeChar(value);
        } catch (Exception e) {
            throw new SerializationException("Failed to write a char", e);
        }
        return this;
    }

    @Override
    public boolean readBoolean() {
        try {
            return buf.readBoolean();
        } catch (Exception e) {
            throw new DeserializationException("Failed to read a boolean", e);
        }
    }

    @Override
    public Stream writeBoolean(boolean value) {
        try {
            buf.writeBoolean(value);
        } catch (Exception e) {
            throw new SerializationException("Failed to write a boolean", e);
        }
        return this;
    }

    @Override
    public String readString() {
        int length = readVarint32();
        if (length == 0) {
            throw new DeserializationException("The string is null");
        }
        if (length < 0) {
            throw new DeserializationException(
                    "The string length is negative: "
                            + length);
        }
        length--;
        byte[] bytes = readBytes(length);
        return StringUtil.newString(bytes, readByte());
    }

    @Override
    @Nullable
    public String readNullableString() {
        int length = readVarint32();
        if (length == 0) {
            return null;
        }
        if (length < 0) {
            throw new DeserializationException(
                    "The string length is negative: "
                            + length);
        }
        length--;
        byte[] bytes = readBytes(length);
        return StringUtil.newString(bytes, readByte());
    }

    @Override
    public Stream writeString(String str) {
        byte[] bytes = StringUtil.getBytes(str);
        int length = bytes.length;
        writeVarint32(length + 1);
        if (length > 0) {
            writeBytes(bytes).writeByte(StringUtil.getCoder(str));
        }
        return this;
    }

    @Override
    public Stream writeNullableString(@Nullable String str) {
        if (str == null) {
            writeByte(0);
            return this;
        }
        byte[] bytes = StringUtil.getBytes(str);
        int length = bytes.length;
        writeVarint32(length + 1);
        if (length > 0) {
            writeBytes(bytes).writeByte(StringUtil.getCoder(str));
        }
        return this;
    }

    @Override
    public List<String> readStrings() {
        int size = readVarint32();
        if (size == 0) {
            throw new DeserializationException("The string list is null");
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The string list size is negative: "
                            + size);
        }
        size--;
        List<String> strings = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            strings.add(readNullableString());
        }
        return strings;
    }

    @Nullable
    @Override
    public List<String> readNullableStrings() {
        int size = readVarint32();
        if (size == 0) {
            return null;
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The string list size is negative: "
                            + size);
        }
        size--;
        List<String> strings = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            strings.add(readNullableString());
        }
        return strings;
    }

    @Override
    public Stream writeStrings(Collection<String> strings) {
        if (strings == null) {
            throw new IllegalArgumentException("The input string collection must not be null");
        }
        int size = strings.size();
        writeVarint32(size + 1);
        for (String string : strings) {
            writeNullableString(string);
        }
        return this;
    }

    @Override
    public Stream writeNullableStrings(@Nullable Collection<String> strings) {
        if (strings == null) {
            writeByte(0);
            return this;
        }
        int size = strings.size();
        writeVarint32(size + 1);
        for (String string : strings) {
            writeNullableString(string);
        }
        return this;
    }

    @Override
    public Map<String, String> readStringMap() {
        int size = readVarint32();
        if (size == 0) {
            throw new DeserializationException("The string map is null");
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The string map size is negative: "
                            + size);
        }
        size--;
        Map<String, String> map = CollectionUtil.newMapWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            map.put(readString(), readNullableString());
        }
        return map;
    }

    @Nullable
    @Override
    public Map<String, String> readNullableStringMap() {
        int size = readVarint32();
        if (size == 0) {
            return null;
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The string map size is negative: "
                            + size);
        }
        size--;
        Map<String, String> map = CollectionUtil.newMapWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            map.put(readString(), readNullableString());
        }
        return map;
    }

    @Override
    public Stream writeStringMap(@Nullable Map<String, String> map) {
        if (map == null) {
            throw new IllegalArgumentException("The input string map must not be null");
        }
        writeVarint32(map.size() + 1);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            writeString(entry.getKey());
            writeNullableString(entry.getValue());
        }
        return this;
    }

    @Override
    public Stream writeNullableStringMap(@Nullable Map<String, String> map) {
        if (map == null) {
            writeByte(0);
            return this;
        }
        writeVarint32(map.size() + 1);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            writeString(entry.getKey());
            writeNullableString(entry.getValue());
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
                        throw new DeserializationException("Failed to read a varint32");
                    }
                }
            }
        }
        return out;
    }

    @Override
    public Stream writeNullableIp(@Nullable byte[] ipBytes) {
        if (ipBytes == null) {
            writeByte(-1);
            return this;
        }
        writeByte(ipBytes.length == IPV4_BYTE_LENGTH
                ? 0
                : 1);
        writeBytes(ipBytes);
        return this;
    }

    @Override
    @Nullable
    public byte[] readNullableIp() {
        int flag = readByte();
        if (flag == -1) {
            return null;
        }
        return readBytes(flag == 0
                ? IPV4_BYTE_LENGTH
                : IPV6_BYTE_LENGTH);
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