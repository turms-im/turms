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

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.serialization.DeserializationException;
import im.turms.server.common.infra.serialization.SerializationException;

import static im.turms.server.common.infra.net.InetAddressUtil.IPV4_BYTE_LENGTH;
import static im.turms.server.common.infra.net.InetAddressUtil.IPV6_BYTE_LENGTH;

/**
 * @author James Chen
 */
public class Stream implements AutoCloseable, StreamInput, StreamOutput {

    final ByteBuf buf;

    public Stream(ByteBuf buf) {
        this.buf = buf;
    }

    public Stream(ByteBuffer byteBuffer) {
        buf = Unpooled.wrappedBuffer(byteBuffer);
    }

    public ByteBuf getBuffer() {
        return buf;
    }

    @Override
    public void close() throws Exception {
        buf.release();
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
    public int[] readInts() {
        int size = readVarint32();
        if (size == 0) {
            throw new DeserializationException("The int array is null");
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The int array size is negative: "
                            + size);
        }
        size--;
        int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            ints[i] = readInt();
        }
        return ints;
    }

    @Override
    public List<Integer> readIntList() {
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
    public List<Integer> readNullableIntList() {
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
    public Stream writeSizeAndInts(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("The input integers must not be null");
        }
        writeVarint32(values.length + 1);
        for (int integer : values) {
            writeInt(integer);
        }
        return this;
    }

    @Override
    public Stream writeSizeAndInts(Collection<Integer> values) {
        if (values == null) {
            throw new IllegalArgumentException("The input integers must not be null");
        }
        writeVarint32(values.size() + 1);
        for (int integer : values) {
            writeInt(integer);
        }
        return this;
    }

    @Override
    public Stream writeSizeAndNullableInts(@Nullable Collection<Integer> values) {
        if (values == null) {
            writeByte(0);
            return this;
        }
        writeVarint32(values.size() + 1);
        for (int integer : values) {
            writeInt(integer);
        }
        return this;
    }

    @Override
    public int[] readSparseInts() {
        int size = readVarint32();
        if (size == 0) {
            throw new DeserializationException("The int array is null");
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The int array size is negative: "
                            + size);
        }
        if (size == 1) {
            return new int[0];
        }
        int nonZeroValuesCount = readInt();
        size--;
        int[] ints = new int[size];
        for (int i = 0; i < nonZeroValuesCount; i++) {
            ints[readVarint32()] = readInt();
        }
        return ints;
    }

    @Override
    public Stream writeSizeAndSparseInts(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("The input integers must not be null");
        }
        int length = values.length;
        writeVarint32(length + 1);
        if (length == 0) {
            return this;
        }
        int nonZeroValueCountWriterIndex = buf.writerIndex();
        buf.writerIndex(buf.writerIndex() + Integer.BYTES);
        int nonZeroValueCount = 0;
        int integer;
        for (int i = 0; i < length; i++) {
            integer = values[i];
            if (integer != 0) {
                nonZeroValueCount++;
                writeVarint32(i);
                writeInt(integer);
            }
        }
        buf.setInt(nonZeroValueCountWriterIndex, nonZeroValueCount);
        return this;
    }

    @Override
    public int[][] readSparseInt2DArray() {
        int size = readVarint32();
        if (size == 0) {
            throw new DeserializationException("The int 2D array is null");
        }
        if (size < 0) {
            throw new DeserializationException(
                    "The int 2D array size is negative: "
                            + size);
        }
        if (size == 1) {
            return new int[0][];
        }
        int nonNullValueCount = readInt();
        size--;
        int[][] int2dArray = new int[size][];
        int index;
        for (int i = 0; i < nonNullValueCount; i++) {
            index = readVarint32();
            int2dArray[index] = readInts();
        }
        return int2dArray;
    }

    @Override
    public Stream writeSizeAndSparseInt2DArray(int[][] values) {
        if (values == null) {
            throw new IllegalArgumentException("The input integers must not be null");
        }
        int length = values.length;
        writeVarint32(length + 1);
        if (length == 0) {
            return this;
        }
        int nonNullValueCountWriterIndex = buf.writerIndex();
        buf.writerIndex(nonNullValueCountWriterIndex + Integer.BYTES);
        int nonNullValueCount = 0;
        for (int i = 0; i < length; i++) {
            int[] ints = values[i];
            if (ints != null) {
                nonNullValueCount++;
                writeVarint32(i);
                writeSizeAndInts(ints);
            }
        }
        buf.setInt(nonNullValueCountWriterIndex, nonNullValueCount);
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

    @Nullable
    @Override
    public List<Long> readNullableLongList() {
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
    public Stream writeSizeAndLongs(Collection<Long> values) {
        if (values == null) {
            throw new IllegalArgumentException("The input long collection must not be null");
        }
        writeVarint32(values.size() + 1);
        for (long l : values) {
            writeLong(l);
        }
        return this;
    }

    @Override
    public Stream writeSizeAndNullableLongs(@Nullable Collection<Long> values) {
        if (values == null) {
            writeByte(0);
            return this;
        }
        writeVarint32(values.size() + 1);
        for (long l : values) {
            writeLong(l);
        }
        return this;
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
    public char[] readChars() {
        try {
            int length = readVarint32();
            if (length == 0) {
                throw new DeserializationException("The chars are null");
            }
            if (length < 0) {
                throw new DeserializationException(
                        "The chars length is negative: "
                                + length);
            }
            length--;
            char[] chars = new char[length];
            for (int i = 0; i < length; i++) {
                chars[i] = readChar();
            }
            return chars;
        } catch (Exception e) {
            throw new DeserializationException("Failed to read chars", e);
        }
    }

    @Override
    public Stream writeSizeAndChars(char[] values) {
        try {
            int length = values.length;
            writeVarint32(length + 1);
            for (char value : values) {
                buf.writeChar(value);
            }
        } catch (Exception e) {
            throw new SerializationException("Failed to write chars", e);
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
    public Stream writeString(String value) {
        byte[] bytes = StringUtil.getBytes(value);
        int length = bytes.length;
        writeVarint32(length + 1);
        if (length > 0) {
            writeBytes(bytes).writeByte(StringUtil.getCoder(value));
        }
        return this;
    }

    @Override
    public Stream writeNullableString(@Nullable String value) {
        if (value == null) {
            writeByte(0);
            return this;
        }
        byte[] bytes = StringUtil.getBytes(value);
        int length = bytes.length;
        writeVarint32(length + 1);
        if (length > 0) {
            writeBytes(bytes).writeByte(StringUtil.getCoder(value));
        }
        return this;
    }

    @Override
    public List<String> readStringList() {
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
    public List<String> readNullableStringList() {
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
    public Stream writeSizeAndStrings(Collection<String> values) {
        if (values == null) {
            throw new IllegalArgumentException("The input string collection must not be null");
        }
        int size = values.size();
        writeVarint32(size + 1);
        for (String string : values) {
            writeNullableString(string);
        }
        return this;
    }

    @Override
    public Stream writeSizeAndNullableStrings(@Nullable Collection<String> values) {
        if (values == null) {
            writeByte(0);
            return this;
        }
        int size = values.size();
        writeVarint32(size + 1);
        for (String string : values) {
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
    public Stream writeSizeAndStringMap(Map<String, String> map) {
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
    public Stream writeSizeAndNullableStringMap(@Nullable Map<String, String> map) {
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
        byte type = readByte();
        return switch (type) {
            case -1 -> null;
            case 0 -> readBytes(IPV4_BYTE_LENGTH);
            case 1 -> readBytes(IPV6_BYTE_LENGTH);
            default -> throw new DeserializationException(
                    "Unknown IP type: "
                            + type);
        };
    }

    @Override
    public int readableBytes() {
        return buf.readableBytes();
    }

    @Override
    public boolean isReadable() {
        return buf.isReadable();
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