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

package im.turms.server.common.infra.cluster.service.codec.codec;

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
public final class CodecUtil {

    private CodecUtil() {
    }

    // Common

    public static void writeObject(ByteBuf output, Object obj) {
        Codec<Object> codec = CodecPool.getCodec(obj.getClass());
        codec.write(output, obj);
    }

    public static <T> T readObject(ByteBuf input) {
        int codecId = input.readShort();
        Codec<T> codec = CodecPool.getCodec(codecId);
        return codec.read(input);
    }

    public static <K, V> void writeMap(ByteBuf output, @Nullable Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            output.writeShort(0);
            return;
        }
        writeVarint32(output, map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            writeObject(output, entry.getKey());
            writeObject(output, entry.getValue());
        }
    }

    public static <K, V> Map<K, V> readMap(ByteBuf input) {
        int size = CodecUtil.readVarint32(input);
        if (size == 0) {
            return Collections.emptyMap();
        }
        Map<K, V> map = Maps.newHashMapWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            map.put(readObject(input), readObject(input));
        }
        return map;
    }

    // Specific Types

    public static void writeStringMap(ByteBuf output, @Nullable Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            output.writeByte(0);
            return;
        }
        writeVarint32(output, map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            writeString(output, entry.getKey());
            writeString(output, entry.getValue());
        }
    }

    public static Map<String, String> readStringMap(ByteBuf input) {
        int size = readVarint32(input);
        if (size == 0) {
            return Collections.emptyMap();
        }
        Map<String, String> map = Maps.newHashMapWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            map.put(readString(input), readString(input));
        }
        return map;
    }

    public static void writeString(ByteBuf output, @Nullable String str) {
        if (str == null) {
            output.writeByte(0);
            return;
        }
        byte[] bytes = StringUtil.getBytes(str);
        int length = bytes.length;
        writeVarint32(output, length);
        if (length > 0) {
            output.writeBytes(bytes)
                    .writeByte(StringUtil.getCoder(str));
        }
    }

    @Nullable
    public static String readString(ByteBuf input) {
        int length = readVarint32(input);
        if (length == 0) {
            return null;
        }
        byte[] bytes = new byte[length];
        input.readBytes(bytes);
        return StringUtil.newString(bytes, input.readByte());
    }

    public static void writeStrings(ByteBuf output, Collection<String> strings) {
        int size = strings.size();
        writeVarint32(output, size);
        for (String string : strings) {
            writeString(output, string);
        }
    }

    public static List<String> readStrings(ByteBuf input) {
        int length = readVarint32(input);
        List<String> strings = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            strings.add(readString(input));
        }
        return strings;
    }

    public static void writeInts(ByteBuf output, Collection<Integer> integers) {
        writeVarint32(output, integers.size());
        for (int integer : integers) {
            output.writeInt(integer);
        }
    }

    public static List<Integer> readInts(ByteBuf input) {
        int length = readVarint32(input);
        List<Integer> integers = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            integers.add(input.readInt());
        }
        return integers;
    }

    public static void writeLongs(ByteBuf output, Collection<Long> longs) {
        writeVarint32(output, longs.size());
        for (long l : longs) {
            output.writeLong(l);
        }
    }

    public static List<Long> readLongs(ByteBuf input) {
        int length = readVarint32(input);
        List<Long> longs = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            longs.add(input.readLong());
        }
        return longs;
    }

    public static Set<Long> readLongSet(ByteBuf input) {
        int length = readVarint32(input);
        Set<Long> longs = CollectionUtil.newSetWithExpectedSize(length);
        for (int i = 0; i < length; i++) {
            longs.add(input.readLong());
        }
        return longs;
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

    public static void writeVarint32(ByteBuf out, int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                out.writeByte(value);
                return;
            } else {
                out.writeByte((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    public static int readVarint32(ByteBuf in) {
        byte tmp = in.readByte();
        if (0 <= tmp) {
            return tmp;
        }
        int out = tmp & 0x7F;
        if ((tmp = in.readByte()) >= 0) {
            out |= tmp << 7;
        } else {
            out |= (tmp & 0x7F) << 7;
            if ((tmp = in.readByte()) >= 0) {
                out |= tmp << 14;
            } else {
                out |= (tmp & 0x7F) << 14;
                if ((tmp = in.readByte()) >= 0) {
                    out |= tmp << 21;
                } else {
                    out |= (tmp & 0x7F) << 21;
                    out |= (tmp = in.readByte()) << 28;
                    if (tmp < 0) {
                        // Discard upper 32 bits.
                        for (int i = 0; i < 5; i++) {
                            if (0 <= in.readByte()) {
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

    // IP

    public static void writeIp(ByteBuf output, @Nullable byte[] ipBytes) {
        if (ipBytes == null) {
            output.writeByte(-1);
        } else {
            output.writeByte(ipBytes.length == IPV4_BYTE_LENGTH ? 0 : 1);
        }
        output.writeBytes(ipBytes);
    }

    @Nullable
    public static byte[] readIp(ByteBuf input) {
        int flag = input.readByte();
        if (flag == -1) {
            return null;
        }
        byte[] ipBytes = new byte[flag == 0 ? IPV4_BYTE_LENGTH : IPV6_BYTE_LENGTH];
        input.readBytes(ipBytes);
        return ipBytes;
    }

}
